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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.editor.editor.ui.control.attribute.AttributeEditionControl;
import org.bonitasoft.studio.businessobject.editor.editor.ui.formpage.AbstractBdmFormPage;
import org.bonitasoft.studio.businessobject.editor.editor.ui.provider.BusinessObjectTreeContentProvider;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.editor.model.FieldType;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.bonitasoft.studio.businessobject.editor.model.Query;
import org.bonitasoft.studio.businessobject.editor.model.builder.BusinessObjectBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.PackageBuilder;
import org.bonitasoft.studio.businessobject.editor.model.builder.SimpleFieldBuilder;
import org.bonitasoft.studio.businessobject.helper.PackageHelper;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.refactor.DiffElement;
import org.bonitasoft.studio.businessobject.refactor.Event;
import org.bonitasoft.studio.businessobject.ui.wizard.BusinessObjectTransfer;
import org.bonitasoft.studio.businessobject.validator.BusinessObjectListValidator;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.converter.ConverterBuilder;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.bonitasoft.studio.ui.util.StringIncrementer;
import org.bonitasoft.studio.ui.viewer.EditingSupportBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.Section;

public class BusinessObjectList {

    public static final String DEFAULT_BO_NAME = "BusinessObject";

    public static final String BUSINESS_OBJECT_LIST_VIEWER_ID = "boListViewerid";
    public static final String ADD_BO_BUTTON_ID = "addBoId";
    public static final String ADD_PACKAGE_BUTTON_ID = "addPackageId";
    public static final String REMOVE_BUTTON_ID = "removeBoId";

    protected Section section;
    private TreeViewer viewer;
    private IObservableValue<BusinessObjectModel> input = new WritableValue<>();
    private DataBindingContext ctx;
    private IObservableValue<Object> selectionObservable;
    private IObservableValue<BusinessObject> businessObjectSelectionObservable = new WritableValue<>();
    private ToolItem deleteItem;
    private ToolItem addBoItem;
    private ToolItem addPackageItem;
    private List<BusinessObject> boToFilter = new ArrayList<>();
    private Set<Package> expandedPackages = new HashSet();

    public BusinessObjectList(
            Composite parent, AbstractBdmFormPage formPage,
            DataBindingContext ctx) {
        this.ctx = ctx;

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.listOfBusinessObjects);

        Composite boListComposite = createBusinessObjectListComposite(formPage);

        ctx.bindValue(businessObjectSelectionObservable, selectionObservable,
                UpdateStrategyFactory.updateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(ConverterBuilder.<Object, BusinessObject> newConverter()
                                .fromType(Object.class)
                                .toType(BusinessObject.class)
                                .withConvertFunction(o -> o instanceof BusinessObject ? (BusinessObject) o : null)
                                .create())
                        .create());
        ctx.bindValue(businessObjectSelectionObservable, formPage.observeBusinessObjectSelected());

        section.setClient(boListComposite);
    }

    protected Composite createBusinessObjectListComposite(AbstractBdmFormPage formPage) {
        Composite boListComposite = formPage.getToolkit().createComposite(section);
        boListComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1).margins(5, 10)
                        .create());
        boListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(boListComposite, formPage);
        createSearchField(boListComposite, formPage);
        createViewer(boListComposite, formPage);
        enableButtons();
        return boListComposite;
    }

    protected void createSearchField(Composite parent, AbstractBdmFormPage formPage) {
        TextWidget searchWidget = createSearchWidget(parent, formPage);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                List<Package> expandedElements = Arrays.asList(viewer.getExpandedElements()).stream()
                        .filter(Package.class::isInstance).map(Package.class::cast).collect(Collectors.toList());
                expandedPackages.removeIf(pkg -> !expandedElements.contains(pkg)
                        && pkg.getBusinessObjects().stream().anyMatch(bo -> !boToFilter.contains(bo)));
                expandedPackages.addAll(expandedElements);
                boToFilter.clear();
                input.getValue().getPackages().stream()
                        .map(Package::getBusinessObjects)
                        .flatMap(Collection::stream)
                        .filter(bo -> !bo.getSimpleName().toLowerCase().contains(search))
                        .forEach(boToFilter::add);
                viewer.refresh();
                expandedPackages.forEach(pkg -> viewer.expandToLevel(pkg, 1));
            });
        });
    }

    protected TextWidget createSearchWidget(Composite parent, AbstractBdmFormPage formPage) {
        return new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withPlaceholder(Messages.search)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    protected void enableButtons() {
        ComputedValue<Boolean> selectionNotNull = new ComputedValue<Boolean>(Boolean.TYPE) {

            @Override
            protected Boolean calculate() {
                return selectionObservable.getValue() != null;
            }
        };
        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), selectionNotNull);
        ctx.bindValue(WidgetProperties.enabled().observe(addBoItem), selectionNotNull);
    }

    protected void createViewer(Composite parent, AbstractBdmFormPage formPage) {
        viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
        formPage.getToolkit().adapt(viewer.getTree());
        viewer.getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, BUSINESS_OBJECT_LIST_VIEWER_ID);
        viewer.addFilter(createSearchFilter());
        viewer.addDoubleClickListener(e -> {
            viewer.setExpandedState(selectionObservable.getValue(),
                    !viewer.getExpandedState(selectionObservable.getValue()));
        });

        ColumnViewerToolTipSupport.enableFor(viewer);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTree().setLayout(layout);
        viewer.setUseHashlookup(true);
        createBusinessObjectColumn(viewer, formPage);
        viewer.setContentProvider(new BusinessObjectTreeContentProvider());
        viewer.setComparator(new BusinessObjectViewerComparator());
        viewer.setInput(input);
        addDNDSupport(formPage);
        selectionObservable = ViewerProperties.singleSelection(Object.class).observe(viewer);
    }

    protected void addDNDSupport(AbstractBdmFormPage formPage) {
        viewer.addDropSupport(DND.DROP_MOVE | DND.DROP_MOVE | DND.DROP_DEFAULT,
                new Transfer[] { BusinessObjectTransfer.getInstance() },
                new DropTargetAdapter() {

                    @Override
                    public void drop(DropTargetEvent event) {
                        dragLeave(event);
                        if (event.data instanceof BusinessObject && event.item != null) {
                            TreeItem treeItem = (TreeItem) event.item;
                            BusinessObject bo = (BusinessObject) event.data;
                            if (treeItem.getParentItem() == null) {
                                updatePackage(formPage, bo, treeItem.getText());
                            } else {
                                updatePackage(formPage, bo, treeItem.getParentItem().getText());
                            }
                        }
                    }

                });

        viewer.addDragSupport(DND.DROP_MOVE,
                new Transfer[] { BusinessObjectTransfer.getInstance() },
                new DragSourceAdapter() {

                    @Override
                    public void dragStart(DragSourceEvent event) {
                        if (selectionObservable.getValue() instanceof BusinessObject) {
                            event.detail = DND.DROP_MOVE;
                            dragSetData(event);
                        }
                    }

                    @Override
                    public void dragSetData(DragSourceEvent event) {
                        event.doit = selectionObservable.getValue() != null;
                        event.data = selectionObservable.getValue();
                    }
                });
    }

    private void updatePackage(AbstractBdmFormPage formPage, BusinessObject bo, String name) {
        Package newPackage = input.getValue().getPackages().stream().filter(pakage -> Objects.equals(name, pakage.getName()))
                .findFirst().orElseThrow(() -> new RuntimeException(String.format("Unable to find package %s", name)));
        Package oldPackage = (Package) bo.eContainer();

        oldPackage.getBusinessObjects().remove(bo);
        newPackage.getBusinessObjects().add(bo);

        String oldQualifiedName = bo.getQualifiedName();
        bo.setQualifiedName(String.format("%s.%s", name, bo.getSimpleName()));

        bo.getQueries().stream()
                .filter(query -> Objects.equals(query.getReturnType(), oldQualifiedName))
                .forEach(query -> query.setReturnType(bo.getQualifiedName()));
        Display.getDefault().asyncExec(() -> {
            Object[] elementsToExpend = viewer.getExpandedElements();
            viewer.refresh();
            viewer.setExpandedElements(elementsToExpend);
            selectionObservable.setValue(bo);
            formPage.getEditorContribution().refreshBusinessObjectList();
        });
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof BusinessObject) {
                    return !boToFilter.contains(element);
                }
                return ((Package) element).getBusinessObjects()
                        .stream().anyMatch(bo -> !boToFilter.contains(bo));
            }
        };
    }

    protected void createBusinessObjectColumn(TreeViewer viewer, AbstractBdmFormPage formPage) {
        TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<>()
                .withTextProvider(this::getName)
                .withImageProvider(this::getIcon)
                .withStatusProvider(
                        businessObjectStatusProvider(new BusinessObjectListValidator(formPage.observeWorkingCopy())))
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
        addEditingSupport(viewer, formPage, column);
    }

    protected void addEditingSupport(TreeViewer viewer, AbstractBdmFormPage formPage, TreeViewerColumn column) {
        column.setEditingSupport(new EditingSupportBuilder<>(viewer)
                .withId(SWTBotConstants.SWTBOT_ID_BO_NAME_TEXTEDITOR)
                .withValueProvider(this::getName)
                .withValueUpdater((elt, name) -> {
                    if (!Objects.equals(getName(elt), name)) {
                        updateName(elt, (String) name, formPage);
                        formPage.getEditorContribution().refreshBusinessObjectList();
                        formPage.getEditorContribution().refreshConstraintList();
                        formPage.getEditorContribution().refreshIndexList();
                        formPage.getEditorContribution().refreshQueryViewers();
                        formPage.refactorAccessControl();
                    }
                })
                .create());
    }

    private void updateName(Object element, String newName, AbstractBdmFormPage formPage) {
        EObject old = EcoreUtil.copy(((EObject) element));
        if (element instanceof BusinessObject) {
            BusinessObject bo = (BusinessObject) element;
            String packageName = ((Package) bo.eContainer()).getName();
            String qualifiedName = String.format("%s.%s", packageName, newName);
            bo.setQualifiedName(qualifiedName);
            bo.setSimpleName(newName);
            updateDefaultQueries(bo, formPage);
            formPage.addToAccessControlRefactorQueue(new DiffElement(Event.RENAME_BO, old, bo));
            // TODO update queries: content and return type
        } else {
            Package pakage = (Package) element;
            pakage.setName(newName);
            formPage.addToAccessControlRefactorQueue(new DiffElement(Event.RENAME_PACKAGE, old, pakage));
            pakage.getBusinessObjects().forEach(bo -> updateName(bo, bo.getSimpleName(), formPage));
        }
    }

    private String getName(Object element) {
        return element instanceof Package
                ? ((Package) element).getName()
                : ((BusinessObject) element).getSimpleName();
    }

    private Image getIcon(Object element) {
        return element instanceof Package
                ? BusinessObjectPlugin.getImage("/icons/packageIcon.png")
                : BusinessObjectPlugin.getImage("/icons/bdm.png");
    }

    public Function<Object, IStatus> businessObjectStatusProvider(IValidator... validators) {
        return element -> Stream.of(validators)
                .map(v -> v.validate(element))
                .map(status -> status instanceof MultiStatus ? Arrays.asList(status.getChildren()) : Arrays.asList(status))
                .flatMap(Collection::stream)
                .collect(StatusCollectors.toMultiStatus());
    }

    private void createToolbar(Composite parent, AbstractBdmFormPage formPage) {
        Composite composite = formPage.getToolkit().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.END, SWT.FILL).create());

        ToolBar toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);
        createAddDeleteItems(formPage, toolBar);
        createExpandCollapseItems(toolBar);
    }

    private void createExpandCollapseItems(ToolBar toolBar) {
        ToolItem expandItem = new ToolItem(toolBar, SWT.PUSH);
        expandItem.setImage(Pics.getImage(PicsConstants.expandAll));
        expandItem.setToolTipText(Messages.expandAll);
        expandItem.addListener(SWT.Selection, e -> {
            viewer.expandAll();

            /**
             * TODO https://bugs.eclipse.org/bugs/show_bug.cgi?id=567132 -> fixed in 4.18 (2020-12)
             */
            if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
                viewer.getControl().redraw();
            }
        });

        ToolItem collapseItem = new ToolItem(toolBar, SWT.PUSH);
        collapseItem.setImage(Pics.getImage(PicsConstants.collapseAll));
        collapseItem.setToolTipText(Messages.collapseAll);
        collapseItem.addListener(SWT.Selection, e -> viewer.collapseAll());
    }

    protected void createAddDeleteItems(AbstractBdmFormPage formPage, ToolBar toolBar) {
        addPackageItem = new ToolItem(toolBar, SWT.PUSH);
        addPackageItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_PACKAGE_BUTTON_ID);
        addPackageItem.setImage(BusinessObjectPlugin.getImage("/icons/addPackageIcon.png"));
        addPackageItem.setToolTipText(Messages.createPackageTooltip);
        addPackageItem.addListener(SWT.Selection, e -> addPackage(formPage));

        addBoItem = new ToolItem(toolBar, SWT.PUSH);
        addBoItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_BO_BUTTON_ID);
        addBoItem.setImage(BusinessObjectPlugin.getImage("/icons/addObjectIcon.png"));
        addBoItem.setToolTipText(Messages.createBoTooltip);
        addBoItem.addListener(SWT.Selection, e -> addBusinessObject(formPage));

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_BUTTON_ID);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setToolTipText(Messages.deleteTooltip);
        deleteItem.addListener(SWT.Selection, e -> remove(formPage));
    }

    private void addPackage(AbstractBdmFormPage formPage) {
        viewer.applyEditorValue();
        List<String> existingPackages = input.getValue().getPackages().stream().map(Package::getName)
                .collect(Collectors.toList());
        String newPackageName = StringIncrementer.getNextIncrement(PackageHelper.DEFAULT_PACKAGE_NAME, existingPackages);
        Package newPackage = new PackageBuilder().withName(newPackageName).create();
        input.getValue().getPackages().add(newPackage);
        addBusinessObject(formPage, newPackage, false);
        viewer.getControl().getDisplay().asyncExec(() -> viewer.editElement(newPackage, 0));
    }

    private void addBusinessObject(AbstractBdmFormPage formPage) {
        viewer.applyEditorValue();
        Package pakage = selectionObservable.getValue() instanceof Package
                ? (Package) selectionObservable.getValue()
                : (Package) ((BusinessObject) selectionObservable.getValue()).eContainer();
        addBusinessObject(formPage, pakage, true);
    }

    private void addBusinessObject(AbstractBdmFormPage formPage, Package pakage, boolean edit) {
        List<String> existingNames = input.getValue().getPackages().stream()
                .map(Package::getBusinessObjects)
                .flatMap(Collection::stream)
                .map(BusinessObject::getSimpleName).collect(Collectors.toList());
        String newName = StringIncrementer.getNextIncrement(DEFAULT_BO_NAME, existingNames);

        BusinessObject newBusinessObject = new BusinessObjectBuilder()
                .withQualifiedName(String.format("%s.%s", pakage.getName(), newName))
                .withFields(new SimpleFieldBuilder()
                        .withName(AttributeEditionControl.DEFAULT_FIELD_NAME)
                        .withType(FieldType.STRING)
                        .withLength(255)
                        .create())
                .create();
        formPage.getConverter().createDefaultQueries(newBusinessObject).forEach(newBusinessObject.getDefaultQueries()::add);
        pakage.getBusinessObjects().add(newBusinessObject);
        formPage.getEditorContribution().refreshBusinessObjectList();
        viewer.getControl().getDisplay().asyncExec(() -> {
            if (edit) {
                viewer.editElement(newBusinessObject, 0);
            } else {
                viewer.expandToLevel(newBusinessObject, 1);
            }
        });
    }

    private void remove(AbstractBdmFormPage formPage) {
        if (selectionObservable.getValue() instanceof BusinessObject) {
            BusinessObject bo = (BusinessObject) selectionObservable.getValue();
            if (MessageDialog.openQuestion(section.getShell(), Messages.deleteBOConfirmTitle,
                    String.format(Messages.deleteBOConfirmMessage,
                            NamingUtils.getSimpleName(bo.getSimpleName())))) {
                Package pakage = (Package) bo.eContainer();
                pakage.getBusinessObjects().remove(selectionObservable.getValue());
                formPage.addToAccessControlRefactorQueue(new DiffElement(Event.REMOVE_BO, bo, null));
                if (pakage.getBusinessObjects().isEmpty()) {
                    input.getValue().getPackages().remove(pakage);
                    formPage.addToAccessControlRefactorQueue(new DiffElement(Event.REMOVE_PACKAGE, pakage, null));
                }
                formPage.refactorAccessControl();
            }
        } else {
            Package pakage = (Package) selectionObservable.getValue();
            if (MessageDialog.openQuestion(section.getShell(), Messages.deletePackageConfirmTitle,
                    String.format(Messages.deletePackageConfirm, pakage.getName()))) {
                input.getValue().getPackages().remove(pakage);
                formPage.addToAccessControlRefactorQueue(new DiffElement(Event.REMOVE_PACKAGE, pakage, null));
                formPage.refactorAccessControl();
            }
        }
        formPage.getEditorContribution().refreshBusinessObjectList();
    }

    private void updateDefaultQueries(BusinessObject businessObject, AbstractBdmFormPage formPage) {
        Stream<Query> newDefaultQueries = formPage.getConverter().createDefaultQueries(businessObject);
        businessObject.getDefaultQueries().clear();
        newDefaultQueries.forEach(businessObject.getDefaultQueries()::add);
    }

    public IObservableValue<BusinessObjectModel> observeInput() {
        return input;
    }

    public void layout() {
        section.layout();
    }

    public IObservableValue<Object> observeSelection() {
        return selectionObservable;
    }

    public void refreshViewer() {
        viewer.refresh();
    }

    public void expandAll() {
        viewer.expandAll();
    }

    public void showBusinessObjectSelection() {
        viewer.expandToLevel(businessObjectSelectionObservable.getValue(), 1);
        selectionObservable.setValue(businessObjectSelectionObservable.getValue());
    }

}
