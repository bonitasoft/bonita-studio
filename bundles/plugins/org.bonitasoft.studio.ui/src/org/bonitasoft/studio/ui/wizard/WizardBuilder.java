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
package org.bonitasoft.studio.ui.wizard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IPageChangeProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.WorkbenchPlugin;

/**
 * A helper builder to create a JFace {@link Wizard}
 */
public class WizardBuilder<T> {

    private String windowTitle;
    private final List<WizardPageBuilder> pages = new ArrayList<>();
    private FinishHandler<T> finishHandler;
    private Optional<T> finishResult = Optional.empty();
    private boolean needProgress = false;
    private int width = SWT.DEFAULT;
    private int height = SWT.DEFAULT;
    private int nbLine = 0;
    private boolean fixedInitialSize = false;

    private WizardBuilder() {
    }

    public static <T> WizardBuilder<T> newWizard() {
        return new WizardBuilder<>();
    }

    /**
     * Set the window title of the {@link Dialog} containing the {@link Wizard}
     */
    public WizardBuilder<T> withTitle(String title) {
        this.windowTitle = title;
        return this;
    }

    public WizardBuilder<T> needProgress() {
        this.needProgress = true;
        return this;
    }

    /**
     * Add Wizard page to this {@link Wizard} using {@link WizardPageBuilder}
     */
    public WizardBuilder<T> havingPage(WizardPageBuilder... pageBuilders) {
        Stream.of(pageBuilders).forEach(pages::add);
        return this;
    }

    /**
     * A handler executed in the performFinish() operation of the {@link Wizard}
     */
    public WizardBuilder<T> onFinish(FinishHandler<T> handler) {
        this.finishHandler = handler;
        return this;
    }

    /**
     * fix the initial size of the wizard withg the height and width provided
     */
    public WizardBuilder<T> withFixedInitialSize() {
        this.fixedInitialSize = true;
        return this;
    }

    /**
     * Create an instance of {@link Wizard} from the builder
     */
    public Wizard asWizard() {
        final Wizard wizard = new Wizard() {

            @Override
            public boolean performFinish() {
                pages.stream()
                        .map(WizardPageBuilder::getControlSupplier)
                        .filter(Objects::nonNull)
                        .forEachOrdered(controlSupplier -> controlSupplier.saveSettings(getDialogSettings()));
                try {
                    if (finishHandler != null) {
                        finishResult = finishHandler.finish(getContainer());
                        return finishResult.isPresent();
                    }
                    return true;
                } catch (final Throwable t) {
                    new ExceptionDialogHandler().openErrorDialog(getShell(), Messages.errorOccuredDuringFinish, t);
                    return false;
                }
            }

            @Override
            public void setContainer(IWizardContainer wizardContainer) {
                super.setContainer(wizardContainer);
                if (wizardContainer instanceof IPageChangeProvider) {
                    IPageChangeProvider pageChangeProvider = (IPageChangeProvider) wizardContainer;
                    Stream.of(getPages())
                            .filter(IPageChangedListener.class::isInstance)
                            .map(IPageChangedListener.class::cast)
                            .forEach(pageChangeProvider::addPageChangedListener);
                }
            }

        };
        pages.stream().forEachOrdered(page -> wizard.addPage(page.asPage()));
        wizard.setNeedsProgressMonitor(needProgress);
        wizard.setWindowTitle(windowTitle);
        wizard.setDefaultPageImageDescriptor(Pics.getWizban());
        wizard.setDialogSettings(WorkbenchPlugin.getDefault().getDialogSettings());
        return wizard;
    }

    /**
     * Create the {@link Wizard} instance and open it in a {@link WizardDialog} for the given {@link Shell}
     * 
     * @param finishButton The label of the finish button
     */
    public Optional<T> open(Shell shell, String finishButton) {
        new CustomLabelWizardDialog(shell, asWizard(), finishButton) {

            @Override
            protected Point getInitialSize() {
                final Point initialSize = super.getInitialSize();
                if (!fixedInitialSize && initialSize.x > width) {
                    width = initialSize.x;
                }
                if (!fixedInitialSize && initialSize.y > height) {
                    height = initialSize.y;
                }
                final Point size = new Point(width == SWT.DEFAULT ? initialSize.x : width,
                        height == SWT.DEFAULT ? initialSize.y : height);
                size.y = size.y + convertHeightInCharsToPixels(nbLine);
                return size;
            }
        }.open();
        return finishResult;
    }

    /**
     * Create the {@link Wizard} instance and open it in a {@link WizardDialog} for the given {@link Shell}
     */
    public Optional<T> open(Shell shell) {
        open(shell, IDialogConstants.FINISH_LABEL);
        return finishResult;
    }

    public WizardBuilder<T> withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Use to compute additional vertical space for the given number of line
     * 
     * @param nbLine, the number of line to add to the initial size of the dialog
     */
    public WizardBuilder<T> verticalHeightAdjustment(int nbLine) {
        this.nbLine = nbLine;
        return this;
    }
}
