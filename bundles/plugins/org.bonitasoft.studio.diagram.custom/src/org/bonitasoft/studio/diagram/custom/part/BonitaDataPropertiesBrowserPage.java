package org.bonitasoft.studio.diagram.custom.part;

import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public class BonitaDataPropertiesBrowserPage extends
        BonitaPropertiesBrowserPage {

    public BonitaDataPropertiesBrowserPage(
            final ITabbedPropertySheetPageContributor contributor) {
        super(contributor);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage#defaultSelectedTabIndex()
     */
    @Override
    protected int defaultSelectedTabIndex() {
        return 1;
    }

    @Override
    protected String getViewID() {
        return "org.bonitasoft.studio.views.properties.process.data";
    }

}
