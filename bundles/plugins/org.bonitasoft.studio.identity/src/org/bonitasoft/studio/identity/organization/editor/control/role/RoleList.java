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
package org.bonitasoft.studio.identity.organization.editor.control.role;

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
import org.bonitasoft.studio.identity.organization.editor.formpage.role.RoleFormPage;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationFactory;
import org.bonitasoft.studio.identity.organization.model.organization.Role;
import org.bonitasoft.studio.identity.organization.validator.RoleListValidator;
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
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
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
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Section;

public class RoleList {

    public static final String ROLE_LIST_VIEWER_ID = "roleListViewerid";
    public static final String ADD_ROLE_BUTTON_ID = "addRoleButtonId";
    private static final String REMOVE_BUTTON_ID = "deleteRoleButtonId";

    private RoleFormPage formPage;
    private DataBindingContext ctx;
    private Section section;
    private TableViewer viewer;
    private IObservableValue<Role> selectionObservable;
    private IObservableList<Role> input = new WritableList<>();
    private List<Role> rolesToFilter = new ArrayList<>();

    private ToolItem addItem;
    private ToolItem deleteItem;
    private IObservableList<Membership> membershipList;

    public RoleList(Composite parent, RoleFormPage formPage, DataBindingContext ctx,
            IObservableList<Membership> membershipList) {
        this.formPage = formPage;
        this.ctx = ctx;
        this.membershipList = membershipList;

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.organizationRoles);

        Composite roleListComposite = createRoleListComposite();

        section.setClient(roleListComposite);
    }

    private Composite createRoleListComposite() {
        Composite roleListComposite = formPage.getToolkit().createComposite(section);
        roleListComposite.setLayout(
                GridLayoutFactory.fillDefaults().spacing(LayoutConstants.getSpacing().x, 1).margins(5, 10).create());
        roleListComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(roleListComposite);
        createSearchField(roleListComposite);
        createViewer(roleListComposite);
        enableButtons();

        return roleListComposite;
    }

    private void enableButtons() {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteItem), new ComputedValueBuilder()
                .withSupplier(() -> selectionObservable.getValue() != null)
                .build());
    }

    protected void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        formPage.getToolkit().adapt(viewer.getTable());
        viewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ROLE_LIST_VIEWER_ID);
        viewer.addFilter(createSearchFilter());

        ColumnViewerToolTipSupport.enableFor(viewer);
        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, true));
        viewer.getTable().setLayout(layout);
        viewer.setUseHashlookup(true);
        createRoleColumn();
        viewer.setContentProvider(new ObservableListContentProvider<Role>());
        viewer.setInput(input);
        selectionObservable = ViewerProperties.singleSelection(Role.class).observe(viewer);
    }

    private void createRoleColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        Image rolesImage = Pics.getImage(PicsConstants.organization_role);
        column.setLabelProvider(new LabelProviderBuilder<Role>()
                .withTextProvider(Role::getDisplayName)
                .withImageProvider(grp -> rolesImage)
                .withStatusProvider(groupStatusProvider(new RoleListValidator(formPage.observeWorkingCopy())))
                .shouldRefreshAllLabels(viewer)
                .createColumnLabelProvider());
    }

    public Function<Role, IStatus> groupStatusProvider(IValidator<Role> validators) {
        return element -> Stream.of(validators)
                .map(v -> v.validate(element))
                .map(status -> status instanceof MultiStatus ? Arrays.asList(status.getChildren()) : Arrays.asList(status))
                .flatMap(Collection::stream)
                .collect(StatusCollectors.toMultiStatus());
    }

    private ViewerFilter createSearchFilter() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                return !rolesToFilter.contains(element);
            }
        };
    }

    private void createSearchField(Composite parent) {
        TextWidget searchWidget = createSearchWidget(parent);
        IObservableValue<String> searchObservableValue = searchWidget.observeText(SWT.Modify);
        searchObservableValue.addValueChangeListener(e -> {
            Display.getDefault().asyncExec(() -> {
                String search = searchObservableValue.getValue().toLowerCase();
                rolesToFilter.clear();
                input.stream()
                        .filter(role -> !matchesSearchValue(role, search))
                        .forEach(rolesToFilter::add);
                viewer.refresh(); // apply filter

                // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
                // TODO Hopefully this could be removed on the futur (current date: 08/01/2021)
                if (Objects.equals(Platform.getOS(), Platform.OS_MACOSX)) {
                    viewer.getControl().redraw();
                }
            });
        });
    }

    private boolean matchesSearchValue(Role role, String search) {
        return role.getDisplayName().toLowerCase().contains(search) || role.getName().toLowerCase().contains(search);
    }

    private TextWidget createSearchWidget(Composite parent) {
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
        composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).create());

        ToolBar toolBar = new ToolBar(composite, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        Image addImage = new DecorationOverlayIcon(Pics.getImage(PicsConstants.organization_role),
                Pics.getImageDescriptor(PicsConstants.add_decorator), IDecoration.BOTTOM_RIGHT).createImage();

        addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_ROLE_BUTTON_ID);
        addItem.setImage(addImage);
        addItem.setText(Messages.addNewRole);
        addItem.setToolTipText(Messages.addRoleTooltip);
        addItem.addListener(SWT.Selection, e -> addRole());

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_BUTTON_ID);
        deleteItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteItem.setText(Messages.delete);
        deleteItem.setToolTipText(Messages.deleteTooltip);
        deleteItem.addListener(SWT.Selection, e -> removeRole());
    }

    private void removeRole() {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteRoleTitle,
                String.format(Messages.deleteRoleMsg, selectionObservable.getValue().getDisplayName()))) {
            String oldName = selectionObservable.getValue().getName();
            input.remove(selectionObservable.getValue());
            formPage.refactorMemberships(membershipList, oldName, null);
        }
    }

    private void addRole() {
        Role role = OrganizationFactory.eINSTANCE.createRole();
        role.setName(generateRolename());
        role.setDisplayName(role.getName());
        input.add(role);
    }

    private String generateRolename() {
        Set<String> names = input.stream().map(Role::getName).collect(Collectors.toSet());
        return NamingUtils.generateNewName(names, Messages.defaultRoleName, 1);
    }

    public IObservableList<Role> observeInput() {
        return input;
    }

    public IObservableValue<Role> observeSelectedRole() {
        return selectionObservable;
    }

    public void refreshSelectedRole() {
        viewer.refresh(selectionObservable.getValue());
    }

    public void refreshRoleList() {
        viewer.refresh();
    }

}
