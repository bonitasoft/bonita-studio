/**
 * Copyright (C) 2026 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.junit.Test;

/**
 * The decision to show the recovery dialog, which is what keeps a modal dialog out of CI. The dialog
 * itself is redirected rather than opened: the shell resolution inside it needs a workbench, so that
 * half stays covered by the manual pass.
 */
public class RuntimeErrorReporterTest {

    private final List<String> dispatched = new ArrayList<>();

    /** A reporter that believes whatever the test says about the platform and records the dialog. */
    private RuntimeErrorReporter reporterThatIsHeadless(boolean headless) {
        return new RuntimeErrorReporter() {

            @Override
            boolean headless() {
                return headless;
            }

            @Override
            void dispatch(String title, String message) {
                dispatched.add(title + "|" + message);
            }
        };
    }

    @Test
    public void should_not_show_a_dialog_when_headless() {
        reporterThatIsHeadless(true).report(new CoreException(Status.error("locked bundle")));

        assertThat(dispatched).as("a headless run must log the failure and tell nobody").isEmpty();
    }

    @Test
    public void should_show_the_failure_message_when_a_user_is_there() {
        // the message IS the recovery instruction, so it has to reach the dialog verbatim rather
        // than being replaced by a generic one
        reporterThatIsHeadless(false).report(new CoreException(Status.error("close the file and delete it")));

        assertThat(dispatched).singleElement().asString()
                .endsWith("|close the file and delete it");
    }

    @Test
    public void should_show_a_direct_dialog_request_when_a_user_is_there() {
        // waitServerRunning calls openErrorDialog without a CoreException: same guard, other entry
        reporterThatIsHeadless(false).openErrorDialog("Cannot start Tomcat", "timed out");

        assertThat(dispatched).containsExactly("Cannot start Tomcat|timed out");
    }

    @Test
    public void should_not_show_a_direct_dialog_request_when_headless() {
        reporterThatIsHeadless(true).openErrorDialog("Cannot start Tomcat", "timed out");

        assertThat(dispatched).isEmpty();
    }
}
