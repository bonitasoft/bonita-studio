///**
// * Copyright (C) 2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.properties.sections.resources;
//
//import java.io.File;
//
//import org.bonitasoft.studio.common.emf.tools.ModelHelper;
//import org.bonitasoft.studio.common.log.BonitaStudioLog;
//import org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact;
//import org.bonitasoft.studio.model.process.AbstractProcess;
//import org.bonitasoft.studio.model.process.Lane;
//import org.bonitasoft.studio.model.process.ProcessPackage;
//import org.bonitasoft.studio.model.process.ResourceContainer;
//import org.bonitasoft.studio.properties.i18n.Messages;
//import org.bonitasoft.studio.repository.connectors.jar.AbstractJARArtifact;
//import org.bonitasoft.studio.repository.connectors.jar.JarRepository;
//import org.bonitasoft.studio.repository.connectors.jar.ManageJarsHandler;
//import org.bonitasoft.studio.repository.connectors.jar.ProvidedJarRepository;
//import org.bonitasoft.studio.repository.validators.ValidatorRepository;
//import org.bonitasoft.studio.repository.validators.ValidatorRepositoryArtifact;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.core.resources.IContainer;
//import org.eclipse.emf.common.command.CompoundCommand;
//import org.eclipse.emf.ecore.EAttribute;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.edit.command.AddCommand;
//import org.eclipse.emf.edit.command.RemoveCommand;
//import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
//import org.eclipse.jface.viewers.CheckStateChangedEvent;
//import org.eclipse.jface.viewers.ICheckStateListener;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
//import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
//
///**
// * @author Aurelien Pupier
// */
//public class ApplicationDependenciesPropertySection extends AbstractModelerPropertySection {
//	
//	
//	private ContainerCheckedTreeViewer tv;
//	private ICheckStateListener checkStateListener;
//	private ApplicationDependenciesCheckStateProvider checkStateProvider;
//
//	/**
//	 * @author Mickael Istria
//	 * 
//	 */
//	private final class ApplicationTreeCheckStateListener implements ICheckStateListener {
//		public void checkStateChanged(CheckStateChangedEvent event) {
//			// If the item is checked . . .
//			if (event.getElement() instanceof AbstractRepositoryArtifact) {
//				checkArtifact((AbstractRepositoryArtifact) event.getElement(), event.getChecked());
//			} else if (event.getElement().equals(ApplicationDependenciesTreeContentProvider.LIB_CATEGORY)) {
//				for (AbstractJARArtifact artifact : JarRepository.getInstance().getAllArtifacts()) {
//					checkArtifact(artifact, event.getChecked());
//				}
//			} else if (event.getElement().equals(ApplicationDependenciesTreeContentProvider.VALIDATOR_CATEGORY)) {
//				for (ValidatorRepositoryArtifact artifact : ValidatorRepository.getInstance().getAllArtifacts()) {
//					checkArtifact(artifact, event.getChecked());
//				}
//			} else if (event.getElement() instanceof String) {
//				checkFilePath((String) event.getElement(), event.getChecked());
//			}
//		}
//	}
//	
//	/**
//	 * When a item in the tree is checked: check or uncheck the subTree create
//	 * or remove resfolders and resfiles
//	 * 
//	 * @param textAnnotation
//	 *            the checked file
//	 * @param checked
//	 *            check or uncheck
//	 */
//	private void checkFilePath(String filePath, boolean checked) {
//		File file = new File(filePath);
//		if (!file.exists()) {// ProvidedJar
//			IContainer libFolder = ProvidedJarRepository.getInstance().getProject().getFolder(
//					ProvidedJarRepository.getInstance().getLibFolder() + File.separatorChar + filePath);
//			if (libFolder.exists()) {
//				for (AbstractJARArtifact jarArtifact : ProvidedJarRepository.getInstance().getAllArtifacts(libFolder)) {
//					checkArtifact(jarArtifact, checked);
//				}
//			}
//
//		}
//	}
//
//	
//	private void checkArtifact(AbstractRepositoryArtifact element, boolean checked) {
//		CompoundCommand cc = new CompoundCommand();
//		EAttribute eAttribute = null;
//		if (element instanceof AbstractJARArtifact) {
//			eAttribute = ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_JARS;
//		} else if (element instanceof ValidatorRepositoryArtifact) {
//			eAttribute = ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_VALIDATORS;
//		}
//
//		if (checked) {
//			cc.append(new AddCommand(getEditingDomain(), resourceContainer, eAttribute, element.getId()));
//		} else {
//			cc.append(new RemoveCommand(getEditingDomain(), resourceContainer, eAttribute, element.getId()));
//		}
//		getEditingDomain().getCommandStack().execute(cc);
//	}
//	
//	private ResourceContainer resourceContainer;
//	private ApplicationDependenciesTreeContentProvider apppliDepsTreeContentProvider;
//	
//	@Override
//	public void createControls(Composite parent,
//			TabbedPropertySheetPage aTabbedPropertySheetPage) {
//		super.createControls(parent, aTabbedPropertySheetPage);
//		Composite mainComposite = getWidgetFactory().createComposite(parent, SWT.NONE);
//		GridLayout layout = new GridLayout(2, false);
//		mainComposite.setLayout(layout);
//		createTree(mainComposite);
//		createButtons(mainComposite);
//	}
//	
//	private void createTree(Composite parent) {
//
//		// Create the tree viewer to display the file tree
//		tv = new ContainerCheckedTreeViewer(parent);
//		GridData treeGridData = new GridData(GridData.FILL, GridData.FILL, false, false, 1, 5);
//		treeGridData.heightHint = 150;
//		treeGridData.widthHint = 300;
//		tv.getTree().setLayoutData(treeGridData);
//		apppliDepsTreeContentProvider = new ApplicationDependenciesTreeContentProvider();
//		tv.setContentProvider(apppliDepsTreeContentProvider);
//		ResourceTreeLabelProvider fileTreeLabelProvider = new ResourceTreeLabelProvider();
//		tv.setLabelProvider(fileTreeLabelProvider);
//
//		// When user checks a checkbox in the tree, check all its children
//		checkStateListener = new ApplicationTreeCheckStateListener();
//
//		tv.addCheckStateListener(checkStateListener);
//		checkStateProvider = new ApplicationDependenciesCheckStateProvider(apppliDepsTreeContentProvider);
//		checkStateProvider.setResourceContainer((ResourceContainer) resourceContainer);
//		tv.setCheckStateProvider(checkStateProvider);
//
//	}
//
//	private void createButtons(Composite parent) {
//		Composite buttonsComposite = this.getWidgetFactory().createPlainComposite(parent, SWT.NONE);
//		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));
//		buttonsComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 2));
//
//		// add a resource folder in the tree
//		Button addJar = getWidgetFactory().createButton(buttonsComposite, Messages.addJar, SWT.FLAT);
//		addJar.addSelectionListener(new SelectionAdapter() {
//			/* (non-Javadoc)
//			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
//			 */
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				try {
//					new ManageJarsHandler().execute(null);
//					refresh();
//				} catch (ExecutionException e1) {
//					BonitaStudioLog.log(e1);
//				}
//			}
//		});
//	}
//
//	
//	/**
//	 * if the EObject has changed refresh all
//	 */
//	public void refresh() {
//		if (getEObject() != null) {
//			// if it's a lane take the eContainer (the pool)
//			if (getEObject() instanceof Lane) {
//				resourceContainer = (ResourceContainer) getEObject().eContainer();
//			} else if (getEObject() instanceof ResourceContainer) {
//				resourceContainer = (ResourceContainer) getEObject();
//			}
//			tv.setInput(resourceContainer);
//			checkStateProvider.setResourceContainer((ResourceContainer) resourceContainer);
//		}
//
//		tv.refresh();
//	}
//	
//	
//	protected AbstractProcess getAbstractProcess() {
//		EObject eo = getEObject();
//		if (eo instanceof AbstractProcess) {
//			return (AbstractProcess) eo;
//		} else {
//			return ModelHelper.getParentProcess(eo);
//		}
//	}
//	
//}
