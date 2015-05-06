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
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.swt.widgets.Text;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Florine Boudin
 */
@RunWith(MockitoJUnitRunner.class)
public class DocumentWizardPageTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_bindDocumentName_bind_documentText_and_document_name() throws Exception {
        final EMFDataBindingContext dbc = new EMFDataBindingContext();
        final Document document = aDocument().build();
        final DocumentWizardPage documentWizardPageUnderTest = new DocumentWizardPage(aPool().build(), document);
        final Text text = documentWizardPageUnderTest.createDocumentNameField(realmWithDisplay.createComposite(), dbc);

        assertThat(document.getName()).isEmpty();

        text.setText("myDocumentName");

        assertThat(document.getName()).isEqualTo("myDocumentName");

        text.setText("my Document Name");

        assertThat(document.getName()).isEqualTo("myDocumentName");

        assertThat(dbc.getValidationStatusProviders()).hasSize(1);

        final ValidationStatusProvider validationStatus = (ValidationStatusProvider) dbc.getValidationStatusProviders().get(0);
        final IObservableValue iObservableValue = validationStatus.getValidationStatus();
        final IStatus status = (IStatus) iObservableValue.getValue();

        assertThat(status.isOK()).isFalse();
        assertThat(status.getMessage()).isEqualTo(Messages.bind(Messages.nameCantHaveAWhitespace, "my Document Name"));
    }

}
