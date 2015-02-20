/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.businessobject.core.repository;

import java.io.ByteArrayInputStream;
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
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
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

/**
 * @author Romain Bioteau
 */
public class BusinessObjectModelFileStore extends AbstractFileStore {

    public static final String DEFAULT_BDM_FILENAME = "bdm.zip";

    public BusinessObjectModelConverter converter;

    public Map<Long, BusinessObjectModel> cachedBusinessObjectModel = new HashMap<Long, BusinessObjectModel>();

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
        InputStream contents = null;
        try {
            contents = resource.getContents();
            final byte[] bytes = FileUtil.loadBytes(contents);
            final BusinessObjectModel bom = converter.unzip(bytes);
            cachedBusinessObjectModel.clear();
            cachedBusinessObjectModel.put(modificationStamp, bom);
            return bom;
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            if (contents != null) {
                try {
                    contents.close();
                } catch (final IOException e) {
                    BonitaStudioLog.error(e);
                }
            }
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
            final byte[] zip = converter.zip((BusinessObjectModel) content);
            final ByteArrayInputStream source = new ByteArrayInputStream(zip);
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
        return getName().replace(".zip", "") + "-client-pojo.jar";
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
