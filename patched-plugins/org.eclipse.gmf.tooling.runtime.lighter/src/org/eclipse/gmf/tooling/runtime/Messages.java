package org.eclipse.gmf.tooling.runtime;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	static {
		NLS.initializeMessages("messages", Messages.class); //$NON-NLS-1$
	}

	private Messages() {
	}

	public static String AbstractAttributeParser_UnexpectedValueType;

	public static String AbstractAttributeParser_WrongStringConversion;

	public static String AbstractAttributeParser_UnknownLiteral;

	/**
	 * @since 3.2
	 */
	public static String AbstractAttributeParser_NullIsNotAllowed;

	/**
	 * @since 3.1
	 */
	public static String DefaultModelElementSelectionPageMessage;

}
