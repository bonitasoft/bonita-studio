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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.difflog.IDiffLogger;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.control.AttributesTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.control.IndexesTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.control.QueriesTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.control.UniqueConstraintTabItemControl;
import org.bonitasoft.studio.businessobject.ui.wizard.editingsupport.BusinessObjectNameEditingSupport;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.BusinessObjectTreeContentProvider;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.databinding.observables.GroupTextProperty;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.ButtonWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author Romain Bioteau
 */
public class BusinessDataModelWizardPage extends WizardPage {

    public static final String DEFAULT_BUSINESS_OBJECT_NAME = "BusinessObject";

    private IObservableList fieldsList;
    private IDiffLogger diffLogger;
    private IObservableList businessObjectObserveList;
    private IViewerObservableValue selectionObservable;
    private PackageHelper packageHelper;
    private BusinessObjectModel businessObjectModel;
    private TreeViewer viewer;
    private ButtonWidget deleteButton;
    private BusinessObjectNameEditingSupport editingSupport;

    protected BusinessDataModelWizardPage(BusinessObjectModel businessObjectModel, IDiffLogger diffLogger) {
        super(BusinessDataModelWizardPage.class.getName());
        this.businessObjectModel = businessObjectModel;
        this.diffLogger = diffLogger;
        packageHelper = new PackageHelper();
    }

    @Override
    public void createControl(Composite parent) {
        DataBindingContext ctx = new DataBindingContext();
        Composite mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(3).extendedMargins(10, 10, 10, 0).spacing(1, 5).create());

        createBusinessObjectDefinitionGroup(mainComposite, ctx);
        createSeparator(mainComposite);
        createBusinessObjectEditionGroup(mainComposite, ctx);

        if (!businessObjectModel.getBusinessObjects().isEmpty()) {
            selectionObservable.setValue(businessObjectModel.getBusinessObjects().get(0));
        }
        WizardPageSupport.create(this, ctx);
        setControl(mainComposite);
    }

    private void createBusinessObjectDefinitionGroup(Composite mainComposite, DataBindingContext ctx) {
        Group group = new Group(mainComposite, SWT.NONE);
        group.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create());
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).spacing(5, 0).create());
        group.setText(Messages.listOfBusinessObjects);
        createButtons(group);
        createBusinessObjectViewer(group, ctx);
        ctx.bindValue(deleteButton.observeEnabled(), new ComputedValue<Boolean>() {

            @Override
            protected Boolean calculate() {
                return selectionObservable.getValue() != null;
            }
        });
    }

    private void createButtons(Composite parent) {
        Composite buttonsComposite = new Composite(parent, SWT.NONE);
        buttonsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).indent(0, 20).create());
        buttonsComposite.setLayout(GridLayoutFactory.fillDefaults().create());

        new ButtonWidget.Builder()
                .withStyle(SWT.FLAT)
                .withLabel(Messages.addPackage)
                .fill()
                .onClick(e -> addPackage())
                .createIn(buttonsComposite);

        new ButtonWidget.Builder()
                .withStyle(SWT.FLAT)
                .withLabel(Messages.addBusinessObject)
                .fill()
                .onClick(e -> addBusinessObject(getSelectedPackage().orElse(PackageHelper.DEFAULT_PACKAGE_NAME)))
                .createIn(buttonsComposite);

        deleteButton = new ButtonWidget.Builder()
                .withStyle(SWT.FLAT)
                .withLabel(Messages.delete)
                .fill()
                .onClick(e -> deleteSelection())
                .createIn(buttonsComposite);
    }

    private Optional<String> getSelectedPackage() {
        Object selection = selectionObservable.getValue();
        if (selection instanceof String) {
            return Optional.of((String) selection);
        } else if (selection instanceof BusinessObject) {
            return Optional.of(packageHelper.getPackageName((BusinessObject) selection));
        }
        return Optional.empty();
    }

    private void addPackage() {
        String newPackageName = packageHelper.getNextPackageName(businessObjectModel);
        addBusinessObject(newPackageName);
    }

    private void createBusinessObjectEditionGroup(Composite parent, DataBindingContext ctx) {
        Group editionGroup = new Group(parent, SWT.NONE);
        editionGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(1, 2).create());
        editionGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        editionGroup.setText(Messages.selectABusinessObjectToEdit);

        createDescription(editionGroup, ctx);
        createEditionTabFolder(ctx, editionGroup);

        ISWTObservableValue editionGroupTextObservable = new GroupTextProperty().observe(editionGroup);
        editingSupport.setEditionGroupTextObservable(editionGroupTextObservable);
        ctx.bindValue(PojoProperties.value("qualifiedName").observeDetail(selectionObservable),
                editionGroupTextObservable,
                UpdateStrategyFactory.updateValueStrategy().withConverter(toPaneDescripitonlTitle()).create(),
                UpdateStrategyFactory.neverUpdateValueStrategy().create());
    }

    private void createEditionTabFolder(DataBindingContext ctx, Composite parent) {
        TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
        tabFolder.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).hint(500, 300).span(2, 1).create());
        fieldsList = PojoProperties.list("fields").observeDetail(selectionObservable);

        createAttributeTabItem(ctx, selectionObservable, tabFolder);
        createConstraintsTabItem(ctx, selectionObservable, tabFolder);
        createQueriesTabItem(ctx, selectionObservable, tabFolder);
        createIndexesTabItem(ctx, selectionObservable, tabFolder);
        tabFolder.setSelection(tabFolder.getItem(0));
    }

    private void createQueriesTabItem(DataBindingContext ctx, IViewerObservableValue viewerObservableValue,
            TabFolder tabFolder) {
        final TabItem queriesItem = new TabItem(tabFolder, SWT.NONE);
        queriesItem.setText(Messages.queries);
        queriesItem.setControl(new QueriesTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList));
    }

    private void createAttributeTabItem(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            TabFolder tabFolder) {
        final TabItem attributeItem = new TabItem(tabFolder, SWT.NONE);
        attributeItem.setText(Messages.attributes);
        attributeItem.setControl(
                new AttributesTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList, businessObjectModel,
                        diffLogger));
    }

    private void createConstraintsTabItem(final DataBindingContext ctx, final IViewerObservableValue viewerObservableValue,
            TabFolder tabFolder) {
        final TabItem constraintsTabItem = new TabItem(tabFolder, SWT.NONE);
        constraintsTabItem.setText(Messages.constraints);
        constraintsTabItem.setControl(
                new UniqueConstraintTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList, businessObjectModel));
    }

    private void createIndexesTabItem(DataBindingContext ctx, IViewerObservableValue viewerObservableValue,
            TabFolder tabFolder) {
        final TabItem indexTabItem = new TabItem(tabFolder, SWT.NONE);
        indexTabItem.setText(Messages.indexes);
        indexTabItem.setControl(
                new IndexesTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList, businessObjectModel));
    }

    private void createDescription(Composite parent, DataBindingContext ctx) {

        final Label descriptionLabel = new Label(parent, SWT.NONE);
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.TOP).create());
        descriptionLabel.setText(Messages.description);

        final Text descriptionText = new Text(parent, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
        descriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 50).create());
        descriptionText.setEnabled(selectionObservable.getValue() instanceof BusinessObject);

        final IObservableValue<String> observeDetailValue = PojoProperties.value("description")
                .observeDetail(selectionObservable);
        ctx.bindValue(WidgetProperties.text(SWT.Modify).observe(descriptionText), observeDetailValue);

        final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject instanceof BusinessObject;
            }
        });
        ctx.bindValue(WidgetProperties.enabled().observe(descriptionText), selectionObservable, null, enableStrategy);
    }

    private void createSeparator(final Composite mainComposite) {
        final Label separator = new Label(mainComposite, SWT.NONE);
        separator.setImage(Pics.getImage("arrow.png", BusinessObjectPlugin.getDefault()));
        separator.setLayoutData(
                GridDataFactory.fillDefaults().grab(false, true).align(SWT.CENTER, SWT.CENTER).span(1, 2).create());
    }

    private void createBusinessObjectViewer(Composite parent, DataBindingContext ctx) {
        viewer = new TreeViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        viewer.getControl()
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, SWT.DEFAULT).create());
        viewer.getTree().setLinesVisible(true);
        viewer.getTree().setHeaderVisible(true);
        ColumnViewerToolTipSupport.enableFor(viewer);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1));
        viewer.getTree().setLayout(layout);
        viewer.setContentProvider(new BusinessObjectTreeContentProvider());
        selectionObservable = ViewersObservables.observeSingleSelection(viewer);
        createNameColumn(ctx);
        final UpdateValueStrategy enableStrategy = new UpdateValueStrategy();
        enableStrategy.setConverter(new Converter(Object.class, Boolean.class) {

            @Override
            public Object convert(final Object fromObject) {
                return fromObject != null;
            }
        });

        businessObjectObserveList = PojoProperties.list("businessObjects").observe(businessObjectModel);
        viewer.setInput(businessObjectModel);
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

    private void addBusinessObject(String packageName) {
        BusinessObject businessObject = new BusinessObject();
        businessObject.setQualifiedName(generateObjectName(packageName));
        businessObjectObserveList.add(businessObject);
        diffLogger.boAdded(businessObject.getQualifiedName());
        viewer.getControl().getDisplay().asyncExec(() -> {
            viewer.refresh();
            viewer.editElement(businessObject, 0);
        });
    }

    private void deleteSelection() {
        if (selectionObservable.getValue() instanceof BusinessObject) {
            deleteBusinessObject((BusinessObject) selectionObservable.getValue());
        } else if (selectionObservable.getValue() instanceof String) {
            deletePackage((String) selectionObservable.getValue());
        }
    }

    private void deletePackage(String packageName) {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deletePackageConfirmTitle,
                String.format(Messages.deletePackageConfirm, packageName))) {
            packageHelper.getAllBusinessObjects(businessObjectModel, packageName).stream()
                    .peek(businessObjectObserveList::remove)
                    .map(BusinessObject::getQualifiedName)
                    .forEach(diffLogger::boRemoved);
            viewer.refresh();
        }
    }

    private void deleteBusinessObject(BusinessObject bo) {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteBOConfirmTitle,
                String.format(Messages.deleteBOConfirmMessage, bo.getSimpleName()))) {
            businessObjectObserveList.remove(bo);
            diffLogger.boRemoved(bo.getQualifiedName());
            viewer.refresh();
        }
    }

    private void createNameColumn(DataBindingContext ctx) {
        TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.FILL);
        column.getColumn().setText(Messages.name);
        column.setLabelProvider(new LabelProviderBuilder<>()
                .withTextProvider(this::getElementName)
                .createColumnLabelProvider());
        editingSupport = new BusinessObjectNameEditingSupport(businessObjectModel, selectionObservable, viewer, ctx,
                diffLogger);
        column.setEditingSupport(editingSupport);
    }

    private String getElementName(Object element) {
        return element instanceof BusinessObject ? ((BusinessObject) element).getSimpleName() : element.toString();
    }

    private String generateObjectName(String packageName) {
        List<String> existingNames = businessObjectModel.getBusinessObjects().stream()
                .map(BusinessObject::getSimpleName)
                .collect(Collectors.toList());
        String newName = StringIncrementer.getNextIncrement(DEFAULT_BUSINESS_OBJECT_NAME, existingNames);
        return String.format("%s.%s", packageName, newName);
    }

}
