package org.bonitasoft.studio.application.views.process;

import org.bonitasoft.studio.application.views.BonitaPropertiesView;
import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.bonitasoft.studio.diagram.custom.part.BonitaDataPropertiesBrowserPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public class BonitaDataPropertiesView extends BonitaPropertiesView {

    public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.process.data";

    protected String getViewId() {
        return VIEW_ID;
    }

    @Override
    protected BonitaPropertiesBrowserPage getBonitaPropertiesBrowserPage(
            final ITabbedPropertySheetPageContributor part) {
        return new BonitaDataPropertiesBrowserPage(part);
    }

}
