/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.configuration.ConfigurationPlugin;
import org.bonitasoft.studio.configuration.i18n.Messages;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class FragmentTypeLabelProvider extends LabelProvider {

    @Override
    public String getText(Object object) {
        if (object instanceof FragmentContainer) {
            String id = ((FragmentContainer) object).getId();
            if (FragmentTypes.CONNECTOR.equals(id)) {
                return Messages.connector;
            } else if (FragmentTypes.ACTOR_FILTER.equals(id)) {
                return Messages.actorfilter;
            } else if (FragmentTypes.VALIDATOR.equals(id)) {
                return Messages.validator;
            } else if (FragmentTypes.DATA_TYPES.equals(id)) {
                return Messages.datatypes;
            } else if (FragmentTypes.OTHER.equals(id)) {
                return Messages.others;
            } else if (FragmentTypes.GROOVY_SCRIPT.equals(id)) {
                return Messages.groovyScripts;
            } else {
                return ((FragmentContainer) object).getId();
            }
        }
        return super.getText(object);
    }

    @Override
    public Image getImage(Object object) {
        if (object instanceof FragmentContainer) {
            String id = ((FragmentContainer) object).getId();
            if (FragmentTypes.CONNECTOR.equals(id)) {
                return Pics.getImage(PicsConstants.connectorDef);
            } else if (FragmentTypes.ACTOR_FILTER.equals(id)) {
                return Pics.getImage(PicsConstants.filterDef);
            } else if (FragmentTypes.VALIDATOR.equals(id)) {
                return Pics.getImage(PicsConstants.validator);
            } else if (FragmentTypes.DATA_TYPES.equals(id)) {
                return Pics.getImage(PicsConstants.datatypes);
            } else if (FragmentTypes.OTHER.equals(id)) {
                return Pics.getImage("dependencies.png", ConfigurationPlugin.getDefault());
            } else if (FragmentTypes.GROOVY_SCRIPT.equals(id)) {
                return Pics.getImage(PicsConstants.groovyScript);
            } else {
                FragmentContainer fc = ((FragmentContainer) object).getParent();
                if (fc != null) {
                    return getImage(fc);
                }
            }
        }

        return super.getImage(object);
    }

}
