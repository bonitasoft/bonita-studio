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
package org.bonitasoft.studio.migration.ui.wizard;

import static org.bonitasoft.studio.common.Messages.bosProductName;

import org.bonitasoft.studio.migration.MigrationPlugin;
import org.bonitasoft.studio.migration.i18n.Messages;
import org.bonitasoft.studio.migration.preferences.BarImporterPreferenceConstants;
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
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).extendedMargins(10, 5, 0, 0).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        
        final Label textArea = new Label(mainComposite,  SWT.WRAP );
        textArea.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).hint(600, SWT.DEFAULT).create());
        textArea.setText(Messages.bind(Messages.importWarningMessageContent, new Object[]{bosProductName}));
        
        final Composite captionComposite = new Composite(mainComposite,SWT.NONE);
        captionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10,15).create());
        captionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        
        final CLabel noActionRequiredLabel = new CLabel(captionComposite,SWT.WRAP);
        noActionRequiredLabel.setText(Messages.noActionRequiredHelp);
        noActionRequiredLabel.setImage(Pics.getImage("valid.png",MigrationPlugin.getDefault()));
        noActionRequiredLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());
        
        final CLabel reviewRequiredLabel = new CLabel(captionComposite,SWT.WRAP);
        reviewRequiredLabel.setText(Messages.reviewRequiredHelp);
        reviewRequiredLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK));
        reviewRequiredLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());
        
        final CLabel actionRequiredLabel = new CLabel(captionComposite,SWT.WRAP);
        actionRequiredLabel.setText(Messages.actionRequiredHelp);
        actionRequiredLabel.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK));
        actionRequiredLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());
        
        final Label textArea2 = new Label(mainComposite,  SWT.WRAP );
        textArea2.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).hint(600, SWT.DEFAULT).create());
        textArea2.setText(Messages.importWarningMessageContentAfterPart);
        final Composite buttonComposite = new Composite(mainComposite,SWT.NONE);
        buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
        buttonComposite.setLayoutData(GridDataFactory.swtDefaults().grab(true, true).create());
        final Button displayCheckbox = new Button(buttonComposite, SWT.CHECK);
        displayCheckbox.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).indent(0, 10).align(SWT.BEGINNING, SWT.BOTTOM).create());
        displayCheckbox.setText(Messages.doNotDisplayAtNextImport);
        displayCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
            	MigrationPlugin.getDefault().getPreferenceStore().setValue(BarImporterPreferenceConstants.DISPLAY_MIGRATION_WARNING, !displayCheckbox.getSelection());
            }
        });
        displayCheckbox.setSelection(!MigrationPlugin.getDefault().getPreferenceStore().getBoolean(BarImporterPreferenceConstants.DISPLAY_MIGRATION_WARNING));

        setControl(mainComposite);
    }

}
