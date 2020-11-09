/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.query;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.DeployRequiredControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject.ReadOnlyBusinessObjectList;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.query.QueryEditionControl;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.AbstractFormPart;

public class QueryFormPart extends AbstractFormPart {

    private DataBindingContext ctx = new DataBindingContext();
    private QueryFormPage formPage;
    private ReadOnlyBusinessObjectList businessObjectList;
    private QueryEditionControl queryEditionControl;
    private DeployRequiredControl deployRequiredControl;

    public QueryFormPart(Composite parent, QueryFormPage formPage) {
        this.formPage = formPage;
        parent.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        parent.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        deployRequiredControl = new DeployRequiredControl(parent, formPage,
                GridDataFactory.fillDefaults().span(2, 1).create());

        createBusinessObjectList(parent);
        createQueryEditionControl(parent);
    }

    private void createQueryEditionControl(Composite parent) {
        queryEditionControl = new QueryEditionControl(parent, formPage, ctx);
        ctx.bindValue(formPage.observeBusinessObjectSelected(), queryEditionControl.observeSectionTitle(),
                updateValueStrategy().withConverter(ConverterBuilder.<BusinessObject, String> newConverter()
                        .fromType(BusinessObject.class)
                        .toType(String.class)
                        .withConvertFunction(
                                bo -> bo == null ? "" : String.format(Messages.querySectionTitle, bo.getSimpleName()))
                        .create()).create(),
                neverUpdateValueStrategy().create());
        ctx.bindValue(queryEditionControl.observeSectionVisible(), new ComputedValueBuilder<Boolean>()
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
        businessObjectList.expandAll();
    }

    public void refreshQueryList() {
        queryEditionControl.refreshQueryViewers();
    }

    public void refreshBusinessObjectList() {
        businessObjectList.refreshViewer();
    }

    public void showBusinessObjectSelection() {
        businessObjectList.showBusinessObjectSelection();
    }

    public void updateDeployRequiredComposite(Boolean deployRequired) {
        if (deployRequired) {
            deployRequiredControl.show();
        } else {
            deployRequiredControl.hide();
        }
    }
}
