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

import java.util.Objects;

import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model.BusinessDataModelFormPage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.FetchType;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.RelationField;
import org.bonitasoft.studio.businessobject.editor.model.RelationType;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.refactor.DiffElement;
import org.bonitasoft.studio.businessobject.refactor.Event;
import org.bonitasoft.studio.common.properties.Well;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class RelationFieldDetailsControl extends Composite {

    private BusinessDataModelFormPage formPage;
    private IObservableValue<Field> selectedFieldObservable;
    private DataBindingContext ctx;
    private IObservableValue<String> descriptionObservable;

    public RelationFieldDetailsControl(Composite parent, BusinessDataModelFormPage formPage,
            IObservableValue<Field> selectedFieldObservable, IObservableValue<String> descriptionObservable,
            DataBindingContext ctx) {
        super(parent, SWT.None);
        this.descriptionObservable = descriptionObservable;
        setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        formPage.getToolkit().adapt(this);

        this.formPage = formPage;
        this.selectedFieldObservable = selectedFieldObservable;
        this.ctx = ctx;

        createDescriptionTextArea();
        createRelationKindLabel();
        createRelationKindCombo();
        createLoadingModeRadioButtons();
    }

    private void createDescriptionTextArea() {
        new TextAreaWidget.Builder()
                .withLabel(Messages.fieldDescriptionPlaceholder)
                .labelAbove()
                .widthHint(500)
                .heightHint(70)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(this);
    }

    private void createLoadingModeRadioButtons() {
        Composite buttonsComposite = formPage.getToolkit().createComposite(this);
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(20, 5).create());
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        Button lazyRadio = formPage.getToolkit().createButton(buttonsComposite, Messages.loadOnDemand, SWT.RADIO);
        lazyRadio.setLayoutData(GridDataFactory.fillDefaults().create());

        ControlDecoration lazyDecorator = new ControlDecoration(lazyRadio, SWT.RIGHT);
        lazyDecorator.setImage(Pics.getImage(PicsConstants.hint));
        lazyDecorator.setDescriptionText(Messages.loadOnDemandHint);

        Button eagerRadio = formPage.getToolkit().createButton(buttonsComposite, Messages.alwaysLoad, SWT.RADIO);
        eagerRadio.setLayoutData(GridDataFactory.fillDefaults().create());

        ControlDecoration eagerDecorator = new ControlDecoration(eagerRadio, SWT.RIGHT);
        eagerDecorator.setImage(Pics.getImage(PicsConstants.hint));
        eagerDecorator.setDescriptionText(Messages.alwaysLoadHint);

        SelectObservableValue<FetchType> radioGroupObservable = new SelectObservableValue<>(FetchType.class);
        radioGroupObservable.addOption(FetchType.LAZY, WidgetProperties.selection().observe(lazyRadio));
        radioGroupObservable.addOption(FetchType.EAGER, WidgetProperties.selection().observe(eagerRadio));

        IObservableValue<FetchType> fetchTypeObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                selectedFieldObservable, BusinessDataModelPackage.Literals.RELATION_FIELD__FETCH_TYPE);
        ctx.bindValue(radioGroupObservable, fetchTypeObservable);

        var composite = formPage.getToolkit().createComposite(this);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());
        var warning = new Well(composite, Messages.eagerRelationWarning, formPage.getToolkit(), IStatus.WARNING);
        warning.setVisible(fetchTypeObservable.getValue() == FetchType.EAGER);
        fetchTypeObservable.addValueChangeListener(e -> warning.setVisible(e.diff.getNewValue() == FetchType.EAGER));
    }

    private void createRelationKindLabel() {
        Composite composite = formPage.getToolkit().createComposite(this);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());

        Label relationLabel = formPage.getToolkit().createLabel(composite, Messages.relation);
        relationLabel.setLayoutData(GridDataFactory.fillDefaults().create());

        ControlDecoration controlDecoration = new ControlDecoration(relationLabel, SWT.RIGHT);
        controlDecoration.setDescriptionText(Messages.realtionTooltip);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
    }

    private void createRelationKindCombo() {
        ComboViewer relationComboViewer = new ComboViewer(this, SWT.BORDER | SWT.READ_ONLY);
        relationComboViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().indent(0, -5).create());
        relationComboViewer.setContentProvider(ArrayContentProvider.getInstance());
        relationComboViewer.setLabelProvider(new LabelProviderBuilder<RelationType>()
                .withTextProvider(type -> Objects.equals(type, RelationType.AGGREGATION)
                        ? Messages.aggregation
                        : Messages.composition)
                .createLabelProvider());
        relationComboViewer.setInput(RelationType.values());

        IObservableValue<RelationType> selection = ViewersObservables.observeSingleSelection(relationComboViewer);
        IObservableValue<RelationType> relationTypeObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                selectedFieldObservable, BusinessDataModelPackage.Literals.RELATION_FIELD__TYPE);

        relationTypeObservable.addValueChangeListener(e -> {
            if (selectedFieldObservable.getValue() != null && e.diff.getOldValue() != null && e.diff.getNewValue() != null) {
                RelationField oldValue = (RelationField) EcoreUtil.copy(selectedFieldObservable.getValue());
                oldValue.setType(e.diff.getOldValue());
                DiffElement diffElement = new DiffElement(Event.UPDATE_ATTRIBUTE_TYPE, oldValue,
                        selectedFieldObservable.getValue());
                diffElement.addProperty(AttributeEditionControl.PARENT_QUALIFIED_NAME,
                        formPage.observeBusinessObjectSelected().getValue().getQualifiedName());
                formPage.refactorAccessControl(diffElement);
            }
        });
        ctx.bindValue(selection, relationTypeObservable);
    }

}
