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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.ui.UIPlugin;
import org.eclipse.jface.bindings.Trigger;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;

public class AutoCompleteTextCellEditor extends TextCellEditor {

    private ContentProposalAdapter proposalAdapter;
    private KeyStroke contentAssistKeyStroke;

    public AutoCompleteTextCellEditor(ColumnViewer viewer) {
        super((Composite) viewer.getControl());
        Optional<KeyStroke> eclipseContentAssistKeyStroke = retrieveEclipseContentAssistKeyStroke();
        if (eclipseContentAssistKeyStroke.isPresent()) {
            contentAssistKeyStroke = eclipseContentAssistKeyStroke.get();
        } else {
            BonitaStudioLog.warning(
                    "Unable to retrieve eclipse content assist binding. Content assist won't be available in editors. Check key binding for the `Content Assist` command in the eclipse preferences.",
                    UIPlugin.PLUGIN_ID);
        }
    }

    private Optional<KeyStroke> retrieveEclipseContentAssistKeyStroke() {
        IBindingService bindingService = PlatformUI.getWorkbench().getService(IBindingService.class);
        return Optional
                .ofNullable(bindingService.getBestActiveBindingFor("org.eclipse.ui.edit.text.contentAssist.proposals"))
                .map(triggerSequence -> {
                    List<Trigger> triggers = Arrays.asList(triggerSequence.getTriggers());
                    return triggers.size() > 1
                            ? null // auto complete cell editor doesn't handle sequence
                            : triggers.stream()
                                    .filter(KeyStroke.class::isInstance)
                                    .map(KeyStroke.class::cast)
                                    .findFirst()
                                    .orElse(null);
                });
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
        if (contentAssistKeyStroke != null
                && proposalAdapter != null
                && !proposalAdapter.isProposalPopupOpen()
                && (getValue() == null || getValue().toString().isEmpty())) {
            final Event ctrlSpaceEvent = new Event();
            ctrlSpaceEvent.keyCode = contentAssistKeyStroke.getNaturalKey();
            ctrlSpaceEvent.stateMask = contentAssistKeyStroke.getModifierKeys();
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
                proposalProvider, contentAssistKeyStroke, null);
        proposalAdapter.setPropagateKeys(true);
        proposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
        proposalAdapter.setAutoActivationDelay(0);
        proposalAdapter.getControl().addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                AutoCompleteTextCellEditor.this.focusLost();
            }
        });
    }

    @Override
    protected boolean dependsOnExternalFocusListener() {
        return getClass() != AutoCompleteTextCellEditor.class;
    }

}
