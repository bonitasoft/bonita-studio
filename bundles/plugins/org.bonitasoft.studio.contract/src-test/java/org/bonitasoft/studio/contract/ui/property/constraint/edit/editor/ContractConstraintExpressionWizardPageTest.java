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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.AbstractSWTTestCase;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractConstraintExpressionWizardPageTest extends AbstractSWTTestCase {

    private Composite composite;
    private ContractConstraintExpressionWizardPage wizardPage;
    private ContractConstraint constraint;
    @Mock
    private GroovyViewer groovyViewer;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        composite = createDisplayAndRealm();
        constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setExpression("name.length() == 5");
        final Contract c = ProcessFactory.eINSTANCE.createContract();
        c.getConstraints().add(constraint);
        final ContractInput name = ProcessFactory.eINSTANCE.createContractInput();
        name.setName("name");
        c.getInputs().add(name);
        final SourceViewer sourceViewer = new SourceViewer(composite, null, SWT.NONE);
        sourceViewer.setDocument(new Document());
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        wizardPage = new ContractConstraintExpressionWizardPage(constraint, c.getInputs());
        //        doReturn(groovyViewer).when(wizardPage).createSourceViewer(any(Composite.class));
        //        doReturn(groovyViewer.getSourceViewer()).when(wizardPage).getSourceViewer();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_createControl() throws Exception {
        wizardPage.createControl(composite);
        assertThat(wizardPage.getControl()).isNotNull();
    }

}
