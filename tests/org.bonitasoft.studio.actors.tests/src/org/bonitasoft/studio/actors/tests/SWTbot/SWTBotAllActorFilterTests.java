package org.bonitasoft.studio.actors.tests.SWTbot;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.util.test.BonitaTestSuite;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author aurelie
 *
 */
@RunWith(BonitaTestSuite.class)
@Suite.SuiteClasses({
	 ActorDefinitionTranslationsTest.class,
	    ActorFilterDefinitionTest.class,
	    ActorFilterDefinitionWizardPageTest.class,
	    ActorFilterEditionTest.class,
	    ActorFilterImplementationTest.class
})

public class SWTBotAllActorFilterTests {
	  @BeforeClass
	    public static void setUp() {
	        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
	    //    WebBrowserUIPlugin.getInstance().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
	        FileActionDialog.setDisablePopup(true);
	        
	    }
}
