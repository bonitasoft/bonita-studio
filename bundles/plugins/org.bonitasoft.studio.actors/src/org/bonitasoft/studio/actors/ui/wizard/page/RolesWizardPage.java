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
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.databinding.WrappingValidator;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.jface.databinding.WizardPageSupportWithoutMessages;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
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
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public class RolesWizardPage extends AbstractOrganizationWizardPage {



    private final List<Membership> roleMemberShips = new ArrayList<Membership>();
    private Text roleNameText;
    private Text displayNamedText;
    private Text roleDescriptionText;
    private WrappingValidator roleNameValidator;

    public RolesWizardPage() {
        super(RolesWizardPage.class.getName());
        setTitle(Messages.displayRolesPageTitle) ;
        setDescription(Messages.displayRolesPageDesc) ;
    }

    @Override
    protected void configureViewer(StructuredViewer viewer) {
        TableViewerColumn column = new TableViewerColumn((TableViewer) viewer, SWT.FILL) ;
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


        column = new TableViewerColumn((TableViewer) viewer, SWT.FILL) ;
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

        column = new TableViewerColumn((TableViewer) viewer, SWT.FILL) ;
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


        TableColumnSorter sorter = new TableColumnSorter((TableViewer) viewer) ;
        sorter.setColumn(nameColumn) ;


        if(roleList!= null && getViewer() != null){
            getViewer().setInput(roleList) ;
        }
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
        refreshBinding((Role) ((IStructuredSelection) event.getSelection()).getFirstElement()) ;
    }

    private void refreshBinding(final Role selectedRole) {
        if(context != null){
            context.dispose() ;
        }
        if(pageSupport != null){
            pageSupport.dispose() ;
        }
        context = new EMFDataBindingContext() ;

        roleMemberShips.clear() ;
        if(selectedRole != null){
            setControlEnabled(getInfoGroup(), true) ;

            for(Membership m : membershipList){
                if(selectedRole.getName() != null && selectedRole.getName().equals(m.getRoleName())){
                    roleMemberShips.add(m) ;
                }
            }



            UpdateValueStrategy strategy = new UpdateValueStrategy() ;
            roleNameValidator.setValidator(new IValidator() {

                @Override
                public IStatus validate(Object value) {
                    if(value.toString().isEmpty()){
                        return ValidationStatus.error(Messages.nameIsEmpty) ;
                    }

                    for(Role r : roleList){
                        if(!r.equals(selectedRole)){
                            if(r.getName().equals(value)){
                                return ValidationStatus.error(Messages.roleNameAlreadyExists) ;
                            }
                        }
                    }
                    return Status.OK_STATUS;
                }
            });
            strategy.setAfterGetValidator(roleNameValidator);
            IObservableValue value = EMFObservables.observeValue(selectedRole, OrganizationPackage.Literals.ROLE__NAME) ;
            value.addValueChangeListener(new IValueChangeListener() {

                @Override
                public void handleValueChange(ValueChangeEvent event) {
                    Role role = (Role) ((EObjectObservableValue)event.getObservable()).getObserved();
                    getViewer().refresh(role) ;
                    for(Membership m : roleMemberShips){
                        m.setRoleName(role.getName()) ;
                    }
                }
            }) ;

            IObservableValue descriptionValue = EMFObservables.observeValue(selectedRole,  OrganizationPackage.Literals.ROLE__DESCRIPTION) ;
            descriptionValue.addValueChangeListener(new IValueChangeListener() {

                @Override
                public void handleValueChange(ValueChangeEvent event) {
                    Role role = (Role) ((EObjectObservableValue)event.getObservable()).getObserved();
                    getViewer().refresh(role) ;
                }
            }) ;

            IObservableValue displayNameValue = EMFObservables.observeValue(selectedRole,  OrganizationPackage.Literals.ROLE__DISPLAY_NAME) ;
            displayNameValue.addValueChangeListener(new IValueChangeListener() {

                @Override
                public void handleValueChange(ValueChangeEvent event) {
                    Role role = (Role) ((EObjectObservableValue)event.getObservable()).getObserved();
                    getViewer().refresh(role) ;
                }
            }) ;

            context.bindValue(SWTObservables.observeDelayedValue(500,SWTObservables.observeText(roleNameText,SWT.Modify)),value,strategy,new UpdateValueStrategy()) ;
            context.bindValue(SWTObservables.observeDelayedValue(500,SWTObservables.observeText(roleDescriptionText,SWT.Modify)),descriptionValue) ;
            context.bindValue(SWTObservables.observeText(displayNamedText,SWT.Modify), displayNameValue) ;


        }else{
            displayNamedText.setText("") ;
            roleDescriptionText.setText("") ;
            roleNameText.setText("") ;
            setControlEnabled(getInfoGroup(), false) ;
        }
        pageSupport = WizardPageSupportWithoutMessages.create(this, context) ;
    }


    @Override
    protected void configureInfoGroup(Group group) {
        group.setText(Messages.roleInfo) ;
        group.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(15, 5).spacing(5, 2).create()) ;


        Label roleName = new Label(group, SWT.NONE) ;
        roleName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
        roleName.setText(Messages.name) ;

        roleNameText = new Text(group, SWT.BORDER) ;
        roleNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(130, SWT.DEFAULT).create()) ;
        final ControlDecoration decoration =  new ControlDecoration(roleNameText,SWT.LEFT);
        roleNameValidator = new WrappingValidator(decoration, null,false,true);


        Label displayNameLabel = new Label(group, SWT.NONE) ;
        displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
        displayNameLabel.setText(Messages.displayName) ;

        displayNamedText = new Text(group, SWT.BORDER) ;
        displayNamedText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;


        Label descriptionLabel = new Label(group, SWT.NONE) ;
        descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.FILL).create()) ;
        descriptionLabel.setText(Messages.description) ;

        roleDescriptionText = new Text(group, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL) ;
        roleDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 80).create()) ;
        roleDescriptionText.setMessage(Messages.descriptionHint) ;


        getViewer().setSelection(new StructuredSelection()) ;
        refreshBinding(null) ;
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
