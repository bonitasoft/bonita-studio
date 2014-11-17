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
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.Widget;
import org.junit.Test;


public class DefaultValueContributionTest {

    @Test
    public void testGetExpressionViewerFilterContainsGroupIterator() {
        final DefaultValueContribution defaultValueContribution = new DefaultValueContribution();
        final Group group = FormFactory.eINSTANCE.createGroup();
        final Widget widget = FormFactory.eINSTANCE.createListFormField();
        group.getWidgets().add(widget);
        defaultValueContribution.setEObject(widget);
        final AvailableExpressionTypeFilter filter = defaultValueContribution.getExpressionViewerFilter();
        Assertions.assertThat(filter.getContentTypes()).contains(ExpressionConstants.GROUP_ITERATOR_TYPE);
    }

    @Test
    public void testGetExpressionViewerFilterNotContainGroupIterator() {
        final DefaultValueContribution defaultValueContribution = new DefaultValueContribution();
        final Widget widget = FormFactory.eINSTANCE.createListFormField();
        defaultValueContribution.setEObject(widget);
        final AvailableExpressionTypeFilter filter = defaultValueContribution.getExpressionViewerFilter();
        Assertions.assertThat(filter.getContentTypes()).doesNotContain(ExpressionConstants.GROUP_ITERATOR_TYPE);
    }

    @Test
    public void testInitialInformationMessage() {
        final DefaultValueContribution defaultValueContribution = new DefaultValueContribution();
        final Widget[] widgets = new Widget[] {
                FormFactory.eINSTANCE.createRadioFormField(),
                FormFactory.eINSTANCE.createComboFormField(),
                FormFactory.eINSTANCE.createSelectFormField()
        };
        for (final Widget widget : widgets) {
            defaultValueContribution.setEObject(widget);
            Assertions.assertThat(defaultValueContribution.getInitialInformationMessage()).isEqualTo(Messages.data_tooltip_text);
        }

        final Widget[] widgetForList = new Widget[] {
                FormFactory.eINSTANCE.createCheckBoxMultipleFormField(),
                FormFactory.eINSTANCE.createListFormField(),
                FormFactory.eINSTANCE.createSuggestBox(),
                FormFactory.eINSTANCE.createTable()
        };
        for (final Widget widget : widgetForList) {
            defaultValueContribution.setEObject(widget);
            Assertions.assertThat(defaultValueContribution.getInitialInformationMessage()).isEqualTo(Messages.data_tooltip_list);
        }

    }

}
