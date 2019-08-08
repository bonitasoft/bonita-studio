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
package org.bonitasoft.studio.businessobject.ui.wizard;

import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.neverUpdateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.Query;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.control.AttributesTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.control.IndexesTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.control.QueriesTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.control.UniqueConstraintTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.BusinessObjectNameEditingSupport;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.jface.databinding.observables.GroupTextProperty;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class BusinessDataModelWizardPage extends WizardPage {

    private static final String ORG_BONITASOFT_PREFIX = "org.bonitasoft";

    private static final String COM_BONITASOFT_PREFIX = "com.bonitasoft";

    private static final String DEFAULT_PACKAGE_NAME = "com.company.model";

    private final BusinessObjectModel businessObjectModel;

    private String packageName;

    private IObservableList fieldsList;

    private Label helpLabel;

    private TableViewerColumn businessObjectNameColumn;

    private TableViewer boTableViewer;

    private IDiffLogger diffLogger;

    protected BusinessDataModelWizardPage(final BusinessObjectModel businessObjectModel, IDiffLogger diffLogger) {
        super(BusinessDataModelWizardPage.class.getName());
        this.businessObjectModel = businessObjectModel;
        this.diffLogger = diffLogger;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final DataBindingContext ctx = new DataBindingContext();
        final Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(10, 10, 10, 0).spacing(1, 5).create());

        final IViewerObservableValue viewerObservableValue = createListOfBusinessObjects(mainComposite, ctx);
        createSeparator(mainComposite);
        createBusinessObjectDescription(mainComposite, ctx, viewerObservableValue);
        createPackageName(ctx, mainComposite);
        if (!businessObjectModel.getBusinessObjects().isEmpty()) {
            viewerObservableValue.setValue(businessObjectModel.getBusinessObjects().get(0));
        }
        WizardPageSupport.create(this, ctx);
        setControl(mainComposite);
    }

    protected void createBusinessObjectDescription(final Composite mainComposite, final DataBindingContext ctx,
            final IViewerObservableValue viewerObservableValue) {
        final Group businessObjectDescriptionGroup = new Group(mainComposite, SWT.NONE);
        businessObjectDescriptionGroup.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).span(1, 2).create());
        businessObjectDescriptionGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        businessObjectDescriptionGroup.setText(Messages.selectABusinessObjectToEdit);

        final IObservableValue observeDetailValue = PojoObservables.observeDetailValue(viewerObservableValue,
                "qualifiedName", String.class);
        final ISWTObservableValue groupTextObservable = new GroupTextProperty().observe(businessObjectDescriptionGroup);
        businessObjectNameColumn.setEditingSupport(new BusinessObjectNameEditingSupport(businessObjectModel,
                viewerObservableValue, PojoObservables.observeValue(
                        this, "packageName"),
                groupTextObservable,
                boTableViewer, ctx, diffLogger));
        ctx.bindValue(observeDetailValue,
                groupTextObservable,
                updateValueStrategy().withConverter(toPaneDescripitonlTitle()).create(),
                neverUpdateValueStrategy().create());

        createDescription(ctx, viewerObservableValue, businessObjectDescriptionGroup);

        CTabFolder tabFolder = new CTabFolder(businessObjectDescriptionGroup, SWT.BORDER);
        tabFolder.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(500, 300).span(2, 1).create());
        tabFolder.setLayout(GridLayoutFactory.fillDefaults().create());
        tabFolder.setSelectionBackground(new Color[] { Display.getDefault().getSystemColor(SWT.COLOR_WHITE),
                tabFolder.getBackground() }, new int[] { 100 }, true);
        fieldsList = PojoObservables.observeDetailList(viewerObservableValue, "fields", null);

        createAttributeTabItem(ctx, viewerObservableValue, tabFolder);
        createConstraintsTabItem(ctx, viewerObservableValue, tabFolder);
        createQueriesTabItem(ctx, viewerObservableValue, tabFolder);
        createIndexesTabItem(ctx, viewerObservableValue, tabFolder);
        tabFolder.setSelection(tabFolder.getItem(0));
    }

    protected void createQueriesTabItem(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            final CTabFolder tabFolder) {
        final CTabItem queriesItem = new CTabItem(tabFolder, SWT.BORDER);
        queriesItem.setText(Messages.queries);
        queriesItem.setControl(new QueriesTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList));
    }

    protected void createAttributeTabItem(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            final CTabFolder tabFolder) {
        final CTabItem attributeItem = new CTabItem(tabFolder, SWT.NONE);
        attributeItem.setText(Messages.attributes);
        attributeItem.setControl(
                new AttributesTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList, businessObjectModel,
                        diffLogger));
    }

    protected void createConstraintsTabItem(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            final CTabFolder tabFolder) {
        final CTabItem constraintsTabItem = new CTabItem(tabFolder, SWT.BORDER);
        constraintsTabItem.setText(Messages.constraints);
        constraintsTabItem.setControl(
                new UniqueConstraintTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList, businessObjectModel));
    }

    protected void createIndexesTabItem(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            final CTabFolder tabFolder) {
        final CTabItem indexTabItem = new CTabItem(tabFolder, SWT.BORDER);
        indexTabItem.setText(Messages.indexes);
        indexTabItem.setControl(
                new IndexesTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList, businessObjectModel));
    }

    protected Button createDeleteButton(final Composite buttonsComposite) {
        final Button deleteButton = new Button(buttonsComposite, SWT.FLAT);
        deleteButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        deleteButton.setText(Messages.delete);
        deleteButton.setEnabled(false);
        return deleteButton;
    }

    protected Button createAddButton(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            final Composite buttonsComposite) {
        final Button addButton = new Button(buttonsComposite, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addButton.setText(Messages.add);
        addButton.setEnabled(false);
        return addButton;
    }

    protected void createDescription(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            final Group descriptionGroup) {

        final Label descriptionLabel = new Label(descriptionGroup, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.TOP).create());
        descriptionLabel.setText(Messages.description);

        final Text descriptionText = new Text(descriptionGroup, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 50).create());
        descriptionText.setEnabled(viewerObservableValue.getValue() != null);

        final IObservableValue<String> observeDetailValue = PojoProperties.value("description")
                .observeDetail(viewerObservableValue);
        ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(descriptionText), observeDetailValue);

        final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }
        });
        ctx.bindValue(WidgetProperties.enabled().observe(descriptionText), viewerObservableValue, null, enableStrategy);
    }

    protected void createSeparator(final Composite mainComposite) {
        final Label separator = new Label(mainComposite, SWT.NONE);
        separator.setImage(Pics.getImage("arrow.png", BusinessObjectPlugin.getDefault()));
        separator.setLayoutData(
                GridDataFactory.fillDefaults().grab(false, true).align(SWT.CENTER, SWT.CENTER).span(1, 2).create());
    }

    protected IViewerObservableValue createListOfBusinessObjects(final Composite mainComposite,
            final DataBindingContext ctx) {
        final Group group = new Group(mainComposite, SWT.NONE);
        group.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).spacing(5, 0).create());
        group.setText(Messages.listOfBusinessObjects);

        final Composite buttonsComposite = new Composite(group, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).indent(0, 20).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).spacing(0, 3).create());

        final Button addButton = new Button(buttonsComposite, SWT.FLAT);
        addButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        addButton.setText(Messages.add);

        final Button deleteButton = new Button(buttonsComposite, SWT.FLAT);
        deleteButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false)
                .minSize(IDialogConstants.BUTTON_WIDTH, SWT.DEFAULT).create());
        deleteButton.setText(Messages.delete);
        deleteButton.setEnabled(false);

        boTableViewer = new TableViewer(group, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        boTableViewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, SWT.DEFAULT).create());
        boTableViewer.getTable().setLinesVisible(true);
        boTableViewer.getTable().setHeaderVisible(true);
        final TableLayout tableLayout = new TableLayout();
        tableLayout.addColumnData(new ColumnWeightData(1));
        boTableViewer.getTable().setLayout(tableLayout);
        boTableViewer.setContentProvider(new ObservableListContentProvider());

        final IViewerObservableValue observeBusinessObjectSingleSelection = ViewersObservables
                .observeSingleSelection(boTableViewer);

        businessObjectNameColumn = createBusinessObjectNameColumn(ctx, observeBusinessObjectSingleSelection, boTableViewer);

        final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }
        });

        ctx.bindValue(SWTObservables.observeEnabled(deleteButton), observeBusinessObjectSingleSelection,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER),
                enableStrategy);

        final IObservableList businessObjectObserveList = PojoObservables.observeList(businessObjectModel,
                "businessObjects");
        boTableViewer.setInput(businessObjectObserveList);
        addButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                addBusinessObject(boTableViewer, businessObjectObserveList);
            }

        });

        deleteButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                deleteBusinessObject(boTableViewer, observeBusinessObjectSingleSelection, businessObjectObserveList);
            }

        });
        return observeBusinessObjectSingleSelection;
    }

    private IConverter toPaneDescripitonlTitle() {
        return new Converter(String.class, String.class) {

            @Override
            public Object convert(Object fromObject) {
                if (fromObject != null) {
                    return NamingUtils.getSimpleName((String) fromObject);
                }
                return Messages.selectABusinessObjectToEdit;
            }
        };
    }

    protected void createPackageName(final DataBindingContext ctx, final Composite parent) {
        final Composite packageComposite = new Composite(parent, SWT.NONE);
        packageComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        packageComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).create());

        final Label packageNameLabel = new Label(packageComposite, SWT.NONE);
        packageNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).create());
        packageNameLabel.setText(Messages.packageName);

        final Text packageNameText = new Text(packageComposite, SWT.BORDER);
        packageNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final UpdateValueStrategy targetToModel = new UpdateValueStrategy();
        targetToModel.setAfterGetValidator(new IValidator() {

            @Override
            public IStatus validate(final Object value) {
                return validatePackageName(value);
            }

        });
        final IObservableValue packageNameObserveValue = PojoObservables.observeValue(this, "packageName");
        final ISWTObservableValue packageNameObserveText = SWTObservables.observeText(packageNameText, SWT.Modify);
        ctx.bindValue(SWTObservables.observeDelayedValue(200, packageNameObserveText), packageNameObserveValue,
                targetToModel, null);
        packageNameObserveValue.addValueChangeListener(new IValueChangeListener<String>() {

            @Override
            public void handleValueChange(ValueChangeEvent<? extends String> event) {
                String newPackageName = event.diff.getNewValue();
                if (newPackageName.isEmpty()) {
                    newPackageName = DEFAULT_PACKAGE_NAME;
                }
                if (newPackageName.endsWith(".")) {
                    newPackageName.substring(0, newPackageName.length() - 1);
                }
                for (final BusinessObject bo : businessObjectModel.getBusinessObjects()) {
                    final String previousName = bo.getQualifiedName();
                    final String qualifiedName = newPackageName + "." + NamingUtils.getSimpleName(bo.getQualifiedName());
                    diffLogger.boRenamed(bo.getQualifiedName(), qualifiedName);
                    bo.setQualifiedName(qualifiedName);

                    for (final Query q : bo.getQueries()) {
                        if (previousName.equals(q.getReturnType())) {
                            q.setReturnType(qualifiedName);
                        }
                    }
                }
            }
        });
        if (businessObjectModel.getBusinessObjects().isEmpty()) {
            packageNameObserveText.setValue(DEFAULT_PACKAGE_NAME);
        } else {
            packageNameObserveText.setValue(
                    NamingUtils.getPackageName(businessObjectModel.getBusinessObjects().get(0).getQualifiedName()));
        }
    }

    protected void addBusinessObject(final TableViewer boTableViewer, final IObservableList businessObjectsObserveList) {
        final BusinessObject businessObject = new BusinessObject();
        businessObject.setQualifiedName(generateObjectName());
        businessObjectsObserveList.add(businessObject);
        diffLogger.boAdded(businessObject.getQualifiedName());
        boTableViewer.getControl().getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                boTableViewer.editElement(businessObject, 0);
            }
        });

    }

    protected IStatus validatePackageName(final Object value) {
        if (value == null || value.toString().isEmpty()) {
            return ValidationStatus.error(Messages.error_emptyPackageName);
        }
        final IStatus status = javaPackageValidation(value);
        if (status.isOK()) {
            if (value.toString().startsWith(COM_BONITASOFT_PREFIX + ".") || value.toString().equals(COM_BONITASOFT_PREFIX)) {
                return ValidationStatus.error(Messages.bind(Messages.error_reservedPackagePrefix, value.toString()));
            }
            if (value.toString().startsWith(ORG_BONITASOFT_PREFIX + ".") || value.toString().equals(ORG_BONITASOFT_PREFIX)) {
                return ValidationStatus.error(Messages.bind(Messages.error_reservedPackagePrefix, value.toString()));
            }
        }
        return status;
    }

    protected IStatus javaPackageValidation(final Object value) {
        return JavaConventions.validatePackageName(value.toString(), JavaCore.VERSION_1_6, JavaCore.VERSION_1_6);
    }

    protected void deleteBusinessObject(final TableViewer boTableViewer, final IViewerObservableValue observeSingleSelection,
            final IObservableList businessObjectsObserveList) {
        final IStructuredSelection selection = (IStructuredSelection) boTableViewer.getSelection();
        final BusinessObject bo = (BusinessObject) selection.getFirstElement();
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteBOConfirmTitle,
                Messages.bind(Messages.deleteBOConfirmMessage, NamingUtils.getSimpleName(bo.getQualifiedName())))) {
            for (final Object selected : selection.toList()) {
                businessObjectsObserveList.remove(selected);
                diffLogger.boRemoved(((BusinessObject) selected).getQualifiedName());
            }
            if (businessObjectsObserveList.isEmpty()) {
                observeSingleSelection.setValue(null);
            }
        }
    }

    protected TableViewerColumn createBusinessObjectNameColumn(final DataBindingContext ctx,
            final IViewerObservableValue businessObjectSingleSelection,
            final TableViewer boTableViewer) {
        final TableViewerColumn columnViewer = new TableViewerColumn(boTableViewer, SWT.FILL);
        final TableColumn column = columnViewer.getColumn();
        column.setText(Messages.name);
        columnViewer.setLabelProvider(new ColumnLabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(final Object element) {
                if (element instanceof BusinessObject) {
                    return NamingUtils.getSimpleName(((BusinessObject) element).getQualifiedName());
                }
                return super.getText(element);
            }
        });

        final TableColumnSorter sorter = new TableColumnSorter(boTableViewer);
        sorter.setColumn(column);
        return columnViewer;
    }

    protected String generateObjectName() {
        final Set<String> existingNames = new HashSet<>();
        for (final BusinessObject businessObject : businessObjectModel.getBusinessObjects()) {
            existingNames.add(businessObject.getQualifiedName());
        }
        return NamingUtils.generateNewName(existingNames, getPackageName() + ".BusinessObject", 1);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

}
