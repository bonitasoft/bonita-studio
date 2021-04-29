/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.operation.extension.participant.configuration.preview;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.configuration.ProcessConfigurationChange;
import org.bonitasoft.studio.application.operation.extension.participant.preview.ChangePreview;
import org.bonitasoft.studio.model.configuration.Configuration;


public class JarRemovedChange implements ChangePreview,ProcessConfigurationChange {

    private String jarFile;
    private ChangePreview parent;

    public JarRemovedChange(String jarFile, ChangePreview parent) {
        this.jarFile = jarFile;
        this.parent = parent;
    }

    @Override
    public String getDescription() {
        return String.format(Messages.jarFileRemovedChangeDescription, jarFile);
    }

    @Override
    public List<ChangePreview> getDetails() {
        return List.of();
    }

    @Override
    public Kind getKind() {
        return Kind.REMOVE;
    }

    @Override
    public boolean hasBreakingChanges() {
        return false;
    }

    @Override
    public ChangePreview getParent() {
        return parent;
    }

    @Override
    public void apply(Configuration configuration) {
        var otherJarFragmentContainer = getOtherJarFragmentContainer(configuration);
        otherJarFragmentContainer.getFragments().removeIf(f -> Objects.equals(f.getValue(), jarFile));
    }
    

}
