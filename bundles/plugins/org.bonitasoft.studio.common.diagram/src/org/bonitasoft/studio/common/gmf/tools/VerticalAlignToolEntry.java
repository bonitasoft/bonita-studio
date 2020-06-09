/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author Romain Bioteau
 *
 */
public class VerticalAlignToolEntry extends ToolEntry{


	public VerticalAlignToolEntry() {
		this(null, null);
	}

	public VerticalAlignToolEntry(String label) {
		this(label, null);
	}

	public VerticalAlignToolEntry(String label, String description) {
		super(label, description, null, null, VerticalAlignTool.class);
		if (label == null || label.length() == 0)
			setLabel(Messages.toolAlignVertical);
		setUserModificationPermission(PERMISSION_NO_MODIFICATION);
	}


	/**
	 * @see org.eclipse.gef.palette.PaletteEntry#getDescription()
	 */
	public String getDescription() {
		return Messages.toolAlignVerticalDesc;
	}

	/**
	 * @see org.eclipse.gef.palette.PaletteEntry#getLargeIcon()
	 */
	public ImageDescriptor getLargeIcon() {
		return Pics.getImageDescriptor("palette/aligncenter.gif");
	}


	/**
	 * @see org.eclipse.gef.palette.PaletteEntry#getSmallIcon()
	 */
	public ImageDescriptor getSmallIcon() {
		return Pics.getImageDescriptor("palette/align-vertical.png");
	}
}
