/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
/**
 * 
 */
package org.bonitasoft.studio.properties.sections.index;

import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

/**
 * @author aurelie
 *
 */
public class IndexFilter implements IFilter {

	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
	 */
	@Override
	public boolean select(Object toTest) {
		if ( toTest instanceof IGraphicalEditPart) {
			IGraphicalEditPart editPart = (IGraphicalEditPart)  toTest;
			Object model = editPart.resolveSemanticElement();	
			return (model instanceof Pool || model instanceof Lane);
		}
		return false;
	}

}
