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

import org.apache.maven.artifact.Artifact;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.participant.configuration.ProcessConfigurationChange;
import org.bonitasoft.studio.application.operation.extension.participant.preview.ChangePreview;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationFactory;
import org.bonitasoft.studio.model.configuration.Fragment;


public class JarAddedChange implements ChangePreview, ProcessConfigurationChange {

    private Artifact artifact;
    private ChangePreview parent;

    public JarAddedChange(Artifact artifact, ChangePreview parent) {
        this.artifact = artifact;
        this.parent = parent;
    }

    @Override
    public String getDescription() {
        return String.format(Messages.jarFileAddedChangeDescription, artifact.getFile().getName());
    }

    @Override
    public List<ChangePreview> getDetails() {
        return List.of();
    }

    @Override
    public Kind getKind() {
        return Kind.ADD;
    }

    @Override
    public boolean hasBreakingChanges() {
        return false;
    }

    @Override
    public ChangePreview getParent() {
        return parent;
    }

    public String getJarFileName() {
        return artifact.getFile().getName();
    }

    @Override
    public void apply(Configuration configuration) {
        var otherJarFragmentContainer = getOtherJarFragmentContainer(configuration);
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setExported(true);
        fragment.setKey(artifact.getFile().getName());
        fragment.setType(FragmentTypes.JAR);
        fragment.setValue(artifact.getFile().getName());
        otherJarFragmentContainer.getFragments().add(fragment);
    }

}
