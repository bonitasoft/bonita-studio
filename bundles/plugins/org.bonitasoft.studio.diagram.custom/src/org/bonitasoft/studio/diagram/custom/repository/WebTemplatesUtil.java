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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.ApplicationResourceFileStore.ResourceType;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ResourceFile;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.bonitasoft.studio.repository.themes.ApplicationLookNFeelFileStore;
import org.bonitasoft.studio.repository.themes.LookNFeelRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @author Mickael Istria
 * @author Baptiste Mesta
 */
public class WebTemplatesUtil {

    /**
     * @param filePath
     * @return
     */
    public static ResourceFolder createResourceFolder(final String filePath) {
        final ResourceFolder resFolder = ProcessFactory.eINSTANCE.createResourceFolder();
        resFolder.setPath(filePath);
        return resFolder;
    }

    //	/**
    //	 * Create and then get the list of ResourceFile from the file and all its
    //	 * sub files
    //	 *
    //	 * @param file
    //	 * @param currentList
    //	 */
    //	public static void populateAddFileList(ResourceFolder resFolder, File file, Collection<ResourceFile> currentList) {
    //		File fsResFolder = new File(resFolder.getPath());
    //		if (!fsResFolder.exists()) {
    //
    //			if (resFolder.getPath().startsWith(ProvidedWebTemplatesRepository.getInstance().getFolderName())) {
    //				fsResFolder = ProvidedWebTemplatesRepository.getInstance().getFileInProject(resFolder.getPath());
    //			} else {
    //				fsResFolder = UserWebTemplatesRepository.getInstance().getFileInProject(resFolder.getPath());
    //			}
    //		}
    //		// call it on all sub files
    //		if (file.isDirectory()) {
    //			for (File subFile : file.listFiles()) {
    //				populateAddFileList(resFolder, subFile, currentList);
    //			}
    //		}
    //		// check if the file already exists
    //		boolean alreadyExists = false;
    //		EList<ResourceFile> rfs = resFolder.getResourceFiles();
    //		for (ResourceFile resourceFile : rfs) {
    //			// remove all sub resources that are checked
    //			if (resourceFile.getPath().equals(file.getPath())) {
    //				alreadyExists = true;
    //				break;
    //			}
    //		}
    //		if (!alreadyExists) {
    //			// create the resourceFile and add it to the list
    //			ResourceFile toAdd = ProcessFactory.eINSTANCE.createResourceFile();
    //			toAdd.setPath(file.getAbsolutePath().substring(fsResFolder.getAbsolutePath().length()));
    //			currentList.add(toAdd);
    //		}
    //	}

    /**
     * get the file from the path even it's a file in a template folder or null
     * if the file does not exists
     *
     * @param filePath
     * @return the file on the file system
     */
    public static File getFile(final String path) {
        if (path != null) {
            final File file = new File(path);
            if (file.exists()) {
                return file;
            } else {
                final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
                return store.getFileInProject(path);

            }
        }
        return null;
    }

    /**
     * get the file from the path even it's a file in a template folder or null
     * if the file does not exists
     *
     * @param filePath
     * @return the file on the file system
     */
    public static IFile getIFile(final String path) {
        if (path != null) {
            final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
            return store.getIFileInProject(path);

        }
        return null;
    }

    public static void refreshFile(final String path) {
        if (path != null) {
            final File file = new File(path);
            if (!file.exists()) {
                final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
                final Path ipath = new Path(path);
                try {
                    store.getResource().getFile(ipath).refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
    }

    /**
     * @param process
     * @param theme
     * @return
     */
    public static CompoundCommand createAddTemplateCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme) {
        return createAddTemplateCommand(editingDomain, process, theme, Repository.NULL_PROGRESS_MONITOR);
    }

    /**
     * @param process
     * @param theme
     * @param monitor TODO
     * @return
     */
    public static CompoundCommand createAddTemplateCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme, final IProgressMonitor monitor) {
        //clear old look'n feel
        final CompoundCommand cc = new CompoundCommand();
        clearOldLnF(editingDomain, process, cc);

        //copy all files to the user artifact
        final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
        final String processUUID = ModelHelper.getEObjectID(process);
        ApplicationResourceFileStore artifact = store.getChild(processUUID);
        if (artifact == null) {
            artifact = store.createRepositoryFileStore(processUUID);
        }

        artifact.clear();

        appendCommandToAddFilesAndFolders(editingDomain, process, theme, monitor, cc, artifact);
        cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__BASED_ON_LOOK_AND_FEEL, theme.getName()));
        setProcessTemplateCommand(editingDomain, process, theme, cc, artifact);
        setConsultationTemplateCommand(editingDomain, process, theme, cc, artifact);
        setConfirmationTemplateCommand(editingDomain, process, theme, cc, artifact);
        setErrorTemplateCommand(editingDomain, process, theme, cc, artifact);
        setGlobalTemplateCommand(editingDomain, process, theme, cc, artifact);
        setLoginPageCommand(editingDomain, process, theme, cc, artifact);
        setHostPageCommand(editingDomain, process, theme, cc, artifact);
        setWelcomePageCommand(editingDomain, process, theme, cc, artifact);

        artifact.save(null); //Call save event only

        return cc;
    }

    private static void clearOldLnF(final TransactionalEditingDomain editingDomain, final AbstractProcess process, final CompoundCommand cc) {
        for (final ResourceFolder folder : process.getResourceFolders()) {
            cc.append(new RemoveCommand(editingDomain, process, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS, folder));
        }
        for (final ResourceFile file : process.getResourceFiles()) {
            cc.append(new RemoveCommand(editingDomain, process, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES, file));
        }
    }

    private static void setWelcomePageCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getWelcomePage().exists()) {
            artifact.setWelcomePage(theme.getWelcomePage().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getWelcomePageRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE, processTemplate));
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL, Boolean.TRUE));
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE, null));
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__WELCOME_PAGE_INTERNAL, Boolean.FALSE));
        }
    }

    private static void setHostPageCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getHostPage().exists()) {
            artifact.setHostPage(theme.getHostPage().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getHostPageRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE, processTemplate));
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__HOST_PAGE, null));
        }
    }

    private static void setLoginPageCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getLoginPage().exists()) {
            artifact.setLoginPage(theme.getLoginPage().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getLoginPageRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE, processTemplate));
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__LOG_IN_PAGE, null));
        }
    }

    private static void setGlobalTemplateCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getGlobalPageTemplate().exists()) {
            artifact.setGlobalPageTemplate(theme.getGlobalPageTemplate().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getGlobalPageTemplateRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE, processTemplate));
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__PAGE_TEMPLATE, null));
        }
    }

    private static void setErrorTemplateCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getErrorTemplate().exists()) {
            artifact.setErrorTemplate(theme.getErrorTemplate().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getErrorTemplateProjectRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE, processTemplate));
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__ERROR_TEMPLATE, null));
        }
    }

    private static void setConfirmationTemplateCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getConfirmationTemplate().exists()) {
            artifact.setConfirmationTemplate(theme.getConfirmationTemplate().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getConfirmationTemplateRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_TEMPLATE, processTemplate));//bad
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PAGE_FLOW__CONFIRMATION_TEMPLATE, null));
        }
    }

    private static void setConsultationTemplateCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getConsultationTemplate().exists()) {
            artifact.setGlobalConsultationPage(theme.getConsultationTemplate().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getGlobalConsultationTemplateRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE, processTemplate));
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__CONSULTATION_TEMPLATE, null));
        }
    }

    private static void setProcessTemplateCommand(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme,
            final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        if (theme.getProcessTemplate().exists()) {
            artifact.setProcessTemplate(theme.getProcessTemplate().getAbsolutePath());
            final AssociatedFile processTemplate = ProcessFactory.eINSTANCE.createAssociatedFile();
            processTemplate.setPath(artifact.getProcessTemplateProjectRelativePath());
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE, processTemplate));
        } else {
            cc.append(new SetCommand(editingDomain, process, ProcessPackage.Literals.PROCESS_APPLICATION__PROCESS_TEMPLATE, null));
        }
    }

    private static void appendCommandToAddFilesAndFolders(final TransactionalEditingDomain editingDomain, final AbstractProcess process,
            final ApplicationLookNFeelFileStore theme, final IProgressMonitor monitor, final CompoundCommand cc, final ApplicationResourceFileStore artifact) {
        final Collection<ResourceFile> filesToAdd = new ArrayList<ResourceFile>();
        final Collection<ResourceFolder> foldersToAdd = new ArrayList<ResourceFolder>();
        computeFilesAndFoldersToAdd(theme, monitor, artifact, filesToAdd, foldersToAdd);
        if (filesToAdd.size() > 0) {
            cc.append(new AddCommand(editingDomain, process, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES, filesToAdd));
        }
        if (foldersToAdd.size() > 0) {
            cc.append(new AddCommand(editingDomain, process, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS, foldersToAdd));
        }
    }

    private static void computeFilesAndFoldersToAdd(final ApplicationLookNFeelFileStore theme, final IProgressMonitor monitor,
            final ApplicationResourceFileStore artifact,
            final Collection<ResourceFile> filesToAdd, final Collection<ResourceFolder> foldersToAdd) {
        final File themeResourcesApplicationFolder = theme.getResourcesApplicationFolder();
        if (themeResourcesApplicationFolder != null) {
            for (final File resource : themeResourcesApplicationFolder.listFiles()) {
                if (!resource.getName().equals(".svn")) {
                    final String res = artifact.addResource(resource, monitor);
                    if (res != null && res.length() > 0) {
                        if (resource.isDirectory()) {
                            final ResourceFolder af = ProcessFactory.eINSTANCE.createResourceFolder();
                            af.setPath(res);
                            foldersToAdd.add(af);
                        } else {
                            final ResourceFile af = ProcessFactory.eINSTANCE.createResourceFile();
                            af.setPath(res);
                            filesToAdd.add(af);
                        }
                    }
                }
            }
        } else {
            BonitaStudioLog.log("The resource Application folder of the theme was not found. (theme: " + theme.getDisplayName() + ")");
        }
    }

    /**
     * @param res
     * @param abstractProcess
     * @return
     */
    public static String putResourceInProcessTemplate(final String res, final AbstractProcess abstractProcess, final Object parentFolder) {
        final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
        final String processUUID = ModelHelper.getEObjectID(abstractProcess);
        ApplicationResourceFileStore artifact = store.getChild(processUUID);
        if (artifact == null) {
            artifact = store.createRepositoryFileStore(processUUID);
        }
        String path;
        if (parentFolder != null) {
            path = artifact.addResource(new File(res), ResourceType.RESOURCE, parentFolder, Repository.NULL_PROGRESS_MONITOR);
        } else {
            path = artifact.addResource(new File(res), new NullProgressMonitor());
        }
        return path;
    }

    /**
     * @param temp
     */
    public static boolean isInUserTemplate(final Object temp) {
        final ApplicationResourceRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
        if (temp instanceof String) {
            return ((String) temp).startsWith(store.getResource().getName());
        } else if (temp instanceof ResourceFolder) {
            if (((ResourceFolder) temp).getPath().startsWith(store.getResource().getName())) {
                return true;
            } else if (((ResourceFolder) temp).eContainer() instanceof Pool) {
                return true;
            }
        } else if (temp instanceof ResourceFile) {
            if (((ResourceFile) temp).getPath().startsWith(store.getResource().getName())) {
                return true;
            } else if (((ResourceFile) temp).eContainer() instanceof Pool) {
                return true;
            }
        } else if (temp instanceof File) {
            File parent = ((File) temp).getParentFile();
            final File userFolder = store.getResource().getLocation().toFile();
            while (parent != null) {
                if (parent.equals(userFolder)) {
                    return true;
                }
                parent = parent.getParentFile();
            }
            return false;
        }
        return false;
    }

    public static ApplicationLookNFeelFileStore convertWebTemplateToTheme(final ApplicationResourceFileStore artifact, final String newName,
            final String previewPath, final IProgressMonitor monitor) throws Exception {
        final LookNFeelRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(LookNFeelRepositoryStore.class);
        final IFolder folder = store.getResource().getFolder(newName);
        if (folder.exists()) {
            return null;
        }
        folder.create(true, true, monitor);
        final File targetFolder = folder.getLocation().toFile();
        final File srcFolder = artifact.getResource().getLocation().toFile();
        PlatformUtil.copyResourceDirectory(targetFolder, srcFolder, monitor);
        final ApplicationLookNFeelFileStore target = (ApplicationLookNFeelFileStore) store.createRepositoryFileStore(newName);
        target.setAuthor("Generated from Bonita Studio");
        if (previewPath != null) {
            final File previewFile = new File(previewPath);
            if (previewFile.exists()) {
                final FileInputStream is = new FileInputStream(previewFile);
                target.copyPreviewFile(previewFile.getName(), is);
                is.close();
                target.setPreviewFilePath(previewFile.getName());
            }
        }
        target.save(null);
        return target;
    }

    /**
     * @param editingDomain
     * @param poolModel
     * @return
     */
    public static org.eclipse.emf.common.command.Command createDefaultResourceFolders(final TransactionalEditingDomain editingDomain, final Pool poolModel) {
        // copy it in the process template directory
        final IRepositoryStore<?> resourceStore = RepositoryManager.getInstance().getRepositoryStore(ApplicationResourceRepositoryStore.class);
        final String processUUID = ModelHelper.getEObjectID(poolModel);
        ApplicationResourceFileStore artifact = (ApplicationResourceFileStore) resourceStore.getChild(processUUID);
        if (artifact == null) {
            artifact = (ApplicationResourceFileStore) resourceStore.createRepositoryFileStore(processUUID);
        }
        final ResourceFolder resFolder = searchApplicationFolder(poolModel, artifact);
        if (resFolder != null) {
            return null;
        } else {
            final String path = artifact.createResourceFolder("application");//$NON-NLS-1$
            final ResourceFolder af = ProcessFactory.eINSTANCE.createResourceFolder();
            af.setPath(path);
            return new AddCommand(editingDomain, poolModel, ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS, af);
        }
    }

    private static ResourceFolder searchApplicationFolder(final Pool poolModel, final ApplicationResourceFileStore artifact) {
        final Iterator<ResourceFolder> it = poolModel.getResourceFolders().iterator();
        ResourceFolder resFolder = null;
        while (resFolder == null && it.hasNext()) {
            final ResourceFolder resourceFolder = it.next();
            if (resourceFolder.getPath().equals(artifact.getResourceProjectRelativePath() + "/application")) {//$NON-NLS-1$
                resFolder = resourceFolder;
                break;
            }
        }
        return resFolder;
    }

    public static AssociatedFile putResourcesInProcessTemplate(String res, final Object parentFolder, final TransactionalEditingDomain editingDomain,
            final AbstractProcess process) {
        // copy it in the process template directory
        final boolean isDirectory = new File(res).isDirectory();
        res = WebTemplatesUtil.putResourceInProcessTemplate(res, process, parentFolder);
        if (res != null && parentFolder == null) {//do not add a resfolder if it was already in a res folder
            AssociatedFile af;
            if (isDirectory) {
                af = ProcessFactory.eINSTANCE.createResourceFolder();
            } else {
                af = ProcessFactory.eINSTANCE.createResourceFile();
            }
            af.setPath(res);
            editingDomain.getCommandStack().execute(
                    new AddCommand(editingDomain, process, isDirectory ? ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FOLDERS
                            : ProcessPackage.Literals.RESOURCE_CONTAINER__RESOURCE_FILES
                            , af));
            return af;
        }
        return null;
    }

}
