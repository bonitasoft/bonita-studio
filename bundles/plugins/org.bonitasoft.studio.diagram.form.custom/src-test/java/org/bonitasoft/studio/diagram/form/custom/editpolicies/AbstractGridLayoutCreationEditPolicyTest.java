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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.GroupEditPart;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Florine Boudin
 *
 */
public class AbstractGridLayoutCreationEditPolicyTest {
	
	private AbstractGridLayoutCreationEditPolicy gridLayoutCreationEditPolicy;
	private IGraphicalEditPart editPart;
	private AbstractGridLayer gridLayer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		gridLayoutCreationEditPolicy = mock(AbstractGridLayoutCreationEditPolicy.class);
		when(gridLayoutCreationEditPolicy.getCreateCommand(any(CreateViewRequest.class))).thenCallRealMethod();
		when(gridLayoutCreationEditPolicy.getLayoutOrigin()).thenReturn(new Point(0,0));
		
		gridLayer=mock(GridLayer.class);
		when(gridLayer.getGridLayout()).thenReturn(new GridLayoutManager(gridLayer));
		
		when(gridLayoutCreationEditPolicy.getGridLayer(any(IGraphicalEditPart.class))).thenReturn(gridLayer);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_getCreateCommand_return_a_CreateCommand_for_FormEditPart() throws Exception {
		editPart=mock(FormEditPart.class);
		when(editPart.getEditingDomain()).thenReturn(new WorkspaceEditingDomainFactory().createEditingDomain());
		when(gridLayoutCreationEditPolicy.getHost()).thenReturn(editPart);
		CreateViewRequest request = new CreateViewRequest(NotationFactory.eINSTANCE.createNode(), (PreferencesHint)null);
		request.setLocation(new Point(0, 0));
		Command resultCommand = gridLayoutCreationEditPolicy.getCreateCommand(request);
		assertThat(resultCommand).isNotNull();
	}
	
	

	@Test
	public void should_getCreateCommand_return_a_CreateCommand_for_GroupEditPart() throws Exception {
		editPart=mock(GroupEditPart.class);
		when(editPart.getEditingDomain()).thenReturn(new WorkspaceEditingDomainFactory().createEditingDomain());
		when(gridLayoutCreationEditPolicy.getHost()).thenReturn(editPart);
		CreateViewRequest request = new CreateViewRequest(NotationFactory.eINSTANCE.createNode(), (PreferencesHint)null);
		request.setLocation(new Point(0, 0));
		Command resultCommand = gridLayoutCreationEditPolicy.getCreateCommand(request);
		assertThat(resultCommand).isNotNull();
	}
}
