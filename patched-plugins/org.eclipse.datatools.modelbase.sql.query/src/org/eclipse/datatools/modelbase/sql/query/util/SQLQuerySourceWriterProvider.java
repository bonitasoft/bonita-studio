/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;


/**
 * Singleton <code>SQLQuerySourceWriterProvider</code> holds a registry of
 * <code>SQLQuerySourceWriter</code> extensions and creates instances of
 * <code>SQLQuerySourceWriter</code>s, that are registered for a specific
 * <code>SQLQueryObject</code> <code>Package</code>.
 * As plugin in an eclipse runtime environment, the registration of
 * <code>SQLQuerySourceWriter</code> for a package of
 * <code>SQLQueryObject</code> extensions is done at start time of the plugin
 * containing the SQL Query model extension.
 * The registry is implemented following the lazy loading
 * startegy and the plugin extending the SQL Query model plugin is only loaded
 * the first time a <code>SQLQueryObject</code> is
 * instanciated in the extending plugin.
 * 
 * 
 * @author ckadner
 *
 */
public class SQLQuerySourceWriterProvider
{
    /** TODO: doc! 
     * maps the SQLSourceWriter (value) to the fully qualified name of a 
     * <code>Package</code> of <code>SQLQueryObjects</code> */
    private Map registeredSourceWriterPackageMap = null;

    private static SQLQuerySourceWriterProvider instance = null;
    
    /**
     * Private constructor enforcing singleton construction pattern.
     */
    private SQLQuerySourceWriterProvider()
    {
        registeredSourceWriterPackageMap = new HashMap();
    }

    /**
     * @return the singleton <code>SQLQuerySourceWriterProvider</code> instance
     */
    public static SQLQuerySourceWriterProvider getInstance()
    {
        if (instance == null)
        {
            instance = new SQLQuerySourceWriterProvider();
        }
        return instance;
    }
    
    
    /* ************ registration methods for SourceWriter extensions ******** */
    
    
    /**
     * Method to register a subclass of the <code>SQLQuerySourceWriter</code>
     * for a specific package of <code>SQLQueryObject</code>s, that the given
     * <code>sourceWriterClass</code> generates the SQL source for. <b>Note:
     * </b> there can only be one <code>SQLQuerySourceWriter</code> for one
     * package of <code>SQLQueryObject</code>s, however one
     * <code>SQLQuerySourceWriter</code> can generate the SQL source for
     * multiple packages of <code>SQLQueryObject</code>s. In general an
     * extension of a <code>SQLQuerySourceWriter</code> goes along with an
     * extension to the <code>SQLQueryObject</code> model. <b>Developer note:
     * </b> if the name and packaging of the <code>SQLQuerySourceWriter</code>
     * follows the naming convention described in the class documentaion
     * section, the <code>SQLQuerySourceWriter</code> will be registered
     * automatically via <code>@link java.lang.reflect.*</code>
     * 
     * @param sourceWriterClass
     *            the <code>SQLQuerySourceWriter</code> class that generates
     *            the SQL source for the <code>SQLQueryObject</code> s in the
     *            given <code>sqlModelObjectsPackage</code>
     * @param sqlModelObjectsPackageName
     *            fully qualified package name of <code>SQLQueryObject</code>s
     */
    public void registerSourceWriter( Class sourceWriterClass,
                                      String sqlModelObjectsPackageName)
    {
        
        // FIXME: with that one-SW-for-one-SQLQueryObject-package approach we
        //  inhibit overwriting of methods in SW extensions, because we have
        //  the base SW registered forever and all the package of SQLQueryObjects
        //  and an extension that overwrites a few methods for some of these
        //  SQLQueryObects will have no effect
        // we should register SWs for single Objects and respect method overwritings in extending SWs
        
        //StatementHelper.logError("register given sourceWriter = "+sourceWriterClass.getName() + " in package = "+sqlModelObjectsPackage.getName());
        
        if (sourceWriterClass != null && sqlModelObjectsPackageName != null) 
        {
            if (registeredSourceWriterPackageMap
                            .containsKey(sqlModelObjectsPackageName))
            {
                // check if the newly found SourceWriter is a subclass of a
                // previously found one, if so overwrite the previous mapping
                Class previousSW = (Class)
                    registeredSourceWriterPackageMap.get(sqlModelObjectsPackageName);
                
                // if the new SW to register is the same as previously registered
                // SW; or the new SW is a super class of the previously registered
                // SW it will not replace the previously registred SW because it
                // totally assignment compatible (all methods are contained in
                // previously registered SW)
                // if the new SW to register is neither extending the previously
                // registered SW nor it is a super class of it, we have a
                // conflict between two distinct SW and we here decide to keep
                // the previous one, because there is no guaranty that all the
                // SQLQueryObjects covered by the previous are also covered by
                // the new SW 
                if (sourceWriterClass == previousSW
                                || sourceWriterClass.isAssignableFrom(previousSW))
                {
                    return;
                }
                else if (!previousSW.isAssignableFrom(sourceWriterClass))
                {
                    StatementHelper.logError("\n"+sourceWriterClass.getName()+
                                    " can not displace "+
                                    previousSW.getName()+
                                    " for package "+
                                    sqlModelObjectsPackageName +
                                    " because it is not an extending "+
                                    previousSW.getName());
                    return;
                }
            }
            
            // no previously registered SW or the previously registered is a super class
            // of the new SW and is therefor covered completely by the new SW, so the
            // previous registration will be displaced
            registeredSourceWriterPackageMap.put(sqlModelObjectsPackageName, sourceWriterClass);
			// register the interface level of the impl package as well, as we have some
			// SQLObjects with its implementation in the interface package and not in impl
            String packageName = sqlModelObjectsPackageName;
            if (packageName.endsWith(".impl")) 
            {
                int interfaceLevelPackageNameLength =
                    packageName.lastIndexOf(".impl");
                String interfaceLevelPackageName = 
                    packageName.substring(0, interfaceLevelPackageNameLength);
                
                // we will only get the package if the classloader has it visible
                // in eclipse runtime environment that might not be the case
                if (interfaceLevelPackageName != null)
                {
                    registeredSourceWriterPackageMap.put(interfaceLevelPackageName, sourceWriterClass);
                }
            }
            else 
            {
                String implPackageName = packageName + ".impl";
                //Package implPackage = Package.getPackage(implPackageName);
                
                // we will only get the package if the classloader has it visible
                // in eclipse runtime environment that might not be the case
                if (implPackageName != null)
                {
                    registeredSourceWriterPackageMap.put(implPackageName, sourceWriterClass);
                }
            }
        }
        
    }
    
    
    // TODO: consider that to return a list of SourceWriters that implement a
    //       method taking the queryObject
    Class getQuerySourceWriterClass(Class queryObjectClass) 
    {
        Class sourceWriterClass = null;
        String queryObjectsPackageName = queryObjectClass.getPackage().getName();
        Map registeredSourceWriters = registeredSourceWriterPackageMap;
        
        if (registeredSourceWriters.containsKey(queryObjectsPackageName))
        {
            // running as eclipse plugin, registration is done at plugin startup
            // by SQLQueryModelPlugin.java
            sourceWriterClass = 
                (Class) registeredSourceWriters.get(queryObjectsPackageName);
        }
        else
        {
            // running in headless mode (outside eclipse runtime)
            // classloader must have access to external/extending SourceWriters
            // naming convention for SourceWriters is to be obeyed
	        
	        List potentialSourceWriterNames = getPotentialQuerySourceWriterNames(queryObjectClass);
	        List sourceWriterClasses = getClassesForNames(potentialSourceWriterNames);
	        
	/*
	        System.out.println("\n\n * * * * potential SourceWriter * * * *\n");
	        for (Iterator it = potentialSourceWriterNames.iterator(); it.hasNext();)
	        {
	            String potSourceWriterName = (String) it.next();
	            System.out.println(potSourceWriterName);
	        }
	        System.out.println("\n * * * * potential SourceWriter * * * *\n\n");
	        
	
	        System.out.println("\n\n * * * * SourceWriter found * * * *\n");
	        for (Iterator it = sourceWriterClasses.iterator(); it.hasNext();)
	        {
	            Class sourceWriterClass = (Class) it.next();
	            System.out.println(sourceWriterClass.getName());
	        }
	        System.out.println("\n * * * * SourceWriter found * * * *\n\n");
	*/      
	        
	        try
            {
	            queryObjectClass = Class.forName(SQLQuerySourceWriter.getInterfaceName(queryObjectClass));
            }
            catch (Exception e)
            {
                // well then we hope we got an interface level class or we will
                // not get the sourceWriter due to classloader visibility issues
            }
	        
	        for (Iterator swIt = sourceWriterClasses.iterator(); swIt.hasNext();)
	        {
	            Class swClass = (Class) swIt.next();
	            try
	            {
	                // check if we find the appendSQL method in the SourceWriter
	                Method appendSQLMethod =
	                    SQLQuerySourceWriter.getSpecificAppendSQLMethod(swClass, queryObjectClass);
	               
	                
	                //TODO: consider more than one matching SourceWriter!
	                
	                sourceWriterClass = swClass;
	                break;
	                
	                
	            }
	            catch (NoSuchMethodException e)  {}
	            
	        }
        }
        
        return sourceWriterClass;
    }    
    
    private List getClassesForNames (List classNames) {
        List classes = new ArrayList();
        if (classNames != null) {
            for (Iterator nameIt = classNames.iterator(); nameIt.hasNext();)
            {
                String className = (String) nameIt.next();
                try
                {
                    Class swClass = Class.forName(className);
                    classes.add(swClass);
                    //StatementHelper.logError(className+" found!");
                }
                catch (ClassNotFoundException e)
                {
                    //StatementHelper.logError(className+" not found! ClassNotFoundException "+ e.getMessage());
                }
                catch (NoClassDefFoundError e)
                {
                    //StatementHelper.logError(className+" not found! NoClassDefFoundError "+ e.getMessage());
                }
                catch (Exception e) {
                    //StatementHelper.logError(e.getMessage());
                }
            }
        }
        return classes;
    }
    
    //ck, sorry for no doc time, please debug to see what's haapening here!
    List getPotentialQuerySourceWriterNames(Class queryObjectClass) {
        List potentialSourceWriterNames = new ArrayList();

        if (queryObjectClass == null) {
            return null;
        }
        
        String packageName = queryObjectClass.getPackage().getName();
        
        int sqlQueryPackageBeginIndex = packageName.indexOf(".sql.")+1;
        int sqlQueryPackageEndIndex = packageName.length();
        
        if (packageName.endsWith(".impl")) {
            sqlQueryPackageEndIndex -= 5; 
        }
        String basePackage = packageName.substring(0, sqlQueryPackageBeginIndex-1);
        String stripToSQLQueryPackage = packageName.substring(sqlQueryPackageBeginIndex, sqlQueryPackageEndIndex);
        
        String[] subpackages = stripToSQLQueryPackage.split("\\.");
        
        List potentialSourceWriterPackageAndPrefixes = new ArrayList();
        
        String currentPackage = basePackage + ".";
        
        for (int i = 0; i < subpackages.length; i++)
        {
            String subpackage = subpackages[i];
            String firstUpperCase = subpackage.substring(0, 1).toUpperCase();
            String restLowerCase = subpackage.substring(1);
            String partName = firstUpperCase + restLowerCase;
            String partNameUC = subpackage.toUpperCase();
            
            currentPackage += subpackage + ".";
            
            List additionalSourceWriterPackageAndPrefixes = new ArrayList();
            
            for (Iterator it = potentialSourceWriterPackageAndPrefixes.iterator(); it
                            .hasNext();)
            {
                String potential = (String) it.next();
                int potentialSubpackageEndIndex = potential.lastIndexOf(".")+1;
                String newPotentialSubpackage = currentPackage;
                String partPrefix = potential.substring(potentialSubpackageEndIndex);
                String newPart = newPotentialSubpackage+partPrefix+partName;
                String newPartUC = newPotentialSubpackage+partPrefix+partName.toUpperCase();
                additionalSourceWriterPackageAndPrefixes.add(newPart);
                if (partName.length() <= 3) {
                    additionalSourceWriterPackageAndPrefixes.add(newPartUC);
                }
            }
            potentialSourceWriterPackageAndPrefixes.addAll(additionalSourceWriterPackageAndPrefixes);
            
            potentialSourceWriterPackageAndPrefixes.add(currentPackage+partName);
            if (partName.length() <= 3) {
                potentialSourceWriterPackageAndPrefixes.add(currentPackage+partNameUC);
            }
        }
        
        for (Iterator it = potentialSourceWriterPackageAndPrefixes.iterator(); it.hasNext();)
        {
            String potPrefix = (String) it.next();
            int lastDot = potPrefix.lastIndexOf('.');
            String sourceWriterPackage = potPrefix.substring(0, lastDot);
            String sourceWriterName = potPrefix.substring(lastDot+1);
            potentialSourceWriterNames.add(0, sourceWriterPackage+".util."+sourceWriterName+"SourceWriter");
        }
        
        return potentialSourceWriterNames;
    }
    

    
}
