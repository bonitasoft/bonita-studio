package org.bonitasoft.studio.diagram.custom.part;

import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public class BonitaExecutionPropertiesBrowserPage extends
		BonitaPropertiesBrowserPage {

	public BonitaExecutionPropertiesBrowserPage(
			ITabbedPropertySheetPageContributor contributor) {
		super(contributor);
	}

	@Override
	protected String getViewID() {
		
		return  "org.bonitasoft.studio.views.properties.process.execution";
	}

}
