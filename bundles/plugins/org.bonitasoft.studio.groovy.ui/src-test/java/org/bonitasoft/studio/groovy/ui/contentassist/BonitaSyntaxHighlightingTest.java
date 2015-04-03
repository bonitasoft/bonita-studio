/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.contentassist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class BonitaSyntaxHighlightingTest {

    private BonitaSyntaxHighlighting bonitaSyntaxHighlighting;

    @Mock
    private IJavaProject javaProject;

    @Mock
    private IType employeeDAOType;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private BusinessObjectModelRepositoryStore boRepoStore;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        bonitaSyntaxHighlighting = spy(new BonitaSyntaxHighlighting(repositoryAccessor));
        when(repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)).thenReturn(boRepoStore);
        doReturn(javaProject).when(bonitaSyntaxHighlighting).currentJavaProject();
    }

    @Test
    public void should_add_engine_provided_expression_in_additional_GJDK_keywords() throws Exception {
        doReturn(new IClasspathEntry[0]).when(javaProject).getRawClasspath();

        assertThat(bonitaSyntaxHighlighting.getAdditionalGJDKKeywords()).containsAll(BonitaSyntaxHighlighting.BONITA_KEYWORDS);
    }

    @Test
    public void should_add_business_object_dao() throws Exception {
        doReturn(Lists.newArrayList(employeeDAOType)).when(boRepoStore).allBusinessObjectDao(javaProject);
        doReturn("EmployeeDAO").when(employeeDAOType).getElementName();

        assertThat(bonitaSyntaxHighlighting.getAdditionalGJDKKeywords()).contains("employeeDAO");
    }
}
