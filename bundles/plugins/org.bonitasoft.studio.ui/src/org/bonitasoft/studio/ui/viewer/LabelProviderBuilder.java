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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class LabelProviderBuilder<T> {

    private Optional<Function<T, String>> textFunction = Optional.empty();
    private Optional<Function<T, StyledString>> styledStringFunction = Optional.empty();
    private Optional<Function<T, Image>> imageFunction = Optional.empty();
    private Optional<Function<T, IStatus>> statusProvider = Optional.empty();
    private Optional<Function<T, Font>> fontProvider = Optional.empty();
    private Optional<Function<T, String>> tooltipFunction = Optional.empty();
    private List<ColumnViewer> viewersToUpdate = new ArrayList<>();

    public LabelProviderBuilder<T> withTextProvider(Function<T, String> textFunction) {
        this.textFunction = Optional.ofNullable(textFunction);
        return this;
    }

    public LabelProviderBuilder<T> withTooltipProvider(Function<T, String> tooltipFunction) {
        this.tooltipFunction = Optional.ofNullable(tooltipFunction);
        return this;
    }

    public LabelProviderBuilder<T> withStyledStringProvider(Function<T, StyledString> styledStringFunction) {
        this.styledStringFunction = Optional.ofNullable(styledStringFunction);
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

    public LabelProviderBuilder<T> shouldRefreshAllLabels(ColumnViewer... viewers) {
        this.viewersToUpdate = Arrays.asList(viewers);
        return this;
    }

    public LabelProviderBuilder<T> withFontProvider(Function<T, Font> fontProvider) {
        this.fontProvider = Optional.ofNullable(fontProvider);
        return this;
    }

    public StyledCellLabelProvider createStyledCellLabelProvider() {

        return new StyledCellLabelProvider() {

            private Color errorColor;
            private Color warningColor;
            private ColumnViewer viewer;
            private boolean isDarkTheme;
            private Color darkModeSelectLineUnfocused;

            @Override
            public void initialize(ColumnViewer viewer, ViewerColumn column) {
                super.initialize(viewer, column);
                this.viewer = viewer;
                errorColor = new Color(Display.getDefault(), ColorConstants.ERROR_RGB);
                warningColor = new Color(Display.getDefault(), ColorConstants.WARNING_RGB);
                isDarkTheme = PreferenceUtil.isDarkTheme();
                darkModeSelectLineUnfocused = new Color(Display.getDefault(),
                        ColorConstants.DARK_MODE_TABLE_SELECTED_UNFOCUS);
                viewer.getColumnViewerEditor().addEditorActivationListener(refreshAllAfterEdit());
            }

            @Override
            public void update(ViewerCell cell) {
                T element = (T) cell.getElement();
                StyledString styledString = getStyledString(element);
                cell.setText(styledString.getString());
                cell.setImage(getImage(element));

                statusProvider.ifPresent(provider -> {
                    final IStatus status = provider.apply(element);
                    if (!status.isOK()) {
                        cell.setImage(statusImage(status, element));
                        statusColor(status).ifPresent(cell::setForeground);
                    } else {
                        cell.setForeground(null);
                    }
                });

                cell.setStyleRanges(styledString.getStyleRanges());
            }

            @Override
            public String getToolTipText(Object element) {
                return tooltipProvider().apply((T) element).orElse(super.getToolTipText(element));
            }

            private StyledString getStyledString(T element) {
                return styledStringFunction.map(function -> function.apply(element))
                        .orElse(new StyledString());
            }

            private Image getImage(T element) {
                return imageFunction.map(function -> function.apply(element)).orElse(null);
            }

            private Optional<Color> statusColor(IStatus status) {
                switch (status.getSeverity()) {
                    case IStatus.ERROR:
                        return Optional.of(errorColor);
                    case IStatus.WARNING:
                        return Optional.of(warningColor);
                    default:
                        return Optional.empty();
                }
            }

            private Image statusImage(IStatus status, T element) {
                switch (status.getSeverity()) {
                    case IStatus.ERROR:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
                    case IStatus.WARNING:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
                    case IStatus.INFO:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
                    default:
                        return getImage(element);
                }
            }

            private ColumnViewerEditorActivationListener refreshAllAfterEdit() {
                return refreshAfterEditListener();
            }

            @Override
            public void dispose() {
                errorColor.dispose();
                warningColor.dispose();
                darkModeSelectLineUnfocused.dispose();
                super.dispose();
            }

        };
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
                viewer.getColumnViewerEditor().addEditorActivationListener(refreshAllAfterEdit());
            }

            private ColumnViewerEditorActivationListener refreshAllAfterEdit() {
                return refreshAfterEditListener();
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
                    if (!status.isOK()) {
                        cell.setImage(statusImage(status, element));
                        statusColor(status).ifPresent(cell::setForeground);
                    } else {
                        cell.setForeground(null);
                    }
                });
                fontProvider.ifPresent(provider -> {
                    cell.setFont(provider.apply(element));
                });
            }

            @Override
            public String getToolTipText(Object element) {
                return tooltipProvider().apply((T) element).orElse(super.getToolTipText(element));
            }

            @Override
            public String getText(Object element) {
                try {
                    final T e = (T) element;
                    return textFunction.map(function -> function.apply(e)).orElse("");
                } catch (final ClassCastException e) {
                    return super.getText(element);
                }
            }

            @Override
            public Image getImage(Object element) {
                try {
                    final T e = (T) element;
                    return imageFunction.map(function -> function.apply(e)).orElse(super.getImage(e));
                } catch (final ClassCastException e) {
                    return super.getImage(element);
                }
            }

            private Optional<Color> statusColor(IStatus status) {
                switch (status.getSeverity()) {
                    case IStatus.ERROR:
                        return Optional.of(errorColor);
                    case IStatus.WARNING:
                        return Optional.of(warningColor);
                    default:
                        return Optional.empty();
                }
            }

            private Image statusImage(IStatus status, T element) {
                switch (status.getSeverity()) {
                    case IStatus.ERROR:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
                    case IStatus.WARNING:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
                    case IStatus.INFO:
                        return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
                    default:
                        return getImage(element);
                }
            }
        };
    }

    public LabelProvider createLabelProvider() {
        return new LabelProvider() {

            @Override
            public String getText(Object element) {
                try {
                    final T e = (T) element;
                    return textFunction.map(function -> function.apply(e)).orElse("");
                } catch (final ClassCastException e) {
                    return super.getText(element);
                }
            }

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

    private Function<T, Optional<String>> tooltipProvider() {
        return element -> {
            if (statusProvider.isPresent()) {
                final IStatus status = statusProvider.get().apply(element);
                if (!status.isOK()) {
                    if (status.isMultiStatus()) {
                        return Optional.ofNullable(Arrays.asList(((MultiStatus) status).getChildren()).stream()
                                .filter(s -> !s.isOK())
                                .map(IStatus::getMessage)
                                .reduce((message1, message2) -> String.format("%s\n%s", message1, message2)).orElse(""));
                    }
                    return Optional.ofNullable(status.getMessage());
                }
            }
            if (tooltipFunction.isPresent()) {
                return Optional.ofNullable(tooltipFunction.get().apply(element));
            }
            return Optional.empty();
        };
    }

    private ColumnViewerEditorActivationListener refreshAfterEditListener() {
        return new ColumnViewerEditorActivationListener() {

            @Override
            public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
            }

            @Override
            public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
            }

            @Override
            public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
                viewersToUpdate.forEach(viewer -> {
                    if (viewer.getInput() instanceof Collection) {
                        viewer.getControl().getDisplay().asyncExec(
                                () -> {
                                    if (viewer != null && !viewer.getControl().isDisposed()
                                            && viewer.getInput() != null) {
                                        viewer.update(((Collection<Object>) viewer.getInput()).toArray(), null);
                                    }
                                });
                    }
                });

            }

            @Override
            public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
            }
        };
    }

}
