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
package org.bonitasoft.studio.preferences.dialog;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BonitaPreferenceDialogTest {

    private BonitaPreferenceDialog bpd;

    @Mock
    private ToolItem toolItem;

    @Mock
    private Label label;

    @Mock
    private Composite composite;

    @Test
    public void testCreateOtherCategoryLine() throws Exception {
        final Shell shell = new Shell();
        bpd = spy(new BonitaPreferenceDialog(shell));
        doCallRealMethod().when(bpd).createOtherCategoryLine(any(Composite.class), eq(null));
        doReturn(toolItem).when(bpd).createTool(any(Composite.class), any(Color.class), any(Image.class), any(Image.class), anyString());
        doReturn(composite).when(bpd).createRow(any(Composite.class), any(Color.class), anyString(), anyInt());
        doReturn(label).when(bpd).createItemLabel(any(Composite.class), any(Color.class), anyString());

        bpd.createOtherCategoryLine(composite, null);

        verify(bpd).createTool(any(Composite.class), any(Color.class), any(Image.class), any(Image.class), eq(BonitaPreferenceDialog.ADVANCED_PAGE_ID));
        verify(bpd).createTool(any(Composite.class), any(Color.class), any(Image.class), any(Image.class), eq((String) null));
        verify(bpd).putInItemPerPreferenceNode(BonitaPreferenceDialog.ADVANCED_PAGE_ID, toolItem);
        verify(bpd).putInItemPerPreferenceNode(BonitaPreferenceDialog.ECLIPSE_PAGE_ID, toolItem);
        verify(bpd).putInLabelPerPreferenceNode(BonitaPreferenceDialog.ADVANCED_PAGE_ID, label);
        verify(bpd).putInLabelPerPreferenceNode(BonitaPreferenceDialog.ECLIPSE_PAGE_ID, label);

    }
}
