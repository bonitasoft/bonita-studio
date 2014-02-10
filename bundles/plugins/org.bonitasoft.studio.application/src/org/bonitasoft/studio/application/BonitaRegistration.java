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
package org.bonitasoft.studio.application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta
 * 
 */
public class BonitaRegistration {

	public static final String BONITA_USER_MAIL = "user.email";
	public static final String BONITA_USER_COUNTRY = "user.country";
	public static final String BONITA_USER_REGISTERED = "user.registered";
	public static final String BONITA_USER_REGISTER_TRY = "user.register.try";
	public static final String BONITA_USER_LAST_NAME = "user.register.last_name";
	public static final String BONITA_USER_FIRST_NAME = "user.register.first_name";
	public static final String BONITA_USER_PHONE = "user.register.phone";
	public static final String BONITA_INFO_SENT = "user.info.sent";
	public static final String BONITA_USER_INFOS = "user.info.data";
	public static final int BONITA_USER_REGISTER_MAXTRY = 6;

	private static HashMap<String, String> initUserInfos(IPreferenceStore prefStore) {
		HashMap<String, String> infos2 = new HashMap<String, String>();
		infos2.put(BonitaRegistration.BONITA_USER_MAIL, prefStore.getString(BonitaRegistration.BONITA_USER_MAIL));
		infos2.put(BonitaRegistration.BONITA_USER_COUNTRY, prefStore.getString(BonitaRegistration.BONITA_USER_COUNTRY));
		infos2.put(BonitaRegistration.BONITA_USER_FIRST_NAME, prefStore.getString(BonitaRegistration.BONITA_USER_FIRST_NAME));
		infos2.put(BonitaRegistration.BONITA_USER_LAST_NAME, prefStore.getString(BonitaRegistration.BONITA_USER_LAST_NAME));
		infos2.put(BonitaRegistration.BONITA_USER_PHONE, prefStore.getString(BonitaRegistration.BONITA_USER_PHONE));
		infos2.put("bonita.version", ProductVersion.CURRENT_VERSION);
		infos2.put("java.version", System.getProperty("java.version"));
		infos2.put("java.vendor", System.getProperty("java.vendor"));
		infos2.put("os.name", System.getProperty("os.name"));
		infos2.put("os.arch", System.getProperty("os.arch"));
		infos2.put("os.version", System.getProperty("os.version"));
		infos2.put("proc.number", String.valueOf(Runtime.getRuntime().availableProcessors()));
		infos2.put("mem.total", String.valueOf(Runtime.getRuntime().totalMemory() / 1048576) + "mo");
		infos2.put("mem.max", String.valueOf(Runtime.getRuntime().maxMemory() / 1048576) + "mo");
		Display display = PlatformUI.getWorkbench().getDisplay();
		if(display != null){
			infos2.put("screen.number", String.valueOf(display.getMonitors().length));
			Rectangle b = display.getMonitors()[0].getBounds();
			String rez = b.width + "x" + b.height;
			infos2.put("screen.main.size", rez);
			if (display.getMonitors().length == 2) {
				b = display.getMonitors()[1].getBounds();
				rez = b.width + "x" + b.height;
				infos2.put("screen.sec.size", rez);
			}
		}
		// get ip adresses
		Enumeration<NetworkInterface> e;
		try {
			e = NetworkInterface.getNetworkInterfaces();

			while (e.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e.nextElement();
				if (!ni.getName().contains("lo")) {

					Enumeration<InetAddress> e2 = ni.getInetAddresses();

					while (e2.hasMoreElements()) {
						InetAddress ip = e2.nextElement();
						String name = ip.getClass().getName();
						infos2.put("netwrk." + ni.getName() + "." + (name.contains("Inet6") ? "ipv6" : "ipv4"), ip.getHostAddress());
					}
				}
			}
		} catch (SocketException e1) {

		}
		return infos2;
	}

	/**
	 * @param prefStore
	 * 
	 */
	public static void sendUserInfo(IPreferenceStore prefStore) {

		HashMap<String, String> infos = initUserInfos(prefStore);
		sendUserInfo(prefStore, infos.get(BonitaRegistration.BONITA_USER_MAIL), infos);
	}

	/**
	 * @param prefStore
	 * 
	 */
	public static void sendUserInfo(final IPreferenceStore prefStore, final String email1, final HashMap<String, String> infos2) {
		// send info to bonitasoft
		String data = "";

		try {

			// create email
			String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(infos2.get(BonitaRegistration.BONITA_USER_MAIL), "UTF-8");

			// send stats
			StringBuilder sb = new StringBuilder();
			Set<Entry<String, String>> infos2Entries = infos2.entrySet();
			for(Entry<String, String> infos2Entry: infos2Entries){
				String key = infos2Entry.getKey();
				String value = infos2Entry.getValue();
				if (!key.equals(BonitaRegistration.BONITA_USER_MAIL)){
					sb.append("<value key=\"").append(key).append("\">").append(value).append("</value>\n");
				}
			}
			data = "&data=" + URLEncoder.encode(sb.toString(), "UTF-8");

			sendUserInfoEncoded(prefStore, email, data);

		} catch (UnsupportedEncodingException e) {
			BonitaStudioLog.error(e);
		}

	}

	/**
	 * @param prefStore
	 * 
	 */
	public static void sendUserInfo(final IPreferenceStore prefStore, final String email1) {
		// send info to bonitasoft
		HashMap<String, String> infos2 = initUserInfos(prefStore);

		sendUserInfo(prefStore, email1, infos2);

	}

	private static void sendUserInfoEncoded(final IPreferenceStore prefStore, final String email, final String data) {

		if (prefStore.getInt(BonitaRegistration.BONITA_INFO_SENT) != 1 && data != null && data.length() > 0 && email != null && email.length() > 0) {
			Job job = new Job("Send user infos") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					try {

						// Send data
						URL url = new URL("http://stats.bonitasoft.org/stats.php");
						URLConnection conn = url.openConnection();

						conn.setDoOutput(true);
						OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
						wr.write(email);
						wr.write(data);
						wr.flush();

						// Get the response
						BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

						String line;
						while ((line = rd.readLine()) != null) {
							if (line.equals("1")) {
								prefStore.setValue(BonitaRegistration.BONITA_INFO_SENT, 1);
							}
						}
						wr.close();
						rd.close();
					} catch (Exception e) {
						// may not be online saving info for sending it later
						prefStore.setValue(BonitaRegistration.BONITA_USER_INFOS, data);
						prefStore.setValue(BonitaRegistration.BONITA_INFO_SENT, 0);
						return Status.CANCEL_STATUS;
					}
					return Status.OK_STATUS;
				}
			};

			job.setSystem(true);
			job.schedule();
		}

	}

}
