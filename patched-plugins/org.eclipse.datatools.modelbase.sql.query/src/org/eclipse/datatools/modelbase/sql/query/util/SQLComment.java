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

/**
 * @author ckadner
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SQLComment
{
    public static final int COMMENT_POSITION_LINE_END = 0;
    public static final int COMMENT_POSITION_PREV_LINE = 1;
    public static final int COMMENT_POSITION_NEXT_LINE = 2;
    
    
    private boolean isMultiLineComment = false;
    
    private String text = null;
    
    private int relativePosition = COMMENT_POSITION_LINE_END;
    
    private SQLQuerySourceInfo sourceInfo = null;
    
    
    /**
     * Empty constructor.
     */
    public SQLComment()
    {
    }

    
    /**
     * @param text
     * @param relativePosition default is {@link #COMMENT_POSITION_LINE_END}
     * @param isMultiLineComment
     */
    public SQLComment(String text, int relativePosition, boolean isMultiLineComment)
    {
        this.text = text;
        this.relativePosition = relativePosition;
        this.isMultiLineComment = isMultiLineComment;
    }
    
    
    /**
     * @return Returns the isMultiLineComment.
     */
    public boolean isMultiLineComment()
    {
        return isMultiLineComment;
    }
    
    /**
     * @param isMultiLineComment The isMultiLineComment to set.
     */
    public void setMultiLineComment(boolean isMultiLineComment)
    {
        this.isMultiLineComment = isMultiLineComment;
    }
    
    /**
     * @return Returns the text.
     */
    public String getText()
    {
        return text;
    }
    
    /**
     * @param text The text to set.
     */
    public void setText(String text)
    {
        this.text = text;
    }
    
    /**
     * @return Returns the relativePosition.
     */
    public int getRelativePosition()
    {
        return relativePosition;
    }
    
    /**
     * @param relativePosition The relativePosition to set.
     */
    public void setRelativePosition(int relativePosition)
    {
        this.relativePosition = relativePosition;
    }
    
    /**
     * @return Returns the sourceInfo.
     */
    public SQLQuerySourceInfo getSourceInfo()
    {
        return sourceInfo;
    }
    
    /**
     * @param sourceInfo The sourceInfo to set.
     */
    public void setSourceInfo(SQLQuerySourceInfo sourceInfo)
    {
        this.sourceInfo = sourceInfo;
    }
}
