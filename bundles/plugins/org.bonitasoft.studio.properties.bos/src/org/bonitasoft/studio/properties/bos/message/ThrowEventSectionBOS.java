/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.bos.message;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.properties.sections.message.ThrowEventSection;
import org.bonitasoft.studio.properties.sections.message.wizards.UpdateMessageEventWizard;



/**
 * @author Aurelien Pupier
 *
 */
public class ThrowEventSectionBOS extends ThrowEventSection {

	public ThrowEventSectionBOS(){
		//KEEP me, I'm a section declaration accessed by introspection
	}
	
	protected UpdateMessageEventWizard createMessageEventWizard(MainProcess diagram,Message message) {
		return new UpdateMessageEventWizard(diagram,getThrowMessageEvent(), message, getEditingDomain(), ThrowEventSectionBOS.this);
	}
}
