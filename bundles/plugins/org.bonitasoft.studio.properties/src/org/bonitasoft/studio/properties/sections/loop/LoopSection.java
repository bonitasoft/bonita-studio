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
package org.bonitasoft.studio.properties.sections.loop;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;

/**
 * @author Mickael Istria
 *
 */
public class LoopSection extends ExtensibleGridPropertySection {

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection#addContributions()
     */
    @Override
    protected void addContributions() {
        addContribution(new MultiInstantiationPropertyContribution());
    }
    
    protected Layout getLayout() {
        GridLayout layout = new GridLayout(2,false);
        layout.marginTop = 1;
        layout.marginLeft = 1;
        layout.verticalSpacing = 5 ;
        layout.horizontalSpacing = 5 ;
        return layout;
    }



	@Override
	public String getSectionDescription() {
		return Messages.loopSectionDescription;
	}


    
}