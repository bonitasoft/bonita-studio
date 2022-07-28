/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.connectors.ui;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.ui.viewsupport.JavaUILabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;

/**
 * @author Romain Bioteau
 */
public class TestConnectorResultDialog extends Dialog {

    private static final String RESULTS_TITLE = Messages.resultTitleLabel;

    private Map<String, Object> testResultAsMap;
    private Set<String> testResultAsSet;
    private Composite mainComposite;
    private ListViewer listViewer;
    private Throwable resultExecption;

    @SuppressWarnings("unchecked")
    public TestConnectorResultDialog(Shell parentShell, Object testResult) {
        super(parentShell);
        setShellStyle(SWT.MAX | SWT.CLOSE | SWT.APPLICATION_MODAL | SWT.RESIZE);
        if (testResult instanceof Map<?, ?>) {
            testResultAsMap = (Map<String, Object>) testResult;
        } else if (testResult instanceof Set<?>) {
            testResultAsSet = (Set<String>) testResult;
        } else if (testResult instanceof Throwable) {
            resultExecption = (Throwable) testResult;
        }
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(new GridLayout(2, false));
        Composite descriptionComposite = new Composite(mainComposite, SWT.NONE);
        descriptionComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, SWT.DEFAULT).create());
        descriptionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
        CLabel description = new CLabel(descriptionComposite, SWT.WRAP);
        description.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        description.setText(Messages.unserializableOutputWarning);
        description.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(org.eclipse.ui.ISharedImages.IMG_OBJS_WARN_TSK));
        if (testResultAsMap != null && !testResultAsMap.isEmpty()) {
            Iterator<Entry<String, Object>> it = testResultAsMap.entrySet().iterator();
            int cpt = 0;
            while (it.hasNext()) {
                cpt++;
                Entry<String, Object> entry = it.next();
                final Label outputLabel = new Label(mainComposite, SWT.NONE);
                outputLabel.setText(toConnectorOutputLabel(cpt));
                outputLabel.setLayoutData(GridDataFactory.fillDefaults().grab(false, false).align(SWT.FILL, SWT.TOP).create());
                Object value = entry.getValue();
                if (value == null || value instanceof String || value instanceof Number) {
                    Text text = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.READ_ONLY);
                    text.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).minSize(SWT.DEFAULT, 20).create());
                    if (entry.getValue() != null) {
                        text.setText(entry.getValue().toString());
                    } else {
                        text.setText("NULL"); //$NON-NLS-1$
                    }
                    text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
                } else if (value instanceof Document) {
                    TreeViewer viewer = new TreeViewer(mainComposite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
                    viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
                    viewer.setContentProvider(new XmlDocumentContentProvider());
                    viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().minSize(SWT.DEFAULT, 20).grab(true, true).hint(SWT.DEFAULT, 300).create());
                    viewer.setLabelProvider(new XmlLabelProvider());
                    viewer.setInput(value);
                    viewer.getTree().setFont(BonitaStudioFontRegistry.getCommentsFont());
                    viewer.expandAll();
                } else {
                    TreeViewer viewer = new TreeViewer(mainComposite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
                    viewer.setContentProvider(new PojoBrowserContentProvider());
                    viewer.getControl().setLayoutData(GridDataFactory.fillDefaults().minSize(SWT.DEFAULT, 100).grab(false, true).create());
                    viewer.setLabelProvider(new JavaUILabelProvider() {

                        @Override
                        public Image getImage(Object element) {
                            if (element instanceof Pair<?, ?>) {
                                return getImage(((Pair<?, ?>) element).getFirst());
                            } else if (element == null) {
                                return null;
                            }
                            return super.getImage(element);
                        }

                        @Override
                        public String getText(Object item) {
                            if (item instanceof String) {
                                return (String) item;
                            }
                            if (item instanceof Pair<?, ?>) {

                                Object first = ((Pair<?, ?>) item).getFirst();
                                if (first instanceof IType) {
                                    return getText(first) + " : " + ((Pair<?, ?>) item).getSecond().toString();
                                } else {
                                    return getText(first);
                                }
                            } else if (item instanceof IMember) {
                                return super.getText(item);
                            } else if (item != null) {
                                return item.toString();
                            } else {
                                return null;
                            }
                        }
                    });
                    new Label(mainComposite, SWT.NONE);
                    Label warningLabel = new Label(mainComposite, SWT.NONE);
                    GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).grab(true, false).applyTo(warningLabel);
                    warningLabel.setText(Messages.testConnectorPOJOWarning);
                    viewer.setInput(value);

                }

            }
        } else if (testResultAsSet != null) {
            listViewer = new ListViewer(mainComposite, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
            GridData gd = GridDataFactory.fillDefaults().grab(true, true).create();
            gd.horizontalSpan = 2;
            listViewer.getList().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
            listViewer.setContentProvider(new IStructuredContentProvider() {

                @Override
                public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                }

                @Override
                public void dispose() {
                }

                @Override
                public Object[] getElements(Object inputElement) {
                    return ((Set) inputElement).toArray();
                }
            });
            listViewer.setLabelProvider(new LabelProvider());
            listViewer.setInput(testResultAsSet);

        } else if (resultExecption != null) {
            Label foundExceptionLabel = new Label(mainComposite, SWT.NONE);
            foundExceptionLabel.setText(Messages.exceptionFound);
            GridData gd = new GridData();
            gd.horizontalSpan = 2;
            foundExceptionLabel.setLayoutData(gd);
            Text text = new Text(mainComposite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.WRAP);
            text.setLayoutData(new GridData(GridData.FILL_BOTH));

            String exception = ""; //$NON-NLS-1$
            Throwable e = resultExecption;
            while (e != null) {
                exception = exception.concat(e.toString() + "\n"); //$NON-NLS-1$
                e = e.getCause();
            }

            if (exception.contains("engine.exception.ClassLoaderException")
                    && exception.contains("unserializable output")) {
                exception = "The connector contains an unserializable output. You can't use the wizard currently.\n"
                        + "The workaround is to use a dedicated test processes with the connector and modify the connector outputs and store them in data in order to display them in a form.\n"
                        + "Then, you will have just to Run the process.\n\n" + exception;
            }

            text.setText(exception);
            text.setEditable(false);
            text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        } else {
            Label successMessage = new Label(mainComposite, SWT.NONE);
            successMessage.setText(Messages.successMessage);
            GridData gd = new GridData();
            gd.horizontalSpan = 2;
            successMessage.setLayoutData(gd);
        }

        return parent;
    }

    protected String toConnectorOutputLabel(int cpt) {
        if (cpt == 1) {
            return Messages.connectorOutput;
        } else {
            return Messages.connectorOutput + " " + cpt;
        }
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CANCEL_ID,
                IDialogConstants.CANCEL_LABEL, false);
        getButton(IDialogConstants.CANCEL_ID).setText(IDialogConstants.OK_LABEL);
    }

    @Override
    protected Point getInitialSize() {
        return new Point(800, 500);
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(RESULTS_TITLE);
    }

    @SuppressWarnings("unchecked")
    public void setResult(Object result) {
        if (result instanceof Map) {
            testResultAsMap = (Map<String, Object>) result;
        } else if (result instanceof Set) {
            testResultAsSet = (Set<String>) result;
        }
    }
}
