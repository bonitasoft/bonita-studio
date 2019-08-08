package org.bonitasoft.studio.importer.bos.provider;

import org.bonitasoft.studio.importer.bos.model.AbstractFileModel;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;

public class ActionLabelProvider extends LabelProvider implements IStyledLabelProvider {

    @Override
    public StyledString getStyledText(Object element) {
        StyledString styledString = new StyledString();
        if (element instanceof AbstractFileModel) {
            AbstractFileModel fileModel = (AbstractFileModel) element;
            styledString.append(fileModel.isConflicting() ? fileModel.getImportAction().toString() : "");
        }
        return styledString;
    }

}
