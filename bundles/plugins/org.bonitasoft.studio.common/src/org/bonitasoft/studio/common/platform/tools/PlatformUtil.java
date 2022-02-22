/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.common.platform.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Optional;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.perspectives.ViewIds;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.intro.impl.IntroPlugin;
import org.eclipse.ui.internal.intro.impl.model.IntroModelRoot;
import org.eclipse.ui.intro.IIntroManager;
import org.eclipse.ui.intro.config.CustomizableIntroPart;

/**
 * @author Romain Bioteau
 */
public class PlatformUtil {

    private static final String OPEN_DASHBOARD_COMMAND = "org.bonitasoft.studio.application.show.overview.command";
    private static final String INTROVIEW_ID = "org.eclipse.ui.internal.introview";
    private static IFileSystem fileSystem; // SINGLETON

    private PlatformUtil() {

    }

    public static void maximizeWindow(final IWorkbenchPage page) {
        if (!page.isPageZoomed()) {
            if (page.getActivePartReference() != null) {
                page.toggleZoom(page.getActivePartReference());
            }
        }
    }

    public static void maximizeWindow(final IWorkbenchPage page, final IWorkbenchPartReference pr) {
        if (!page.isPageZoomed()) {
            if (pr != null) {
                page.toggleZoom(pr);
            }
        }
    }

    public static void restoreWindow(final IWorkbenchPage page) {
        if (page.isPageZoomed()) {
            page.toggleZoom(page.getActivePartReference());
        }
    }

    public static void closeIntro() {
        Optional.ofNullable(PlatformUI.getWorkbench())
                .map(IWorkbench::getDisplay)
                .ifPresent(display -> display.asyncExec(PlatformUtil::hideIntroPart));
    }

    public static Optional<IViewPart> findIntroPart() {
        return Optional.ofNullable(PlatformUI.getWorkbench())
                .map(IWorkbench::getActiveWorkbenchWindow)
                .map(IWorkbenchWindow::getActivePage)
                .flatMap(activePage -> Optional.ofNullable(activePage.findView(INTROVIEW_ID)));
    }

    public static Optional<IEditorPart> findActiveEditor() {
        if (PlatformUI.isWorkbenchRunning()) {
            return Optional.ofNullable(PlatformUI.getWorkbench())
                    .map(IWorkbench::getActiveWorkbenchWindow)
                    .map(IWorkbenchWindow::getActivePage)
                    .map(IWorkbenchPage::getActiveEditor);
        }
        return Optional.empty();
    }

    public static void hideIntroPart() {
        findIntroPart().ifPresent(PlatformUtil::hidePart);
    }

    public static void hidePart(IViewPart part) {
        Optional.ofNullable(PlatformUI.getWorkbench())
                .map(IWorkbench::getActiveWorkbenchWindow)
                .map(IWorkbenchWindow::getActivePage)
                .ifPresent(activePage -> activePage.hideView(part));
    }

    /**
     * If there is no more opened editor,
     * that we are in a BOS product:
     * open the intro
     */
    public static void openIntroIfNoOtherEditorOpen() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            workbench.getDisplay().asyncExec(() -> Optional.ofNullable(PlatformUI.getWorkbench())
                    .map(IWorkbench::getActiveWorkbenchWindow)
                    .map(IWorkbenchWindow::getActivePage)
                    .map(page -> page.getEditorReferences().length)
                    .filter(nbEditors -> nbEditors == 0)
                    .ifPresent(nbEditors -> {
                        // and that we are in BOS or BOS-SP
                        if (isABonitaProduct(Platform.getProduct().getId())) {
                            showIntroPart();
                        } else {
                            hideIntroPart();
                        }
                    }));
        }
    }

    public static void openDashboardIfNoOtherEditorOpen() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            workbench.getDisplay().asyncExec(() -> Optional.ofNullable(PlatformUI.getWorkbench())
                    .map(IWorkbench::getActiveWorkbenchWindow)
                    .map(IWorkbenchWindow::getActivePage)
                    .map(page -> page.getEditorReferences().length)
                    .filter(nbEditors -> nbEditors == 0)
                    .ifPresent(nbEditors -> {
                        // and that we are in BOS or BOS-SP
                        if (isABonitaProduct(Platform.getProduct().getId())) {
                            CommandExecutor commandExecutor = new CommandExecutor();
                            if (commandExecutor.canExecute(OPEN_DASHBOARD_COMMAND, null)) {
                                commandExecutor.executeCommand(OPEN_DASHBOARD_COMMAND, null);
                            }
                        }
                    }));
        }
    }

    public static void openIntro() {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench != null) {
            workbench.getDisplay().asyncExec(PlatformUtil::showIntroPart);
        }
    }

    public static void showIntroPart() {
        Optional.ofNullable(PlatformUI.getWorkbench())
                .map(IWorkbench::getActiveWorkbenchWindow)
                .map(IWorkbenchWindow::getActivePage)
                .ifPresent(PlatformUtil::showIntroPart);
    }

    public static void showIntroPart(IWorkbenchPage page) {
        IViewReference explorerView = page.findViewReference("org.bonitasoft.studio.application.project.explorer");
        if (explorerView != null) {
            page.activate(explorerView.getPart(true));
        }

        final IIntroManager introManager = PlatformUI.getWorkbench().getIntroManager();
        //colse intro to reload content if already opened
        hideIntroPart();
        final IntroModelRoot model = IntroPlugin.getDefault().getIntroModelRoot();
        if (model != null
                && introManager.getIntro() != null
                && ((CustomizableIntroPart) introManager.getIntro()).getControl() != null) {
            model.getPresentation().navigateHome();
        }
        BonitaPerspectivesUtils.switchToPerspective("org.bonitasoft.studio.perspective.welcomePage");
        page.setEditorAreaVisible(false);
        introManager.showIntro(
                page.getWorkbenchWindow(),
                false);

        page.setPartState(page.findViewReference("org.bonitasoft.studio.application.project.explorer"),
                IWorkbenchPage.STATE_RESTORED);
        page.setPartState(page.findViewReference(ViewIds.PROBLEM_VIEW_ID),
                IWorkbenchPage.STATE_RESTORED);
    }

   
    /**
     * Copy a resource a the bundle to the destination path
     *
     * @param destinationFolder
     * @param bundle
     * @param resourceName
     * @return the copied resource
     */
    public static void copyResource(File destFolder, final File sourceFolder, final IProgressMonitor monitor) {
        if (fileSystem == null) {
            fileSystem = EFS.getLocalFileSystem();
        }
        if (sourceFolder.isDirectory()) {
            destFolder = destFolder.getParentFile();
        }
        copyResource(destFolder, sourceFolder.toURI(), monitor);
    }

    /**
     * Copy a resource a the bundle to the destination path
     *
     * @param destinationFolder
     * @param bundle
     * @param resourceName
     * @return the copied resource
     */
    public static void copyResourceDirectory(final File destFolder, final File sourceFolder,
            final IProgressMonitor monitor) {
        if (fileSystem == null) {
            fileSystem = EFS.getLocalFileSystem();
        }
        if (sourceFolder.isDirectory()) {
            final IFileStore sourceStore = fileSystem.fromLocalFile(sourceFolder);
            final IFileStore destStore = fileSystem.fromLocalFile(destFolder);
            try {
                sourceStore.copy(destStore, EFS.OVERWRITE, new NullProgressMonitor());
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        } else {
            copyResource(destFolder, sourceFolder.toURI(), monitor);
        }
    }

    /**
     * Copy a resource a the bundle to the destination path
     *
     * @param destinationFolder
     * @param bundle
     * @param resourceName
     * @return the copied resource
     */
    public static void copyResource(final File destFolder, final File sourceFolder, final FilenameFilter filter,
            final IProgressMonitor monitor) {
        if (sourceFolder.isDirectory()) {
            if (!destFolder.exists()) {
                destFolder.mkdir();
            }
            final File[] listFilesIgnoringSvn = sourceFolder.listFiles(filter);
            for (final File child : listFilesIgnoringSvn) {
                copyResource(new File(destFolder, child.getName()), child, filter, monitor);
            }
        } else {
            final File parentFile = destFolder.getParentFile();
            parentFile.mkdirs();
            copyResource(parentFile, sourceFolder, monitor);
            if (!destFolder.getName().equals(sourceFolder.getName())) {
                if (destFolder.exists()) {
                    destFolder.delete();
                }
                new File(destFolder.getParent() + File.separator + sourceFolder.getName()).renameTo(destFolder);
            }
        }
    }

    public static String getFileContent(final InputStream fis) {
        final InputStreamReader reader = new InputStreamReader(fis, Charset.forName("UTF-8"));
        final StringBuilder sb = new StringBuilder();
        try {
            for (int a = reader.read(); a != -1; a = reader.read()) {
                sb.append((char) a);
            }

            reader.close();

        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
        return sb.toString();
    }

    public static void delete(final File fileToDelete, IProgressMonitor monitor) {

        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }

        if (!fileToDelete.exists()) {
            return;
        }

        if (fileSystem == null) {
            fileSystem = EFS.getLocalFileSystem();
        }

        final IFileStore fileToDeleteStore = fileSystem.fromLocalFile(fileToDelete);
        try {
            fileToDeleteStore.delete(EFS.NONE, new NullProgressMonitor());
            monitor.worked(1);
        } catch (final CoreException e) {
            // BonitaStudioLog.log(e);
            fileToDelete.delete();
        }

    }

    public static IFileSystem getFileSystem() {
        if (fileSystem == null) {
            fileSystem = EFS.getLocalFileSystem();
        }
        return fileSystem;
    }

    public static void copyResource(final File destFolder, final URL url,
            final IProgressMonitor monitor) {
        PlatformUtil.copyResource(destFolder, URIUtil.toURI(url.getPath()), monitor);
    }

    /**
     * Unzip the file with path filename and return the output folder File
     *
     * @param monitor
     * @throws Exception
     */
    public static void unzipZipFiles(final File zipFile, final File destDir, IProgressMonitor monitor) throws Exception {
        if (monitor == null) {
            monitor = new NullProgressMonitor();
        }
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile);
            final byte[] readBuffer = new byte[2156];
            int bytesIn = 0;
            final Enumeration<? extends ZipEntry> enumEntry = zip.entries();
            while (enumEntry.hasMoreElements()) {
                final ZipEntry file = enumEntry.nextElement();
                final java.io.File f = new java.io.File(destDir.getAbsolutePath() + File.separatorChar + file.getName());
                if (file.isDirectory()) { // if its a directory, create it
                    f.mkdir();
                    continue;
                }
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdirs();
                }
                try (final InputStream is = zip.getInputStream(file);
                        final FileOutputStream fos = new FileOutputStream(f);) {
                    while ((bytesIn = is.read(readBuffer)) != -1) { // write contents of 'is' to 'fos'
                        fos.write(readBuffer, 0, bytesIn);
                    }
                }
            }
            monitor.worked(1);
        } finally {
            if (zip != null) {
                zip.close();
            }
        }
    }

    public static void move(final File fileToMove, final File toDir, final IProgressMonitor monitor) {
        if (fileToMove == null || !fileToMove.exists()) {
            return;
        }

        if (fileSystem == null) {
            fileSystem = EFS.getLocalFileSystem();
        }

        final IFileStore fileToMoveStore = fileSystem.fromLocalFile(fileToMove);
        final IFileStore dest = fileSystem.fromLocalFile(toDir);
        try {
            fileToMoveStore.move(dest.getChild(fileToMoveStore.getName()), EFS.OVERWRITE, new NullProgressMonitor());
            monitor.worked(1);
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }

    }

    public static void copyResource(final File destFolder, final URI uri,
            final IProgressMonitor monitor) {
        if (fileSystem == null) {
            fileSystem = EFS.getLocalFileSystem();
        }
        try {
            final IFileStore sourceStore = fileSystem.getStore(URIUtil.toURI(uri.getPath()));
            final IFileStore destStore = fileSystem.fromLocalFile(destFolder);
            IFileStore target = destStore.getChild(sourceStore.getName());
            sourceStore.copy(target, EFS.OVERWRITE, new NullProgressMonitor());
            monitor.worked(1);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    public static boolean isHeadless() {
        if (Platform.getCommandLineArgs().length > 1) {
            for (final String arg : Platform.getCommandLineArgs()) {
                if (arg.equals("org.bonitasoft.studio.application.InitializerApplication")
                        || arg.equals("org.bonitasoft.studio.application.WorkspaceRecovery")
                        || arg.equals("org.bonitasoft.studio.initializer")
                        || arg.equals("org.bonitasoft.studio.workspaceRecovery")
                        || arg.equals("org.bonitasoft.studio.workspaceAPI")
                        || arg.equals("org.eclipse.platform.ide")
                        || arg.equals("org.bonitasoft.studio.importer.bos.ImportWorkspaceApplication")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Properties getStudioGlobalProperties() {
        final File res = new File(ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile(), "studio.properties");
        if (res.exists()) {
            final Properties properties = new Properties();
            try (final InputStream is = new FileInputStream(res);) {
                properties.load(is);
                return properties;
            } catch (final Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    public static boolean isABonitaProduct(final String id) {
        if (id != null) {
            return id.equals("org.bonitasoft.studio.product") || id.equals("org.bonitasoft.studioEx.product")
                    || id.equals("org.bonitasoft.talendBPM.product");
        }
        return false;
    }

    public static boolean isACommunityBonitaProduct() {
        if (Platform.getProduct() != null) {
            return Platform.getProduct().getId().equals("org.bonitasoft.studio.product");
        }
        return false;
    }

    public static boolean isIntroOpen() {
        final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            final IWorkbenchPage activePage = window.getActivePage();
            if (activePage != null) {
                for (final IViewReference vr : activePage.getViewReferences()) {
                    if (vr.getId().equals(INTROVIEW_ID)) {
                        return true;
                    }
                }
                final IWorkbenchPart part = activePage.getActivePart();
                if (part != null) {
                    final IIntroManager introManager = PlatformUI.getWorkbench().getIntroManager();
                    if (introManager != null) {
                        if (introManager.getIntro() != null) {
                            return true;
                        } else {
                            final IViewPart view = activePage.findView(INTROVIEW_ID);
                            return view != null;
                        }
                    }
                }
            }

        }
        return false;
    }

    public static IEditorReference getOpenEditor(final String editorName) {
        IEditorReference openEditor = null;
        if (PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null) {
            final IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            if (activePage != null) {
                final IEditorReference[] editors = activePage.getEditorReferences();
                for (final IEditorReference iEditorReference : editors) {
                    if (iEditorReference.getName().equals(editorName)) {
                        openEditor = iEditorReference;
                        break;
                    }
                }
            }
        }
        return openEditor;
    }

    public static void swtichToOpenedEditor(final IEditorReference openEditor) {
        if (isIntroOpen()) {
            closeIntro();
        }
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().setActivePage(openEditor.getPage());
        try {
            openEditor.getPage().openEditor(openEditor.getEditorInput(), openEditor.getId());
        } catch (final PartInitException e) {
            e.printStackTrace();
        }
    }
}
