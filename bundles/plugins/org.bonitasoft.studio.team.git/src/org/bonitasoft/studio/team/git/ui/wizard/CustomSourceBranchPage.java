/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.git.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.egit.core.credentials.UserPasswordCredentials;
import org.eclipse.egit.core.internal.credentials.EGitCredentialsProvider;
import org.eclipse.egit.core.op.ListRemoteOperation;
import org.eclipse.egit.core.settings.GitSettings;
import org.eclipse.egit.ui.Activator;
import org.eclipse.egit.ui.UIPreferences;
import org.eclipse.egit.ui.internal.CommonUtils;
import org.eclipse.egit.ui.internal.UIIcons;
import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.egit.ui.internal.components.CachedCheckboxTreeViewer;
import org.eclipse.egit.ui.internal.components.FilteredCheckboxTree;
import org.eclipse.egit.ui.internal.components.RepositorySelection;
import org.eclipse.egit.ui.internal.dialogs.SourceBranchFailureDialog;
import org.eclipse.egit.ui.internal.repository.tree.RepositoryTreeNodeType;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jgit.annotations.NonNull;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.TagOpt;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.progress.WorkbenchJob;
/**
 * Copy of egit ui SourceBranchPage (with package scope so not extensible)
 */
public class CustomSourceBranchPage extends WizardPage {
     
    private RepositorySelection validatedRepoSelection;

    private Ref head;

    private final List<Ref> availableRefs = new ArrayList<>();

    private Label label;

    private String transportError;

    private Button selectB;

    private Button unselectB;

    private TagOpt tagOption = TagOpt.AUTO_FOLLOW;

    private CachedCheckboxTreeViewer refsViewer;

    private UserPasswordCredentials credentials;

    private String helpContext = null;

    public CustomSourceBranchPage() {
        super(CustomSourceBranchPage.class.getName());
        setTitle(UIText.SourceBranchPage_title);
        setDescription(UIText.SourceBranchPage_description);
    }

    public List<Ref> getSelectedBranches() {
        Object[] checkedElements = refsViewer.getCheckedElements();
        Ref[] checkedRefs = new Ref[checkedElements.length];
        System.arraycopy(checkedElements, 0, checkedRefs, 0, checkedElements.length);
        return Arrays.asList(checkedRefs);
    }

    public TagOpt getTagOption() {
        return tagOption;
    }

    public List<Ref> getAvailableBranches() {
        return availableRefs;
    }

    public Ref getHEAD() {
        return head;
    }

    boolean isSourceRepoEmpty() {
        return availableRefs.isEmpty();
    }

    boolean isAllSelected() {
        return availableRefs.size() == refsViewer.getCheckedElements().length;
    }

    @Override
    public void createControl(final Composite parent) {
        final Composite panel = new Composite(parent, SWT.NULL);
        final GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        panel.setLayout(layout);

        label = new Label(panel, SWT.NONE);
        label.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        FilteredCheckboxTree fTree = new FilteredCheckboxTree(panel, null,
                SWT.NONE, new PatternFilter()) {
            /*
             * Overridden to check page when refreshing is done.
             */
            @Override
            protected WorkbenchJob doCreateRefreshJob() {
                WorkbenchJob refreshJob = super.doCreateRefreshJob();
                refreshJob.addJobChangeListener(new JobChangeAdapter() {
                    @Override
                    public void done(IJobChangeEvent event) {
                        if (event.getResult().isOK()) {
                            getDisplay().asyncExec(new Runnable() {
                                @Override
                                public void run() {
                                    checkPage();
                                }
                            });
                        }
                    }
                });
                return refreshJob;
            }
        };
        refsViewer = fTree.getCheckboxTreeViewer();

        ITreeContentProvider provider = new ITreeContentProvider() {

            @Override
            public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
                // nothing
            }

            @Override
            public void dispose() {
                // nothing
            }

            @Override
            public Object[] getElements(Object input) {
                return ((List) input).toArray();
            }

            @Override
            public boolean hasChildren(Object element) {
                return false;
            }

            @Override
            public Object getParent(Object element) {
                return null;
            }

            @Override
            public Object[] getChildren(Object parentElement) {
                return null;
            }
        };
        refsViewer.setContentProvider(provider);
        refsViewer.setLabelProvider(new LabelProvider() {
            @Override
            public String getText(Object element) {
                if (((Ref)element).getName().startsWith(Constants.R_HEADS))
                    return ((Ref)element).getName().substring(Constants.R_HEADS.length());
                return ((Ref)element).getName();
            }

            @Override
            public Image getImage(Object element) {
                return UIIcons.getImage(
                        Activator.getDefault().getResourceManager(),
                        RepositoryTreeNodeType.REF.getIcon());
            }
        });

        refsViewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(CheckStateChangedEvent event) {
                checkPage();
            }
        });
        final Composite bPanel = new Composite(panel, SWT.NONE);
        bPanel.setLayout(new RowLayout());
        selectB = new Button(bPanel, SWT.PUSH);
        selectB.setText(UIText.SourceBranchPage_selectAll);
        selectB.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                refsViewer.setAllChecked(true);
                checkPage();
            }
        });
        unselectB = new Button(bPanel, SWT.PUSH);
        unselectB.setText(UIText.SourceBranchPage_selectNone);
        unselectB.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                refsViewer.setAllChecked(false);
                checkPage();
            }
        });
        createTagOptionGroup(panel);
        bPanel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        Dialog.applyDialogFont(panel);
        setControl(panel);
        checkPage();
    }

    private void createTagOptionGroup(Composite parent) {
        final Group tagsGroup = new Group(parent, SWT.NULL);
        tagsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        tagsGroup.setText(UIText.TagOptions_groupName);
        tagsGroup.setLayout(new GridLayout());
        createTagOptionButton(tagsGroup, TagOpt.AUTO_FOLLOW,
                UIText.TagOptions_autoFollow);
        createTagOptionButton(tagsGroup, TagOpt.FETCH_TAGS,
                UIText.TagOptions_fetchTags);
        createTagOptionButton(tagsGroup, TagOpt.NO_TAGS,
                UIText.TagOptions_noTags);
    }

    private void createTagOptionButton(Group tagsGroup, final TagOpt option,
            String text) {
        Button button = new Button(tagsGroup, SWT.RADIO);
        button.setText(text);
        button.setSelection(option == tagOption);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                tagOption = option;
            }
        });
    }

    public void setSelection(@NonNull RepositorySelection selection) {
        revalidate(selection);
    }

    public void setCredentials(UserPasswordCredentials credentials) {
        this.credentials = credentials;
    }

    /**
     * Set the ID for context sensitive help
     *
     * @param id
     *            help context
     */
    public void setHelpContext(String id) {
        helpContext = id;
    }

    @Override
    public void performHelp() {
        PlatformUI.getWorkbench().getHelpSystem().displayHelp(helpContext);
    }

    /**
     * Check internal state for page completion status. This method should be
     * called only when all necessary data from previous form is available.
     */
    private void checkPage() {
        setMessage(null);
        int checkedElementCount = refsViewer.getCheckedElements().length;
        selectB.setEnabled(checkedElementCount != availableRefs.size());
        unselectB.setEnabled(checkedElementCount != 0);
        if (transportError != null) {
            setErrorMessage(transportError);
            setPageComplete(false);
            return;
        }

        if (getSelectedBranches().isEmpty()) {
            setErrorMessage(UIText.SourceBranchPage_errorBranchRequired);
            setPageComplete(false);
            return;
        }
        setErrorMessage(null);
        setPageComplete(true);
    }

    private void checkForEmptyRepo() {
        if (isSourceRepoEmpty()) {
            setErrorMessage(null);
            setMessage(UIText.SourceBranchPage_repoEmpty, IMessageProvider.WARNING);
            setPageComplete(true);
        }
    }

    private void revalidate(@NonNull final RepositorySelection newRepoSelection) {
        if (newRepoSelection.equals(validatedRepoSelection)) {
            // URI hasn't changed, no need to refill the page with new data
            checkPage();
            return;
        }

        label.setText(NLS.bind(UIText.SourceBranchPage_branchList,
                newRepoSelection.getURI().toString()));
        label.getParent().layout();

        validatedRepoSelection = null;
        transportError = null;
        head = null;
        availableRefs.clear();
        refsViewer.setInput(null);
        setPageComplete(false);
        setErrorMessage(null);
        setMessage(null);
        label.getDisplay().asyncExec(new Runnable() {
            @Override
            public void run() {
                revalidateImpl(newRepoSelection);
            }
        });
    }

    private void revalidateImpl(final RepositorySelection newRepoSelection) {
        if (label.isDisposed() || !isCurrentPage())
            return;

        final ListRemoteOperation listRemoteOp;
        final URIish uri = newRepoSelection.getURI();
        try {
            listRemoteOp = new ListRemoteOperation(uri,
                    GitSettings.getRemoteConnectionTimeout());
            if (credentials != null)
                listRemoteOp
                        .setCredentialsProvider(new EGitCredentialsProvider(
                                credentials.getUser(), credentials
                                        .getPassword()));
            getContainer().run(true, true, new IRunnableWithProgress() {
                @Override
                public void run(IProgressMonitor monitor)
                        throws InvocationTargetException, InterruptedException {
                    listRemoteOp.run(monitor);
                }
            });
        } catch (InvocationTargetException e) {
            Throwable why = e.getCause();
            transportError(why);
            if (showDetailedFailureDialog())
                SourceBranchFailureDialog.show(getShell(), uri);
            return;
        } catch (InterruptedException e) {
            transportError(UIText.SourceBranchPage_remoteListingCancelled);
            return;
        }

        final Ref idHEAD = listRemoteOp.getRemoteRef(Constants.HEAD);
        head = null;
        boolean headIsMaster = false;
        final String masterBranchRef = Constants.R_HEADS + Constants.MASTER;
        for (final Ref r : listRemoteOp.getRemoteRefs()) {
            final String n = r.getName();
            if (!n.startsWith(Constants.R_HEADS)) {
                continue;
            }
            availableRefs.add(r);
            if (idHEAD == null || headIsMaster) {
                continue;
            }
            ObjectId objectId = r.getObjectId();
            if (objectId == null) {
                continue;
            }
            if (objectId.equals(idHEAD.getObjectId())) {
                headIsMaster = masterBranchRef.equals(r.getName());
                if (head == null || headIsMaster) {
                    head = r;
                }
            }
        }
        Collections.sort(availableRefs, CommonUtils.REF_ASCENDING_COMPARATOR);
        if (idHEAD != null && head == null) {
            head = idHEAD;
            availableRefs.add(0, idHEAD);
        }

        validatedRepoSelection = newRepoSelection;
        refsViewer.setInput(availableRefs);
        refsViewer.setAllChecked(true);
        checkPage();
        checkForEmptyRepo();
    }

    private void transportError(final Throwable why) {
        Activator.logError(why.getMessage(), why);
        Throwable cause = why.getCause();
        if (why instanceof TransportException && cause != null)
            transportError(NLS.bind(getMessage(why), why.getMessage(),
                    cause.getMessage()));
        else
            transportError(why.getMessage());
    }

    private String getMessage(final Throwable why) {
        if (why.getMessage().endsWith("Auth fail")) //$NON-NLS-1$
            return UIText.SourceBranchPage_AuthFailMessage;
        else
            return UIText.SourceBranchPage_CompositeTransportErrorMessage;
    }

    private void transportError(final String msg) {
            transportError = msg;
            checkPage();
    }

    private boolean showDetailedFailureDialog() {
        return Activator
                .getDefault()
                .getPreferenceStore()
                .getBoolean(
                        UIPreferences.CLONE_WIZARD_SHOW_DETAILED_FAILURE_DIALOG);
    }
    
}
