/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.diagram.form.custom.clipboard;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.form.custom.editpolicies.GridLayer;
import org.bonitasoft.studio.diagram.form.custom.editpolicies.GridLayoutManager;
import org.bonitasoft.studio.diagram.form.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.form.custom.parts.CustomFormEditPart;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.util.ProcessSwitch;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.Tool;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 * 
 */
public class CustomPasteCommand extends AbstractCommand {

	private static final String COPY_LABEL = Messages.copyOfLabel;

	private EObject targetElement;
	private IGraphicalEditPart targetEditPart;

	public CustomPasteCommand(String label, IGraphicalEditPart target) {
		super(label);
		targetEditPart = target;
		targetElement = target.resolveSemanticElement();
	}

	private void updateLabelandId(String name , Widget sourceCopy,List<Element> elems) {
		Iterator<Element> it = elems.iterator();
		boolean alreadyExists = false ;
		while(it.hasNext() && !alreadyExists){
			Element e = it.next() ;
			if(e.getName().equals(name)){
				alreadyExists = true ;
			}
		}
		if(alreadyExists){
			updateLabelandId(NamingUtils.convertToId(COPY_LABEL +" ")+name, sourceCopy, elems);
		}else{
			sourceCopy.setName(name) ;
		}
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {
		if (CustomCopyCommand.copiedElement != null) {
			final TransactionalEditingDomain domain = (TransactionalEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(targetElement);
			final Element elem = (Element) EcoreUtil.copy(CustomCopyCommand.copiedElement);

			Form form = null ;
			if(targetElement instanceof Form){
				form = (Form) targetElement ;
			}else{
				form = ModelHelper.getForm((Widget) targetElement);
			}

			List<Element> elems = ModelHelper.getAllItemsOfType(form, ProcessPackage.eINSTANCE.getElement()) ;
			Iterator<Element> it = elems.iterator();
			boolean alreadyExists = false ;
			while(it.hasNext() && !alreadyExists){
				Element e = it.next() ;
				if(e.getName().equals(((Element) CustomCopyCommand.copiedElement).getName())){
					alreadyExists = true ;
				}
			}
			if(alreadyExists){
				updateLabelandId(((Widget) CustomCopyCommand.copiedElement).getName(), (Widget) elem, elems);
			}


			final Point loc = Display.getCurrent().map(null, targetEditPart.getViewer().getControl(), Display.getCurrent().getCursorLocation());
			org.eclipse.draw2d.geometry.Point toolLocation = null;
			try {
				Tool tool = targetEditPart.getViewer().getEditDomain().getActiveTool();
				AbstractTool aTool = (AbstractTool) tool;
				Method m = AbstractTool.class.getDeclaredMethod("getLocation");
				m.setAccessible(true);
				toolLocation = (org.eclipse.draw2d.geometry.Point) m.invoke(aTool);
				FiguresHelper.translateToAbsolute(targetEditPart.getFigure(), toolLocation) ;
			} catch (Exception e) {
				BonitaStudioLog.log("can't find exact location for pasting");
			}

			final org.eclipse.draw2d.geometry.Point location = toolLocation == null ? new org.eclipse.draw2d.geometry.Point(0, 0)
			: new org.eclipse.draw2d.geometry.Point(toolLocation);

			ProcessSwitch<Object> processSwitch = new ProcessSwitch<Object>() {

				@Override
				public Object caseElement(Element object) {
					if (targetElement instanceof Widget) {
						while(!(targetElement instanceof Form) && targetElement != null){
							targetElement = targetElement.eContainer();
							targetEditPart = (IGraphicalEditPart) targetEditPart.getParent();
						}
					}
					if (targetElement instanceof Form) {

						if (elem instanceof Widget) {
							final Form form = (Form) targetElement;

							domain.getCommandStack().execute(new RecordingCommand(domain) {
								@Override
								protected void doExecute() {
									Widget w = (Widget) elem;
									// where the paste was done
									if (targetEditPart instanceof CustomFormEditPart) {
										if (targetEditPart.getFigure().getLayoutManager() instanceof GridLayoutManager) {
											ModelHelper.removedReferencedEObjects(w,targetElement);
											int margin = ((GridLayer) targetEditPart.getFigure()).getMargin();
											GridLayoutManager layoutManager = (GridLayoutManager) targetEditPart.getFigure().getLayoutManager();
											org.eclipse.draw2d.geometry.Point newLoc;
											if (location.x == 0 && location.y == 0) {
												// no location found with the
												// gef tool
												newLoc = new org.eclipse.draw2d.geometry.Point(loc.x, loc.y);
												newLoc.translate(-(margin + 50), -(margin + 150));// ugly
											} else {
												// gef tools location
												newLoc = new org.eclipse.draw2d.geometry.Point(location.x, location.y);
												newLoc.translate(-(margin), -(margin));
											}
											// way to get the context menu
											// location
											// TODO get the location of the context menu
											newLoc = layoutManager.getConstraintFor(newLoc);
											location.x = newLoc.x;
											location.y = newLoc.y;
											org.eclipse.draw2d.geometry.Point startLocation = location.getCopy();
											org.eclipse.draw2d.geometry.Point size = new org.eclipse.draw2d.geometry.Point(w.getWidgetLayoutInfo()
													.getHorizontalSpan(), w.getWidgetLayoutInfo().getVerticalSpan());

											boolean found = ModelHelper.findAvailableLocation(form, location, size);
											if (found) {
												/*Look if we are in a Group*/
												Group g = (Group) ModelHelper.getWidgetIn(form, location.x, location.y, true);
												if (g != null) {//if we are in a Group
													int dx = 0;
													int dy = 0;
													EObject container = g;
													while(container != null && !(container instanceof Form)){
														if(container instanceof Group){
															dx +=((Widget) container).getWidgetLayoutInfo().getColumn();
															dy += ((Widget) container).getWidgetLayoutInfo().getLine();
														}
														container = container.eContainer();
													}
													w.getWidgetLayoutInfo().setLine(location.y-dy);
													w.getWidgetLayoutInfo().setColumn(location.x-dx);
													g.getWidgets().add(w);
												} else {//we are not in a Group
													w.getWidgetLayoutInfo().setLine(location.y);
													w.getWidgetLayoutInfo().setColumn(location.x);
													form.getWidgets().add(w);
												}
											} else {//we don't find an available location keeping span, look reducing span to 1,1
												if(w instanceof Group){
													//TODO: check to reduce size depending of what it contains
												} else {
													found = ModelHelper.findAvailableLocation(form, startLocation);
													if (found) {
														w.getWidgetLayoutInfo().setVerticalSpan(1);
														w.getWidgetLayoutInfo().setHorizontalSpan(1);
														Group g = (Group) ModelHelper.getWidgetIn(form, startLocation.x, startLocation.y, true);
														if (g != null) {
															int dx = 0;
															int dy = 0;
															EObject container = g;
															while(container != null && !(container instanceof Form)){
																if(container instanceof Group){
																	dx +=((Widget) container).getWidgetLayoutInfo().getColumn();
																	dy += ((Widget) container).getWidgetLayoutInfo().getLine();
																}
																container = container.eContainer();
															}
															w.getWidgetLayoutInfo().setLine(location.y-dy);
															w.getWidgetLayoutInfo().setColumn(location.x-dx);
															g.getWidgets().add(w);
														} else {
															w.getWidgetLayoutInfo().setLine(startLocation.y);
															w.getWidgetLayoutInfo().setColumn(startLocation.x);
															form.getWidgets().add(w);
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
					return super.caseElement(object);

				}

			};

			if (targetElement instanceof Element) {
				processSwitch.doSwitch(CustomCopyCommand.copiedElement);
			}

		} else {
			return CommandResult.newErrorCommandResult("Nothing to copy"); //$NON-NLS-1$
		}
		return CommandResult.newOKCommandResult();

	}

	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

		return CommandResult.newOKCommandResult();
	}

	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor, IAdaptable info) throws ExecutionException {

		return CommandResult.newOKCommandResult();
	}

}
