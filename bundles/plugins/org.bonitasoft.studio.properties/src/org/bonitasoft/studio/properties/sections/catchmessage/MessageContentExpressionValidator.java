/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.properties.sections.catchmessage;

import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionValidator;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author aurelie Zara
 *
 */
public class MessageContentExpressionValidator implements IExpressionValidator {

    private AbstractCatchMessageEvent messageEvent;

    @Override
    public IStatus validate(final Object value) {
        if ( messageEvent !=null){
            final String expr = value.toString();
            if(expr == null || expr.isEmpty()){
                return ValidationStatus.ok();
            }
            final MessageFlow incomingMessag = messageEvent.getIncomingMessag();
            TableExpression throwMessageContent=null;
            if(incomingMessag != null){
                final Message message = ModelHelper.findEvent(messageEvent, incomingMessag.getName());
                if(message != null){
                    throwMessageContent = message.getMessageContent();
                    boolean isExisting =false;
                    for (final ListExpression row : throwMessageContent.getExpressions()) {
                        final List<org.bonitasoft.studio.model.expression.Expression> col =  row.getExpressions() ;
                        if (col.size()==2){
                            if (expr.equals(col.get(0).getName())){
                                isExisting =true;
                                break;
                            }
                        }
                    }
                    if (!isExisting){
                        return ValidationStatus.warning(Messages.bind(Messages.messageContentIdExistenceWarning,expr,message.getName()));
                    }
                }
            }
        } else {
            return ValidationStatus.warning(Messages.NoIncomingMessageWarning);
        }
        return ValidationStatus.OK_STATUS;
    }

    public void setCatchMessageEvent(final EObject object){
        if (object instanceof AbstractCatchMessageEvent ){
            messageEvent = (AbstractCatchMessageEvent)object;
        }
    }

	@Override
	public void setInputExpression(final Expression inputExpression) {
        //Nothing
	}

	@Override
	public void setDomain(final EditingDomain domain) {
        //Nothing
	}

	@Override
	public void setContext(final EObject context) {
        //Nothing
	}

    @Override
    public boolean isRelevantForExpressionType(final String type) {
        return ExpressionConstants.MESSAGE_ID_TYPE.equals(type)
                || ExpressionConstants.CONSTANT_TYPE.equals(type);
    }

}
