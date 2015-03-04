/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.pagedesigner.ui.contribution;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.studio.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.studio.model.process.builders.TaskBuilder.aTask;
import static org.mockito.Mockito.doReturn;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.pagedesigner.core.expression.CreateNewFormProposalListener;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class NewFormContributionItemTest {

    @InjectMocks
    private NewFormContributionItem contribution;

    @Mock
    private CreateNewFormProposalListener listener;

    @Mock
    private RepositoryAccessor repositoryAccessor;

    @Mock
    private ISelectionProvider selectionProvider;

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contribution.setSelectionProvider(selectionProvider);
    }

    @Test
    public void should_fillToolbar_with_a_toolItem() throws Exception {
        final Composite composite = realmWithDisplay.createComposite();
        final ToolBar toolbar = new ToolBar(composite, SWT.NONE);
        contribution.fill(toolbar, SWT.NONE);

        assertThat(toolbar.getItemCount()).isEqualTo(1);
    }

    @Test
    public void should_update_refresh_toolItem_enablement() throws Exception {
        final Composite composite = realmWithDisplay.createComposite();
        contribution.fill(new ToolBar(composite, SWT.NONE), SWT.NONE);

        contribution.update();
        assertThat(contribution.isEnabled()).isFalse();

        doReturn(new StructuredSelection(aTask().havingFormMapping(aFormMapping().havingTargetForm(anExpression())).build())).when(selectionProvider)
                .getSelection();
        contribution.update();

        assertThat(contribution.isEnabled()).isTrue();
    }

}
