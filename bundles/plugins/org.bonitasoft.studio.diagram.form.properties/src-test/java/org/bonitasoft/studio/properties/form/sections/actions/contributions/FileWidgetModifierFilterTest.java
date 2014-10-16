/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.emf.tools.WidgetModifiersSwitch;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FormFactory;
import org.junit.Test;


public class FileWidgetModifierFilterTest {

    @Test
    public void testSelectViewerDuplicate() {
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        widget.setDuplicate(true);
        final FileWidgetModifierFilter filter = new FileWidgetModifierFilter(widget);

        Assertions.assertThat(filter.select(null, null, WidgetModifiersSwitch.OLD_LIST_OF_DOCUMENT)).isTrue();
        Assertions.assertThat(filter.select(null, null, WidgetModifiersSwitch.NEW_LIST_OF_DOCUMENT)).isTrue();
        Assertions.assertThat(filter.select(null, null, WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME)).isFalse();
    }

    @Test
    public void testSelectViewerSimple() {
        final FileWidget widget = FormFactory.eINSTANCE.createFileWidget();
        final FileWidgetModifierFilter filter = new FileWidgetModifierFilter(widget);

        Assertions.assertThat(filter.select(null, null, WidgetModifiersSwitch.OLD_LIST_OF_DOCUMENT)).isFalse();
        Assertions.assertThat(filter.select(null, null, WidgetModifiersSwitch.NEW_LIST_OF_DOCUMENT)).isFalse();
        Assertions.assertThat(filter.select(null, null, WidgetModifiersSwitch.ENGINE_DOCUMENT_QUALIFIED_NAME)).isTrue();
    }
}
