/*******************************************************************************
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.team;

import org.bonitasoft.studio.common.api.BonitaAPI;

/**
 * @author Aurelien Pupier
 *
 */
@BonitaAPI
public interface CommitCommentGenerator {

	/**
	 * Provides the default comment generated by Bonita as parameter,
	 * you can return any composition of String based on it.
	 *  
	 * @param defaultComment
	 * @return
	 */
	public String generateComment(String defaultComment);
	
}
