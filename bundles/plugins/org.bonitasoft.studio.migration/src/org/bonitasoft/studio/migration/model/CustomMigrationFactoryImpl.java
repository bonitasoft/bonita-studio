package org.bonitasoft.studio.migration.model;

import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.impl.MigrationFactoryImpl;

public class CustomMigrationFactoryImpl extends MigrationFactoryImpl {

    @Override
    public Instance createInstance() {
        return new CustomInstanceImpl();
    }
    
}
