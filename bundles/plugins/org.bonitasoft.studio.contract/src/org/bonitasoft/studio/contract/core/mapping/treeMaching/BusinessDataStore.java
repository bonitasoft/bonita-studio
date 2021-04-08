package org.bonitasoft.studio.contract.core.mapping.treeMaching;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.engine.bdm.model.BusinessObject;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Pool;

public class BusinessDataStore {

    private Pool pool;
    private RepositoryAccessor repositoryAccessor;

    public BusinessDataStore(Pool pool, RepositoryAccessor repositoryAccessor) {
        this.pool = pool;
        this.repositoryAccessor = repositoryAccessor;
    }

    public List<BusinessObjectData> getBusinessData() {
        return pool.getData().stream()
                .filter(BusinessObjectData.class::isInstance)
                .map(BusinessObjectData.class::cast)
                .collect(Collectors.toList());
    }

    public Optional<BusinessObjectData> find(String name) {
        return getBusinessData().stream()
                .filter(data -> Objects.equals(data.getName(), name))
                .findFirst();
    }

    public Optional<BusinessObject> getBusinessObject(String className) {
        return repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class)
                .getBusinessObjectByQualifiedName(className);
    }
}
