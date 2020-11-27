/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.swtbot.framework.diagram.general;

import org.bonitasoft.studio.swtbot.framework.BotPropertiesView;
import org.bonitasoft.studio.swtbot.framework.diagram.general.actors.BotActorAssignementPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.actors.BotActorDefinitionPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.general.BotDiagramPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.general.BotGeneralPropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.iteration.BotReccurencePropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.lane.BotLanePropertySection;
import org.bonitasoft.studio.swtbot.framework.diagram.general.pool.BotPoolPropertySection;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;

/**
 * General Diagram properties view.
 *
 * @author Joachim Segala
 */
public class BotGeneralDiagramPropertiesView extends BotPropertiesView {

    public BotGeneralDiagramPropertiesView(final SWTGefBot bot, SWTBotView botView) {
        super(bot, botView);
    }

    /**
     * Select the general tab.
     *
     * @return
     */
    public BotGeneralPropertySection selectGeneralTab() {
        selectTab("General");
        return new BotGeneralPropertySection(bot);
    }

    public BotDiagramPropertySection selectDiagramTab() {
        selectTab("Diagram");
        return new BotDiagramPropertySection(bot);
    }

    public void selectPortalTab() {
        selectTab("Portal");
    }

    /**
     * Select the pool tab.
     *
     * @return
     */
    public BotPoolPropertySection selectPoolTab() {
        selectTab("Pool");
        return new BotPoolPropertySection(bot);
    }

    /**
     * Select the lane tab.
     *
     * @return
     */
    public BotLanePropertySection selectLaneTab() {
        selectTab("Lane");
        return new BotLanePropertySection(bot);
    }

    public BotActorDefinitionPropertySection selectActorsDefinitionTab() {
        selectTab("Actors");
        return new BotActorDefinitionPropertySection(bot);
    }

    public BotActorAssignementPropertySection selectActorAssignementTab() {
        selectTab("Actors");
        return new BotActorAssignementPropertySection(bot);
    }

    public BotReccurencePropertySection selectIterationTab() {
        selectTab("Iteration");
        return new BotReccurencePropertySection(bot);
    }

}
