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
package org.bonitasoft.studio.connector.model.definition.dialog;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.LabelProvider;


/**
 * @author Romain Bioteau
 *
 */
public class WidgetLabelProvider extends LabelProvider {

    @Override
    public String getText(Object element) {
        EClass eClass = (EClass) element ;
        if(eClass.equals(ConnectorDefinitionPackage.Literals.TEXT)){
            return Messages.textWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.CHECKBOX)){
            return Messages.checkboxWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.PASSWORD)){
            return Messages.passwordWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.SELECT)){
            return Messages.selectWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.LIST)){
            return Messages.listWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.ARRAY)){
            return Messages.arrayWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.RADIO_GROUP)){
            return Messages.radioGroupWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.GROUP)){
            return Messages.groupWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.SCRIPT_EDITOR)){
            return Messages.scriptEditorWidgetLabel ;
        }else if(eClass.equals(ConnectorDefinitionPackage.Literals.TEXT_AREA)){
            return Messages.textAreaEditor ;
        }
        return super.getText(element);
    }

}
