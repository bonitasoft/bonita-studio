/*
 * Copyright (c) 2006, 2009 Borland Software Corporation
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 * Dmitry Stadnik (Borland) - initial API and implementation
 * Alexander Shatalin (Borland) - initial API and implementation
 * Michael Golubev (Montages) - [368169] extract not-generated shared code to GMF-T runtime
 */
package org.eclipse.gmf.tooling.runtime.edit.policies;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.NonResizableLabelEditPolicy;

/**
 * @since 3.1
 */
public class DefaultLinkLabelDragPolicy extends NonResizableLabelEditPolicy {

    @SuppressWarnings("rawtypes")
    protected List createSelectionHandles() {
        MoveHandle mh = new MoveHandle((GraphicalEditPart) getHost());
        mh.setBorder(null);
        return Collections.singletonList(mh);
    }

}
