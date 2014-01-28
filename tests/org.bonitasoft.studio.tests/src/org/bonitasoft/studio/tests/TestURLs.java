/**
 * Copyright (C) 2011-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import junit.framework.TestCase;

import org.bonitasoft.studio.application.actions.OpenConsoleCommand;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 *
 */
public class TestURLs extends TestCase {

    public void testRunURl() throws ExecutionException, MalformedURLException, UnsupportedEncodingException{
        NewDiagramCommandHandler cmd =  new NewDiagramCommandHandler();
        cmd.execute(null);
        MainProcess diagram = cmd.getNewDiagramFileStore().getContent();
        final RunProcessCommand runProcessCommand = new RunProcessCommand((AbstractProcess) diagram.getElements().get(0),true);
        runProcessCommand.execute(null);
        URL url = runProcessCommand.getUrl();
        assertNotNull("Timeout exceeded while retrieveing run url",url);
        final String stringURL = url.toString();
        assertTrue("wrong Run url, it doesn't contains bonita/portal/homepage; current: " +stringURL, stringURL.contains(URLEncoder.encode("bonita/portal/homepage","UTF-8")));
        assertTrue("wrong Run url, it doesn't contain form or process; current: " +stringURL, stringURL.contains(URLEncoder.encode("form=","UTF-8")) && stringURL.contains(URLEncoder.encode("process=","UTF-8")));
        assertTrue("wrong run URL, it doesn't contains mode=app; current: " +stringURL, stringURL.contains(URLEncoder.encode("mode=app","UTF-8")));
    }

    public void testUserXPURl() throws ExecutionException, MalformedURLException, UnsupportedEncodingException{
        new NewDiagramCommandHandler().execute(null);
        final OpenConsoleCommand cmd = new OpenConsoleCommand(true);
        cmd.execute(null);
        URL url = cmd.getURL();
        assertNotNull("Timeout exceeded while retrieveing console url",url);
        final String stringURL = url.toString();
        assertTrue("wrong user User XP url, current:" +stringURL, stringURL.contains(URLEncoder.encode("bonita/portal/homepage","UTF-8")));
    }

    @Override
    protected void tearDown() throws Exception {
    	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

}
