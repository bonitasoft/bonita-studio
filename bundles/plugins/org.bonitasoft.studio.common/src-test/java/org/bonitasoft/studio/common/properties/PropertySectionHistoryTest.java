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
package org.bonitasoft.studio.common.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PropertySectionHistoryTest {

    private PropertySectionHistory propertySectionHistory;

    @Before
    public void setUp() throws Exception {
        propertySectionHistory = new PropertySectionHistory("mySection");
    }

    @After
    public void tearDown() throws Exception {
        propertySectionHistory.clear();
    }

    @Test
    public void should_save_description_visibility_when_showing_description() throws Exception {
        propertySectionHistory.showDescription();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_save_description_visibility_when_hiding_description() throws Exception {
        propertySectionHistory.hideDescription();

        assertThat(propertySectionHistory.isDescriptionVisible()).isFalse();
    }

    @Test
    public void should_keep_last_saved_state_of_description_visibility() throws Exception {
        propertySectionHistory.showDescription();
        propertySectionHistory.save();
        propertySectionHistory.hideDescription();
        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_description_be_hidden_by_default() throws Exception {
        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isFalse();
    }

    @Test
    public void should_description_be_visible_by_default_for_section_with_OperationsPropertySection_id() throws Exception {
        propertySectionHistory = new PropertySectionHistory("OperationsPropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_description_be_visible_by_default_for_section_with_EntryFormMappingPropertySection_id()
            throws Exception {
        propertySectionHistory = new PropertySectionHistory("EntryFormMappingPropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_description_be_visible_by_default_for_section_with_CaseStartFormMappingPropertySection_id()
            throws Exception {
        propertySectionHistory = new PropertySectionHistory("CaseStartFormMappingPropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_description_be_visible_by_default_for_section_with_CaseOverviewFormMappingPropertySection_id()
            throws Exception {
        propertySectionHistory = new PropertySectionHistory("CaseOverviewFormMappingPropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_description_be_hidden_by_default_for_section_with_ContractPropertySection_id() throws Exception {
        propertySectionHistory = new PropertySectionHistory("ContractPropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isFalse();
    }

    @Test
    public void should_description_be_visible_by_default_for_section_with_ResourcePropertySection_id() throws Exception {
        propertySectionHistory = new PropertySectionHistory("ResourcePropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_description_be_visible_by_default_for_section_with_LookAndFeelPropertySection_id() throws Exception {
        propertySectionHistory = new PropertySectionHistory("LookAndFeelPropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

    @Test
    public void should_description_be_visible_by_default_for_section_with_ConfirmationPropertySection_id() throws Exception {
        propertySectionHistory = new PropertySectionHistory("ConfirmationPropertySection");

        propertySectionHistory.load();

        assertThat(propertySectionHistory.isDescriptionVisible()).isTrue();
    }

}
