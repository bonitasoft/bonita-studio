/*******************************************************************************
 * Copyright (C) 2009, 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.parameters.configuration;

import org.bonitasoft.studio.configuration.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.parameter.ParameterFactory;
import org.bonitasoft.studio.model.parameter.ParameterPackage;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * @author Romain Bioteau
 *
 */
public class ParametersConfigurationSynchronizer implements IConfigurationSynchronizer {


    @Override
    public void synchronize(Configuration configuration, AbstractProcess process, CompoundCommand cc, EditingDomain editingDomain) {
        addNewParameters(configuration,process,editingDomain,cc);
        removeDeletedParameters(configuration,process,editingDomain,cc) ;
    }

    private void removeDeletedParameters(Configuration configuration, AbstractProcess process, EditingDomain editingDomain, CompoundCommand cc) {
        for(Parameter param : configuration.getParameters()){
            String paramName = param.getName() ;
            boolean exists = false ;
            for(Parameter p : process.getParameters()){
                if(p.getName().equals(paramName)){
                    exists = true ;
                    break ;
                }
            }
            if(!exists){
                cc.append(RemoveCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__PARAMETERS, param)) ;
            }
        }
    }



    private void addNewParameters(Configuration configuration, AbstractProcess process, EditingDomain editingDomain, CompoundCommand cc) {
        for(Parameter parameter : process.getParameters()){
            String parameterName = parameter.getName() ;
            boolean exists = false ;
            for(Parameter p : configuration.getParameters()){
                if(p.getName().equals(parameterName)){
                    exists = true ;
                    if(p.getTypeClassname() == null || !p.getTypeClassname().equals(parameter.getTypeClassname())){
                        cc.append(SetCommand.create(editingDomain, p, ParameterPackage.Literals.PARAMETER__TYPE_CLASSNAME, parameter.getTypeClassname())) ;
                    }
                    break ;
                }
            }
            if(!exists){
                Parameter param = ParameterFactory.eINSTANCE.createParameter() ;
                param.setName(parameterName) ;
                param.setTypeClassname(parameter.getTypeClassname()) ;
                param.setDescription(parameter.getDescription()) ;
                cc.append(AddCommand.create(editingDomain, configuration, ConfigurationPackage.Literals.CONFIGURATION__PARAMETERS, param)) ;
            }
        }
    }


    @Override
    public String getFragmentContainerId() {
        return null;
    }

    @Override
    public EStructuralFeature getDependencyKind() {
        return null;
    }


}
