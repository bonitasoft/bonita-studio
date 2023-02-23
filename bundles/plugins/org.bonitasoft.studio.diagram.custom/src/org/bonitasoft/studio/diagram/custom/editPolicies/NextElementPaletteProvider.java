/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.tools.ToolAndToolEntry;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier : improve resource management
 */
public class NextElementPaletteProvider {
	public static List<ToolAndToolEntry> getAllowedNextTypes(final GraphicalEditPart host) {
		final EObject element = host.resolveSemanticElement();

		// Same as palette, but removing StartEvent
		final ArrayList<ToolAndToolEntry> res = new ArrayList<ToolAndToolEntry>();
		// Map<Tool,ToolEntry> res1 = new HashMap<Tool, ToolEntry>();
		final List<ToolAndToolEntry> temp = new ProcessEditPolicyEntries((EditDomain)host.getDiagramEditDomain()).getEntriesAndTools();
		if (element instanceof EndEvent || element instanceof EndMessageEvent || element instanceof EndTerminatedEvent || element instanceof EndErrorEvent || element instanceof ThrowLinkEvent || element instanceof EndSignalEvent) {
			for (final ToolAndToolEntry tate : temp) {
				if (tate.getTool() instanceof UnspecifiedTypeCreationTool) {
					if (tate.getToolEntry().getLabel().contains("Text Annotation")) { //$NON-NLS-1$
						res.add(tate);
					}
				}
				if (tate.getTool() instanceof UnspecifiedTypeConnectionTool && element instanceof EndMessageEvent) {
					if (!tate.getToolEntry().getLabel().contains("Text Annotation ") && !tate.getToolEntry().getLabel().contains("Message")) { //$NON-NLS-1$ //$NON-NLS-2$
						res.add(tate);
					}
				}
			}
		} else {
			for (final ToolAndToolEntry tate : temp) {
				// Tool tool1 = entry.createTool();
				if (tate.getTool() instanceof UnspecifiedTypeCreationTool) {
					if(!(element instanceof TextAnnotation)){
						final UnspecifiedTypeCreationTool tool = (UnspecifiedTypeCreationTool) tate.getTool();
						final CreateUnspecifiedTypeRequest createRequest = (CreateUnspecifiedTypeRequest) tool.createCreateRequest();
						final EClass eclass = ((IElementType) createRequest.getElementTypes().get(0)).getEClass();
						if (ProcessPackage.Literals.SERVICE_TASK.equals(eclass)
								|| ProcessPackage.Literals.EVENT.equals(eclass)
								|| ProcessPackage.Literals.AND_GATEWAY.equals(eclass)
								|| !(element instanceof BoundaryEvent) && ProcessPackage.Literals.TEXT_ANNOTATION.equals(eclass)) {
							res.add(tate);
						} else {
							if(!(element instanceof BoundaryEvent)){
								tate.getTool().deactivate();
							}
						}
					}
				} else if (tate.getTool() instanceof UnspecifiedTypeConnectionTool) {
                    if (!tate.getToolEntry().getLabel().contains("Text Annotation ")
                            && !tate.getToolEntry().getLabel().contains("Message")) { //$NON-NLS-1$ //$NON-NLS-2$
						res.add(tate);
					}
				}
			}
		}
		Collections.sort(res, new Comparator<ToolAndToolEntry>(){

			@Override
			public int compare(final ToolAndToolEntry arg0, final ToolAndToolEntry arg1) {
				return getToolOrder(arg0).compareTo(getToolOrder(arg1));
			}

		});
		return res;
	}

	protected static Integer getToolOrder(final ToolAndToolEntry entry) {
		final String id = entry.getToolEntry().getId();
		if(id.equals("createGate1CreationTool")){
			return 1;
		}
		if(id.equals("createTransition1CreationTool")){
			return 2;
		}
		if(id.equals("createServiceTask1CreationTool")){
			return 0;
		}
		if(id.equals("createEvent9CreationTool")){
			return 3;
		}
		return Integer.MAX_VALUE;
	}

}
