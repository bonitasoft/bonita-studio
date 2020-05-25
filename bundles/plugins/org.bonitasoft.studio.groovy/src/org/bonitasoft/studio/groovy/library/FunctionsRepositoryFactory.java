/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

package org.bonitasoft.studio.groovy.library;

import static org.bonitasoft.studio.common.Messages.bonitaName;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.groovy.GroovyDocumentUtil;
import org.bonitasoft.studio.groovy.Messages;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;

/**
 * @author Romain Bioteau
 *
 */
public class FunctionsRepositoryFactory {


    private static FunctionsCategories categories;
    private static FunctionCategory userCat;
    private static FunctionCategory stringCat;
    private static FunctionCategory mathCat;
    private static FunctionCategory collectionCat;
    private static FunctionCategory otherCat;
    private static FunctionCategory bonitaCat;
    private static ArrayList<String> funcName;

    private static synchronized void createFunctionCatgories(Repository repository) {
        try {
            IJavaProject javaProject = repository.getJavaProject();
            IType defaultType = javaProject.findType("org.codehaus.groovy.runtime.DefaultGroovyMethods"); //$NON-NLS-1$

            if(defaultType != null){
                for(IMethod m :defaultType.getMethods()){
                    if(m.getFlags() == (Flags.AccStatic | Flags.AccPublic) ) {
                        new GroovyFunction(m,null);
                    }
                }
            }

            getBonitaFunctionCatgory().removeAllFunctions();
            final ProvidedGroovyRepositoryStore store = repository.getRepositoryStore(ProvidedGroovyRepositoryStore.class);
            for(IRepositoryFileStore artifact : store.getChildren()){
                GroovyFileStore file = (GroovyFileStore) artifact ;
                IType type = javaProject.findType(file.getDisplayName()) ;
                if(type != null){
                    for(IMethod m :type.getMethods()){
                        if(m.getFlags() == (Flags.AccStatic | Flags.AccPublic) ){
                            getBonitaFunctionCatgory().addFunction(
                                    new GroovyFunction(m, FunctionsRepositoryFactory.getBonitaFunctionCatgory()));
                        }
                    }
                }
            }

        } catch (Exception e) {
            BonitaStudioLog.error(e);
        }

        categories = new FunctionsCategories();
        categories.addCategory(getUserFunctionCatgory());
        categories.addCategory(getBonitaFunctionCatgory());
        categories.addCategory(getCollectionFunctionCatgory());
        categories.addCategory(getNumberFunctionCatgory());
        categories.addCategory(getStringFunctionCatgory());
        categories.addCategory(getOtherFunctionCatgory());

        GroovyDocumentUtil.refreshUserLibrary(repository);

    }

    public static synchronized IFunctionsCategories getFunctionCatgories(Repository repository) {
        if(categories == null){
            createFunctionCatgories(repository);
        }
        return categories ;
    }

    public static IFunctionCategory getUserFunctionCatgory(){
        if(userCat == null) {
            userCat = new FunctionCategory(Messages.userDefinedCatLabel);
        }

        return userCat;
    }

    public static IFunctionCategory getBonitaFunctionCatgory(){
        if(bonitaCat == null) {
            bonitaCat = new FunctionCategory(bonitaName);
        }

        return bonitaCat;
    }

    public static IFunctionCategory getCollectionFunctionCatgory(){
        if(collectionCat == null) {
            collectionCat = new FunctionCategory(Messages.collectionCatLabel);
        }
        return collectionCat ;

    }

    public static IFunctionCategory getStringFunctionCatgory(){
        if(stringCat == null) {
            stringCat  = new FunctionCategory(Messages.stringCatLabel);
        }

        return stringCat;
    }

    public static IFunctionCategory getNumberFunctionCatgory(){
        if(mathCat == null) {
            mathCat = new FunctionCategory(Messages.numberCatLabel);
        }
        return mathCat ;
    }

    public static IFunctionCategory getOtherFunctionCatgory(){
        if(otherCat == null) {
            otherCat = new FunctionCategory(Messages.otherCatLabel);
        }
        return otherCat;
    }

    public static void addUserFunction(IFunction f){
        if(userCat != null){
            userCat.addFunction(f);
        }
    }

    public static synchronized List<String> getFunctionsNames() {
        if(funcName == null){
            funcName = new ArrayList<>();
            List<IFunction> func = FunctionsRepositoryFactory.getStringFunctionCatgory().getFunctions();
            func.addAll( FunctionsRepositoryFactory.getCollectionFunctionCatgory().getFunctions());
            func.addAll( FunctionsRepositoryFactory.getNumberFunctionCatgory().getFunctions());
            func.addAll( FunctionsRepositoryFactory.getOtherFunctionCatgory().getFunctions());

            for(IFunction f : func){
                funcName.add(f.getName());
            }
        }
        return funcName ;
    }

    public static void reset(){
        userCat = null ;
        stringCat = null ;
        collectionCat = null ;
        mathCat = null ;
        otherCat = null ;
        funcName = null ;
        bonitaCat = null ;
        categories = null ;
    }

}
