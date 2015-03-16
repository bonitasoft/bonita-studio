/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 */

package org.bonitasoft.studio.application.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.importer.ImporterRegistry;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

/**
 * @author Baptiste Mesta
 *         Handler that launch a dialog to import artifacts from a zip
 */
public class ImportArtifactsFromZipHandler extends AbstractHandler {

    public static final String FILE_EXTENSION_OLD = ".bosworkspace";

    public static final String FILE_EXTENSION = ".bos";

    @Override
    public Object execute(final ExecutionEvent event) throws ExecutionException {
        final FileDialog dialog = new FileDialog(Display.getDefault().getActiveShell(), SWT.OPEN);
        dialog.setText(Messages.importedRepository_title);
        dialog.setFilterExtensions(new String[] { "*" + FILE_EXTENSION }); //$NON-NLS-1$
        final String workspaceArchiveFile = dialog.open();
        if (workspaceArchiveFile != null) {
            final IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            final IRunnableWithProgress runnable = new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    final ToProcProcessor importer = ImporterRegistry.getInstance().createImporterFor(workspaceArchiveFile);
                    try {
                        importer.createDiagram(new File(workspaceArchiveFile).toURL(), monitor);
                    } catch (final MalformedURLException e) {
                        throw new InvocationTargetException(e);
                    } catch (final Exception e) {
                        throw new InvocationTargetException(e);
                    }
                }
            };

            try {
                progressService.run(true, false, runnable);
            } catch (final Exception e) {
                new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.importRepositoryFailedTitle, Messages.importRepositoryFailedMsg,
                        new org.eclipse.core.runtime.Status(IStatus.ERROR, "org.bonitasoft.studio.repository.ex", e.getMessage(), e), IStatus.ERROR).open();
                BonitaStudioLog.error(e);
            }
            PlatformUtil.openIntro();

        }
        return null;
    }

    // private void showResult(AbstractRepositoryArtifact[] importedArtifacts, String workspaceArchiveFile) {
    // if(importedArtifacts.length>0){
    // String message = Messages.importedArtifacts_message;
    // final StringBuilder stringBuilder = new StringBuilder(message);
    // for (AbstractRepositoryArtifact artifact : importedArtifacts) {
    // stringBuilder.append("\n");
    // stringBuilder.append(artifact.getRepository().getName()+": "+artifact.getName());
    // }
    //
    // MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), Messages.importedArtifacts_title, null, stringBuilder.toString(),
    // MessageDialog.INFORMATION, new String[] { IDialogConstants.OK_LABEL }, 0){
    // protected org.eclipse.swt.widgets.Control createMessageArea(org.eclipse.swt.widgets.Composite composite) {
    // // create message
    // if (message != null) {
    // ScrolledComposite c2 = new ScrolledComposite(composite, SWT.BORDER|SWT.V_SCROLL);
    // GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).hint(SWT.DEFAULT, 200).applyTo(c2);
    // c2.setExpandHorizontal(true);
    // c2.setExpandVertical(true);
    // messageLabel = new Label(c2, SWT.WRAP);
    // messageLabel.setText(message);
    // c2.setContent(messageLabel);
    // c2.setMinHeight(messageLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
    //
    // }
    // return composite;
    // };
    // protected boolean isResizable() {
    // return true;
    // };
    // protected org.eclipse.swt.widgets.Control createDialogArea(org.eclipse.swt.widgets.Composite parent) {
    // // create message area
    // createMessageArea(parent);
    // return null;
    // };
    // };
    // dialog.open();
    // }else{
    // MessageDialog.openError(Display.getDefault().getActiveShell(), Messages.noArtifactsFound_title,
    // NLS.bind(Messages.noArtifactsFound_message,workspaceArchiveFile)) ;
    // }
    // }
    //
    // /**
    // * import artifacts from zip
    // * the zip must have been created by BOS
    // * @param stream
    // * @param monitor
    // * @return
    // */
    // public static AbstractRepositoryArtifact[] importArtifactsFromZip(final ZipInputStream stream,final IProgressMonitor monitor) {
    // return importArtifactsFromZip(stream,true, monitor) ;
    // }

}
