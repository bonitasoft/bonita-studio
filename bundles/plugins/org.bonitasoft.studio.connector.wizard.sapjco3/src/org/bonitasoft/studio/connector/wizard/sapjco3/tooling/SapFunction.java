/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.tooling;

/**
 * @author Maxence Raoux
 * 
 */
public class SapFunction {

	private String name;
	private String groupe;
	private String description;
	public static String NO_DESCRIPTION = "No description";

	public SapFunction(String name, String groupe, String description) {
		this.name = name;
		this.groupe = groupe;
		if (description == null || description.isEmpty()) {
			this.description = NO_DESCRIPTION;
		} else {
			this.description = description;
		}
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getGroup() {
		return groupe;
	}

	@Override
	public String toString() {
		return "SapFunction [name=" + name + ", groupe=" + groupe + ", description=" + description + "]";
	}

}
