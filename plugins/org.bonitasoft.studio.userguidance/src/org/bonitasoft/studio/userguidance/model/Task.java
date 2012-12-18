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
package org.bonitasoft.studio.userguidance.model;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.profiles.manager.BonitaProfilesManager;
import org.bonitasoft.studio.userguidance.UserGuidancePlugin;
import org.eclipse.core.runtime.IConfigurationElement;

/**
 * @author Romain Bioteau
 *
 */
public class Task {

	private static final String PREF_DONE = "isDone";
	private static final String PREF_SKIP = "isSkip";
	private static Map<Integer, Task> tasksByRank;
	private boolean done ;
	private boolean skip ;
	private SortedSet<Task> subtasks ;
	private String contentURL ;
	private String id ;
	private String name ;
	private String parentTaskId ;
	private int rank;
	private String redirectId;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public Task(String id,String name,String redirectId,String contentUrl,boolean done,boolean skipped,int rank,String parentTaskId){
		this.id = id ;
		this.name = name ;
		this.contentURL = contentUrl ;
		this.done = done ;
		this.skip = skipped ;
		this.parentTaskId  = parentTaskId ;
		this.rank = rank ;
		this.subtasks = new TreeSet<Task>(new TaskComparator()) ;
		this.redirectId = redirectId ;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		UserGuidancePlugin.getDefault().getPreferenceStore().setValue(CommonRepositoryPlugin.getCurrentRepository()+"_"+getId()+"_"+PREF_DONE, done) ;
		this.done = done;
	}

	public String getId() {
		return id;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		UserGuidancePlugin.getDefault().getPreferenceStore().setValue(CommonRepositoryPlugin.getCurrentRepository()+"_"+getId()+"_"+PREF_SKIP, skip) ;
		this.skip = skip;
	}

	public SortedSet<Task> getSubtasks() {
		return subtasks;
	}

	public void addSubtasks(Task subtask) {
		this.subtasks.add(subtask);
	}

	public String getContentURL() {
		return contentURL;
	}

	public void setContentURL(String contentURL) {
		this.contentURL = contentURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static SortedSet<Task> buildTaskTree() throws IOException{
		SortedSet<Task> result = new TreeSet<Task>(new TaskComparator()) ;
		tasksByRank = new HashMap<Integer, Task>();
		String profileId = BonitaProfilesManager.getInstance().getActiveProfile() ;
		Set<String> features = BonitaProfilesManager.getInstance().getFeatureByProfile(profileId) ;
		Map<String, Task> tasks = getTasks(features) ;
		for(Task t : tasks.values()){
			if(t.getParentTaskId() != null){
				if(tasks.get(t.getParentTaskId()) != null){
					Task parentTask = tasks.get(t.getParentTaskId()) ;
					parentTask.addSubtasks(t) ;
					tasksByRank.put(t.getRank(),t) ;
				}
			}
		}

		
		for(Task t : tasks.values()){
			if(t.getParentTaskId() == null){
				result.add(t) ;
				tasksByRank.put(t.getRank(),t) ;
			}
		}
		return result;
	}

	private static boolean isSkipped(String id) {
		return UserGuidancePlugin.getDefault().getPreferenceStore().getBoolean(CommonRepositoryPlugin.getCurrentRepository()+"_"+id+"_"+PREF_SKIP) ;
	}

	private static boolean isDone(String id) {
		return UserGuidancePlugin.getDefault().getPreferenceStore().getBoolean(CommonRepositoryPlugin.getCurrentRepository()+"_"+id+"_"+PREF_DONE) ;
	}


	private static Map<String, Task> getTasks(Set<String> features){
		IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance().getConfigurationElements("org.bonitasoft.studio.userguidance.bonitaTaskGuidance"); //$NON-NLS-1$
		Map<String, Task> tasks = new HashMap<String, Task>() ;

		for (IConfigurationElement elem : elements){ 
			String taskId = elem.getAttribute("id"); 
			String taskName = elem.getAttribute("name"); 
			String contentURL = elem.getAttribute("content"); 
			String parentTask = elem.getAttribute("parentTask"); 
			String featureId = elem.getAttribute("categoryId"); 
			String redirectId = elem.getAttribute("redirectId"); 

			String rankAttribute =  elem.getAttribute("rank"); 
			int rank = Integer.parseInt(rankAttribute) ; 
			if(features.contains(featureId)){
				tasks.put(taskId, new Task(taskId,taskName,redirectId, contentURL, isDone(taskId), isSkipped(taskId),rank,parentTask)) ;
			}
		}

		return tasks ;
	}
	
	private static class TaskComparator implements Comparator<Task>{

		@Override
		public int compare(Task t1, Task t2) {
			return ((Integer)t1.getRank()).compareTo(((Integer)t2.getRank()));
		}
		
	}

	public static Map<Integer,Task> getTaskByRank() {
		return tasksByRank ;
	}

	public String getRedirectId() {
		return redirectId;
	}
	
	
}
