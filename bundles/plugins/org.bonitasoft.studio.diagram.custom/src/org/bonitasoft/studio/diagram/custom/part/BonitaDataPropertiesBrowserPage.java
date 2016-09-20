package org.bonitasoft.studio.diagram.custom.part;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.instanceOf;
import static com.google.common.base.Predicates.not;

import org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.SendTask;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;

public class BonitaDataPropertiesBrowserPage extends
        BonitaPropertiesBrowserPage {

    public static final String VIEW_ID = "org.bonitasoft.studio.views.properties.process.data";

    public BonitaDataPropertiesBrowserPage(
            final ITabbedPropertySheetPageContributor contributor) {
        super(contributor);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.views.BonitaPropertiesBrowserPage#defaultSelectedTabIndex()
     */
    @Override
    protected int defaultSelectedTabIndex(final ISelection input) {
        return activitySelected(input) ? 1 : 0;
    }

    private boolean activitySelected(final ISelection input) {
        if (!input.isEmpty() && input instanceof IStructuredSelection) {
            final Object selectedElement = ((IStructuredSelection) input).getFirstElement();
            if (selectedElement instanceof IAdaptable) {
                final EObject element = (EObject) ((IAdaptable) selectedElement).getAdapter(EObject.class);
                return and(instanceOf(Activity.class), not(instanceOf(SendTask.class))).apply(element);
            }
        }
        return false;
    }

    @Override
    protected String getViewID() {
        return VIEW_ID;
    }

}
