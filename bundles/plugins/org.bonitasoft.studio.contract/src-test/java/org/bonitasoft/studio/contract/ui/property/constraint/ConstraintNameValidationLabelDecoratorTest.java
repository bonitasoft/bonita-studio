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
package org.bonitasoft.studio.contract.ui.property.constraint;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.core.ContractConstraintUtil;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ContractInputType;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.swt.graphics.Image;
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
public class ConstraintNameValidationLabelDecoratorTest extends AbstractSWTTestCase {

    private Composite container;
    private ConstraintNameValidationLabelDecorator decorator;
    private Image baseImage;
    private Image decoratorImage;

    @Mock
    private FieldDecoration fieldDecorator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        container = createDisplayAndRealm();
        decorator = spy(new ConstraintNameValidationLabelDecorator());
        decoratorImage = createImage();
        baseImage = createImage();
        when(fieldDecorator.getImage()).thenReturn(decoratorImage);
        doReturn(fieldDecorator).when(decorator).getErrorDecorator();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (baseImage != null) {
            baseImage.dispose();
        }
        if (decoratorImage != null) {
            decoratorImage.dispose();
        }
        dispose();
    }

    @Test
    public void should_isProperty_returns_true() throws Exception {
        assertThat(decorator.isLabelProperty(null, null)).isTrue();
    }

    @Test
    public void should_decorateImage_returns_null() throws Exception {
        final ContractInput ageInput = ProcessFactory.eINSTANCE.createContractInput();
        ageInput.setName("age");
        ageInput.setType(ContractInputType.INTEGER);
        final ContractConstraint ageConstraint = ContractConstraintUtil.createConstraint("legal age", "age >= 18", "not legal age", ageInput);
        assertThat(decorator.decorateImage(baseImage, ageConstraint)).isNull();
    }

    @Test
    public void should_decorateImage_returns_a_decorator_image_for_duplicated_constraint_name() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput ageInput = ProcessFactory.eINSTANCE.createContractInput();
        ageInput.setName("age");
        ageInput.setType(ContractInputType.INTEGER);
        final ContractConstraint ageConstraint = ContractConstraintUtil.createConstraint("legal age", "age >= 18", "not legal age", ageInput);
        final ContractConstraint ageConstraint2 = ContractConstraintUtil.createConstraint("legal age", "age >= 18", "not legal age", ageInput);
        contract.getInputs().add(ageInput);
        contract.getConstraints().add(ageConstraint);
        contract.getConstraints().add(ageConstraint2);
        assertThat(decorator.decorateImage(baseImage, ageConstraint)).isNotNull();
    }

    @Test
    public void should_decorateImage_returns_null_for_other_duplicated_constraint_name() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput ageInput = ProcessFactory.eINSTANCE.createContractInput();
        ageInput.setName("age");
        ageInput.setType(ContractInputType.INTEGER);
        final ContractConstraint ageConstraint = ContractConstraintUtil.createConstraint("legal age", "age >= 18", "not legal age", ageInput);
        final ContractConstraint ageConstraint2 = ContractConstraintUtil.createConstraint("legal age", "age >= 18", "not legal age", ageInput);
        final ContractConstraint ageConstraint3 = ContractConstraintUtil.createConstraint("age is positive", "age > 0", "age not positive", ageInput);
        contract.getInputs().add(ageInput);
        contract.getConstraints().add(ageConstraint);
        contract.getConstraints().add(ageConstraint2);
        contract.getConstraints().add(ageConstraint3);
        assertThat(decorator.decorateImage(baseImage, ageConstraint3)).isNull();
    }

    @Test
    public void should_decorateImage_returns_a_decorator_image_for_empty_constraint_name() throws Exception {
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final ContractInput ageInput = ProcessFactory.eINSTANCE.createContractInput();
        ageInput.setName("age");
        ageInput.setType(ContractInputType.INTEGER);
        final ContractConstraint ageConstraint = ContractConstraintUtil.createConstraint("", "age >= 18", "not legal age", ageInput);
        contract.getInputs().add(ageInput);
        contract.getConstraints().add(ageConstraint);
        assertThat(decorator.decorateImage(baseImage, ageConstraint)).isNotNull();
    }

}
