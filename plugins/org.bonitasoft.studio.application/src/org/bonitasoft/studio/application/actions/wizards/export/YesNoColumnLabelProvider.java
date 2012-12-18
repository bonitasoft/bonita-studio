package org.bonitasoft.studio.application.actions.wizards.export;

import org.bonitasoft.studio.application.i18n.Messages;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;

/**
 * @author Mickael Istria
 *
 */
public abstract class YesNoColumnLabelProvider extends ColumnLabelProvider {
	
	public abstract boolean getBoolean(Object item);
	
	@Override
	public String getText(Object item) {
		if (!showNothing(item)) {
			if (getBoolean(item)) {
				return Messages.yes;
			} else {
				return Messages.no;
			}
		} else {
			return "";
		}
	}

	/**
	 * @return
	 */
	protected boolean showNothing(Object item) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
	 */
	public Color getForeground(Object element) {
		if (getBoolean(element)) {
			return ColorConstants.darkGreen;
		} else {
			return ColorConstants.red;
		}
	}
}