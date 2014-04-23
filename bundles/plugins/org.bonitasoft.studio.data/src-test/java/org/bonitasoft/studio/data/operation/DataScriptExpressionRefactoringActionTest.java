/**
 * 
 */
package org.bonitasoft.studio.data.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 * 
 */
public class DataScriptExpressionRefactoringActionTest {

    private DataScriptExpressionRefactoringAction refactoringAction;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        refactoringAction = new DataScriptExpressionRefactoringAction(ProcessFactory.eINSTANCE.createData(), "myData", "",
                Collections.<Expression> emptyList(),
                Collections.<Expression> emptyList(),
                new CompoundCommand(), null,
                RefactoringOperationType.UPDATE);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_retrieve_data_in_expression_dependencies() throws Exception {
        Expression expr = ExpressionFactory.eINSTANCE.createExpression();
        Data createData = ProcessFactory.eINSTANCE.createData();
        createData.setName("myData");
        Parameter parameter = ParameterFactory.eINSTANCE.createParameter();
        parameter.setName("myData");
        expr.getReferencedElements().add(parameter);
        expr.getReferencedElements().add(createData);
        EObject eObject = refactoringAction.getReferencedObjectInScriptsOperation(expr);
        assertThat(eObject).isNotNull().isEqualTo(createData);

    }
}
