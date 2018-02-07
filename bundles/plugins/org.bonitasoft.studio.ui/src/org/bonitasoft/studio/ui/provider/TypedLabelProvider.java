package org.bonitasoft.studio.ui.provider;

import org.eclipse.swt.graphics.Image;

public interface TypedLabelProvider<T> {

    String getText(T element);

    Image getImage(T element);

    default String getToolTipText(T element) {
        return null;
    }

}
