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
package org.bonitasoft.studio.identity.actors.repository;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.bpm.connector.model.implementation.DocumentRoot;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Romain Bioteau
 */
public class ActorFilterImplFileStore extends AbstractFromArtifactFileStore<ConnectorImplementation> {

    public ActorFilterImplFileStore(String fileName, AbstractEMFRepositoryStore<ActorFilterImplFileStore> store) {
        super(fileName, store, ConnectorImplementation.class);
    }

    public ActorFilterImplFileStore(String projectName, String entryInOutputDirectory,
            AbstractEMFRepositoryStore<ActorFilterImplFileStore> store) {
        super(projectName, entryInOutputDirectory, store, ConnectorImplementation.class);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore#extractRootContent(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected ConnectorImplementation extractRootContent(EObject documentRoot) {
        return ((DocumentRoot) documentRoot).getConnectorImplementation();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore#wrapContentWithRoot(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected EObject wrapContentWithRoot(ConnectorImplementation content) {
        final DocumentRoot root = ConnectorImplementationFactory.eINSTANCE.createDocumentRoot();
        root.setConnectorImplementation(EcoreUtil.copy(content));
        return root;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connector.model.definition.AbstractFromArtifactFileStore#makeUnloadableContent()
     */
    @Override
    protected ConnectorImplementation makeUnloadableContent() {
        var connectorImpl = ConnectorImplementationFactory.eINSTANCE.createUnloadableConnectorImplementation();
        connectorImpl.setImplementationId(getName());
        connectorImpl.setImplementationVersion("");
        connectorImpl.setImplementationClassname("");
        return connectorImpl;
    }

}
