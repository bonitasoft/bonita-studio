/**
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.businessobject.core.repository;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.bdm.BusinessObjectModelConverter;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;
import org.xml.sax.SAXException;


public class CustomBusinessObjectModelConverter extends BusinessObjectModelConverter {

    @Override
    public byte[] marshall(BusinessObjectModel bom) throws JAXBException, IOException, SAXException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Bundle bundle = FrameworkUtil.getBundle(JAXBContext.class);
            ClassLoader bundleClassloader = bundle.adapt(BundleWiring.class).getClassLoader();
            //Due to some issue with tycho-surefire-plugin we need to set the proper context classloader
            Thread.currentThread().setContextClassLoader(bundleClassloader);
            return super.marshall(bom);
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }
    
    @Override
    public BusinessObjectModel unmarshall(byte[] bomXML) throws JAXBException, IOException, SAXException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Bundle bundle = FrameworkUtil.getBundle(JAXBContext.class);
            ClassLoader bundleClassloader = bundle.adapt(BundleWiring.class).getClassLoader();
            //Due to some issue with tycho-surefire-plugin we need to set the proper context classloader
            Thread.currentThread().setContextClassLoader(bundleClassloader);
            return super.unmarshall(bomXML);
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }
}
