package org.bonitasoft.studio.condition.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.bonitasoft.studio.condition.services.ConditionModelGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalConditionModelParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_DOUBLE", "RULE_LONG", "RULE_STRING", "RULE_ID", "RULE_BOOLEAN", "RULE_UTF8_CHARACTERS", "RULE_WS", "RULE_DATE", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_ANY_OTHER", "'<='", "'<'", "'>='", "'>'", "'!='", "'=='", "'!'"
    };
    public static final int RULE_BOOLEAN=8;
    public static final int RULE_ID=7;
    public static final int RULE_DATE=11;
    public static final int RULE_LONG=5;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=15;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_SL_COMMENT=14;
    public static final int EOF=-1;
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


        public InternalConditionModelParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalConditionModelParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalConditionModelParser.tokenNames; }
    public String getGrammarFileName() { return "../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g"; }



     	private ConditionModelGrammarAccess grammarAccess;
     	
        public InternalConditionModelParser(TokenStream input, ConditionModelGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Operation_Compare";	
       	}
       	
       	@Override
       	protected ConditionModelGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleOperation_Compare"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:67:1: entryRuleOperation_Compare returns [EObject current=null] : iv_ruleOperation_Compare= ruleOperation_Compare EOF ;
    public final EObject entryRuleOperation_Compare() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Compare = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:68:2: (iv_ruleOperation_Compare= ruleOperation_Compare EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:69:2: iv_ruleOperation_Compare= ruleOperation_Compare EOF
            {
             newCompositeNode(grammarAccess.getOperation_CompareRule()); 
            pushFollow(FOLLOW_ruleOperation_Compare_in_entryRuleOperation_Compare75);
            iv_ruleOperation_Compare=ruleOperation_Compare();

            state._fsp--;

             current =iv_ruleOperation_Compare; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Compare85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Compare"


    // $ANTLR start "ruleOperation_Compare"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:76:1: ruleOperation_Compare returns [EObject current=null] : ( () ( ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) ) ) ) ;
    public final EObject ruleOperation_Compare() throws RecognitionException {
        EObject current = null;

        EObject lv_op_1_1 = null;

        EObject lv_op_1_2 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:79:28: ( ( () ( ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:80:1: ( () ( ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:80:1: ( () ( ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:80:2: () ( ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:80:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:81:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_CompareAccess().getOperation_CompareAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:86:2: ( ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:87:1: ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:87:1: ( (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:88:1: (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:88:1: (lv_op_1_1= ruleOperation | lv_op_1_2= ruleUnary_Operation )
            int alt1=2;
            switch ( input.LA(1) ) {
            case RULE_DOUBLE:
                {
                int LA1_1 = input.LA(2);

                if ( ((LA1_1>=16 && LA1_1<=21)) ) {
                    alt1=1;
                }
                else if ( (LA1_1==EOF) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_LONG:
                {
                int LA1_2 = input.LA(2);

                if ( ((LA1_2>=16 && LA1_2<=21)) ) {
                    alt1=1;
                }
                else if ( (LA1_2==EOF) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_BOOLEAN:
                {
                int LA1_3 = input.LA(2);

                if ( ((LA1_3>=16 && LA1_3<=21)) ) {
                    alt1=1;
                }
                else if ( (LA1_3==EOF) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 3, input);

                    throw nvae;
                }
                }
                break;
            case RULE_STRING:
                {
                int LA1_4 = input.LA(2);

                if ( ((LA1_4>=16 && LA1_4<=21)) ) {
                    alt1=1;
                }
                else if ( (LA1_4==EOF) ) {
                    alt1=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 4, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ID:
                {
                int LA1_5 = input.LA(2);

                if ( (LA1_5==EOF) ) {
                    alt1=2;
                }
                else if ( ((LA1_5>=16 && LA1_5<=21)) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 5, input);

                    throw nvae;
                }
                }
                break;
            case 22:
                {
                alt1=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:89:3: lv_op_1_1= ruleOperation
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperation_CompareAccess().getOpOperationParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleOperation_in_ruleOperation_Compare142);
                    lv_op_1_1=ruleOperation();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperation_CompareRule());
                    	        }
                           		set(
                           			current, 
                           			"op",
                            		lv_op_1_1, 
                            		"Operation");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:104:8: lv_op_1_2= ruleUnary_Operation
                    {
                     
                    	        newCompositeNode(grammarAccess.getOperation_CompareAccess().getOpUnary_OperationParserRuleCall_1_0_1()); 
                    	    
                    pushFollow(FOLLOW_ruleUnary_Operation_in_ruleOperation_Compare161);
                    lv_op_1_2=ruleUnary_Operation();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOperation_CompareRule());
                    	        }
                           		set(
                           			current, 
                           			"op",
                            		lv_op_1_2, 
                            		"Unary_Operation");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }
                    break;

            }


            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Compare"


    // $ANTLR start "entryRuleUnary_Operation"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:130:1: entryRuleUnary_Operation returns [EObject current=null] : iv_ruleUnary_Operation= ruleUnary_Operation EOF ;
    public final EObject entryRuleUnary_Operation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUnary_Operation = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:131:2: (iv_ruleUnary_Operation= ruleUnary_Operation EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:132:2: iv_ruleUnary_Operation= ruleUnary_Operation EOF
            {
             newCompositeNode(grammarAccess.getUnary_OperationRule()); 
            pushFollow(FOLLOW_ruleUnary_Operation_in_entryRuleUnary_Operation200);
            iv_ruleUnary_Operation=ruleUnary_Operation();

            state._fsp--;

             current =iv_ruleUnary_Operation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnary_Operation210); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUnary_Operation"


    // $ANTLR start "ruleUnary_Operation"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:139:1: ruleUnary_Operation returns [EObject current=null] : (this_Operation_Unary_0= ruleOperation_Unary | this_Operation_NotUnary_1= ruleOperation_NotUnary ) ;
    public final EObject ruleUnary_Operation() throws RecognitionException {
        EObject current = null;

        EObject this_Operation_Unary_0 = null;

        EObject this_Operation_NotUnary_1 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:142:28: ( (this_Operation_Unary_0= ruleOperation_Unary | this_Operation_NotUnary_1= ruleOperation_NotUnary ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:143:1: (this_Operation_Unary_0= ruleOperation_Unary | this_Operation_NotUnary_1= ruleOperation_NotUnary )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:143:1: (this_Operation_Unary_0= ruleOperation_Unary | this_Operation_NotUnary_1= ruleOperation_NotUnary )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>=RULE_DOUBLE && LA2_0<=RULE_BOOLEAN)) ) {
                alt2=1;
            }
            else if ( (LA2_0==22) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:144:5: this_Operation_Unary_0= ruleOperation_Unary
                    {
                     
                            newCompositeNode(grammarAccess.getUnary_OperationAccess().getOperation_UnaryParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleOperation_Unary_in_ruleUnary_Operation257);
                    this_Operation_Unary_0=ruleOperation_Unary();

                    state._fsp--;

                     
                            current = this_Operation_Unary_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:154:5: this_Operation_NotUnary_1= ruleOperation_NotUnary
                    {
                     
                            newCompositeNode(grammarAccess.getUnary_OperationAccess().getOperation_NotUnaryParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleOperation_NotUnary_in_ruleUnary_Operation284);
                    this_Operation_NotUnary_1=ruleOperation_NotUnary();

                    state._fsp--;

                     
                            current = this_Operation_NotUnary_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUnary_Operation"


    // $ANTLR start "entryRuleOperation"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:170:1: entryRuleOperation returns [EObject current=null] : iv_ruleOperation= ruleOperation EOF ;
    public final EObject entryRuleOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:171:2: (iv_ruleOperation= ruleOperation EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:172:2: iv_ruleOperation= ruleOperation EOF
            {
             newCompositeNode(grammarAccess.getOperationRule()); 
            pushFollow(FOLLOW_ruleOperation_in_entryRuleOperation319);
            iv_ruleOperation=ruleOperation();

            state._fsp--;

             current =iv_ruleOperation; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation329); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation"


    // $ANTLR start "ruleOperation"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:179:1: ruleOperation returns [EObject current=null] : (this_Operation_Less_Equals_0= ruleOperation_Less_Equals | this_Operation_Less_1= ruleOperation_Less | this_Operation_Greater_Equals_2= ruleOperation_Greater_Equals | this_Operation_Greater_3= ruleOperation_Greater | this_Operation_Not_Equals_4= ruleOperation_Not_Equals | this_Operation_Equals_5= ruleOperation_Equals ) ;
    public final EObject ruleOperation() throws RecognitionException {
        EObject current = null;

        EObject this_Operation_Less_Equals_0 = null;

        EObject this_Operation_Less_1 = null;

        EObject this_Operation_Greater_Equals_2 = null;

        EObject this_Operation_Greater_3 = null;

        EObject this_Operation_Not_Equals_4 = null;

        EObject this_Operation_Equals_5 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:182:28: ( (this_Operation_Less_Equals_0= ruleOperation_Less_Equals | this_Operation_Less_1= ruleOperation_Less | this_Operation_Greater_Equals_2= ruleOperation_Greater_Equals | this_Operation_Greater_3= ruleOperation_Greater | this_Operation_Not_Equals_4= ruleOperation_Not_Equals | this_Operation_Equals_5= ruleOperation_Equals ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:183:1: (this_Operation_Less_Equals_0= ruleOperation_Less_Equals | this_Operation_Less_1= ruleOperation_Less | this_Operation_Greater_Equals_2= ruleOperation_Greater_Equals | this_Operation_Greater_3= ruleOperation_Greater | this_Operation_Not_Equals_4= ruleOperation_Not_Equals | this_Operation_Equals_5= ruleOperation_Equals )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:183:1: (this_Operation_Less_Equals_0= ruleOperation_Less_Equals | this_Operation_Less_1= ruleOperation_Less | this_Operation_Greater_Equals_2= ruleOperation_Greater_Equals | this_Operation_Greater_3= ruleOperation_Greater | this_Operation_Not_Equals_4= ruleOperation_Not_Equals | this_Operation_Equals_5= ruleOperation_Equals )
            int alt3=6;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:184:5: this_Operation_Less_Equals_0= ruleOperation_Less_Equals
                    {
                     
                            newCompositeNode(grammarAccess.getOperationAccess().getOperation_Less_EqualsParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleOperation_Less_Equals_in_ruleOperation376);
                    this_Operation_Less_Equals_0=ruleOperation_Less_Equals();

                    state._fsp--;

                     
                            current = this_Operation_Less_Equals_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:194:5: this_Operation_Less_1= ruleOperation_Less
                    {
                     
                            newCompositeNode(grammarAccess.getOperationAccess().getOperation_LessParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleOperation_Less_in_ruleOperation403);
                    this_Operation_Less_1=ruleOperation_Less();

                    state._fsp--;

                     
                            current = this_Operation_Less_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:204:5: this_Operation_Greater_Equals_2= ruleOperation_Greater_Equals
                    {
                     
                            newCompositeNode(grammarAccess.getOperationAccess().getOperation_Greater_EqualsParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleOperation_Greater_Equals_in_ruleOperation430);
                    this_Operation_Greater_Equals_2=ruleOperation_Greater_Equals();

                    state._fsp--;

                     
                            current = this_Operation_Greater_Equals_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:214:5: this_Operation_Greater_3= ruleOperation_Greater
                    {
                     
                            newCompositeNode(grammarAccess.getOperationAccess().getOperation_GreaterParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleOperation_Greater_in_ruleOperation457);
                    this_Operation_Greater_3=ruleOperation_Greater();

                    state._fsp--;

                     
                            current = this_Operation_Greater_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:224:5: this_Operation_Not_Equals_4= ruleOperation_Not_Equals
                    {
                     
                            newCompositeNode(grammarAccess.getOperationAccess().getOperation_Not_EqualsParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleOperation_Not_Equals_in_ruleOperation484);
                    this_Operation_Not_Equals_4=ruleOperation_Not_Equals();

                    state._fsp--;

                     
                            current = this_Operation_Not_Equals_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:234:5: this_Operation_Equals_5= ruleOperation_Equals
                    {
                     
                            newCompositeNode(grammarAccess.getOperationAccess().getOperation_EqualsParserRuleCall_5()); 
                        
                    pushFollow(FOLLOW_ruleOperation_Equals_in_ruleOperation511);
                    this_Operation_Equals_5=ruleOperation_Equals();

                    state._fsp--;

                     
                            current = this_Operation_Equals_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation"


    // $ANTLR start "entryRuleOperation_Less_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:250:1: entryRuleOperation_Less_Equals returns [EObject current=null] : iv_ruleOperation_Less_Equals= ruleOperation_Less_Equals EOF ;
    public final EObject entryRuleOperation_Less_Equals() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Less_Equals = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:251:2: (iv_ruleOperation_Less_Equals= ruleOperation_Less_Equals EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:252:2: iv_ruleOperation_Less_Equals= ruleOperation_Less_Equals EOF
            {
             newCompositeNode(grammarAccess.getOperation_Less_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Less_Equals_in_entryRuleOperation_Less_Equals546);
            iv_ruleOperation_Less_Equals=ruleOperation_Less_Equals();

            state._fsp--;

             current =iv_ruleOperation_Less_Equals; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Less_Equals556); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Less_Equals"


    // $ANTLR start "ruleOperation_Less_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:259:1: ruleOperation_Less_Equals returns [EObject current=null] : ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<=' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_Less_Equals() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:262:28: ( ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<=' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:263:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<=' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:263:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<=' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:263:2: () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<=' ( (lv_right_3_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:263:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:264:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_Less_EqualsAccess().getOperation_Less_EqualsAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:269:2: ( (lv_left_1_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:270:1: (lv_left_1_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:270:1: (lv_left_1_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:271:3: lv_left_1_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_Less_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less_Equals611);
            lv_left_1_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_Less_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"left",
                    		lv_left_1_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleOperation_Less_Equals623); 

                	newLeafNode(otherlv_2, grammarAccess.getOperation_Less_EqualsAccess().getLessThanSignEqualsSignKeyword_2());
                
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:291:1: ( (lv_right_3_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:292:1: (lv_right_3_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:292:1: (lv_right_3_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:293:3: lv_right_3_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_Less_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less_Equals644);
            lv_right_3_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_Less_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"right",
                    		lv_right_3_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Less_Equals"


    // $ANTLR start "entryRuleOperation_Less"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:317:1: entryRuleOperation_Less returns [EObject current=null] : iv_ruleOperation_Less= ruleOperation_Less EOF ;
    public final EObject entryRuleOperation_Less() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Less = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:318:2: (iv_ruleOperation_Less= ruleOperation_Less EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:319:2: iv_ruleOperation_Less= ruleOperation_Less EOF
            {
             newCompositeNode(grammarAccess.getOperation_LessRule()); 
            pushFollow(FOLLOW_ruleOperation_Less_in_entryRuleOperation_Less680);
            iv_ruleOperation_Less=ruleOperation_Less();

            state._fsp--;

             current =iv_ruleOperation_Less; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Less690); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Less"


    // $ANTLR start "ruleOperation_Less"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:326:1: ruleOperation_Less returns [EObject current=null] : ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_Less() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:329:28: ( ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:330:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:330:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:330:2: () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '<' ( (lv_right_3_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:330:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:331:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_LessAccess().getOperation_LessAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:336:2: ( (lv_left_1_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:337:1: (lv_left_1_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:337:1: (lv_left_1_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:338:3: lv_left_1_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_LessAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less745);
            lv_left_1_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_LessRule());
            	        }
                   		set(
                   			current, 
                   			"left",
                    		lv_left_1_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_17_in_ruleOperation_Less757); 

                	newLeafNode(otherlv_2, grammarAccess.getOperation_LessAccess().getLessThanSignKeyword_2());
                
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:358:1: ( (lv_right_3_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:359:1: (lv_right_3_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:359:1: (lv_right_3_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:360:3: lv_right_3_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_LessAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less778);
            lv_right_3_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_LessRule());
            	        }
                   		set(
                   			current, 
                   			"right",
                    		lv_right_3_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Less"


    // $ANTLR start "entryRuleOperation_Greater_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:384:1: entryRuleOperation_Greater_Equals returns [EObject current=null] : iv_ruleOperation_Greater_Equals= ruleOperation_Greater_Equals EOF ;
    public final EObject entryRuleOperation_Greater_Equals() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Greater_Equals = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:385:2: (iv_ruleOperation_Greater_Equals= ruleOperation_Greater_Equals EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:386:2: iv_ruleOperation_Greater_Equals= ruleOperation_Greater_Equals EOF
            {
             newCompositeNode(grammarAccess.getOperation_Greater_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Greater_Equals_in_entryRuleOperation_Greater_Equals814);
            iv_ruleOperation_Greater_Equals=ruleOperation_Greater_Equals();

            state._fsp--;

             current =iv_ruleOperation_Greater_Equals; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Greater_Equals824); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Greater_Equals"


    // $ANTLR start "ruleOperation_Greater_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:393:1: ruleOperation_Greater_Equals returns [EObject current=null] : ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>=' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_Greater_Equals() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:396:28: ( ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>=' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:397:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>=' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:397:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>=' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:397:2: () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>=' ( (lv_right_3_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:397:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:398:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_Greater_EqualsAccess().getOperation_Greater_EqualsAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:403:2: ( (lv_left_1_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:404:1: (lv_left_1_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:404:1: (lv_left_1_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:405:3: lv_left_1_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_Greater_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater_Equals879);
            lv_left_1_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_Greater_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"left",
                    		lv_left_1_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleOperation_Greater_Equals891); 

                	newLeafNode(otherlv_2, grammarAccess.getOperation_Greater_EqualsAccess().getGreaterThanSignEqualsSignKeyword_2());
                
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:425:1: ( (lv_right_3_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:426:1: (lv_right_3_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:426:1: (lv_right_3_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:427:3: lv_right_3_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_Greater_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater_Equals912);
            lv_right_3_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_Greater_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"right",
                    		lv_right_3_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Greater_Equals"


    // $ANTLR start "entryRuleOperation_Greater"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:451:1: entryRuleOperation_Greater returns [EObject current=null] : iv_ruleOperation_Greater= ruleOperation_Greater EOF ;
    public final EObject entryRuleOperation_Greater() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Greater = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:452:2: (iv_ruleOperation_Greater= ruleOperation_Greater EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:453:2: iv_ruleOperation_Greater= ruleOperation_Greater EOF
            {
             newCompositeNode(grammarAccess.getOperation_GreaterRule()); 
            pushFollow(FOLLOW_ruleOperation_Greater_in_entryRuleOperation_Greater948);
            iv_ruleOperation_Greater=ruleOperation_Greater();

            state._fsp--;

             current =iv_ruleOperation_Greater; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Greater958); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Greater"


    // $ANTLR start "ruleOperation_Greater"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:460:1: ruleOperation_Greater returns [EObject current=null] : ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_Greater() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:463:28: ( ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:464:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:464:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:464:2: () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '>' ( (lv_right_3_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:464:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:465:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_GreaterAccess().getOperation_GreaterAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:470:2: ( (lv_left_1_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:471:1: (lv_left_1_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:471:1: (lv_left_1_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:472:3: lv_left_1_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_GreaterAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater1013);
            lv_left_1_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_GreaterRule());
            	        }
                   		set(
                   			current, 
                   			"left",
                    		lv_left_1_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,19,FOLLOW_19_in_ruleOperation_Greater1025); 

                	newLeafNode(otherlv_2, grammarAccess.getOperation_GreaterAccess().getGreaterThanSignKeyword_2());
                
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:492:1: ( (lv_right_3_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:493:1: (lv_right_3_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:493:1: (lv_right_3_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:494:3: lv_right_3_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_GreaterAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater1046);
            lv_right_3_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_GreaterRule());
            	        }
                   		set(
                   			current, 
                   			"right",
                    		lv_right_3_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Greater"


    // $ANTLR start "entryRuleOperation_Not_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:518:1: entryRuleOperation_Not_Equals returns [EObject current=null] : iv_ruleOperation_Not_Equals= ruleOperation_Not_Equals EOF ;
    public final EObject entryRuleOperation_Not_Equals() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Not_Equals = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:519:2: (iv_ruleOperation_Not_Equals= ruleOperation_Not_Equals EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:520:2: iv_ruleOperation_Not_Equals= ruleOperation_Not_Equals EOF
            {
             newCompositeNode(grammarAccess.getOperation_Not_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Not_Equals_in_entryRuleOperation_Not_Equals1082);
            iv_ruleOperation_Not_Equals=ruleOperation_Not_Equals();

            state._fsp--;

             current =iv_ruleOperation_Not_Equals; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Not_Equals1092); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Not_Equals"


    // $ANTLR start "ruleOperation_Not_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:527:1: ruleOperation_Not_Equals returns [EObject current=null] : ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '!=' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_Not_Equals() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:530:28: ( ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '!=' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:531:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '!=' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:531:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '!=' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:531:2: () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '!=' ( (lv_right_3_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:531:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:532:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_Not_EqualsAccess().getOperation_Not_EqualsAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:537:2: ( (lv_left_1_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:538:1: (lv_left_1_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:538:1: (lv_left_1_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:539:3: lv_left_1_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_Not_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Not_Equals1147);
            lv_left_1_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_Not_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"left",
                    		lv_left_1_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleOperation_Not_Equals1159); 

                	newLeafNode(otherlv_2, grammarAccess.getOperation_Not_EqualsAccess().getExclamationMarkEqualsSignKeyword_2());
                
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:559:1: ( (lv_right_3_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:560:1: (lv_right_3_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:560:1: (lv_right_3_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:561:3: lv_right_3_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_Not_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Not_Equals1180);
            lv_right_3_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_Not_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"right",
                    		lv_right_3_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Not_Equals"


    // $ANTLR start "entryRuleOperation_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:585:1: entryRuleOperation_Equals returns [EObject current=null] : iv_ruleOperation_Equals= ruleOperation_Equals EOF ;
    public final EObject entryRuleOperation_Equals() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Equals = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:586:2: (iv_ruleOperation_Equals= ruleOperation_Equals EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:587:2: iv_ruleOperation_Equals= ruleOperation_Equals EOF
            {
             newCompositeNode(grammarAccess.getOperation_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Equals_in_entryRuleOperation_Equals1216);
            iv_ruleOperation_Equals=ruleOperation_Equals();

            state._fsp--;

             current =iv_ruleOperation_Equals; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Equals1226); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Equals"


    // $ANTLR start "ruleOperation_Equals"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:594:1: ruleOperation_Equals returns [EObject current=null] : ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '==' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_Equals() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject lv_left_1_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:597:28: ( ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '==' ( (lv_right_3_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:598:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '==' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:598:1: ( () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '==' ( (lv_right_3_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:598:2: () ( (lv_left_1_0= ruleExpression_Terminal ) ) otherlv_2= '==' ( (lv_right_3_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:598:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:599:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_EqualsAccess().getOperation_EqualsAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:604:2: ( (lv_left_1_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:605:1: (lv_left_1_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:605:1: (lv_left_1_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:606:3: lv_left_1_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Equals1281);
            lv_left_1_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"left",
                    		lv_left_1_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleOperation_Equals1293); 

                	newLeafNode(otherlv_2, grammarAccess.getOperation_EqualsAccess().getEqualsSignEqualsSignKeyword_2());
                
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:626:1: ( (lv_right_3_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:627:1: (lv_right_3_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:627:1: (lv_right_3_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:628:3: lv_right_3_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Equals1314);
            lv_right_3_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_EqualsRule());
            	        }
                   		set(
                   			current, 
                   			"right",
                    		lv_right_3_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Equals"


    // $ANTLR start "entryRuleOperation_Unary"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:652:1: entryRuleOperation_Unary returns [EObject current=null] : iv_ruleOperation_Unary= ruleOperation_Unary EOF ;
    public final EObject entryRuleOperation_Unary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_Unary = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:653:2: (iv_ruleOperation_Unary= ruleOperation_Unary EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:654:2: iv_ruleOperation_Unary= ruleOperation_Unary EOF
            {
             newCompositeNode(grammarAccess.getOperation_UnaryRule()); 
            pushFollow(FOLLOW_ruleOperation_Unary_in_entryRuleOperation_Unary1350);
            iv_ruleOperation_Unary=ruleOperation_Unary();

            state._fsp--;

             current =iv_ruleOperation_Unary; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Unary1360); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_Unary"


    // $ANTLR start "ruleOperation_Unary"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:661:1: ruleOperation_Unary returns [EObject current=null] : ( () ( (lv_value_1_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_Unary() throws RecognitionException {
        EObject current = null;

        EObject lv_value_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:664:28: ( ( () ( (lv_value_1_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:665:1: ( () ( (lv_value_1_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:665:1: ( () ( (lv_value_1_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:665:2: () ( (lv_value_1_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:665:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:666:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_UnaryAccess().getOperation_UnaryAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:671:2: ( (lv_value_1_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:672:1: (lv_value_1_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:672:1: (lv_value_1_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:673:3: lv_value_1_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_UnaryAccess().getValueExpression_TerminalParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_Unary1415);
            lv_value_1_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_UnaryRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_1_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_Unary"


    // $ANTLR start "entryRuleOperation_NotUnary"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:697:1: entryRuleOperation_NotUnary returns [EObject current=null] : iv_ruleOperation_NotUnary= ruleOperation_NotUnary EOF ;
    public final EObject entryRuleOperation_NotUnary() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation_NotUnary = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:698:2: (iv_ruleOperation_NotUnary= ruleOperation_NotUnary EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:699:2: iv_ruleOperation_NotUnary= ruleOperation_NotUnary EOF
            {
             newCompositeNode(grammarAccess.getOperation_NotUnaryRule()); 
            pushFollow(FOLLOW_ruleOperation_NotUnary_in_entryRuleOperation_NotUnary1451);
            iv_ruleOperation_NotUnary=ruleOperation_NotUnary();

            state._fsp--;

             current =iv_ruleOperation_NotUnary; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_NotUnary1461); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation_NotUnary"


    // $ANTLR start "ruleOperation_NotUnary"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:706:1: ruleOperation_NotUnary returns [EObject current=null] : ( () otherlv_1= '!' ( (lv_value_2_0= ruleExpression_Terminal ) ) ) ;
    public final EObject ruleOperation_NotUnary() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_value_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:709:28: ( ( () otherlv_1= '!' ( (lv_value_2_0= ruleExpression_Terminal ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:710:1: ( () otherlv_1= '!' ( (lv_value_2_0= ruleExpression_Terminal ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:710:1: ( () otherlv_1= '!' ( (lv_value_2_0= ruleExpression_Terminal ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:710:2: () otherlv_1= '!' ( (lv_value_2_0= ruleExpression_Terminal ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:710:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:711:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOperation_NotUnaryAccess().getOperation_NotUnaryAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,22,FOLLOW_22_in_ruleOperation_NotUnary1507); 

                	newLeafNode(otherlv_1, grammarAccess.getOperation_NotUnaryAccess().getExclamationMarkKeyword_1());
                
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:720:1: ( (lv_value_2_0= ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:721:1: (lv_value_2_0= ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:721:1: (lv_value_2_0= ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:722:3: lv_value_2_0= ruleExpression_Terminal
            {
             
            	        newCompositeNode(grammarAccess.getOperation_NotUnaryAccess().getValueExpression_TerminalParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression_Terminal_in_ruleOperation_NotUnary1528);
            lv_value_2_0=ruleExpression_Terminal();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOperation_NotUnaryRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_2_0, 
                    		"Expression_Terminal");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation_NotUnary"


    // $ANTLR start "entryRuleExpression_Terminal"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:746:1: entryRuleExpression_Terminal returns [EObject current=null] : iv_ruleExpression_Terminal= ruleExpression_Terminal EOF ;
    public final EObject entryRuleExpression_Terminal() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression_Terminal = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:747:2: (iv_ruleExpression_Terminal= ruleExpression_Terminal EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:748:2: iv_ruleExpression_Terminal= ruleExpression_Terminal EOF
            {
             newCompositeNode(grammarAccess.getExpression_TerminalRule()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_entryRuleExpression_Terminal1564);
            iv_ruleExpression_Terminal=ruleExpression_Terminal();

            state._fsp--;

             current =iv_ruleExpression_Terminal; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Terminal1574); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression_Terminal"


    // $ANTLR start "ruleExpression_Terminal"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:755:1: ruleExpression_Terminal returns [EObject current=null] : (this_Expression_Double_0= ruleExpression_Double | this_Expression_Integer_1= ruleExpression_Integer | this_Expression_Boolean_2= ruleExpression_Boolean | this_Expression_String_3= ruleExpression_String | this_Expression_ProcessRef_4= ruleExpression_ProcessRef ) ;
    public final EObject ruleExpression_Terminal() throws RecognitionException {
        EObject current = null;

        EObject this_Expression_Double_0 = null;

        EObject this_Expression_Integer_1 = null;

        EObject this_Expression_Boolean_2 = null;

        EObject this_Expression_String_3 = null;

        EObject this_Expression_ProcessRef_4 = null;


         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:758:28: ( (this_Expression_Double_0= ruleExpression_Double | this_Expression_Integer_1= ruleExpression_Integer | this_Expression_Boolean_2= ruleExpression_Boolean | this_Expression_String_3= ruleExpression_String | this_Expression_ProcessRef_4= ruleExpression_ProcessRef ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:759:1: (this_Expression_Double_0= ruleExpression_Double | this_Expression_Integer_1= ruleExpression_Integer | this_Expression_Boolean_2= ruleExpression_Boolean | this_Expression_String_3= ruleExpression_String | this_Expression_ProcessRef_4= ruleExpression_ProcessRef )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:759:1: (this_Expression_Double_0= ruleExpression_Double | this_Expression_Integer_1= ruleExpression_Integer | this_Expression_Boolean_2= ruleExpression_Boolean | this_Expression_String_3= ruleExpression_String | this_Expression_ProcessRef_4= ruleExpression_ProcessRef )
            int alt4=5;
            switch ( input.LA(1) ) {
            case RULE_DOUBLE:
                {
                alt4=1;
                }
                break;
            case RULE_LONG:
                {
                alt4=2;
                }
                break;
            case RULE_BOOLEAN:
                {
                alt4=3;
                }
                break;
            case RULE_STRING:
                {
                alt4=4;
                }
                break;
            case RULE_ID:
                {
                alt4=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:760:5: this_Expression_Double_0= ruleExpression_Double
                    {
                     
                            newCompositeNode(grammarAccess.getExpression_TerminalAccess().getExpression_DoubleParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleExpression_Double_in_ruleExpression_Terminal1621);
                    this_Expression_Double_0=ruleExpression_Double();

                    state._fsp--;

                     
                            current = this_Expression_Double_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:770:5: this_Expression_Integer_1= ruleExpression_Integer
                    {
                     
                            newCompositeNode(grammarAccess.getExpression_TerminalAccess().getExpression_IntegerParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleExpression_Integer_in_ruleExpression_Terminal1648);
                    this_Expression_Integer_1=ruleExpression_Integer();

                    state._fsp--;

                     
                            current = this_Expression_Integer_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:780:5: this_Expression_Boolean_2= ruleExpression_Boolean
                    {
                     
                            newCompositeNode(grammarAccess.getExpression_TerminalAccess().getExpression_BooleanParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleExpression_Boolean_in_ruleExpression_Terminal1675);
                    this_Expression_Boolean_2=ruleExpression_Boolean();

                    state._fsp--;

                     
                            current = this_Expression_Boolean_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:790:5: this_Expression_String_3= ruleExpression_String
                    {
                     
                            newCompositeNode(grammarAccess.getExpression_TerminalAccess().getExpression_StringParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleExpression_String_in_ruleExpression_Terminal1702);
                    this_Expression_String_3=ruleExpression_String();

                    state._fsp--;

                     
                            current = this_Expression_String_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:800:5: this_Expression_ProcessRef_4= ruleExpression_ProcessRef
                    {
                     
                            newCompositeNode(grammarAccess.getExpression_TerminalAccess().getExpression_ProcessRefParserRuleCall_4()); 
                        
                    pushFollow(FOLLOW_ruleExpression_ProcessRef_in_ruleExpression_Terminal1729);
                    this_Expression_ProcessRef_4=ruleExpression_ProcessRef();

                    state._fsp--;

                     
                            current = this_Expression_ProcessRef_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression_Terminal"


    // $ANTLR start "entryRuleExpression_Double"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:816:1: entryRuleExpression_Double returns [EObject current=null] : iv_ruleExpression_Double= ruleExpression_Double EOF ;
    public final EObject entryRuleExpression_Double() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression_Double = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:817:2: (iv_ruleExpression_Double= ruleExpression_Double EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:818:2: iv_ruleExpression_Double= ruleExpression_Double EOF
            {
             newCompositeNode(grammarAccess.getExpression_DoubleRule()); 
            pushFollow(FOLLOW_ruleExpression_Double_in_entryRuleExpression_Double1764);
            iv_ruleExpression_Double=ruleExpression_Double();

            state._fsp--;

             current =iv_ruleExpression_Double; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Double1774); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression_Double"


    // $ANTLR start "ruleExpression_Double"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:825:1: ruleExpression_Double returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleExpression_Double() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:828:28: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:829:1: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:829:1: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:829:2: () ( (lv_value_1_0= RULE_DOUBLE ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:829:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:830:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpression_DoubleAccess().getExpression_DoubleAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:835:2: ( (lv_value_1_0= RULE_DOUBLE ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:836:1: (lv_value_1_0= RULE_DOUBLE )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:836:1: (lv_value_1_0= RULE_DOUBLE )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:837:3: lv_value_1_0= RULE_DOUBLE
            {
            lv_value_1_0=(Token)match(input,RULE_DOUBLE,FOLLOW_RULE_DOUBLE_in_ruleExpression_Double1825); 

            			newLeafNode(lv_value_1_0, grammarAccess.getExpression_DoubleAccess().getValueDOUBLETerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getExpression_DoubleRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_1_0, 
                    		"DOUBLE");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression_Double"


    // $ANTLR start "entryRuleExpression_Integer"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:861:1: entryRuleExpression_Integer returns [EObject current=null] : iv_ruleExpression_Integer= ruleExpression_Integer EOF ;
    public final EObject entryRuleExpression_Integer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression_Integer = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:862:2: (iv_ruleExpression_Integer= ruleExpression_Integer EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:863:2: iv_ruleExpression_Integer= ruleExpression_Integer EOF
            {
             newCompositeNode(grammarAccess.getExpression_IntegerRule()); 
            pushFollow(FOLLOW_ruleExpression_Integer_in_entryRuleExpression_Integer1866);
            iv_ruleExpression_Integer=ruleExpression_Integer();

            state._fsp--;

             current =iv_ruleExpression_Integer; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Integer1876); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression_Integer"


    // $ANTLR start "ruleExpression_Integer"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:870:1: ruleExpression_Integer returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_LONG ) ) ) ;
    public final EObject ruleExpression_Integer() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:873:28: ( ( () ( (lv_value_1_0= RULE_LONG ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:874:1: ( () ( (lv_value_1_0= RULE_LONG ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:874:1: ( () ( (lv_value_1_0= RULE_LONG ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:874:2: () ( (lv_value_1_0= RULE_LONG ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:874:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:875:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpression_IntegerAccess().getExpression_IntegerAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:880:2: ( (lv_value_1_0= RULE_LONG ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:881:1: (lv_value_1_0= RULE_LONG )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:881:1: (lv_value_1_0= RULE_LONG )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:882:3: lv_value_1_0= RULE_LONG
            {
            lv_value_1_0=(Token)match(input,RULE_LONG,FOLLOW_RULE_LONG_in_ruleExpression_Integer1927); 

            			newLeafNode(lv_value_1_0, grammarAccess.getExpression_IntegerAccess().getValueLONGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getExpression_IntegerRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_1_0, 
                    		"LONG");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression_Integer"


    // $ANTLR start "entryRuleExpression_String"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:906:1: entryRuleExpression_String returns [EObject current=null] : iv_ruleExpression_String= ruleExpression_String EOF ;
    public final EObject entryRuleExpression_String() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression_String = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:907:2: (iv_ruleExpression_String= ruleExpression_String EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:908:2: iv_ruleExpression_String= ruleExpression_String EOF
            {
             newCompositeNode(grammarAccess.getExpression_StringRule()); 
            pushFollow(FOLLOW_ruleExpression_String_in_entryRuleExpression_String1968);
            iv_ruleExpression_String=ruleExpression_String();

            state._fsp--;

             current =iv_ruleExpression_String; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_String1978); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression_String"


    // $ANTLR start "ruleExpression_String"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:915:1: ruleExpression_String returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleExpression_String() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:918:28: ( ( () ( (lv_value_1_0= RULE_STRING ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:919:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:919:1: ( () ( (lv_value_1_0= RULE_STRING ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:919:2: () ( (lv_value_1_0= RULE_STRING ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:919:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:920:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpression_StringAccess().getExpression_StringAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:925:2: ( (lv_value_1_0= RULE_STRING ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:926:1: (lv_value_1_0= RULE_STRING )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:926:1: (lv_value_1_0= RULE_STRING )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:927:3: lv_value_1_0= RULE_STRING
            {
            lv_value_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleExpression_String2029); 

            			newLeafNode(lv_value_1_0, grammarAccess.getExpression_StringAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getExpression_StringRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_1_0, 
                    		"STRING");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression_String"


    // $ANTLR start "entryRuleExpression_ProcessRef"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:951:1: entryRuleExpression_ProcessRef returns [EObject current=null] : iv_ruleExpression_ProcessRef= ruleExpression_ProcessRef EOF ;
    public final EObject entryRuleExpression_ProcessRef() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression_ProcessRef = null;


         
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");
        	
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:955:2: (iv_ruleExpression_ProcessRef= ruleExpression_ProcessRef EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:956:2: iv_ruleExpression_ProcessRef= ruleExpression_ProcessRef EOF
            {
             newCompositeNode(grammarAccess.getExpression_ProcessRefRule()); 
            pushFollow(FOLLOW_ruleExpression_ProcessRef_in_entryRuleExpression_ProcessRef2076);
            iv_ruleExpression_ProcessRef=ruleExpression_ProcessRef();

            state._fsp--;

             current =iv_ruleExpression_ProcessRef; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_ProcessRef2086); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleExpression_ProcessRef"


    // $ANTLR start "ruleExpression_ProcessRef"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:966:1: ruleExpression_ProcessRef returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleExpression_ProcessRef() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:970:28: ( ( () ( (otherlv_1= RULE_ID ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:971:1: ( () ( (otherlv_1= RULE_ID ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:971:1: ( () ( (otherlv_1= RULE_ID ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:971:2: () ( (otherlv_1= RULE_ID ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:971:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:972:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpression_ProcessRefAccess().getExpression_ProcessRefAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:977:2: ( (otherlv_1= RULE_ID ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:978:1: (otherlv_1= RULE_ID )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:978:1: (otherlv_1= RULE_ID )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:979:3: otherlv_1= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getExpression_ProcessRefRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleExpression_ProcessRef2144); 

            		newLeafNode(otherlv_1, grammarAccess.getExpression_ProcessRefAccess().getValueEObjectCrossReference_1_0()); 
            	

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleExpression_ProcessRef"


    // $ANTLR start "entryRuleExpression_Boolean"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1001:1: entryRuleExpression_Boolean returns [EObject current=null] : iv_ruleExpression_Boolean= ruleExpression_Boolean EOF ;
    public final EObject entryRuleExpression_Boolean() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression_Boolean = null;


        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1002:2: (iv_ruleExpression_Boolean= ruleExpression_Boolean EOF )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1003:2: iv_ruleExpression_Boolean= ruleExpression_Boolean EOF
            {
             newCompositeNode(grammarAccess.getExpression_BooleanRule()); 
            pushFollow(FOLLOW_ruleExpression_Boolean_in_entryRuleExpression_Boolean2184);
            iv_ruleExpression_Boolean=ruleExpression_Boolean();

            state._fsp--;

             current =iv_ruleExpression_Boolean; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Boolean2194); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression_Boolean"


    // $ANTLR start "ruleExpression_Boolean"
    // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1010:1: ruleExpression_Boolean returns [EObject current=null] : ( () ( (lv_value_1_0= RULE_BOOLEAN ) ) ) ;
    public final EObject ruleExpression_Boolean() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;

         enterRule(); 
            
        try {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1013:28: ( ( () ( (lv_value_1_0= RULE_BOOLEAN ) ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1014:1: ( () ( (lv_value_1_0= RULE_BOOLEAN ) ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1014:1: ( () ( (lv_value_1_0= RULE_BOOLEAN ) ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1014:2: () ( (lv_value_1_0= RULE_BOOLEAN ) )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1014:2: ()
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1015:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getExpression_BooleanAccess().getExpression_BooleanAction_0(),
                        current);
                

            }

            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1020:2: ( (lv_value_1_0= RULE_BOOLEAN ) )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1021:1: (lv_value_1_0= RULE_BOOLEAN )
            {
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1021:1: (lv_value_1_0= RULE_BOOLEAN )
            // ../org.bonitasoft.studio.condition/src-gen/org/bonitasoft/studio/condition/parser/antlr/internal/InternalConditionModel.g:1022:3: lv_value_1_0= RULE_BOOLEAN
            {
            lv_value_1_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_ruleExpression_Boolean2245); 

            			newLeafNode(lv_value_1_0, grammarAccess.getExpression_BooleanAccess().getValueBOOLEANTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getExpression_BooleanRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_1_0, 
                    		"BOOLEAN");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression_Boolean"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String DFA3_eotS =
        "\14\uffff";
    static final String DFA3_eofS =
        "\14\uffff";
    static final String DFA3_minS =
        "\1\4\5\20\6\uffff";
    static final String DFA3_maxS =
        "\1\10\5\25\6\uffff";
    static final String DFA3_acceptS =
        "\6\uffff\1\6\1\1\1\2\1\3\1\5\1\4";
    static final String DFA3_specialS =
        "\14\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\2\1\4\1\5\1\3",
            "\1\7\1\10\1\11\1\13\1\12\1\6",
            "\1\7\1\10\1\11\1\13\1\12\1\6",
            "\1\7\1\10\1\11\1\13\1\12\1\6",
            "\1\7\1\10\1\11\1\13\1\12\1\6",
            "\1\7\1\10\1\11\1\13\1\12\1\6",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "183:1: (this_Operation_Less_Equals_0= ruleOperation_Less_Equals | this_Operation_Less_1= ruleOperation_Less | this_Operation_Greater_Equals_2= ruleOperation_Greater_Equals | this_Operation_Greater_3= ruleOperation_Greater | this_Operation_Not_Equals_4= ruleOperation_Not_Equals | this_Operation_Equals_5= ruleOperation_Equals )";
        }
    }
 

    public static final BitSet FOLLOW_ruleOperation_Compare_in_entryRuleOperation_Compare75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Compare85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_in_ruleOperation_Compare142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnary_Operation_in_ruleOperation_Compare161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnary_Operation_in_entryRuleUnary_Operation200 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnary_Operation210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Unary_in_ruleUnary_Operation257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_NotUnary_in_ruleUnary_Operation284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_in_entryRuleOperation319 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_Equals_in_ruleOperation376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_in_ruleOperation403 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_Equals_in_ruleOperation430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_in_ruleOperation457 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Not_Equals_in_ruleOperation484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Equals_in_ruleOperation511 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_Equals_in_entryRuleOperation_Less_Equals546 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Less_Equals556 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less_Equals611 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleOperation_Less_Equals623 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less_Equals644 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_in_entryRuleOperation_Less680 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Less690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less745 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleOperation_Less757 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Less778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_Equals_in_entryRuleOperation_Greater_Equals814 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Greater_Equals824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater_Equals879 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_ruleOperation_Greater_Equals891 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater_Equals912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_in_entryRuleOperation_Greater948 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Greater958 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater1013 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleOperation_Greater1025 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Greater1046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Not_Equals_in_entryRuleOperation_Not_Equals1082 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Not_Equals1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Not_Equals1147 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleOperation_Not_Equals1159 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Not_Equals1180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Equals_in_entryRuleOperation_Equals1216 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Equals1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Equals1281 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleOperation_Equals1293 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Equals1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Unary_in_entryRuleOperation_Unary1350 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Unary1360 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_Unary1415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_NotUnary_in_entryRuleOperation_NotUnary1451 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_NotUnary1461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleOperation_NotUnary1507 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_ruleOperation_NotUnary1528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_entryRuleExpression_Terminal1564 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Terminal1574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Double_in_ruleExpression_Terminal1621 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Integer_in_ruleExpression_Terminal1648 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Boolean_in_ruleExpression_Terminal1675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_String_in_ruleExpression_Terminal1702 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_ProcessRef_in_ruleExpression_Terminal1729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Double_in_entryRuleExpression_Double1764 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Double1774 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DOUBLE_in_ruleExpression_Double1825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Integer_in_entryRuleExpression_Integer1866 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Integer1876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LONG_in_ruleExpression_Integer1927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_String_in_entryRuleExpression_String1968 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_String1978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleExpression_String2029 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_ProcessRef_in_entryRuleExpression_ProcessRef2076 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_ProcessRef2086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleExpression_ProcessRef2144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Boolean_in_entryRuleExpression_Boolean2184 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Boolean2194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_ruleExpression_Boolean2245 = new BitSet(new long[]{0x0000000000000002L});

}