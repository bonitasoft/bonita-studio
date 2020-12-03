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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.index;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.editor.editor.filter.IndexableFieldFilter;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute.FieldTransfer;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.provider.FieldStyleStringProvider;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.forms.widgets.Section;

public class IndexEditionControl extends Composite {

    public static final String INDEXED_FIELDS_VIEWER_ID = "indexedFieldsViewerId";
    public static final String AVAILABLE_FIELDS_VIEWER_ID = "availableFieldsViewerId";

    private AbstractBdmFormPage formPage;
    private IObservableValue<Index> selectedIndexObservable;
    private IObservableList<Field> actualsFieldsObservable;
    private IndexableFieldFilter indexableFieldFilter;
    private FieldStyleStringProvider fieldStyleStringProvider;
    private TableViewer availableAttributesTableViewer;
    private TableViewer indexedAttributesTableViewer;
    private IObservableList<String> indexedFieldNameObservable;
    private IObservableList<Field> indexedFieldsObservable;
    private IObservableList<Field> selectedAvailableAttributeObservable;
    private IObservableList<Field> selectedIndexedAttributeObservable;
    private Cursor cursorHand;
    private Cursor cursorArrow;
    private Color errorColor;

    public IndexEditionControl(Section parent, AbstractBdmFormPage formPage, DataBindingContext ctx,
            IObservableValue<Index> selectedIndexObservable, IObservableList<Field> actualsFieldsObservable) {
        super(parent, SWT.NONE);
        formPage.getToolkit().adapt(this);
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        setLayout(GridLayoutFactory.fillDefaults().margins(15, 10).create());

        this.formPage = formPage;
        this.selectedIndexObservable = selectedIndexObservable;
        this.actualsFieldsObservable = actualsFieldsObservable;
        this.indexableFieldFilter = new IndexableFieldFilter();
        this.fieldStyleStringProvider = new FieldStyleStringProvider();
        this.cursorHand = new Cursor(parent.getDisplay(), SWT.CURSOR_HAND);
        this.cursorArrow = new Cursor(parent.getDisplay(), SWT.CURSOR_ARROW);
        this.errorColor = new LocalResourceManager(JFaceResources.getResources(),
                parent).createColor(ColorConstants.ERROR_RGB);

        indexedFieldNameObservable = EMFObservables.observeDetailList(Realm.getDefault(), selectedIndexObservable,
                BusinessDataModelPackage.Literals.INDEX__FIELD_NAMES);
        indexedFieldsObservable = new WritableList<>();

        bindIndexedFieldObservableLists(ctx, actualsFieldsObservable);

        createLabels();

        Composite viewersComposite = formPage.getToolkit().createComposite(this);
        viewersComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 10, 0).numColumns(2).create());
        viewersComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createAvailableAttributesTableViewer(viewersComposite);
        createIndexedAttributesTableViewer(viewersComposite);

        indexedFieldsObservable.addChangeListener(e -> availableAttributesTableViewer.refresh());

        selectedIndexObservable.addValueChangeListener(e -> {
            // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
            // TODO Hopefully this could be removed on the futur (current date: 23/11/2020)
            if (Objects.equals(Platform.getOS(), Platform.OS_MACOSX)) {
                availableAttributesTableViewer.getControl().redraw();
                indexedAttributesTableViewer.getControl().redraw();
            }
        });
    }

    private void bindIndexedFieldObservableLists(DataBindingContext ctx, IObservableList<Field> actualsFieldsObservable) {
        ctx.bindList(indexedFieldsObservable, indexedFieldNameObservable,
                UpdateStrategyFactory.updateListStrategyFactory()
                        .withConverter(ConverterBuilder.<Field, String> newConverter()
                                .fromType(Field.class)
                                .toType(String.class)
                                .withConvertFunction(Field::getName)
                                .create())
                        .create(),
                UpdateStrategyFactory.updateListStrategyFactory()
                        .withConverter(ConverterBuilder.<String, Field> newConverter()
                                .fromType(String.class)
                                .toType(Field.class)
                                .withConvertFunction(fieldName -> actualsFieldsObservable
                                        .stream()
                                        .filter(aField -> Objects.equals(fieldName, aField.getName()))
                                        .findFirst()
                                        .orElseThrow(() -> new IllegalArgumentException(
                                                String.format("The field %s doesn't exists.", fieldName))))
                                .create())
                        .create());
    }

    private void createAvailableAttributesTableViewer(Composite parent) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.availableAttributes);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayout(GridLayoutFactory.fillDefaults().create());
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        availableAttributesTableViewer = new TableViewer(client,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        availableAttributesTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        availableAttributesTableViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, AVAILABLE_FIELDS_VIEWER_ID);
        formPage.getToolkit().adapt(availableAttributesTableViewer.getTable());
        ColumnViewerToolTipSupport.enableFor(availableAttributesTableViewer);
        availableAttributesTableViewer.setUseHashlookup(true);
        availableAttributesTableViewer.setFilters(indexableFieldFilter, indexedFieldsFilter());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        availableAttributesTableViewer.getTable().setLayout(layout);

        createAttributesColumn(availableAttributesTableViewer);

        availableAttributesTableViewer.setContentProvider(new ObservableListContentProvider());
        availableAttributesTableViewer.setInput(actualsFieldsObservable);
        selectedAvailableAttributeObservable = ViewersObservables.observeMultiSelection(availableAttributesTableViewer);

        availableAttributesTableViewer.getTable().addMouseMoveListener(e -> updateCursor(e, availableAttributesTableViewer));
        availableAttributesTableViewer.addDragSupport(DND.DROP_MOVE, new Transfer[] { FieldTransfer.getInstance() },
                dragSourceAdapter(selectedAvailableAttributeObservable));
        availableAttributesTableViewer.addDropSupport(DND.DROP_MOVE,
                new Transfer[] { FieldTransfer.getInstance() },
                new DropTargetAdapter() {

                    @Override
                    public void drop(DropTargetEvent event) {
                        dragLeave(event);
                        indexedFieldsObservable.removeAll((Collection<?>) event.data);
                    }
                });

        section.setClient(client);
    }

    private DragSourceAdapter dragSourceAdapter(IObservableList<Field> observable) {
        return new DragSourceAdapter() {

            @Override
            public void dragStart(DragSourceEvent event) {
                event.detail = DND.DROP_MOVE;
                dragSetData(event);
            }

            @Override
            public void dragSetData(DragSourceEvent event) {
                event.doit = !observable.isEmpty();
                event.data = observable;
            }
        };
    }

    private ViewerFilter indexedFieldsFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return !indexedFieldsObservable.stream()
                        .anyMatch(indexedField -> Objects.equals(((Field) element).getName(), indexedField.getName()));
            }
        };
    }

    private void createIndexedAttributesTableViewer(Composite parent) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.indexedAttributes);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayout(GridLayoutFactory.fillDefaults().create());
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        indexedAttributesTableViewer = new TableViewer(client,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        indexedAttributesTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        indexedAttributesTableViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, INDEXED_FIELDS_VIEWER_ID);
        formPage.getToolkit().adapt(indexedAttributesTableViewer.getTable());
        ColumnViewerToolTipSupport.enableFor(indexedAttributesTableViewer);
        indexedAttributesTableViewer.setUseHashlookup(true);
        indexedAttributesTableViewer.getTable().addMouseMoveListener(e -> updateCursor(e, indexedAttributesTableViewer));

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        indexedAttributesTableViewer.getTable().setLayout(layout);

        createAttributesColumn(indexedAttributesTableViewer);

        indexedAttributesTableViewer.setContentProvider(new ObservableListContentProvider());
        indexedAttributesTableViewer.setInput(indexedFieldsObservable);
        selectedIndexedAttributeObservable = ViewersObservables.observeMultiSelection(indexedAttributesTableViewer);

        indexedAttributesTableViewer.addDropSupport(DND.DROP_MOVE | DND.DROP_MOVE | DND.DROP_DEFAULT,
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
                        List<Field> droppedField = (List) event.data;
                        boolean fromCurrentViewer = droppedField.stream().anyMatch(indexedFieldsObservable::contains);
                        int dropIndex = item != null && item instanceof TableItem
                                ? indexedAttributesTableViewer.getTable().indexOf((TableItem) item)
                                : indexedFieldsObservable.size();
                        if (fromCurrentViewer) {
                            int maxIndex = droppedField.stream()
                                    .mapToInt(indexedFieldsObservable::indexOf)
                                    .max().orElseThrow(() -> new RuntimeException(
                                            "An error occured while computing drag field indexes."));
                            int minIndex = droppedField.stream()
                                    .mapToInt(indexedFieldsObservable::indexOf)
                                    .min().orElseThrow(() -> new RuntimeException(
                                            "An error occured while computing drag field indexes."));
                            if (dropIndex > maxIndex || dropIndex < minIndex) {
                                int newIndex = dropIndex - (item != null && maxIndex > dropIndex ? 0 : 1);
                                for (int i = 0; i < droppedField.size(); i++) {
                                    indexedFieldsObservable.move(maxIndex, newIndex);
                                    if (maxIndex < dropIndex) {
                                        maxIndex -= 1;
                                        newIndex -= 1;
                                    }
                                }
                            }
                        } else {
                            for (int i = droppedField.size() - 1; i >= 0; i--) {
                                indexedFieldsObservable.add(dropIndex, droppedField.get(i));
                            }
                        }
                    }
                });

        indexedAttributesTableViewer.addDragSupport(DND.DROP_MOVE, new Transfer[] { FieldTransfer.getInstance() },
                dragSourceAdapter(selectedIndexedAttributeObservable));

        section.setClient(client);
    }

    private void createAttributesColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<Field>()
                .withStyledStringProvider(fieldStyleStringProvider)
                .createStyledCellLabelProvider());
    }

    private void updateCursor(MouseEvent e, TableViewer viewer) {
        if (viewer.getCell(new Point(e.x, e.y)) != null) {
            viewer.getTable().setCursor(cursorHand);
        } else {
            viewer.getTable().setCursor(cursorArrow);
        }
    }

    private void createLabels() {
        formPage.getToolkit()
                .createLabel(this, Messages.selectIndexFieldsMessages, SWT.WRAP)
                .setLayoutData(GridDataFactory.fillDefaults().create());
        formPage.getToolkit()
                .createLabel(this, Messages.warningTextIndex, SWT.WRAP)
                .setLayoutData(GridDataFactory.fillDefaults().create());

        Label validationLabel = formPage.getToolkit().createLabel(this, "", SWT.WRAP);
        validationLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        validationLabel.addPaintListener(e -> validationLabel.setForeground(errorColor));

        indexedFieldNameObservable.addChangeListener(e -> {
            if (selectedIndexObservable.getValue() != null) {
                if (!selectedIndexObservable.getValue().getFieldNames().isEmpty()) {
                    validationLabel.setVisible(false);
                    ((GridData) validationLabel.getLayoutData()).exclude = true;
                } else {
                    validationLabel.setVisible(true);
                    ((GridData) validationLabel.getLayoutData()).exclude = false;
                    validationLabel.setText(
                            String.format(Messages.indexFieldEmptiness, selectedIndexObservable.getValue().getName()));
                }
                layout();
            }
        });
    }

    public void refreshIndexEditionViewers() {
        availableAttributesTableViewer.refresh();
        indexedAttributesTableViewer.refresh();
    }

    @Override
    public void dispose() {
        fieldStyleStringProvider.dispose();
        cursorHand.dispose();
        cursorArrow.dispose();
        super.dispose();
    }

}
