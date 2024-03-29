/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.core.repository;

import static java.util.Map.entry;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditor;
import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditorContribution;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.model.SmartImportBdmModel;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.SmartImportBdmValidator;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.event.BdmEvents;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.filestore.EditorFinder;
import org.bonitasoft.studio.common.repository.model.DeployOptions;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.model.smartImport.ISmartImportable;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.bonitasoft.studio.common.ui.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.google.common.io.ByteSource;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectModelFileStore extends AbstractBDMFileStore<BusinessObjectModel>
        implements ISmartImportable {

    public static final String DO_NOT_SHOW_INSTALL_MESSAGE_DIALOG = "DO_NOT_SHOW_INSTALL_MESSAGE_DIALOG";
    public static final String ZIP_FILENAME = "bdm.zip";
    public static final String BOM_FILENAME = "bom.xml";
    public static final String BDM_DEPLOY_REQUIRED_PROPERTY = "bdmDeployRequired";
    private static final String CLEAN_ACCESS_CONTROL_CMD = "org.bonitasoft.studio.bdm.access.control.command.clean";

    private final Map<Long, BusinessObjectModel> cachedBusinessObjectModel = new HashMap<>();
    private CommandExecutor commandExecutor;
    private List<IType> daoTypes;

    public BusinessObjectModelFileStore(final String fileName,
            final IRepositoryStore<? extends AbstractBDMFileStore> store) {
        super(fileName, store);
        commandExecutor = new CommandExecutor();
    }

    @Override
    public BusinessObjectModel getContent() throws ReadFileStoreException {
        final IFile resource = getResource();
        if (!resource.exists()) {
            cachedBusinessObjectModel.clear();
            daoTypes = null;
            return null;
        }
        final long modificationStamp = resource.getModificationStamp();
        if (cachedBusinessObjectModel.containsKey(modificationStamp)) {
            return cachedBusinessObjectModel.get(modificationStamp);
        }
        return super.getContent();
    }

    @Override
    protected BusinessObjectModel doGetContent() throws ReadFileStoreException {
        final IFile resource = getResource();
        if (!resource.exists()) {
            cachedBusinessObjectModel.clear();
            daoTypes = null;
            return null;
        }
        final long modificationStamp = resource.getModificationStamp();
        try {
            final BusinessObjectModel bom = getConverter()
                    .unmarshall(Files.readAllBytes(resource.getLocation().toFile().toPath()));
            cachedBusinessObjectModel.clear();
            daoTypes = null;
            cachedBusinessObjectModel.put(modificationStamp, bom);
            return bom;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    protected BusinessObjectModelConverter getConverter() {
        return ((BusinessObjectModelRepositoryStore) getParentStore()).getConverter();
    }

    @Override
    protected void doSave(final Object content) {
        Assert.isNotNull(content);
        Assert.isLegal(content instanceof BusinessObjectModel);
        try {
            final byte[] xml = getConverter().marshall((BusinessObjectModel) content);
            final ByteArrayInputStream source = new ByteArrayInputStream(xml);
            final IFile resource = getResource();
            if (resource.exists()) {
                resource.setContents(source, IResource.FORCE, AbstractRepository.NULL_PROGRESS_MONITOR);
            } else {
                resource.create(source, IResource.FORCE, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            cachedBusinessObjectModel.clear();
            daoTypes = null;
            cachedBusinessObjectModel.put(resource.getModificationStamp(), (BusinessObjectModel) content);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    public Dependency getModelMavenDependency() throws CoreException {
        var project = Adapters.adapt(getRepository(), BonitaProject.class);
        var metadata = project.getProjectMetadata(new NullProgressMonitor());
        Dependency dependency = new Dependency();
        dependency.setGroupId(metadata.getGroupId());
        dependency.setArtifactId(project.getId() + "-bdm-model");
        dependency.setVersion(metadata.getVersion());
        dependency.setType("jar");
        dependency.setScope(Artifact.SCOPE_PROVIDED);
        return dependency;
    }

    @Override
    protected void doDelete() {
        try {
            var dependency = getModelMavenDependency();
            eventBroker().ifPresent(broker -> broker.send(BdmEvents.BDM_DELETED_TOPIC, Map.ofEntries(entry(BdmEvents.DEPENDENCY_PROPERTY, dependency))));
        } catch (CoreException e) {
           BonitaStudioLog.error(e);
        }
        super.doDelete();
        cachedBusinessObjectModel.clear();
        clearBusinessObjectDaoCache();
       
        if (commandExecutor.canExecute(CLEAN_ACCESS_CONTROL_CMD, null)) {
            commandExecutor.executeCommand(CLEAN_ACCESS_CONTROL_CMD, null);
        }
        getRepositoryAccessor().getCurrentProject().ifPresent(p -> {
            try {
                p.getBdmModelProject().delete(true, true, new NullProgressMonitor());
                p.getBdmDaoClientProject().delete(true, true, new NullProgressMonitor());
                p.getBdmParentProject().delete(true, true, new NullProgressMonitor());
                p.removeModule(p.getParentProject(), BonitaProject.BDM_MODULE, new NullProgressMonitor());
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        });
    }
    protected Optional<IEventBroker> eventBroker() {
        if (PlatformUI.isWorkbenchRunning()) {
            return Optional.of(PlatformUI.getWorkbench().getService(IEventBroker.class));
        }
        return Optional.empty();
    }

    protected DependencyRepositoryStore getDependencyRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
    }

    @Override
    protected IWorkbenchPart doOpen() {
        try {
            BusinessDataModelEditor openedEditor = getOpenedEditor();
            if (openedEditor != null) {
                if (openedEditor.setActiveContribution(BusinessDataModelEditorContribution.ID)) {
                    openedEditor.getSite().getPage().activate(openedEditor);
                    return openedEditor;
                }
                openedEditor.close(true);
            }
            openedEditor = (BusinessDataModelEditor) IDE.openEditor(getActivePage(), getResource());
            openedEditor.setActiveContribution(BusinessDataModelEditorContribution.ID);
            openedEditor.getSite().getPage().activate(openedEditor);
            return openedEditor;
        } catch (final PartInitException e) {
            throw new RuntimeException("Failed to open bdm", e);
        }
    }

    public BusinessDataModelEditor getOpenedEditor() {
        return findOpenedEditor()
                .map(BusinessDataModelEditor.class::cast)
                .orElse(null);
    }

    @Override
    protected Optional<IEditorPart> findOpenedEditor() {
        return EditorFinder.findOpenedEditor(this::validateEditorInstance);
    }

    @Override
    protected boolean validateEditorInstance(IEditorPart editor) {
        return editor instanceof BusinessDataModelEditor;
    }

    public List<BusinessObject> getBusinessObjects() {
        BusinessObjectModel content;
        try {
            content = getContent();
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), BusinessObjectPlugin.PLUGIN_ID);
            return Collections.emptyList();
        }
        Assert.isNotNull(content);
        return content.getBusinessObjects();
    }

    @Override
    protected void doClose() {

    }

    public BusinessObject getBusinessObject(final String qualifiedName) {
        try {
            BusinessObjectModel model = getContent();
            for (final BusinessObject bo : model.getBusinessObjects()) {
                if (bo.getQualifiedName().equals(qualifiedName)) {
                    return bo;
                }
            }
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), BusinessObjectPlugin.PLUGIN_ID);
            return null;
        }
        return null;
    }

    @Override
    public IStatus export(String targetAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetAbsoluteFilePath));
        final IResource file = getResource();
        if (file != null) {
            final File to = new File(targetAbsoluteFilePath);
            to.mkdirs();
            final File target = new File(to, ZIP_FILENAME);
            if (target.exists()) {
                if (FileActionDialog.overwriteQuestion(file.getName())) {
                    PlatformUtil.delete(target, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    return ValidationStatus.cancel("");
                }
            }
            try (InputStream inputStream = ByteSource.wrap(toByteArray()).openBufferedStream();) {
                Files.copy(inputStream, target.toPath());
                return ValidationStatus.ok();
            }
        }
        return ValidationStatus.error(
                String.format(org.bonitasoft.studio.common.repository.Messages.failedToRetrieveResourceToExport,
                        getName()));
    }

    @Override
    public byte[] toByteArray() {
        try {
            return getConverter().zip(getContent());
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public boolean sameContentAs(final BusinessObjectModel businessObjectModel) {
        try {
            BusinessObjectModel originalContent = getContent();
            if (businessObjectModel == null && originalContent == null) {
                return true;
            } else if (businessObjectModel != null) {
                return businessObjectModel.equals(originalContent);
            }
            return false;
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.warning(e.getMessage(), BusinessObjectPlugin.PLUGIN_ID);
            return false;
        }

    }

    @Override
    public void deployInUI() {
        GenerateBDMOperation generateBDMOperation = new GenerateBDMOperation(this);
        DeployBDMOperation deployBDMOperation = new DeployBDMOperation(this);
        try {
            PlatformUI.getWorkbench().getProgressService().run(true, false, new IRunnableWithProgress() {

                @Override
                public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    generateBDMOperation.run(monitor);
                    deployBDMOperation.run(monitor);
                }
            });
            IPreferenceStore preferenceStore = BusinessObjectPlugin.getDefault().getPreferenceStore();
            if (!preferenceStore.getBoolean(DO_NOT_SHOW_INSTALL_MESSAGE_DIALOG)) {
                MessageDialogWithPrompt.openWithDetails(MessageDialog.INFORMATION,
                        Display.getDefault().getActiveShell(),
                        Messages.bdmDeployedTitle,
                        Messages.bdmDeployedMessage,
                        Messages.doNotShowMeAgain,
                        Messages.bdmDeployDetails,
                        false,
                        preferenceStore,
                        DO_NOT_SHOW_INSTALL_MESSAGE_DIALOG,
                        SWT.NONE);
            }
        } catch (InvocationTargetException | InterruptedException e) {
            throw new RuntimeException("An error occured while depoying the BDM", e);
        }
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        GenerateBDMOperation generateBDMOperation = new GenerateBDMOperation(this);
        Object cleanBDM = options.containsKey(DeployOptions.CLEAN_BDM) ? options.get(DeployOptions.CLEAN_BDM) : false;
        if (!(cleanBDM instanceof Boolean)) {
            return new Status(IStatus.ERROR,
                    BusinessObjectPlugin.PLUGIN_ID,
                    String.format("Invalid option type for %s. Expected a Boolean value but found a %s",
                            DeployOptions.CLEAN_BDM, cleanBDM.getClass()));
        }
        DeployBDMOperation deployBDMOperation = new DeployBDMOperation(this, (boolean) cleanBDM).reuseSession(session);
        try {
            generateBDMOperation.run(monitor);
            deployBDMOperation.run(monitor);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "An error occured while depoying the BDM",
                    e);
        }
        return ValidationStatus.info(Messages.businessDataModelDeployed);
    }
    
    public void clearBusinessObjectDaoCache() {
        daoTypes = null;
    }

    public List<IType> allBusinessObjectDao(final IJavaProject javaProject) {
        try {
            if (daoTypes == null) {
                BusinessObjectModel model = getContent();
                daoTypes = model.getBusinessObjectsClassNames()
                        .stream()
                        .map(name -> name + "DAO")
                        .map(daoType -> {
                            try {
                                return javaProject.findType(daoType);
                            } catch (JavaModelException e) {
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }
            return daoTypes;
        } catch (ReadFileStoreException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public <T> T getAdapter(Class<T> adapter) {
        if (adapter.isAssignableFrom(BusinessObjectModelFileStore.class)) {
            return (T) this;
        }
        if (adapter.isAssignableFrom(SmartImportBdmModel.class)) {
            return (T) new SmartImportBdmModel(this, getConverter(), new SmartImportBdmValidator(this, getConverter()),
                    false);
        }
        return null;
    }

}
