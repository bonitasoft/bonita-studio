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
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinition;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoDefinitions;
import org.bonitasoft.studio.actors.model.organization.CustomUserInfoValue;
import org.bonitasoft.studio.actors.model.organization.Membership;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.bonitasoft.studio.actors.model.organization.PasswordType;
import org.bonitasoft.studio.actors.model.organization.Role;
import org.bonitasoft.studio.actors.model.organization.User;
import org.bonitasoft.studio.actors.ui.editingsupport.CustomUserInformationDefinitionNameEditingSupport;
import org.bonitasoft.studio.actors.ui.editingsupport.CustomerUserInformationDefinitionDescriptionEditingSupport;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.TableColumnSorter;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationUpdater;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public class UsersWizardPage extends AbstractOrganizationWizardPage {


	private static final String DEFAULT_USER_PASSWORD = "bpm";
	private static final int MIN_SC_WIDTH = 426;
	private static final int MIN_SC_HEIGHT = 268;
	//	private Text usernameText;
	//	private Text passwordText;

	//private final Map<EAttribute, Control> personalWidgetMap = new HashMap<EAttribute, Control>();
	private final Map<EAttribute, Control> professionalWidgetMap = new HashMap<EAttribute, Control>();
	private final Map<EAttribute, Control> generalWidgetMap = new HashMap<EAttribute, Control>() ;
	private final Map<Membership, Map<EAttribute,Control[]>> membershipWidgetMap = new HashMap<Membership, Map<EAttribute,Control[]>>();
	private final Map<CustomUserInfoValue,  Control[]> customUserInfoWidgetMap = new HashMap<CustomUserInfoValue, Control[]>();

	CustomUserInfoDefinitions infoDefinitions;

	private TabItem generalTab;
	private TabItem personalTab;
	private TabItem professionnalTab;
	private TabFolder tab;
	private final List<Membership> userMemberShips = new ArrayList<Membership>();
	private TabItem memberShipTab;
	private TabItem otherTab;
	private TabItem infoTab;

	TableViewer otherInfoTable;
	private IObservableList customUserInfoObservableList;
	private CustomUserInformationDefinitionNameEditingSupport customUserInformationDefinitionNameEditingSupport;
	private IViewerObservableValue userSingleSelectionObservable;
	private TabItem userTab;
	private Composite labelComposite;


	public UsersWizardPage() {
		super(UsersWizardPage.class.getName());
		setTitle(Messages.displayUsersPageTitle) ;
		setDescription(Messages.displayUsersPageDesc) ;
	}

	@Override
	protected void configureViewer(StructuredViewer viewer) {

		TableViewer userTableViewer = (TableViewer) viewer;
		Table table = userTableViewer.getTable();

		userSingleSelectionObservable = ViewersObservables.observeSingleSelection(getViewer());
		userSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				User selectedUser = (User) event.diff.getNewValue();
				boolean isAUserSelected = selectedUser !=null;
				setControlEnabled(getInfoGroup(), isAUserSelected);

				if(selectedUser.getPersonalData() == null){
					selectedUser.setPersonalData(OrganizationFactory.eINSTANCE.createContactData());
				}

				if(selectedUser.getProfessionalData() == null){
					selectedUser.setProfessionalData(OrganizationFactory.eINSTANCE.createContactData());
				}
				
			}
		});

		addFirstNameColumn(userTableViewer);

		addLastNameColumn(userTableViewer);

		addUserNameColumn(userTableViewer);

		addTableColumLayout(table);

		if(userList != null && getViewer() != null){
			getViewer().setInput(userList) ;
		}
	}

	private void addUserNameColumn(StructuredViewer viewer) {
		TableViewerColumn column;
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
	}

	private void addLastNameColumn(StructuredViewer viewer) {
		TableViewerColumn column;
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
	}

	private void addFirstNameColumn(StructuredViewer viewer) {
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
	}

	@Override
	public void setOrganization(Organization organization) {
		super.setOrganization(organization);
		if(organization != null && getViewer() != null){
			getViewer().setInput(userList) ;
			if(otherInfoTable != null){
				if(organization.getCustomUserInfoDefinitions() == null){
					organization.setCustomUserInfoDefinitions(OrganizationFactory.eINSTANCE.createCustomUserInfoDefinitions());
				}
				customUserInfoObservableList = EMFProperties.list(OrganizationPackage.Literals.CUSTOM_USER_INFO_DEFINITIONS__CUSTOM_USER_INFO_DEFINITION).observe(organization.getCustomUserInfoDefinitions());
				otherInfoTable.setInput(customUserInfoObservableList);
				customUserInfoObservableList.addListChangeListener(new IListChangeListener() {

					@Override
					public void handleListChange(ListChangeEvent event) {
						if(otherTab !=null && !otherTab.isDisposed()){
							otherTab.getControl().redraw();
						}
						if(getViewer() != null && !getViewer().getControl().isDisposed()){
							getViewer().refresh(infoTab) ;
						}

					}
				});
			}
			customUserInformationDefinitionNameEditingSupport.setOrganization(organization);
		}
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		//		Event e = new Event() ;
		//		e.item = tab.getSelection()[0] ;
		//		tab.notifyListeners(SWT.Selection,e);
	}

	private void refreshBinding(final User selectedUser) {
		//		if(context != null){
		//			context.dispose() ;
		//		}
		//		if(pageSupport != null){
		//			pageSupport.dispose() ;
		//		}
		//		context = new EMFDataBindingContext() ;


		userMemberShips.clear();
		//		if(selectedUser != null){
		//			setControlEnabled(getInfoGroup(), true) ;


		//			if(userList != null){
		//				managerNameCombo.removeAll() ;
		//				managerNameCombo.add("") ;
		//				for(User u : userList){
		//					if(!u.equals(selectedUser)){
		//						managerNameCombo.add(u.getUserName()) ;
		//					}
		//				}
		//			}


		//			UpdateValueStrategy strategy = new UpdateValueStrategy() ;
		//
		//			strategy.setConverter(new Converter(String.class, String.class){
		//
		//				@Override
		//				public Object convert(Object fromObject) {
		//					if (userList!=null){
		//						for (User u:userList){
		//							if (selectedUser!=null && u.getManager()!=null && u.getManager().equals(selectedUser.getUserName())){
		//								u.setManager((String)fromObject);
		//							}
		//						}
		//					}
		//					return fromObject;
		//				}
		//
		//			});
		//			IObservableValue value = EMFObservables.observeValue(selectedUser, OrganizationPackage.Literals.USER__USER_NAME) ;
		//			value.addValueChangeListener(new IValueChangeListener() {
		//
		//				@Override
		//				public void handleValueChange(ValueChangeEvent event) {
		//					User user = (User) ((EObjectObservableValue)event.getObservable()).getObserved();
		//					for(Membership m : userMemberShips){
		//						m.setUserName(user.getUserName()) ;
		//					}
		//					updateDelegueeMembership(event.diff.getOldValue().toString(),event.diff.getNewValue().toString()) ;
		//					getViewer().refresh(user) ;
		//
		//				}
		//			}) ;



		//			context.bindValue(SWTObservables.observeDelayedValue(500,SWTObservables.observeText(usernameText,SWT.Modify)),value,strategy,null) ;

		//			UpdateValueStrategy mandatoryStrategy = new UpdateValueStrategy();
		//			passwordValidator.setValidator(new EmptyInputValidator(Messages.password));
		//			mandatoryStrategy.setAfterGetValidator(passwordValidator);
		//
		//
		//			IObservableValue observeValue = EMFObservables.observeValue(selectedUser.getPassword(),  OrganizationPackage.Literals.PASSWORD_TYPE__VALUE);
		//			context.bindValue(SWTObservables.observeText(passwordText,SWT.Modify), observeValue,mandatoryStrategy,null);
		//			observeValue.addValueChangeListener(new IValueChangeListener() {
		//
		//				@Override
		//				public void handleValueChange(ValueChangeEvent event) {
		//					IObservableValue value = event.getObservableValue();
		//					PasswordType password = (PasswordType) ((EObjectObservableValue)value).getObserved();
		//					password.setEncrypted(false);
		//				}
		//			});

		//			context.bindValue(SWTObservables.observeSelection(managerNameCombo), EMFObservables.observeValue(selectedUser,  OrganizationPackage.Literals.USER__MANAGER));

		//			for(Entry<EAttribute, Control> entry : generalWidgetMap.entrySet()){
		//				EAttribute attributre = entry.getKey() ;
		//				Control control =  entry.getValue() ;
		//				if(!control.isDisposed()){
		//					IObservableValue observableValue = EMFObservables.observeValue(selectedUser, attributre) ;
		//					if(attributre.equals(OrganizationPackage.Literals.USER__FIRST_NAME) ||
		//							attributre.equals(OrganizationPackage.Literals.USER__LAST_NAME)){
		//						observableValue.addValueChangeListener(new IValueChangeListener() {
		//
		//							@Override
		//							public void handleValueChange(ValueChangeEvent event) {
		//								getViewer().refresh(((EObjectObservableValue)event.getObservable()).getObserved()) ;
		//							}
		//						}) ;
		//					}
		//
		//					context.bindValue(SWTObservables.observeText(control,SWT.Modify), observableValue);
		//
		//				}
		//			}

		if(selectedUser.getPersonalData() == null){
			selectedUser.setPersonalData(OrganizationFactory.eINSTANCE.createContactData()) ;
		}

		//			for(Entry<EAttribute, Control> entry : personalWidgetMap.entrySet()){
		//				EAttribute attributre = entry.getKey() ;
		//				Control control =  entry.getValue() ;
		//				if(!control.isDisposed()){
		//					IObservableValue observableValue = EMFObservables.observeValue(selectedUser.getPersonalData(), attributre);
		//					context.bindValue(SWTObservables.observeText(control,SWT.Modify), observableValue) ;
		//				}
		//			}

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


		for(Entry<CustomUserInfoValue,  Control[]> entry : customUserInfoWidgetMap.entrySet()){
			final CustomUserInfoValue customInfo = entry.getKey();
			final Control control = entry.getValue()[1];

			if(!control.isDisposed()){
				context.bindValue(SWTObservables.observeText(control,SWT.Modify), EMFObservables.observeValue(customInfo, OrganizationPackage.Literals.CUSTOM_USER_INFO_VALUE__VALUE)) ;
			}

		}


		for(Entry<Membership, Map<EAttribute,Control[]>> entry : membershipWidgetMap.entrySet()){
			final Membership membership = entry.getKey() ;
			final Map<EAttribute,Control[]> controls =  entry.getValue() ;
			for(Entry<EAttribute, Control[]> e : controls.entrySet()){
				final EAttribute attributre = e.getKey() ;
				final Control control = e.getValue()[1] ;
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
					Binding binding = context.bindValue(SWTObservables.observeSelection(control), EMFObservables.observeValue(membership, attributre),selectionStrategy,modelStrategy);
					ControlDecorationSupport.create(binding,SWT.LEFT);
				}
			}
		}

		//			pageSupport = WizardPageSupport.create(this, context);
		//		}else{
		//			setControlEnabled(getInfoGroup(), false) ;
		//		}


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
		group.setText(Messages.details) ;

		Composite detailsComposite = new Composite(group, SWT.NONE) ;
		detailsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2,1).create()) ;
		detailsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(0, 2).margins(15, 5).equalWidth(false).create()) ;

		createUserNameField(detailsComposite);

		createPasswordField(detailsComposite);

		createManagerCombo(detailsComposite);
		

		tab = new TabFolder(group, SWT.NONE) ;
		tab.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create()) ;
		tab.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;

		tab.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Control control =null;
				TabItem item = (TabItem) e.item ;
				for(Control c : tab.getChildren()){
					c.dispose() ;
				}


				if(item.equals(generalTab)){
					final ScrolledComposite sc = createScrolledComposite();
					control=createGeneralControl(sc,generalWidgetMap);
					sc.setContent(control);

					generalTab.setControl(sc) ;


				}else if(item.equals(personalTab)){
					final ScrolledComposite sc = createScrolledComposite();
					control=createContactInfoControl(sc, OrganizationPackage.Literals.USER__PERSONAL_DATA);
					sc.setContent(control);
					personalTab.setControl(sc) ;

				}else if(item.equals(professionnalTab)){
					final ScrolledComposite sc = createScrolledComposite();
					control = createContactInfoControl(sc, OrganizationPackage.Literals.USER__PROFESSIONAL_DATA);
					sc.setContent(control);
					professionnalTab.setControl(sc) ;
				}

				else if(item.equals(memberShipTab)){
					final ScrolledComposite sc = createScrolledComposite();
					control = createMembershipControl(sc,membershipWidgetMap);
					sc.setContent(control);
					memberShipTab.setControl(sc) ;
				}

				else if(item.equals(otherTab)){
					final ScrolledComposite sc = createScrolledComposite();
					control = createOtherControl(sc,customUserInfoWidgetMap);
					sc.setContent(control);
					otherTab.setControl(sc) ;
				}

				getInfoGroup().layout(true, true) ;

//				userSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {
//					
//					@Override
//					public void handleValueChange(ValueChangeEvent arg0) {
//						if(Arrays.asList(tab.getSelection()).contains(memberShipTab)){
//							Event event = new Event();
//							event.item = memberShipTab;
//							tab.notifyListeners(SWT.Selection, event);
//						}
//	
//					}
//				});

			}
		}) ;

		generalTab = new TabItem(tab, SWT.NONE) ;
		generalTab.setText(Messages.general) ;

		memberShipTab = new TabItem(tab, SWT.NONE) ;
		memberShipTab.setText(Messages.membership+" *") ;

		personalTab = new TabItem(tab, SWT.NONE) ;
		personalTab.setText(Messages.personalData) ;

		professionnalTab = new TabItem(tab, SWT.NONE) ;
		professionnalTab.setText(Messages.professionalData) ;

		otherTab = new TabItem(tab, SWT.NONE);
		otherTab.setText(Messages.other);


		//		getViewer().setSelection(new StructuredSelection()) ;
		setControlEnabled(getInfoGroup(), false);
	}

	private void createManagerCombo(Composite rightColumnComposite) {
		Label managerName = new Label(rightColumnComposite, SWT.NONE) ;
		managerName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
		managerName.setText(Messages.manager) ;

		final ComboViewer managerNameComboViewer = new ComboViewer(rightColumnComposite, SWT.BORDER | SWT.READ_ONLY) ;
		managerNameComboViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).indent(5, 0).create()) ;
		managerNameComboViewer.setLabelProvider(new LabelProvider());
		managerNameComboViewer.setContentProvider(new ObservableListContentProvider());

		userSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				User currentUser = (User) userSingleSelectionObservable.getValue();
				if(userList != null){
					WritableList users = new WritableList();
					users.add("");
					for(User u : userList){
						if(!u.equals(currentUser)){
							users.add(u.getUserName()) ;
						}
					}
					
					managerNameComboViewer.setInput(users);
					if(currentUser.getManager() != null){
						managerNameComboViewer.setSelection(new StructuredSelection(currentUser.getManager()));
					}
				
				}
			}
		});
		
		IObservableValue managerValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__MANAGER);

		context.bindValue(ViewersObservables.observeSingleSelection(managerNameComboViewer),managerValue );

	}

	private void createPasswordField(Composite rightColumnComposite) {
		Label passwordLabel = new Label(rightColumnComposite, SWT.NONE) ;
		passwordLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
		passwordLabel.setText(Messages.password+" *") ;

		Text passwordText = new Text(rightColumnComposite, SWT.BORDER |SWT.PASSWORD) ;
		passwordText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 0).create()) ;

		UpdateValueStrategy mandatoryStrategy = new UpdateValueStrategy();
		mandatoryStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.password));

		IObservableValue userPasswordObservableValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__PASSWORD);

		IObservableValue passwordValueObservableValue = EMFObservables.observeDetailValue(Realm.getDefault(), userPasswordObservableValue, OrganizationPackage.Literals.PASSWORD_TYPE__VALUE);

		Binding binding = context.bindValue(SWTObservables.observeText(passwordText, SWT.Modify), passwordValueObservableValue,mandatoryStrategy,null);
		ControlDecorationSupport.create(binding, SWT.LEFT, rightColumnComposite, new ControlDecorationUpdater(){
			@Override
			protected void update(ControlDecoration decoration, IStatus status) {
				if(userSingleSelectionObservable.getValue() != null){
					super.update(decoration, status);
				}
			}
		});

	}

	private void createUserNameField(Composite rightColumnComposite) {
		Label userName = new Label(rightColumnComposite, SWT.NONE) ;
		userName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING,SWT.CENTER).create()) ;
		userName.setText(Messages.userName+" *") ;

		Text usernameText = new Text(rightColumnComposite, SWT.BORDER) ;
		usernameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(5, 0).create()) ;
		usernameText.setMessage(Messages.userNameHint) ;

		UpdateValueStrategy stategy = new UpdateValueStrategy();
		stategy.setAfterGetValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if(value.toString().isEmpty()){
					return ValidationStatus.error(Messages.nameIsEmpty) ;
				}

				if(value.toString().length()>NAME_SIZE){
					return ValidationStatus.error(Messages.nameLimitSize);
				}

				User currentUser = (User) userSingleSelectionObservable.getValue();
				for(User u : userList){
					if(!u.equals(currentUser)){
						if(u.getUserName().equals(value)){
							return ValidationStatus.error(Messages.userNameAlreadyExists) ;
						}
					}
				}
				return Status.OK_STATUS;
			}
		});


		IObservableValue userNameValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__USER_NAME);
		Binding binding = context.bindValue(SWTObservables.observeText(usernameText, SWT.Modify), userNameValue , stategy, null); 
		ControlDecorationSupport.create(binding, SWT.LEFT, rightColumnComposite, new ControlDecorationUpdater(){
			@Override
			protected void update(ControlDecoration decoration, IStatus status) {
				if(userSingleSelectionObservable.getValue()!=null){
					super.update(decoration, status);
				}
			}
		});


		userNameValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleUserNameChange(event);

			}
		});
	}

	private void handleUserNameChange(ValueChangeEvent event) {
		User user = (User) userSingleSelectionObservable.getValue();
		User oldUser = EcoreUtil.copy(user);
		Object oldValue = event.diff.getOldValue();
		if(oldValue!=null){
			oldUser.setUserName(oldValue.toString());

			//			for(Membership m : userMemberShips){
			//				m.setUserName(user.getUserName()) ;
			//			}
			//			updateDelegueeMembership(event.diff.getOldValue().toString(),event.diff.getNewValue().toString()) ;
			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(user) ;
			}
		}
	}

	private void handleFirstNameChange(ValueChangeEvent event) {
		User user = (User) userSingleSelectionObservable.getValue();
		User oldUser = EcoreUtil.copy(user);
		Object oldValue = event.diff.getOldValue();
		if(oldValue!=null){
			oldUser.setFirstName(oldValue.toString());

			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(user) ;
			}
		}
	}

	private void handleLastNameChange(ValueChangeEvent event) {
		User user = (User) userSingleSelectionObservable.getValue();
		User oldUser = EcoreUtil.copy(user);
		Object oldValue = event.diff.getOldValue();
		if(oldValue!=null){
			oldUser.setLastName(oldValue.toString());

			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(user) ;
			}
		}
	}

	/**
	 * @return
	 */
	private ScrolledComposite createScrolledComposite() {
		final ScrolledComposite sc = new ScrolledComposite(tab, SWT.V_SCROLL);
		sc.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(0, 0).create());
		sc.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create()) ;
		sc.setMinSize(MIN_SC_WIDTH,MIN_SC_HEIGHT);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		return sc;
	}


	protected Control createOtherControl(Composite parent,Map<CustomUserInfoValue, Control[]> widgetMap) {

		if( widgetMap == null){
			widgetMap = new HashMap<CustomUserInfoValue, Control[]>() ;
		}else{
			widgetMap.clear() ;
		}

		Composite otherInfoComposite = new Composite(parent, SWT.NONE) ;
		otherInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		otherInfoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).equalWidth(false).create()) ;

		User selectedUser = (User) userSingleSelectionObservable.getValue() ;
		if(selectedUser != null){

			if(selectedUser.getCustomUserInfoValues() == null){
				selectedUser.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType()) ;
			}
			
			IObservableValue customUserInfoValuesValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__CUSTOM_USER_INFO_VALUES);

			IObservableList customUserInfoListValue = EMFObservables.observeDetailList(Realm.getDefault(), customUserInfoValuesValue, OrganizationPackage.Literals.CUSTOM_USER_INFO_VALUES_TYPE__CUSTOM_USER_INFO_VALUE);

			for(CustomUserInfoValue infoValue : selectedUser.getCustomUserInfoValues().getCustomUserInfoValue()){
				
				

				Label labelName = new Label(otherInfoComposite, SWT.LEFT);
				labelName.setText(infoValue.getName());
				
				Text textValue = new Text(otherInfoComposite, SWT.BORDER);
				textValue.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
				
				Control[] controls = new Control[]{labelName, textValue};
				widgetMap.put(infoValue, controls);
			}
		}

		// LINK
		Link addInfoLink = new Link(otherInfoComposite, SWT.NONE);
		addInfoLink.setText("<A>"+Messages.customUserInfoOtherTabLink+"</A>");
		addInfoLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tabFolder.setSelection(infoTab);
			}

		});

		return otherInfoComposite;
	}	

	protected Control createMembershipControl(Composite parent,Map<Membership, Map<EAttribute,Control[]>> widgetMap) {
		widgetMap.clear() ;

		Composite detailsInfoComposite = new Composite(parent, SWT.NONE) ;
		detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		detailsInfoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(5).margins(5, 5).equalWidth(false).create()) ;

		//		User selectedUser = (User) ((StructuredSelection) getViewer().getSelection()).getFirstElement() ;
		User selectedUser = (User) userSingleSelectionObservable.getValue() ;
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


				createMembershipGroupCombo(detailsInfoComposite, membership);
				createMemberShipRoleCombo(detailsInfoComposite, membership);


				Button removeMembershipButton = new Button(detailsInfoComposite, SWT.FLAT) ;
				removeMembershipButton.setVisible(userMemberShips.size() > 1);
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

	private void createMemberShipRoleCombo(Composite detailsInfoComposite, final Membership membership) {
		// ROLE

		Label roleName = new Label(detailsInfoComposite, SWT.NONE) ;
		roleName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		roleName.setText(Messages.role) ;

		final Combo roleNameCombo = new Combo(detailsInfoComposite, SWT.BORDER | SWT.READ_ONLY) ;
		roleNameCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create()) ;

		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setAfterGetValidator(new EmptyInputValidator(Messages.emtpyMembershipValue));

		IObservableValue roleMemmberShipValue=EMFObservables.observeValue(membership, OrganizationPackage.Literals.MEMBERSHIP__ROLE_NAME);
		Binding binding = context.bindValue(SWTObservables.observeText(roleNameCombo), roleMemmberShipValue,  strategy, null);
		ControlDecorationSupport.create(binding, SWT.LEFT,detailsInfoComposite);

		for(Role r : roleList){
			roleNameCombo.add(r.getName()) ;
		}



	}

	private void createMembershipGroupCombo(Composite detailsInfoComposite,
			final Membership membership) {
		// GROUP
		final Combo groupNameCombo = new Combo(detailsInfoComposite, SWT.BORDER | SWT.READ_ONLY) ;
		groupNameCombo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(100, SWT.DEFAULT).create()) ;

		//				groupNameCombo.add("") ;
//		if (groupList.size() > 0) {
//			ECollections.sort((EList<org.bonitasoft.studio.actors.model.organization.Group>)groupList, new Comparator<org.bonitasoft.studio.actors.model.organization.Group>() {
//				@Override
//				public int compare(org.bonitasoft.studio.actors.model.organization.Group group1, org.bonitasoft.studio.actors.model.organization.Group group2) {
//					if(GroupContentProvider.getGroupPath(group1).compareTo(GroupContentProvider.getGroupPath(group2)) >0){
//						return 1;
//					}
//					else {
//						return -1;
//					}
//				}
//			});
//		}
		for(org.bonitasoft.studio.actors.model.organization.Group g : groupList){
			groupNameCombo.add(GroupContentProvider.getGroupPath(g)) ;
		}

		UpdateValueStrategy targetStrategy = new UpdateValueStrategy();
		targetStrategy.setAfterGetValidator(new EmptyInputValidator(Messages.emtpyMembershipValue));
		targetStrategy.setConverter(new Converter(String.class,String.class) {
			
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

		UpdateValueStrategy modelStrategy = new UpdateValueStrategy();
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
		IObservableValue membershipValue = EMFObservables.observeValue(membership, OrganizationPackage.Literals.MEMBERSHIP__GROUP_NAME);
		Binding binding = context.bindValue(SWTObservables.observeText(groupNameCombo), membershipValue, targetStrategy, modelStrategy);
		ControlDecorationSupport.create(binding, SWT.LEFT,detailsInfoComposite);
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

	protected Control createContactInfoControl(Composite parent, EReference ref) {

		Composite detailsInfoComposite = new Composite(parent, SWT.NONE) ;

		detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).minSize(0, 0).create()) ;
		detailsInfoComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false).create()) ;

		createEmailField( detailsInfoComposite,ref );
		createPhoneField(detailsInfoComposite,ref);
		createMobileField(detailsInfoComposite,ref);
		createFaxField(detailsInfoComposite,ref);
		createWebSiteField(detailsInfoComposite,ref);
		createBuildingInfoFields(detailsInfoComposite,ref);
		createAddressField(detailsInfoComposite,ref);
		createAddressInfoFields(detailsInfoComposite,ref);
		createCountryField( detailsInfoComposite,ref);
		return detailsInfoComposite;	

	}



	private void createFaxField(Composite detailsInfoComposite, EReference reference) {
		Label faxLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		faxLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		faxLabel.setText(Messages.faxLabel) ;

		Text faxText = new Text(detailsInfoComposite, SWT.BORDER) ;
		faxText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		faxText.setMessage(Messages.faxHint) ;
		bindContactDataValue(faxText,reference, OrganizationPackage.Literals.CONTACT_DATA__FAX_NUMBER);
	}

	private void createMobileField(Composite detailsInfoComposite, EReference reference) {
		Label mobileLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		mobileLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		mobileLabel.setText(Messages.mobileLabel) ;

		Text mobileText = new Text(detailsInfoComposite, SWT.BORDER) ;
		mobileText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		mobileText.setMessage(Messages.mobileHint) ;
		bindContactDataValue(mobileText,reference, OrganizationPackage.Literals.CONTACT_DATA__MOBILE_NUMBER);
	}

	private void createPhoneField(Composite detailsInfoComposite, EReference reference) {
		Label phoneLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		phoneLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		phoneLabel.setText(Messages.phoneLabel) ;

		Text phoneText = new Text(detailsInfoComposite, SWT.BORDER) ;
		phoneText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		phoneText.setMessage(Messages.phoneHint) ;
		bindContactDataValue(phoneText,reference, OrganizationPackage.Literals.CONTACT_DATA__PHONE_NUMBER);
	}

	private void createWebSiteField(Composite detailsInfoComposite, EReference reference) {
		Label websiteLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		websiteLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		websiteLabel.setText(Messages.websiteLabel) ;

		Text websiteText = new Text(detailsInfoComposite, SWT.BORDER) ;
		websiteText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		websiteText.setMessage(Messages.websiteHint) ;
		bindContactDataValue(websiteText,reference, OrganizationPackage.Literals.CONTACT_DATA__WEBSITE);
	}

	private void createBuildingInfoFields(Composite detailsInfoComposite, EReference reference) {
		Label buildingLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		buildingLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		buildingLabel.setText(Messages.buildingLabel) ;


		Composite buildingInfo = new Composite(detailsInfoComposite, SWT.NONE) ;
		buildingInfo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		buildingInfo.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).margins(0, 0).spacing(2, 0).equalWidth(false).create()) ;

		Text buildingText = new Text(buildingInfo, SWT.BORDER) ;
		buildingText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		buildingText.setMessage(Messages.buildingHint) ;
		bindContactDataValue(buildingText,reference, OrganizationPackage.Literals.CONTACT_DATA__BUILDING);

		createPersonalRoomField(buildingInfo,reference);
	}

	private void createPersonalRoomField(Composite buildingInfo, EReference reference) {
		Label roomLabel = new Label(buildingInfo, SWT.NONE) ;
		roomLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		roomLabel.setText(Messages.roomLabel) ;

		Text roomText = new Text(buildingInfo, SWT.BORDER) ;
		roomText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		roomText.setMessage(Messages.roomHint) ;
		bindContactDataValue(roomText,reference, OrganizationPackage.Literals.CONTACT_DATA__ROOM);
	}

	private void createAddressField(Composite detailsInfoComposite, EReference reference) {
		Label adressLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		adressLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		adressLabel.setText(Messages.addressLabel) ;

		Text adressText = new Text(detailsInfoComposite, SWT.BORDER) ;
		adressText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		adressText.setMessage(Messages.addressHint) ;
		bindContactDataValue(adressText,reference, OrganizationPackage.Literals.CONTACT_DATA__ADDRESS);
	}

	private void createAddressInfoFields(Composite detailsInfoComposite, EReference reference) {
		Label cityLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		cityLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		cityLabel.setText(Messages.cityLabel) ;

		Composite addressInfo = new Composite(detailsInfoComposite, SWT.NONE) ;
		addressInfo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		addressInfo.setLayout(GridLayoutFactory.fillDefaults().numColumns(5).margins(0, 0).spacing(2, 0).equalWidth(false).create()) ;


		Text cityText = new Text(addressInfo, SWT.BORDER) ;
		cityText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		cityText.setMessage(Messages.cityHint) ;
		bindContactDataValue(cityText,reference, OrganizationPackage.Literals.CONTACT_DATA__CITY);

		createPersonalStateField(addressInfo,reference);

		createPersonalZipCodeField(addressInfo,reference);
	}

	private void createPersonalStateField(Composite addressInfo, EReference reference) {
		Label stateLabel = new Label(addressInfo, SWT.NONE) ;
		stateLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		stateLabel.setText(Messages.stateLabel) ;

		Text stateText = new Text(addressInfo, SWT.BORDER) ;
		stateText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		stateText.setMessage(Messages.stateHint) ;
		bindContactDataValue(stateText,reference, OrganizationPackage.Literals.CONTACT_DATA__STATE);
	}

	private void createPersonalZipCodeField(Composite addressInfo, EReference reference) {
		Label zipcodeLabel = new Label(addressInfo, SWT.NONE) ;
		zipcodeLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		zipcodeLabel.setText(Messages.zipCodeLabel) ;

		Text zipCodeText = new Text(addressInfo, SWT.BORDER) ;
		zipCodeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		zipCodeText.setMessage(Messages.zipCodeHint) ;
		bindContactDataValue(zipCodeText, reference, OrganizationPackage.Literals.CONTACT_DATA__ZIP_CODE);
	}

	private void createEmailField( Composite detailsInfoComposite, EReference reference) {
		Label emailLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		emailLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		emailLabel.setText(Messages.emailLabel) ;

		Text emailText = new Text(detailsInfoComposite, SWT.BORDER) ;
		emailText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		emailText.setMessage(Messages.emailHint) ;

		bindContactDataValue(emailText, reference,OrganizationPackage.Literals.CONTACT_DATA__EMAIL);

	}

	private void createCountryField(Composite detailsInfoComposite, EReference reference) {
		Label countryLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		countryLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		countryLabel.setText(Messages.countryLabel) ;

		Text countryText = new Text(detailsInfoComposite, SWT.BORDER) ;
		countryText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		countryText.setMessage(Messages.coutryHint) ;

		bindContactDataValue(countryText,reference, OrganizationPackage.Literals.CONTACT_DATA__COUNTRY);

	}

	private void bindContactDataValue(Text countryText,EReference reference, EAttribute attribute) {
		bindDataValue(countryText, attribute, reference);
	}


	private void bindDataValue(Text countryText, EAttribute attribute, EReference attribute2) {
		IObservableValue personalDataValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, attribute2);
		IObservableValue propertyUserValue = EMFObservables.observeDetailValue(Realm.getDefault(),personalDataValue,attribute );
		context.bindValue(SWTObservables.observeText(countryText, SWT.Modify), propertyUserValue);
	}



	protected Control createGeneralControl(Composite parent,Map<EAttribute, Control> widgetMap) {
		if( widgetMap == null){
			widgetMap = new HashMap<EAttribute, Control>() ;
		}else{
			widgetMap.clear() ;
		}

		Composite detailsInfoComposite = new Composite(parent, SWT.NONE) ;
		detailsInfoComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		detailsInfoComposite.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).margins(5, 5).equalWidth(false).create()) ;

		createGeneralTitleField(widgetMap, detailsInfoComposite);

		createGeneralFirstNameField(widgetMap, detailsInfoComposite);

		createGeneralLastNameField(widgetMap, detailsInfoComposite);

		createGeneralJobLabel(widgetMap, detailsInfoComposite);

		return detailsInfoComposite ;
	}

	private void createGeneralJobLabel(Map<EAttribute, Control> widgetMap,
			Composite detailsInfoComposite) {
		Label jobLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		jobLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		jobLabel.setText(Messages.jobLabel) ;

		Text jobText = new Text(detailsInfoComposite, SWT.BORDER) ;
		jobText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		jobText.setMessage(Messages.jobHint) ;
		//		widgetMap.put(OrganizationPackage.Literals.USER__JOB_TITLE, jobText) ;
		IObservableValue jobUserValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__JOB_TITLE);
		context.bindValue(SWTObservables.observeText(jobText, SWT.Modify), jobUserValue);

	}

	private void createGeneralLastNameField(Map<EAttribute, Control> widgetMap,
			Composite detailsInfoComposite) {
		Label lastName = new Label(detailsInfoComposite, SWT.NONE) ;
		lastName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		lastName.setText(Messages.lastName) ;

		Text lastNameText = new Text(detailsInfoComposite, SWT.BORDER) ;
		lastNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		lastNameText.setMessage(Messages.lastNameHint) ;
		//		widgetMap.put(OrganizationPackage.Literals.USER__LAST_NAME, lastNameText) ;

		IObservableValue lastNameUserValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__LAST_NAME);
		context.bindValue(SWTObservables.observeText(lastNameText, SWT.Modify), lastNameUserValue);

		lastNameUserValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleLastNameChange(event);
			}


		});
	}

	private void createGeneralFirstNameField(Map<EAttribute, Control> widgetMap, Composite detailsInfoComposite) {
		Label firstName = new Label(detailsInfoComposite, SWT.NONE) ;
		firstName.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		firstName.setText(Messages.firstName) ;

		Text firstNameText = new Text(detailsInfoComposite, SWT.BORDER) ;
		firstNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		firstNameText.setMessage(Messages.firstNameHint) ;
		//		widgetMap.put(OrganizationPackage.Literals.USER__FIRST_NAME, firstNameText) ;

		IObservableValue firstNameUserValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__FIRST_NAME);
		context.bindValue(SWTObservables.observeText(firstNameText, SWT.Modify), firstNameUserValue);

		firstNameUserValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleFirstNameChange(event);
			}


		});


	}

	private void createGeneralTitleField(Map<EAttribute, Control> widgetMap,
			Composite detailsInfoComposite) {
		Label titleLabel = new Label(detailsInfoComposite, SWT.NONE) ;
		titleLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		titleLabel.setText(Messages.userTitle) ;

		Text titleText = new Text(detailsInfoComposite, SWT.BORDER) ;
		titleText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		titleText.setMessage(Messages.titleHint) ;
		//		widgetMap.put(OrganizationPackage.Literals.USER__TITLE, titleText) ;

		IObservableValue titleUserValue = EMFObservables.observeDetailValue(Realm.getDefault(), userSingleSelectionObservable, OrganizationPackage.Literals.USER__TITLE);
		context.bindValue(SWTObservables.observeText(titleText, SWT.Modify), titleUserValue);

	}

	@Override
	protected void addButtonSelected() {
		User user = OrganizationFactory.eINSTANCE.createUser() ;
		user.setUserName(generateUsername()) ;
		user.setPassword(createPassword(DEFAULT_USER_PASSWORD));
		user.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType());
		for(CustomUserInfoDefinition definitions : organization.getCustomUserInfoDefinitions().getCustomUserInfoDefinition()){
			CustomUserInfoValue newValue = OrganizationFactory.eINSTANCE.createCustomUserInfoValue();
			newValue.setName(definitions.getName());
			user.getCustomUserInfoValues().getCustomUserInfoValue().add(newValue);
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

	//	public User getSelectedUser() {
	//		return selectedUser;
	//	}
	//
	//	public void setSelectedUser(User selectedUser) {
	//		this.selectedUser = selectedUser;
	//	}


	@Override
	public void createControl(Composite parent) {

		tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setLayout(GridLayoutFactory.fillDefaults().create());
		tabFolder.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		super.createControl(tabFolder);

		userTab = new TabItem(tabFolder, SWT.NONE);
		userTab.setText(Messages.listOfUsersTabTitle);
		userTab.setControl(mainComposite);

		Composite compo = addInformationComposite();

		infoTab = new TabItem(tabFolder, SWT.NONE);
		infoTab.setText(Messages.customUserInformationTabTitle);
		infoTab.setControl(compo);

	}


	private Composite addInformationComposite(){
		Composite infoCompo = new Composite(tabFolder, SWT.NONE);
		infoCompo.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		infoCompo.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create()) ;

		//add label

        labelComposite = new Composite(infoCompo, SWT.NONE);
        labelComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        labelComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;
        
        Label labelCustomUserInfo = new Label(labelComposite, SWT.WRAP );
		labelCustomUserInfo.setText(Messages.labelExplicationCustomUserInformation);
		
		final GridData labelData = new GridData();
		labelData.horizontalAlignment = SWT.FILL;
		Rectangle rect = Display.getCurrent().getClientArea();
	    labelData.widthHint = rect.width / 4;
	    labelCustomUserInfo.setLayoutData(labelData);
	    
	    labelComposite.addListener(SWT.Resize, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				Rectangle bounds = labelComposite.getBounds();
	            labelData.widthHint = bounds.width;
			}
		});
	    
		
        Composite groupComposite = new Composite(infoCompo, SWT.NONE);
        groupComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        groupComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(10,0).equalWidth(false).create()) ;
		
		// Group Default Information
		Group defaultGroup = new Group(groupComposite,  SWT.FILL);
		setDefaultUserInformationGroup(defaultGroup);

		// Group Other informations
		Group otherGroup = new Group(groupComposite,  SWT.FILL);
		setOtherGroup(otherGroup);


		return infoCompo;
	}

	protected void setOtherGroup(Group otherGroup) {

		otherGroup.setText(Messages.otherInformationGroupTitle);
		otherGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		otherGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;

		Composite otherGroupComposite = new Composite(otherGroup, SWT.NONE);
		otherGroupComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		otherGroupComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 5).equalWidth(false).create()) ;

		createCustomUserInfoTableButtonComposite(otherGroupComposite);


		createCustomUserInformationTableComposite(otherGroupComposite);

	}

	private void createCustomUserInfoTableButtonComposite(Composite otherGroupComposite) {
		// BUTTONS
		Composite buttons = new Composite(otherGroupComposite, SWT.NONE);
		buttons.setLayoutData(GridDataFactory.fillDefaults().indent(0, 25).create()) ;
		buttons.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(true).spacing(0, 2).create()) ;

		GridData gridDataButton = GridDataFactory.fillDefaults().grab(true, false).create();

		// ADD BUTTON
		Button addInfoButton = new Button(buttons, SWT.FLAT );
		addInfoButton.setLayoutData(gridDataButton);
		addInfoButton.setText(Messages.otherInformationGroupAddButton);
		addInfoButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				addCustomUserInfoDefinitionAction();
			}
		});


		Button removeInfoButton = new Button(buttons, SWT.FLAT );
		removeInfoButton.setLayoutData(gridDataButton);
		removeInfoButton.setText(Messages.otherInformationGroupRemoveButton);
		removeInfoButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				List<CustomUserInfoDefinition> definitions = ((IStructuredSelection) otherInfoTable.getSelection()).toList();
				String listDef = "";
				for(CustomUserInfoDefinition def : definitions){
					listDef = listDef + def.getName() + "\n";
				}

				listDef+="\n\n"+Messages.otherInformationGroupRemoveDialogTextWarning;
				if(!definitions.isEmpty() && MessageDialog.openQuestion(Display.getDefault().getActiveShell(),Messages.otherInformationGroupRemoveDialogTitle ,Messages.bind(Messages.otherInformationGroupRemoveDialogText,listDef))){

					customUserInfoObservableList.removeAll(definitions);
					for(User user : organization.getUsers().getUser()){
						List<CustomUserInfoValue> toRemove = new ArrayList<CustomUserInfoValue>();
						for(CustomUserInfoDefinition def : definitions){
							for(CustomUserInfoValue v : user.getCustomUserInfoValues().getCustomUserInfoValue()){
								if(v.getName().equals(def.getName())){
									toRemove.add(v);
								}
							}
						}
						user.getCustomUserInfoValues().getCustomUserInfoValue().removeAll(toRemove);
					}
				}
			}
		});
	}

	private void createCustomUserInformationTableComposite(Composite otherGroupComposite) {

		
		
		Composite tableComposite = new Composite(otherGroupComposite, SWT.NONE);
		tableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		tableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).create()) ;

		// TABLE VIEWER Custom User Definitions
		otherInfoTable = new TableViewer(tableComposite, 	SWT.H_SCROLL 		| SWT.V_SCROLL	| 
				SWT.FULL_SELECTION 	| SWT.BORDER 	| SWT.MULTI);
		otherInfoTable.getControl().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		final Table table = otherInfoTable.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		otherInfoTable.setContentProvider(new ObservableListContentProvider()) ;


		TableViewerColumn nameColumn = new TableViewerColumn(otherInfoTable, SWT.NONE);
		nameColumn.getColumn().setText(Messages.customUserInfoName+" *") ;
		nameColumn.getColumn().setWidth(100);
		customUserInformationDefinitionNameEditingSupport = new CustomUserInformationDefinitionNameEditingSupport(nameColumn.getViewer(), context);
		nameColumn.setEditingSupport(customUserInformationDefinitionNameEditingSupport);
		nameColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((CustomUserInfoDefinition)element).getName();
			}
		});

		TableViewerColumn descriptionColumn = new TableViewerColumn(otherInfoTable, SWT.NONE);
		descriptionColumn.getColumn().setText(Messages.customUserInfoDescription) ;
		descriptionColumn.getColumn().setWidth(100);
		descriptionColumn.setEditingSupport(new CustomerUserInformationDefinitionDescriptionEditingSupport(nameColumn.getViewer(), context));
		descriptionColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return ((CustomUserInfoDefinition)element).getDescription();
			}
		});

		TableColumnLayout tcLayout = new TableColumnLayout();
		tcLayout.setColumnData(table.getColumn(0), new ColumnWeightData(1));
		tcLayout.setColumnData(table.getColumn(1), new ColumnWeightData(2));
		table.getParent().setLayout(tcLayout);
	}

	public void addCustomUserInfoDefinitionAction() {


		String customUserInfoName = NamingUtils.generateNewNameCaseInsensitive(getLowerCaseExistingCustomerUserInfoNAme(), Messages.defaultCustomUserInformationName);
		String customUserInfoDescription = "";

		// add new CustomUserInfoDefinition
		CustomUserInfoDefinition customUserInfo = OrganizationFactory.eINSTANCE.createCustomUserInfoDefinition() ;
		customUserInfo.setName(customUserInfoName);
		customUserInfo.setDescription(customUserInfoDescription);
		customUserInfoObservableList.add(customUserInfo);


		// add this new CustomUserInfo as a a CustomUserInfoValue for the User
		for(User user : organization.getUsers().getUser()){
			CustomUserInfoValue newCustomUserInfoValueType = OrganizationFactory.eINSTANCE.createCustomUserInfoValue();
			newCustomUserInfoValueType.setName(customUserInfoName);
			newCustomUserInfoValueType.setValue("");

			if(user.getCustomUserInfoValues()==null){
				user.setCustomUserInfoValues(OrganizationFactory.eINSTANCE.createCustomUserInfoValuesType());
			}
			user.getCustomUserInfoValues().getCustomUserInfoValue().add(newCustomUserInfoValueType);
		}
	}

	private Set<String> getLowerCaseExistingCustomerUserInfoNAme() {
		Set<String> existingCustomUserInfoNames = new HashSet<String>();
		if(organization!=null){
			if(organization.getCustomUserInfoDefinitions() == null){
				organization.setCustomUserInfoDefinitions(OrganizationFactory.eINSTANCE.createCustomUserInfoDefinitions());
			}
			for( CustomUserInfoDefinition custom : organization.getCustomUserInfoDefinitions().getCustomUserInfoDefinition()){
				existingCustomUserInfoNames.add(custom.getName().toLowerCase());
			}
		}
		return existingCustomUserInfoNames;
	}

	protected void setDefaultUserInformationGroup(Group defaultGroup) {
		defaultGroup.setText(Messages.defaultInformationGroupTitle);
		defaultGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;
		defaultGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		Composite globalComposite = new Composite(defaultGroup, SWT.FILL);
		globalComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).create());
		globalComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		Composite tablesComposite = new Composite(globalComposite, SWT.FILL);
		tablesComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(3).equalWidth(true).create());
		tablesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		createGeneralDataTable(tablesComposite);
		createBusinessCardTable(tablesComposite);
		createPersonalDataTable(tablesComposite);

		Composite membershipsComposite = new Composite(globalComposite, SWT.NONE);
		membershipsComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		membershipsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		createMembershipsTable(membershipsComposite);
	}

	private void createGeneralDataTable(Composite tables) {
		Composite generalDataTableComposite =  new Composite(tables, SWT.NONE);
		generalDataTableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		generalDataTableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		Table generalDataTable = new Table(generalDataTableComposite, SWT.BORDER );
		generalDataTable.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		generalDataTable.setLinesVisible(true);
		generalDataTable.setHeaderVisible(true);

		String[] generalDataItems = getGeneralDataItems();
		TableColumn generalData = new TableColumn(generalDataTable, SWT.NONE );
		generalData.setText(Messages.defaultInformationGroupGeneralDataTableTitle);
		generalData.setResizable(false);

		for (int i = 0; i < generalDataItems.length; i++) {
			TableItem item = new TableItem(generalDataTable, SWT.NONE| SWT.FILL);
			item.setText(generalDataItems[i]);
		}

		addTableColumLayout(generalDataTable);
	}

	private void createBusinessCardTable(Composite tables) {
		Composite businessCardTableComposite =  new Composite(tables, SWT.NONE);
		businessCardTableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		businessCardTableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		Table businessCardTable = new Table(businessCardTableComposite, SWT.BORDER );
		businessCardTable.setLinesVisible(true);
		businessCardTable.setHeaderVisible(true);

		String[] businessCardTitles = getBusinessCardItems();
		TableColumn businessCardColumn = new TableColumn(businessCardTable, SWT.NONE);
		businessCardColumn.setText(Messages.defaultInformationGroupBusinessCardTableTitle);
		businessCardColumn.setResizable(false);

		for (int i = 0; i < businessCardTitles.length; i++) {
			TableItem item = new TableItem(businessCardTable, SWT.NONE);
			item.setText(businessCardTitles[i]);
		}
		addTableColumLayout(businessCardTable);
	}

	private void createPersonalDataTable(Composite tables) {
		Composite personalTableComposite =  new Composite(tables, SWT.NONE);
		personalTableComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create());
		personalTableComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		Table personnalTable = new Table(personalTableComposite, SWT.BORDER );
		personnalTable.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		personnalTable.setLinesVisible(true);
		personnalTable.setHeaderVisible(true);

		String[] personalTitles = getPersonalItems();
		TableColumn personalColumn = new TableColumn(personnalTable, SWT.NONE);
		personalColumn.setText(Messages.defaultInformationGroupPersonalTableTitle);
		personalColumn.setResizable(false);

		for (int i = 0; i < personalTitles.length; i++) {
			TableItem item = new TableItem(personnalTable, SWT.NONE);
			item.setText(personalTitles[i]);
		}
		addTableColumLayout(personnalTable);
	}

	private void createMembershipsTable(Composite memberships) {

		Table membershipTable = new Table(memberships, SWT.BORDER | SWT.FILL);
		membershipTable.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		membershipTable.setLinesVisible(true);
		membershipTable.setHeaderVisible(true);

		TableColumn membershipColumn = new TableColumn(membershipTable, SWT.NONE| SWT.FILL);
		membershipColumn.setText(Messages.defaultInformationGroupMembershipsTableTitle);
		membershipColumn.setResizable(false);

		TableItem descriptionMembership = new TableItem(membershipTable, SWT.NONE);
		descriptionMembership.setText(Messages.defaultInformationGroupMembershipsTableText);
		addTableColumLayout(membershipTable);
	}



	protected String[] getGeneralDataItems() {
		String[] titles = { Messages.userTitle,
				Messages.firstName,
				Messages.lastName,
				Messages.userName+" *",
				Messages.password+" *",
				Messages.jobLabel,
				Messages.manager };
		return titles;
	}

	protected String[] getBusinessCardItems() {
		String[] titles = { Messages.emailLabel,
				Messages.phoneLabel,
				Messages.mobileLabel,
				Messages.faxLabel,
				Messages.websiteLabel,
				Messages.buildingLabel,
				Messages.roomLabel,
				Messages.addressLabel,
				Messages.cityLabel,
				Messages.stateLabel,
				Messages.zipCodeLabel,
				Messages.countryLabel};
		return titles;
	}

	protected String[] getPersonalItems() {
		String[] titles = { Messages.emailLabel,
				Messages.phoneLabel,
				Messages.mobileLabel,
				Messages.faxLabel,
				Messages.websiteLabel,
				Messages.buildingLabel,
				Messages.roomLabel,
				Messages.addressLabel,
				Messages.cityLabel,
				Messages.stateLabel,
				Messages.zipCodeLabel,
				Messages.countryLabel};
		return titles;
	}



}
