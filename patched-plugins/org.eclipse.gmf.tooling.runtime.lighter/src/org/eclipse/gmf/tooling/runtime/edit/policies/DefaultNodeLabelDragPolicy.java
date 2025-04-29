/*
 * Copyright (c) 2006, 2009 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 *    Alexander Shatalin (Borland) - initial API and implementation
 *    Michael Golubev (Montages) - [368169] extract not-generated shared code to GMF-T runtime
 */
package org.eclipse.gmf.tooling.runtime.edit.policies;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;

/**
 * @since 3.1
 */
public class DefaultNodeLabelDragPolicy extends NonResizableEditPolicy {

	@SuppressWarnings("rawtypes")
	protected List createSelectionHandles() {
		MoveHandle h = new MoveHandle((GraphicalEditPart) getHost());
		h.setBorder(null);
		return Collections.singletonList(h);
	}

	public Command getCommand(Request request) {
		return null;
	}

	public boolean understandsRequest(Request request) {
		return false;
	}

}
