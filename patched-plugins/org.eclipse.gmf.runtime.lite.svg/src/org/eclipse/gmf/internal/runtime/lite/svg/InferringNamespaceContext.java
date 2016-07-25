/**
 * Copyright (c) 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik - initial API and implementation
 */
package org.eclipse.gmf.internal.runtime.lite.svg;

import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class InferringNamespaceContext implements NamespaceContext {

	private Node namespaceContext;

	public InferringNamespaceContext(Node namespaceContext) {
		this.namespaceContext = namespaceContext;
	}

	public String getNamespaceURI(String prefix) {
		if (prefix.equals(XMLConstants.XML_NS_PREFIX)) {
			return XMLConstants.XML_NS_URI;
		} else if (prefix.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
			return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
		} else {
			return inferNamespaceURI(prefix);
		}
	}

	/*
	 * @see com.sun.org.apache.xml.internal.utils.PrefixResolverDefault
	 */
	protected String inferNamespaceURI(String prefix) {
		Node parent = namespaceContext;
		String namespace = null;
		int type;
		while ((null != parent) && (null == namespace) && (((type = parent.getNodeType()) == Node.ELEMENT_NODE) || (type == Node.ENTITY_REFERENCE_NODE))) {
			if (type == Node.ELEMENT_NODE) {
				if (parent.getNodeName().indexOf(prefix + ":") == 0) {
					return parent.getNamespaceURI();
				}
				NamedNodeMap nnm = parent.getAttributes();
				for (int i = 0; i < nnm.getLength(); i++) {
					Node attr = nnm.item(i);
					String aname = attr.getNodeName();
					boolean isPrefix = aname.startsWith("xmlns:");
					if (isPrefix || aname.equals("xmlns")) {
						int index = aname.indexOf(':');
						String p = isPrefix ? aname.substring(index + 1) : "";
						if (p.equals(prefix)) {
							namespace = attr.getNodeValue();
							break;
						}
					}
				}
			}
			parent = parent.getParentNode();
		}
		return namespace;
	}

	public String getPrefix(String namespaceURI) {
		return null;
	}

	@SuppressWarnings("unchecked")
	public Iterator getPrefixes(String namespaceURI) {
		return null;
	}
}
