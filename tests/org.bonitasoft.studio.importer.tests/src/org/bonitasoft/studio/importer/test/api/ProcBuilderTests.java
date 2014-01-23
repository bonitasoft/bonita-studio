/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.importer.test.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.ProjectUtil;
import org.bonitasoft.studio.engine.export.BarExporter;
import org.bonitasoft.studio.importer.builder.IProcBuilder;
import org.bonitasoft.studio.importer.builder.IProcBuilder.EventType;
import org.bonitasoft.studio.importer.builder.ProcBuilder;
import org.bonitasoft.studio.importer.builder.ProcBuilderException;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartEvent;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;

/**
 * @author Romain Bioteau
 *
 */
public class ProcBuilderTests extends TestCase {

	private IProcBuilder procBuilder ;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.procBuilder = new ProcBuilder() ;
	}
	
	public void testAddGenericAttribute() throws ProcBuilderException, IOException {
		
		final String diagamFileName = "testDiagram.proc";
		final String diagramName = "testDiagram";
		final String poolName = "Pooli";
		String description =  "Simple description" ;
		List<String> categories = new ArrayList<String>(2) ;
		categories.add("R&D") ;
		categories.add("Service") ;
		File diagramFile = new File(ProjectUtil.getBonitaStudioWorkFolder(),diagamFileName) ;
		URI targetURI = URI.createFileURI(diagramFile.getAbsolutePath()) ;
				
		initProcBuilderWithDefaultContent(diagramName, poolName, description, categories, diagramFile);
		
		procBuilder.done() ;
		
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		final Resource diagramResource = editingDomain.getResourceSet().createResource(targetURI);
		diagramResource.load(new HashMap<String, String>()) ;
		assertTrue("Import as failed", diagramResource.getContents().size() > 0) ;
		MainProcess proc = (MainProcess) diagramResource.getContents().get(0) ;
		assertEquals(diagramName, proc.getName()) ;
		Pool p = (Pool) proc.getElements().get(0) ; 
		assertEquals(poolName, p.getName()) ;
		assertEquals("Missing pool categories",categories.size(), p.getCategories().size()) ;
		StartEvent event = (StartEvent) p.getElements().get(0) ; 
		assertEquals("Start", event.getName()) ;
		assertEquals(description, event.getDocumentation()) ;
		
		diagramResource.delete(new HashMap<String, String>()) ;
	}

	private void initProcBuilderWithDefaultContent(final String diagramName,
			final String poolName, String description, List<String> categories,
			File diagramFile) throws ProcBuilderException {
		procBuilder.createDiagram(diagramName, diagramName, "1.0",diagramFile) ;
		
		procBuilder.addPool(poolName, poolName, "1.0", new Point(0,0), new Dimension(1000, 200)) ;
		procBuilder.setAttributeOnCurrentContainer(ProcessPackage.eINSTANCE.getAbstractProcess_Categories(),categories)  ;
		procBuilder.addEvent("Start", "Start", new Point(20,20), new Dimension(50,50), EventType.START) ;
		
		procBuilder.setAttributeOnCurrentStep(ProcessPackage.eINSTANCE.getElement_Documentation(),description) ;
	}
	
	public void testStartErrorEvent() throws Exception {
		final String diagamFileName = "testDiagramWithStartErrorEvent.proc";
		final String diagramName = "testDiagramWithStartErrorEvent";
		final String poolName = "PoolWithStartErrorEvent";
		final String description =  "Simple description" ;
		List<String> categories = new ArrayList<String>(2) ;
		categories.add("R&D") ;
		categories.add("Service") ;
		File diagramFile = new File(ProjectUtil.getBonitaStudioWorkFolder(),diagamFileName) ;
		URI targetURI = URI.createFileURI(diagramFile.getAbsolutePath()) ;
		initProcBuilderWithDefaultContent(diagramName, poolName, description, categories, diagramFile);
		
		procBuilder.addEventSubprocess("eventSubProc", "eventSubProc", new Point(100,100), new Dimension(200, 200), false);
		procBuilder.addEvent("startError", "startError", new Point(100,100),  new Dimension(50,50), EventType.START_ERROR);
		
		procBuilder.done();
		
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		final Resource diagramResource = editingDomain.getResourceSet().createResource(targetURI);
		diagramResource.load(new HashMap<String, String>()) ;
		assertTrue("Import as failed", diagramResource.getContents().size() > 0) ;
		MainProcess proc = (MainProcess) diagramResource.getContents().get(0) ;
		Pool p = (Pool) proc.getElements().get(0) ;
		BarExporter.getInstance().createBusinessArchive(p, (Configuration)null, Collections.<EObject>emptySet());
		
	}
	
	
}
