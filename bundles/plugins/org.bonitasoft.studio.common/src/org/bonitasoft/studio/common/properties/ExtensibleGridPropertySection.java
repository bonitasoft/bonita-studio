/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @author Aurelien Pupier
 * @author Mickael Istria
 * @author Baptiste Mesta
 * @author Romain Bioteau
 */
public abstract class ExtensibleGridPropertySection extends AbstractBonitaDescriptionSection
	implements ISelectionListener {

    private final List<IExtensibleGridPropertySectionContribution> contributions;
    Composite mainComposite;
    TabbedPropertySheetWidgetFactory widgetFactory;
    private EObject lastObject;
    private Composite contentsComposite;
    private IWorkbenchPart part;

    /**
     * This is needed when renaming the diagram.
     */
    private final ISelectionChangedListener selectionListener = new ISelectionChangedListener() {
	// TODO : find a better way to handle case of diagram rename
	@Override
	public void selectionChanged(final SelectionChangedEvent event) {
	    if (part != null) {
		if (part instanceof DiagramEditor) {
		    /*
		     * in order to avoid to polute log on each diagram switch between process/form
		     * we need to not called setInput if getEditingDomain throws a NPE
		     */
		    // TODO : find a more elegant way to handle this (override ghetEditingDomain in
		    // ProcessDiagramEditor/FormDiagramEditor?) or far better solve the above todo
		    try {
			((DiagramEditor) part).getEditingDomain();
		    } catch (final NullPointerException e) {
			return;
		    }
		    setInput(part, event.getSelection());
		}
	    }

	}
    };

    public ExtensibleGridPropertySection() {
	contributions = new ArrayList<IExtensibleGridPropertySectionContribution>();
    }

    @Override
    public void refresh() {
	super.refresh();
	BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

	    @Override
	    public void run() {
		if (!getEObject().equals(lastObject)) {
		    lastObject = getEObject();
		    refreshUI();
		}
		for (final IExtensibleGridPropertySectionContribution contrib : contributions) {
		    if (contrib.isRelevantFor(getEObject())) {
			contrib.refresh();
		    }
		}

	    }
	});
    }

    /**
     * Dispose all the Controls inside the mainComposite and then redraw the needed
     * ones.
     */
    public void refreshUI() {
	for (final IExtensibleGridPropertySectionContribution contrib : contributions) {
	    contrib.dispose();
	}
	for (final Control c : mainComposite.getChildren()) {
	    c.dispose();
	}
	contentsComposite = widgetFactory.createPlainComposite(mainComposite, SWT.NONE);
	contentsComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
	contentsComposite.setLayout(getLayout());
	for (final IExtensibleGridPropertySectionContribution contrib : contributions) {
	    if (contrib.isRelevantFor(getEObject())) {
		final Composite composite = widgetFactory.createComposite(contentsComposite);
		composite.setLayout(
			GridLayoutFactory.swtDefaults().spacing(LayoutConstants.getSpacing().x, 2).create());
		composite.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		if (contrib.getLabel() != null && contrib.getLabel().length() != 0) {
		    final Label label = widgetFactory.createLabel(composite, contrib.getLabel());
		    label.setLayoutData(GridDataFactory.swtDefaults().grab(false, false).create());
		}
		contrib.createControl(composite, widgetFactory, this);
	    }
	}
	/* Force to layout even if there is the same Control than previously */
	mainComposite.getParent().getParent().layout(true, true);
    }

    /**
     *
     * this layout is for contributions to place contribution label and contribution
     * widgets
     * 
     * @return the layout used for children
     */
    protected Layout getLayout() {
	final GridLayout layout = new GridLayout(2, false);
	layout.verticalSpacing = 10;
	layout.horizontalSpacing = 10;
	return layout;
    }

    @Override
    protected void createContent(final Composite parent) {
	widgetFactory = getWidgetFactory();
	mainComposite = widgetFactory.createPlainComposite(parent, SWT.NONE);
	mainComposite.setLayout(
		GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 5).extendedMargins(0, 20, 0, 0).create());
	mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
	addContributions();
    }

    /**
     * Add your contributions here Example : protected void addContributions() {
     * addContribution(new MyContribution1()); addContribution(new
     * MyContribution2()); }
     */
    protected abstract void addContributions();

    protected void addContribution(final IExtensibleGridPropertySectionContribution contrib) {
	contributions.add(contrib);
    }

    @Override
    protected void setEObject(final EObject object) {
	super.setEObject(object);
	for (final IExtensibleGridPropertySectionContribution contrib : contributions) {
	    if (contrib.isRelevantFor(object)) {
		contrib.setEObject(object);
	    }
	}
    }

    @Override
    protected void setEditingDomain(final TransactionalEditingDomain editingDomain) {
	super.setEditingDomain(editingDomain);
	for (final IExtensibleGridPropertySectionContribution contrib : contributions) {
	    contrib.setEditingDomain(editingDomain);
	}
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
	super.setInput(part, selection);
	for (final IExtensibleGridPropertySectionContribution contrib : contributions) {
	    if (contrib.isRelevantFor(getEObject())) {
		contrib.setSelection(selection);
	    }
	}
	if (part instanceof DiagramEditor) {
	    this.part = part;
	    ((DiagramEditor) part).getDiagramGraphicalViewer().removeSelectionChangedListener(selectionListener);
	    ((DiagramEditor) part).getDiagramGraphicalViewer().addSelectionChangedListener(selectionListener);
	}
    }

    protected void updatePropertyTabTitle(final ISelection selection, final Element elem) {
	final TabbedPropertyComposite tabbedPropertyComposite = (TabbedPropertyComposite) getTabbedPropertySheetPage()
		.getControl();
	final TabbedPropertyTitle title = tabbedPropertyComposite.getTitle();
	final Image image = getTabbedPropertySheetPage().getTitleImage(selection);
	title.setTitle(elem.getName(), image);
    }

    /**
     * Calls
     * {@link TabbedPropertySheetPage#selectionChanged(IWorkbenchPart, ISelection)}
     * 
     * @param part
     * @param selection
     */
    @Override
    public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
	if (getTabbedPropertySheetPage() != null) {
	    getTabbedPropertySheetPage().selectionChanged(part, new StructuredSelection());
	    getTabbedPropertySheetPage().selectionChanged(part, selection);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.properties.sections.
     * AbstractModelerPropertySection#dispose()
     */
    @Override
    public void dispose() {
	super.dispose();
	for (final IExtensibleGridPropertySectionContribution toDispose : contributions) {
	    toDispose.dispose();
	}
    }

    public static Control getLabelCompositeOf(final Composite composite) {
	Control[] children = null;
	if (composite.getParent() != null) {
	    children = composite.getParent().getChildren();
	    for (int i = 1; i < children.length; i++) {
		if (children[i].equals(composite)) {
		    return children[i - 1];
		}
	    }
	}
	return null;
    }

    /**
     * @return the contributions
     */
    public List<IExtensibleGridPropertySectionContribution> getContributions() {
	return Collections.unmodifiableList(contributions);
    }

}
