/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BMW Car IT - Initial API and implementation
 *     Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.migration;

import java.io.File;
import java.io.FileReader;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.edapt.common.URIUtils;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * Helper methods for extraction of namespace URI and version from a model file.
 * 
 * @author herrmama
 * @author $Author$
 * @version $Rev$
 * @levd.rating YELLOW Hash: 680A9E3F1CAE7963EB55E8E8749FF9E6
 */
public final class ReleaseUtils {

	/** Constructor. */
	private ReleaseUtils() {
		// hidden, since this class only provides static helper methods
	}

	/** Extract the namespace URI from a model. */
	public static String getNamespaceURI(URI uri) {
		return getNamespaceURI_SAX(URIUtils.getJavaFile(uri));
	}

	/** Extract the namespace URI from a model file using SAX. */
	public static String getNamespaceURI_SAX(File file) {

		ContentHandler contentHandler = new ContentHandler();
		FileReader fileReader = null;
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(contentHandler);

			fileReader = new FileReader(file);
			
			reader.parse(new InputSource(fileReader));
		} catch (Exception e) {
			// loading should fail
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (final Exception e) {
				// ignore
			}
		}

		return contentHandler.getNsURI();
	}

	/** Content handler for extraction of namespace URI using SAX. */
	private static class ContentHandler extends DefaultHandler {

		/** Namespace URI. */
		private String nsURI;

		/** {@inheritDoc} */
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			if (!ExtendedMetaData.XMI_URI.equals(uri)
					&& !ExtendedMetaData.XML_SCHEMA_URI.equals(uri)) {
				nsURI = uri;
				throw new SAXException();
			}
		}

		/** Returns the namespace URI. */
		public String getNsURI() {
			return nsURI;
		}
	}

	/** Extract the namespace URI from a model file using EMF Resource loading. */
	public static String getNamespaceURI_Registry(URI modelURI) {
		ResourceSet resourceSet = new ResourceSetImpl();

		// register delegating package registry
		PackageRegistry registry = new PackageRegistry(
				resourceSet.getPackageRegistry());
		resourceSet.setPackageRegistry(registry);

		Resource resource = resourceSet.createResource(modelURI);
		try {
			resource.load(null);
		} catch (Exception e) {
			// loading should fail here
		}

		return registry.getNsURI();
	}

	/**
	 * Package registry for extraction of namespace URI using EMF Resource
	 * loading.
	 */
	private static class PackageRegistry implements EPackage.Registry {

		/** Registry to which method calls are delegated. */
		private final EPackage.Registry delegate;

		/** Namespace URI. */
		private String nsURI;

		/**
		 * Default constructor.
		 * 
		 * @param delegate
		 *            Registry to which method calls are delegated
		 */
		public PackageRegistry(EPackage.Registry delegate) {
			this.delegate = delegate;
		}

		/** {@inheritDoc} */
		public void clear() {
			delegate.clear();
		}

		/** {@inheritDoc} */
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/** {@inheritDoc} */
		public boolean containsValue(Object value) {
			return delegate.containsValue(value);
		}

		/** {@inheritDoc} */
		@SuppressWarnings("unchecked")
		public Set entrySet() {
			return delegate.entrySet();
		}

		/** {@inheritDoc} */
		@Override
		public boolean equals(Object o) {
			return delegate.equals(o);
		}

		/** {@inheritDoc} */
		public Object get(Object key) {
			return delegate.get(key);
		}

		/** {@inheritDoc} */
		public EFactory getEFactory(String nsURI) {
			return delegate.getEFactory(nsURI);
		}

		/** {@inheritDoc} */
		public EPackage getEPackage(String nsURI) {
			if (this.nsURI == null) {
				this.nsURI = nsURI;
			}
			return delegate.getEPackage(nsURI);
		}

		/** {@inheritDoc} */
		@Override
		public int hashCode() {
			return delegate.hashCode();
		}

		/** {@inheritDoc} */
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/** {@inheritDoc} */
		@SuppressWarnings("unchecked")
		public Set keySet() {
			return delegate.keySet();
		}

		/** {@inheritDoc} */
		public Object put(String key, Object value) {
			return delegate.put(key, value);
		}

		/** {@inheritDoc} */
		@SuppressWarnings("unchecked")
		public void putAll(Map t) {
			delegate.putAll(t);
		}

		/** {@inheritDoc} */
		public Object remove(Object key) {
			return delegate.remove(key);
		}

		/** {@inheritDoc} */
		public int size() {
			return delegate.size();
		}

		/** {@inheritDoc} */
		@SuppressWarnings("unchecked")
		public Collection values() {
			return delegate.values();
		}

		/** Returns the namespace URI. */
		public String getNsURI() {
			return nsURI;
		}

	}
}
