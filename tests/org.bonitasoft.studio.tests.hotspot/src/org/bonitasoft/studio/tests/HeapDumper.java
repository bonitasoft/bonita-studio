package org.bonitasoft.studio.tests;
/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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


import javax.management.MBeanServer;

import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.HotSpotDiagnosticMXBean;

public class HeapDumper implements IHeapDumper {
	// This is the name of the HotSpot Diagnostic MBean
	private static final String HOTSPOT_BEAN_NAME = "com.sun.management:type=HotSpotDiagnostic";

	// field to store the hotspot diagnostic MBean 
	private static volatile HotSpotDiagnosticMXBean hotspotMBean;


	/**      Call this method from your application whenever you 
	 *     want to dump the heap snapshot into a file.
	 *     
	 *      @param fileName name of the heap dump file
	 *      @param live flag that tells whether to dump
	 *                  only the live objects
	 */
	@Override
	public void dumpHeap(String fileName, boolean live) {
		// initialize hotspot diagnostic MBean
		initHotspotMBean();
		try {
			File f = new File(fileName);
			if(f.exists()){
				f.delete();
			}
			hotspotMBean.dumpHeap(fileName, live);
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}

	}

	// initialize the hotspot diagnostic MBean field
	private static void initHotspotMBean() {
		if (hotspotMBean == null) {
			synchronized (HeapDumper.class) {
				if (hotspotMBean == null) {
					hotspotMBean = getHotspotMBean();
				}
			}
		}
	}

	// get the hotspot diagnostic MBean from the
	// platform MBean server
	private static HotSpotDiagnosticMXBean getHotspotMBean() {
		try {
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			HotSpotDiagnosticMXBean bean = 
					ManagementFactory.newPlatformMXBeanProxy(server,
							HOTSPOT_BEAN_NAME, HotSpotDiagnosticMXBean.class);
			return bean;
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
