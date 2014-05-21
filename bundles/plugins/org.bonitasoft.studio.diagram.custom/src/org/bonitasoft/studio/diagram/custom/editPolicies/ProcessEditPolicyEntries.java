/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
import java.util.List;

import org.bonitasoft.studio.diagram.custom.tools.ToolAndToolEntry;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessPaletteFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier : improve resource management
 */
public class ProcessEditPolicyEntries {

	private PaletteRoot paletteRoot;
	private EditDomain domain;

	public ProcessEditPolicyEntries(EditDomain domain) {
		ProcessPaletteFactory paletteFactory = new ProcessPaletteFactory();
		paletteRoot = new PaletteRoot();
		paletteFactory.fillPalette(paletteRoot);
		this.domain = domain;
	}

	public List<ToolAndToolEntry> getEntriesAndTools() {
		List<ToolAndToolEntry> res = new ArrayList<ToolAndToolEntry>();
		fillList(paletteRoot, res);
		return res;
	}

	private void fillList(PaletteContainer container, List<ToolAndToolEntry> list) {
		for (Object child : container.getChildren()) {
			if (child instanceof ToolEntry) {
				ToolEntry entry = (ToolEntry)child;
				/*create only one tool per entry*/
				Tool tool1 = entry.createTool();
				if(tool1 != null){
					tool1.setEditDomain(domain);
					if ( tool1 instanceof UnspecifiedTypeCreationTool) {
						UnspecifiedTypeCreationTool tool = (UnspecifiedTypeCreationTool)tool1;
						CreateUnspecifiedTypeRequest request = (CreateUnspecifiedTypeRequest) tool.createCreateRequest();
						EClass targetEClass = ((List<IElementType>)request.getElementTypes()).get(0).getEClass();
						if ((ProcessPackage.Literals.FLOW_ELEMENT.isSuperTypeOf(targetEClass))
								|| ProcessPackage.Literals.TEXT_ANNOTATION.isSuperTypeOf(targetEClass)) {
							list.add(new ToolAndToolEntry(tool1, entry));
						} else {
							tool1.deactivate();
						}
					}else if(tool1 instanceof UnspecifiedTypeConnectionTool){	
						list.add(new ToolAndToolEntry(tool1, entry));
					} else if(tool1 != null){
						tool1.deactivate();
					}
				}
			} else if (child instanceof PaletteContainer) {
				fillList((PaletteContainer)child, list);
			}
		}
	}
}
