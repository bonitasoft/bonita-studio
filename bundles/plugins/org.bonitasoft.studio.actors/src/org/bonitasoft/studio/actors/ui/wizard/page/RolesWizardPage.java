/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.ui.wizard.page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.validator.DisplayNameValidator;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public class RolesWizardPage extends AbstractOrganizationWizardPage {



	private final List<Membership> roleMemberShips = new ArrayList<Membership>();
	private IViewerObservableValue roleSingleSelectionObservable;

	public RolesWizardPage() {
		super(RolesWizardPage.class.getName());
		setTitle(Messages.displayRolesPageTitle) ;
		setDescription(Messages.displayRolesPageDesc) ;
	}

	@Override
	protected void configureViewer(StructuredViewer viewer) {

		TableViewer roleTableViewer = (TableViewer) viewer;
		Table table = roleTableViewer.getTable();

		roleSingleSelectionObservable = ViewersObservables.observeSingleSelection(getViewer());
		roleSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				Role selectedRole = (Role) event.diff.getNewValue();
				boolean isSelectedRole = selectedRole != null;
				setControlEnabled(getInfoGroup(), isSelectedRole) ;
			}
		});

		

		addNameColumn(roleTableViewer);

		addDisplayNameColumn(roleTableViewer);

		addDescriptionColumn(roleTableViewer);

		addTableColumLayout(table);

		if(roleList!= null && getViewer() != null){
			getViewer().setInput(roleList) ;
		}
		
		
	}

	private void addDescriptionColumn(TableViewer tViewer) {
		TableViewerColumn column = new TableViewerColumn(tViewer, SWT.FILL) ;
		column.getColumn().setText(Messages.description);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Role)element).getDescription();
			}
		});

		column.getColumn().setWidth(90);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);
	}

	private void addDisplayNameColumn(TableViewer tViewer) {
		TableViewerColumn column = new TableViewerColumn(tViewer, SWT.FILL) ;
		column.getColumn().setText(Messages.displayName);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Role)element).getDisplayName();
			}
		});

		column.getColumn().setWidth(90);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);
	}

	private void addNameColumn(TableViewer tViewer) {
		TableViewerColumn column = new TableViewerColumn(tViewer, SWT.FILL) ;
		TableColumn nameColumn = column.getColumn() ;
		column.getColumn().setText(Messages.roleName);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((Role)element).getName();
			}
		});

		column.getColumn().setWidth(90);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);

		TableColumnSorter sorter = new TableColumnSorter(tViewer) ;
		sorter.setColumn(nameColumn) ;
	}

	@Override
	public void setOrganization(Organization organization) {
		super.setOrganization(organization);
		if(organization != null && getViewer() != null){
			getViewer().setInput(roleList) ;
		}
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
	}



	@Override
	protected void configureInfoGroup(Group group) {

		group.setText(Messages.details) ;
		group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 5).spacing(10, 5).create()) ;

		createNameField(group);

		createDisplayNameField(group);

		createDescriptionField(group);
		
		setControlEnabled(getInfoGroup(), false);
	}

	private void createDescriptionField(Group group) {
		Label descriptionLabel = new Label(group, SWT.NONE) ;
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.FILL).create()) ;
		descriptionLabel.setText(Messages.description) ;

		Text roleDescriptionText = new Text(group, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL) ;
		roleDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 80).create()) ;
		roleDescriptionText.setMessage(Messages.descriptionHint) ;
		roleDescriptionText.setTextLimit(255);

		IObservableValue roleDescriptionValue = EMFObservables.observeDetailValue(Realm.getDefault(), roleSingleSelectionObservable, OrganizationPackage.Literals.ROLE__DESCRIPTION);
		context.bindValue(SWTObservables.observeText(roleDescriptionText, SWT.Modify), roleDescriptionValue);
		roleDescriptionValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleRoleDescriptionChange(event);
			}
		});
	}

	private void createDisplayNameField(Group group) {
		Label displayNameLabel = new Label(group, SWT.NONE) ;
		displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		displayNameLabel.setText(Messages.displayName) ;

		Text displayNamedText = new Text(group, SWT.BORDER) ;
		displayNamedText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;


		UpdateValueStrategy roleDisplayNameStrategy = new UpdateValueStrategy();
		roleDisplayNameStrategy.setAfterGetValidator(new DisplayNameValidator());

		IObservableValue roleDisplayNameValue = EMFObservables.observeDetailValue(Realm.getDefault(), roleSingleSelectionObservable, OrganizationPackage.Literals.ROLE__DISPLAY_NAME);
		Binding binding =context.bindValue(SWTObservables.observeText(displayNamedText, SWT.Modify), roleDisplayNameValue,roleDisplayNameStrategy,null);
		ControlDecorationSupport.create(binding, SWT.LEFT);
		
		roleDisplayNameValue.addValueChangeListener(new IValueChangeListener() {
			
			@Override
			public void handleValueChange(ValueChangeEvent arg0) {
				handleRoleDisplayNameChange(arg0);
				
			}
		});
	}

	private void createNameField(Group group) {
		Label roleName = new Label(group, SWT.NONE) ;
		roleName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		roleName.setText(Messages.name+" *") ;

		Text roleNameText = new Text(group, SWT.BORDER) ;
		roleNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(130, SWT.DEFAULT).create()) ;

		UpdateValueStrategy roleNameStrategy = new UpdateValueStrategy();
		roleNameStrategy.setAfterGetValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if(value.toString().isEmpty()){
					return ValidationStatus.error(Messages.nameIsEmpty) ;
				}
				if(value.toString().length()>NAME_SIZE){
					return ValidationStatus.error(Messages.nameLimitSize) ;
				}

				for(Role role : roleList){
					if(!role.equals(roleSingleSelectionObservable.getValue())){
						if(role.getName().equals(value)){
							return ValidationStatus.error(Messages.roleNameAlreadyExists) ;
						}
					}
				}
				return Status.OK_STATUS;
			}
		});

		IObservableValue roleNameValue = EMFObservables.observeDetailValue(Realm.getDefault(), roleSingleSelectionObservable, OrganizationPackage.Literals.ROLE__NAME);
		Binding binding = context.bindValue(SWTObservables.observeText(roleNameText, SWT.Modify), roleNameValue, roleNameStrategy,null);
		ControlDecorationSupport.create(binding, SWT.LEFT, group, new ControlDecorationUpdater(){
			@Override
			protected void update(ControlDecoration decoration, IStatus status) {
				if(roleSingleSelectionObservable.getValue() !=null){
					super.update(decoration, status);
				}
			}
		});
		roleNameValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleRoleNameChange(event);
			}
		});
	}


	private void handleRoleNameChange(ValueChangeEvent event) {
		Role role = (Role) roleSingleSelectionObservable.getValue();
		Role oldRole = EcoreUtil.copy(role);
		Object oldValue = event.diff.getOldValue();
		if(oldValue!=null){
			oldRole.setName(oldValue.toString());
			for(Membership m : membershipList){
				if(oldRole.getName() != null && oldRole.getName().equals(m.getRoleName())){
					roleMemberShips.add(m) ;
				}
			}

			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(role) ;
			}

			String newRoleName = role.getName();
			for(Membership m : roleMemberShips){
				m.setRoleName(newRoleName) ;
			}
		}
	}

	private void handleRoleDescriptionChange(ValueChangeEvent event) {
		Role role = (Role) roleSingleSelectionObservable.getValue();
		Role oldRole = EcoreUtil.copy(role);
		Object oldValue = event.diff.getOldValue();
		if(oldValue!=null){
			oldRole.setName(oldValue.toString());

			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(role) ;
			}
		}
	}
	
	private void handleRoleDisplayNameChange(ValueChangeEvent event) {
		Role role = (Role) roleSingleSelectionObservable.getValue();
		Role oldRole = EcoreUtil.copy(role);
		Object oldValue = event.diff.getOldValue();
		if(oldValue!=null){
			oldRole.setDisplayName(oldValue.toString());

			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(role) ;
			}
		}
	}

	@Override
	protected void addButtonSelected() {
		Role role = OrganizationFactory.eINSTANCE.createRole() ;
		role.setName(generateRolename()) ;
		role.setDisplayName(role.getName()) ;
		roleList.add(role) ;
		getViewer().setInput(roleList) ;
		getViewer().setSelection(new StructuredSelection(role)) ;
	}

	private String generateRolename() {
		Set<String> names = new HashSet<String>() ;
		for(Role r : roleList){
			names.add(r.getName()) ;
		}

		return NamingUtils.generateNewName(names, Messages.defaultRoleName);
	}

	@Override
	protected void removeButtonSelected() {
		for(Object sel :  ((IStructuredSelection) getViewer().getSelection()).toList()){
			if(sel instanceof Role){
				roleList.remove(sel) ;
			}
		}
		getViewer().setInput(roleList) ;
		selectionChanged(new SelectionChangedEvent(getViewer(),new StructuredSelection())) ;
	}

	@Override
	protected boolean viewerSelect(Object element, String searchQuery) {
		if(searchQuery == null || searchQuery.isEmpty()
				|| (((Role)element).getName() != null && ((Role)element).getName().toLowerCase().contains(searchQuery.toLowerCase()))
				|| (((Role)element).getDisplayName() != null && ((Role)element).getDisplayName().toLowerCase().contains(searchQuery.toLowerCase()))
				|| (((Role)element).getDescription() != null && ((Role)element).getDescription().toLowerCase().contains(searchQuery.toLowerCase()))){
			return true ;
		}
		return false ;
	}


}
