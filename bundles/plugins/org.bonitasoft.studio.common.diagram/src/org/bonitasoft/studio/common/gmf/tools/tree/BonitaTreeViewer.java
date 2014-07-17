/**
 * Copyright (C) 2011-2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools.tree;

import java.util.Collection;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.RootTreeEditPart;
import org.eclipse.gef.ui.parts.AbstractEditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

/**
 * @author Romain Bioteau
 *
 */
public abstract class BonitaTreeViewer extends AbstractEditPartViewer implements ISelectionProvider,SWTBotConstants{


    private static final class SearchPatternFilter extends PatternFilter {
		@Override
		protected boolean isLeafMatch(final org.eclipse.jface.viewers.Viewer viewer, final Object element) {
		    final String labelText = ((ILabelProvider) ((StructuredViewer) viewer).getLabelProvider()).getText(element);

		    if(labelText == null) {
		        return false;
		    }
		    if(wordMatches(labelText)){
		        return true ;
		    }else{
		        for(final EAttribute attribute : ((EObject) element).eClass().getEAllAttributes()){
		            final Object value = ((EObject) element).eGet(attribute) ;
		            if( value != null && attribute.getEType().getName().equals("EString") && wordMatches(value.toString())){
		                return true ;
		            }
		        }
		    }
		    return false;
		}
	}


	protected FilteredTree filteredTree;
    protected DiagramEditPart diagramEditPart;
    private final  ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
    private final AdapterFactoryContentProvider adapterFactoryContentProvider = new AdapterFactoryContentProvider(adapterFactory);
    private final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory){

        @Override
        public String getText(final Object object) {
            final EStructuralFeature eContainingFeature = ((EObject)object).eContainingFeature();
			if(eContainingFeature != null && eContainingFeature.equals(ProcessPackage.eINSTANCE.getAssignable_Filters())){
                return "Filter "+((Element) object).getName() ;
            }
            if (object instanceof Element){
            	if (!((Element)object).getName().isEmpty()){
            		return ((Element)object).getName();
            	}
            }
            return super.getText(object);
        }

    };



    /**
     * Constructs a TreeViewer with the default root editpart.
     */
    public BonitaTreeViewer() {
        final RootTreeEditPart rep = new RootTreeEditPart();
        setRootEditPart(rep);
    }


    /**
     * Creates the default tree and sets it as the control. The default styles
     * will show scrollbars as needed, and allows for multiple selection.
     * 
     * @param parent
     *            The parent for the Tree
     * @return the control
     */
    @Override
    public Control createControl(final Composite parent) {
        parent.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        final Composite mainComposite = new Composite(parent, SWT.BORDER);
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(5, 10).create());
        mainComposite.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        final TreeViewer treeViewer = createFilteredTree(mainComposite);

        treeViewer.setLabelProvider(adapterFactoryLabelProvider);
        treeViewer.setContentProvider(adapterFactoryContentProvider) ;

        addFilters(treeViewer);

        treeViewer.getTree().addListener(SWT.MouseDoubleClick, new Listener() {

            @Override
            public void handleEvent(final Event event) {
                handlTreeDoubleClick();
            }

        });
      
        setControl(mainComposite);
        return mainComposite;
    }


	private TreeViewer createFilteredTree(final Composite mainComposite) {
		final PatternFilter filter = new SearchPatternFilter() ;
        filter.setIncludeLeadingWildcard(true) ;
        filteredTree = new FilteredTree(mainComposite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL,filter,true);
        final TreeViewer treeViewer = filteredTree.getViewer();
		treeViewer.getTree().setData(SWTBOT_WIDGET_ID_KEY, BONITA_OVERVIEW_TREE_ID);
        filteredTree.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        filteredTree.getFilterControl().setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        filteredTree.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
		return treeViewer;
	}


	private void addFilters(final TreeViewer treeViewer) {
		treeViewer.addFilter(new DatatypesViewFilter());
        treeViewer.addFilter(new DecisionTableViewFilter());
        treeViewer.addFilter(new TextAnnotationLinkViewFilter());
        treeViewer.addFilter(new EmptyExpressionViewFilter());
        treeViewer.addFilter(new EmptySearchIndexViewFilter());
        treeViewer.addFilter(new MultiInstanciationViewFilter());
        treeViewer.addFilter(new EmptyOperationViewFilter());
	}

    public void setDiagramEditPart(final DiagramEditPart diagramEditPart){
        this.diagramEditPart = diagramEditPart ;
        if(filteredTree != null){
            final EObject resolveSemanticElement = ((IGraphicalEditPart) diagramEditPart).resolveSemanticElement();
			filteredTree.getViewer().setInput(resolveSemanticElement) ;
			diagramEditPart.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
				
				@Override
				public void selectionChanged(final SelectionChangedEvent event) {
					if (!filteredTree.isDisposed()) {
						filteredTree.getViewer().refresh();
						final EditPartViewer viewer = diagramEditPart.getViewer();
						if(viewer != null){
							final IStructuredSelection selection= (IStructuredSelection) viewer.getSelection();
							final Object ep = selection.getFirstElement();
							if (ep instanceof IGraphicalEditPart){
								final EObject element = ((IGraphicalEditPart) ep).resolveSemanticElement();
								if (element !=null){
									final Object selected = ((IStructuredSelection) filteredTree.getViewer().getSelection()).getFirstElement();
									if(selected != null && selected instanceof EObject){
										final IGraphicalEditPart foundEP = findEditPartFor((EObject) selected);
										if(!foundEP.equals(ep)){
											filteredTree.getViewer().setSelection(new StructuredSelection(element));
										}
									}
								}
							}
						}				
					}
				}
			});
        }
    }

    protected abstract void handlTreeDoubleClick() ;

    /**
     * @see org.eclipse.gef.EditPartViewer#findObjectAtExcluding(Point,
     *      Collection, EditPartViewer.Conditional)
     */
    @Override
    @SuppressWarnings("rawtypes")
    public EditPart findObjectAtExcluding(final Point pt, final Collection exclude,
            final Conditional condition) {
        return null;
    }

    /**
     * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#fireSelectionChanged()
     */
    @Override
    protected void fireSelectionChanged() {
    	
    }

    /**
     * "Hooks up" a Control, i.e. sets it as the control for the
     * RootTreeEditPart, adds necessary listener for proper operation, etc.
     */
    @Override
    protected void hookControl() {
        if (getControl() == null) {
            return;
        }

        final Tree tree =  filteredTree.getViewer().getTree();
        filteredTree.getViewer().addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				  handleTreeSelection(tree);
			}
		});
        super.hookControl();
    }


    protected abstract void handleTreeSelection(final Tree tree) ;

    protected abstract IGraphicalEditPart findEditPartFor(EObject elem);


    /**
     * @see org.eclipse.gef.ui.parts.AbstractEditPartViewer#reveal(org.eclipse.gef.EditPart)
     */
    @Override
    public void reveal(final EditPart part) {}

    /**
     * Unhooks a control so that it can be reset. This method deactivates the
     * contents, removes the Control as being the Control of the
     * RootTreeEditPart, etc. It does not remove the listeners because it is
     * causing errors, although that would be a desirable outcome.
     */
    @Override
    protected void unhookControl() {
        if (getControl() == null) {
            return;
        }
        if(adapterFactoryContentProvider != null){
            adapterFactoryContentProvider.dispose();
        }
        if(adapterFactoryContentProvider != null){
            adapterFactoryContentProvider.dispose();
        }
        if(adapterFactory != null){
            adapterFactory.dispose();
        }
        super.unhookControl();
    }

    protected void scrollDiagram(final IGraphicalEditPart ep) {
        final org.eclipse.draw2d.geometry.Rectangle bounds = ep.getFigure().getBounds().getCopy();
        //Get the absolute coordinate
        final IFigure referenceFigure = ep.getFigure() ;
        referenceFigure.translateToAbsolute(bounds) ;
        IFigure parentFigure = referenceFigure.getParent();
        while( parentFigure != null  ) {
            if(parentFigure instanceof Viewport) {
                final Viewport viewport = (Viewport)parentFigure;
                bounds.translate(
                        viewport.getHorizontalRangeModel().getValue(),
                        viewport.getVerticalRangeModel().getValue());
                parentFigure = parentFigure.getParent();
            }
            else {
                parentFigure = parentFigure.getParent();
            }
        }
        final Point loc = bounds.getLocation() ;
        final Scrollable f = diagramEditPart.getScrollableControl() ;
        ((FigureCanvas)f).scrollTo(loc.x - f.getBounds().width /2 , loc.y - f.getBounds().height /2) ;
    }

    @Override
    public void setRootEditPart(final RootEditPart editpart) {
        //super.setRootEditPart(editpart);
    }

    @Override
    public void setContents(final EditPart editpart) {

    }

    @Override
    public EditPart getContents() {
        return null;
    }


    public Tree getTree() {
        return filteredTree.getViewer().getTree();
    }
}
