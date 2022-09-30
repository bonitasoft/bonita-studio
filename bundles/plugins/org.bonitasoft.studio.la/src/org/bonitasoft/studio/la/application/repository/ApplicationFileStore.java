/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.la.application.repository;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRenamable;
import org.bonitasoft.studio.common.repository.model.IValidable;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.handler.DeployApplicationHandler;
import org.bonitasoft.studio.la.validator.ApplicationHomepageValidator;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.bonitasoft.studio.ui.validator.ExtensionSupported;
import org.bonitasoft.studio.ui.validator.FileNameValidator;
import org.bonitasoft.studio.ui.validator.InputValidatorWrapper;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.xml.sax.SAXException;

import com.google.common.io.ByteStreams;

public class ApplicationFileStore extends AbstractFileStore<ApplicationNodeContainer>
        implements IDeployable, IBuildable, IRenamable, IValidable {

    public static final String DEPLOY_COMMAND = "org.bonitasoft.studio.la.deploy.command";

    public ApplicationFileStore(String fileName, ApplicationRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    @Override
    protected ApplicationNodeContainer doGetContent() throws ReadFileStoreException {
        try (InputStream inputStream = openInputStream()) {
            return getConverter().unmarshallFromXML(ByteStreams.toByteArray(inputStream));
        } catch (CoreException | JAXBException | IOException | SAXException e) {
            throw new ReadFileStoreException("Failed to load application model", e);
        }
    }

    protected ApplicationNodeContainerConverter getConverter() {
        return ((ApplicationRepositoryStore) getParentStore()).getConverter();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {
        checkArgument(content instanceof ApplicationNodeContainer,
                String.format("Only instance of %s are supported",
                        ApplicationNodeContainer.class));
        try {
            final byte[] xmlContent = getConverter()
                    .marshallToXML((ApplicationNodeContainer) content);
            try (ByteArrayInputStream is = new ByteArrayInputStream(xmlContent)) {
                final IFile resource = getResource();
                if (!resource.exists()) {
                    resource.create(is, IResource.FORCE,
                            AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    resource.setContents(is, IResource.KEEP_HISTORY | IResource.FORCE,
                            AbstractRepository.NULL_PROGRESS_MONITOR);
                }
            }
        } catch (JAXBException | IOException | SAXException | CoreException e) {
            BonitaStudioLog.error("Failed to save application model", e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#getResource()
     */
    @Override
    public IFile getResource() {
        return (IFile) super.getResource();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null && activeWorkbenchWindow.getActivePage() != null) {
            IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
            Stream.of(activePage.getEditorReferences())
                    .filter(editorRef -> {
                        try {
                            return getName().contentEquals(editorRef.getEditorInput().getName());
                        } catch (PartInitException e) {
                            throw new RuntimeException("an error occured while trying to close an application", e);
                        }
                    })
                    .forEach(editorRef -> {
                        IEditorPart editor = editorRef.getEditor(false);
                        activePage.closeEditor(editor, false);
                    });
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        try {
            return IDE.openEditor(getActivePage(), getResource());
        } catch (final PartInitException e) {
            throw new RuntimeException("Failed to open application descriptor.", e);
        }
    }

    protected IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    @Override
    public void deployInUI() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(DeployApplicationHandler.APPLICATION_TO_DEPLOY_PARAMETER, getName());
        parameters.put(DeployApplicationHandler.DEPLOY_WIZARD_PARAMETER, Boolean.TRUE.toString());
        executeCommand(DEPLOY_COMMAND, parameters);
    }

    @Override
    public void rename(String newName) {
        renameLegacy(newName);
    }

    @Override
    public Optional<String> retrieveNewName() {
        FileNameValidator validator = new FileNameValidator(store, ExtensionSupported.XML, getDisplayName());
        InputDialog dialog = new InputDialog(Display.getDefault().getActiveShell(), Messages.rename,
                Messages.renameFile, getDisplayName(), new InputValidatorWrapper(validator));
        if (dialog.open() == Dialog.OK
                && !getDisplayName().equals(stripExtension(dialog.getValue(), ".xml"))) {
            return Optional.of(stripExtension(dialog.getValue(), ".xml") + ".xml");
        }
        return Optional.empty();
    }

    /**
     * Get name to display for artifact
     * 
     * @return displayable name
     */
    private String getDisplayName() {
        return IDisplayable.toDisplayName(this).orElse("");
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Optional<IEditorPart> findOpenedEditor() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("fileStore", getName());
        Optional<IEditorPart> editor = (Optional<IEditorPart>) executeCommand(
                "org.bonitasoft.studio.la.findOpenedEditor.command", parameters);
        return editor;
    }

    @Override
    public IStatus build(IPath buildPath, IProgressMonitor monitor) {
        IPath applicationFolderPath = buildPath.append("application");
        IFolder applicationFolder = getRepository().getProject()
                .getFolder(applicationFolderPath.makeRelativeTo(getRepository().getProject().getLocation()));
        if (!applicationFolder.exists()) {
            try {
                applicationFolder.create(true, true, new NullProgressMonitor());
                getResource().copy(applicationFolder.getFile(getName()).getFullPath(), false,
                        new NullProgressMonitor());
            } catch (CoreException e) {
                return e.getStatus();
            }
        }
        return Status.OK_STATUS;
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        options.put(DeployApplicationHandler.APPLICATION_TO_DEPLOY_PARAMETER, getName());
        options.put(DeployApplicationHandler.DEPLOY_WIZARD_PARAMETER, Boolean.FALSE.toString());
        Object status = executeCommand(DEPLOY_COMMAND, options);
        return status instanceof IStatus ? (IStatus) status : ValidationStatus.ok();
    }

    @Override
    public IStatus validate(IProgressMonitor monitor) {
        MultiStatus status = new MultiStatus(LivingApplicationPlugin.PLUGIN_ID, 0, "", null);
        ApplicationHomepageValidator validator = new ApplicationHomepageValidator();
        try {
            getContent().getApplications().stream()
                    .map(validator::validate)
                    .forEach(status::add);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
        }
        return status;
    }
}
