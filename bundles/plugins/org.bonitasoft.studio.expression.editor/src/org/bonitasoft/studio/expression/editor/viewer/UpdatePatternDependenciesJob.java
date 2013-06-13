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
package org.bonitasoft.studio.expression.editor.viewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;

/**
 * @author Romain Bioteau
 *
 */
public class UpdatePatternDependenciesJob extends Job {

    private final Map<String, List<EObject>> cache;
    private IDocument document;
    private final Expression patternExpression;

    public UpdatePatternDependenciesJob(IDocument document,Expression patternExpression){
        super(UpdatePatternDependenciesJob.class.getName()) ;
        cache = new HashMap<String, List<EObject>>() ;
        this.document = document;
        this.patternExpression = patternExpression ;
    }



    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected IStatus run(IProgressMonitor monitor) {
        String expression = document.get() ;
        if(cache.get(expression) == null){
            final List<EObject> dependencies = patternExpression.getReferencedElements() ;
            final FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(document) ;
            IRegion index = null ;
            try{
                index = finder.find(0, "\\{[0-9]+}", true, true, false,true);
            }catch (Exception e) {

            }
            List<Integer> foundIndexes = new ArrayList<Integer>();
            while(index != null){
                String r;
                try {
                    r = document.get(index.getOffset(),index.getLength());
                    if(r.length() > 2){
                        String content = r.substring(1, r.length()-1);
                        try{
                            int i = Integer.parseInt(content);
                            foundIndexes.add(i);
                        }catch (NumberFormatException e) {
                            // TODO: handle exception
                        }
                    }
                    index = finder.find(index.getOffset()+index.getLength(), "\\{[0-9]+}", true, true, false,true);
                } catch (BadLocationException e) {

                }

            }
            for(Integer patternIndex : foundIndexes){
                if(dependencies.size() <= patternIndex || dependencies.get(patternIndex) == null){
                    Data d  = ProcessFactory.eINSTANCE.createData() ;
                    d.setName("< value not set >");
                    dependencies.add(patternIndex,d);
                }
            }
            List<EObject> deps = new ArrayList<EObject>() ;
            //            for(String name : foundVariable){
            //                for(Data d : ModelHelper.getAccessibleData(context, true)){
            //                    if(d.getName().equals(name)){
            //                        deps.add(EcoreUtil.copy(d)) ;
            //                    }
            //                }
            //                if(context instanceof Widget){
            //                    if(name.startsWith("field_") && ((Widget) context).getName().equals(name.substring("field_".length()))){
            //                        deps.add(EcoreUtil.copy(context)) ;
            //                    }
            //                }
            //
            //                if(context instanceof Form){
            //                    for (Form f : ((PageFlow) context.eContainer()).getForm()){
            //                        for (Widget w : f.getWidgets()) {
            //                            if (w instanceof FormField && name.startsWith("field_") && w.getName().equals(name.substring("field_".length()))){
            //                                deps.add(EcoreUtil.copy(w)) ;
            //                            }
            //                        }
            //                    }
            //                }
            //
            //                if(context instanceof Connector){
            //                    IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.CONNECTOR_OUTPUT_TYPE) ;
            //                    for(Expression e : provider.getExpressions(context)){
            //                        if(e.getName().equals(name)){
            //                            deps.add(EcoreUtil.copy(e.getReferencedElements().get(0))) ;
            //                        }
            //                    }
            //                }
            //
            //                if(context instanceof SimulationDataContainer){
            //                    IExpressionProvider provider = ExpressionEditorService.getInstance().getExpressionProvider(ExpressionConstants.SIMULATION_VARIABLE_TYPE) ;
            //                    for(Expression e : provider.getExpressions(context)){
            //                        if(e.getName().equals(name)){
            //                            deps.add(EcoreUtil.copy(e.getReferencedElements().get(0))) ;
            //                        }
            //                    }
            //                }

            //            }
            cache.put(expression, deps) ;
        }
        return Status.OK_STATUS;
    }



    public List<EObject> getDependencies(String expression){
        return cache.get(expression) ;
    }

    public IDocument getDocument() {
        return document;
    }


    public void setDocument(IDocument document) {
        this.document = document;
    }
}
