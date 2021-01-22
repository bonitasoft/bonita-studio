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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.filter.SearchFilter;
import org.bonitasoft.studio.businessobject.editor.editor.ui.editingSupport.FieldTypeEditingSupport;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.model.BusinessDataModelFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.provider.TypeLabelProvider;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.refactor.DiffElement;
import org.bonitasoft.studio.businessobject.refactor.Event;
import org.bonitasoft.studio.businessobject.validator.FieldNameValidator;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.CheckboxLabelProviderBuilder;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.Section;

public class AttributeEditionControl extends Composite {

    public static final String PARENT_QUALIFIED_NAME = "parentQualifiedName";
    public static final String ATTRIBUTE_LIST_VIEWER_ID = "attributeViewerId";
    public static final String ADD_ATTRIBUTE_BUTTON_ID = "addAttributeId";
    public static final String REMOVE_ATTRIBUTE_BUTTON_ID = "removeAttributeId";

    public static final String DEFAULT_FIELD_NAME = "attribute";
    public static final int DEFAULT_LENGTH = 255;

    private BusinessDataModelFormPage formPage;
    private IObservableValue<BusinessObject> selectedBoObservable;
    private IObservableList<Field> fieldsObservable;
    private DataBindingContext ctx;
    private IObservableValue<Field> selectedFieldObservable;
    private ToolItem deleteFieldItem;
    private ToolItem upFieldItem;
    private ToolItem downFieldItem;
    private FieldDetailsControl fieldDetailsControl;
    private List<Field> fieldToFilter = new ArrayList<>();
    private TableViewer viewer;

    public AttributeEditionControl(Composite parent,
            BusinessDataModelFormPage formPage,
            IObservableValue<BusinessObject> selectedBoObservable,
            DataBindingContext ctx) {
        super(parent, SWT.None);
        setLayout(GridLayoutFactory.fillDefaults().create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        formPage.getToolkit().adapt(this);
        this.formPage = formPage;
        this.selectedBoObservable = selectedBoObservable;
        this.ctx = ctx;

        fieldsObservable = EMFObservables.observeDetailList(Realm.getDefault(), selectedBoObservable,
                BusinessDataModelPackage.Literals.BUSINESS_OBJECT__FIELDS);

        Composite viewerComposite = formPage.getToolkit().createComposite(this);
        viewerComposite.setLayout(GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 300).create());

        Composite toolbarComposite = formPage.getToolkit().createComposite(viewerComposite);
        toolbarComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        toolbarComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        createToolbar(toolbarComposite);
        createSearchField(toolbarComposite);
        createAttributeTableViewer(viewerComposite);

        createDetailsSection();
        enableButtons();
        selectedBoObservable.addValueChangeListener(e -> selectedFieldObservable.setValue(null));
    }

    private void createSearchField(Composite parent) {
        TextWidget searchWidget = new SearchWidget.Builder()
                .labelAbove()
                .widthHint(300)
                .withPlaceholder(Messages.searchAttribute)
                .adapt(formPage.getToolkit())
                .createIn(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                fieldToFilter.clear();
                fieldsObservable.stream()
                        .filter(field -> !field.getName().toLowerCase().contains(search))
                        .forEach(fieldToFilter::add);
                viewer.refresh();

                // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
                // TODO Hopefully this could be removed on the futur (current date: 23/11/2020)
                if (Objects.equals(Platform.getOS(), Platform.OS_MACOSX)) {
                    viewer.getControl().redraw();
                }
            });
        });
    }

    private void createDetailsSection() {
        Section detailsSection = formPage.getToolkit().createSection(this, Section.EXPANDED);
        detailsSection.setLayout(GridLayoutFactory.fillDefaults().create());
        detailsSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        detailsSection.setText(Messages.details);
        fieldDetailsControl = new FieldDetailsControl(detailsSection, formPage, selectedFieldObservable, ctx);
        detailsSection.setClient(fieldDetailsControl);
        ctx.bindValue(WidgetProperties.visible().observe(detailsSection), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectedFieldObservable.getValue() != null)
                .build());
    }

    private void enableButtons() {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteFieldItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectedFieldObservable.getValue() != null)
                .build());

        ctx.bindValue(WidgetProperties.enabled().observe(upFieldItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectedFieldObservable.getValue() != null
                        && fieldsObservable.indexOf(selectedFieldObservable.getValue()) > 0)
                .build());

        ctx.bindValue(WidgetProperties.enabled().observe(downFieldItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectedFieldObservable.getValue() != null
                        && fieldsObservable.indexOf(selectedFieldObservable.getValue()) < fieldsObservable.size() - 1)
                .build());
    }

    private void createAttributeTableViewer(Composite parent) {
        viewer = new TableViewer(parent,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ATTRIBUTE_LIST_VIEWER_ID);
        formPage.getToolkit().adapt(viewer.getTable());
        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.setUseHashlookup(true);
        viewer.getTable().setHeaderVisible(true);
        viewer.addFilter(new SearchFilter<>(fieldToFilter));

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(5, true));
        layout.addColumnData(new ColumnWeightData(4, true));
        layout.addColumnData(new ColumnWeightData(3, true));
        layout.addColumnData(new ColumnWeightData(3, true));
        viewer.getTable().setLayout(layout);

        createNameColumn(viewer);
        createTypeColumn(viewer);
        createMultipleColumn(viewer);
        createMandatoryColumn(viewer);

        viewer.setContentProvider(new ObservableListContentProvider());
        viewer.setInput(fieldsObservable);
        selectedFieldObservable = ViewerProperties.singleSelection(Field.class).observe(viewer);

        // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
        // TODO Hopefully this could be removed on the futur (current date: 19/11/2020)
        if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
            selectedBoObservable.addValueChangeListener(e -> viewer.getTable().redraw());
        }

        addDragAndDropSupport();
    }

    private void addDragAndDropSupport() {
        viewer.addDropSupport(DND.DROP_MOVE | DND.DROP_MOVE | DND.DROP_DEFAULT,
                new Transfer[] { FieldTransfer.getInstance() },
                new DropTargetAdapter() {

                    @Override
                    public void dragOver(DropTargetEvent event) {
                        event.feedback = DND.FEEDBACK_INSERT_BEFORE;
                    }

                    @Override
                    public void drop(DropTargetEvent event) {
                        dragLeave(event);
                        Widget item = event.item;
                        Field droppedField = (Field) event.data;
                        int dropIndex = item != null
                                ? viewer.getTable().indexOf((TableItem) item)
                                : fieldsObservable.size();
                        int oldIndex = fieldsObservable.indexOf(droppedField);
                        fieldsObservable.move(oldIndex,
                                dropIndex - (item != null && oldIndex > dropIndex ? 0 : 1));
                        viewer.refresh();
                    }
                });

        viewer.addDragSupport(DND.DROP_MOVE, new Transfer[] { FieldTransfer.getInstance() },
                dragSourceAdapter(selectedFieldObservable));
    }

    private DragSourceAdapter dragSourceAdapter(IObservableValue<Field> observable) {
        return new DragSourceAdapter() {

            @Override
            public void dragStart(DragSourceEvent event) {
                event.detail = DND.DROP_MOVE;
                dragSetData(event);
            }

            @Override
            public void dragSetData(DragSourceEvent event) {
                event.doit = observable.getValue() != null;
                event.data = observable.getValue();
            }
        };
    }

    // kinda confusing because the field is called 'mandatory' in the U.I but 'nullable' in the engine model, so we have to negate everything
    private void createMandatoryColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.CENTER);
        column.getColumn().setText(Messages.mandatory);
        column.setLabelProvider(new CheckboxLabelProviderBuilder<Field>()
                .withIsSelectedProvider(field -> !field.isNullable())
                .withIsEnableProvider(field -> !field.isCollection())
                .withTooltipProvider(field -> field.isCollection() ? Messages.disabledMandatoryTooltip : null)
                .createCheckboxLabelProvider(viewer));
        column.setEditingSupport(new EditingSupportBuilder<Field>(viewer)
                .withCanEditProvider(field -> !field.isCollection())
                .withCellEditorProvider(field -> new CheckboxCellEditor((Composite) viewer.getControl(), SWT.CHECK))
                .withValueProvider(field -> !field.isNullable())
                .withValueUpdater((field, mandatory) -> field.setNullable(!(boolean) mandatory))
                .create());
    }

    private void createMultipleColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.CENTER);
        column.getColumn().setText(Messages.multiple);
        column.setLabelProvider(new CheckboxLabelProviderBuilder<Field>()
                .withIsSelectedProvider(Field::isCollection)
                .createCheckboxLabelProvider(viewer));
        column.setEditingSupport(new EditingSupportBuilder<Field>(viewer)
                .withCellEditorProvider(field -> new CheckboxCellEditor((Composite) viewer.getControl(), SWT.CHECK))
                .withValueProvider(field -> field.isCollection())
                .withValueUpdater((field, multiple) -> {
                    field.setCollection((boolean) multiple);
                    if ((boolean) multiple) {
                        field.setNullable(true);
                    }
                })
                .create());
    }

    private void createTypeColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.type);
        column.setLabelProvider(new TypeLabelProvider(formPage.observeWorkingCopy()));
        column.setEditingSupport(new FieldTypeEditingSupport(viewer, formPage));
    }

    private void createNameColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.name);
        FieldNameValidator fieldNameValidator = new FieldNameValidator();

        column.setLabelProvider(new LabelProviderBuilder<Field>()
                .withStyledStringProvider((element -> new StyledString(element.getName())))
                .withStatusProvider(fieldNameValidator::validate)
                .shouldRefreshAllLabels(viewer)
                .createStyledCellLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<Field>(viewer)
                .withId(SWTBotConstants.SWTBOT_ID_ATTRIBUTE_NAME_TEXTEDITOR)
                .withValueProvider(Field::getName)
                .withValueUpdater((field, newName) -> {
                    String oldName = field.getName();
                    if (!Objects.equals(oldName, newName)) {
                        Field oldField = EcoreUtil.copy(field);
                        field.setName((String) newName);
                        refactorConstraintsOnRename(oldName, (String) newName);
                        refactorIndexesOnRename(oldName, (String) newName);
                        refactorQueriesOnRename(oldName, (String) newName);
                        DiffElement diff = new DiffElement(Event.RENAME_ATTRIBUTE, oldField, field);
                        diff.addProperty(PARENT_QUALIFIED_NAME, ((BusinessObject) field.eContainer()).getQualifiedName());
                        formPage.refactorAccessControl(diff);
                    }
                })
                .create());
    }

    private void refactorQueriesOnRename(String oldName, String newName) {
        formPage.updateDefaultQueries();
        // TODO: refactor the JPQL expression -> complicated ?
        formPage.getEditorContribution().refreshQueryViewers();
    }

    private void refactorConstraintsOnRename(String oldName, String newName) {
        selectedBoObservable.getValue().getUniqueConstraints().stream()
                .filter(constraint -> constraint.getFieldNames().contains(oldName))
                .forEach(constraint -> {
                    int index = constraint.getFieldNames().indexOf(oldName);
                    constraint.getFieldNames().remove(oldName);
                    if (!constraint.getFieldNames().contains(newName)) {
                        constraint.getFieldNames().add(index, newName);
                    }
                    formPage.getEditorContribution().refreshConstraintList();
                });
    }

    private void refactorConstraintsOnDelete(Field fieldDeleted) {
        selectedBoObservable.getValue().getUniqueConstraints().stream()
                .filter(constraint -> constraint.getFieldNames().contains(fieldDeleted.getName()))
                .forEach(constraint -> constraint.getFieldNames().remove(fieldDeleted.getName()));
        selectedBoObservable.getValue().getUniqueConstraints()
                .removeIf(constraint -> constraint.getFieldNames().isEmpty());
        formPage.getEditorContribution().refreshConstraintList();
    }

    private void refactorIndexesOnRename(String oldName, String newName) {
        selectedBoObservable.getValue().getIndexes().stream()
                .filter(index -> index.getFieldNames().contains(oldName))
                .forEach(index -> {
                    int i = index.getFieldNames().indexOf(oldName);
                    index.getFieldNames().remove(oldName);
                    if (!index.getFieldNames().contains(newName)) {
                        index.getFieldNames().add(i, newName);
                    }
                    formPage.getEditorContribution().refreshIndexList();
                });
    }

    private void refactorIndexesOnDelete(Field fieldDeleted) {
        selectedBoObservable.getValue().getIndexes().stream()
                .filter(index -> index.getFieldNames().contains(fieldDeleted.getName()))
                .forEach(index -> index.getFieldNames().remove(fieldDeleted.getName()));
        selectedBoObservable.getValue().getIndexes().removeIf(index -> index.getFieldNames().isEmpty());
        formPage.getEditorContribution().refreshIndexList();
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.LEFT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        ToolItem addFieldItem = new ToolItem(toolBar, SWT.PUSH);
        addFieldItem.setImage(BusinessObjectPlugin.getImage("/icons/add.png"));
        addFieldItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_ATTRIBUTE_BUTTON_ID);
        addFieldItem.setToolTipText(Messages.addFieldTooltip);
        addFieldItem.addListener(SWT.Selection, e -> addAttribute());

        deleteFieldItem = new ToolItem(toolBar, SWT.PUSH);
        deleteFieldItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_ATTRIBUTE_BUTTON_ID);
        deleteFieldItem.setImage(BusinessObjectPlugin.getImage("/icons/delete_icon.png"));
        deleteFieldItem.setToolTipText(Messages.deleteFieldTooltip);
        deleteFieldItem.addListener(SWT.Selection, e -> removeSelectedAttribute());

        upFieldItem = new ToolItem(toolBar, SWT.PUSH);
        upFieldItem.setImage(BusinessObjectPlugin.getImage("/icons/up.png"));
        upFieldItem.setToolTipText(Messages.up);
        upFieldItem.addListener(SWT.Selection, e -> upSelectedAttribute());

        downFieldItem = new ToolItem(toolBar, SWT.PUSH);
        downFieldItem.setImage(BusinessObjectPlugin.getImage("/icons/down.png"));
        downFieldItem.setToolTipText(Messages.down);
        downFieldItem.addListener(SWT.Selection, e -> downSelectedAttribute());
    }

    private void downSelectedAttribute() {
        Field field = selectedFieldObservable.getValue();
        int oldIndex = fieldsObservable.indexOf(field);
        fieldsObservable.move(oldIndex, oldIndex + 1);
        viewer.refresh();
    }

    private void upSelectedAttribute() {
        Field field = selectedFieldObservable.getValue();
        int oldIndex = fieldsObservable.indexOf(field);
        fieldsObservable.move(oldIndex, oldIndex - 1);
        viewer.refresh();
    }

    private void removeSelectedAttribute() {
        Field field = selectedFieldObservable.getValue();
        if (MessageDialog.openQuestion(getShell(), Messages.deleteFieldConfirmTitle,
                String.format(Messages.deleteFieldConfirmMessage, field.getName()))) {
            refactorConstraintsOnDelete(field);
            refactorIndexesOnDelete(field);
            DiffElement diff = new DiffElement(Event.REMOVE_ATTRIBUTE, field, null);
            diff.addProperty(PARENT_QUALIFIED_NAME, ((BusinessObject) field.eContainer()).getQualifiedName());
            formPage.refactorAccessControl(diff);
            selectedBoObservable.getValue().getFields().remove(field);
            formPage.updateDefaultQueries();
        }
    }

    private void addAttribute() {
        viewer.applyEditorValue();
        String fieldName = StringIncrementer.getNextIncrement(DEFAULT_FIELD_NAME,
                selectedBoObservable.getValue().getFields().stream().map(Field::getName)
                        .collect(Collectors.toList()));
        Field newField = new SimpleFieldBuilder()
                .withName(fieldName)
                .withType(FieldType.STRING)
                .withLength(DEFAULT_LENGTH)
                .create();
        selectedBoObservable.getValue().getFields().add(newField);
        viewer.getControl().getDisplay().asyncExec(() -> viewer.editElement(newField, 0));
        formPage.updateDefaultQueries();
    }

    public void updateFieldDetailsTopControl() {
        fieldDetailsControl.updateTopControl();
    }

}
