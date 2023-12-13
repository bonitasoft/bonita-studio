package org.eclipse.gmf.tooling.runtime;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * @since 3.2
 */
public class LogHelper {

	private final Plugin myPlugin;

	private final String myId;

	private final ILog myLog;

	public LogHelper(Plugin plugin) {
		this(plugin, plugin.getBundle().getSymbolicName());
	}

	public LogHelper(Plugin plugin, String bundleID) {
		myPlugin = plugin;
		myId = bundleID;
		myLog = myPlugin.getLog();
	}

	public void logError(String error) {
		logError(error, null);
	}

	public void logError(String error, Throwable throwable) {
		if (error == null && throwable != null) {
			error = throwable.getMessage();
		}
		myLog.log(new Status(IStatus.ERROR, myId, notNull(error), throwable));
		debug(error, throwable);
	}

	public void logInfo(String message) {
		logInfo(message, null);
	}

	public void logInfo(String message, Throwable throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		myLog.log(new Status(IStatus.INFO, myId, notNull(message), throwable));
		debug(message, throwable);
	}

	public void logStatus(IStatus status) {
		myLog.log(status);
	}

	private static String notNull(String text) {
		return text == null ? "" : text;
	}

	private void debug(String message, Throwable throwable) {
		if (!myPlugin.isDebugging()) {
			return;
		}
		if (message != null) {
			System.err.println(message);
		}
		if (throwable != null) {
			throwable.printStackTrace();
		}
	}
}
