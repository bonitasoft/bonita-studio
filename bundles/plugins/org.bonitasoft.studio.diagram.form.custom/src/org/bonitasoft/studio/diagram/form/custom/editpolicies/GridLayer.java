/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.form.custom.commands.AddColumnCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.AddRowCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.RemoveColumnCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.RemoveRowCommand;
import org.bonitasoft.studio.diagram.form.custom.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gmf.runtime.common.ui.services.editor.EditorService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * Class for a figure with a grid and with grid management for its children.
 */
public class GridLayer extends AbstractGridLayer {


    protected Label nextFormFigure = new Label(Messages.labelNextForm);
    protected Label previousFormFigure = new Label(Messages.labelPreviousForm);

    private final ArrayList<IFigure> removeColumns = new ArrayList<IFigure>();
    private final ArrayList<IFigure> removeRows = new ArrayList<IFigure>();
    private final ArrayList<IFigure> insertColumns = new ArrayList<IFigure>();
    private final ArrayList<IFigure> insertRows = new ArrayList<IFigure>();
    private int drawnForNColumn = -1;
    private int drawnForNLine = -1;

    protected FormEditPart formEditPart;

    private final IFigure insertColumnTooltipFigure = new Label(Messages.insertColumnTooltip);
    private final IFigure insertRowTooltipFigure = new Label(Messages.insertRowTooltip);
    private final IFigure removeRowTooltipFigure = new Label(Messages.removeRowTooltip);
    private final IFigure removeColumnTooltipFigure = new Label(Messages.removeColumnTooltip);

    /*Image displayed to add/remove column/row*/
    private final Image imagePlusBlack = Pics.getImage(PicsConstants.plusBlack);
    private final Image imagePlusGrey = Pics.getImage(PicsConstants.plusGrey);
    private final Image imageMinusBlack = Pics.getImage(PicsConstants.minusBlack);
    private final Image imageMinusGrey = Pics.getImage(PicsConstants.minusGrey);

    /*Listener used to update the minus image when the mouse is on it*/
    protected MouseMotionListener mmlForMinus = new MouseMotionListener.Stub(){

        @Override
        public void mouseEntered(final MouseEvent me) {
            if(me.getSource() instanceof ImageFigure){
                final ImageFigure f = (ImageFigure) me.getSource();
                f.setImage(imageMinusBlack);
            }
        };

        @Override
        public void mouseExited(final MouseEvent me) {
            if(me.getSource() instanceof ImageFigure){
                final ImageFigure f = (ImageFigure) me.getSource();
                f.setImage(imageMinusGrey);
            }

        };
    };

    /*Listener used to update the plus image when the mouse is on it*/
    protected MouseMotionListener mmlForPlus = new MouseMotionListener.Stub(){

        @Override
        public void mouseEntered(final MouseEvent me) {
            if(me.getSource() instanceof ImageFigure){
                final ImageFigure f = (ImageFigure) me.getSource();
                f.setImage(imagePlusBlack);
            }
        };

        @Override
        public void mouseExited(final MouseEvent me) {
            if(me.getSource() instanceof ImageFigure){
                final ImageFigure f = (ImageFigure) me.getSource();
                f.setImage(imagePlusGrey);
            }

        };
    };



    /**
     * Constructs a new GridFigure with grid drawing enabled and default grid
     * color.
     *
     * @param formEditPart
     */
    public GridLayer(final FormEditPart formEditPart) {
        this.formEditPart = formEditPart;
        setBorder(new MarginBorder(margin, margin, margin, margin));
        setOpaque(false);
        setLayoutManager(new GridLayoutManager(this));

        setBounds(new Rectangle(0, 0, getGridLayout().getSizeX()*getNumColumn()+2*margin, getGridLayout().getSizeY()*getNumLine()+2*margin));
    }



    /**
     * Redraw if needed the figure in the border (add, remove)
     */
    protected void redrawBorder() {
        if(drawnForNColumn != getNumColumn()){
            clearColumnBorder();
            drawColumnFigure();
            drawnForNColumn = getNumColumn();
        }
        if(drawnForNLine != getNumLine()){
            clearRowBorder();
            drawRowsFigure();
            drawnForNLine = getNumLine();
        }
    }

    private void clearRowBorder() {
        for (final IFigure fig : removeRows) {
            if (fig != null) {
                fig.getParent().remove(fig);
                fig.erase();
            }
        }
        removeRows.clear();
        for (final IFigure fig : insertRows) {
            if (fig != null) {
                fig.getParent().remove(fig);
                fig.erase();
            }
        }
        insertRows.clear();

    }

    private void clearColumnBorder() {
        for (final IFigure fig : removeColumns) {
            if (fig != null) {
                fig.getParent().remove(fig);
                fig.erase();
            }
        }
        removeColumns.clear();
        for (final IFigure fig : insertColumns) {
            if (fig != null) {
                fig.getParent().remove(fig);
                fig.erase();
            }
        }
        insertColumns.clear();
    }

    /**
     * @param index
     * @return
     */
    protected Set<GraphicalEditPart> getEditPartsInRow(final int index) {
        final Set<GraphicalEditPart> res = new HashSet<GraphicalEditPart>();
        for (final Object part : formEditPart.getChildren()) {
            if (part instanceof GraphicalEditPart) {
                if (((GraphicalEditPart) part).resolveSemanticElement() instanceof Widget) {
                    final Widget widget = (Widget) ((GraphicalEditPart) part).resolveSemanticElement();
                    if (widget.getWidgetLayoutInfo().getLine() == index) {
                        res.add((GraphicalEditPart) part);
                    }
                }
            }
        }
        return res;
    }



    /**
     *
     */
    private void drawColumnFigure() {
        removeColumns.clear();
        insertColumns.clear();
        if (getNumColumn() == 1) {
            createInsertColumn(0);
        }
        if (getNumColumn() != 1) {
            for (int i = 0; i < getNumColumn(); i++) {
                createRemoveColumn(i);
                createInsertColumn(i);
            }
        }
        createInsertColumn(getNumColumn());

    }

    /**
     *
     */
    private void drawRowsFigure() {
        removeRows.clear();
        insertRows.clear();

        if (getNumLine() == 1) {
            //			IFigure selectFigure = createSelectRow(0);
            //			selectRows.add(selectFigure);
            //			this.getParent().add(selectFigure);
        } else {
            for (int i = 0;i < getNumLine(); i++){
                createRemoveRow(i);
                createInsertRow(i);
            }
        }
        createInsertRow(getNumLine());
    }

    public Set<GraphicalEditPart> getEditPartsInColumn(final int columnIndex) {
        final Set<GraphicalEditPart> res = new HashSet<GraphicalEditPart>();
        for (final Object part : formEditPart.getChildren()) {
            if (part instanceof GraphicalEditPart) {
                if (((GraphicalEditPart) part).resolveSemanticElement() instanceof Widget) {
                    final Widget widget = (Widget) ((GraphicalEditPart) part).resolveSemanticElement();
                    if (widget.getWidgetLayoutInfo().getColumn() == columnIndex) {
                        res.add((GraphicalEditPart) part);
                    }
                }
            }
        }
        return res;
    }


    /**
     * @param rowIndex
     * @return
     */
    private void createInsertRow(final int index) {

        final ImageFigure figure = new ImageFigure(imagePlusGrey);
        figure.addMouseMotionListener(mmlForPlus);
        commonAllFigure(figure, index);
        final Point location = new Point(margin - 25, margin + index * getGridLayout().getSizeY() - 8);
        figure.setLocation(location);
        figure.setToolTip(insertRowTooltipFigure);
        insertRows.add(figure);

        figure.addMouseListener(new MouseListener.Stub() {

            @Override
            public void mousePressed(final MouseEvent me) {
                addRow(index);
            }

            private void addRow(final int index) {
                final IUndoableOperation command = new AddRowCommand(formEditPart, index);
                commandToExecuteWhenMousePressed(command);
            }
        });
    }

    /**
     * @param columnIndex
     * @return
     */
    private void createInsertColumn(final int index) {
        final ImageFigure figure = new ImageFigure(imagePlusGrey);
        figure.addMouseMotionListener(mmlForPlus);
        commonAllFigure(figure, index);
        final Point location = new Point(margin + index * getGridLayout().getSizeX() - 8, margin - 25);
        figure.setLocation(location);
        figure.setToolTip(insertColumnTooltipFigure);
        insertColumns.add(figure);
        figure.addMouseListener(new MouseListener.Stub() {

            @Override
            public void mousePressed(final MouseEvent me) {
                final IUndoableOperation command = new AddColumnCommand(formEditPart, index);
                commandToExecuteWhenMousePressed(command);
            }
        });

    }

    private void commonAllFigure(final ImageFigure figure, final int index) {
        figure.setSize(16, 16);
        getParent().add(figure);
    }

    /**
     * @param index
     * @return
     */
    private void createRemoveRow(final int index) {
        final ImageFigure figure = new ImageFigure(imageMinusGrey);
        figure.addMouseMotionListener(mmlForMinus);
        commonAllFigure(figure, index);
        final Point location = new Point(margin - 25, margin + index * getGridLayout().getSizeY() + getGridLayout().getSizeY() / 2 - 8);
        figure.setLocation(location);
        figure.setToolTip(removeRowTooltipFigure);
        removeRows.add(figure);
        figure.addMouseListener(new MouseListener.Stub() {

            @Override
            public void mousePressed(final MouseEvent me) {
                final IUndoableOperation command = createRemoveRowCommand(index);
                commandToExecuteWhenMousePressed(command);
            }
        });
    }

    protected IUndoableOperation createRemoveRowCommand(final int index) {
        return new RemoveRowCommand(formEditPart, index);
    }



    /**
     * @param columnIndex
     * @return
     */
    private void createRemoveColumn(final int index) {
        final ImageFigure figure = new ImageFigure(imageMinusGrey);
        figure.addMouseMotionListener(mmlForMinus);
        commonAllFigure(figure, index);
        final Point location = new Point(margin + index * getGridLayout().getSizeX() + getGridLayout().getSizeX() / 2 - 8,
                margin - 25);
        figure.setLocation(location);
        figure.setToolTip(removeColumnTooltipFigure);
        removeColumns.add(figure);
        figure.addMouseListener(new MouseListener.Stub() {

            @Override
            public void mousePressed(final MouseEvent me) {
                final IUndoableOperation command = createRemoveColumnCommand(index);
                commandToExecuteWhenMousePressed(command);
            }
        });
    }

    protected IUndoableOperation createRemoveColumnCommand(final int index) {
        return new RemoveColumnCommand(formEditPart, index);
    }



    /**
     * @return the numColumn
     */
    @Override
    public int getNumColumn() {
        return ((Form) formEditPart.resolveSemanticElement()).getNColumn();
    }

    @Override
    public int getNumLine(){
        return ((Form) formEditPart.resolveSemanticElement()).getNLine();
    }

    /**
     * Set the numColumn in the model and repaint
     *
     * @param numColumn
     *            the numColumn to set
     */
    @Override
    public void setNumColumn(final int numColumn) {
        /* update the value in the model */
        final Form form = (Form) formEditPart.resolveSemanticElement();
        formEditPart.getEditingDomain().getCommandStack().execute(
                new SetCommand(formEditPart.getEditingDomain(), form, FormPackage.eINSTANCE.getForm_NColumn(), numColumn));
        repaint();
    }


    /**
     * Execute IUndoableOperation and repaint.
     * */
    protected void commandToExecuteWhenMousePressed(final IUndoableOperation operation){
        try {
            OperationHistoryFactory.getOperationHistory().execute(operation, null, null);
        } catch (final ExecutionException e) {
            BonitaStudioLog.error(e);
        }
        revalidate();
        repaint();
    }

    protected void goToForm(final int n, final EStructuralFeature feature){
        final Form actualForm = (Form) formEditPart.resolveSemanticElement();
        final PageFlow pageFlow = (PageFlow) actualForm.eContainer();
        final Form formToOpen = (Form) ((List<?>) pageFlow.eGet(feature)).get(n);
        /* get the Diagram element related to the form to open */
        final Diagram diag = ModelHelper.getDiagramFor(formToOpen);
        final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        final IEditorPart editor = page.getActiveEditor();
        if(editor instanceof FormDiagramEditor){
            /*Close the actual form editor*/
            final URI uri = EcoreUtil.getURI(diag);
            EditorService.getInstance().openEditor(new URIEditorInput(uri, formToOpen.getName()));

            page.closeEditor(editor, false);
        }
    }

    @Override
    protected void paintFigure(final Graphics graphics) {
        super.paintFigure(graphics);
        if(isDrawGrid()){
            /*update border*/
            redrawBorder();
        }

        /*Draw if needed arrow to go to adjacent forms.*/
        goToPreviousOrNextPage(graphics);
    }



    /**
     * Draw if needed arrow to go to adjacent forms.
     * @param graphics
     */
    protected void goToPreviousOrNextPage(final Graphics graphics) {
        final Form form = (Form) formEditPart.resolveSemanticElement();
        final EStructuralFeature feature;
        if(form instanceof ViewForm){
            if(form.eContainer() != null && form.eContainingFeature().equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS)){
                feature = ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS;
            }else{
                feature = ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM;
            }
        }else{
            feature = ProcessPackage.Literals.PAGE_FLOW__FORM;
        }
        final Element pageFlow = (Element) form.eContainer();
        if(pageFlow == null){
            /*Case of edit templates*/
            return;
        }
        final int index = ((List<?>) pageFlow.eGet(feature)).indexOf(form);

        /*go previous if form index is greater than 0*/
        //TODO : find a better condition to avoid to redraw always
        if(index > 0 && previousFormFigure.getBounds().width == 0){

            previousFormFigure.setLocation(this.getClientArea().getTopLeft().translate(-40, -50));
            final Dimension dim = previousFormFigure.getTextUtilities().getStringExtents(Messages.labelPreviousForm, graphics.getFont());
            previousFormFigure.setSize(dim.expand(20, 0));
            previousFormFigure.setIcon(Pics.getImage(PicsConstants.arrowLeft));
            previousFormFigure.addMouseListener(new MouseListener.Stub(){
                @Override
                public void mousePressed(final MouseEvent me) {
                    goToForm(index-1,feature);
                    me.consume();
                }
            });
            getParent().add(previousFormFigure);
        }
        /*go next if form is not the last in the index*/
        //TODO : find a better condition to avoid to redraw always
        if(index < ((List<?>) pageFlow.eGet(feature)).size()-1 && nextFormFigure.getBounds().width == 0){
            final Dimension dimOfPrevious = previousFormFigure.getTextUtilities().getStringExtents(Messages.labelPreviousForm, graphics.getFont());
            nextFormFigure.setLocation(this.getClientArea().getTopLeft().translate(dimOfPrevious.width-10, -50));
            final Dimension dim = nextFormFigure.getTextUtilities().getStringExtents(Messages.labelNextForm, graphics.getFont());
            nextFormFigure.setSize(dim.expand(20, 0));
            nextFormFigure.setIcon(Pics.getImage(PicsConstants.arrowRight));
            nextFormFigure.setTextPlacement(PositionConstants.WEST);
            nextFormFigure.addMouseListener(new MouseListener.Stub(){
                @Override
                public void mousePressed(final MouseEvent me) {
                    goToForm(index+1,feature);
                    me.consume();
                }
            });
            getParent().add(nextFormFigure);
        }

    }



    @Override
    protected void setBoundsOfTheGrid() {
        /*set the bounds in a way that we see all the grid*/
        setBounds(new Rectangle(0, 0, getGridLayout().getSizeX()*getNumColumn()+2*margin, getGridLayout().getSizeY()*getNumLine()+2*margin));

    }

    @Override
    public Point getOrigin() {
        return new Point(margin, margin);
    }



}