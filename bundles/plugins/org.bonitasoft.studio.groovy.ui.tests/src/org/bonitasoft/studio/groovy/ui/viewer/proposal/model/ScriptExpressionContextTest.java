/**
 * Copyright (C) 2026 BonitaSoft S.A.
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
package org.bonitasoft.studio.groovy.ui.viewer.proposal.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.studio.groovy.ScriptVariable;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jdt.core.IJavaProject;
import org.junit.Rule;
import org.junit.Test;

public class ScriptExpressionContextTest {

    private static final String DAO_TYPE = "com.company.model.EmployeeDAO";

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_create_category_without_proposals_when_dao_type_cannot_be_resolved() throws Exception {
        IJavaProject javaProject = mock(IJavaProject.class);
        when(javaProject.findType(DAO_TYPE)).thenReturn(null);
        ScriptVariable variable = new ScriptVariable("employeeDAO", DAO_TYPE);

        Category category = ScriptExpressionContext.createBusinessObjectDaoCategory(variable,
                new BusinessObjectModel(), javaProject);

        assertThat(category).isNotNull();
        assertThat(category.getId()).isEqualTo(DAO_TYPE);
        assertThat(category.hasProposals()).isFalse();
    }
}
