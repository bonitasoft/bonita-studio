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

import java.text.CharacterIterator;
import java.util.ArrayList;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

import com.ibm.icu.text.StringCharacterIterator;



/**
 * @author ckadner
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SQLQuerySourceBuffer
{
    // have original source analyzer or a SQL indentation and linewraaping format (from some preference page)
    // spaces before certain operators and parens,  line breaks befor certain keyword (always,conditionally), indent, 
    // line length, ... see Java code prefernce and how its export format stores the stuff
    
    
    
    // concept of this clas
    //
    
private static final char TAB = '\t';
    private boolean preserveFormatting = false;

    private String originalSource = null;
    private String originalSourceUC = null;
//    private String originalSourceTrimmedUC = null; // trimmed upper case
//    private int[] originalSrcTrimmedIndexToOriginalSrcIndex = null; //init as virtualIndexToStringRefernces
    private StringBuffer generatedSource = null;
    private int[] originalSourceCharStatus = null;

    private String delimitedIdentQt = "\"";


    
    StringBuffer[] bucketArray = null; // initialized as long as original src

    int indexOffset = 0;
    int scopeStartIndex = 0;
    int scopeEndIndex = 0;
    
    private static final int SOURCE_CHAR_STATUS_WRITTEN = 1;
    private static final int SOURCE_CHAR_STATUS_UNWRITTEN = 0;
    private static final int SOURCE_CHAR_STATUS_WHITESPACE = 2;
    //private static final int SOURCE_CHAR_STATUS_IN_COMMENT = 3;

    /** the index in the originalSource that is the next insert point or the
     *  position of the bucket element w/ index 1 to be inserted and the
     *  position after the last char of the bucket element w/ index 0 */
    private int currentIndex = 0; 

    /** element at index 0 is the part that is in sync with the origin */
//    private ArrayList bucket = null;

//    private String[] virtualIndexToStringReferences = null; // init w/ double the size of originalSource, increase capacity on demand/append
//    private int[] virtualIndexToStringReferenceOffset = null; //init as virtualIndexToStringRefernces



    /** char constant for the new line character, value: 'rn' */
    private static final char CARRIAGE_RETURN = '\r';
    
    /** char constant for the new line character, value: '\n' */
    protected static final char NEW_LINE  = SQLQuerySourceWriter.NEW_LINE;
    
    /** char constant, value: ' ' */
    protected static final char SPACE     = SQLQuerySourceWriter.SPACE;

    /** char constant, value: ',' */
    protected static final char COMMA     = SQLQuerySourceWriter.COMMA;



    /** char constant, value: ')' */
    private static final char PAREN_RIGHT             = ')';

    /** char constant, value: '(' */
    private static final char PAREN_LEFT              = '(';

    /**
     * String constant, value: "="
     */
    private final static char EQUAL                   = '=';

    /**
     * String constant, value: " <"
     */
    private final static char LESS_THAN               = '<';

    /**
     * char constant, value: '>'
     */
    private final static char GREATER_THAN            = '>';

    /**
     * char constant, value: '+'
     */
    private final static char PLUS                    = '+';

    /**
     * char constant, value: '-'
     */
    private final static char MINUS                   = '-';

    /**
     * char constant, value: '*'
     */
    private final static char MULTIPLY                = '*';

    /**
     * char constant, value: '/'
     */
    private final static char DIVIDE                  = '/';

    /** TODO: concat is two chars!!
     * String constant, value: '|'
     */
    private final static char CONCATENATE             = '|';


    /** String constant, value: ':' */
    private final static char COLON                   = ':';

    /** String constant, value: ';' */
    private final static char SEMICOLON               = ';';

    char[] sqlSeparators = new char[] {
                    NEW_LINE,
                    SPACE,
                    COMMA,
                    PAREN_RIGHT,
                    PAREN_LEFT,
                    EQUAL,
                    LESS_THAN,
                    GREATER_THAN,
                    PLUS,
                    MINUS,
                    MULTIPLY,
                    DIVIDE,
                    CONCATENATE,
//                    COLON,
                    SEMICOLON
                    };

    /**
     * @param originalSource
     */
    protected SQLQuerySourceBuffer(String originalSource)
    {
        this.originalSource = originalSource;
        this.originalSourceUC = originalSource.toUpperCase();
        //this.originalSourceCharStatus = classifySQLCharacterIndices(originalSource);
//        this.virtualIndexToStringReferences = new String[originalSource.length()];
//        this.virtualIndexToStringReferenceOffset = new int[originalSource.length()];

        this.bucketArray = new StringBuffer[originalSource.length()];
        
//        this.stripOriginalSourceCommentsAndBlanks_makeUpperCase_createIndexMapping();
        
        if (originalSource != null && originalSource.length() > 0) {
            preserveFormatting = true;
            
        }
        this.scopeEndIndex = originalSource.length()-1;
    }

    /**
     * @param originalSource
     */
    protected SQLQuerySourceBuffer(SQLQuerySourceInfo originalSourceInfo)
    {
        this(originalSourceInfo.getSourceSnippet());
        this.indexOffset = originalSourceInfo.getSpanStartOffset();
        char delimIdQtChar = originalSourceInfo.getSqlFormat().getDelimitedIdentifierQuote();
        this.delimitedIdentQt = String.valueOf(delimIdQtChar);
    }

    protected SQLQuerySourceBuffer()
    {
        this.originalSource = null;
        this.preserveFormatting = false;
        generatedSource = new StringBuffer();
    }

    protected void setScope(int start, int end) {
        this.scopeStartIndex = start-indexOffset;
        this.scopeEndIndex = end-indexOffset;
        
        if (currentIndex < this.scopeStartIndex) {
            currentIndex = this.scopeStartIndex;
        }
    }

    protected void setScope(SQLQuerySourceInfo sourceInfo) {
        this.scopeStartIndex = sourceInfo.getSpanStartOffset() - indexOffset;
        this.scopeEndIndex = sourceInfo.getSpanEndOffset() - indexOffset;
        
//        if (currentIndex < this.scopeStartIndex) {
            currentIndex = this.scopeStartIndex;
//        }
    }

    /**
     * @param sql
     * @return
     */
    public SQLQuerySourceBuffer append(String sql)
    {
        if (!preserveFormatting) {
            generatedSource.append(sql);
            return this;
        }

        if (sql != null && sql.length() > 0)
        {
            if (sql.length() == 1) {
                return append(sql.charAt(0),false);
            }
        
            // cut the String into pieces according to space or line break, commas,
            // get all words or separators by them self
//            ArrayList toAppend = splitSQL(sql, sqlSeparators);
//            bucket.addAll(toAppend);
        
            
        
        
        }

        return this;
    }

    
    /**
     * @param keyword
     * @param optional
     * @param ignoreCase
     * @return
     */
    public SQLQuerySourceBuffer appendKeyword(String keyword, boolean optional)
    {
        return appendWord(keyword, optional, true);
    }
    
    /**
     * @param ident
     * @param optional
     * @param ignoreCase
     * @return
     */
    public SQLQuerySourceBuffer appendIdentifier(String ident)
    {
        boolean caseSensitive = ident.startsWith(delimitedIdentQt); 
        return appendWord(ident, false, !caseSensitive);
    }
    
    
    /**
     * @param word
     * @param optional
     * @param ignoreCase
     * @return
     */
    public SQLQuerySourceBuffer appendWord(String word, boolean optional, boolean ignoreCase)
    {
        if (!preserveFormatting) {
            generatedSource.append(word);
            return this;
        }

        if (word != null && word.length() > 0)
        {
            char c = Character.toUpperCase(word.charAt(0));
            int startedFromIndex = currentIndex;
//            String sqlKeywordUC = sqlKeyword.toUpperCase();
            int wordLength = word.length();

            
            if (currentIndex > scopeEndIndex) {
                addToBucket(word);
                return this;
            }
            
            if (isWhiteSpace(originalSource.charAt(currentIndex))
                    && (isWhiteSpace(originalSource.charAt(currentIndex-1))
                            || isSeparator(originalSource.charAt(currentIndex-1))))
            {
                skipWhiteSpace();
            }
            
            
            if (c == originalSourceUC.charAt(currentIndex))
            {
                if (originalSourceUC.regionMatches(ignoreCase,currentIndex,word,0,wordLength))
                {
                    currentIndex += wordLength;
                    updateOriginalSourceCharStatus(startedFromIndex, currentIndex, SOURCE_CHAR_STATUS_WRITTEN);
                }
            }
            else
            {
                // TODO: what about the skipped whitespaces?
                if (!optional) {
                    addToBucket(word);
                }
            }
        
        
        }

        return this;
    }


    
    /**
     * @param startIndex
     * @param endIndex included!
     * @param sourceCharStatus {@link #SOURCE_CHAR_STATUS_UNWRITTEN},
     *        {@link #SOURCE_CHAR_STATUS_WRITTEN}, or
     *        {@link #SOURCE_CHAR_STATUS_WHITESPACE}
     */
    private void updateOriginalSourceCharStatus(int startIndex, int endIndex, int sourceCharStatus) {
        if (startIndex > -1 && endIndex < originalSource.length()) {
            for (int i = startIndex; i < endIndex + 1; i++) {
                originalSourceCharStatus[i] = sourceCharStatus;
            }
        }
    }

    /**
     * @param c
     * @return
     */
    public SQLQuerySourceBuffer append(char c)
    {
        return append(c, false);
    }

    /**
     * @param c
     * @return
     */
    public SQLQuerySourceBuffer appendOptional(char c)
    {
        return append(c, true);
    }

    
    
    /**
     * @param c
     * @param optional
     * @return
     */
    private SQLQuerySourceBuffer append(char c, boolean optional)
    {
        if (!preserveFormatting) {
            generatedSource.append(c);
            return this;
        }
        
        
        
        // does it match? not?
        //   not? 
        //     is it optional?
        //       yes (space) -> ignore
        //       not, skip spaces in orig to next relevant,
        //         match?
        //           no -> bucket
        
        
        // irrelevant keywords AS (not always), parens   SourceBuffer.addOptionalKeyword(String), #addOptional(char), addOptionalWhiteSpace
        
        
        // insert into tab(col1,col2)
        // INSERT INTO TAB (COL1, COL2)    check for optional/did we have a space/separator?->skip or put in bucket, check later if bucket is necessary, skip space
        //                ^
        // insert into tab (col1,col2)
        // INSERT INTO TAB(COL1, COL2)     check if current insert is a separator (),+-/*  skip spaces in orig then
        //                ^
        // insert into tab (col1,col2)
        // INSERT INTO TAB\n   (COL1, COL2)    bucketize whitespaces along with eventual new stuff to come   
        //                ^
        // insert into tab (col1,col2)
        // INSERT INTO TAB(COL1, COL2)     is insert a separator skip underlying orig space
        //                ^
        // insert into tab  (col1,col2)
        // INSERT INTO TAB (COL1, COL2)    is insert a separator or did we have a whitespace before then skip orig whitespace
        //                 ^
        
        // test changed source
        // select * from tab as t1
        // select * from tabas t1

        // test source
        // select * from tab as t

        if (currentIndex > scopeEndIndex) {
            addToBucket(c);
        }
        else if (c == originalSource.charAt(currentIndex))
        {
            originalSourceCharStatus[currentIndex] = SOURCE_CHAR_STATUS_WRITTEN;
            currentIndex++;
        }
        else
        {
            if (isWhiteSpace(c))
            {
                if (!optional) addToBucket(c);
                
                if (isWhiteSpace(originalSource.charAt(currentIndex))) {
                    // don't update originalSourceCharStatus! we skiped WS and kept the new one in bucket
                    currentIndex++;
                }
            }
            else if (isWhiteSpace(originalSource.charAt(currentIndex))
                    && (isWhiteSpace(originalSource.charAt(currentIndex-1))
                            || isSeparator(originalSource.charAt(currentIndex-1))))
            {
                skipWhiteSpace();
                
                if (c == originalSource.charAt(currentIndex)) {
                    originalSourceCharStatus[currentIndex] = SOURCE_CHAR_STATUS_WRITTEN;
                    currentIndex++;
                } else {
                    if (!optional) addToBucket(c);
                }
            } else
            {
                if (!optional) addToBucket(c);
            }
        }
        return this;
    }



    /**
     * 
     */
    private void skipWhiteSpace() {
        while (isWhiteSpace(originalSource.charAt(currentIndex))) {
            originalSourceCharStatus[currentIndex] = SOURCE_CHAR_STATUS_WHITESPACE;
            currentIndex++;
        }
        
    }

    /**
     * @param c
     */
    private void addToBucket(char c) {
        StringBuffer bucket = getBucket(currentIndex);
        bucket.append(c);
    }

    /**
     * @param string
     */
    private void addToBucket(String string) {
        StringBuffer bucket = getBucket(currentIndex);
        bucket.append(string);
    }

    /**
     * @return
     */
    private StringBuffer getBucket(int position) {
        if (bucketArray[position] == null) {
            bucketArray[position] = new StringBuffer();
        }
        StringBuffer bucket = bucketArray[position];
        return bucket;
    }

    /**
     * @param c
     * @return
     */
    private boolean isSeparator(char c) {
        if (isWhiteSpace(c) || isOperator(c) || c == COMMA
                || c == PAREN_LEFT || c == PAREN_RIGHT) {
            return true;
        }
        
        return false;
    }

    /**
     * @param c
     * @return
     */
    private boolean isOperator(char c) {
        if (c == PLUS || c == MINUS || c == MULTIPLY || c == DIVIDE) {
            return true;
        }
        if (c == LESS_THAN || c == GREATER_THAN || c == EQUAL) {
            return true;
        }
        return false;
    }

    /**
     * @param c
     * @return
     */
    private boolean isWhiteSpace(char c) {
        if (c == SPACE || c == NEW_LINE || c == CARRIAGE_RETURN || c == TAB) {
            return true;
        }
        return false;
    }

    /**
     * @param sql
     * @param sqlSeparators
     * @return
     */
    private ArrayList splitSQL(String sql, char[] sqlSeparators)
    {
        // String.split() doesn't work for us, cause we need the separator itself, too
        ArrayList splitSQL = new ArrayList();
        
        int lastSplitIndex = 0;
        for (int i = 0; i < sql.length(); i++)
        {
            char c = sql.charAt(i);
            
            // TODO: optimize splitSQL, compare char ranges instead of single chars,
            //       but think of special char-identifier constellation like ":VARIABLE"
            //if (!(c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '.' || c == '_')) {
            
            for (int si = 0; si < sqlSeparators.length; si++)
            {
                char s = sqlSeparators[si];
                
                if (c == s) {
                    String split = sql.substring(lastSplitIndex, i);
                    String separator = String.valueOf(c);
                    splitSQL.add(split);
                    splitSQL.add(separator);
                    lastSplitIndex = i+1;
                }
                
            }
        }

        if (lastSplitIndex < sql.length()) {
            String lastSplitSegment = sql.substring(lastSplitIndex);
            splitSQL.add(lastSplitSegment);
        }
        
        
        return splitSQL;
    }
    
    
    
    
    /**
     * @return
     */
    public int length()
    {
        if (!preserveFormatting) {
            return generatedSource.length();
        } else {
            return toString().length();
//             throw new UnsupportedOperationException(" length() not yet implemented in "+this.getClass().getName());
//             // means we have to do something like resolving with states we keep once we resolved it
        }
    }
    
    /**
     * @param start
     * @param end
     * @param str
     * @return
     */
    public SQLQuerySourceBuffer replace(int start, int end, String str)
    {
        if (!preserveFormatting) {
            generatedSource.replace(start, end, str);
        } else {
            throw new UnsupportedOperationException(" replace(int, int, String) not implemented in "+this.getClass().getName());
            // means we have to do something like resolving with states we keep once we resolved it
        }
        return this;
    }
    
    
    /** returns a rather expensive snapshot of this SourceBuffer's content
     *  (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        if (!preserveFormatting) {
            return generatedSource.toString();
        }
        
        StringBuffer updatedSource = new StringBuffer(currentIndex+1);
        
        for (int i = 0; i < updatedSource.length(); i++) {
            
            if (bucketArray[i] != null && !isWhiteSpaceOnly(bucketArray[i])) {
                updatedSource.append(bucketArray[i]);
            }
            
            if (originalSourceCharStatus[i] != SOURCE_CHAR_STATUS_UNWRITTEN) {
                updatedSource.append(originalSource.charAt(i));
            }
            
            
        }
        
    
    
 
    

        
//        int[] originalSrcUpdateStatus = new int[originalSourceTrimmedUC.length()];
        
        // lastchar whitespace? no lookahead search for whitespace
//        while (bucket.size() > 1)
        
            // go through bucket, find element that 
            
/* some test scenarios...
 
ORIGINAL SOURCE: 
  select col1,
            case 
             when col2-col1 < 15 then col2
             when col2-col1 > 15 then col3
             else col2-col1
            end
    from table1;            

GENERATED SOURCE:            
  SELECT COL1, COL2-COL1
    FROM TABLE1          
    WHERE (CASE 
             WHEN COL2-COL1 < 15 THEN COL2
             WHEN COL2-COL1 > 15 THEN COL3
           END) > 15;

EXPECTED RESULT:       
  select col1, COL2-COL1
    from table1;            
    WHERE (CASE 
             WHEN COL2-COL1 < 15 THEN COL2
             WHEN COL2-COL1 > 15 THEN COL3
           END) > 15;
  
HOW TO GET THERE (HOW NOT TO):            

  select col1, case when col2-col1 < 15 then col2 when col2-col1 > 15 then col3 else col2-col1 end from table1;            
  SELECT COL1, ^
               COL2
               -
               COL1
               FROM
               TABLE1          
               WHERE 
               (
               CASE 
               WHEN
               COL2
               -
               COL1
               <
               15
               THEN
               COL2
               WHEN
               COL2
               -
               COL1
               >
               15
               THEN
               COL3
               END
               )
               >
               15
               ;


szenario 1: (close to goal) high distance not considered to be likely from program point of view
            what a mess if we would allow fill ups (mess up order)

  select col1, case when col2-col1 < 15 then col2 when col2-col1 > 15 then col3 else col2-col1 end from table1;            
  SELECT COL1,           COL2-COL1                                                                 FROM TABLE1 ^         
                                                                                                               WHERE 
                                                                                                               (
                                                                                                               CASE 
                                                                                                               WHEN
                                                                                                               COL2
                                                                                                               -
                                                                                                               COL1
                                                                                                               <
                                                                                                               15
                                                                                                               THEN
                                                                                                               COL2
                                                                                                               WHEN
                                                                                                               COL2
                                                                                                               -
                                                                                                               COL1
                                                                                                               >
                                                                                                               15
                                                                                                               THEN
                                                                                                               COL3
                                                                                                               END
                                                                                                               )
                                                                                                               >
                                                                                                               15
                                                                                                               ;
                                                                                                               
  -->
  select col1, col2-col1 from table1 WHERE (CASE WHEN COL2-COL1 < 15 THEN COL2 WHEN COL2-COL1 > 15 THEN COL3 END) > 15;
  ~~~~~~~~~~~~~!!!!!!!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

szenario 2:

  select col1, case when col2-col1 < 15 then col2 when col2-col1 > 15 then col3 else col2-col1 end from table1;            
  SELECT COL1, CASE WHEN COL2-COL1 < 15 THEN COL2 WHEN COL2-COL1 > 15 THEN COL3                END
               ^                                                                                   ^
               COL2                                                                                   )
               -                                                                                   >
               COL1                                                                                   15
               FROM                                                                                   ;
               TABLE1          
               WHERE 
               (
               

  -->
  select col1, COL2-COL1 FROM TABLE1 WHERE (case when col2-col1 < 15 then col2 when col2-col1 > 15 then col3 end )>15;            
  ~~~~~~~~~~~~~~~~~~~~~~~!!!!!!!!!!!~~~~~~~~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!~~~~
  
  
szenario 3: (goal) high distance not considered to be likely from program point of view

  select col1, case when col2-col1 < 15 then col2 when col2-col1 > 15 then col3 else col2-col1 end from table1;            
  SELECT COL1,                                                                                     FROM TABLE1
                 ^                                                                                               ^         
               COL2                                                                                               WHERE 
               -                                                                                               (
               COL1                                                                                               CASE 
                                                                                                               WHEN
                                                                                                               COL2
                                                                                                               -
                                                                                                               COL1
                                                                                                               <
                                                                                                               15
                                                                                                               THEN
                                                                                                               COL2
                                                                                                               WHEN
                                                                                                               COL2
                                                                                                               -
                                                                                                               COL1
                                                                                                               >
                                                                                                               15
                                                                                                               THEN
                                                                                                               COL3
                                                                                                               END
                                                                                                               )
                                                                                                               >
                                                                                                               15
                                                                                                               ;
                                                                                                               
  -->
  select col1, COL2-COL1 from table1 WHERE (CASE WHEN COL2-COL1 < 15 THEN COL2 WHEN COL2-COL1 > 15 THEN COL3 END) > 15;
  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


  mmmh I could check for the element types and instance that both 
  originalSrcSpan and generatedSrcSpan we're in and only overwrite/cover/match 
  if we're in the exact same element (in the general appendSQL(QuerObject) 
  we can set the parent type beginning index before we make the call to 
  appendSQL(child) and after the call we set the end after invoking the 
  appendSQL(child) plus we can set the original span(top-down!!!) and - think of
  column ref - same parent)
  ...we can rather know the element that is writing (child), so before calling appendSQL(child) in appendSQL(SQLQueryObject)
  set the current writer in the SQLQuerySourceBuffer and unset it after calling appendSQL(child)
  so in the SourceBuffer there is an List of current writer, current writers parent, current writers grandpa,...
  if the current writer has no Span, it is new and stays in bucket (no attempts to match original)
  if current writer has span and we run out of bounds, bucket it
  ...try to match only within the current Span
  ...try to match/write immediately, if something doesn't match bucket it up
  write (also from bucket) when current writer is done or a child (with a span)
  starts writing, cause we will need his(current/parent) span
  ...when writing from bucket try to fit one of all the succeeding bucket elements
  in the first position after buckets index (try to find out if we have a 
  new SQL element (the bucket elements  till the one element we fit in the first
  position after bucket)) - the succeeding bucket elements must fit after that and after ...
  if not fit - try starting with first bucket element, to find the nearest match
  within the span and then try to append the succeeding bucket elements
  - if no way either - keep bucket until end
  
  ...why the SQLQuerySourceBuffer: no new String construction on .append(String)
            
*/         
        
        return updatedSource.toString();
    }
    
    /**
     * @param buffer
     * @return
     */
    private boolean isWhiteSpaceOnly(StringBuffer buffer) {
        boolean foundNonWhiteSpace = false;
        if (buffer != null) {
            for (int i = 0; i < buffer.length(); i++) {
                if (!isWhiteSpace(buffer.charAt(i))) {
                    foundNonWhiteSpace = true;
                    break;
                }
            }
        }
        return foundNonWhiteSpace == false;
    }

    /** updates the given int[] with the given value from the index begin to the
     * index end-1 
     * @param toBeUpdated
     * @param begin
     * @param end
     * @param value
     */
    private void updateIntArray(int[] toBeUpdated, int begin, int end, int value) {
        if (end > begin && toBeUpdated.length >= end && begin >= 0) {
            
            for (int i = begin; i < end; i++)
            {
                toBeUpdated[i] = value;
                
            }
        }
    }

/*    *//**
     * @see #indexOfFirstRelevantCharacterInOriginalSource(int)
     *//*
    private int indexOfFirstRelevantCharacterInOriginalSource()
    {
        return indexOfFirstRelevantCharacterInOriginalSource(0);
    }

    *//**
     * @param fromIndex
     * @return
     *//*
    private int indexOfFirstRelevantCharacterInOriginalSource(int fromIndex)
    {
        int indexOfFirstRelevantChar = 0;
        
        for (int i = 0; i < originalSourceCharStatus.length; i++)
        {
            int charStatus = originalSourceCharStatus[i];
            if (charStatus != SOURCE_CHAR_STATUS_IN_COMMENT
                            && charStatus != SOURCE_CHAR_STATUS_WHITESPACE
                            && charStatus != SOURCE_CHAR_STATUS_WRITTEN) {
                
                indexOfFirstRelevantChar = i;
                break;
            }
            
        } 
        
        
        return indexOfFirstRelevantChar;
    }
*/    
    
    
    /**
     * strips out the comments of a SQL statement that are identified
     * by two dashes in line "--" and that are not part of a delimited character
     * string (single quotes), or SQL delimited object name (double quotes).
     * <br><b>NOTE:</b> The given SQL statement string must contain the line-break
     * characters to delimit the "--" comments, otherwise all of the SQL-Statement
     * that comes after the first occurence of a "--" comment be treated as part
     * of the comment and rather be ignored, moreover the resulting statement is
     * likely to fail!
     * <br>
     * <p>
     * SELECT EMP_NUM FROM DEPARTEMENT -- pre-accumulated number of employees
     * <br>
     * WHERE LOCATION = 'C-Tower--01' OR LOCATION = 'C-Tower-02'
     * <p>
     * In this statement the first occurence of "--" would indicate a comment
     * that would be stripped of and the second occurence would be retained, as
     * it is part of a String delimited by the enclosing single quotes.
     * 
     * 
     * @param statement  A SQL statement.
     * @return The a SQL statement without "--" comments.
     */
    public void stripOriginalSourceCommentsAndBlanks_makeUpperCase_createIndexMapping()
    {
        final int    NO_QUOTE           = 0;
        final int    SINGLE_QUOTE       = 1;
        final int    DOUBLE_QUOTE       = 2;
        final int    INSIDE_COMMENT     = 3;
        
        char         lookAheadChar;
        char         lastChar           = SPACE;
        int          delimiterState     = NO_QUOTE;
        StringBuffer nonCommentStmt     = new StringBuffer();
        
        int[]        trimmedIndexToOriginalIndex = new int[originalSource.length()];
        
        StringCharacterIterator iter = new StringCharacterIterator(originalSource);
        // Iterate through the string to find a comments.
        // Ignore comments that are enclosed in quotes:
        // delimited SQL object names (double quotes) or character
        // strings (single quotes)
        int i = 0;
        for (char c = iter.first(); c != CharacterIterator.DONE; c = iter.next())
        {
            switch (delimiterState)
            {
                case INSIDE_COMMENT: // --comment end with line
                    if ((c == CARRIAGE_RETURN) || (c == NEW_LINE)) // carriage return
                    {
                        delimiterState = NO_QUOTE;
                    }
                    break;
                    
                case NO_QUOTE:
                    if (c == '\'')
                    {
                        delimiterState = SINGLE_QUOTE;
                    }
                    else
                        if (c == '"')
                        {
                            delimiterState = DOUBLE_QUOTE;
                        }
                        else
                            if (c == '-')
                            {
                                lookAheadChar = iter.next();
                                if (lookAheadChar == '-')
                                {
                                    delimiterState = INSIDE_COMMENT;
                                }
                                else
                                {
                                    nonCommentStmt.append(c);
                                    trimmedIndexToOriginalIndex[nonCommentStmt.length()-1]=i;
                                    i++;
                                    lastChar = c;
                                    c = lookAheadChar;
                                }
                            }
                    break;
                    
                case SINGLE_QUOTE:
                    if (c == '\'')
                    {
                        lookAheadChar = iter.next();
                        if (lookAheadChar != '\'')
                        {
                            delimiterState = NO_QUOTE;
                            nonCommentStmt.append(c);
                            trimmedIndexToOriginalIndex[nonCommentStmt.length()-1]=i;
                            i++;
                            lastChar = c;
                            c = lookAheadChar;
                        }
                    }
                    break;
                case DOUBLE_QUOTE:
                    if (c == '"')
                    {
                        lookAheadChar = iter.next();
                        if (lookAheadChar != '"')
                        {
                            delimiterState = NO_QUOTE;
                            nonCommentStmt.append(c);
                            trimmedIndexToOriginalIndex[nonCommentStmt.length()-1]=i;
                            i++;
                            lastChar = c;
                            c = lookAheadChar;
                        }
                    }
                    break;
            }
            
            // if not inside a comment, append the current char to the resulting StringBuffer
            if (delimiterState != INSIDE_COMMENT)
            {
                
                // filter out the line terminators and multiple spaces
                if ((c == CARRIAGE_RETURN || c == NEW_LINE || c == SPACE) && delimiterState == NO_QUOTE)
                {
                    if (lastChar != SPACE)
                    {
                        nonCommentStmt.append(SPACE);
                        trimmedIndexToOriginalIndex[nonCommentStmt.length()-1]=i;
                        lastChar = SPACE;
                    }
                } 
                else
                
                    // append characters, not CharacterIterator limiter
                    if (c != CharacterIterator.DONE)
                    {
                        // only append blanks in quotes or if the previous char was no blank
                        if (delimiterState != NO_QUOTE || lastChar != SPACE || c != SPACE)
                        {
                            if (delimiterState == NO_QUOTE) {
                                c = Character.toUpperCase(c);
                            }
                            
                            nonCommentStmt.append(c);
                            trimmedIndexToOriginalIndex[nonCommentStmt.length()-1]=i;
                            lastChar = c;
                        }
                    }
                
            }
            i++;
        }
        
//        originalSourceTrimmedUC = nonCommentStmt.toString().trim();
        
//        originalSrcTrimmedIndexToOriginalSrcIndex = new int[originalSourceTrimmedUC.length()];
        
        // shorten the index mapping array to the size of the originalSourceTrimmedUC
//        for (int j = 0; j < originalSrcTrimmedIndexToOriginalSrcIndex.length; j++)
//        {
//            originalSrcTrimmedIndexToOriginalSrcIndex[j] = trimmedIndexToOriginalIndex[j];
//        } 
    }

    /**
     * @param string
     * @return
     */
    public int lastIndexOf(String string) {
        return toString().lastIndexOf(string);
    }

    /**
     * @param i
     * @return
     */
    public char charAt(int i) {
        return toString().charAt(i);
    }

    /**
     * @param i
     */
    public SQLQuerySourceBuffer append(int i) {
        return append(String.valueOf(i));
        
    }
    
    
    public void insertConditionalLineBreak(SQLQueryObject context) {
        // so we can line break all potential line breaks for that context object
        // if we later decide to wrap, we can easily at the right points
        // always break line before the largest containing section if not already broken?
        //   e.g.
        // SELECT col_a, col_b, col_c, some_long_function_name_with_arguments(
        //     arg_1, arg_2, arg_3)
        //   FROM table_a, table_b
        //  vs.
        // SELECT col_a, col_b, col_c,
        //     some_long_function_name_with_arguments( arg_1, arg_2, arg_3)
        //   FROM table_a, table_b
        
        
        // we need to be aware of the total new length of the source, so we need
        // a mapping between orig source indexes to new generated source indices
        
        // flush buckets for new elements and latest when scope is changed, so
        // we have the conditional breaks in the right place
        // so we fill up a StringBuffer in paralell that, either gets the old source or the new in
        
        
        // for use in editor: keep track of sections, associate smallest SQLQueryObject
        // related to section like Span also for new elements in generated StringBuffer
        
    }
    
    public void setIndent(SQLQueryObject context) {}
    
}
