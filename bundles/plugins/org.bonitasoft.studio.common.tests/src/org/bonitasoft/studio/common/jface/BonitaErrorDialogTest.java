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
package org.bonitasoft.studio.common.jface;

import static org.mockito.Mockito.doCallRealMethod;

import java.lang.reflect.InvocationTargetException;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BonitaErrorDialogTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Mock
    private BonitaErrorDialog bed;

    private List list;

    @Before
    public void init() throws Exception {
        list = new List(realm.createComposite(), SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
    }

    @Test
    public void testPopulateStackDetailsSimple() {
        doCallRealMethod().when(bed).populateStackDetails(Mockito.any(List.class));
        final RuntimeException runtimeException = new RuntimeException("plop");
        final StackTraceElement stackTraceElement = new StackTraceElement(this.getClass().getName(), "plopMethod", "fileName", 100);
        runtimeException.setStackTrace(new StackTraceElement[] { stackTraceElement });
        Mockito.doReturn(runtimeException).when(bed).getStatusException();

        bed.populateStackDetails(list);

        Assertions.assertThat(list.getItem(0)).isEqualTo("plop");
        Assertions.assertThat(list.getItem(1)).isEqualTo("org.bonitasoft.studio.common.jface.BonitaErrorDialogTest.plopMethod(fileName:100)");
    }

    @Test
    public void testPopulateStackDetailsWithInvocationTargetException() {
        doCallRealMethod().when(bed).populateStackDetails(Mockito.any(List.class));
        final Throwable targetException = new RuntimeException("targetException");
        final StackTraceElement stackTraceElement = new StackTraceElement("targetClassName", "plopMethod", "fileName", 100);
        targetException.setStackTrace(new StackTraceElement[] { stackTraceElement });
        final InvocationTargetException invocationTargetException = new InvocationTargetException(targetException, "Invocation exception");
        Mockito.doReturn(invocationTargetException).when(bed).getStatusException();

        bed.populateStackDetails(list);

        Assertions.assertThat(list.getItem(0)).isEqualTo("Invocation exception");
        Assertions.assertThat(list.getItem(1)).isEqualTo("targetException");
        Assertions.assertThat(list.getItem(2)).isEqualTo("targetClassName.plopMethod(fileName:100)");
    }

}
