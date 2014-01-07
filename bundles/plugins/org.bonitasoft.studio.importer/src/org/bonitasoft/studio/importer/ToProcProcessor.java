/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author Baptiste Mesta
 *
 */
public abstract class ToProcProcessor {


	protected String resourceName;
	
	public abstract File createDiagram(URL sourceFileURL, IProgressMonitor progressMonitor) throws Exception;

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public abstract List<File> getResources() ;
	
//	/**
//	 * 
//	 *  try to place elements of the graph correctly 
//	 * @param modelProcess2
//	 * @param diagram
//	 */
//	public void arrangeGraph(MainProcess modelProcess2, Diagram diagram) {
//
//		for(Pool pool:ModelHelper.getChildrenProcess(modelProcess2)){
//
//			IGraphicalEditPart poolPart = (IGraphicalEditPart) diagramPart.findEditPart(diagramPart, pool);
//			//resize the pool
//			org.eclipse.draw2d.geometry.Dimension poolSize = poolPart.getFigure().getBounds().getSize();
//			int nbElements = ModelHelper.getFlowElements(pool, true).size();
//			if(100*(1+ nbElements/ 7) >= poolSize.height  ){
//				//resizePool
//				ChangeBoundsRequest req = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE);
//				int height = 100*(1+ nbElements/ 7);
//				
//				req.setSizeDelta(new org.eclipse.draw2d.geometry.Dimension(0, height-poolSize.height));
//				
//				poolPart.performRequest(req);
//			}
//			
//		}
//	}

	/**
	 * @return
	 */
	public abstract String getExtension();

	public List<Object> getErrors(){
		return new ArrayList<Object>();
	}

}