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
package org.bonitasoft.studio.refactoring.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.DataBuilder.aData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.studio.model.parameter.builders.ParameterBuilder;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.builders.DataBuilder;
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

    private Data oldData;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final List<DataRefactorPair> pairsToRefactor = new ArrayList<DataRefactorPair>();
        oldData = aData().withName("myData").build();
        pairsToRefactor.add(new DataRefactorPair(oldData, oldData));
        refactoringAction = new DataScriptExpressionRefactoringAction(pairsToRefactor,
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
        final Data createData = aData().withName("myData").build();
        final Expression expr = anExpression().
                havingReferencedElements(createData,
                        ParameterBuilder.create().withName("myData").build()).build();
        final Map<EObject, EObject> eObject = refactoringAction.getReferencedObjectInScriptsOperation(expr);
        assertThat(eObject).isNotEmpty().containsEntry(createData, oldData);
    }

}
