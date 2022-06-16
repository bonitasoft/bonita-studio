/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.configuration.ui.wizard.page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.environment.Environment;
import org.bonitasoft.studio.configuration.environment.EnvironmentFactory;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.configuration.preferences.ConfigurationPreferenceConstants;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class EnvironmentWizardPage extends WizardPage implements ISelectionChangedListener {

    private final List<Environment> environments;
    private Button removeButton;
    private TableViewer viewer;
    private Optional<ISelection> selection = Optional.empty();

    public EnvironmentWizardPage(List<Environment> environments) {
        super(EnvironmentWizardPage.class.getName());
        setTitle(Messages.environmentTitle);
        setDescription(Messages.environmentDesc);
        this.environments = environments;
    }

    public EnvironmentWizardPage(List<Environment> environments, Optional<ISelection> selection) {
        this(environments);
        this.selection = selection;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 15).create());

        viewer = new TableViewer(mainComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(700, SWT.DEFAULT).create());
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.addSelectionChangedListener(this);

        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(25));
        tableLayout.addColumnData(new ColumnWeightData(75));
        viewer.getTable().setLayout(tableLayout);

        TableViewerColumn column = new TableViewerColumn(viewer, SWT.FILL);
        TableColumn nameColumn = column.getColumn();
        nameColumn.setText(Messages.name);
        column.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(Object element) {
                return ((Environment) element).getName();
            }
        });
        column.setEditingSupport(new EnvironmentNameEditingSupport(column.getViewer()));

        TableViewerColumn descColumnViewer = new TableViewerColumn(viewer, SWT.FILL);
        TableColumn descColumn = descColumnViewer.getColumn();
        descColumn.setText(Messages.descripiton);
        descColumnViewer.setEditingSupport(new EnvironmentDescriptionEditingSupport(descColumnViewer.getViewer()));
        descColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            @Override
            public String getText(Object element) {
                return ((Environment) element).getDescription();
            }
        });

        viewer.getTable().setHeaderVisible(true);
        viewer.getTable().setLinesVisible(true);

        Environment e = EnvironmentFactory.eINSTANCE.createEnvironment();
        e.setName(ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON);
        e.setDescription(Messages.localEnvironmentDesc);
        environments.add(0, e);

        viewer.setInput(environments);

        createButtons(mainComposite);

        if (selection.isPresent() && !selection.get().isEmpty()) {
            Object envName = ((IStructuredSelection) selection.get()).getFirstElement();

            viewer.setSelection(environments.stream().filter(env -> envName.equals(env.getName())).findFirst()
                    .map(StructuredSelection::new).orElse(new StructuredSelection()));
        }

        setControl(mainComposite);
    }

    protected void createButtons(Composite parent) {
        final Composite buttonComposite = new Composite(parent, SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        createAddButton(buttonComposite);
        createRemoveButton(buttonComposite);
    }

    protected void createRemoveButton(final Composite buttonComposite) {
        removeButton = new Button(buttonComposite, SWT.FLAT);
        removeButton.setText(Messages.remove);
        removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        removeButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                for (Object sel : ((IStructuredSelection) viewer.getSelection()).toList()) {
                    Environment env = (Environment) sel;
                    final String environmentName = env.getName();
                    if (FileActionDialog.confirmDeletionQuestion(environmentName)) {
                        environments.remove(env);
                        DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                                .getRepositoryStore(DiagramRepositoryStore.class);

                        for (AbstractProcess proc : diagramStore.getAllProcesses()) {
                            List<Configuration> confsToRemove = retrieveConfsToRemoveInProc(environmentName, proc);
                            removeConfsFromProc(proc, confsToRemove);
                        }
                    }
                    viewer.setInput(environments);
                }
            }

            protected void removeConfsFromProc(AbstractProcess proc,
                    List<Configuration> confsToRemove) {
                if (!confsToRemove.isEmpty()) {
                    final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(proc);
                    CompoundCommand cc = new CompoundCommand();
                    for (Configuration configurationToRemove : confsToRemove) {
                        if (editingDomain != null) {
                            cc.append(RemoveCommand.create(editingDomain, configurationToRemove));
                        } else {
                            proc.getConfigurations().remove(configurationToRemove);
                        }
                        if (configurationToRemove.getName().equals(ConfigurationPlugin.getDefault().getPreferenceStore()
                                .getString(ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION))) {
                            ConfigurationPlugin.getDefault().getPreferenceStore().setValue(
                                    ConfigurationPreferenceConstants.DEFAULT_CONFIGURATION,
                                    ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON);
                        }
                    }

                    if (editingDomain != null) {
                        editingDomain.getCommandStack().execute(cc);
                    }
                }
            }

            protected List<Configuration> retrieveConfsToRemoveInProc(
                    final String environmentName, AbstractProcess proc) {
                List<Configuration> confsToRemove = new ArrayList<>();
                for (Configuration conf : proc.getConfigurations()) {
                    if (environmentName.equals(conf.getName())) {
                        confsToRemove.add(conf);
                    }
                }
                return confsToRemove;
            }
        });
    }

    protected void createAddButton(final Composite buttonComposite) {
        final Button addButton = new Button(buttonComposite, SWT.FLAT);
        addButton.setText(Messages.add);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                Environment env = EnvironmentFactory.eINSTANCE.createEnvironment();
                env.setName(generateEnvName());
                environments.add(env);
                viewer.setInput(environments);
            }
        });
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        updateButtons();
    }

    private void updateButtons() {
        if (removeButton != null && !removeButton.isDisposed()) {
            boolean enable = true;
            final ISelection viewerSelection = viewer.getSelection();
            if (!viewerSelection.isEmpty()) {
                for (Object sel : ((IStructuredSelection) viewerSelection).toList()) {
                    Environment env = (Environment) sel;
                    if (ConfigurationPreferenceConstants.LOCAL_CONFIGURAITON.equals(env.getName())) {
                        enable = false;
                    }
                }
            }
            removeButton.setEnabled(!viewerSelection.isEmpty() && enable);
        }

    }

    private String generateEnvName() {
        Set<String> names = new HashSet<>();
        for (Environment e : environments) {
            names.add(e.getName());
        }

        return NamingUtils.generateNewName(names, Messages.defaultEnvName, 1);
    }

}
