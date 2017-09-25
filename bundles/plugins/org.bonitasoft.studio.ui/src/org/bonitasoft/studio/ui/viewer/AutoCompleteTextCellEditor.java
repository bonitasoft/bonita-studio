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
package org.bonitasoft.studio.ui.viewer;

import java.util.stream.Stream;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

public class AutoCompleteTextCellEditor extends TextCellEditor {

    private ContentProposalAdapter proposalAdapter;

    public AutoCompleteTextCellEditor(ColumnViewer viewer) {
        super((Composite) viewer.getControl());
    }

    @Override
    protected Control createControl(Composite parent) {
        final Control control = super.createControl(parent);
        Stream.of(control.getListeners(SWT.FocusIn)).forEach(l -> control.removeListener(SWT.FocusIn, l));
        Stream.of(control.getListeners(SWT.FocusOut)).forEach(l -> control.removeListener(SWT.FocusOut, l));
        control.addListener(SWT.Modify, e -> fireControlSpaceEvent());
        return control;
    }

    private void fireControlSpaceEvent() {
        if (proposalAdapter != null && !proposalAdapter.isProposalPopupOpen()
                && (getValue() == null || getValue().toString().isEmpty())) {
            final Event ctrlSpaceEvent = new Event();
            ctrlSpaceEvent.keyCode = SWT.SPACE;
            ctrlSpaceEvent.stateMask = SWT.MOD1;
            getControl().getDisplay().asyncExec(() -> getControl().notifyListeners(SWT.KeyDown, ctrlSpaceEvent));
        }
    }

    @Override
    protected void focusLost() {
        if (proposalAdapter == null || !proposalAdapter.isProposalPopupOpen()) {
            super.focusLost();
        }
    }

    public void setProposalProvider(IContentProposalProvider proposalProvider) {
        final TextContentAdapter controlContentAdapter = new TextContentAdapter();
        proposalAdapter = new ContentProposalAdapter(getControl(), controlContentAdapter,
                proposalProvider, KeyStroke.getInstance(SWT.MOD1, SWT.SPACE), null);
        proposalAdapter.setPropagateKeys(true);
        proposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
        proposalAdapter.setAutoActivationDelay(0);
    }

    @Override
    protected boolean dependsOnExternalFocusListener() {
        return getClass() != AutoCompleteTextCellEditor.class;
    }

}
