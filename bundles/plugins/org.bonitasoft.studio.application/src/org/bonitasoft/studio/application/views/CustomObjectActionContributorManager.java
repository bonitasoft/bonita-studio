/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.application.views;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.internal.IObjectContributor;
import org.eclipse.ui.internal.ObjectActionContributor;
import org.eclipse.ui.internal.ObjectActionContributorManager;

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
    private static final String SVN_ACTION_ID_PREFIX = "org.eclipse.team.svn.ui.*";
    private static final String SVN_SHARE_PROJECT_ACTION_ID = "org.eclipse.team.svn.ui.IProjectContributions";

    private static CustomObjectActionContributorManager sharedInstance;

    private List<String> includes;
    private List<String> excludes;

    private List<String> getIncludedActions() {
        if (includes == null) {
            includes = new ArrayList<>();
            includes.add(MAVEN_PROJECT_MENU_ID);
            includes.add(MAVEN_MODULE_PROJECT_ID);
            includes.add(MAVEN_ADD_DEPENDENCY_PLUGIN_ID);
            includes.add(MAVEN_UPDATE_PROJECT_ID);
            includes.add(MAVEN_FILE_MENU_ID);
            includes.add(MAVEN_FILE_MODULE_PROJECT_ID);
            includes.add(MAVEN_FILE_ADD_DEPENDENCY_PLUGIN_ID);
            includes.add(MAVEN_PROFILE_FROM_PROJECT_ID);
            includes.add(MAVEN_PROFILE_FROM_POM_ID);
            includes.add(COMPARE_MENU_ID);
            includes.add(COMPARE_ACTIONS_ID);
            includes.add(REPLACE_WITH_EDITION_ID);
            includes.add(COMPARE_WITH_EDITION_ID);
            includes.add(SVN_ACTION_ID_PREFIX);
        }
        return includes;
    }

    private List<String> getExcludedActions() {
        if (excludes == null) {
            excludes = new ArrayList<>();
            excludes.add(SVN_SHARE_PROJECT_ACTION_ID);
        }
        return excludes;
    }

    @Override
    public void registerContributor(IObjectContributor contributor, String targetType) {
        if (contributor instanceof ObjectActionContributor) {
            String id = ((ObjectActionContributor) contributor).getAdapter(IConfigurationElement.class)
                    .getAttribute("id");
            if (getIncludedActions().stream().anyMatch(include -> id.matches(include))) {
                if (getExcludedActions().stream().noneMatch(exclude -> id.matches(exclude))) {
                    super.registerContributor(contributor, targetType);
                }
            }
        }
    }

    public static CustomObjectActionContributorManager getManager() {
        if (sharedInstance == null) {
            sharedInstance = new CustomObjectActionContributorManager();
            //We need to filter editor context menu to remove some dangerous actions like Run As, Debug As...
            //This is the most straightforward approach I found. It will be filtered for all editors
            try {
                Field sharedInstanceField = ObjectActionContributorManager.class.getDeclaredField("sharedInstance");
                sharedInstanceField.setAccessible(true);
                sharedInstanceField.set(null, sharedInstance);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                BonitaStudioLog.error(e);
            }
        }
        return sharedInstance;
    }
}
