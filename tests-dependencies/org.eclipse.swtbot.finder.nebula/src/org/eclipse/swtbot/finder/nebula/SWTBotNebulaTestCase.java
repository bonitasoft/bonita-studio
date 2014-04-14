/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Aurelien Pupier <aurelien.pupier@bonitasoft.com> - initial API and implementation
 */
package org.eclipse.swtbot.finder.nebula;

import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTBotGefTestCase;


/**
 * This is a wrapper test case to the SWTBotEclipseTestCase that adds a Nebula bot
 * instead of the standard eclipse bot.
 * 
 * @author Aurelien Pupier
 */
public class SWTBotNebulaTestCase extends SWTBotGefTestCase {
	protected SWTNebulaBot bot = new SWTNebulaBot();
}
