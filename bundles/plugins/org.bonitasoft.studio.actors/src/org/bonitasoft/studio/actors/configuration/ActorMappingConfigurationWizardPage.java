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
package org.bonitasoft.studio.actors.configuration;

import java.util.Collection;
import java.util.List;

import org.bonitasoft.studio.actors.ActorsPlugin;
import org.bonitasoft.studio.actors.action.ExportActorMappingAction;
import org.bonitasoft.studio.actors.action.ImportActorMappingAction;
import org.bonitasoft.studio.actors.i18n.Messages;
import org.bonitasoft.studio.actors.ui.wizard.SelectGroupsWizard;
import org.bonitasoft.studio.actors.ui.wizard.SelectMembershipsWizard;
import org.bonitasoft.studio.actors.ui.wizard.SelectRolesWizard;
import org.bonitasoft.studio.actors.ui.wizard.SelectUsersWizard;
import org.bonitasoft.studio.configuration.extension.IProcessConfigurationWizardPage;
import org.bonitasoft.studio.model.actormapping.ActorMapping;
import org.bonitasoft.studio.model.actormapping.Groups;
import org.bonitasoft.studio.model.actormapping.Membership;
import org.bonitasoft.studio.model.actormapping.MembershipType;
import org.bonitasoft.studio.model.actormapping.Roles;
import org.bonitasoft.studio.model.actormapping.Users;
import org.bonitasoft.studio.model.actormapping.util.ActorMappingAdapterFactory;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * @author Romain Bioteau
 *
 */
public class ActorMappingConfigurationWizardPage extends WizardPage implements ISelectionChangedListener,IProcessConfigurationWizardPage,IDoubleClickListener {

	private Configuration configuration;
	private AbstractProcess process;
	private final ComposedAdapterFactory adapterFactory;
	private TreeViewer mappingTree;
	private Button groupButton;
	private Button roleButton;
	private Button membershipButton;
	private Button userButton;

	public ActorMappingConfigurationWizardPage() {
		super(ActorMappingConfigurationWizardPage.class.getName()) ;
		setTitle(Messages.actorMappingTitle) ;
		setDescription(Messages.actorMappingDesc) ;
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ActorMappingAdapterFactory()) ;
	}


	@Override
	public void createControl(Composite parent) {
		final Composite mainComposite = new Composite(parent, SWT.NONE) ;
		mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create()) ;
		mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).spacing(5,2).create()) ;

		final Label descriptionLabel = new Label(mainComposite,SWT.WRAP);
		descriptionLabel.setText(getDescription());
		descriptionLabel.setLayoutData(GridDataFactory.swtDefaults().grab(true, false).span(2, 1).create());

		createMappingList(mainComposite) ;
		createImportExportButtons(mainComposite) ;
		setControl(mainComposite) ;
	}




	private void createImportExportButtons(Composite mainComposite) {
		final Composite composite = new Composite(mainComposite, SWT.NONE) ;
		composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).create());

		final Button importButton = new Button(composite, SWT.PUSH);
		importButton.setText(Messages.importActorMappingFile);
		importButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create());
		importButton.addSelectionListener(new SelectionAdapter() {


			@Override
			public void widgetSelected(SelectionEvent e) {
				final ImportActorMappingAction action = new ImportActorMappingAction() ;
				action.setConfiguration(configuration);
				action.setProcess(process) ;
				action.run() ;
				mappingTree.refresh();
				getContainer().updateMessage();
			}
		}) ;

		final Button exportButton = new Button(composite, SWT.PUSH);
		exportButton.setText(Messages.exportActorMappingFile);
		exportButton.setLayoutData(GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).create()) ;
		exportButton.addSelectionListener(new SelectionAdapter() {


			@Override
			public void widgetSelected(SelectionEvent e) {
				final ExportActorMappingAction action = new ExportActorMappingAction() ;
				action.setConfiguration(configuration);
				action.setProcess(process) ;
				action.run() ;
			}
		}) ;
	}


	private void createMappingList(Composite parent) {
		Composite listComposite = new Composite(parent, SWT.NONE) ;
		listComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
		listComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(5, 0).spacing(5, 3).create()) ;

		mappingTree = new TreeViewer(listComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SINGLE | SWT.V_SCROLL) ;
		mappingTree.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true,true).create()) ;
		mappingTree.setContentProvider(new AdapterFactoryContentProvider(adapterFactory){
			@Override
			public Object[] getElements(Object object) {
				if(object instanceof Collection){
					return ((Collection) object).toArray() ;
				}
				return super.getElements(object);
			}
		}) ;
		mappingTree.setLabelProvider(new ActorMappingStyledTreeLabelProvider(adapterFactory)) ;
		mappingTree.addSelectionChangedListener(this) ;
		mappingTree.addDoubleClickListener(this);
		mappingTree.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return (element instanceof String || element instanceof MembershipType) || ((ITreeContentProvider)mappingTree.getContentProvider()).hasChildren(element);
			}
		});

		Composite buttonComposite = new Composite(listComposite, SWT.NONE) ;
		buttonComposite.setLayoutData(GridDataFactory.fillDefaults().grab(false, true).create()) ;
		buttonComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).create()) ;

		groupButton = new Button(buttonComposite, SWT.FLAT) ;
		groupButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		groupButton.setText(Messages.addGroup) ;
		groupButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				groupAction() ;
				getContainer().updateMessage() ;
			}
		}) ;
		roleButton = new Button(buttonComposite, SWT.FLAT) ;
		roleButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		roleButton.setText(Messages.addRole) ;
		roleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				roleAction() ;
				getContainer().updateMessage() ;
			}
		}) ;

		membershipButton = new Button(buttonComposite, SWT.FLAT) ;
		membershipButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		membershipButton.setText(Messages.addMembershipEtc) ;
		membershipButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				membershipAction() ;
				getContainer().updateMessage() ;
			}
		}) ;

		userButton = new Button(buttonComposite, SWT.FLAT) ;
		userButton.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create()) ;
		userButton.setText(Messages.addUser) ;
		userButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userAction() ;
				getContainer().updateMessage() ;
			}
		}) ;

		updateButtons();
	}


	protected void userAction() {
		final ActorMapping mapping = getSelectedActor();
		SelectUsersWizard wizard = new SelectUsersWizard(mapping) ;
		WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wizard) ;
		dialog.open() ;
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				mappingTree.refresh();
				mappingTree.expandToLevel(mapping.getUsers(), 2) ;
			}
		}) ;

	}


	protected void membershipAction() {
		final ActorMapping mapping = getSelectedActor();
		SelectMembershipsWizard wizard = new SelectMembershipsWizard(mapping) ;
		WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wizard) ;
		dialog.open() ;
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				mappingTree.refresh();
				mappingTree.expandToLevel(mapping.getMemberships(), 2) ;
			}
		}) ;

	}


	protected void roleAction() {
		final ActorMapping mapping = getSelectedActor();
		SelectRolesWizard wizard = new SelectRolesWizard(mapping) ;
		WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wizard) ;
		dialog.open() ;
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				mappingTree.refresh();
				mappingTree.expandToLevel(mapping.getRoles(), 2) ;
			}
		}) ;

	}


	protected void groupAction() {
		final ActorMapping mapping = getSelectedActor();
		SelectGroupsWizard wizard = new SelectGroupsWizard(mapping) ;
		WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(),wizard) ;
		dialog.open() ;
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				mappingTree.refresh();
				mappingTree.expandToLevel(mapping.getGroups(), 2) ;
			}
		}) ;
	}


	private ActorMapping getSelectedActor() {
		final TreePath treePath = ((ITreeSelection) mappingTree.getSelection()).getPaths()[0];
		return (ActorMapping) treePath.getFirstSegment();
	}


	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		updateButtons() ;
	}


	@Override
	public void updatePage(AbstractProcess process, Configuration configuration) {
		this.configuration = configuration ;
		this.process = process ;
		if(this.configuration != null && mappingTree != null && !mappingTree.getTree().isDisposed() && this.configuration.getActorMappings() != null){
			List<ActorMapping> mappings = this.configuration.getActorMappings().getActorMapping() ;
			mappingTree.setInput(mappings) ;
			mappingTree.expandAll() ;
			updateButtons() ;
		}

	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			updateButtons();
		}
	}

	private void updateButtons() {
		groupButton.setEnabled(!mappingTree.getSelection().isEmpty()) ;
		roleButton.setEnabled(!mappingTree.getSelection().isEmpty()) ;
		userButton.setEnabled(!mappingTree.getSelection().isEmpty()) ;
		membershipButton.setEnabled(!mappingTree.getSelection().isEmpty()) ;
	}


	@Override
	public String isConfigurationPageValid(Configuration conf) {
		if(conf != null && conf.getActorMappings() != null){
			for(ActorMapping mapping : conf.getActorMappings().getActorMapping()){
				if(mappingIsEmpty(mapping)){
					return Messages.bind(Messages.actorHasNoMapping, mapping.getName()) ;
				}
			}
		}
		return null;
	}


	private boolean mappingIsEmpty(ActorMapping mapping) {
		boolean hasGroup = false ;
		if(mapping.getGroups() != null){
			hasGroup = !mapping.getGroups().getGroup().isEmpty() ;
		}
		boolean hasUser = false ;
		if(mapping.getUsers() != null){
			hasUser = !mapping.getUsers().getUser().isEmpty() ;
		}
		boolean hasRole = false ;
		if(mapping.getRoles() != null){
			hasRole = !mapping.getRoles().getRole().isEmpty() ;
		}
		boolean hasMembership = false ;
		if(mapping.getMemberships() != null){
			hasMembership = !mapping.getMemberships().getMembership().isEmpty() ;
		}
		return !hasGroup && !hasMembership && !hasUser && !hasRole;
	}


	@Override
	public Image getConfigurationImage() {
		return Pics.getImage("actors_mapping.png",ActorsPlugin.getDefault());
	}


	@Override
	public boolean isDefault() {
		return true;
	}


	@Override
	public void doubleClick(DoubleClickEvent event) {
		final TreePath treePath = ((ITreeSelection) event.getSelection()).getPaths()[0];
		for(int i = treePath.getSegmentCount()-1 ; i >= 0 ; i--){
			Object selection = treePath.getSegment(i);
			if(selection instanceof Users){
				userAction();
			}else if(selection instanceof Membership){
				membershipAction();
			}else if(selection instanceof Groups){
				groupAction();
			}else if(selection instanceof Roles){
				roleAction();
			}

		}
	}


}
