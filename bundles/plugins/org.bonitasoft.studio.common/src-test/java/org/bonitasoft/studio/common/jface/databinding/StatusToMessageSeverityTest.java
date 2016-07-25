/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.jface.databinding;

import static org.assertj.core.api.Assertions.assertThat;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.forms.IMessage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StatusToMessageSeverityTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_convert_error_status_severity_to_error_message_severity() throws Exception {
        assertThat(new StatusToMessageSeverity(ValidationStatus.error("an error message")).toMessageSeverity()).isEqualTo(IMessage.ERROR);
    }

    @Test
    public void should_convert_warning_status_severity_to_warning_message_severity() throws Exception {
        assertThat(new StatusToMessageSeverity(ValidationStatus.warning("a warning message")).toMessageSeverity()).isEqualTo(IMessage.WARNING);
    }

    @Test
    public void should_convert_info_status_severity_to_info_message_severity() throws Exception {
        assertThat(new StatusToMessageSeverity(ValidationStatus.info("an info message")).toMessageSeverity()).isEqualTo(IMessage.INFORMATION);
    }

    @Test
    public void should_convert_ok_status_severity_to_none_message_severity() throws Exception {
        assertThat(new StatusToMessageSeverity(Status.OK_STATUS).toMessageSeverity()).isEqualTo(IMessage.NONE);
    }

    @Test
    public void should_throw_an_IllegalArgumentException_if_severity_code_is_unknown() throws Exception {
        final StatusToMessageSeverity status2MessageSeverity = new StatusToMessageSeverity(Status.CANCEL_STATUS);

        thrown.expect(IllegalArgumentException.class);

        status2MessageSeverity.toMessageSeverity();
    }

}
