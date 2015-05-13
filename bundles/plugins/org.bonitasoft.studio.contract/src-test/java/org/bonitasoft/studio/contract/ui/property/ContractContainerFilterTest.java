/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.ui.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.LaneBuilder.aLane;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class ContractContainerFilterTest {

    @Test
    public void accept_TaskEditPart() throws Exception {
        final ContractContainerFilter filter = new ContractContainerFilter();

        assertThat(filter.select(editPartWithSemanticElement(aTask().build()))).isTrue();
    }

    @Test
    public void accept_PoolEditPart() throws Exception {
        final ContractContainerFilter filter = new ContractContainerFilter();

        assertThat(filter.select(editPartWithSemanticElement(aPool().build()))).isTrue();
    }

    @Test
    public void accept_LaneEditPart() throws Exception {
        final ContractContainerFilter filter = new ContractContainerFilter();

        assertThat(filter.select(editPartWithSemanticElement(aLane().build()))).isTrue();
    }

    @Test
    public void do_not_accept_other_type_than_editpart() throws Exception {
        final ContractContainerFilter filter = new ContractContainerFilter();

        assertThat(filter.select(aTask().build())).isFalse();
    }

    private IGraphicalEditPart editPartWithSemanticElement(final EObject semanticElement) {
        final IGraphicalEditPart iGraphicalEditPart = mock(IGraphicalEditPart.class);
        doReturn(semanticElement).when(iGraphicalEditPart).resolveSemanticElement();
        return iGraphicalEditPart;
    }

}
