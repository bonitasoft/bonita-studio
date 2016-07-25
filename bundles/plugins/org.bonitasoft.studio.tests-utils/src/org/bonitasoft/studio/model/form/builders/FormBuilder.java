/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.form.builders;

import org.bonitasoft.studio.model.Buildable;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.builders.ElementBuilder;

/**
 * @author Romain Bioteau
 */
public class FormBuilder extends ElementBuilder<Form, FormBuilder> {

    public static FormBuilder aForm() {
        return new FormBuilder();
    }

    public FormBuilder in(final Buildable<? extends PageFlow> pageFlowBuildable) {
        pageFlowBuildable.build().getForm().add(getBuiltInstance());
        return this;
    }

    public FormBuilder havingWidget(final Buildable<? extends Widget>... widgetBuildables) {
        for (final Buildable<? extends Widget> widgetBuildable : widgetBuildables) {
            getBuiltInstance().getWidgets().add(widgetBuildable.build());
        }
        return this;
    }

    public FormBuilder havingWidget(final Widget... widgets) {
        for (final Widget widget : widgets) {
            getBuiltInstance().getWidgets().add(widget);
        }
        return this;
    }

    public FormBuilder havingValidator(final ValidatorBuilder... validatorBuilders) {
        for (final ValidatorBuilder validatorBuilder : validatorBuilders) {
            getBuiltInstance().getValidators().add(validatorBuilder.build());
        }
        return this;
    }

    @Override
    protected Form newInstance() {
        return FormFactory.eINSTANCE.createForm();
    }

}
