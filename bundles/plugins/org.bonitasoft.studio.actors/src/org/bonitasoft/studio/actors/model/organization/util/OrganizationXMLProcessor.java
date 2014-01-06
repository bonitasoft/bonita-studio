/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
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
package org.bonitasoft.studio.actors.model.organization.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.actors.model.organization.OrganizationPackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OrganizationXMLProcessor extends XMLProcessor {

    /**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public OrganizationXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		OrganizationPackage.eINSTANCE.eClass();
	}

    /**
	 * Register for "*" and "xml" file extensions the OrganizationResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new OrganizationResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new OrganizationResourceFactoryImpl());
		}
		return registrations;
	}
    
    
    /* (non-Javadoc)
     * @see org.eclipse.emf.ecore.xmi.util.XMLProcessor#saveToString(org.eclipse.emf.ecore.resource.Resource, java.util.Map)
     *
     * Use charset for the toString
     */
    @Override
    public String saveToString(Resource resource, Map<?, ?> options) throws IOException{
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      if (options != null){
        Map<Object, Object> mergedOptions = new HashMap<Object, Object>(saveOptions);
        mergedOptions.putAll(options);
        
        ((XMLResource)resource).save(os, mergedOptions);
      } else {
        ((XMLResource)resource).save(os, saveOptions);
      }
      return os.toString("UTF-8");
    }

} //OrganizationXMLProcessor
