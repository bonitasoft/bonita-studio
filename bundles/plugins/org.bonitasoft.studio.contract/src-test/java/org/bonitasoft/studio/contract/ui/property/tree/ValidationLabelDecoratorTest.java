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
package org.bonitasoft.studio.contract.ui.property.tree;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.contract.AbstractSWTTestCase;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.swt.graphics.Image;
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
public class ValidationLabelDecoratorTest extends AbstractSWTTestCase {

    private ValidationLabelDecorator validationLabelDecorator;
    private Image errorDecorator;
    @Mock
    private FieldDecoration decorator;
    private Image baseImage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        createDisplayAndRealm();
        validationLabelDecorator = spy(new ValidationLabelDecorator());
        baseImage = createImage();
        errorDecorator = createImage();
        when(decorator.getImage()).thenReturn(errorDecorator);
        doReturn(decorator).when(validationLabelDecorator).getErrorDecorator();
    }


    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (baseImage != null) {
            baseImage.dispose();
        }
        dispose();
    }

    @Test
    public void shoud_decorateText_returns_error_message_if_input_has_no_name() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        assertThat(validationLabelDecorator.decorateText(null, input)).isNotEmpty();
    }

    @Test
    public void shoud_decorateText_returns_null_if_input_has_no_error() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("hello");
        assertThat(validationLabelDecorator.decorateText(null, input)).isNullOrEmpty();
    }

    @Test
    public void shoud_decorateImage_returns_error_decorator_if_input_is_duplicated() throws Exception {
        final Contract c = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("nameInput");
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        input2.setName("nameInput");
        c.getInputs().add(input);
        c.getInputs().add(input2);
        assertThat(validationLabelDecorator.decorateImage(baseImage, input)).isNotNull();
    }

    @Test
    public void shoud_decorateImage_returns_error_null_if_other_duplicated_name() throws Exception {
        final Contract c = ProcessFactory.eINSTANCE.createContract();
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("nameInput");
        final ContractInput input2 = ProcessFactory.eINSTANCE.createContractInput();
        input2.setName("nameInput");
        final ContractInput input3 = ProcessFactory.eINSTANCE.createContractInput();
        input3.setName("otherInput");
        c.getInputs().add(input);
        c.getInputs().add(input2);
        c.getInputs().add(input3);
        assertThat(validationLabelDecorator.decorateImage(baseImage, input3)).isNull();
    }

    @Test
    public void shoud_decorateImage_returns_error_decorator_if_input_has_no_name() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("");
        assertThat(validationLabelDecorator.decorateImage(baseImage, input)).isNotNull();
    }

    @Test
    public void shoud_decorateImage_returns_null_if_no_error() throws Exception {
        final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
        input.setName("input");
        assertThat(validationLabelDecorator.decorateImage(baseImage, input)).isNull();
    }


}
