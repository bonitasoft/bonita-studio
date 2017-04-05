/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.viewer;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class LabelProviderBuilder<T> {

    private Optional<Function<T, String>> textFunction = Optional.empty();
    private Optional<Function<T, Image>> imageFunction = Optional.empty();
    private Optional<Function<T, IStatus>> statusProvider = Optional.empty();
    private Optional<Function<T, Font>> fontProvider = Optional.empty();
    private boolean refreshAll;

    public LabelProviderBuilder<T> withTextProvider(Function<T, String> textFunction) {
        this.textFunction = Optional.ofNullable(textFunction);
        return this;
    }

    public LabelProviderBuilder<T> withImageProvider(Function<T, Image> imageFunction) {
        this.imageFunction = Optional.ofNullable(imageFunction);
        return this;
    }

    public LabelProviderBuilder<T> withStatusProvider(Function<T, IStatus> statusProvider) {
        this.statusProvider = Optional.ofNullable(statusProvider);
        return this;
    }

    public LabelProviderBuilder<T> shouldRefreshAllLabels() {
        this.refreshAll = true;
        return this;
    }

    public LabelProviderBuilder<T> withFontProvider(Function<T, Font> fontProvider) {
        this.fontProvider = Optional.ofNullable(fontProvider);
        return this;
    }

    public ColumnLabelProvider createColumnLabelProvider() {
        return new ColumnLabelProvider() {

            private Color errorColor;
            private Color warningColor;

            @Override
            protected void initialize(ColumnViewer viewer, ViewerColumn column) {
                super.initialize(viewer, column);
                errorColor = new Color(Display.getDefault(), ColorConstants.ERROR_RGB);
                warningColor = new Color(Display.getDefault(), ColorConstants.WARNING_RGB);
                if (refreshAll) {
                    viewer.getColumnViewerEditor().addEditorActivationListener(refreshAllAfterEdit(viewer));
                }
            }

            private ColumnViewerEditorActivationListener refreshAllAfterEdit(ColumnViewer viewer) {
                return new ColumnViewerEditorActivationListener() {

                    @Override
                    public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                    }

                    @Override
                    public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
                    }

                    @Override
                    public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                        if (viewer.getInput() instanceof Collection) {
                            viewer.getControl().getDisplay().asyncExec(
                                    () -> viewer.update(((Collection<Object>) viewer.getInput()).toArray(), null));
                        }
                    }

                    @Override
                    public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
                    }
                };
            }

            @Override
            public void dispose() {
                errorColor.dispose();
                warningColor.dispose();
                super.dispose();
            }

            @Override
            public void update(ViewerCell cell) {
                super.update(cell);
                final T element = (T) cell.getElement();
                statusProvider.ifPresent(provider -> {
                    final IStatus status = provider.apply(element);
                    cell.setImage(statusImage(status, element));
                    cell.setForeground(statusColor(status));
                });
                fontProvider.ifPresent(provider -> {
                    cell.setFont(provider.apply(element));
                });
            }

            @Override
            public String getToolTipText(Object element) {
                if (statusProvider.isPresent()) {
                    final IStatus status = statusProvider.get().apply((T) element);
                    if (!status.isOK()) {
                        return status.getMessage();
                    }
                }
                return super.getToolTipText(element);
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                try {
                    final T e = (T) element;
                    return textFunction.map(function -> function.apply(e)).orElse(super.getText(element));
                } catch (final ClassCastException e) {
                    return super.getText(element);
                }
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
             */
            @Override
            public Image getImage(Object element) {
                try {
                    final T e = (T) element;
                    return imageFunction.map(function -> function.apply(e)).orElse(super.getImage(e));
                } catch (final ClassCastException e) {
                    return super.getImage(element);
                }
            }

            private Color statusColor(IStatus status) {
                switch (status.getSeverity()) {
                    case IStatus.ERROR:
                        return errorColor;
                    case IStatus.WARNING:
                        return warningColor;
                    default:
                        return Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
                }
            }

            private Image statusImage(IStatus status, T element) {
                switch (status.getSeverity()) {
                    case IStatus.ERROR:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
                    case IStatus.WARNING:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
                    default:
                        return imageFunction.map(function -> function.apply(element)).orElse(super.getImage(element));
                }
            }
        };
    }

    public LabelProvider createLabelProvider() {
        return new LabelProvider() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getText(java.lang.Object)
             */
            @Override
            public String getText(Object element) {
                try {
                    final T e = (T) element;
                    return textFunction.map(function -> function.apply(e)).orElse(super.getText(element));
                } catch (final ClassCastException e) {
                    return super.getText(element);
                }
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
             */
            @Override
            public Image getImage(Object element) {
                try {
                    final T e = (T) element;
                    return imageFunction.map(function -> function.apply(e)).orElse(super.getImage(element));
                } catch (final ClassCastException e) {
                    return super.getImage(element);
                }
            }
        };
    }
}
