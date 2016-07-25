package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.model.process.ResourceContainer;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Aurelien Pupier
 *
 */
public class SelectFileStoreWizard extends Wizard {

    private final TransactionalEditingDomain editingDomain;
    private final ResourceContainer resourceContainer;
    private SelectFileStoreWizardPage page;
    private final String initialValue;


    public SelectFileStoreWizard(TransactionalEditingDomain editingDomain, ResourceContainer resourceContainer, String initialValue) {
        this.editingDomain = editingDomain;
        this.resourceContainer = resourceContainer;
        this.initialValue = initialValue;
        setDefaultPageImageDescriptor(Pics.getWizban());
    }


    @Override
    public void addPages() {
        super.addPages();
        page = new SelectFileStoreWizardPage(editingDomain, resourceContainer, initialValue);
        addPage(page);
    }


    @Override
    public boolean performFinish() {
        return true;
    }


    public String getSelectedFilePath() {
        return page.getSelectedFilePath();
    }


}
