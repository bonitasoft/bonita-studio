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
package org.bonitasoft.studio.contract.ui.property.constraint.edit.editor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.groovy.ui.viewer.BonitaGroovyEditor;
import org.bonitasoft.studio.groovy.ui.viewer.GroovySourceViewerFactory;
import org.bonitasoft.studio.groovy.ui.viewer.GroovyViewer;
import org.bonitasoft.studio.model.process.Contract;
import org.bonitasoft.studio.model.process.ContractConstraint;
import org.bonitasoft.studio.model.process.ContractInput;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.codehaus.groovy.eclipse.editor.GroovyEditor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;

/**
 * @author Romain Bioteau
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractConstraintExpressionWizardPageTest {

    private Composite composite;
    private ContractConstraintExpressionWizardPage wizardPage;
    private ContractConstraint constraint;
    @Mock
    private GroovyViewer groovyViewer;
    @Mock
    private BundleContext bundleContext;
    @Mock
    private GroovySourceViewerFactory groovySourceViewerFactory;
    @Mock
    private ContractConstraintEditorFactory editorFactory;
    @Mock
    private ContractConstraintEditor editor;
    @Mock
    private WebBrowserFactory browserFactory;

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        constraint = ProcessFactory.eINSTANCE.createContractConstraint();
        constraint.setExpression("");
        final Contract c = ProcessFactory.eINSTANCE.createContract();
        c.getConstraints().add(constraint);
        final ContractInput name = ProcessFactory.eINSTANCE.createContractInput();
        name.setName("name");
        c.getInputs().add(name);
        composite = realm.createComposite();
        final SourceViewer sourceViewer = new SourceViewer(composite, null, SWT.NONE);
        sourceViewer.setDocument(new Document());
        when(groovyViewer.getSourceViewer()).thenReturn(sourceViewer);
        when(editorFactory.newInstance()).thenReturn(editor);
        when(groovySourceViewerFactory.createSourceViewer(any(Composite.class), any(BonitaGroovyEditor.class))).thenReturn(groovyViewer);
        wizardPage = new ContractConstraintExpressionWizardPage(constraint,
                c.getInputs(),
                groovySourceViewerFactory,
                editorFactory,
                browserFactory);
        final ContractConstraintExpressionWizard wizard = new ContractConstraintExpressionWizard(constraint, null);
        final WizardDialog wizardContainer = new WizardDialog(composite.getShell(), wizard) {

            @Override
            protected Control createContents(final Composite parent) {
                return null;
            }
        };
        wizardContainer.create();
        assertThat(wizardContainer.getShell()).isNotNull();
        wizard.setContainer(wizardContainer);
        wizardPage.setWizard(wizard);
    }

    @Test
    public void should_createControl_set_page_control() throws Exception {
        wizardPage.createControl(composite);
        assertThat(wizardPage.getControl()).isNotNull();
    }

    @Test
    public void should_dispose_dispose_viewer() throws Exception {
        wizardPage.createControl(composite);
        wizardPage.dispose();
        verify(groovyViewer).dispose();
    }

    @Test
    public void should_documentChanged_set_expression_value() throws Exception {
        wizardPage.createControl(composite);
        final Document document = new Document("name.length() > 25");
        final DocumentEvent event = new DocumentEvent(document, 0, document.getLength(), document.get());
        wizardPage.documentChanged(event);
        assertThat(constraint.getExpression()).isEqualTo(document.get());
    }

    @Test
    public void should_performHelp_open_browser_with_groovy_getting_start_url() throws Exception {
        wizardPage.createControl(composite);
        wizardPage.performHelp();
        verify(browserFactory).openExteranlBrowser(ContractConstraintExpressionWizardPage.GROOVY_BASICS_URL);
    }
}
