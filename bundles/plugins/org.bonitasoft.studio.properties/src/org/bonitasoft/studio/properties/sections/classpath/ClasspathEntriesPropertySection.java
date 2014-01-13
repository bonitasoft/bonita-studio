///**
// * Copyright (C) 2009 BonitaSoft S.A.
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
//package org.bonitasoft.studio.properties.sections.classpath;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.bonitasoft.studio.common.emf.tools.ModelHelper;
//import org.bonitasoft.studio.common.log.BonitaStudioLog;
//import org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact;
//import org.bonitasoft.studio.common.repository.extension.ExtensionProjectUtil;
//import org.bonitasoft.studio.model.process.MainProcess;
//import org.bonitasoft.studio.properties.i18n.Messages;
//import org.bonitasoft.studio.repository.connectors.jar.AddJarsHandler;
//import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.emf.common.command.Command;
//import org.eclipse.emf.common.command.CompoundCommand;
//import org.eclipse.emf.edit.command.AddCommand;
//import org.eclipse.emf.edit.command.RemoveCommand;
//import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.viewers.CheckStateChangedEvent;
//import org.eclipse.jface.viewers.CheckboxTreeViewer;
//import org.eclipse.jface.viewers.ICheckStateListener;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.ui.PlatformUI;
//import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
//
///**
// * @author Mickael Istria
// *
// */
//public class ClasspathEntriesPropertySection extends AbstractModelerPropertySection {
//
//	protected CheckboxTreeViewer jarList;
//	private MainProcess process;
//	private ClasspathEntryCheckedStateProvider checkStateProvider;
//	private ClasspathEntriesContentProvider contentProvider;
//
//	@Override
//	public void refresh() {
//		if (getEObject() != null && jarList != null) {
//			process = getProcess();
//			jarList.setInput(process);
//			checkStateProvider.setProcess(process);
//			jarList.refresh();
//			//jarList.setCheckedElements(process.getIncludedEntries().toArray());
//		}
//	}
//
//	/**
//	 * @return
//	 */
//	protected MainProcess getProcess() {
//		return ModelHelper.getMainProcess(getEObject());
//	}
//
//	@Override
//	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
//		super.createControls(parent, aTabbedPropertySheetPage);
//		doCreateControls(parent);
//	}
//
//	private void doCreateControls(Composite parent) {
//		Composite mainComposite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
//		mainComposite.setLayout(new GridLayout(2, false));
//
//		jarList = new CheckboxTreeViewer(mainComposite, SWT.BORDER | SWT.MULTI);
//		jarList.getControl().setLayoutData(new GridData(300, 150));
//		contentProvider = getContentProvider();
//		jarList.setContentProvider(contentProvider);
//		jarList.setLabelProvider(new ClasspathEntriesLabelProvider());
//		jarList.addCheckStateListener(new ICheckStateListener() {
//			public void checkStateChanged(CheckStateChangedEvent event) {
//				updateProcessDependencies(event.getElement(), event.getChecked());
//			}
//		});
//		checkStateProvider = getCheckedStateProvider(contentProvider);
//		jarList.setCheckStateProvider(checkStateProvider);
//
//		Composite buttonsComposite = getWidgetFactory().createPlainComposite(mainComposite, SWT.NONE);
//		buttonsComposite.setLayout(new FillLayout(SWT.VERTICAL));
//		createAddJarButton(buttonsComposite);
//
//		Button refreshButton = getWidgetFactory().createButton(buttonsComposite, Messages.refresh, SWT.FLAT);
//		refreshButton.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event event) {
//				ExtensionProjectUtil.markDirty();  // A jar was added, refresh ConnectorAPI next time
//				refresh();
//			}
//		});
//	}
//
//	protected ClasspathEntryCheckedStateProvider getCheckedStateProvider(ClasspathEntriesContentProvider contentProvider) {
//		return new ClasspathEntryCheckedStateProvider(contentProvider);
//	}
//
//	protected ClasspathEntriesContentProvider getContentProvider() {
//		return new ClasspathEntriesContentProvider();
//	}
//
//
//	/**
//	 * @param buttonsComposite
//	 */
//	private void createAddJarButton(Composite buttonsComposite) {
//		Button button = getWidgetFactory().createButton(buttonsComposite, Messages.addJar, SWT.FLAT);
//		button.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event e) {
//				try {
//					String[] jars = (String[]) new AddJarsHandler(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()).execute(null);
//					CompoundCommand command = new CompoundCommand();
//					for (String jar : jars) {
//						command.append(new AddCommand(getEditingDomain(), getProcess().getIncludedEntries(), jar));						
//					}
//					getEditingDomain().getCommandStack().execute(command);
//					refresh();
//				} catch (ExecutionException ex) {
//					BonitaStudioLog.log(ex);
//				}
//			}
//		});
//	}
//
//	/**
//	 * @param checkedElement 
//	 * @param checked 
//	 * 
//	 */
//	protected void updateProcessDependencies(Object checkedElement, boolean checked) {
//
//		MainProcess process = getProcess();
//		Command command = null;
//		if (checkedElement instanceof AbstractRepositoryArtifact) {
//			String id = ((AbstractRepositoryArtifact)checkedElement).getId();
//			if (checked) {
//				command = new AddCommand(getEditingDomain(), process.getIncludedEntries(), id);
//			} else {
//				command = new RemoveCommand(getEditingDomain(), process.getIncludedEntries(), id);
//			}
//		} else if(checkedElement instanceof UnresolvedDependency){
//			if (MessageDialog.openConfirm(getPart().getSite().getShell(), Messages.confirmRemoveMissingDepTitle, Messages.confirmRemoveMissingDepMessage)) {
//				String id = ((UnresolvedDependency)checkedElement).getId();
//				if (checked) {
//					command = new AddCommand(getEditingDomain(), process.getIncludedEntries(), id);
//				} else {
//					command = new RemoveCommand(getEditingDomain(), process.getIncludedEntries(), id);
//				}
//			}
//		}else if (checkedElement instanceof String) { // category
//			if (!checkedElement.equals(ClasspathEntriesContentProvider.UNRESOLVED_DEPENDENCIES_CATEGORY) ||
//					MessageDialog.openConfirm(getPart().getSite().getShell(), Messages.confirmRemoveMissingDepTitle, Messages.confirmRemoveMissingDepMessage)) {
//				List<String> all = getAll((String)checkedElement);
//				List<String> toRemove = intersect(all, process.getIncludedEntries());
//				
//				command = checked ? 
//						new AddCommand(getEditingDomain(), process.getIncludedEntries(), all ) :
//							new RemoveCommand(getEditingDomain(), process.getIncludedEntries(), toRemove);
//			}
//		}
//
//		if (command != null) {
//			getEditingDomain().getCommandStack().execute(command);
//		}
//		jarList.refresh();
//	}
//
//	/**
//	 * @param all
//	 * @param includedEntries
//	 * @return
//	 */
//	private List<String> intersect(List<String> all, List<String> includedEntries) {
//		Set<String> res = new HashSet<String>(all);
//		for (String item : all) {
//			if (!includedEntries.contains(item)) {
//				res.remove(item);
//			}
//		}
//		return new ArrayList<String>(res);
//	}
//
//	/**
//	 * @return
//	 */
//	private List<String> getAll(String category) {
//		List<String> res = new ArrayList<String>();
//		for (Object item : contentProvider.getChildren(category)) {
//			if (item instanceof AbstractRepositoryArtifact) {
//				AbstractRepositoryArtifact artifact = (AbstractRepositoryArtifact)item;
//				res.add(artifact.getId());
//			} else if (item instanceof String) {
//				res.addAll(getAll((String)item));
//			} else if(item instanceof UnresolvedDependency){
//				res.add(((UnresolvedDependency)item).getId());
//			}
//		}
//		return res;
//	}
//
//}
