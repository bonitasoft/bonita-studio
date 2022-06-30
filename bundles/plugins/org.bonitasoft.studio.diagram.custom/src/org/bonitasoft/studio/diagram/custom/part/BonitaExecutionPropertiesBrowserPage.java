package org.bonitasoft.studio.diagram.custom.part;

import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public class BonitaExecutionPropertiesBrowserPage extends
		BonitaPropertiesBrowserPage {

    public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.process.execution";

    public BonitaExecutionPropertiesBrowserPage(
			ITabbedPropertySheetPageContributor contributor) {
		super(contributor);
	}

	@Override
	protected String getViewID() {
		return VIEW_ID;
	}

}
