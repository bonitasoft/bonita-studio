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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.businessobject.BusinessObjectPlugin;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

/**
 * @author Romain Bioteau
 */
public class BusinessObjectModelFileStore extends AbstractFileStore {

    public static final String BDM_JAR_NAME = "bdm-client-pojo.jar";
    public static final String ZIP_FILENAME = "bdm.zip";
    public static final String BOM_FILENAME = "bom.xml";

    private final BusinessObjectModelConverter converter;

    private final Map<Long, BusinessObjectModel> cachedBusinessObjectModel = new HashMap<>();

    public BusinessObjectModelFileStore(final String fileName, final IRepositoryStore<BusinessObjectModelFileStore> store) {
        super(fileName, store);
        converter = new BusinessObjectModelConverter();
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
            final BusinessObjectModel bom = converter.unmarshall(ByteStreams.toByteArray(contents));
            cachedBusinessObjectModel.clear();
            cachedBusinessObjectModel.put(modificationStamp, bom);
            return bom;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
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
            final byte[] xml = converter.marshall((BusinessObjectModel) content);
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
        final DependencyFileStore depJar = depStore.getChild(getDependencyName());
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
    protected IWorkbenchPart doOpen() {
        return null;
    }

    public List<BusinessObject> getBusinessObjects() {
        final BusinessObjectModel content = getContent();
        Assert.isNotNull(content);
        return content.getBusinessObjects();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("bdm.png", BusinessObjectPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
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
    public void export(String targetAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetAbsoluteFilePath));
        final IResource file = getResource();
        if (file != null) {
            final File to = new File(targetAbsoluteFilePath);
            to.mkdirs();
            final File target = new File(to,ZIP_FILENAME);
            if (target.exists()) {
                if (FileActionDialog.overwriteQuestion(file.getName())) {
                    PlatformUtil.delete(target, Repository.NULL_PROGRESS_MONITOR);
                } else {
                    return;
                }
            }
            Files.copy(ByteStreams.newInputStreamSupplier(toByteArray()), target);
        }
    }

    @Override
    public byte[] toByteArray() {
        try {
            return converter.zip(getContent());
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#getResource()
     */
    @Override
    public IFile getResource() {
        return (IFile) super.getResource();
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
}
