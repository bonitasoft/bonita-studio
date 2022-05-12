/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.validator;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.engine.business.application.xml.ApplicationPageNode;
import org.bonitasoft.studio.la.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class ApplicationHomepageValidator implements IValidator<ApplicationNode> {

    @Override
    public IStatus validate(ApplicationNode applicationNode) {
        if (applicationNode.getHomePage() == null || applicationNode.getHomePage().isEmpty()) {
            return ValidationStatus
                    .error(String.format(Messages.applicationWithoutHomepage,
                            applicationNode.getDisplayName()));
        }
        return applicationNode.getApplicationPages().stream().map(ApplicationPageNode::getToken)
                .anyMatch(applicationNode.getHomePage()::equals)
                        ? ValidationStatus.ok()
                        : ValidationStatus
                                .error(String.format(Messages.applicationWithUnknownHomepage,
                                        applicationNode.getDisplayName()));
    }

}
