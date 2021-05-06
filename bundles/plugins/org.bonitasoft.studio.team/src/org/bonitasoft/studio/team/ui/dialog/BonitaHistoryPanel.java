/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.dialog;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide.document.StorageEditorInputProxy;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.team.svn.core.SVNMessages;
import org.eclipse.team.svn.core.connector.SVNLogEntry;
import org.eclipse.team.svn.core.connector.SVNLogPath;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.history.ResourceContentStorage;
import org.eclipse.team.svn.core.operation.local.RevertOperation;
import org.eclipse.team.svn.core.operation.remote.GetFileContentOperation;
import org.eclipse.team.svn.core.operation.remote.GetLogMessagesOperation;
import org.eclipse.team.svn.core.resource.IRepositoryFile;
import org.eclipse.team.svn.core.resource.IRepositoryResource;
import org.eclipse.team.svn.core.svnstorage.SVNRemoteStorage;
import org.eclipse.team.svn.core.utility.SVNUtility;
import org.eclipse.team.svn.ui.SVNUIMessages;
import org.eclipse.team.svn.ui.extension.ExtensionsManager;
import org.eclipse.team.svn.ui.extension.factory.ICommentView;
import org.eclipse.team.svn.ui.history.AffectedPathsComposite;
import org.eclipse.team.svn.ui.history.data.SVNChangedPathData;
import org.eclipse.team.svn.ui.history.model.ILogNode;
import org.eclipse.team.svn.ui.history.model.SVNLogNode;
import org.eclipse.team.svn.ui.operation.OpenRemoteFileOperation;
import org.eclipse.team.svn.ui.preferences.SVNTeamPreferences;
import org.eclipse.team.svn.ui.utility.ColumnedViewerComparator;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.themes.ITheme;

/**
 * @author Baptiste Mesta
 * 
 *         panel that show a ShashForm with the history of the artifact set with
 *         setArtifact
 * 
 */
public class BonitaHistoryPanel extends SashForm implements IMenuListener {

	protected static final String MENU_PATHCOMPOSITE = "menu_pathcomposite";
	private static final String MENU_HISTORYTABLE = "menu_historytable";
	private SashForm innerSashForm;
	private ICommentView commentViewManager;
	private CheckboxTreeViewer historyTable;
	private ISelectionChangedListener historyTableListener;
	private IRepositoryResource[] remoteResources;
	private Map<SVNLogEntry, SVNChangedPathData[]> pathData = new HashMap<SVNLogEntry, SVNChangedPathData[]>();
	private AffectedPathsComposite pathComposite;
	protected TableViewer pathTable;
	private IAction restoreResource = new Action() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#getText()
		 */
		@Override
		public String getText() {
			// TODO Auto-generated method stub
			return Messages.team_restoreLabel;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		@Override
		public void run() {

			IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
			try {
				progressManager.run(true,false,new IRunnableWithProgress() {

					public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								if (!((IStructuredSelection) pathTable.getSelection()).isEmpty()) {
									Object element = ((IStructuredSelection) pathTable.getSelection()).getFirstElement();
									if (element instanceof SVNChangedPathData) {
										SVNChangedPathData svnPath = (SVNChangedPathData) element;
										IStructuredSelection tSelection = (IStructuredSelection) BonitaHistoryPanel.this.historyTable.getSelection();
										ILogNode selection = (ILogNode) tSelection.getFirstElement();
										long revisionNumber = selection.getRevision();
										SVNRevision revision = SVNRevision.fromNumber(svnPath.action == SVNLogPath.ChangeType.DELETED ? revisionNumber - 1
												: revisionNumber);
										// TODO not hardcoded trunk
										String path = svnPath.getFullResourcePath().substring(svnPath.getFullResourcePath().indexOf("/trunk/") + 7);
										IFile file = RepositoryManager.getInstance().getCurrentRepository().getProject().getFile(Path.fromPortableString(path));
										if (file != null) {
											InputStream backupIS = null ;
											if (file.exists()){
												try {
													backupIS = file.getContents() ;
												} catch (CoreException e1) {
													BonitaStudioLog.error(e1) ;
												} 
												try {
													file.delete(true, monitor);
												} catch (CoreException e) {
													BonitaStudioLog.error(e);
												}
											}
											IRepositoryResource repFile;
											repFile = SVNRemoteStorage.instance().asRepositoryResource(file);
											repFile.setSelectedRevision(revision);
											repFile.setPegRevision(revision);
											org.eclipse.team.svn.core.operation.remote.GetFileContentOperation op = new org.eclipse.team.svn.core.operation.remote.GetFileContentOperation(repFile);

											op.run(monitor);
											try {
												file.create(op.getContent(), true, monitor);
											} catch (CoreException e) {
												BonitaStudioLog.error(e);
											}

										
											if(file.exists()){
												MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.team_restoreOK_title, Messages.team_restoreOK_msg);
											}else{
												if(backupIS != null){
													try {
														file.setContents(backupIS, IResource.FORCE, monitor);
													} catch (CoreException e) {
														BonitaStudioLog.error(e);
													}
												}
												MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), /*Messages.team_restoreOK_title*/ "Impossible to restore to a previous version", /*Messages.team_restoreOK_msg*/"Impossible to restore to a previous version");
											}
											repository.refresh();
										}
									} else {
										// TODO not found path error

									}
								}
							}



						});

					}
				});
			} catch (InvocationTargetException e) {
				BonitaStudioLog.error(e);
			} catch (InterruptedException e) {
				BonitaStudioLog.error(e);
			}

		}

		public boolean isEnabled() {
			return !((IStructuredSelection) pathTable.getSelection()).isEmpty();
		};
	};

	private IAction revertArtifact = new Action() {

		public String getText() {
			return Messages.team_revertLabel;
		};
		public void run() {
			IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
			try {
				progressManager.busyCursorWhile(new IRunnableWithProgress() {


					public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

						Display.getDefault().asyncExec(new Runnable() {
							public void run() {
								IStructuredSelection selection = (IStructuredSelection) historyTable.getSelection();
								if (!(selection).isEmpty()) {
									Object element = selection.getFirstElement();
									if (element instanceof ILogNode) {
										ILogNode node = (ILogNode) element;
										new RevertOperation(localresources, true).run(monitor);
										org.eclipse.team.svn.core.connector.SVNRevision.Number revision = SVNRevision.Number.fromNumber(node.getRevision());
										for (int i=0;i<localresources.length;i++) {
											try {
												IResource locResource = localresources[i];
												IRepositoryResource remoteResource = remoteResources[i];
												IRepositoryResource copyRemote = SVNUtility.copyOf(remoteResource);
												copyRemote.setPegRevision(revision);
												copyRemote.setSelectedRevision(revision);
												GetFileContentOperation getRemote = new GetFileContentOperation(copyRemote);
												getRemote.run(monitor);
												InputStream backupIS = ((IFile)locResource).getContents() ; 

												((IFile)locResource).setContents(getRemote.getContent(), IResource.FORCE, monitor);
												locResource.refreshLocal(IResource.DEPTH_ZERO, monitor);
												if(artifact != null){
//													try {
//														repository.migrateArtifact(artifact, monitor) ;
//													} catch (Exception e) {
//														BonitaStudioLog.log(e) ;
//													}
//													artifact.getRepository().fireArtifactChanged(artifact);
													MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.team_revert_title, Messages.team_revert_msg);
												}else{
													if(backupIS != null){
														try {
															((IFile)locResource).setContents(backupIS, IResource.FORCE, monitor);
														} catch (CoreException e) {
															BonitaStudioLog.error(e);
														}
													}
													MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.team_restoreFail_title, Messages.team_restoreFail_msg);
												}

											} catch (CoreException e) {
												BonitaStudioLog.error(e);
											}
										}
									}
								}
							}
						});

					}
				});
			} catch (InvocationTargetException e) {
				BonitaStudioLog.error(e);
			} catch (InterruptedException e) {
				BonitaStudioLog.error(e);
			}
		};
		public boolean isEnabled() {
			return  artifact != null && !historyTable.getSelection().isEmpty();
		};
	};
	private IResource[] localresources;
	private IRepositoryFileStore artifact;
	private IRepositoryStore repository;

	/**
	 * @param parent
	 * @param style
	 */
	public BonitaHistoryPanel(Composite parent) {
		super(parent, SWT.VERTICAL);
		initComposite(this);
	}




	/**
	 * @param parent
	 * 
	 */
	private void initComposite(Composite parent) {

		this.innerSashForm = new SashForm(this, SWT.VERTICAL);

		Tree treeTable = new Tree(this.innerSashForm, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.NONE);
		treeTable.setHeaderVisible(true);
		treeTable.setLinesVisible(true);
		TableLayout layout = new TableLayout();
		treeTable.setLayout(layout);

		this.commentViewManager = ExtensionsManager.getInstance().getCurrentMessageFactory().getCommentView();
		this.commentViewManager.createCommentView(this.innerSashForm, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);

		this.historyTable = new CheckboxTreeViewer(treeTable);

		// creating a comparator now to get listeners for columns
		HistoryTableComparator comparator = new HistoryTableComparator(this.historyTable);

		// revision
		TreeColumn col = new TreeColumn(treeTable, SWT.NONE);
		col.setResizable(true);
		col.setAlignment(SWT.RIGHT);
		col.setText(SVNUIMessages.LogMessagesComposite_Revision);
		col.addSelectionListener(comparator);
		layout.addColumnData(new ColumnWeightData(17, true));

		// creation date
		col = new TreeColumn(treeTable, SWT.NONE);
		col.setResizable(true);
		col.setText(SVNUIMessages.LogMessagesComposite_Date);
		col.addSelectionListener(comparator);
		layout.addColumnData(new ColumnWeightData(22, true));

		// file count
		col = new TreeColumn(treeTable, SWT.NONE);
		col.setResizable(true);
		col.setAlignment(SWT.RIGHT);
		col.setText(SVNUIMessages.LogMessagesComposite_Changes);
		col.addSelectionListener(comparator);
		layout.addColumnData(new ColumnWeightData(6, true));

		// author
		col = new TreeColumn(treeTable, SWT.NONE);
		col.setResizable(true);
		col.setText(SVNUIMessages.LogMessagesComposite_Author);
		col.addSelectionListener(comparator);
		layout.addColumnData(new ColumnWeightData(12, true));

		// comment
		col = new TreeColumn(treeTable, SWT.NONE);
		col.setResizable(true);
		col.setText(SVNUIMessages.LogMessagesComposite_Comment);
		col.addSelectionListener(comparator);
		layout.addColumnData(new ColumnWeightData(43, true));

		// adding a comparator and initializing default sort column and
		// direction
		this.historyTable.setComparator(comparator);
		comparator.setColumnNumber(ILogNode.COLUMN_DATE);
		comparator.setReversed(true);
		this.historyTable.getTree().setSortColumn(this.historyTable.getTree().getColumn(ILogNode.COLUMN_DATE));
		this.historyTable.getTree().setSortDirection(SWT.DOWN);

		this.historyTable.setContentProvider(new ITreeContentProvider() {
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Object[]) {
					return (Object[]) inputElement;
				}
				return inputElement == null ? new Object[0] : ((ILogNode) inputElement).getChildren();
			}

			public Object[] getChildren(Object parentElement) {
				return ((ILogNode) parentElement).getChildren();
			}

			public Object getParent(Object element) {
				return ((ILogNode) element).getParent();
			}

			public boolean hasChildren(Object element) {
				return ((ILogNode) element).hasChildren();
			}

			public void dispose() {
			}
		});
		this.historyTable.setLabelProvider(new HistoryLabelProvider());

		this.historyTableListener = new ISelectionChangedListener() {
			protected ILogNode oldSelection;

			public void selectionChanged(SelectionChangedEvent event) {
				if (BonitaHistoryPanel.this.isDisposed()) {
					return;
				}
				IStructuredSelection tSelection = (IStructuredSelection) BonitaHistoryPanel.this.historyTable.getSelection();
				if (tSelection.size() > 0) {
					ILogNode selection = (ILogNode) tSelection.getFirstElement();
					if (this.oldSelection != selection) {
						String message = selection.getComment();
						if (message == null || message.length() == 0) {
							message = selection.getType() == ILogNode.TYPE_SVN ? SVNMessages.SVNInfo_NoComment : ""; //$NON-NLS-1$
						}
						BonitaHistoryPanel.this.commentViewManager.setComment(message);

						pathComposite.setInput(pathData.get(selection.getEntity()), null, null, -1);
						this.oldSelection = selection;
					}
				} else {
					BonitaHistoryPanel.this.commentViewManager.setComment(""); //$NON-NLS-1$
					pathComposite.setInput(new SVNChangedPathData[] {}, null, null, -1);
					this.oldSelection = null;
				}
			}
		};
		this.historyTable.addSelectionChangedListener(historyTableListener);
		this.historyTable.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				List<IRepositoryFile> toOpen = new ArrayList<IRepositoryFile>();
				for (IRepositoryResource repResource : remoteResources) {
					if (repResource instanceof IRepositoryFile) {
						toOpen.add((IRepositoryFile) repResource);
					}
				}

				IStructuredSelection tSelection = (IStructuredSelection) BonitaHistoryPanel.this.historyTable.getSelection();
				if (tSelection.size() > 0) {
					ILogNode selection = (ILogNode) tSelection.getFirstElement();
					long revision = selection.getRevision();
					IRepositoryFile[] repFiles = toOpen.toArray(new IRepositoryFile[] {});
					for (IRepositoryFile iRepositoryFile : repFiles) {
						iRepositoryFile.setSelectedRevision(SVNRevision.fromNumber(revision));
					}
					for(IEditorReference er : PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences()){
						try {
							IEditorInput editorInput = er.getEditorInput();
							if(editorInput instanceof StorageEditorInputProxy){
								ResourceContentStorage storage = (ResourceContentStorage) ((StorageEditorInputProxy) editorInput).getStorage() ;
								for (IRepositoryFile iRepositoryFile : toOpen) {
									if(iRepositoryFile.getUrl().equals(storage.getFullPath().toString())){
										PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeEditors(new IEditorReference[]{er}, false) ;
									}
								}
							}
						} catch (PartInitException e) {
							BonitaStudioLog.error(e) ;
						} catch (CoreException e) {
							BonitaStudioLog.error(e) ;
						}
					}
					OpenRemoteFileOperation op = new OpenRemoteFileOperation(repFiles, OpenRemoteFileOperation.OPEN_DEFAULT) ;
					op.run(new NullProgressMonitor());
				}
			}
		});
		this.historyTable.setAutoExpandLevel(2); // auto-expand all categories
		MenuManager contextMenu = new MenuManager("#PopUp", BonitaHistoryPanel.MENU_HISTORYTABLE);

		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(BonitaHistoryPanel.this);

		Menu menu = contextMenu.createContextMenu(historyTable.getTree());
		historyTable.getControl().setMenu(menu);
		pathComposite = new AffectedPathsComposite(innerSashForm, SWT.NONE) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.team.svn.ui.history.AffectedPathsComposite#createControls
			 * ()
			 */
			@Override
			protected void createControls() {
				super.createControls();
				tableViewer.getTable().getColumn(AffectedPathsComposite.COLUMN_COPIED_FROM).dispose();
				tableViewer.getTable().getColumn(AffectedPathsComposite.COLUMN_PATH).dispose();
				MenuManager contextMenu = new MenuManager("#PopUp", BonitaHistoryPanel.MENU_PATHCOMPOSITE);

				contextMenu.setRemoveAllWhenShown(true);
				contextMenu.addMenuListener(BonitaHistoryPanel.this);

				Menu menu = contextMenu.createContextMenu(tableViewer.getTable());
				tableViewer.getControl().setMenu(menu);
				BonitaHistoryPanel.this.pathTable = tableViewer;
			}
		};
		pathComposite.setResourceTreeVisible(false);

		this.innerSashForm.setWeights(new int[] { 60, 20, 20 });

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface
	 * .action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager arg0) {
		if (arg0.getId().equals(MENU_PATHCOMPOSITE)) {
			// menu on the path composite
			if (!pathTable.getSelection().isEmpty())
				restoreResource.setEnabled(!((IStructuredSelection) pathTable.getSelection()).isEmpty());
			arg0.add(restoreResource);
		} else if (arg0.getId().equals(MENU_HISTORYTABLE)) {
			// menu on the path composite
			if (!historyTable.getSelection().isEmpty()) {
				revertArtifact.setEnabled(artifact != null && !historyTable.getSelection().isEmpty());
				arg0.add(revertArtifact);
			}
		}
	}

	public void setResources(IResource[] iResources,Object el) {
		if(el instanceof IRepositoryFileStore){

			this.artifact = (IRepositoryFileStore) el;
			this.repository = artifact.getParentStore();
		}else{
			this.artifact = null;
			this.repository = (IRepositoryStore) el;
		}
		this.localresources = iResources;
		this.remoteResources = new IRepositoryResource[localresources.length];
		for (int i = 0; i < iResources.length; i++) {
			remoteResources[i] = SVNRemoteStorage.instance().asRepositoryResource(iResources[i]);
		}
		List<ILogNode> nodeList = new ArrayList<ILogNode>();
		for (IRepositoryResource repResource : remoteResources) {
			GetLogMessagesOperation op = new GetLogMessagesOperation(repResource);
			op.run(new NullProgressMonitor());
			SVNLogEntry[] messages = op.getMessages();
			if (messages != null) {
				for (int i = 0; i < messages.length; i++) {
					nodeList.add(new SVNLogNode(messages[i], null));
				}
				for (SVNLogEntry svnLogEntry : messages) {
					mapPathData(svnLogEntry);
				}
			}
		}
		historyTable.setInput(nodeList.toArray());
	}

	protected void mapPathData(SVNLogEntry key) {
		SVNChangedPathData[] pathData = new SVNChangedPathData[key.changedPaths == null ? 0 : key.changedPaths.length];
		for (int i = 0; i < pathData.length; i++) {
			String path = key.changedPaths[i].path;
			path = path.startsWith("/") ? path.substring(1) : path; //$NON-NLS-1$
			int idx = path.lastIndexOf("/"); //$NON-NLS-1$
			pathData[i] = new SVNChangedPathData(key.changedPaths[i].action, idx != -1 ? path.substring(idx + 1) : path, idx != -1 ? path.substring(0, idx)
					: "", //$NON-NLS-1$
					key.changedPaths[i].copiedFromRevision != SVNRevision.INVALID_REVISION_NUMBER ? key.changedPaths[i].copiedFromPath : "", //$NON-NLS-1$
							key.changedPaths[i].copiedFromRevision);
		}
		this.pathData.put(key, pathData);
		SVNLogEntry[] children = key.getChildren();
		if (children != null) {
			for (SVNLogEntry child : children) {
				this.mapPathData(child);
			}
		}
	}

	protected class HistoryTableComparator extends ColumnedViewerComparator {
		public HistoryTableComparator(Viewer treeViewer) {
			super(treeViewer);
		}

		public int compareImpl(Viewer viewer, Object row1, Object row2) {
			ILogNode node1 = (ILogNode) row1;
			ILogNode node2 = (ILogNode) row2;
			switch (this.column) {
			case (ILogNode.COLUMN_REVISION): {
				long rev1 = node1.getRevision();
				long rev2 = node2.getRevision();
				if (rev1 != SVNRevision.INVALID_REVISION_NUMBER && rev2 != SVNRevision.INVALID_REVISION_NUMBER) {
					return rev1 < rev2 ? -1 : rev1 > rev2 ? 1 : 0;
				}
			}
			case (ILogNode.COLUMN_DATE): {
				return node1.getTimeStamp() < node2.getTimeStamp() ? -1 : node1.getTimeStamp() > node2.getTimeStamp() ? 1 : 0;
			}
			case (ILogNode.COLUMN_CHANGES): {
				return node1.getChangesCount() < node2.getChangesCount() ? -1 : node1.getChangesCount() > node2.getChangesCount() ? 1 : 0;
			}
			case (ILogNode.COLUMN_AUTHOR): {
				return ColumnedViewerComparator.compare(node1.getAuthor(), node2.getAuthor());
			}
			case (ILogNode.COLUMN_COMMENT): {
				return ColumnedViewerComparator.compare(node1.getComment(), node2.getComment());
			}
			}
			return 0;
		}
	}

	protected class HistoryLabelProvider implements ITableLabelProvider, IFontProvider, IColorProvider {
		private Color mergedRevisionsForeground;
		private Map<ImageDescriptor, Image> images;
		protected IPropertyChangeListener configurationListener;

		public HistoryLabelProvider() {
			this.images = new HashMap<ImageDescriptor, Image>();
			this.loadConfiguration();
		}

		protected void loadConfiguration() {
			if (this.mergedRevisionsForeground != null) {
				this.mergedRevisionsForeground.dispose();
			}
			ITheme current = PlatformUI.getWorkbench().getThemeManager().getCurrentTheme();
			this.mergedRevisionsForeground = new Color(Display.getCurrent(), current.getColorRegistry().get(
					SVNTeamPreferences.fullDecorationName(SVNTeamPreferences.NAME_OF_MERGED_REVISIONS_FOREGROUND_COLOR)).getRGB());
		}

		public Image getColumnImage(Object element, int columnIndex) {
			if (columnIndex == 0) {
				ILogNode node = (ILogNode) element;
				ImageDescriptor descr = node.getImageDescriptor();
				Image retVal = this.images.get(descr);
				if (descr != null && retVal == null) {
					this.images.put(descr, retVal = descr.createImage());
				}
				return retVal;
			}
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			return ((ILogNode) element).getLabel(columnIndex, ILogNode.LABEL_TRIM, -1);
		}

		public Font getFont(Object element) {
			// return
			// ((ILogNode)element).requiresBoldFont(BonitaHistoryPanel.this.info.getCurrentRevision())
			// ? BonitaHistoryPanel.this.currentRevisionFont : null;
			return null;
		}

		public void dispose() {
			for (Image img : this.images.values()) {
				img.dispose();
			}
			this.mergedRevisionsForeground.dispose();
		}

		public boolean isLabelProperty(Object element, String property) {
			return true;
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void removeListener(ILabelProviderListener listener) {
		}

		public Color getBackground(Object element) {
			return null;
		}

		public Color getForeground(Object element) {
			ILogNode node = (ILogNode) element;
			return node.getType() == ILogNode.TYPE_SVN && node.getParent() instanceof SVNLogNode ? this.mergedRevisionsForeground : null;
		}

	}

}
