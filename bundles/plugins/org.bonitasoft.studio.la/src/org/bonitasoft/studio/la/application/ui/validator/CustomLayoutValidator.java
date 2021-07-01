/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.validator;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageDescriptor;
import org.bonitasoft.studio.la.application.ui.editor.customPage.CustomPageProvider;
import org.bonitasoft.studio.la.application.ui.editor.customPage.LayoutProposalProvider;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.validator.TypedValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class CustomLayoutValidator extends TypedValidator<String, IStatus> {

    private final LayoutProposalProvider layoutProvider;

    public CustomLayoutValidator(LayoutProposalProvider layoutProvider) {
        this.layoutProvider = Objects.requireNonNull(layoutProvider);
    }

    @Override
    protected IStatus doValidate(Optional<String> value) {
        String layoutDisplayName = value.orElse("");
        Collection<CustomPageDescriptor> layouts = layoutProvider.getLayouts();
        CustomPageDescriptor layoutDescriptor = CustomPageProvider
                .getCustomPageDescriptorConverter(layouts)
                .convert(layoutDisplayName);
        return layouts
                .stream()
                .anyMatch(layout -> Objects.equals(layout.getDisplayName(), layoutDescriptor.getDisplayName()))
                        ? ValidationStatus.ok()
                        : ValidationStatus.warning(Messages.unknownCustomLayout);
    }

}
