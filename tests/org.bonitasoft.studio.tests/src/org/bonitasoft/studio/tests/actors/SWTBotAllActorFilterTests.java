package org.bonitasoft.studio.tests.actors;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.tests.util.BonitaSuite;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(BonitaSuite.class)
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
        BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE,
                BonitaPreferenceConstants.INTERNAL_BROWSER);
        //    WebBrowserUIPlugin.getInstance().getPreferenceStore().setValue(BonitaPreferenceConstants.CONSOLE_BROWSER_CHOICE, BonitaPreferenceConstants.INTERNAL_BROWSER);
        FileActionDialog.setDisablePopup(true);

    }
}
