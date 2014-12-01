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
package org.bonitasoft.studio.diagram.custom.wizard;

import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * @author Mickael Istria
 *
 */
public class OpenDiagramWizardPage extends AbstractManageDiagramWizardPage {


    private Text processLocationText;
    private Button removeProcessButton;


    /**
     * @param openProcessWizard
     * @param example
     *
     */
    public OpenDiagramWizardPage() {
        super(Messages.openProcessWizardPage_title);
        setTitle(Messages.openProcessWizardPage_title);
        setDescription(Messages.openProcessWizardPage_desc);
        setImageDescriptor(Pics.getWizban());

    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);
        removeProcessButton = new Button(getMainComposite(), SWT.FLAT);
        final GridData gd = new GridData();
        gd.widthHint = 90 ;
        removeProcessButton.setLayoutData(gd);
        removeProcessButton.setText(Messages.removeProcessLabel);
        removeProcessButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                final AbstractManageDiagramWizard wizard = (AbstractManageDiagramWizard) getWizard();
                wizard.deleteDiagrams(OpenDiagramWizardPage.this);
            }

            @Override
            public void widgetDefaultSelected(final SelectionEvent e) {

            }
        });

        updateRemoveButton();

        // Separator
        final Composite blank = new Composite(getMainComposite(), SWT.NONE);
        blank.setLayoutData(new GridData(SWT.DEFAULT, 40));

        getDiagramTree().getViewer().setInput(new Object());
        setControl(getMainComposite());
    }


    protected void updateRemoveButton() {
        if (getDiagramTree().isEnabled() && !getDiagramTree().getViewer().getSelection().isEmpty()) {
            removeProcessButton.setEnabled(true);
        }else{
            removeProcessButton.setEnabled(false);
        }
    }




    @Override
    public boolean isPageComplete() {
        return (getDiagrams() != null && !getDiagrams().isEmpty());
    }

    /**
     * @return
     */
    public boolean useIFile() {
        return !processLocationText.isEnabled();
    }


    protected Button getRemoveButton() {
        return removeProcessButton;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.diagram.custom.wizard.ManageWizardPage#diagramTreeSelectionChangeListener()
     */
    @Override
    public ISelectionChangedListener diagramTreeSelectionChangeListener() {
        return new ISelectionChangedListener() {

            @Override
            public void selectionChanged(final SelectionChangedEvent event) {
                updateRemoveButton();
                setPageComplete(isPageComplete());
                getContainer().updateButtons();
            }
        };
    }

}
