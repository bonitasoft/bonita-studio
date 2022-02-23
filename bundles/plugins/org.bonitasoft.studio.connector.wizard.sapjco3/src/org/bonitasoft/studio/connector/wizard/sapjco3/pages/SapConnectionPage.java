/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.connector.wizard.sapjco3.i18n.Messages;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapTool;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Section;

import com.sap.conn.jco.ext.DestinationDataProvider;

/**
 * @author Maxence Raoux
 */
public class SapConnectionPage extends AbstractPage {

    private Button disableSapjcoButton;
    private Button testConnectionButton;
    private EMFDataBindingContext context;
    private SapTool sap;
    private String selection;

    private static final String CLIENT = "client";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String LANGUAGE = "language";
    private static final String HOST = "host";
    private static final String SYSTEM_NUMBER = "systemNumber";
    private static final String SYSTEM_ID = "systemId";
    private static final String DESTINATION_NAME = "destinationName";
    private static final String DESTINATION_DATA = "destinationData";
    private static final String GROUP_NAME = "groupName";
    private static final String SERVER_TYPE = "serverType";
    private SapConnectionPageComponentSwitch cmptSwitch;

    public SapConnectionPage() {
        super();
        if (libraryLoaded) {
            sap = new SapTool("EmptyDestination");
        } else {
            sap = null;
        }
    }

    @Override
    protected PageComponentSwitch getPageComponentSwitch(final EMFDataBindingContext context,
            final Composite pageComposite) {
        if (libraryLoaded) {
            cmptSwitch = new SapConnectionPageComponentSwitch(getContainer(), 
                    pageComposite, 
                    getElementContainer(),
                    getDefinition(),
                    getConfiguration(), 
                    context, 
                    getExpressionTypeFilter(),
                    sap,
                    this);
            return cmptSwitch;
        } else {
            return super.getPageComponentSwitch(context, pageComposite);
        }
    }

    @Override
    public Control doCreateControl(final Composite parent, final EMFDataBindingContext context) {

        this.context = context;

        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 0).create());

        final Composite pageComposite = new Composite(mainComposite, SWT.NONE);
        pageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(3, 10).create());
        pageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        if (!libraryLoaded) {
            createGroupToAvertUser(pageComposite);
        } else {
            createGroupTooling(pageComposite);
        }
        fillConnectionFieldsWithDefaultValues();

        final Page page = getPage();
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(context, pageComposite);

        final List<String> rejectedComponentId = libraryLoaded
                ? Arrays.asList(SERVER_TYPE, SYSTEM_NUMBER, SYSTEM_ID, GROUP_NAME,
                        DESTINATION_DATA)
                : Arrays.asList(SERVER_TYPE, DESTINATION_DATA);
        for (final Component component : page.getWidget()) {
            if (!rejectedComponentId.contains(component.getId())) {
                componentSwitch.doSwitch(component);
            }
        }
        for (final Section section : componentSwitch.getSectionsToExpand()) {
            section.setExpanded(true);
        }

        createDestinationGroup(pageComposite);

        createAdditionalParametersGroup(pageComposite);

        return mainComposite;
    }

    private void createAdditionalParametersGroup(final Composite pageComposite) {
        final Composite destinationDataComposite = new Composite(pageComposite, SWT.NONE);
        destinationDataComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        destinationDataComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(context, destinationDataComposite);
        componentSwitch.doSwitch(getComponentById(DESTINATION_DATA));
    }

    private void createDestinationGroup(final Composite pageComposite) {
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(context, pageComposite);
        componentSwitch.doSwitch(getComponentById(SERVER_TYPE));
    }

    public Component getComponentById(final String id) {
        for (final Component component : getPage().getWidget()) {
            if (component.getId().equals(id)) {
                return component;
            }
        }
        return null;
    }

    private void createGroupTooling(final Composite pageComposite) {
        final Group toolingGroup = new Group(pageComposite, SWT.NONE);
        toolingGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());
        toolingGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        toolingGroup.setText(Messages.toolGroupTitle);

        final Label disableSapjcoLabel = new Label(toolingGroup, SWT.NONE);
        disableSapjcoLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        disableSapjcoLabel.setText(Messages.disableSapjcoExplanationText);

        disableSapjcoButton = new Button(toolingGroup, SWT.PUSH);
        disableSapjcoButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        disableSapjcoButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                libraryLoaded = !libraryLoaded;
                updateButton();
            }
        });

        final Label testConnectionLabel = new Label(toolingGroup, SWT.NONE);
        testConnectionLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        testConnectionLabel.setText(Messages.toolGroupConnectionLabel);

        testConnectionButton = new Button(toolingGroup, SWT.PUSH);
        testConnectionButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        testConnectionButton.setText(Messages.toolGroupConnectionButton);
        testConnectionButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                testConnectionButtonFunction();
            }
        });
        updateButton();
    }

    private void fillConnectionFieldsWithDefaultValues() {
        fillField(CLIENT, "000");
        fillField(SYSTEM_NUMBER, "00");
        fillField(LANGUAGE, "EN");
    }

    protected void fillField(final String parameterName, final String content) {
        if (isParameterEmpty(getInput(parameterName))) {
            final Expression expr = ExpressionHelper.createConstantExpression(content, String.class.getName());
            getConnectorParameter(getInput(parameterName)).setExpression(expr);
        }
    }

    private boolean isParameterEmpty(final Input input) {
        final Expression expr = (Expression) getConnectorParameter(input).getExpression();
        if (expr.getContent() == null || expr.getContent().isEmpty()) {
            return true;
        }
        return false;
    }

    private void testConnectionButtonFunction() {
        if (libraryLoaded) {
            fillConnectionSapToolProperties();
            sap.tryConnect();
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    if (sap.connectionOK) {
                        MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
                                Messages.connectionTestMessageTitle, sap.connectionMessage);
                    } else {
                        final ErrorDialog d = new ErrorDialog(Display.getDefault().getActiveShell(),
                                Messages.connectionTestMessageTitle, Messages.connectionError, new Status(IStatus.ERROR,
                                        "sapjco", sap.connectionMessage),
                                IStatus.ERROR | IStatus.CANCEL | IStatus.WARNING);
                        d.open();
                    }
                }
            });
        } else {
            Display.getDefault().syncExec(new Runnable() {

                @Override
                public void run() {
                    final ErrorDialog d = new ErrorDialog(Display.getDefault().getActiveShell(),
                            Messages.connectionTestMessageTitle, Messages.connectionError, new Status(IStatus.ERROR,
                                    "sapjco", Messages.connectionLibraryError),
                            IStatus.ERROR | IStatus.CANCEL
                                    | IStatus.WARNING);
                    d.open();
                }
            });
        }
    }

    private void fillConnectionSapToolProperties() {
        if (libraryLoaded) {
            sap.cleanConnectionProperties();
            sap.setDistanationName(getWizardField(DESTINATION_NAME));
            sap.addConnectionProperties(DestinationDataProvider.JCO_CLIENT, getWizardField(CLIENT));
            sap.addConnectionProperties(DestinationDataProvider.JCO_USER, getWizardField(USER));
            sap.addConnectionProperties(DestinationDataProvider.JCO_PASSWD, getWizardField(PASSWORD));
            sap.addConnectionProperties(DestinationDataProvider.JCO_LANG, getWizardField(LANGUAGE));
            sap.addConnectionProperties(DestinationDataProvider.JCO_ASHOST, getWizardField(HOST));
            if (selection.equals("ApplicationServer")) {
                sap.addConnectionProperties(DestinationDataProvider.JCO_SYSNR, getWizardField(SYSTEM_NUMBER));
            } else if (selection.equals("MessageServer")) {
                sap.addConnectionProperties(DestinationDataProvider.JCO_GROUP, getWizardField(GROUP_NAME));
                sap.addConnectionProperties(DestinationDataProvider.JCO_R3NAME, getWizardField(SYSTEM_ID));
            }
        }
    }

    public void refreshServerTypeOptions(final Composite serverTypeOptionsComposite, final String selection) {
        this.selection = selection;
        final PageComponentSwitch componentSwitch = getPageComponentSwitch(context, serverTypeOptionsComposite);
        componentSwitch.doSwitch(getComponentById(DESTINATION_DATA));
        for (final Control control : serverTypeOptionsComposite.getChildren()) {
            control.dispose();
        }
        if (selection.equals("MessageServer")) {
            componentSwitch.doSwitch(getComponentById(SYSTEM_ID));
            componentSwitch.doSwitch(getComponentById(GROUP_NAME));
        } else {
            componentSwitch.doSwitch(getComponentById(SYSTEM_NUMBER));
        }
        serverTypeOptionsComposite.layout(true, true);
    }

    private String getWizardField(final String fieldName) {
        return ((Expression) getConnectorParameter(getInput(fieldName)).getExpression()).getContent();
    }

    private void updateButton() {
        if (libraryLoaded) {
            disableSapjcoButton.setText(Messages.disableSapjcoButtonLabelOff);
            testConnectionButton.setEnabled(true);

        } else {
            disableSapjcoButton.setText(Messages.disableSapjcoButtonLabelOn);
            testConnectionButton.setEnabled(false);
        }

    }

    private void createGroupToAvertUser(final Composite pageComposite) {
        final Group avertUserGroup = new Group(pageComposite, SWT.NONE);
        avertUserGroup.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
        avertUserGroup.setText(Messages.improvement);

        avertUserGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).create());

        final Label connectionLabel = new Label(avertUserGroup, SWT.NONE);
        connectionLabel.setLayoutData(GridDataFactory.swtDefaults().span(2, 1).create());
        connectionLabel.setText(Messages.explanationText);

        final Button provideSapjcoButton = new Button(avertUserGroup, SWT.PUSH);
        provideSapjcoButton.setText(Messages.provideSapjcoButtonLabel);
        provideSapjcoButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                provideSapjcoButtonFunction();
            }
        });

        final Button restartNowButton = new Button(avertUserGroup, SWT.PUSH);
        restartNowButton.setText(Messages.restartNowButtonLabel);
        restartNowButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                super.widgetSelected(e);
                PlatformUI.getWorkbench().restart();
            }
        });
    }

    @Override
    public IWizardPage getNextPage() {
        final IWizardPage nextPage = super.getNextPage();
        if (!libraryLoaded) {
            if (nextPage instanceof AbstractPage) {
                ((AbstractPage) nextPage).sapTool = null;
            }
        } else {
            fillConnectionSapToolProperties();
            sap.userWantToUseIt = true;
            sap.tryConnect();
            if (nextPage instanceof AbstractPage) {
                ((AbstractPage) nextPage).sapTool = sap;
            }
        }
        return nextPage;
    }

    private void provideSapjcoButtonFunction() {
        final FileDialog fd = new FileDialog(getShell());
        fd.setFilterExtensions(new String[] { "*.jar" });
        final String filePath = fd.open();
        if (filePath != null) {
            try {
                final File from = new File(filePath);
                final File tomcatLibFolder = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(),
                        "tomcat" + File.separatorChar + "server" + File.separatorChar + "lib");
                final File endorsedFileEngine = new File(tomcatLibFolder, "sapjco3.jar");
                if (!endorsedFileEngine.createNewFile()) {
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.addFileTitle,
                                    Messages.addFileErrorAlreadyExist);
                        }
                    });
                } else {
                    Files.copy(from.toPath(), endorsedFileEngine.toPath().resolve(from.toPath().getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    Display.getDefault().syncExec(new Runnable() {

                        @Override
                        public void run() {
                            MessageDialog.openInformation(Display.getCurrent().getActiveShell(), Messages.addFileTitle,
                                    Messages.addFileSuccess);
                        }
                    });
                }
            } catch (final IOException e) {
                final Exception error = e;
                BonitaStudioLog.log(error.getMessage());
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        final ErrorDialog d = new ErrorDialog(Display.getDefault().getActiveShell(), Messages.addFileTitle,
                                Messages.addFileError, new Status(IStatus.ERROR, "sapjco", error.getMessage(), error),
                                IStatus.ERROR | IStatus.CANCEL | IStatus.WARNING);
                        d.open();
                    }
                });
            }
        }
    }

}
