/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools;

import java.util.Collections;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.image.ImageFileFormat;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Aurelien Pupier
 *
 */
public class CopyToImageUtilEx extends CopyToImageUtil {
	
	/**
	 * This version avoid problem with model without xmi id.
	 * 
     * Creates an image of the diagram in the specified image file format. The diagram image is scaled to fit in
     * the maxWidth, maxHeight window. The image is returned as a byte array
     * 
     * @param diagram diagram model
     * @param maxWidth the max width of the image
     * @param maxHeight the max height of the image
     * @param format image format
     * @param monitor progress monitor
     * @param preferencesHint preference hint for the diagram
     * @param useMargins true if a 10 pixel margin is required around the diagram
     * @return the image as array of bytes
     * @throws CoreException
     */
    public byte [] copyToImageByteArray(Diagram diagram,AbstractProcess processToDraw, int maxWidth, int maxHeight, ImageFileFormat format, IProgressMonitor monitor, PreferencesHint preferencesHint, boolean useMargins) throws CoreException {
    	//avoid strange NPE when creating image
//    	String idStr = ViewUtil.getIdStr(diagram);
//    	if(!idStr.equals(StringStatics.BLANK)){//ie there is no problem with xmi id
//    		DiagramEditor openedDiagramEditor = null;
//    		try{
//    			DiagramEditorUtil.findOpenedDiagramEditorForID(idStr);
//    		}catch (Throw e) {
//				// TODO: handle exception
//			}
//    		if (openedDiagramEditor != null) {
//    			return copyToImageByteArray(openedDiagramEditor.getDiagramEditPart(), null, maxWidth, maxHeight, format, monitor, useMargins);
//    		}
//    	}
    	
    	Shell shell = new Shell();
    	DiagramEditPart diagramEditPart = null ;
    	try {
    		 diagramEditPart = createDiagramEditPart(diagram,
    		shell, preferencesHint);
    		Assert.isNotNull(diagramEditPart);
    		IGraphicalEditPart ep = null ;
    		for(Object child : diagramEditPart.getChildren()){
    			if(child instanceof IGraphicalEditPart){
    				if(((Element)((IGraphicalEditPart) child).resolveSemanticElement()).getName().equals(processToDraw.getName())){
    					ep = (IGraphicalEditPart) child ; 
    				}
    			}
    		}

    		return copyToImageByteArray(diagramEditPart, Collections.singletonList(ep), maxWidth, maxHeight, format, monitor, useMargins);
    	} finally {
    		if(diagramEditPart != null){
    			diagramEditPart.getEditingDomain().dispose();
    		}
    		if(shell != null){
    			shell.dispose();
    		}
    	}

    }
	
    public byte [] copyToImageByteArray(Diagram diagram,Element elementToDraw, int maxWidth, int maxHeight, ImageFileFormat format, IProgressMonitor monitor, PreferencesHint preferencesHint, boolean useMargins) throws Exception {

    	Shell shell = new Shell();
    	DiagramEditPart diagramEditPart = null ;
    	EditPart ep = null ;
    	try {
    		diagramEditPart = createDiagramEditPart(diagram,
    		shell, preferencesHint);
    		Assert.isNotNull(diagramEditPart);
    		ep = GMFTools.findEditPart(diagramEditPart,elementToDraw) ;
    
    		
    		if(ep == null)
    			throw new Exception("Element to draw not found") ;

    		return copyToImageByteArray(diagramEditPart, Collections.singletonList(ep), maxWidth, maxHeight, format, monitor, useMargins);
    	}catch(Exception e){
    		e.printStackTrace();
    		throw e ;
    	} finally {
    		shell.dispose();
    	}

    }

}
