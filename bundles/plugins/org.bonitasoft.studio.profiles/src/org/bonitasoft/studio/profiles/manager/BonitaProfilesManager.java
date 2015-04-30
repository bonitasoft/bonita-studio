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

import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.preferences.BonitaPreferenceConstants;
import org.bonitasoft.studio.preferences.BonitaStudioPreferencesPlugin;
import org.bonitasoft.studio.profiles.ProfilePlugin;
import org.bonitasoft.studio.profiles.i18n.Messages;
import org.bonitasoft.studio.profiles.repository.ProfileFileStore;
import org.bonitasoft.studio.profiles.repository.ProfileRepositoryStore;
import org.eclipse.jface.preference.IPreferenceStore;
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

        final List<String> modelingFeatures = new ArrayList<String>();
        final List<String> extensionFeatures = new ArrayList<String>();
        final List<String> applicationFeatures = new ArrayList<String>();
        for(final Object category : workingCopy.getDefinedCategoryIds()){
            final String categoryId = (String) category ;
            if(isIncludedInModelingFeature(categoryId)){
                modelingFeatures.add(categoryId) ;
            }
            if(isIncludedInExtensionFeature(categoryId)){
                extensionFeatures.add(categoryId) ;
            }
            if (isIncludedInApplicationFeature(categoryId)) {
                applicationFeatures.add(categoryId) ;
            }
        }

        featuresByFamily.put(IBonitaActivitiesCategory.MODELING_FAMILY, modelingFeatures) ;
        featuresByFamily.put(IBonitaActivitiesCategory.EXTENSIONS_FAMILY, extensionFeatures) ;
        featuresByFamily.put(IBonitaActivitiesCategory.APPLICATION_FAMILY, applicationFeatures) ;

    }

    protected boolean isIncludedInApplicationFeature(final String categoryId) {
        return categoryId.equals(LOOK_N_FEELS) ||
                categoryId.equals(FORMS_MODELING) ||
                categoryId.equals(FORMS_TEMPLATES) ||
                categoryId.equals(VALIDATORS);
    }

    protected boolean isIncludedInExtensionFeature(final String categoryId) {
        return categoryId.equals(EXECUTION) ||
                categoryId.equals(CONNECTORS) ||
                categoryId.equals(DATA_MANAGEMENT) ||
                categoryId.equals(DEPENDENCIES_MANAGEMENT) ||
                categoryId.equals(KPI);
    }

    protected boolean isIncludedInModelingFeature(final String categoryId) {
        return categoryId.equals(PROCESS_MODELING) ||
                categoryId.equals(APPEARANCE_CUSTOMIZATION) ||
                categoryId.equals(VALIDATION) ||
                categoryId.equals(SIMULATION);
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

    public Set<String> getFeatureByProfile(final String profileId){
        if(profiles.get(profileId) != null){
            return profiles.get(profileId) ;
        }else{
            final ProfileRepositoryStore profileStore = RepositoryManager.getInstance().getRepositoryStore(ProfileRepositoryStore.class) ;
            final ProfileFileStore file = profileStore.getChild(profileId+"."+ProfileRepositoryStore.PROFILE_EXT);
            if(file != null){
                if(file.isProvided()){
                    profiles.put(profileId,  file.getFeatures()) ;
                }
                return file.getFeatures();
            }
        }
        return  null;
    }


    public boolean isEnabled(final String category) {
        return InternalActivityHelper.getEnabledCategories(workingCopy).contains(category);
    }

    public List<String> getFeatureByFamily(final String family) {
        return featuresByFamily.get(family);
    }

    @SuppressWarnings("unchecked")
	public void applyChanges()  {
        final String profileName =  ProfilePlugin.getDefault().getPreferenceStore().getString(PREF_ACTIVE_PROFILE) ;
        ICategory[] categories = WorkbenchActivityHelper.resolveCategories(workingCopy,getAllFeature()) ;
        final Set activitySet = new HashSet() ;
        for(final ICategory category : categories){
            activitySet.addAll(WorkbenchActivityHelper.getActivityIdsForCategory(category));
        }

        final HashSet newSet = new HashSet(workingCopy.getEnabledActivityIds());
        newSet.removeAll(activitySet);
        workingCopy.setEnabledActivityIds(newSet);

        final Set<String> featureByProfile = getFeatureByProfile(profileName);
        categories = WorkbenchActivityHelper.resolveCategories(workingCopy,featureByProfile) ;
        activitySet.clear();
        for(final ICategory category : categories){
            activitySet.addAll(WorkbenchActivityHelper.getActivityIdsForCategory(category));
        }

        activitySet.addAll(workingCopy.getEnabledActivityIds());
        workingCopy.setEnabledActivityIds(activitySet);


        PlatformUI.getWorkbench().getActivitySupport().setEnabledActivityIds(workingCopy.getEnabledActivityIds()) ;

    }

    protected IPreferenceStore getBonitaPreferenceStore() {
        return BonitaStudioPreferencesPlugin.getDefault().getPreferenceStore();
    }


    public void setActiveProfile(String profileName,final boolean updateUI) {
        final ProfileRepositoryStore profileStore = RepositoryManager.getInstance().getRepositoryStore(ProfileRepositoryStore.class) ;
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
            final IWorkbenchPage activePage =  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() ;
            if(activePage != null){
                for(final String id : BonitaPerspectivesUtils.getAllPropertiesViews()){
                    final IViewPart part = activePage.findView(id) ;
                    if(part != null){
                        activePage.hideView(part) ;
                    }
                }
                activePage.resetPerspective();
            }
        }
    }

    private Set<String> getAllFeature() {
        final Set<String> result = new HashSet<String>();
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

    public static Integer getPriority(final String id) {
        if(id.equals(APPLICATION_DEVELOPER_PROFILE)){
            return 3 ;
        }
        if(id.equals(BUSINESS_ANALYST_PROFILE)){
            return 1 ;
        }
        return null;
    }

}
