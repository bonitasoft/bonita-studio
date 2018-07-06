/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.ConnectableElement;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class ConnectorsSelectionPage extends WizardPage {

    private static final String DEBUG_KEY = "debug_connector";
    private final MainProcess mainProcess;
    private CheckboxTreeViewer connectorsTree;
    private List<EObject> input;
    private ICheckStateProvider checkStateProvider;
    private final Set<EObject> checked;

    public ConnectorsSelectionPage(MainProcess sourceProcesses) {
        super(ConnectorsSelectionPage.class.getName(), Messages.selectConnectorTitle, Pics.getWizban());
        setDescription(Messages.selectConnectorMessage);
        mainProcess = sourceProcesses;
        checked = new HashSet<EObject>();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        GridLayout gl = new GridLayout(1, false);
        mainComposite.setLayout(gl);

        createSelectionButtonBar(mainComposite);
        createTreeViewer(mainComposite);
        createSelectionButtonBar(mainComposite);

        getContainer().updateButtons();
        setControl(mainComposite);
    }

    protected void createTreeViewer(Composite mainComposite) {
        connectorsTree = new CheckboxTreeViewer(mainComposite, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
        connectorsTree.setLabelProvider(new DebugConnectorLabelProvider());

        final ITreeContentProvider provider = new ITreeContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

            }

            @Override
            public void dispose() {

            }

            @Override
            public boolean hasChildren(Object element) {
                if (element instanceof Connector) {
                    return false;
                }
                return true;
            }

            @Override
            public Object getParent(Object element) {
                if (element instanceof Connector) {
                    return ((EObject) element).eContainer();
                }
                return null;

            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ((Set<EObject>) inputElement).toArray();
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                List<EObject> children = new ArrayList<EObject>();
                if (parentElement instanceof ConnectableElement) {
                    children.addAll(((ConnectableElement) parentElement).getConnectors());
                }
                return children.toArray();
            }
        };
        connectorsTree.setContentProvider(provider);

        input = new ArrayList<EObject>();
        retrieveAllConnectors(mainProcess, input);

        Set<EObject> parentSet = new HashSet<EObject>();
        for (EObject object : input) {
            parentSet.add(object.eContainer());
        }
        connectorsTree.setInput(parentSet);

        connectorsTree.addCheckStateListener(new ICheckStateListener() {

            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                if (!(event.getElement() instanceof Connector)) {
                    for (Object child : provider.getChildren(event.getElement())) {
                        connectorsTree.setChecked(child, event.getChecked());
                        if (event.getChecked()) {
                            checked.add((EObject) child);
                        } else {
                            checked.remove(child);
                        }

                    }
                } else {
                    if (event.getChecked()) {
                        checked.add((EObject) event.getElement());
                    } else {
                        checked.remove(event.getElement());
                    }
                    Object parent = provider.getParent(event.getElement());
                    boolean atLeastOne = false;
                    for (Object child : provider.getChildren(parent)) {
                        if (connectorsTree.getChecked(child)) {
                            atLeastOne = true;
                            break;
                        }
                    }
                    connectorsTree.setChecked(parent, atLeastOne);

                }

            }
        });

        checkStateProvider = new ICheckStateProvider() {

            @Override
            public boolean isGrayed(Object element) {
                if (!(element instanceof Connector)) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean isChecked(Object element) {
                if (element instanceof Connector) {
                    if (ConnectorPlugin.getDefault().getDialogSettings()
                            .get(DEBUG_KEY + ModelHelper.getEObjectID((EObject) element)) != null) {
                        boolean isExceuting = ConnectorPlugin.getDefault().getDialogSettings()
                                .getBoolean(DEBUG_KEY + ModelHelper.getEObjectID((EObject) element));
                        if (isExceuting) {
                            checked.add((EObject) element);
                        } else {
                            checked.remove(element);
                        }
                        return isExceuting;
                    } else {
                        checked.add((EObject) element);
                        return true;
                    }
                } else {
                    boolean allChecked = true;
                    for (Object child : provider.getChildren(element)) {
                        if (!isChecked(child)) {
                            allChecked = false;
                            break;
                        }
                    }
                    return allChecked;
                }

            }
        };
        connectorsTree.setCheckStateProvider(checkStateProvider);
        connectorsTree.collapseAll();
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 350;
        gd.heightHint = 400;
        connectorsTree.getTree().setLayoutData(gd);
    }

    private void retrieveAllConnectors(MainProcess diagram, List<EObject> result) {
        ModelHelper.findAllConnectors(diagram, result);
        final List<CallActivity> callActivities = ModelHelper.getAllItemsOfType(diagram,
                ProcessPackage.Literals.CALL_ACTIVITY);
        final DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        final List<AbstractProcess> allProcesses = store.getAllProcesses();
        for (CallActivity activity : callActivities) {
            final String procName = activity.getCalledActivityName().getContent();
            if (procName != null && !procName.isEmpty()) {
                final String procVersion = activity.getCalledActivityVersion().getContent();
                for (AbstractProcess proc : allProcesses) {
                    if (!ModelHelper.getMainProcess(proc).equals(diagram)) {//NOT ON THE SAME DIAGRAM
                        if (procVersion == null || procVersion.isEmpty()) {
                            if (procName.equals(proc.getName())) {
                                retrieveAllConnectors(ModelHelper.getMainProcess(proc), result);
                            }
                        } else {
                            if (procName.equals(proc.getName()) && procVersion.equals(proc.getVersion())) {
                                retrieveAllConnectors(ModelHelper.getMainProcess(proc), result);
                            }
                        }
                    }
                }
            }
        }
    }

    protected void createSelectionButtonBar(Composite mainComposite) {
        Composite buttonComposite = new Composite(mainComposite, SWT.NONE);
        RowLayout rl = new RowLayout();
        rl.spacing = 10;
        buttonComposite.setLayout(rl);
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(-3, 0).create());

        final Button selectAll = new Button(buttonComposite, SWT.FLAT);
        selectAll.setText(Messages.selectAll);
        selectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                connectorsTree.setAllChecked(true);
                for (Object element : input) {
                    connectorsTree.setChecked(element, true);
                }
                checked.addAll(input);
            }
        });

        final Button unselectAll = new Button(buttonComposite, SWT.FLAT);
        unselectAll.setText(Messages.unSelectAll);
        unselectAll.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                connectorsTree.setAllChecked(false);
                for (Object element : input) {
                    connectorsTree.setChecked(element, false);
                }
                checked.addAll(input);
                checked.clear();
            }
        });
    }

    @Override
    public boolean isPageComplete() {
        return true;
    }

    public Set<EObject> getExcludedConnectors() {
        Set<EObject> unchecked = new HashSet<EObject>();
        for (EObject o : input) {
            if (checked.contains(o)) {
                ConnectorPlugin.getDefault().getDialogSettings().put(DEBUG_KEY + ModelHelper.getEObjectID(o), true);
            } else {
                ConnectorPlugin.getDefault().getDialogSettings().put(DEBUG_KEY + ModelHelper.getEObjectID(o), false);
                unchecked.add(o);
            }
        }

        return unchecked;
    }

}
