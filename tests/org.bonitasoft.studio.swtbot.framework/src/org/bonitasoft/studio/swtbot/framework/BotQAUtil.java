/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework;


import static org.bonitasoft.studio.data.i18n.Messages.addData;
import static org.bonitasoft.studio.data.i18n.Messages.classLabel;
import static org.bonitasoft.studio.data.i18n.Messages.datatypeLabel;
import static org.bonitasoft.studio.data.i18n.Messages.name;
import static org.bonitasoft.studio.data.i18n.Messages.newVariable;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.editExpression;
import static org.bonitasoft.studio.expression.editor.i18n.Messages.expressionTypeLabel;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType;
import static org.bonitasoft.studio.properties.i18n.Messages.activityType_task;

import java.lang.reflect.Method;
import java.util.List;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
// import org.bonitasoft.studio.properties.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.editPolicies.UpdateSizePoolSelectionEditPolicy;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Widget;
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
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement;
// import org.eclipse.xtext.ui.editor.reconciler.XtextReconciler;
// import org.eclipse.xtext.ui.editor.validation.ValidationJob;
import org.junit.Assert;


public class BotQAUtil implements SWTBotConstants{

    /** Change the type of a Task to be 'Human'
     * @param gmfEditor
     * @param nameTask name of the Task
     */
    private void setTaskAsHuman(final SWTGefBot bot, final SWTBotGefEditor gmfEditor, final String nameTask) {
        gmfEditor.getEditPart(nameTask).click();

        // set the Task as Human Task
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "General");

        // "Task type" , "Human"
        bot.comboBoxWithLabel(activityType).setSelection(activityType_task);
    }

    /** Add and set a new variable in the data entry of the General tab of a Task
     * @param varName name of the variable
     * @param varType type of the variable : "Text", "Integer", "String", "Boolean", etc...
     * @param autoGenerateForm  BOS-SP only : true if the checkBox must be selected, else false
     */
    private void setNewVariable(final SWTGefBot bot, final String varName, final String varType, final boolean autoGenerateForm ) {
        bot.button("Add...").click();

        // open shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(newVariable));

        // "Name"
        bot.textWithLabel(name).setText(varName);

        // "Data type"
        bot.comboBoxWithLabel(datatypeLabel).setSelection(varType);

        if(SWTBotTestUtil.testingBosSp()){
            final SWTBotCheckBox cb = bot.checkBox("Auto-generate form");
            if(cb.isChecked() && !autoGenerateForm){
                cb.deselect();
            }else if(!cb.isChecked() && autoGenerateForm){
                cb.select();
            }
        }

        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    public static SWTBotGefEditPart getPartRecursively(final SWTBotGefEditPart from, final String label) {
        for (final SWTBotGefEditPart child : from.children()) {
            final Element model = (Element) ((IGraphicalEditPart)child.part()).resolveSemanticElement();
            if (model.getName().equals(label)) {
                return child;
            }
        }
        SWTBotGefEditPart res = null;
        for (final SWTBotGefEditPart child : from.children()) {
            res = getPartRecursively(child, label);
            if (res != null) {
                return res;
            }
        }
        return res;
    }



    public static void movepositions(final SWTGefBot bot, SWTBotGefEditor gmfEditor,final String sourcelane,final String destlane, final String element, final int j, final int i)
    {

        final SWTBotEditor botEditor = bot.activeEditor();
        gmfEditor = bot.gefEditor(botEditor.getTitle());


        final SWTBotGefEditPart lane2 = gmfEditor.getEditPart(sourcelane);
        lane2.parent().select().resize(PositionConstants.NORTH, 100, 150);

        // compute target destination
        final SWTBotGefEditPart lane3 = gmfEditor.getEditPart(destlane);

        // move step2 to lane2

        final SWTBotGefEditPart ele = gmfEditor.getEditPart(element).parent();
        ele.select();


        // move step2 to lane2

        SWTBotGefEditPart step = gmfEditor.getEditPart(element).parent();
        step.select();


        IFigure figure = ((GraphicalEditPart)step.part()).getFigure();
        final Rectangle dest = figure.getBounds().getCopy();
        figure.translateToAbsolute(dest);

        gmfEditor.drag( step, dest.x+j  , dest.y+i);

        step = gmfEditor.getEditPart(element).parent();
        step.select();
        figure = ((GraphicalEditPart)step.part()).getFigure();
        final Rectangle targetdest = figure.getBounds().getCopy();
        figure.translateToAbsolute(targetdest);

        Assert.assertTrue("Move has failed",!targetdest.equals(dest));

        bot.waitUntil(new ICondition() {
            @Override
            public boolean test() throws Exception {
                return  getPartRecursively(lane3.parent(), element) != null;
            }

            @Override
            public void init(final SWTBot bot) {
            }

            @Override
            public String getFailureMessage() {
                return "element is not in sourcelane (unable to move the step)";
            }
        });

    }

    public static void addemailconnector(final SWTGefBot bot, final String name, final String from, final String to, final String subject, final String content)
    {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Connectors");
        bot.button("Add...").click();
        bot.waitUntil(Conditions.shellIsActive("Connectors"));

        bot.tree().setFocus();
        final SWTBotTreeItem wss = bot.tree().getTreeItem("Messaging").expand();

        String emailNode = null;
        for (final String child : wss.getNodes()) {


            if (child.contains("Email")) {
                emailNode = child;
            }
        }
        wss.getNode(emailNode).select();
        bot.button(IDialogConstants.NEXT_LABEL).click();

        bot.textWithLabel("Name *").setText(name);
        bot.button(IDialogConstants.NEXT_LABEL).click();

        bot.button(IDialogConstants.NEXT_LABEL).click();

        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 0).setText(from);
        bot.textWithId(SWTBotConstants.SWTBOT_ID_EXPRESSIONVIEWER_TEXT, 1).setText(to);
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.NEXT_LABEL)));
        bot.button(IDialogConstants.NEXT_LABEL).click();

        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot,  "subject",  subject,  null );

        //bot.button(IDialogConstants.OK_LABEL).click();

        bot.styledText().setText(content);

        /*bot.sleep(2000);
            bot.link(0).click();
            bot.sleep(2000);
            bot.button("Yes").click();
            bot.sleep(2000);

            bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 1).click();
            SWTBotTestUtil.setScriptExpression( bot,  "body",  content,  null );
         */

        bot.sleep(5000);

        bot.button(IDialogConstants.NEXT_LABEL).click();
        bot.button(IDialogConstants.FINISH_LABEL).click();

        bot.activeEditor().save();
        //DiagramEditPart dpart = (DiagramEditPart) bot.gefEditor(bot.activeEditor().getTitle()).mainEditPart().part();
        //MainProcess proc = (MainProcess) dpart.resolveSemanticElement();
        //List<Connector> connectors = ModelHelper.getAllItemsOfType(proc, ProcessPackage.Literals.CONNECTOR);
        /*	for(Connector c : connectors){
				if(c.getDefinitionId().equals("email")){
					for(ConnectorParameter p : c.getConfiguration().getParameters()){
						if("message".equals(p.getKey())){


							String htmlContent = ((Expression) p.getExpression()).getContent();
							assertEquals("Invalid html content", HTML_CONTENT, htmlContent.replaceAll(" ","").replaceAll("\\s",""));
						}
					}

		}*/

    }

    /**
     *
     * @param dataName
     * @param defaultValue
     * @param classType
     */
    public void addJavaObjectData(final SWTGefBot bot,final String dataName, final String classType){


        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();

        // DATA
        SWTBotTestUtil.selectTabbedPropertyView(bot, "Data");

        // Add a new variable
        bot.button(addData).click();
        bot.waitUntil(Conditions.shellIsActive(newVariable));
        bot.shell(newVariable);

        // set global value
        bot.sleep(10000);

        bot.waitUntilWidgetAppears(Conditions.widgetIsEnabled(bot.textWithLabel(name)));
        bot.textWithLabel(name).setText(dataName);
        bot.comboBoxWithLabel(datatypeLabel).setSelection("Java Object");
        bot.textWithLabel(classLabel).setText(classType);
    }


    /*
    private void stepGateWayXor(SWTGefBot bot, SWTBotGefEditor gmfEditor, String sourcestep){
        //  SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, "Start1",1,new Point(200,100));
      	SWTBotTestUtil.selectElementInContextualPaletteAndDragIt(gmfEditor, sourcestep,SWTBotTestUtil.CONTEXTUALPALETTE_GATEWAY,PositionConstants.EAST);
      	bot.viewById(SWTBotTestUtil. VIEWS_PROPERTIES_PROCESS_GENERAL).show();
      	SWTBotTestUtil.selectTabbedPropertyView(bot,"General");
        bot.comboBoxWithLabel(Messages.gatewayType).setSelection(Messages.gatwetypeXor);

      }


     */

    private void finalEvent(final SWTGefBot bot, final SWTBotGefEditor gmfEditor,  final String source){
        gmfEditor.activateTool("End");
        final Point targetLocation = SWTBotTestUtil.computeTargetLocation(gmfEditor,source,PositionConstants.SOUTH);
        gmfEditor.click(targetLocation.x,targetLocation.y);
        SWTBotTestUtil.addSequenceFlow(bot, gmfEditor, source, "End1",PositionConstants.WEST);
        //SWTBotTestUtil.configureSequenceFlow(bot,"sequenceFlow4","Web Purchase", false, null, ExpressionConstants.VARIABLE_TYPE);
    }


    private void terminateEvent(final SWTGefBot bot, final SWTBotGefEditor gmfEditor,  final String source){

        gmfEditor.activateTool("Terminate end event");

        final Point targetLocation = SWTBotTestUtil.computeTargetLocation(gmfEditor,source,PositionConstants.EAST);
        gmfEditor.click(targetLocation.x,targetLocation.y);
        SWTBotTestUtil.addSequenceFlow(bot, gmfEditor, source, "End3",PositionConstants.WEST);
        //SWTBotTestUtil.configureSequenceFlow(bot,"sequenceFlow4","Web Purchase", false, null, ExpressionConstants.VARIABLE_TYPE);
    }


    /**
     * Create a new Form and save it
     * @param bot
     * @param gmfEditor
     * @param nameOfStepOnwhichCreateTheForm
     * @return
     */
    public static SWTBotEditor createFormWhenOnAProcessWithStep1(final SWTGefBot bot,
            final SWTBotGefEditor gmfEditor, final String nameOfStepOnwhichCreateTheForm) {
        final SWTBotGefEditPart part = gmfEditor.getEditPart(nameOfStepOnwhichCreateTheForm);
        if(part!=null) {
            System.out.println("wow part is not null" + part.toString());
        } else {
            System.out.println("part is null");
        }

        try {
            System.out.println("tosting is:" + part.toString() + "source size is:" + part.sourceConnections().size() + "target size is:" + part.targetConnections().size() + "parent" + part.parent().toString());
        }
        catch(final Exception e) {
            System.out.println("Exception is:" + e.getMessage());
        }

        part.click();

        //part.focus();


        bot.viewById("org.bonitasoft.studio.views.properties.application").show();
        bot.viewById("org.bonitasoft.studio.views.properties.application").setFocus();

        SWTBotTestUtil.selectTabbedPropertyView(bot, "Pageflow");
        final SWTBotView properties = bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION);
        properties.bot().button("Add...").click();
        bot.waitUntil(Conditions.shellIsActive("Add form..."));
        bot.button(IDialogConstants.NEXT_LABEL).click();

        bot.button("Unselect all").click();

        bot.button(IDialogConstants.FINISH_LABEL).click();
        final SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.save();
        return activeEditor;
    }



    /** Add and set a new variable in the data entry of the General tab of a Task
     * @param varName name of the variable
     * @param varType type of the variable : "Text", "Integer", "String", "Boolean", etc...
     * @param autoGenerateForm  BOS-SP only : true if the checkBox must be selected, else false
     */
    private void setNewVariablewithdefault(final SWTGefBot bot, final String varName, final String varType, final boolean autoGenerateForm, final String defname, final String script, final String returntype ) {
        bot.button("Add...").click();

        // open shell "New variable"
        bot.waitUntil(Conditions.shellIsActive(newVariable));

        // "Name"
        bot.textWithLabel(name).setText(varName);

        // "Data type"
        bot.comboBoxWithLabel(datatypeLabel).setSelection(varType);

        if(SWTBotTestUtil.testingBosSp()){
            final SWTBotCheckBox cb = bot.checkBox("Auto-generate form");
            if(cb.isChecked() && !autoGenerateForm){
                cb.deselect();
            }else if(!cb.isChecked() && autoGenerateForm){
                cb.select();
            }
        }

        bot.toolbarButtonWithId(ExpressionViewer.SWTBOT_ID_EDITBUTTON, 0).click();
        SWTBotTestUtil.setScriptExpression( bot,  defname,  script,  returntype );
        bot.waitUntil(Conditions.shellIsActive(newVariable));

        bot.button(IDialogConstants.FINISH_LABEL).click();
    }

    /** Select an actor in a Human task in the list of Process Actor
     *
     * @param bot
     * @param actor
     *
	public static void selectActorInLane(SWTGefBot bot, String actor){
		SWTBotTestUtil.selectTabbedPropertyView(bot, "Actors");
		// "Use below actor"
		//bot.radio(useTaskActors).click();
		// "Select Actor"
		//bot.waitUntil(Conditions.widgetIsEnabled(bot.comboBoxWithLabel(selectActor).setSelection(actor)));
		bot.comboBoxWithLabel(selectActor).setSelection(actor);
	}*/

    /**
     *
     * @param scriptName
     * @param expression
     * @param returnTypeOfScript
     */
    public static void setScriptExpression1(final SWTGefBot bot, final String scriptName, final String expression ){
        bot.waitUntil(Conditions.shellIsActive(editExpression));
        bot.tableWithLabel(expressionTypeLabel).select("Script");
        bot.sleep(1000);
        // set the Script name
        bot.textWithLabel("Name *").setText(scriptName);
        bot.styledText().setText(expression);

        bot.waitUntil(Conditions.widgetIsEnabled(bot.button(IDialogConstants.OK_LABEL)));
        bot.button(IDialogConstants.OK_LABEL).click();
    }

    public static void increaseLaneHeight(final SWTBotGefEditor editor,final String laneName){
        final SWTBotGefEditPart lanePart = editor.getEditPart(laneName).parent();
        lanePart.select();

        final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) lanePart.part();
        final UpdateSizePoolSelectionEditPolicy addPoolSizeElementEditPolicy = (UpdateSizePoolSelectionEditPolicy)graphicalEditPart.getEditPolicy(UpdateSizePoolSelectionEditPolicy.UPDATE_POOL_SIZE_SELECTION_FEEDBACK_ROLE);

        final IFigure toolbarFigure = addPoolSizeElementEditPolicy.getFigure(UpdateSizePoolSelectionEditPolicy.ADD_BOTTOM);
        final Point location = toolbarFigure.getBounds().getCenter().getCopy();
        toolbarFigure.translateToAbsolute(location);
        editor.click(location.x, location.y);
    }

    public static void printPossibleWidgets(final SWTGefBot bot) {
        final List<? extends Widget> widgets = bot.widgets(WidgetMatcherFactory.withRegex(".*"));
        for (final Widget widget : widgets) {
            System.out.println(widget.getClass());
        }
    }

    public static void selectTabbedPropertyView(final SWTBot viewerBot, final String tabeText) {
        viewerBot.sleep(1000); //DO NOT REMOVE ME //TODO : find a way to remove bot.sleep, can save almost one minute
        UIThreadRunnable.syncExec(new VoidResult() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.swtbot.swt.finder.results.VoidResult#run()
             */
            @Override
            @SuppressWarnings("restriction")
            public void run() {
                try {
                    final List<? extends Widget> widgets = viewerBot.getFinder().findControls(WidgetMatcherFactory.widgetOfType(TabbedPropertyList.class));
                    Assert.assertTrue("No widget of type " + TabbedPropertyList.class.getName() + " has been found", widgets.size() > 0);
                    final TabbedPropertyList tabbedPropertyList = (TabbedPropertyList) widgets.get(0);
                    final StringBuilder availableTabbedPropertySection = new StringBuilder("");
                    int i = 0;
                    boolean found = false;
                    ListElement currentTab;
                    final Method selectMethod = TabbedPropertyList.class.getDeclaredMethod("select", new Class[] { int.class });
                    selectMethod.setAccessible(true);
                    do {
                        currentTab = (org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement) tabbedPropertyList.getElementAt(i);
                        if (currentTab != null) {
                            final String label = currentTab.getTabItem().getText();
                            availableTabbedPropertySection.append(label);
                            if (label.equals(tabeText)) {
                                found = true;
                                selectMethod.invoke(tabbedPropertyList, i);
                            }
                        }
                        i++;
                    } while (currentTab != null && !found);
                    if (!found) {
                        throw new WidgetNotFoundException("Can't find a tab item with " + tabeText + " label. Only found: "
                                + availableTabbedPropertySection.toString());
                    }
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                    throw new WidgetNotFoundException("Can't find a tab item with " + tabeText + " label");
                }
            }
        });
    }
}