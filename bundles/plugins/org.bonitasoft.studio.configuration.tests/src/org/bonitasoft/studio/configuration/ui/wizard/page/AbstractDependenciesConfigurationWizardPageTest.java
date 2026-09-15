/**
 * Copyright (C) 2026 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.junit.Test;

public class AbstractDependenciesConfigurationWizardPageTest {

    @Test
    public void should_unselect_the_other_version_of_the_picked_library() {
        Fragment inPlace = jar("xmlbeans-5.0.3.jar", true);
        Fragment picked = jar("xmlbeans-5.4.0.jar", true);
        AbstractDependenciesConfigurationWizardPage page = pageWith(inPlace, picked);

        List<String> unselected = page.unselectOtherVersions(picked);

        assertThat(unselected).containsExactly("xmlbeans-5.0.3.jar");
        assertThat(inPlace.isExported()).isFalse();
        assertThat(picked.isExported()).isTrue();
    }

    @Test
    public void should_leave_an_unversioned_jar_of_the_same_base_selected() {
        // the generated connector jar is named after its container and holds no version
        Fragment generated = jar("email.jar", true);
        Fragment picked = jar("email-1.0.jar", true);
        AbstractDependenciesConfigurationWizardPage page = pageWith(generated, picked);

        List<String> unselected = page.unselectOtherVersions(picked);

        assertThat(unselected).isEmpty();
        assertThat(generated.isExported()).isTrue();
    }

    @Test
    public void should_not_arbitrate_when_the_picked_jar_holds_no_version() {
        Fragment inPlace = jar("email-1.0.jar", true);
        Fragment picked = jar("email.jar", true);
        AbstractDependenciesConfigurationWizardPage page = pageWith(inPlace, picked);

        List<String> unselected = page.unselectOtherVersions(picked);

        assertThat(unselected).isEmpty();
        assertThat(inPlace.isExported()).isTrue();
    }

    @Test
    public void should_leave_another_library_alone() {
        Fragment other = jar("xmlbeans-5.0.3.jar", true);
        Fragment picked = jar("commons-text-1.9.jar", true);
        AbstractDependenciesConfigurationWizardPage page = pageWith(other, picked);

        List<String> unselected = page.unselectOtherVersions(picked);

        assertThat(unselected).isEmpty();
        assertThat(other.isExported()).isTrue();
    }

    @Test
    public void should_ignore_the_versions_the_user_did_not_select() {
        Fragment unselectedInPlace = jar("xmlbeans-5.0.3.jar", false);
        Fragment picked = jar("xmlbeans-5.4.0.jar", true);
        AbstractDependenciesConfigurationWizardPage page = pageWith(unselectedInPlace, picked);

        assertThat(page.unselectOtherVersions(picked)).isEmpty();
    }

    @Test
    public void should_leave_alone_a_version_the_user_excluded_elsewhere() {
        // exported by the connector, but excluded in OTHER: the explicit exclusion wins and the jar is
        // already out of the archive, so there is nothing to unselect nor to announce
        Fragment exportedByConnector = jar("xmlbeans-5.0.3.jar", true);
        Fragment excludedByUser = jar("xmlbeans-5.0.3.jar", false);
        Fragment picked = jar("xmlbeans-5.4.0.jar", true);
        AbstractDependenciesConfigurationWizardPage page = new JavaDependenciesConfigurationWizardPage();
        page.configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        container(page.configuration, "myConnector", exportedByConnector);
        container(page.configuration, FragmentTypes.OTHER, excludedByUser, picked);

        List<String> unselected = page.unselectOtherVersions(picked);

        assertThat(unselected).isEmpty();
        assertThat(exportedByConnector.isExported()).isTrue();
    }

    @Test
    public void should_leave_the_version_in_place_alone_when_the_picked_jar_is_excluded_elsewhere() {
        // mirror of the case above, on the picked jar this time: it is excluded in OTHER, so checking it
        // in the connector embeds nothing. Unselecting the version in place would leave the archive
        // without any version of the library, and announce a swap that did not happen.
        Fragment picked = jar("xmlbeans-5.4.0.jar", true);
        Fragment excludedByUser = jar("xmlbeans-5.4.0.jar", false);
        Fragment inPlace = jar("xmlbeans-5.0.3.jar", true);
        AbstractDependenciesConfigurationWizardPage page = new JavaDependenciesConfigurationWizardPage();
        page.configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        container(page.configuration, "myConnector", picked);
        container(page.configuration, FragmentTypes.OTHER, excludedByUser, inPlace);

        List<String> unselected = page.unselectOtherVersions(picked);

        assertThat(unselected).isEmpty();
        assertThat(inPlace.isExported()).isTrue();
    }

    private AbstractDependenciesConfigurationWizardPage pageWith(Fragment... fragments) {
        AbstractDependenciesConfigurationWizardPage page = new JavaDependenciesConfigurationWizardPage();
        page.configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        container(page.configuration, FragmentTypes.OTHER, fragments);
        return page;
    }

    private FragmentContainer container(Configuration configuration, String id, Fragment... fragments) {
        FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId(id);
        container.getFragments().addAll(List.of(fragments));
        configuration.getProcessDependencies().add(container);
        return container;
    }

    private Fragment jar(String name, boolean exported) {
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setKey(name);
        fragment.setValue(name);
        fragment.setType(FragmentTypes.JAR);
        fragment.setExported(exported);
        return fragment;
    }

}
