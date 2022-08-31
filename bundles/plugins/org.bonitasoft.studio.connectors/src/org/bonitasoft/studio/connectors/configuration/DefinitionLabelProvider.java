/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.connectors.configuration;

import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.eclipse.swt.graphics.Image;


/**
 * @author Romain Bioteau
 *
 */
public class DefinitionLabelProvider extends ConnectorAssociationLabelProvider {

    public DefinitionLabelProvider(DefinitionResourceProvider resourceProvider, IDefinitionRepositoryStore store) {
        super(resourceProvider, store);
    }

    @Override
    public String getText(Object element) {
        return getDefinitionText((DefinitionMapping) element);
    }

    @Override
    public Image getImage(Object element) {
        return getDefinitionImage((DefinitionMapping) element);
    }
}
