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
package org.bonitasoft.studio.contract.core.constraint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;

import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.codehaus.groovy.ast.ModuleNode;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.jdt.groovy.model.GroovyCompilationUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ConstraintInputIndexerTest {

    @Mock
    private GroovyCompilationUnit groovyCompilationUnit;
    private ConstraintInputIndexer indexer;
    private ArrayList<ContractInput> availableInputs;

    @Before
    public void setUp() throws Exception {
        availableInputs = new ArrayList<ContractInput>();
        configureModuleNode("name.length() > 0");
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        indexer = new ConstraintInputIndexer(constraint, availableInputs, groovyCompilationUnit);
    }

    protected void configureModuleNode(final String source) {
        final CompilationUnit compilationUnit = new CompilationUnit();
        final SourceUnit sourceUnit = SourceUnit.create("unitName", source);
        compilationUnit.addSource(sourceUnit);
        compilationUnit.compile();
        when(groovyCompilationUnit.getModuleNode()).thenReturn(sourceUnit.getAST());
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("unitName.class");
        if(file.exists()) {
            file.delete();
        }
    }

    @Test
    public void should_run_add_a_referenced_inputs() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        availableInputs.add(input);
        assertThat(indexer.run(null).isOK()).isTrue();
        assertThat(indexer.getReferencedInputs()).containsExactly("name");
    }

    @Test
    public void should_run_clear_referenced_inputs() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        availableInputs.add(input);
        assertThat(indexer.run(null).isOK()).isTrue();
        assertThat(indexer.getReferencedInputs()).containsExactly("name");
        configureModuleNode("myName.length() > 0");
        assertThat(indexer.run(null).isOK()).isTrue();
        assertThat(indexer.getReferencedInputs()).isEmpty();
    }

    @Test
    public void should_run_ignore_invalid_module_node() throws Exception {
        when(groovyCompilationUnit.getModuleNode()).thenReturn(new ModuleNode(SourceUnit.create("unitName", "")));
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("name");
        availableInputs.add(input);
        assertThat(indexer.run(null).isOK()).isTrue();
        assertThat(indexer.getReferencedInputs()).isEmpty();
    }


}
