/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.internal.ui.viewsupport.JavaViewerFilter;
import org.eclipse.jdt.internal.ui.viewsupport.ResourceToItemsMapper;
import org.eclipse.jdt.internal.ui.viewsupport.ResourceToItemsMapper.IContentViewerAccessor;
import org.eclipse.jdt.ui.IWorkingCopyProvider;
import org.eclipse.jdt.ui.ProblemsLabelDecorator.ProblemsLabelChangedEvent;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.navigator.CommonViewer;


public class ProblemTreeViewer extends CommonViewer implements IContentViewerAccessor {

    protected ResourceToItemsMapper fResourceToItemsMapper;


    /*
     * @see TreeViewer#TreeViewer(Composite, int)
     */
    public ProblemTreeViewer(String id, Composite parent, int style) {
        super(id, parent, style);
        initMapper();
    }


    @Override
    public void doUpdateItem(Widget item) {
        doUpdateItem(item, item.getData(), true);
    }

    private void initMapper() {
        fResourceToItemsMapper = new ResourceToItemsMapper(this);
    }

    /*
     * @see StructuredViewer#mapElement(Object, Widget)
     */
    @Override
    protected void mapElement(Object element, Widget item) {
        super.mapElement(element, item);
        if (item instanceof Item) {
            fResourceToItemsMapper.addToMap(element, (Item) item);
        }
    }

    /*
     * @see StructuredViewer#unmapElement(Object, Widget)
     */
    @Override
    protected void unmapElement(Object element, Widget item) {
        if (item instanceof Item) {
            fResourceToItemsMapper.removeFromMap(element, (Item) item);
        }
        super.unmapElement(element, item);
    }

    /*
     * @see StructuredViewer#unmapAllElements()
     */
    @Override
    protected void unmapAllElements() {
        fResourceToItemsMapper.clearMap();
        super.unmapAllElements();
    }

    // ---------------- filter sessions ----------------------------

    @Override
    public void addFilter(ViewerFilter filter) {
        if (filter instanceof JavaViewerFilter) {
            ((JavaViewerFilter) filter).filteringStart();
        }
        super.addFilter(filter);
    }

    @Override
    public void removeFilter(ViewerFilter filter) {
        super.removeFilter(filter);
        if (filter instanceof JavaViewerFilter) {
            ((JavaViewerFilter) filter).filteringEnd();
        }
    }

    @Override
    public void setFilters(ViewerFilter... filters) {
        ViewerFilter[] oldFilters = getFilters();
        for (int i = 0; i < filters.length; i++) {
            ViewerFilter curr = filters[i];
            if (curr instanceof JavaViewerFilter && !findAndRemove(oldFilters, curr)) {
                ((JavaViewerFilter) curr).filteringStart();
            }
        }
        endFilterSessions(oldFilters);
        super.setFilters(filters);
    }

    @Override
    public void resetFilters() {
        endFilterSessions(getFilters());
        super.resetFilters();
    }

    private boolean findAndRemove(ViewerFilter[] filters, ViewerFilter filter) {
        for (int i = 0; i < filters.length; i++) {
            if (filters[i] == filter) {
                filters[i] = null;
                return true;
            }
        }
        return false;
    }

    private void endFilterSessions(ViewerFilter[] filters) {
        for (int i = 0; i < filters.length; i++) {
            ViewerFilter curr = filters[i];
            if (curr instanceof JavaViewerFilter) {
                ((JavaViewerFilter) curr).filteringEnd();
            }
        }
    }

    @Override
    protected void handleDispose(DisposeEvent event) {
        endFilterSessions(getFilters());
        super.handleDispose(event);
    }

    /*
     * @see ContentViewer#handleLabelProviderChanged(LabelProviderChangedEvent)
     */
    @Override
    protected void handleLabelProviderChanged(LabelProviderChangedEvent event) {
        if (event instanceof ProblemsLabelChangedEvent) {
            ProblemsLabelChangedEvent e = (ProblemsLabelChangedEvent) event;
            if (!e.isMarkerChange() && canIgnoreChangesFromAnnotionModel()) {
                return;
            }
        }
        Object[] changed = addAditionalProblemParents(event.getElements());

        if (changed != null && !fResourceToItemsMapper.isEmpty()) {
            ArrayList<Object> others = new ArrayList<>();
            for (int i = 0; i < changed.length; i++) {
                Object curr = changed[i];
                if (curr instanceof IResource) {
                    fResourceToItemsMapper.resourceChanged((IResource) curr);
                } else {
                    others.add(curr);
                }
            }
            if (others.isEmpty()) {
                return;
            }
            event = new LabelProviderChangedEvent((IBaseLabelProvider) event.getSource(), others.toArray());
        } else {
            // we have modified the list of changed elements via add additional parents.
            if (event.getElements() != changed)
                event = new LabelProviderChangedEvent((IBaseLabelProvider) event.getSource(), changed);
        }
        super.handleLabelProviderChanged(event);
    }

    /**
     * Answers whether this viewer can ignore label provider changes resulting from
     * marker changes in annotation models
     * 
     * @return return <code>true</code> if annotation model marker changes can be ignored
     */
    private boolean canIgnoreChangesFromAnnotionModel() {
        Object contentProvider = getContentProvider();
        return contentProvider instanceof IWorkingCopyProvider
                && !((IWorkingCopyProvider) contentProvider).providesWorkingCopies();
    }

    /**
     * Decides if {@link #isExpandable(Object)} should also test filters. The default behaviour is to
     * do this only for IMembers. Implementors can replace this behaviour.
     * 
     * @param parent the given element
     * @return returns if if {@link #isExpandable(Object)} should also test filters for the given element.
     */
    protected boolean evaluateExpandableWithFilters(Object parent) {
        return parent instanceof IMember;
    }

    @Override
    public boolean isExpandable(Object parent) {
        if (hasFilters() && evaluateExpandableWithFilters(parent)) {
            // workaround for 65762
            return hasFilteredChildren(parent);
        }
        return super.isExpandable(parent);
    }

    /**
     * Public method to test if a element has any children that passed the filters
     * 
     * @param parent the element to test
     * @return return <code>true</code> if the element has at least a child that passed the filters
     */
    public final boolean hasFilteredChildren(Object parent) {
        Object[] rawChildren = getRawChildren(parent);
        return containsNonFiltered(rawChildren, parent);
    }

    @Override
    protected final Object[] getFilteredChildren(Object parent) {
        return filter(getRawChildren(parent), parent);
    }

    private Object[] filter(Object[] elements, Object parent) {
        if (!hasFilters() || elements.length == 0) {
            return elements;
        }
        List<Object> list = new ArrayList<>(elements.length);
        ViewerFilter[] filters = getFilters();
        for (int i = 0; i < elements.length; i++) {
            Object object = elements[i];
            if (!isFiltered(object, parent, filters)) {
                list.add(object);
            }
        }
        return list.toArray();
    }

    private boolean containsNonFiltered(Object[] elements, Object parent) {
        if (elements.length == 0) {
            return false;
        }
        if (!hasFilters()) {
            return true;
        }
        ViewerFilter[] filters = getFilters();
        for (int i = 0; i < elements.length; i++) {
            Object object = elements[i];
            if (!isFiltered(object, parent, filters)) {
                return true;
            }
        }
        return false;
    }

    /**
     * All element filter tests must go through this method.
     * Can be overridden by subclasses.
     *
     * @param object the object to filter
     * @param parent the parent
     * @param filters the filters to apply
     * @return true if the element is filtered
     */
    protected boolean isFiltered(Object object, Object parent, ViewerFilter[] filters) {
        for (int i = 0; i < filters.length; i++) {
            ViewerFilter filter = filters[i];
            if (!filter.select(this, parent, object))
                return true;
        }
        return false;
    }

    @Override
    protected final Object[] filter(Object[] elements) {
        return filter(elements, getRoot());
    }

    protected Object[] addAditionalProblemParents(Object[] elements) {
        return elements;
    }

    /**
     * Public method to test if a element is filtered by the views active filters
     * 
     * @param object the element to test for
     * @param parent the parent element
     * @return return <code>true</code> if the element is filtered
     */
    public boolean isFiltered(Object object, Object parent) {
        return isFiltered(object, parent, getFilters());
    }
}
