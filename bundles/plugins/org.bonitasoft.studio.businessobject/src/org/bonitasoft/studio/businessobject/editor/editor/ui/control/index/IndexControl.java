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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.filter.IndexableFieldFilter;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Field;
import org.bonitasoft.studio.businessobject.editor.model.Index;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.builder.IndexBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.IndexFieldsValidator;
import org.bonitasoft.studio.businessobject.validator.IndexNameValidator;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
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
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class IndexControl {

    private static final String DEFAULT_INDEX_NAME = "INDEX_";

    public static final String ADD_INDEX_BUTTON_ID = "addIndexButtonId";
    public static final String INDEX_LIST_VIEWER_ID = "indexListViewerId";

    private AbstractBdmFormPage formPage;
    private IObservableValue<Index> selectedIndexObservable;
    private IObservableList<Index> indexObservable;
    private IObservableList<Field> actualsFieldsObservable;
    private IObservableValue<BusinessObject> selectedBoObservable;
    private ToolItem deleteIndexItem;
    private TableViewer indexViewer;
    private IndexableFieldFilter indexableFieldFilter;
    private IndexEditionControl indexEditionControl;
    private Section section;
    private Composite mainComposite;
    private List<Index> indexesToFilter = new ArrayList<>();

    public IndexControl(Composite parent,
            AbstractBdmFormPage formPage,
            DataBindingContext ctx) {

        this.indexableFieldFilter = new IndexableFieldFilter();
        this.formPage = formPage;
        this.selectedBoObservable = formPage.observeBusinessObjectSelected();

        this.indexObservable = EMFObservables.observeDetailList(Realm.getDefault(), selectedBoObservable,
                BusinessDataModelPackage.Literals.BUSINESS_OBJECT__INDEXES);
        this.actualsFieldsObservable = EMFObservables.observeDetailList(Realm.getDefault(), selectedBoObservable,
                BusinessDataModelPackage.Literals.BUSINESS_OBJECT__FIELDS);

        this.mainComposite = formPage.getToolkit().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createIndexDefinitionSection();
        createIndexEditionComposite(ctx);
        enableButtons(ctx);

        EMFObservables.observeDetailList(Realm.getDefault(), selectedIndexObservable,
                BusinessDataModelPackage.Literals.INDEX__FIELD_NAMES).addChangeListener(e -> indexViewer.refresh());
    }

    private void createIndexDefinitionSection() {
        this.section = formPage.getToolkit().createSection(mainComposite, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayout(GridLayoutFactory.fillDefaults().create());
        client.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        createIndexDefinitionComposite(client);

        section.setClient(client);
    }

    private void createIndexDefinitionComposite(Composite parent) {
        Composite composite = formPage.getToolkit().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1)
                .extendedMargins(15, 5, 10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        createToolbar(composite);
        createSearchField(composite);
        createIndexViewer(composite);
    }

    private void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                indexesToFilter.clear();
                indexObservable.stream()
                        .filter(index -> !index.getName().toLowerCase().contains(search))
                        .forEach(indexesToFilter::add);
                indexViewer.refresh();
            });
        });
    }

    protected TextWidget createSearchWidget(Composite parent) {
        return new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withPlaceholder(Messages.searchIndex)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof Index) {
                    return !indexesToFilter.contains(element);
                }
                return false;
            }
        };
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.LEFT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        ToolItem addIndexItem = new ToolItem(toolBar, SWT.PUSH);
        addIndexItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_INDEX_BUTTON_ID);
        addIndexItem.setImage(BusinessObjectPlugin.getImage("/icons/add.png"));
        addIndexItem.setToolTipText(Messages.addIndexTooltip);
        addIndexItem.addListener(SWT.Selection, e -> addIndex());

        deleteIndexItem = new ToolItem(toolBar, SWT.PUSH);
        deleteIndexItem.setImage(BusinessObjectPlugin.getImage("/icons/delete_icon.png"));
        deleteIndexItem.setToolTipText(Messages.deleteIndexTooltip);
        deleteIndexItem.addListener(SWT.Selection, e -> removeIndex());
    }

    private void createIndexEditionComposite(DataBindingContext ctx) {
        Composite composite = formPage.getToolkit().createComposite(mainComposite);
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        composite.setLayout(GridLayoutFactory.fillDefaults().create());

        createIndexDescriptionSection(composite, ctx);
        createIndexEditionSection(composite, ctx);

        ctx.bindValue(WidgetProperties.visible().observe(composite), new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return selectedIndexObservable.getValue() != null;
            }
        });
    }

    private void createIndexDescriptionSection(Composite parent, DataBindingContext ctx) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.description);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());

        IObservableValue descriptionObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                selectedIndexObservable, BusinessDataModelPackage.Literals.INDEX__DESCRIPTION);

        new TextAreaWidget.Builder()
                .widthHint(500)
                .heightHint(70)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(client);

        section.setClient(client);
    }

    private void createIndexEditionSection(Composite parent, DataBindingContext ctx) {
        Section section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.attributes);

        indexEditionControl = new IndexEditionControl(section, formPage, ctx, selectedIndexObservable,
                actualsFieldsObservable);

        section.setClient(indexEditionControl);
    }

    private void createIndexViewer(Composite parent) {
        indexViewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        indexViewer.getTable()
                .setLayoutData(GridDataFactory.fillDefaults().grab(false, true).span(2, 1).hint(350, SWT.DEFAULT).create());
        indexViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, INDEX_LIST_VIEWER_ID);
        formPage.getToolkit().adapt(indexViewer.getTable());
        ColumnViewerToolTipSupport.enableFor(indexViewer);
        indexViewer.setUseHashlookup(true);
        indexViewer.getTable().setHeaderVisible(true);
        indexViewer.getTable().setLinesVisible(true);
        indexViewer.addFilter(createSearchFilter());

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        layout.addColumnData(new ColumnWeightData(1, true));
        indexViewer.getTable().setLayout(layout);

        createIndexNameColumn(indexViewer);
        createAttributesColumn(indexViewer);

        indexViewer.setContentProvider(new ObservableListContentProvider());
        indexViewer.setInput(indexObservable);
        selectedIndexObservable = ViewerProperties.singleSelection(Index.class).observe(indexViewer);
    }

    private void enableButtons(DataBindingContext ctx) {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteIndexItem),
                new ComputedValueBuilder<Boolean>()
                        .withSupplier(() -> selectedIndexObservable.getValue() != null)
                        .build());
    }

    private void createAttributesColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.LEFT);
        column.getColumn().setText(Messages.attributes);
        IndexFieldsValidator indexFieldsValidator = new IndexFieldsValidator();
        column.setLabelProvider(new LabelProviderBuilder<Index>()
                .withTextProvider(index -> index.getFieldNames().toString())
                .withStatusProvider(index -> indexFieldsValidator.validate(index))
                .createColumnLabelProvider());
    }

    private void createIndexNameColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.name);
        IndexNameValidator indexNameValidator = new IndexNameValidator(formPage.observeWorkingCopy());

        column.setLabelProvider(new LabelProviderBuilder<Index>()
                .withTextProvider(element -> element.getName())
                .withStatusProvider(indexNameValidator::validate)
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<Index>(viewer)
                .withId(SWTBotConstants.SWTBOT_ID_UNIQUE_CONSTRAINT_NAME_TEXTEDITOR)
                .withValueProvider(Index::getName)
                .withValueUpdater((index, newName) -> {
                    if (!Objects.equals(index.getName(), newName)) {
                        index.setName((String) newName);
                    }
                })
                .create());
    }

    private void removeIndex() {
        if (MessageDialog.openQuestion(mainComposite.getShell(), Messages.deleteIndexConfirmTitle,
                String.format(Messages.deleteIndexConfirmMessage, selectedIndexObservable.getValue().getName()))) {
            indexObservable.remove(selectedIndexObservable.getValue());
        }
    }

    private void addIndex() {
        indexViewer.applyEditorValue();
        String newName = getNewName();
        Index newIndex = new IndexBuilder()
                .withName(newName)
                .create();
        indexObservable.add(newIndex);
        Display.getDefault().asyncExec(() -> indexViewer.editElement(newIndex, 0));
    }

    private String getNewName() {
        List<String> existingNames = new ArrayList<>();
        existingNames.add(DEFAULT_INDEX_NAME);
        formPage.observeWorkingCopy().getValue().getPackages()
                .stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .map(BusinessObject::getIndexes)
                .flatMap(Collection::stream)
                .map(Index::getName)
                .forEach(existingNames::add);
        return StringIncrementer.getNextIncrement(DEFAULT_INDEX_NAME, existingNames);
    }

    public void refreshIndexList() {
        indexViewer.refresh();
        indexEditionControl.refreshIndexEditionViewers();
    }

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.<Section, String> value("text").observe(section);
    }

    public IObservableValue<Boolean> observeSectionVisible() {
        return WidgetProperties.visible().observe(mainComposite);
    }
}
