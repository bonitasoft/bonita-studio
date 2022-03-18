/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.contract.ui.property;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.LaneBuilder.aLane;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.model.process.ContractContainer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Test;

public class ContractContainerAdaptableSelectionProviderTest {

    @Test
    public void adapt_selection_to_contract_container_for_pool_edit_part_selection() throws Exception {
        final ContractContainerAdaptableSelectionProvider selectionProvider = new ContractContainerAdaptableSelectionProvider();

        selectionProvider.setSelection(new StructuredSelection(editPartWithSemanticElement(aPool().build())));

        assertThat(((IStructuredSelection) selectionProvider.getSelection()).getFirstElement()).isInstanceOf(ContractContainer.class);
    }

    @Test
    public void adapt_selection_to_contract_container_for_lane_edit_part_selection() throws Exception {
        final ContractContainerAdaptableSelectionProvider selectionProvider = new ContractContainerAdaptableSelectionProvider();

        selectionProvider.setSelection(new StructuredSelection(editPartWithSemanticElement(aPool().havingElements(aLane()).build().getElements().get(0))));

        assertThat(((IStructuredSelection) selectionProvider.getSelection()).getFirstElement()).isInstanceOf(ContractContainer.class);
    }

    private GraphicalEditPart editPartWithSemanticElement(final EObject semanticElement) {
        final GraphicalEditPart iGraphicalEditPart = mock(GraphicalEditPart.class);
        doReturn(semanticElement).when(iGraphicalEditPart).getAdapter(EObject.class);
        return iGraphicalEditPart;
    }
}
