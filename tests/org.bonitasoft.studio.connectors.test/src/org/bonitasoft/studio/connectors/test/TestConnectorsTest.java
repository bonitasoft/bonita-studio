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
package org.bonitasoft.studio.connectors.test;


import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 * @author Baptiste Mesta
 *
 */
public class TestConnectorsTest extends SWTBotGefTestCase {


    private static long timeout;


    @BeforeClass
    public static void setUpBeforeClass() {
        timeout = SWTBotPreferences.TIMEOUT;
        //more timeout because the dialog takes time to open
        SWTBotPreferences.TIMEOUT = 20000;
    }

    @AfterClass
    public static void tearDownAfterClass() {
        SWTBotPreferences.TIMEOUT = timeout;
    }

    /**
     * This test ensures that the Test connector work with groovy connector
     * @throws InterruptedException
     */
    @Test
    @Ignore
    public void testTestGroovyConnector() throws InterruptedException {

        //        SWTBotMenu menu = bot.menu("Connectors");
        //        menu.menu("Test a Connector").click();
        //
        //        SWTBotShell shell = bot.shell("Test a Connector");
        //        shell.activate();
        //        bot.tree().select("Connectors");
        //        bot.button("Next >").click();
        //        bot.tree().expandNode("Scripting").select("Groovy -- Execute a Groovy script").click();
        //
        //        ConnectorConfRepositoryStore ccrs = (ConnectorConfRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class);
        //        fail();
        //		if(ccrs.getConnectorConfigurationsFor("GroovyConnector", "1.0").size() > 0)
        //		bot.button("Next >").click();//pass through the page asking to reuse configuration
        //		bot.button("Next >").click();
        //		bot.text().setText("\"test string\"");
        //		bot.button("Evaluate").click();
        //		bot.shell("Results").activate();
        //		System.out.println(bot.text().getText());
        //		assertEquals("test string",bot.text().getText());
        //		bot.button("Back").click();
        //		bot.button("Close").click();
    }




}

