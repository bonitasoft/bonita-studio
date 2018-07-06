/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.designer.ui.property.section.control;

import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public final class InfoMessageConverter extends Converter {

    private final FormMappingType formMappingType;

    public InfoMessageConverter(final FormMappingType formMappingType) {
        super(FormMapping.class, String.class);
        this.formMappingType = formMappingType;
    }

    @Override
    public Object convert(final Object fromObject) {
        if (fromObject != null) {
            final EObject context = ((FormMapping) fromObject).eContainer();
            final EReference formMappingFeature = ((FormMapping) fromObject).eContainmentFeature();
            switch (formMappingType) {
                case INTERNAL:
                    return getUIDesignerMessage(context, formMappingFeature);
                case URL:
                    return getURLMessage(context, formMappingFeature);
                case NONE:
                    return getNoFormMessage(context, formMappingFeature);
                default:
                    break;
            }
        }
        return null;
    }

    private String getUIDesignerMessage(final EObject context, final EReference formMappingFeature) {
        if (ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING.equals(formMappingFeature)) {
            return Messages.overviewUIDesignerInfo;
        }
        return context instanceof Task ? Messages.stepUIDesignerInfo : Messages.processUIDesignerInfo;
    }

    private String getURLMessage(final EObject context, final EReference formMappingFeature) {
        if (ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING.equals(formMappingFeature)) {
            return Messages.overviewURLInfo;
        }
        return context instanceof Task ? Messages.stepURLInfo : Messages.processURLInfo;
    }

    private String getLegacyMessage(final EObject context, final EReference formMappingFeature) {
        return org.bonitasoft.studio.common.Messages.deprecatedLegacyMode;
    }

    private String getNoFormMessage(final EObject context, final EReference formMappingFeature) {
        if (ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING.equals(formMappingFeature)) {
            return Messages.overviewnoFormMessage;
        }
        return context instanceof Task ? Messages.noFormMessageOnTask : Messages.noFormMessageOnProcess;
    }

}
