/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools;

import java.net.MalformedURLException;
import java.net.URL;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIPlugin;
import org.eclipse.gmf.runtime.diagram.ui.internal.DiagramUIStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.providers.DefaultPaletteProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;

/**
 * @author Romain Bioteau
 *
 */

public class ZoomInToolEntry extends ToolEntry {

	public ZoomInToolEntry() {
		super(Messages.zoomInLabel, "", findIconImageDescriptor("icons/zoomplus.gif"),findIconImageDescriptor("icons/zoomplus_24x24.gif"));
	}

	@Override
	public Tool createTool() {
		return new CustomZoomTool(true);
	}

	private static ImageDescriptor findIconImageDescriptor(String iconPath) {
		String pluginId = "org.eclipse.gmf.runtime.diagram.ui.providers";
		Bundle bundle = Platform.getBundle(pluginId);
		try
		{
			if (iconPath != null) {
				URL fullPathString = FileLocator.find(bundle, new Path(iconPath), null);
				fullPathString = fullPathString != null ? fullPathString : new URL(iconPath);
				if (fullPathString != null) {
					return ImageDescriptor.createFromURL(fullPathString);
				}
			}
		}
		catch (MalformedURLException e)
		{
			Trace.catching(DiagramUIPlugin.getInstance(),
					DiagramUIDebugOptions.EXCEPTIONS_CATCHING,
					DefaultPaletteProvider.class, e.getLocalizedMessage(), e); 
			Log.error(DiagramUIPlugin.getInstance(),
					DiagramUIStatusCodes.RESOURCE_FAILURE, e.getMessage(), e);
		}

		return null;
	}
}
