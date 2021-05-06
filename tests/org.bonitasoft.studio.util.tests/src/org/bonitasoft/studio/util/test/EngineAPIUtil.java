/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.util.test;

import java.util.List;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.engine.BOSEngineManager;

public class EngineAPIUtil {

    public static HumanTaskInstance findNewPendingTaskForSpecifiedProcessDefAndUser(APISession session,
            List<HumanTaskInstance> previousTasks, long processDefinitionUUID, long userID)
            throws InvalidSessionException, SearchException {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        SearchResult<HumanTaskInstance> tasks = processApi.searchPendingTasksForUser(userID, searchOptions);

        if (tasks.getCount() == previousTasks.size()) {
            return null;
        }
        boolean newTaskIsOld = false;
        HumanTaskInstance newTask = null;
        for (HumanTaskInstance currentTask : tasks.getResult()) {
            newTaskIsOld = false;//reinit the value
            for (HumanTaskInstance oldTask : previousTasks) {
                if (currentTask.getId() == oldTask.getId()) {
                    newTaskIsOld = true;
                }
            }
            if (!newTaskIsOld && currentTask.getProcessDefinitionId() == processDefinitionUUID) {
                newTask = currentTask;
                break;
            }
        }
        return newTask;
    }

    public static HumanTaskInstance findNewAssignedTaskForSpecifiedProcessDefAndUser(APISession session,
            List<HumanTaskInstance> previousTasks, long processDefinitionUUID, long userID)
            throws InvalidSessionException {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final List<HumanTaskInstance> tasks = processApi.getAssignedHumanTaskInstances(userID, 0, 10, null);

        if (tasks.size() == previousTasks.size()) {
            return null;
        }
        boolean newTaskIsOld = false;
        HumanTaskInstance newTask = null;
        for (HumanTaskInstance currentTask : tasks) {
            newTaskIsOld = false;//reinit the value
            for (HumanTaskInstance oldTask : previousTasks) {
                if (currentTask.getId() == oldTask.getId()) {
                    newTaskIsOld = true;
                }
            }
            if (!newTaskIsOld && currentTask.getProcessDefinitionId() == processDefinitionUUID) {
                newTask = currentTask;
                break;
            }
        }
        return newTask;
    }

}
