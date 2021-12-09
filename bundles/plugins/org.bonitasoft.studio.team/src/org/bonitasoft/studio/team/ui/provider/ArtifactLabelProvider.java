/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
///**
// * Copyright (C) 2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// */
//package org.bonitasoft.studio.team.ui.provider;
//
//import java.util.Map;
//
//import org.bonitasoft.studio.common.repository.AbstractRepositoryArtifact;
//import org.bonitasoft.studio.pics.Pics;
//import org.bonitasoft.studio.pics.PicsConstants;
//import org.bonitasoft.studio.team.ui.handler.BonitaScanLockHandler.LockStatus;
//import org.eclipse.core.resources.IResource;
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.swt.graphics.Image;
//
///**
// * @author Baptiste Mesta
// *
// */
//public class ArtifactLabelProvider extends LabelProvider {
//	
//	
//	
//	private Map<IResource, LockStatus> lockStatus;
//
//	public ArtifactLabelProvider(){
//		
//	}
//	
//	/**
//	 * @param lockstatus
//	 */
//	public ArtifactLabelProvider(Map<IResource, LockStatus> lockstatus) {
//		this.lockStatus = lockstatus;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
//	 */
//	@Override
//	public Image getImage(Object element) {
//		//TODO : do update of readOnly mode elsewhere?
//		if(element instanceof AbstractRepositoryArtifact){
//			if(lockStatus != null){//if no lockstatus w don't want to show lock status
//				IResource resource = ((AbstractRepositoryArtifact) element).getResource();
//				if(lockStatus.get(resource)!=null){
//					if(lockStatus.get(resource).equals(LockStatus.LOCALLY_LOCKED)){
//						//locally locked
//						((AbstractRepositoryArtifact) element).setReadOnly(false);
//						return Pics.getImage(PicsConstants.locked_local);
//					}else{
//						//not locally locked
//						((AbstractRepositoryArtifact) element).setReadOnly(true);
//						return Pics.getImage(PicsConstants.locked_other);
//					}
//				} else {
//					((AbstractRepositoryArtifact) element).setReadOnly(false);
//				}
//			} else {
//				return ((AbstractRepositoryArtifact) element).getImage();
//			}
//		}
//		return null;
//	}
//	
//	@Override
//	public String getText(Object element) {
//		if(element instanceof AbstractRepositoryArtifact){
//			return ((AbstractRepositoryArtifact) element).getDisplayNameForLabelProvider();
//		}
//		return super.getText(element);
//	}
//	
//	
//}
