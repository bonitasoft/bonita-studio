/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Message;
import org.eclipse.emf.ecore.EObject;

/**
 * @author aurelie zara
 *
 */
public class ActionExpressionNatureProvider implements IExpressionNatureProvider {


    @Override
    public Expression[] getExpressions(final EObject context) {
        if (context instanceof AbstractCatchMessageEvent) {
            final String event = ((AbstractCatchMessageEvent) context).getEvent();
            TableExpression throwMessageContent = null;
            Set<Expression> messageContentIds = null;
            if (event != null) {
                final Message message = ModelHelper.findEvent((Element) context, event);
                if (message != null) {
                    throwMessageContent = message.getMessageContent();
                    messageContentIds = new HashSet<Expression>();
                    for (int i = 0; i < throwMessageContent.getExpressions().size(); i++) {
                        final ListExpression row = throwMessageContent.getExpressions().get(i);
                        final Expression id = row.getExpressions().get(0);
                        if (id != null && id.getName() != null) {
                            messageContentIds.add(id);
                        }
                    }
                }
            }
            if (messageContentIds != null) {
                return messageContentIds.toArray(new Expression[messageContentIds.size()]);
            }
        }
        return null;
    }


}
