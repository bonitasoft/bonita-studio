/**
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connectors.ui.wizard.custom.webservice;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javax.xml.namespace.QName;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.wizard.AbstractConnectorConfigurationWizardPage;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.connectors.ui.wizard.custom.webservice.WSDLParsingTool.SOAP_PROTOCOL;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.expression.editor.provider.ExpressionLabelProvider;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionSynchronizer;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewerCellEditor;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.AbstractExpression;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.xml.ui.SelectPathDialog;
import org.bonitasoft.studio.xml.ui.XSDContentProvider;
import org.bonitasoft.studio.xml.ui.XSDLabelProvider;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.w3c.dom.Document;

/**
 * @author Florine Boudin
 */
public class SecuredWSConnectorWizardPage extends AbstractConnectorConfigurationWizardPage {

    static final String ENVELOPPE = "envelope";
    static final String SERVICE_NS = "serviceNS";
    static final String SERVICE_NAME = "serviceName";
    static final String PORT_NAME = "portName";
    static final String BINDING_NAME = "binding";
    static final String ENDPOINT_ADRESS = "endpointAddress";
    static final String SOAP_ACTION = "soapAction";
    static final String BUILD_DOCUMENT_RESPONSE = "sourceResponse";
    static final String PRINT_REQUEST_AND_RESPONSE = "printRequestAndResponse";
    static final String USERNAME = "userName";
    static final String PASSWORD = "password";

    class WSDLCredentialDialog extends Dialog {

        private Text usernameText;
        private Text passwordText;

        protected WSDLCredentialDialog(final Shell parentShell) {
            super(parentShell);
        }

        @Override
        protected void configureShell(final Shell newShell) {
            super.configureShell(newShell);
            newShell.setText("Login");
        }

        @Override
        protected Control createDialogArea(final Composite parent) {
            final Composite main = new Composite(parent, SWT.NONE);
            main.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
            final GridLayout gl = new GridLayout(2, false);
            gl.horizontalSpacing = 10;
            gl.verticalSpacing = 15;
            main.setLayout(gl);

            final Label name = new Label(main, SWT.NONE);
            name.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
            name.setText(getMessageProvider().getFieldLabel(getDefinition(), USERNAME));
            usernameText = new Text(main, SWT.BORDER);
            usernameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
            if (username != null) {
                usernameText.setText(username);
            }
            final Label pass = new Label(main, SWT.NONE);
            pass.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
            pass.setText(getMessageProvider().getFieldLabel(getDefinition(), PASSWORD));
            passwordText = new Text(main, SWT.PASSWORD | SWT.BORDER);
            passwordText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
            if (password != null) {
                passwordText.setText(password);
            }
            return parent;
        }

        @Override
        protected void okPressed() {
            username = usernameText.getText();
            password = passwordText.getText();
            super.okPressed();
        }

        protected String getUsername() {
            return username;
        }

        protected String getPassword() {
            return password;
        }
    }

    private String username;
    private String password;

    private String wsdlURL;
    private QName serviceQName;
    private String portName;
    private String operationName;
    private final Map<String, Expression> elements = new HashMap<String, Expression>();

    private XSDContentProvider contentProvider;
    boolean clickedIntrospect;
    private Map<Object, String> elementTreePath;
    private Map<String, Object> elementPathToTreeItem;
    private XSDElementDeclaration outputElement;
    // UI
    private ComboViewer serviceNameCombo;
    private Button refreshButton;
    private ComboViewer portCombo;
    private ComboViewer operationViewer;
    private TreeViewer paramViewer;
    private Combo wsdlLocationText;
    private WSDLParsingTool wsdlParsingTool;
    private ComboViewer endPointViewer;
    private String envelope;
    private Map<String, String> prefixMap;

    public SecuredWSConnectorWizardPage() {
        setImageDescriptor(Pics.getWizban());
        setTitle(org.bonitasoft.studio.connectors.i18n.Messages.configureXMLWebServiceTitle);
        setDescription(Messages.configureXMLWebServiceDescription);
    }

    @Override
    public Control doCreateControl(final Composite parent,
            final EMFDataBindingContext context) {
        final Composite res = new Composite(parent, SWT.NONE);
        res.setLayout(new GridLayout(3, false));

        // wsdl
        final Label wsdlLocationLabel = new Label(res, SWT.NONE);
        wsdlLocationLabel.setText(Messages.wsdlLocation);
        wsdlLocationLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        wsdlLocationText = new Combo(res, SWT.BORDER);
        for (final String knownWsdl : WSDLPreferences.getKnownsWsdls()) {
            wsdlLocationText.add(knownWsdl);
        }
        //TODO Implement Browse for Windows
        //wsdlLocationText.add("Browse...") ;

        wsdlLocationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        refreshButton = new Button(res, SWT.PUSH);
        refreshButton.setText(Messages.readWSDL);
        // service
        final Label serviceLabel = new Label(res, SWT.NONE);
        serviceLabel.setText(Messages.service);
        serviceLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        serviceNameCombo = new ComboViewer(res);
        serviceNameCombo.setContentProvider(ArrayContentProvider.getInstance());
        serviceNameCombo.setLabelProvider(new LabelProvider() {

            @Override
            public String getText(final Object item) {
                return ((QName) item).getLocalPart();
            }
        });
        serviceNameCombo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
        // binding
        final Label portLabel = new Label(res, SWT.NONE);
        portLabel.setText(Messages.port);
        portLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        portCombo = new ComboViewer(res);
        portCombo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
        portCombo.setContentProvider(ArrayContentProvider.getInstance());
        portCombo.setLabelProvider(new LabelProvider());
        // operation
        final Label operationLabel = new Label(res, SWT.None);
        operationLabel.setText(Messages.operation);
        operationLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        operationViewer = new ComboViewer(res);
        operationViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, true, false, 2, 1));
        operationViewer.setLabelProvider(new LabelProvider());
        operationViewer.setContentProvider(ArrayContentProvider.getInstance());
        final Label endPointLabel = new Label(res, SWT.NONE);
        endPointLabel.setText(getMessageProvider().getFieldLabel(getDefinition(), ENDPOINT_ADRESS));
        endPointLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());

        endPointViewer = new ComboViewer(res);
        GridDataFactory.fillDefaults().grab(true, false).span(2, 1).applyTo(endPointViewer.getControl());
        endPointViewer.setLabelProvider(new LabelProvider());
        endPointViewer.setContentProvider(ArrayContentProvider.getInstance());
        // parameters
        final Label paramLabel = new Label(res, SWT.NONE);
        paramLabel.setText(Messages.parameters);
        paramLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.TOP).create());

        paramViewer = new TreeViewer(res, SWT.BORDER | SWT.FULL_SELECTION);
        paramViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).span(2, 1).create());

        contentProvider = new XSDContentProvider(false);
        paramViewer.setContentProvider(contentProvider);
        paramViewer.getTree().setHeaderVisible(true);

        final TreeViewerColumn paramNameColumn = new TreeViewerColumn(paramViewer, SWT.DEFAULT);
        paramNameColumn.getColumn().setWidth(300);
        paramNameColumn.getColumn().setText(Messages.parameter);
        paramNameColumn.getColumn().setResizable(true);
        paramNameColumn.getColumn().setMoveable(true);
        paramNameColumn.setLabelProvider(new XSDLabelProvider(true));
        final TreeViewerColumn valueColumn = new TreeViewerColumn(paramViewer, SWT.DEFAULT);
        valueColumn.getColumn().setWidth(200);
        valueColumn.getColumn().setText(Messages.value);
        valueColumn.getColumn().setResizable(true);
        valueColumn.getColumn().setMoveable(true);
        final ExpressionLabelProvider expressionLabelProvider = new ExpressionLabelProvider();
        valueColumn.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(final Object item) {
                Expression res = null;
                if (elementTreePath.get(item) != null) {
                    res = elements.get(elementTreePath.get(item));
                }
                if (res == null) {
                    return "";
                } else {
                    return expressionLabelProvider.getText(res);
                }
            }

            @Override
            public Image getImage(final Object item) {
                Expression res = null;
                if (elementTreePath.get(item) != null) {
                    res = elements.get(elementTreePath.get(item));
                }
                if (res == null) {
                    return super.getImage(item);
                } else {
                    return expressionLabelProvider.getImage(res);
                }

            }
        });
        valueColumn.setEditingSupport(new EditingSupport(valueColumn.getViewer()) {

            @Override
            protected CellEditor getCellEditor(final Object element) {
                final ExpressionViewerCellEditor cellEditor = new ExpressionViewerCellEditor(valueColumn.getViewer(),
                        (Composite) valueColumn.getViewer()
                                .getControl(),
                        null, null);
                cellEditor.addFilter(new AvailableExpressionTypeFilter(
                        new String[] { ExpressionConstants.CONSTANT_TYPE, ExpressionConstants.VARIABLE_TYPE,
                                ExpressionConstants.PARAMETER_TYPE }));
                cellEditor.setContext(getElementContainer());
                cellEditor.setInput(getElementContainer());
                if (elementTreePath.get(element) != null) {
                    cellEditor.setSelection(new StructuredSelection(elements.get(elementTreePath.get(element))));
                } else {
                    cellEditor.setSelection(
                            new StructuredSelection(ExpressionHelper.createConstantExpression("", String.class.getName())));
                }
                return cellEditor;
            }

            @Override
            protected boolean canEdit(final Object element) {
                return true;
            }

            @Override
            protected Object getValue(final Object element) {
                return element;
            }

            @Override
            protected void setValue(final Object element, final Object value) {
                updateValues();
                getViewer().refresh();
                getViewer().getControl().notifyListeners(SWT.Selection, new Event());
            }

            @Override
            protected void saveCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
                super.saveCellEditorValue(cellEditor, cell);
                getViewer().refresh();
            }

        });

        //ADD LISTENERS
        wsdlLocationText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(final ModifyEvent e) {
                wsdlURL = wsdlLocationText.getText();
                clickedIntrospect = false;
                refreshButton.setEnabled(!wsdlURL.isEmpty());
                updateValues();
            }
        });
        refreshButton.setEnabled(!wsdlLocationText.getText().isEmpty());
        operationViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final String operationName = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (operationName != null) {
                    SecuredWSConnectorWizardPage.this.operationName = operationName;
                    updateParamTreeViewer(operationName);
                    updateValues();
                }
            }
        });
        portCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final String port = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
                if (!port.equals(portName)) {
                    selectPort(port);
                }
            }
        });
        serviceNameCombo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final QName service = (QName) ((IStructuredSelection) event.getSelection()).getFirstElement();
                selectService(service);
            }
        });
        refreshButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                try {
                    final WSDLCredentialDialog dialog = new WSDLCredentialDialog(Display.getDefault().getActiveShell());
                    if (dialog.open() == Dialog.OK) {
                        serviceQName = null;
                        operationName = null;
                        portName = null;
                        username = dialog.getUsername();
                        password = dialog.getPassword();
                        refreshWSDL(wsdlLocationText.getText(), username, password);
                        updateValues();
                    }
                } catch (final Throwable ex) {
                    BonitaStudioLog.error(ex);
                    openErrorPopup(ex);
                }
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {
            }
        });

        setControl(res);
        return res;
    }

    private void openErrorPopup(final Throwable ex) {
        Throwable rootEx = ex;
        while (rootEx.getCause() != null) {
            rootEx = rootEx.getCause();
        }
        new BonitaErrorDialog(getShell(),
                Messages.errorIntrospectTitle,
                Messages.errorIntrospectMessage,
                new Status(Status.ERROR, ConnectorPlugin.PLUGIN_ID, rootEx.getLocalizedMessage(), rootEx),
                Status.ERROR)
                        .open();
    }

    private void refreshWSDL(final String url, final String authUserName, final String authPassword) throws Throwable {

        wsdlParsingTool = new WSDLParsingTool(url, authUserName, authPassword);

        Collection<?> input = (Collection<?>) endPointViewer.getInput();
        if (input != null) {
            endPointViewer.remove(input.toArray());
        }
        endPointViewer.setSelection(new StructuredSelection());
        input = (Collection<?>) operationViewer.getInput();
        if (input != null) {
            operationViewer.remove(input.toArray());
        }
        operationViewer.setSelection(new StructuredSelection());
        input = (Collection<?>) serviceNameCombo.getInput();
        if (input != null) {
            serviceNameCombo.remove(input.toArray());
        }
        serviceNameCombo.setSelection(new StructuredSelection());
        serviceQName = null;
        envelope = null;
        operationName = null;
        outputElement = null;
        if (elementPathToTreeItem != null) {
            elementPathToTreeItem.clear();
        }
        if (elements != null) {
            elements.clear();
        }
        if (elementTreePath != null) {
            elementTreePath.clear();
        }

        final Set<QName> availableServices = wsdlParsingTool.getAvailableServices();
        if (availableServices != null && !availableServices.isEmpty()) {
            WSDLPreferences.addKnownWSDL(new URL(url));
            clickedIntrospect = true;
            refreshButton.setEnabled(false);

            serviceNameCombo.setInput(availableServices);
            QName service = null;
            service = availableServices.iterator().next();
            selectService(service);
            serviceNameCombo.setSelection(new StructuredSelection(service));
        }
        portCombo.refresh();
    }

    /**
     * @param serviceQName
     */
    protected void selectService(final QName serviceQName) {
        if (serviceQName == null) {
            this.serviceQName = null;
        } else {
            this.serviceQName = serviceQName;
        }
        if (serviceQName == null) {
            return;
        }
        final Set<String> availablePorts = wsdlParsingTool.getAvailablePorts(serviceQName);
        if (!availablePorts.isEmpty()) {
            portCombo.setInput(availablePorts);
            final String port = availablePorts.iterator().next();
            if (Arrays.asList(portCombo.getCombo().getItems()).contains(portName)) {
                portCombo.setSelection(new StructuredSelection(portName));
            } else {
                portCombo.setSelection(new StructuredSelection(port));
            }
        } else {
            portCombo.setInput(Collections.emptySet());
            portCombo.setSelection(new StructuredSelection());
        }
        updateValues();
    }

    /**
     * @return
     */
    public XSDElementDeclaration getOutputType() {
        return outputElement;
    }

    /**
     * @param port
     */
    protected void selectPort(final String port) {
        if (port != null) {
            portName = port;
            final Set<String> availableOperations = wsdlParsingTool.getAvailableOperations(serviceQName, port);
            operationViewer.setInput(availableOperations);
            if (!availableOperations.isEmpty()) {
                operationViewer.setSelection(new StructuredSelection(availableOperations.iterator().next()));
            } else {
                operationViewer.setSelection(new StructuredSelection());
            }
            final Set<String> availableEndpoints = wsdlParsingTool.getAvailableEndpoints(wsdlURL, serviceQName, port);
            endPointViewer.setInput(availableEndpoints);
            if (!availableEndpoints.isEmpty()) {
                endPointViewer.setSelection(new StructuredSelection(availableEndpoints.iterator().next()));
            } else {
                endPointViewer.setSelection(new StructuredSelection());
            }
            updateValues();
        } else {
            operationViewer.setSelection(new StructuredSelection());
            endPointViewer.setSelection(new StructuredSelection());
        }
    }

    /**
     * @param operationName
     */
    public void updateParamTreeViewer(final String operationName) {
        final XSDElementDeclaration operationPart = wsdlParsingTool.getOperationPart(serviceQName, portName, operationName);
        if (operationPart == null) {
            resetElementTreePath();
        }
        if (operationPart != null) {
            prefixMap = operationPart.getSchema().getQNamePrefixToNamespaceMap();
        }
        contentProvider.setElement(operationPart);
        updateElementTreePath(contentProvider);
        paramViewer.getControl().setEnabled(operationPart != null);
        // Output
        final XSDElementDeclaration elementDeclaration = wsdlParsingTool.getOperationOutPut(serviceQName, portName,
                operationName);
        if (elementDeclaration != null
                && !elementDeclaration.equals(outputElement)) {
            outputElement = elementDeclaration;
            //FIXME add xsd to the repository
            //			XSDSchema schema = outputElement.getSchema();
            //			String xsdID = outputElement.getName()+".xsd";
            //			if (XSDRepository.getInstance().getArtifact(xsdID) == null) {
            //				XSDRepository.getInstance().createArtifact(schema,
            //						xsdID, new NullProgressMonitor());
            //			}
        }

        paramViewer.setInput(new Object());
        paramViewer.expandAll();
    }

    /**
     * @param contentProvider2
     */
    protected void updateElementTreePath(final XSDContentProvider contentProvider) {
        resetElementTreePath();
        final Stack<Object> stack = new Stack<Object>();

        populateTreePath(contentProvider, stack, XSDContentProvider.WHOLE_XML);
    }

    /**
     *
     */
    protected void resetElementTreePath() {
        elementTreePath = new HashMap<Object, String>();
        elementPathToTreeItem = new HashMap<String, Object>();
    }

    /**
     * @param elementTreePath2
     * @param stack
     * @param element
     */
    private void populateTreePath(final XSDContentProvider contentProvider, final Stack<Object> stack,
            final Object element) {
        stack.push(element);
        final TreePath path = new TreePath(stack.toArray());
        final String xpath = SelectPathDialog.computeXPath(path, true);
        elementTreePath.put(element, xpath);
        elementPathToTreeItem.put(xpath, element);
        final Object[] children = contentProvider.getChildren(element);
        elements.put(xpath, ExpressionHelper.createConstantExpression("", String.class.getName()));//put an empty string as child value
        for (final Object child : children) {
            if (child != null) {
                populateTreePath(contentProvider, stack, child);
            }
        }
        stack.pop();
    }

    public void updateValues() {

        final Map<String, Expression> concreteParams = new HashMap<String, Expression>();
        for (final Entry<String, Expression> entry : elements.entrySet()) {
            if (elementPathToTreeItem.containsKey(entry.getKey()) && entry.getValue() != null) {
                if (entry.getValue().getContent() == null || entry.getValue().getContent().isEmpty()) {
                    final Object object = elementPathToTreeItem.get(entry.getKey());
                    if (object instanceof XSDAttributeDeclaration) {
                        concreteParams.put(entry.getKey(), entry.getValue());
                    }
                    if (object instanceof XSDElementDeclaration) {
                        if (!(((XSDElementDeclaration) object).getType() instanceof XSDComplexTypeDefinition)
                                || ((XSDElementDeclaration) object).eContents().size() == 0) {
                            concreteParams.put(entry.getKey(), entry.getValue());
                        }
                    }
                } else {
                    concreteParams.put(entry.getKey(), entry.getValue());
                }
            }
        }
        if (serviceQName != null && portName != null && operationName != null) {
            final String requestMessageNamespace = wsdlParsingTool.getRequestMessageNamespace(serviceQName, portName,
                    operationName);
            //			final String requestMessagePrefix = wsdlParsingTool.getRequestMessagePrefix(requestMessageNamespace);
            if (requestMessageNamespace != null) {
                try {
                    final Document request = wsdlParsingTool.createRequest(prefixMap, requestMessageNamespace,
                            concreteParams);
                    final SOAP_PROTOCOL soapProtocol = wsdlParsingTool.getSoapProtocol(serviceQName, portName);
                    if (soapProtocol != null) {
                        envelope = wsdlParsingTool.getEnvelope(request, soapProtocol);
                        envelope = prettyFormat(envelope, 2);
                    } else {
                        envelope = null;
                    }
                } catch (final Exception e) {
                    BonitaStudioLog.error(e);
                }
            } else {
                envelope = null;
            }
            setInputParameter(USERNAME, ExpressionHelper.createConstantExpression(username, String.class.getName()));
            setInputParameter(PASSWORD, ExpressionHelper.createConstantExpression(password, String.class.getName()));
            final Expression enveloppeExpression = ExpressionHelper.createConstantExpression(envelope,
                    String.class.getName());
            enveloppeExpression.setType(ExpressionConstants.PATTERN_TYPE);
            setInputParameter(ENVELOPPE, enveloppeExpression);
            setInputParameter(SERVICE_NS,
                    ExpressionHelper.createConstantExpression(serviceQName.getLocalPart(), String.class.getName()));
            setInputParameter(SERVICE_NAME,
                    ExpressionHelper.createConstantExpression(serviceQName.getNamespaceURI(), String.class.getName()));
            setInputParameter(PORT_NAME, ExpressionHelper.createConstantExpression(portName, String.class.getName()));
            setInputParameter(BINDING_NAME,
                    ExpressionHelper.createConstantExpression(wsdlParsingTool.getBindingId(serviceQName, portName),
                            String.class.getName()));
            setInputParameter(
                    ENDPOINT_ADRESS,
                    ExpressionHelper.createConstantExpression(
                            (String) ((StructuredSelection) endPointViewer.getSelection()).getFirstElement(),
                            String.class.getName()));
            setInputParameter(SOAP_ACTION,
                    ExpressionHelper.createConstantExpression(
                            wsdlParsingTool.getSoapAction(operationName, serviceQName, portName), String.class.getName()));
        }
    }

    private void setInputParameter(final String inputName, final AbstractExpression value) {
        final ConnectorParameter parameter = getConnectorParameter(getInput(inputName));
        if (parameter.getExpression() == null) {
            parameter.setExpression(value);
        } else {
            if (value instanceof Expression && parameter.getExpression() instanceof Expression) {
                final Expression selectedExpression = (Expression) parameter.getExpression();
                new ExpressionSynchronizer(null, (Expression) value, selectedExpression).synchronize(new CompoundCommand());
            }
        }
    }

    public static String prettyFormat(final String input, final int indent) {
        try {
            final Source xmlInput = new StreamSource(new StringReader(input));
            final StringWriter stringWriter = new StringWriter();
            final StreamResult xmlOutput = new StreamResult(stringWriter);
            final TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            final Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (final Exception e) {
            throw new RuntimeException(e); // simple exception handling, please review it
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
     */
    @Override
    public boolean isOverViewContext() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
     */
    @Override
    public void setIsOverviewContext(final boolean isOverviewContext) {
    }

}
