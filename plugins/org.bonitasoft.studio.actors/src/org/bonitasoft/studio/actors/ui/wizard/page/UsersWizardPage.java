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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.MetaDatasType;
import org.bonitasoft.studio.actors.model.organization.Metadata;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.databinding.WrappingValidator;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.jface.databinding.WizardPageSupportWithoutMessages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public class UsersWizardPage extends AbstractOrganizationWizardPage {


	private static final String DEFAULT_USER_PASSWORD = "bpm";
	private static final int TABFOLDER_HEIGHT = 240;
	private Text usernameText;
	private Text passwordText;

	private final Map<EAttribute, Control> personalWidgetMap = new HashMap<EAttribute, Control>();
	private final Map<EAttribute, Control> professionalWidgetMap = new HashMap<EAttribute, Control>();
	private final Map<Metadata, Control> metadataWidgetMap = new HashMap<Metadata, Control>();
	private final Map<EAttribute, Control> generalWidgetMap = new HashMap<EAttribute, Control>() ;
	private final Map<Membership, Map<EAttribute,Control>> membershipWidgetMap = new HashMap<Membership, Map<EAttribute,Control>>();
	private TabItem generalTab;
	private TabItem personalTab;
	private TabItem profesionnalTab;
	private TabItem metadataTab;
	private TabFolder tab;
	private final List<Membership> userMemberShips = new ArrayList<Membership>();
	private Combo managerNameCombo;
	private TabItem memberShipTab;
	private User selectedUser ;
	private WrappingValidator userNameValidator;
	private WrappingValidator passwordValidator;


	public UsersWizardPage() {
		super(UsersWizardPage.class.getName());
		setTitle(Messages.displayUsersPageTitle) ;
		setDescription(Messages.displayUsersPageDesc) ;
	}

	@Override
	protected void configureViewer(StructuredViewer viewer) {
		TableViewerColumn column = new TableViewerColumn((TableViewer) viewer, SWT.FILL) ;
		column.getColumn().setText(Messages.firstName);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((User)element).getFirstName();
			}
		});

		column.getColumn().setWidth(90);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);

		column = new TableViewerColumn((TableViewer) viewer, SWT.FILL) ;
		column.getColumn().setText(Messages.lastName);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((User)element).getLastName();
			}
		});

		column.getColumn().setWidth(90);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);

		column = new TableViewerColumn((TableViewer) viewer, SWT.FILL) ;
		TableColumn usernameColumn = column.getColumn() ;
		column.getColumn().setText(Messages.userName);
		column.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((User)element).getUserName();
			}
		});

		column.getColumn().setWidth(90);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);


		TableColumnSorter sorter = new TableColumnSorter((TableViewer) viewer) ;
		sorter.setColumn(usernameColumn) ;

		if(userList != null && getViewer() != null){
			getViewer().setInput(userList) ;
		}
	}

	@Override
	public void setOrganization(Organization organization) {
		super.setOrganization(organization);
		if(organization != null && getViewer() != null){
			getViewer().setInput(userList) ;
		}
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		Event e = new Event() ;
		e.item = tab.getSelection()[0] ;
		tab.notifyListeners(SWT.Selection,e);
	}

	private void refreshBinding(final User selectedUser) {
		if(context != null){
			context.dispose() ;
		}
		if(pageSupport != null){
			pageSupport.dispose() ;
		}
		context = new EMFDataBindingContext() ;


		userMemberShips.clear();
		if(selectedUser != null){
			setControlEnabled(getInfoGroup(), true) ;


			if(userList != null){
				managerNameCombo.removeAll() ;
				managerNameCombo.add("") ;
				for(User u : userList){
					if(!u.equals(selectedUser)){
						managerNameCombo.add(u.getUserName()) ;
					}
				}
			}


			UpdateValueStrategy strategy = new UpdateValueStrategy() ;
			userNameValidator.setValidator(new IValidator() {

				@Override
				public IStatus validate(Object value) {
					if(value.toString().isEmpty()){
						return ValidationStatus.error(Messages.nameIsEmpty) ;
					}
					for(User u : userList){
						if(!u.equals(selectedUser)){
							if(u.getUserName().equals(value)){
								return ValidationStatus.error(Messages.userNameAlreadyExists) ;
							}
						}
					}
					return Status.OK_STATUS;
				}
			});
			strategy.setAfterGetValidator(userNameValidator);
			IObservableValue value = EMFObservables.observeValue(selectedUser, OrganizationPackage.Literals.USER__USER_NAME) ;
			value.addValueChangeListener(new IValueChangeListener() {

				@Override
				public void handleValueChange(ValueChangeEvent event) {
					User user = (User) ((EObjectObservableValue)event.getObservable()).getObserved();
					for(Membership m : userMemberShips){
						m.setUserName(user.getUserName()) ;
					}
					managerNameCombo.removeAll() ;
					managerNameCombo.add("") ;
					for(User u : userList){
						if(!u.getUserName().equals(user.getUserName())){
							managerNameCombo.add(u.getUserName()) ;
						}
					}
					updateDelegueeMembership(event.diff.getOldValue().toString(),event.diff.getNewValue().toString()) ;
					getViewer().refresh(user) ;
				}
			}) ;



			context.bindValue(SWTObservables.observeDelayedValue(500,SWTObservables.observeText(usernameText,SWT.Modify)),value,strategy,null) ;

			UpdateValueStrategy mandatoryStrategy = new UpdateValueStrategy();
			passwordValidator.setValidator(new EmptyInputValidator(Messages.password));
			mandatoryStrategy.setAfterGetValidator(passwordValidator);


			IObservableValue observeValue = EMFObservables.observeValue(selectedUser.getPassword(),  OrganizationPackage.Literals.PASSWORD_TYPE__VALUE);
			context.bindValue(SWTObservables.observeText(passwordText,SWT.Modify), observeValue,mandatoryStrategy,null);
			observeValue.addValueChangeListener(new IValueChangeListener() {

				@Override
				public void handleValueChange(ValueChangeEvent event) {
					IObservableValue value = event.getObservableValue();
					PasswordType password = (PasswordType) ((EObjectObservableValue)value).getObserved();
					password.setEncrypted(false);
				}
			});
			context.bindValue(SWTObservables.observeText(managerNameCombo), EMFObservables.observeValue(selectedUser,  OrganizationPackage.Literals.USER__MANAGER)) ;

			for(Entry<EAttribute, Control> entry : generalWidgetMap.entrySet()){
				EAttribute attributre = entry.getKey() ;
				Control control =  entry.getValue() ;
				if(!control.isDisposed()){
					IObservableValue observableValue = EMFObservables.observeValue(selectedUser, attributre) ;
					UpdateValueStrategy mandatoryStartegy = null;
					if(attributre.equals(OrganizationPackage.Literals.USER__FIRST_NAME) ||
							attributre.equals(OrganizationPackage.Literals.USER__LAST_NAME)){
						observableValue.addValueChangeListener(new IValueChangeListener() {

							@Override
							public void handleValueChange(ValueChangeEvent event) {
								getViewer().refresh(((EObjectObservableValue)event.getObservable()).getObserved()) ;
							}
						}) ;
						mandatoryStartegy = new UpdateValueStrategy();
						if(attributre.equals(OrganizationPackage.Literals.USER__FIRST_NAME)){
							mandatoryStartegy.setAfterGetValidator(new EmptyInputValidator(Messages.firstName));
						}else if( attributre.equals(OrganizationPackage.Literals.USER__LAST_NAME))  {
							mandatoryStartegy.setAfterGetValidator(new EmptyInputValidator(Messages.lastName));
						}

					}
					if(mandatoryStartegy != null){
						ControlDecorationSupport.create(context.bindValue(SWTObservables.observeText(control,SWT.Modify), observableValue,mandatoryStartegy,null),SWT.LEFT) ;
					}else{
						context.bindValue(SWTObservables.observeText(control,SWT.Modify), observableValue);
					}
				}
			}

			if(selectedUser.getPersonalData() == null){
				selectedUser.setPersonalData(OrganizationFactory.eINSTANCE.createContactData()) ;
			}

			for(Entry<EAttribute, Control> entry : personalWidgetMap.entrySet()){
				EAttribute attributre = entry.getKey() ;
				Control control =  entry.getValue() ;
				if(!control.isDisposed()){
					IObservableValue observableValue = EMFObservables.observeValue(selectedUser.getPersonalData(), attributre);
					context.bindValue(SWTObservables.observeText(control,SWT.Modify), observableValue) ;
				}
			}

			if(selectedUser.getProfessionalData() == null){
				selectedUser.setProfessionalData(OrganizationFactory.eINSTANCE.createContactData()) ;
			}

			for(Entry<EAttribute, Control> entry : professionalWidgetMap.entrySet()){
				EAttribute attributre = entry.getKey() ;
				Control control =  entry.getValue() ;
				if(!control.isDisposed()){
					context.bindValue(SWTObservables.observeText(control,SWT.Modify), EMFObservables.observeValue(selectedUser.getProfessionalData(), attributre)) ;
				}
			}

			for(Entry<Metadata, Control> entry : metadataWidgetMap.entrySet()){
				Metadata metadata = entry.getKey() ;
				Control control =  entry.getValue() ;
				if(!control.isDisposed()){
					context.bindValue(SWTObservables.observeText(control,SWT.Modify), EMFObservables.observeValue(metadata, OrganizationPackage.Literals.METADATA__VALUE)) ;
				}
			}

			for(Entry<Membership, Map<EAttribute,Control>> entry : membershipWidgetMap.entrySet()){
				final Membership membership = entry.getKey() ;
				final Map<EAttribute,Control> controls =  entry.getValue() ;
				for(Entry<EAttribute, Control> e : controls.entrySet()){
					final EAttribute attributre = e.getKey() ;
					final Control control = e.getValue() ;
					if(!control.isDisposed()){
						UpdateValueStrategy selectionStrategy = new UpdateValueStrategy() ;

						selectionStrategy.setAfterConvertValidator(new IValidator() {

							@Override
							public IStatus validate(Object value) {
								if(value == null || value.toString().isEmpty()){
									return ValidationStatus.error(Messages.emtpyMembershipValue) ;
								}
								return Status.OK_STATUS;
							}
						}) ;
						if(attributre.equals(OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME)){
							selectionStrategy.setConverter(new Converter(String.class,String.class) {

								@Override
								public Object convert(Object from) {
									String path = (String) from ;
									if(!path.isEmpty()){
										String parentPath = path.substring(0, path.lastIndexOf(GroupContentProvider.GROUP_SEPARATOR)) ;
										String groupName = path.substring(path.lastIndexOf(GroupContentProvider.GROUP_SEPARATOR)+1,path.length()) ;
										if(parentPath.isEmpty()){
											membership.setGroupParentPath(null) ;
										}else{
											membership.setGroupParentPath(parentPath) ;
										}
										return groupName;
									}else{
										return "";
									}

								}
							});

						}
						UpdateValueStrategy modelStrategy = null;
						if(attributre.equals(OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME)){
							modelStrategy =  new UpdateValueStrategy();
							modelStrategy.setConverter(new Converter(String.class,String.class) {
								@Override
								public Object convert(Object from) {
									if(membership.getGroupParentPath() != null && !membership.getGroupParentPath().isEmpty()){
										String parentPath =  membership.getGroupParentPath() ;
										if(!membership.getGroupParentPath().endsWith(GroupContentProvider.GROUP_SEPARATOR)){
											parentPath = parentPath + GroupContentProvider.GROUP_SEPARATOR ;
										}
										String path = parentPath + from ;
										return path ;
									}else if(from!= null && !from.toString().isEmpty()){
										return GroupContentProvider.GROUP_SEPARATOR  + membership.getGroupName() ;
									}else{
										return "";
									}
								}
							});
						}
						ControlDecorationSupport.create(context.bindValue(SWTObservables.observeSelection(control), EMFObservables.observeValue(membership, attributre),selectionStrategy,modelStrategy),SWT.LEFT) ;
					}
				}

			}
			pageSupport = WizardPageSupportWithoutMessages.create(this, context);
		}else{
			setControlEnabled(getInfoGroup(), false) ;
		}


	}

	private void updateDelegueeMembership(String oldUserName,String newUserName) {
		for(Membership m : membershipList){
			if(oldUserName.equals(m.getUserName())){
				m.setUserName(newUserName) ;
			}
		}
	}

	@Override
	protected void configureInfoGroup(Group group) {
		group.setText(Messages.userInfo) ;

		Composite rightColumnComposite = new Composite(group, SWT.NONE) ;
		rightColumnComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2,1).create()) ;
		rightColumnComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).spacing(0, 2).margins(10, 0).equalWidth(false).create()) ;

		Label userName = new Label(rightColumnComposite, SWT.NONE) ;
		userName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
		userName.setText(Messages.userName) ;

		usernameText = new Text(rightColumnComposite, SWT.BORDER) ;
		usernameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		usernameText.setMessage(Messages.userNameHint) ;
		final ControlDecoration decoration =  new ControlDecoration(usernameText,SWT.LEFT);
		userNameValidator = new WrappingValidator(decoration, null,false,true);

		Label passwordLabel = new Label(rightColumnComposite, SWT.NONE) ;
		passwordLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
		passwordLabel.setText(Messages.password) ;

		passwordText = new Text(rightColumnComposite, SWT.BORDER |SWT.PASSWORD) ;
		passwordText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		final ControlDecoration decoration2 =  new ControlDecoration(passwordText,SWT.LEFT);
		passwordValidator = new WrappingValidator(decoration2, null,false,true);

		Label managerName = new Label(rightColumnComposite, SWT.NONE) ;
		managerName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
		managerName.setText(Messages.manager) ;

		managerNameCombo = new Combo(rightColumnComposite, SWT.BORDER | SWT.READ_ONLY) ;
		managerNameCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create()) ;

		tab = new TabFolder(group, SWT.NONE) ;
		tab.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create()) ;
		tab.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;
		tab.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TabItem item = (TabItem) e.item ;
				for(Control c : tab.getChildren()){
					c.dispose() ;
				}
				if(item.equals(generalTab)){
					generalTab.setControl(createGeneralControl(tab,generalWidgetMap)) ;
				}else if(item.equals(personalTab)){
					personalTab.setControl(createInfoControl(tab,personalWidgetMap)) ;
				}else if(item.equals(profesionnalTab)){
					profesionnalTab.setControl(createInfoControl(tab,professionalWidgetMap)) ;
				}else if(item.equals(metadataTab)){
					metadataTab.setControl(createMetadataControl(tab,metadataWidgetMap)) ;
				}else if(item.equals(memberShipTab)){
					memberShipTab.setControl(createMembershipControl(tab,membershipWidgetMap)) ;
				}

				getInfoGroup().layout(true, true) ;

				User selectedUser = (User) ((StructuredSelection) getViewer().getSelection()).getFirstElement() ;
				refreshBinding(selectedUser) ;
				if(selectedUser == null){
					usernameText.setText("") ;
					passwordText.setText("") ;
					managerNameCombo.setText("") ;
				}

			}
		}) ;

		generalTab = new TabItem(tab, SWT.NONE) ;
		generalTab.setText(Messages.general) ;

		memberShipTab = new TabItem(tab, SWT.NONE) ;
		memberShipTab.setText(Messages.membership) ;

		personalTab = new TabItem(tab, SWT.NONE) ;
		personalTab.setText(Messages.personalData) ;

		profesionnalTab = new TabItem(tab, SWT.NONE) ;
		profesionnalTab.setText(Messages.professionalData) ;

		metadataTab = new TabItem(tab, SWT.NONE) ;
		metadataTab.setText(Messages.metadata) ;

		getViewer().setSelection(new StructuredSelection()) ;
		refreshBinding(null) ;
	}

	protected Control createMembershipControl(Composite parent,Map<Membership, Map<EAttribute,Control>> widgetMap) {
		widgetMap.clear() ;

		Composite detailsInfoComposite = new Composite(parent, SWT.NONE) ;
		detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, TABFOLDER_HEIGHT).create()) ;
		detailsInfoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(5).margins(5, 5).equalWidth(false).create()) ;

		User selectedUser = (User) ((StructuredSelection) getViewer().getSelection()).getFirstElement() ;
		if(selectedUser != null){
			userMemberShips.clear() ;
			for(Membership m : membershipList){
				if(m.getUserName().equals(selectedUser.getUserName())){
					userMemberShips.add( m ) ;
				}
			}

			for(final Membership membership : userMemberShips){
				Label groupName = new Label(detailsInfoComposite, SWT.NONE) ;
				groupName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
				groupName.setText(Messages.groupName) ;

				final Combo groupNameCombo = new Combo(detailsInfoComposite, SWT.BORDER | SWT.READ_ONLY) ;
				groupNameCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create()) ;


				groupNameCombo.add("") ;
				for(org.bonitasoft.studio.actors.model.organization.Group g : groupList){
					groupNameCombo.add(GroupContentProvider.getGroupPath(g)) ;
				}


				Label roleName = new Label(detailsInfoComposite, SWT.NONE) ;
				roleName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
				roleName.setText(Messages.role) ;

				final Combo roleNameCombo = new Combo(detailsInfoComposite, SWT.BORDER | SWT.READ_ONLY) ;
				roleNameCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create()) ;
				roleNameCombo.add("") ;
				for(Role r : roleList){
					roleNameCombo.add(r.getName()) ;
				}

				Map<EAttribute, Control> map = new HashMap<EAttribute, Control>() ;
				map.put(OrganizationPackage.Literals.MEMBERSHIP__ROLE_NAME, roleNameCombo) ;
				map.put(OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME,groupNameCombo);
				widgetMap.put(membership, map) ;


				Button removeMembershipButton = new Button(detailsInfoComposite, SWT.FLAT) ;
				removeMembershipButton.setImage(Pics.getImage("delete.png")) ;
				removeMembershipButton.setToolTipText(Messages.delete) ;
				removeMembershipButton.setLayoutData(GridDataFactory.swtDefaults().create()) ;
				removeMembershipButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(MessageDialog.openQuestion(Display.getDefault().getActiveShell(),Messages.deleteMembershipTitle , Messages.deleteMembershipMsg)){
							membershipList.remove(membership) ;
							Event ev = new Event() ;
							ev.item = tab.getSelection()[0] ;
							tab.notifyListeners(SWT.Selection, ev) ;
						}
					}
				}) ;
			}
		}

		Button addMembershipButton = new Button(detailsInfoComposite, SWT.PUSH) ;
		addMembershipButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(5, 1).align(SWT.END, SWT.CENTER).create()) ;
		addMembershipButton.setText(Messages.addMembership) ;
		addMembershipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addMembershipAction() ;
			}
		}) ;

		return detailsInfoComposite ;
	}

	protected void addMembershipAction() {
		Membership m = OrganizationFactory.eINSTANCE.createMembership() ;
		User u = (User) ((IStructuredSelection) getViewer().getSelection()).getFirstElement() ;
		m.setUserName(u.getUserName()) ;
		membershipList.add(m) ;
		Event ev = new Event() ;
		ev.item = tab.getSelection()[0] ;
		tab.notifyListeners(SWT.Selection, ev) ;
	}

	protected Control createMetadataControl(Composite parent, Map<Metadata, Control> widgetMap) {
		if( widgetMap == null){
			widgetMap = new HashMap<Metadata, Control>() ;
		}else{
			widgetMap.clear() ;
		}


		Composite detailsInfoComposite = new Composite(parent, SWT.NONE) ;
		detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, TABFOLDER_HEIGHT).create()) ;
		detailsInfoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(5, 5).equalWidth(false).create()) ;

		User selectedUser = (User) ((StructuredSelection) getViewer().getSelection()).getFirstElement() ;
		if(selectedUser != null){
			if(selectedUser.getMetaDatas() == null){
				selectedUser.setMetaDatas(OrganizationFactory.eINSTANCE.createMetaDatasType()) ;
			}
			for(final Metadata metadata : selectedUser.getMetaDatas().getMetaData()){
				Label label = new Label(detailsInfoComposite, SWT.NONE) ;
				label.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END,SWT.CENTER).create()) ;
				label.setText(metadata.getName()) ;

				Text text = new Text(detailsInfoComposite, SWT.BORDER) ;
				text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
				widgetMap.put(metadata, text) ;

				Button removeMetadataButton = new Button(detailsInfoComposite, SWT.FLAT) ;
				removeMetadataButton.setImage(Pics.getImage("delete.png")) ;
				removeMetadataButton.setToolTipText(Messages.delete) ;
				removeMetadataButton.setLayoutData(GridDataFactory.swtDefaults().create()) ;
				removeMetadataButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(MessageDialog.openQuestion(Display.getDefault().getActiveShell(),Messages.deleteMetadataTitle , Messages.deleteMetadataMsg)){
							for(User u : userList){
								List<Metadata> toRemove = new ArrayList<Metadata>() ;
								for(Metadata m : u.getMetaDatas().getMetaData()){
									if(m.getName().equals(metadata.getName())){
										toRemove.add(m) ;
									}
								}
								u.getMetaDatas().getMetaData().removeAll(toRemove) ;
							}


							Event ev = new Event() ;
							ev.item = tab.getSelection()[0] ;
							tab.notifyListeners(SWT.Selection, ev) ;
						}
					}
				}) ;
			}
		}

		Button addMetadataButton = new Button(detailsInfoComposite, SWT.PUSH) ;
		addMetadataButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).align(SWT.END, SWT.CENTER).create()) ;
		addMetadataButton.setText(Messages.addMetadata) ;
		addMetadataButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addMetadataAction() ;
			}
		}) ;

		return detailsInfoComposite ;
	}

	protected void addMetadataAction() {
		InputDialog dialog = new InputDialog(Display.getDefault().getActiveShell(), Messages.addMetadata, Messages.metadataName, null, new IInputValidator() {

			@Override
			public String isValid(String input) {
				User u = (User) ((IStructuredSelection) getViewer().getSelection()).getFirstElement() ;
				if(u.getMetaDatas() == null){
					u.setMetaDatas(OrganizationFactory.eINSTANCE.createMetaDatasType()) ;
				}
				for(Metadata m : u.getMetaDatas().getMetaData()){
					if(m.getName().equals(input)){
						return Messages.bind(Messages.metadataAlreadyExists,input) ;
					}
				}
				return null;
			}
		}) ;

		if(dialog.open() == Dialog.OK){
			for(User u : userList){
				Metadata metadata = OrganizationFactory.eINSTANCE.createMetadata() ;
				metadata.setName(dialog.getValue()) ;
				if(u.getMetaDatas() == null){
					u.setMetaDatas(OrganizationFactory.eINSTANCE.createMetaDatasType());
				}
				u.getMetaDatas().getMetaData().add(metadata) ;
			}
			Event ev = new Event() ;
			ev.item = tab.getSelection()[0] ;
			tab.notifyListeners(SWT.Selection, ev) ;
		}
	}

	protected Control createInfoControl(Composite parent, Map<EAttribute, Control> widgetMap) {
		if( widgetMap == null){
			widgetMap = new HashMap<EAttribute, Control>() ;
		}else{
			widgetMap.clear() ;
		}

		Composite detailsInfoComposite = new Composite(parent, SWT.NONE) ;
		detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, TABFOLDER_HEIGHT).create()) ;
		detailsInfoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).equalWidth(false).create()) ;

		Label emailLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		emailLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		emailLabel.setText(Messages.emailLabel) ;

		Text emailText = new Text(detailsInfoComposite, SWT.BORDER) ;
		emailText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		emailText.setMessage(Messages.emailHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__EMAIL, emailText) ;

		Label phoneLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		phoneLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		phoneLabel.setText(Messages.phoneLabel) ;

		Text phoneText = new Text(detailsInfoComposite, SWT.BORDER) ;
		phoneText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		phoneText.setMessage(Messages.phoneHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__PHONE_NUMBER, phoneText) ;

		Label mobileLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		mobileLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		mobileLabel.setText(Messages.mobileLabel) ;

		Text mobileText = new Text(detailsInfoComposite, SWT.BORDER) ;
		mobileText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mobileText.setMessage(Messages.mobileHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__MOBILE_NUMBER, mobileText) ;

		Label faxLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		faxLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		faxLabel.setText(Messages.faxLabel) ;

		Text faxText = new Text(detailsInfoComposite, SWT.BORDER) ;
		faxText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		faxText.setMessage(Messages.faxHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__FAX_NUMBER, faxText) ;

		Label websiteLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		websiteLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		websiteLabel.setText(Messages.websiteLabel) ;

		Text websiteText = new Text(detailsInfoComposite, SWT.BORDER) ;
		websiteText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		websiteText.setMessage(Messages.websiteHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__WEBSITE, websiteText) ;

		Label buildingLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		buildingLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		buildingLabel.setText(Messages.buildingLabel) ;

		Composite buildingInfo = new Composite(detailsInfoComposite, SWT.NONE) ;
		buildingInfo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		buildingInfo.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(2, 0).equalWidth(false).create()) ;

		Text buildingText = new Text(buildingInfo, SWT.BORDER) ;
		buildingText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		buildingText.setMessage(Messages.buildingHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__BUILDING, buildingText) ;

		Label roomLabel = new Label(buildingInfo, SWT.NONE) ;
		roomLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		roomLabel.setText(Messages.roomLabel) ;

		Text roomText = new Text(buildingInfo, SWT.BORDER) ;
		roomText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		roomText.setMessage(Messages.roomHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__ROOM, roomText) ;

		Label adressLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		adressLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		adressLabel.setText(Messages.addressLabel) ;

		Text adressText = new Text(detailsInfoComposite, SWT.BORDER) ;
		adressText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		adressText.setMessage(Messages.addressHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__ADDRESS, adressText) ;

		Label cityLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		cityLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		cityLabel.setText(Messages.cityLabel) ;

		Composite addressInfo = new Composite(detailsInfoComposite, SWT.NONE) ;
		addressInfo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		addressInfo.setLayout(GridLayoutFactory.fillDefaults().numColumns(5).margins(0, 0).spacing(2, 0).equalWidth(false).create()) ;

		Text cityText = new Text(addressInfo, SWT.BORDER) ;
		cityText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		cityText.setMessage(Messages.cityHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__CITY, cityText) ;

		Label stateLabel = new Label(addressInfo, SWT.NONE) ;
		stateLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		stateLabel.setText(Messages.stateLabel) ;

		Text stateText = new Text(addressInfo, SWT.BORDER) ;
		stateText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		stateText.setMessage(Messages.stateHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__STATE, stateText) ;

		Label zipcodeLabel = new Label(addressInfo, SWT.NONE) ;
		zipcodeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		zipcodeLabel.setText(Messages.zipCodeLabel) ;

		Text zipCodeText = new Text(addressInfo, SWT.BORDER) ;
		zipCodeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		zipCodeText.setMessage(Messages.zipCodeHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__ZIP_CODE, zipCodeText) ;

		Label countryLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		countryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		countryLabel.setText(Messages.countryLabel) ;

		Text countryText = new Text(detailsInfoComposite, SWT.BORDER) ;
		countryText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		countryText.setMessage(Messages.coutryHint) ;
		widgetMap.put(OrganizationPackage.Literals.CONTACT_DATA__COUNTRY, countryText) ;


		return detailsInfoComposite ;
	}

	protected Control createGeneralControl(Composite parent,Map<EAttribute, Control> widgetMap) {
		if( widgetMap == null){
			widgetMap = new HashMap<EAttribute, Control>() ;
		}else{
			widgetMap.clear() ;
		}

		Composite detailsInfoComposite = new Composite(parent, SWT.NONE) ;
		detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, TABFOLDER_HEIGHT).create()) ;
		detailsInfoComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).margins(5, 5).equalWidth(false).create()) ;

		Label titleLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		titleLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		titleLabel.setText(Messages.userTitle) ;

		Text titleText = new Text(detailsInfoComposite, SWT.BORDER) ;
		titleText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		titleText.setMessage(Messages.titleHint) ;
		widgetMap.put(OrganizationPackage.Literals.USER__TITLE, titleText) ;

		Label firstName = new Label(detailsInfoComposite, SWT.NONE) ;
		firstName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		firstName.setText(Messages.firstName) ;

		Text firstNameText = new Text(detailsInfoComposite, SWT.BORDER) ;
		firstNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		firstNameText.setMessage(Messages.firstNameHint) ;
		widgetMap.put(OrganizationPackage.Literals.USER__FIRST_NAME, firstNameText) ;

		Label lastName = new Label(detailsInfoComposite, SWT.NONE) ;
		lastName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		lastName.setText(Messages.lastName) ;

		Text lastNameText = new Text(detailsInfoComposite, SWT.BORDER) ;
		lastNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		lastNameText.setMessage(Messages.lastNameHint) ;
		widgetMap.put(OrganizationPackage.Literals.USER__LAST_NAME, lastNameText) ;

		Label jobLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		jobLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		jobLabel.setText(Messages.jobLabel) ;

		Text jobText = new Text(detailsInfoComposite, SWT.BORDER) ;
		jobText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		jobText.setMessage(Messages.jobHint) ;
		widgetMap.put(OrganizationPackage.Literals.USER__JOB_TITLE, jobText) ;

		return detailsInfoComposite ;
	}

	@Override
	protected void addButtonSelected() {
		User user = OrganizationFactory.eINSTANCE.createUser() ;
		user.setUserName(generateUsername()) ;
		user.setPassword(createPassword(DEFAULT_USER_PASSWORD));
		if(!userList.isEmpty()){
			final MetaDatasType metaDatas = userList.get(0).getMetaDatas();
			if(metaDatas != null){
				for(Metadata m : metaDatas.getMetaData()){
					if(m!=null){
						Metadata metadata = OrganizationFactory.eINSTANCE.createMetadata() ;
						metadata.setName(m.getName()) ;
						if(user.getMetaDatas()!=null){
							user.getMetaDatas().getMetaData().add(metadata) ;
						}
					}
				}
			}
		}

		userList.add(user) ;
		getViewer().setInput(userList) ;
		getViewer().setSelection(new StructuredSelection(user)) ;
	}

	private PasswordType createPassword(String defaultUserPassword) {
		final PasswordType password = OrganizationFactory.eINSTANCE.createPasswordType();
		password.setEncrypted(false);
		password.setValue(defaultUserPassword);
		return password;
	}

	private String generateUsername() {
		Set<String> names = new HashSet<String>() ;
		for(User u : userList){
			names.add(u.getUserName()) ;
		}

		return NamingUtils.generateNewName(names, Messages.defaultUserName);
	}

	@Override
	protected void removeButtonSelected() {
		for(Object sel :  ((IStructuredSelection) getViewer().getSelection()).toList()){
			if(sel instanceof User){
				List<Membership> toRemove = new ArrayList<Membership>() ;
				for(Membership m : membershipList){
					if(m.getUserName().equals(((User) sel).getUserName())){
						toRemove.add(m) ;
					}
				}
				membershipList.removeAll(toRemove) ;
				userList.remove(sel) ;
			}
		}
		getViewer().setInput(userList) ;
		selectionChanged(new SelectionChangedEvent(getViewer(),new StructuredSelection())) ;
	}

	@Override
	protected boolean viewerSelect(Object element, String searchQuery) {
		if(searchQuery == null || searchQuery.isEmpty()
				|| (((User)element).getUserName() != null && ((User)element).getUserName().toLowerCase().contains(searchQuery.toLowerCase()))
				|| (((User)element).getFirstName() != null && ((User)element).getFirstName().toLowerCase().contains(searchQuery.toLowerCase()))
				|| (((User)element).getLastName() != null && ((User)element).getLastName().toLowerCase().contains(searchQuery.toLowerCase()))){
			return true ;
		}
		return false ;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}


}
