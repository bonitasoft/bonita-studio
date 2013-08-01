/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.engine.coolbar;

import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.profiles.manager.BonitaProfilesManager;
import org.bonitasoft.studio.profiles.manager.IBonitaActivitiesCategory;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

/**
 * @author Romain Bioteau
 *
 */
public class RunCoolbarItem implements IBonitaContributionItem {



    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#dispose()
     */
    @Override
    public void dispose() {  }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void fill(Composite parent) {}

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
     */
    @Override
    public void fill(Menu parent, int index) {}

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int)
     */
    @Override
    public void fill(ToolBar parent, int index) {

    }

    private Command getCommand() {
        ICommandService service = (ICommandService)PlatformUI.getWorkbench().getService(ICommandService.class);
        Command cmd = service.getCommand("org.bonitasoft.studio.engine.runCommand") ;
        return cmd;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.CoolBar, int)
     */
    @Override
    public void fill(CoolBar parent, int index) {}

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#getId()
     */
    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.run";
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        Command cmd = getCommand();
        return cmd.isEnabled() && isSelectionRunnable();
    }

    private boolean isSelectionRunnable() {
        IEditorPart editor = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().getActiveEditor() ;
        boolean isADiagram = editor != null && editor instanceof DiagramEditor;
        if(isADiagram){
            EObject rootElement = ((DiagramEditor)editor).getDiagramEditPart().resolveSemanticElement();
            if(rootElement instanceof Form){
                if(ModelHelper.getParentProcess(rootElement) != null){
                    return true ;
                }
            }
            List selectedEditParts = ((DiagramEditor)editor).getDiagramGraphicalViewer().getSelectedEditParts();
            if(selectedEditParts != null && !selectedEditParts.isEmpty()){
                Object selectedEp = selectedEditParts.iterator().next() ;
                if(selectedEp != null){
                    return true ;
                }
            }
        }
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isDirty()
     */
    @Override
    public boolean isDirty() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isDynamic()
     */
    @Override
    public boolean isDynamic() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isGroupMarker()
     */
    @Override
    public boolean isGroupMarker() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isSeparator()
     */
    @Override
    public boolean isSeparator() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isVisible()
     */
    @Override
    public boolean isVisible() {
        return BonitaProfilesManager.getInstance().isEnabled(IBonitaActivitiesCategory.EXECUTION);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#saveWidgetState()
     */
    @Override
    public void saveWidgetState() {


    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#setParent(org.eclipse.jface.action.IContributionManager)
     */
    @Override
    public void setParent(IContributionManager parent) {


    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#setVisible(boolean)
     */
    @Override
    public void setVisible(boolean visible) {


    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#update()
     */
    @Override
    public void update() {


    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#update(java.lang.String)
     */
    @Override
    public void update(String id) {

    }

    @Override
    public void fill(ToolBar toolbar, int index, int iconSize) {
        ToolItem item = new ToolItem(toolbar,  SWT.PUSH) ;
        item.setToolTipText(Messages.RunButtonLabel) ;
        if(iconSize <  0){
            item.setText(Messages.RunButtonLabel) ;
            item.setImage(Pics.getImage(PicsConstants.coolbar_run_48)) ;
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_run_disabled_48));
        }else{
        	 item.setImage(Pics.getImage(PicsConstants.coolbar_run_16)) ;
             item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_run_disabled_16));
        }
        item.setEnabled(false);
        item.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                Command cmd = getCommand();
                try {
                    cmd.executeWithChecks(new ExecutionEvent());
                } catch (Exception ex) {
                    BonitaStudioLog.error(ex);
                }
            }
        }) ;
    }

}
