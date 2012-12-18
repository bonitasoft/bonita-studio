/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.application.actions.wizards.export;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.exporter.ExporterService;
import org.bonitasoft.studio.exporter.ExporterService.SERVICE_TYPE;
import org.bonitasoft.studio.exporter.application.service.IWarFactory;
import org.bonitasoft.studio.exporter.application.service.IWarFactory.ExportMode;
import org.bonitasoft.studio.exporter.runtime.RuntimeExportMode;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class AdvancedExportWizard extends Wizard implements IWizard{

    private AdvancedExportRecapWizardPage recapPage ;
    private AdvancedExportQuestionWizardPage questionPage ;
    private AdvancedExportChooseApplicationsWizardPage selectApplicationsPage ;

    /*Use only one ExportAction in order to keep in-memory association AbstractProcess/ProcessDefinition to avoid regeneration of ProcessDefition for war generation*/
    private final ExportActions exportActions = new ExportActions();

    public AdvancedExportWizard(){
        setWindowTitle(Messages.exportLabel);
        setNeedsProgressMonitor(true);
    }

    /**
     * @nooverride
     */
    @Override
    public final void addPages() {
        recapPage = getRecapPage();
        questionPage = new AdvancedExportQuestionWizardPage(getOpenedProcesses(), recapPage) ;
        selectApplicationsPage = new AdvancedExportChooseApplicationsWizardPage(getOpenedProcesses()) ;
        addPage(questionPage);
        addPage(selectApplicationsPage);
        addPage(recapPage);
    }

    private List<AbstractProcess> getOpenedProcesses() {
        IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
        if(editor != null && editor instanceof ProcessDiagramEditor){
            MainProcess mainProc = (MainProcess) ((ProcessDiagramEditor)editor).getDiagramEditPart().resolveSemanticElement() ;
            return ModelHelper.getAllProcesses(mainProc) ;
        }
        return Collections.emptyList();
    }

    /**
     * This method can be overriden by subclasses, however subclasses
     * MUST call the super.getPage() method.
     * @return
     */
    protected AdvancedExportRecapWizardPage getRecapPage() {
        if (recapPage == null) {
            recapPage = createRecapPage();
        }
        return recapPage;
    }

    /**
     * This method is intended to be overriden by children.
     * @return
     */
    protected AdvancedExportRecapWizardPage createRecapPage() {
        return new AdvancedExportRecapWizardPage();
    }

    @Override
    public boolean performFinish() {
        AdvancedExportRecapWizardPage recapPage2 = getRecapPage();
        if (!recapPage2.getLocation().exists()) {
            recapPage2.getLocation().mkdirs();
        }
        return export(recapPage2.getProcessesToExportAsBar(),
                true,
                recapPage2.getProcessesToExportAsWar(),
                recapPage2.getExportUserXP(),
                recapPage2.getExportRestWar(),
                recapPage2.getExportRuntimeMode(),
                recapPage2.getProcessesExportMode(),
                recapPage2.getLocation());
    }

    /**
     * @param processesToExportAsBar
     * @param processesToExportAsWar
     * @param exportRestWar
     * @param exportUserXP2
     * @param exportRuntime2
     * @param location2
     * @return
     */
    public boolean export(final List<AbstractProcess> processesToExportAsBar,
            final boolean withApplication,
            final List<AbstractProcess> processesToExportAsWar,
            final boolean exportUserXP,
            final boolean exportRestWar,
            final RuntimeExportMode runtimeExportMode,
            final ExportMode exportMode,
            final File destFolder) {
        try {
            if(getContainer() != null){
                getContainer().run(true, true, new IRunnableWithProgress() {

                    @Override
                    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                        doExport(processesToExportAsBar, withApplication, processesToExportAsWar,
                                exportUserXP, exportRestWar, exportMode, runtimeExportMode,
                                destFolder, monitor);
                    }

                });
            }else{
                doExport(processesToExportAsBar,withApplication, processesToExportAsWar, exportUserXP, exportRestWar, exportMode, runtimeExportMode, destFolder, new NullProgressMonitor());
            }
        } catch (Exception ex) {
            BonitaStudioLog.error(ex);
            return false;
        }
        return true;
    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        return super.getNextPage(page);
    }

    /**
     * @param barExportError
     * @param warExportError
     * @param runtimeException
     * @param userXPException
     */
    protected void openErrorMessage(final Shell shell, List<Pair<AbstractProcess, Exception>> barExportError, List<Pair<AbstractProcess, Exception>> warExportError, Exception runtimeException, Exception userXPException) {
        if(shell != null){
            boolean hasError = false;
            final StringBuilder errorMessage = new StringBuilder(Messages.exportErrorOccured);
            errorMessage.append('\n');
            for (Pair<AbstractProcess, Exception> error : barExportError) {
                hasError = true;
                errorMessage.append(NLS.bind(Messages.errorMessageBAR, new Object[] {error.getFirst().getName(), error.getFirst().getVersion(), error.getSecond().getMessage()}));
                errorMessage.append('\n');
            }
            for (Pair<AbstractProcess, Exception> error : warExportError) {
                hasError = true;
                errorMessage.append(NLS.bind(Messages.errorMessageWAR, new Object[] {error.getFirst().getName(), error.getFirst().getVersion(), error.getSecond().getMessage()}));
                errorMessage.append('\n');
            }
            if (userXPException != null) {
                hasError = true;
                errorMessage.append(NLS.bind(Messages.errorMessageUserXP, userXPException.getMessage()));
                errorMessage.append('\n');
            }
            if (runtimeException != null) {
                hasError = true;
                errorMessage.append(NLS.bind(Messages.errorRuntime, userXPException.getMessage()));
                errorMessage.append('\n');
            }
            if (hasError) {
                Display.getDefault().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageDialog.openError(shell, Messages.errorWhileExportingTitle, errorMessage.toString());
                    }
                });

            }
        }
    }

    private void doExport(
            final List<AbstractProcess> processesToExportAsBar,
            final boolean withApplication,
            final List<AbstractProcess> processesToExportAsWar,
            final boolean exportUserXP,
            final boolean exportRestWar,
            final ExportMode exportMode,
            final RuntimeExportMode runtimeExportMode,
            final File destFolder,
            IProgressMonitor monitor) {
        int totalWork = 16 * processesToExportAsBar.size() + 10 * processesToExportAsWar.size() + (exportUserXP? 14 : 0) + (runtimeExportMode != null ? 1 : 0);
        monitor.beginTask(Messages.exporting, totalWork);
        List<Pair<AbstractProcess, Exception>> barExportError = new ArrayList<Pair<AbstractProcess, Exception>>();
        doExportBars(processesToExportAsBar,withApplication, destFolder, monitor, barExportError);
        // WAR
        List<Pair<AbstractProcess, Exception>> warExportError = doExportWars(processesToExportAsWar, exportMode, destFolder, monitor);
        // User XP
        Exception userXPException = doExportUserXPWar(exportUserXP, exportMode, destFolder, monitor);
        Exception restWarException = doExportRestWar(exportRestWar, exportMode, destFolder, monitor);

        IWarFactory warFactory = (IWarFactory) ExporterService.getInstance().getExporterService(SERVICE_TYPE.WarFactory);
        //warFactory.addConfFiles(destFolder, monitor);
        // Runtime
        Exception runtimeException = doExportRuntime(destFolder, runtimeExportMode, monitor);
        //warFactory.addReadmeFile(destFolder, monitor);
        openErrorMessage(getShell(), barExportError, warExportError, runtimeException, userXPException);
    }

    protected Exception doExportRuntime(final File destFolder, final RuntimeExportMode exportMode, IProgressMonitor monitor) {
        Exception runtimeException = null;
        if (exportMode != null) {
            try {
                monitor.setTaskName(Messages.exportingRuntime);
                IWarFactory warFactory = (IWarFactory) ExporterService.getInstance().getExporterService(SERVICE_TYPE.WarFactory);
                //	warFactory.addRuntime(destFolder, exportMode, monitor);
            } catch (Exception ex) {
                runtimeException = ex;
            } finally {
                monitor.worked(1);
            }
        }
        return runtimeException;
    }

    protected Exception doExportUserXPWar(final boolean exportUserXP,
            final ExportMode exportMode, final File destFolder,
            IProgressMonitor monitor) {
        Exception userXPException = null;
        if (exportUserXP) {
            try {
                monitor.setTaskName(Messages.exportingUserXP);
                exportActions.doExportUserXP(new NullProgressMonitor(), exportMode, destFolder);
            } catch (Exception ex) {
                userXPException = ex;
            } finally {
                monitor.worked(14);
            }
        }
        return userXPException;
    }

    protected Exception doExportRestWar(final boolean exportRestWar,
            final ExportMode exportMode, final File destFolder,
            IProgressMonitor monitor) {
        Exception userXPException = null;
        if (exportRestWar) {
            try {
                monitor.setTaskName(Messages.exportingUserXP);
                exportActions.doExportRestWar(new NullProgressMonitor(), exportMode, destFolder);
            } catch (Exception ex) {
                userXPException = ex;
            } finally {
                monitor.worked(14);
            }
        }
        return userXPException;
    }

    protected List<Pair<AbstractProcess, Exception>> doExportWars(
            final List<AbstractProcess> processesToExportAsWar,
            final ExportMode exportMode, final File destFolder,
            IProgressMonitor monitor) {
        List<Pair<AbstractProcess, Exception>> warExportError = new ArrayList<Pair<AbstractProcess, Exception>>();
        for (AbstractProcess process : processesToExportAsWar) {
            try {
                monitor.setTaskName(Messages.bind(Messages.exportingWar, process.getName(), process.getVersion()));
                doExportProcessWar(exportMode, destFolder, process);
            } catch (Exception ex) {
                warExportError.add(new Pair<AbstractProcess, Exception>(process, ex));
            } finally {
                monitor.worked(10);
            }
        }
        return warExportError;
    }

    protected void doExportProcessWar(final ExportMode exportMode,
            final File destFolder, AbstractProcess process) throws Exception {
        exportActions.doExportProcessWar(new NullProgressMonitor(), process, exportMode, destFolder);
    }

    protected void doExportBars(
            final List<AbstractProcess> processesToExportAsBar,
            final boolean withApplication,
            final File destFolder, IProgressMonitor monitor,
            List<Pair<AbstractProcess, Exception>> barExportError) {
        for (AbstractProcess process : processesToExportAsBar) {
            try {
                monitor.setTaskName(Messages.bind(Messages.exportingBar, process.getName(), process.getVersion()));
                exportActions.doExportProcessBar(new NullProgressMonitor(), process,null,withApplication, destFolder);
            } catch (Exception ex) {
                ex.printStackTrace();
                barExportError.add(new Pair<AbstractProcess, Exception>(process, ex));
            } finally {
                monitor.worked(16);
            }
        }
    }

    protected void updateExportConfiguration(boolean exportJEE,boolean exportLight) {
        if(exportJEE){
            recapPage.setExportMode(ExportMode.JEE) ;
        }else if(exportLight){
            recapPage.setExportMode(ExportMode.LIGHT) ;
        }else{
            recapPage.setExportMode(ExportMode.EMBEDDED) ;
        }
    }

    protected void updateExportConfiguration(List<ExportEntry> applications,boolean userXp, boolean restWar) {
        recapPage.setExportApplications(applications,userXp,restWar) ;
    }

    public void updateExportRuntimeConfiguration(boolean exportRuntime) {
        recapPage.setExportRuntime(exportRuntime) ;
    }


}
