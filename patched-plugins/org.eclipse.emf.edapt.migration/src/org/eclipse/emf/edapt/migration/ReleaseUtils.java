/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 * Johannes Faltermeier - Extension
 *******************************************************************************/
package org.eclipse.emf.edapt.migration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.edapt.internal.common.URIUtils;
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
 * @authot Johannes Faltermeier
 */
public final class ReleaseUtils {

	/** Constructor. */
	private ReleaseUtils() {
		// hidden, since this class only provides static helper methods
	}

	/** Extract the namespace URI from a model. */
	public static String getNamespaceURI(URI uri) {
		return getNamespaceURI_SAX(URIUtils.getInputStream(uri));
	}

	/**
	 * Extract all namespace URIs from a model.
	 *
	 * @since 1.2
	 */
	public static Set<String> getAllNamespaceURIsFromPrefixes(URI uri) {
		final InputStream inputStream = URIUtils.getInputStream(uri);
		if (inputStream == null) {
			return Collections.emptySet();
		}
		try {
			final XMLReader reader = XMLReaderFactory.createXMLReader();
			final NameSpaceURIsHandler contentHandler = new NameSpaceURIsHandler();
			reader.setContentHandler(contentHandler);
			reader.parse(new InputSource(inputStream));
			return contentHandler.getNsURIs();
		} catch (final SAXException ex) {
			return Collections.emptySet();
		} catch (final IOException ex) {
			return Collections.emptySet();
		}
	}

	/** Extract the namespace URI from a model file using SAX. */
	public static String getNamespaceURI_SAX(File file) {

		final ContentHandler contentHandler = new ContentHandler();
		FileReader fileReader = null;
		try {
			final XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(contentHandler);

			fileReader = new FileReader(file);

			reader.parse(new InputSource(fileReader));
		} catch (final Exception e) {
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

	/**
	 * Extract the namespace URI from an inputstream using SAX.
	 *
	 * @since 1.1
	 */
	public static String getNamespaceURI_SAX(InputStream inputStream) {

		final ContentHandler contentHandler = new ContentHandler();
		try {
			final XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(contentHandler);

			reader.parse(new InputSource(inputStream));
		} catch (final Exception e) {
			// loading should fail
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
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

	/**
	 * Handler for getting all namespace uris.
	 * 
	 * @author Johannes Faltermeier
	 *
	 */
	private static class NameSpaceURIsHandler extends DefaultHandler {

		private final Set<String> namespaceURIs = new LinkedHashSet<String>();

		@Override
		public void startPrefixMapping(String prefix, String uri) throws SAXException {
			super.startPrefixMapping(prefix, uri);
			if (!uri.equals(ExtendedMetaData.XMI_URI) && !uri.equals(ExtendedMetaData.XML_SCHEMA_URI)
				&& !uri.equals(ExtendedMetaData.XSI_URI)) {
				namespaceURIs.add(uri);
			}
		}

		/**
		 * @return the namespace URIs.
		 */
		public Set<String> getNsURIs() {
			return namespaceURIs;
		}
	}

	/** Extract the namespace URI from a model file using EMF Resource loading. */
	public static String getNamespaceURI_Registry(URI modelURI) {
		final ResourceSet resourceSet = new ResourceSetImpl();

		// register delegating package registry
		final PackageRegistry registry = new PackageRegistry(
			resourceSet.getPackageRegistry());
		resourceSet.setPackageRegistry(registry);

		final Resource resource = resourceSet.createResource(modelURI);
		try {
			resource.load(null);
		} catch (final Exception e) {
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
		@Override
		public void clear() {
			delegate.clear();
		}

		/** {@inheritDoc} */
		@Override
		public boolean containsKey(Object key) {
			return delegate.containsKey(key);
		}

		/** {@inheritDoc} */
		@Override
		public boolean containsValue(Object value) {
			return delegate.containsValue(value);
		}

		/** {@inheritDoc} */
		@Override
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
		@Override
		public Object get(Object key) {
			return delegate.get(key);
		}

		/** {@inheritDoc} */
		@Override
		public EFactory getEFactory(String nsURI) {
			return delegate.getEFactory(nsURI);
		}

		/** {@inheritDoc} */
		@Override
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
		@Override
		public boolean isEmpty() {
			return delegate.isEmpty();
		}

		/** {@inheritDoc} */
		@Override
		@SuppressWarnings("unchecked")
		public Set keySet() {
			return delegate.keySet();
		}

		/** {@inheritDoc} */
		@Override
		public Object put(String key, Object value) {
			return delegate.put(key, value);
		}

		/** {@inheritDoc} */
		@Override
		@SuppressWarnings("unchecked")
		public void putAll(Map t) {
			delegate.putAll(t);
		}

		/** {@inheritDoc} */
		@Override
		public Object remove(Object key) {
			return delegate.remove(key);
		}

		/** {@inheritDoc} */
		@Override
		public int size() {
			return delegate.size();
		}

		/** {@inheritDoc} */
		@Override
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
