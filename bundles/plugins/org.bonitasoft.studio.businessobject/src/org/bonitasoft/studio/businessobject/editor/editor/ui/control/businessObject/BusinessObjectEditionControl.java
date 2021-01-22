/**
 * Copyright (C) 2018 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject;

import org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute.AttributeEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model.BusinessDataModelFormPage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;

public class BusinessObjectEditionControl {

    private Section section;
    private AttributeEditionControl attributeEditionControl;
    private IObservableValue<BusinessObject> boSelectedObservable;

    public BusinessObjectEditionControl(Composite parent, BusinessDataModelFormPage formPage, DataBindingContext ctx) {
        this.boSelectedObservable = formPage.observeBusinessObjectSelected();
        this.section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite boEditionComposite = formPage.getToolkit().createComposite(section);
        boEditionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        boEditionComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        formPage.getToolkit().createLabel(boEditionComposite, "");// filler
        buildDescriptionSection(boEditionComposite, formPage, ctx);
        buildEditionSection(boEditionComposite, formPage, ctx);

        section.setClient(boEditionComposite);
    }

    private void buildEditionSection(Composite parent, BusinessDataModelFormPage formPage,
            DataBindingContext ctx) {
        Section editionSection = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        editionSection.setLayoutData(GridDataFactory.fillDefaults().indent(0, 10).grab(true, true).create());
        editionSection.setLayout(GridLayoutFactory.fillDefaults().create());
        editionSection.setText(Messages.attributes);

        Composite client = formPage.getToolkit().createComposite(editionSection);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());

        attributeEditionControl = new AttributeEditionControl(client, formPage, boSelectedObservable, ctx);

        editionSection.setClient(client);
    }

    private void buildDescriptionSection(Composite parent, BusinessDataModelFormPage formPage, DataBindingContext ctx) {
        IObservableValue<String> descriptionObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                boSelectedObservable, BusinessDataModelPackage.Literals.BUSINESS_OBJECT__DESCRIPTION);

        Section descriptionSection = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        descriptionSection.setLayoutData(GridDataFactory.fillDefaults().create());
        descriptionSection.setLayout(GridLayoutFactory.fillDefaults().create());
        descriptionSection.setText(Messages.description);

        Composite client = formPage.getToolkit().createComposite(descriptionSection);
        client.setLayoutData(GridDataFactory.fillDefaults().create());
        client.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 0, 10, 0).create());

        new TextAreaWidget.Builder()
                .widthHint(500)
                .heightHint(70)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(client);

        descriptionSection.setClient(client);
    }

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.value("text").observe(section);
    }

    public IObservableValue<Boolean> observeSectionVisible() {
        return WidgetProperties.visible().observe(section);
    }

    public void updateFieldDetailsTopControl() {
        attributeEditionControl.updateFieldDetailsTopControl();
    }

}
