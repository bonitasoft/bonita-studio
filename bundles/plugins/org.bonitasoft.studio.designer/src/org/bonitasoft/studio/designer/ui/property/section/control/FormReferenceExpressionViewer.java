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
package org.bonitasoft.studio.designer.ui.property.section.control;

import java.util.Objects;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.designer.core.command.UpdateFormMappingCommand;
import org.bonitasoft.studio.designer.core.repository.WebPageFileStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.expression.editor.viewer.EditExpressionDialog;
import org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.process.FormMapping;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import com.google.common.base.Preconditions;

public class FormReferenceExpressionViewer extends ExpressionViewer {

    private final WebPageRepositoryStore pageStore;
    private final CreateOrEditFormProposalListener createOrEditFormListener;

    public FormReferenceExpressionViewer(final Composite composite, final int style, final TabbedPropertySheetWidgetFactory widgetFactory,
            final WebPageRepositoryStore pageStore, final CreateOrEditFormProposalListener createOrEditFormListener) {
        super(composite, style, widgetFactory);
        this.pageStore = pageStore;
        this.createOrEditFormListener = createOrEditFormListener;
    }

    @Override
    protected EditExpressionDialog createEditDialog(final EObject editInput) {
        return null;
    }

    @Override
    protected String getContentTypeFromInput(final String input) {
        return ExpressionConstants.FORM_REFERENCE_TYPE;
    }

    @Override
    protected String defaultExpressionType() {
        return ExpressionConstants.FORM_REFERENCE_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.viewer.ExpressionViewer#getNameConverter()
     */
    @Override
    protected Converter getNameConverter() {
        return new Converter(String.class, String.class) {

            @Override
            public Object convert(final Object fromObject) {
                final String content = getSelectedExpression().getContent();
                final WebPageFileStore webPageFileStore = pageStore.getChild(content);
                if (webPageFileStore == null) {
                    updateContent("");
                    updateName("");
                    return "";
                }
                final String pageName = webPageFileStore.getCustomPageName();
                if (!Objects.equals(pageName,fromObject)) {
                    updateName(pageName);
                }
                return pageName;
            }
        };
    }

    protected void updateName(final String newName) {
        expressionItemProvider.setPropertyValue(getSelectedExpression(), ExpressionPackage.Literals.EXPRESSION__NAME.getName(), newName);
    }


    @Override
    protected void editControlSelected(final ToolBar tb, final Event event, final EditingDomain editingDomain) {
        Preconditions.checkState(context instanceof FormMapping);
        final String newPageId = createOrEditFormListener.handleEvent(context, null);
        if (newPageId != null) {
            pageStore.refresh();
            final WebPageFileStore webPageFileStore = pageStore.getChild(newPageId);
            if (webPageFileStore != null) {
                editingDomain.getCommandStack().execute(new UpdateFormMappingCommand(editingDomain, (FormMapping) context,
                        ExpressionHelper.createFormReferenceExpression(webPageFileStore.getName(), newPageId)));
            }
        }
    }

}
