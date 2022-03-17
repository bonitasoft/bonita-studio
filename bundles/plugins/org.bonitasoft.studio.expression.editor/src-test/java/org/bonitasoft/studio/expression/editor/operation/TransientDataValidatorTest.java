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
package org.bonitasoft.studio.expression.editor.operation;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 * 
 */
public class TransientDataValidatorTest {

    private TransientDataValidator validator;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        validator = new TransientDataValidator();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_validate_return_warning_status() throws Exception {
        Expression inputExpression = ExpressionFactory.eINSTANCE.createExpression();
        Data transientData = ProcessFactory.eINSTANCE.createData();
        transientData.setTransient(true);
        inputExpression.getReferencedElements().add(transientData);
        validator.setInputExpression(inputExpression);
        IStatus status = validator.validate("");
        assertThat(status.getSeverity()).isEqualTo(IStatus.WARNING);
    }

    @Test
    public void should_validate_return_valid_status() throws Exception {
        Expression inputExpression = ExpressionFactory.eINSTANCE.createExpression();
        Data transientData = ProcessFactory.eINSTANCE.createData();
        transientData.setTransient(false);
        inputExpression.getReferencedElements().add(transientData);
        validator.setInputExpression(inputExpression);
        IStatus status = validator.validate("");
        assertThat(status.getSeverity()).isEqualTo(IStatus.OK);
    }
}
