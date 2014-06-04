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
import org.bonitasoft.studio.actors.validator.DisplayNameValidator;
import org.bonitasoft.studio.common.NamingUtils;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;


/**
 * @author Romain Bioteau
 *
 */
public class GroupsWizardPage extends AbstractOrganizationWizardPage {


	private final List<Membership> groupMemberShips = new ArrayList<Membership>();
	private Button addSubGroupButton;
	private IViewerObservableValue groupSingleSelectionObservable;
	private String groupPath ;
	private IObservableValue groupPathObserveValue;

	public GroupsWizardPage() {
		super(GroupsWizardPage.class.getName());
		setTitle(Messages.displayGroupsPageTitle) ;
		setDescription(Messages.displayGroupsPageDesc) ;
	}


	@Override
	protected StructuredViewer createViewer(Composite parent) {
		FilteredTree fileredTree = new FilteredTree(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION, new PatternFilter(), true) ;
		((Text)((Composite)fileredTree.getChildren()[0]).getChildren()[0]).setMessage(Messages.search) ;
		fileredTree.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).minSize(200, 200).create()) ;
		fileredTree.getViewer().setContentProvider(new GroupContentProvider()) ;
		fileredTree.forceFocus() ;

		groupSingleSelectionObservable = ViewersObservables.observeSingleSelection(fileredTree.getViewer());
		groupSingleSelectionObservable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				groupMemberShips.clear() ;
				org.bonitasoft.studio.actors.model.organization.Group selectedGroup = (org.bonitasoft.studio.actors.model.organization.Group) event.diff.getNewValue();
				if(selectedGroup != null){
					setControlEnabled(getInfoGroup(), true) ;
					groupPathObserveValue.setValue(GroupContentProvider.getGroupPath(selectedGroup));
					for(Membership m : membershipList){
						if(selectedGroup.getName() != null && selectedGroup.getName().equals(m.getGroupName())
								&& ((selectedGroup.getParentPath() != null && selectedGroup.getParentPath().equals(m.getGroupParentPath()) || (selectedGroup.getParentPath() == null && m.getGroupParentPath() == null)))){
							groupMemberShips.add(m) ;
						}
					}
					addSubGroupButton.setEnabled(true);
				}else{
					addSubGroupButton.setEnabled(false);
					setControlEnabled(getInfoGroup(), false) ;
				}

			}
		});


		return fileredTree.getViewer();
	}


	@Override
	protected void configureViewer(StructuredViewer viewer) {
		viewer.setLabelProvider(new LabelProvider(){
			@Override
			public String getText(Object element) {
				final String displayName = ((org.bonitasoft.studio.actors.model.organization.Group)element).getDisplayName();
				if(displayName == null || displayName.isEmpty()){
					return ((org.bonitasoft.studio.actors.model.organization.Group)element).getName();
				}
				return displayName;
			}
		}) ;

		if(groupList != null && getViewer() != null){
			getViewer().setInput(groupList) ;
			((TreeViewer)getViewer()).expandAll() ;
		}
	}

	@Override
	public void setOrganization(Organization organization) {
		super.setOrganization(organization);
		if(organization != null && getViewer() != null){
			getViewer().setInput(groupList) ;
			((TreeViewer)getViewer()).expandAll() ;
		}
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
	}

	protected void handleGroupParentPathChange(ValueChangeEvent event) {
		org.bonitasoft.studio.actors.model.organization.Group group = (org.bonitasoft.studio.actors.model.organization.Group) groupSingleSelectionObservable.getValue();
		org.bonitasoft.studio.actors.model.organization.Group oldGroup = EcoreUtil.copy(group) ;

		Object oldValue = event.diff.getOldValue();
		if(oldValue != null){
			oldGroup.setParentPath(oldValue.toString()) ;
			for(Membership m : membershipList){
				if(m.getGroupName().equals(group.getName()) && m.getGroupParentPath().equals(oldGroup.getParentPath())){
					m.setGroupParentPath(group.getParentPath());
				}
			}
		}
	}


	protected void handleGroupNameChange(ValueChangeEvent event,IObservableValue pathObservableValue) {
		org.bonitasoft.studio.actors.model.organization.Group group = (org.bonitasoft.studio.actors.model.organization.Group) groupSingleSelectionObservable.getValue();
		org.bonitasoft.studio.actors.model.organization.Group oldGroup = EcoreUtil.copy(group) ;
		Object oldValue = event.diff.getOldValue();
		if(oldValue != null){
			oldGroup.setName(oldValue.toString()) ;
			ITreeContentProvider provider = (ITreeContentProvider) getViewer().getContentProvider() ;
			String oldPath = GroupContentProvider.getGroupPath(oldGroup) + GroupContentProvider.GROUP_SEPARATOR ;
			String newPath = GroupContentProvider.getGroupPath(group) + GroupContentProvider.GROUP_SEPARATOR ;

			if(provider != null && provider.hasChildren(oldGroup)){
				for(Object child : provider.getChildren(oldGroup)){
					org.bonitasoft.studio.actors.model.organization.Group childGroup = (org.bonitasoft.studio.actors.model.organization.Group) child ;
					updateParentPath(childGroup, oldPath, newPath, provider) ;
					String parent = childGroup.getParentPath() + GroupContentProvider.GROUP_SEPARATOR ;
					String path = parent.replace(oldPath, newPath) ;
					if(path.endsWith(GroupContentProvider.GROUP_SEPARATOR )){
						path = path.substring(0, path.length() -1) ;
					}
					if(path.equals(GroupContentProvider.GROUP_SEPARATOR)){
						childGroup.setParentPath(null) ;
					}else{
						childGroup.setParentPath(path) ;
					}

				}
			}
			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(group) ;
			}

			pathObservableValue.setValue(GroupContentProvider.getGroupPath(group)) ;

			for(Membership m : groupMemberShips){
				m.setGroupName(group.getName()) ;
			}
		}
	}



	private void handleGroupDisplayName(ValueChangeEvent event) {
		org.bonitasoft.studio.actors.model.organization.Group group = (org.bonitasoft.studio.actors.model.organization.Group) groupSingleSelectionObservable.getValue();
		org.bonitasoft.studio.actors.model.organization.Group oldGroup = EcoreUtil.copy(group) ;
		Object oldValue = event.diff.getOldValue();
		if(oldValue != null){
			oldGroup.setDisplayName(oldValue.toString()) ;
			
			if(getViewer() != null && !getViewer().getControl().isDisposed()){
				getViewer().refresh(group) ;
			}
		}
		
	}
	
	private void updateParentPath(org.bonitasoft.studio.actors.model.organization.Group group,String pathToReplace,String newPath,ITreeContentProvider provider) {
		if(provider.hasChildren(group)){
			for(Object child : provider.getChildren(group)){
				org.bonitasoft.studio.actors.model.organization.Group childGroup = (org.bonitasoft.studio.actors.model.organization.Group) child ;
				updateParentPath(childGroup, pathToReplace, newPath, provider) ;
				String path = childGroup.getParentPath().replace(pathToReplace, newPath) ;
				if(path.endsWith(GroupContentProvider.GROUP_SEPARATOR )){
					path = path.substring(0, path.length() -1) ;
				}
				if(path != null && path.equals(GroupContentProvider.GROUP_SEPARATOR)){
					childGroup.setParentPath(null) ;
				}else{
					childGroup.setParentPath(path) ;
				}
			}
		}
	}

	@Override
	protected void configureInfoGroup(Group group) {
		group.setText(Messages.details) ;
		group.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(15, 5).spacing(10,5).create()) ;

		createNameField(group);

		createDisplayNameField(group);

		createPathField(group);

		createDescriptionField(group);

		getViewer().setSelection(new StructuredSelection()) ;
		setControlEnabled(getInfoGroup(), false);
	}


	private void createDescriptionField(Group group) {
		Label descriptionLabel = new Label(group, SWT.NONE) ;
		descriptionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.FILL).create()) ;
		descriptionLabel.setText(Messages.description) ;

		Text groupDescriptionText = new Text(group, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL) ;
		groupDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 80).create()) ;

		IObservableValue groupDescriptionValue = EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable, OrganizationPackage.Literals.GROUP__DESCRIPTION);
		context.bindValue(SWTObservables.observeText(groupDescriptionText,SWT.Modify), groupDescriptionValue);
	}


	private void createDisplayNameField(Group group) {
		Label displayNameLabel = new Label(group, SWT.NONE) ;
		displayNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		displayNameLabel.setText(Messages.displayName) ;

		Text displayNamedText = new Text(group, SWT.BORDER) ;
		displayNamedText.setMessage(Messages.groupNameExample);
		displayNamedText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

		UpdateValueStrategy displayNameStrategy = new UpdateValueStrategy();
		displayNameStrategy.setAfterGetValidator(new DisplayNameValidator());

		IObservableValue displayNameValue = EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable, OrganizationPackage.Literals.GROUP__DISPLAY_NAME);
		Binding binding = context.bindValue(SWTObservables.observeText(displayNamedText,SWT.Modify), displayNameValue, displayNameStrategy, null) ;
		ControlDecorationSupport.create(binding, SWT.LEFT);
		
		displayNameValue.addValueChangeListener(new IValueChangeListener() {
			
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleGroupDisplayName(event);
				
			}

		});

	}


	private void createPathField(Group group) {
		Label pathLabel = new Label(group, SWT.NONE) ;
		pathLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		pathLabel.setText(Messages.groupPath) ;

		Text pathText = new Text(group, SWT.BORDER | SWT.READ_ONLY) ;
		pathText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;

		IObservableValue groupParentPathValue = EMFObservables.observeDetailValue(Realm.getDefault(),groupSingleSelectionObservable, OrganizationPackage.Literals.GROUP__PARENT_PATH) ;
		groupParentPathValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleGroupParentPathChange(event);
			}
		}) ;

		context.bindValue(SWTObservables.observeText(pathText, SWT.Modify), groupPathObserveValue);
	}


	private void createNameField(Group group) {
		Label groupNameLabel = new Label(group, SWT.NONE) ;
		groupNameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END,SWT.CENTER).create()) ;
		groupNameLabel.setText(Messages.name+" *") ;

		Text groupNameText = new Text(group, SWT.BORDER) ;
		groupNameText.setMessage(Messages.groupIdExample);
		groupNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).minSize(130, SWT.DEFAULT).create()) ;

		IObservableValue groupNameValue =  EMFObservables.observeDetailValue(Realm.getDefault(), groupSingleSelectionObservable, OrganizationPackage.Literals.GROUP__NAME);
		groupPathObserveValue = PojoObservables.observeValue(this, "groupPath");
		groupNameValue.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				handleGroupNameChange(event,groupPathObserveValue);
			}
		}) ;

		UpdateValueStrategy strategy = new UpdateValueStrategy() ;
		strategy.setAfterGetValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if(value.toString().isEmpty()){
					return ValidationStatus.error(Messages.nameIsEmpty) ;
				}
				if(value.toString().length()>NAME_SIZE){
					return ValidationStatus.error(Messages.nameLimitSize);
				}
				if(value.toString().contains("/")){
					return ValidationStatus.error(Messages.illegalCharacter) ;
				}
				for(org.bonitasoft.studio.actors.model.organization.Group g : groupList){
					org.bonitasoft.studio.actors.model.organization.Group selectedGroup = (org.bonitasoft.studio.actors.model.organization.Group) groupSingleSelectionObservable.getValue();
					if(!g.equals(selectedGroup)){
						if(g.getName().equals(value)
								&&  ((g.getParentPath() != null && g.getParentPath().equals(selectedGroup.getParentPath()))
										|| (g.getParentPath() == null && selectedGroup.getParentPath() == null))){
							return ValidationStatus.error(Messages.groupNameAlreadyExistsForLevel) ;
						}
					}
				}
				return Status.OK_STATUS;
			}
		});

		Binding binding = context.bindValue(SWTObservables.observeText(groupNameText,SWT.Modify),groupNameValue,strategy,null);
		ControlDecorationSupport.create(binding, SWT.LEFT,group,new ControlDecorationUpdater(){
			@Override
			protected void update(ControlDecoration decoration, IStatus status) {
				if(groupSingleSelectionObservable.getValue() != null){
					super.update(decoration, status);
				}
				
			}
		});
	}

	@Override
	protected Button createButtons(Composite buttonComposite) {
		final Button addGroupButton = new Button(buttonComposite, SWT.FLAT) ;
		addGroupButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		addGroupButton.setText(Messages.addParentGroup) ;
		addGroupButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addRootGroupButtonSelected() ;
			}
		}) ;

		addSubGroupButton = new Button(buttonComposite, SWT.FLAT) ;
		addSubGroupButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		addSubGroupButton.setText(Messages.addSubGroup) ;
		addSubGroupButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addButtonSelected() ;
			}
		}) ;

		final Button removeButton = new Button(buttonComposite, SWT.FLAT) ;
		removeButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		removeButton.setText(Messages.remove) ;
		removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeButtonSelected();
			}
		}) ;
		removeButton.setEnabled(false) ;
		return removeButton;
	}

	protected void addRootGroupButtonSelected() {
		getViewer().setSelection(new StructuredSelection());
		addButtonSelected();
	}


	@Override
	protected void addButtonSelected() {
		org.bonitasoft.studio.actors.model.organization.Group parentGoup = (org.bonitasoft.studio.actors.model.organization.Group) ((IStructuredSelection) getViewer().getSelection()).getFirstElement() ;
		org.bonitasoft.studio.actors.model.organization.Group group = OrganizationFactory.eINSTANCE.createGroup() ;
		group.setName(generateGroupname()) ;
		group.setDisplayName(group.getName()) ;
		if(parentGoup != null){
			if(parentGoup.getParentPath() == null){
				group.setParentPath(GroupContentProvider.GROUP_SEPARATOR + parentGoup.getName()) ;
			}else{
				group.setParentPath(parentGoup.getParentPath() + GroupContentProvider.GROUP_SEPARATOR + parentGoup.getName()) ;
			}
		}
		groupList.add(group) ;
		getViewer().setInput(groupList) ;
		((TreeViewer) getViewer()).expandAll() ;
		getViewer().setSelection(new StructuredSelection(group)) ;
	}

	private String generateGroupname() {
		Set<String> names = new HashSet<String>() ;
		for(org.bonitasoft.studio.actors.model.organization.Group g : groupList){
			names.add(g.getName()) ;
		}

		return NamingUtils.generateNewName(names, Messages.defaultGroupName);
	}

	@Override
	protected void removeButtonSelected() {
		for(Object sel :  ((IStructuredSelection) getViewer().getSelection()).toList()){
			if(sel instanceof org.bonitasoft.studio.actors.model.organization.Group){
				ITreeContentProvider contentProvider = (ITreeContentProvider)getViewer().getContentProvider();
				if(contentProvider.hasChildren(sel)){
					if(groupList.contains(sel)){
						if(MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.deleteGroupTitle, Messages.deleteGroupMsg)){
							removeChildren(contentProvider,sel) ;
						}else{
							return ;
						}
					}
				}
				groupList.remove(sel) ;
			}
		}
		getViewer().setInput(groupList) ;
		selectionChanged(new SelectionChangedEvent(getViewer(),new StructuredSelection())) ;
		((TreeViewer) getViewer()).expandAll() ;
	}

	private void removeChildren(ITreeContentProvider contentProvider, Object sel) {
		if(contentProvider.hasChildren(sel)){
			for(Object child : contentProvider.getChildren(sel)){
				removeChildren(contentProvider, child) ;
				groupList.remove(child) ;
			}
		}
	}

	@Override
	protected boolean viewerSelect(Object element, String searchQuery) {
		return false ;
	}


	public String getGroupPath() {
		return groupPath;
	}


	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}


}
