/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.test.swtbot.util;

import static org.bonitasoft.studio.actors.i18n.Messages.selectActor;
import static org.bonitasoft.studio.actors.i18n.Messages.setAsProcessInitiator;
import static org.bonitasoft.studio.actors.i18n.Messages.useTaskActors;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.editExpression;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.expressionTypeLabel;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.returnType;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.diagram.custom.editPolicies.NextElementEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.UpdateSizePoolSelectionEditPolicy;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement;
import org.eclipse.xtext.ui.editor.reconciler.XtextReconciler;
import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;

public class SWTBotTestUtil implements SWTBotConstants{

	private static final String PAGEFLOW_LABEL = "Pageflow";
	public static final int CONTEXTUALPALETTE_STEP = 0;
	public static final int CONTEXTUALPALETTE_GATEWAY = 1;
	public static final int CONTEXTUALPALETTE_SEQUENCEFLOW = 2;
	//TOOLBAREVENT doesn't work, create a comment
	public static final int CONTEXTUALPALETTE_EVENT= 3;


	public static void createNewDiagram(final SWTWorkbenchBot bot){
		final long timebeforeCreatenewDiagram = System.currentTimeMillis();    	
		final int nbEditorsBefore = bot.editors().size();
		bot.waitUntil(Conditions.widgetIsEnabled(bot.menu("Diagram")),40000);
		SWTBotMenu menu = bot.menu("Diagram");
		menu.menu("New").click();
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				return nbEditorsBefore +1 == bot.editors().size();
			}

			@Override
			public void init(SWTBot bot) {
			}

			@Override
			public String getFailureMessage() {
				return "Editor for new diagram has not been opened";
			}
		}, 30000,100);
		System.out.println("Time to create a new diagram: "+String.valueOf(System.currentTimeMillis()-timebeforeCreatenewDiagram));
	}

	public static IStatus selectAndRunFirstPoolFound(final SWTGefBot bot) throws ExecutionException {
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				return  bot.activeEditor() != null;
			}

			@Override
			public void init(SWTBot bot) { }

			@Override
			public String getFailureMessage() {
				return "No active editor found";
			}
		});
		SWTBotEditor botEditor = bot.activeEditor();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());
		List<SWTBotGefEditPart> runnableEPs = gmfEditor.editParts(new BaseMatcher<EditPart>() {

			@Override
			public boolean matches(Object item) {
				return item instanceof PoolEditPart || item instanceof FormEditPart;
			}

			@Override
			public void describeTo(Description description) {

			}
		});
		Assert.assertFalse(runnableEPs.isEmpty());
		gmfEditor.select(runnableEPs.get(0));
		RunProcessCommand cmd =  new RunProcessCommand(true);
		return (IStatus) cmd.execute(null);
	}


	public static boolean testingBosSp() {
		return Platform.getBundle("org.bonitasoft.studioEx.console.libs") != null;
	}


	public static void selectTabbedPropertyView(final SWTBot viewerBot, final String tabeText) {
		viewerBot.sleep(1000); //DO NOT REMOVE ME //TODO : find a way to remove bot.sleep, can save almost one minute
		UIThreadRunnable.syncExec(new VoidResult() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swtbot.swt.finder.results.VoidResult#run()
			 */
			@Override
			@SuppressWarnings("restriction")
			public void run() {
				try {
					List<? extends Widget> widgets = viewerBot.getFinder().findControls(WidgetMatcherFactory.widgetOfType(TabbedPropertyList.class));
					Assert.assertTrue("No widget of type "+TabbedPropertyList.class.getName()+" has been found",widgets.size() > 0);
					TabbedPropertyList tabbedPropertyList = (TabbedPropertyList) widgets.get(0);
					int i = 0;
					boolean found = false;
					ListElement currentTab;
					Method selectMethod = TabbedPropertyList.class.getDeclaredMethod("select", new Class[] { int.class });
					selectMethod.setAccessible(true);
					do {
						currentTab = ((org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement)tabbedPropertyList.getElementAt(i));
						if (currentTab != null) {
							String label = currentTab.getTabItem().getText();
							if (label.equals(tabeText)) {
								found = true;
								selectMethod.invoke(tabbedPropertyList, i);
							}
						}
						i++;
					} while (currentTab != null && !found);
					if (!found) {
						throw new WidgetNotFoundException("Can't find a tab item with " + tabeText + " label");
					}
				} catch (Exception ex) {
					BonitaStudioLog.error(ex);
					throw new WidgetNotFoundException("Can't find a tab item with " + tabeText + " label");
				}
			}
		});
	}


	/**
	 * @param bot
	 * @param resourceNameInClasspath
	 * @param importName: type of import (Bonita, xpdl, jpdl, ...)
	 * @param diagramEditorTitle
	 * @param srcClass
	 * @param mustAskOverride
	 * @throws IOException
	 */
	public static void importProcessWIthPathFromClass(final SWTWorkbenchBot bot, String resourceNameInClasspath, String importName, final String diagramEditorTitle, Class<?> srcClass, boolean mustAskOverride) throws IOException{
		BonitaStudioLog.log("SWTBot begin to import "+ resourceNameInClasspath + " in mode " +importName);
		boolean disable = FileActionDialog.getDisablePopup();
		FileActionDialog.setDisablePopup(true);
		bot.waitUntil(Conditions.shellIsActive("Bonita BPM"));
		SWTBotMenu menu = bot.menu("Diagram");
		menu.menu("Import...").click();

		bot.waitUntil(Conditions.shellIsActive("Import..."));
		URL url = srcClass.getResource(resourceNameInClasspath);
		url = FileLocator.toFileURL(url);
		File file = new File(url.getFile());
		bot.text().setText(file.getAbsolutePath());
		bot.table().select(importName);
		bot.button("Import").click();
		bot.waitUntil(new ICondition() {
			@Override
			public boolean test() throws Exception {
				for (SWTBotEditor aBot : bot.editors()) {
					if(aBot.getTitle().contains(diagramEditorTitle)) {
						return true;
					}
				}

				return false;
			}

			@Override
			public void init(SWTBot bot) {
			}

			@Override
			public String getFailureMessage() {
				return "no active editor";
			}
		},65000);
		FileActionDialog.setDisablePopup(disable);
		BonitaStudioLog.log("SWTBot has imported "+ resourceNameInClasspath + " in mode " +importName);
	}


	/**
	 * Create a new Form and save it
	 * @param bot
	 * @param gmfEditor
	 * @param nameOfStepOnwhichCreateTheForm
	 * @return
	 */
	public static SWTBotEditor createFormWhenOnAProcessWithStep(SWTGefBot bot,
			SWTBotGefEditor gmfEditor, String nameOfStepOnwhichCreateTheForm) {
		SWTBotGefEditPart part = gmfEditor.getEditPart(nameOfStepOnwhichCreateTheForm);
		part.click();
		bot.viewById(VIEWS_PROPERTIES_APPLICATION).show();
		bot.viewById(VIEWS_PROPERTIES_APPLICATION).setFocus();
		SWTBotTestUtil.selectTabbedPropertyView(bot, PAGEFLOW_LABEL);
		SWTBotView properties = bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION);
		properties.bot().button("Add...").click();
		bot.waitUntil(Conditions.shellIsActive("Add form..."));
		bot.button(IDialogConstants.FINISH_LABEL).click();
		SWTBotEditor activeEditor = bot.activeEditor();
		activeEditor.save();
		return activeEditor;
	}

	/**
	 * select an event on diagram with the given name
	 * @param bot
	 * @param gmfEditor
	 * @param poolName
	 * @param eventName
	 */
	public static void selectEventOnProcess(final  SWTGefBot bot, SWTBotGefEditor gmfEditor,String eventName){
		SWTBotGefEditPart event = gmfEditor.getEditPart(eventName).parent();
		event.click();
	}

	/**
	 * select an event in the overview tree. Becarefull, if treeViewer exists in other views SWTBot may not find the one in overview
	 * @param bot
	 * @param poolName
	 * @param eventName
	 */
	public static void selectElementFromOverview(final  SWTGefBot bot,String poolName,String laneName, String eventName){
		final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
		view.show();
		view.setFocus();
		final SWTBotTree tree = bot.treeWithId(BONITA_OVERVIEW_TREE_ID);
		tree.setFocus();
		tree.getTreeItem(poolName).click();
		if (laneName==null){
			tree.expandNode(poolName).select(eventName);
		} else {
			tree.expandNode(laneName).click();
		}
	}

	public static void selectElementFromFormOverview(final SWTGefBot bot,String widgetName){
		final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
		view.show();
		view.setFocus();
		final SWTBotTree tree = bot.treeWithId(BONITA_OVERVIEW_TREE_ID);
		tree.setFocus();
		bot.waitUntil(Conditions.widgetIsEnabled( tree.getTreeItem(widgetName)));
		tree.select(widgetName);
		//tree.getTreeItem(widgetName).select(widgetName);

		//tree.getTreeItem(widgetName).click();
	}

	/**
	 * @param gmfEditor
	 * @param selectedElementName
	 * @param elementIndex
	 * @param isEvent
	 * @param dropLocation
	 */
	public static void selectElementInContextualPaletteAndDragIt(SWTBotGefEditor gmfEditor,String selectedElementName,int elementIndex, Point dropLocation){
		SWTBotGefEditPart element ;
		SWTBotGefEditPart gep = gmfEditor.getEditPart(selectedElementName);
		Assert.assertNotNull("Error: No Edit Part \'"+selectedElementName+"\' found.", gep);
		element = gep.parent();
		element.select();
		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
		NextElementEditPolicy nextElementEditPolicy = (NextElementEditPolicy)graphicalEditPart.getEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE);

		IFigure toolbarFigure =nextElementEditPolicy.getFigure(elementIndex);
		Point location = toolbarFigure.getBounds().getCenter().getCopy();
		toolbarFigure.translateToAbsolute(location);
		gmfEditor.drag(location.x, location.y, dropLocation.x,dropLocation.y);
	}

	public static void selectElementInContextualPaletteAndDragIt(SWTBotGefEditor gmfEditor,String selectedElementName,int elementIndex, int position){
		SWTBotGefEditPart gep = gmfEditor.getEditPart(selectedElementName);
		Assert.assertNotNull("Error: No Edit Part \'"+selectedElementName+"\' found.", gep);
		SWTBotGefEditPart element = gep.parent();
		element.select();
		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();
		NextElementEditPolicy nextElementEditPolicy = (NextElementEditPolicy)graphicalEditPart.getEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE);

		IFigure toolbarFigure = nextElementEditPolicy.getFigure(elementIndex);
		Point dropLocation = null;


		int yPaletteDelta = computeYPaletteDelta(elementIndex);

		final Rectangle bounds = graphicalEditPart.getFigure().getBounds();
		switch (position) {
		case PositionConstants.NORTH:dropLocation = bounds.getTop().getCopy().translate(-20, -70);break;
		case PositionConstants.SOUTH:dropLocation = bounds.getBottom().getCopy().translate(-20, 70);break;
		case PositionConstants.WEST:dropLocation = bounds.getLeft().getCopy().translate(-80, yPaletteDelta);break;
		case PositionConstants.EAST:dropLocation = bounds.getRight().getCopy().translate(80,yPaletteDelta);break;

		case PositionConstants.NORTH_EAST:dropLocation = bounds.getTopRight().getCopy().translate(80, -70);break;
		case PositionConstants.NORTH_WEST:dropLocation = bounds.getTopLeft().getCopy().translate(-80, 70);break;
		case PositionConstants.SOUTH_EAST:dropLocation = bounds.getBottomRight().getCopy().translate(80, 70);break;
		case PositionConstants.SOUTH_WEST:dropLocation = bounds.getBottomLeft().getCopy().translate(-80,-70);break;

		default: throw new RuntimeException("Invalid position specified") ;
		}

		Point location = toolbarFigure.getBounds().getCenter().getCopy();
		toolbarFigure.translateToAbsolute(location);

		gmfEditor.drag(location.x, location.y, dropLocation.x,dropLocation.y);
	}

	private static int computeYPaletteDelta(int elementIndex) {
		switch (elementIndex) {
		case CONTEXTUALPALETTE_EVENT:return -8;
		case CONTEXTUALPALETTE_GATEWAY:return 5;
		case CONTEXTUALPALETTE_SEQUENCEFLOW:return -5;
		case CONTEXTUALPALETTE_STEP:return 8;
		default:return 0;
		}
	}

	/**
	 * 
	 * @param gmfEditor
	 * @param selectedElementName
	 * @param dropLocation
	 */
	public static void selectTaskFromSelectedElementAndDragIt(SWTBotGefEditor gmfEditor,String selectedElementName, Point dropLocation){
		selectElementInContextualPaletteAndDragIt( gmfEditor, selectedElementName, CONTEXTUALPALETTE_STEP, dropLocation);
	}

	/**
	 * 
	 * @param gmfEditor
	 * @param selectedElementName
	 * @param dropLocation
	 */
	public static void selectTransitionFromSelectedElementAndDragIt(SWTBotGefEditor gmfEditor,String selectedElementName, Point dropLocation){
		selectElementInContextualPaletteAndDragIt( gmfEditor, selectedElementName, CONTEXTUALPALETTE_SEQUENCEFLOW, dropLocation);
	}


	/**
	 * add data wizard configuration (only for defined types as String Integer Boolean etc.)
	 * @param bot
	 * @param name
	 * @param type
	 * @param multiplicity
	 * @param defaultValue
	 */
	public static void addNewData(SWTBot bot,String name, String type, boolean multiplicity,String defaultValue){
		bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.data.i18n.Messages.newVariable));
		bot.textWithLabel(org.bonitasoft.studio.data.i18n.Messages.name+" *").setText(name);
		bot.comboBoxWithLabel(org.bonitasoft.studio.data.i18n.Messages.datatypeLabel).setSelection(type);
		if (multiplicity){
			bot.checkBox(org.bonitasoft.studio.data.i18n.Messages.isMultiple).select();
		}
		if (defaultValue!=null){
			bot.textWithLabel(org.bonitasoft.studio.data.i18n.Messages.defaultValueLabel).setText(defaultValue);
		}
		bot.button(IDialogConstants.FINISH_LABEL).click();
	}

	public static void addNewCustomDataInitWithScript(SWTGefBot bot,
			String name, String type, String defaultValueAsScript) {
		bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.data.i18n.Messages.newVariable));
		bot.textWithLabel(org.bonitasoft.studio.properties.i18n.Messages.name).setText(name);
		bot.comboBoxWithLabel(org.bonitasoft.studio.properties.i18n.Messages.datatypeLabel).setSelection(org.bonitasoft.studio.common.Messages.JavaType);
		bot.button(org.bonitasoft.studio.data.i18n.Messages.browseClasses).click();
		bot.text().setText(type);
		bot.waitUntil(Conditions.tableHasRows(bot.table(), 2));
		bot.table().select(0);
		bot.button(IDialogConstants.OK_LABEL).click();
		bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.data.i18n.Messages.newVariable));
		clickOnPenToEditExpression(bot, 0);
		SWTBotTestUtil.setScriptExpression(bot, "result", defaultValueAsScript, type);
		bot.button(IDialogConstants.FINISH_LABEL).click();
	}

	/**
	 * add data with option wizard configuration (only for defined types as String Integer Boolean etc.)
	 * @param bot
	 * @param name
	 * @param type
	 * @param multiplicity
	 * @param defaultValue
	 */
	public static void addListOfOptionData(SWTBot bot,String name,String type,Map<String,List<String>> options,boolean isMultiple,String defaultValue){
		bot.waitUntil(Conditions.shellIsActive("New variable"));
		bot.textWithLabel(org.bonitasoft.studio.properties.i18n.Messages.name+" *").setText(name);
		bot.comboBoxWithLabel(org.bonitasoft.studio.properties.i18n.Messages.datatypeLabel).setSelection(type);
		bot.button("List of options...").click();
		bot.waitUntil(Conditions.shellIsActive("List of options"));
		int i =0;
		for (String optionsName:options.keySet()){
			bot.button("Add", 0).click();
			bot.table(0).click(i, 0);
			bot.text().setText(optionsName);
			int j=0;
			for (String option:options.get(optionsName)){
				bot.button("Add",1).click();
				bot.table(1).click(j, 0);
				bot.text().setText(option);
				j++;
			}
			i++;
		}
		bot.button(IDialogConstants.OK_LABEL).click();
		if (isMultiple){
			bot.checkBox("Is multiple").select();
		}
		if (defaultValue!=null){
			bot.textWithLabel(org.bonitasoft.studio.properties.i18n.Messages.defaultValueLabel).setText(defaultValue);
			bot.sleep(1000);
		}
		bot.button(IDialogConstants.FINISH_LABEL).click();
	}


	/**
	 * add a sequence flow between startElement and End Element
	 * @param bot
	 * @param gmfEditor
	 * @param startElementName
	 * @param endElementName
	 */
	public static void addSequenceFlow(SWTGefBot bot,final SWTBotGefEditor gmfEditor,final String startElementName,final String endElementName,int targetAnchorPosition){
		final int nbConnection = ModelHelper.getAllItemsOfType(((IGraphicalEditPart)gmfEditor.mainEditPart().part()).resolveSemanticElement(),ProcessPackage.Literals.SEQUENCE_FLOW).size();
		final IGraphicalEditPart gep =  (IGraphicalEditPart) gmfEditor.getEditPart(endElementName).parent().part();
		IFigure figure = gep.getFigure();
		Point targetLocation = null;
		final Rectangle bounds = figure.getBounds();
		switch (targetAnchorPosition) {
		case PositionConstants.NORTH: targetLocation = bounds.getTop().getCopy().translate(-10, 10); break;
		case PositionConstants.SOUTH: targetLocation = bounds.getBottom().getCopy().translate(-10, -10); break;
		case PositionConstants.EAST: targetLocation = bounds.getRight().getCopy().translate(-10, 8);break;
		case PositionConstants.WEST: targetLocation = bounds.getLeft().getCopy().translate(10, 8);break;
		default:throw new RuntimeException("Unhandled position : "+targetAnchorPosition);
		}

		figure.translateToAbsolute(targetLocation);
		gmfEditor.mainEditPart().part().getViewer().findObjectAt(targetLocation);

		selectElementInContextualPaletteAndDragIt(gmfEditor, startElementName, CONTEXTUALPALETTE_SEQUENCEFLOW, targetLocation);
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				return nbConnection + 1 == ModelHelper.getAllItemsOfType(((IGraphicalEditPart)gmfEditor.mainEditPart().part()).resolveSemanticElement(),ProcessPackage.Literals.SEQUENCE_FLOW).size();
			}

			@Override
			public void init(SWTBot bot) {}

			@Override
			public String getFailureMessage() {			
				return "Failed to create the sequenceflow between "+startElementName+" and "+endElementName;
			}
		}, 5000,1000);
	}



	public Point getLocationOfElementInDiagram(SWTGefBot bot,SWTBotGefEditor gmfEditor,String elementName){
		final IGraphicalEditPart gep =  (IGraphicalEditPart) gmfEditor.getEditPart(elementName).parent().part();
		IFigure figure = gep.getFigure();
		Rectangle dest = figure.getBounds().getCopy();
		figure.translateToAbsolute(dest);
		return dest.getLocation();
	}


	/**
	 * 
	 * @param bot
	 * @param taskName
	 * @param newName
	 */
	public static void changeDiagramName(SWTGefBot bot, String taskName, String newName){

		SWTBotEditor botEditor = bot.activeEditor();
		SWTBotGefEditor gmfEditor = bot.gefEditor(botEditor.getTitle());

		//	    AbstractProcess proc = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart(taskName).part()).resolveSemanticElement());
		//	    AbstractProcess diag = ModelHelper.getParentProcess(((IGraphicalEditPart)gmfEditor.getEditPart(proc.getName()).part()).resolveSemanticElement());
		//	    AbstractProcess diag = ModelHelper.getMainProcess(((IGraphicalEditPart)gmfEditor.getEditPart("Step1").part()).resolveSemanticElement());

		gmfEditor.mainEditPart().click();

		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();

		selectTabbedPropertyView(bot, "Diagram");
		bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Edit...")));
		bot.button("Edit...").click();


		// Open new Shell
		bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

		bot.textWithLabel(org.bonitasoft.studio.common.Messages.name,0).setText(newName);
		bot.button(IDialogConstants.OK_LABEL).click();

	}

	/**
	 * select in the overview tree the first transition flow  in the given pool
	 * @param name
	 * @param defaultFlow
	 * @param condition
	 */
	public static void configureSequenceFlow(SWTGefBot bot,String name,final String pool,boolean defaultFlow,String condition,String expressionType){
		bot.activeEditor().setFocus();
		final SWTBotView view = bot.viewById(SWTBotTestUtil.VIEWS_TREE_OVERVIEW);
		view.show();
		view.setFocus();
		final SWTBotTree overviewTree = bot.treeWithId(BONITA_OVERVIEW_TREE_ID);
		bot.waitUntil(Conditions.widgetIsEnabled(overviewTree));
		overviewTree.setFocus();
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				SWTBotTreeItem item = null;
				try{
					item = overviewTree.getTreeItem(pool);
				}catch (TimeoutException e) {
					return false;
				}
				return item != null;
			}

			@Override
			public void init(SWTBot bot) {  }

			@Override
			public String getFailureMessage() {
				StringBuilder res= new StringBuilder();
				for(SWTBotTreeItem sti : overviewTree.getAllItems()){
					res.append(sti.getText()+"\n");
				}
				return "Pool "+pool +" not found in overview tree.\n"+res;
			}
		},10000,500);
		final SWTBotTreeItem treeItem = overviewTree.getTreeItem(pool);
		if(!treeItem.isExpanded()){
			treeItem.expand();
		}
		treeItem.select("Sequence Flow");
		selectTabbedPropertyView(bot, "General");
		bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Condition")));
		bot.textWithLabel("Name").setText(name);
		bot.sleep(1000);
		if (defaultFlow) {
			bot.checkBox("Default flow").select();
		}
		//ExpressionConstants.VARIABLE_TYPE
		if (condition!=null ){
			if(expressionType==ExpressionConstants.VARIABLE_TYPE){
				// edit button
				bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
				setVariableExpression( bot, condition );
			}else if(expressionType==ExpressionConstants.SCRIPT_TYPE){
				// edit button
				bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
				//edit expression shell
				setScriptExpression(bot, "script_"+name, condition, null);
				bot.sleep(600);
			}else if(expressionType==ExpressionConstants.CONSTANT_TYPE){
				bot.textWithLabel("Condition").setText(condition);
				bot.sleep(600);
			} else if (expressionType==ExpressionConstants.CONDITION_TYPE){
				bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
				setComparisonExpression(bot,condition);
			}else{
				Assert.assertTrue("Error: Expression type "+expressionType+  " is not supported.", false);
			}
		}



	}

	/**
	 * @param bot
	 * @param condition
	 * warning : sequence flow properties must be displayed before using this method
	 */
	public static void initializeComparisonExpression(SWTGefBot bot, String condition){
		bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
		setComparisonExpression(bot, condition);
	}

	/**
	 * @param bot
	 * @param condition
	 */
	private static void setComparisonExpression(final SWTGefBot bot, String condition) {
		bot.waitUntil(Conditions.shellIsActive(editExpression));
		bot.tableWithLabel(expressionTypeLabel).select("Comparison");
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				return bot.styledText() != null ;
			}

			@Override
			public void init(SWTBot bot) {}

			@Override
			public String getFailureMessage() {
				return "StyledText is not ready";
			}
		});
		bot.styledText().setFocus();
		bot.styledText().setText(condition);
	}


	public static StyleRange getTextStyleInEditExpressionDialog(final SWTGefBot bot,String expressionType, int line,int column) throws OperationCanceledException, InterruptedException{
		bot.waitUntil(new ICondition() {

			@Override
			public boolean test() throws Exception {
				return bot.styledText() != null ;
			}

			@Override
			public void init(SWTBot bot) {}

			@Override
			public String getFailureMessage() {
				return "StyledText is not ready";
			}
		});
		bot.styledText().setFocus();
		Job.getJobManager().join(ValidationJob.XTEXT_VALIDATION_FAMILY, Repository.NULL_PROGRESS_MONITOR);//Wait for ValidationJob
		Job.getJobManager().join(XtextReconciler.class.getName(), Repository.NULL_PROGRESS_MONITOR);//Wait for Reconciler Job
		bot.sleep(600);
		return bot.styledText().getStyle(line,column);
	}

	public static Keyboard getKeybord(){
		SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
		return KeyboardFactory.getSWTKeyboard();
	}



	public static void pressEnter(){
		getKeybord().pressShortcut(Keystrokes.CR);
	}

	public static void pressDelete(){
		getKeybord().pressShortcut(Keystrokes.DELETE);
	}


	public static void clickOnPenToEditExpression(SWTGefBot bot, int index){
		bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, index).click();
	}

	/**
	 * 
	 * @param scriptName
	 * @param expression
	 * @param returnTypeOfScript
	 */
	public static void setScriptExpression(SWTGefBot bot, String scriptName, String expression, String returnTypeOfScript ){
		bot.waitUntil(Conditions.shellIsActive(editExpression));
		bot.tableWithLabel(expressionTypeLabel).select("Script");
		bot.sleep(1000);
		// set the Script name
		bot.textWithLabel("Name *").setText(scriptName);
		bot.styledText().setText(expression);
		if(returnTypeOfScript!=null){
			bot.comboBoxWithLabel(returnType).setText(returnTypeOfScript);
		}
		bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
		bot.button(IDialogConstants.OK_LABEL).click();
	}

	/**
	 * 
	 * @param bot
	 * @param variableName
	 */
	public static void setVariableExpression(SWTGefBot bot, String variableName ){
		bot.waitUntil(Conditions.shellIsActive(editExpression));
		bot.tableWithLabel(expressionTypeLabel).select("Variable");
		bot.sleep(1000);
		// select the variable
		SWTBotTable tableVar = bot.table(1);
		for(int i =0;i<tableVar.rowCount();i++ ){
			final SWTBotTableItem tableItem = tableVar.getTableItem(i);
			if(tableItem.getText().startsWith(variableName+" --")){
				tableItem.select();
				break;
			}
		}
		bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
		bot.button(IDialogConstants.OK_LABEL).click();
	}

	/** Select an actor in a Human task in the list of Process Actor
	 * 
	 * @param bot
	 * @param actor
	 */
	public static void selectActorInHumanTask(SWTGefBot bot, String actor){
		bot.viewById(VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		selectTabbedPropertyView(bot, "Actors");
		// "Use below actor"
		bot.radio(useTaskActors).click();
		// "Select Actor"
		bot.comboBoxWithLabel(selectActor).setSelection(actor);
	}

	/** Select an actor and define it as an initiator
	 * 
	 * @param bot
	 * @param actor
	 */
	public static void selectInitiatorinActor(SWTGefBot bot, String actor){
		bot.viewById(VIEWS_PROPERTIES_PROCESS_GENERAL).show();
		selectTabbedPropertyView(bot, "Actors");
		if(bot.button(setAsProcessInitiator).isEnabled()){
			int actorIdx = bot.table().indexOf(actor, org.bonitasoft.studio.actors.i18n.Messages.name);
			Assert.assertTrue("Error: no actor "+actor+" available", actorIdx!=-1);
			bot.table().getTableItem(actorIdx).select();
			bot.button(setAsProcessInitiator).click();
		}
	}

	/** Wait for the shell with its title name with the default timeout
	 * 
	 * @param bot
	 * @param shellTitle
	 */
	public static void waitForActiveShell(SWTGefBot bot, String shellTitle){
		bot.waitUntil(Conditions.shellIsActive(shellTitle));
	}

	public static void increasePoolWidth(SWTBotGefEditor editor,String poolName){
		SWTBotGefEditPart poolPart = editor.getEditPart(poolName).parent();
		poolPart.select();

		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) poolPart.part();
		UpdateSizePoolSelectionEditPolicy addPoolSizeElementEditPolicy = (UpdateSizePoolSelectionEditPolicy)graphicalEditPart.getEditPolicy(UpdateSizePoolSelectionEditPolicy.UPDATE_POOL_SIZE_SELECTION_FEEDBACK_ROLE);

		IFigure toolbarFigure = addPoolSizeElementEditPolicy.getFigure(UpdateSizePoolSelectionEditPolicy.ADD_RIGHT);
		Point location = toolbarFigure.getBounds().getCenter().getCopy();
		toolbarFigure.translateToAbsolute(location);
		editor.click(location.x, location.y);
	}

	public static void increasePoolHeight(SWTBotGefEditor editor,String poolName){
		SWTBotGefEditPart poolPart = editor.getEditPart(poolName).parent();
		poolPart.select();

		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) poolPart.part();
		UpdateSizePoolSelectionEditPolicy addPoolSizeElementEditPolicy = (UpdateSizePoolSelectionEditPolicy)graphicalEditPart.getEditPolicy(UpdateSizePoolSelectionEditPolicy.UPDATE_POOL_SIZE_SELECTION_FEEDBACK_ROLE);

		IFigure toolbarFigure = addPoolSizeElementEditPolicy.getFigure(UpdateSizePoolSelectionEditPolicy.ADD_BOTTOM);
		Point location = toolbarFigure.getBounds().getCenter().getCopy();
		toolbarFigure.translateToAbsolute(location);
		editor.click(location.x, location.y);
	}    

	/**
	 * 
	 * @param bot
	 */
	public static void editScriptConnector(SWTGefBot bot, String scriptName, String scriptText){
		editScriptConnector( bot,  scriptName,  scriptText, null );
	}

	/**
	 * 
	 * @param bot
	 */
	public static void editScriptConnector(SWTGefBot bot, String scriptName, String scriptText, String scriptDescription ){
		// 1st page
		editConnector( bot,"Script", "Groovy");

		// 2nde page
		bot.textWithLabel("Name *").setText(scriptName);
		if(scriptDescription!=null){
			bot.textWithLabel("Description").setText(scriptDescription);
		}
		Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
		bot.button(IDialogConstants.NEXT_LABEL).click();

		// 3th page
		bot.toolbarButtonWithId(SWTBOT_ID_EDITBUTTON).click();
		bot.waitUntil(Conditions.widgetIsEnabled(bot.textWithLabel("Name *")));
		bot.textWithLabel("Name *").setText(scriptName);
		bot.styledText().setText(scriptText);
		bot.button(IDialogConstants.OK_LABEL).click();
		//Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
		bot.button(IDialogConstants.NEXT_LABEL).click();

		// 4th page
		Assert.assertTrue("Error : finish button is not enable in Connectors Wizard.", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
		bot.button(IDialogConstants.FINISH_LABEL).click();
	}

	/** Set the connector type and the select the connector specified in the parameters
	 * 
	 * @param bot
	 * @param connectorType
	 * @param connectorTool
	 */
	public static void editConnector(SWTGefBot bot, String connectorType, String connectorTool){

		bot.waitUntil(Conditions.shellIsActive("Connectors"));
//		SWTBotTree tree = bot.tree();
//		tree.expandNode(connectorType);
//		SWTBotTreeItem theItem = tree.getTreeItem(connectorType);
//		Assert.assertNotNull("Error : No item "+connectorType+" found in the tree.", theItem);
//		for( SWTBotTreeItem item : theItem.getItems()){
//			System.out.println("item = "+item.getText());
//			if(item.getText().startsWith(connectorTool)){
//				item.select();
//				item.click();
//				break;
//			}
//		}
		bot.text().setText(connectorTool);
		bot.table().select(0);
		Assert.assertTrue("Error : No "+ connectorTool +" "+connectorType +" found in the connector list", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());

		bot.button(IDialogConstants.NEXT_LABEL).click();

	}

	public static void editEmailConnector(SWTGefBot bot, String emailName, String emailDescription, String from, String to, String subject, String message ) {
		// 1st page
		editConnector( bot,"Messaging", "Email");

		// 2nde page
		bot.textWithLabel("Name *").setText(emailName);
		if(emailDescription!=null && !emailDescription.isEmpty()){
			bot.textWithLabel("Description").setText(emailDescription);
		}
		Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
		bot.button(IDialogConstants.NEXT_LABEL).click();

		// 3th page
		Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
		bot.button(IDialogConstants.NEXT_LABEL).click();

		// 4th page
		bot.textWithLabel("From *").setText(from);
		bot.textWithLabel("To *").setText(to);
		bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)));
		Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
		bot.button(IDialogConstants.NEXT_LABEL).click();

		// 5th page
		bot.textWithLabel("Subject *").setText(subject);
		if(message!=null && !message.isEmpty()){
			bot.textWithLabel("Message").setText(message);
		}
		bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)));
		Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.", bot.button(IDialogConstants.NEXT_LABEL).isEnabled());
		bot.button(IDialogConstants.NEXT_LABEL).click();

		// 6th page
		Assert.assertTrue("Error : Next button is not enable in Connectors Wizard.", bot.button(IDialogConstants.FINISH_LABEL).isEnabled());
		bot.button(IDialogConstants.FINISH_LABEL).click();


	}

	public static Point computeTargetLocation(SWTBotGefEditor gmfEditor, String sourceElement, int position) {
		SWTBotGefEditPart gep = gmfEditor.getEditPart(sourceElement);
		Assert.assertNotNull("Error: No Edit Part \'"+sourceElement+"\' found.", gep);
		SWTBotGefEditPart element = gep.parent();
		IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) element.part();

		switch (position) {
		case PositionConstants.NORTH: return graphicalEditPart.getFigure().getBounds().getTop().getCopy().translate(-20, -70);
		case PositionConstants.SOUTH: return graphicalEditPart.getFigure().getBounds().getBottom().getCopy().translate(-20, 70);
		case PositionConstants.WEST: return graphicalEditPart.getFigure().getBounds().getLeft().getCopy().translate(-80, 10);
		case PositionConstants.EAST: return graphicalEditPart.getFigure().getBounds().getRight().getCopy().translate(80,10);
		case PositionConstants.NORTH_EAST:return graphicalEditPart.getFigure().getBounds().getTopRight().getCopy().translate(80, -70);
		case PositionConstants.NORTH_WEST:return graphicalEditPart.getFigure().getBounds().getTopLeft().getCopy().translate(-80, 70);
		case PositionConstants.SOUTH_EAST:return graphicalEditPart.getFigure().getBounds().getBottomRight().getCopy().translate(-80, 70);
		case PositionConstants.SOUTH_WEST:return graphicalEditPart.getFigure().getBounds().getBottomLeft().getCopy().translate(80,-70);
		default: throw new RuntimeException("Invalid position specified") ;
		}

	}

	public static void setOutputStorageExpressionByName(SWTBot bot,String storageExpressionName,String returnType, int index) {
		bot.toolbarButtonWithId(SWTBOT_ID_EXPRESSIONVIEWER_DROPDOWN,index).click();
		final SWTBot proposalBot = bot.shellWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_SHELL).bot();
		final SWTBotTable proposalTAble = proposalBot.tableWithId(SWTBOT_ID_EXPRESSIONVIEWER_PROPOSAL_TABLE);
		int row = proposalTAble.indexOf(storageExpressionName+" -- "+returnType, 0);
		if(row == -1){
			throw new WidgetNotFoundException(storageExpressionName + " not found in proposals");
		}
		proposalTAble.select(row);
		SWTBotTestUtil.pressEnter();
	}




}
