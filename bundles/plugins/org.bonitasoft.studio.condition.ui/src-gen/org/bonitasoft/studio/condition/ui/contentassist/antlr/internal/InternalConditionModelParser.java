package org.bonitasoft.studio.condition.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import org.bonitasoft.studio.condition.services.ConditionModelGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalConditionModelParser extends AbstractInternalContentAssistParser {
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
    public String getGrammarFileName() { return "../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g"; }


     
     	private ConditionModelGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(ConditionModelGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRuleOperation_Compare"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:60:1: entryRuleOperation_Compare : ruleOperation_Compare EOF ;
    public final void entryRuleOperation_Compare() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:61:1: ( ruleOperation_Compare EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:62:1: ruleOperation_Compare EOF
            {
             before(grammarAccess.getOperation_CompareRule()); 
            pushFollow(FOLLOW_ruleOperation_Compare_in_entryRuleOperation_Compare61);
            ruleOperation_Compare();

            state._fsp--;

             after(grammarAccess.getOperation_CompareRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Compare68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Compare"


    // $ANTLR start "ruleOperation_Compare"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:69:1: ruleOperation_Compare : ( ( rule__Operation_Compare__Group__0 ) ) ;
    public final void ruleOperation_Compare() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:73:2: ( ( ( rule__Operation_Compare__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:74:1: ( ( rule__Operation_Compare__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:74:1: ( ( rule__Operation_Compare__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:75:1: ( rule__Operation_Compare__Group__0 )
            {
             before(grammarAccess.getOperation_CompareAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:76:1: ( rule__Operation_Compare__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:76:2: rule__Operation_Compare__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Compare__Group__0_in_ruleOperation_Compare94);
            rule__Operation_Compare__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_CompareAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Compare"


    // $ANTLR start "entryRuleUnary_Operation"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:88:1: entryRuleUnary_Operation : ruleUnary_Operation EOF ;
    public final void entryRuleUnary_Operation() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:89:1: ( ruleUnary_Operation EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:90:1: ruleUnary_Operation EOF
            {
             before(grammarAccess.getUnary_OperationRule()); 
            pushFollow(FOLLOW_ruleUnary_Operation_in_entryRuleUnary_Operation121);
            ruleUnary_Operation();

            state._fsp--;

             after(grammarAccess.getUnary_OperationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUnary_Operation128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleUnary_Operation"


    // $ANTLR start "ruleUnary_Operation"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:97:1: ruleUnary_Operation : ( ( rule__Unary_Operation__Alternatives ) ) ;
    public final void ruleUnary_Operation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:101:2: ( ( ( rule__Unary_Operation__Alternatives ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:102:1: ( ( rule__Unary_Operation__Alternatives ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:102:1: ( ( rule__Unary_Operation__Alternatives ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:103:1: ( rule__Unary_Operation__Alternatives )
            {
             before(grammarAccess.getUnary_OperationAccess().getAlternatives()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:104:1: ( rule__Unary_Operation__Alternatives )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:104:2: rule__Unary_Operation__Alternatives
            {
            pushFollow(FOLLOW_rule__Unary_Operation__Alternatives_in_ruleUnary_Operation154);
            rule__Unary_Operation__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getUnary_OperationAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleUnary_Operation"


    // $ANTLR start "entryRuleOperation"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:116:1: entryRuleOperation : ruleOperation EOF ;
    public final void entryRuleOperation() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:117:1: ( ruleOperation EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:118:1: ruleOperation EOF
            {
             before(grammarAccess.getOperationRule()); 
            pushFollow(FOLLOW_ruleOperation_in_entryRuleOperation181);
            ruleOperation();

            state._fsp--;

             after(grammarAccess.getOperationRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation"


    // $ANTLR start "ruleOperation"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:125:1: ruleOperation : ( ( rule__Operation__Alternatives ) ) ;
    public final void ruleOperation() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:129:2: ( ( ( rule__Operation__Alternatives ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:130:1: ( ( rule__Operation__Alternatives ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:130:1: ( ( rule__Operation__Alternatives ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:131:1: ( rule__Operation__Alternatives )
            {
             before(grammarAccess.getOperationAccess().getAlternatives()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:132:1: ( rule__Operation__Alternatives )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:132:2: rule__Operation__Alternatives
            {
            pushFollow(FOLLOW_rule__Operation__Alternatives_in_ruleOperation214);
            rule__Operation__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getOperationAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation"


    // $ANTLR start "entryRuleOperation_Less_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:144:1: entryRuleOperation_Less_Equals : ruleOperation_Less_Equals EOF ;
    public final void entryRuleOperation_Less_Equals() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:145:1: ( ruleOperation_Less_Equals EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:146:1: ruleOperation_Less_Equals EOF
            {
             before(grammarAccess.getOperation_Less_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Less_Equals_in_entryRuleOperation_Less_Equals241);
            ruleOperation_Less_Equals();

            state._fsp--;

             after(grammarAccess.getOperation_Less_EqualsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Less_Equals248); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Less_Equals"


    // $ANTLR start "ruleOperation_Less_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:153:1: ruleOperation_Less_Equals : ( ( rule__Operation_Less_Equals__Group__0 ) ) ;
    public final void ruleOperation_Less_Equals() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:157:2: ( ( ( rule__Operation_Less_Equals__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:158:1: ( ( rule__Operation_Less_Equals__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:158:1: ( ( rule__Operation_Less_Equals__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:159:1: ( rule__Operation_Less_Equals__Group__0 )
            {
             before(grammarAccess.getOperation_Less_EqualsAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:160:1: ( rule__Operation_Less_Equals__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:160:2: rule__Operation_Less_Equals__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__0_in_ruleOperation_Less_Equals274);
            rule__Operation_Less_Equals__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Less_EqualsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Less_Equals"


    // $ANTLR start "entryRuleOperation_Less"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:172:1: entryRuleOperation_Less : ruleOperation_Less EOF ;
    public final void entryRuleOperation_Less() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:173:1: ( ruleOperation_Less EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:174:1: ruleOperation_Less EOF
            {
             before(grammarAccess.getOperation_LessRule()); 
            pushFollow(FOLLOW_ruleOperation_Less_in_entryRuleOperation_Less301);
            ruleOperation_Less();

            state._fsp--;

             after(grammarAccess.getOperation_LessRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Less308); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Less"


    // $ANTLR start "ruleOperation_Less"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:181:1: ruleOperation_Less : ( ( rule__Operation_Less__Group__0 ) ) ;
    public final void ruleOperation_Less() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:185:2: ( ( ( rule__Operation_Less__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:186:1: ( ( rule__Operation_Less__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:186:1: ( ( rule__Operation_Less__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:187:1: ( rule__Operation_Less__Group__0 )
            {
             before(grammarAccess.getOperation_LessAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:188:1: ( rule__Operation_Less__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:188:2: rule__Operation_Less__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Less__Group__0_in_ruleOperation_Less334);
            rule__Operation_Less__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_LessAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Less"


    // $ANTLR start "entryRuleOperation_Greater_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:200:1: entryRuleOperation_Greater_Equals : ruleOperation_Greater_Equals EOF ;
    public final void entryRuleOperation_Greater_Equals() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:201:1: ( ruleOperation_Greater_Equals EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:202:1: ruleOperation_Greater_Equals EOF
            {
             before(grammarAccess.getOperation_Greater_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Greater_Equals_in_entryRuleOperation_Greater_Equals361);
            ruleOperation_Greater_Equals();

            state._fsp--;

             after(grammarAccess.getOperation_Greater_EqualsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Greater_Equals368); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Greater_Equals"


    // $ANTLR start "ruleOperation_Greater_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:209:1: ruleOperation_Greater_Equals : ( ( rule__Operation_Greater_Equals__Group__0 ) ) ;
    public final void ruleOperation_Greater_Equals() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:213:2: ( ( ( rule__Operation_Greater_Equals__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:214:1: ( ( rule__Operation_Greater_Equals__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:214:1: ( ( rule__Operation_Greater_Equals__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:215:1: ( rule__Operation_Greater_Equals__Group__0 )
            {
             before(grammarAccess.getOperation_Greater_EqualsAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:216:1: ( rule__Operation_Greater_Equals__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:216:2: rule__Operation_Greater_Equals__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__0_in_ruleOperation_Greater_Equals394);
            rule__Operation_Greater_Equals__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Greater_EqualsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Greater_Equals"


    // $ANTLR start "entryRuleOperation_Greater"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:228:1: entryRuleOperation_Greater : ruleOperation_Greater EOF ;
    public final void entryRuleOperation_Greater() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:229:1: ( ruleOperation_Greater EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:230:1: ruleOperation_Greater EOF
            {
             before(grammarAccess.getOperation_GreaterRule()); 
            pushFollow(FOLLOW_ruleOperation_Greater_in_entryRuleOperation_Greater421);
            ruleOperation_Greater();

            state._fsp--;

             after(grammarAccess.getOperation_GreaterRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Greater428); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Greater"


    // $ANTLR start "ruleOperation_Greater"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:237:1: ruleOperation_Greater : ( ( rule__Operation_Greater__Group__0 ) ) ;
    public final void ruleOperation_Greater() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:241:2: ( ( ( rule__Operation_Greater__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:242:1: ( ( rule__Operation_Greater__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:242:1: ( ( rule__Operation_Greater__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:243:1: ( rule__Operation_Greater__Group__0 )
            {
             before(grammarAccess.getOperation_GreaterAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:244:1: ( rule__Operation_Greater__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:244:2: rule__Operation_Greater__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Greater__Group__0_in_ruleOperation_Greater454);
            rule__Operation_Greater__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_GreaterAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Greater"


    // $ANTLR start "entryRuleOperation_Not_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:256:1: entryRuleOperation_Not_Equals : ruleOperation_Not_Equals EOF ;
    public final void entryRuleOperation_Not_Equals() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:257:1: ( ruleOperation_Not_Equals EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:258:1: ruleOperation_Not_Equals EOF
            {
             before(grammarAccess.getOperation_Not_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Not_Equals_in_entryRuleOperation_Not_Equals481);
            ruleOperation_Not_Equals();

            state._fsp--;

             after(grammarAccess.getOperation_Not_EqualsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Not_Equals488); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Not_Equals"


    // $ANTLR start "ruleOperation_Not_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:265:1: ruleOperation_Not_Equals : ( ( rule__Operation_Not_Equals__Group__0 ) ) ;
    public final void ruleOperation_Not_Equals() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:269:2: ( ( ( rule__Operation_Not_Equals__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:270:1: ( ( rule__Operation_Not_Equals__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:270:1: ( ( rule__Operation_Not_Equals__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:271:1: ( rule__Operation_Not_Equals__Group__0 )
            {
             before(grammarAccess.getOperation_Not_EqualsAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:272:1: ( rule__Operation_Not_Equals__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:272:2: rule__Operation_Not_Equals__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__0_in_ruleOperation_Not_Equals514);
            rule__Operation_Not_Equals__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Not_EqualsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Not_Equals"


    // $ANTLR start "entryRuleOperation_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:284:1: entryRuleOperation_Equals : ruleOperation_Equals EOF ;
    public final void entryRuleOperation_Equals() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:285:1: ( ruleOperation_Equals EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:286:1: ruleOperation_Equals EOF
            {
             before(grammarAccess.getOperation_EqualsRule()); 
            pushFollow(FOLLOW_ruleOperation_Equals_in_entryRuleOperation_Equals541);
            ruleOperation_Equals();

            state._fsp--;

             after(grammarAccess.getOperation_EqualsRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Equals548); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Equals"


    // $ANTLR start "ruleOperation_Equals"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:293:1: ruleOperation_Equals : ( ( rule__Operation_Equals__Group__0 ) ) ;
    public final void ruleOperation_Equals() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:297:2: ( ( ( rule__Operation_Equals__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:298:1: ( ( rule__Operation_Equals__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:298:1: ( ( rule__Operation_Equals__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:299:1: ( rule__Operation_Equals__Group__0 )
            {
             before(grammarAccess.getOperation_EqualsAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:300:1: ( rule__Operation_Equals__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:300:2: rule__Operation_Equals__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Equals__Group__0_in_ruleOperation_Equals574);
            rule__Operation_Equals__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_EqualsAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Equals"


    // $ANTLR start "entryRuleOperation_Unary"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:312:1: entryRuleOperation_Unary : ruleOperation_Unary EOF ;
    public final void entryRuleOperation_Unary() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:313:1: ( ruleOperation_Unary EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:314:1: ruleOperation_Unary EOF
            {
             before(grammarAccess.getOperation_UnaryRule()); 
            pushFollow(FOLLOW_ruleOperation_Unary_in_entryRuleOperation_Unary601);
            ruleOperation_Unary();

            state._fsp--;

             after(grammarAccess.getOperation_UnaryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_Unary608); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_Unary"


    // $ANTLR start "ruleOperation_Unary"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:321:1: ruleOperation_Unary : ( ( rule__Operation_Unary__Group__0 ) ) ;
    public final void ruleOperation_Unary() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:325:2: ( ( ( rule__Operation_Unary__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:326:1: ( ( rule__Operation_Unary__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:326:1: ( ( rule__Operation_Unary__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:327:1: ( rule__Operation_Unary__Group__0 )
            {
             before(grammarAccess.getOperation_UnaryAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:328:1: ( rule__Operation_Unary__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:328:2: rule__Operation_Unary__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_Unary__Group__0_in_ruleOperation_Unary634);
            rule__Operation_Unary__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_UnaryAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_Unary"


    // $ANTLR start "entryRuleOperation_NotUnary"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:340:1: entryRuleOperation_NotUnary : ruleOperation_NotUnary EOF ;
    public final void entryRuleOperation_NotUnary() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:341:1: ( ruleOperation_NotUnary EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:342:1: ruleOperation_NotUnary EOF
            {
             before(grammarAccess.getOperation_NotUnaryRule()); 
            pushFollow(FOLLOW_ruleOperation_NotUnary_in_entryRuleOperation_NotUnary661);
            ruleOperation_NotUnary();

            state._fsp--;

             after(grammarAccess.getOperation_NotUnaryRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperation_NotUnary668); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOperation_NotUnary"


    // $ANTLR start "ruleOperation_NotUnary"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:349:1: ruleOperation_NotUnary : ( ( rule__Operation_NotUnary__Group__0 ) ) ;
    public final void ruleOperation_NotUnary() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:353:2: ( ( ( rule__Operation_NotUnary__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:354:1: ( ( rule__Operation_NotUnary__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:354:1: ( ( rule__Operation_NotUnary__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:355:1: ( rule__Operation_NotUnary__Group__0 )
            {
             before(grammarAccess.getOperation_NotUnaryAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:356:1: ( rule__Operation_NotUnary__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:356:2: rule__Operation_NotUnary__Group__0
            {
            pushFollow(FOLLOW_rule__Operation_NotUnary__Group__0_in_ruleOperation_NotUnary694);
            rule__Operation_NotUnary__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_NotUnaryAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOperation_NotUnary"


    // $ANTLR start "entryRuleExpression_Terminal"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:368:1: entryRuleExpression_Terminal : ruleExpression_Terminal EOF ;
    public final void entryRuleExpression_Terminal() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:369:1: ( ruleExpression_Terminal EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:370:1: ruleExpression_Terminal EOF
            {
             before(grammarAccess.getExpression_TerminalRule()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_entryRuleExpression_Terminal721);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getExpression_TerminalRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Terminal728); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression_Terminal"


    // $ANTLR start "ruleExpression_Terminal"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:377:1: ruleExpression_Terminal : ( ( rule__Expression_Terminal__Alternatives ) ) ;
    public final void ruleExpression_Terminal() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:381:2: ( ( ( rule__Expression_Terminal__Alternatives ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:382:1: ( ( rule__Expression_Terminal__Alternatives ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:382:1: ( ( rule__Expression_Terminal__Alternatives ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:383:1: ( rule__Expression_Terminal__Alternatives )
            {
             before(grammarAccess.getExpression_TerminalAccess().getAlternatives()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:384:1: ( rule__Expression_Terminal__Alternatives )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:384:2: rule__Expression_Terminal__Alternatives
            {
            pushFollow(FOLLOW_rule__Expression_Terminal__Alternatives_in_ruleExpression_Terminal754);
            rule__Expression_Terminal__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getExpression_TerminalAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression_Terminal"


    // $ANTLR start "entryRuleExpression_Double"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:396:1: entryRuleExpression_Double : ruleExpression_Double EOF ;
    public final void entryRuleExpression_Double() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:397:1: ( ruleExpression_Double EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:398:1: ruleExpression_Double EOF
            {
             before(grammarAccess.getExpression_DoubleRule()); 
            pushFollow(FOLLOW_ruleExpression_Double_in_entryRuleExpression_Double781);
            ruleExpression_Double();

            state._fsp--;

             after(grammarAccess.getExpression_DoubleRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Double788); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression_Double"


    // $ANTLR start "ruleExpression_Double"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:405:1: ruleExpression_Double : ( ( rule__Expression_Double__Group__0 ) ) ;
    public final void ruleExpression_Double() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:409:2: ( ( ( rule__Expression_Double__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:410:1: ( ( rule__Expression_Double__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:410:1: ( ( rule__Expression_Double__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:411:1: ( rule__Expression_Double__Group__0 )
            {
             before(grammarAccess.getExpression_DoubleAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:412:1: ( rule__Expression_Double__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:412:2: rule__Expression_Double__Group__0
            {
            pushFollow(FOLLOW_rule__Expression_Double__Group__0_in_ruleExpression_Double814);
            rule__Expression_Double__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpression_DoubleAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression_Double"


    // $ANTLR start "entryRuleExpression_Integer"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:424:1: entryRuleExpression_Integer : ruleExpression_Integer EOF ;
    public final void entryRuleExpression_Integer() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:425:1: ( ruleExpression_Integer EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:426:1: ruleExpression_Integer EOF
            {
             before(grammarAccess.getExpression_IntegerRule()); 
            pushFollow(FOLLOW_ruleExpression_Integer_in_entryRuleExpression_Integer841);
            ruleExpression_Integer();

            state._fsp--;

             after(grammarAccess.getExpression_IntegerRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Integer848); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression_Integer"


    // $ANTLR start "ruleExpression_Integer"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:433:1: ruleExpression_Integer : ( ( rule__Expression_Integer__Group__0 ) ) ;
    public final void ruleExpression_Integer() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:437:2: ( ( ( rule__Expression_Integer__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:438:1: ( ( rule__Expression_Integer__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:438:1: ( ( rule__Expression_Integer__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:439:1: ( rule__Expression_Integer__Group__0 )
            {
             before(grammarAccess.getExpression_IntegerAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:440:1: ( rule__Expression_Integer__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:440:2: rule__Expression_Integer__Group__0
            {
            pushFollow(FOLLOW_rule__Expression_Integer__Group__0_in_ruleExpression_Integer874);
            rule__Expression_Integer__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpression_IntegerAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression_Integer"


    // $ANTLR start "entryRuleExpression_String"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:452:1: entryRuleExpression_String : ruleExpression_String EOF ;
    public final void entryRuleExpression_String() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:453:1: ( ruleExpression_String EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:454:1: ruleExpression_String EOF
            {
             before(grammarAccess.getExpression_StringRule()); 
            pushFollow(FOLLOW_ruleExpression_String_in_entryRuleExpression_String901);
            ruleExpression_String();

            state._fsp--;

             after(grammarAccess.getExpression_StringRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_String908); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression_String"


    // $ANTLR start "ruleExpression_String"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:461:1: ruleExpression_String : ( ( rule__Expression_String__Group__0 ) ) ;
    public final void ruleExpression_String() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:465:2: ( ( ( rule__Expression_String__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:466:1: ( ( rule__Expression_String__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:466:1: ( ( rule__Expression_String__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:467:1: ( rule__Expression_String__Group__0 )
            {
             before(grammarAccess.getExpression_StringAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:468:1: ( rule__Expression_String__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:468:2: rule__Expression_String__Group__0
            {
            pushFollow(FOLLOW_rule__Expression_String__Group__0_in_ruleExpression_String934);
            rule__Expression_String__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpression_StringAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression_String"


    // $ANTLR start "entryRuleExpression_ProcessRef"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:480:1: entryRuleExpression_ProcessRef : ruleExpression_ProcessRef EOF ;
    public final void entryRuleExpression_ProcessRef() throws RecognitionException {

        	HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");

        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:484:1: ( ruleExpression_ProcessRef EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:485:1: ruleExpression_ProcessRef EOF
            {
             before(grammarAccess.getExpression_ProcessRefRule()); 
            pushFollow(FOLLOW_ruleExpression_ProcessRef_in_entryRuleExpression_ProcessRef966);
            ruleExpression_ProcessRef();

            state._fsp--;

             after(grammarAccess.getExpression_ProcessRefRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_ProcessRef973); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	myHiddenTokenState.restore();

        }
        return ;
    }
    // $ANTLR end "entryRuleExpression_ProcessRef"


    // $ANTLR start "ruleExpression_ProcessRef"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:495:1: ruleExpression_ProcessRef : ( ( rule__Expression_ProcessRef__Group__0 ) ) ;
    public final void ruleExpression_ProcessRef() throws RecognitionException {

        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens("RULE_WS");
        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:500:2: ( ( ( rule__Expression_ProcessRef__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:501:1: ( ( rule__Expression_ProcessRef__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:501:1: ( ( rule__Expression_ProcessRef__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:502:1: ( rule__Expression_ProcessRef__Group__0 )
            {
             before(grammarAccess.getExpression_ProcessRefAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:503:1: ( rule__Expression_ProcessRef__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:503:2: rule__Expression_ProcessRef__Group__0
            {
            pushFollow(FOLLOW_rule__Expression_ProcessRef__Group__0_in_ruleExpression_ProcessRef1003);
            rule__Expression_ProcessRef__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpression_ProcessRefAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);
            	myHiddenTokenState.restore();

        }
        return ;
    }
    // $ANTLR end "ruleExpression_ProcessRef"


    // $ANTLR start "entryRuleExpression_Boolean"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:516:1: entryRuleExpression_Boolean : ruleExpression_Boolean EOF ;
    public final void entryRuleExpression_Boolean() throws RecognitionException {
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:517:1: ( ruleExpression_Boolean EOF )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:518:1: ruleExpression_Boolean EOF
            {
             before(grammarAccess.getExpression_BooleanRule()); 
            pushFollow(FOLLOW_ruleExpression_Boolean_in_entryRuleExpression_Boolean1030);
            ruleExpression_Boolean();

            state._fsp--;

             after(grammarAccess.getExpression_BooleanRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression_Boolean1037); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleExpression_Boolean"


    // $ANTLR start "ruleExpression_Boolean"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:525:1: ruleExpression_Boolean : ( ( rule__Expression_Boolean__Group__0 ) ) ;
    public final void ruleExpression_Boolean() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:529:2: ( ( ( rule__Expression_Boolean__Group__0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:530:1: ( ( rule__Expression_Boolean__Group__0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:530:1: ( ( rule__Expression_Boolean__Group__0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:531:1: ( rule__Expression_Boolean__Group__0 )
            {
             before(grammarAccess.getExpression_BooleanAccess().getGroup()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:532:1: ( rule__Expression_Boolean__Group__0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:532:2: rule__Expression_Boolean__Group__0
            {
            pushFollow(FOLLOW_rule__Expression_Boolean__Group__0_in_ruleExpression_Boolean1063);
            rule__Expression_Boolean__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getExpression_BooleanAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleExpression_Boolean"


    // $ANTLR start "rule__Operation_Compare__OpAlternatives_1_0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:544:1: rule__Operation_Compare__OpAlternatives_1_0 : ( ( ruleOperation ) | ( ruleUnary_Operation ) );
    public final void rule__Operation_Compare__OpAlternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:548:1: ( ( ruleOperation ) | ( ruleUnary_Operation ) )
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
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:549:1: ( ruleOperation )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:549:1: ( ruleOperation )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:550:1: ruleOperation
                    {
                     before(grammarAccess.getOperation_CompareAccess().getOpOperationParserRuleCall_1_0_0()); 
                    pushFollow(FOLLOW_ruleOperation_in_rule__Operation_Compare__OpAlternatives_1_01099);
                    ruleOperation();

                    state._fsp--;

                     after(grammarAccess.getOperation_CompareAccess().getOpOperationParserRuleCall_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:555:6: ( ruleUnary_Operation )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:555:6: ( ruleUnary_Operation )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:556:1: ruleUnary_Operation
                    {
                     before(grammarAccess.getOperation_CompareAccess().getOpUnary_OperationParserRuleCall_1_0_1()); 
                    pushFollow(FOLLOW_ruleUnary_Operation_in_rule__Operation_Compare__OpAlternatives_1_01116);
                    ruleUnary_Operation();

                    state._fsp--;

                     after(grammarAccess.getOperation_CompareAccess().getOpUnary_OperationParserRuleCall_1_0_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Compare__OpAlternatives_1_0"


    // $ANTLR start "rule__Unary_Operation__Alternatives"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:566:1: rule__Unary_Operation__Alternatives : ( ( ruleOperation_Unary ) | ( ruleOperation_NotUnary ) );
    public final void rule__Unary_Operation__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:570:1: ( ( ruleOperation_Unary ) | ( ruleOperation_NotUnary ) )
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
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:571:1: ( ruleOperation_Unary )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:571:1: ( ruleOperation_Unary )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:572:1: ruleOperation_Unary
                    {
                     before(grammarAccess.getUnary_OperationAccess().getOperation_UnaryParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleOperation_Unary_in_rule__Unary_Operation__Alternatives1148);
                    ruleOperation_Unary();

                    state._fsp--;

                     after(grammarAccess.getUnary_OperationAccess().getOperation_UnaryParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:577:6: ( ruleOperation_NotUnary )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:577:6: ( ruleOperation_NotUnary )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:578:1: ruleOperation_NotUnary
                    {
                     before(grammarAccess.getUnary_OperationAccess().getOperation_NotUnaryParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleOperation_NotUnary_in_rule__Unary_Operation__Alternatives1165);
                    ruleOperation_NotUnary();

                    state._fsp--;

                     after(grammarAccess.getUnary_OperationAccess().getOperation_NotUnaryParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Unary_Operation__Alternatives"


    // $ANTLR start "rule__Operation__Alternatives"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:588:1: rule__Operation__Alternatives : ( ( ruleOperation_Less_Equals ) | ( ruleOperation_Less ) | ( ruleOperation_Greater_Equals ) | ( ruleOperation_Greater ) | ( ruleOperation_Not_Equals ) | ( ruleOperation_Equals ) );
    public final void rule__Operation__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:592:1: ( ( ruleOperation_Less_Equals ) | ( ruleOperation_Less ) | ( ruleOperation_Greater_Equals ) | ( ruleOperation_Greater ) | ( ruleOperation_Not_Equals ) | ( ruleOperation_Equals ) )
            int alt3=6;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:593:1: ( ruleOperation_Less_Equals )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:593:1: ( ruleOperation_Less_Equals )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:594:1: ruleOperation_Less_Equals
                    {
                     before(grammarAccess.getOperationAccess().getOperation_Less_EqualsParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleOperation_Less_Equals_in_rule__Operation__Alternatives1197);
                    ruleOperation_Less_Equals();

                    state._fsp--;

                     after(grammarAccess.getOperationAccess().getOperation_Less_EqualsParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:599:6: ( ruleOperation_Less )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:599:6: ( ruleOperation_Less )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:600:1: ruleOperation_Less
                    {
                     before(grammarAccess.getOperationAccess().getOperation_LessParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleOperation_Less_in_rule__Operation__Alternatives1214);
                    ruleOperation_Less();

                    state._fsp--;

                     after(grammarAccess.getOperationAccess().getOperation_LessParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:605:6: ( ruleOperation_Greater_Equals )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:605:6: ( ruleOperation_Greater_Equals )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:606:1: ruleOperation_Greater_Equals
                    {
                     before(grammarAccess.getOperationAccess().getOperation_Greater_EqualsParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleOperation_Greater_Equals_in_rule__Operation__Alternatives1231);
                    ruleOperation_Greater_Equals();

                    state._fsp--;

                     after(grammarAccess.getOperationAccess().getOperation_Greater_EqualsParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:611:6: ( ruleOperation_Greater )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:611:6: ( ruleOperation_Greater )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:612:1: ruleOperation_Greater
                    {
                     before(grammarAccess.getOperationAccess().getOperation_GreaterParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleOperation_Greater_in_rule__Operation__Alternatives1248);
                    ruleOperation_Greater();

                    state._fsp--;

                     after(grammarAccess.getOperationAccess().getOperation_GreaterParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:617:6: ( ruleOperation_Not_Equals )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:617:6: ( ruleOperation_Not_Equals )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:618:1: ruleOperation_Not_Equals
                    {
                     before(grammarAccess.getOperationAccess().getOperation_Not_EqualsParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleOperation_Not_Equals_in_rule__Operation__Alternatives1265);
                    ruleOperation_Not_Equals();

                    state._fsp--;

                     after(grammarAccess.getOperationAccess().getOperation_Not_EqualsParserRuleCall_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:623:6: ( ruleOperation_Equals )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:623:6: ( ruleOperation_Equals )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:624:1: ruleOperation_Equals
                    {
                     before(grammarAccess.getOperationAccess().getOperation_EqualsParserRuleCall_5()); 
                    pushFollow(FOLLOW_ruleOperation_Equals_in_rule__Operation__Alternatives1282);
                    ruleOperation_Equals();

                    state._fsp--;

                     after(grammarAccess.getOperationAccess().getOperation_EqualsParserRuleCall_5()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation__Alternatives"


    // $ANTLR start "rule__Expression_Terminal__Alternatives"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:634:1: rule__Expression_Terminal__Alternatives : ( ( ruleExpression_Double ) | ( ruleExpression_Integer ) | ( ruleExpression_Boolean ) | ( ruleExpression_String ) | ( ruleExpression_ProcessRef ) );
    public final void rule__Expression_Terminal__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:638:1: ( ( ruleExpression_Double ) | ( ruleExpression_Integer ) | ( ruleExpression_Boolean ) | ( ruleExpression_String ) | ( ruleExpression_ProcessRef ) )
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
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:639:1: ( ruleExpression_Double )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:639:1: ( ruleExpression_Double )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:640:1: ruleExpression_Double
                    {
                     before(grammarAccess.getExpression_TerminalAccess().getExpression_DoubleParserRuleCall_0()); 
                    pushFollow(FOLLOW_ruleExpression_Double_in_rule__Expression_Terminal__Alternatives1314);
                    ruleExpression_Double();

                    state._fsp--;

                     after(grammarAccess.getExpression_TerminalAccess().getExpression_DoubleParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:645:6: ( ruleExpression_Integer )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:645:6: ( ruleExpression_Integer )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:646:1: ruleExpression_Integer
                    {
                     before(grammarAccess.getExpression_TerminalAccess().getExpression_IntegerParserRuleCall_1()); 
                    pushFollow(FOLLOW_ruleExpression_Integer_in_rule__Expression_Terminal__Alternatives1331);
                    ruleExpression_Integer();

                    state._fsp--;

                     after(grammarAccess.getExpression_TerminalAccess().getExpression_IntegerParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:651:6: ( ruleExpression_Boolean )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:651:6: ( ruleExpression_Boolean )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:652:1: ruleExpression_Boolean
                    {
                     before(grammarAccess.getExpression_TerminalAccess().getExpression_BooleanParserRuleCall_2()); 
                    pushFollow(FOLLOW_ruleExpression_Boolean_in_rule__Expression_Terminal__Alternatives1348);
                    ruleExpression_Boolean();

                    state._fsp--;

                     after(grammarAccess.getExpression_TerminalAccess().getExpression_BooleanParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:657:6: ( ruleExpression_String )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:657:6: ( ruleExpression_String )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:658:1: ruleExpression_String
                    {
                     before(grammarAccess.getExpression_TerminalAccess().getExpression_StringParserRuleCall_3()); 
                    pushFollow(FOLLOW_ruleExpression_String_in_rule__Expression_Terminal__Alternatives1365);
                    ruleExpression_String();

                    state._fsp--;

                     after(grammarAccess.getExpression_TerminalAccess().getExpression_StringParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:663:6: ( ruleExpression_ProcessRef )
                    {
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:663:6: ( ruleExpression_ProcessRef )
                    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:664:1: ruleExpression_ProcessRef
                    {
                     before(grammarAccess.getExpression_TerminalAccess().getExpression_ProcessRefParserRuleCall_4()); 
                    pushFollow(FOLLOW_ruleExpression_ProcessRef_in_rule__Expression_Terminal__Alternatives1382);
                    ruleExpression_ProcessRef();

                    state._fsp--;

                     after(grammarAccess.getExpression_TerminalAccess().getExpression_ProcessRefParserRuleCall_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Terminal__Alternatives"


    // $ANTLR start "rule__Operation_Compare__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:676:1: rule__Operation_Compare__Group__0 : rule__Operation_Compare__Group__0__Impl rule__Operation_Compare__Group__1 ;
    public final void rule__Operation_Compare__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:680:1: ( rule__Operation_Compare__Group__0__Impl rule__Operation_Compare__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:681:2: rule__Operation_Compare__Group__0__Impl rule__Operation_Compare__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Compare__Group__0__Impl_in_rule__Operation_Compare__Group__01412);
            rule__Operation_Compare__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Compare__Group__1_in_rule__Operation_Compare__Group__01415);
            rule__Operation_Compare__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Compare__Group__0"


    // $ANTLR start "rule__Operation_Compare__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:688:1: rule__Operation_Compare__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Compare__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:692:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:693:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:693:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:694:1: ()
            {
             before(grammarAccess.getOperation_CompareAccess().getOperation_CompareAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:695:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:697:1: 
            {
            }

             after(grammarAccess.getOperation_CompareAccess().getOperation_CompareAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Compare__Group__0__Impl"


    // $ANTLR start "rule__Operation_Compare__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:707:1: rule__Operation_Compare__Group__1 : rule__Operation_Compare__Group__1__Impl ;
    public final void rule__Operation_Compare__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:711:1: ( rule__Operation_Compare__Group__1__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:712:2: rule__Operation_Compare__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Compare__Group__1__Impl_in_rule__Operation_Compare__Group__11473);
            rule__Operation_Compare__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Compare__Group__1"


    // $ANTLR start "rule__Operation_Compare__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:718:1: rule__Operation_Compare__Group__1__Impl : ( ( rule__Operation_Compare__OpAssignment_1 ) ) ;
    public final void rule__Operation_Compare__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:722:1: ( ( ( rule__Operation_Compare__OpAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:723:1: ( ( rule__Operation_Compare__OpAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:723:1: ( ( rule__Operation_Compare__OpAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:724:1: ( rule__Operation_Compare__OpAssignment_1 )
            {
             before(grammarAccess.getOperation_CompareAccess().getOpAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:725:1: ( rule__Operation_Compare__OpAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:725:2: rule__Operation_Compare__OpAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Compare__OpAssignment_1_in_rule__Operation_Compare__Group__1__Impl1500);
            rule__Operation_Compare__OpAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_CompareAccess().getOpAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Compare__Group__1__Impl"


    // $ANTLR start "rule__Operation_Less_Equals__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:739:1: rule__Operation_Less_Equals__Group__0 : rule__Operation_Less_Equals__Group__0__Impl rule__Operation_Less_Equals__Group__1 ;
    public final void rule__Operation_Less_Equals__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:743:1: ( rule__Operation_Less_Equals__Group__0__Impl rule__Operation_Less_Equals__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:744:2: rule__Operation_Less_Equals__Group__0__Impl rule__Operation_Less_Equals__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__0__Impl_in_rule__Operation_Less_Equals__Group__01534);
            rule__Operation_Less_Equals__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__1_in_rule__Operation_Less_Equals__Group__01537);
            rule__Operation_Less_Equals__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__0"


    // $ANTLR start "rule__Operation_Less_Equals__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:751:1: rule__Operation_Less_Equals__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Less_Equals__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:755:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:756:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:756:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:757:1: ()
            {
             before(grammarAccess.getOperation_Less_EqualsAccess().getOperation_Less_EqualsAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:758:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:760:1: 
            {
            }

             after(grammarAccess.getOperation_Less_EqualsAccess().getOperation_Less_EqualsAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__0__Impl"


    // $ANTLR start "rule__Operation_Less_Equals__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:770:1: rule__Operation_Less_Equals__Group__1 : rule__Operation_Less_Equals__Group__1__Impl rule__Operation_Less_Equals__Group__2 ;
    public final void rule__Operation_Less_Equals__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:774:1: ( rule__Operation_Less_Equals__Group__1__Impl rule__Operation_Less_Equals__Group__2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:775:2: rule__Operation_Less_Equals__Group__1__Impl rule__Operation_Less_Equals__Group__2
            {
            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__1__Impl_in_rule__Operation_Less_Equals__Group__11595);
            rule__Operation_Less_Equals__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__2_in_rule__Operation_Less_Equals__Group__11598);
            rule__Operation_Less_Equals__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__1"


    // $ANTLR start "rule__Operation_Less_Equals__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:782:1: rule__Operation_Less_Equals__Group__1__Impl : ( ( rule__Operation_Less_Equals__LeftAssignment_1 ) ) ;
    public final void rule__Operation_Less_Equals__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:786:1: ( ( ( rule__Operation_Less_Equals__LeftAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:787:1: ( ( rule__Operation_Less_Equals__LeftAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:787:1: ( ( rule__Operation_Less_Equals__LeftAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:788:1: ( rule__Operation_Less_Equals__LeftAssignment_1 )
            {
             before(grammarAccess.getOperation_Less_EqualsAccess().getLeftAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:789:1: ( rule__Operation_Less_Equals__LeftAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:789:2: rule__Operation_Less_Equals__LeftAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Less_Equals__LeftAssignment_1_in_rule__Operation_Less_Equals__Group__1__Impl1625);
            rule__Operation_Less_Equals__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Less_EqualsAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__1__Impl"


    // $ANTLR start "rule__Operation_Less_Equals__Group__2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:799:1: rule__Operation_Less_Equals__Group__2 : rule__Operation_Less_Equals__Group__2__Impl rule__Operation_Less_Equals__Group__3 ;
    public final void rule__Operation_Less_Equals__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:803:1: ( rule__Operation_Less_Equals__Group__2__Impl rule__Operation_Less_Equals__Group__3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:804:2: rule__Operation_Less_Equals__Group__2__Impl rule__Operation_Less_Equals__Group__3
            {
            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__2__Impl_in_rule__Operation_Less_Equals__Group__21655);
            rule__Operation_Less_Equals__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__3_in_rule__Operation_Less_Equals__Group__21658);
            rule__Operation_Less_Equals__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__2"


    // $ANTLR start "rule__Operation_Less_Equals__Group__2__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:811:1: rule__Operation_Less_Equals__Group__2__Impl : ( '<=' ) ;
    public final void rule__Operation_Less_Equals__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:815:1: ( ( '<=' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:816:1: ( '<=' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:816:1: ( '<=' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:817:1: '<='
            {
             before(grammarAccess.getOperation_Less_EqualsAccess().getLessThanSignEqualsSignKeyword_2()); 
            match(input,16,FOLLOW_16_in_rule__Operation_Less_Equals__Group__2__Impl1686); 
             after(grammarAccess.getOperation_Less_EqualsAccess().getLessThanSignEqualsSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__2__Impl"


    // $ANTLR start "rule__Operation_Less_Equals__Group__3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:830:1: rule__Operation_Less_Equals__Group__3 : rule__Operation_Less_Equals__Group__3__Impl ;
    public final void rule__Operation_Less_Equals__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:834:1: ( rule__Operation_Less_Equals__Group__3__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:835:2: rule__Operation_Less_Equals__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Less_Equals__Group__3__Impl_in_rule__Operation_Less_Equals__Group__31717);
            rule__Operation_Less_Equals__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__3"


    // $ANTLR start "rule__Operation_Less_Equals__Group__3__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:841:1: rule__Operation_Less_Equals__Group__3__Impl : ( ( rule__Operation_Less_Equals__RightAssignment_3 ) ) ;
    public final void rule__Operation_Less_Equals__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:845:1: ( ( ( rule__Operation_Less_Equals__RightAssignment_3 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:846:1: ( ( rule__Operation_Less_Equals__RightAssignment_3 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:846:1: ( ( rule__Operation_Less_Equals__RightAssignment_3 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:847:1: ( rule__Operation_Less_Equals__RightAssignment_3 )
            {
             before(grammarAccess.getOperation_Less_EqualsAccess().getRightAssignment_3()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:848:1: ( rule__Operation_Less_Equals__RightAssignment_3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:848:2: rule__Operation_Less_Equals__RightAssignment_3
            {
            pushFollow(FOLLOW_rule__Operation_Less_Equals__RightAssignment_3_in_rule__Operation_Less_Equals__Group__3__Impl1744);
            rule__Operation_Less_Equals__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Less_EqualsAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__Group__3__Impl"


    // $ANTLR start "rule__Operation_Less__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:866:1: rule__Operation_Less__Group__0 : rule__Operation_Less__Group__0__Impl rule__Operation_Less__Group__1 ;
    public final void rule__Operation_Less__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:870:1: ( rule__Operation_Less__Group__0__Impl rule__Operation_Less__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:871:2: rule__Operation_Less__Group__0__Impl rule__Operation_Less__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Less__Group__0__Impl_in_rule__Operation_Less__Group__01782);
            rule__Operation_Less__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Less__Group__1_in_rule__Operation_Less__Group__01785);
            rule__Operation_Less__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__0"


    // $ANTLR start "rule__Operation_Less__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:878:1: rule__Operation_Less__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Less__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:882:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:883:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:883:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:884:1: ()
            {
             before(grammarAccess.getOperation_LessAccess().getOperation_LessAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:885:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:887:1: 
            {
            }

             after(grammarAccess.getOperation_LessAccess().getOperation_LessAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__0__Impl"


    // $ANTLR start "rule__Operation_Less__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:897:1: rule__Operation_Less__Group__1 : rule__Operation_Less__Group__1__Impl rule__Operation_Less__Group__2 ;
    public final void rule__Operation_Less__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:901:1: ( rule__Operation_Less__Group__1__Impl rule__Operation_Less__Group__2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:902:2: rule__Operation_Less__Group__1__Impl rule__Operation_Less__Group__2
            {
            pushFollow(FOLLOW_rule__Operation_Less__Group__1__Impl_in_rule__Operation_Less__Group__11843);
            rule__Operation_Less__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Less__Group__2_in_rule__Operation_Less__Group__11846);
            rule__Operation_Less__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__1"


    // $ANTLR start "rule__Operation_Less__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:909:1: rule__Operation_Less__Group__1__Impl : ( ( rule__Operation_Less__LeftAssignment_1 ) ) ;
    public final void rule__Operation_Less__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:913:1: ( ( ( rule__Operation_Less__LeftAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:914:1: ( ( rule__Operation_Less__LeftAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:914:1: ( ( rule__Operation_Less__LeftAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:915:1: ( rule__Operation_Less__LeftAssignment_1 )
            {
             before(grammarAccess.getOperation_LessAccess().getLeftAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:916:1: ( rule__Operation_Less__LeftAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:916:2: rule__Operation_Less__LeftAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Less__LeftAssignment_1_in_rule__Operation_Less__Group__1__Impl1873);
            rule__Operation_Less__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_LessAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__1__Impl"


    // $ANTLR start "rule__Operation_Less__Group__2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:926:1: rule__Operation_Less__Group__2 : rule__Operation_Less__Group__2__Impl rule__Operation_Less__Group__3 ;
    public final void rule__Operation_Less__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:930:1: ( rule__Operation_Less__Group__2__Impl rule__Operation_Less__Group__3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:931:2: rule__Operation_Less__Group__2__Impl rule__Operation_Less__Group__3
            {
            pushFollow(FOLLOW_rule__Operation_Less__Group__2__Impl_in_rule__Operation_Less__Group__21903);
            rule__Operation_Less__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Less__Group__3_in_rule__Operation_Less__Group__21906);
            rule__Operation_Less__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__2"


    // $ANTLR start "rule__Operation_Less__Group__2__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:938:1: rule__Operation_Less__Group__2__Impl : ( '<' ) ;
    public final void rule__Operation_Less__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:942:1: ( ( '<' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:943:1: ( '<' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:943:1: ( '<' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:944:1: '<'
            {
             before(grammarAccess.getOperation_LessAccess().getLessThanSignKeyword_2()); 
            match(input,17,FOLLOW_17_in_rule__Operation_Less__Group__2__Impl1934); 
             after(grammarAccess.getOperation_LessAccess().getLessThanSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__2__Impl"


    // $ANTLR start "rule__Operation_Less__Group__3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:957:1: rule__Operation_Less__Group__3 : rule__Operation_Less__Group__3__Impl ;
    public final void rule__Operation_Less__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:961:1: ( rule__Operation_Less__Group__3__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:962:2: rule__Operation_Less__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Less__Group__3__Impl_in_rule__Operation_Less__Group__31965);
            rule__Operation_Less__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__3"


    // $ANTLR start "rule__Operation_Less__Group__3__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:968:1: rule__Operation_Less__Group__3__Impl : ( ( rule__Operation_Less__RightAssignment_3 ) ) ;
    public final void rule__Operation_Less__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:972:1: ( ( ( rule__Operation_Less__RightAssignment_3 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:973:1: ( ( rule__Operation_Less__RightAssignment_3 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:973:1: ( ( rule__Operation_Less__RightAssignment_3 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:974:1: ( rule__Operation_Less__RightAssignment_3 )
            {
             before(grammarAccess.getOperation_LessAccess().getRightAssignment_3()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:975:1: ( rule__Operation_Less__RightAssignment_3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:975:2: rule__Operation_Less__RightAssignment_3
            {
            pushFollow(FOLLOW_rule__Operation_Less__RightAssignment_3_in_rule__Operation_Less__Group__3__Impl1992);
            rule__Operation_Less__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOperation_LessAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__Group__3__Impl"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:993:1: rule__Operation_Greater_Equals__Group__0 : rule__Operation_Greater_Equals__Group__0__Impl rule__Operation_Greater_Equals__Group__1 ;
    public final void rule__Operation_Greater_Equals__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:997:1: ( rule__Operation_Greater_Equals__Group__0__Impl rule__Operation_Greater_Equals__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:998:2: rule__Operation_Greater_Equals__Group__0__Impl rule__Operation_Greater_Equals__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__0__Impl_in_rule__Operation_Greater_Equals__Group__02030);
            rule__Operation_Greater_Equals__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__1_in_rule__Operation_Greater_Equals__Group__02033);
            rule__Operation_Greater_Equals__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__0"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1005:1: rule__Operation_Greater_Equals__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Greater_Equals__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1009:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1010:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1010:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1011:1: ()
            {
             before(grammarAccess.getOperation_Greater_EqualsAccess().getOperation_Greater_EqualsAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1012:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1014:1: 
            {
            }

             after(grammarAccess.getOperation_Greater_EqualsAccess().getOperation_Greater_EqualsAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__0__Impl"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1024:1: rule__Operation_Greater_Equals__Group__1 : rule__Operation_Greater_Equals__Group__1__Impl rule__Operation_Greater_Equals__Group__2 ;
    public final void rule__Operation_Greater_Equals__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1028:1: ( rule__Operation_Greater_Equals__Group__1__Impl rule__Operation_Greater_Equals__Group__2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1029:2: rule__Operation_Greater_Equals__Group__1__Impl rule__Operation_Greater_Equals__Group__2
            {
            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__1__Impl_in_rule__Operation_Greater_Equals__Group__12091);
            rule__Operation_Greater_Equals__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__2_in_rule__Operation_Greater_Equals__Group__12094);
            rule__Operation_Greater_Equals__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__1"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1036:1: rule__Operation_Greater_Equals__Group__1__Impl : ( ( rule__Operation_Greater_Equals__LeftAssignment_1 ) ) ;
    public final void rule__Operation_Greater_Equals__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1040:1: ( ( ( rule__Operation_Greater_Equals__LeftAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1041:1: ( ( rule__Operation_Greater_Equals__LeftAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1041:1: ( ( rule__Operation_Greater_Equals__LeftAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1042:1: ( rule__Operation_Greater_Equals__LeftAssignment_1 )
            {
             before(grammarAccess.getOperation_Greater_EqualsAccess().getLeftAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1043:1: ( rule__Operation_Greater_Equals__LeftAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1043:2: rule__Operation_Greater_Equals__LeftAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Greater_Equals__LeftAssignment_1_in_rule__Operation_Greater_Equals__Group__1__Impl2121);
            rule__Operation_Greater_Equals__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Greater_EqualsAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__1__Impl"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1053:1: rule__Operation_Greater_Equals__Group__2 : rule__Operation_Greater_Equals__Group__2__Impl rule__Operation_Greater_Equals__Group__3 ;
    public final void rule__Operation_Greater_Equals__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1057:1: ( rule__Operation_Greater_Equals__Group__2__Impl rule__Operation_Greater_Equals__Group__3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1058:2: rule__Operation_Greater_Equals__Group__2__Impl rule__Operation_Greater_Equals__Group__3
            {
            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__2__Impl_in_rule__Operation_Greater_Equals__Group__22151);
            rule__Operation_Greater_Equals__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__3_in_rule__Operation_Greater_Equals__Group__22154);
            rule__Operation_Greater_Equals__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__2"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__2__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1065:1: rule__Operation_Greater_Equals__Group__2__Impl : ( '>=' ) ;
    public final void rule__Operation_Greater_Equals__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1069:1: ( ( '>=' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1070:1: ( '>=' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1070:1: ( '>=' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1071:1: '>='
            {
             before(grammarAccess.getOperation_Greater_EqualsAccess().getGreaterThanSignEqualsSignKeyword_2()); 
            match(input,18,FOLLOW_18_in_rule__Operation_Greater_Equals__Group__2__Impl2182); 
             after(grammarAccess.getOperation_Greater_EqualsAccess().getGreaterThanSignEqualsSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__2__Impl"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1084:1: rule__Operation_Greater_Equals__Group__3 : rule__Operation_Greater_Equals__Group__3__Impl ;
    public final void rule__Operation_Greater_Equals__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1088:1: ( rule__Operation_Greater_Equals__Group__3__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1089:2: rule__Operation_Greater_Equals__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Greater_Equals__Group__3__Impl_in_rule__Operation_Greater_Equals__Group__32213);
            rule__Operation_Greater_Equals__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__3"


    // $ANTLR start "rule__Operation_Greater_Equals__Group__3__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1095:1: rule__Operation_Greater_Equals__Group__3__Impl : ( ( rule__Operation_Greater_Equals__RightAssignment_3 ) ) ;
    public final void rule__Operation_Greater_Equals__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1099:1: ( ( ( rule__Operation_Greater_Equals__RightAssignment_3 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1100:1: ( ( rule__Operation_Greater_Equals__RightAssignment_3 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1100:1: ( ( rule__Operation_Greater_Equals__RightAssignment_3 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1101:1: ( rule__Operation_Greater_Equals__RightAssignment_3 )
            {
             before(grammarAccess.getOperation_Greater_EqualsAccess().getRightAssignment_3()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1102:1: ( rule__Operation_Greater_Equals__RightAssignment_3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1102:2: rule__Operation_Greater_Equals__RightAssignment_3
            {
            pushFollow(FOLLOW_rule__Operation_Greater_Equals__RightAssignment_3_in_rule__Operation_Greater_Equals__Group__3__Impl2240);
            rule__Operation_Greater_Equals__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Greater_EqualsAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__Group__3__Impl"


    // $ANTLR start "rule__Operation_Greater__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1120:1: rule__Operation_Greater__Group__0 : rule__Operation_Greater__Group__0__Impl rule__Operation_Greater__Group__1 ;
    public final void rule__Operation_Greater__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1124:1: ( rule__Operation_Greater__Group__0__Impl rule__Operation_Greater__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1125:2: rule__Operation_Greater__Group__0__Impl rule__Operation_Greater__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Greater__Group__0__Impl_in_rule__Operation_Greater__Group__02278);
            rule__Operation_Greater__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Greater__Group__1_in_rule__Operation_Greater__Group__02281);
            rule__Operation_Greater__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__0"


    // $ANTLR start "rule__Operation_Greater__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1132:1: rule__Operation_Greater__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Greater__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1136:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1137:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1137:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1138:1: ()
            {
             before(grammarAccess.getOperation_GreaterAccess().getOperation_GreaterAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1139:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1141:1: 
            {
            }

             after(grammarAccess.getOperation_GreaterAccess().getOperation_GreaterAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__0__Impl"


    // $ANTLR start "rule__Operation_Greater__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1151:1: rule__Operation_Greater__Group__1 : rule__Operation_Greater__Group__1__Impl rule__Operation_Greater__Group__2 ;
    public final void rule__Operation_Greater__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1155:1: ( rule__Operation_Greater__Group__1__Impl rule__Operation_Greater__Group__2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1156:2: rule__Operation_Greater__Group__1__Impl rule__Operation_Greater__Group__2
            {
            pushFollow(FOLLOW_rule__Operation_Greater__Group__1__Impl_in_rule__Operation_Greater__Group__12339);
            rule__Operation_Greater__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Greater__Group__2_in_rule__Operation_Greater__Group__12342);
            rule__Operation_Greater__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__1"


    // $ANTLR start "rule__Operation_Greater__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1163:1: rule__Operation_Greater__Group__1__Impl : ( ( rule__Operation_Greater__LeftAssignment_1 ) ) ;
    public final void rule__Operation_Greater__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1167:1: ( ( ( rule__Operation_Greater__LeftAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1168:1: ( ( rule__Operation_Greater__LeftAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1168:1: ( ( rule__Operation_Greater__LeftAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1169:1: ( rule__Operation_Greater__LeftAssignment_1 )
            {
             before(grammarAccess.getOperation_GreaterAccess().getLeftAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1170:1: ( rule__Operation_Greater__LeftAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1170:2: rule__Operation_Greater__LeftAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Greater__LeftAssignment_1_in_rule__Operation_Greater__Group__1__Impl2369);
            rule__Operation_Greater__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_GreaterAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__1__Impl"


    // $ANTLR start "rule__Operation_Greater__Group__2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1180:1: rule__Operation_Greater__Group__2 : rule__Operation_Greater__Group__2__Impl rule__Operation_Greater__Group__3 ;
    public final void rule__Operation_Greater__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1184:1: ( rule__Operation_Greater__Group__2__Impl rule__Operation_Greater__Group__3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1185:2: rule__Operation_Greater__Group__2__Impl rule__Operation_Greater__Group__3
            {
            pushFollow(FOLLOW_rule__Operation_Greater__Group__2__Impl_in_rule__Operation_Greater__Group__22399);
            rule__Operation_Greater__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Greater__Group__3_in_rule__Operation_Greater__Group__22402);
            rule__Operation_Greater__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__2"


    // $ANTLR start "rule__Operation_Greater__Group__2__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1192:1: rule__Operation_Greater__Group__2__Impl : ( '>' ) ;
    public final void rule__Operation_Greater__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1196:1: ( ( '>' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1197:1: ( '>' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1197:1: ( '>' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1198:1: '>'
            {
             before(grammarAccess.getOperation_GreaterAccess().getGreaterThanSignKeyword_2()); 
            match(input,19,FOLLOW_19_in_rule__Operation_Greater__Group__2__Impl2430); 
             after(grammarAccess.getOperation_GreaterAccess().getGreaterThanSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__2__Impl"


    // $ANTLR start "rule__Operation_Greater__Group__3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1211:1: rule__Operation_Greater__Group__3 : rule__Operation_Greater__Group__3__Impl ;
    public final void rule__Operation_Greater__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1215:1: ( rule__Operation_Greater__Group__3__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1216:2: rule__Operation_Greater__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Greater__Group__3__Impl_in_rule__Operation_Greater__Group__32461);
            rule__Operation_Greater__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__3"


    // $ANTLR start "rule__Operation_Greater__Group__3__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1222:1: rule__Operation_Greater__Group__3__Impl : ( ( rule__Operation_Greater__RightAssignment_3 ) ) ;
    public final void rule__Operation_Greater__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1226:1: ( ( ( rule__Operation_Greater__RightAssignment_3 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1227:1: ( ( rule__Operation_Greater__RightAssignment_3 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1227:1: ( ( rule__Operation_Greater__RightAssignment_3 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1228:1: ( rule__Operation_Greater__RightAssignment_3 )
            {
             before(grammarAccess.getOperation_GreaterAccess().getRightAssignment_3()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1229:1: ( rule__Operation_Greater__RightAssignment_3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1229:2: rule__Operation_Greater__RightAssignment_3
            {
            pushFollow(FOLLOW_rule__Operation_Greater__RightAssignment_3_in_rule__Operation_Greater__Group__3__Impl2488);
            rule__Operation_Greater__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOperation_GreaterAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__Group__3__Impl"


    // $ANTLR start "rule__Operation_Not_Equals__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1247:1: rule__Operation_Not_Equals__Group__0 : rule__Operation_Not_Equals__Group__0__Impl rule__Operation_Not_Equals__Group__1 ;
    public final void rule__Operation_Not_Equals__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1251:1: ( rule__Operation_Not_Equals__Group__0__Impl rule__Operation_Not_Equals__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1252:2: rule__Operation_Not_Equals__Group__0__Impl rule__Operation_Not_Equals__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__0__Impl_in_rule__Operation_Not_Equals__Group__02526);
            rule__Operation_Not_Equals__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__1_in_rule__Operation_Not_Equals__Group__02529);
            rule__Operation_Not_Equals__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__0"


    // $ANTLR start "rule__Operation_Not_Equals__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1259:1: rule__Operation_Not_Equals__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Not_Equals__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1263:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1264:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1264:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1265:1: ()
            {
             before(grammarAccess.getOperation_Not_EqualsAccess().getOperation_Not_EqualsAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1266:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1268:1: 
            {
            }

             after(grammarAccess.getOperation_Not_EqualsAccess().getOperation_Not_EqualsAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__0__Impl"


    // $ANTLR start "rule__Operation_Not_Equals__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1278:1: rule__Operation_Not_Equals__Group__1 : rule__Operation_Not_Equals__Group__1__Impl rule__Operation_Not_Equals__Group__2 ;
    public final void rule__Operation_Not_Equals__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1282:1: ( rule__Operation_Not_Equals__Group__1__Impl rule__Operation_Not_Equals__Group__2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1283:2: rule__Operation_Not_Equals__Group__1__Impl rule__Operation_Not_Equals__Group__2
            {
            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__1__Impl_in_rule__Operation_Not_Equals__Group__12587);
            rule__Operation_Not_Equals__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__2_in_rule__Operation_Not_Equals__Group__12590);
            rule__Operation_Not_Equals__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__1"


    // $ANTLR start "rule__Operation_Not_Equals__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1290:1: rule__Operation_Not_Equals__Group__1__Impl : ( ( rule__Operation_Not_Equals__LeftAssignment_1 ) ) ;
    public final void rule__Operation_Not_Equals__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1294:1: ( ( ( rule__Operation_Not_Equals__LeftAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1295:1: ( ( rule__Operation_Not_Equals__LeftAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1295:1: ( ( rule__Operation_Not_Equals__LeftAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1296:1: ( rule__Operation_Not_Equals__LeftAssignment_1 )
            {
             before(grammarAccess.getOperation_Not_EqualsAccess().getLeftAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1297:1: ( rule__Operation_Not_Equals__LeftAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1297:2: rule__Operation_Not_Equals__LeftAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Not_Equals__LeftAssignment_1_in_rule__Operation_Not_Equals__Group__1__Impl2617);
            rule__Operation_Not_Equals__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Not_EqualsAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__1__Impl"


    // $ANTLR start "rule__Operation_Not_Equals__Group__2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1307:1: rule__Operation_Not_Equals__Group__2 : rule__Operation_Not_Equals__Group__2__Impl rule__Operation_Not_Equals__Group__3 ;
    public final void rule__Operation_Not_Equals__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1311:1: ( rule__Operation_Not_Equals__Group__2__Impl rule__Operation_Not_Equals__Group__3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1312:2: rule__Operation_Not_Equals__Group__2__Impl rule__Operation_Not_Equals__Group__3
            {
            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__2__Impl_in_rule__Operation_Not_Equals__Group__22647);
            rule__Operation_Not_Equals__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__3_in_rule__Operation_Not_Equals__Group__22650);
            rule__Operation_Not_Equals__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__2"


    // $ANTLR start "rule__Operation_Not_Equals__Group__2__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1319:1: rule__Operation_Not_Equals__Group__2__Impl : ( '!=' ) ;
    public final void rule__Operation_Not_Equals__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1323:1: ( ( '!=' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1324:1: ( '!=' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1324:1: ( '!=' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1325:1: '!='
            {
             before(grammarAccess.getOperation_Not_EqualsAccess().getExclamationMarkEqualsSignKeyword_2()); 
            match(input,20,FOLLOW_20_in_rule__Operation_Not_Equals__Group__2__Impl2678); 
             after(grammarAccess.getOperation_Not_EqualsAccess().getExclamationMarkEqualsSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__2__Impl"


    // $ANTLR start "rule__Operation_Not_Equals__Group__3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1338:1: rule__Operation_Not_Equals__Group__3 : rule__Operation_Not_Equals__Group__3__Impl ;
    public final void rule__Operation_Not_Equals__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1342:1: ( rule__Operation_Not_Equals__Group__3__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1343:2: rule__Operation_Not_Equals__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Not_Equals__Group__3__Impl_in_rule__Operation_Not_Equals__Group__32709);
            rule__Operation_Not_Equals__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__3"


    // $ANTLR start "rule__Operation_Not_Equals__Group__3__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1349:1: rule__Operation_Not_Equals__Group__3__Impl : ( ( rule__Operation_Not_Equals__RightAssignment_3 ) ) ;
    public final void rule__Operation_Not_Equals__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1353:1: ( ( ( rule__Operation_Not_Equals__RightAssignment_3 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1354:1: ( ( rule__Operation_Not_Equals__RightAssignment_3 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1354:1: ( ( rule__Operation_Not_Equals__RightAssignment_3 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1355:1: ( rule__Operation_Not_Equals__RightAssignment_3 )
            {
             before(grammarAccess.getOperation_Not_EqualsAccess().getRightAssignment_3()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1356:1: ( rule__Operation_Not_Equals__RightAssignment_3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1356:2: rule__Operation_Not_Equals__RightAssignment_3
            {
            pushFollow(FOLLOW_rule__Operation_Not_Equals__RightAssignment_3_in_rule__Operation_Not_Equals__Group__3__Impl2736);
            rule__Operation_Not_Equals__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOperation_Not_EqualsAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__Group__3__Impl"


    // $ANTLR start "rule__Operation_Equals__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1374:1: rule__Operation_Equals__Group__0 : rule__Operation_Equals__Group__0__Impl rule__Operation_Equals__Group__1 ;
    public final void rule__Operation_Equals__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1378:1: ( rule__Operation_Equals__Group__0__Impl rule__Operation_Equals__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1379:2: rule__Operation_Equals__Group__0__Impl rule__Operation_Equals__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Equals__Group__0__Impl_in_rule__Operation_Equals__Group__02774);
            rule__Operation_Equals__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Equals__Group__1_in_rule__Operation_Equals__Group__02777);
            rule__Operation_Equals__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__0"


    // $ANTLR start "rule__Operation_Equals__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1386:1: rule__Operation_Equals__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Equals__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1390:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1391:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1391:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1392:1: ()
            {
             before(grammarAccess.getOperation_EqualsAccess().getOperation_EqualsAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1393:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1395:1: 
            {
            }

             after(grammarAccess.getOperation_EqualsAccess().getOperation_EqualsAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__0__Impl"


    // $ANTLR start "rule__Operation_Equals__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1405:1: rule__Operation_Equals__Group__1 : rule__Operation_Equals__Group__1__Impl rule__Operation_Equals__Group__2 ;
    public final void rule__Operation_Equals__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1409:1: ( rule__Operation_Equals__Group__1__Impl rule__Operation_Equals__Group__2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1410:2: rule__Operation_Equals__Group__1__Impl rule__Operation_Equals__Group__2
            {
            pushFollow(FOLLOW_rule__Operation_Equals__Group__1__Impl_in_rule__Operation_Equals__Group__12835);
            rule__Operation_Equals__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Equals__Group__2_in_rule__Operation_Equals__Group__12838);
            rule__Operation_Equals__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__1"


    // $ANTLR start "rule__Operation_Equals__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1417:1: rule__Operation_Equals__Group__1__Impl : ( ( rule__Operation_Equals__LeftAssignment_1 ) ) ;
    public final void rule__Operation_Equals__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1421:1: ( ( ( rule__Operation_Equals__LeftAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1422:1: ( ( rule__Operation_Equals__LeftAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1422:1: ( ( rule__Operation_Equals__LeftAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1423:1: ( rule__Operation_Equals__LeftAssignment_1 )
            {
             before(grammarAccess.getOperation_EqualsAccess().getLeftAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1424:1: ( rule__Operation_Equals__LeftAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1424:2: rule__Operation_Equals__LeftAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Equals__LeftAssignment_1_in_rule__Operation_Equals__Group__1__Impl2865);
            rule__Operation_Equals__LeftAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_EqualsAccess().getLeftAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__1__Impl"


    // $ANTLR start "rule__Operation_Equals__Group__2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1434:1: rule__Operation_Equals__Group__2 : rule__Operation_Equals__Group__2__Impl rule__Operation_Equals__Group__3 ;
    public final void rule__Operation_Equals__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1438:1: ( rule__Operation_Equals__Group__2__Impl rule__Operation_Equals__Group__3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1439:2: rule__Operation_Equals__Group__2__Impl rule__Operation_Equals__Group__3
            {
            pushFollow(FOLLOW_rule__Operation_Equals__Group__2__Impl_in_rule__Operation_Equals__Group__22895);
            rule__Operation_Equals__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Equals__Group__3_in_rule__Operation_Equals__Group__22898);
            rule__Operation_Equals__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__2"


    // $ANTLR start "rule__Operation_Equals__Group__2__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1446:1: rule__Operation_Equals__Group__2__Impl : ( '==' ) ;
    public final void rule__Operation_Equals__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1450:1: ( ( '==' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1451:1: ( '==' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1451:1: ( '==' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1452:1: '=='
            {
             before(grammarAccess.getOperation_EqualsAccess().getEqualsSignEqualsSignKeyword_2()); 
            match(input,21,FOLLOW_21_in_rule__Operation_Equals__Group__2__Impl2926); 
             after(grammarAccess.getOperation_EqualsAccess().getEqualsSignEqualsSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__2__Impl"


    // $ANTLR start "rule__Operation_Equals__Group__3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1465:1: rule__Operation_Equals__Group__3 : rule__Operation_Equals__Group__3__Impl ;
    public final void rule__Operation_Equals__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1469:1: ( rule__Operation_Equals__Group__3__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1470:2: rule__Operation_Equals__Group__3__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Equals__Group__3__Impl_in_rule__Operation_Equals__Group__32957);
            rule__Operation_Equals__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__3"


    // $ANTLR start "rule__Operation_Equals__Group__3__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1476:1: rule__Operation_Equals__Group__3__Impl : ( ( rule__Operation_Equals__RightAssignment_3 ) ) ;
    public final void rule__Operation_Equals__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1480:1: ( ( ( rule__Operation_Equals__RightAssignment_3 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1481:1: ( ( rule__Operation_Equals__RightAssignment_3 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1481:1: ( ( rule__Operation_Equals__RightAssignment_3 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1482:1: ( rule__Operation_Equals__RightAssignment_3 )
            {
             before(grammarAccess.getOperation_EqualsAccess().getRightAssignment_3()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1483:1: ( rule__Operation_Equals__RightAssignment_3 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1483:2: rule__Operation_Equals__RightAssignment_3
            {
            pushFollow(FOLLOW_rule__Operation_Equals__RightAssignment_3_in_rule__Operation_Equals__Group__3__Impl2984);
            rule__Operation_Equals__RightAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOperation_EqualsAccess().getRightAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__Group__3__Impl"


    // $ANTLR start "rule__Operation_Unary__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1501:1: rule__Operation_Unary__Group__0 : rule__Operation_Unary__Group__0__Impl rule__Operation_Unary__Group__1 ;
    public final void rule__Operation_Unary__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1505:1: ( rule__Operation_Unary__Group__0__Impl rule__Operation_Unary__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1506:2: rule__Operation_Unary__Group__0__Impl rule__Operation_Unary__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_Unary__Group__0__Impl_in_rule__Operation_Unary__Group__03022);
            rule__Operation_Unary__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_Unary__Group__1_in_rule__Operation_Unary__Group__03025);
            rule__Operation_Unary__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Unary__Group__0"


    // $ANTLR start "rule__Operation_Unary__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1513:1: rule__Operation_Unary__Group__0__Impl : ( () ) ;
    public final void rule__Operation_Unary__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1517:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1518:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1518:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1519:1: ()
            {
             before(grammarAccess.getOperation_UnaryAccess().getOperation_UnaryAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1520:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1522:1: 
            {
            }

             after(grammarAccess.getOperation_UnaryAccess().getOperation_UnaryAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Unary__Group__0__Impl"


    // $ANTLR start "rule__Operation_Unary__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1532:1: rule__Operation_Unary__Group__1 : rule__Operation_Unary__Group__1__Impl ;
    public final void rule__Operation_Unary__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1536:1: ( rule__Operation_Unary__Group__1__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1537:2: rule__Operation_Unary__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Operation_Unary__Group__1__Impl_in_rule__Operation_Unary__Group__13083);
            rule__Operation_Unary__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Unary__Group__1"


    // $ANTLR start "rule__Operation_Unary__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1543:1: rule__Operation_Unary__Group__1__Impl : ( ( rule__Operation_Unary__ValueAssignment_1 ) ) ;
    public final void rule__Operation_Unary__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1547:1: ( ( ( rule__Operation_Unary__ValueAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1548:1: ( ( rule__Operation_Unary__ValueAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1548:1: ( ( rule__Operation_Unary__ValueAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1549:1: ( rule__Operation_Unary__ValueAssignment_1 )
            {
             before(grammarAccess.getOperation_UnaryAccess().getValueAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1550:1: ( rule__Operation_Unary__ValueAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1550:2: rule__Operation_Unary__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Operation_Unary__ValueAssignment_1_in_rule__Operation_Unary__Group__1__Impl3110);
            rule__Operation_Unary__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOperation_UnaryAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Unary__Group__1__Impl"


    // $ANTLR start "rule__Operation_NotUnary__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1564:1: rule__Operation_NotUnary__Group__0 : rule__Operation_NotUnary__Group__0__Impl rule__Operation_NotUnary__Group__1 ;
    public final void rule__Operation_NotUnary__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1568:1: ( rule__Operation_NotUnary__Group__0__Impl rule__Operation_NotUnary__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1569:2: rule__Operation_NotUnary__Group__0__Impl rule__Operation_NotUnary__Group__1
            {
            pushFollow(FOLLOW_rule__Operation_NotUnary__Group__0__Impl_in_rule__Operation_NotUnary__Group__03144);
            rule__Operation_NotUnary__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_NotUnary__Group__1_in_rule__Operation_NotUnary__Group__03147);
            rule__Operation_NotUnary__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_NotUnary__Group__0"


    // $ANTLR start "rule__Operation_NotUnary__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1576:1: rule__Operation_NotUnary__Group__0__Impl : ( () ) ;
    public final void rule__Operation_NotUnary__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1580:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1581:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1581:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1582:1: ()
            {
             before(grammarAccess.getOperation_NotUnaryAccess().getOperation_NotUnaryAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1583:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1585:1: 
            {
            }

             after(grammarAccess.getOperation_NotUnaryAccess().getOperation_NotUnaryAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_NotUnary__Group__0__Impl"


    // $ANTLR start "rule__Operation_NotUnary__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1595:1: rule__Operation_NotUnary__Group__1 : rule__Operation_NotUnary__Group__1__Impl rule__Operation_NotUnary__Group__2 ;
    public final void rule__Operation_NotUnary__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1599:1: ( rule__Operation_NotUnary__Group__1__Impl rule__Operation_NotUnary__Group__2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1600:2: rule__Operation_NotUnary__Group__1__Impl rule__Operation_NotUnary__Group__2
            {
            pushFollow(FOLLOW_rule__Operation_NotUnary__Group__1__Impl_in_rule__Operation_NotUnary__Group__13205);
            rule__Operation_NotUnary__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Operation_NotUnary__Group__2_in_rule__Operation_NotUnary__Group__13208);
            rule__Operation_NotUnary__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_NotUnary__Group__1"


    // $ANTLR start "rule__Operation_NotUnary__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1607:1: rule__Operation_NotUnary__Group__1__Impl : ( '!' ) ;
    public final void rule__Operation_NotUnary__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1611:1: ( ( '!' ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1612:1: ( '!' )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1612:1: ( '!' )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1613:1: '!'
            {
             before(grammarAccess.getOperation_NotUnaryAccess().getExclamationMarkKeyword_1()); 
            match(input,22,FOLLOW_22_in_rule__Operation_NotUnary__Group__1__Impl3236); 
             after(grammarAccess.getOperation_NotUnaryAccess().getExclamationMarkKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_NotUnary__Group__1__Impl"


    // $ANTLR start "rule__Operation_NotUnary__Group__2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1626:1: rule__Operation_NotUnary__Group__2 : rule__Operation_NotUnary__Group__2__Impl ;
    public final void rule__Operation_NotUnary__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1630:1: ( rule__Operation_NotUnary__Group__2__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1631:2: rule__Operation_NotUnary__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Operation_NotUnary__Group__2__Impl_in_rule__Operation_NotUnary__Group__23267);
            rule__Operation_NotUnary__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_NotUnary__Group__2"


    // $ANTLR start "rule__Operation_NotUnary__Group__2__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1637:1: rule__Operation_NotUnary__Group__2__Impl : ( ( rule__Operation_NotUnary__ValueAssignment_2 ) ) ;
    public final void rule__Operation_NotUnary__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1641:1: ( ( ( rule__Operation_NotUnary__ValueAssignment_2 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1642:1: ( ( rule__Operation_NotUnary__ValueAssignment_2 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1642:1: ( ( rule__Operation_NotUnary__ValueAssignment_2 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1643:1: ( rule__Operation_NotUnary__ValueAssignment_2 )
            {
             before(grammarAccess.getOperation_NotUnaryAccess().getValueAssignment_2()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1644:1: ( rule__Operation_NotUnary__ValueAssignment_2 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1644:2: rule__Operation_NotUnary__ValueAssignment_2
            {
            pushFollow(FOLLOW_rule__Operation_NotUnary__ValueAssignment_2_in_rule__Operation_NotUnary__Group__2__Impl3294);
            rule__Operation_NotUnary__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getOperation_NotUnaryAccess().getValueAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_NotUnary__Group__2__Impl"


    // $ANTLR start "rule__Expression_Double__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1660:1: rule__Expression_Double__Group__0 : rule__Expression_Double__Group__0__Impl rule__Expression_Double__Group__1 ;
    public final void rule__Expression_Double__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1664:1: ( rule__Expression_Double__Group__0__Impl rule__Expression_Double__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1665:2: rule__Expression_Double__Group__0__Impl rule__Expression_Double__Group__1
            {
            pushFollow(FOLLOW_rule__Expression_Double__Group__0__Impl_in_rule__Expression_Double__Group__03330);
            rule__Expression_Double__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression_Double__Group__1_in_rule__Expression_Double__Group__03333);
            rule__Expression_Double__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Double__Group__0"


    // $ANTLR start "rule__Expression_Double__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1672:1: rule__Expression_Double__Group__0__Impl : ( () ) ;
    public final void rule__Expression_Double__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1676:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1677:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1677:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1678:1: ()
            {
             before(grammarAccess.getExpression_DoubleAccess().getExpression_DoubleAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1679:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1681:1: 
            {
            }

             after(grammarAccess.getExpression_DoubleAccess().getExpression_DoubleAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Double__Group__0__Impl"


    // $ANTLR start "rule__Expression_Double__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1691:1: rule__Expression_Double__Group__1 : rule__Expression_Double__Group__1__Impl ;
    public final void rule__Expression_Double__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1695:1: ( rule__Expression_Double__Group__1__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1696:2: rule__Expression_Double__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression_Double__Group__1__Impl_in_rule__Expression_Double__Group__13391);
            rule__Expression_Double__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Double__Group__1"


    // $ANTLR start "rule__Expression_Double__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1702:1: rule__Expression_Double__Group__1__Impl : ( ( rule__Expression_Double__ValueAssignment_1 ) ) ;
    public final void rule__Expression_Double__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1706:1: ( ( ( rule__Expression_Double__ValueAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1707:1: ( ( rule__Expression_Double__ValueAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1707:1: ( ( rule__Expression_Double__ValueAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1708:1: ( rule__Expression_Double__ValueAssignment_1 )
            {
             before(grammarAccess.getExpression_DoubleAccess().getValueAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1709:1: ( rule__Expression_Double__ValueAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1709:2: rule__Expression_Double__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Expression_Double__ValueAssignment_1_in_rule__Expression_Double__Group__1__Impl3418);
            rule__Expression_Double__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getExpression_DoubleAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Double__Group__1__Impl"


    // $ANTLR start "rule__Expression_Integer__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1723:1: rule__Expression_Integer__Group__0 : rule__Expression_Integer__Group__0__Impl rule__Expression_Integer__Group__1 ;
    public final void rule__Expression_Integer__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1727:1: ( rule__Expression_Integer__Group__0__Impl rule__Expression_Integer__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1728:2: rule__Expression_Integer__Group__0__Impl rule__Expression_Integer__Group__1
            {
            pushFollow(FOLLOW_rule__Expression_Integer__Group__0__Impl_in_rule__Expression_Integer__Group__03452);
            rule__Expression_Integer__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression_Integer__Group__1_in_rule__Expression_Integer__Group__03455);
            rule__Expression_Integer__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Integer__Group__0"


    // $ANTLR start "rule__Expression_Integer__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1735:1: rule__Expression_Integer__Group__0__Impl : ( () ) ;
    public final void rule__Expression_Integer__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1739:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1740:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1740:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1741:1: ()
            {
             before(grammarAccess.getExpression_IntegerAccess().getExpression_IntegerAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1742:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1744:1: 
            {
            }

             after(grammarAccess.getExpression_IntegerAccess().getExpression_IntegerAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Integer__Group__0__Impl"


    // $ANTLR start "rule__Expression_Integer__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1754:1: rule__Expression_Integer__Group__1 : rule__Expression_Integer__Group__1__Impl ;
    public final void rule__Expression_Integer__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1758:1: ( rule__Expression_Integer__Group__1__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1759:2: rule__Expression_Integer__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression_Integer__Group__1__Impl_in_rule__Expression_Integer__Group__13513);
            rule__Expression_Integer__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Integer__Group__1"


    // $ANTLR start "rule__Expression_Integer__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1765:1: rule__Expression_Integer__Group__1__Impl : ( ( rule__Expression_Integer__ValueAssignment_1 ) ) ;
    public final void rule__Expression_Integer__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1769:1: ( ( ( rule__Expression_Integer__ValueAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1770:1: ( ( rule__Expression_Integer__ValueAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1770:1: ( ( rule__Expression_Integer__ValueAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1771:1: ( rule__Expression_Integer__ValueAssignment_1 )
            {
             before(grammarAccess.getExpression_IntegerAccess().getValueAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1772:1: ( rule__Expression_Integer__ValueAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1772:2: rule__Expression_Integer__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Expression_Integer__ValueAssignment_1_in_rule__Expression_Integer__Group__1__Impl3540);
            rule__Expression_Integer__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getExpression_IntegerAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Integer__Group__1__Impl"


    // $ANTLR start "rule__Expression_String__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1786:1: rule__Expression_String__Group__0 : rule__Expression_String__Group__0__Impl rule__Expression_String__Group__1 ;
    public final void rule__Expression_String__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1790:1: ( rule__Expression_String__Group__0__Impl rule__Expression_String__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1791:2: rule__Expression_String__Group__0__Impl rule__Expression_String__Group__1
            {
            pushFollow(FOLLOW_rule__Expression_String__Group__0__Impl_in_rule__Expression_String__Group__03574);
            rule__Expression_String__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression_String__Group__1_in_rule__Expression_String__Group__03577);
            rule__Expression_String__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_String__Group__0"


    // $ANTLR start "rule__Expression_String__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1798:1: rule__Expression_String__Group__0__Impl : ( () ) ;
    public final void rule__Expression_String__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1802:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1803:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1803:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1804:1: ()
            {
             before(grammarAccess.getExpression_StringAccess().getExpression_StringAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1805:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1807:1: 
            {
            }

             after(grammarAccess.getExpression_StringAccess().getExpression_StringAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_String__Group__0__Impl"


    // $ANTLR start "rule__Expression_String__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1817:1: rule__Expression_String__Group__1 : rule__Expression_String__Group__1__Impl ;
    public final void rule__Expression_String__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1821:1: ( rule__Expression_String__Group__1__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1822:2: rule__Expression_String__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression_String__Group__1__Impl_in_rule__Expression_String__Group__13635);
            rule__Expression_String__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_String__Group__1"


    // $ANTLR start "rule__Expression_String__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1828:1: rule__Expression_String__Group__1__Impl : ( ( rule__Expression_String__ValueAssignment_1 ) ) ;
    public final void rule__Expression_String__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1832:1: ( ( ( rule__Expression_String__ValueAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1833:1: ( ( rule__Expression_String__ValueAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1833:1: ( ( rule__Expression_String__ValueAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1834:1: ( rule__Expression_String__ValueAssignment_1 )
            {
             before(grammarAccess.getExpression_StringAccess().getValueAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1835:1: ( rule__Expression_String__ValueAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1835:2: rule__Expression_String__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Expression_String__ValueAssignment_1_in_rule__Expression_String__Group__1__Impl3662);
            rule__Expression_String__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getExpression_StringAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_String__Group__1__Impl"


    // $ANTLR start "rule__Expression_ProcessRef__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1849:1: rule__Expression_ProcessRef__Group__0 : rule__Expression_ProcessRef__Group__0__Impl rule__Expression_ProcessRef__Group__1 ;
    public final void rule__Expression_ProcessRef__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1853:1: ( rule__Expression_ProcessRef__Group__0__Impl rule__Expression_ProcessRef__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1854:2: rule__Expression_ProcessRef__Group__0__Impl rule__Expression_ProcessRef__Group__1
            {
            pushFollow(FOLLOW_rule__Expression_ProcessRef__Group__0__Impl_in_rule__Expression_ProcessRef__Group__03696);
            rule__Expression_ProcessRef__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression_ProcessRef__Group__1_in_rule__Expression_ProcessRef__Group__03699);
            rule__Expression_ProcessRef__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_ProcessRef__Group__0"


    // $ANTLR start "rule__Expression_ProcessRef__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1861:1: rule__Expression_ProcessRef__Group__0__Impl : ( () ) ;
    public final void rule__Expression_ProcessRef__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1865:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1866:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1866:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1867:1: ()
            {
             before(grammarAccess.getExpression_ProcessRefAccess().getExpression_ProcessRefAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1868:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1870:1: 
            {
            }

             after(grammarAccess.getExpression_ProcessRefAccess().getExpression_ProcessRefAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_ProcessRef__Group__0__Impl"


    // $ANTLR start "rule__Expression_ProcessRef__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1880:1: rule__Expression_ProcessRef__Group__1 : rule__Expression_ProcessRef__Group__1__Impl ;
    public final void rule__Expression_ProcessRef__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1884:1: ( rule__Expression_ProcessRef__Group__1__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1885:2: rule__Expression_ProcessRef__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression_ProcessRef__Group__1__Impl_in_rule__Expression_ProcessRef__Group__13757);
            rule__Expression_ProcessRef__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_ProcessRef__Group__1"


    // $ANTLR start "rule__Expression_ProcessRef__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1891:1: rule__Expression_ProcessRef__Group__1__Impl : ( ( rule__Expression_ProcessRef__ValueAssignment_1 ) ) ;
    public final void rule__Expression_ProcessRef__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1895:1: ( ( ( rule__Expression_ProcessRef__ValueAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1896:1: ( ( rule__Expression_ProcessRef__ValueAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1896:1: ( ( rule__Expression_ProcessRef__ValueAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1897:1: ( rule__Expression_ProcessRef__ValueAssignment_1 )
            {
             before(grammarAccess.getExpression_ProcessRefAccess().getValueAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1898:1: ( rule__Expression_ProcessRef__ValueAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1898:2: rule__Expression_ProcessRef__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Expression_ProcessRef__ValueAssignment_1_in_rule__Expression_ProcessRef__Group__1__Impl3784);
            rule__Expression_ProcessRef__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getExpression_ProcessRefAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_ProcessRef__Group__1__Impl"


    // $ANTLR start "rule__Expression_Boolean__Group__0"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1912:1: rule__Expression_Boolean__Group__0 : rule__Expression_Boolean__Group__0__Impl rule__Expression_Boolean__Group__1 ;
    public final void rule__Expression_Boolean__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1916:1: ( rule__Expression_Boolean__Group__0__Impl rule__Expression_Boolean__Group__1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1917:2: rule__Expression_Boolean__Group__0__Impl rule__Expression_Boolean__Group__1
            {
            pushFollow(FOLLOW_rule__Expression_Boolean__Group__0__Impl_in_rule__Expression_Boolean__Group__03818);
            rule__Expression_Boolean__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Expression_Boolean__Group__1_in_rule__Expression_Boolean__Group__03821);
            rule__Expression_Boolean__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Boolean__Group__0"


    // $ANTLR start "rule__Expression_Boolean__Group__0__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1924:1: rule__Expression_Boolean__Group__0__Impl : ( () ) ;
    public final void rule__Expression_Boolean__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1928:1: ( ( () ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1929:1: ( () )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1929:1: ( () )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1930:1: ()
            {
             before(grammarAccess.getExpression_BooleanAccess().getExpression_BooleanAction_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1931:1: ()
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1933:1: 
            {
            }

             after(grammarAccess.getExpression_BooleanAccess().getExpression_BooleanAction_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Boolean__Group__0__Impl"


    // $ANTLR start "rule__Expression_Boolean__Group__1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1943:1: rule__Expression_Boolean__Group__1 : rule__Expression_Boolean__Group__1__Impl ;
    public final void rule__Expression_Boolean__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1947:1: ( rule__Expression_Boolean__Group__1__Impl )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1948:2: rule__Expression_Boolean__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__Expression_Boolean__Group__1__Impl_in_rule__Expression_Boolean__Group__13879);
            rule__Expression_Boolean__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Boolean__Group__1"


    // $ANTLR start "rule__Expression_Boolean__Group__1__Impl"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1954:1: rule__Expression_Boolean__Group__1__Impl : ( ( rule__Expression_Boolean__ValueAssignment_1 ) ) ;
    public final void rule__Expression_Boolean__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1958:1: ( ( ( rule__Expression_Boolean__ValueAssignment_1 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1959:1: ( ( rule__Expression_Boolean__ValueAssignment_1 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1959:1: ( ( rule__Expression_Boolean__ValueAssignment_1 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1960:1: ( rule__Expression_Boolean__ValueAssignment_1 )
            {
             before(grammarAccess.getExpression_BooleanAccess().getValueAssignment_1()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1961:1: ( rule__Expression_Boolean__ValueAssignment_1 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1961:2: rule__Expression_Boolean__ValueAssignment_1
            {
            pushFollow(FOLLOW_rule__Expression_Boolean__ValueAssignment_1_in_rule__Expression_Boolean__Group__1__Impl3906);
            rule__Expression_Boolean__ValueAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getExpression_BooleanAccess().getValueAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Boolean__Group__1__Impl"


    // $ANTLR start "rule__Operation_Compare__OpAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1976:1: rule__Operation_Compare__OpAssignment_1 : ( ( rule__Operation_Compare__OpAlternatives_1_0 ) ) ;
    public final void rule__Operation_Compare__OpAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1980:1: ( ( ( rule__Operation_Compare__OpAlternatives_1_0 ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1981:1: ( ( rule__Operation_Compare__OpAlternatives_1_0 ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1981:1: ( ( rule__Operation_Compare__OpAlternatives_1_0 ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1982:1: ( rule__Operation_Compare__OpAlternatives_1_0 )
            {
             before(grammarAccess.getOperation_CompareAccess().getOpAlternatives_1_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1983:1: ( rule__Operation_Compare__OpAlternatives_1_0 )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1983:2: rule__Operation_Compare__OpAlternatives_1_0
            {
            pushFollow(FOLLOW_rule__Operation_Compare__OpAlternatives_1_0_in_rule__Operation_Compare__OpAssignment_13945);
            rule__Operation_Compare__OpAlternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getOperation_CompareAccess().getOpAlternatives_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Compare__OpAssignment_1"


    // $ANTLR start "rule__Operation_Less_Equals__LeftAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1992:1: rule__Operation_Less_Equals__LeftAssignment_1 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Less_Equals__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1996:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1997:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1997:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:1998:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_Less_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less_Equals__LeftAssignment_13978);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_Less_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__LeftAssignment_1"


    // $ANTLR start "rule__Operation_Less_Equals__RightAssignment_3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2007:1: rule__Operation_Less_Equals__RightAssignment_3 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Less_Equals__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2011:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2012:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2012:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2013:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_Less_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less_Equals__RightAssignment_34009);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_Less_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less_Equals__RightAssignment_3"


    // $ANTLR start "rule__Operation_Less__LeftAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2022:1: rule__Operation_Less__LeftAssignment_1 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Less__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2026:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2027:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2027:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2028:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_LessAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less__LeftAssignment_14040);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_LessAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__LeftAssignment_1"


    // $ANTLR start "rule__Operation_Less__RightAssignment_3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2037:1: rule__Operation_Less__RightAssignment_3 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Less__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2041:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2042:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2042:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2043:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_LessAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less__RightAssignment_34071);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_LessAccess().getRightExpression_TerminalParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Less__RightAssignment_3"


    // $ANTLR start "rule__Operation_Greater_Equals__LeftAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2052:1: rule__Operation_Greater_Equals__LeftAssignment_1 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Greater_Equals__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2056:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2057:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2057:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2058:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_Greater_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater_Equals__LeftAssignment_14102);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_Greater_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__LeftAssignment_1"


    // $ANTLR start "rule__Operation_Greater_Equals__RightAssignment_3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2067:1: rule__Operation_Greater_Equals__RightAssignment_3 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Greater_Equals__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2071:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2072:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2072:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2073:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_Greater_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater_Equals__RightAssignment_34133);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_Greater_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater_Equals__RightAssignment_3"


    // $ANTLR start "rule__Operation_Greater__LeftAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2082:1: rule__Operation_Greater__LeftAssignment_1 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Greater__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2086:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2087:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2087:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2088:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_GreaterAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater__LeftAssignment_14164);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_GreaterAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__LeftAssignment_1"


    // $ANTLR start "rule__Operation_Greater__RightAssignment_3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2097:1: rule__Operation_Greater__RightAssignment_3 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Greater__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2101:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2102:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2102:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2103:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_GreaterAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater__RightAssignment_34195);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_GreaterAccess().getRightExpression_TerminalParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Greater__RightAssignment_3"


    // $ANTLR start "rule__Operation_Not_Equals__LeftAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2112:1: rule__Operation_Not_Equals__LeftAssignment_1 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Not_Equals__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2116:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2117:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2117:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2118:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_Not_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Not_Equals__LeftAssignment_14226);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_Not_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__LeftAssignment_1"


    // $ANTLR start "rule__Operation_Not_Equals__RightAssignment_3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2127:1: rule__Operation_Not_Equals__RightAssignment_3 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Not_Equals__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2131:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2132:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2132:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2133:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_Not_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Not_Equals__RightAssignment_34257);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_Not_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Not_Equals__RightAssignment_3"


    // $ANTLR start "rule__Operation_Equals__LeftAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2142:1: rule__Operation_Equals__LeftAssignment_1 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Equals__LeftAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2146:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2147:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2147:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2148:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Equals__LeftAssignment_14288);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_EqualsAccess().getLeftExpression_TerminalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__LeftAssignment_1"


    // $ANTLR start "rule__Operation_Equals__RightAssignment_3"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2157:1: rule__Operation_Equals__RightAssignment_3 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Equals__RightAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2161:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2162:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2162:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2163:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Equals__RightAssignment_34319);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_EqualsAccess().getRightExpression_TerminalParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Equals__RightAssignment_3"


    // $ANTLR start "rule__Operation_Unary__ValueAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2172:1: rule__Operation_Unary__ValueAssignment_1 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_Unary__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2176:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2177:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2177:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2178:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_UnaryAccess().getValueExpression_TerminalParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_Unary__ValueAssignment_14350);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_UnaryAccess().getValueExpression_TerminalParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_Unary__ValueAssignment_1"


    // $ANTLR start "rule__Operation_NotUnary__ValueAssignment_2"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2187:1: rule__Operation_NotUnary__ValueAssignment_2 : ( ruleExpression_Terminal ) ;
    public final void rule__Operation_NotUnary__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2191:1: ( ( ruleExpression_Terminal ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2192:1: ( ruleExpression_Terminal )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2192:1: ( ruleExpression_Terminal )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2193:1: ruleExpression_Terminal
            {
             before(grammarAccess.getOperation_NotUnaryAccess().getValueExpression_TerminalParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleExpression_Terminal_in_rule__Operation_NotUnary__ValueAssignment_24381);
            ruleExpression_Terminal();

            state._fsp--;

             after(grammarAccess.getOperation_NotUnaryAccess().getValueExpression_TerminalParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Operation_NotUnary__ValueAssignment_2"


    // $ANTLR start "rule__Expression_Double__ValueAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2202:1: rule__Expression_Double__ValueAssignment_1 : ( RULE_DOUBLE ) ;
    public final void rule__Expression_Double__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2206:1: ( ( RULE_DOUBLE ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2207:1: ( RULE_DOUBLE )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2207:1: ( RULE_DOUBLE )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2208:1: RULE_DOUBLE
            {
             before(grammarAccess.getExpression_DoubleAccess().getValueDOUBLETerminalRuleCall_1_0()); 
            match(input,RULE_DOUBLE,FOLLOW_RULE_DOUBLE_in_rule__Expression_Double__ValueAssignment_14412); 
             after(grammarAccess.getExpression_DoubleAccess().getValueDOUBLETerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Double__ValueAssignment_1"


    // $ANTLR start "rule__Expression_Integer__ValueAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2217:1: rule__Expression_Integer__ValueAssignment_1 : ( RULE_LONG ) ;
    public final void rule__Expression_Integer__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2221:1: ( ( RULE_LONG ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2222:1: ( RULE_LONG )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2222:1: ( RULE_LONG )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2223:1: RULE_LONG
            {
             before(grammarAccess.getExpression_IntegerAccess().getValueLONGTerminalRuleCall_1_0()); 
            match(input,RULE_LONG,FOLLOW_RULE_LONG_in_rule__Expression_Integer__ValueAssignment_14443); 
             after(grammarAccess.getExpression_IntegerAccess().getValueLONGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Integer__ValueAssignment_1"


    // $ANTLR start "rule__Expression_String__ValueAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2232:1: rule__Expression_String__ValueAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Expression_String__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2236:1: ( ( RULE_STRING ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2237:1: ( RULE_STRING )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2237:1: ( RULE_STRING )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2238:1: RULE_STRING
            {
             before(grammarAccess.getExpression_StringAccess().getValueSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_RULE_STRING_in_rule__Expression_String__ValueAssignment_14474); 
             after(grammarAccess.getExpression_StringAccess().getValueSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_String__ValueAssignment_1"


    // $ANTLR start "rule__Expression_ProcessRef__ValueAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2247:1: rule__Expression_ProcessRef__ValueAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__Expression_ProcessRef__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2251:1: ( ( ( RULE_ID ) ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2252:1: ( ( RULE_ID ) )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2252:1: ( ( RULE_ID ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2253:1: ( RULE_ID )
            {
             before(grammarAccess.getExpression_ProcessRefAccess().getValueEObjectCrossReference_1_0()); 
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2254:1: ( RULE_ID )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2255:1: RULE_ID
            {
             before(grammarAccess.getExpression_ProcessRefAccess().getValueEObjectIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__Expression_ProcessRef__ValueAssignment_14509); 
             after(grammarAccess.getExpression_ProcessRefAccess().getValueEObjectIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getExpression_ProcessRefAccess().getValueEObjectCrossReference_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_ProcessRef__ValueAssignment_1"


    // $ANTLR start "rule__Expression_Boolean__ValueAssignment_1"
    // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2266:1: rule__Expression_Boolean__ValueAssignment_1 : ( RULE_BOOLEAN ) ;
    public final void rule__Expression_Boolean__ValueAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2270:1: ( ( RULE_BOOLEAN ) )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2271:1: ( RULE_BOOLEAN )
            {
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2271:1: ( RULE_BOOLEAN )
            // ../org.bonitasoft.studio.condition.ui/src-gen/org/bonitasoft/studio/condition/ui/contentassist/antlr/internal/InternalConditionModel.g:2272:1: RULE_BOOLEAN
            {
             before(grammarAccess.getExpression_BooleanAccess().getValueBOOLEANTerminalRuleCall_1_0()); 
            match(input,RULE_BOOLEAN,FOLLOW_RULE_BOOLEAN_in_rule__Expression_Boolean__ValueAssignment_14544); 
             after(grammarAccess.getExpression_BooleanAccess().getValueBOOLEANTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Expression_Boolean__ValueAssignment_1"

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
        "\6\uffff\1\6\1\2\1\1\1\4\1\5\1\3";
    static final String DFA3_specialS =
        "\14\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\1\1\2\1\4\1\5\1\3",
            "\1\10\1\7\1\13\1\11\1\12\1\6",
            "\1\10\1\7\1\13\1\11\1\12\1\6",
            "\1\10\1\7\1\13\1\11\1\12\1\6",
            "\1\10\1\7\1\13\1\11\1\12\1\6",
            "\1\10\1\7\1\13\1\11\1\12\1\6",
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
            return "588:1: rule__Operation__Alternatives : ( ( ruleOperation_Less_Equals ) | ( ruleOperation_Less ) | ( ruleOperation_Greater_Equals ) | ( ruleOperation_Greater ) | ( ruleOperation_Not_Equals ) | ( ruleOperation_Equals ) );";
        }
    }
 

    public static final BitSet FOLLOW_ruleOperation_Compare_in_entryRuleOperation_Compare61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Compare68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Compare__Group__0_in_ruleOperation_Compare94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnary_Operation_in_entryRuleUnary_Operation121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUnary_Operation128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Unary_Operation__Alternatives_in_ruleUnary_Operation154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_in_entryRuleOperation181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation__Alternatives_in_ruleOperation214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_Equals_in_entryRuleOperation_Less_Equals241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Less_Equals248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__0_in_ruleOperation_Less_Equals274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_in_entryRuleOperation_Less301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Less308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__0_in_ruleOperation_Less334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_Equals_in_entryRuleOperation_Greater_Equals361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Greater_Equals368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__0_in_ruleOperation_Greater_Equals394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_in_entryRuleOperation_Greater421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Greater428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__0_in_ruleOperation_Greater454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Not_Equals_in_entryRuleOperation_Not_Equals481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Not_Equals488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__0_in_ruleOperation_Not_Equals514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Equals_in_entryRuleOperation_Equals541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Equals548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__0_in_ruleOperation_Equals574 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Unary_in_entryRuleOperation_Unary601 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_Unary608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Unary__Group__0_in_ruleOperation_Unary634 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_NotUnary_in_entryRuleOperation_NotUnary661 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperation_NotUnary668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_NotUnary__Group__0_in_ruleOperation_NotUnary694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_entryRuleExpression_Terminal721 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Terminal728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Terminal__Alternatives_in_ruleExpression_Terminal754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Double_in_entryRuleExpression_Double781 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Double788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Double__Group__0_in_ruleExpression_Double814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Integer_in_entryRuleExpression_Integer841 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Integer848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Integer__Group__0_in_ruleExpression_Integer874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_String_in_entryRuleExpression_String901 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_String908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_String__Group__0_in_ruleExpression_String934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_ProcessRef_in_entryRuleExpression_ProcessRef966 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_ProcessRef973 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_ProcessRef__Group__0_in_ruleExpression_ProcessRef1003 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Boolean_in_entryRuleExpression_Boolean1030 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression_Boolean1037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Boolean__Group__0_in_ruleExpression_Boolean1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_in_rule__Operation_Compare__OpAlternatives_1_01099 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUnary_Operation_in_rule__Operation_Compare__OpAlternatives_1_01116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Unary_in_rule__Unary_Operation__Alternatives1148 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_NotUnary_in_rule__Unary_Operation__Alternatives1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_Equals_in_rule__Operation__Alternatives1197 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Less_in_rule__Operation__Alternatives1214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_Equals_in_rule__Operation__Alternatives1231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Greater_in_rule__Operation__Alternatives1248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Not_Equals_in_rule__Operation__Alternatives1265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperation_Equals_in_rule__Operation__Alternatives1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Double_in_rule__Expression_Terminal__Alternatives1314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Integer_in_rule__Expression_Terminal__Alternatives1331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Boolean_in_rule__Expression_Terminal__Alternatives1348 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_String_in_rule__Expression_Terminal__Alternatives1365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_ProcessRef_in_rule__Expression_Terminal__Alternatives1382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Compare__Group__0__Impl_in_rule__Operation_Compare__Group__01412 = new BitSet(new long[]{0x00000000004001F0L});
    public static final BitSet FOLLOW_rule__Operation_Compare__Group__1_in_rule__Operation_Compare__Group__01415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Compare__Group__1__Impl_in_rule__Operation_Compare__Group__11473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Compare__OpAssignment_1_in_rule__Operation_Compare__Group__1__Impl1500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__0__Impl_in_rule__Operation_Less_Equals__Group__01534 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__1_in_rule__Operation_Less_Equals__Group__01537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__1__Impl_in_rule__Operation_Less_Equals__Group__11595 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__2_in_rule__Operation_Less_Equals__Group__11598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__LeftAssignment_1_in_rule__Operation_Less_Equals__Group__1__Impl1625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__2__Impl_in_rule__Operation_Less_Equals__Group__21655 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__3_in_rule__Operation_Less_Equals__Group__21658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_rule__Operation_Less_Equals__Group__2__Impl1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__Group__3__Impl_in_rule__Operation_Less_Equals__Group__31717 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less_Equals__RightAssignment_3_in_rule__Operation_Less_Equals__Group__3__Impl1744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__0__Impl_in_rule__Operation_Less__Group__01782 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__1_in_rule__Operation_Less__Group__01785 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__1__Impl_in_rule__Operation_Less__Group__11843 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__2_in_rule__Operation_Less__Group__11846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less__LeftAssignment_1_in_rule__Operation_Less__Group__1__Impl1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__2__Impl_in_rule__Operation_Less__Group__21903 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__3_in_rule__Operation_Less__Group__21906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_rule__Operation_Less__Group__2__Impl1934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less__Group__3__Impl_in_rule__Operation_Less__Group__31965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Less__RightAssignment_3_in_rule__Operation_Less__Group__3__Impl1992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__0__Impl_in_rule__Operation_Greater_Equals__Group__02030 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__1_in_rule__Operation_Greater_Equals__Group__02033 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__1__Impl_in_rule__Operation_Greater_Equals__Group__12091 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__2_in_rule__Operation_Greater_Equals__Group__12094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__LeftAssignment_1_in_rule__Operation_Greater_Equals__Group__1__Impl2121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__2__Impl_in_rule__Operation_Greater_Equals__Group__22151 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__3_in_rule__Operation_Greater_Equals__Group__22154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__Operation_Greater_Equals__Group__2__Impl2182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__Group__3__Impl_in_rule__Operation_Greater_Equals__Group__32213 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater_Equals__RightAssignment_3_in_rule__Operation_Greater_Equals__Group__3__Impl2240 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__0__Impl_in_rule__Operation_Greater__Group__02278 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__1_in_rule__Operation_Greater__Group__02281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__1__Impl_in_rule__Operation_Greater__Group__12339 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__2_in_rule__Operation_Greater__Group__12342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater__LeftAssignment_1_in_rule__Operation_Greater__Group__1__Impl2369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__2__Impl_in_rule__Operation_Greater__Group__22399 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__3_in_rule__Operation_Greater__Group__22402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__Operation_Greater__Group__2__Impl2430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater__Group__3__Impl_in_rule__Operation_Greater__Group__32461 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Greater__RightAssignment_3_in_rule__Operation_Greater__Group__3__Impl2488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__0__Impl_in_rule__Operation_Not_Equals__Group__02526 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__1_in_rule__Operation_Not_Equals__Group__02529 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__1__Impl_in_rule__Operation_Not_Equals__Group__12587 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__2_in_rule__Operation_Not_Equals__Group__12590 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__LeftAssignment_1_in_rule__Operation_Not_Equals__Group__1__Impl2617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__2__Impl_in_rule__Operation_Not_Equals__Group__22647 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__3_in_rule__Operation_Not_Equals__Group__22650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__Operation_Not_Equals__Group__2__Impl2678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__Group__3__Impl_in_rule__Operation_Not_Equals__Group__32709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Not_Equals__RightAssignment_3_in_rule__Operation_Not_Equals__Group__3__Impl2736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__0__Impl_in_rule__Operation_Equals__Group__02774 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__1_in_rule__Operation_Equals__Group__02777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__1__Impl_in_rule__Operation_Equals__Group__12835 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__2_in_rule__Operation_Equals__Group__12838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Equals__LeftAssignment_1_in_rule__Operation_Equals__Group__1__Impl2865 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__2__Impl_in_rule__Operation_Equals__Group__22895 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__3_in_rule__Operation_Equals__Group__22898 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__Operation_Equals__Group__2__Impl2926 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Equals__Group__3__Impl_in_rule__Operation_Equals__Group__32957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Equals__RightAssignment_3_in_rule__Operation_Equals__Group__3__Impl2984 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Unary__Group__0__Impl_in_rule__Operation_Unary__Group__03022 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_Unary__Group__1_in_rule__Operation_Unary__Group__03025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Unary__Group__1__Impl_in_rule__Operation_Unary__Group__13083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Unary__ValueAssignment_1_in_rule__Operation_Unary__Group__1__Impl3110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_NotUnary__Group__0__Impl_in_rule__Operation_NotUnary__Group__03144 = new BitSet(new long[]{0x00000000004001F0L});
    public static final BitSet FOLLOW_rule__Operation_NotUnary__Group__1_in_rule__Operation_NotUnary__Group__03147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_NotUnary__Group__1__Impl_in_rule__Operation_NotUnary__Group__13205 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Operation_NotUnary__Group__2_in_rule__Operation_NotUnary__Group__13208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__Operation_NotUnary__Group__1__Impl3236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_NotUnary__Group__2__Impl_in_rule__Operation_NotUnary__Group__23267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_NotUnary__ValueAssignment_2_in_rule__Operation_NotUnary__Group__2__Impl3294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Double__Group__0__Impl_in_rule__Expression_Double__Group__03330 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Expression_Double__Group__1_in_rule__Expression_Double__Group__03333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Double__Group__1__Impl_in_rule__Expression_Double__Group__13391 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Double__ValueAssignment_1_in_rule__Expression_Double__Group__1__Impl3418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Integer__Group__0__Impl_in_rule__Expression_Integer__Group__03452 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_rule__Expression_Integer__Group__1_in_rule__Expression_Integer__Group__03455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Integer__Group__1__Impl_in_rule__Expression_Integer__Group__13513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Integer__ValueAssignment_1_in_rule__Expression_Integer__Group__1__Impl3540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_String__Group__0__Impl_in_rule__Expression_String__Group__03574 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__Expression_String__Group__1_in_rule__Expression_String__Group__03577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_String__Group__1__Impl_in_rule__Expression_String__Group__13635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_String__ValueAssignment_1_in_rule__Expression_String__Group__1__Impl3662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_ProcessRef__Group__0__Impl_in_rule__Expression_ProcessRef__Group__03696 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_rule__Expression_ProcessRef__Group__1_in_rule__Expression_ProcessRef__Group__03699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_ProcessRef__Group__1__Impl_in_rule__Expression_ProcessRef__Group__13757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_ProcessRef__ValueAssignment_1_in_rule__Expression_ProcessRef__Group__1__Impl3784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Boolean__Group__0__Impl_in_rule__Expression_Boolean__Group__03818 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__Expression_Boolean__Group__1_in_rule__Expression_Boolean__Group__03821 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Boolean__Group__1__Impl_in_rule__Expression_Boolean__Group__13879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Expression_Boolean__ValueAssignment_1_in_rule__Expression_Boolean__Group__1__Impl3906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Operation_Compare__OpAlternatives_1_0_in_rule__Operation_Compare__OpAssignment_13945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less_Equals__LeftAssignment_13978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less_Equals__RightAssignment_34009 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less__LeftAssignment_14040 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Less__RightAssignment_34071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater_Equals__LeftAssignment_14102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater_Equals__RightAssignment_34133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater__LeftAssignment_14164 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Greater__RightAssignment_34195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Not_Equals__LeftAssignment_14226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Not_Equals__RightAssignment_34257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Equals__LeftAssignment_14288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Equals__RightAssignment_34319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_Unary__ValueAssignment_14350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression_Terminal_in_rule__Operation_NotUnary__ValueAssignment_24381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DOUBLE_in_rule__Expression_Double__ValueAssignment_14412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LONG_in_rule__Expression_Integer__ValueAssignment_14443 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_rule__Expression_String__ValueAssignment_14474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__Expression_ProcessRef__ValueAssignment_14509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BOOLEAN_in_rule__Expression_Boolean__ValueAssignment_14544 = new BitSet(new long[]{0x0000000000000002L});

}