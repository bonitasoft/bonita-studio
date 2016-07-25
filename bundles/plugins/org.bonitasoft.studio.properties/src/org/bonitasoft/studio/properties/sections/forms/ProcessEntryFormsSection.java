/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.sections.forms;

import org.bonitasoft.studio.model.process.EntryPageFlowType;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Aurelien Pupier
 */
public class ProcessEntryFormsSection extends EntryFormsSection {

    @Override
    protected Composite createRadioButtons(final Composite parent) {
        final Composite radioComposite = getWidgetFactory().createComposite(parent);
        radioComposite.setLayout(new GridLayout(2, false));
        pageFlowRadio = getWidgetFactory().createButton(radioComposite, Messages.pageFlow, SWT.RADIO);
        skipRadio = getWidgetFactory().createButton(radioComposite, Messages.skip, SWT.RADIO);
        skipRadio.setToolTipText(Messages.skipPageFlowTooltip);
        return radioComposite;
    }

    @Override
    protected Object getPageFlowType() {
        if (pageFlowRadio.getSelection()) {
            hideAllContentComposite();
            showOrHideComposite(pageFlowComposite, true);
            return EntryPageFlowType.PAGEFLOW;
        } else if (skipRadio.getSelection()) {
            hideAllContentComposite();
            return EntryPageFlowType.SKIP;
        }
        return null;
    }

    @Override
    public PageFlow getPageFlow() {
        return super.getPageFlow();
    }

    @Override
    protected void refreshDataBinding() {
        super.refreshDataBinding();

        context.bindValue(SWTObservables.observeSelection(pageFlowRadio),
                EMFEditObservables.observeValue(getEditingDomain(), getPageFlow(), ProcessPackage.Literals.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE),
                new UpdateValueStrategy() {

                    @Override
                    public Object convert(final Object object) {
                        return getPageFlowType();
                    }
                }, new UpdateValueStrategy() {

                    @Override
                    public Object convert(final Object object) {
                        return object == EntryPageFlowType.PAGEFLOW;
                    }
                });
        context.bindValue(SWTObservables.observeSelection(skipRadio),
                EMFEditObservables.observeValue(getEditingDomain(), getPageFlow(), ProcessPackage.Literals.PAGE_FLOW__ENTRY_PAGE_FLOW_TYPE),
                new UpdateValueStrategy() {

                    @Override
                    public Object convert(final Object object) {
                        return getPageFlowType();
                    }
                }, new UpdateValueStrategy() {

                    @Override
                    public Object convert(final Object object) {
                        return object == EntryPageFlowType.SKIP;
                    }
                });

        showOrHideComposite(pageFlowComposite, getPageFlow().getEntryPageFlowType() == EntryPageFlowType.PAGEFLOW);
    }

    @Override
    public String getSectionDescription() {
        return Messages.sectionDescriptionProcessEntryForm;
    }

}
