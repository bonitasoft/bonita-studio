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
package org.bonitasoft.studio.businessobject.ui.wizard.control;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.swt.AbstractSWTTestCase;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.engine.bdm.model.BusinessObjectModel;
import org.bonitasoft.engine.bdm.model.UniqueConstraint;


/**
 * @author Romain Bioteau
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UniqueConstraintTabItemControlTest extends AbstractSWTTestCase {

    @Mock
    private IViewerObservableValue viewerObservableValue;
    @Mock
    private IObservableList fieldsList;

    private BusinessObjectModel bom;

    private UniqueConstraintTabItemControl uniqueConstraintTabItemControl;

    private DataBindingContext ctx;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        final Composite composite = createDisplayAndRealm();
        final TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
        bom = new BusinessObjectModel();
        when(viewerObservableValue.getValue()).thenReturn("");
        ctx = new DataBindingContext(Realm.getDefault());
        when(viewerObservableValue.getRealm()).thenReturn(Realm.getDefault());
        uniqueConstraintTabItemControl = new UniqueConstraintTabItemControl(tabFolder, ctx, viewerObservableValue, fieldsList, bom);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        dispose();
    }

    @Test
    public void should_generateConstraintName_generate_unique_names() throws Exception {
        final String constraintName = uniqueConstraintTabItemControl.generateConstraintName();
        final UniqueConstraint uniqueConstraint = new UniqueConstraint();
        uniqueConstraint.setName(constraintName);
        final BusinessObject bo = new BusinessObject();
        bo.addUniqueConstraint(uniqueConstraint);
        bom.addBusinessObject(bo);
        final String constraintName2 = uniqueConstraintTabItemControl.generateConstraintName();
        assertThat(constraintName).isNotEmpty().isNotEqualTo(constraintName2);
    }
}
