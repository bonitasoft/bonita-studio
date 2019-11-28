/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/

package org.bonitasoft.studio.businessobject.ui.wizard;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Romain Bioteau
 * 
 */
public class SelectBusinessObjectWizard extends Wizard {

    protected SelectBusinessObjectWizardPage selectedWizardPage;

    private BusinessObjectModelFileStore selectedArtifact;

    private BusinessObjectModelRepositoryStore store;

    public SelectBusinessObjectWizard(BusinessObjectModelRepositoryStore store) {
        Assert.isNotNull(store, "store");
        setDefaultPageImageDescriptor(Pics.getWizban());
        setWindowTitle(Messages.selectBusinessObjectWizardTitle);
        this.store = store;
    }

    @Override
    public void addPages() {
        selectedWizardPage = createSelectBusinessObjectWizardPage();
        addPage(selectedWizardPage);
    }

    protected SelectBusinessObjectWizardPage createSelectBusinessObjectWizardPage() {
        return new SelectBusinessObjectWizardPage(store);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.wizard.Wizard#performFinish()
     */
    @Override
    public boolean performFinish() {
        selectedArtifact = selectedWizardPage.getSelectedArtifact();
        return selectedArtifact != null;
    }

    public BusinessObjectModelFileStore getSelectedArtifact() {
        return selectedArtifact;
    }

    public BusinessObjectModelRepositoryStore getStore() {
        return store;
    }

}
