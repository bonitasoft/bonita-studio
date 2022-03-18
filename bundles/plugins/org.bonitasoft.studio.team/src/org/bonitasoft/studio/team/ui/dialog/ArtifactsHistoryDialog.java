/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.dialog;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.ui.viewer.RepositoryTreeViewer;
import org.bonitasoft.studio.team.i18n.Messages;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Baptiste Mesta
 * 
 *         Dialog that show the history of all the artifacts
 * 
 */
public class ArtifactsHistoryDialog extends Dialog {
	private static final int DEFAULT_HEIGHT = 500;
	private static final int DEFAULT_WIDTH = 700;
	private TreeViewer viewer;
	private BonitaHistoryPanel panel;

	/**
	 * @param parentShell
	 */
	public ArtifactsHistoryDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MAX | SWT.CLOSE | SWT.RESIZE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {

		SashForm sash = new SashForm(parent, SWT.HORIZONTAL);
		sash.setLayout(new FillLayout());
		sash.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).create());

		createTree(sash);
		createHistoryView(sash);
		sash.setWeights(new int[] { 30, 70 });

		
		viewer.setInput(RepositoryManager.getInstance().getCurrentRepository().getAllSharedStores());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				
				IProgressService progressManager = PlatformUI.getWorkbench().getProgressService();
				try {
					progressManager.busyCursorWhile(new IRunnableWithProgress() {

						public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							
							if (((IStructuredSelection) event.getSelection()).size() == 1) {
								final Object elem = ((IStructuredSelection) event.getSelection()).getFirstElement();
								if (elem instanceof IRepositoryFileStore) {
									final IResource resource = ((IRepositoryFileStore) elem).getResource();
									Display.getDefault().asyncExec(new Runnable() {
										
										public void run() {
											panel.setResources(new IResource[]{resource},elem);
										}
									});
								} else if (elem instanceof IRepositoryStore) {
									// TODO get the resource containing only
									// artifacts
									final IResource resource = ((IRepositoryStore) elem).getResource();
									Display.getDefault().asyncExec(new Runnable() {

										public void run() {
											panel.setResources(new IResource[]{resource},elem);
										}
									});
								}
							}
						}
					});
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return sash;
	}

	/**
	 * @param controle
	 */
	private void createHistoryView(Composite parent) {
		panel = new BonitaHistoryPanel(parent);

	}

	/**
	 * @param controle
	 */
	private void createTree(Composite composite) {
		viewer = new RepositoryTreeViewer(composite, SWT.BORDER);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.team_History);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#getInitialSize()
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}
}
