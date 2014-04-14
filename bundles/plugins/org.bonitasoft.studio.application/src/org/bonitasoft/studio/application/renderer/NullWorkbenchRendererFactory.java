/**
 * 
 */
package org.bonitasoft.studio.application.renderer;

import org.eclipse.e4.ui.internal.workbench.swt.AbstractPartRenderer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.workbench.renderers.swt.WorkbenchRendererFactory;

/**
 * @author Romain Bioteau
 * 
 */
public class NullWorkbenchRendererFactory extends WorkbenchRendererFactory {

    @Override
    public AbstractPartRenderer getRenderer(MUIElement uiElement, Object parent) {
        return null;
    }
}
