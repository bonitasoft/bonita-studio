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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.BonitaDiagramSelectionManager;
import org.bonitasoft.studio.common.diagram.tools.CustomDiagramGraphicalViewerKeyHandler;
import org.bonitasoft.studio.common.editingdomain.BonitaEditingDomainUtil;
import org.bonitasoft.studio.common.editingdomain.EditingDomainResourcesDisposer;
import org.bonitasoft.studio.common.gmf.tools.PaletteToolTransferDropTargetListenerWithSelection;
import org.bonitasoft.studio.common.gmf.tools.tree.BonitaTreeViewer;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.preferences.CommonDiagramPreferencesConstants;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.form.navigator.FormNavigatorItem;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DiagramRootTreeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.l10n.DiagramUIPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.internal.parts.DirectEditKeyHandler;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.draw2d.ui.internal.parts.ScrollableThumbnailEx;
import org.eclipse.gmf.runtime.draw2d.ui.internal.parts.ThumbnailEx;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.ShowInContext;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * @generated
 */
public class FormDiagramEditor extends DiagramDocumentEditor implements IGotoMarker {

	/**
	* @generated
	*/
	public static final String ID = "org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorID"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static final String CONTEXT_ID = "org.bonitasoft.studio.model.process.diagram.form.ui.diagramContext"; //$NON-NLS-1$

	private DiagramOutlinePage outlinePage;

	/**
	* @generated
	*/
	public FormDiagramEditor() {
		super(true);
	}

	/**
	* @generated
	*/
	protected String getContextID() {
		return CONTEXT_ID;
	}

	/**
	* @generated
	*/
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		new ProcessPaletteFactory().fillPalette(root);
		return root;
	}

	/**
	* @generated
	*/
	protected PreferencesHint getPreferencesHint() {
		return FormDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	* @generated
	*/
	public String getContributorId() {
		return FormDiagramEditorPlugin.ID;
	}

	/**
	 * A diagram outline page
	 */
	class DiagramOutlinePage extends ContentOutlinePage implements IAdaptable {

		private EditPartViewer viewer;

		private PageBook pageBook;

		private Control outline;

		private Canvas overview;

		private IAction showOutlineAction, showOverviewAction;

		private boolean overviewInitialized;

		private ThumbnailEx thumbnail;

		private DisposeListener disposeListener;

		/**
		 * @param viewer
		 */
		public DiagramOutlinePage(EditPartViewer viewer) {
			super(viewer);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
		 */
		public void init(IPageSite pageSite) {
			super.init(pageSite);
			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();
			String id = ActionFactory.UNDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.REDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			id = ActionFactory.DELETE.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));
			bars.updateActionBars();

			// Toolbar refresh to solve linux defect RATLC525198
			bars.getToolBarManager().markDirty();
		}

		/**
		 * configures the outline viewer
		 */
		protected void configureOutlineViewer() {
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(getOutlineViewEditPartFactory());

			MenuManager outlineContextMenuProvider = getOutlineContextMenuProvider(getViewer());
			if (outlineContextMenuProvider != null) {
				getViewer().setContextMenu(outlineContextMenuProvider);
			}

			getViewer().setKeyHandler(getKeyHandler());
			IToolBarManager tbm = this.getSite().getActionBars().getToolBarManager();
			showOutlineAction = new Action() {

				public void run() {
					showPage(ID_OUTLINE);
				}
			};
			showOutlineAction.setImageDescriptor(DiagramUIPluginImages.DESC_OUTLINE);
			showOutlineAction.setToolTipText(DiagramUIMessages.OutlineView_OutlineTipText);
			//   tbm.add(showOutlineAction);
			showOverviewAction = new Action() {

				public void run() {
					showPage(ID_OVERVIEW);
				}
			};
			showOverviewAction.setImageDescriptor(DiagramUIPluginImages.DESC_OVERVIEW);
			showOverviewAction.setToolTipText(DiagramUIMessages.OutlineView_OverviewTipText);

			//   tbm.add(showOverviewAction);
			showPage(ID_OVERVIEW);
		}

		public void createControl(Composite parent) {
			pageBook = new PageBook(parent, SWT.NONE);
			outline = getViewer().createControl(pageBook);
			overview = new Canvas(pageBook, SWT.NONE);
			pageBook.showPage(outline);
			configureOutlineViewer();
			hookOutlineViewer();
			initializeOutlineViewer();
		}

		public void dispose() {
			unhookOutlineViewer();
			if (thumbnail != null) {
				thumbnail.deactivate();
			}
			this.overviewInitialized = false;
			super.dispose();
		}

		public Object getAdapter(Class type) {
			return null;
		}

		public Control getControl() {
			return pageBook;
		}

		/**
		 * hook the outline viewer
		 */
		protected void hookOutlineViewer() {
			getSelectionSynchronizer().addViewer(getViewer());
		}

		public IAction getShowOverivewAction() {
			return showOverviewAction;
		}

		public IAction getShowOutlineAction() {
			return showOutlineAction;
		}

		/**
		 * initialize the outline viewer
		 */
		protected void initializeOutlineViewer() {
			try {
				TransactionUtil.getEditingDomain(getDiagram()).runExclusive(new Runnable() {

					public void run() {
						getViewer().setContents(getDiagram());
					}
				});
			} catch (InterruptedException e) {
				Trace.catching(DiagramUIPlugin.getInstance(), DiagramUIDebugOptions.EXCEPTIONS_CATCHING, getClass(),
						"initializeOutlineViewer", e); //$NON-NLS-1$
				Log.error(DiagramUIPlugin.getInstance(), DiagramUIStatusCodes.IGNORED_EXCEPTION_WARNING,
						"initializeOutlineViewer", e); //$NON-NLS-1$
			}
			((BonitaTreeViewer) getViewer()).setDiagramEditPart(getDiagramEditPart());
		}

		@Override
		protected EditPartViewer getViewer() {
			if (viewer == null) {
				viewer = new BonitaTreeViewer();
			}
			return viewer;
		}

		/**
		 * initialize the overview
		 */
		protected void initializeOverview() {
			LightweightSystem lws = new LightweightSystem(overview);
			RootEditPart rep = getGraphicalViewer().getRootEditPart();
			DiagramRootEditPart root = (DiagramRootEditPart) rep;
			thumbnail = new ScrollableThumbnailEx((Viewport) root.getFigure());
			// thumbnail.setSource(root.getLayer(org.eclipse.gef.LayerConstants.PRINTABLE_LAYERS));
			thumbnail.setSource(root.getLayer(LayerConstants.SCALABLE_LAYERS));

			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {

				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getEditor().addDisposeListener(disposeListener);
			this.overviewInitialized = true;
		}

		/**
		 * show page with a specific ID, possibel values are ID_OUTLINE and
		 * ID_OVERVIEW
		 * 
		 * @param id
		 */
		protected void showPage(int id) {
			if (id == ID_OUTLINE) {
				pageBook.showPage(outline);
				getPreferenceStore().setValue(CommonDiagramPreferencesConstants.DEFAULT_OUTLINE_TYPE,
						CommonDiagramPreferencesConstants.TREE_OUTLINE_TYPE);
				if (thumbnail != null)
					thumbnail.setVisible(false);
			} else if (id == ID_OVERVIEW) {
				if (!overviewInitialized)
					initializeOverview();
				pageBook.showPage(overview);
				getPreferenceStore().setValue(CommonDiagramPreferencesConstants.DEFAULT_OUTLINE_TYPE,
						CommonDiagramPreferencesConstants.OVERVIEW_OUTLINE_TYPE);
				thumbnail.setVisible(true);
			}
		}

		/**
		 * unhook the outline viewer
		 */
		protected void unhookOutlineViewer() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (disposeListener != null && getEditor() != null && !getEditor().isDisposed())
				getEditor().removeDisposeListener(disposeListener);
		}

		/**
		 * getter for the editor conrolo
		 * 
		 * @return <code>Control</code>
		 */
		protected Control getEditor() {
			return getGraphicalViewer().getControl();
		}

	}

	/**
	 * @generated BonitaSoft
	 */
	public Object getAdapter(Class type) {
		if (type == IShowInTargetList.class) {
			return new IShowInTargetList() {
				public String[] getShowInTargetIds() {
					return new String[] { ProjectExplorer.VIEW_ID };
				}
			};
		}

		if (type == IContentOutlinePage.class) {

			TreeViewer viewer = new TreeViewer();
			viewer.setRootEditPart(new DiagramRootTreeEditPart());
			outlinePage = new DiagramOutlinePage(viewer);
			return outlinePage;
		}

		return super.getAdapter(type);
	}

	public IAction getShowOverivewAction() {
		return outlinePage.getShowOverivewAction();
	}

	public IAction getShowOutlineAction() {
		return outlinePage.getShowOutlineAction();
	}

	@Override
	protected int getDefaultOutlineViewMode() {
		return getPreferenceStore().getInt(CommonDiagramPreferencesConstants.DEFAULT_OUTLINE_TYPE);
	}

	/**
	* @generated
	*/
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput || input instanceof URIEditorInput) {
			return FormDiagramEditorPlugin.getInstance().getDocumentProvider();
		}
		return super.getDocumentProvider(input);
	}

	/**
	 *  @generated BonitaSoft
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain() {
		if (getDocumentProvider() == null) {
			return super.getEditingDomain();
		}
		IDocument document = getEditorInput() != null ? getDocumentProvider().getDocument(getEditorInput()) : null;
		if (document instanceof IDiagramDocument) {
			return ((IDiagramDocument) document).getEditingDomain();
		}

		return super.getEditingDomain();
	}

	/**
	* @generated
	*/
	protected void setDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput || input instanceof URIEditorInput) {
			setDocumentProvider(FormDiagramEditorPlugin.getInstance().getDocumentProvider());
		} else {
			super.setDocumentProvider(input);
		}
	}

	/**
	* @generated
	*/
	public void gotoMarker(IMarker marker) {
		MarkerNavigationService.getInstance().gotoMarker(this, marker);
	}

	/**
	* @generated
	*/
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	* @generated
	*/
	public void doSaveAs() {
		performSaveAs(new NullProgressMonitor());
	}

	/**
	* @generated
	*/
	protected void performSaveAs(IProgressMonitor progressMonitor) {
		Shell shell = getSite().getShell();
		IEditorInput input = getEditorInput();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input).getFile() : null;
		if (original != null) {
			dialog.setOriginalFile(original);
		}
		dialog.create();
		IDocumentProvider provider = getDocumentProvider();
		if (provider == null) {
			// editor has been programmatically closed while the dialog was open
			return;
		}
		if (provider.isDeleted(input) && original != null) {
			String message = NLS.bind(Messages.FormDiagramEditor_SavingDeletedFile, original.getName());
			dialog.setErrorMessage(null);
			dialog.setMessage(message, IMessageProvider.WARNING);
		}
		if (dialog.open() == Window.CANCEL) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IPath filePath = dialog.getResult();
		if (filePath == null) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = workspaceRoot.getFile(filePath);
		final IEditorInput newInput = new FileEditorInput(file);
		// Check if the editor is already open
		IEditorMatchingStrategy matchingStrategy = getEditorDescriptor().getEditorMatchingStrategy();
		IEditorReference[] editorRefs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (int i = 0; i < editorRefs.length; i++) {
			if (matchingStrategy.matches(editorRefs[i], newInput)) {
				MessageDialog.openWarning(shell, Messages.FormDiagramEditor_SaveAsErrorTitle,
						Messages.FormDiagramEditor_SaveAsErrorMessage);
				return;
			}
		}
		boolean success = false;
		try {
			provider.aboutToChange(newInput);
			getDocumentProvider(newInput).saveDocument(progressMonitor, newInput,
					getDocumentProvider().getDocument(getEditorInput()), true);
			success = true;
		} catch (CoreException x) {
			IStatus status = x.getStatus();
			if (status == null || status.getSeverity() != IStatus.CANCEL) {
				ErrorDialog.openError(shell, Messages.FormDiagramEditor_SaveErrorTitle,
						Messages.FormDiagramEditor_SaveErrorMessage, x.getStatus());
			}
		} finally {
			provider.changed(newInput);
			if (success) {
				setInput(newInput);
			}
		}
		if (progressMonitor != null) {
			progressMonitor.setCanceled(!success);
		}
	}

	/**
	* @generated
	*/
	public ShowInContext getShowInContext() {
		return new ShowInContext(getEditorInput(), getNavigatorSelection());
	}

	/**
	* @generated
	*/
	private ISelection getNavigatorSelection() {
		IDiagramDocument document = getDiagramDocument();
		if (document == null) {
			return StructuredSelection.EMPTY;
		}
		Diagram diagram = document.getDiagram();
		if (diagram == null || diagram.eResource() == null) {
			return StructuredSelection.EMPTY;
		}
		IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
		if (file != null) {
			FormNavigatorItem item = new FormNavigatorItem(diagram, file, false);
			return new StructuredSelection(item);
		}
		return StructuredSelection.EMPTY;
	}

	/**
	* @generated
	*/
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(this,
				getDiagramGraphicalViewer());
		getDiagramGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU, provider, getDiagramGraphicalViewer());

		getDiagramGraphicalViewer().setSelectionManager(new BonitaDiagramSelectionManager());

		KeyHandler viewerKeyHandler = new CustomDiagramGraphicalViewerKeyHandler(getDiagramGraphicalViewer())
				.setParent(getKeyHandler());
		getDiagramGraphicalViewer()
				.setKeyHandler(new DirectEditKeyHandler(getDiagramGraphicalViewer()).setParent(viewerKeyHandler));
	}

	/**
	* @generated BonitaSoft
	*/
	protected TransactionalEditingDomain createEditingDomain() {
		// Use shared editing domain with id depending on the input
		return BonitaEditingDomainUtil.getSharedEditingDomain("org.bonitasoft.studio.diagram.EditingDomain");
	}

	//React to drop requests even if shortcuts are disabled

	/**
	* @generated
	*/
	/**
	 * @generated BonitaSoft
	 * Disable Zoom on CTRL+MOUSE WHEEL
	 */
	protected void initializeGraphicalViewer() {
		getDiagramGraphicalViewer()
				.addDropTargetListener(new PaletteToolTransferDropTargetListenerWithSelection(getGraphicalViewer()));
		super.initializeGraphicalViewer();
		getDiagramGraphicalViewer().addDropTargetListener(
				new DropTargetListener(getDiagramGraphicalViewer(), LocalSelectionTransfer.getTransfer()) {

					protected Object getJavaObject(TransferData data) {
						return LocalSelectionTransfer.getTransfer().nativeToJava(data);
					}

				});
		getDiagramGraphicalViewer().addDropTargetListener(
				new DropTargetListener(getDiagramGraphicalViewer(), LocalTransfer.getInstance()) {

					protected Object getJavaObject(TransferData data) {
						return LocalTransfer.getInstance().nativeToJava(data);
					}

				});
		getGraphicalViewer().setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.CTRL), null);
	}

	/**
	* @generated
	*/
	private abstract class DropTargetListener extends DiagramDropTargetListener {

		/**
		* @generated
		*/
		public DropTargetListener(EditPartViewer viewer, Transfer xfer) {
			super(viewer, xfer);
		}

		/**
		* @generated
		*/
		protected List getObjectsBeingDropped() {
			TransferData data = getCurrentEvent().currentDataType;
			HashSet<URI> uris = new HashSet<URI>();

			Object transferedObject = getJavaObject(data);
			if (transferedObject instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) transferedObject;
				for (Iterator<?> it = selection.iterator(); it.hasNext();) {
					Object nextSelectedObject = it.next();
					if (nextSelectedObject instanceof FormNavigatorItem) {
						View view = ((FormNavigatorItem) nextSelectedObject).getView();
						nextSelectedObject = view.getElement();
					} else if (nextSelectedObject instanceof IAdaptable) {
						IAdaptable adaptable = (IAdaptable) nextSelectedObject;
						nextSelectedObject = adaptable.getAdapter(EObject.class);
					}

					if (nextSelectedObject instanceof EObject) {
						EObject modelElement = (EObject) nextSelectedObject;
						uris.add(EcoreUtil.getURI(modelElement));
					}
				}
			}

			ArrayList<EObject> result = new ArrayList<EObject>(uris.size());
			for (URI nextURI : uris) {
				EObject modelObject = getEditingDomain().getResourceSet().getEObject(nextURI, true);
				result.add(modelObject);
			}
			return result;
		}

		/**
		* @generated
		*/
		protected abstract Object getJavaObject(TransferData data);

	}

	/**
	 * @generated BonitaSoft
	 * Set a friendly tooltip on the title. 
	 */
	@Override
	public String getTitleToolTip() {
		try {
			String tooltip = "";

			DiagramEditPart diagramEditPart = this.getDiagramEditPart();
			if (diagramEditPart == null) {
				return super.getTitleToolTip();//diagramEditPart not yet exists
			}
			Form form = (Form) diagramEditPart.resolveSemanticElement();
			Element element = (Element) form.eContainer();
			if (element != null) {
				tooltip += element.getName() + "/";
			}
			return tooltip += form.getName();
		} catch (Exception e) {
			BonitaStudioLog.error(e);
		}
		//if there was a problem set the default titleToolTip
		return super.getTitleToolTip();
	}

	/**
	 * @generated BonitaSoft
	 * Allow to update the part name when the name of the diagram change 
	 */
	@Override
	public void setPartName(String partName) {
		super.setPartName(partName);
	}

	@Override
	public void dispose() {
		if (this.getEditorInput() instanceof URIEditorInput || this.getEditorInput() instanceof FileEditorInput)
			super.dispose();

		//avoid Memory leak
		if (getDiagramGraphicalViewer() != null) {
			getDiagramGraphicalViewer().deselectAll();
			if (getDiagramGraphicalViewer().getVisualPartMap() != null) {
				getDiagramGraphicalViewer().getVisualPartMap().clear();
			}
			if (getDiagramGraphicalViewer().getEditPartRegistry() != null) {
				getDiagramGraphicalViewer().getEditPartRegistry().clear();
			}
		}

		//GMF bug, avoid memory leak
		final RulerComposite rulerComposite = getRulerComposite();
		if (rulerComposite != null) {
			rulerComposite.dispose();
		}

	}

	/**
	 *  @generated BonitaSoft
	 */
	protected int getInitialPaletteSize() {
		return 166;
	}

	/**
	 *  @generated BonitaSoft
	 *  Save needed only if resource set is not in use in an other editor
	 */
	@Override
	public boolean isSaveOnCloseNeeded() {
		return isDirty() && getDiagramEditPart().resolveSemanticElement().eResource() != null
				&& !EditingDomainResourcesDisposer.isResourceUsedElseWhere(
						getDiagramEditPart().resolveSemanticElement().eResource().getResourceSet(), getEditorInput());
	}

	/**
	*  @generated Bonitasoft
	*/
	public void setReadOnly(final boolean isReadOnly) {
		enableSanityChecking(!isReadOnly);
	}

}
