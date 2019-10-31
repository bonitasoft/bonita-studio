package org.bonitasoft.studio.la.application.repository;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.bonitasoft.engine.business.application.exporter.ApplicationNodeContainerConverter;
import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.wiring.BundleWiring;
import org.xml.sax.SAXException;


public class CustomApplicationNodeContainerConverter extends ApplicationNodeContainerConverter {
    
    @Override
    public byte[] marshallToXML(ApplicationNodeContainer applicationNodeContainer)
            throws JAXBException, IOException, SAXException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Bundle bundle = FrameworkUtil.getBundle(JAXBContext.class);
            ClassLoader bundleClassloader = bundle.adapt(BundleWiring.class).getClassLoader();
            //Due to some issue with tycho-surefire-plugin we need to set the proper context classloader
            Thread.currentThread().setContextClassLoader(bundleClassloader);
            return super.marshallToXML(applicationNodeContainer);
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }
    
    @Override
    public ApplicationNodeContainer unmarshallFromXML(byte[] applicationXML)
            throws JAXBException, IOException, SAXException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            Bundle bundle = FrameworkUtil.getBundle(JAXBContext.class);
            ClassLoader bundleClassloader = bundle.adapt(BundleWiring.class).getClassLoader();
            //Due to some issue with tycho-surefire-plugin we need to set the proper context classloader
            Thread.currentThread().setContextClassLoader(bundleClassloader);
            return super.unmarshallFromXML(applicationXML);
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }
    
}
