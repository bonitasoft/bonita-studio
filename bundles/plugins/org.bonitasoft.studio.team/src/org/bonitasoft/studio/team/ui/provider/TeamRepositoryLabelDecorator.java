/*******************************************************************************
 * Copyright (C) 2009-2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team.ui.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.team.i18n.Messages;
import org.bonitasoft.studio.team.operations.ScanResourcesLockOperation.LockStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.DecorationOverlayIcon;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * @author Baptiste Mesta
 *
 */
public class TeamRepositoryLabelDecorator implements ILabelDecorator {


    private Map<IResource, LockStatus> lockStatus;
    private final ArrayList<Image> images;
    private Map<IResource, String> lockOwners = new HashMap<IResource, String>();


    public TeamRepositoryLabelDecorator(){
        images = new ArrayList<Image>() ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
     */
    @Override
    public Image decorateImage(final Image image, final Object element) {
        if(lockStatus != null && image != null){
            if(element instanceof IRepositoryFileStore){
                final IResource resource = ((IRepositoryFileStore) element).getResource();
                final LockStatus resourceLockStatus = lockStatus.get(resource);
                if(resourceLockStatus!=null && !(resourceLockStatus== LockStatus.NOT_LOCKED) && !LockStatus.BROKEN.equals(resourceLockStatus)){
                    return getLockedImage(image, resourceLockStatus);
                }
            }
        }
        return null;
    }

    protected Image getLockedImage(final Image image, final LockStatus resourceLockStatus) {
        if(resourceLockStatus.equals(LockStatus.LOCALLY_LOCKED)){
            //locally locked
            final Image icon = new DecorationOverlayIcon(image,Pics.getImageDescriptor(PicsConstants.locked_local) , IDecoration.BOTTOM_RIGHT).createImage();
            images.add(icon) ;
            return icon;
        } else {
            final Image icon = new DecorationOverlayIcon(image, Pics.getImageDescriptor(PicsConstants.locked_other), IDecoration.BOTTOM_RIGHT).createImage();
            images.add(icon);
            return icon;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
     */
    @Override
    public String decorateText(String text, final Object element) {
        if (element instanceof IRepositoryFileStore) {
            final IRepositoryFileStore fileStore = (IRepositoryFileStore) element;
            text = getMigrationTextForDiagram(fileStore, text);
            if (lockStatus != null) {
                text = getResourceOwnerText(fileStore, text);
            }
        }
        return text;
    }

    private String getMigrationTextForDiagram(final IRepositoryFileStore fileStore,String text){
        if (fileStore instanceof DiagramFileStore) {

            if (((DiagramFileStore) fileStore).hasMigrationReport()) {
                text = text + " -- " + Messages.migrationOngoing;
            }
        }
        return text;
    }

    private String getResourceOwnerText(final IRepositoryFileStore fileStore,String text){
        final LockStatus resourceLockStatus = lockStatus.get(fileStore.getResource());
        final String owner = lockOwners.get(fileStore.getResource());
        if (resourceLockStatus != null && !(resourceLockStatus == LockStatus.NOT_LOCKED) && owner != null
                && !LockStatus.BROKEN.equals(resourceLockStatus)) {
            text = text + " (" + Messages.bind(Messages.lockedBy, owner) + ")";
        }
        return text;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void addListener(final ILabelProviderListener listener) {
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    @Override
    public void dispose() {
        for(final Image im : images){
            im.dispose() ;
        }
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean isLabelProperty(final Object element, final String property) {
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    @Override
    public void removeListener(final ILabelProviderListener listener) {
    }


    /**
     * @param lockStatus the lockStatus to set
     */
    public void setLockStatus(final Map<IResource, LockStatus> lockStatus) {
        this.lockStatus = lockStatus;
    }

    public void updateLockStatus(final IResource resource, final LockStatus status){
        if(lockStatus == null) {
            lockStatus = new HashMap<IResource, LockStatus>();
        }
        lockStatus.put(resource, status);
    }


    /**
     * @return the lockStatus
     */
    public LockStatus getLockStatus(final IResource resource) {
        return lockStatus != null? lockStatus.get(resource):null;
    }

    public void setLockOwners(final Map<IResource, String> lockOwners) {
        this.lockOwners = lockOwners;
    }

    public void updateLockOwner(final IResource resource, final String owner) {
        if (lockOwners == null) {
            lockOwners = new HashMap<IResource, String>();
        }
        lockOwners.put(resource, owner);

    }

    public String getLockOwner(final IResource resource) {
        return lockOwners != null ? lockOwners.get(resource) : null;
    }


}
