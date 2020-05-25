/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.diagram.custom.contributionItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.diagram.custom.actions.OpenSpecificProcessCommand;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;

public class ListSubprocessContributionItem extends CompoundContributionItem {


    @Override
    protected IContributionItem[] getContributionItems() {
        IStructuredSelection selection = (IStructuredSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
        Object item = selection.getFirstElement();
        if (item instanceof CallActivityEditPart || item instanceof CallActivity2EditPart) {
            CallActivity subProcess = (CallActivity) ((GraphicalEditPart)item).resolveSemanticElement();
            if(subProcess.getCalledActivityName() != null
                    && subProcess.getCalledActivityName().getContent() != null
                    && subProcess.getCalledActivityName().getType().equals(ExpressionConstants.CONSTANT_TYPE)){
                return new ListSubprocesses(subProcess.getCalledActivityName()).getContributionItems();
            }

        }
        return new IContributionItem[0];
    }

    public static class ListSubprocesses extends CompoundContributionItem {

        private final String subprocessName;
        private DiagramRepositoryStore diagramSotre;

        /**
         * @param calledProcessName
         */
        public ListSubprocesses(Expression calledProcessName) {
            subprocessName = calledProcessName.getContent();
        }

        /* (non-Javadoc)
         * @see org.eclipse.ui.actions.CompoundContributionItem#getContributionItems()
         */
        @Override
        protected IContributionItem[] getContributionItems() {
            final List<IContributionItem> res = new ArrayList<IContributionItem>();
            IRepository repository = RepositoryManager.getInstance().getCurrentRepository() ;
            diagramSotre = (DiagramRepositoryStore) repository.getRepositoryStore(DiagramRepositoryStore.class) ;
            try {
                for(AbstractProcess process : diagramSotre.getAllProcesses()){
                    if (process.getName().equals(subprocessName)) {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(OpenSpecificProcessCommand.PARAMETER_PROCESS_NAME, process.getName());
                        params.put(OpenSpecificProcessCommand.PARAMETER_PROCESS_VERSION, process.getVersion());
                        CommandContributionItemParameter param = new CommandContributionItemParameter(PlatformUI.getWorkbench().getActiveWorkbenchWindow(), null, OpenSpecificProcessCommand.ID, CommandContributionItem.STYLE_PUSH);
                        param.parameters = params;
                        param.label = process.getVersion();
                        param.visibleEnabled = true;
                        param.commandId = OpenSpecificProcessCommand.ID;
                        CommandContributionItem commandContributionItem = new CommandContributionItem(param);
                        commandContributionItem.setVisible(true);
                        res.add(commandContributionItem);
                    }
                }


            } catch (Exception ex) {
                BonitaStudioLog.error(ex);
            }
            return res.toArray(new IContributionItem[res.size()]);
        }

    }
}
