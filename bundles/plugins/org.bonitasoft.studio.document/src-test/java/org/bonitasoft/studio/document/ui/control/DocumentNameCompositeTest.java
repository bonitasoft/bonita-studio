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
package org.bonitasoft.studio.document.ui.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.model.process.builders.DocumentBuilder.aDocument;
import static org.bonitasoft.studio.model.process.builders.PoolBuilder.aPool;

import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.junit.Rule;
import org.junit.Test;

public class DocumentNameCompositeTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_set_document_name_with_text_control() throws Exception {
        final Document document = aDocument().build();
        final DocumentNameComposite control = new DocumentNameComposite(realmWithDisplay.createComposite());
        control.bindControl(document, aPool().build(), new EMFDataBindingContext());

        control.getText().setText("myDocument");

        assertThat(document.getName()).isEqualTo("myDocument");
    }

    @Test
    public void should_not_set_document_name_if_text_is_not_a_valid_groovy_field_reference() throws Exception {
        final Document document = aDocument().build();
        final DocumentNameComposite control = new DocumentNameComposite(realmWithDisplay.createComposite());
        control.bindControl(document, aPool().build(), new EMFDataBindingContext());

        control.getText().setText("a invalid ref");

        assertThat(document.getName()).isNullOrEmpty();
    }

    @Test
    public void should_not_set_document_name_if_text_is_longer_then_50_characters() throws Exception {
        final Document document = aDocument().build();
        final DocumentNameComposite control = new DocumentNameComposite(realmWithDisplay.createComposite());
        control.bindControl(document, aPool().build(), new EMFDataBindingContext());

        control.getText().setText("imtoooooooooooooooooooooooooolooooooooooooooonggggggggggggggggg");

        assertThat(document.getName()).isNullOrEmpty();
    }

    @Test
    public void should_not_set_document_name_if_text_already_exists() throws Exception {
        final Document document = aDocument().build();
        final DocumentNameComposite control = new DocumentNameComposite(realmWithDisplay.createComposite());
        control.bindControl(document, aPool().havingDocuments(aDocument().withName("doc1")).build(), new EMFDataBindingContext());

        control.getText().setText("doc1");

        assertThat(document.getName()).isNullOrEmpty();
    }

    @Test
    public void should_edit_document_name_if_text_already_exists() throws Exception {
        final Document document = aDocument().withName("doc1").build();
        final DocumentNameComposite control = new DocumentNameComposite(realmWithDisplay.createComposite());
        control.bindControl(document, aPool().havingDocuments(aDocument().withName("doc1")).build(), new EMFDataBindingContext());

        control.getText().setText("doc");
        control.getText().setText("doc1");

        assertThat(document.getName()).isEqualTo("doc1");
    }

}
