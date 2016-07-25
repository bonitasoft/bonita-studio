package org.bonitasoft.studio.application.views.process;

import org.bonitasoft.studio.application.views.BonitaPropertiesView;
import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.bonitasoft.studio.diagram.custom.part.BonitaExecutionPropertiesBrowserPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public class BonitaExecutionPropertiesView extends BonitaPropertiesView {

	public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.process.execution";
	
	  protected String getViewId() {
	        return VIEW_ID;
	    }
	
	@Override
	protected BonitaPropertiesBrowserPage getBonitaPropertiesBrowserPage(
			ITabbedPropertySheetPageContributor part) {
		return new BonitaExecutionPropertiesBrowserPage(part);
	}

}
