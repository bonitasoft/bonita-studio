/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.application.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.IObjectContributor;
import org.eclipse.ui.internal.ObjectActionContributor;
import org.eclipse.ui.internal.ObjectActionContributorManager;

@SuppressWarnings("restriction")
public class CustomObjectActionContributorManager extends ObjectActionContributorManager {

    private static final String MAVEN_PROJECT_MENU_ID = "org.eclipse.m2e.core.projectMenu";
    private static final String MAVEN_MODULE_PROJECT_ID = "org.eclipse.m2e.core.projectMenu.newModuleProject";
    private static final String MAVEN_ADD_DEPENDENCY_PLUGIN_ID = "org.eclipse.m2e.core.projectMenu.addDependencyPlugin";
    private static final String MAVEN_UPDATE_PROJECT_ID = "org.eclipse.m2e.updateConfigurationAction";
    private static final String MAVEN_FILE_MENU_ID = "org.eclipse.m2e.core.fileMenu";
    private static final String MAVEN_FILE_MODULE_PROJECT_ID = "org.eclipse.m2e.core.fileMenu.newModuleProject";
    private static final String MAVEN_FILE_ADD_DEPENDENCY_PLUGIN_ID = "org.eclipse.m2e.core.fileMenu.addDependencyPlugin";
    private static final String MAVEN_PROFILE_FROM_PROJECT_ID = "org.eclipse.m2e.profiles.ui.profiles.selectFromProject";
    private static final String MAVEN_PROFILE_FROM_POM_ID = "org.eclipse.m2e.profiles.ui.profiles.selectFromPom";
    private static final String COMPARE_MENU_ID = "org.eclipse.compare.MenuGroups";
    private static final String COMPARE_ACTIONS_ID = "org.eclipse.compare.CompareAction";
    private static final String REPLACE_WITH_EDITION_ID = "org.eclipse.compare.ReplaceWithEditionAction";
    private static final String COMPARE_WITH_EDITION_ID = "org.eclipse.compare.CompareWithEditionAction";
    private static final String ADD_FROM_HISTORY_ID = "org.eclipse.compare.AddFromHistoryAction";

    private static CustomObjectActionContributorManager sharedInstance;

    private List<String> actionIds;

    private List<String> getActions() {
        if (actionIds == null) {
            actionIds = new ArrayList<>();
            actionIds.add(MAVEN_PROJECT_MENU_ID);
            actionIds.add(MAVEN_MODULE_PROJECT_ID);
            actionIds.add(MAVEN_ADD_DEPENDENCY_PLUGIN_ID);
            actionIds.add(MAVEN_UPDATE_PROJECT_ID);
            actionIds.add(MAVEN_FILE_MENU_ID);
            actionIds.add(MAVEN_FILE_MODULE_PROJECT_ID);
            actionIds.add(MAVEN_FILE_ADD_DEPENDENCY_PLUGIN_ID);
            actionIds.add(MAVEN_PROFILE_FROM_PROJECT_ID);
            actionIds.add(MAVEN_PROFILE_FROM_POM_ID);
            actionIds.add(COMPARE_MENU_ID);
            actionIds.add(COMPARE_ACTIONS_ID);
            actionIds.add(REPLACE_WITH_EDITION_ID);
            actionIds.add(COMPARE_WITH_EDITION_ID);
            actionIds.add(ADD_FROM_HISTORY_ID);
        }
        return actionIds;
    }

    @Override
    public void registerContributor(IObjectContributor contributor, String targetType) {
        if (contributor instanceof ObjectActionContributor) {
            String id = ((ObjectActionContributor) contributor).getAdapter(IConfigurationElement.class).getAttribute("id");
            if (getActions().contains(id)) {
                super.registerContributor(contributor, targetType);
            }
        }
    }

    public static CustomObjectActionContributorManager getManager() {
        if (sharedInstance == null) {
            sharedInstance = new CustomObjectActionContributorManager();
        }
        return sharedInstance;
    }
}
