/**
 * Copyright (C) 2009-2017 Bonitasoft S.A.
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

package org.bonitasoft.studio.common.emf.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.Activator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer;
import org.eclipse.gmf.runtime.notation.Edge;

public class RemoveDanglingReferences {

    private final EObject root;

    public RemoveDanglingReferences(final EObject root) {
        this.root = root;
    }

    public void execute() {
        removeDanglingReferences(root);
        removeSequenceFlowWithoutSourceAndTarget(root);
    }

    private void removeSequenceFlowWithoutSourceAndTarget(EObject element) {
        if (element.eResource() != null && element.eResource().getResourceSet() != null) {
            final List<EObject> toRemove = new ArrayList<>();
            element.eAllContents().forEachRemaining(eObject -> {
                if (eObject instanceof Connection &&
                        (((Connection) eObject).getTarget() == null || ((Connection) eObject).getSource() == null)) {
                    toRemove.add(eObject);
                }
                if (eObject instanceof MessageFlow &&
                        (((MessageFlow) eObject).getTarget() == null || ((MessageFlow) eObject).getSource() == null)) {
                    toRemove.add(eObject);
                }
                if (eObject instanceof Edge &&
                        (((Edge) eObject).getTarget() == null || ((Edge) eObject).getSource() == null)) {
                    toRemove.add(eObject);
                }
            });
            toRemove.forEach(EcoreUtil::remove);
        }
    }

    /**
     * Remove all dangling references of all objects that are contained by the root element.
     * 
     * @param element
     *        the root element
     */
    public void removeDanglingReferences(final EObject element) {
        if (element.eResource() != null && element.eResource().getResourceSet() != null) {
            removeDanglingReferences(new DanglingReferencesDetector(element.eResource()));
        }
    }

    /**
     * Removes all dangling references from all the elements in the given resourceSet.
     * 
     * @param resourceSet
     *        The resourceSet which is to be cleaned of dangling references.
     */
    public void removeDanglingReferences(final ResourceSet resourceSet) {
        removeDanglingReferences(new DanglingReferencesDetector(resourceSet));
    }

    /**
     * Removes the dangling references of the given cross referencer.
     * 
     * @param referencer
     *        the referencer
     */
    private void removeDanglingReferences(CrossReferencer referencer) {
        for (final Map.Entry<EObject, Collection<Setting>> entry : referencer.entrySet()) {
            for (final EStructuralFeature.Setting value : entry.getValue()) {
                try {
                    EcoreUtil.remove(value, entry.getKey());
                    BonitaStudioLog.warning(
                            String.format("Dandling reference removed from process at import: %s", value.getEObject()),
                            Activator.PLUGIN_ID);
                } catch (final UnsupportedOperationException e) {
                    // we know some time the setting is unsettable, just ignore
                    // that cases
                } catch (final NullPointerException e) {
                    // we don't want to clean unresolved proxies in the model,
                    // so let's ignore Exceptions coming from that.
                }
            }
        }
    }

    /**
     * Specific {@link CrossReferencer} to detect dangling references.
     */
    private static class DanglingReferencesDetector extends EcoreUtil.CrossReferencer {

        /**
         * Generated serial version UID.
         */
        private static final long serialVersionUID = 616050158241084372L;

        /**
         * Creates an instance for the given resource.
         * 
         * @param resource
         *        the resource to cross reference.
         */
        public DanglingReferencesDetector(Resource resource) {
            super(resource);
            crossReference();
            done();
        }

        /**
         * Creates an instance for the given resource set.
         * 
         * @param resourceSet
         *        the resource set to cross reference.
         */
        public DanglingReferencesDetector(ResourceSet resourceSet) {
            super(resourceSet);
            crossReference();
            done();
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.emf.ecore.util.EcoreUtil.CrossReferencer#crossReference(org.eclipse.emf.ecore.EObject,
         *      org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject)
         */
        @Override
        protected boolean crossReference(final EObject eObject, final EReference eReference,
                final EObject crossReferencedEObject) {
            // A reference is dangling if the referenced object is not attached to a resource
            return crossReferencedEObject.eResource() == null;
        }
    }
}
