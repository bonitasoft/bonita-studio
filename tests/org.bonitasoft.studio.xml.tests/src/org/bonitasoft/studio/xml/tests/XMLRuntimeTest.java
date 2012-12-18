/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.xml.tests;

import java.io.File;

import junit.framework.TestCase;

import org.eclipse.core.runtime.FileLocator;

/**
 * @author Mickael Istria
 *
 */
public class XMLRuntimeTest extends TestCase {

	public void testXMLOutputExec() throws Exception {
		File file = new File(FileLocator.toFileURL(this.getClass().getResource("MockXMLProcess--1.0.bar")).getFile());
		fail();
		//		ProcessArtifact artifact = ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), file);
//		artifact.open();
//		new RunProcessCommand(true).execute(null);
//		final LoginContext lc = BonitaEnvironmentInit.createALoginContext();
//		lc.login();
//		ProcessDefinition engineProcess = AccessorUtil.getQueryDefinitionAPI().getProcess("MockXMLProcess", "1.0");
//		final ProcessInstanceUUID instanceUUID = AccessorUtil.getAPIAccessor().getRuntimeAPI().instantiateProcess(engineProcess.getUUID());
//		final QueryRuntimeAPI queryRuntimeAPI = AccessorUtil.getQueryRuntimeAPI();
//		assertTrue(new TestAsyncThread(30, 1000) {
//			@Override
//			public boolean isTestGreen() throws Exception {
//				lc.login();
//				InstanceState state = AccessorUtil.getQueryRuntimeAPI().getProcessInstance(instanceUUID).getInstanceState();
//				lc.logout();
//				return state.equals(InstanceState.FINISHED);
//			}
//		}.evaluate());
//		String aware = (String) queryRuntimeAPI.getProcessInstanceVariable(instanceUUID, "aware");
//		assertEquals("1", aware);
//		String unaware = (String) queryRuntimeAPI.getProcessInstanceVariable(instanceUUID, "unaware");
//		assertEquals("1", unaware);
	}
}
