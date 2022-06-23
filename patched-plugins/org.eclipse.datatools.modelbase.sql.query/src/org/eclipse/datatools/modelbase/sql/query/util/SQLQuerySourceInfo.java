/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.util;

import java.lang.ref.WeakReference;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;


/**
 * @author ckadner
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SQLQuerySourceInfo
{

    private int                  spanStartOffset    = 0;
    private int                  spanEndOffset      = 0;
    private String               sourceSnippet      = null;
    private int                  lineNumberStart    = 0;
    private int                  columnNumberStart  = 0;
    private int                  lineNumberEnd      = 0;
    private int                  columnNumberEnd    = 0;

    private SQLQuerySourceFormat sqlFormat          = null;
    
    private List                 comments           = null;

    private WeakReference        queryObjectBackRef = null;

    
    /**
     * 
     */
    public SQLQuerySourceInfo()
    {
        super();
    }
    
    /**
     * Constructor. Will hold a back reference to the
     * <code>SQLQueryObject</code> that holds this
     * <code>SQLQuerySourceInfo</code>.
     * 
     * @param queryObjectBackRef
     *            will be hold as a weak reference
     */
    public SQLQuerySourceInfo(SQLQueryObject queryObjectBackRef)
    {
        this.queryObjectBackRef = new WeakReference(queryObjectBackRef);
    }
    
    
    /**
     * <p>
     * <b>Note: </b> if this <code>SQLQuerySourceInfo</code>'s reference to
     * the <code>SQLQuerySourceFormat</code> is <code>null</code>,
     * {@link SQLQuerySourceFormat#SQL_SOURCE_FORMAT_DEFAULT} instance will be
     * returned. No modifications on its members are allowed as that
     * <code>SQLQuerySourceFormat</code> instance is <code>final</code>.
     * </p>
     * 
     * @return Returns this <code>sqlFormat</code> or, if that's <code>null</code>,
     *         <code>SQLQuerySourceFormat#SQL_SOURCE_FORMAT_DEFAULT</code>
     */
    public SQLQuerySourceFormat getSqlFormat()
    {
//        if (this.sqlFormat == null) 
        {
            // try to recursively get the SQLSourceFormat of the eContainer
            if (queryObjectBackRef != null
                            && queryObjectBackRef.get() != null)
            {
                SQLQueryObject queryObject =
                    (SQLQueryObject) queryObjectBackRef.get();   
                
                if (queryObject.eContainer() instanceof SQLQueryObject)
                {
                    SQLQueryObject queryObjectContainer =
                        (SQLQueryObject) queryObject.eContainer();
                    this.sqlFormat =
                        queryObjectContainer.getSourceInfo().getSqlFormat();
                }
            }
            if (this.sqlFormat == null)
            {
                this.sqlFormat = SQLQuerySourceFormat.copyDefaultFormat(); 
            }
        }
        return sqlFormat;
    }

    /**
     * @param sqlFormat
     *            The sqlFormat to set.
     */
    public void setSqlFormat(SQLQuerySourceFormat sqlFormat)
    {
        this.sqlFormat = sqlFormat;
    }

    /**
     * @return Returns the sourceSnippet.
     */
    public String getSourceSnippet()
    {
        return sourceSnippet;
    }

    /**
     * @param sourceSnippet
     *            The sourceSnippet to set.
     */
    public void setSourceSnippet(String sourceSnippet)
    {
        this.sourceSnippet = sourceSnippet;
    }

    /**
     * @return Returns the spanEndOffset.
     */
    public int getSpanEndOffset()
    {
        return spanEndOffset;
    }

    /**
     * @param spanEndOffset
     *            The spanEndOffset to set.
     */
    public void setSpanEndOffset(int spanEndOffset)
    {
        this.spanEndOffset = spanEndOffset;
    }

    /**
     * @return Returns the spanStartOffset.
     */
    public int getSpanStartOffset()
    {
        return spanStartOffset;
    }

    /**
     * @param spanStartOffset
     *            The spanStartOffset to set.
     */
    public void setSpanStartOffset(int spanStartOffset)
    {
        this.spanStartOffset = spanStartOffset;
    }

    
    /**
     * @return Returns the columnNumberEnd.
     */
    public int getColumnNumberEnd()
    {
        return columnNumberEnd;
    }
    
    /**
     * @param columnNumberEnd The columnNumberEnd to set.
     */
    public void setColumnNumberEnd(int columnNumberEnd)
    {
        this.columnNumberEnd = columnNumberEnd;
    }
    
    /**
     * @return Returns the columnNumberStart.
     */
    public int getColumnNumberStart()
    {
        return columnNumberStart;
    }
    
    /**
     * @param columnNumberStart The columnNumberStart to set.
     */
    public void setColumnNumberStart(int columnNumberStart)
    {
        this.columnNumberStart = columnNumberStart;
    }
    
    /**
     * @return Returns the lineNumberEnd.
     */
    public int getLineNumberEnd()
    {
        return lineNumberEnd;
    }
    
    /**
     * @param lineNumberEnd The lineNumberEnd to set.
     */
    public void setLineNumberEnd(int lineNumberEnd)
    {
        this.lineNumberEnd = lineNumberEnd;
    }
    
    /**
     * @return Returns the lineNumberStart.
     */
    public int getLineNumberStart()
    {
        return lineNumberStart;
    }
    
    /**
     * @param lineNumberStart The lineNumberStart to set.
     */
    public void setLineNumberStart(int lineNumberStart)
    {
        this.lineNumberStart = lineNumberStart;
    }

    /**
     * Returns the List of <code>SQLComment</code>s.
     * 
     * @return List of <code>SQLComment</code>s.
     */
    public List getComments()
    {
        return comments;
    }

    /**
     * Sets the List of <code>SQLComment</code>s.
     * 
     * @param comments The List of <code>SQLComment</code>s to set.
     */
    public void setComments(List comments)
    {
        this.comments = comments;
    }

    /**
     * Returns the queryObject that holds this <code>SQLQuerySourceInfo</code>.
     * 
     * @return Returns the queryObject that holds this
     *         <code>SQLQuerySourceInfo</code>, is hold as a weak reference
     */
    public SQLQueryObject getQueryObjectBackReference()
    {
        if (this.queryObjectBackRef != null)
        {
            return (SQLQueryObject) this.queryObjectBackRef.get();
        }
        return null;
    }
    
    /**
     * Sets the queryObject that holds this <code>SQLQuerySourceInfo</code>.
     * 
     * @param queryObjectBackRef
     *            The queryObjectBackRef to set, will be hold as a weak
     *            reference
     */
    public void setQueryObjectBackReference(SQLQueryObject queryObjectBackRef)
    {
        this.queryObjectBackRef = new WeakReference(queryObjectBackRef);
    }
}