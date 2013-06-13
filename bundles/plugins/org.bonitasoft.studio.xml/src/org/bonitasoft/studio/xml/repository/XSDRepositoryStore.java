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
package org.bonitasoft.studio.xml.repository;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.xml.Messages;
import org.bonitasoft.studio.xml.XMLPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDAdapterFactory;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

/**
 * @author Romain Bioteau
 *
 */
public class XSDRepositoryStore extends AbstractEMFRepositoryStore<XSDFileStore> {

    public static final String PREFIX_IN_BAR = "xsd/";
    private static final String STORE_NAME = "xsd";
    private static final Set<String> extensions = new HashSet<String>() ;
    static{
        extensions.add("xsd") ;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public XSDFileStore createRepositoryFileStore(String fileName) {
        return new XSDFileStore(fileName,this);
    }


    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.xsdRepositoryName;
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.xml);
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getCompatibleExtensions()
     */
    @Override
    public Set<String> getCompatibleExtensions() {
        return extensions;
    }

    public IStatus isXSDFileValid(File file) {
        Resource resource = null ;
        final String filePath = file.getAbsolutePath();
		try {
            XSDResourceFactoryImpl factory = new XSDResourceFactoryImpl();
            resource = factory.createResource(URI.createFileURI(filePath));
            resource.load(Collections.EMPTY_MAP);
            XSDSchema schema = (XSDSchema) resource.getContents().get(0);
            if(schema.getTargetNamespace() == null){
                return new Status(IStatus.ERROR, XMLPlugin.PLUGIN_ID, Messages.missingATargetNamespace);
            }
        } catch (Exception ex) {
            return new Status(IStatus.ERROR, XMLPlugin.PLUGIN_ID, "An xsd file seems corrupted in your workspace ("+filePath+").\n"+ex.getMessage(),ex);
        } finally{
            if(resource != null){
                resource.unload() ;
            }
        }
        return Status.OK_STATUS;
    }

    public XSDElementDeclaration findElementDeclaration(String namespace, String elementName) {
        for (IRepositoryFileStore artifact : getChildren()) {
            XSDFileStore file = (XSDFileStore) artifact ;
            XSDElementDeclaration decl = file.findElementDeclaration(namespace, elementName);
            if (decl != null) {
                return decl;
            }
        }
        return null;
    }

    public XSDFileStore findArtifactWithNamespace(String xmlNamespace) {
        for (IRepositoryFileStore artifact : getChildren()) {
            XSDSchema schema = (XSDSchema) artifact.getContent() ;
            if (schema.getTargetNamespace() != null && schema.getTargetNamespace().equals(xmlNamespace)) {
                return (XSDFileStore) artifact;
            }
        }
        return null;
    }


    @Override
    protected void addAdapterFactory(ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new XSDAdapterFactory()) ;
    }

}
