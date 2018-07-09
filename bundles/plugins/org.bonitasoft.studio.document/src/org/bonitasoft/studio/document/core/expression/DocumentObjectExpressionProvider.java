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

package org.bonitasoft.studio.document.core.expression;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.expression.editor.provider.IExpressionEditor;
import org.bonitasoft.studio.expression.editor.provider.IExpressionProvider;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class DocumentObjectExpressionProvider implements IExpressionProvider {


    @Override
    public Set<Expression> getExpressions(final EObject context) {
        final Set<Expression> result = new HashSet<Expression>() ;
        Pool process = null;
        final EObject parent = ModelHelper.getParentProcess(context);
        if (parent instanceof Pool) {
            process = (Pool) parent;
        }
        if(context != null && process != null){
            for(final Document d : process.getDocuments()){
                result.add(createExpression(d));
            }
        }
        return result;
    }

    @Override
    public String getExpressionType() {
        return ExpressionConstants.DOCUMENT_TYPE;
    }

    @Override
    public Image getIcon(final Expression expression) {
        return getTypeIcon();
    }

    @Override
    public String getProposalLabel(final Expression expression) {
        return expression.getName();
    }

    private Expression createExpression(final Document d) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression() ;
        exp.setType(getExpressionType()) ;
        exp.setContent(d.getName()) ;
        exp.setName(d.getName()) ;
        if (d.isMultiple()) {
            exp.setReturnType(List.class.getName());
        } else {
            exp.setReturnType(org.bonitasoft.engine.bpm.document.Document.class.getName());
        }
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(d)) ;
        return exp;
    }

    @Override
    public boolean isRelevantFor(final EObject context) {
        return true;
    }

    @Override
    public Image getTypeIcon() {
        return Pics.getImage(PicsConstants.attachmentData);
    }

    @Override
    public String getTypeLabel() {
        return org.bonitasoft.studio.document.i18n.Messages.documentType;
    }

    @Override
    public IExpressionEditor getExpressionEditor(final Expression expression,final EObject context) {
        return null;
    }



}
