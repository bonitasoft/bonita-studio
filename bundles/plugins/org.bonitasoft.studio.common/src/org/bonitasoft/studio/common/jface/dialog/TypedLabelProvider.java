package org.bonitasoft.studio.common.jface.dialog;

import org.eclipse.swt.graphics.Image;


public interface TypedLabelProvider<T>  {

    String getText(T element);
  
    Image getImage(T element);
    
}
