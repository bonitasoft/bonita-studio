/**
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.team.git.core;

import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.egit.ui.Activator;
import org.eclipse.egit.ui.internal.UIText;
import org.eclipse.egit.ui.internal.actions.PushBranchActionHandler;
import org.eclipse.egit.ui.internal.push.PushBranchPage;
import org.eclipse.egit.ui.internal.push.PushBranchWizard;
import org.eclipse.egit.ui.internal.push.PushWizardDialog;
import org.eclipse.egit.ui.internal.selection.SelectionUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class CustomPushBranchActionHandler extends PushBranchActionHandler {


    public CustomPushBranchActionHandler(IProject project) {
        setSelection(new StructuredSelection(project));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.egit.ui.internal.actions.PushBranchActionHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Repository repository = getRepository(true, event);

        try {
            PushBranchWizard wizard = null;
            Ref ref = getBranchRef(repository);
            if (ref != null) {
                wizard = new PushBranchWizard(repository, ref) {

                    /*
                     * (non-Javadoc)
                     * @see org.eclipse.egit.ui.internal.push.PushBranchWizard#addPages()
                     */
                    @Override
                    public void addPages() {
                        super.addPages();
                        PushBranchPage page = (PushBranchPage) getPage(UIText.PushBranchPage_PageName);
                        page.setShowNewRemoteButton(false);
                    }
                };

            } else {
                ObjectId id = repository.resolve(repository.getFullBranch());
                wizard = new PushBranchWizard(repository, id);
            }
            PushWizardDialog dlg = new PushWizardDialog(getShell(event),
                    wizard);
            dlg.create();
            CustomGitWizardHelper.removeAdvancedPushLink(wizard);
            dlg.open();
        } catch (IOException ex) {
            Activator.handleError(ex.getLocalizedMessage(), ex, false);
        }

        return null;
    }

    private Ref getBranchRef(Repository repository) {
        try {
            String fullBranch = repository.getFullBranch();
            if (fullBranch != null && fullBranch.startsWith(Constants.R_HEADS))
                return repository.exactRef(fullBranch);
        } catch (IOException e) {
            Activator.handleError(e.getLocalizedMessage(), e, false);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.egit.ui.internal.actions.RepositoryActionHandler#getRepository(boolean, org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    protected Repository getRepository(boolean warn, ExecutionEvent event) throws ExecutionException {
        IStructuredSelection selection = getSelection();
        if (warn) {
            Shell shell = getShell(event);
            return SelectionUtils.getRepositoryOrWarn(selection, shell);
        } else {
            return SelectionUtils.getRepository(selection);
        }
    }
    
    /*
     * (non-Javadoc)
     * @see org.eclipse.egit.ui.internal.actions.RepositoryActionHandler#getShell(org.eclipse.core.commands.ExecutionEvent)
     */
    @Override
    protected Shell getShell(ExecutionEvent event) throws ExecutionException {
        return Display.getDefault().getActiveShell();
    }


}
