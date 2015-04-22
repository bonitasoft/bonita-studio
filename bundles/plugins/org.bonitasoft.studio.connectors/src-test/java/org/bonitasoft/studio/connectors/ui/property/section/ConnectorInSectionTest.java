package org.bonitasoft.studio.connectors.ui.property.section;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorWizard;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
@RunWith(MockitoJUnitRunner.class)
public class ConnectorInSectionTest {

	
	
	
	@Spy
	ConnectorInSection section;
	
	@Mock
	ConnectorWizard connectorWizard;
	
	
	@Mock
	TableViewer viewer;
	
	
	@Before
	public void setup(){
		 doReturn(connectorWizard).when(section).createConnectorWizard();
		 doReturn(viewer).when(section).getTree();
		 
		 
	}
	
	
	@Test
	public void should_setConnectorEvent_when_creating_aConnetorWizard(){
		section.createAddConnectorWizard();
		verify(section).setConnectorEvent(connectorWizard,ConnectorEvent.ON_ENTER.toString());
		
	}
	
	@Test
	public void should_set_tree_filter(){
		section.setTreeFilter();
		verify(section).getTree();
	}
	

}
