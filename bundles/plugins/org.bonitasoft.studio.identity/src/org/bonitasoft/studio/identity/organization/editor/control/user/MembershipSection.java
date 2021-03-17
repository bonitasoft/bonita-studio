/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.identity.organization.editor.control.user;

import java.util.Objects;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.editingsupport.MembershipGroupEditingSupport;
import org.bonitasoft.studio.identity.organization.editor.editingsupport.MembershipRoleEditingSupport;
import org.bonitasoft.studio.identity.organization.editor.formpage.user.UserFormPage;
import org.bonitasoft.studio.identity.organization.editor.provider.content.GroupContentProvider;
import org.bonitasoft.studio.identity.organization.model.organization.Membership;
import org.bonitasoft.studio.identity.organization.model.organization.OrganizationPackage;
import org.bonitasoft.studio.identity.organization.model.organization.User;
import org.bonitasoft.studio.identity.organization.validator.MembershipGroupValidator;
import org.bonitasoft.studio.identity.organization.validator.MembershipRoleValidator;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.ui.databinding.ComputedValueBuilder;
import org.bonitasoft.studio.ui.viewer.LabelProviderBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Platform;
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

public class MembershipSection {

    public static final String ADD_MEMBERSHIP_BUTTON_ID = "addMembership";
    public static final String REMOVE_MEMBERSHIP_BUTTON_ID = "removeMembership";
    public static final String MEMBERSHIP_LIST_VIEWER_ID = "membershipViewer";

    private UserFormPage formPage;
    private Section section;
    private ToolItem deleteMembershipItem;
    private TableViewer viewer;
    private IObservableValue<User> selectedUserObservable;
    private IObservableList<Membership> input;
    private IObservableValue<Membership> selectedMembershipObservable;
    private DataBindingContext ctx;

    public MembershipSection(Composite parent, UserFormPage formPage, IObservableValue<User> selectedUserObservable,
            DataBindingContext ctx) {
        this.formPage = formPage;
        this.selectedUserObservable = selectedUserObservable;
        this.ctx = ctx;

        section = formPage.getToolkit().createSection(parent, Section.EXPANDED);
        section.setLayout(GridLayoutFactory.fillDefaults().create());
        section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        section.setText(Messages.membership);

        input = formPage.observeMemberships();

        section.setClient(createMembsershipComposite());
    }

    private Composite createMembsershipComposite() {
        Composite membershipComposite = formPage.getToolkit().createComposite(section);
        membershipComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 10, 0)
                .spacing(LayoutConstants.getSpacing().x, 1).create());
        membershipComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        createToolbar(membershipComposite);
        createViewer(membershipComposite);
        enableButtons();

        return membershipComposite;
    }

    private void enableButtons() {
        ctx.bindValue(WidgetProperties.enabled().observe(deleteMembershipItem), new ComputedValueBuilder()
                .withSupplier(() -> selectedMembershipObservable.getValue() != null)
                .build());
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE);
        viewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        viewer.getTable().setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, MEMBERSHIP_LIST_VIEWER_ID);
        formPage.getToolkit().adapt(viewer.getTable());
        ColumnViewerToolTipSupport.enableFor(viewer);
        viewer.setUseHashlookup(true);
        viewer.getTable().setHeaderVisible(true);
        selectedMembershipObservable = ViewerProperties.singleSelection(Membership.class).observe(viewer);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(1, 200, true));
        layout.addColumnData(new ColumnWeightData(1, 200, true));
        viewer.getTable().setLayout(layout);
        viewer.setFilters(new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof Membership && selectedUserObservable.getValue() != null) {
                    return Objects.equals(((Membership) element).getUserName(),
                            selectedUserObservable.getValue().getUserName());
                }
                return false;
            }
        });

        createGroupColumn();
        createRoleColumn();

        viewer.setContentProvider(new ObservableListContentProvider());
        viewer.setInput(input);
        selectedUserObservable.addValueChangeListener(e -> Display.getDefault().asyncExec(() -> viewer.refresh()));

        // Necessary since the MacOS Big Sur update -> Seems that table with StyledCellLabelProvider aren't redraw automatically 
        // TODO Hopefully this could be removed on the futur (current date: 19/11/2020)
        if (Objects.equals(Platform.OS_MACOSX, Platform.getOS())) {
            selectedUserObservable
                    .addValueChangeListener(e -> Display.getDefault().asyncExec(() -> viewer.getTable().redraw()));
        }
    }

    private void createRoleColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.role);
        MembershipRoleValidator validator = new MembershipRoleValidator(formPage.observeWorkingCopy());
        column.setLabelProvider(new LabelProviderBuilder<Membership>()
                .withTextProvider(Membership::getRoleName)
                .withStatusProvider(validator::validate)
                .createColumnLabelProvider());

        IObservableValue<String> selectedMembershipRoleNameObservable = EMFObservables.observeDetailValue(
                ctx.getValidationRealm(), selectedMembershipObservable, OrganizationPackage.Literals.MEMBERSHIP__ROLE_NAME);
        column.setEditingSupport(new MembershipRoleEditingSupport(viewer,
                selectedMembershipRoleNameObservable, formPage, ctx));
    }

    private void createGroupColumn() {
        TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
        column.getColumn().setText(Messages.groupName);
        MembershipGroupValidator validator = new MembershipGroupValidator(formPage.observeWorkingCopy());
        column.setLabelProvider(new LabelProviderBuilder<Membership>()
                .withTextProvider(memberShip -> memberShip.getGroupName() == null
                        ? ""
                        : GroupContentProvider.getGroupPath(memberShip.getGroupName(), memberShip.getGroupParentPath()))
                .withStatusProvider(validator::validate)
                .createColumnLabelProvider());

        IObservableValue<String> selectedMembershipGroupNameObservable = EMFObservables.observeDetailValue(
                ctx.getValidationRealm(), selectedMembershipObservable, OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME);
        IObservableValue<String> selectedMembershipGroupParentPathObservable = EMFObservables.observeDetailValue(
                ctx.getValidationRealm(), selectedMembershipObservable,
                OrganizationPackage.Literals.MEMBERSHIP__GROUP_PARENT_PATH);
        column.setEditingSupport(new MembershipGroupEditingSupport(viewer, selectedMembershipGroupNameObservable,
                selectedMembershipGroupParentPathObservable, formPage, ctx));
    }

    private void createToolbar(Composite parent) {
        ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.RIGHT | SWT.NO_FOCUS | SWT.FLAT);
        formPage.getToolkit().adapt(toolBar);

        ToolItem addMembershipItem = new ToolItem(toolBar, SWT.PUSH);
        addMembershipItem.setImage(Pics.getImage(PicsConstants.add_simple));
        addMembershipItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, ADD_MEMBERSHIP_BUTTON_ID);
        addMembershipItem.setText(Messages.add);
        addMembershipItem.setToolTipText(Messages.addMembershipTooltip);
        addMembershipItem.addListener(SWT.Selection, e -> addMembership());

        deleteMembershipItem = new ToolItem(toolBar, SWT.PUSH);
        deleteMembershipItem.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, REMOVE_MEMBERSHIP_BUTTON_ID);
        deleteMembershipItem.setImage(Pics.getImage(PicsConstants.delete));
        deleteMembershipItem.setText(Messages.delete);
        deleteMembershipItem.setToolTipText(Messages.deleteMembershipTooltip);
        deleteMembershipItem.addListener(SWT.Selection, e -> removeSelectedMembership());
    }

    private void removeSelectedMembership() {
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteMembershipTitle,
                Messages.deleteMembershipMsg)) {
            input.remove(selectedMembershipObservable.getValue());
            formPage.refreshSelectedUser();
        }
    }

    private void addMembership() {
        input.add(formPage.createDefaultMembership(selectedUserObservable.getValue().getUserName()));
        formPage.refreshSelectedUser();
    }

    public void refreshTable() {
        viewer.refresh();
    }

}
