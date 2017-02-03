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
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * A helper builder to create a JFace {@link Wizard}
 */
public class WizardBuilder<T> {

    private String windowTitle;
    private final List<WizardPageBuilder> pages = new ArrayList<>();
    private FinishHandler<T> finishHandler;
    private final ImageDescriptor imageDescriptor = ImageDescriptor.createFromFile(WizardBuilder.class, "defaultPage.png");//$NON-NLS-N$
    private Optional<T> finishResult = Optional.empty();
    private boolean needProgress = false;

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
     * Create an instance of {@link Wizard} from the builder
     */
    public Wizard asWizard() {
        final Wizard wizard = new Wizard() {

            @Override
            public boolean performFinish() {
                try {
                    finishResult = finishHandler.finish(getContainer());
                    return finishResult.isPresent();
                } catch (final Throwable t) {
                    new ExceptionDialogHandler().openErrorDialog(getShell(), Messages.errorOccuredDuringFinish, t);
                    return false;
                }
            }
        };
        pages.stream().forEachOrdered(page -> wizard.addPage(page.asPage()));
        wizard.setNeedsProgressMonitor(needProgress);
        wizard.setWindowTitle(windowTitle);
        wizard.setDefaultPageImageDescriptor(imageDescriptor);
        return wizard;
    }

    /**
     * Create the {@link Wizard} instance and open it in a {@link WizardDialog} for the given {@link Shell}
     * 
     * @param finishButton The label of the finish button
     */
    public Optional<T> open(Shell shell, String finishButton) {
        new CustomLabelWizardDialog(shell, asWizard(), finishButton).open();
        return finishResult;
    }

    /**
     * Create the {@link Wizard} instance and open it in a {@link WizardDialog} for the given {@link Shell}
     */
    public Optional<T> open(Shell shell) {
        open(shell, IDialogConstants.FINISH_LABEL);
        return finishResult;
    }
}
