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
package org.bonitasoft.studio.contract.ui.property.constraint.labelProvider;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.contract.ui.property.constraint.labelProvider.ConstraintExpressionCellLabelProvider;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.StyledString;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ConstraintExpressionCellLabelProviderTest {

    private ConstraintExpressionCellLabelProvider labelProvider;

    @Before
    public void setUp() throws Exception {
        labelProvider = new ConstraintExpressionCellLabelProvider(new AdapterFactoryContentProvider(new ProcessItemProviderAdapterFactory()), new WritableSet());
    }

    @Test
    public void should_getStyledText_strip_expression_charriage() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setExpression("toto == true && \n titi == false \r");
        final StyledString styledText = labelProvider.getStyledText(constraint);
        assertThat(styledText.toString()).doesNotContain("\n").doesNotContain("\r");
    }

    @Test
    public void should_getStyledText_supportNull() throws Exception {
        final ContractConstraint constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setExpression(null);
        final StyledString styledText = labelProvider.getStyledText(constraint);
        assertThat(styledText.toString()).doesNotContain("\n").doesNotContain("\r");
    }

}
