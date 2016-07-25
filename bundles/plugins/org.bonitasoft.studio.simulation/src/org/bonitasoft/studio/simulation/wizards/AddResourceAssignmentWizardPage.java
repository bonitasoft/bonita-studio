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
package org.bonitasoft.studio.simulation.wizards;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.widgets.DurationComposite;
import org.bonitasoft.studio.model.simulation.ResourceUsage;
import org.bonitasoft.studio.model.simulation.SimulationActivity;
import org.bonitasoft.studio.simulation.i18n.Messages;
import org.bonitasoft.studio.simulation.repository.SimulationResourceRepositoryStore;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Baptiste Mesta
 * 
 */
public class AddResourceAssignmentWizardPage extends WizardPage {

    private final ResourceUsage resourceUsage;
    private DataBindingContext context;
    private Combo combo;
    private Text quantityText;
    private final SimulationActivity activity;
    private DurationComposite durationWidget;
    private Button useActivity;
    private Button createResourceButton;
    private final SimulationResourceRepositoryStore resourceStore;

    /**
     * @param resourceUsage2
     * @param editingDomain
     * @param pageName
     */
    protected AddResourceAssignmentWizardPage(ResourceUsage resourceUsage2,SimulationActivity eObject2) {
        super("Add Resource Assignment");//$NON-NLS-1$
        resourceUsage = resourceUsage2;
        activity = eObject2;
        setTitle(Messages.AddResourceAssignmentWizardPage_title);
        setMessage(Messages.AddResourceAssignmentWizardPage_msg);
        resourceStore =  (SimulationResourceRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(SimulationResourceRepositoryStore.class) ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
     * .Composite)
     */
    public void createControl(Composite parent) {
        context = new DataBindingContext();
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(new GridLayout(3, false));

        Label resourceIDLabel = new Label(mainComposite, SWT.NONE);
        resourceIDLabel.setText(Messages.resourceName);
        combo = new Combo(mainComposite, SWT.READ_ONLY);
        for (IRepositoryFileStore artifact : resourceStore.getChildren()) {
            combo.add(artifact.getDisplayName());
        }
        if(combo.getItemCount() > 0){
            combo.select(0) ;
        }
        if (resourceUsage != null && resourceUsage.getResourceID() != null) {
            combo.setText(resourceUsage.getResourceID());
        }
        combo.setLayoutData(GridDataFactory.swtDefaults().hint(150, SWT.DEFAULT).create());

        createResourceButton = new Button(mainComposite,SWT.FLAT) ;
        createResourceButton.setText(Messages.create) ;
        createResourceButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                EditSimulationResourceWizard wiz = new EditSimulationResourceWizard() ;
                if(new WizardDialog(Display.getCurrent().getActiveShell(), wiz).open() == WizardDialog.OK){
                    combo.removeAll() ;
                    for (IRepositoryFileStore artifact : resourceStore.getChildren()) {
                        combo.add(artifact.getDisplayName());
                    }
                    combo.setText(wiz.getArtifact().getDisplayName()) ;
                    getContainer().updateButtons();
                }
            }
        });

        Label quantityLabel = new Label(mainComposite, SWT.NONE);
        quantityLabel.setText(Messages.quantity);

        quantityText = new Text(mainComposite, SWT.BORDER);
        quantityText.setLayoutData(GridDataFactory.swtDefaults().hint(130, SWT.DEFAULT).span(2, 1).create() );
        if (resourceUsage != null) {
            quantityText.setText(String.valueOf(resourceUsage.getQuantity()));
        }else{
            quantityText.setText("1");//$NON-NLS-1$
        }



        Label durationLabel = new Label(mainComposite, SWT.NONE) ;
        durationLabel.setText(Messages.duration) ;

        Composite radioComposite = new Composite(mainComposite, SWT.NONE);
        radioComposite.setLayoutData(GridDataFactory.swtDefaults().hint(SWT.DEFAULT, SWT.DEFAULT).span(2, 1).create()) ;
        radioComposite.setLayout(new GridLayout(2, false));
        useActivity = new Button(radioComposite, SWT.RADIO);
        useActivity.setText(Messages.AddResourceAssignmentWizardPage_useActivity);
        Button useCustom = new Button(radioComposite, SWT.RADIO);
        useCustom.setText(Messages.AddResourceAssignmentWizardPage_useCustom);
        if(resourceUsage != null){
            useActivity.setSelection(resourceUsage.isUseActivityDuration());
            useCustom.setSelection(!resourceUsage.isUseActivityDuration());
        } else{
            useActivity.setSelection(true);
            useCustom.setSelection(false);
        }

        durationWidget = new DurationComposite(mainComposite, false, false, true, true, true, true, null) ;
        durationWidget.setLayoutData(GridDataFactory.fillDefaults().span(3, 1).create());
        if(activity != null){
            durationWidget.setMaxDuration(activity.getExecutionTime()) ;
            if(resourceUsage != null){
                durationWidget.setDuration(resourceUsage.getDuration()) ;
            }else{
                durationWidget.setDuration(activity.getExecutionTime()) ;
            }
        }
        context.bindValue(SWTObservables.observeVisible(durationWidget), SWTObservables.observeSelection(useCustom));


        setControl(mainComposite);
        getContainer().updateButtons();
    }

    @Override
    public boolean isPageComplete() {
        setErrorMessage(null);
        String resourceID = getResourceID();


        if(resourceID.length()<=0){
            setErrorMessage(Messages.EditSimulationResourceWizard_ErrorEmptyName);
            return false;
        }
        String quantityText = getQuantity();
        int quantity = 0;
        try{
            quantity = Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            setErrorMessage(Messages.EditSimulationResourceWizard_ErrorQuantity);
            return false;
        }

        if(activity.getExecutionTime() == 0){
            setMessage(Messages.NoExecutionTime, IMessageProvider.WARNING) ;
        }
        return super.isPageComplete();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.DialogPage#dispose()
     */
    @Override
    public void dispose() {
        context.dispose();
        super.dispose();
    }

    /**
     * @return the resourceID
     */
    public String getResourceID() {
        return combo.getText();
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantityText.getText();
    }

    /**
     * @return the duration
     */
    public long getDuration() {
        return durationWidget.getDuration() ;
    }

    /**
     * @return the useActivity
     */
    public boolean getUseActivityDuration() {
        return useActivity.getSelection();
    }
}
