/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.xml.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;

/**
 * @author Romain Bioteau
 */
public class XSDFileStore extends EMFFileStore {

    private static final String ECORE_TYPE_NS = "http://www.eclipse.org/emf/2002/Ecore";

    public XSDFileStore(String fileName, IRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage(PicsConstants.xml);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(Object content) {
        if (content instanceof XSDSchema) {
            Resource emfResource = getEMFResource();
            emfResource.getContents().clear();
            emfResource.getContents().add((EObject) content);
            Map<String, String> options = new HashMap<String, String>();
            options.put(XMLResource.OPTION_ENCODING, "UTF-8");
            options.put(XMLResource.OPTION_XML_VERSION, "1.0");
            try {
                emfResource.save(options);
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
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
        } catch (PartInitException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    private IWorkbenchPage getActivePage() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    }

    public XSDElementDeclaration findElementDeclaration(String namespace, String elementName) {
        XSDSchema schema = (XSDSchema) getContent();
        // check namespace
        if (namespace == null) {
            namespace = "";
        }
        String schemaNamespace = schema.getTargetNamespace();
        if (schemaNamespace == null) {
            schemaNamespace = "";
        }
        if (!namespace.equals(schemaNamespace)) {
            return null;
        }
        // search element
        for (XSDElementDeclaration element : schema.getElementDeclarations()) {
            if (element.getName().equals(elementName)) {
                return element;
            }
        }
        return null;
    }

    public List<String> getElements() {
        List<String> res = new ArrayList<>();
        XSDSchema schema = (XSDSchema) getContent();
        if (schema != null) {
            for (XSDElementDeclaration element : schema.getElementDeclarations()) {
                if (!ECORE_TYPE_NS.equals(element.getTargetNamespace())) {
                    res.add(element.getName());
                }

            }
        }
        return res;
    }
}
