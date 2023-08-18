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
package org.bonitasoft.studio.identity.organization.editor.control.overview;

import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.control.group.GroupListReadOnly;
import org.bonitasoft.studio.identity.organization.editor.control.role.RoleListReadOnly;
import org.bonitasoft.studio.identity.organization.editor.control.user.UserListReadOnly;
import org.bonitasoft.studio.identity.organization.editor.formpage.overview.OverviewFormPage;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

public class OverviewEditionControl {

	private OverviewFormPage formPage;
	private DataBindingContext ctx;
	private GroupListReadOnly groupList;
	private RoleListReadOnly roleList;
	private UserListReadOnly userList;

	public OverviewEditionControl(Composite parent, OverviewFormPage formPage, DataBindingContext ctx) {
		this.formPage = formPage;
		this.ctx = ctx;

		Section overviewSection = formPage.getToolkit().createSection(parent, ExpandableComposite.EXPANDED);
		overviewSection.setLayout(GridLayoutFactory.fillDefaults().create());
		overviewSection.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		overviewSection.setText(Messages.displayOrganizationOverviewPageTitle);

		Composite mainComposite = formPage.getToolkit().createComposite(overviewSection);
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		mainComposite.setLayout(
				GridLayoutFactory.fillDefaults().margins(10, 10).spacing(LayoutConstants.getSpacing().x, 10).create());

		Label separator = new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

		createOrganizationContentOverview(mainComposite);

		overviewSection.setClient(mainComposite);
	}

	private void createOrganizationContentOverview(Composite parent) {
		Composite contentComposite = formPage.getToolkit().createComposite(parent);
		contentComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		contentComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).numColumns(3).equalWidth(true)
				.spacing(40, 10).create());

		groupList = new GroupListReadOnly(contentComposite, formPage, ctx);
		groupList.expandAll();
		roleList = new RoleListReadOnly(contentComposite, formPage, ctx);
		userList = new UserListReadOnly(contentComposite, formPage, ctx);
	}


	public void refreshList() {
		groupList.refreshGroupList();
		roleList.refreshRoleList();
		userList.refreshUserList();
	}

	public void refreshGroupList() {
		groupList.refreshGroupList();
	}

	public void refreshUserList() {
		userList.refreshUserList();
	}

	public void refreshRoleList() {
		roleList.refreshRoleList();
	}

}
