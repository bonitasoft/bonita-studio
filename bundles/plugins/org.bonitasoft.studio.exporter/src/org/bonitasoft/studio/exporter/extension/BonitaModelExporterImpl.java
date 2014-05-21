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
package org.bonitasoft.studio.exporter.extension;

import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaModelExporterImpl implements IBonitaModelExporter {

	private MainProcessEditPart mainEditPart;
	private MainProcess mainProcess;

	public BonitaModelExporterImpl(MainProcessEditPart mainPart){
		this.mainEditPart = mainPart ;
		this.mainProcess = (MainProcess) mainPart.resolveSemanticElement() ;
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getDiagram()
	 */
	public MainProcess getDiagram() {
		return mainProcess;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getPools()
	 */
	public List<Pool> getPools() {
		return ModelHelper.getAllItemsOfType(mainProcess, ProcessPackage.eINSTANCE.getPool());
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getFlowElements(org.bonitasoft.studio.model.process.Container)
	 */
	public List<FlowElement> getFlowElements(Container container) {
		return ModelHelper.getAllItemsOfType(container, ProcessPackage.eINSTANCE.getFlowElement());
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getLanes(org.bonitasoft.studio.model.process.Pool)
	 */
	public List<Lane> getLanes(Pool pool) {
		return ModelHelper.getAllItemsOfType(pool, ProcessPackage.eINSTANCE.getLane());
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.exporter.extension.IBonitaModelExporter#getElementBounds(org.bonitasoft.studio.model.process.Element)
	 */
	public Rectangle getElementBounds(Element element) {
		Rectangle result = new Rectangle();
		IGraphicalEditPart ep = GMFTools.findEditPart(mainEditPart, element) ;
		if(ep != null){
			result = ep.getFigure().getBounds().getCopy() ;
			FiguresHelper.translateToAbsolute(ep.getFigure(),result) ;
		}
		return result;
	}

	public View getElementNotationView(Element element) {
		IGraphicalEditPart ep = GMFTools.findEditPart(mainEditPart, element) ;
		if(ep != null){
			return ep.getNotationView();
		}
		return null;
	}

	public MainProcessEditPart getMainProcessEditPart() {
		return mainEditPart;
	}

}
