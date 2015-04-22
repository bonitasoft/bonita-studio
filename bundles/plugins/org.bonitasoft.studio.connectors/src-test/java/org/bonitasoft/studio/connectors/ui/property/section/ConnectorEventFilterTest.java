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
package org.bonitasoft.studio.connectors.ui.property.section;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.jface.viewers.Viewer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConnectorEventFilterTest {
	
	
	 @Mock
	 private Viewer viewer;
	 ConnectorEventFilter onFinishFilter;
	 
	 @Before
	 public void setup() {
		 onFinishFilter = new ConnectorEventFilter(ConnectorEvent.ON_FINISH.toString());
	 }
	
	 
	@Test
	public void should_return_true_when_connectorEvent_isOnFinish(){
		Connector connector = ProcessFactory.eINSTANCE.createConnector();
		connector.setEvent(ConnectorEvent.ON_FINISH.toString());
		assertTrue(onFinishFilter.select(viewer, null, connector));
	}
	
	
	@Test
	public void select_should_return_false_when_connectorEvent_isOnEnter(){
		Connector connector = ProcessFactory.eINSTANCE.createConnector();
		connector.setEvent(ConnectorEvent.ON_ENTER.toString());
		assertFalse(onFinishFilter.select(viewer, null, connector));
	}
	
	@Test
	public void select_shoud_return_false_when_notAConnector(){
		Task task = ProcessFactory.eINSTANCE.createTask();
		assertFalse(onFinishFilter.select(viewer, null, task));
	}
	
	
	@Test
	public void should_create_connectorEventFilter_when_ConnectorEvent_isOnEnter(){
		ConnectorEventFilter onEnterFilter = new ConnectorEventFilter(ConnectorEvent.ON_ENTER.toString());
		assertNotNull(onEnterFilter);
	}
	
	@Test (expected=AssertionFailedException.class)
	public void should_failed_when_event_isNot_ON_ENTER_Or_ON_FINISH(){
		ConnectorEventFilter failFilter = new ConnectorEventFilter("a");
	}
	
	

}
