/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.jpdl;

import org.bonitasoft.studio.common.NamingUtils;
import org.jbpm.jpdl32.ConditionType;
import org.jbpm.jpdl32.TransitionType;

/**
 * @author Mickael Istria
 *
 */
public class TransitionDesc {

	private String source;
	private TransitionType jpdlTransition;

	/**
	 * @param bonita
	 * @param jpdlTransition
	 */
	public TransitionDesc(String sourceId, TransitionType jpdlTransition) {
		this.source = sourceId;
		this.jpdlTransition = jpdlTransition;
	}

	/**
	 * @return
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * @return
	 */
	public String getTo() {
		return jpdlTransition.getTo();
	}

	/**
	 * @return
	 */
	public String getName() {
		return NamingUtils.convertToId(jpdlTransition.getName());
	}

	/**
	 * @return
	 */
	public String getCondition() {
		StringBuilder res = new StringBuilder();
		for (ConditionType transition : jpdlTransition.getCondition()) {
			res.append(transition.getExpression());
		}
		return res.toString();
	}

	/**
	 * @return
	 */
	public String getLabel() {
		return jpdlTransition.getName();
	}

}
