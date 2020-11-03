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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.filter.IndexableFieldFilter;
import org.bonitasoft.studio.businessobject.editor.editor.listener.ConstraintAttributeCheckListener;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.provider.FieldStyleStringProvider;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint;
import org.bonitasoft.studio.businessobject.editor.model.builder.UniqueConstraintBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.UniqueConstraintFieldsValidator;
import org.bonitasoft.studio.businessobject.validator.UniqueConstraintNameValidator;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckable;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class ConstraintEditionControl {

    public static final String CONSTRAINTS_LIST_VIEWER_ID = "constraintViewerid";
    public static final String CONSTRAINT_EDITION_VIEWER_ID = "constraintEditionViewerid";
    public static final String ADD_CONSTRAINT_BUTTON_ID = "addConstraintId";
    public static final String REMOVE_CONSTRAINTE_BUTTON_ID = "removeConstraintId";

    private IObservableList<UniqueConstraint> constraintsObservable;
    private AbstractBdmFormPage formPage;
    private IObservableValue<UniqueConstraint> selectedConstraintObservable;
    private IObservableValue<Field> selectedAttributeObservable;
    private CheckboxTableViewer constraintEditionViewer;
    private IObservableList<Field> actualsFieldsObservable;
    private WritableSet<Field> attributesSetObservable = new WritableSet<>();
    private TableViewer constraintViewer;
    private FieldStyleStringProvider fieldStyleStringProvider;
    private Section section;
    private Composite mainComposite;
    private ToolItem deleteConstraintItem;
    private List<UniqueConstraint> constraintsToFilter = new ArrayList<>();
    private Color errorColor;

    public ConstraintEditionControl(Composite parent,
            AbstractBdmFormPage formPage,
            DataBindingContext ctx) {
        this.formPage = formPage;
        this.fieldStyleStringProvider = new FieldStyleStringProvider();
        this.actualsFieldsObservable = EMFObservables.observeDetailList(Realm.getDefault(),
                formPage.observeBusinessObjectSelected(),
                BusinessDataModelPackage.Literals.BUSINESS_OBJECT__FIELDS);
        this.constraintsObservable = EMFObservables.observeDetailList(Realm.getDefault(),
                formPage.observeBusinessObjectSelected(),
                BusinessDataModelPackage.Literals.BUSINESS_OBJECT__UNIQUE_CONSTRAINTS);
        this.errorColor = new LocalResourceManager(JFaceResources.getResources(),
                parent).createColor(ColorConstants.ERROR_RGB);

        this.mainComposite = formPage.getToolkit().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createConstraintDefinitionSection();
        createConstraintEditionComposite(ctx);
        enableButtons(ctx);

        ctx.bindSet(ViewerProperties.checkedElements(Field.class).observe((ICheckable) constraintEditionViewer),
                attributesSetObservable);
        selectedConstraintObservable.addValueChangeListener(e -> {
            attributesSetObservable.clear();
            if (e.diff.getNewValue() != null) {
                e.diff.getNewValue().getFieldNames().stream()
                        .map(this::findCorrespondingField)
                        .forEach(attributesSetObservable::add);
                constraintEditionViewer.refresh();
            }
        });
    }

    private void createConstraintDefinitionSection() {
        this.section = formPage.getToolkit().createSection(mainComposite, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayout(GridLayoutFactory.fillDefaults().create());
        client.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        createContraintsDefinitionComposite(client, formPage);

        section.setClient(client);
    }

    private void enableButtons(DataBindingContext ctx) {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteConstraintItem),
                new ComputedValue<Boolean>() {

                    @Override
                    protected Boolean calculate() {
                        return selectedConstraintObservable.getValue() != null;
                    }
                });
    }

    private Field findCorrespondingField(String fieldName) {
        return actualsFieldsObservable.stream()
                .filter(field -> Objects.equals(field.getName(), fieldName))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(String.format("Unable to find a field with the name %s", fieldName)));
    }

    private void createConstraintEditionComposite(DataBindingContext ctx) {
        Composite composite = formPage.getToolkit().createComposite(mainComposite);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createConstraintDescriptionSection(composite, ctx);
        createConstraintEditionSection(composite);

        ctx.bindValue(WidgetProperties.visible().observe(composite), new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return selectedConstraintObservable.getValue() != null;
            }
        });
    }

    private void createConstraintDescriptionSection(Composite parent, DataBindingContext ctx) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.description);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());

        IObservableValue descriptionObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                selectedConstraintObservable, BusinessDataModelPackage.Literals.UNIQUE_CONSTRAINT__DESCRIPTION);

        new TextAreaWidget.Builder()
                .widthHint(500)
                .heightHint(70)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(client);

        section.setClient(client);
    }

    private void createConstraintEditionSection(Composite parent) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.attributes);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());

        formPage.getToolkit()
                .createLabel(client, Messages.selectUniqueConstraintFieldsMessage, SWT.WRAP)
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        formPage.getToolkit()
                .createLabel(client, Messages.warningTextConstraint, SWT.WRAP)
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        createValidationLabel(client);
        createConstraintEditionViewer(client);

        section.setClient(client);
    }

    private void createValidationLabel(Composite client) {
        Label validationLabel = formPage.getToolkit().createLabel(client, "", SWT.WRAP);
        validationLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        validationLabel.addPaintListener(e -> validationLabel.setForeground(errorColor));

        attributesSetObservable.addChangeListener(e -> {
            if (selectedConstraintObservable.getValue() != null) {
                if (!attributesSetObservable.isEmpty()) {
                    validationLabel.setVisible(false);
                    ((GridData) validationLabel.getLayoutData()).exclude = true;
                } else {
                    validationLabel.setVisible(true);
                    ((GridData) validationLabel.getLayoutData()).exclude = false;
                    validationLabel.setText(
                            String.format(Messages.constraintFieldEmptiness,
                                    selectedConstraintObservable.getValue().getName()));
                }
                client.layout();
            }
        });
    }

    private void createConstraintEditionViewer(Composite parent) {
        constraintEditionViewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER);
        constraintEditionViewer.getTable()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        constraintEditionViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, CONSTRAINT_EDITION_VIEWER_ID);
        formPage.getToolkit().adapt(constraintEditionViewer.getTable(), true, true);

        constraintEditionViewer.setContentProvider(new ObservableListContentProvider());
        constraintEditionViewer.setLabelProvider(new LabelProviderBuilder<Field>()
                .withStyledStringProvider(fieldStyleStringProvider)
                .createStyledCellLabelProvider());
        constraintEditionViewer.addFilter(new IndexableFieldFilter());
        constraintEditionViewer.setInput(actualsFieldsObservable);

        selectedAttributeObservable = ViewerProperties.singleSelection(Field.class).observe(constraintEditionViewer);
        constraintEditionViewer.addCheckStateListener(
                new ConstraintAttributeCheckListener(selectedConstraintObservable, selectedAttributeObservable,
                        formPage, constraintViewer));
    }

    private void createContraintsDefinitionComposite(Composite parent, AbstractBdmFormPage formPage) {
        Composite composite = formPage.getToolkit().createComposite(parent);
        composite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1).margins(5, 10)
                        .create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(500, SWT.DEFAULT).create());

        createToolbar(composite);
        createSearchField(composite);
        createConstraintsDefinitionViewer(formPage, composite);
    }

    private void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                constraintsToFilter.clear();
                constraintsObservable.stream()
                        .filter(constraint -> !constraint.getName().toLowerCase().contains(search))
                        .forEach(constraintsToFilter::add);
                constraintViewer.refresh();
            });
        });
    }

    protected TextWidget createSearchWidget(Composite parent) {
        return new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withPlaceholder(Messages.searchConstraint)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof UniqueConstraint) {
                    return !constraintsToFilter.contains(element);
                }
                return false;
            }
        };
    }

    private void createConstraintsDefinitionViewer(AbstractBdmFormPage formPage, Composite parent) {
        constraintViewer = new TableViewer(parent,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        constraintViewer.getTable()
                .setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
        constraintViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, CONSTRAINTS_LIST_VIEWER_ID);
        formPage.getToolkit().adapt(constraintViewer.getTable());
        ColumnViewerToolTipSupport.enableFor(constraintViewer);
        constraintViewer.setUseHashlookup(true);
        constraintViewer.getTable().setHeaderVisible(true);
        constraintViewer.addFilter(createSearchFilter());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        constraintViewer.getTable().setLayout(layout);

        createConstraintnameColumn(constraintViewer);
        createAttributesColumn(constraintViewer);

        constraintViewer.setContentProvider(new ObservableListContentProvider());
        constraintViewer.setInput(constraintsObservable);
        selectedConstraintObservable = ViewerProperties.singleSelection(UniqueConstraint.class).observe(constraintViewer);
    }

    private void createAttributesColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.LEFT);
        column.getColumn().setText(Messages.attributes);
        UniqueConstraintFieldsValidator uniqueConstraintFieldsValidator = new UniqueConstraintFieldsValidator();
        column.setLabelProvider(new LabelProviderBuilder<UniqueConstraint>()
                .withTextProvider(constraint -> constraint.getFieldNames().toString())
                .withStatusProvider(constraint -> uniqueConstraintFieldsValidator.validate(constraint))
                .createColumnLabelProvider());
    }

    private void createConstraintnameColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.name);
        UniqueConstraintNameValidator constraintNameValidator = new UniqueConstraintNameValidator();

        column.setLabelProvider(new LabelProviderBuilder<UniqueConstraint>()
                .withTextProvider(element -> element.getName())
                .withStatusProvider(constraint -> constraintNameValidator.validate(constraint))
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<UniqueConstraint>(viewer)
                .withId(SWTBotConstants.SWTBOT_ID_UNIQUE_CONSTRAINT_NAME_TEXTEDITOR)
                .withValueProvider(UniqueConstraint::getName)
                .withValueUpdater((constraint, newName) -> {
                    if (!Objects.equals(constraint.getName(), newName)) {
                        constraint.setName((String) newName);
                    }
                })
                .create());
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.LEFT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        ToolItem addConstraintItem = new ToolItem(toolBar, SWT.PUSH);
        addConstraintItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_CONSTRAINT_BUTTON_ID);
        addConstraintItem.setImage(BusinessObjectPlugin.getImage("/icons/add.png"));
        addConstraintItem.setToolTipText(Messages.addConstraintTooltip);
        addConstraintItem.addListener(SWT.Selection, e -> addUniqueConstraint());

        deleteConstraintItem = new ToolItem(toolBar, SWT.PUSH);
        deleteConstraintItem.setImage(BusinessObjectPlugin.getImage("/icons/delete_icon.png"));
        deleteConstraintItem.setToolTipText(Messages.deleteConstraintTooltip);
        deleteConstraintItem.addListener(SWT.Selection, e -> removeSelectedConstraint());
    }

    private void removeSelectedConstraint() {
        if (MessageDialog.openQuestion(mainComposite.getShell(), Messages.deleteConstraintConfirmTitle,
                String.format(Messages.deleteConstraintConfirmMessage, selectedConstraintObservable.getValue().getName()))) {
            constraintsObservable.remove(selectedConstraintObservable.getValue());
            formPage.updateDefaultQueries();
        }
    }

    private void addUniqueConstraint() {
        constraintViewer.applyEditorValue();
        String name = StringIncrementer.getNextIncrement(Messages.defaultConstraintName,
                constraintsObservable.stream().map(UniqueConstraint::getName).collect(Collectors.toList()));
        UniqueConstraint newConstraint = new UniqueConstraintBuilder()
                .withName(name)
                .create();
        constraintsObservable.add(newConstraint);
        Display.getDefault().asyncExec(() -> constraintViewer.editElement(newConstraint, 0));
    }

    public void refreshConstraintList() {
        constraintViewer.refresh();
        constraintEditionViewer.refresh();
    }

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.<Section, String> value("text").observe(section);
    }

    public IObservableValue<Boolean> observeSectionVisible() {
        return WidgetProperties.visible().observe(mainComposite);
    }

    public void dispose() {
        fieldStyleStringProvider.dispose();
    }

}
