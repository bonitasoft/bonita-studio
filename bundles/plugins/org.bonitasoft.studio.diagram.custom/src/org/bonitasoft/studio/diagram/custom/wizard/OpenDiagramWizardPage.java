/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.wizard;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.eclipse.jface.layout.GridDataFactory.swtDefaults;

import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 */
public class OpenDiagramWizardPage extends AbstractManageDiagramWizardPage {

    /**
     * @param diagramRepositoryStore
     * @param openProcessWizard
     * @param example
     */
    public OpenDiagramWizardPage(final DiagramRepositoryStore diagramRepositoryStore) {
        super(Messages.openProcessWizardPage_title, diagramRepositoryStore);
        setTitle(Messages.openProcessWizardPage_title);
        setDescription(Messages.openProcessWizardPage_desc);
        setImageDescriptor(Pics.getWizban());
    }

    @Override
    protected Composite doCreateControl(final Composite parent, final DataBindingContext context) {
        final Composite mainComposite = super.doCreateControl(parent, context);
        final Button removeProcessButton = new Button(mainComposite, SWT.FLAT);
        removeProcessButton.setLayoutData(swtDefaults().hint(90, SWT.DEFAULT).create());
        removeProcessButton.setText(Messages.removeProcessLabel);
        removeProcessButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {

                final AbstractManageDiagramWizard wizard = (AbstractManageDiagramWizard) getWizard();
                if (wizard.confirmDelete(getSelectedDiagrams())) {
                    wizard.deleteDiagrams(getSelectedDiagrams(), getViewer());
                }
            }

        });

        context.bindValue(SWTObservables.observeEnabled(removeProcessButton),
                ViewersObservables.observeSingleSelection(getDiagramTree().getViewer()),
                neverUpdateValueStrategy().create(),
                updateValueStrategy().withConverter(convertSelectionToBooleanForRemoveEnablement()).create());

        // Separator
        final Composite blank = new Composite(mainComposite, SWT.NONE);
        blank.setLayoutData(new GridData(SWT.DEFAULT, 40));

        return mainComposite;
    }

    protected IConverter convertSelectionToBooleanForRemoveEnablement() {
        return new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }
        };
    }

}
