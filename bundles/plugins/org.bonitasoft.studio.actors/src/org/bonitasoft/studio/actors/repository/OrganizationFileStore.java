/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.actors.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.actors.model.organization.DocumentRoot;
import org.bonitasoft.studio.actors.model.organization.Organization;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.bonitasoft.studio.actors.model.organization.util.OrganizationXMLProcessor;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class OrganizationFileStore extends EMFFileStore {

	public OrganizationFileStore(String fileName, OrganizationRepositoryStore store) {
		super(fileName, store);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return getContent().getName();
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
	 */
	@Override
	public Image getIcon() {
		return getParentStore().getIcon();
	}

	@Override
	public Organization getContent() {
		DocumentRoot root = (DocumentRoot) super.getContent();
		if(root != null){
			return root.getOrganization() ;
		}
		return null;
	}

	@Override
	protected void doSave(Object content) {
		if(content instanceof Organization){
			Resource emfResource = getEMFResource() ;
			emfResource.getContents().clear() ;
			DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot() ;
			root.setOrganization((Organization) EcoreUtil.copy((EObject) content)) ;
			emfResource.getContents().add(root) ;
			try {
				Map<String, String> options = new HashMap<String, String>() ;
				options.put(XMLResource.OPTION_ENCODING, "UTF-8");
				options.put(XMLResource.OPTION_XML_VERSION, "1.0");
				emfResource.save(options) ;
			} catch (IOException e) {
				BonitaStudioLog.error(e) ;
			}
		}
	}

	@Override
	public void export(String targetAbsoluteFilePath) throws IOException {
		checkWritePermission(new File(targetAbsoluteFilePath));
		Organization organization = getContent() ;
		DocumentRoot root = OrganizationFactory.eINSTANCE.createDocumentRoot() ;
		Organization exportedCopy = EcoreUtil.copy(organization)  ;
		exportedCopy.setName(null) ;
		exportedCopy.setDescription(null) ;
		root.setOrganization(exportedCopy) ;
		try{
			File to = new File(targetAbsoluteFilePath) ;
			if(targetAbsoluteFilePath.endsWith(".xml")){
				to.getParentFile().mkdirs() ;
			}else{
				to.mkdirs() ;
			}
			XMLProcessor processor = new OrganizationXMLProcessor() ;

			File target = null;
			if(to.isDirectory()){
				String targetFilename = organization.getName()+".xml";
				target = new File(to,targetFilename) ;
			}else{
				target = to ;
			}
			if(target.exists()){
				if(FileActionDialog.overwriteQuestion(target.getName())){
					PlatformUtil.delete(target,  Repository.NULL_PROGRESS_MONITOR) ;
				}else{
					return ;
				}
			}

			Resource resource = new XMLResourceImpl(URI.createFileURI(target.getAbsolutePath())) ;
			resource.getContents().add(root) ;
			Map<String, String> options = new HashMap<String, String>() ;
			options.put(XMLResource.OPTION_ENCODING, "UTF-8");
			options.put(XMLResource.OPTION_XML_VERSION, "1.0");

			FileOutputStream fos = new FileOutputStream(target)  ;
			processor.save(fos, resource, options)  ;
			fos.close() ;
		}catch (Exception e) {
			BonitaStudioLog.error(e);
		}

	}

	@Override
	protected IWorkbenchPart doOpen() {
		return null;
	}


	@Override
	public IFile getResource() {
		return getParentStore().getResource().getFile(getName());
	}
	
	public boolean isCorrectlySyntaxed(){
		if (getContent()==null){
			return false;
		} else {
			return true;
		}
	}
}