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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.QueryContentCreator;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.query.QueryFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.provider.QueryContentProvider;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.QueryValidator;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class QueryEditionControl {

    public static final String QUERY_LIST_VIEWER_ID = "queryListViewerId";
    public static final String DEFAULT_QUERY_NAME = "query";
    public static final String ADD_QUERY_BUTTON_ID = "addQueryButtonId";

    private Section section;
    private QueryFormPage formPage;
    private IObservableValue<BusinessObject> boSelectedObservable;
    private DataBindingContext ctx;
    private ToolItem deleteQueryItem;
    private TreeViewer viewer;
    private QueryDetailsControl queryDetailsControl;
    private QueryContentCreator queryContentCreator;
    private IObservableValue<Query> querySelectedObservable;
    private QueryContentProvider provider;
    private List<Query> queriesToFilter = new ArrayList<>();
    private IObservableList<Query> customQueries;
    private IObservableList<Query> defaultQueries;

    public QueryEditionControl(Composite parent,
            QueryFormPage formPage,
            DataBindingContext ctx) {
        this.formPage = formPage;
        this.boSelectedObservable = formPage.observeBusinessObjectSelected();
        this.ctx = ctx;
        this.queryContentCreator = new QueryContentCreator();

        Composite queryEditionComposite = formPage.getToolkit().createComposite(parent);
        queryEditionComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());
        queryEditionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createDefinitionSection(queryEditionComposite);
        queryDetailsControl = new QueryDetailsControl(queryEditionComposite, querySelectedObservable, formPage,
                boSelectedObservable, viewer);
        enableButtons();

        customQueries = EMFObservables.observeDetailList(Realm.getDefault(), boSelectedObservable,
                BusinessDataModelPackage.Literals.BUSINESS_OBJECT__QUERIES);
        defaultQueries = EMFObservables.observeDetailList(Realm.getDefault(), boSelectedObservable,
                BusinessDataModelPackage.Literals.BUSINESS_OBJECT__DEFAULT_QUERIES);
    }

    private void createDefinitionSection(Composite parent) {
        this.section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayout(
                GridLayoutFactory.fillDefaults().create());
        client.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        Composite definitionComposite = formPage.getToolkit().createComposite(client);
        definitionComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        definitionComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1)
                        .margins(10, 10).create());

        createToolbar(definitionComposite);
        createSearchField(definitionComposite);
        createViewer(definitionComposite);

        section.setClient(client);
    }

    private void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                queriesToFilter.clear();
                defaultQueries.stream()
                        .filter(query -> !query.getName().toLowerCase().contains(search))
                        .forEach(queriesToFilter::add);
                customQueries.stream()
                        .filter(query -> !query.getName().toLowerCase().contains(search))
                        .forEach(queriesToFilter::add);
                viewer.refresh();
            });
        });
    }

    protected TextWidget createSearchWidget(Composite parent) {
        return new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withPlaceholder(Messages.searchQuery)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof Query) { // TODO something here if we want to hide `default` and `custom` when all children are hidden.
                    return !queriesToFilter.contains(element);
                }
                return false;
            }
        };
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.LEFT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        ToolItem addQueryItem = new ToolItem(toolBar, SWT.PUSH);
        addQueryItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_QUERY_BUTTON_ID);
        addQueryItem.setImage(BusinessObjectPlugin.getImage("/icons/add.png"));
        addQueryItem.setToolTipText(Messages.addQueryTooltip);
        addQueryItem.addListener(SWT.Selection, e -> addQuery());

        deleteQueryItem = new ToolItem(toolBar, SWT.PUSH);
        deleteQueryItem.setImage(BusinessObjectPlugin.getImage("/icons/delete_icon.png"));
        deleteQueryItem.setToolTipText(Messages.deleteQueryTooltip);
        deleteQueryItem.addListener(SWT.Selection, e -> removeQuery());

        ToolItem expandItem = new ToolItem(toolBar, SWT.PUSH);
        expandItem.setImage(Pics.getImage(PicsConstants.expandAll));
        expandItem.setToolTipText(Messages.expandAll);
        expandItem.addListener(SWT.Selection, e -> viewer.expandAll());

        ToolItem collapseItem = new ToolItem(toolBar, SWT.PUSH);
        collapseItem.setImage(Pics.getImage(PicsConstants.collapseAll));
        collapseItem.setToolTipText(Messages.collapseAll);
        collapseItem.addListener(SWT.Selection, e -> viewer.collapseAll());
    }

    private void createViewer(Composite parent) {
        Composite customModeComposite = formPage.getToolkit().createComposite(parent);
        customModeComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        customModeComposite.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());

        viewer = createQueryViewer(customModeComposite);
        viewer.setInput(boSelectedObservable);

        querySelectedObservable = ViewerProperties.singleSelection(Query.class).observe(viewer);
    }

    private void enableButtons() {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteQueryItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> {
                    return boSelectedObservable.getValue() != null && querySelectedObservable.getValue() != null
                            && boSelectedObservable.getValue().getQueries().contains(querySelectedObservable.getValue());
                })
                .build());
    }

    private void removeQuery() {
        if (MessageDialog.openQuestion(section.getShell(), Messages.deleteQueryConfirmTitle,
                String.format(Messages.deleteQueryConfirmMessage, querySelectedObservable.getValue().getName()))) {
            boSelectedObservable.getValue().getQueries().remove(querySelectedObservable.getValue());
            Display.getDefault().asyncExec(() -> {
                viewer.getTree().deselectAll();
                viewer.refresh();
            });
        }
    }

    private void addQuery() {
        viewer.applyEditorValue();
        String queryName = generateNewQueryName();
        Query newQuery = new QueryBuilder().withName(queryName)
                .withReturnType(boSelectedObservable.getValue().getQualifiedName()).create();
        queryContentCreator.createQueryContent(boSelectedObservable.getValue(), newQuery);
        boSelectedObservable.getValue().getQueries().add(newQuery);
        Display.getDefault().asyncExec(() -> {
            viewer.refresh();
            viewer.setExpandedState(provider.getCustomRoot(), true);
            viewer.editElement(newQuery, 0);
        });
    }

    private String generateNewQueryName() {
        List<String> existingNames = boSelectedObservable.getValue().getQueries().stream()
                .map(Query::getName)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(DEFAULT_QUERY_NAME, existingNames);
    }

    private TreeViewer createQueryViewer(Composite parent) {
        TreeViewer queryViewer = new TreeViewer(parent,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        queryViewer.getTree()
                .setLayoutData(GridDataFactory.fillDefaults().grab(false, true).hint(300, SWT.DEFAULT).create());
        queryViewer.getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, QUERY_LIST_VIEWER_ID);
        formPage.getToolkit().adapt(queryViewer.getTree());
        ColumnViewerToolTipSupport.enableFor(queryViewer);
        queryViewer.setUseHashlookup(true);
        queryViewer.addFilter(createSearchFilter());
        queryViewer.addDoubleClickListener(e -> {
            queryViewer.setExpandedState(querySelectedObservable.getValue(),
                    !viewer.getExpandedState(querySelectedObservable.getValue()));
        });

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        queryViewer.getTree().setLayout(layout);

        createQueryNameColumn(queryViewer);

        provider = new QueryContentProvider();
        queryViewer.setContentProvider(provider);
        return queryViewer;
    }

    private void createQueryNameColumn(TreeViewer viewer) {
        TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.name);

        QueryValidator validator = new QueryValidator(boSelectedObservable);

        column.setLabelProvider(new LabelProviderBuilder<Query>()
                .withTextProvider(Query::getName)
                .withStatusProvider(query -> boSelectedObservable.getValue() != null
                        && boSelectedObservable.getValue().getQueries().contains(query)
                                ? validator.validate(query)
                                : ValidationStatus.ok())
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<Query>(viewer)
                .withId(SWTBotConstants.SWTBOT_ID_QUERY_NAME_TEXTEDITOR)
                .withCanEditProvider(query -> boSelectedObservable.getValue() != null
                        && boSelectedObservable.getValue().getQueries().contains(query))
                .withValueProvider(Query::getName)
                .withValueUpdater((query, newName) -> {
                    if (!Objects.equals(query.getName(), newName)) {
                        query.setName((String) newName);
                    }
                })
                .create());
        column.getViewer().getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
            }

            @Override
            public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
            }

            @Override
            public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                if (boSelectedObservable.getValue() != null) {
                    column.getViewer().update(boSelectedObservable.getValue().getQueries().toArray(), null);
                }
            }

            @Override
            public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
            }
        });
    }

    public void refreshQueryViewers() {
        viewer.refresh();
        queryDetailsControl.refreshParameterViewer();
        queryDetailsControl.updateReturnTypeViewerInput();
    }

    public IObservableValue<String> observeSectionTitle() {
        return PojoProperties.<Section, String> value("text").observe(section);
    }

    public IObservableValue<Boolean> observeSectionVisible() {
        return WidgetProperties.visible().observe(section);
    }
}
