/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.diagram.tools.BonitaUnspecifiedTypeProcessCreationTool;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.EMFtoGEFCommandWrapper;
import org.bonitasoft.studio.diagram.custom.figures.IEventSelectionListener;
import org.bonitasoft.studio.diagram.custom.figures.MenuEventFigure;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneNameEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubProcessEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubprocessEventCompartmentEditPart;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.StartTimerScriptType;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.FileModificationValidator;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandUtilities;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier - integrate evenemential subProcess
 */
public class CustomCreationEditPolicy extends CreationEditPolicy {

	private IElementType type;
	private Object[] types;
	private Cursor cursor;
	@Override
    public Command getCommand(final Request request) {
		if (understandsRequest(request)) {
			if (request instanceof CreateUnspecifiedTypeRequest){
				return getUnspecifiedTypeCreateCommand((CreateUnspecifiedTypeRequest) request);
			} else {
				return super.getCommand(request) ;
			}
		}
		return super.getCommand(request);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy#getReparentCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
	 */
	@Override
	protected Command getReparentCommand(final ChangeBoundsRequest request) {
		final CompoundCommand cc = new CompoundCommand();
		if(getHost() instanceof ITextAwareEditPart){
			return UnexecutableCommand.INSTANCE;
		}
		if(request.getExtendedData().get("DELETE_FROM_LANE") != null){
			return super.getReparentCommand(request);
		}
		if(getHost() instanceof CustomPoolCompartmentEditPart
				&& request.getEditParts().get(0) != null
				&& ((EditPart) request.getEditParts().get(0)).getParent() instanceof CustomLaneCompartmentEditPart){
			return UnexecutableCommand.INSTANCE;
		}

		if(getHost() instanceof CustomSubprocessEventCompartmentEditPart
				&& request.getEditParts().get(0) != null){
			for(final Object child : request.getEditParts()){
				if(child instanceof IGraphicalEditPart){
					if(!((IGraphicalEditPart) child).getTargetConnections().isEmpty() ||
							!((IGraphicalEditPart) child).getSourceConnections().isEmpty()){
						return  UnexecutableCommand.INSTANCE;
					} else {
						if (child instanceof StartTimerEvent2EditPart
								&& !(((IGraphicalEditPart) child).getParent() instanceof CustomSubprocessEventCompartmentEditPart)
								&& !((IGraphicalEditPart)child).getParent().equals(getHost())){
							final Command editCommand = editTimerEventValue((IGraphicalEditPart)child);
							if (editCommand.canExecute()){
								cc.add(editCommand);
							}
						}
					}
				}
		}
		}
		if(getHost() instanceof CustomSubProcessEvent2EditPart){
			return  UnexecutableCommand.INSTANCE;
		}

		if(getHost() instanceof CustomLaneEditPart){
			if( request.getLocation() != null){
				return   UnexecutableCommand.INSTANCE;
			}

		}
		cc.add(super.getReparentCommand(request));
		return cc;
	}
	/**
	 * Gets the command to create a new view (and optionally element) for an
	 * unspecified type request. This command includes a command to popup a menu
	 * to prompt the user for the type to be created.
	 *
	 * @param request
	 *            the unspecified type request
	 * @return the command
	 */
	private Command getUnspecifiedTypeCreateCommand(
			final CreateUnspecifiedTypeRequest request) {

		final Map<IElementType,Command> createCmds = new HashMap<IElementType,Command>();
		final List<IElementType> validTypes = new ArrayList<IElementType>();
		final List<?> requestElementTypes = request.getElementTypes();
		for (final Iterator<?> iter = requestElementTypes.iterator(); iter.hasNext();) {
			final IElementType elementType = (IElementType) iter.next();
			final Request createRequest = request.getRequestForType(elementType);
			if (createRequest != null) {
				EditPart target = getHost().getTargetEditPart(createRequest);
				if ( target == null )  {
					continue;
				}

				//Handle LAne DnD case
				if(target instanceof ITextAwareEditPart
						&& (requestElementTypes.contains(ProcessElementTypes.Pool_2007))){
					target = null ;
					continue ;
				}

				/*If we are on NameEditPart of a Pool or EventSUbProcessPool, take the corresponding parent*/
				if((target instanceof PoolNameEditPart && requestElementTypes.contains(ProcessElementTypes.Lane_3007))){
					target = target.getParent() ;
					for(final Object child : target.getChildren()){
						if(child instanceof CustomPoolCompartmentEditPart){
							target = (EditPart) child ;
						}
						if(target instanceof CustomPoolCompartmentEditPart) {
                            break ;
                        }
					}
				}

				/*
				 * This is for the case: create an element on the border between Pool and Lane; Be precise!!
				 * The idea is to allow only creation of Lane in direct child of Pool.*/
				if((target instanceof PoolPoolCompartmentEditPart)
						&& !(requestElementTypes.contains(ProcessElementTypes.Lane_3007))){
					final List<Element> elements = ((Container)((IGraphicalEditPart) target).resolveSemanticElement()).getElements() ;
					final Iterator<Element> it = elements.iterator() ;
					while (it.hasNext()) {
						final Object object = it.next();
						if(object instanceof Lane){
							target = null ;
							continue ;
						}
					}
				}

				/*If we are a Lane take the corresponding grand-parent: the compartment Pool or EventSubProcessPool EditPart*/
				if(((target instanceof CustomLaneCompartmentEditPart || target instanceof CustomLaneNameEditPart) && requestElementTypes.contains(ProcessElementTypes.Lane_3007)) ){
					target = target.getParent().getParent() ;
				}
                if ((target instanceof CustomLaneEditPart && requestElementTypes.contains(ProcessElementTypes.Lane_3007))) {
                    target = target.getParent();
                }

				/*if we are on an ITextAwareEditPart and ShapeCompartmentEditPart, set target to null???*/
				if(target instanceof ITextAwareEditPart && target instanceof ShapeCompartmentEditPart){
					target = null ;
					continue ;
				}

				if(target instanceof ITextAwareEditPart){
					target = target.getParent() ;
				}

				if(target == null) {
                    continue;
                }

				final Command individualCmd = target.getCommand(createRequest);

				if (individualCmd != null && individualCmd.canExecute() ) {
					if(!(elementType.equals(ProcessElementTypes.Event_2024)
							|| elementType.equals(ProcessElementTypes.Event_3024))){
						createCmds.put(elementType, individualCmd);
						validTypes.add(elementType);
					}
				}
			}
		}

		if (createCmds.isEmpty()) {
			return null;
		} else if (createCmds.size() == 1 ) {
			return (Command) createCmds.values().toArray()[0];
		} else {

			final CustomCreateOrSelectElementCommand selectAndCreateViewCmd = new CustomCreateOrSelectElementCommand(
					DiagramUIMessages.CreateCommand_Label, Display.getCurrent().getActiveShell(), validTypes, LayerManager.Helper.find(getHost()).getLayer(LayerConstants.HANDLE_LAYER)) {

				private Command _createCmd;
				private IFigure selectedFigure;
				private FreeformLayer composite;
				private boolean outsideClick;

				/**
				 * Execute the command that prompts the user with the popup
				 * menu, then executes the command prepared for the element
				 * type that the user selected.
				 */
				@Override
                protected CommandResult doExecuteWithResult(
						final IProgressMonitor progressMonitor, final IAdaptable info)
				throws ExecutionException {


					selectedFigure = FiguresHelper.getSelectedFigure(ProcessElementTypes.Event_2024.getEClass(),-1,-1,null,null);
					final IFigure layer = getLayerFigure();
					final Point loc = new Point(request.getLocation().x, request.getLocation().y);
					FiguresHelper.translateToAbsolute(((GraphicalEditPart) getHost()).getFigure(), loc);
					selectedFigure.setLocation(loc);
					layer.add(selectedFigure);

					composite = new FreeformLayer();
					composite.setSize(20, 100);
					composite.setLocation(loc);

					final MenuEventFigure eventFigure = new MenuEventFigure(composite,createCmds);
					eventFigure.paintElements();
					eventFigure.show() ;
					layer.add(composite);

					eventFigure.addSelectionListener(new IEventSelectionListener() {

						@Override
                        public void eventSelected(final SelectionEvent ev) {
							types = (Object[])ev.data ;
							eventFigure.hide();
						}


					});
					final Display display = Display.getCurrent() ;
					cursor = new Cursor(display, SWT.CURSOR_ARROW);

					outsideClick = false ;
					display.addFilter(SWT.MouseDown, new Listener() {

						@Override
                        public void handleEvent(final Event event) {
							outsideClick = true ;

						}
					});

					while (composite.isVisible() && !outsideClick) {
						if (!display.readAndDispatch()){
							final EditPartViewer viewer = getHost().getViewer();
							final Control control = viewer.getControl();
							if(control.getCursor() != null && !control.getCursor().equals(cursor)){
								control.setCursor(cursor);
							}
							if(viewer.getEditDomain().getActiveTool() instanceof BonitaUnspecifiedTypeProcessCreationTool) {
                                ((BonitaUnspecifiedTypeProcessCreationTool)viewer.getEditDomain().getActiveTool()).setCursor(cursor,true);
                            }
							display.sleep();

						}
					}


					layer.remove(composite);
					composite.erase() ;
					composite = null ;
					layer.remove(selectedFigure);

					final CommandResult cmdResult = CommandResult.newOKCommandResult(types);
					if (cmdResult.getReturnValue() == null || (outsideClick && types == null)) {
						return cmdResult;
					}

					if(_createCmd == null){
						for(final Object t : types){
							_createCmd = createCmds.get(t);
							if(_createCmd != null) {
								type =(IElementType) t ;
								break ;
							}
						}
					}
					types = null;
					Assert.isTrue(_createCmd != null && _createCmd.canExecute());

					// validate the affected files
					final IStatus status = validateAffectedFiles(_createCmd);
					if (!status.isOK()) {
						layer.remove(selectedFigure);
						return new CommandResult(status);
					}


					_createCmd.execute();

					// Set the result in the unspecified type request.
					final CreateRequest createRequest = request.getRequestForType(type);

					final Collection<?> newObject = ((Collection<?>) createRequest
							.getNewObject());
					request.setNewObject(newObject);

					return CommandResult.newOKCommandResult(newObject);
				}

				@Override
                protected CommandResult doUndoWithResult(
						final IProgressMonitor progressMonitor, final IAdaptable info)
				throws ExecutionException {

					if (_createCmd != null && _createCmd.canUndo() ) {
						// validate the affected files
						final IStatus status = validateAffectedFiles(_createCmd);
						if (!status.isOK()) {
							return new CommandResult(status);
						}
						_createCmd.undo();
					}
					return super.doUndoWithResult(progressMonitor, info);
				}

				@Override
                protected CommandResult doRedoWithResult(
						final IProgressMonitor progressMonitor, final IAdaptable info)
				throws ExecutionException {

					if (_createCmd != null && CommandUtilities.canRedo(_createCmd)) {
						// validate the affected files
						final IStatus status = validateAffectedFiles(_createCmd);
						if (!status.isOK()) {
							return new CommandResult(status);
						}
						_createCmd.redo();
					}
					return super.doRedoWithResult(progressMonitor, info);
				}

				private IStatus validateAffectedFiles(final Command command) {
					final Collection<?> affectedFiles = CommandUtilities
					.getAffectedFiles(command);
					final int fileCount = affectedFiles.size();
					if (fileCount > 0) {
						return FileModificationValidator
						.approveFileModification(affectedFiles
								.toArray(new IFile[fileCount]));
					}
					return Status.OK_STATUS;
				}
			};

			return new ICommandProxy(selectAndCreateViewCmd);
		}
	}

	@Override
	public void deactivate() {
		super.deactivate();
		if(cursor != null) {
            cursor.dispose();
        }
	}

	private Command editTimerEventValue(final IGraphicalEditPart timerPart){
		final CompoundCommand cc = new CompoundCommand();
		final StartTimerEvent timer = (StartTimerEvent) timerPart.resolveSemanticElement();
		final StartTimerScriptType scriptType = timer.getScriptType();
		if (!(scriptType.equals(StartTimerScriptType.GROOVY) || scriptType.equals(StartTimerScriptType.CONSTANT)) ){
			cc.add (new EMFtoGEFCommandWrapper(new SetCommand(timerPart.getEditingDomain(), timer.getCondition(),ExpressionPackage.Literals.EXPRESSION__CONTENT, ""),timerPart.getEditingDomain()));
			cc.add(new EMFtoGEFCommandWrapper(new SetCommand(timerPart.getEditingDomain(), timer.getCondition(),ExpressionPackage.Literals.EXPRESSION__NAME, ""), timerPart.getEditingDomain()));
			cc.add(new EMFtoGEFCommandWrapper(new SetCommand(timerPart.getEditingDomain(),timer,ProcessPackage.Literals.START_TIMER_EVENT__SCRIPT_TYPE,StartTimerScriptType.GROOVY),timerPart.getEditingDomain()));

		}
		return cc;
	}

}
