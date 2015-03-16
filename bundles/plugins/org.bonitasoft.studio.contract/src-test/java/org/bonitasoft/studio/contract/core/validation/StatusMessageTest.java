/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.contract.core.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.MessageFormat;

import org.bonitasoft.studio.contract.ContractPlugin;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Romain Bioteau
 *
 */
public class StatusMessageTest {

    private StatusMessage statusMessage;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_toString_return_status_message_for_single_statuses() throws Exception {
        final Status status = new Status(IStatus.ERROR, ContractPlugin.PLUGIN_ID, "a message");
        statusMessage = new StatusMessage(status) {

            @Override
            protected String getBaseLabel() {
                return null;
            }
        };

        assertThat(statusMessage.toString()).isEqualTo(status.getMessage());
    }

    @Test
    public void should_toString_return_appended_message_for_multi_status() throws Exception {
        final MultiStatus status = new MultiStatus(ContractPlugin.PLUGIN_ID, IStatus.OK, null, null);
        status.add(ValidationStatus.error("error1"));
        status.add(ValidationStatus.error("error2"));
        statusMessage = new StatusMessage(status) {

            @Override
            protected String getBaseLabel() {
                return "Error found: {0}";
            }
        };

        assertThat(statusMessage.toString()).isEqualTo(MessageFormat.format(statusMessage.getBaseLabel(), " \"error1\", \"error2\""));
    }

}
