package org.bonitasoft.studio.application.property.tester;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.repository.GroovyRepositoryStore;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IPackageFragment;


public class GroovySrcPropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        IResource adapter = ((IAdaptable) receiver).getAdapter(IResource.class);
        IPackageFragment packageFragment = ((IAdaptable) receiver).getAdapter(IPackageFragment.class);
        IFolder groovySrcFolder = RepositoryManager.getInstance().getRepositoryStore(GroovyRepositoryStore.class)
                .getResource();
        return Objects.equals(
                groovySrcFolder,
                adapter)
                || (packageFragment != null
                        && groovySrcFolder.getLocation().isPrefixOf(packageFragment.getResource().getLocation()));
    }

}
