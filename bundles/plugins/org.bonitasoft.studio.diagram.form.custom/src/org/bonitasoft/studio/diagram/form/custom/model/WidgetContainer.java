/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.form.custom.model;

import java.util.List;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.runtime.Assert;

/**
 * @author Romain Bioteau
 *
 */
public class WidgetContainer {

	private Group group;
	private Form form;

	public WidgetContainer(Group group){
		Assert.isLegal(group != null);
		this.group = group;
	}

	public WidgetContainer(Form form){
		Assert.isLegal(form != null);
		this.form = form;
	}

	public List<Widget> getWidgets(){
		if(form != null){
			return form.getWidgets();
		}
		if(group != null){
			return group.getWidgets();
		}
		return null;
	}


	public boolean isGroup() {
		return group != null && form == null;
	}

	public Group getGroup() {
		return group;
	}
	
	public Form getForm() {
		return form;
	}

}
