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

import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.junit.Rule;
import org.junit.Test;

public class DocumentDescriptionCompositeTest {

    @Rule
    public RealmWithDisplay realmWithDisplay = new RealmWithDisplay();

    @Test
    public void should_set_document_description_with_text_control() throws Exception {
        final Document document = aDocument().build();
        final DocumentDescriptionComposite control = new DocumentDescriptionComposite(realmWithDisplay.createComposite());
        control.bindControl(document, new EMFDataBindingContext());

        control.getText().setText("myDocument description");

        assertThat(document.getDocumentation()).isEqualTo("myDocument description");
    }

}
