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
package org.bonitasoft.studio.common.jface.databinding.validator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class ReservedRESTAPIKeywordsValidatorTest {

    @Test
    public void testValidate_isOk_withInputNull() throws Exception {
        assertThat(new ReservedRESTAPIKeywordsValidator().validate(null).isOK()).isTrue();
    }

    @Test
    public void testValidate_isKO_withReservedWordsWithVariousCase() throws Exception {
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("content").isOK()).isFalse();
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("CONTENT").isOK()).isFalse();
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("API").isOK()).isFalse();
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("api").isOK()).isFalse();
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("theme").isOK()).isFalse();
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("Theme").isOK()).isFalse();
    }

    @Test
    public void testValidate_isOK_withBasicStrings() throws Exception {
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("test").isOK()).isTrue();
    }

    @Test
    public void testValidate_isOK_withStringMatchingNotExctlyIgnoringCase() throws Exception {
        assertThat(new ReservedRESTAPIKeywordsValidator().validate("content1").isOK()).isTrue();
    }

}
