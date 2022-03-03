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
package org.bonitasoft.studio.diagram.custom.tools;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.ToolEntry;

/**
 * @author Aurelien Pupier
 *
 */
public class ToolAndToolEntry {
	
	private Tool tool;
	private ToolEntry toolEntry;
	
	/**
	 * @return the toolEntry
	 */
	public ToolEntry getToolEntry() {
		return toolEntry;
	}

	/**
	 * @param toolEntry the toolEntry to set
	 */
	public void setToolEntry(ToolEntry toolEntry) {
		this.toolEntry = toolEntry;
	}

	public ToolAndToolEntry(Tool tool, ToolEntry toolEntry){
		this.tool = tool;
		this.toolEntry = toolEntry;
	}
	
	/**
	 * @return the tool
	 */
	public Tool getTool() {
		return tool;
	}

	/**
	 * @param tool the tool to set
	 */
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	
}
