package org.bonitasoft.studio.contract.ui.property.input.edit;
// /**
// * Copyright (C) 2014 BonitaSoft S.A.
// * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program. If not, see <http://www.gnu.org/licenses/>.
// */
// package org.bonitasoft.studio.contract.ui.property.edit;
//
// import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Mockito.any;
// import static org.mockito.Mockito.never;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
//
// import org.bonitasoft.studio.model.process.Contract;
// import org.bonitasoft.studio.model.process.ContractInput;
// import org.bonitasoft.studio.model.process.ContractInputMapping;
// import org.bonitasoft.studio.model.process.Data;
// import org.bonitasoft.studio.model.process.ProcessFactory;
// import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
// import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
// import org.eclipse.jface.viewers.TableViewer;
// import org.eclipse.ui.forms.IMessageManager;
// import org.eclipse.ui.views.properties.IPropertySource;
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.Mock;
// import org.mockito.junit.MockitoJUnitRunner;
//
//
// /**
// * @author Romain Bioteau
// */
// @RunWith(MockitoJUnitRunner.class)
// public class InputMappingPropertyEditingSupportTest {
//
// @Mock
// private TableViewer viewer;
//
// private InputMappingPropertyEditingSupport propertyEditingSupport;
//
// @Mock
// private AdapterFactoryContentProvider propertySourceProvider;
//
// @Mock
// private AdapterFactoryLabelProvider adapterFactoryLabelProvider;
//
// @Mock
// private IMessageManager iMessageManager;
//
// @Mock
// private IPropertySource propertySource;
//
// /**
// * @throws java.lang.Exception
// */
// @Before
// public void setUp() throws Exception {
// when(propertySourceProvider.getPropertySource(any(ContractInputMapping.class))).thenReturn(propertySource);
// propertyEditingSupport = new InputMappingPropertyEditingSupport(propertySourceProvider, viewer);
// }
//
// /**
// * @throws java.lang.Exception
// */
// @After
// public void tearDown() throws Exception {
// }
//
// @Test
// public void should_canEdit_returns_true() throws Exception {
// final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
// assertThat(propertyEditingSupport.canEdit(input)).isTrue();
// }
//
// @Test
// public void should_canEdit_returns_false() throws Exception {
// final Contract contract = ProcessFactory.eINSTANCE.createContract();
// assertThat(propertyEditingSupport.canEdit(contract)).isFalse();
// }
//
// @Test
// public void should_getValue_returns_mapping_label() throws Exception {
// final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
// final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
// final Data data = ProcessFactory.eINSTANCE.createData();
// data.setName("myData");
// mapping.setData(data);
// input.setMapping(mapping);
// assertThat(propertyEditingSupport.getValue(input)).isEqualTo("myData");
// }
//
// @Test
// public void should_getValue_returns_mapping_null() throws Exception {
// final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
// input.setMapping(null);
// assertThat(propertyEditingSupport.getValue(input)).isNull();
// assertThat(propertyEditingSupport.getValue(null)).isNull();
// }
//
// @Test
// public void should_setValue_update_viewer() throws Exception {
// final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
// final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
// final Data data = ProcessFactory.eINSTANCE.createData();
// data.setName("myData");
// mapping.setData(data);
//
// propertyEditingSupport.setValue(input, mapping);
// verify(propertySource).setPropertyValue("data", data);
// verify(viewer).update(input, null);
// }
//
// @Test
// public void should_setValue_doNothing() throws Exception {
// final ContractInput input = ProcessFactory.eINSTANCE.createContractInput();
// final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
// final Data data = ProcessFactory.eINSTANCE.createData();
// data.setName("myData");
// mapping.setData(data);
//
// propertyEditingSupport.setValue(null, mapping);
// verify(propertySource, never()).setPropertyValue("data", data);
// verify(viewer, never()).update(input, null);
//
// propertyEditingSupport.setValue(input, null);
// verify(propertySource, never()).setPropertyValue("data", data);
// verify(viewer).update(input, null);
// }
//
//}
