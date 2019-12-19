/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.engine.bdm.model.field.RelationField;
import org.bonitasoft.engine.bdm.model.field.RelationField.FetchType;
import org.bonitasoft.engine.bdm.model.field.RelationField.Type;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.DateTypeLabels;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.FieldNameEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.FieldTypeEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.MandatoryEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.MultipleEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.FieldTypeLabelProvider;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.MandatoryCheckboxLabelProvider;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.MultipleCheckboxLabelProvider;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.RelationKindLabelProvider;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.widget.CComboWidget;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 */
public class AttributesTabItemControl extends AbstractTabItemControl {

    private final IObservableList fieldsList;
    private IViewerObservableValue attributeSelectionObservable;
    private final BusinessObjectModel businessObjectModel;
    private Group detailGroup;
    private StackLayout stackLayout;
    private Composite relationFieldContent;
    private Composite stringFieldContent;
    private Composite emptyContent;
    private Composite textFieldContent;
    private Composite dateFieldContent;
    private Composite dateOnlyFieldContent;
    private Composite datTimeFieldContent;
    private Composite datTimeInTimezoneFieldContent;
    private IDiffLogger diffLogger;

    public AttributesTabItemControl(final TabFolder parent, final DataBindingContext ctx,
            final IViewerObservableValue viewerObservableValue,
            final IObservableList fieldsList,
            final BusinessObjectModel businessObjectModel,
            IDiffLogger diffLogger) {
        super(parent, SWT.NONE);
        this.businessObjectModel = businessObjectModel;
        this.fieldsList = fieldsList;
        this.diffLogger = diffLogger;
        createControl(ctx, viewerObservableValue);
    }

    protected void createControl(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue) {
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());

        Composite attributeEditionComposite = new Composite(this, SWT.None);
        attributeEditionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        attributeEditionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        attributeSelectionObservable = createAttributeTableControl(attributeEditionComposite, ctx, viewerObservableValue);

        createAttributeDetailControl(ctx, viewerObservableValue);
    }

    private void createAttributeDetailControl(final DataBindingContext ctx,
            final IViewerObservableValue viewerObservableValue) {
        detailGroup = new Group(this, SWT.NONE);
        detailGroup.setText(Messages.details);
        detailGroup.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).indent(0, 10).create());
        stackLayout = new StackLayout();
        detailGroup.setLayout(stackLayout);

        relationFieldContent = createRelationFieldDetailContent(detailGroup, ctx, viewerObservableValue);
        stringFieldContent = createStringFieldDetailContent(detailGroup, ctx);
        dateFieldContent = createFieldDescriptionContent(detailGroup, Messages.dateDetails, Messages.legacyDateTooltip);
        dateOnlyFieldContent = createFieldDescriptionContent(detailGroup,
                String.format(Messages.dateOnlyDetails, DateTypeLabels.DATE_ONLY), null);
        datTimeFieldContent = createFieldDescriptionContent(detailGroup,
                String.format(Messages.dateTimeDetails, DateTypeLabels.DATE_AND_TIME,
                        DateTypeLabels.DATE_TIME_WITH_TIMEZONE),
                null);
        datTimeInTimezoneFieldContent = createFieldDescriptionContent(detailGroup,
                String.format(Messages.dateTimeInTimezoneDetails, DateTypeLabels.DATE_TIME_WITH_TIMEZONE), null);
        emptyContent = createNoDetailsContent(detailGroup);
        textFieldContent = createFieldDescriptionContent(detailGroup, Messages.textDetails, null);
        stackLayout.topControl = emptyContent;
        attributeSelectionObservable.addValueChangeListener(this::changeType);
    }

    private Composite createFieldDescriptionContent(Group detailGroup, String description, String tooltip) {
        final Composite composite = new Composite(detailGroup, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).create());
        final Label textLabel = new Label(composite, SWT.WRAP);
        textLabel.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, false).hint(400, SWT.DEFAULT)
                        .indent(tooltip != null ? 10 : 0, 0).create());
        textLabel.setText(description);
        if (tooltip != null) {
            ControlDecoration controlDecoration = new ControlDecoration(textLabel, SWT.LEFT | SWT.TOP, composite);
            controlDecoration.setMarginWidth(2);
            controlDecoration.setShowOnlyOnFocus(false);
            controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                    .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
            controlDecoration.setDescriptionText(tooltip);
        }
        return composite;
    }

    private void changeType(ValueChangeEvent event) {
        final Field selectedField = (Field) event.diff.getNewValue();
        if (selectedField != null) {
            detailGroup.setText(Messages.bind(Messages.detailsFor, selectedField.getName()));
        } else {
            detailGroup.setText(Messages.details);
            stackLayout.topControl = emptyContent;
        }
        if (selectedField instanceof RelationField) {
            stackLayout.topControl = relationFieldContent;
        } else if (selectedField instanceof SimpleField) {
            stackLayout.topControl = getFieldContent((SimpleField) selectedField);
        }
        detailGroup.layout();
    }

    protected Composite getFieldContent(final SimpleField selectedField) {
        switch (selectedField.getType()) {
            case STRING:
                return stringFieldContent;
            case TEXT:
                return textFieldContent;
            case DATE:
                return dateFieldContent;
            case LOCALDATE:
                return dateOnlyFieldContent;
            case LOCALDATETIME:
                return datTimeFieldContent;
            case OFFSETDATETIME:
                return datTimeInTimezoneFieldContent;
            default:
                return emptyContent;
        }
    }

    private Composite createNoDetailsContent(final Group detailGroup) {
        final Composite composite = new Composite(detailGroup, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        final Label noDetailLabel = new Label(composite, SWT.NONE);
        noDetailLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        noDetailLabel.setText(Messages.noDetailsAvailable);
        return composite;
    }

    private Composite createStringFieldDetailContent(final Group detailGroup, final DataBindingContext ctx) {
        final Composite composite = new Composite(detailGroup, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 0).create());
        composite.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).grab(true, false).create());

        final CComboWidget stringFieldCombo = new CComboWidget.Builder()
                .withLabel(Messages.length)
                .withItems(getStringLengthValues())
                .labelAbove()
                .alignMiddle()
                .widthHint(250)
                .bindTo(PojoObservables.observeDetailValue(attributeSelectionObservable, "length", Integer.class))
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(StringToNumberConverter.toInteger(false))
                        .withValidator(this::stringLengthValidator)
                        .create())
                .withModelToTargetStrategy(UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(NumberToStringConverter.fromInteger(false))
                        .create())
                .inContext(ctx)
                .createIn(composite);

        Label stringHelp = new Label(composite, SWT.WRAP);
        stringHelp.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        stringHelp.setText(Messages.stringLengthTooltip);

        attributeSelectionObservable.addValueChangeListener(e -> stringFieldCombo.getValueBinding().updateModelToTarget());
        return composite;
    }

    private IStatus stringLengthValidator(final Object value) {
        final Object selectedAttribute = attributeSelectionObservable.getValue();
        if (selectedAttribute == null) {
            return ValidationStatus.ok();
        }
        if (value == null || value.toString().isEmpty()) {
            return ValidationStatus.error(Messages.lengthCannotBeEmpty);
        }
        try {
            final int parseInt = Integer.parseInt((String) value);
            if (parseInt <= 0) {
                return ValidationStatus.error(Messages.lengthIsNotAPositiveNumber);
            }
        } catch (final NumberFormatException e) {
            return ValidationStatus.error(Messages.lengthIsNotANumber);
        }
        return ValidationStatus.ok();
    }

    protected String[] getStringLengthValues() {
        final List<String> values = new ArrayList<>();
        values.add("64");
        values.add("128");
        values.add("255");
        values.add("512");
        return values.toArray(new String[values.size()]);
    }

    @SuppressWarnings("unchecked")
    private Composite createRelationFieldDetailContent(final Group detailGroup, final DataBindingContext ctx,
            final IViewerObservableValue viewerObservableValue) {
        final Composite composite = new Composite(detailGroup, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 5).spacing(15, 10).create());

        final Label relationKindLabel = new Label(composite, SWT.NONE);
        relationKindLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        relationKindLabel.setText(Messages.relation);

        final ControlDecoration controlDecoration = new ControlDecoration(relationKindLabel, SWT.RIGHT);
        controlDecoration.setDescriptionText(Messages.realtionTooltip);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
        controlDecoration.setMarginWidth(-2);

        final ComboViewer relationComboViewer = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
        relationComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        relationComboViewer.setContentProvider(ArrayContentProvider.getInstance());
        relationComboViewer.setLabelProvider(new RelationKindLabelProvider());
        relationComboViewer.setInput(RelationField.Type.values());

        attributeSelectionObservable.addValueChangeListener(new IValueChangeListener<Field>() {

            private IObservableValue<Type> typeObservable;
            private IValueChangeListener<Type> changeRelationTypeListener = event -> {
                if (attributeSelectionObservable != null
                        && attributeSelectionObservable.getValue() instanceof RelationField) {
                    Type oldValue = event.diff.getOldValue() instanceof Type ? event.diff.getOldValue() : null;
                    Type newValue = event.diff.getNewValue() instanceof Type ? event.diff.getNewValue() : null;
                    if (oldValue != null && newValue != null) {
                        diffLogger.fieldRelationTypeChanged(
                                ((BusinessObject) viewerObservableValue.getValue()).getQualifiedName(),
                                ((Field) attributeSelectionObservable.getValue()).getName(),
                                ((RelationField) attributeSelectionObservable.getValue()).getReference().getQualifiedName(),
                                oldValue, newValue);
                    }
                }
            };
            private Binding bindValue;

            @Override
            public void handleValueChange(ValueChangeEvent<? extends Field> event) {
                Field selectedField = event.diff.getNewValue();
                if (selectedField instanceof RelationField) {
                    if (typeObservable != null) {
                        typeObservable.removeValueChangeListener(changeRelationTypeListener);
                    }
                    if (bindValue != null) {
                        bindValue.dispose();
                    }
                    typeObservable = PojoProperties.value("type").observe(selectedField);
                    typeObservable.addValueChangeListener(changeRelationTypeListener);
                    bindValue = ctx.bindValue(ViewersObservables.observeSingleSelection(relationComboViewer),
                            typeObservable);
                }

            }
        });

        new Label(composite, SWT.NONE);

        final Composite lazyRadioComposite = new Composite(composite, SWT.NONE);
        lazyRadioComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        lazyRadioComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());

        final Button lazyRadio = new Button(lazyRadioComposite, SWT.RADIO);
        lazyRadio.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());
        lazyRadio.setText(Messages.loadOnDemand);

        final ControlDecoration lazyDecorator = new ControlDecoration(lazyRadio, SWT.RIGHT);
        lazyDecorator.setImage(Pics.getImage(PicsConstants.hint));
        lazyDecorator.setDescriptionText(Messages.loadOnDemandHint);

        final Button eagerRadio = new Button(lazyRadioComposite, SWT.RADIO);
        eagerRadio.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());
        eagerRadio.setText(Messages.alwaysLoad);

        final ControlDecoration eagerDecorator = new ControlDecoration(eagerRadio, SWT.RIGHT);
        eagerDecorator.setImage(Pics.getImage(PicsConstants.hint));
        viewerObservableValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                if (event.diff.getNewValue() instanceof BusinessObject) {
                    final BusinessObject bo = (BusinessObject) event.diff.getNewValue();
                    if (bo != null) {
                        eagerDecorator.setDescriptionText(
                                Messages.bind(Messages.alwaysLoadHint, NamingUtils.getSimpleName(bo.getQualifiedName())));
                    }
                }
            }
        });

        final SelectObservableValue radioGroupObservable = new SelectObservableValue(FetchType.class);
        radioGroupObservable.addOption(FetchType.LAZY, SWTObservables.observeSelection(lazyRadio));
        radioGroupObservable.addOption(FetchType.EAGER, SWTObservables.observeSelection(eagerRadio));
        ctx.bindValue(radioGroupObservable,
                PojoObservables.observeDetailValue(attributeSelectionObservable, "fetchType", FetchType.class));

        return composite;
    }

    private IViewerObservableValue createAttributeTableControl(Composite parent, final DataBindingContext ctx,
            final IObservableValue<BusinessObject> viewerObservableValue) {
        final Composite buttonsComposite = new Composite(parent, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(0, 3).create());

        final Button addButton = createAddButton(buttonsComposite);

        UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject instanceof BusinessObject;
            }
        });

        ctx.bindValue(SWTObservables.observeEnabled(addButton), viewerObservableValue, null, enableStrategy);
        final Button upButton = createUpButton(viewerObservableValue, buttonsComposite);
        final Button downButton = createDownButton(viewerObservableValue, buttonsComposite);
        final Button deleteButton = createDeleteButton(buttonsComposite);

        final TableViewer featuresTableViewer = new TableViewer(parent,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        featuresTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true)
                        .create());
        featuresTableViewer.getTable().setEnabled(viewerObservableValue.getValue() instanceof BusinessObject);
        featuresTableViewer.getTable().setLinesVisible(true);
        featuresTableViewer.getTable().setHeaderVisible(true);
        featuresTableViewer.setContentProvider(new ObservableListContentProvider());
        ColumnViewerToolTipSupport.enableFor(featuresTableViewer);

        ctx.bindValue(SWTObservables.observeEnabled(featuresTableViewer.getTable()), viewerObservableValue, null,
                enableStrategy);
        final IViewerObservableValue observeAttributeSelection = ViewersObservables
                .observeSingleSelection(featuresTableViewer);

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(5, 100));
        tableLayout.addColumnData(new ColumnWeightData(4, 80));
        tableLayout.addColumnData(new ColumnWeightData(3, 20));
        tableLayout.addColumnData(new ColumnWeightData(3, 20));
        featuresTableViewer.getTable().setLayout(tableLayout);

        createFieldNameColumn(ctx, featuresTableViewer, viewerObservableValue);
        createFieldTypeColumn(ctx, featuresTableViewer, observeAttributeSelection, viewerObservableValue);
        createMultipleColumn(featuresTableViewer, viewerObservableValue);
        createMandatoryColumn(featuresTableViewer);

        //Resetting viewer input to avoid BS-16262
        viewerObservableValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                featuresTableViewer.setInput(fieldsList);
            }
        });
        fieldsList.addListChangeListener(new IListChangeListener() {

            @Override
            public void handleListChange(final ListChangeEvent event) {
                final IObservableList observableList = event.getObservableList();
                if (observableList != null && !observableList.isEmpty()) {
                    featuresTableViewer.getControl().getDisplay().asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            featuresTableViewer.getTable().select(0);
                        }
                    });
                }
            }
        });

        addButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                addField(viewerObservableValue, featuresTableViewer, observeAttributeSelection);
            }

        });

        deleteButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                deleteField(featuresTableViewer, viewerObservableValue);
            }

        });

        enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }
        });

        ctx.bindValue(SWTObservables.observeEnabled(deleteButton), observeAttributeSelection, null, enableStrategy);

        final UpdateValueStrategy upEnableStrategy = new UpdateValueStrategy();
        upEnableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject != null) {
                    final List<Field> eList = getFieldList(viewerObservableValue);
                    return eList.indexOf(fromObject) > 0;
                }
                return false;
            }
        });

        ctx.bindValue(SWTObservables.observeEnabled(upButton), observeAttributeSelection, null, upEnableStrategy);

        final UpdateValueStrategy downEnableStrategy = new UpdateValueStrategy();
        downEnableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                if (fromObject != null) {
                    final List<Field> eList = getFieldList(viewerObservableValue);
                    return eList.indexOf(fromObject) < eList.size() - 1 && eList.size() > 1;
                }
                return false;
            }
        });
        ctx.bindValue(SWTObservables.observeEnabled(downButton), observeAttributeSelection, null, downEnableStrategy);

        upButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                moveUp(viewerObservableValue, observeAttributeSelection);
                featuresTableViewer.refresh();
            }

        });

        downButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                moveDown(viewerObservableValue, observeAttributeSelection);
                featuresTableViewer.refresh();
            }

        });

        viewerObservableValue.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                final Object selectedBO = event.diff.getNewValue();
                if (selectedBO instanceof BusinessObject) {
                    final List<Field> fields = ((BusinessObject) selectedBO).getFields();
                    if (fields.isEmpty()) {
                        observeAttributeSelection.setValue(null);
                    } else {
                        observeAttributeSelection.setValue(fields.get(0));
                    }

                }

            }
        });

        return observeAttributeSelection;
    }

    protected void createFieldNameColumn(final DataBindingContext ctx, final TableViewer featuresTableViewer,
            final IObservableValue<BusinessObject> viewerObservableValue) {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(featuresTableViewer, SWT.FILL);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name);
        nameColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof Field) {
                    return ((Field) element).getName();
                }
                return super.getText(element);
            }
        });
        nameColumnViewer
                .setEditingSupport(
                        new FieldNameEditingSupport(viewerObservableValue, nameColumnViewer.getViewer(), ctx, diffLogger));
    }

    protected void createFieldTypeColumn(final DataBindingContext ctx, final TableViewer featuresTableViewer,
            final IViewerObservableValue fieldSingSelectionObervableValue, IObservableValue viewerObservableValue) {
        final TableViewerColumn typeColumnViewer = new TableViewerColumn(featuresTableViewer, SWT.FILL);
        final TableColumn column = typeColumnViewer.getColumn();
        column.setText(Messages.type);
        typeColumnViewer.setLabelProvider(new FieldTypeLabelProvider());
        typeColumnViewer
                .setEditingSupport(new FieldTypeEditingSupport(typeColumnViewer.getViewer(), businessObjectModel, fieldsList,
                        fieldSingSelectionObervableValue, viewerObservableValue, diffLogger));
    }

    protected void createMandatoryColumn(final TableViewer featuresTableViewer) {
        final TableViewerColumn mandatoryColumnViewer = new TableViewerColumn(featuresTableViewer, SWT.CENTER);
        final TableColumn column = mandatoryColumnViewer.getColumn();
        column.setText(Messages.mandatory);
        mandatoryColumnViewer.setLabelProvider(new MandatoryCheckboxLabelProvider(mandatoryColumnViewer.getViewer()));
        mandatoryColumnViewer.setEditingSupport(new MandatoryEditingSupport(mandatoryColumnViewer.getViewer()));
    }

    protected void createMultipleColumn(final TableViewer featuresTableViewer,
            IObservableValue<BusinessObject> boObservable) {
        final TableViewerColumn multipleColumnViewer = new TableViewerColumn(featuresTableViewer, SWT.CENTER);
        final TableColumn column = multipleColumnViewer.getColumn();
        column.setText(Messages.multiple);
        multipleColumnViewer.setLabelProvider(new MultipleCheckboxLabelProvider(multipleColumnViewer.getViewer()));
        multipleColumnViewer
                .setEditingSupport(new MultipleEditingSupport(multipleColumnViewer.getViewer()));
    }

    protected void addField(final IObservableValue<BusinessObject> viewerObservableValue,
            final TableViewer featuresTableViewer,
            final IViewerObservableValue observeAttributeSelection) {
        final SimpleField field = new SimpleField();
        field.setName(generateAttributeName(viewerObservableValue));
        field.setType(FieldType.STRING);
        field.setLength(255);
        field.setCollection(Boolean.FALSE);
        field.setNullable(Boolean.TRUE);
        fieldsList.add(field);
        diffLogger.fieldAdded(viewerObservableValue.getValue().getQualifiedName(), field.getName());
        observeAttributeSelection.setValue(field);
        featuresTableViewer.getControl().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                featuresTableViewer.editElement(field, 0);
            }
        });

    }

    protected void deleteField(final TableViewer featuresTableViewer, final IObservableValue viewerObservableValue) {
        final IStructuredSelection selection = (IStructuredSelection) featuresTableViewer.getSelection();
        selection.toList().stream().forEach(field -> diffLogger.fieldRemoved(
                ((BusinessObject) viewerObservableValue.getValue()).getQualifiedName(), ((Field) field).getName()));
        fieldsList.removeAll(selection.toList());
    }

    protected void moveUp(final IObservableValue observeFieldsDetailValue,
            final IViewerObservableValue observeSingleSelection) {
        final Field selectedFeature = (Field) observeSingleSelection.getValue();
        final List<Field> eList = getFieldList(observeFieldsDetailValue);
        final int indexOf = eList.indexOf(selectedFeature);
        Collections.swap(eList, indexOf, indexOf - 1);
        observeSingleSelection.setValue(null);
        observeSingleSelection.setValue(selectedFeature);
    }

    @SuppressWarnings("unchecked")
    protected List<Field> getFieldList(final IObservableValue viewerObservableValue) {
        return (List<Field>) PojoObservables.observeDetailValue(viewerObservableValue, "fields", null).getValue();
    }

    protected void moveDown(final IObservableValue observeFieldsDetailValue,
            final IViewerObservableValue observeSingleSelection) {
        final Field selectedFeature = (Field) observeSingleSelection.getValue();
        final List<Field> eList = getFieldList(observeFieldsDetailValue);
        final int indexOf = eList.indexOf(selectedFeature);
        Collections.swap(eList, indexOf, indexOf + 1);
        observeSingleSelection.setValue(null);
        observeSingleSelection.setValue(selectedFeature);
    }

    protected String generateAttributeName(final IObservableValue<BusinessObject> viewerObservableValue) {
        final Set<String> existingNames = new HashSet<>();
        final BusinessObject businessObject = viewerObservableValue.getValue();
        for (final Field feature : businessObject.getFields()) {
            existingNames.add(feature.getName());
        }
        return NamingUtils.generateNewName(existingNames, Messages.attribute, 1);
    }

}
