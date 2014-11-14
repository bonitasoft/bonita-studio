/**
 * Copyright (C) 2009-2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.bonitasoft.studio.diagram.form.custom.clipboard;

import java.util.Hashtable;

import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.gmf.runtime.common.ui.services.action.global.AbstractGlobalActionHandlerProvider;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.gmf.runtime.common.ui.util.PartListenerAdapter;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class CustomGlobalActionHandlerProvider extends AbstractGlobalActionHandlerProvider{

	/**
	 * List for handlers.
	 */
    private final Hashtable<IWorkbenchPart, IGlobalActionHandler> handlerList = new Hashtable<IWorkbenchPart, IGlobalActionHandler>();

	/**
	 * Creates a new instance.
	 */
	public CustomGlobalActionHandlerProvider() {
		super();
	}

	/**
	 * Returns a global action handler that supports global image operations
	 * (cut, copy, and paste).
	 */
	@Override
    public IGlobalActionHandler getGlobalActionHandler(final IGlobalActionHandlerContext context) {
		/* Create the handler */
		final IWorkbenchPart activePart = context.getActivePart();
        if (!getHandlerList().containsKey(activePart)) {
            getHandlerList().put(activePart, retrieveCorrectClipboardSupportDependingOnActivePart(activePart));

			/*
			 * Register as a part listener so that the cache can be cleared when
			 * the part is disposed
			 */
			activePart.getSite().getPage().addPartListener(
                    new PartListenerAdapter() {

					private IWorkbenchPart localPart = activePart;

					/**
					 * @see org.eclipse.ui.IPartListener#partClosed(IWorkbenchPart)
					 */
					@Override
                    public void partClosed(final IWorkbenchPart part) {
						/* Remove the cache associated with the part */
						if (part != null && part == localPart
							&& getHandlerList().containsKey(part)) {
							getHandlerList().remove(part);
							localPart.getSite().getPage().removePartListener(
								this);
							localPart = null;
						}
					}
				});
		}

        return getHandlerList().get(activePart);
	}

    protected IGlobalActionHandler retrieveCorrectClipboardSupportDependingOnActivePart(final IWorkbenchPart activePart) {
        if (activePart instanceof FormDiagramEditor) {
            return new CustomClipboardSupportGlobalActionHandler();
        } else {
            return new org.bonitasoft.studio.diagram.custom.clipboard.CustomClipboardSupportGlobalActionHandler();
        }
    }

	/**
	 * Returns the handlerList.
	 *
	 * @return Hashtable
	 */
    private Hashtable<IWorkbenchPart, IGlobalActionHandler> getHandlerList() {
		return handlerList;
	}

}
