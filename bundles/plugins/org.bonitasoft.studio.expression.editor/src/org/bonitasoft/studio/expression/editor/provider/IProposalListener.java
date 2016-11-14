/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.expression.editor.provider;

import org.bonitasoft.studio.common.IBonitaVariableContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ISelection;

/**
 * @author Maxence Raoux
 *
 */
public interface IProposalListener extends IBonitaVariableContext {

    public String handleEvent(EObject context, String fixedReturnType);

    public void setEStructuralFeature(EStructuralFeature feature);

    public boolean isRelevant(EObject context, ISelection selection);
}

