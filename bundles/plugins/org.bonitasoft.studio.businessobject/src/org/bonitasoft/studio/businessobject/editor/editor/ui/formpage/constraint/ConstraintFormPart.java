/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.constraint;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.ReadOnlyBusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.constraint.ConstraintEditionControl;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;

public class ConstraintFormPart extends AbstractFormPart {

    private DataBindingContext ctx = new EMFDataBindingContext();
    private ConstraintFormPage formPage;
    private ReadOnlyBusinessObjectList businessObjectList;
    private ConstraintEditionControl constraintObjectEditionControl;

    public ConstraintFormPart(Composite parent, ConstraintFormPage formPage) {
        this.formPage = formPage;

        parent.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(20, LayoutConstants.getSpacing().y).create());
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createBusinessObjectList(parent);
        createConstraintEditionControl(parent);
    }

    private void createConstraintEditionControl(Composite parent) {
        constraintObjectEditionControl = new ConstraintEditionControl(parent, formPage, ctx);

        ctx.bindValue(formPage.observeBusinessObjectSelected(), constraintObjectEditionControl.observeSectionTitle(),
                updateValueStrategy().withConverter(ConverterBuilder.<BusinessObject, String> newConverter()
                        .fromType(BusinessObject.class)
                        .toType(String.class)
                        .withConvertFunction(
                                bo -> bo == null ? "" : String.format(Messages.constraintsSectionTitle, bo.getSimpleName()))
                        .create()).create(),
                neverUpdateValueStrategy().create());

        ctx.bindValue(constraintObjectEditionControl.observeSectionVisible(), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> formPage.observeBusinessObjectSelected().getValue() != null)
                .build());
    }

    private void createBusinessObjectList(Composite parent) {
        Composite businessObjectListComposite = formPage.getToolkit().createComposite(parent);
        businessObjectListComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        businessObjectListComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(300, SWT.DEFAULT).create());

        businessObjectList = new ReadOnlyBusinessObjectList(businessObjectListComposite, formPage, ctx);
        ctx.bindValue(businessObjectList.observeInput(), formPage.observeWorkingCopy());
    }

    public void refreshConstraintList() {
        constraintObjectEditionControl.refreshConstraintList();

    }

    public void updateTopControl() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        constraintObjectEditionControl.dispose();
    }

    public void refreshBusinessObjectList() {
        businessObjectList.refreshViewer();
    }

    public void showBusinessObjectSelection() {
        businessObjectList.showBusinessObjectSelection();
    }

}
