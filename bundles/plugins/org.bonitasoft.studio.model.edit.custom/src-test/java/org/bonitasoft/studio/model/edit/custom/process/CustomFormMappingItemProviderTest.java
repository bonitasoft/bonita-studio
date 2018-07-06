/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.model.edit.custom.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.model.edit.custom.i18n.Messages;
import org.bonitasoft.studio.model.process.FormMapping;
import org.bonitasoft.studio.model.process.FormMappingType;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class CustomFormMappingItemProviderTest {

    private CustomFormMappingItemProvider customFormMappingItemProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customFormMappingItemProvider = new CustomFormMappingItemProvider(new ProcessItemProviderAdapterFactory());
    }

    @Test
    public void should_display_internal_mode_form_mapping() throws Exception {
        //Given
        final FormMapping internalFormMapping = aFormMapping().withType(FormMappingType.INTERNAL).havingTargetForm(anExpression().withName("Step1 form"))
                .build();

        //When
        final String text = customFormMappingItemProvider.getText(internalFormMapping);

        //Then
        assertThat(text).isEqualTo("Form Mapping INTERNAL");
    }

    @Test
    public void should_display_url_for_url_form_mapping() throws Exception {
        //Given
        final FormMapping urlFormMapping = aFormMapping().withType(FormMappingType.URL).withURL("http://www.myUrl.com").build();

        //When
        final String text = customFormMappingItemProvider.getText(urlFormMapping);

        //Then
        assertThat(text).isEqualTo(Messages.bind(Messages.urlFormMapping, "http://www.myUrl.com"));
    }

    @Test
    public void should_display_when_url_form_mapping_is_for_overview() throws Exception {
        //Given
        final FormMapping overviewFormMapping = aPool()
                .havingOverviewFormMapping(aFormMapping().withType(FormMappingType.URL).withURL("http://www.myUrl.com"))
                .build().getOverviewFormMapping();

        //When
        final String text = customFormMappingItemProvider.getText(overviewFormMapping);

        //Then
        assertThat(text).isEqualTo(Messages.bind(Messages.overviewUrlFormMapping, "http://www.myUrl.com"));
    }

}
