/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram;

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.diagram.application.BotApplicationDiagramPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.data.BotDataDiagramPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.execution.BotExecutionDiagramPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.general.BotGeneralDiagramPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.validation.BotValidationPropertiesView;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * Diagram properties view folder.
 *
 * @author Joachim Segala
 */
public class BotProcessDiagramPropertiesViewFolder extends BotBase {

    public BotProcessDiagramPropertiesViewFolder(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Select the general tab.
     *
     * @return
     */
    public BotGeneralDiagramPropertiesView selectGeneralTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        return new BotGeneralDiagramPropertiesView(bot);
    }
    
    public BotDataDiagramPropertiesView selectDataTab(){
    	bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_DATA).show();
    	return new BotDataDiagramPropertiesView(bot);
    }
    
    public BotExecutionDiagramPropertiesView selectExecutionTab(){
    	bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_EXECUTION).show();
    	return new BotExecutionDiagramPropertiesView(bot);
    }
    

    /**
     * Select the application tab.
     *
     * @return
     */
    public BotApplicationDiagramPropertiesView selectApplicationTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPLICATION).show();
        return new BotApplicationDiagramPropertiesView(bot);
    }

    public void selectAppearanceTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_APPEARANCE).show();
        //TODO:
    }

    public void selectSimulationTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_SIMULATION).show();
        //TODO:
    }

    public BotValidationPropertiesView selectValidationStatusTab() {
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_VALIDATION_STATUS).show();
        return new BotValidationPropertiesView(bot);
    }
}
