/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bar.ui;

import org.bonitasoft.studio.importer.bar.BarImporterPlugin;
import org.bonitasoft.studio.importer.bar.i18n.Messages;
import org.bonitasoft.studio.importer.bar.preferences.BarImporterPreferenceConstants;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;


/**
 * @author Romain Bioteau
 *
 */
public class MigrationWarningWizardPage extends WizardPage {

    protected MigrationWarningWizardPage() {
        super(MigrationWarningWizardPage.class.getName());
        setTitle(Messages.migrationWizardTitle);
        setDescription(Messages.migrationWizardDescription);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10,10).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        
        final Label textArea = new Label(mainComposite,  SWT.WRAP );
        textArea.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(300, SWT.DEFAULT).create());
        textArea.setText(Messages.importWarningMessageContent);
        
        final Composite captionComposite = new Composite(mainComposite,SWT.NONE);
        captionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        captionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        final CLabel noActionRequiredLabel = new CLabel(captionComposite,SWT.NONE);
        noActionRequiredLabel.setText(Messages.noActionRequired);
        noActionRequiredLabel.setImage(Pics.getImage("valid.png",BarImporterPlugin.getDefault()));
        noActionRequiredLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        final CLabel reviewRequiredLabel = new CLabel(captionComposite,SWT.NONE);
        reviewRequiredLabel.setText(Messages.reviewRequired);
        reviewRequiredLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
        reviewRequiredLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        final CLabel actionRequiredLabel = new CLabel(captionComposite,SWT.NONE);
        actionRequiredLabel.setText(Messages.actionRequired);
        actionRequiredLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK));
        actionRequiredLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        final Label textArea2 = new Label(mainComposite,  SWT.WRAP );
        textArea2.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, SWT.DEFAULT).create());
        textArea2.setText(Messages.importWarningMessageContentAfterPart);
        final Composite buttonComposite = new Composite(mainComposite,SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
        buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.LEFT, SWT.BOTTOM).create());
        final Button displayCheckbox = new Button(buttonComposite, SWT.CHECK);
        displayCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        displayCheckbox.setText("Do not display at next import");
        displayCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                BarImporterPlugin.getDefault().getPreferenceStore().setValue(BarImporterPreferenceConstants.DISPLAY_MIGRATION_WARNING, !displayCheckbox.getSelection());
            }
        });
        displayCheckbox.setSelection(!BarImporterPlugin.getDefault().getPreferenceStore().getBoolean(BarImporterPreferenceConstants.DISPLAY_MIGRATION_WARNING));
        setControl(mainComposite);
    }

}
