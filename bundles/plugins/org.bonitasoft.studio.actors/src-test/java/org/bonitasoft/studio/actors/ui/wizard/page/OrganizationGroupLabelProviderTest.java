/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.actors.ui.wizard.page;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.actors.model.organization.Group;
import org.bonitasoft.studio.actors.model.organization.OrganizationFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class OrganizationGroupLabelProviderTest {

    private OrganizationGroupLabelProvider organizationGroupLabelProvider;
    private Group shortGroup;
    private Group longGroup;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        organizationGroupLabelProvider = new OrganizationGroupLabelProvider();
        longGroup = OrganizationFactory.eINSTANCE.createGroup();
        longGroup.setName("longGroup");
        longGroup.setParentPath("/acme/group1/group2/group3/group4/group5/group6");
        shortGroup = OrganizationFactory.eINSTANCE.createGroup();
        shortGroup.setName("shortGroup");
        shortGroup.setParentPath("/acme/");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_getText_returns_group_path() throws Exception {
        assertThat(organizationGroupLabelProvider.getText(shortGroup)).isEqualTo(GroupContentProvider.getGroupPath(shortGroup));
    }

    @Test
    public void should_getText_returns_group_path_with_ellipsis() throws Exception {
        assertThat(organizationGroupLabelProvider.getText(longGroup)).hasSize(OrganizationGroupLabelProvider.MAX_GROUP_PATH_LENGTH).endsWith("longGroup")
                .contains("...");
    }

    @Test
    public void should_getText_returns_empty_string() throws Exception {
        assertThat(organizationGroupLabelProvider.getText(null)).isEmpty();
    }
}
