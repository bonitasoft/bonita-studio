/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf;

import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.gmf.tools.HorizontalAlignToolEntry;
import org.bonitasoft.studio.common.gmf.tools.MultipleShapesHorizontalMoveTool;
import org.bonitasoft.studio.common.gmf.tools.MultipleShapesVerticalMoveTool;
import org.bonitasoft.studio.common.gmf.tools.VerticalAlignToolEntry;
import org.bonitasoft.studio.common.gmf.tools.ZoomInToolEntry;
import org.bonitasoft.studio.common.gmf.tools.ZoomOutToolEntry;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PanningSelectionToolEntry;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.common.core.service.ExecutionStrategy;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.palette.ContributeToPaletteOperation;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.PaletteService;
import org.eclipse.gmf.runtime.diagram.ui.services.palette.SelectionToolEx;
import org.eclipse.gmf.runtime.gef.ui.internal.palette.PaletteSeparator;
import org.eclipse.gmf.runtime.gef.ui.internal.palette.PaletteToolbar;
import org.eclipse.ui.IEditorPart;

public class CustomPaletteService extends PaletteService {

	private static PaletteService instanceBis = new CustomPaletteService();
	static {
		instanceBis.configureProviders(DiagramUIPlugin.getPluginId(), "paletteProviders"); //$NON-NLS-1$
	}

	protected CustomPaletteService() {
		super();
	}
	
	/**
	 * gets the singleton instance
	 * @return <code>PaletteService</code>
	 */
	public static PaletteService getInstance() {
		return instanceBis;
	}
	
	public void contributeToPalette(
			IEditorPart editor,
			Object content,
			PaletteRoot root, Map predefinedEntries) {

		    PaletteToolbar standardGroup = new PaletteToolbar(GROUP_STANDARD, DiagramUIMessages.StandardGroup_Label);
			standardGroup.setDescription(DiagramUIMessages.StandardGroup_Description);
			root.add(standardGroup);

			PaletteSeparator standardSeparator = new PaletteSeparator(SEPARATOR_STANDARD);
			standardGroup.add(standardSeparator);

			ToolEntry selectTool = new PanningSelectionToolEntry();
			selectTool.setId(TOOL_SELECTION);
			selectTool.setToolClass(SelectionToolEx.class);
			standardGroup.add(selectTool);
			root.setDefaultEntry(selectTool);
			
			ToolEntry hSpace = new ToolEntry(Messages.SpacingHorizontalTitle,
					Messages.SpacingHorizontalDesc,
					Pics.getImageDescriptor(PicsConstants.HorizontalSpacingTool16),
					Pics.getImageDescriptor(PicsConstants.HorizontalSpacingTool24),
					MultipleShapesHorizontalMoveTool.class) {
			};
			hSpace.setId("Messages.SpacingHorizontalTitle");

			ToolEntry vSpave = new ToolEntry(Messages.SpacingVerticalTitle,
					Messages.SpacingVerticalDesc,
					Pics.getImageDescriptor(PicsConstants.VerticalSpacingTool16),
					Pics.getImageDescriptor(PicsConstants.VerticalSpacingTool24),
					MultipleShapesVerticalMoveTool.class) {
			};
			vSpave.setId(Messages.SpacingVerticalTitle);

			final MarqueeToolEntry marqueeToolEntry = new MarqueeToolEntry();
			marqueeToolEntry.setId("Bonita.MarqueeToolEntry");
			standardGroup.add(marqueeToolEntry);
			final ZoomInToolEntry zoomInToolEntry = new ZoomInToolEntry();
			zoomInToolEntry.setId("Bonita.ZoomInToolEntry");
			standardGroup.add(zoomInToolEntry);
			final ZoomOutToolEntry zoomOutToolEntry = new ZoomOutToolEntry();
			zoomOutToolEntry.setId("Bonita.ZoomOutToolEntry");
			standardGroup.add(zoomOutToolEntry);
			standardGroup.add(hSpace);
			standardGroup.add(vSpave);
			final HorizontalAlignToolEntry horizontalAlignToolEntry = new HorizontalAlignToolEntry();
			horizontalAlignToolEntry.setId("Bonita.HorizontalAlignToolEntry");
			standardGroup.add(horizontalAlignToolEntry);
			final VerticalAlignToolEntry verticalAlignToolEntry = new VerticalAlignToolEntry();
			verticalAlignToolEntry.setId("Bonita.VerticalAlignToolEntry");
			standardGroup.add(verticalAlignToolEntry);

			execute(new ContributeToPaletteOperation(editor, content, root, predefinedEntries));       
		}
	
    /**
	 * Executes the palette operation using 
	 * the REVERSE execution strategy.
	 * 
	 * @param operation
	 * @return List of results
	 */
	private List<?> execute(IOperation operation) {
		return execute(ExecutionStrategy.REVERSE, operation);
	}

}
