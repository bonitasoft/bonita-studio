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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.groovy.GroovyDocumentUtil;
import org.bonitasoft.studio.groovy.Messages;
import org.bonitasoft.studio.groovy.repository.GroovyFileStore;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import static org.bonitasoft.studio.common.Messages.bonitaName;

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

    private synchronized static void createFunctionCatgories(){
        try {
            IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
            IPackageFragmentRoot[] packages = project.getAllPackageFragmentRoots();
            IPackageFragmentRoot groovyAll = null ;

            for(IPackageFragmentRoot f : packages){
                if(f.toString().indexOf("org.codehaus.groovy_") != -1 && f.isOpen()){
                    String searchString =  f.toString().substring(f.toString().indexOf("org.codehaus.groovy_")) ;
                    if(searchString.contains("lib"+File.separatorChar+"groovy-all-") && searchString.contains(".jar")){ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        groovyAll = f;
                    }
                }
                if(groovyAll != null) {
                    break ;
                }
            }

            IPackageFragment pack = groovyAll.getPackageFragment("org.codehaus.groovy.runtime"); //$NON-NLS-1$
            pack.open(null);

            IType defaultType = pack.getClassFile("DefaultGroovyMethods.class").getType(); //$NON-NLS-1$

            if(defaultType != null){
                for(IMethod m :defaultType.getMethods()){
                    if(m.getFlags() == (Flags.AccStatic | Flags.AccPublic) ) {
                        new GroovyFunction(m,null);
                    }
                }
            }

            FunctionsRepositoryFactory.getBonitaFunctionCatgory().removeAllFunctions();
            final ProvidedGroovyRepositoryStore store = (ProvidedGroovyRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(ProvidedGroovyRepositoryStore.class) ;
            IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
            for(IRepositoryFileStore artifact : store.getChildren()){
                GroovyFileStore file = (GroovyFileStore) artifact ;
                IType type = javaProject.findType(file.getDisplayName()) ;
                if(type != null){
                    for(IMethod m :type.getMethods()){
                        if(m.getFlags() == (Flags.AccStatic | Flags.AccPublic) ){
                            FunctionsRepositoryFactory.getBonitaFunctionCatgory().addFunction(new GroovyFunction(m,FunctionsRepositoryFactory.getBonitaFunctionCatgory()));
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

        GroovyDocumentUtil.refreshUserLibrary();

    }

    public synchronized static IFunctionsCategories getFunctionCatgories(){
        if(categories == null){
            createFunctionCatgories();
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

    public synchronized static List<String> getFunctionsNames(){
        if(funcName == null){
            funcName = new ArrayList<String>();
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
