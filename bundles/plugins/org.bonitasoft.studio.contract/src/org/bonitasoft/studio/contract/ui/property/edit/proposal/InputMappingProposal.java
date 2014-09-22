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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.property.edit.proposal;

import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.data.ui.property.section.DataLabelProvider;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ContractInputMapping;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.JavaObjectData;
import org.eclipse.jface.fieldassist.IContentProposal;


/**
 * @author Romain Bioteau
 *
 */
public class InputMappingProposal implements IContentProposal {

    private final Data data;
    private final String setterName;
    private final String setterParamType;


    public InputMappingProposal(final Data data) {
        this(data, null, null);
    }

    public InputMappingProposal(final Data data, final String setterName, final String setterParamType) {
        this.data = data;
        this.setterName = setterName;
        this.setterParamType = setterParamType;
    }

    public InputMappingProposal(final ContractInputMapping mapping) {
        this(mapping.getData(), mapping.getSetterName(), mapping.getSetterParamType());
    }

    public Data getData() {
        return data;
    }

    @Override
    public String getLabel() {
        if (data instanceof BusinessObjectData || data instanceof JavaObjectData) {
            if (setterName != null) {
                String paramName = setterName.substring(3);
                paramName = Character.toLowerCase(paramName.charAt(0)) + paramName.substring(1);
                return data.getName() + "." + paramName + " -- " + setterParamType;
            }
            return data.getName() + " -- " + DataLabelProvider.getTypeLabel(data);
        } else if (data != null) {
            return data.getName() + " -- " + DataLabelProvider.getTypeLabel(data);
        } else {
            return null;
        }
    }


    @Override
    public String getContent() {
        if (data instanceof BusinessObjectData || data instanceof JavaObjectData) {
            return toDataNameWithField();
        } else if (data != null) {
            return data.getName();
        } else {
            return null;
        }
    }

    public String getInputContent() {
        if (data instanceof BusinessObjectData || data instanceof JavaObjectData) {
            return toValidIdentifierNameWithField();
        } else if (data != null) {
            return data.getName() + Messages.inputSuffix;
        } else {
            return null;
        }
    }

    protected String toDataNameWithField() {
        if (setterName != null) {
            String paramName = setterName.substring(3);
            paramName = Character.toLowerCase(paramName.charAt(0)) + paramName.substring(1);
            return data.getName() + "." + paramName;
        }
        return data.getName();
    }

    protected String toValidIdentifierNameWithField() {
        if (setterName != null) {
            String paramName = setterName.substring(3);
            paramName = Character.toUpperCase(paramName.charAt(0)) + paramName.substring(1);
            return data.getName() + paramName + Messages.inputSuffix;
        }
        return data.getName() + Messages.inputSuffix;
    }

    public String getSetterName() {
        return setterName;
    }

    public String getSetterParamType() {
        return setterParamType;
    }

    @Override
    public int getCursorPosition() {
        return getContent().length();
    }

    @Override
    public String getDescription() {
        return null;
    }
}
