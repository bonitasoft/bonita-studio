/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.xml.export;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.XMLData;
import org.bonitasoft.studio.xml.repository.XSDFileStore;
import org.bonitasoft.studio.xml.repository.XSDRepositoryStore;
import org.eclipse.emf.ecore.EObject;

/**
 * @author Romain Bioteau
 */
public class XSDResourceProvider implements IBOSArchiveFileStoreProvider {

    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(AbstractProcess process, Configuration configuration) {
        final Set<IRepositoryFileStore> files = new HashSet<IRepositoryFileStore>();
        final XSDRepositoryStore store = (XSDRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(XSDRepositoryStore.class);

        List<EObject> xmlData = ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.XML_DATA);
        for (EObject item : xmlData) {
            XMLData data = (XMLData) item;
            Optional<XSDFileStore> artifact = Optional.ofNullable(store.findArtifactWithNamespace(data.getNamespace()));
            artifact.ifPresent(files::add);
        }

        return files;
    }

}
