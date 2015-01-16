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
import java.util.List;

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

import com.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import com.bonitasoft.engine.bdm.model.BusinessObject;
import com.bonitasoft.engine.bdm.model.BusinessObjectModel;

/**
 * @author Romain Bioteau
 * 
 */
public class BusinessObjectModelFileStore extends AbstractFileStore {

    public static final String DEFAULT_BDM_FILENAME = "bdm.zip";

    public BusinessObjectModelConverter converter;

    public BusinessObjectModelFileStore(String fileName, IRepositoryStore<BusinessObjectModelFileStore> store) {
        super(fileName, store);
        converter = new BusinessObjectModelConverter();
    }

    @Override
    public BusinessObjectModel getContent() {
        IFile resource = getResource();
        if (!resource.exists()) {
            return null;
        }
        InputStream contents = null;
        try {
            contents = resource.getContents();
            byte[] bytes = FileUtil.loadBytes(contents);
            return converter.unzip(bytes);
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        } finally {
            if (contents != null) {
                try {
                    contents.close();
                } catch (IOException e) {
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
    protected void doSave(Object content) {
        Assert.isNotNull(content);
        Assert.isLegal(content instanceof BusinessObjectModel);
        try {
            byte[] zip = converter.zip((BusinessObjectModel) content);
            ByteArrayInputStream source = new ByteArrayInputStream(zip);
            IFile resource = getResource();
            if (resource.exists()) {
                resource.setContents(source, IResource.FORCE, Repository.NULL_PROGRESS_MONITOR);
            } else {
                resource.create(source, IResource.FORCE, Repository.NULL_PROGRESS_MONITOR);
            }
        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    @Override
    protected void doDelete() {
        final DependencyRepositoryStore depStore = getDependencyRepositoryStore();
        DependencyFileStore depJar = depStore.getChild(getDependencyName());
        if (depJar != null) {
            depJar.delete();
        }
        super.doDelete();
    }

    protected DependencyRepositoryStore getDependencyRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
    }

    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    public List<BusinessObject> getBusinessObjects() {
        BusinessObjectModel content = getContent();
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

    public BusinessObject getBusinessObject(String qualifiedName) {
        for (BusinessObject bo : getContent().getBusinessObjects()) {
            if (bo.getQualifiedName().equals(qualifiedName)) {
                return bo;
            }
        }
        return null;
    }

    public byte[] toByteArray() {
        try {
            return converter.zip(getContent());
        } catch (Exception e) {
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

    public boolean sameContentAs(BusinessObjectModel businessObjectModel) {
        BusinessObjectModel originalContent = getContent();
        if (businessObjectModel == null && originalContent == null) {
            return true;
        } else if (businessObjectModel != null) {
            return businessObjectModel.equals(originalContent);
        }
        return false;
    }
}
