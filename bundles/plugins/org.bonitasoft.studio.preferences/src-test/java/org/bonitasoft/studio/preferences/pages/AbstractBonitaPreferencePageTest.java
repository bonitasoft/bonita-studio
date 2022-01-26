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
package org.bonitasoft.studio.preferences.pages;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.preferences.extension.IPreferenceFieldEditorContribution;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractBonitaPreferencePageTest {

    @Mock
    private AbstractBonitaPreferencePage preferencePage;
    @Mock
    private FieldEditor aFieldEditor;

    @Test
    public void should_createPreferenceEditorContributions_call_createFieldEditors_for_matching_ids() throws Exception {
        final List<IPreferenceFieldEditorContribution> contributions = listOfContribWithIds("myContribId");
        when(preferencePage.getFieldEditorContibutions()).thenReturn(contributions);
        doCallRealMethod().when(preferencePage).createPreferenceEditorContributions(any());
        preferencePage.createPreferenceEditorContributions("myContribId");

        verify(contributions.get(0)).createFieldEditors(any());
    }

    @Test
    public void should_createPreferenceEditorContributions_do_not_call_createFieldEditors_for_not_matching_ids() throws Exception {
        final List<IPreferenceFieldEditorContribution> contributions = listOfContribWithIds("myContribId");
        when(preferencePage.getFieldEditorContibutions()).thenReturn(contributions);
        doCallRealMethod().when(preferencePage).createPreferenceEditorContributions(anyString());
        preferencePage.createPreferenceEditorContributions("myContribId2");

        verify(contributions.get(0), never()).createFieldEditors(any(Composite.class));
    }

    @Test
    public void should_initialize_load_editor_preference_store() throws Exception {
        final Map<FieldEditor, IPreferenceStore> contributedEditor = mapOfContributedEditors(aFieldEditor);
        when(preferencePage.getContributedEditors()).thenReturn(contributedEditor);
        doCallRealMethod().when(preferencePage).initialize();
        preferencePage.initialize();

        verify(contributedEditor.keySet().iterator().next()).setPreferenceStore(contributedEditor.values().iterator().next());
        verify(contributedEditor.keySet().iterator().next()).load();
    }

    private Map<FieldEditor, IPreferenceStore> mapOfContributedEditors(final FieldEditor... editors) {
        final Map<FieldEditor, IPreferenceStore> contribuedEditors = new HashMap<FieldEditor, IPreferenceStore>();
        for (final FieldEditor editor : editors) {
            contribuedEditors.put(editor, mock(IPreferenceStore.class));
        }
        return contribuedEditors;
    }

    private List<IPreferenceFieldEditorContribution> listOfContribWithIds(final String... ids) {
        final List<IPreferenceFieldEditorContribution> contribList = new ArrayList<IPreferenceFieldEditorContribution>();
        for (final String id : ids) {
            final IPreferenceFieldEditorContribution filedEditorContribMock = mock(IPreferenceFieldEditorContribution.class);
            when(filedEditorContribMock.appliesTo(id)).thenReturn(true);
            contribList.add(filedEditorContribMock);
        }
        return contribList;
    }
}
