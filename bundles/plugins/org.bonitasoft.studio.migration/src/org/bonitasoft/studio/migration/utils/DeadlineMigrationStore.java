/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.migration.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.edapt.spi.migration.Instance;

/**
 * @author Romain Bioteau
 *
 */
public class DeadlineMigrationStore {

	private static Map<String, List<DeadlineStore>> deadlines = new HashMap<String,List<DeadlineStore>>() ;

	public static void addDeadline(final String taskUUID, final Instance deadline){
		List<DeadlineStore> deadlineList = deadlines.get(taskUUID);
		if(deadlineList == null){
			deadlineList = new ArrayList<DeadlineStore>();
		}
		deadlineList.add(new DeadlineStore((String) ((Instance) deadline.get("connector")).get("name"), (String)deadline.get("condition")));
		deadlines.put(taskUUID,deadlineList);
	}

	public static List<DeadlineStore> getDeadlines(final String taskUUID){
		return deadlines.get(taskUUID);
	}

	public static boolean hasDeadline(final String taskUUID){
		return deadlines.containsKey(taskUUID);
	}

	public static void clearDeadlines(){
		deadlines.clear();
	}

}
