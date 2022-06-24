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

import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WellTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_have_an_error_backround_for_error_status() throws Exception {
        final Well well = new Well(realm.createComposite(), "", new FormToolkit(realm.getShell().getDisplay()),
                IStatus.ERROR);

        final GC gc = paint(well);

        assertThat(gc.getBackground()).isEqualTo(Well.errorBackground);
    }

    @Test
    public void should_have_an_error_foreground_for_error_status() throws Exception {
        final Well well = new Well(realm.createComposite(), "", new FormToolkit(realm.getShell().getDisplay()),
                IStatus.ERROR);

        final GC gc = paint(well);

        assertThat(gc.getForeground()).isEqualTo(Well.errorSeprator);
    }

    @Test
    public void should_have_an_info_background_for_info_status() throws Exception {
        final Well well = new Well(realm.createComposite(), "", new FormToolkit(realm.getShell().getDisplay()),
                IStatus.INFO);

        final GC gc = paint(well);

        assertThat(gc.getBackground()).isEqualTo(Well.infoBackground);
    }

    @Test
    public void should_have_an_info_foreground_for_info_status() throws Exception {
        final Well well = new Well(realm.createComposite(), "", new FormToolkit(realm.getShell().getDisplay()),
                IStatus.INFO);

        final GC gc = paint(well);

        assertThat(gc.getForeground()).isEqualTo(Well.infoSeprator);
    }

    @Test
    public void should_have_a_warning_background_for_warning_status() throws Exception {
        final Well well = new Well(realm.createComposite(), "", new FormToolkit(realm.getShell().getDisplay()),
                IStatus.WARNING);

        final GC gc = paint(well);

        assertThat(gc.getBackground()).isEqualTo(Well.warningBackground);
    }

    @Test
    public void should_have_a_warning_foreground_for_warning_status() throws Exception {
        final Well well = new Well(realm.createComposite(), "", new FormToolkit(realm.getShell().getDisplay()),
                IStatus.WARNING);

        final GC gc = paint(well);

        assertThat(gc.getForeground()).isEqualTo(Well.warningSeprator);
    }

    @Test
    public void should_update_label_text() throws Exception {
        final Well well = new Well(realm.createComposite(), "", new FormToolkit(realm.getShell().getDisplay()),
                IStatus.WARNING);

        well.setText("hello");

        assertThat(well.getText()).isEqualTo("hello");
    }

    private GC paint(final Well well) {
        final GC gc = new GC(well);
        final Event event = new Event();
        event.gc = gc;
        well.notifyListeners(SWT.Paint, event);
        return gc;
    }

}
