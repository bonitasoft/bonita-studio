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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.anyCollection;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractConstraintExpressionWizardTest {

    private ContractConstraint constraint;
    private ContractConstraintExpressionWizard wizard;
    @Mock
    private IPropertySourceProvider propertySourceProvider;
    @Mock
    private IPropertySource propertySource;

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        final Contract c = ProcessFactory.eINSTANCE.createContract();
        c.getConstraints().add(constraint);
        when(propertySourceProvider.getPropertySource(constraint)).thenReturn(propertySource);
        wizard = new ContractConstraintExpressionWizard(constraint, propertySourceProvider);
    }

    @Test
    public void should_addPages_add_editor_wizard_page_with_edition_title_when_expression_exists() throws Exception {
        constraint.setExpression("an expression");
        wizard = new ContractConstraintExpressionWizard(constraint, propertySourceProvider);
        wizard.addPages();
        assertThat(wizard.getPages()).hasSize(1);
        assertThat(wizard.getPages()).extracting("name").contains(ContractConstraintExpressionWizardPage.class.getName());
        assertThat(wizard.getPages()).extracting("title").contains(Messages.bind(Messages.editContentToConstraint, constraint.getName()));
    }

    @Test
    public void should_addPages_add_editor_wizard_page_with_creation_title_when_expression_is_null() throws Exception {
        wizard.addPages();
        assertThat(wizard.getPages()).hasSize(1);
        assertThat(wizard.getPages()).extracting("name").contains(ContractConstraintExpressionWizardPage.class.getName());
        assertThat(wizard.getPages()).extracting("title").contains(Messages.bind(Messages.addContentToConstraint, constraint.getName()));
    }

    @Test
    public void should_addPages_add_editor_wizard_page_with_creation_title_when_expression_is_empty() throws Exception {
        constraint.setExpression("");
        wizard = new ContractConstraintExpressionWizard(constraint, propertySourceProvider);
        wizard.addPages();
        assertThat(wizard.getPages()).extracting("title").contains(Messages.bind(Messages.addContentToConstraint, constraint.getName()));
    }

    @Test
    public void should_performFinish_return_true_and_update_constraint_expression_and_input_names() throws Exception {
        wizard.addPages();
        assertThat(wizard.performFinish()).isTrue();
        verify(propertySource).setPropertyValue(eq(ProcessPackage.Literals.CONTRACT_CONSTRAINT__EXPRESSION), anyString());
        verify(propertySource).setPropertyValue(eq(ProcessPackage.Literals.CONTRACT_CONSTRAINT__INPUT_NAMES), anyCollection());
    }

}
