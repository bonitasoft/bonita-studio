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

import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Iterables.filter;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

import com.google.common.base.Predicate;

public class EditPartResolver {

    private final Map<EObject, IGraphicalEditPart> cache = new WeakHashMap<EObject, IGraphicalEditPart>();

    public IGraphicalEditPart findEditPart(final DiagramEditPart diagramEditPart, final EObject semanticModelElement) throws EditPartNotFoundException {
        if (semanticModelElement == null) {
            throw new EditPartNotFoundException();
        }
        if (cache.containsKey(semanticModelElement)) {
            return cache.get(semanticModelElement);
        }
        final IGraphicalEditPart result = findEditPartRecursively(diagramEditPart, semanticModelElement);
        if (result == null) {
            throw new EditPartNotFoundException();
        }
        cache.put(semanticModelElement, result);
        return result;
    }

    public void dispose() {
        cache.clear();
    }

    private boolean semanticElementMatches(final EditPart editPart, final EObject semanticModelElement) {
        if (editPart instanceof IGraphicalEditPart) {
            final EObject editPartModelElement = ((IGraphicalEditPart) editPart).resolveSemanticElement();
            if (isEqualTo(semanticModelElement).apply(editPartModelElement)) {
                return true;
            } else if (not(
                    or(instanceOf(FlowElement.class),
                            instanceOf(BoundaryEvent.class),
                            instanceOf(SubProcessEvent.class),
                            instanceOf(Lane.class),
                            instanceOf(TextAnnotation.class),
                            instanceOf(TextAnnotationAttachment.class),
                            instanceOf(Connection.class),
                            instanceOf(MessageFlow.class),
                            instanceOf(Pool.class)))
                    .apply(semanticModelElement)) {
                final Connection connection = ModelHelper.getFirstContainerOfType(semanticModelElement, Connection.class);
                final FlowElement flowElement = ModelHelper.getFirstContainerOfType(semanticModelElement, FlowElement.class);
                if (connection != null) {
                    if (semanticElementMatches(editPart, connection)) {
                        return true;
                    }
                } else if (flowElement != null) {
                    if (semanticElementMatches(editPart, flowElement)) {
                        return true;
                    }
                } else {
                    if (semanticElementMatches(editPart, ModelHelper.getFirstContainerOfType(semanticModelElement, Pool.class))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Predicate<EObject> isEqualTo(final EObject semanticModelElement) {
        return new Predicate<EObject>() {

            @Override
            public boolean apply(final EObject input) {
                return Objects.equals(semanticModelElement, input);
            }
        };
    }

    private IGraphicalEditPart findEditPartRecursively(final EditPart editPart, final EObject semanticModelElement) {
        if (semanticElementMatches(editPart, semanticModelElement)) {
            return (IGraphicalEditPart) editPart;
        }
        for (final Object ep : filter(((IGraphicalEditPart) editPart).getSourceConnections(), instanceOf(IGraphicalEditPart.class))) {
            final IGraphicalEditPart result = findEditPartRecursively((EditPart) ep, semanticModelElement);
            if (result != null) {
                return result;
            }
        }
        for (final Object ep : filter(((IGraphicalEditPart) editPart).getTargetConnections(), instanceOf(IGraphicalEditPart.class))) {
            final IGraphicalEditPart result = findEditPartRecursively((EditPart) ep, semanticModelElement);
            if (result != null) {
                return result;
            }
        }
        for (final Object ep : filter(editPart.getChildren(), instanceOf(IGraphicalEditPart.class))) {
            final IGraphicalEditPart result = findEditPartRecursively((EditPart) ep, semanticModelElement);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
