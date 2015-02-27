/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.property.section;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

/**
 * @author Romain Bioteau
 *
 */
public class AbstractProcessFilter implements IFilter {

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    @Override
    public boolean select(Object object) {
        if (object instanceof IGraphicalEditPart) {
            IGraphicalEditPart editPart = (IGraphicalEditPart) object;
            Object model = editPart.resolveSemanticElement();
            if((model instanceof AbstractProcess || model instanceof Lane) && !(model instanceof MainProcess) ){
                return true ;
            }
        }
        return false;
    }

}
