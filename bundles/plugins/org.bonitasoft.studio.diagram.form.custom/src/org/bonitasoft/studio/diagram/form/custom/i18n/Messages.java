package org.bonitasoft.studio.diagram.form.custom.i18n;


import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	
	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
	public static String addColumnCommand;
	public static String addRowCommand;
	public static String RemoveColumnCommand;
	public static String RemoveRowCommand;
	public static String gridLayoutMoveCommand;
	public static String insertColumnTooltip;
	public static String insertRowTooltip;
	public static String removeRowTooltip;
	public static String removeColumnTooltip;
	public static String labelPreviousForm;
	public static String labelNextForm;
	public static String copyOfLabel;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
