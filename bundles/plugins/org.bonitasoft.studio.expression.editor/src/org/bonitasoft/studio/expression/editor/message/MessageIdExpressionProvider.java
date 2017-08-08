/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.expression.editor.message;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.i18n.Messages;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ListExpression;
import org.bonitasoft.studio.model.expression.TableExpression;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.Message;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class MessageIdExpressionProvider implements IExpressionProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressions(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Set<Expression> getExpressions(EObject context) {
        AbstractCatchMessageEvent catchMessageEvent = ModelHelper.getFirstContainerOfType(context,
                AbstractCatchMessageEvent.class);
        String event = ((AbstractCatchMessageEvent) catchMessageEvent).getEvent();
        if (event != null) {
            final Message message = ModelHelper.findEvent(catchMessageEvent, event);
            if (message != null) {
                TableExpression throwMessageContent = message.getMessageContent();
                HashSet<Expression> messageContentIds = new HashSet<Expression>();
                for (int i = 0; i < throwMessageContent.getExpressions().size(); i++) {
                    ListExpression row = throwMessageContent.getExpressions().get(i);
                    Expression id = row.getExpressions().get(0);
                    Expression value = row.getExpressions().get(1);
                    if (id != null && id.getName() != null) {
                        messageContentIds.add(createExpression(id.getName(), value.getReturnType()));
                    }
                }
                return messageContentIds;
            }
        }
        return Collections.emptySet();
    }

    private Expression createExpression(String name, String returnType) {
        Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(getExpressionType());
        exp.setContent(name);
        exp.setName(name);
        exp.setReturnType(returnType);
        return exp;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getExpressionType()
     */
    @Override
    public String getExpressionType() {
        return ExpressionConstants.MESSAGE_ID_TYPE;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getIcon(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public Image getIcon(Expression expression) {
        return Pics.getImage(PicsConstants.enveloppe);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getTypeIcon()
     */
    @Override
    public Image getTypeIcon() {
        return Pics.getImage(PicsConstants.enveloppe);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#getProposalLabel(org.bonitasoft.studio.model.expression.Expression)
     */
    @Override
    public String getProposalLabel(Expression expression) {
        return expression.getContent();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.expression.editor.provider.IExpressionProvider#isRelevantFor(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isRelevantFor(EObject context) {
        return ModelHelper.getFirstContainerOfType(context, AbstractCatchMessageEvent.class) != null;
    }

    @Override
    public String getTypeLabel() {
        return Messages.messageIdTypeLabel;
    }

    @Override
    public IExpressionEditor getExpressionEditor(Expression expression, EObject context) {
        return new MessageIdExpressionEditor();
    }

}
