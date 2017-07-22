package org.bonitasoft.studio.tests.dialog;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.swtbot.framework.SWTBotTestUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class OpenNameAndVersionDiagramForDiagramTest  implements SWTBotConstants {

    private SWTGefBot bot = new SWTGefBot();
    
    @Test
    public void testOpenNameAndVersionDiagramForDiagram() {

        final SWTWorkbenchBot workbenchBot = new SWTWorkbenchBot();


        SWTBotTestUtil.createNewDiagram(workbenchBot);
        SWTBotTestUtil.createNewDiagram(workbenchBot);

        final SWTGefBot gefBot = new SWTGefBot();
        final SWTBotEditor botEditor = gefBot.activeEditor();
        final SWTBotGefEditor gmfEditor = gefBot.gefEditor(botEditor.getTitle());
        gmfEditor.mainEditPart().click();

        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).show();
        bot.viewById(SWTBotTestUtil.VIEWS_PROPERTIES_PROCESS_GENERAL).setFocus();

        SWTBotTestUtil.selectTabbedPropertyView(bot, "Diagram");
        bot.waitUntil(Conditions.widgetIsEnabled(bot.button("Edit...")));
        bot.button("Edit...").click();

        bot.waitUntil(Conditions.shellIsActive(org.bonitasoft.studio.common.Messages.openNameAndVersionDialogTitle));

        SWTBotText t = bot.textWithLabelInGroup("Name", "Pools");
        t.setText("Pool");

        final SWTBotButton okBtton = bot.button(IDialogConstants.OK_LABEL);

        assertFalse("Ok button is enabled when rename the pool name but the pool name already exist", okBtton.isEnabled());

        t = bot.textWithLabelInGroup("Name", "Pools");
        t.setText("Pool2"+System.currentTimeMillis());

        assertTrue("Ok button is disabled when rename the pool name but the pool name doesn't already exist",
                okBtton.isEnabled());

        okBtton.click();
    }

}
