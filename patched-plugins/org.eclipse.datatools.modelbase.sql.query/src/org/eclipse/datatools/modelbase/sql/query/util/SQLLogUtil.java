/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.util;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;


/**
 * This class provides the logging and tracing facility using Eclipse 
 * standard logging machanism.
 */
public class SQLLogUtil {

    private static int MAX_TRACELINE_LEN = 200;
    //protected boolean isDebugOn;
    private Plugin currentPlugin;

    public SQLLogUtil(Plugin plugin) {
        // check if plugin is null if SQLLogUtils are used outside eclipse container
        if (plugin != null) {
            //	isDebugOn = plugin.isDebugging() ;
            this.setCurrentPlugin(plugin);
        }
    }

    /**
     * @return Returns the currentPlugin.
     */
    public Plugin getCurrentPlugin() {
        return currentPlugin;
    }

    /**
     * @param currentPlugin The currentPlugin to set.
     */
    public void setCurrentPlugin(Plugin currentPlugin) {
        this.currentPlugin = currentPlugin;
    }

    /**
     * Gets whether or not tracing is active.  This method can be used to improve
     * performance by "guarding" trace calls that do something expensive, such as
     * creating objects.  For example:
     *   if (getPlugin().getLogUtil().isTracing()) {
     *     getPlugin().getLogUtil().writeTraceEntry( new Object[] {new Integer( intval )} ); 
     *   } 
     * @return true when tracing is active, otherwise false
     */
    public boolean isTracing() {
        boolean tracing = false;

        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            tracing = true;
        }

        return tracing;
    }

    /**
     * Logs an error described by a throwable.
     * 
     * <p>This method should be used whenever a class in this plugin
     * has to log an error since it adheres to the global logging
     * strategy.
     * 
     * @param throwable
     */
    public void write(Throwable throwable) {
        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            Status status = new Status(1, getCurrentPlugin().getBundle().getSymbolicName(), 0, throwable.toString(), throwable);
            getCurrentPlugin().getLog().log(status);
        }
    }

    /**
     * Logs an error described by a text.
     * 
     * <p>This method should be whenever a class in this plugin
     * has to log an error since it adheres to the global logging
     * strategy.
     * 
     * @param text
     */
    public void write(String text) {
        write(new Throwable(text));
    }

    /**
     * Logs an error described by an object.
     * 
     * <p>This method should be whenever a class in this plugin
     * has to log an error since it adheres to the global logging
     * strategy.
     * 
     * @param obj 
     */
    public void write(Object obj) {
        write(obj.toString());
    }

    /**
     * Logs an information described by a text.
     * 
     * <p>This method should be whenever a class in this plugin
     * has to log an information since it adheres to the global logging
     * strategy.
     * 
     * @param text
     */
    public void writeInfo(String text) {
        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            Status status = new Status(Status.INFO, getCurrentPlugin().getBundle().getSymbolicName(), 0, text, new Throwable(text));
            getCurrentPlugin().getLog().log(status);
        }
    }

    /**
     * Logs an error described by a throwable.
     * 
     * <p>This method should be used whenever a class in this plugin
     * has to log an error since it adheres to the global logging
     * strategy.
     * 
     * @param throwable
     */
    public void writeLog(Throwable throwable) {
        if (getCurrentPlugin() != null) {
            Status status = new Status(Status.ERROR, getCurrentPlugin().getBundle().getSymbolicName(), 0, throwable.toString(), throwable);
            getCurrentPlugin().getLog().log(status);
        }
    }

    /**
     * Logs an error described by a text.
     * 
     * <p>This method should be whenever a class in this plugin
     * has to log an error since it adheres to the global logging
     * strategy.
     * 
     * @param text
     */
    public void writeLog(String text) {
        writeLog(new Throwable(text));
    }

    /**
     * Logs an error described by an object.
     * 
     * <p>This method should be whenever a class in this plugin
     * has to log an error since it adheres to the global logging
     * strategy.
     * 
     * @param text
     */
    public void writeLog(Object obj) {
        writeLog(obj.toString());
    }

    /**
     * Traces the given string.
     * <p>
     * This method is used to trace intermediate results in a method.  The calls
     * writeTraceEntry and writeTraceExit can be used to provide context for this
     * trace entry.
     * 
     * @param text the text to write to the trace log
     */
    public void writeTrace(String text) {
        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            Status status = new Status(Status.INFO, getCurrentPlugin().getBundle().getSymbolicName(), 0, "Trace: " + text, null); //$NON-NLS-1$
            getCurrentPlugin().getLog().log(status);
        }
    }

    /**
     * Traces a method entry and optionally the method's arguments.
     * 
     * <p>
     * This method should be used to log the control flow in this plugin since
     * it adheres to the global logging strategy.
     * 
     * @param args optional <code>Object[]</code> the arguments for the method
     *        invokation, <b>Note: </b> <code>args[i].toString()</code>
     *        will be called to create the trace message
     */
    public void writeTraceEntry(Object[] args) {
        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            Throwable t = new Throwable();
            StackTraceElement caller = t.getStackTrace()[1];
            String className = caller.getClassName();
            String methodName = caller.getMethodName();
            int line = caller.getLineNumber();

            StringBuffer msg = new StringBuffer();
            msg.append("Trace: Entry "); //$NON-NLS-1$
            msg.append(className);
            msg.append("."); //$NON-NLS-1$
            msg.append(methodName);
            msg.append(" at line "); //$NON-NLS-1$
            msg.append(line);

            if (args != null) {
                msg.append(" with args: "); //$NON-NLS-1$
                for (int i = 0; i < args.length; i++) {
                    String arg = null;
                    boolean truncate = false;

                    if (args[i] != null) {
                        if (args[i] instanceof SQLQueryObject) {
                            arg = StatementHelper.getSQLSourceUnformatted((SQLQueryObject) args[i]);
                        }
                        else {
                            arg = args[i].toString();
                        }
                    }
                    else {
                        arg = "null"; //$NON-NLS-1$
                    }

                    // truncate toStrings that are too long to not clutter up the trace file
                    truncate = arg.length() > MAX_TRACELINE_LEN;
                    if (truncate) {
                        arg = arg.substring(0, MAX_TRACELINE_LEN) + "..."; //$NON-NLS-1$
                    }

                    msg.append(arg);

                    // append a separater if needed
                    if (i < args.length - 1) {
                        msg.append(", "); //$NON-NLS-1$                        
                    }
                }
            }

            Status status = new Status(Status.INFO, getCurrentPlugin().getBundle().getSymbolicName(), 0, msg.toString(), null);

            getCurrentPlugin().getLog().log(status);
        }
    }

    /**
     * Traces a method exit and optionally the method's return value.
     * 
     * <p>
     * This method should be used to log the control flow in this plugin since
     * it adheres to the global logging strategy.
     * 
     * @param returnValue the return value of the method to be traced,
     * @return the given <code>returnValue</code>
     */
    public boolean writeTraceExit(boolean returnValue) {
        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            writeTraceExit(new Boolean(returnValue));
        }
        return returnValue;
    }

    /**
     * Traces a method exit and optionally the method's return value.
     * 
     * <p>
     * This method should be used to log the control flow in this plugin since
     * it adheres to the global logging strategy.
     * 
     * @param returnValue the return value of the method to be traced,
     * @return the given <code>returnValue</code>
     */
    public int writeTraceExit(int returnValue) {
        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            writeTraceExit(new Integer(returnValue));
        }
        return returnValue;
    }

    /**
     * Traces a method exit and optionally the method's return value.
     * 
     * <p>
     * This method should be used to log the control flow in this plugin since
     * it adheres to the global logging strategy.
     * 
     * @param returnValue optional the return value of the
     *        method to be traced,
     *        <b>Note:</b> <code>returnValue.toString()</code> will be called to
     *        create the trace message
     * @return the given <code>returnValue</code>
     */
    public Object writeTraceExit(Object returnValue) {
        if (getCurrentPlugin() != null && getCurrentPlugin().isDebugging()) {
            Throwable t = new Throwable();
            StackTraceElement caller = t.getStackTrace()[1];
            // Handle case where this writeTraceExit method is called from another
            // writeTraceExit method.
            String className = caller.getClassName();
            String methodName = caller.getMethodName();
            if (methodName.equals("writeTraceExit")) { //$NON-NLS-1$
                caller = t.getStackTrace()[2];
                className = caller.getClassName();
                methodName = caller.getMethodName();
            }
            int line = caller.getLineNumber();

            StringBuffer msg = new StringBuffer();
            msg.append("Trace: Exit "); //$NON-NLS-1$
            msg.append(className);
            msg.append("."); //$NON-NLS-1$
            msg.append(methodName);
            msg.append(" at line "); //$NON-NLS-1$
            msg.append(line);

            String returnValueString = null;
            if (returnValue != null) {
                if (returnValue instanceof SQLQueryObject) {
                    returnValueString = StatementHelper.getSQLSourceUnformatted((SQLQueryObject) returnValue);
                }
                else {
                    returnValueString = returnValue.toString();
                }
                if (returnValueString != null && returnValueString.length() > MAX_TRACELINE_LEN) {
                    returnValueString = returnValueString.substring(0, MAX_TRACELINE_LEN) + "..."; //$NON-NLS-1$
                }
                msg.append(" with return value: "); //$NON-NLS-1$
                msg.append(returnValueString);
            }

            Status status = new Status(Status.INFO, getCurrentPlugin().getBundle().getSymbolicName(), 0, msg.toString(), null);
            getCurrentPlugin().getLog().log(status);
        }
        return returnValue;
    }

}
