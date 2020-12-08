/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.control.group;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.group.GroupFormPage;
import org.bonitasoft.studio.identity.organization.editor.provider.content.GroupContentProvider;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.validator.GroupListValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class GroupList {

    public static final String GROUP_LIST_VIEWER_ID = "groupListViewerid";

    private DataBindingContext ctx;
    private Section section;
    private TreeViewer viewer;
    private IObservableValue<Group> selectionObservable;
    private IObservableList<Group> input = new WritableList<>();
    private GroupContentProvider contentProvider;

    public GroupList(Composite parent, GroupFormPage formPage, DataBindingContext ctx) {
        this.ctx = ctx;

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.organizationGroups);

        Composite groupListComposite = createGroupListComposite(formPage);

        section.setClient(groupListComposite);

    }

    private Composite createGroupListComposite(GroupFormPage formPage) {
        Composite groupListComposite = formPage.getToolkit().createComposite(section);
        groupListComposite.setLayout(
                GridLayoutFactory.fillDefaults().numColumns(2).spacing(LayoutConstants.getSpacing().x, 1).margins(5, 10)
                        .create());
        groupListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(groupListComposite, formPage);
        createSearchField(groupListComposite, formPage);
        createViewer(groupListComposite, formPage);
        //        enableButtons();
        return groupListComposite;
    }

    protected void createViewer(Composite parent, GroupFormPage formPage) {
        viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());
        formPage.getToolkit().adapt(viewer.getTree());
        viewer.getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, GROUP_LIST_VIEWER_ID);
        //        viewer.addFilter(createSearchFilter()); TODO
        viewer.addDoubleClickListener(e -> {
            viewer.setExpandedState(selectionObservable.getValue(),
                    !viewer.getExpandedState(selectionObservable.getValue()));
        });

        ColumnViewerToolTipSupport.enableFor(viewer);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTree().setLayout(layout);
        viewer.setUseHashlookup(true);
        createGroupColumn();
        contentProvider = new GroupContentProvider();
        viewer.setContentProvider(contentProvider);
        //        viewer.setComparator(new BusinessObjectViewerComparator()); // TODO
        viewer.setInput(input);
        //        addDNDSupport(formPage);
        selectionObservable = ViewerProperties.singleSelection(Group.class).observe(viewer);
        selectionObservable.addValueChangeListener(new IValueChangeListener() {

            Adapter groupAdapter = new AdapterImpl() {

                @Override
                public void notifyChanged(Notification notification) {
                    switch (notification.getFeatureID(Group.class)) {
                        case OrganizationPackage.GROUP__NAME:
                            handleGroupNameChange(notification);
                            break;
                        default:
                            break;
                    }
                    super.notifyChanged(notification);
                }
            };

            @Override
            public void handleValueChange(ValueChangeEvent event) {
                Group previousGroup = (Group) event.diff.getOldValue();
                if (previousGroup != null) {
                    previousGroup.eAdapters().remove(groupAdapter);
                }
                Group selectedGroup = (Group) event.diff.getNewValue();
                if (selectedGroup != null) {
                    selectedGroup.eAdapters().add(groupAdapter);
                }
            }
        });
    }

    private void createGroupColumn() {
        TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
        column.setLabelProvider(new LabelProviderBuilder<Group>()
                .withTextProvider(Group::getDisplayName)
                .withStatusProvider(groupStatusProvider(new GroupListValidator()))
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
    }

    public Function<Group, IStatus> groupStatusProvider(IValidator<Group> validators) {
        return element -> Stream.of(validators)
                .map(v -> v.validate(element))
                .map(status -> status instanceof MultiStatus ? Arrays.asList(status.getChildren()) : Arrays.asList(status))
                .flatMap(Collection::stream)
                .collect(StatusCollectors.toMultiStatus());
    }

    protected void createSearchField(Composite parent, GroupFormPage formPage) {
        TextWidget searchWidget = createSearchWidget(parent, formPage);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                // TODO
                //                String search = searchObservableValue.getValue().toLowerCase();
                //                List<Package> expandedElements = Arrays.asList(viewer.getExpandedElements()).stream()
                //                        .filter(Package.class::isInstance).map(Package.class::cast).collect(Collectors.toList());
                //                expandedPackages.removeIf(pkg -> !expandedElements.contains(pkg)
                //                        && pkg.getBusinessObjects().stream().anyMatch(bo -> !boToFilter.contains(bo)));
                //                expandedPackages.addAll(expandedElements);
                //                boToFilter.clear();
                //                input.getValue().getPackages().stream()
                //                        .map(Package::getBusinessObjects)
                //                        .flatMap(Collection::stream)
                //                        .filter(bo -> !bo.getSimpleName().toLowerCase().contains(search))
                //                        .forEach(boToFilter::add);
                //                viewer.refresh();
                //                expandedPackages.forEach(pkg -> viewer.expandToLevel(pkg, 1));
            });
        });
    }

    protected TextWidget createSearchWidget(Composite parent, GroupFormPage formPage) {
        return new SearchWidget.Builder()
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .withPlaceholder(Messages.search)
                .adapt(formPage.getToolkit())
                .createIn(parent);
    }

    private void createToolbar(Composite parent, GroupFormPage formPage) {
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

    // TODO
    protected void createAddDeleteItems(GroupFormPage formPage, ToolBar toolBar) {
        //        addPackageItem = new ToolItem(toolBar, SWT.PUSH);
        //        addPackageItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_PACKAGE_BUTTON_ID);
        //        addPackageItem.setImage(BusinessObjectPlugin.getImage("/icons/addPackageIcon.png"));
        //        addPackageItem.setToolTipText(Messages.createPackageTooltip);
        //        addPackageItem.addListener(SWT.Selection, e -> addPackage(formPage));
        //
        //        addBoItem = new ToolItem(toolBar, SWT.PUSH);
        //        addBoItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_BO_BUTTON_ID);
        //        addBoItem.setImage(BusinessObjectPlugin.getImage("/icons/addObjectIcon.png"));
        //        addBoItem.setToolTipText(Messages.createBoTooltip);
        //        addBoItem.addListener(SWT.Selection, e -> addBusinessObject(formPage));
        //
        //        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        //        deleteItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_BUTTON_ID);
        //        deleteItem.setImage(BusinessObjectPlugin.getImage("/icons/delete_icon.png"));
        //        deleteItem.setToolTipText(Messages.deleteTooltip);
        //        deleteItem.addListener(SWT.Selection, e -> remove(formPage));
    }

    public IObservableList observeInput() {
        return input;
    }

    public void expandAll() {
        viewer.expandAll();
    }

    public IObservableValue<Group> observeGroupSelected() {
        return selectionObservable;
    }

    public void handleGroupNameChange(Notification notification) {
        Group group = (Group) notification.getNotifier();
        Group oldGroup = EcoreUtil.copy(group);
        Object oldValue = notification.getOldValue();
        if (oldValue != null) {
            if (oldGroup != null) {
                oldGroup.setName(oldValue.toString());
                String oldPath = GroupContentProvider.getGroupPath(oldGroup) + GroupContentProvider.GROUP_SEPARATOR;
                String newPath = GroupContentProvider.getGroupPath(group) + GroupContentProvider.GROUP_SEPARATOR;

                if (contentProvider.hasChildren(oldGroup)) {
                    for (Object child : contentProvider.getChildren(oldGroup)) {
                        Group childGroup = (Group) child;
                        updateParentPath(childGroup, oldPath, newPath);
                        String parent = childGroup.getParentPath() + GroupContentProvider.GROUP_SEPARATOR;
                        String path = parent.replace(oldPath, newPath);
                        if (path.endsWith(GroupContentProvider.GROUP_SEPARATOR)) {
                            path = path.substring(0, path.length() - 1);
                        }
                        if (path.equals(GroupContentProvider.GROUP_SEPARATOR)) {
                            childGroup.setParentPath(null);
                        } else {
                            childGroup.setParentPath(path);
                        }
                    }
                }
                //                for (final Membership m : membershipList) {
                //                    if (withGroupParentPath(oldPath).apply(m)) {
                //                        String groupParentPath = m.getGroupParentPath();
                //                        if (groupParentPath != null && !groupParentPath.endsWith("/")) {
                //                            groupParentPath = groupParentPath + "/";
                //                        }
                //                        final String replace = groupParentPath.replace(oldPath, newPath);
                //                        m.setGroupParentPath(replace.substring(0, replace.length() - 1));
                //                    }
                //                }
                //                for (final Membership m : membershipList) {
                //                    if (withGroupName(oldValue.toString()).apply(m)) {
                //                        m.setGroupName(group.getName());
                //                    }
                //                }
            }
            viewer.refresh();
        }
    }

    private void updateParentPath(Group group, String pathToReplace, String newPath) {
        if (contentProvider.hasChildren(group)) {
            for (Object child : contentProvider.getChildren(group)) {
                Group childGroup = (Group) child;
                updateParentPath(childGroup, pathToReplace, newPath);
                String path = childGroup.getParentPath().replace(pathToReplace, newPath);
                if (path.endsWith(GroupContentProvider.GROUP_SEPARATOR)) {
                    path = path.substring(0, path.length() - 1);
                }
                if (path != null && path.equals(GroupContentProvider.GROUP_SEPARATOR)) {
                    childGroup.setParentPath(null);
                } else {
                    childGroup.setParentPath(path);
                }
            }
        }
    }

}
