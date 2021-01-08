/**
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * Copyright (C) 2020 Bonitasoft S.A.
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.group.GroupFormPage;
import org.bonitasoft.studio.identity.organization.editor.provider.content.GroupContentProvider;
import org.bonitasoft.studio.identity.organization.model.organization.Group;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.validator.GroupListValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.bonitasoft.studio.ui.widget.SearchWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.ComputedValue;
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
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.typed.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.widgets.Section;

public class GroupList {

    public static final String GROUP_LIST_VIEWER_ID = "groupListViewerid";
    public static final String ADD_GROUP_BUTTON_ID = "addGroupButtonId";
    private static final String ADD_SUB_GROUP_BUTTON_ID = "addSubGroupButtonId";
    private static final String REMOVE_BUTTON_ID = "deleteGroupButtonId";

    private GroupFormPage formPage;
    private DataBindingContext ctx;
    private Section section;
    private TreeViewer viewer;
    private IObservableValue<Group> selectionObservable;
    private IObservableList<Group> input = new WritableList<>();
    private GroupContentProvider contentProvider;
    private List<Group> groupsToFilter = new ArrayList<>();
    private Cursor cursorHand;
    private Cursor cursorArrow;

    private ToolItem addGroupItem;
    private ToolItem addSubGroupItem;
    private ToolItem deleteItem;

    public GroupList(Composite parent, GroupFormPage formPage, DataBindingContext ctx) {
        this.formPage = formPage;
        this.ctx = ctx;
        this.cursorHand = new Cursor(parent.getDisplay(), SWT.CURSOR_HAND);
        this.cursorArrow = new Cursor(parent.getDisplay(), SWT.CURSOR_ARROW);

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.organizationGroups);

        Composite groupListComposite = createGroupListComposite();

        section.setClient(groupListComposite);
    }

    private Composite createGroupListComposite() {
        Composite groupListComposite = formPage.getToolkit().createComposite(section);
        groupListComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).margins(5, 10).create());
        groupListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(groupListComposite);
        createSearchField(groupListComposite);
        createViewer(groupListComposite);
        enableButtons();

        return groupListComposite;
    }

    private void enableButtons() {
        ComputedValue<Boolean> selectionNotNull = new ComputedValueBuilder<Boolean>()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build();
        ctx.bindValue(WidgetProperties.enabled().observe(addSubGroupItem), selectionNotNull);
        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), selectionNotNull);
    }

    protected void createViewer(Composite parent) {
        viewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        formPage.getToolkit().adapt(viewer.getTree());
        viewer.getTree().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, GROUP_LIST_VIEWER_ID);
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
        createGroupColumn();
        contentProvider = new GroupContentProvider(input);
        viewer.setContentProvider(contentProvider);
        viewer.setInput(input);
        addDNDSupport();
        viewer.getTree().addMouseMoveListener(this::updateCursor);
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

    private void addDNDSupport() {
        viewer.addDropSupport(DND.DROP_MOVE | DND.DROP_MOVE | DND.FEEDBACK_INSERT_BEFORE | DND.DROP_DEFAULT,
                new Transfer[] { GroupTransfer.getInstance() },
                new DropTargetAdapter() {

                    @Override
                    public void dragOver(DropTargetEvent event) {
                        if (event.item != null) {
                            TreeItem treeItem = (TreeItem) event.item;
                            if (treeItem.getItems().length > 0) {
                                event.feedback = DND.FEEDBACK_INSERT_AFTER;
                            }
                        }
                    }

                    @Override
                    public void drop(DropTargetEvent event) {
                        dragLeave(event);
                        if (event.item != null) {
                            TreeItem parentItem = (TreeItem) event.item;
                            if (parentItem.getItems().length == 0 && parentItem.getParentItem() != null) {
                                parentItem = parentItem.getParentItem();
                            }
                            Group grp = (Group) event.data;
                            Group parentGroup = (Group) parentItem.getData();
                            updateParentPath(grp, parentGroup);
                            viewer.refresh();
                        } else {
                            // TODO root group
                        }
                    }

                });

        viewer.addDragSupport(DND.DROP_MOVE,
                new Transfer[] { GroupTransfer.getInstance() },
                new DragSourceAdapter() {

                    @Override
                    public void dragStart(DragSourceEvent event) {
                        if (selectionObservable.getValue() instanceof Group) {
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

    private void updateCursor(MouseEvent e) {
        if (viewer.getCell(new Point(e.x, e.y)) != null) {
            viewer.getTree().setCursor(cursorHand);
        } else {
            viewer.getTree().setCursor(cursorArrow);
        }
    }

    private void updateParentPath(Group group, Group newParent) {
        List<Group> children = Arrays.asList(contentProvider.getChildren(group))
                .stream()
                .map(Group.class::cast)
                .collect(Collectors.toList());
        group.setParentPath(GroupContentProvider.getGroupPath(newParent));
        children.forEach(child -> updateParentPath(child, group));
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return !groupsToFilter.contains(element);
            }
        };
    }

    private void createGroupColumn() {
        TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.NONE);
        Image groupsImage = Pics.getImage(PicsConstants.organization_group);
        column.setLabelProvider(new LabelProviderBuilder<Group>()
                .withTextProvider(Group::getDisplayName)
                .withImageProvider(grp -> groupsImage)
                .withStatusProvider(groupStatusProvider(new GroupListValidator(formPage.observeWorkingCopy())))
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

    protected void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent, formPage);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                groupsToFilter.clear();
                getRootGroups().stream()
                        .filter(grp -> !matchesSearchValue(grp, search))
                        .forEach(groupsToFilter::add);
                viewer.refresh(); // apply filter
                viewer.expandAll();
            });
        });
    }

    private boolean matchesSearchValue(Group group, String search) {
        if (group.getDisplayName().toLowerCase().contains(search) || group.getName().toLowerCase().contains(search)) {
            return true;
        }
        long count = Arrays.asList(contentProvider.getChildren(group))
                .stream()
                .map(Group.class::cast)
                .filter(grp -> matchesSearchValue(grp, search))
                .count();
        if (count == 0) {
            groupsToFilter.add(group);
            return false;
        }
        return true;
    }

    private List<Group> getRootGroups() {
        return input.stream()
                .filter(grp -> grp.getParentPath() == null)
                .collect(Collectors.toList());
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

    private void createToolbar(Composite parent) {
        Composite composite = formPage.getToolkit().createComposite(parent);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).create());

        ToolBar toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);
        createAddDeleteItems(toolBar);
        new ToolItem(toolBar, SWT.SEPARATOR);
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

    protected void createAddDeleteItems(ToolBar toolBar) {
        Image addImage = new DecorationOverlayIcon(Pics.getImage(PicsConstants.organization_group),
                Pics.getImageDescriptor(PicsConstants.add_decorator), IDecoration.BOTTOM_RIGHT).createImage();
        addGroupItem = new ToolItem(toolBar, SWT.PUSH);
        addGroupItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_GROUP_BUTTON_ID);
        addGroupItem.setImage(addImage);
        addGroupItem.setText(Messages.addNewGroup);
        addGroupItem.setToolTipText(Messages.addGroupTooltip);
        addGroupItem.addListener(SWT.Selection, e -> addGroup(null));

        Image addSubGroupImage = new DecorationOverlayIcon(Pics.getImage(PicsConstants.organization_group),
                Pics.getImageDescriptor(PicsConstants.add_subelement_decorator), IDecoration.BOTTOM_RIGHT).createImage();
        addSubGroupItem = new ToolItem(toolBar, SWT.PUSH);
        addSubGroupItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_SUB_GROUP_BUTTON_ID);
        addSubGroupItem.setImage(addSubGroupImage);
        addSubGroupItem.setText(Messages.addSubGroup);
        addSubGroupItem.setToolTipText(Messages.addSubGroupTooltip);
        addSubGroupItem.addListener(SWT.Selection, e -> addSubGroup());

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_BUTTON_ID);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setText(Messages.delete);
        deleteItem.setToolTipText(Messages.deleteTooltip);
        deleteItem.addListener(SWT.Selection, e -> removeGroup());
    }

    private void removeGroup() {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteGroupTitle,
                String.format(Messages.deleteGroupMsg, selectionObservable.getValue().getDisplayName()))) {
            Group selectedGroup = selectionObservable.getValue();
            removeChildren(selectedGroup);
            formPage.observeWorkingCopy().getValue().getGroups().getGroup().remove(selectedGroup);
            viewer.refresh();

            /**
             * TODO https://bugs.eclipse.org/bugs/show_bug.cgi?id=567132 -> fixed in 4.18 (2020-12)
             */
            if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
                viewer.getControl().redraw();
            }
        }
    }

    private void removeChildren(Group group) {
        for (Object child : contentProvider.getChildren(group)) {
            removeChildren((Group) child);
            formPage.observeWorkingCopy().getValue().getGroups().getGroup().remove(child);
        }
    }

    private void addSubGroup() {
        String parentName = selectionObservable.getValue().getName();
        String parentPath = selectionObservable.getValue().getParentPath();
        String path = parentPath == null
                ? String.format("%s%s", GroupContentProvider.GROUP_SEPARATOR, parentName)
                : String.format("%s%s%s", parentPath, GroupContentProvider.GROUP_SEPARATOR, parentName);
        addGroup(path);
    }

    private void addGroup(String path) {
        Group newGroup = OrganizationFactory.eINSTANCE.createGroup();
        String newName = generateNewGroupName();
        newGroup.setName(newName);
        newGroup.setDisplayName(newName);
        newGroup.setParentPath(path);
        input.add(newGroup); // TODO faire un observable de la liste de groupes
        viewer.refresh();
        viewer.expandAll();
        selectionObservable.setValue(newGroup);
        /**
         * TODO https://bugs.eclipse.org/bugs/show_bug.cgi?id=567132 -> fixed in 4.18 (2020-12)
         */
        if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
            viewer.getControl().redraw();
        }
    }

    private String generateNewGroupName() {
        Set<String> existingNames = formPage.observeWorkingCopy().getValue().getGroups().getGroup()
                .stream()
                .map(Group::getName)
                .collect(Collectors.toSet());
        return NamingUtils.generateNewName(existingNames, Messages.defaultGroupName, 1);
    }

    public IObservableList<Group> observeInput() {
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
        if (oldValue != null && oldGroup != null) {
            oldGroup.setName(oldValue.toString());
            Arrays.asList(contentProvider.getChildren(oldGroup))
                    .stream()
                    .map(Group.class::cast)
                    .forEach(child -> updateParentPath(child, group));
        }
        //                //                for (final Membership m : membershipList) {
        //                //                    if (withGroupParentPath(oldPath).apply(m)) {
        //                //                        String groupParentPath = m.getGroupParentPath();
        //                //                        if (groupParentPath != null && !groupParentPath.endsWith("/")) {
        //                //                            groupParentPath = groupParentPath + "/";
        //                //                        }
        //                //                        final String replace = groupParentPath.replace(oldPath, newPath);
        //                //                        m.setGroupParentPath(replace.substring(0, replace.length() - 1));
        //                //                    }
        //                //                }
        //                //                for (final Membership m : membershipList) {
        //                //                    if (withGroupName(oldValue.toString()).apply(m)) {
        //                //                        m.setGroupName(group.getName());
        //                //                    }
        //                //                }
        //            }
        //        }
    }

    public void refreshSelectedGroup() {
        viewer.refresh(selectionObservable.getValue());
    }

    public void dispose() {
        cursorHand.dispose();
        cursorArrow.dispose();
    }

}
