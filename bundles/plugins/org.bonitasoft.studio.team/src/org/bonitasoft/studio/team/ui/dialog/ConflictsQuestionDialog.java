/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.resource.IRepositoryFile;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.ui.operation.OpenRemoteFileOperation;

/**
 * @author Baptiste Mesta
 *
 */
public class ConflictsQuestionDialog extends Dialog {

    /**
     * How to test it: put the following code in a SWTBot test:
     *
	 public void openPopup() {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				List<IResource> resources = new ArrayList<IResource>();
				for (int i = 0; i < 30; i++) resources.add(ExtensionProjectUtil.getProject());
				ConflictsQuestionDialog dialog = new ConflictsQuestionDialog(shell, resources.toArray(new IResource[0]));
				dialog.open();
			}
		});
	}
     */

    private final IResource[] resources;
    private final List<IResource> toCommit = new ArrayList<IResource>();
    private final SelectionListener localSL = new SelectionAdapter() {
        @Override
        public void widgetSelected(final SelectionEvent e) {
            if(((Button) e.widget).getSelection()) {
                if(!toCommit.contains(e.widget.getData())){
                    toCommit.add((IResource) e.widget.getData());
                }
            }
        };
    };
    private final SelectionListener remoteSL = new SelectionAdapter() {
        @Override
        public void widgetSelected(final SelectionEvent e) {
            if(((Button) e.widget).getSelection()) {
                toCommit.remove(e.widget.getData());
            }
        };
    };
    private final SelectionListener openlistener = new SelectionAdapter() {
        @Override
        public void widgetSelected(final SelectionEvent e) {

            final IRepositoryResource repFile = SVNRemoteStorage.instance().asRepositoryResource((IResource) e.widget.getData());
            if(repFile instanceof IRepositoryFile){
                repFile.setPegRevision(SVNRevision.HEAD);
                repFile.setSelectedRevision(SVNRevision.HEAD);
                new OpenRemoteFileOperation(new IRepositoryFile[]{(IRepositoryFile) repFile}, OpenRemoteFileOperation.OPEN_DEFAULT).run(new NullProgressMonitor());
            }
        };
    };

    /**
     * @param parentShell
     */
    public ConflictsQuestionDialog(final Shell parentShell,final IResource[] resources) {
        super(parentShell);
        setShellStyle(SWT.MAX | SWT.CLOSE | SWT.APPLICATION_MODAL);
        this.resources = resources;
        for (final IResource iResource : resources) {
            toCommit.add(iResource);
        }
        parentShell.setFocus() ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(final Composite parent) {
        final Composite main = new Composite(parent,SWT.NONE);
        main.setLayout(new GridLayout(1,false));
        final Label desc = new Label(main, SWT.WRAP);
        desc.setText(Messages.team_conflictDetected_msg);
        desc.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).hint(250, -1).create());
        final ScrolledComposite scrolledComposite = new ScrolledComposite(main, SWT.V_SCROLL);
        scrolledComposite.setLayout(new GridLayout(1, false));
        final GridData gd = new GridData(GridData.FILL_BOTH);
        gd.heightHint = 400 ;
        scrolledComposite.setLayoutData(gd);
        scrolledComposite.setAlwaysShowScrollBars(false);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);
        //scrolledComposite.setLayout(new FillLayout());
        //scrolled
        final Composite composite = new Composite(scrolledComposite, SWT.NONE);
        composite.setLayout(new GridLayout(2,false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        for (final IResource resource : resources) {

            final Label name = new Label(composite, SWT.NONE);
            //TODO search the artifact
            name.setText(resource.getName());

            final Composite resourceComposite = new Composite(composite, SWT.NONE);
            resourceComposite.setLayout(new GridLayout(3, false));
            final Button local = new Button(resourceComposite, SWT.RADIO);
            //TODO i18n
            local.setText(Messages.team_conflict_local);
            local.setData(resource);
            local.setSelection(true);
            local.addSelectionListener(localSL);


            final Button remote = new Button(resourceComposite, SWT.RADIO);
            remote.setText(Messages.team_conflict_remote);
            remote.setData(resource);
            remote.addSelectionListener(remoteSL);

            final Link open = new Link(resourceComposite, SWT.NONE);
            open.setText("<a>"+Messages.team_conflict_openRemote+"</a>");
            open.setData(resource);
            open.addSelectionListener(openlistener);
        }
        scrolledComposite.setContent(composite);
        scrolledComposite.setMinSize(scrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        return main;
    }


    public IResource[] getResourceToRevert(){
        final IResource[] array = new IResource[resources.length-toCommit.size()];
        int j=0;
        for (int i = 0; i < resources.length; i++) {
            if(!toCommit.contains(resources[i])){
                array[j++] = resources[i];
            }
        }
        return array;
    }

    public IResource[] getResourceToCommit(){
        return toCommit.toArray(new IResource[toCommit.size()]);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(Messages.team_conflictDetected_title);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(final Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
                true);
    }

}
