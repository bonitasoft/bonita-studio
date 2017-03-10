/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.wizard.listener;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardDialog;
import org.junit.Test;

public class WizardDoubleClickListenerTest {

    @Test
    public void should_perform_finish_on_double_click() {
        IWizard wizard = mock(IWizard.class);
        when(wizard.canFinish()).thenReturn(true);
        when(wizard.performFinish()).thenReturn(true);

        IWizardPage currentPage = mock(IWizardPage.class);
        when(currentPage.getWizard()).thenReturn(wizard);

        WizardDialog wizardContainer = mock(WizardDialog.class);
        when(wizardContainer.getCurrentPage()).thenReturn(currentPage);

        WizardDoubleClickListener listener = new WizardDoubleClickListener(wizardContainer);

        DoubleClickEvent event = mock(DoubleClickEvent.class);
        listener.doubleClick(event);

        verify(wizard).performFinish();
        verify(wizardContainer).close();

    }

    @Test
    public void should_go_on_next_page_on_double_click() {
        IWizardPage currentPage = mock(IWizardPage.class);
        IWizardPage nextPage = mock(IWizardPage.class);
        when(currentPage.getNextPage()).thenReturn(nextPage);
        when(currentPage.canFlipToNextPage()).thenReturn(true);

        WizardDialog wizardContainer = mock(WizardDialog.class);
        when(wizardContainer.getCurrentPage()).thenReturn(currentPage);

        WizardDoubleClickListener listener = new WizardDoubleClickListener(wizardContainer);

        DoubleClickEvent event = mock(DoubleClickEvent.class);
        listener.doubleClick(event);

        verify(wizardContainer).showPage(nextPage);
    }

    @Test
    public void should_not_go_to_next_page_or_exit_on_double_click() {
        IWizard wizard = mock(IWizard.class);
        when(wizard.canFinish()).thenReturn(false);

        IWizardPage currentPage = mock(IWizardPage.class);
        IWizardPage nextPage = mock(IWizardPage.class);
        when(currentPage.getWizard()).thenReturn(wizard);
        when(currentPage.getNextPage()).thenReturn(nextPage);
        when(currentPage.canFlipToNextPage()).thenReturn(false);

        WizardDialog wizardContainer = mock(WizardDialog.class);
        when(wizardContainer.getCurrentPage()).thenReturn(currentPage);

        WizardDoubleClickListener listener = new WizardDoubleClickListener(wizardContainer);

        DoubleClickEvent event = mock(DoubleClickEvent.class);
        listener.doubleClick(event);

        verify(wizardContainer, times(0)).close();
    }

    @Test
    public void should_not_close_on_double_click_if_finish_fail() {
        IWizard wizard = mock(IWizard.class);
        when(wizard.canFinish()).thenReturn(true);
        when(wizard.performFinish()).thenReturn(false);

        IWizardPage currentPage = mock(IWizardPage.class);
        when(currentPage.getWizard()).thenReturn(wizard);

        WizardDialog wizardContainer = mock(WizardDialog.class);
        when(wizardContainer.getCurrentPage()).thenReturn(currentPage);

        WizardDoubleClickListener listener = new WizardDoubleClickListener(wizardContainer);

        DoubleClickEvent event = mock(DoubleClickEvent.class);
        listener.doubleClick(event);

        verify(wizardContainer, times(0)).close();
    }
}
