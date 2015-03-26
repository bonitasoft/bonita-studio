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
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.engine.bdm.dao.BusinessObjectDAO;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IRegion;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class BonitaSyntaxHighlightingTest {

    @Spy
    private BonitaSyntaxHighlighting bonitaSyntaxHighlighting;
    @Mock
    private IJavaProject javaProject;
    @Mock
    private IClasspathEntry repositoryDependenies;
    @Mock
    private IPackageFragmentRoot packageFragmentRoot;
    @Mock
    private ITypeHierarchy typeHierarchy;
    @Mock
    private IType daoType;
    @Mock
    private IType employeeDAOType;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        doReturn(javaProject).when(bonitaSyntaxHighlighting).currentJavaProject();
    }

    @Test
    public void should_add_engine_provided_expression_in_additional_GJDK_keywords() throws Exception {
        doReturn(new IClasspathEntry[0]).when(javaProject).getRawClasspath();

        assertThat(bonitaSyntaxHighlighting.getAdditionalGJDKKeywords()).containsAll(BonitaSyntaxHighlighting.BONITA_KEYWORDS);
    }

    @Test
    public void should_create_a_region_with_bdm_jar() throws Exception {
        doReturn(new Path("repositoryDependencies")).when(repositoryDependenies).getPath();
        doReturn(new IClasspathEntry[] { repositoryDependenies }).when(javaProject).getRawClasspath();
        doReturn("bdm-client-pojo.jar").when(packageFragmentRoot).getElementName();
        doReturn(new IPackageFragmentRoot[] { packageFragmentRoot }).when(javaProject).findPackageFragmentRoots(repositoryDependenies);

        assertThat(bonitaSyntaxHighlighting.regionWithBDM(javaProject).contains(packageFragmentRoot)).isTrue();
    }

    @Test
    public void should_add_business_object_dao() throws Exception {
        doReturn(daoType).when(javaProject).findType(BusinessObjectDAO.class.getName());
        doReturn(typeHierarchy).when(javaProject).newTypeHierarchy(eq(daoType), notNull(IRegion.class), notNull(IProgressMonitor.class));
        doReturn(new IType[] { employeeDAOType }).when(typeHierarchy).getAllSubtypes(daoType);
        doReturn("EmployeeDAO").when(employeeDAOType).getElementName();

        assertThat(bonitaSyntaxHighlighting.getAdditionalGJDKKeywords()).contains("employeeDAO");
    }
}
