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
package org.bonitasoft.studio.migration.custom.migration.form;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ShapeFillColorCustomMigrationTest {

    @Mock
    private Metamodel metamodel;

    @Mock
    private Instance originalTaskInstance;

    @Mock
    private Instance newContractInstance, existingContractInstance;

    @Test
    public void should_remove_fillColor_of_shapes_contained_in_a_form_diagram() throws Exception {
        final ShapeFillColorCustomMigration migration = new ShapeFillColorCustomMigration();

        final Instance aShape = aShapeInstance();
        final Model model = modelWithFormDiagramWithShapes(aShape);
        migration.migrateAfter(model, metamodel);

        verify(aShape).set("fillColor", null);
    }

    @Test
    public void should_remove_lineColor_of_shapes_contained_in_a_form_diagram() throws Exception {
        final ShapeFillColorCustomMigration migration = new ShapeFillColorCustomMigration();

        final Instance aShape = aShapeInstance();
        final Model model = modelWithFormDiagramWithShapes(aShape);
        migration.migrateAfter(model, metamodel);

        verify(aShape).set("lineColor", null);
    }

    private Instance aShapeInstance() {
        final Instance aShape = mock(Instance.class);
        when(aShape.getContents()).thenReturn(eList());
        when(aShape.instanceOf(NotationPackage.Literals.SHAPE)).thenReturn(true);
        return aShape;
    }

    private Model modelWithFormDiagramWithShapes(final Instance... shapeInstances) {
        final Model model = mock(Model.class);
        doReturn(eList(formDiagramWithShapes(shapeInstances))).when(model).getAllInstances(NotationPackage.Literals.DIAGRAM);
        return model;
    }

    private Instance formDiagramWithShapes(final Instance... shapeInstances) {
        final Instance diagram = mock(Instance.class);
        when(diagram.get("type")).thenReturn("Form");
        when(diagram.getContents()).thenReturn(eList(shapeInstances));
        return diagram;
    }

    private BasicEList<Instance> eList(final Instance... instances) {
        final BasicEList<Instance> eList = new BasicEList<Instance>();
        for (final Instance d : instances) {
            eList.add(d);
        }
        return eList;
    }
}
