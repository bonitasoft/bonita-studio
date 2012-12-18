///**
// * Copyright (C) 2010 BonitaSoft S.A.
// * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 2.0 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.bonitasoft.studio.simulation.properties.contributions;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.bonitasoft.studio.common.emf.tools.ModelHelper;
//import org.bonitasoft.studio.groovy.GroovyUtil;
//import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
//import org.bonitasoft.studio.model.process.AbstractProcess;
//import org.bonitasoft.studio.model.process.Element;
//import org.bonitasoft.studio.model.simulation.SimulationData;
//import org.bonitasoft.studio.model.simulation.SimulationDataContainer;
//import org.codehaus.groovy.ast.FieldNode;
//import org.eclipse.swt.widgets.Composite;
//
///**
// * @author Baptiste Mesta
// *
// */
//public class TextOrSimulationData extends TextOrData {
//
//    /**
//     * @param parent
//     * @param element
//     */
//    public TextOrSimulationData(Composite parent, Element element) {
//        super(parent, element);
//    }
//
//    // TODO add data in groovy editor
//    /*
//     * (non-Javadoc)
//     *
//     * @see org.bonitasoft.studio.groovy.widgets.TextOrData#addDatas()
//     */
//    @Override
//    protected Set<String> addDatas() {
//        Set<String> result = new HashSet<String>() ;
//        if (!(getElement() instanceof AbstractProcess)) {
//            if (getElement() instanceof SimulationDataContainer) {
//                for (SimulationData data : ((SimulationDataContainer) getElement()).getSimulationData()) {
//                    String entry = GroovyUtil.GROOVY_PREFIX + data.getName() + GroovyUtil.GROOVY_SUFFIX;
//                    combo.add(entry);
//                    result.add(entry) ;
//                }
//            }
//        }
//        for (SimulationData data : ModelHelper.getParentProcess(getElement()).getSimulationData()) {
//            String entry = GroovyUtil.GROOVY_PREFIX + data.getName() + GroovyUtil.GROOVY_SUFFIX;
//            combo.add(entry);
//            result.add(entry) ;
//        }
//        return result ;
//    }
//
//    /* (non-Javadoc)
//     * @see org.bonitasoft.studio.groovy.widgets.TextOrData#computeAccessibleData()
//     */
//    @Override
//    public List<FieldNode> computeAccessibleData() {
//        //		List<FieldNode> listVariables;
//        //		listVariables = GroovyUtil.createVariablesFromSimulationElement(eObject);
//        return null;
//    }
//
//}
