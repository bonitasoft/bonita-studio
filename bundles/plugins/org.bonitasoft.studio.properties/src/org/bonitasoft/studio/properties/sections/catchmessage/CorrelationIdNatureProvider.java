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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionNatureProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.eclipse.emf.ecore.EObject;

/**
 * @author aurelie Zara
 *
 */
public class CorrelationIdNatureProvider implements IExpressionNatureProvider{


    @Override
    public Expression[] getExpressions(final EObject context) {
        if (context instanceof AbstractCatchMessageEvent) {
            final MessageFlow incomingMessag = ((AbstractCatchMessageEvent) context).getIncomingMessag();
            TableExpression throwCorrelations = null;
            Expression[] expressionsList = null;
            if (incomingMessag != null) {
                final Message message = ModelHelper.findEvent((Element) context, incomingMessag.getName());
                if (message != null) {
                    throwCorrelations = message.getCorrelation().getCorrelationAssociation();
                    expressionsList = new Expression[throwCorrelations.getExpressions().size()];
                    for (int i = 0; i < throwCorrelations.getExpressions().size(); i++) {
                        final ListExpression row = throwCorrelations.getExpressions().get(i);
                        expressionsList[i] = row.getExpressions().get(0);
                    }
                }
            }
            return expressionsList;
        }
        return null;
    }

}
