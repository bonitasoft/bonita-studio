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

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.groovy.GroovyDocumentUtil;
import org.bonitasoft.studio.groovy.Messages;

/**
 * @author Romain Bioteau
 *
 */
public class FunctionsRepositoryFactory {


    private static FunctionsCategories categories;
    private static FunctionCategory userCat;

    private static synchronized void createFunctionCatgories(AbstractRepository repository) {
        categories = new FunctionsCategories();
        categories.addCategory(getUserFunctionCatgory());

        GroovyDocumentUtil.refreshUserLibrary(repository);

    }

    public static synchronized IFunctionsCategories getFunctionCatgories(AbstractRepository repository) {
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

    public static void addUserFunction(IFunction f){
        if(userCat != null){
            userCat.addFunction(f);
        }
    }


    public static void reset(){
        userCat = null ;
        categories = null ;
    }

}
