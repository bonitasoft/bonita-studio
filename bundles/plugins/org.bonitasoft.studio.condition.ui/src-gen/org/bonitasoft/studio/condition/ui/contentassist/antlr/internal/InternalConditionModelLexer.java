package org.bonitasoft.studio.condition.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalConditionModelLexer extends Lexer {
    public static final int RULE_BOOLEAN=8;
    public static final int RULE_ID=7;
    public static final int RULE_DATE=11;
    public static final int RULE_LONG=5;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=15;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=14;
    public static final int RULE_UTF8_CHARACTERS=9;
    public static final int RULE_DOUBLE=4;
    public static final int RULE_ML_COMMENT=13;
    public static final int T__19=19;
    public static final int RULE_STRING=6;
    public static final int T__16=16;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int RULE_INT=12;
    public static final int RULE_WS=10;

    // delegates
    // delegators

    public InternalConditionModelLexer() {;} 
    public InternalConditionModelLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalConditionModelLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g"; }

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:11:7: ( '<=' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:11:9: '<='
            {
            match("<="); 


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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:12:7: ( '<' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:12:9: '<'
            {
            match('<'); 

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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:13:7: ( '>=' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:13:9: '>='
            {
            match(">="); 


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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:14:7: ( '>' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:14:9: '>'
            {
            match('>'); 

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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:15:7: ( '!=' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:15:9: '!='
            {
            match("!="); 


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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:16:7: ( '==' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:16:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:17:7: ( '!' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:17:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "RULE_UTF8_CHARACTERS"
    public final void mRULE_UTF8_CHARACTERS() throws RecognitionException {
        try {
            int _type = RULE_UTF8_CHARACTERS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2282:22: ( '\\u00C0' .. '\\uFEFF' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2282:24: '\\u00C0' .. '\\uFEFF'
            {
            matchRange('\u00C0','\uFEFF'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_UTF8_CHARACTERS"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2284:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2284:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2284:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
            	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2286:14: ( ( 'true' | 'false' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2286:16: ( 'true' | 'false' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2286:16: ( 'true' | 'false' )
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
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2286:17: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2286:24: 'false'
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2288:11: ( '\\'' (~ ( '\\'' ) )* '\\'' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2288:13: '\\'' (~ ( '\\'' ) )* '\\''
            {
            match('\''); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2288:18: (~ ( '\\'' ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='\u0000' && LA3_0<='&')||(LA3_0>='(' && LA3_0<='\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2288:18: ~ ( '\\'' )
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2290:11: ( ( '-' )? ( '0' .. '9' )+ )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2290:13: ( '-' )? ( '0' .. '9' )+
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2290:13: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2290:13: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2290:18: ( '0' .. '9' )+
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
            	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2290:19: '0' .. '9'
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2292:13: ( ( '-' )? RULE_INT '.' RULE_INT )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2292:15: ( '-' )? RULE_INT '.' RULE_INT
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2292:15: ( '-' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='-') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2292:15: '-'
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2294:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' | RULE_UTF8_CHARACTERS ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | RULE_UTF8_CHARACTERS )* )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2294:11: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | RULE_UTF8_CHARACTERS ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | RULE_UTF8_CHARACTERS )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\uFEFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2294:56: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' | RULE_UTF8_CHARACTERS )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='Z')||LA7_0=='_'||(LA7_0>='a' && LA7_0<='z')||(LA7_0>='\u00C0' && LA7_0<='\uFEFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\uFEFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop7;
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2296:10: ( ( '0' .. '9' )+ )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2296:12: ( '0' .. '9' )+
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2296:12: ( '0' .. '9' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2296:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\"') ) {
                alt11=1;
            }
            else if ( (LA11_0=='\'') ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop9:
                    do {
                        int alt9=3;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0=='\\') ) {
                            alt9=1;
                        }
                        else if ( ((LA9_0>='\u0000' && LA9_0<='!')||(LA9_0>='#' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='\uFFFF')) ) {
                            alt9=2;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:66: ~ ( ( '\\\\' | '\"' ) )
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
                    	    break loop9;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='\\') ) {
                            alt10=1;
                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<='&')||(LA10_0>='(' && LA10_0<='[')||(LA10_0>=']' && LA10_0<='\uFFFF')) ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
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
                    	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2298:137: ~ ( ( '\\\\' | '\\'' ) )
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
                    	    break loop10;
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2300:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2300:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2300:24: ( options {greedy=false; } : . )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='*') ) {
                    int LA12_1 = input.LA(2);

                    if ( (LA12_1=='/') ) {
                        alt12=2;
                    }
                    else if ( ((LA12_1>='\u0000' && LA12_1<='.')||(LA12_1>='0' && LA12_1<='\uFFFF')) ) {
                        alt12=1;
                    }


                }
                else if ( ((LA12_0>='\u0000' && LA12_0<=')')||(LA12_0>='+' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2300:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop12;
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop13;
                }
            } while (true);

            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:40: ( ( '\\r' )? '\\n' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='\n'||LA15_0=='\r') ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:41: ( '\\r' )? '\\n'
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:41: ( '\\r' )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='\r') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2302:41: '\\r'
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
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2304:16: ( . )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2304:18: .
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
        // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:8: ( T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | RULE_UTF8_CHARACTERS | RULE_WS | RULE_BOOLEAN | RULE_DATE | RULE_LONG | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_ANY_OTHER )
        int alt16=19;
        alt16 = dfa16.predict(input);
        switch (alt16) {
            case 1 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:10: T__16
                {
                mT__16(); 

                }
                break;
            case 2 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:16: T__17
                {
                mT__17(); 

                }
                break;
            case 3 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:22: T__18
                {
                mT__18(); 

                }
                break;
            case 4 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:28: T__19
                {
                mT__19(); 

                }
                break;
            case 5 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:34: T__20
                {
                mT__20(); 

                }
                break;
            case 6 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:40: T__21
                {
                mT__21(); 

                }
                break;
            case 7 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:46: T__22
                {
                mT__22(); 

                }
                break;
            case 8 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:52: RULE_UTF8_CHARACTERS
                {
                mRULE_UTF8_CHARACTERS(); 

                }
                break;
            case 9 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:73: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 10 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:81: RULE_BOOLEAN
                {
                mRULE_BOOLEAN(); 

                }
                break;
            case 11 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:94: RULE_DATE
                {
                mRULE_DATE(); 

                }
                break;
            case 12 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:104: RULE_LONG
                {
                mRULE_LONG(); 

                }
                break;
            case 13 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:114: RULE_DOUBLE
                {
                mRULE_DOUBLE(); 

                }
                break;
            case 14 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:126: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 15 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:134: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 16 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:143: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 17 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:155: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 18 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:171: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 19 :
                // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1:187: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\1\uffff\1\21\1\23\1\25\1\17\1\27\1\uffff\2\30\2\17\1\40\1\uffff"+
        "\2\17\13\uffff\2\30\3\uffff\1\40\2\uffff\1\40\3\uffff\2\30\1\52"+
        "\2\uffff\1\55\1\30\1\uffff\1\55";
    static final String DFA16_eofS =
        "\57\uffff";
    static final String DFA16_minS =
        "\1\0\4\75\1\60\1\uffff\1\162\1\141\1\0\1\60\1\56\1\uffff\1\0\1\52"+
        "\13\uffff\1\165\1\154\2\0\1\uffff\1\56\2\uffff\1\56\3\uffff\1\145"+
        "\1\163\2\0\1\uffff\1\60\1\145\1\uffff\1\60";
    static final String DFA16_maxS =
        "\1\uffff\4\75\1\ufeff\1\uffff\1\162\1\141\1\uffff\2\71\1\uffff\1"+
        "\uffff\1\57\13\uffff\1\165\1\154\2\uffff\1\uffff\1\71\2\uffff\1"+
        "\71\3\uffff\1\145\1\163\2\uffff\1\uffff\1\ufeff\1\145\1\uffff\1"+
        "\ufeff";
    static final String DFA16_acceptS =
        "\6\uffff\1\11\5\uffff\1\16\2\uffff\1\23\1\1\1\2\1\3\1\4\1\5\1\7"+
        "\1\6\1\10\1\16\1\11\4\uffff\1\13\1\uffff\1\14\1\15\1\uffff\1\20"+
        "\1\21\1\22\4\uffff\1\13\2\uffff\1\12\1\uffff";
    static final String DFA16_specialS =
        "\1\2\10\uffff\1\0\3\uffff\1\1\16\uffff\1\6\1\5\12\uffff\1\3\1\4"+
        "\5\uffff}>";
    static final String[] DFA16_transitionS = {
            "\11\17\2\6\2\17\1\6\22\17\1\6\1\3\1\15\4\17\1\11\5\17\1\12\1"+
            "\17\1\16\12\13\2\17\1\1\1\4\1\2\2\17\32\14\4\17\1\14\1\17\5"+
            "\14\1\10\15\14\1\7\6\14\105\17\ufe40\5\u0100\17",
            "\1\20",
            "\1\22",
            "\1\24",
            "\1\26",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30\105\uffff\ufe40"+
            "\30",
            "",
            "\1\32",
            "\1\33",
            "\47\35\1\36\64\35\1\34\uffa3\35",
            "\12\37",
            "\1\41\1\uffff\12\42",
            "",
            "\0\43",
            "\1\44\4\uffff\1\45",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\46",
            "\1\47",
            "\42\52\1\51\4\52\1\50\64\52\1\51\5\52\1\51\3\52\1\51\7\52\1"+
            "\51\3\52\1\51\1\52\2\51\uff8a\52",
            "\47\35\1\36\64\35\1\34\uffa3\35",
            "",
            "\1\41\1\uffff\12\37",
            "",
            "",
            "\1\41\1\uffff\12\42",
            "",
            "",
            "",
            "\1\53",
            "\1\54",
            "\0\43",
            "\47\35\1\36\64\35\1\34\uffa3\35",
            "",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30\105\uffff\ufe40"+
            "\30",
            "\1\56",
            "",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30\105\uffff\ufe40"+
            "\30"
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | RULE_UTF8_CHARACTERS | RULE_WS | RULE_BOOLEAN | RULE_DATE | RULE_LONG | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_9 = input.LA(1);

                        s = -1;
                        if ( (LA16_9=='\\') ) {s = 28;}

                        else if ( ((LA16_9>='\u0000' && LA16_9<='&')||(LA16_9>='(' && LA16_9<='[')||(LA16_9>=']' && LA16_9<='\uFFFF')) ) {s = 29;}

                        else if ( (LA16_9=='\'') ) {s = 30;}

                        else s = 15;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA16_13 = input.LA(1);

                        s = -1;
                        if ( ((LA16_13>='\u0000' && LA16_13<='\uFFFF')) ) {s = 35;}

                        else s = 15;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA16_0 = input.LA(1);

                        s = -1;
                        if ( (LA16_0=='<') ) {s = 1;}

                        else if ( (LA16_0=='>') ) {s = 2;}

                        else if ( (LA16_0=='!') ) {s = 3;}

                        else if ( (LA16_0=='=') ) {s = 4;}

                        else if ( ((LA16_0>='\u00C0' && LA16_0<='\uFEFF')) ) {s = 5;}

                        else if ( ((LA16_0>='\t' && LA16_0<='\n')||LA16_0=='\r'||LA16_0==' ') ) {s = 6;}

                        else if ( (LA16_0=='t') ) {s = 7;}

                        else if ( (LA16_0=='f') ) {s = 8;}

                        else if ( (LA16_0=='\'') ) {s = 9;}

                        else if ( (LA16_0=='-') ) {s = 10;}

                        else if ( ((LA16_0>='0' && LA16_0<='9')) ) {s = 11;}

                        else if ( ((LA16_0>='A' && LA16_0<='Z')||LA16_0=='_'||(LA16_0>='a' && LA16_0<='e')||(LA16_0>='g' && LA16_0<='s')||(LA16_0>='u' && LA16_0<='z')) ) {s = 12;}

                        else if ( (LA16_0=='\"') ) {s = 13;}

                        else if ( (LA16_0=='/') ) {s = 14;}

                        else if ( ((LA16_0>='\u0000' && LA16_0<='\b')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='\u001F')||(LA16_0>='#' && LA16_0<='&')||(LA16_0>='(' && LA16_0<=',')||LA16_0=='.'||(LA16_0>=':' && LA16_0<=';')||(LA16_0>='?' && LA16_0<='@')||(LA16_0>='[' && LA16_0<='^')||LA16_0=='`'||(LA16_0>='{' && LA16_0<='\u00BF')||(LA16_0>='\uFF00' && LA16_0<='\uFFFF')) ) {s = 15;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA16_40 = input.LA(1);

                        s = -1;
                        if ( ((LA16_40>='\u0000' && LA16_40<='\uFFFF')) ) {s = 35;}

                        else s = 42;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA16_41 = input.LA(1);

                        s = -1;
                        if ( (LA16_41=='\'') ) {s = 30;}

                        else if ( (LA16_41=='\\') ) {s = 28;}

                        else if ( ((LA16_41>='\u0000' && LA16_41<='&')||(LA16_41>='(' && LA16_41<='[')||(LA16_41>=']' && LA16_41<='\uFFFF')) ) {s = 29;}

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA16_29 = input.LA(1);

                        s = -1;
                        if ( (LA16_29=='\'') ) {s = 30;}

                        else if ( (LA16_29=='\\') ) {s = 28;}

                        else if ( ((LA16_29>='\u0000' && LA16_29<='&')||(LA16_29>='(' && LA16_29<='[')||(LA16_29>=']' && LA16_29<='\uFFFF')) ) {s = 29;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA16_28 = input.LA(1);

                        s = -1;
                        if ( (LA16_28=='\'') ) {s = 40;}

                        else if ( (LA16_28=='\"'||LA16_28=='\\'||LA16_28=='b'||LA16_28=='f'||LA16_28=='n'||LA16_28=='r'||(LA16_28>='t' && LA16_28<='u')) ) {s = 41;}

                        else if ( ((LA16_28>='\u0000' && LA16_28<='!')||(LA16_28>='#' && LA16_28<='&')||(LA16_28>='(' && LA16_28<='[')||(LA16_28>=']' && LA16_28<='a')||(LA16_28>='c' && LA16_28<='e')||(LA16_28>='g' && LA16_28<='m')||(LA16_28>='o' && LA16_28<='q')||LA16_28=='s'||(LA16_28>='v' && LA16_28<='\uFFFF')) ) {s = 42;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}