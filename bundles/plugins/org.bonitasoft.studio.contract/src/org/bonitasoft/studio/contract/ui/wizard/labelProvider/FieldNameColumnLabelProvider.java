/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.contract.ui.wizard.labelProvider;

import org.bonitasoft.studio.contract.core.mapping.FieldToContractInputMapping;
import org.bonitasoft.studio.contract.core.mapping.UnselectLazyReferencesInMultipleContainer;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class FieldNameColumnLabelProvider extends ColumnLabelProvider {

    private UnselectLazyReferencesInMultipleContainer lazyFieldStatusProvider;

    public FieldNameColumnLabelProvider(UnselectLazyReferencesInMultipleContainer lazyFieldStatusProvider) {
        this.lazyFieldStatusProvider = lazyFieldStatusProvider;
    }

    @Override
    public String getText(final Object element) {
        if (element instanceof FieldToContractInputMapping) {
            return ((FieldToContractInputMapping) element).getField().getName();
        }
        return super.getText(element);
    }

    @Override
    public Image getToolTipImage(Object element) {
        IStatus status = lazyFieldStatusProvider.getStatus((FieldToContractInputMapping) element);
        if (!status.isOK()) {
            return getStatusImage(status.getSeverity());
        }
        return super.getToolTipImage(element);
    }

    @Override
    public String getToolTipText(Object element) {
        IStatus status = lazyFieldStatusProvider.getStatus((FieldToContractInputMapping) element);
        if (!status.isOK()) {
            return status.getMessage();
        }
        return super.getToolTipText(element);
    }

    @Override
    public Image getImage(Object element) {
        IStatus status = lazyFieldStatusProvider.getStatus((FieldToContractInputMapping) element);
        if (!status.isOK()) {
            return getStatusImage(status.getSeverity());
        }
        return super.getImage(element);
    }

    private Image getStatusImage(int severity) {
        switch (severity) {
            case IStatus.ERROR:
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
            case IStatus.WARNING:
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
            case IStatus.INFO:
                return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
            default:
                return null;
        }

    }
}
