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
package org.bonitasoft.studio.common.jface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;


/**
 * @author Romain Bioteau
 *
 */
public abstract class ExtensibleWizard extends Wizard {

    private final List<IWizardPage> additionalPages = new ArrayList<IWizardPage>() ;

    protected ExtensibleWizard(){
        super() ;
    }


    public void addAdditionalPage(IWizardPage page) {
        page.setWizard(this) ;
        setForcePreviousAndNextButtons(true) ;
        additionalPages.add(page) ;
    }

    public void removeAdditionalPage(IWizardPage page,boolean disposePage) {
        if(additionalPages.contains(page)){
            if(disposePage && page.getControl() != null && !page.getControl().isDisposed()){
                page.getControl().dispose() ;
            }
            additionalPages.remove(page) ;
        }
    }

    public void removeAllAdditionalPages() {
        for(IWizardPage p : additionalPages){
            p.dispose();
        }
        additionalPages.clear() ;
    }

    protected List<IWizardPage> getAllPageList(){
        IWizardPage[] pages =  super.getPages();
        List<IWizardPage> result = new ArrayList<IWizardPage>(Arrays.asList(pages)) ;
        result.addAll(additionalPages) ;
        return result ;
    }

    @Override
    public IWizardPage[] getPages() {
        List<IWizardPage> result = getAllPageList() ;
        return result.toArray(new WizardPage[result.size()]) ;
    }

    @Override
    public boolean canFinish() {
        boolean canFinish = super.canFinish() ;
        boolean additionPageAreCompleted = true ;
        for(IWizardPage page : additionalPages){
            additionPageAreCompleted = page.isPageComplete() ;
            if(!additionPageAreCompleted) {
                break ;
            }
        }
        return canFinish && additionPageAreCompleted;
    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        List<IWizardPage> pages = getAllPageList() ;
        int index = pages.indexOf(page);
        if (index == pages.size() - 1 || index == -1) {
            // last page or page not found
            return null;
        }
        return pages.get(index + 1);
    }

    /*
     * (non-Javadoc) Method declared on IWizard.
     */
    @Override
    public IWizardPage getPage(String name) {
        List<IWizardPage> pages = getAllPageList() ;
        for (int i = 0; i < pages.size(); i++) {
            IWizardPage page = pages.get(i);
            String pageName = page.getName();
            if (pageName.equals(name)) {
                return page;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc) Method declared on IWizard.
     */
    @Override
    public int getPageCount() {
        return getPages().length ;
    }


    /*
     * (non-Javadoc) Method declared on IWizard. The default behavior is to
     * return the page that was added to this wizard before the given page.
     */
    @Override
    public IWizardPage getPreviousPage(IWizardPage page) {
        List<IWizardPage> pages = getAllPageList() ;
        int index = pages.indexOf(page);
        if (index == 0 || index == -1) {
            // first page or page not found
            return null;
        }
        return pages.get(index - 1);
    }
    
    @Override
    public void dispose() {
    	super.dispose();
    	 // notify pages
        for (int i = 0; i < additionalPages.size(); i++) {
			try {
	            ((IWizardPage) additionalPages.get(i)).dispose();
			} catch (Exception e) {
				Status status = new Status(IStatus.ERROR, Policy.JFACE, IStatus.ERROR, e.getMessage(), e);
				Policy.getLog().log(status);
			}
        }
    }
}
