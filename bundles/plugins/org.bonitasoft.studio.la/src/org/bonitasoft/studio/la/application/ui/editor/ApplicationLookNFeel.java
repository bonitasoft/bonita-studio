/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.la.application.ui.editor;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationDescriptorValidators;
import org.bonitasoft.studio.la.application.ui.validator.CustomLayoutValidator;
import org.bonitasoft.studio.la.application.ui.validator.CustomThemeValidator;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ApplicationLookNFeel extends Composite implements IValueChangeListener {

    private final FormToolkit toolkit;

    private ApplicationFormPage formPage;

    public ApplicationLookNFeel(Composite parent,
            FormToolkit toolkit,
            ApplicationNode application,
            ApplicationFormPage formPage) {
        super(parent, SWT.NONE);
        toolkit.adapt(this);
        this.formPage = formPage;
        this.toolkit = toolkit;
        final DataBindingContext ctx = new DataBindingContext();

        setLayout(GridLayoutFactory.swtDefaults().create());
        setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        buildLookNFeel(ctx, application);

        ctx.updateTargets();
    }

    private void buildLookNFeel(final DataBindingContext ctx, ApplicationNode application) {
        final IObservableValue<String> layoutModelObservable = PojoProperties.<ApplicationNode, String> value("layout")
                .observe(application);
        layoutModelObservable.addValueChangeListener(this);

        final IObservableValue<String> themeModelObservable = PojoProperties.<ApplicationNode, String> value("theme")
                .observe(application);
        themeModelObservable.addValueChangeListener(this);

        final MultiValidator layoutValidator = new MultiValidator.Builder().havingValidators(
                new EmptyInputValidator.Builder()
                        .warningLevel()
                        .withMessage(Messages.warningNoLayout).create(),
                ApplicationDescriptorValidators.customPageNameValidator(),
                new CustomLayoutValidator(formPage.getApplicationEditorProviders().getLayoutProposalProvider()))
                .create();

        new TextWidget.Builder()
                .withLabel(Messages.layout)
                .labelAbove()
                .withMessage(Messages.layoutMessage)
                .grabHorizontalSpace()
                .fill()
                .withProposalProvider(formPage.getApplicationEditorProviders().getLayoutProposalProvider())
                .bindTo(layoutModelObservable)
                .withDelay(500)
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy().withValidator(layoutValidator))
                .inContext(ctx)
                .adapt(toolkit)
                .createIn(this);

        MultiValidator themeValidator = new MultiValidator.Builder().havingValidators(
                new EmptyInputValidator.Builder()
                        .warningLevel()
                        .withMessage(Messages.warningNoTheme)
                        .create(),
                ApplicationDescriptorValidators.customPageNameValidator(),
                new CustomThemeValidator(formPage.getApplicationEditorProviders().getThemeProposalProvider()))
                .create();

        new TextWidget.Builder()
                .withLabel(Messages.theme)
                .withMessage(Messages.themeMessage)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .withProposalProvider(formPage.getApplicationEditorProviders().getThemeProposalProvider())
                .bindTo(themeModelObservable)
                .withDelay(500)
                .withTargetToModelStrategy(UpdateStrategyFactory.updateValueStrategy().withValidator(themeValidator))
                .inContext(ctx)
                .adapt(toolkit)
                .createIn(this);
    }

    @Override
    public void handleValueChange(ValueChangeEvent event) {
        formPage.makeDirty();
    }

}
