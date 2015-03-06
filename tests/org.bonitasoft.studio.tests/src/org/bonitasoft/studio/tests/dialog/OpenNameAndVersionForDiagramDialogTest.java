/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Aurelien Pupier
 *         Replace me with unit test
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class OpenNameAndVersionForDiagramDialogTest extends SWTBotEclipseTestCase {

    //    @Before
    //    public void openDialog() {
    //        Display.getDefault().asyncExec(new Runnable() {
    //
    //            @Override
    //            public void run() {
    //                final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    //                final MainProcess mp = ProcessFactory.eINSTANCE.createMainProcess();
    //                mp.setName("TestName");
    //                mp.setVersion("test.version");
    //                final Pool pool = ProcessFactory.eINSTANCE.createPool();
    //                pool.setName("Pool");
    //                pool.setVersion("1.0");
    //                mp.getElements().add(pool);
    //
    //                final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
    //                final IRepositoryFileStore fileStore = store.createRepositoryFileStore(NamingUtils.toDiagramFilename(mp));
    //                fileStore.save(mp);
    //                final OpenNameAndVersionDialog dialog = new OpenNameAndVersionForDiagramDialog(shell, mp, RepositoryManager.getInstance().getRepositoryStore(
    //                        DiagramRepositoryStore.class));
    //                dialog.open();
    //            }
    //        });
    //        bot.waitUntil(Conditions.shellIsActive(Messages.openNameAndVersionDialogTitle));
    //    }

    @Test
    @Ignore
    public void testForbiddenSameNameDifferentCaseForDiagram() {
        bot.text("TestName").setText("testname");
        bot.sleep(500);
        assertFalse("We allow to duplicate a diagram with just different case although it doesn't work on windows",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    @Ignore
    public void testForbiddenSameVersionDifferentCaseForDiagram() {
        bot.text("test.version").setText("Test.version");
        bot.sleep(500);
        assertFalse("We allow to duplicate a diagram with just different case although it doesn't work on windows",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    @Ignore
    public void testAllowDifferentVersionForDiagram() {
        bot.text("test.version").setText("wouhou.version");
        bot.sleep(500);
        assertTrue("We forbid to duplicate a diagram with different version",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    @Ignore
    public void testAllowDifferentNameForDiagram() {
        bot.text("TestName").setText("wouhouname");
        bot.sleep(500);
        assertTrue("We forbid to duplicate a diagram with different name",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    @Ignore
    public void testForbiddenInvalidCharacterInNameForPool() {
        setDiagramOk();
        bot.text("Pool").setText("test:name/invalid");
        bot.sleep(500);
        assertTrue("We now allow UTF-8 characters in pool names",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    @Ignore
    public void testForbiddenInvalidCharacterInVersionForPool() {
        setDiagramOk();
        bot.text("1.0").setText("1.0?beta");
        bot.sleep(500);
        assertTrue("We now allow UTF-8 characters in pool versions",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    @Test
    @Ignore
    public void testAllowDifferentVersionForPool() {
        setDiagramOk();
        bot.text("1.0").setText("wouhou.version");
        bot.sleep(500);
        assertTrue("We forbid to duplicate a diagram with different version",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

    private void setDiagramOk() {
        bot.text("TestName").setText("TestNameDiagram");
        bot.text("test.version").setText("1.0");
        bot.sleep(500);
    }

    @Test
    @Ignore
    public void testAllowDifferentNameForPool() {
        setDiagramOk();
        bot.text("Pool").setText("wouhouname");
        bot.sleep(500);
        assertTrue("We forbid to duplicate a diagram with different name",
                bot.button(IDialogConstants.OK_LABEL).isEnabled());
        bot.button(IDialogConstants.CANCEL_LABEL).click();
    }

}
