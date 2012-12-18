/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.widgets;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.exporter.ExporterTools;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ViewPageFlow;
import org.codehaus.groovy.ast.FieldNode;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;

/**
 * 
 * Very specific text or data: must be put on a page flow, add next buttons ids
 * of next buttons of each form
 * 
 * @author Baptiste Mesta
 * 
 */
public class PageFlowTransitionTextOrData extends TextOrDataForCellEditor {

    private final EStructuralFeature feature;

    /**
     * @param parent
     * @param modelElement
     * @param focusListener
     * @param isPassword
     * @param usePassword
     */
    public PageFlowTransitionTextOrData(Composite parent, Element modelElement, FocusListener focusListener, boolean isPassword, boolean usePassword,
            EStructuralFeature feature) {
        super(parent, modelElement, focusListener, isPassword, usePassword);
        this.feature = feature;
        setShowGroovyPrefixSuffix(false);
        setExepectedIsGroovy(false);
        reset();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bonitasoft.studio.groovy.widgets.TextOrData#addAll(java.util.Collection
     * )
     */
    /*
     * (non-Javadoc)
     * 
     * @see org.bonitasoft.studio.groovy.widgets.TextOrData#populate()
     */
    @Override
    public Set<String> populate() {
        Set<String> result = new HashSet<String>() ;
        if (getElement() instanceof PageFlow || getElement() instanceof ViewPageFlow) {
            Element pageflow = getElement();
            if (feature != null) {

                List<?> list = (List<?>) pageflow.eGet(feature);
                for (Object form : list) {
                    TreeIterator<EObject> eAllContents = ((EObject) form).eAllContents();
                    while (eAllContents.hasNext()) {
                        EObject eObject = eAllContents.next();
                        if (eObject instanceof NextFormButton) {
                            String entry = ExporterTools.FIELD_IDENTIFIER + ((Element) eObject).getName();
                            combo.add(entry);
                            result.add(entry) ;
                        }
                    }
                }
            }
        }
        result.addAll(super.populate());
        return result ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bonitasoft.studio.groovy.widgets.TextOrData#addDatas(java.util.Collection
     * )
     */
    @Override
    protected Set<String> addDatas(Collection<EClass> eClasses2) {
        Set<String> result = super.addDatas(eClasses2);
        if (feature != null) {
            List<Data> datas = getPageFlowDatas();
            result.addAll(proceedData(eClasses2, datas));
        }
        return result ;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.bonitasoft.studio.groovy.widgets.TextOrData#computeAccessibleData()
     */
    @Override
    public List<FieldNode> computeAccessibleData() {
        List<FieldNode> listVariables = super.computeAccessibleData();
        // add variables from forms + add next buttons
        //		if (getElement() instanceof PageFlow || getElement() instanceof ViewPageFlow) {
        //			Element pageflow = getElement();
        //			if (feature != null) {
        //				List<Data> datas = getPageFlowDatas();
        //				for (Data d : datas) {
        //
        //					FieldNode field = GroovyUtil.createFiledNodeVariable(d);
        //					if (field != null) {
        //						listVariables.add(field);
        //					}
        //				}
        //				List<?> list = (List<?>) pageflow.eGet(feature);
        //				for (Object form : list) {
        //					TreeIterator<EObject> eAllContents = ((EObject) form).eAllContents();
        //					while (eAllContents.hasNext()) {
        //						EObject eObject = (EObject) eAllContents.next();
        //						if (eObject instanceof NextFormButton || eObject instanceof FormField) {
        //							FieldNode field = GroovyUtil.createFieldNodeVariable((Widget) eObject);
        //							listVariables.add(field);
        //						}
        //					}
        //				}
        //			}
        //		}

        return listVariables;
    }

    /**
     * @return
     */
    public List<Data> getPageFlowDatas() {
        EReference trFeature;
        if (feature.equals(ProcessPackage.Literals.PAGE_FLOW__FORM)) {
            trFeature = ProcessPackage.Literals.PAGE_FLOW__TRANSIENT_DATA;
        } else if (feature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM)) {
            trFeature = ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_TRANSIENT_DATA;
        } else {
            trFeature = ProcessPackage.Literals.RECAP_FLOW__RECAP_TRANSIENT_DATA;
        }
        List<Data> datas = (List<Data>) getElement().eGet(trFeature);
        return datas;
    }

}
