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
package org.bonitasoft.studio.la.ui.validator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.ui.editor.layout.LayoutDescriptor;
import org.bonitasoft.studio.la.ui.editor.layout.LayoutProvider;
import org.bonitasoft.studio.ui.validator.TypedValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

public class CustomLayoutValidator extends TypedValidator<String, IStatus> {

    private final LayoutProvider layoutProvider;

    public CustomLayoutValidator(LayoutProvider layoutProvider) {
        this.layoutProvider = Objects.requireNonNull(layoutProvider);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.validator.TypedValidator#doValidate(java.util.Optional)
     */
    @Override
    protected IStatus doValidate(Optional<String> value) {
        final String layoutDisplayName = value.orElse("");
        final List<LayoutDescriptor> layouts = layoutProvider.getLayouts();
        final LayoutDescriptor layoutDescriptor = (LayoutDescriptor) LayoutProvider
                .toLayoutDescriptor(layouts).convert(layoutDisplayName);
        return layoutProvider.isDefaultLayout(layoutDescriptor.getId())
                ? ValidationStatus.ok()
                : layouts.stream()
                        .noneMatch(
                                layout -> Objects.equals(layout.getDisplayName(), layoutDescriptor.getDisplayName()))
                                        ? ValidationStatus.warning(Messages.unknownCustomLayout)
                                        : ValidationStatus.ok();
    }

}
