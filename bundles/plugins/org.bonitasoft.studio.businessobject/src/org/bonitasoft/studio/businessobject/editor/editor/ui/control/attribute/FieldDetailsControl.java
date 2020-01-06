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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute;

import java.util.Optional;

import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model.BusinessDataModelFormPage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.SimpleField;
import org.bonitasoft.studio.businessobject.editor.validator.StringLengthValidator;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.common.widgets.CustomStackLayout;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class FieldDetailsControl extends Composite {

    private BusinessDataModelFormPage formPage;
    private DataBindingContext ctx;
    private IObservableValue<Field> selectedFieldObservable;
    private IObservableValue<String> descriptionObservable;
    private CustomStackLayout stackLayout;
    private Composite stringFieldDetailsComposite;
    private Composite dateDeprecatedComposite;
    private Composite dateOnlyComposite;
    private Composite dateTimeComposite;
    private Composite dateTimeInTimezoneComposite;
    private Composite textFieldComposite;
    private Composite relationFieldComposite;
    private Composite noDetailsComposite;

    public FieldDetailsControl(Composite parent, BusinessDataModelFormPage formPage,
            IObservableValue<Field> selectedFieldObservable, DataBindingContext ctx) {
        super(parent, SWT.None);
        this.formPage = formPage;
        this.ctx = ctx;
        this.selectedFieldObservable = selectedFieldObservable;
        this.descriptionObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                selectedFieldObservable, BusinessDataModelPackage.Literals.FIELD__DESCRIPTION);
        formPage.getToolkit().adapt(this);

        stackLayout = new CustomStackLayout(this);
        setLayout(stackLayout);
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createStringFieldDetailsComposite(ctx);
        createDateDeprecatedDetailsComposite();
        createDateOnlyDetailsComposite();
        createDateTimeComposite();
        createDateTimeInTimeZoneComposite();
        createTextFieldComposite();
        createRelationFieldComposite(ctx);
        createNoDetailsComposite();

        selectedFieldObservable.addValueChangeListener(e -> updateTopControl());
    }

    public void updateTopControl() {
        Field field = selectedFieldObservable.getValue();
        if (field != null) {
            if (field instanceof RelationField) {
                stackLayout.setTopControl(relationFieldComposite);
            } else {
                SimpleField simpleField = (SimpleField) field;
                switch (simpleField.getType()) {
                    case DATE:
                        stackLayout.setTopControl(dateDeprecatedComposite);
                        break;
                    case LOCALDATE:
                        stackLayout.setTopControl(dateOnlyComposite);
                        break;
                    case LOCALDATETIME:
                        stackLayout.setTopControl(dateTimeComposite);
                        break;
                    case OFFSETDATETIME:
                        stackLayout.setTopControl(dateTimeInTimezoneComposite);
                        break;
                    case TEXT:
                        stackLayout.setTopControl(textFieldComposite);
                        break;
                    case STRING:
                        stackLayout.setTopControl(stringFieldDetailsComposite);
                        break;
                    default:
                        stackLayout.setTopControl(noDetailsComposite);
                }
            }
        }
    }

    private void createNoDetailsComposite() {
        noDetailsComposite = createDescriptionComposite(Optional.empty());
    }

    private void createRelationFieldComposite(DataBindingContext ctx) {
        relationFieldComposite = new RelationFieldDetailsControl(this, formPage, selectedFieldObservable,
                descriptionObservable, ctx);
    }

    private void createTextFieldComposite() {
        textFieldComposite = createDescriptionComposite(Optional.of(Messages.textDetails));
    }

    private void createDateTimeComposite() {
        dateTimeComposite = createDescriptionComposite(Optional.of(String.format(Messages.dateTimeDetails,
                DateTypeLabels.DATE_AND_TIME, DateTypeLabels.DATE_TIME_WITH_TIMEZONE)));
    }

    private void createDateTimeInTimeZoneComposite() {
        dateTimeInTimezoneComposite = createDescriptionComposite(
                Optional.of(String.format(Messages.dateTimeInTimezoneDetails, DateTypeLabels.DATE_TIME_WITH_TIMEZONE)));
    }

    private void createDateOnlyDetailsComposite() {
        dateOnlyComposite = createDescriptionComposite(
                Optional.of(String.format(Messages.dateOnlyDetails, DateTypeLabels.DATE_ONLY)));
    }

    private void createStringFieldDetailsComposite(DataBindingContext ctx) {
        stringFieldDetailsComposite = formPage.getToolkit().createComposite(this);
        stringFieldDetailsComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        stringFieldDetailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        IObservableValue<Integer> fieldLengthObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                selectedFieldObservable, BusinessDataModelPackage.Literals.SIMPLE_FIELD__LENGTH);

        createDescriptionTextArea(stringFieldDetailsComposite);

        new ComboWidget.Builder()
                .withLabel(Messages.length)
                .withCompositeMessageDecorator()
                .withItems(new String[] { "64", "128", "255", "512" })
                .labelAbove()
                .widthHint(500)
                .bindTo(fieldLengthObservable)
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(StringToNumberConverter.toInteger(false))
                        .withValidator(new StringLengthValidator(selectedFieldObservable))
                        .create())
                .withModelToTargetStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(NumberToStringConverter.fromInteger(false))
                        .create())
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(stringFieldDetailsComposite);

        Label stringHelp = formPage.getToolkit().createLabel(stringFieldDetailsComposite, Messages.stringLengthTooltip);
        stringHelp.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    }

    private void createDateDeprecatedDetailsComposite() {
        dateDeprecatedComposite = formPage.getToolkit().createComposite(this);
        dateDeprecatedComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        dateDeprecatedComposite.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());

        createDescriptionTextArea(dateDeprecatedComposite);

        Label descriptionLabel = formPage.getToolkit().createLabel(dateDeprecatedComposite, Messages.dateDetails);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().create());

        Label deprecatedDetailsLabel = formPage.getToolkit().createLabel(dateDeprecatedComposite,
                Messages.legacyDateTooltip);
        deprecatedDetailsLabel.setLayoutData(GridDataFactory.fillDefaults().create());
    }

    private Composite createDescriptionComposite(Optional<String> description) {
        Composite composite = formPage.getToolkit().createComposite(this);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());

        createDescriptionTextArea(composite);

        description.ifPresent(desc -> {
            Label descriptionLabel = formPage.getToolkit().createLabel(composite, desc);
            descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        });

        return composite;
    }

    private void createDescriptionTextArea(Composite parent) {
        new TextAreaWidget.Builder()
                .withLabel(Messages.fieldDescriptionPlaceholder)
                .labelAbove()
                .widthHint(500)
                .heightHint(70)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

}
