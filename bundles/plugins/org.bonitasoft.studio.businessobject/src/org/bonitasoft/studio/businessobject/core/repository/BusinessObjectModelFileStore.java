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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.core.operation.DeployBDMOperation;
import org.bonitasoft.studio.businessobject.core.operation.GenerateBDMOperation;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.DeployOptions;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectModelFileStore extends AbstractBDMFileStore {

    public static final String BDM_JAR_NAME = "bdm-client-pojo.jar";
    public static final String ZIP_FILENAME = "bdm.zip";
    public static final String BOM_FILENAME = "bom.xml";

    private static final String DEFINE_BDM_COMMAND_ID = "org.bonitasoft.studio.businessobject.manage";

    private final Map<Long, BusinessObjectModel> cachedBusinessObjectModel = new HashMap<>();

    public BusinessObjectModelFileStore(final String fileName, final IRepositoryStore<AbstractBDMFileStore> store) {
        super(fileName, store);
    }

    @Override
    public BusinessObjectModel getContent() {
        final IFile resource = getResource();
        if (!resource.exists()) {
            cachedBusinessObjectModel.clear();
            return null;
        }
        final long modificationStamp = resource.getModificationStamp();
        if (cachedBusinessObjectModel.containsKey(modificationStamp)) {
            return cachedBusinessObjectModel.get(modificationStamp);
        }
        try (InputStream contents = resource.getContents()) {
            final BusinessObjectModel bom = getConverter().unmarshall(ByteStreams.toByteArray(contents));
            cachedBusinessObjectModel.clear();
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
    public String getDisplayName() {
        return Messages.businessDataModel;
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
                resource.setContents(source, IResource.FORCE, Repository.NULL_PROGRESS_MONITOR);
            } else {
                resource.create(source, IResource.FORCE, Repository.NULL_PROGRESS_MONITOR);
            }
            cachedBusinessObjectModel.clear();
            cachedBusinessObjectModel.put(resource.getModificationStamp(), (BusinessObjectModel) content);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    protected void doDelete() {
        final DependencyRepositoryStore depStore = getDependencyRepositoryStore();
        final DependencyFileStore depJar = depStore.getChild(getDependencyName(), true);
        if (depJar != null) {
            depJar.delete();
        }
        super.doDelete();
        cachedBusinessObjectModel.clear();
    }

    protected DependencyRepositoryStore getDependencyRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
    }

    @Override
    /**
     * we use the command to call the handler, so we do not have to extend this class for sp features.
     */
    protected IWorkbenchPart doOpen() {
        executeCommand(DEFINE_BDM_COMMAND_ID, null);
        return null;
    }

    public List<BusinessObject> getBusinessObjects() {
        final BusinessObjectModel content = getContent();
        Assert.isNotNull(content);
        return content.getBusinessObjects();
    }

    @Override
    public Image getIcon() {
        return Pics.getImage("bdm.png", BusinessObjectPlugin.getDefault());
    }

    @Override
    protected void doClose() {

    }

    public BusinessObject getBusinessObject(final String qualifiedName) {
        for (final BusinessObject bo : getContent().getBusinessObjects()) {
            if (bo.getQualifiedName().equals(qualifiedName)) {
                return bo;
            }
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
                    PlatformUtil.delete(target, Repository.NULL_PROGRESS_MONITOR);
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

    public String getDependencyName() {
        return BDM_JAR_NAME;
    }

    public boolean sameContentAs(final BusinessObjectModel businessObjectModel) {
        final BusinessObjectModel originalContent = getContent();
        if (businessObjectModel == null && originalContent == null) {
            return true;
        } else if (businessObjectModel != null) {
            return businessObjectModel.equals(originalContent);
        }
        return false;
    }

    @Override
    public void deploy() {
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
        } catch (InvocationTargetException | InterruptedException e) {
            throw new RuntimeException("An error occured while depoying the BDM", e);
        }
    }

    @Override
    public IStatus deploy(APISession session, Map<String, Object> options, IProgressMonitor monitor) {
        GenerateBDMOperation generateBDMOperation = new GenerateBDMOperation(this);
        Object cleanBDM = options.containsKey(DeployOptions.CLEAN_BDM) ? false : options.get(DeployOptions.CLEAN_BDM);
        if(!(cleanBDM instanceof Boolean)) {
            return new Status(IStatus.ERROR, 
                    BusinessObjectPlugin.PLUGIN_ID, 
                    String.format("Invalid option type for %s. Expected a Boolean value but found a %s",DeployOptions.CLEAN_BDM, cleanBDM.getClass()));
        }
        DeployBDMOperation deployBDMOperation = new DeployBDMOperation(this, (boolean) cleanBDM).reuseSession(session);
        try {
            generateBDMOperation.run(monitor);
            deployBDMOperation.run(monitor);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, BusinessObjectPlugin.PLUGIN_ID, "An error occured while depoying the BDM", e);
        }
        return ValidationStatus.info(Messages.businessDataModelDeployed);
    }

}
