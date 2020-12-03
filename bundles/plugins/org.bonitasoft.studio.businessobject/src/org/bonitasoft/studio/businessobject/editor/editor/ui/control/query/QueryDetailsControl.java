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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.ui.editingSupport.QueryParameterTypeEditingSupport;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.query.QueryFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.provider.QueryResultTypeLabelProvider;
import org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.QueryParameter;
import org.bonitasoft.studio.businessobject.editor.model.builder.QueryParameterBuilder;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.validator.QueryParameterNameValidator;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class QueryDetailsControl extends Composite {

    public static final String QUERY_CONTENT_WIDGET_ID = "queryContentWidgetId";
    public static final String QUERY_PARAMETERS_VIEWER_ID = "queryParametersTableId";
    public static final String ADD_PARAM_BUTTON_ID = "addParamId";
    public static final String REMOVE_PARAM_BUTTON_ID = "removeParamId";

    private static final String JPQL_HELP_URL = "http://en.wikibooks.org/wiki/Java_Persistence/JPQL";

    protected IObservableValue<Query> querySelectedObservable;
    protected QueryFormPage formPage;
    private DataBindingContext ctx;
    protected IObservableValue<BusinessObject> boSelectedObservable;
    protected TableViewer parametersTableViewer;
    private ComboViewer returnTypeComboViewer;
    protected IObservableList<QueryParameter> selectedQueryParameterObservableList;
    protected IObservableList<QueryParameter> parametersMultipleSelectionObservable;
    private List<QueryParameter> parametersToFilter = new ArrayList<>();
    private ToolItem deleteParameterItem;
    private ToolItem addParameterItem;
    private Section descriptionSection;
    private TreeViewer queryViewer;

    public QueryDetailsControl(Composite parent, IObservableValue<Query> querySelectedObservable,
            QueryFormPage formPage, IObservableValue<BusinessObject> boSelectedObservable, TreeViewer queryViewer) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults().create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        formPage.getToolkit().adapt(this);

        this.formPage = formPage;
        this.querySelectedObservable = querySelectedObservable;
        this.ctx = new DataBindingContext();
        this.boSelectedObservable = boSelectedObservable;
        this.queryViewer = queryViewer;

        createDescriptionSection();
        createContentSection();

        ctx.bindValue(WidgetProperties.visible().observe(this), new ComputedValueBuilder<Boolean>()
                .withSupplier(
                        () -> querySelectedObservable.getValue() != null
                                && isRealQuery(querySelectedObservable.getValue()))
                .build());

        querySelectedObservable.addValueChangeListener(e -> {
            descriptionSection.setExpanded(!isDefault());
            descriptionSection.setVisible(!isDefault());
        });

        ctx.bindValue(WidgetProperties.enabled().observe(deleteParameterItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> !isDefault() && !parametersMultipleSelectionObservable.isEmpty())
                .build());
        ctx.bindValue(WidgetProperties.enabled().observe(addParameterItem), new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> !isDefault())
                .build());
    }

    private void createContentSection() {
        Section section = formPage.getToolkit().createSection(this, Section.EXPANDED);
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setText(Messages.queryContent);

        Composite client = formPage.getToolkit().createComposite(section);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        client.setLayout(
                GridLayoutFactory.fillDefaults().margins(10, 10).create());

        createLink(client);
        createQueryTextArea(client);
        createParametersComposite(client);
        createReturnTypeComposite(client);

        section.setClient(client);
    }

    private void createDescriptionSection() {
        descriptionSection = formPage.getToolkit().createSection(this, Section.EXPANDED);
        descriptionSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        descriptionSection.setLayout(GridLayoutFactory.fillDefaults().create());
        descriptionSection.setText(Messages.description);

        Composite client = formPage.getToolkit().createComposite(descriptionSection);
        client.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        client.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());

        IObservableValue descriptionObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                querySelectedObservable, BusinessDataModelPackage.Literals.QUERY__DESCRIPTION);

        TextWidget widget = new TextAreaWidget.Builder()
                .widthHint(500)
                .heightHint(70)
                .bindTo(descriptionObservable)
                .inContext(ctx)
                .adapt(formPage.getToolkit())
                .createIn(client);

        ctx.bindValue(widget.observeEnable(), new ComputedValueBuilder()
                .withSupplier(() -> !isDefault())
                .build());

        descriptionSection.setClient(client);
    }

    // return false if the input query isn't one of the default or the custom queries of the selected bo (i.e one of the root of the tree viewer)
    private boolean isRealQuery(Query query) {
        if (boSelectedObservable.getValue() != null) {
            return boSelectedObservable.getValue().getDefaultQueries().contains(query)
                    || boSelectedObservable.getValue().getQueries().contains(query);
        }
        return false;
    }

    private void createReturnTypeComposite(Composite parent) {
        Composite returnTypeComposite = formPage.getToolkit().createComposite(parent);
        returnTypeComposite.setLayout(GridLayoutFactory.fillDefaults().create());
        returnTypeComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());

        Label queryResultTypeLabel = formPage.getToolkit().createLabel(returnTypeComposite, Messages.queryResultType);
        queryResultTypeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());

        returnTypeComboViewer = new ComboViewer(returnTypeComposite, SWT.BORDER | SWT.READ_ONLY);
        returnTypeComboViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(false, false).hint(300, SWT.DEFAULT).create());
        returnTypeComboViewer.setContentProvider(ArrayContentProvider.getInstance());
        returnTypeComboViewer.setLabelProvider(new QueryResultTypeLabelProvider(boSelectedObservable));
        updateReturnTypeViewerInput();

        Label queryResultTypeInfoLabel = formPage.getToolkit().createLabel(returnTypeComposite,
                Messages.queryReturnTypeWarning);
        queryResultTypeInfoLabel
                .setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).indent(20, 0).create());

        ControlDecoration typeinfo = new ControlDecoration(queryResultTypeInfoLabel, SWT.LEFT);
        typeinfo.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO));
        typeinfo.setMarginWidth(5);

        querySelectedObservable.addValueChangeListener(e -> {
            if (isDefault()) {
                returnTypeComboViewer.getControl().setEnabled(false);
            } else {
                returnTypeComboViewer.getControl().setEnabled(true);
            }
        });

        boSelectedObservable.addValueChangeListener(e -> updateReturnTypeViewerInput());

        IObservableValue<String> returnTypeSelectionObservable = ViewerProperties.singleSelection(String.class)
                .observe(returnTypeComboViewer);
        IObservableValue<String> returnTypeObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                querySelectedObservable, BusinessDataModelPackage.Literals.QUERY__RETURN_TYPE);
        ctx.bindValue(returnTypeSelectionObservable, returnTypeObservable);
        returnTypeObservable.addValueChangeListener(e -> queryViewer.refresh()); // might impact the info tooltip for the count queries
    }

    public void updateReturnTypeViewerInput() {
        returnTypeComboViewer.setInput(getSupportedReturnTypes());
        ctx.updateTargets();
    }

    private List<String> getSupportedReturnTypes() {
        if (boSelectedObservable.getValue() != null) {
            return Arrays.asList(List.class.getName(),
                    Long.class.getName(),
                    Double.class.getName(),
                    Float.class.getName(),
                    Integer.class.getName(),
                    boSelectedObservable.getValue().getQualifiedName());
        }
        return Collections.EMPTY_LIST;
    }

    protected void createParametersComposite(Composite parent) {
        Composite parametersComposite = formPage.getToolkit().createComposite(parent);
        parametersComposite.setLayout(GridLayoutFactory.fillDefaults().margins(0, 10).create());
        parametersComposite
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(500, SWT.DEFAULT).create());

        Label parametersLabel = formPage.getToolkit().createLabel(parametersComposite, Messages.parametersLabel);
        parametersLabel.setLayoutData(GridDataFactory.fillDefaults().create());

        Composite viewerComposite = formPage.getToolkit().createComposite(parametersComposite);
        viewerComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1).create());
        viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(viewerComposite);
        createSearchField(viewerComposite);
        createParametersTable(viewerComposite);
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.LEFT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        addParameterItem = new ToolItem(toolBar, SWT.PUSH);
        addParameterItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_PARAM_BUTTON_ID);
        addParameterItem.setImage(BusinessObjectPlugin.getImage("/icons/add.png"));
        addParameterItem.setToolTipText(Messages.addParameterTooltip);
        addParameterItem.addListener(SWT.Selection, e -> addParameter());

        deleteParameterItem = new ToolItem(toolBar, SWT.PUSH);
        deleteParameterItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_PARAM_BUTTON_ID);
        deleteParameterItem.setImage(BusinessObjectPlugin.getImage("/icons/delete_icon.png"));
        deleteParameterItem.setToolTipText(Messages.deleteParameterTooltip);
        deleteParameterItem.addListener(SWT.Selection, e -> removeParameter());
    }

    private void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                parametersToFilter.clear();
                selectedQueryParameterObservableList.stream()
                        .filter(param -> !param.getName().toLowerCase().contains(search))
                        .forEach(parametersToFilter::add);
                parametersTableViewer.refresh();

                // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
                // TODO Hopefully this could be removed on the futur (current date: 23/11/2020)
                if (Objects.equals(Platform.getOS(), Platform.OS_MACOSX)) {
                    parametersTableViewer.getControl().redraw();
                }
            });
        });
    }

    protected TextWidget createSearchWidget(Composite parent) {
        return new SearchWidget.Builder()
                .labelAbove()
                .widthHint(300)
                .withPlaceholder(Messages.search)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof QueryParameter) {
                    return !parametersToFilter.contains(element);
                }
                return false;
            }
        };
    }

    private void removeParameter() {
        if (MessageDialog.openQuestion(getShell(), Messages.deleteQueryParamsConfirmTitle,
                Messages.deleteQueryParamsConfirmMessage)) {
            selectedQueryParameterObservableList.removeAll(parametersMultipleSelectionObservable);
        }
    }

    private void addParameter() {
        QueryParameter newParameter = new QueryParameterBuilder().withName(generateParameterName())
                .withClassName(String.class.getName()).create();
        selectedQueryParameterObservableList.add(newParameter);
        Display.getDefault().asyncExec(() -> parametersTableViewer.editElement(newParameter, 0));
    }

    private String generateParameterName() {
        List<String> existingNames = boSelectedObservable.getValue().getQueries().stream()
                .map(Query::getQueryParameters)
                .flatMap(Collection::stream)
                .map(QueryParameter::getName)
                .collect(Collectors.toList());
        return StringIncrementer.getNextIncrement(Messages.parameter, existingNames);
    }

    protected void createParametersTable(Composite parent) {
        parametersTableViewer = new TableViewer(parent,
                SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        parametersTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        parametersTableViewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, QUERY_PARAMETERS_VIEWER_ID);
        ColumnViewerToolTipSupport.enableFor(parametersTableViewer);
        parametersTableViewer.getTable().setHeaderVisible(true);
        parametersTableViewer.setContentProvider(new ObservableListContentProvider());
        parametersTableViewer.addFilter(createSearchFilter());

        TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(1));
        tableLayout.addColumnData(new ColumnWeightData(2));
        parametersTableViewer.getTable().setLayout(tableLayout);

        createNameColumn(parametersTableViewer);
        createTypeColumn(parametersTableViewer);
        createDescriptionColumn(parametersTableViewer);

        selectedQueryParameterObservableList = EMFObservables.observeDetailList(Realm.getDefault(),
                querySelectedObservable,
                BusinessDataModelPackage.Literals.QUERY__QUERY_PARAMETERS);
        parametersMultipleSelectionObservable = ViewerProperties.multipleSelection(QueryParameter.class)
                .observe(parametersTableViewer);
        parametersTableViewer.setInput(selectedQueryParameterObservableList);

        querySelectedObservable.addValueChangeListener(e -> {
            // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
            // TODO Hopefully this could be removed on the futur (current date: 23/11/2020)
            if (Objects.equals(Platform.getOS(), Platform.OS_MACOSX)) {
                parametersTableViewer.getControl().redraw();
            }
        });
    }

    private void createDescriptionColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.description);
        column.setLabelProvider(new LabelProviderBuilder<QueryParameter>()
                .withTextProvider(parameter -> parameter.getDescription())
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<QueryParameter>(viewer)
                .withCanEditProvider(e -> !isDefault())
                .withValueProvider(parameter -> parameter.getDescription() != null ? parameter.getDescription() : "")
                .withValueUpdater((parameter, newDesc) -> {
                    String oldDesc = parameter.getDescription();
                    if (!Objects.equals(oldDesc, newDesc)) {
                        parameter.setDescription((String) newDesc);
                        formPage.refreshQueryViewers();
                    }
                })
                .create());
    }

    protected TableViewerColumn createTypeColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.type);
        column.setLabelProvider(new LabelProviderBuilder<QueryParameter>()
                .withTextProvider(parameter -> {
                    String classname = parameter.getClassName();
                    Class<?> clazz;
                    try {
                        clazz = Class.forName(classname);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                    return clazz.getCanonicalName();
                })
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
        column.setEditingSupport(new QueryParameterTypeEditingSupport(viewer, () -> isDefault()));
        return column;
    }

    protected TableViewerColumn createNameColumn(TableViewer viewer) {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.name);

        QueryParameterNameValidator parameterNameValidator = new QueryParameterNameValidator(querySelectedObservable);

        column.setLabelProvider(new LabelProviderBuilder<QueryParameter>()
                .withTextProvider(parameter -> parameter.getName())
                .withStatusProvider(parameterNameValidator::validate)
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
        column.setEditingSupport(new EditingSupportBuilder<QueryParameter>(viewer)
                .withCanEditProvider(e -> !isDefault())
                .withValueProvider(QueryParameter::getName)
                .withValueUpdater((parameter, newName) -> {
                    String oldName = parameter.getName();
                    if (!Objects.equals(oldName, newName)) {
                        parameter.setName((String) newName);
                        formPage.refreshQueryViewers();
                    }
                })
                .withId(SWTBotConstants.SWTBOT_ID_QUERY_PARAM_NAME_TEXTEDITOR)
                .create());

        return column;
    }

    private void createQueryTextArea(Composite parent) {
        IObservableValue<String> contentObservable = EMFObservables.observeDetailValue(Realm.getDefault(),
                querySelectedObservable, BusinessDataModelPackage.Literals.QUERY__CONTENT);
        TextWidget textWidget = new TextAreaWidget.Builder()
                .grabHorizontalSpace()
                .fill()
                .heightHint(175)
                .adapt(formPage.getToolkit())
                .bindTo(contentObservable)
                .inContext(ctx)
                .withId(QUERY_CONTENT_WIDGET_ID)
                .createIn(parent);
        querySelectedObservable.addValueChangeListener(e -> textWidget.setEnabled(!isDefault()));
    }

    private void createLink(Composite parent) {
        Link link = new Link(parent, SWT.None);
        link.setLayoutData(GridDataFactory.fillDefaults().create());
        formPage.getToolkit().adapt(link, true, true);
        link.setText(Messages.queryLink);
        link.addListener(SWT.Selection, event -> {
            try {
                new OpenBrowserOperation(new URL(JPQL_HELP_URL)).execute();
            } catch (MalformedURLException e) {
                BonitaStudioLog.error(e);
            }
        });
    }

    protected boolean isDefault() {
        if (boSelectedObservable.getValue() != null && querySelectedObservable.getValue() instanceof Query) {
            return boSelectedObservable.getValue().getDefaultQueries().contains(querySelectedObservable.getValue());
        }
        return true;
    }

    public void refreshParameterViewer() {
        parametersTableViewer.refresh();
    }

}
