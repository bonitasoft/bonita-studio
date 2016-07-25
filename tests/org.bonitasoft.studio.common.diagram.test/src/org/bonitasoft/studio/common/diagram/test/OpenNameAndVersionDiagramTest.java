package org.bonitasoft.studio.common.diagram.test;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.test.swtbot.util.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class OpenNameAndVersionDiagramTest extends SWTBotEclipseTestCase implements SWTBotConstants {

    @Test
    public void testOpenNameAndVersionDiagram() {

        SWTBotTestUtil.pressEnter();

        final SWTWorkbenchBot workbenchBot = new SWTWorkbenchBot();

        SWTBotTestUtil.createNewDiagram(workbenchBot);
        SWTBotTestUtil.createNewDiagram(workbenchBot);

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();

        SWTBotTestUtil.selectTabbedPropertyView(bot, "Pool");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Edit...")));
        bot.button("Edit...").click();

        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        SWTBotText t = bot.textWithLabel("Name");
        t.setText("Pool");

        final SWTBotButton okBtton = bot.button(IDialogConstants.OK_LABEL);

        assertFalse("Ok button is enabled when rename the pool name but the pool name already exist", okBtton.isEnabled());

        t = bot.textWithLabel("Name");
        t.setText("Pool2"+System.currentTimeMillis());

        assertTrue("Ok button is disabled when rename the pool name but the pool name doesn't already exist", okBtton.isEnabled());

        okBtton.click();
    }

}
