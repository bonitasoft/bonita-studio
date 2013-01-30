package org.bonitasoft.studio.condition.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalConditionModelLexer extends Lexer {
    public static final int RULE_BOOLEAN=8;
    public static final int RULE_ID=7;
    public static final int RULE_DATE=10;
    public static final int RULE_LONG=5;
    public static final int RULE_ANY_OTHER=14;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=13;
    public static final int RULE_DOUBLE=4;
    public static final int RULE_ML_COMMENT=12;
    public static final int T__19=19;
    public static final int RULE_STRING=6;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int RULE_INT=11;
    public static final int RULE_WS=9;

    // delegates
    // delegators

    public InternalConditionModelLexer() {;} 
    public InternalConditionModelLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalConditionModelLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g"; }

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:11:7: ( '<=' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:11:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:12:7: ( '<' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:12:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:13:7: ( '>=' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:13:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:14:7: ( '>' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:14:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:15:7: ( '!=' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:15:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:16:7: ( '=' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:16:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:17:7: ( '!' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:17:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1045:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1045:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1045:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_BOOLEAN"
    public final void mRULE_BOOLEAN() throws RecognitionException {
        try {
            int _type = RULE_BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1047:14: ( ( 'true' | 'false' ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1047:16: ( 'true' | 'false' )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1047:16: ( 'true' | 'false' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='t') ) {
                alt2=1;
            }
            else if ( (LA2_0=='f') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1047:17: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1047:24: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BOOLEAN"

    // $ANTLR start "RULE_DATE"
    public final void mRULE_DATE() throws RecognitionException {
        try {
            int _type = RULE_DATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1049:11: ( '\\'' (~ ( '\\'' ) )* '\\'' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1049:13: '\\'' (~ ( '\\'' ) )* '\\''
            {
            match('\''); 
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1049:18: (~ ( '\\'' ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\u0000' && LA3_0<='&')||(LA3_0>='(' && LA3_0<='\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1049:18: ~ ( '\\'' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DATE"

    // $ANTLR start "RULE_LONG"
    public final void mRULE_LONG() throws RecognitionException {
        try {
            int _type = RULE_LONG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1051:11: ( ( '-' )? ( '0' .. '9' )+ )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1051:13: ( '-' )? ( '0' .. '9' )+
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1051:13: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1051:13: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1051:18: ( '0' .. '9' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1051:19: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_LONG"

    // $ANTLR start "RULE_DOUBLE"
    public final void mRULE_DOUBLE() throws RecognitionException {
        try {
            int _type = RULE_DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1053:13: ( ( '-' )? RULE_INT '.' RULE_INT )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1053:15: ( '-' )? RULE_INT '.' RULE_INT
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1053:15: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1053:15: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            mRULE_INT(); 
            match('.'); 
            mRULE_INT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DOUBLE"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1055:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1055:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1055:11: ( '^' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='^') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1055:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1055:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')||(LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1057:10: ( ( '0' .. '9' )+ )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1057:12: ( '0' .. '9' )+
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1057:12: ( '0' .. '9' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1057:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\"') ) {
                alt12=1;
            }
            else if ( (LA12_0=='\'') ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='\\') ) {
                            alt10=1;
                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<='!')||(LA10_0>='#' && LA10_0<='[')||(LA10_0>=']' && LA10_0<='\uFFFF')) ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:66: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop11:
                    do {
                        int alt11=3;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0=='\\') ) {
                            alt11=1;
                        }
                        else if ( ((LA11_0>='\u0000' && LA11_0<='&')||(LA11_0>='(' && LA11_0<='[')||(LA11_0>=']' && LA11_0<='\uFFFF')) ) {
                            alt11=2;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1059:137: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1061:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1061:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1061:24: ( options {greedy=false; } : . )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='*') ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1=='/') ) {
                        alt13=2;
                    }
                    else if ( ((LA13_1>='\u0000' && LA13_1<='.')||(LA13_1>='0' && LA13_1<='\uFFFF')) ) {
                        alt13=1;
                    }


                }
                else if ( ((LA13_0>='\u0000' && LA13_0<=')')||(LA13_0>='+' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1061:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='\u0000' && LA14_0<='\t')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:40: ( ( '\\r' )? '\\n' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='\n'||LA16_0=='\r') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:41: ( '\\r' )? '\\n'
                    {
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:41: ( '\\r' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='\r') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1063:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1065:16: ( . )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1065:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:8: ( T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | RULE_WS | RULE_BOOLEAN | RULE_DATE | RULE_LONG | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_ANY_OTHER )
        int alt17=18;
        alt17 = dfa17.predict(input);
        switch (alt17) {
            case 1 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:10: T__15
                {
                mT__15(); 

                }
                break;
            case 2 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:16: T__16
                {
                mT__16(); 

                }
                break;
            case 3 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:22: T__17
                {
                mT__17(); 

                }
                break;
            case 4 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:28: T__18
                {
                mT__18(); 

                }
                break;
            case 5 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:34: T__19
                {
                mT__19(); 

                }
                break;
            case 6 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:40: T__20
                {
                mT__20(); 

                }
                break;
            case 7 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:46: T__21
                {
                mT__21(); 

                }
                break;
            case 8 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:52: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 9 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:60: RULE_BOOLEAN
                {
                mRULE_BOOLEAN(); 

                }
                break;
            case 10 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:73: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 11 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:83: RULE_LONG
                {
                mRULE_LONG(); 

                }
                break;
            case 12 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:93: RULE_DOUBLE
                {
                mRULE_DOUBLE(); 

                }
                break;
            case 13 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:105: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 14 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:113: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 15 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:122: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 16 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:134: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 17 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:150: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 18 :
                // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1:166: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA17_eotS =
        "\1\uffff\1\21\1\23\1\25\2\uffff\2\31\2\17\1\37\1\17\1\uffff\2\17"+
        "\11\uffff\1\31\1\uffff\1\31\3\uffff\1\37\1\uffff\1\37\4\uffff\2"+
        "\31\1\51\2\uffff\1\54\1\31\1\uffff\1\54";
    static final String DFA17_eofS =
        "\56\uffff";
    static final String DFA17_minS =
        "\1\0\3\75\2\uffff\1\162\1\141\1\0\1\60\1\56\1\101\1\uffff\1\0\1"+
        "\52\11\uffff\1\165\1\uffff\1\154\2\0\1\uffff\1\56\1\uffff\1\56\4"+
        "\uffff\1\145\1\163\2\0\1\uffff\1\60\1\145\1\uffff\1\60";
    static final String DFA17_maxS =
        "\1\uffff\3\75\2\uffff\1\162\1\141\1\uffff\2\71\1\172\1\uffff\1\uffff"+
        "\1\57\11\uffff\1\165\1\uffff\1\154\2\uffff\1\uffff\1\71\1\uffff"+
        "\1\71\4\uffff\1\145\1\163\2\uffff\1\uffff\1\172\1\145\1\uffff\1"+
        "\172";
    static final String DFA17_acceptS =
        "\4\uffff\1\6\1\10\6\uffff\1\15\2\uffff\1\22\1\1\1\2\1\3\1\4\1\5"+
        "\1\7\1\6\1\10\1\uffff\1\15\3\uffff\1\12\1\uffff\1\13\1\uffff\1\14"+
        "\1\17\1\20\1\21\4\uffff\1\12\2\uffff\1\11\1\uffff";
    static final String DFA17_specialS =
        "\1\0\7\uffff\1\3\4\uffff\1\4\15\uffff\1\1\1\5\12\uffff\1\6\1\2\5"+
        "\uffff}>";
    static final String[] DFA17_transitionS = {
            "\11\17\2\5\2\17\1\5\22\17\1\5\1\3\1\15\4\17\1\10\5\17\1\11\1"+
            "\17\1\16\12\12\2\17\1\1\1\4\1\2\2\17\32\14\3\17\1\13\1\14\1"+
            "\17\5\14\1\7\15\14\1\6\6\14\uff85\17",
            "\1\20",
            "\1\22",
            "\1\24",
            "",
            "",
            "\1\30",
            "\1\32",
            "\47\34\1\35\64\34\1\33\uffa3\34",
            "\12\36",
            "\1\41\1\uffff\12\40",
            "\32\31\4\uffff\1\31\1\uffff\32\31",
            "",
            "\0\42",
            "\1\43\4\uffff\1\44",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\45",
            "",
            "\1\46",
            "\42\51\1\50\4\51\1\47\64\51\1\50\5\51\1\50\3\51\1\50\7\51\1"+
            "\50\3\51\1\50\1\51\2\50\uff8a\51",
            "\47\34\1\35\64\34\1\33\uffa3\34",
            "",
            "\1\41\1\uffff\12\36",
            "",
            "\1\41\1\uffff\12\40",
            "",
            "",
            "",
            "",
            "\1\52",
            "\1\53",
            "\0\42",
            "\47\34\1\35\64\34\1\33\uffa3\34",
            "",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31",
            "\1\55",
            "",
            "\12\31\7\uffff\32\31\4\uffff\1\31\1\uffff\32\31"
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | RULE_WS | RULE_BOOLEAN | RULE_DATE | RULE_LONG | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_0 = input.LA(1);

                        s = -1;
                        if ( (LA17_0=='<') ) {s = 1;}

                        else if ( (LA17_0=='>') ) {s = 2;}

                        else if ( (LA17_0=='!') ) {s = 3;}

                        else if ( (LA17_0=='=') ) {s = 4;}

                        else if ( ((LA17_0>='\t' && LA17_0<='\n')||LA17_0=='\r'||LA17_0==' ') ) {s = 5;}

                        else if ( (LA17_0=='t') ) {s = 6;}

                        else if ( (LA17_0=='f') ) {s = 7;}

                        else if ( (LA17_0=='\'') ) {s = 8;}

                        else if ( (LA17_0=='-') ) {s = 9;}

                        else if ( ((LA17_0>='0' && LA17_0<='9')) ) {s = 10;}

                        else if ( (LA17_0=='^') ) {s = 11;}

                        else if ( ((LA17_0>='A' && LA17_0<='Z')||LA17_0=='_'||(LA17_0>='a' && LA17_0<='e')||(LA17_0>='g' && LA17_0<='s')||(LA17_0>='u' && LA17_0<='z')) ) {s = 12;}

                        else if ( (LA17_0=='\"') ) {s = 13;}

                        else if ( (LA17_0=='/') ) {s = 14;}

                        else if ( ((LA17_0>='\u0000' && LA17_0<='\b')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\u001F')||(LA17_0>='#' && LA17_0<='&')||(LA17_0>='(' && LA17_0<=',')||LA17_0=='.'||(LA17_0>=':' && LA17_0<=';')||(LA17_0>='?' && LA17_0<='@')||(LA17_0>='[' && LA17_0<=']')||LA17_0=='`'||(LA17_0>='{' && LA17_0<='\uFFFF')) ) {s = 15;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA17_27 = input.LA(1);

                        s = -1;
                        if ( (LA17_27=='\'') ) {s = 39;}

                        else if ( (LA17_27=='\"'||LA17_27=='\\'||LA17_27=='b'||LA17_27=='f'||LA17_27=='n'||LA17_27=='r'||(LA17_27>='t' && LA17_27<='u')) ) {s = 40;}

                        else if ( ((LA17_27>='\u0000' && LA17_27<='!')||(LA17_27>='#' && LA17_27<='&')||(LA17_27>='(' && LA17_27<='[')||(LA17_27>=']' && LA17_27<='a')||(LA17_27>='c' && LA17_27<='e')||(LA17_27>='g' && LA17_27<='m')||(LA17_27>='o' && LA17_27<='q')||LA17_27=='s'||(LA17_27>='v' && LA17_27<='\uFFFF')) ) {s = 41;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA17_40 = input.LA(1);

                        s = -1;
                        if ( (LA17_40=='\'') ) {s = 29;}

                        else if ( (LA17_40=='\\') ) {s = 27;}

                        else if ( ((LA17_40>='\u0000' && LA17_40<='&')||(LA17_40>='(' && LA17_40<='[')||(LA17_40>=']' && LA17_40<='\uFFFF')) ) {s = 28;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA17_8 = input.LA(1);

                        s = -1;
                        if ( (LA17_8=='\\') ) {s = 27;}

                        else if ( ((LA17_8>='\u0000' && LA17_8<='&')||(LA17_8>='(' && LA17_8<='[')||(LA17_8>=']' && LA17_8<='\uFFFF')) ) {s = 28;}

                        else if ( (LA17_8=='\'') ) {s = 29;}

                        else s = 15;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA17_13 = input.LA(1);

                        s = -1;
                        if ( ((LA17_13>='\u0000' && LA17_13<='\uFFFF')) ) {s = 34;}

                        else s = 15;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA17_28 = input.LA(1);

                        s = -1;
                        if ( (LA17_28=='\'') ) {s = 29;}

                        else if ( (LA17_28=='\\') ) {s = 27;}

                        else if ( ((LA17_28>='\u0000' && LA17_28<='&')||(LA17_28>='(' && LA17_28<='[')||(LA17_28>=']' && LA17_28<='\uFFFF')) ) {s = 28;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA17_39 = input.LA(1);

                        s = -1;
                        if ( ((LA17_39>='\u0000' && LA17_39<='\uFFFF')) ) {s = 34;}

                        else s = 41;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}