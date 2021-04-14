/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.wizard.page;

import static com.google.common.collect.Iterables.find;
import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.Messages.bosProductName;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.jface.ArrayTreeContentProvider;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.ui.dialog.DynamicLabelWizardDialog;
import org.bonitasoft.studio.team.ui.wizard.RemoteRepositoryFinder;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.operation.remote.CreateFolderOperation;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRepositoryFolder;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import com.google.common.base.Predicate;

/**
 * @author Baptiste Mesta
 */
public class ListWorkspacesInRepositoryWizardPage extends WizardPage {

    private FilteredTree repositoriesTree;
    private Button createRepositoryButton;
    private final ViewerFilter compatibleFilter = new ViewerFilter() {

        @Override
        public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
            final Map<IRepositoryContainer, String> bonitaRepositories = remoteRepositoryFinder.getBonitaRepositories();
            final List<IRepositoryResource> emptyContainers = remoteRepositoryFinder.getEmptyContainers();
            final List<IRepositoryResource> unkownContainers = remoteRepositoryFinder.getUnkownContainers();
            return !unkownContainers.contains(element) && (ProductVersion.sameVersion(bonitaRepositories.get(element)) || emptyContainers.contains(element))
                    && !RepositoryManager.getInstance().getAllRepositories().contains(((IRepositoryContainer) element).getName());
        }
    };

    private RemoteRepositoryFinder remoteRepositoryFinder;

    /**
     * @param pageName
     */
    public ListWorkspacesInRepositoryWizardPage(final RemoteRepositoryFinder remoteRepositoryFinder) {
        super(ListWorkspacesInRepositoryWizardPage.class.getName());
        this.remoteRepositoryFinder = remoteRepositoryFinder;
        setTitle(Messages.listWorkspaces_title);
        setDescription(Messages.bind(Messages.listWorkspaces_desc, new Object[] { bosProductName }));
        setImageDescriptor(Pics.getWizban());
    }

    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            ((DynamicLabelWizardDialog) getContainer()).setNextLabel(Messages.migrateWorkspace + " >");
        } else {
            ((DynamicLabelWizardDialog) getContainer()).setNextLabel(IDialogConstants.NEXT_LABEL);
        }
    }

    /*
     * (non-Javadoc)
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        final Label label = new Label(composite, SWT.NONE);
        label.setText(Messages.availableWorkspaces);

        final Button showCompatibleRepositoryOnly = new Button(composite, SWT.CHECK);
        showCompatibleRepositoryOnly.setText(Messages.showOnlyCompatibleWorkspaces);
        showCompatibleRepositoryOnly.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.TOP).create());
        showCompatibleRepositoryOnly.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                if (showCompatibleRepositoryOnly.getSelection()) {
                    repositoriesTree.getViewer().addFilter(compatibleFilter);
                } else {
                    repositoriesTree.getViewer().removeFilter(compatibleFilter);
                }
            }
        });

        repositoriesTree = new FilteredTree(composite, SWT.BORDER | SWT.V_SCROLL, new PatternFilter(), true);
        repositoriesTree.getViewer().setLabelProvider(new RemoteRepositoryLabelProvider(remoteRepositoryFinder));
        final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                final Map<IRepositoryContainer, String> bonitaRepositories = remoteRepositoryFinder.getBonitaRepositories();
                final List<IRepositoryResource> emptyContainers = remoteRepositoryFinder.getEmptyContainers();
                final List<IRepositoryResource> unkownContainers = remoteRepositoryFinder.getUnkownContainers();
                final Object firstElement = ((IStructuredSelection) repositoriesTree.getViewer().getSelection()).getFirstElement();
                getContainer().updateButtons();
                setPageComplete(firstElement != null && !unkownContainers.contains(firstElement)
                        && (ProductVersion.sameVersion(bonitaRepositories.get(firstElement)) || emptyContainers.contains(firstElement))
                        && !RepositoryManager.getInstance().getAllRepositories().contains(((IRepositoryContainer) firstElement).getName()));
            }
        };
        repositoriesTree.getViewer().addSelectionChangedListener(selectionListener);

        repositoriesTree.getViewer().setContentProvider(ArrayTreeContentProvider.getInstance());
        repositoriesTree.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        createRepositoryButton = new Button(composite, SWT.FLAT);
        createRepositoryButton.setText(Messages.createNewRepo);
        createRepositoryButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                // prompt the user for the name of the new folder
                final InputDialog dialog = new InputDialog(getShell(),
                        "",
                        Messages.nameOfTheWorkspace,
                        Messages.default_workspace_name,
                        new IInputValidator() {

                    @Override
                    public String isValid(final String newText) {
                        if (!new Path("").isValidSegment(newText)) {
                            return "Invalid path";
                        } else if (alreadyExist(newText)) {
                            return "A folder of the same name already exists";
                        } else {
                            return null;
                        }
                    }

                    private boolean alreadyExist(final String newText) {
                        return remoteRepositoryFinder.getRemoteFolderNames().contains(newText.toLowerCase());
                    }
                });
                if (dialog.open() == IDialogConstants.OK_ID) {
                    final String name = dialog.getValue();
                    final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                        @Override
                        public void run(final org.eclipse.core.runtime.IProgressMonitor monitor) throws java.lang.reflect.InvocationTargetException,
                                InterruptedException {
                            monitor.beginTask("Creating new remote repository " + name + "...", 2);
                            // create the folder on svn
                            final CreateFolderOperation op = new CreateFolderOperation(remoteRepositoryFinder.getLocation().getRoot(), name,
                                    "Creating new remote repository " + name);
                            op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                            monitor.worked(1);
                            // add it to the repositories list and select it
                            remoteRepositoryFinder.getLocation().getRoot().refresh();
                            try {
                                remoteRepositoryFinder.execute(monitor);
                            } catch (final SVNConnectorException e1) {
                                throw new InvocationTargetException(e1);
                            }
                            monitor.worked(1);
                        };
                    };
                    try {
                        getContainer().run(true, false, runnable);
                    } catch (final InvocationTargetException | InterruptedException e1) {
                        BonitaStudioLog.error(e1);
                    }
                    updateInput();
                    repositoriesTree.getViewer().setSelection(new StructuredSelection(repositoryWithName(name)));
                }
            }
        });

        setControl(composite);
        setPageComplete(false);
    }

    @Override
    public boolean isPageComplete() {
        setErrorMessage(null);
        final ISelection selection = repositoriesTree.getViewer().getSelection();
        if (!selection.isEmpty()) {
            final SVNRepositoryFolder repositoryFolder = (SVNRepositoryFolder) ((IStructuredSelection) selection).getFirstElement();
            final boolean doesNotExists = RepositoryManager.getInstance().getRepository(repositoryFolder.getName()) == null;
            if (!doesNotExists) {
                setErrorMessage(NLS.bind(Messages.localCopyAlreadyExistsInWorkspace, repositoryFolder.getName()));
            }
            return doesNotExists && !canFlipToNextPage();
        }
        return super.isPageComplete();
    }

    private IRepositoryContainer repositoryWithName(final String name) {
        return find(remoteRepositoryFinder.getBonitaRepositories().keySet(), withName(name), null);
    }

    private Predicate<IRepositoryContainer> withName(final String name) {
        return new Predicate<IRepositoryContainer>() {

            @Override
            public boolean apply(final IRepositoryContainer input) {
                return input.getName().equals(name);
            }
        };
    }

    @Override
    public boolean canFlipToNextPage() {
        final Map<IRepositoryContainer, String> bonitaRepositories = remoteRepositoryFinder.getBonitaRepositories();
        final List<IRepositoryResource> emptyContainers = remoteRepositoryFinder.getEmptyContainers();
        final List<IRepositoryResource> unkownContainers = remoteRepositoryFinder.getUnkownContainers();
        final ISelection selection = repositoriesTree.getViewer().getSelection();
        if (!selection.isEmpty()) {
            final Object element = ((IStructuredSelection) selection).getFirstElement();
            if (element instanceof IRepositoryContainer) {
                return !unkownContainers.contains(element) && !emptyContainers.contains(element)
                        && (bonitaRepositories.get(element) == null || ProductVersion.canBeMigrated(bonitaRepositories.get(element)));
            }
        }
        return false;
    }

    public void updateInput() {
        repositoriesTree.getViewer().setInput(newArrayList(remoteRepositoryFinder.getBonitaRepositories().keySet()));
    }

    public IRepositoryResource getSelectedWorkspace() {
        return (IRepositoryResource) ((StructuredSelection) repositoriesTree.getViewer().getSelection()).getFirstElement();
    }

    public String getWorkspaceVersion(final IRepositoryResource selectedWorkspace) {
        final Map<IRepositoryContainer, String> bonitaRepositories = remoteRepositoryFinder.getBonitaRepositories();
        String version = ProductVersion.CURRENT_VERSION + "-";
        if (bonitaRepositories.containsKey(selectedWorkspace)) {
            version = bonitaRepositories.get(selectedWorkspace);
        }
        return version;
    }

    public FilteredTree getRepositoriesTree() {
        return repositoriesTree;
    }

    public void setRemoteRepositoryFinder(final RemoteRepositoryFinder remoteRepositoryFinder) {
        this.remoteRepositoryFinder = remoteRepositoryFinder;
    }
}
