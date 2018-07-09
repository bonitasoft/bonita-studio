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
package org.bonitasoft.studio.common.gmf.tools.tree.selection;

import static com.google.common.collect.Iterables.tryFind;
import static org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.DefaultTabbedPropertyProvider.defaultProvider;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.ITabbedPropertySelectionProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.ActorTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.ConnectorTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.ContractTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.DataTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.DocumentTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.FormMappingTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.KPITabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.OperationTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.ParameterTabbedPropertyProvider;
import org.bonitasoft.studio.common.gmf.tools.tree.selection.provider.process.SearchIndexTabbedPropertyProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorReference;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;

public class TabbedPropertySelectionProviderRegistry {

    private static Set<ITabbedPropertySelectionProvider> registry;
    static {
        registry = new HashSet<ITabbedPropertySelectionProvider>();
        registry.add(new ActorTabbedPropertyProvider());
        registry.add(new ConnectorTabbedPropertyProvider());
        registry.add(new ContractTabbedPropertyProvider());
        registry.add(new DataTabbedPropertyProvider());
        registry.add(new DocumentTabbedPropertyProvider());
        registry.add(new FormMappingTabbedPropertyProvider());
        registry.add(new KPITabbedPropertyProvider());
        registry.add(new OperationTabbedPropertyProvider());
        registry.add(new ParameterTabbedPropertyProvider());
        registry.add(new SearchIndexTabbedPropertyProvider());
    }

    public ITabbedPropertySelectionProvider findSelectionProvider(final EObject element, final IEditorReference activeEditor,
            final ITabbedPropertySelectionProvider defaultProvider) {
        final Optional<ITabbedPropertySelectionProvider> provider = tryFind(registry, appliesTo(element, activeEditor));
        if (provider.isPresent()) {
            return provider.get();
        }
        return defaultProvider != null ? defaultProvider : defaultProvider(activeEditor);
    }

    private Predicate<ITabbedPropertySelectionProvider> appliesTo(final EObject element, final IEditorReference activeEditor) {
        return new Predicate<ITabbedPropertySelectionProvider>() {

            @Override
            public boolean apply(final ITabbedPropertySelectionProvider input) {
                return input.appliesTo(element, activeEditor);
            }
        };
    }

}
