/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.engine.bdm.model.QueryParameter;
import org.bonitasoft.engine.bdm.model.field.Field;
import org.bonitasoft.engine.bdm.model.field.SimpleField;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.QueryParameterNameEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.QueryParameterTypeEditingSupport;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.BonitaStudioFontRegistry;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Romain Bioteau
 *
 */
public class QueryWizardPage extends WizardPage {

    private static final String AND = "\nAND ";

    private static final String JPQL_HELP_URL = "http://en.wikibooks.org/wiki/Java_Persistence/JPQL";

    private Query query;

    private BusinessObject businessObject;

    private Binding queryBinding;

    public QueryWizardPage() {
        super(QueryWizardPage.class.getName());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final DataBindingContext ctx = new DataBindingContext();

        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        final Link queryLabel = new Link(composite, SWT.NO_FOCUS);
        queryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).create());
        queryLabel.setText(Messages.queryLink);
        queryLabel.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                performHelp();
            }
        });

        final StyledText queryText = createQueryText(composite);
        queryText.setFont(getMonospaceFont());
        queryText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, 120).create());

        if (query.getContent() == null || query.getContent().isEmpty()) {
            final String queryExample = createQueryExample(businessObject);
            query.setContent(queryExample);
            if (query.getQueryParameters().isEmpty()) {
                for (final Field f : businessObject.getFields()) {
                    if (f instanceof SimpleField) {
                        query.addQueryParameter(f.getName(), ((SimpleField) f).getType().getClazz().getName());
                    }
                }
            }
        }

        final UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
        targetStrategy.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                if (value == null || value.toString().trim().isEmpty()) {
                    return ValidationStatus.error(Messages.emptyQueryError);
                }
                return checkParametersUsage(value.toString());
            }
        });
        final UpdateValueStrategy strategy = new UpdateValueStrategy();
        strategy.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                return checkParametersUsage(value.toString());
            }
        });

        queryBinding = ctx
                .bindValue(SWTObservables.observeText(queryText, SWT.Modify), PojoObservables.observeValue(getQuery(), "content"), targetStrategy, strategy);

        final Label queryParamLabel = new Label(composite, SWT.NONE);
        queryParamLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.FILL).indent(0, 10).create());
        queryParamLabel.setText(Messages.parameters);

        final ControlDecoration controlDecoration = new ControlDecoration(queryParamLabel, SWT.RIGHT);
        controlDecoration.setImage(Pics.getImage(PicsConstants.hint));
        controlDecoration.setDescriptionText(Messages.jpqlParametersHint);
        controlDecoration.setShowOnlyOnFocus(false);

        createQueryParametersTable(composite, ctx);

        final Label queryResultTypeLabel = new Label(composite, SWT.NONE);
        queryResultTypeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.CENTER).indent(0, 5).create());
        queryResultTypeLabel.setText(Messages.queryResultType);

        ControlDecoration typeWarning = new ControlDecoration(queryResultTypeLabel,SWT.RIGHT);
        typeWarning.setImage(JFaceResources.getImage( Dialog.DLG_IMG_MESSAGE_WARNING));
        typeWarning.setDescriptionText(Messages.queryReturnTypeWarning);
        typeWarning.setMarginWidth(5);
        
        final ComboViewer resultTypeViewer = createReturnTypeComboViewer(composite);
        resultTypeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        resultTypeViewer.setContentProvider(ArrayContentProvider.getInstance());
        resultTypeViewer.setLabelProvider(new LabelProvider() {
        
            @Override
            public String getText(final Object element) {
                final String className = element.toString();
                if (List.class.getName().equals(className)) {
                    return Messages.multipleReturnType;
                } else if (Long.class.getName().equals(className)) {
                    return className + " (COUNT,SUM...etc)";
                } else if (Double.class.getName().equals(className)) {
                    return className + " (AVG...etc)";
                } else if (businessObject.getQualifiedName().equals(className)) {
                    return Messages.bind(Messages.single, businessObject.getQualifiedName());
                }
                return super.getText(element);
            }
        });
        resultTypeViewer.setInput(getSupportedReturnTypes(businessObject));
        final IViewerObservableValue returnTypeSelectionObservable = ViewersObservables.observeSingleSelection(resultTypeViewer);
        ctx.bindValue(returnTypeSelectionObservable, PojoObservables.observeValue(getQuery(), "returnType"));
        returnTypeSelectionObservable.addValueChangeListener(new IValueChangeListener() {

            @Override
            public void handleValueChange(final ValueChangeEvent event) {
                queryBinding.validateTargetToModel();
            }
        });
        
        WizardPageSupport.create(this, ctx);
        setControl(composite);
    }

    private Font getMonospaceFont() {
        return BonitaStudioFontRegistry.getMonospaceFont();
    }

    protected ComboViewer createReturnTypeComboViewer(final Composite composite) {
        return new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
    }

    protected StyledText createQueryText(final Composite composite) {
        return new StyledText(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
    }

    private String createQueryExample(final BusinessObject businessObject) {
        final String boName = NamingUtils.getSimpleName(businessObject.getQualifiedName());
        final char var = Character.toLowerCase(boName.charAt(0));
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(var);
        sb.append(" \n");
        sb.append("FROM ");
        sb.append(boName);
        sb.append(" ");
        sb.append(var);
        sb.append(" \n");
        sb.append("WHERE ");
        for (final Field f : businessObject.getFields()) {
            if (f instanceof SimpleField) {
                if (f.isCollection() != null && f.isCollection()) {
                    sb.append(":");
                    sb.append(f.getName());
                    sb.append(" IN ELEMENTS(");
                    sb.append(var);
                    sb.append(".");
                    sb.append(f.getName());
                    sb.append(")");
                } else {
                    sb.append(var);
                    sb.append(".");
                    sb.append(f.getName());
                    sb.append(" = :");
                    sb.append(f.getName());

                }
                sb.append(AND);
            }
        }
        String query = sb.toString();
        if (query.endsWith(AND)) {
            query = query.substring(0, query.lastIndexOf(AND));
        }
        sb = new StringBuilder(query);
        sb.append("\nORDER BY ");
        sb.append(var);
        sb.append(".");
        sb.append(Field.PERSISTENCE_ID);
        sb.append(" ASC");
        return sb.toString();
    }

    private List<String> getSupportedReturnTypes(final BusinessObject currentBo) {
        return Arrays.asList(List.class.getName(), currentBo.getQualifiedName(), Long.class.getName(), Double.class.getName(), Float.class.getName(),
                Integer.class.getName());
    }

    protected IStatus checkParametersUsage(final String queryContent) {
        final List<QueryParameter> parameters = getQuery().getQueryParameters();
        final Set<String> parametersList = new HashSet<String>();
        for (final QueryParameter p : parameters) {
            parametersList.add(":" + p.getName());
            if (!queryContent.contains(":" + p.getName())) {
                return ValidationStatus.warning(Messages.bind(Messages.parameterNotUsedInQueryWarning, p.getName()));
            }
        }

        final Pattern pattern = Pattern.compile(":[\\w]+");
        final Matcher matcher = pattern.matcher(queryContent);
        while (matcher.find()) {
            final String found = matcher.group();
            if (!parametersList.contains(found)) {
                return ValidationStatus.warning(Messages.bind(Messages.undefinedParameter, found));
            }
        }

        if (List.class.getName().equals(getQuery().getReturnType()) && !queryContent.toUpperCase().contains("ORDER BY")) {
            return ValidationStatus.warning(Messages.bind(Messages.orderByMissingWarning, NamingUtils.getSimpleName(businessObject.getQualifiedName())));
        }

        return ValidationStatus.ok();
    }

    protected void createQueryParametersTable(final Composite parent, final DataBindingContext ctx) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(GridDataFactory.fillDefaults().create());
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(5, 0).create());

        final Composite buttonsComposite = new Composite(composite, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).indent(0, 20).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 3).create());

        final Button addButton = createAddButton(buttonsComposite);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addButton.setText(Messages.add);

        final Button deleteButton = createAddButton(buttonsComposite);
        deleteButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        deleteButton.setText(Messages.delete);
        deleteButton.setEnabled(false);

        final TableViewer parametersTableViewer = new TableViewer(composite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
        parametersTableViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(300, 100).create());
        parametersTableViewer.getTable().setLinesVisible(true);
        parametersTableViewer.getTable().setHeaderVisible(true);
        parametersTableViewer.setContentProvider(new ObservableListContentProvider());

        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(3));
        tableLayout.addColumnData(new ColumnWeightData(2));
        parametersTableViewer.getTable().setLayout(tableLayout);

        final IObservableList queryParameterObserveDetailList = PojoObservables.observeList(getQuery(), "queryParameters");

        addButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final QueryParameter param = new QueryParameter(generateName(), String.class.getName());
                queryParameterObserveDetailList.add(param);
                parametersTableViewer.editElement(param, 0);
                queryBinding.validateTargetToModel();
            }
        });

        final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }
        });
        bindDeleteParameterButtonEnablement(ctx, deleteButton, parametersTableViewer, enableStrategy);
        deleteButton.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
             */
            @Override
            public void widgetSelected(final SelectionEvent e) {
                final List<?> element = ((IStructuredSelection) parametersTableViewer.getSelection()).toList();
                queryParameterObserveDetailList.removeAll(element);
                queryBinding.validateTargetToModel();
            }
        });

        createNameColumn(ctx, parametersTableViewer);
        createTypeColumn(ctx, parametersTableViewer);
        parametersTableViewer.setInput(queryParameterObserveDetailList);
    }

    protected Button createAddButton(final Composite buttonsComposite) {
        return new Button(buttonsComposite, SWT.FLAT);
    }

    protected void bindDeleteParameterButtonEnablement(final DataBindingContext ctx, final Button deleteButton, final TableViewer parametersTableViewer,
            final UpdateValueStrategy enableStrategy) {
        ctx.bindValue(SWTObservables.observeEnabled(deleteButton), ViewersObservables.observeSingleSelection(parametersTableViewer), null, enableStrategy);
    }

    protected TableViewerColumn createNameColumn(final DataBindingContext ctx, final TableViewer tableViewer) {
        final TableViewerColumn nameColumnViewer = new TableViewerColumn(tableViewer, SWT.LEFT);
        final TableColumn column = nameColumnViewer.getColumn();
        column.setText(Messages.name + " *");
        nameColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof QueryParameter) {
                    return ((QueryParameter) element).getName();
                }
                return super.getText(element);
            }
        });
        nameColumnViewer.setEditingSupport(new QueryParameterNameEditingSupport(getQuery(), queryBinding, nameColumnViewer.getViewer(), ctx));
        return nameColumnViewer;
    }

    protected TableViewerColumn createTypeColumn(final DataBindingContext ctx, final TableViewer tableViewer) {
        final TableViewerColumn typeColumnViewer = new TableViewerColumn(tableViewer, SWT.FILL);
        final TableColumn column = typeColumnViewer.getColumn();
        column.setText(Messages.type);
        typeColumnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof QueryParameter) {
                    String classname = ((QueryParameter) element).getClassName();
                    Class<?> clazz;
                    try {
                        clazz = Class.forName(classname);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                    return clazz.getCanonicalName();
                }
                return super.getText(element);
            }
        });
        typeColumnViewer.setEditingSupport(new QueryParameterTypeEditingSupport(typeColumnViewer.getViewer(), ctx));
        return typeColumnViewer;
    }

    protected String generateName() {
        final Set<String> existingNames = new HashSet<>();
        final Query query = getQuery();
        for (final QueryParameter param : query.getQueryParameters()) {
            existingNames.add(param.getName());
        }
        return NamingUtils.generateNewName(existingNames, Messages.parameter, 1);
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(final Query query) {
        this.query = query;
    }

    public void setBusinessObject(final BusinessObject businessObject) {
        this.businessObject = businessObject;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.DialogPage#performHelp()
     */
    @Override
    public void performHelp() {
        try {
            new OpenBrowserOperation(new URL(JPQL_HELP_URL)).execute();
        } catch (final MalformedURLException e) {
            BonitaStudioLog.error(e);
        }
    }

}
