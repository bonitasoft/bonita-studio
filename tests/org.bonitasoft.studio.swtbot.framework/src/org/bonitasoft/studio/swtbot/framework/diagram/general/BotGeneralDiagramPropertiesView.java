/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general;

import org.bonitasoft.studio.swtbot.framework.BotBase;
import org.bonitasoft.studio.swtbot.framework.BotQAUtil;
import org.bonitasoft.studio.swtbot.framework.diagram.general.actors.BotActorAssignementPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.actors.BotActorDefinitionPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.general.BotDiagramPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.general.BotGeneralPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.iteration.BotReccurencePropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.lane.BotLanePropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.pool.BotPoolPropertySection;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * General Diagram properties view.
 *
 * @author Joachim Segala
 */
public class BotGeneralDiagramPropertiesView extends BotBase {

    public BotGeneralDiagramPropertiesView(final SWTGefBot bot) {
        super(bot);
    }

    /**
     * Select the general tab.
     *
     * @return
     */
    public BotGeneralPropertySection selectGeneralTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "General");
        return new BotGeneralPropertySection(bot);
    }

    public BotDiagramPropertySection selectDiagramTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Diagram");
        return new BotDiagramPropertySection(bot);
    }

    public void selectPortalTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Portal");
        //TODO:
    }

    /**
     * Select the pool tab.
     *
     * @return
     */
    public BotPoolPropertySection selectPoolTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Pool");
        return new BotPoolPropertySection(bot);
    }

    /**
     * Select the lane tab.
     *
     * @return
     */
    public BotLanePropertySection selectLaneTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Lane");
        return new BotLanePropertySection(bot);
    }

    public BotActorDefinitionPropertySection selectActorsDefinitionTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Actors");
        return new BotActorDefinitionPropertySection(bot);
    }

    public BotActorAssignementPropertySection selectActorAssignementTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Actors");
        return new BotActorAssignementPropertySection(bot);
    }

    public BotReccurencePropertySection selectIterationTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Iteration");
        return new BotReccurencePropertySection(bot);
    }

    public BotCallActivityMappingPropertySection selectMappingTab() {
        BotQAUtil.selectTabbedPropertyView(bot, "Mapping");
        return new BotCallActivityMappingPropertySection(bot);
    }



}
