package org.bonitasoft.studio.condition.parser.antlr.internal;

import static org.assertj.core.api.Assertions.assertThat;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.Token;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class InternalConditionModelLexerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldNextToken_BuildATokenForUTF8Strings() throws Exception {
        Token nextToken = new InternalConditionModelLexer(new ANTLRStringStream("varąčęėoo")).nextToken();
        assertThat(nextToken.getText()).isEqualTo("varąčęėoo");
        assertThat(nextToken.getType()).isEqualTo(InternalConditionModelLexer.RULE_ID);
        
        nextToken = new InternalConditionModelLexer(new ANTLRStringStream("カタカナ")).nextToken();
        assertThat(nextToken.getText()).isEqualTo("カタカナ");
        assertThat(nextToken.getType()).isEqualTo(InternalConditionModelLexer.RULE_ID);
        
       
    }
    
    @Test
    public void shouldNextToken_BuildABooleanTokenForTrueAndFalse() throws Exception {
        Token  nextToken = new InternalConditionModelLexer(new ANTLRStringStream("true")).nextToken();
        assertThat(nextToken.getText()).isEqualTo("true");
        assertThat(nextToken.getType()).isEqualTo(InternalConditionModelLexer.RULE_BOOLEAN);
        
         nextToken = new InternalConditionModelLexer(new ANTLRStringStream("false")).nextToken();
        assertThat(nextToken.getText()).isEqualTo("false");
        assertThat(nextToken.getType()).isEqualTo(InternalConditionModelLexer.RULE_BOOLEAN);
    }
}
