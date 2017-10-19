/*
* Copyright (C) 2009-2015 BonitaSoft S.A.
* Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
* 
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
package org.bonitasoft.studio.model.process.diagram.form.part;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.editingdomain.BonitaEditingDomainUtil;
import org.bonitasoft.studio.common.editingdomain.BonitaResourceSetInfoDelegate;
import org.bonitasoft.studio.common.editingdomain.EditingDomainResourcesDisposer;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.AbstractDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.DiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.EditorStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @generated
 */
public class ProcessDocumentProvider extends AbstractDocumentProvider implements IDiagramDocumentProvider {

	/**
	* @generated
	*/
	protected ElementInfo createElementInfo(Object element) throws CoreException {
		if (false == element instanceof FileEditorInput && false == element instanceof URIEditorInput) {
			throw new CoreException(new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0,
					NLS.bind(Messages.ProcessDocumentProvider_IncorrectInputError,
							new Object[] { element, "org.eclipse.ui.part.FileEditorInput", //$NON-NLS-1$
									"org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
					null));
		}
		IEditorInput editorInput = (IEditorInput) element;
		IDiagramDocument document = (IDiagramDocument) createDocument(editorInput);

		ResourceSetInfo info = new ResourceSetInfo(document, editorInput);
		info.setModificationStamp(computeModificationStamp(info));
		info.fStatus = null;
		return info;
	}

	/**
	* @generated BonitaSoft
	*/
	protected IDocument createDocument(Object element) throws CoreException {
		if (false == element instanceof FileEditorInput && false == element instanceof URIEditorInput) {
			throw new CoreException(new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0,
					NLS.bind(Messages.ProcessDocumentProvider_IncorrectInputError,
							new Object[] { element, "org.eclipse.ui.part.FileEditorInput", //$NON-NLS-1$
									"org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
					null));
		}
		String id = null;
		if (element instanceof FileEditorInput) {
			FileEditorInput fInput = (FileEditorInput) element;
			id = fInput.getName();
		} else if (element instanceof URIEditorInput) {
			id = URI.decode(((URIEditorInput) element).getURI().lastSegment());
		}
		IDocument document = createEmptyDocument(id);
		setDocumentContent(document, (IEditorInput) element);
		setupDocument(element, document);
		return document;
	}

	/**
	* Sets up the given document as it would be provided for the given element. The
	* content of the document is not changed. This default implementation is empty.
	* Subclasses may reimplement.
	* 
	* @param element the blue-print element
	* @param document the document to set up
	* @generated
	*/
	protected void setupDocument(Object element, IDocument document) {
		// for subclasses
	}

	/**
	* @generated
	*/
	private long computeModificationStamp(ResourceSetInfo info) {
		int result = 0;
		for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
			Resource nextResource = it.next();
			IFile file = WorkspaceSynchronizer.getFile(nextResource);
			if (file != null) {
				if (file.getLocation() != null) {
					result += file.getLocation().toFile().lastModified();
				} else {
					result += file.getModificationStamp();
				}
			}
		}
		return result;
	}

	/**
	* @param id 
	* @generated BonitaSoft
	*/
	protected IDocument createEmptyDocument(String id) {
		DiagramDocument document = new DiagramDocument();
		document.setEditingDomain(createEditingDomain(id));
		return document;
	}

	/**
	 * @generated BonitaSoft
	 */
	protected IDocument createEmptyDocument() {
		return createEmptyDocument(null);
	}

	/**
	* @generated
	*/
	private TransactionalEditingDomain createEditingDomain(String id) {
		// Bonitasoft: use a shared editing domain
		TransactionalEditingDomain editingDomain = BonitaEditingDomainUtil
				.getSharedEditingDomain("org.bonitasoft.studio.diagram.EditingDomain" + (id != null ? "." + id : ""));
		;
		/*	
		org.eclipse.emf.transaction.TransactionalEditingDomain editingDomain = org.eclipse.gmf.runtime.diagram.core.DiagramEditingDomainFactory.getInstance().createEditingDomain();
		editingDomain.setID("org.bonitasoft.studio.diagram.EditingDomain"); //$NON-NLS-1$
		final org.eclipse.emf.transaction.NotificationFilter diagramResourceModifiedFilter = org.eclipse.emf.transaction.NotificationFilter.createNotifierFilter(editingDomain.getResourceSet()).and(org.eclipse.emf.transaction.NotificationFilter.createEventTypeFilter(org.eclipse.emf.common.notify.Notification.ADD)).and(org.eclipse.emf.transaction.NotificationFilter.createFeatureFilter(org.eclipse.emf.ecore.resource.ResourceSet.class, org.eclipse.emf.ecore.resource.ResourceSet.RESOURCE_SET__RESOURCES));
		editingDomain.getResourceSet().eAdapters().add(new org.eclipse.emf.common.notify.Adapter() {
		
			private org.eclipse.emf.common.notify.Notifier myTarger;
		
			public org.eclipse.emf.common.notify.Notifier getTarget() {
				return myTarger;
			}
		
			public boolean isAdapterForType(Object type) {
				return false;
			}
		
			public void notifyChanged(org.eclipse.emf.common.notify.Notification notification) {
				if (diagramResourceModifiedFilter.matches(notification)) {
					Object value = notification.getNewValue();
					if (value instanceof org.eclipse.emf.ecore.resource.Resource) {
						((org.eclipse.emf.ecore.resource.Resource) value).setTrackingModification(true);
					}
				}
			}
		
			public void setTarget(org.eclipse.emf.common.notify.Notifier newTarget) {
				myTarger = newTarget;
			}
				
		});	
		*/
		return editingDomain;
	}

	/**
	* @generated
	*/
	protected void setDocumentContent(IDocument document, IEditorInput element) throws CoreException {
		IDiagramDocument diagramDocument = (IDiagramDocument) document;
		TransactionalEditingDomain domain = diagramDocument.getEditingDomain();
		if (element instanceof FileEditorInput) {
			IStorage storage = ((FileEditorInput) element).getStorage();
			Diagram diagram = DiagramIOUtil.load(domain, storage, true, getProgressMonitor());
			document.setContent(diagram);
		} else if (element instanceof URIEditorInput) {
			URI uri = ((URIEditorInput) element).getURI();
			Resource resource = null;
			try {
				resource = domain.getResourceSet().getResource(uri.trimFragment(), false);
				if (resource == null) {
					resource = domain.getResourceSet().createResource(uri.trimFragment());
				}
				if (!resource.isLoaded()) {
					try {
						Map options = new HashMap(GMFResourceFactory.getDefaultLoadOptions());
						// @see 171060 
						// options.put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
						resource.load(options);
					} catch (IOException e) {
						resource.unload();
						throw e;
					}
				}
				if (uri.fragment() != null) {
					EObject rootElement = resource.getEObject(uri.fragment());
					if (rootElement instanceof Diagram) {
						document.setContent((Diagram) rootElement);
						return;
					}
				} else {
					for (Iterator it = resource.getContents().iterator(); it.hasNext();) {
						Object rootElement = it.next();
						if (rootElement instanceof Diagram) {
							document.setContent((Diagram) rootElement);
							return;
						}
					}
				}
				throw new RuntimeException(Messages.ProcessDocumentProvider_NoDiagramInResourceError);
			} catch (Exception e) {
				CoreException thrownExcp = null;
				if (e instanceof CoreException) {
					thrownExcp = (CoreException) e;
				} else {
					String msg = e.getLocalizedMessage();
					thrownExcp = new CoreException(new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0,
							msg != null ? msg : Messages.ProcessDocumentProvider_DiagramLoadingError, e));
				}
				throw thrownExcp;
			}
		} else {
			throw new CoreException(new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0,
					NLS.bind(Messages.ProcessDocumentProvider_IncorrectInputError,
							new Object[] { element, "org.eclipse.ui.part.FileEditorInput", //$NON-NLS-1$
									"org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
					null));
		}
	}

	/**
	* @generated
	*/
	public long getModificationStamp(Object element) {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			return computeModificationStamp(info);
		}
		return super.getModificationStamp(element);
	}

	/**
	* @generated
	*/
	public boolean isDeleted(Object element) {
		IDiagramDocument document = getDiagramDocument(element);
		if (document != null) {
			Resource diagramResource = document.getDiagram().eResource();
			if (diagramResource != null) {
				IFile file = WorkspaceSynchronizer.getFile(diagramResource);
				return file == null || file.getLocation() == null || !file.getLocation().toFile().exists();
			}
		}
		return super.isDeleted(element);
	}

	/**
	* @generated
	*/
	public ResourceSetInfo getResourceSetInfo(Object editorInput) {
		return (ResourceSetInfo) super.getElementInfo(editorInput);
	}

	/**
	* @generated
	*/
	protected void disposeElementInfo(Object element, ElementInfo info) {
		if (info instanceof ResourceSetInfo) {
			ResourceSetInfo resourceSetInfo = (ResourceSetInfo) info;
			resourceSetInfo.dispose();
		}
		super.disposeElementInfo(element, info);
	}

	/**
	* @generated
	*/
	protected void doValidateState(Object element, Object computationContext) throws CoreException {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			LinkedList<IFile> files2Validate = new LinkedList<IFile>();
			for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = it.next();
				IFile file = WorkspaceSynchronizer.getFile(nextResource);
				if (file != null && file.isReadOnly()) {
					files2Validate.add(file);
				}
			}
			ResourcesPlugin.getWorkspace().validateEdit(
					(IFile[]) files2Validate.toArray(new IFile[files2Validate.size()]), computationContext);
		}

		super.doValidateState(element, computationContext);
	}

	/**
	* @generated
	*/
	public boolean isReadOnly(Object element) {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			if (info.isUpdateCache()) {
				try {
					updateCache(element);
				} catch (CoreException ex) {
					FormDiagramEditorPlugin.getInstance().logError(Messages.ProcessDocumentProvider_isModifiable, ex);
					// Error message to log was initially taken from org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.l10n.EditorMessages.StorageDocumentProvider_isModifiable
				}
			}
			return info.isReadOnly();
		}
		return super.isReadOnly(element);
	}

	/**
	* @generated
	*/
	public boolean isModifiable(Object element) {
		if (!isStateValidated(element)) {
			if (element instanceof FileEditorInput || element instanceof URIEditorInput) {
				return true;
			}
		}
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			if (info.isUpdateCache()) {
				try {
					updateCache(element);
				} catch (CoreException ex) {
					FormDiagramEditorPlugin.getInstance().logError(Messages.ProcessDocumentProvider_isModifiable, ex);
					// Error message to log was initially taken from org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.l10n.EditorMessages.StorageDocumentProvider_isModifiable
				}
			}
			return info.isModifiable();
		}
		return super.isModifiable(element);
	}

	/**
	* @generated
	*/
	protected void updateCache(Object element) throws CoreException {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = it.next();
				IFile file = WorkspaceSynchronizer.getFile(nextResource);
				if (file != null && file.isReadOnly()) {
					info.setReadOnly(true);
					info.setModifiable(false);
					return;
				}
			}
			info.setReadOnly(false);
			info.setModifiable(true);
			return;
		}
	}

	/**
	* @generated
	*/
	protected void doUpdateStateCache(Object element) throws CoreException {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			info.setUpdateCache(true);
		}
		super.doUpdateStateCache(element);
	}

	/**
	* @generated
	*/
	public boolean isSynchronized(Object element) {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			return info.isSynchronized();
		}
		return super.isSynchronized(element);
	}

	/**
	* @generated
	*/
	protected ISchedulingRule getResetRule(Object element) {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			LinkedList<ISchedulingRule> rules = new LinkedList<ISchedulingRule>();
			for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = it.next();
				IFile file = WorkspaceSynchronizer.getFile(nextResource);
				if (file != null) {
					rules.add(ResourcesPlugin.getWorkspace().getRuleFactory().modifyRule(file));
				}
			}
			return new MultiRule((ISchedulingRule[]) rules.toArray(new ISchedulingRule[rules.size()]));
		}
		return null;
	}

	/**
	* @generated
	*/
	protected ISchedulingRule getSaveRule(Object element) {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			LinkedList<ISchedulingRule> rules = new LinkedList<ISchedulingRule>();
			for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = it.next();
				IFile file = WorkspaceSynchronizer.getFile(nextResource);
				if (file != null) {
					rules.add(computeSchedulingRule(file));
				}
			}
			return new MultiRule((ISchedulingRule[]) rules.toArray(new ISchedulingRule[rules.size()]));
		}
		return null;
	}

	/**
	* @generated
	*/
	protected ISchedulingRule getSynchronizeRule(Object element) {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			LinkedList<ISchedulingRule> rules = new LinkedList<ISchedulingRule>();
			for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = it.next();
				IFile file = WorkspaceSynchronizer.getFile(nextResource);
				if (file != null) {
					rules.add(ResourcesPlugin.getWorkspace().getRuleFactory().refreshRule(file));
				}
			}
			return new MultiRule((ISchedulingRule[]) rules.toArray(new ISchedulingRule[rules.size()]));
		}
		return null;
	}

	/**
	* @generated
	*/
	protected ISchedulingRule getValidateStateRule(Object element) {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			LinkedList<ISchedulingRule> files = new LinkedList<ISchedulingRule>();
			for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = it.next();
				IFile file = WorkspaceSynchronizer.getFile(nextResource);
				if (file != null) {
					files.add(file);
				}
			}
			return ResourcesPlugin.getWorkspace().getRuleFactory()
					.validateEditRule((IFile[]) files.toArray(new IFile[files.size()]));
		}
		return null;
	}

	/**
	* @generated
	*/
	private ISchedulingRule computeSchedulingRule(IResource toCreateOrModify) {
		if (toCreateOrModify.exists())
			return ResourcesPlugin.getWorkspace().getRuleFactory().modifyRule(toCreateOrModify);

		IResource parent = toCreateOrModify;
		do {
			/*
			 * XXX This is a workaround for
			 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=67601
			 * IResourceRuleFactory.createRule should iterate the hierarchy
			 * itself.
			 */
			toCreateOrModify = parent;
			parent = toCreateOrModify.getParent();
		} while (parent != null && !parent.exists());

		return ResourcesPlugin.getWorkspace().getRuleFactory().createRule(toCreateOrModify);
	}

	/**
	* @generated
	*/
	protected void doSynchronize(Object element, IProgressMonitor monitor) throws CoreException {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
				Resource nextResource = it.next();
				handleElementChanged(info, nextResource, monitor);
			}
			return;
		}
		super.doSynchronize(element, monitor);
	}

	/**
	* @generated BonitaSoft
	*/
	protected void doSaveDocument(IProgressMonitor monitor, Object element, final IDocument document, boolean overwrite)
			throws CoreException {
		ResourceSetInfo info = getResourceSetInfo(element);
		if (info != null) {
			if (!overwrite && !info.isSynchronized()) {
				throw new CoreException(
						new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, IResourceStatus.OUT_OF_SYNC_LOCAL,
								Messages.ProcessDocumentProvider_UnsynchronizedFileSaveError, null));
			}
			info.stopResourceListening();
			fireElementStateChanging(element);
			try {
				monitor.beginTask(Messages.ProcessDocumentProvider_SaveDiagramTask,
						info.getResourceSet().getResources().size() + 1); //"Saving diagram"
				for (Iterator<Resource> it = info.getLoadedResourcesIterator(); it.hasNext();) {
					Resource nextResource = it.next();
					monitor.setTaskName(
							NLS.bind(Messages.ProcessDocumentProvider_SaveNextResourceTask, nextResource.getURI()));

					if (nextResource.isLoaded() && !info.getEditingDomain().isReadOnly(nextResource)
							&& nextResource.isModified()) {
						try {
							nextResource.save(ProcessDiagramEditorUtil.getSaveOptions());
						} catch (IOException e) {
							fireElementStateChangeFailed(element);
							throw new CoreException(new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID,
									EditorStatusCodes.RESOURCE_FAILURE, e.getLocalizedMessage(), null));
						}
					}
					monitor.worked(1);
				}
				monitor.done();
				info.setModificationStamp(computeModificationStamp(info));
			} catch (RuntimeException x) {
				fireElementStateChangeFailed(element);
				throw x;
			} finally {
				info.startResourceListening();
			}
		} else {
			URI newResoruceURI;
			List<IFile> affectedFiles = null;
			if (element instanceof FileEditorInput) {
				IFile newFile = ((FileEditorInput) element).getFile();
				affectedFiles = Collections.singletonList(newFile);
				newResoruceURI = URI.createPlatformResourceURI(newFile.getFullPath().toString(), true);
			} else if (element instanceof URIEditorInput) {
				newResoruceURI = ((URIEditorInput) element).getURI();
			} else {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0,
						NLS.bind(Messages.ProcessDocumentProvider_IncorrectInputError,
								new Object[] { element, "org.eclipse.ui.part.FileEditorInput", //$NON-NLS-1$
										"org.eclipse.emf.common.ui.URIEditorInput" }), //$NON-NLS-1$ 
						null));
			}
			if (false == document instanceof IDiagramDocument) {
				fireElementStateChangeFailed(element);
				throw new CoreException(new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0,
						"Incorrect document used: " + document //$NON-NLS-1$
								+ " instead of org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument", //$NON-NLS-1$
						null));
			}
			IDiagramDocument diagramDocument = (IDiagramDocument) document;
			final Resource newResource = diagramDocument.getEditingDomain().getResourceSet()
					.createResource(newResoruceURI);
			final Diagram diagramCopy = (Diagram) EcoreUtil.copy(diagramDocument.getDiagram());
			try {
				new AbstractTransactionalCommand(diagramDocument.getEditingDomain(),
						NLS.bind(Messages.ProcessDocumentProvider_SaveAsOperation, diagramCopy.getName()),
						affectedFiles) {
					protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
							throws ExecutionException {

						newResource.getContents().addAll(((EObject) document.getContent()).eResource().getContents());
						return CommandResult.newOKCommandResult();
					}
				}.execute(monitor, null);
				newResource.save(ProcessDiagramEditorUtil.getSaveOptions());
			} catch (ExecutionException e) {
				fireElementStateChangeFailed(element);
				throw new CoreException(
						new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0, e.getLocalizedMessage(), null));
			} catch (IOException e) {
				fireElementStateChangeFailed(element);
				throw new CoreException(
						new Status(IStatus.ERROR, FormDiagramEditorPlugin.ID, 0, e.getLocalizedMessage(), null));
			}
			newResource.unload();
		}
	}

	/**
	* @generated BonitaSoft
	*/
	protected void handleElementChanged(ResourceSetInfo info, Resource changedResource, IProgressMonitor monitor) {
		// BonitaSoft: Using the shared editing domain, we reload externally
		// changed resources centrally
		/*
			org.eclipse.core.resources.IFile file = org.eclipse.emf.workspace.util.WorkspaceSynchronizer.getFile(changedResource);
		if (file != null) {
			try {
				file.refreshLocal(org.eclipse.core.resources.IResource.DEPTH_INFINITE, monitor);
			} catch (org.eclipse.core.runtime.CoreException ex) {
				org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorPlugin.getInstance().logError(org.bonitasoft.studio.model.process.diagram.form.part.Messages.ProcessDocumentProvider_handleElementContentChanged, ex);
				// Error message to log was initially taken from org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.internal.l10n.EditorMessages.FileDocumentProvider_handleElementContentChanged
			}
		}
			changedResource.unload();
		*/
		fireElementContentAboutToBeReplaced(info.getEditorInput());
		removeUnchangedElementListeners(info.getEditorInput(), info);
		info.fStatus = null;
		try {
			setDocumentContent(info.fDocument, info.getEditorInput());
		} catch (CoreException e) {
			info.fStatus = e.getStatus();
		}
		if (!info.fCanBeSaved) {
			info.setModificationStamp(computeModificationStamp(info));
		}
		addUnchangedElementListeners(info.getEditorInput(), info);
		fireElementContentReplaced(info.getEditorInput());
	}

	/**
	* @generated
	*/
	protected void handleElementMoved(IEditorInput input, URI uri) {
		if (input instanceof FileEditorInput) {
			IFile newFile = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(new Path(URI.decode(uri.path())).removeFirstSegments(1));
			fireElementMoved(input, newFile == null ? null : new FileEditorInput(newFile));
			return;
		}
		// TODO: append suffix to the URI! (use diagram as a parameter)
		fireElementMoved(input, new URIEditorInput(uri));
	}

	/**
	* @generated
	*/
	public IEditorInput createInputWithEditingDomain(IEditorInput editorInput, TransactionalEditingDomain domain) {
		return editorInput;
	}

	/**
	* @generated
	*/
	public IDiagramDocument getDiagramDocument(Object element) {
		IDocument doc = getDocument(element);
		if (doc instanceof IDiagramDocument) {
			return (IDiagramDocument) doc;
		}
		return null;
	}

	/**
	* @generated
	*/
	protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
		return null;
	}

	/**
	* @generated
	*/
	public class ResourceSetInfo extends ElementInfo {

		/**
		* @generated
		*/
		private long myModificationStamp = IResource.NULL_STAMP;

		/**
		* @generated
		*/
		private Collection<Resource> myUnSynchronizedResources = new ArrayList<Resource>();

		/**
		* @generated
		*/
		private IDiagramDocument myDocument;

		/**
		* @generated
		*/
		private IEditorInput myEditorInput;

		/**
		* @generated
		*/
		private boolean myUpdateCache = true;

		/**
		* @generated
		*/
		private boolean myModifiable = false;

		/**
		* @generated
		*/
		private boolean myReadOnly = true;

		/**
		* @generated
		*/
		private ResourceSetModificationListener myResourceSetListener;

		/**
		* @generated
		*/
		private BonitaResourceSetInfoDelegate sharedResourceSetInfoDelegate;

		/**
		* @generated
		*/
		private SynchronizerDelegate synchronizerDelegate;

		/**
		* @generated
		*/
		/**
		 * @Generated Bonitasoft
		 */
		public ResourceSetInfo(IDiagramDocument document, IEditorInput editorInput) {
			super(document);
			myDocument = document;
			myEditorInput = editorInput;

			synchronizerDelegate = new SynchronizerDelegate();
			sharedResourceSetInfoDelegate = BonitaResourceSetInfoDelegate.adapt(getEditingDomain());
			sharedResourceSetInfoDelegate.addWorkspaceSynchronizerDelegate(synchronizerDelegate);
			// set the dirty flag depending of the dirty state of the resource set
			fCanBeSaved = sharedResourceSetInfoDelegate.resourceSetIsDirty();

			myResourceSetListener = new ResourceSetModificationListener(this);
			getResourceSet().eAdapters().add(myResourceSetListener);
			startResourceListening();
		}

		/**
		* @generated
		*/
		/**
		 * @Generated Bonitasoft
		 */
		public long getModificationStamp() {
			return sharedResourceSetInfoDelegate.getModificationStamp();
		}

		/**
		* @generated
		*/
		/**
		 * @Generated Bonitasoft
		 */
		public void setModificationStamp(long modificationStamp) {
			sharedResourceSetInfoDelegate.setModificationStamp(modificationStamp);
		}

		/**
		* @generated
		*/
		public TransactionalEditingDomain getEditingDomain() {
			return myDocument.getEditingDomain();
		}

		/**
		* @generated
		*/
		public ResourceSet getResourceSet() {
			return getEditingDomain().getResourceSet();
		}

		/**
		* @generated
		*/
		public Iterator<Resource> getLoadedResourcesIterator() {
			return new ArrayList<Resource>(getResourceSet().getResources()).iterator();
		}

		/**
		* @generated
		*/
		public IEditorInput getEditorInput() {
			return myEditorInput;
		}

		/**
		* @generated
		*/
		public void dispose() {
			sharedResourceSetInfoDelegate.removeWorkspaceSynchronizerDelegate(synchronizerDelegate);
			getResourceSet().eAdapters().remove(myResourceSetListener);
			EditingDomainResourcesDisposer.disposeEditorInput(getResourceSet(), myEditorInput);
		}

		/**
		* @generated
		*/
		public boolean isSynchronized() {
			return myUnSynchronizedResources.size() == 0;
		}

		/**
		* @generated
		*/
		public void setUnSynchronized(Resource resource) {
			myUnSynchronizedResources.add(resource);
		}

		/**
		* @generated
		*/
		public void setSynchronized(Resource resource) {
			myUnSynchronizedResources.remove(resource);
		}

		/**
		* @generated
		*/
		public final void stopResourceListening() {
			sharedResourceSetInfoDelegate.stopResourceListening();
		}

		/**
		* @generated
		*/
		public final void startResourceListening() {
			sharedResourceSetInfoDelegate.startResourceListening();
		}

		/**
		* @generated
		*/
		public boolean isUpdateCache() {
			return myUpdateCache;
		}

		/**
		* @generated
		*/
		public void setUpdateCache(boolean update) {
			myUpdateCache = update;
		}

		/**
		* @generated
		*/
		public boolean isModifiable() {
			return myModifiable;
		}

		/**
		* @generated
		*/
		public void setModifiable(boolean modifiable) {
			myModifiable = modifiable;
		}

		/**
		* @generated
		*/
		public boolean isReadOnly() {
			return myReadOnly;
		}

		/**
		* @generated
		*/
		public void setReadOnly(boolean readOnly) {
			myReadOnly = readOnly;
		}

		/**
		* @generated
		*/
		private class SynchronizerDelegate implements WorkspaceSynchronizer.Delegate {

			/**
			* @generated
			*/
			public void dispose() {
			}

			/**
			* @generated
			*/
			public boolean handleResourceChanged(final Resource resource) {
				synchronized (ResourceSetInfo.this) {
					if (ResourceSetInfo.this.fCanBeSaved) {
						ResourceSetInfo.this.setUnSynchronized(resource);
						return true;
					}
				}
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						handleElementChanged(ResourceSetInfo.this, resource, null);
					}
				});
				return true;
			}

			/**
			* @generated
			*/
			public boolean handleResourceDeleted(Resource resource) {
				synchronized (ResourceSetInfo.this) {
					if (ResourceSetInfo.this.fCanBeSaved) {
						ResourceSetInfo.this.setUnSynchronized(resource);
						return true;
					}
				}
				Display.getDefault().asyncExec(new Runnable() {
					public void run() {
						fireElementDeleted(ResourceSetInfo.this.getEditorInput());
					}
				});
				return true;
			}

			/**
			* @generated
			*/
			public boolean handleResourceMoved(Resource resource, final URI newURI) {
				synchronized (ResourceSetInfo.this) {
					if (ResourceSetInfo.this.fCanBeSaved) {
						ResourceSetInfo.this.setUnSynchronized(resource);
						return true;
					}
				}
				if (myDocument.getDiagram().eResource() == resource) {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							handleElementMoved(ResourceSetInfo.this.getEditorInput(), newURI);
						}
					});
				} else {
					handleResourceDeleted(resource);
				}
				return true;
			}

		}

	}

	/**
	* @generated
	*/
	private class ResourceSetModificationListener extends EContentAdapter {

		/**
		* @generated
		*/
		private NotificationFilter myModifiedFilter;

		/**
		* @generated
		*/
		private ResourceSetInfo myInfo;

		/**
		* @generated
		*/
		public ResourceSetModificationListener(ResourceSetInfo info) {
			myInfo = info;
			myModifiedFilter = NotificationFilter.createEventTypeFilter(Notification.SET)
					.or(NotificationFilter.createEventTypeFilter(Notification.UNSET))
					.and(NotificationFilter.createFeatureFilter(Resource.class, Resource.RESOURCE__IS_MODIFIED));
		}

		/**
		* @generated
		*/
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof ResourceSet) {
				super.notifyChanged(notification);
			}
			if (!notification.isTouch() && myModifiedFilter.matches(notification)) {
				if (notification.getNotifier() instanceof Resource) {
					Resource resource = (Resource) notification.getNotifier();
					if (resource.isLoaded()) {
						boolean modified = false;
						for (Iterator /*<org.eclipse.emf.ecore.resource.Resource>*/ it = myInfo
								.getLoadedResourcesIterator(); it.hasNext() && !modified;) {
							Resource nextResource = (Resource) it.next();
							if (nextResource.isLoaded()) {
								modified = nextResource.isModified();
							}
						}
						boolean dirtyStateChanged = false;
						synchronized (myInfo) {
							if (modified != myInfo.fCanBeSaved) {
								myInfo.fCanBeSaved = modified;
								dirtyStateChanged = true;
							}
							if (!resource.isModified()) {
								myInfo.setSynchronized(resource);
							}
						}
						if (dirtyStateChanged) {
							fireElementDirtyStateChanged(myInfo.getEditorInput(), modified);

							if (!modified) {
								myInfo.setModificationStamp(computeModificationStamp(myInfo));
							}
						}
					}
				}
			}
		}

	}

}
