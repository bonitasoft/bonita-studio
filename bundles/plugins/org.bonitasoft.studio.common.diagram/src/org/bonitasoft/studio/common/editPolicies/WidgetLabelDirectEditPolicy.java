/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.editPolicies;

import org.bonitasoft.studio.model.form.HiddenWidget;
import org.bonitasoft.studio.model.form.HtmlWidget;
import org.bonitasoft.studio.model.form.IFrameWidget;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.internal.parts.TextCellEditorEx;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.widgets.Text;


/**
 * @author Romain Bioteau
 *
 */
public class WidgetLabelDirectEditPolicy extends LabelDirectEditPolicy {

    /**
     * We need an adapter that will be able to hold both a model
     * and an view
     */
    class EObjectAdapterEx
    extends EObjectAdapter {

        private View view = null;

        /**
         * constructor
         * @param element   element to be wrapped
         * @param view  view to be wrapped
         */
        public EObjectAdapterEx(EObject element, View view) {
            super(element);
            this.view = view;
        }

        @Override
        public Object getAdapter(Class adapter) {
            Object o = super.getAdapter(adapter);
            if (o != null) {
                return o;
            }
            if (adapter.equals(View.class)) {
                return view;
            }
            return null;
        }
    }

    @Override
    protected Command getDirectEditCommand(DirectEditRequest edit) {
        if (edit.getCellEditor() instanceof TextCellEditorEx) {
            if (!((TextCellEditorEx) edit.getCellEditor()).hasValueChanged()) {
                return null;
            }
        }

        String labelText = ((Text)edit.getCellEditor().getControl()).getText();

        //for CellEditor, null is always returned for invalid values
        if (labelText == null) {
            return null;
        }

        ITextAwareEditPart compartment = (ITextAwareEditPart) getHost();
        EObject model = (EObject)compartment.getModel();
        EObjectAdapter elementAdapter = null ;
        if (model instanceof View) {
            View view = (View)model;
            EObject resolveSemanticElement = ViewUtil.resolveSemanticElement(view);
           if(resolveSemanticElement instanceof Widget && !(resolveSemanticElement instanceof MessageInfo)
        		   && !(resolveSemanticElement instanceof IFrameWidget)
        		   && !(resolveSemanticElement instanceof HiddenWidget)
        		   && !(resolveSemanticElement instanceof HtmlWidget)){
        	   elementAdapter = new EObjectAdapterEx(((Widget) resolveSemanticElement).getDisplayLabel(),
                       view);
           }else{
        	   elementAdapter = new EObjectAdapterEx(resolveSemanticElement,
                       view);
           }
         
        }
        else{
        	 if(!(model instanceof MessageInfo)
          		   && !(model instanceof IFrameWidget)
          		   && !(model instanceof HiddenWidget)
          		   && !(model instanceof HtmlWidget)){
        		  elementAdapter = new EObjectAdapterEx(((Widget)model).getDisplayLabel(), null);
        	 }else{
        		  elementAdapter = new EObjectAdapterEx(((Widget)model), null);
        	 }
          
        }
        // check to make sure an edit has occurred before returning a command.
        String prevText = compartment.getParser().getEditString(elementAdapter,
                compartment.getParserOptions().intValue());
        if (!prevText.equals(labelText)) {
            ICommand iCommand =
                    compartment.getParser().getParseCommand(elementAdapter, labelText, 0);
            return new ICommandProxy(iCommand);
        }

        return null;
    }

}
