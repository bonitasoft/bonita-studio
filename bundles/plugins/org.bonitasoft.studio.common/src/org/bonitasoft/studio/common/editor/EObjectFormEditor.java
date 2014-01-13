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
package org.bonitasoft.studio.common.editor;

import java.io.IOException;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.actormapping.util.ActorMappingXMLProcessor;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Romain Bioteau
 *
 */
public class EObjectFormEditor extends FormEditor implements IPartListener, IResourceChangeListener {

	private ComposedAdapterFactory adapterFactory;
	private AdapterFactoryEditingDomain editingDomain;
	private AdapterFactoryLabelProvider labelProvider;
	private AdapterFactoryItemDelegator itemDelegator;
	private EObject modelRoot;
	protected Resource originalResource;
	protected Resource workingCopyResource;

	@Override
	public void dispose() {
		super.dispose();
		if(adapterFactory != null){
			adapterFactory.dispose() ;
		}
	}

	@Override
	public boolean isDirty() {
		if(modelRoot != null){
			try {
				ActorMappingXMLProcessor xmlProcessor = new ActorMappingXMLProcessor() ;
				Map<String, String> options = new HashMap<String, String>() ;
				options.put(XMLResource.OPTION_ENCODING, "UTF-8");
				options.put(XMLResource.OPTION_XML_VERSION, "1.0");         
				String orginalString =  xmlProcessor.saveToString(originalResource, options) ;
				String copyString =  xmlProcessor.saveToString(workingCopyResource, options) ;
				return !copyString.equals(orginalString) ;
			} catch (IOException e) {
				BonitaStudioLog.error(e) ;
			}
		}
		return false ;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		Map<String, String> options = new HashMap<String, String>() ;
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_XML_VERSION, "1.0");     
		try {
			originalResource.getContents().clear() ;
			originalResource.getContents().add(EcoreUtil.copy(workingCopyResource.getContents().get(0))) ;
			originalResource.save(options) ;
		} catch (Exception e) {
			BonitaStudioLog.error(e) ;
		} 
		editorDirtyStateChanged();
	}


	@Override
	public void doSaveAs() {

	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	protected void addPages() {

	}

	protected void initializeEditingDomain() {
		// Create an adapter factory that yields item providers.
		this.adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);


		addAdapterFactory(this.adapterFactory) ;

		// command stack that will notify this editor as commands are executed
		BasicCommandStack commandStack = new BasicCommandStack();

		// Add a listener to set the editor dirty of commands have been executed
		commandStack.addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(final EventObject event) {
				getContainer().getDisplay().asyncExec(new Runnable() {
					public void run() {
						editorDirtyStateChanged();
					}
				});
			}
		});

		// Create the editing domain with our adapterFactory and command stack.
		this.editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				commandStack, new HashMap<Resource, Boolean>());

		// These provide access to the model items, their property source and label
		this.itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
		this.labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
	}

	protected void addAdapterFactory(ComposedAdapterFactory factory) {
		factory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		factory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	public void init(IEditorSite site, IEditorInput editorInput)
			throws PartInitException {
		super.init(site, editorInput);
		initializeEditingDomain() ;
		setPartName(editorInput.getName());
		site.getPage().addPartListener(this);
		createModel();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_CHANGE);
	}

	public void createModel() {
		URI resourceURI = EditUIUtil.getURI(getEditorInput());
		try {
			// Load the resource through the editing domain.
			//
			originalResource =  editingDomain.getResourceSet().getResource(resourceURI,
					true);
		} catch (Exception e) {
			originalResource = editingDomain.getResourceSet().getResource(resourceURI,
					false);
		}

		if (originalResource != null) {
			this.modelRoot = EcoreUtil.copy(originalResource.getContents().get(0));
			workingCopyResource = editingDomain.createResource("temp.xml") ; 
			workingCopyResource.getContents().add(modelRoot) ;
		}
	}

	public AdapterFactoryEditingDomain getEditingDomain() {
		return editingDomain;
	}

	public AdapterFactoryLabelProvider getLabelProvider() {
		return labelProvider;
	}


	public AdapterFactoryItemDelegator getItemDelegator() {
		return itemDelegator;
	}
	
	@Override
	protected void initializePageSwitching() {
		super.initializePageSwitching();
		if (getContainer() instanceof CTabFolder && getPageCount() == 1) {
			((CTabFolder)getContainer()).setTabHeight(0);
			final Point point = getContainer().getSize();
			getContainer().setSize(point.x, point.y + 6);
		}
	}
	
	@Override
	protected FormToolkit createToolkit(Display display) {
		FormToolkit toolkit = new CustomFormToolkit(display) ;
		FormColors toolkitColors = toolkit.getColors();
		toolkitColors.createColor(IFormColors.TB_BG,242,242,242) ;
		toolkitColors.createColor(IFormColors.TB_BORDER,200,200,200) ;
		toolkitColors.createColor(IFormColors.H_GRADIENT_START,242,242,242) ;
		toolkitColors.createColor(IFormColors.H_GRADIENT_END,255,255,255) ;
		toolkitColors.createColor(IFormColors.H_BOTTOM_KEYLINE1,255,255,255) ;
		toolkitColors.createColor(IFormColors.H_BOTTOM_KEYLINE2,200,200,200) ;
		toolkitColors.createColor(IFormColors.BORDER,200,200,200) ;
		toolkitColors.createColor(IFormColors.TITLE,100,100,100) ;
		return toolkit;
	}

	@Override
	public void partActivated(IWorkbenchPart part) {
		if(part.equals(this)){
			IWorkbenchPage page = getSite().getPage() ;
			if(!page.isPageZoomed()){
				if(part != null){
					page.toggleZoom(part.getSite().getPage().getActivePartReference());
				}
			}
		}

	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
	
	}

	@Override
	public void partClosed(IWorkbenchPart part) {

	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		if(part.equals(this)){
			IWorkbenchPage page = getSite().getPage() ;
			if(page.isPageZoomed()){
				if(part != null){
					page.toggleZoom(page.getActivePartReference());
				}
			}
		}
	}

	@Override
	public void partOpened(IWorkbenchPart part) {

	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {

	}

	public EObject getModelRoot() {
		return modelRoot;
	}

	public AdapterFactory getAdapterFactory() {
		return this.adapterFactory;
	}

}
