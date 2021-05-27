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

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
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
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.DiagramGenerator;
import org.eclipse.gmf.runtime.diagram.ui.render.util.CopyToImageUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.graphics.Rectangle;
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
	public byte [] copyToImageByteArray(final Diagram diagram,final AbstractProcess processToDraw, final ImageFileFormat format, final IProgressMonitor monitor, final PreferencesHint preferencesHint, final boolean useMargins) throws CoreException {
		final Shell shell = new Shell();
		DiagramEditPart diagramEditPart = null ;
		try {
			diagramEditPart = createDiagramEditPart(diagram,
					shell, preferencesHint);
			Assert.isNotNull(diagramEditPart);
			IGraphicalEditPart ep = null ;
			for(final Object child : diagramEditPart.getChildren()){
				if(child instanceof IGraphicalEditPart){
					if(((Element)((IGraphicalEditPart) child).resolveSemanticElement()).getName().equals(processToDraw.getName())){
						ep = (IGraphicalEditPart) child ;
					}
				}
			}
            if (ep != null) {
                final DiagramGenerator gen = getDiagramGenerator(diagramEditPart, format);
                final Rectangle calculateImageRectangle = gen.calculateImageRectangle(Collections.singletonList(ep));
                return copyToOutputStream(gen, Collections.singletonList(ep), calculateImageRectangle, format, monitor);
            }
            return null;
		} finally {
			if(diagramEditPart != null){
				diagramEditPart.getEditingDomain().dispose();
			}
			if(shell != null){
				shell.dispose();
			}
		}

	}

	protected byte[] copyToOutputStream(final DiagramGenerator gen, final List editParts,
			final org.eclipse.swt.graphics.Rectangle imageRect,
			final ImageFileFormat format, final IProgressMonitor monitor)
					throws CoreException {
		if (format.equals(ImageFileFormat.JPEG)
				|| format.equals(ImageFileFormat.PNG)) {

			String exportFormat = org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.image.ImageExporter.JPEG_FILE;
			if (format.equals(ImageFileFormat.PNG)) {
                exportFormat = org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.image.ImageExporter.PNG_FILE;
            }

			final java.awt.Image image = gen.createAWTImageForParts(editParts,
					imageRect);
			monitor.worked(1);
			ByteArrayOutputStream stream = null;
			try{

				if (image instanceof BufferedImage) {
					stream = new ByteArrayOutputStream();
					org.eclipse.gmf.runtime.draw2d.ui.render.awt.internal.image.ImageExporter.exportToOutputStream(stream,(BufferedImage) image,exportFormat, monitor, format.getQuality());
					return stream.toByteArray();
				}
			}finally{
				if(stream != null){
					try {
						stream.close();
					} catch (final IOException e) {
						BonitaStudioLog.error(e);
					}
				}
			}
		}
		return null;
	}

	public byte [] copyToImageByteArray(final Diagram diagram,final Element elementToDraw, final int maxWidth, final int maxHeight, final ImageFileFormat format, final IProgressMonitor monitor, final PreferencesHint preferencesHint, final boolean useMargins) throws Exception {
		final Shell shell = new Shell();
		DiagramEditPart diagramEditPart = null ;
		EditPart ep = null ;
		try {
			diagramEditPart = createDiagramEditPart(diagram,
					shell, preferencesHint);
			Assert.isNotNull(diagramEditPart);
			ep = GMFTools.findEditPart(diagramEditPart,elementToDraw) ;


			if(ep == null) {
                throw new Exception("Element to draw not found") ;
            }

			return copyToImageByteArray(diagramEditPart, Collections.singletonList(ep), maxWidth, maxHeight, format, monitor, useMargins);
		}catch(final Exception e){
			e.printStackTrace();
			throw e ;
		} finally {
			shell.dispose();
		}
	}


}
