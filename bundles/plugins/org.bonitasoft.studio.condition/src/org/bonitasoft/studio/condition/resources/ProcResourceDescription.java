/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.condition.resources;

import static com.google.common.collect.Lists.newArrayList;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.util.IResourceScopeCache;

/**
 * @author Romain Bioteau
 */
public class ProcResourceDescription extends DefaultResourceDescription
        implements IResourceDescription {

    private final DefaultResourceDescriptionStrategy strategy;

    public ProcResourceDescription(final Resource resource,
            final DefaultResourceDescriptionStrategy strategy,
            final IResourceScopeCache cache) {
        super(resource, strategy, cache);
        this.strategy = strategy;
        this.strategy.setQualifiedNameProvider(new ProcQualifiedNameProvider());
    }

    @Override
    protected List<IEObjectDescription> computeExportedObjects() {
        if (!getResource().isLoaded()) {
            try {
                getResource().load(null);
            } catch (final IOException e) {
                return Collections.<IEObjectDescription> emptyList();
            }
        }
        final List<IEObjectDescription> exportedEObjects = newArrayList();
        final IAcceptor<IEObjectDescription> acceptor = new IAcceptor<IEObjectDescription>() {

            @Override
            public void accept(final IEObjectDescription eObjectDescription) {
                exportedEObjects.add(eObjectDescription);
            }
        };
        final TreeIterator<EObject> allProperContents = EcoreUtil.getAllProperContents(getResource(), false);
        while (allProperContents.hasNext()) {
            final EObject content = allProperContents.next();
            if (!strategy.createEObjectDescriptions(content, acceptor)) {
                allProperContents.prune();
            }
        }
        return exportedEObjects;
    }
}

