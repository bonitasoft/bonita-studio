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
package org.bonitasoft.studio.connectors.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.bpm.connector.model.implementation.DocumentRoot;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ui.IWorkbenchPart;

public class ConnectorImplFileStore extends EMFFileStore<ConnectorImplementation> {

	public ConnectorImplFileStore(String fileName, IRepositoryStore<ConnectorImplFileStore> store) {
		super(fileName, store);
	}

	@Override
	protected ConnectorImplementation doGetContent() throws ReadFileStoreException {
		DocumentRoot root = (DocumentRoot) super.doGetContent();
		if (root != null) {
			return root.getConnectorImplementation();
		}
		ConnectorImplementation unloadableImpl = ConnectorImplementationFactory.eINSTANCE
				.createUnloadableConnectorImplementation();
		unloadableImpl.setImplementationId(getName());
		unloadableImpl.setImplementationVersion("");
		unloadableImpl.setImplementationClassname("");
		return unloadableImpl;
	}

	@Override
	protected void doSave(Object content) {
		if (content instanceof ConnectorImplementation) {
			Resource emfResource = getEMFResource();
			emfResource.getContents().clear();
			DocumentRoot root = ConnectorImplementationFactory.eINSTANCE.createDocumentRoot();
			root.setConnectorImplementation((ConnectorImplementation) EcoreUtil.copy((EObject) content));
			emfResource.getContents().add(root);
			try {
				Map<String, Object> options = new HashMap<>();
				options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
				options.put(XMLResource.OPTION_ENCODING, "UTF-8");
				options.put(XMLResource.OPTION_XML_VERSION, "1.0");
				emfResource.save(options);
			} catch (IOException e) {
				BonitaStudioLog.error(e);
			}
		}

	}

	@Override
	protected IWorkbenchPart doOpen() {
		return null;
	}

	@Override
	protected void doDelete() {
	}

}
