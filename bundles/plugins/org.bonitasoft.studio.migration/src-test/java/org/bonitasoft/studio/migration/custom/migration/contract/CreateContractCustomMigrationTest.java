package org.bonitasoft.studio.migration.custom.migration.contract;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CreateContractCustomMigrationTest {

    private CreateContractCustomMigration customMigration;

    @Mock
    private Model model;

    @Mock
    private Metamodel metamodel;

    @Mock
    private Instance originalTaskInstance;

    @Mock
    private Instance newContractInstance;

    @Before
    public void setUp() throws Exception {
        customMigration = new CreateContractCustomMigration();
        final BasicEList<Instance> uniqueEList = new BasicEList<Instance>();
        uniqueEList.add(originalTaskInstance);
        when(model.getAllInstances("process.Task")).thenReturn(uniqueEList);
        when(model.newInstance("process.Contract")).thenReturn(newContractInstance);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_migrateAfter_add_an_empty_contract_to_a_task() throws Exception {
        customMigration.migrateAfter(model, metamodel);
        verify(originalTaskInstance).set("contract", newContractInstance);
    }

    @Test
    public void should_migrateAfter_NOTadd_an_empty_contract_to_a_task() throws Exception {
        when(originalTaskInstance.get("contract")).thenReturn(newContractInstance);
        customMigration.migrateAfter(model, metamodel);
        verify(model, never()).newInstance("process.Contract");
    }

}
