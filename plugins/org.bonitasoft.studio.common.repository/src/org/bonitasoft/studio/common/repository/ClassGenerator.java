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
package org.bonitasoft.studio.common.repository;

import java.io.ByteArrayInputStream;
import java.util.Hashtable;
import java.util.Map;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.filter.AbstractUserFilter;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.internal.corext.fix.CleanUpConstants;
import org.eclipse.jdt.internal.corext.refactoring.RefactoringExecutionStarter;
import org.eclipse.jdt.internal.ui.fix.CodeFormatCleanUp;
import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUp;
import org.eclipse.jdt.ui.wizards.NewClassWizardPage;
import org.eclipse.swt.widgets.Display;


/**
 * @author Romain Bioteau
 *
 */
public class ClassGenerator {

    public static IFile generateConnectorImplementationAbstractClass(ConnectorImplementation implementation,ConnectorDefinition definition,String superClassName,SourceRepositoryStore sourceStore,IProgressMonitor monitor) throws Exception{
        String className = implementation.getImplementationClassname() ;
        String packageName = "" ;
        String simpleClassName = className ;
        if(className != null && className.indexOf(".") != -1){
            packageName = className.substring(0,className.lastIndexOf(".")) ;
            simpleClassName = className.substring(className.lastIndexOf(".")+1,className.length()) ;
        }
        String abstractClassName =  "Abstract" + simpleClassName ;
        String qualifiedAbstractClassName =  packageName + "." + abstractClassName ;


        final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
        IType classType = javaProject.findType(qualifiedAbstractClassName) ;
        if(classType != null){
            classType.getCompilationUnit().delete(true, monitor) ;
        }
        classType = generateAbstractClass(packageName,abstractClassName,superClassName,sourceStore, monitor);

        createAbstractClassContent(classType, definition,className, monitor);

        Map<String, String> settings= new Hashtable<String, String>();
        settings.put(CleanUpConstants.FORMAT_SOURCE_CODE, CleanUpOptions.TRUE);
        RefactoringExecutionStarter.startCleanupRefactoring(new ICompilationUnit[]{classType.getCompilationUnit()},  new ICleanUp[] {new CodeFormatCleanUp(settings)}, false, Display.getDefault().getActiveShell(), false,"");


        return (IFile) classType.getResource();
    }

    public static void updateConnectorImplementationAbstractClassName(ConnectorImplementation implementation ,String oldSuperClassName, SourceFileStore fileStore,IProgressMonitor monitor) throws Exception{
        final String className = implementation.getImplementationClassname() ;
        String oldSimpleName = oldSuperClassName;
        if(oldSuperClassName.indexOf(".") != -1){
            oldSimpleName = oldSuperClassName.substring(oldSuperClassName.lastIndexOf(".")+1,oldSuperClassName.length());
        }
        final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
        final IType classType = javaProject.findType(className) ;
        String newSimpleName = getAbstractClassName(className);
        if(newSimpleName.indexOf(".") != -1){
            newSimpleName = newSimpleName.substring(newSimpleName.lastIndexOf(".")+1,newSimpleName.length());
        }
        final String newSource = classType.getCompilationUnit().getSource().replace(oldSimpleName,newSimpleName );
        fileStore.getResource().setContents(new ByteArrayInputStream(newSource.getBytes()), IResource.FORCE, monitor);
    }

    public static IFile generateConnectorImplementationClass(ConnectorImplementation implementation, ConnectorDefinition definition,SourceRepositoryStore sourceStore,IProgressMonitor monitor) throws Exception{
        String className = implementation.getImplementationClassname() ;

        final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
        IType classType = javaProject.findType(className) ;

        if(classType == null){
            String packageName = "" ;
            String simpleClassName = className ;
            if(className.indexOf(".") != -1){
                packageName = className.substring(0,className.lastIndexOf(".")) ;
                simpleClassName = className.substring(className.lastIndexOf(".")+1,className.length()) ;
            }
            classType = generateImplementationClass(packageName,simpleClassName,sourceStore, monitor);

            IType abstractConnectorType =  javaProject.findType(AbstractConnector.class.getName()) ;
            IType abstractFilterType =  javaProject.findType(AbstractUserFilter.class.getName()) ;
            ITypeHierarchy hierarchy = classType.newTypeHierarchy(javaProject, monitor) ;

            if(hierarchy.contains(abstractConnectorType)){
                StringBuilder executeMethodContent = new StringBuilder("@Override\nprotected void executeBusinessLogic() throws ConnectorException{\n\t") ;
               
                
                executeMethodContent.append("//Get access to the connector input parameters") ;
                generateGetterComment(definition,executeMethodContent);
                executeMethodContent.append("\n\n }\n");
                classType.createMethod(executeMethodContent.toString(), null,true,monitor) ;
                
             executeMethodContent = new StringBuilder("@Override\npublic void connect() throws ConnectorException{\n\t");
             executeMethodContent.append("//[Optional] Open a connection to remote server\n\n}\n");
                classType.createMethod(executeMethodContent.toString(),null, true, monitor);
                
                executeMethodContent = new StringBuilder("@Override\npublic void disconnect() throws ConnectorException{\n\t");
                executeMethodContent.append("//[Optional] Close connection to remote server\n\n}\n");
                classType.createMethod(executeMethodContent.toString(),null, true, monitor);
                
                classType.getCompilationUnit().createImport("org.bonitasoft.engine.exception.connector.ConnectorException", null, monitor);
            }
            if(hierarchy.contains(abstractFilterType)){
            	StringBuilder executeMethodContent = new StringBuilder("@Override\npublic void validateInputParameters() throws ConnectorValidationException {\n\t");
            	executeMethodContent.append("//TODO validate input parameters here \n\n}\n");
            	 classType.createMethod(executeMethodContent.toString(), null,true,monitor) ;
            	 
                executeMethodContent = new StringBuilder("@Override\npublic List<Long> filter(final String actorName) throws UserFilterException {\n\t") ;
                executeMethodContent.append("//TODO execute the user filter here\n\t//The method must return a list of actor ids\n\t");
                executeMethodContent.append("//you can use getApiAccessor() and getExecutionContext()");
                executeMethodContent.append("\n\treturn null;\n\n}\n") ;
                classType.createMethod(executeMethodContent.toString(), null,true,monitor) ;

                executeMethodContent = new StringBuilder("@Override\npublic boolean shouldAutoAssignTaskIfSingleResult() {\n\t") ;
                executeMethodContent.append("// If this method returns true, the step will be assigned to ");
                executeMethodContent.append("\n\t//the user if there is only one result returned by the filter method");
                executeMethodContent.append("\n\treturn super.shouldAutoAssignTaskIfSingleResult();\n\n}\n") ;
                classType.createMethod(executeMethodContent.toString(), null,true,monitor) ;
                ICompilationUnit compilationUnit = classType.getCompilationUnit();
                compilationUnit.createImport("java.util.List", null, monitor);
                compilationUnit.createImport("org.bonitasoft.engine.exception.connector.ConnectorValidationException", null, monitor);
                compilationUnit.createImport("org.bonitasoft.engine.exception.UserFilterException", null, monitor);
            }
        }

        return (IFile) classType.getResource();
    }

    private static IType generateAbstractClass(String packageName, String className, String superClassName,SourceRepositoryStore sourceStore, IProgressMonitor progressMonitor) throws Exception {

        final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;

        NewClassWizardPage classWizard = new NewClassWizardPage();
        classWizard.setSuperClass(superClassName, false);
        classWizard.setAddComments(true, false);
        classWizard.setModifiers(classWizard.F_PUBLIC | classWizard.F_ABSTRACT, false);
        classWizard.setTypeName(className, false);
        classWizard.setMethodStubSelection(false, false, false, false);
        IPackageFragmentRoot packageFragmentRoot = null;
        IResource srcFolder = sourceStore.getResource() ;
        packageFragmentRoot = javaProject.getPackageFragmentRoot(srcFolder);
        classWizard.setPackageFragmentRoot(packageFragmentRoot, false);
        IPackageFragment packageFragment = packageFragmentRoot.getPackageFragment(packageName == null ? "" : packageName);
        if (! packageFragment.exists()) {
            packageFragment = packageFragmentRoot.createPackageFragment(packageName, true, progressMonitor);
        }
        classWizard.setPackageFragment(packageFragment, false);
        classWizard.createType(progressMonitor);
        return classWizard.getCreatedType();
    }

    private static IType generateImplementationClass(String packageName, String className,SourceRepositoryStore sourceStore, IProgressMonitor progressMonitor) throws Exception {

        final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject() ;
        NewClassWizardPage classWizard = new NewClassWizardPage();
        String abstractClassName = "Abstract" + className ;
        if(packageName != null && !packageName.isEmpty()){
            abstractClassName = packageName +"." +abstractClassName;
        }

        classWizard.setSuperClass(abstractClassName, false);
//        classWizard.enableCommentControl(true);
        classWizard.setAddComments(true, false);
        classWizard.setModifiers(classWizard.F_PUBLIC, false);
        classWizard.setTypeName(className, false);
        classWizard.setMethodStubSelection(false, false, false, false);
        IPackageFragmentRoot packageFragmentRoot = null;
        IResource srcFolder = sourceStore.getResource() ;
        packageFragmentRoot = javaProject.getPackageFragmentRoot(srcFolder);
        classWizard.setPackageFragmentRoot(packageFragmentRoot, false);
        IPackageFragment packageFragment = packageFragmentRoot.getPackageFragment(packageName == null ? "" : packageName);
        if (! packageFragment.exists()) {
            packageFragment = packageFragmentRoot.createPackageFragment(packageName, true, progressMonitor);
        }
        classWizard.setPackageFragment(packageFragment, false);
        classWizard.createType(progressMonitor);
        return classWizard.getCreatedType();
    }

    private static void createAbstractClassContent(IType classType, ConnectorDefinition definition, String className, IProgressMonitor monitor) throws Exception {

        for(Input input : definition.getInput()) {
            String fieldName = NamingUtils.toJavaIdentifier(input.getName().toUpperCase(),true)+"_INPUT_PARAMETER";
            IField field = classType.getField(fieldName);

            /*check a field with this name not already exist*/
            if(field != null && field.exists()){
                field.delete(true, monitor) ;
            }

            String fieldDefinition = "protected final static String " + fieldName + " = \""+ input.getName() + "\" ;";
            classType.createField(fieldDefinition, null, true, null);

            final String getterName = "get"+NamingUtils.toJavaIdentifier(input.getName(),true) ;
            IMethod getterMethod = classType.getMethod(getterName,null);
            if(getterMethod != null && getterMethod.exists()){ //Regenerate method
                getterMethod.delete(true, monitor) ;
            }

            classType.createMethod(
                    "protected final " + input.getType() +" "+getterName + "() {\n" +
                            "\t return ("+input.getType()+") getInputParameter(" + fieldName +");\n"+
                            "}", null, true, null);

        }


        for (Output output : definition.getOutput()) {
            String fieldName = NamingUtils.toJavaIdentifier(output.getName().toUpperCase(),true)+"_OUTPUT_PARAMETER";
            IField field = classType.getField(fieldName);

            /*check a field with this name not already exist*/
            if(field != null && field.exists()){
                field.delete(true, monitor) ;
            }

            String fieldDefinition = "protected final String " + fieldName + " = \""+ output.getName() + "\" ;";
            classType.createField(fieldDefinition, null, true, null);

            final String setterName = "set"+NamingUtils.toJavaIdentifier(output.getName(),true) ;

            String inputName = NamingUtils.toJavaIdentifier(output.getName(), false);
            classType.createMethod(
                    "protected final void " +setterName + "("+output.getType()+" "+ inputName +") {\n" +
                            "\t setOutputParameter("+fieldName+" , "+ inputName +" );\n"+
                            "}\n", null, true, null);
        }

        IMethod validateMethod = classType.getMethod("validateInputParameters", null) ;
        if(validateMethod != null && validateMethod.exists()){
            validateMethod.delete(true, monitor) ;
        }


        StringBuilder validateMethodContent = new StringBuilder("@Override\npublic void validateInputParameters() throws org.bonitasoft.engine.exception.ConnectorValidationException{\n\t") ;

        for(Input input : definition.getInput()) {
            String getterName = "get"+NamingUtils.toJavaIdentifier(input.getName(),true);
            validateMethodContent.append("try{ \n\t"+
                    getterName+"();\n" +
                    " }catch (ClassCastException cce) {\n\t" +
                    " throw new org.bonitasoft.engine.exception.ConnectorValidationException(\""+input.getName()+" type is invalid\") ;\n\t"  +
                    "  }\n" );

        }

        validateMethodContent.append("\n\t}") ;
        classType.createMethod(validateMethodContent.toString(), null, true, null);
        
        

    }

    public static String getAbstractClassName(String className) {
        String packageName = "" ;
        String abstarctClassName = className ;
        if(className.indexOf(".") !=-1){
            packageName = className.substring(0, className.lastIndexOf(".")) ;
            abstarctClassName = className.substring( className.lastIndexOf(".") +1 ,  className.length()) ;
            abstarctClassName = "Abstract" + abstarctClassName ;
        }
        if(!packageName.isEmpty()){
            abstarctClassName = packageName + "."  + abstarctClassName ;
        }
        return abstarctClassName ;
    }
    
    private static void generateGetterComment(ConnectorDefinition definition,StringBuilder stringBuilder){
    	
        for(Input input : definition.getInput()) {
        	String getter = "\n\t//get"+NamingUtils.toJavaIdentifier(input.getName(),true)+"();" ;
        	stringBuilder.append(getter);
        }
        stringBuilder.append("\n\n\t//TODO execute your business logic here \n");
        stringBuilder.append("\n\t//Set the output of the connector execution");
        for (Output output : definition.getOutput()){
        	String setter = "\n\t//set"+NamingUtils.toJavaIdentifier(output.getName(), true)+"("+NamingUtils.toJavaIdentifier(output.getName(), false)+");";
        	stringBuilder.append(setter);
        	
        }
    }
    	
}
