package org.bonitasoft.studio.document.i18n;


import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

	public static String newDocument;
	public static String documentWizardPageTitle;
	public static String newDocumentWizardDescription;
	public static String name;
	public static String description;
	public static String mimeType;
	public static String hintMimeTypeDocument;
	public static String External;
	public static String explanationExternalDocument;
	public static String Internal;
	public static String explanationInternalDocument;
	public static String hintExternalUrl;
	public static String Browse;
	
	
	 static {
	        // initialize resource bundle
	        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	    }

	    private Messages() {
	    }
}
