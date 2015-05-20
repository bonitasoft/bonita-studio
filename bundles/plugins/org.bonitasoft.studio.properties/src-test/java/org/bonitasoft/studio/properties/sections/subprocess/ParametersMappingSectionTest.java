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
package org.bonitasoft.studio.properties.sections.subprocess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.CallActivityBuilder.aCallActivity;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.data.provider.DataExpressionProvider;
import org.bonitasoft.studio.expression.editor.ExpressionEditorService;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.InputMappingAssignationType;
import org.bonitasoft.studio.model.process.builders.InputMappingBuilder;
import org.bonitasoft.studio.model.process.provider.ProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ParametersMappingSectionTest {

    @Rule
    public final RealmWithDisplay realm = new RealmWithDisplay();

    private ParametersMappingSection section;

    @Mock
    private IWorkbenchPart fakePart;
    @Mock
    private TabbedPropertySheetPage aTabbedPropertySheetPage;
    @Mock
    private ExpressionEditorService expressionEditorService;
    @Mock
    private RepositoryAccessor repositoryAccessor;
    @Mock
    private ISharedImages sharedImages;

    @Before
    public void setUp() throws Exception {
        doReturn(new TabbedPropertySheetWidgetFactory()).when(aTabbedPropertySheetPage).getWidgetFactory();
        doReturn(new DataExpressionProvider(repositoryAccessor)).when(expressionEditorService).getExpressionProvider(ExpressionConstants.VARIABLE_TYPE);
    }

    @Test
    public void testInputMappingLineInitialized() throws Exception {
        final CallActivity callActivity = aCallActivity()
                .havingInputMappings(InputMappingBuilder.anInputMapping().build())
                .build();
        final Composite createComposite = initSection(callActivity);
        checkOneInputMappingLine(createComposite);
    }



    @Test
    public void testAddInputMappingLine() throws Exception {
        final CallActivity callActivity = aCallActivity().build();
        final Composite createComposite = initSection(callActivity);

        checkNoInputMappingLine(createComposite);

        /* Add an input map */
        final Integer[] pathToAddInputMappingsButton = new Integer[] { 0, 1, 0, 1, 1 };
        final Control addInputMappingsButton = retrieveWidgetAtPosition(createComposite, pathToAddInputMappingsButton);
        addInputMappingsButton.notifyListeners(SWT.Selection, new Event());
        checkOneInputMappingLine(createComposite);
        assertThat(callActivity.getInputMappings()).isNotEmpty();

        /* Delete the line */
        final Integer[] pathToDeleteInputMappingsButton = new Integer[] { 0, 1, 0, 1, 0, 7 };
        final Button deleteInputMappingsButton = (Button) retrieveWidgetAtPosition(createComposite, pathToDeleteInputMappingsButton);
        deleteInputMappingsButton.notifyListeners(SWT.Selection, new Event());
        checkNoInputMappingLine(createComposite);
        assertThat(callActivity.getInputMappings()).isEmpty();
    }

    @Test
    public void testUpdateAssignationType() {
        final CallActivity callActivity = aCallActivity()
                .havingInputMappings(InputMappingBuilder.anInputMapping().build())
                .build();
        final Composite createComposite = initSection(callActivity);

        final Integer[] pathToAssignedToInputMappingsCombo = new Integer[] { 0, 1, 0, 1, 0, 5 };
        final CCombo assignedToInputMappingsCombo = (CCombo) retrieveWidgetAtPosition(createComposite, pathToAssignedToInputMappingsCombo);
        assertThat(callActivity.getInputMappings().get(0).getAssignationType()).isEqualTo(InputMappingAssignationType.CONTRACT_INPUT);

        assignedToInputMappingsCombo.select(1);
        assertThat(callActivity.getInputMappings().get(0).getAssignationType()).isEqualTo(InputMappingAssignationType.DATA);
    }

    private void checkNoInputMappingLine(final Composite createComposite) {
        final Integer[] pathToInputMappingsComposite = new Integer[] { 0, 1, 0, 1, 0 };
        final Composite inputMappingComposite = (Composite) retrieveWidgetAtPosition(createComposite, pathToInputMappingsComposite);
        assertThat(inputMappingComposite.getChildren()).hasSize(4);
    }

    private void checkOneInputMappingLine(final Composite createComposite) {
        final Integer[] pathToInputMappingsComposite = new Integer[] { 0, 1, 0, 1, 0 };
        final Composite inputMappingComposite = (Composite) retrieveWidgetAtPosition(createComposite, pathToInputMappingsComposite);
        assertThat(inputMappingComposite.getChildren()).hasSize(8);
    }


    protected Control retrieveWidgetAtPosition(final Composite baseComposite, final Integer[] pathToWidget) {
        Control currentComposite = baseComposite;
        for (final Integer position : pathToWidget) {
            currentComposite = ((Composite) currentComposite).getChildren()[position];
        }
        return currentComposite;
    }

    protected Composite initSection(final CallActivity callActivity) {
        section = new ParametersMappingSection(expressionEditorService, sharedImages, repositoryAccessor);
        section.setEditingDomain(new TransactionalEditingDomainImpl(new ProcessItemProviderAdapterFactory()));
        section.setInput(fakePart, new StructuredSelection(new EObjectAdapter(callActivity)));
        final Composite createComposite = realm.createComposite();
        section.createControls(createComposite, aTabbedPropertySheetPage);
        section.refresh();
        return createComposite;
    }
}
