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
package org.bonitasoft.studio.designer.ui.property.section;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.ActivityBuilder.anActivity;
import static org.bonitasoft.studio.model.process.builders.LaneBuilder.aLane;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.designer.ui.property.section.PageFlowAdaptableSelectionProvider;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class PageFlowAdaptableSelectionProviderTest {

    private PageFlowAdaptableSelectionProvider pageFlowAdaptableSelectionProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        pageFlowAdaptableSelectionProvider = new PageFlowAdaptableSelectionProvider();
    }

    @Test
    public void should_return_null_for_unstructured_selection() throws Exception {
        pageFlowAdaptableSelectionProvider.setSelection(mock(ISelection.class));

        assertThat(pageFlowAdaptableSelectionProvider.getSelection()).isNull();
    }

    @Test
    public void should_return_null_for_unsupported_adapter_class() throws Exception {
        assertThat(pageFlowAdaptableSelectionProvider.getAdapter(Object.class)).isNull();
    }

    @Test
    public void should_adapt_selection_to_a_PageFlow_if_selection_is_a_pageflow_adaptable() throws Exception {
        pageFlowAdaptableSelectionProvider.setSelection(new StructuredSelection(new EObjectAdapter(aPool().build())));

        final IStructuredSelection adapter = (IStructuredSelection) pageFlowAdaptableSelectionProvider.getSelection();

        assertThat(adapter.getFirstElement()).isInstanceOf(PageFlow.class);
    }

    @Test
    public void should_adapt_selection_to_a_PageFlow_if_selection_is_a_lane_adaptable() throws Exception {
        pageFlowAdaptableSelectionProvider.setSelection(new StructuredSelection(
                new EObjectAdapter(aPool().havingElements(aLane()).build().getElements().get(0))));

        final IStructuredSelection adapter = (IStructuredSelection) pageFlowAdaptableSelectionProvider.getSelection();

        assertThat(adapter.getFirstElement()).isInstanceOf(PageFlow.class);
    }

    @Test
    public void should_return_an_empty_selection_if_selection_is_not_a_lane_or_a_pageflow_adaptable() throws Exception {
        pageFlowAdaptableSelectionProvider.setSelection(new StructuredSelection(
                new EObjectAdapter(aPool().havingElements(anActivity()).build().getElements().get(0))));

        final IStructuredSelection adapter = (IStructuredSelection) pageFlowAdaptableSelectionProvider.getSelection();

        assertThat(adapter.isEmpty()).isTrue();
    }

    @Test
    public void should_return_an_empty_selection_if_selection_is_a_MainProcess_adaptable() throws Exception {
        pageFlowAdaptableSelectionProvider.setSelection(new StructuredSelection(
                new EObjectAdapter(ProcessFactory.eINSTANCE.createMainProcess())));

        final IStructuredSelection adapter = (IStructuredSelection) pageFlowAdaptableSelectionProvider.getSelection();

        assertThat(adapter.isEmpty()).isTrue();
    }
}
