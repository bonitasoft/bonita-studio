/**
 * Copyright (C) 2013-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.document.ui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.expression.editor.filter.AvailableExpressionTypeFilter;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.swt.widgets.Composite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * @author Florine Boudin
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(WizardPageSupport.class)
public class DocumentWizardPageTest {


    private DocumentWizardPage documentWizardPageUnderTest;

    @Mock
    private EMFDataBindingContext emfDataBindingContext;

    @Mock
    private Composite detailsComposite;

    @Before
    public void setUp() {

        final Document document = ProcessFactory.eINSTANCE.createDocument();
        final Pool pool = ProcessFactory.eINSTANCE.createPool();

        documentWizardPageUnderTest = spy(new DocumentWizardPage(pool, document));
        PowerMockito.mockStatic(WizardPageSupport.class);
        final WizardPageSupport support = mock(WizardPageSupport.class);
        when(WizardPageSupport.create(documentWizardPageUnderTest, emfDataBindingContext)).thenReturn(support);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getConstantTypeOnlyExpressionViewerFilter_should_return_only_CONSTANT_TYPE() throws Exception {

        final AvailableExpressionTypeFilter filterType = documentWizardPageUnderTest.getConstantTypeOnlyExpressionViewerFilter();
        assertThat(filterType).isNotNull();
        assertThat(filterType.getContentTypes()).containsOnly(ExpressionConstants.CONSTANT_TYPE);
    }
}
