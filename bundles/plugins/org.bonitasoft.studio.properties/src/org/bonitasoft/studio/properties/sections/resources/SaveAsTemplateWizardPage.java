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
package org.bonitasoft.studio.properties.sections.resources;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Baptiste Mesta
 *
 */
public class SaveAsTemplateWizardPage extends WizardPage {

    private Text templateName;
    private Text previewPath;


    /**
     * @param pageName
     */
    protected SaveAsTemplateWizardPage() {
        super("saveAs");
        setTitle(Messages.saveAsTemplate_title);
        setImageDescriptor(Pics.getWizban()) ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(3, false));
        Label templateLabel = new Label(composite, SWT.NONE);
        templateLabel.setText(Messages.saveAsTemplate_templateLabel);
        templateName = new Text(composite, SWT.BORDER);
        templateName.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                setPageComplete(templateName.getText().length() > 0 && !alreadyExists(templateName.getText()));
            }
        });
        templateName.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).span(2,1).create());
        Label previewPathLabel = new Label(composite,SWT.NONE);
        previewPathLabel.setText(Messages.saveAsTemplate_previewPathLabel);
        previewPath = new Text(composite, SWT.BORDER);
        previewPath.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).create());
        Button browseButton = new Button(composite, SWT.FLAT);
        browseButton.setText(Messages.Browse);
        browseButton.addSelectionListener(new SelectionAdapter() {
            /* (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getShell());
                String path = dialog.open();
                if(path != null){
                    previewPath.setText(path);
                }
            }
        });

        setPageComplete(false);
        setControl(composite);
    }


    protected boolean alreadyExists(String text) {
        LookNFeelRepositoryStore store = (LookNFeelRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        return store.getChild(text) != null;
    }

    public String getTemplateName(){
        return templateName.getText();
    }

    public String getPreviewPath(){
        return previewPath.getText();
    }

}
