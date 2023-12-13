/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import org.eclipse.m2e.core.project.IArchetype;

public class RestAPIExtensionArchetype {

    public static final String ARTIFACT_ID = "bonita-rest-api-extension-archetype";
    public static final String GROUP_ID = "org.bonitasoft.archetypes";
    public static final String VERSION = "1.7.1";
    public static final IArchetype INSTANCE = new Archetype(GROUP_ID, ARTIFACT_ID, VERSION);
   

    private RestAPIExtensionArchetype() {

    }

}
