/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints.form;

import java.io.File;

import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AssociatedFile;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Aurelien Pupier
 */
public class PageTemplateConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof Form) {
            final Form f = (Form) eObj;
            final AssociatedFile htmlTemplate = f.getHtmlTemplate();
            if (htmlTemplate != null && htmlTemplate.getPath() != null && !htmlTemplate.getPath().isEmpty()) {
                final File template = WebTemplatesUtil.getFile(htmlTemplate.getPath());
                if (template == null || !template.isFile()) {
                    return ctx.createFailureStatus(new Object[] { NLS.bind(Messages.pageTemplateNotValid, f.getName()) });
                }
            }
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraint.form.pagetemplate";
    }

}
