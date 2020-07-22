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
package org.bonitasoft.studio.businessobject.ui.wizard.editingsupport;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.field.FieldType;
import org.bonitasoft.studio.businessobject.core.difflog.NullDiffLogger;
import org.bonitasoft.studio.businessobject.ui.wizard.provider.FieldTypeLabelProvider;
import org.bonitasoft.studio.swt.rules.RealmWithDisplay;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.viewers.TableViewer;
import org.junit.Rule;
import org.junit.Test;

public class FieldTypeEditingSupportTest {

    @Rule
    public RealmWithDisplay realm = new RealmWithDisplay();

    @Test
    public void should_sort_alphabeticaly_attribute_types() throws Exception {
        BusinessObjectModel bom = new BusinessObjectModel();
        BusinessObject B = new BusinessObject("com.company.B");
        bom.addBusinessObject(B);
        BusinessObject A = new BusinessObject("com.company.A");
        bom.addBusinessObject(A);

        final FieldTypeEditingSupport editingSupport = createEditingSupport(bom);

        final List<Object> input = editingSupport.getInput(new FieldTypeLabelProvider());

        assertThat(input).containsExactly(FieldType.BOOLEAN,
                FieldType.LOCALDATE,
                FieldType.LOCALDATETIME,
                FieldType.OFFSETDATETIME,
                FieldType.DOUBLE,
                FieldType.FLOAT,
                FieldType.INTEGER,
                FieldType.LONG,
                FieldType.STRING,
                FieldType.TEXT,
                FieldType.DATE,
                A,
                B);
    }

    private FieldTypeEditingSupport createEditingSupport(BusinessObjectModel bom) {
        return new FieldTypeEditingSupport(new TableViewer(realm.createComposite()), bom,
                new WritableList(), new WritableValue(), new WritableValue(), new NullDiffLogger());
    }

}
