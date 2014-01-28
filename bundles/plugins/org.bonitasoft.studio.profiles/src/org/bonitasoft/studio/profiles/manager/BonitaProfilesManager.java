/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.profiles.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.extension.IEngineAction;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.profiles.ProfilePlugin;
import org.bonitasoft.studio.profiles.i18n.Messages;
import org.bonitasoft.studio.profiles.repository.ProfileFileStore;
import org.bonitasoft.studio.profiles.repository.ProfileRepositoryStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.ICategory;
import org.eclipse.ui.activities.IMutableActivityManager;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.internal.activities.InternalActivityHelper;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaProfilesManager implements IBonitaActivitiesCategory {

    public static final String PREF_ACTIVE_PROFILE = "activeProfile";
    public static final String SHOW_SELECT_PROFILE = "selectProfileOnStartup";


    private static BonitaProfilesManager INSTANCE  ;


    public static String BUSINESS_ANALYST_PROFILE = "Business Analyst"  ;
    public static final String APPLICATION_DEVELOPER_PROFILE = "Application Developer";

    private final Map<String,Set<String>> profiles ;
    private final Map<String,List<String>> featuresByFamily ;
    private final IMutableActivityManager workingCopy ;

    private BonitaProfilesManager(){
        profiles = new HashMap<String, Set<String>>();
        featuresByFamily = new HashMap<String, List<String>>() ;
        workingCopy = PlatformUI.getWorkbench().getActivitySupport().createWorkingCopy() ;

        List<String> modelingFeatures = new ArrayList<String>();
        List<String> extensionFeatures = new ArrayList<String>();
        List<String> applicationFeatures = new ArrayList<String>();
        for(Object category : workingCopy.getDefinedCategoryIds()){
            String categoryId = (String) category ;

            if(categoryId.equals(PROCESS_MODELING) ||
                    categoryId.equals(APPEARANCE_CUSTOMIZATION) ||
                    categoryId.equals(VALIDATION) ||
                    categoryId.equals(SIMULATION)){
                modelingFeatures.add(categoryId) ;
            }

            if(categoryId.equals(EXECUTION) ||
                    categoryId.equals(CONNECTORS) ||
                    categoryId.equals(DATA_MANAGEMENT) ||
                    categoryId.equals(DEPENDENCIES_MANAGEMENT) ||
                    categoryId.equals(KPI)){
                extensionFeatures.add(categoryId) ;
            }

            if(categoryId.equals(LOOK_N_FEELS) ||
                    categoryId.equals(FORMS_MODELING) ||
                    categoryId.equals(FORMS_TEMPLATES) ||
                    categoryId.equals(VALIDATORS) 
                    ){
                applicationFeatures.add(categoryId) ;
            }

        }

        featuresByFamily.put(IBonitaActivitiesCategory.MODELING_FAMILY, modelingFeatures) ;
        featuresByFamily.put(IBonitaActivitiesCategory.EXTENSIONS_FAMILY, extensionFeatures) ;
        featuresByFamily.put(IBonitaActivitiesCategory.APPLICATION_FAMILY, applicationFeatures) ;

    }

    public static BonitaProfilesManager getInstance(){
        if(INSTANCE == null){
            INSTANCE = new BonitaProfilesManager();
        }
        return INSTANCE;
    }

    public IMutableActivityManager getActivityManager(){
        return workingCopy ;
    }

    public Set<String> getFeatureByProfile(String profileId){
        if(profiles.get(profileId) != null){
            return profiles.get(profileId) ;
        }else{
            final ProfileRepositoryStore profileStore = (ProfileRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProfileRepositoryStore.class) ;
            ProfileFileStore file = profileStore.getChild(profileId+"."+ProfileRepositoryStore.PROFILE_EXT);
            if(file != null){
                if(file.isProvided()){
                    profiles.put(profileId,  file.getFeatures()) ;
                }
                return file.getFeatures();
            }
        }
        return  null;
    }


    public boolean isEnabled(String category) {
        return InternalActivityHelper.getEnabledCategories(workingCopy).contains(category);
    }

    public List<String> getFeatureByFamily(String family) {
        return featuresByFamily.get(family);
    }

    @SuppressWarnings("unchecked")
	public void applyChanges()  {
        String profileName =  ProfilePlugin.getDefault().getPreferenceStore().getString(PREF_ACTIVE_PROFILE) ;
        ICategory[] categories = WorkbenchActivityHelper.resolveCategories(workingCopy,getAllFeature()) ;
        Set activitySet = new HashSet() ;
        for(ICategory category : categories){
            activitySet.addAll(WorkbenchActivityHelper.getActivityIdsForCategory(category));
        }

        HashSet newSet = new HashSet(workingCopy.getEnabledActivityIds());
        newSet.removeAll(activitySet);
        workingCopy.setEnabledActivityIds(newSet);

        categories = WorkbenchActivityHelper.resolveCategories(workingCopy,getFeatureByProfile(profileName)) ;
        activitySet.clear();
        for(ICategory category : categories){
            activitySet.addAll(WorkbenchActivityHelper.getActivityIdsForCategory(category));
        }

        activitySet.addAll(workingCopy.getEnabledActivityIds());
        workingCopy.setEnabledActivityIds(activitySet);
      

        PlatformUI.getWorkbench().getActivitySupport().setEnabledActivityIds(workingCopy.getEnabledActivityIds()) ;
   
    }


    private void startJetty() {
        IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.engine.preEngineAction"); //$NON-NLS-1$
        IEngineAction contrib = null;
        for (IConfigurationElement elem : elements){
            try {
                contrib = (IEngineAction) elem.createExecutableExtension("class"); //$NON-NLS-1$
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            contrib.run();
        }
    }

    public void setActiveProfile(String profileName,boolean updateUI) {
        final ProfileRepositoryStore profileStore = (ProfileRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProfileRepositoryStore.class) ;
        if(profileStore.getChild(profileName+"."+ProfileRepositoryStore.PROFILE_EXT) == null){
            profileName = APPLICATION_DEVELOPER_PROFILE ;
        }

        ProfilePlugin.getDefault().getPreferenceStore().setValue(PREF_ACTIVE_PROFILE, profileName) ;
        applyChanges();


        //Handle validation
        if(BonitaProfilesManager.getInstance().isEnabled(IBonitaActivitiesCategory.VALIDATION)){
            BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.PREF_ENABLE_VALIDATION,true) ;
        }else{
            BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore().setValue(BonitaPreferenceConstants.PREF_ENABLE_VALIDATION,false) ;
        }

        if(updateUI){
            Display.getDefault().syncExec(new Runnable() {
                @Override
                public void run() {
                    BusyIndicator.showWhile(Display.getDefault(), new Runnable() {

                        @Override
                        public void run() {
                            updateViews();
                        }
                    });
                }
            }) ;
        }
    }

    protected void updateViews() {
        if(PlatformUI.isWorkbenchRunning() && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null){
            PlatformUtil.openIntroIfNoOtherEditorOpen() ;
            IWorkbenchPage activePage =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() ;
            if(activePage != null){
                for(String id : BonitaPerspectivesUtils.getAllPropertiesViews()){
                    IViewPart part = activePage.findView(id) ;
                    if(part != null){
                        activePage.hideView(part) ;
                    }
                }
                activePage.resetPerspective();
            }
            startJetty();
        }
    }

    private Set<String> getAllFeature() {
        Set<String> result = new HashSet<String>();
        result.addAll(getFeatureByFamily(MODELING_FAMILY)) ;
        result.addAll(getFeatureByFamily(APPLICATION_FAMILY)) ;
        result.addAll(getFeatureByFamily(EXTENSIONS_FAMILY)) ;
        return result;
    }

    public String getActiveProfile() {
        return ProfilePlugin.getDefault().getPreferenceStore().getString(PREF_ACTIVE_PROFILE) ;
    }

    public static String getLabelForProfile(String id) {
        if(id.endsWith("."+ProfileRepositoryStore.PROFILE_EXT)){
            id = id.replaceAll("."+ProfileRepositoryStore.PROFILE_EXT, "") ;
        }

        if(BUSINESS_ANALYST_PROFILE.equals(id)){
            return Messages.BusinessAnalyst_Label ;
        } else if(APPLICATION_DEVELOPER_PROFILE.equals(id)){
            return Messages.ApplicationDeveloper_Label ;
        }

        return id;
    }

    public static Integer getPriority(String id) {
        if(id.equals(APPLICATION_DEVELOPER_PROFILE)){
            return 3 ;
        }
        if(id.equals(BUSINESS_ANALYST_PROFILE)){
            return 1 ;
        }
        return null;
    }

}
