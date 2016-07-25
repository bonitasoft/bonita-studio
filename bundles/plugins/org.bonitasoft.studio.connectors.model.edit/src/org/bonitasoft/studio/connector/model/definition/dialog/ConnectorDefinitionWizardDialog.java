/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition.dialog;

import java.util.List;

import org.bonitasoft.studio.common.jface.ExtensibleWizard;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.wizard.DefinitionPageWizardPage;
import org.bonitasoft.studio.connector.model.definition.wizard.PageWidgetsWizardPage;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 *
 */
public class ConnectorDefinitionWizardDialog extends WizardDialog {


    private final DefinitionResourceProvider messageProvider;
    private boolean askWhenShellCloses = true && !FileActionDialog.getDisablePopup();

    public ConnectorDefinitionWizardDialog(Shell parentShell, IWizard newWizard,DefinitionResourceProvider messageProvider) {
        super(parentShell, newWizard);
        this.messageProvider = messageProvider ;
    }

    @Override
    public void showPage(IWizardPage page) {
        super.showPage(page);
        if (page instanceof PageWidgetsWizardPage) {
            ExtensibleWizard wizard = (ExtensibleWizard) getWizard() ;
            wizard.addAdditionalPage(page) ;
            updateButtons() ;
            getButton(IDialogConstants.FINISH_ID).setText(Messages.apply) ;
            getButton(IDialogConstants.NEXT_ID).setVisible(false) ;
            getButton(IDialogConstants.BACK_ID).setVisible(false) ;
        } else {
            getButton(IDialogConstants.NEXT_ID).setVisible(true) ;
            getButton(IDialogConstants.BACK_ID).setVisible(true) ;
            getButton(IDialogConstants.FINISH_ID).setText(IDialogConstants.FINISH_LABEL);
        }
    }

    @Override
    protected void finishPressed() {
        if (getCurrentPage() instanceof PageWidgetsWizardPage) {
            PageWidgetsWizardPage widgetPage = (PageWidgetsWizardPage) getCurrentPage() ;
            Page p = widgetPage.getPage() ;
            ExtensibleWizard wizard = (ExtensibleWizard) getWizard() ;
            wizard.removeAdditionalPage(widgetPage,false) ;
            backPressed();
            if(widgetPage.getControl() != null){
                widgetPage.getControl().dispose() ;
            }


            DefinitionPageWizardPage page =  (DefinitionPageWizardPage) getCurrentPage() ;
            ConnectorDefinition definition = page.getDefinition() ;
            Page editingPage = page.getEditingPage() ;
            if(editingPage != null){
                List<Page> pageList =  definition.getPage() ;
                int index = pageList.indexOf(editingPage) ;
                pageList.remove(editingPage);
                pageList.add(index,p);
            }else{
                definition.getPage().add(p) ;
            }
            messageProvider.setPageTitleLabel(page.getMessages(), p.getId(), widgetPage.getDisplayName()) ;
            messageProvider.setPageDescriptionLabel(page.getMessages(), p.getId(),widgetPage.getPageDescription()) ;
            page.refresh() ;
        } else {
            super.finishPressed();
        }
    }

    @Override
    public void cancelPressed() {
        if (getCurrentPage() instanceof PageWidgetsWizardPage) {
            ExtensibleWizard wizard = (ExtensibleWizard) getWizard() ;
            IWizardPage page = getCurrentPage() ;
            wizard.removeAdditionalPage(page,false) ;
            backPressed();
            if(page.getControl() != null){
                page.getControl().dispose() ;
            }

        } else {
            super.cancelPressed();
        }
    }

    /**
     * Prevent the wizard to close accidentally by pressing escape (or the red cross)
     * @see org.eclipse.jface.window.Window#canHandleShellCloseEvent()
     */
    @Override
    protected boolean canHandleShellCloseEvent() {
    	if(askWhenShellCloses){
    		Boolean close = MessageDialog.openQuestion(getShell(), org.bonitasoft.studio.common.Messages.handleShellCloseEventTitle, org.bonitasoft.studio.common.Messages.handleShellCloseEventMessage);
    		if(close){
    			return super.canHandleShellCloseEvent();
    		} else {
    			return false;
    		}
    	} else {
    		return true;
    	}
    }

    @Override
    public void updateButtons() {
        // TODO Auto-generated method stub
        super.updateButtons();
    }

}
