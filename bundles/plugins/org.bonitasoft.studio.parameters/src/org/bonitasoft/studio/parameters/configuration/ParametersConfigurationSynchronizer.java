/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
    public void synchronize(final Configuration configuration, final AbstractProcess process, final CompoundCommand cc, final EditingDomain editingDomain) {
        addNewParameters(configuration,process,editingDomain,cc);
        removeDeletedParameters(configuration,process,editingDomain,cc) ;
    }

    private void removeDeletedParameters(final Configuration configuration, final AbstractProcess process, final EditingDomain editingDomain, final CompoundCommand cc) {
        for(final Parameter param : configuration.getParameters()){
            final String paramName = param.getName() ;
            boolean exists = false ;
            for(final Parameter p : process.getParameters()){
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



    private void addNewParameters(final Configuration configuration, final AbstractProcess process, final EditingDomain editingDomain, final CompoundCommand cc) {
        for(final Parameter parameter : process.getParameters()){
            final String parameterName = parameter.getName() ;
            boolean exists = false ;
            for(final Parameter p : configuration.getParameters()){
                if(p.getName().equals(parameterName)){
                    exists = true ;
                    if(p.getTypeClassname() == null || !p.getTypeClassname().equals(parameter.getTypeClassname())){
                        cc.append(SetCommand.create(editingDomain, p, ParameterPackage.Literals.PARAMETER__TYPE_CLASSNAME, parameter.getTypeClassname())) ;
                    }
                    break ;
                }
            }
            if(!exists){
                final Parameter param = ParameterFactory.eINSTANCE.createParameter() ;
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
