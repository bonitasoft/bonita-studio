/**
 * Copyright (C) 2010-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.registration;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.ui.PlatformUI;

/**
 * @author Baptiste Mesta
 * @author Aurelien Pupier
 */
public class BonitaRegistration {

	public static final String BONITA_USER_REGISTERED = "user.registered";
	public static final String BONITA_USER_REGISTER_TRY = "user.register.try";
	public static final String BONITA_INFO_SENT = "user.info.sent";
	public static final String BONITA_USER_INFOS = "user.info.data";
	public static final int BONITA_USER_REGISTER_MAXTRY = 6;
    private static final String DEFAULT_EMAIL = "nowhere@nowhere.org";
    public static final String[] SYSTEM_PROPERTIES_TO_SEND = new String[] { "java.version", "java.vendor", "os.name", "os.arch", "os.version", "osgi.nl" };
    private final IPreferenceStore prefStore;

    public BonitaRegistration(final IPreferenceStore prefStore) {
        this.prefStore = prefStore;
    }

    private Map<String, String> createSystemInfoMap() {
        final HashMap<String, String> infos = new HashMap<String, String>();
        infos.put("bonita.version", ProductVersion.CURRENT_VERSION);
        for (final String systemPropertyToSend : SYSTEM_PROPERTIES_TO_SEND) {
            addSystemPropertyToMap(infos, systemPropertyToSend);
        }
        infos.put("field_user_systeme_lang", Locale.getDefault().toString());
        infos.put("proc.number", String.valueOf(Runtime.getRuntime().availableProcessors()));
        infos.put("mem.total", String.valueOf(Runtime.getRuntime().totalMemory() / 1048576) + "mo");
        infos.put("mem.max", String.valueOf(Runtime.getRuntime().maxMemory() / 1048576) + "mo");
		addScreenSizeInfo(infos);
		addIpAdressInfo(infos);
        return infos;
    }

    protected String addSystemPropertyToMap(final Map<String, String> infos, final String systemPropertyKey) {
        return infos.put(systemPropertyKey, System.getProperty(systemPropertyKey));
    }

    private void addIpAdressInfo(final Map<String, String> infos2) {
        try {
            final Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                final NetworkInterface ni = e.nextElement();
                addIpAddressInfo(infos2, ni);
            }
        } catch (final SocketException e1) {

        }
    }

    protected void addIpAddressInfo(final Map<String, String> infos2, final NetworkInterface ni) {
        if (!ni.getName().contains("lo")) {
            final Enumeration<InetAddress> e2 = ni.getInetAddresses();
            while (e2.hasMoreElements()) {
                final InetAddress ip = e2.nextElement();
                addIpAdressInfo(infos2, ni, ip);
            }
        }
    }

    protected void addIpAdressInfo(final Map<String, String> infos2, final NetworkInterface ni, final InetAddress ip) {
        final String name = ip.getClass().getName();
        infos2.put("netwrk." + ni.getName() + "." + (name.contains("Inet6") ? "ipv6" : "ipv4"), ip.getHostAddress());
    }

    protected void addScreenSizeInfo(final Map<String, String> infos) {
        final Display display = PlatformUI.getWorkbench().getDisplay();
		if (display != null) {
            final Monitor[] monitors = display.getMonitors();
            infos.put("screen.number", String.valueOf(monitors.length));
			Rectangle b = monitors[0].getBounds();
			String rez = b.width + "x" + b.height;
			infos.put("screen.main.size", rez);
			if (monitors.length == 2) {
				b = monitors[1].getBounds();
				rez = b.width + "x" + b.height;
				infos.put("screen.sec.size", rez);
			}
		}
    }

    protected void sendUserInfo(final Map<String, String> infos) {
		try {
            final String email = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(DEFAULT_EMAIL, "UTF-8");
            final String data = computeEncodedDataToSend(infos);
			sendUserInfoEncoded(prefStore, email, data);
		} catch (final UnsupportedEncodingException e) {
			BonitaStudioLog.error(e);
		}
	}

    protected String computeEncodedDataToSend(final Map<String, String> infos2) throws UnsupportedEncodingException {
        final StringBuilder sb = new StringBuilder();
        final Set<Entry<String, String>> infos2Entries = infos2.entrySet();
        for (final Entry<String, String> infos2Entry : infos2Entries) {
        	final String key = infos2Entry.getKey();
        	final String value = infos2Entry.getValue();
            if (!DEFAULT_EMAIL.equals(key)) {
                sb
                        .append("<value key=\"")
                        .append(key)
                        .append("\">")
                        .append(value)
                        .append("</value>\n");
        	}
        }
        return "&data=" + URLEncoder.encode(sb.toString(), "UTF-8");
    }

	/**
	 * @param prefStore
	 *
	 */
    public void sendUserInfoIfNotSent() {
        final String noRegister = System.getProperty("bonita.noregister"); //$NON-NLS-1$
        if (noRegister == null || !noRegister.equals("1")) { //$NON-NLS-1$
            int nbTry = prefStore.getInt(BonitaRegistration.BONITA_USER_REGISTER_TRY);
            final int infoSent = prefStore.getInt(BonitaRegistration.BONITA_INFO_SENT);
            if (infoSent != 1 && nbTry <= BonitaRegistration.BONITA_USER_REGISTER_MAXTRY) {
                prefStore.setValue(BonitaRegistration.BONITA_USER_REGISTER_TRY, ++nbTry);
                prefStore.setValue(BonitaRegistration.BONITA_USER_REGISTERED, 1);
                sendUserInfo(createSystemInfoMap());
            }
        }
	}

    protected void sendUserInfoEncoded(final IPreferenceStore prefStore, final String email, final String data) {
		if (isDataValidToSend(prefStore, email, data)) {
            final Job job = createSystemSendJob(prefStore, email, data);
			job.setSystem(true);
			job.schedule();
		}
	}

    protected SendSystemInfoJob createSystemSendJob(final IPreferenceStore prefStore, final String email, final String data) {
        return new SendSystemInfoJob(data, prefStore, email);
    }

    protected boolean isDataValidToSend(final IPreferenceStore prefStore, final String email, final String data) {
        return prefStore.getInt(BonitaRegistration.BONITA_INFO_SENT) != 1
                && data != null
                && data.length() > 0
                && email != null
				&& email.length() > 0;
    }

}
