package cdc.cpp14;

import cdc.cpp14.grammar.CPP14Listener;
import cdc.cpp14.grammar.CPP14Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class CodeDCCPP14Listener implements CPP14Listener, CPP14TokenConstants {
    private cdc.cpp14.Parser  languageParser;
    public CodeDCCPP14Listener(cdc.cpp14.Parser parser) {
        languageParser = parser;
    }

    @Override
    public void enterBreak(CPP14Parser.BreakContext ctx) {
        languageParser.add(CPP_BREAK,ctx.getStart());
    }

    @Override
    public void exitBreak(CPP14Parser.BreakContext ctx) {

    }

    @Override
    public void enterContinue(CPP14Parser.ContinueContext ctx) {
        languageParser.add(CPP_CONTINUE,ctx.getStart());
    }

    @Override
    public void exitContinue(CPP14Parser.ContinueContext ctx) {

    }

    @Override
    public void enterReturn(CPP14Parser.ReturnContext ctx) {
        languageParser.add(CPP_RETURN,ctx.getStart());
    }

    @Override
    public void exitReturn(CPP14Parser.ReturnContext ctx) {

    }

    @Override
    public void enterGoto(CPP14Parser.GotoContext ctx) {
        languageParser.add(CPP_GOTO,ctx.getStart());
    }

    @Override
    public void exitGoto(CPP14Parser.GotoContext ctx) {

    }

    @Override
    public void enterIf(CPP14Parser.IfContext ctx) {
        languageParser.add(CPP_IF_BEGIN,ctx.getStart());
    }

    @Override
    public void exitIf(CPP14Parser.IfContext ctx) {
        languageParser.add(CPP_IF_END,ctx.getStop());
    }

    @Override
    public void enterIfElse(CPP14Parser.IfElseContext ctx) {
        languageParser.add(CPP_IFELSE_BEGIN,ctx.getStart());
    }

    @Override
    public void exitIfElse(CPP14Parser.IfElseContext ctx) {
        languageParser.add(CPP_IFELSE_END,ctx.getStop());
    }

    @Override
    public void enterSwitch(CPP14Parser.SwitchContext ctx) {
        languageParser.add(CPP_SWITCH_BEGIN,ctx.getStart());
    }

    @Override
    public void exitSwitch(CPP14Parser.SwitchContext ctx) {
        languageParser.add(CPP_SWITCH_END,ctx.getStop());
    }

    @Override
    public void enterWhile(CPP14Parser.WhileContext ctx) {
        languageParser.add(CPP_WHILE_BEGIN,ctx.getStart());
    }

    @Override
    public void exitWhile(CPP14Parser.WhileContext ctx) {
        languageParser.add(CPP_WHILE_END,ctx.getStop());
    }

    @Override
    public void enterDoWhile(CPP14Parser.DoWhileContext ctx) {
        languageParser.add(CPP_DO_WHILE_BEGIN,ctx.getStart());
    }

    @Override
    public void exitDoWhile(CPP14Parser.DoWhileContext ctx) {
        languageParser.add(CPP_DO_WHILE_END,ctx.getStop());
    }

    @Override
    public void enterFor(CPP14Parser.ForContext ctx) {
        languageParser.add(CPP_FOR_BEGIN,ctx.getStart());
    }

    @Override
    public void exitFor(CPP14Parser.ForContext ctx) {
        languageParser.add(CPP_FOR_END,ctx.getStop());
    }

    @Override
    public void enterForEach(CPP14Parser.ForEachContext ctx) {
        languageParser.add(CPP_FOREACH_BEGIN,ctx.getStart());
    }

    @Override
    public void exitForEach(CPP14Parser.ForEachContext ctx) {
        languageParser.add(CPP_FOREACH_END,ctx.getStop());
    }

    @Override
    public void enterTranslationunit(CPP14Parser.TranslationunitContext ctx) {

    }

    @Override
    public void exitTranslationunit(CPP14Parser.TranslationunitContext ctx) {

    }

    @Override
    public void enterPrimaryexpression(CPP14Parser.PrimaryexpressionContext ctx) {

    }

    @Override
    public void exitPrimaryexpression(CPP14Parser.PrimaryexpressionContext ctx) {

    }

    @Override
    public void enterIdexpression(CPP14Parser.IdexpressionContext ctx) {

    }

    @Override
    public void exitIdexpression(CPP14Parser.IdexpressionContext ctx) {

    }

    @Override
    public void enterUnqualifiedid(CPP14Parser.UnqualifiedidContext ctx) {

    }

    @Override
    public void exitUnqualifiedid(CPP14Parser.UnqualifiedidContext ctx) {

    }

    @Override
    public void enterQualifiedid(CPP14Parser.QualifiedidContext ctx) {

    }

    @Override
    public void exitQualifiedid(CPP14Parser.QualifiedidContext ctx) {

    }

    @Override
    public void enterNestednamespecifier(CPP14Parser.NestednamespecifierContext ctx) {

    }

    @Override
    public void exitNestednamespecifier(CPP14Parser.NestednamespecifierContext ctx) {

    }

    @Override
    public void enterLambdaexpression(CPP14Parser.LambdaexpressionContext ctx) {

    }

    @Override
    public void exitLambdaexpression(CPP14Parser.LambdaexpressionContext ctx) {

    }

    @Override
    public void enterLambdaintroducer(CPP14Parser.LambdaintroducerContext ctx) {

    }

    @Override
    public void exitLambdaintroducer(CPP14Parser.LambdaintroducerContext ctx) {

    }

    @Override
    public void enterLambdacapture(CPP14Parser.LambdacaptureContext ctx) {

    }

    @Override
    public void exitLambdacapture(CPP14Parser.LambdacaptureContext ctx) {

    }

    @Override
    public void enterCapturedefault(CPP14Parser.CapturedefaultContext ctx) {

    }

    @Override
    public void exitCapturedefault(CPP14Parser.CapturedefaultContext ctx) {

    }

    @Override
    public void enterCapturelist(CPP14Parser.CapturelistContext ctx) {

    }

    @Override
    public void exitCapturelist(CPP14Parser.CapturelistContext ctx) {

    }

    @Override
    public void enterCapture(CPP14Parser.CaptureContext ctx) {

    }

    @Override
    public void exitCapture(CPP14Parser.CaptureContext ctx) {

    }

    @Override
    public void enterSimplecapture(CPP14Parser.SimplecaptureContext ctx) {

    }

    @Override
    public void exitSimplecapture(CPP14Parser.SimplecaptureContext ctx) {

    }

    @Override
    public void enterInitcapture(CPP14Parser.InitcaptureContext ctx) {

    }

    @Override
    public void exitInitcapture(CPP14Parser.InitcaptureContext ctx) {

    }

    @Override
    public void enterLambdadeclarator(CPP14Parser.LambdadeclaratorContext ctx) {

    }

    @Override
    public void exitLambdadeclarator(CPP14Parser.LambdadeclaratorContext ctx) {

    }

    @Override
    public void enterPostfixexpression(CPP14Parser.PostfixexpressionContext ctx) {

    }

    @Override
    public void exitPostfixexpression(CPP14Parser.PostfixexpressionContext ctx) {

    }

    @Override
    public void enterExpressionlist(CPP14Parser.ExpressionlistContext ctx) {

    }

    @Override
    public void exitExpressionlist(CPP14Parser.ExpressionlistContext ctx) {

    }

    @Override
    public void enterPseudodestructorname(CPP14Parser.PseudodestructornameContext ctx) {

    }

    @Override
    public void exitPseudodestructorname(CPP14Parser.PseudodestructornameContext ctx) {

    }

    @Override
    public void enterUnaryexpression(CPP14Parser.UnaryexpressionContext ctx) {

    }

    @Override
    public void exitUnaryexpression(CPP14Parser.UnaryexpressionContext ctx) {

    }

    @Override
    public void enterUnaryoperator(CPP14Parser.UnaryoperatorContext ctx) {
        languageParser.add(CPP_UNARY_OP,ctx.getStart());
    }

    @Override
    public void exitUnaryoperator(CPP14Parser.UnaryoperatorContext ctx) {

    }

    @Override
    public void enterNewexpression(CPP14Parser.NewexpressionContext ctx) {

    }

    @Override
    public void exitNewexpression(CPP14Parser.NewexpressionContext ctx) {

    }

    @Override
    public void enterNewplacement(CPP14Parser.NewplacementContext ctx) {

    }

    @Override
    public void exitNewplacement(CPP14Parser.NewplacementContext ctx) {

    }

    @Override
    public void enterNewtypeid(CPP14Parser.NewtypeidContext ctx) {

    }

    @Override
    public void exitNewtypeid(CPP14Parser.NewtypeidContext ctx) {

    }

    @Override
    public void enterNewdeclarator(CPP14Parser.NewdeclaratorContext ctx) {

    }

    @Override
    public void exitNewdeclarator(CPP14Parser.NewdeclaratorContext ctx) {

    }

    @Override
    public void enterNoptrnewdeclarator(CPP14Parser.NoptrnewdeclaratorContext ctx) {

    }

    @Override
    public void exitNoptrnewdeclarator(CPP14Parser.NoptrnewdeclaratorContext ctx) {

    }

    @Override
    public void enterNewinitializer(CPP14Parser.NewinitializerContext ctx) {

    }

    @Override
    public void exitNewinitializer(CPP14Parser.NewinitializerContext ctx) {

    }

    @Override
    public void enterDeleteexpression(CPP14Parser.DeleteexpressionContext ctx) {

    }

    @Override
    public void exitDeleteexpression(CPP14Parser.DeleteexpressionContext ctx) {

    }

    @Override
    public void enterNoexceptexpression(CPP14Parser.NoexceptexpressionContext ctx) {

    }

    @Override
    public void exitNoexceptexpression(CPP14Parser.NoexceptexpressionContext ctx) {

    }

    @Override
    public void enterCastexpression(CPP14Parser.CastexpressionContext ctx) {

    }

    @Override
    public void exitCastexpression(CPP14Parser.CastexpressionContext ctx) {

    }

    @Override
    public void enterPmexpression(CPP14Parser.PmexpressionContext ctx) {

    }

    @Override
    public void exitPmexpression(CPP14Parser.PmexpressionContext ctx) {

    }

    @Override
    public void enterMultiplicativeexpression(CPP14Parser.MultiplicativeexpressionContext ctx) {

    }

    @Override
    public void exitMultiplicativeexpression(CPP14Parser.MultiplicativeexpressionContext ctx) {

    }

    @Override
    public void enterAdditiveexpression(CPP14Parser.AdditiveexpressionContext ctx) {

    }

    @Override
    public void exitAdditiveexpression(CPP14Parser.AdditiveexpressionContext ctx) {

    }

    @Override
    public void enterShiftexpression(CPP14Parser.ShiftexpressionContext ctx) {

    }

    @Override
    public void exitShiftexpression(CPP14Parser.ShiftexpressionContext ctx) {

    }

    @Override
    public void enterRelationalexpression(CPP14Parser.RelationalexpressionContext ctx) {

    }

    @Override
    public void exitRelationalexpression(CPP14Parser.RelationalexpressionContext ctx) {

    }

    @Override
    public void enterEqualityexpression(CPP14Parser.EqualityexpressionContext ctx) {

    }

    @Override
    public void exitEqualityexpression(CPP14Parser.EqualityexpressionContext ctx) {

    }

    @Override
    public void enterAndexpression(CPP14Parser.AndexpressionContext ctx) {

    }

    @Override
    public void exitAndexpression(CPP14Parser.AndexpressionContext ctx) {

    }

    @Override
    public void enterExclusiveorexpression(CPP14Parser.ExclusiveorexpressionContext ctx) {

    }

    @Override
    public void exitExclusiveorexpression(CPP14Parser.ExclusiveorexpressionContext ctx) {

    }

    @Override
    public void enterInclusiveorexpression(CPP14Parser.InclusiveorexpressionContext ctx) {

    }

    @Override
    public void exitInclusiveorexpression(CPP14Parser.InclusiveorexpressionContext ctx) {

    }

    @Override
    public void enterLogicalandexpression(CPP14Parser.LogicalandexpressionContext ctx) {

    }

    @Override
    public void exitLogicalandexpression(CPP14Parser.LogicalandexpressionContext ctx) {

    }

    @Override
    public void enterLogicalorexpression(CPP14Parser.LogicalorexpressionContext ctx) {

    }

    @Override
    public void exitLogicalorexpression(CPP14Parser.LogicalorexpressionContext ctx) {

    }

    @Override
    public void enterConditionalexpression(CPP14Parser.ConditionalexpressionContext ctx) {

    }

    @Override
    public void exitConditionalexpression(CPP14Parser.ConditionalexpressionContext ctx) {

    }

    @Override
    public void enterAssignmentexpression(CPP14Parser.AssignmentexpressionContext ctx) {
        //jplagParser.add(CPP_ASSIGNMENT, ctx.getStart());
    }

    @Override
    public void exitAssignmentexpression(CPP14Parser.AssignmentexpressionContext ctx) {

    }

    @Override
    public void enterAssignmentoperator(CPP14Parser.AssignmentoperatorContext ctx) {
        languageParser.add(CPP_ASSIGN_OP, ctx.getStart());
    }

    @Override
    public void exitAssignmentoperator(CPP14Parser.AssignmentoperatorContext ctx) {

    }

    @Override
    public void enterExpression(CPP14Parser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(CPP14Parser.ExpressionContext ctx) {

    }

    @Override
    public void enterConstantexpression(CPP14Parser.ConstantexpressionContext ctx) {

    }

    @Override
    public void exitConstantexpression(CPP14Parser.ConstantexpressionContext ctx) {

    }

    @Override
    public void enterStatement(CPP14Parser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(CPP14Parser.StatementContext ctx) {

    }

    @Override
    public void enterLabeledstatement(CPP14Parser.LabeledstatementContext ctx) {

    }

    @Override
    public void exitLabeledstatement(CPP14Parser.LabeledstatementContext ctx) {

    }

    @Override
    public void enterExpressionstatement(CPP14Parser.ExpressionstatementContext ctx) {

    }

    @Override
    public void exitExpressionstatement(CPP14Parser.ExpressionstatementContext ctx) {

    }

    @Override
    public void enterCompoundstatement(CPP14Parser.CompoundstatementContext ctx) {

    }

    @Override
    public void exitCompoundstatement(CPP14Parser.CompoundstatementContext ctx) {

    }

    @Override
    public void enterStatementseq(CPP14Parser.StatementseqContext ctx) {

    }

    @Override
    public void exitStatementseq(CPP14Parser.StatementseqContext ctx) {

    }

    @Override
    public void enterCondition(CPP14Parser.ConditionContext ctx) {
        //jplagParser.add(CPP_CONDITION_BEGIN,ctx.getStart());
    }

    @Override
    public void exitCondition(CPP14Parser.ConditionContext ctx) {
        //jplagParser.add(CPP_CONDITION_END,ctx.getStop());
    }

    @Override
    public void enterForinitstatement(CPP14Parser.ForinitstatementContext ctx) {

    }

    @Override
    public void exitForinitstatement(CPP14Parser.ForinitstatementContext ctx) {

    }

    @Override
    public void enterForrangedeclaration(CPP14Parser.ForrangedeclarationContext ctx) {

    }

    @Override
    public void exitForrangedeclaration(CPP14Parser.ForrangedeclarationContext ctx) {

    }

    @Override
    public void enterForrangeinitializer(CPP14Parser.ForrangeinitializerContext ctx) {

    }

    @Override
    public void exitForrangeinitializer(CPP14Parser.ForrangeinitializerContext ctx) {

    }


    @Override
    public void enterDeclarationstatement(CPP14Parser.DeclarationstatementContext ctx) {

    }

    @Override
    public void exitDeclarationstatement(CPP14Parser.DeclarationstatementContext ctx) {

    }

    @Override
    public void enterDeclarationseq(CPP14Parser.DeclarationseqContext ctx) {

    }

    @Override
    public void exitDeclarationseq(CPP14Parser.DeclarationseqContext ctx) {

    }

    @Override
    public void enterDeclaration(CPP14Parser.DeclarationContext ctx) {

    }

    @Override
    public void exitDeclaration(CPP14Parser.DeclarationContext ctx) {

    }

    @Override
    public void enterBlockdeclaration(CPP14Parser.BlockdeclarationContext ctx) {
        languageParser.add(CPP_BLOCK_BEGIN, ctx.getStart());
    }

    @Override
    public void exitBlockdeclaration(CPP14Parser.BlockdeclarationContext ctx) {
        //jplagParser.add(CPP_BLOCK_END, ctx.getStop());
    }

    @Override
    public void enterAliasdeclaration(CPP14Parser.AliasdeclarationContext ctx) {

    }

    @Override
    public void exitAliasdeclaration(CPP14Parser.AliasdeclarationContext ctx) {

    }

    @Override
    public void enterSimpledeclaration(CPP14Parser.SimpledeclarationContext ctx) {

    }

    @Override
    public void exitSimpledeclaration(CPP14Parser.SimpledeclarationContext ctx) {

    }

    @Override
    public void enterStatic_assertdeclaration(CPP14Parser.Static_assertdeclarationContext ctx) {

    }

    @Override
    public void exitStatic_assertdeclaration(CPP14Parser.Static_assertdeclarationContext ctx) {

    }

    @Override
    public void enterEmptydeclaration(CPP14Parser.EmptydeclarationContext ctx) {

    }

    @Override
    public void exitEmptydeclaration(CPP14Parser.EmptydeclarationContext ctx) {

    }

    @Override
    public void enterAttributedeclaration(CPP14Parser.AttributedeclarationContext ctx) {
        languageParser.add(CPP_ATTRIBUTE_DECLARATION,ctx.getStart());
    }

    @Override
    public void exitAttributedeclaration(CPP14Parser.AttributedeclarationContext ctx) {

    }

    @Override
    public void enterDeclspecifier(CPP14Parser.DeclspecifierContext ctx) {

    }

    @Override
    public void exitDeclspecifier(CPP14Parser.DeclspecifierContext ctx) {

    }

    @Override
    public void enterDeclspecifierseq(CPP14Parser.DeclspecifierseqContext ctx) {

    }

    @Override
    public void exitDeclspecifierseq(CPP14Parser.DeclspecifierseqContext ctx) {

    }

    @Override
    public void enterStorageclassspecifier(CPP14Parser.StorageclassspecifierContext ctx) {

    }

    @Override
    public void exitStorageclassspecifier(CPP14Parser.StorageclassspecifierContext ctx) {

    }

    @Override
    public void enterFunctionspecifier(CPP14Parser.FunctionspecifierContext ctx) {

    }

    @Override
    public void exitFunctionspecifier(CPP14Parser.FunctionspecifierContext ctx) {

    }

    @Override
    public void enterTypedefname(CPP14Parser.TypedefnameContext ctx) {

    }

    @Override
    public void exitTypedefname(CPP14Parser.TypedefnameContext ctx) {

    }

    @Override
    public void enterTypespecifier(CPP14Parser.TypespecifierContext ctx) {

    }

    @Override
    public void exitTypespecifier(CPP14Parser.TypespecifierContext ctx) {

    }

    @Override
    public void enterTrailingtypespecifier(CPP14Parser.TrailingtypespecifierContext ctx) {

    }

    @Override
    public void exitTrailingtypespecifier(CPP14Parser.TrailingtypespecifierContext ctx) {

    }

    @Override
    public void enterTypespecifierseq(CPP14Parser.TypespecifierseqContext ctx) {

    }

    @Override
    public void exitTypespecifierseq(CPP14Parser.TypespecifierseqContext ctx) {

    }

    @Override
    public void enterTrailingtypespecifierseq(CPP14Parser.TrailingtypespecifierseqContext ctx) {

    }

    @Override
    public void exitTrailingtypespecifierseq(CPP14Parser.TrailingtypespecifierseqContext ctx) {

    }

    @Override
    public void enterSimpletypespecifier(CPP14Parser.SimpletypespecifierContext ctx) {

    }

    @Override
    public void exitSimpletypespecifier(CPP14Parser.SimpletypespecifierContext ctx) {

    }

    @Override
    public void enterThetypename(CPP14Parser.ThetypenameContext ctx) {

    }

    @Override
    public void exitThetypename(CPP14Parser.ThetypenameContext ctx) {

    }

    @Override
    public void enterDecltypespecifier(CPP14Parser.DecltypespecifierContext ctx) {

    }

    @Override
    public void exitDecltypespecifier(CPP14Parser.DecltypespecifierContext ctx) {

    }

    @Override
    public void enterElaboratedtypespecifier(CPP14Parser.ElaboratedtypespecifierContext ctx) {

    }

    @Override
    public void exitElaboratedtypespecifier(CPP14Parser.ElaboratedtypespecifierContext ctx) {

    }

    @Override
    public void enterEnumname(CPP14Parser.EnumnameContext ctx) {

    }

    @Override
    public void exitEnumname(CPP14Parser.EnumnameContext ctx) {

    }

    @Override
    public void enterEnumspecifier(CPP14Parser.EnumspecifierContext ctx) {
        languageParser.add(CPP_ENUM_SPECIFIER_BEGIN,ctx.getStart());
    }

    @Override
    public void exitEnumspecifier(CPP14Parser.EnumspecifierContext ctx) {
        languageParser.add(CPP_ENUM_SPECIFIER_END,ctx.getStop());
    }

    @Override
    public void enterEnumhead(CPP14Parser.EnumheadContext ctx) {

    }

    @Override
    public void exitEnumhead(CPP14Parser.EnumheadContext ctx) {

    }

    @Override
    public void enterOpaqueenumdeclaration(CPP14Parser.OpaqueenumdeclarationContext ctx) {

    }

    @Override
    public void exitOpaqueenumdeclaration(CPP14Parser.OpaqueenumdeclarationContext ctx) {

    }

    @Override
    public void enterEnumkey(CPP14Parser.EnumkeyContext ctx) {

    }

    @Override
    public void exitEnumkey(CPP14Parser.EnumkeyContext ctx) {

    }

    @Override
    public void enterEnumbase(CPP14Parser.EnumbaseContext ctx) {

    }

    @Override
    public void exitEnumbase(CPP14Parser.EnumbaseContext ctx) {

    }

    @Override
    public void enterEnumeratorlist(CPP14Parser.EnumeratorlistContext ctx) {

    }

    @Override
    public void exitEnumeratorlist(CPP14Parser.EnumeratorlistContext ctx) {

    }

    @Override
    public void enterEnumeratordefinition(CPP14Parser.EnumeratordefinitionContext ctx) {

    }

    @Override
    public void exitEnumeratordefinition(CPP14Parser.EnumeratordefinitionContext ctx) {

    }

    @Override
    public void enterEnumerator(CPP14Parser.EnumeratorContext ctx) {

    }

    @Override
    public void exitEnumerator(CPP14Parser.EnumeratorContext ctx) {

    }

    @Override
    public void enterNamespacename(CPP14Parser.NamespacenameContext ctx) {

    }

    @Override
    public void exitNamespacename(CPP14Parser.NamespacenameContext ctx) {

    }

    @Override
    public void enterOriginalnamespacename(CPP14Parser.OriginalnamespacenameContext ctx) {

    }

    @Override
    public void exitOriginalnamespacename(CPP14Parser.OriginalnamespacenameContext ctx) {

    }

    @Override
    public void enterNamespacedefinition(CPP14Parser.NamespacedefinitionContext ctx) {
        languageParser.add(CPP_NAMESPACE_DEFINITION, ctx.getStart());
    }

    @Override
    public void exitNamespacedefinition(CPP14Parser.NamespacedefinitionContext ctx) {

    }

    @Override
    public void enterNamednamespacedefinition(CPP14Parser.NamednamespacedefinitionContext ctx) {

    }

    @Override
    public void exitNamednamespacedefinition(CPP14Parser.NamednamespacedefinitionContext ctx) {

    }

    @Override
    public void enterOriginalnamespacedefinition(CPP14Parser.OriginalnamespacedefinitionContext ctx) {

    }

    @Override
    public void exitOriginalnamespacedefinition(CPP14Parser.OriginalnamespacedefinitionContext ctx) {

    }

    @Override
    public void enterExtensionnamespacedefinition(CPP14Parser.ExtensionnamespacedefinitionContext ctx) {

    }

    @Override
    public void exitExtensionnamespacedefinition(CPP14Parser.ExtensionnamespacedefinitionContext ctx) {

    }

    @Override
    public void enterUnnamednamespacedefinition(CPP14Parser.UnnamednamespacedefinitionContext ctx) {

    }

    @Override
    public void exitUnnamednamespacedefinition(CPP14Parser.UnnamednamespacedefinitionContext ctx) {

    }

    @Override
    public void enterNamespacebody(CPP14Parser.NamespacebodyContext ctx) {

    }

    @Override
    public void exitNamespacebody(CPP14Parser.NamespacebodyContext ctx) {

    }

    @Override
    public void enterNamespacealias(CPP14Parser.NamespacealiasContext ctx) {

    }

    @Override
    public void exitNamespacealias(CPP14Parser.NamespacealiasContext ctx) {

    }

    @Override
    public void enterNamespacealiasdefinition(CPP14Parser.NamespacealiasdefinitionContext ctx) {

    }

    @Override
    public void exitNamespacealiasdefinition(CPP14Parser.NamespacealiasdefinitionContext ctx) {

    }

    @Override
    public void enterQualifiednamespacespecifier(CPP14Parser.QualifiednamespacespecifierContext ctx) {

    }

    @Override
    public void exitQualifiednamespacespecifier(CPP14Parser.QualifiednamespacespecifierContext ctx) {

    }

    @Override
    public void enterUsingdeclaration(CPP14Parser.UsingdeclarationContext ctx) {

    }

    @Override
    public void exitUsingdeclaration(CPP14Parser.UsingdeclarationContext ctx) {

    }

    @Override
    public void enterUsingdirective(CPP14Parser.UsingdirectiveContext ctx) {
        languageParser.add(CPP_USING_DIRECTIVE, ctx.getStart());
    }

    @Override
    public void exitUsingdirective(CPP14Parser.UsingdirectiveContext ctx) {

    }

    @Override
    public void enterAsmdefinition(CPP14Parser.AsmdefinitionContext ctx) {

    }

    @Override
    public void exitAsmdefinition(CPP14Parser.AsmdefinitionContext ctx) {

    }

    @Override
    public void enterLinkagespecification(CPP14Parser.LinkagespecificationContext ctx) {

    }

    @Override
    public void exitLinkagespecification(CPP14Parser.LinkagespecificationContext ctx) {

    }

    @Override
    public void enterAttributespecifierseq(CPP14Parser.AttributespecifierseqContext ctx) {

    }

    @Override
    public void exitAttributespecifierseq(CPP14Parser.AttributespecifierseqContext ctx) {

    }

    @Override
    public void enterAttributespecifier(CPP14Parser.AttributespecifierContext ctx) {

    }

    @Override
    public void exitAttributespecifier(CPP14Parser.AttributespecifierContext ctx) {

    }

    @Override
    public void enterAlignmentspecifier(CPP14Parser.AlignmentspecifierContext ctx) {

    }

    @Override
    public void exitAlignmentspecifier(CPP14Parser.AlignmentspecifierContext ctx) {

    }

    @Override
    public void enterAttributelist(CPP14Parser.AttributelistContext ctx) {

    }

    @Override
    public void exitAttributelist(CPP14Parser.AttributelistContext ctx) {

    }

    @Override
    public void enterAttribute(CPP14Parser.AttributeContext ctx) {

    }

    @Override
    public void exitAttribute(CPP14Parser.AttributeContext ctx) {

    }

    @Override
    public void enterAttributetoken(CPP14Parser.AttributetokenContext ctx) {

    }

    @Override
    public void exitAttributetoken(CPP14Parser.AttributetokenContext ctx) {

    }
    @Override
    public void enterAttributescopedtoken(CPP14Parser.AttributescopedtokenContext ctx) {
    //TODO ??ADD??
        languageParser.add(CPP_SCOPE, ctx.getStart());
    }

    @Override
    public void exitAttributescopedtoken(CPP14Parser.AttributescopedtokenContext ctx) {
    }

    @Override
    public void enterAttributenamespace(CPP14Parser.AttributenamespaceContext ctx) {

    }

    @Override
    public void exitAttributenamespace(CPP14Parser.AttributenamespaceContext ctx) {

    }

    @Override
    public void enterAttributeargumentclause(CPP14Parser.AttributeargumentclauseContext ctx) {

    }

    @Override
    public void exitAttributeargumentclause(CPP14Parser.AttributeargumentclauseContext ctx) {

    }

    @Override
    public void enterBalancedtokenseq(CPP14Parser.BalancedtokenseqContext ctx) {

    }

    @Override
    public void exitBalancedtokenseq(CPP14Parser.BalancedtokenseqContext ctx) {

    }

    @Override
    public void enterBalancedtoken(CPP14Parser.BalancedtokenContext ctx) {

    }

    @Override
    public void exitBalancedtoken(CPP14Parser.BalancedtokenContext ctx) {

    }

    @Override
    public void enterInitdeclaratorlist(CPP14Parser.InitdeclaratorlistContext ctx) {

    }

    @Override
    public void exitInitdeclaratorlist(CPP14Parser.InitdeclaratorlistContext ctx) {

    }

    @Override
    public void enterInitdeclarator(CPP14Parser.InitdeclaratorContext ctx) {

    }

    @Override
    public void exitInitdeclarator(CPP14Parser.InitdeclaratorContext ctx) {

    }

    @Override
    public void enterDeclarator(CPP14Parser.DeclaratorContext ctx) {

    }

    @Override
    public void exitDeclarator(CPP14Parser.DeclaratorContext ctx) {

    }

    @Override
    public void enterPtrdeclarator(CPP14Parser.PtrdeclaratorContext ctx) {

    }

    @Override
    public void exitPtrdeclarator(CPP14Parser.PtrdeclaratorContext ctx) {

    }

    @Override
    public void enterNoptrdeclarator(CPP14Parser.NoptrdeclaratorContext ctx) {

    }

    @Override
    public void exitNoptrdeclarator(CPP14Parser.NoptrdeclaratorContext ctx) {

    }

    @Override
    public void enterParametersandqualifiers(CPP14Parser.ParametersandqualifiersContext ctx) {

    }

    @Override
    public void exitParametersandqualifiers(CPP14Parser.ParametersandqualifiersContext ctx) {

    }

    @Override
    public void enterTrailingreturntype(CPP14Parser.TrailingreturntypeContext ctx) {

    }

    @Override
    public void exitTrailingreturntype(CPP14Parser.TrailingreturntypeContext ctx) {

    }

    @Override
    public void enterPtroperator(CPP14Parser.PtroperatorContext ctx) {
        languageParser.add(CPP_PTR_OP,ctx.getStart());
    }

    @Override
    public void exitPtroperator(CPP14Parser.PtroperatorContext ctx) {

    }

    @Override
    public void enterCvqualifierseq(CPP14Parser.CvqualifierseqContext ctx) {

    }

    @Override
    public void exitCvqualifierseq(CPP14Parser.CvqualifierseqContext ctx) {

    }

    @Override
    public void enterCvqualifier(CPP14Parser.CvqualifierContext ctx) {

    }

    @Override
    public void exitCvqualifier(CPP14Parser.CvqualifierContext ctx) {

    }

    @Override
    public void enterRefqualifier(CPP14Parser.RefqualifierContext ctx) {

    }

    @Override
    public void exitRefqualifier(CPP14Parser.RefqualifierContext ctx) {

    }

    @Override
    public void enterDeclaratorid(CPP14Parser.DeclaratoridContext ctx) {

    }

    @Override
    public void exitDeclaratorid(CPP14Parser.DeclaratoridContext ctx) {

    }

    @Override
    public void enterThetypeid(CPP14Parser.ThetypeidContext ctx) {

    }

    @Override
    public void exitThetypeid(CPP14Parser.ThetypeidContext ctx) {

    }

    @Override
    public void enterAbstractdeclarator(CPP14Parser.AbstractdeclaratorContext ctx) {

    }

    @Override
    public void exitAbstractdeclarator(CPP14Parser.AbstractdeclaratorContext ctx) {

    }

    @Override
    public void enterPtrabstractdeclarator(CPP14Parser.PtrabstractdeclaratorContext ctx) {

    }

    @Override
    public void exitPtrabstractdeclarator(CPP14Parser.PtrabstractdeclaratorContext ctx) {

    }

    @Override
    public void enterNoptrabstractdeclarator(CPP14Parser.NoptrabstractdeclaratorContext ctx) {

    }

    @Override
    public void exitNoptrabstractdeclarator(CPP14Parser.NoptrabstractdeclaratorContext ctx) {

    }

    @Override
    public void enterAbstractpackdeclarator(CPP14Parser.AbstractpackdeclaratorContext ctx) {

    }

    @Override
    public void exitAbstractpackdeclarator(CPP14Parser.AbstractpackdeclaratorContext ctx) {

    }

    @Override
    public void enterNoptrabstractpackdeclarator(CPP14Parser.NoptrabstractpackdeclaratorContext ctx) {

    }

    @Override
    public void exitNoptrabstractpackdeclarator(CPP14Parser.NoptrabstractpackdeclaratorContext ctx) {

    }

    @Override
    public void enterParameterdeclarationclause(CPP14Parser.ParameterdeclarationclauseContext ctx) {

    }

    @Override
    public void exitParameterdeclarationclause(CPP14Parser.ParameterdeclarationclauseContext ctx) {

    }

    @Override
    public void enterParameterdeclarationlist(CPP14Parser.ParameterdeclarationlistContext ctx) {

    }

    @Override
    public void exitParameterdeclarationlist(CPP14Parser.ParameterdeclarationlistContext ctx) {

    }

    @Override
    public void enterParameterdeclaration(CPP14Parser.ParameterdeclarationContext ctx) {

    }

    @Override
    public void exitParameterdeclaration(CPP14Parser.ParameterdeclarationContext ctx) {

    }

    @Override
    public void enterFunctiondefinition(CPP14Parser.FunctiondefinitionContext ctx) {
        languageParser.add(CPP_FUNCTION_DEFINITION_BEGIN, ctx.getStart());
    }

    @Override
    public void exitFunctiondefinition(CPP14Parser.FunctiondefinitionContext ctx) {
        languageParser.add(CPP_FUNCTION_DEFINITION_END, ctx.getStop());
    }

    @Override
    public void enterFunctionbody(CPP14Parser.FunctionbodyContext ctx) {

    }

    @Override
    public void exitFunctionbody(CPP14Parser.FunctionbodyContext ctx) {

    }

    @Override
    public void enterInitializer(CPP14Parser.InitializerContext ctx) {

    }

    @Override
    public void exitInitializer(CPP14Parser.InitializerContext ctx) {

    }

    @Override
    public void enterBraceorequalinitializer(CPP14Parser.BraceorequalinitializerContext ctx) {

    }

    @Override
    public void exitBraceorequalinitializer(CPP14Parser.BraceorequalinitializerContext ctx) {

    }

    @Override
    public void enterInitializerclause(CPP14Parser.InitializerclauseContext ctx) {

    }

    @Override
    public void exitInitializerclause(CPP14Parser.InitializerclauseContext ctx) {

    }

    @Override
    public void enterInitializerlist(CPP14Parser.InitializerlistContext ctx) {

    }

    @Override
    public void exitInitializerlist(CPP14Parser.InitializerlistContext ctx) {

    }

    @Override
    public void enterBracedinitlist(CPP14Parser.BracedinitlistContext ctx) {

    }

    @Override
    public void exitBracedinitlist(CPP14Parser.BracedinitlistContext ctx) {

    }

    @Override
    public void enterClassname(CPP14Parser.ClassnameContext ctx) {

    }

    @Override
    public void exitClassname(CPP14Parser.ClassnameContext ctx) {

    }

    @Override
    public void enterClassspecifier(CPP14Parser.ClassspecifierContext ctx) {
        languageParser.add(CPP_CLASS_SPECIFIER_BEGIN, ctx.getStart());
    }

    @Override
    public void exitClassspecifier(CPP14Parser.ClassspecifierContext ctx) {
        languageParser.add(CPP_CLASS_SPECIFIER_END, ctx.getStop());
    }

    @Override
    public void enterClasshead(CPP14Parser.ClassheadContext ctx) {

    }

    @Override
    public void exitClasshead(CPP14Parser.ClassheadContext ctx) {

    }

    @Override
    public void enterClassheadname(CPP14Parser.ClassheadnameContext ctx) {

    }

    @Override
    public void exitClassheadname(CPP14Parser.ClassheadnameContext ctx) {

    }

    @Override
    public void enterClassvirtspecifier(CPP14Parser.ClassvirtspecifierContext ctx) {

    }

    @Override
    public void exitClassvirtspecifier(CPP14Parser.ClassvirtspecifierContext ctx) {

    }

    @Override
    public void enterClasskey(CPP14Parser.ClasskeyContext ctx) {

    }

    @Override
    public void exitClasskey(CPP14Parser.ClasskeyContext ctx) {

    }

    @Override
    public void enterMemberspecification(CPP14Parser.MemberspecificationContext ctx) {

    }

    @Override
    public void exitMemberspecification(CPP14Parser.MemberspecificationContext ctx) {

    }

    @Override
    public void enterMemberdeclaration(CPP14Parser.MemberdeclarationContext ctx) {

    }

    @Override
    public void exitMemberdeclaration(CPP14Parser.MemberdeclarationContext ctx) {

    }

    @Override
    public void enterMemberdeclaratorlist(CPP14Parser.MemberdeclaratorlistContext ctx) {

    }

    @Override
    public void exitMemberdeclaratorlist(CPP14Parser.MemberdeclaratorlistContext ctx) {

    }

    @Override
    public void enterMemberdeclarator(CPP14Parser.MemberdeclaratorContext ctx) {

    }

    @Override
    public void exitMemberdeclarator(CPP14Parser.MemberdeclaratorContext ctx) {

    }

    @Override
    public void enterVirtspecifierseq(CPP14Parser.VirtspecifierseqContext ctx) {

    }

    @Override
    public void exitVirtspecifierseq(CPP14Parser.VirtspecifierseqContext ctx) {

    }

    @Override
    public void enterVirtspecifier(CPP14Parser.VirtspecifierContext ctx) {

    }

    @Override
    public void exitVirtspecifier(CPP14Parser.VirtspecifierContext ctx) {

    }

    @Override
    public void enterPurespecifier(CPP14Parser.PurespecifierContext ctx) {

    }

    @Override
    public void exitPurespecifier(CPP14Parser.PurespecifierContext ctx) {

    }

    @Override
    public void enterBaseclause(CPP14Parser.BaseclauseContext ctx) {

    }

    @Override
    public void exitBaseclause(CPP14Parser.BaseclauseContext ctx) {

    }

    @Override
    public void enterBasespecifierlist(CPP14Parser.BasespecifierlistContext ctx) {

    }

    @Override
    public void exitBasespecifierlist(CPP14Parser.BasespecifierlistContext ctx) {

    }

    @Override
    public void enterBasespecifier(CPP14Parser.BasespecifierContext ctx) {

    }

    @Override
    public void exitBasespecifier(CPP14Parser.BasespecifierContext ctx) {

    }

    @Override
    public void enterClassordecltype(CPP14Parser.ClassordecltypeContext ctx) {

    }

    @Override
    public void exitClassordecltype(CPP14Parser.ClassordecltypeContext ctx) {

    }

    @Override
    public void enterBasetypespecifier(CPP14Parser.BasetypespecifierContext ctx) {

    }

    @Override
    public void exitBasetypespecifier(CPP14Parser.BasetypespecifierContext ctx) {

    }

    @Override
    public void enterAccessspecifier(CPP14Parser.AccessspecifierContext ctx) {

    }

    @Override
    public void exitAccessspecifier(CPP14Parser.AccessspecifierContext ctx) {

    }

    @Override
    public void enterConversionfunctionid(CPP14Parser.ConversionfunctionidContext ctx) {

    }

    @Override
    public void exitConversionfunctionid(CPP14Parser.ConversionfunctionidContext ctx) {

    }

    @Override
    public void enterConversiontypeid(CPP14Parser.ConversiontypeidContext ctx) {

    }

    @Override
    public void exitConversiontypeid(CPP14Parser.ConversiontypeidContext ctx) {

    }

    @Override
    public void enterConversiondeclarator(CPP14Parser.ConversiondeclaratorContext ctx) {

    }

    @Override
    public void exitConversiondeclarator(CPP14Parser.ConversiondeclaratorContext ctx) {

    }

    @Override
    public void enterCtorinitializer(CPP14Parser.CtorinitializerContext ctx) {
        languageParser.add(CPP_INITIALIZER_BEGIN,ctx.getStart());
    }

    @Override
    public void exitCtorinitializer(CPP14Parser.CtorinitializerContext ctx) {
        languageParser.add(CPP_INITIALIZER_END,ctx.getStop());
    }

    @Override
    public void enterMeminitializerlist(CPP14Parser.MeminitializerlistContext ctx) {

    }

    @Override
    public void exitMeminitializerlist(CPP14Parser.MeminitializerlistContext ctx) {

    }

    @Override
    public void enterMeminitializer(CPP14Parser.MeminitializerContext ctx) {

    }

    @Override
    public void exitMeminitializer(CPP14Parser.MeminitializerContext ctx) {

    }

    @Override
    public void enterMeminitializerid(CPP14Parser.MeminitializeridContext ctx) {

    }

    @Override
    public void exitMeminitializerid(CPP14Parser.MeminitializeridContext ctx) {

    }

    @Override
    public void enterOperatorfunctionid(CPP14Parser.OperatorfunctionidContext ctx) {
        languageParser.add(CPP_OPERATOR_FUN,ctx.getStart());

    }

    @Override
    public void exitOperatorfunctionid(CPP14Parser.OperatorfunctionidContext ctx) {

    }

    @Override
    public void enterLiteraloperatorid(CPP14Parser.LiteraloperatoridContext ctx) {

    }

    @Override
    public void exitLiteraloperatorid(CPP14Parser.LiteraloperatoridContext ctx) {

    }

    @Override
    public void enterTemplatedeclaration(CPP14Parser.TemplatedeclarationContext ctx) {

    }

    @Override
    public void exitTemplatedeclaration(CPP14Parser.TemplatedeclarationContext ctx) {

    }

    @Override
    public void enterTemplateparameterlist(CPP14Parser.TemplateparameterlistContext ctx) {

    }

    @Override
    public void exitTemplateparameterlist(CPP14Parser.TemplateparameterlistContext ctx) {

    }

    @Override
    public void enterTemplateparameter(CPP14Parser.TemplateparameterContext ctx) {

    }

    @Override
    public void exitTemplateparameter(CPP14Parser.TemplateparameterContext ctx) {

    }

    @Override
    public void enterTypeparameter(CPP14Parser.TypeparameterContext ctx) {

    }

    @Override
    public void exitTypeparameter(CPP14Parser.TypeparameterContext ctx) {

    }

    @Override
    public void enterSimpletemplateid(CPP14Parser.SimpletemplateidContext ctx) {

    }

    @Override
    public void exitSimpletemplateid(CPP14Parser.SimpletemplateidContext ctx) {

    }

    @Override
    public void enterTemplateid(CPP14Parser.TemplateidContext ctx) {

    }

    @Override
    public void exitTemplateid(CPP14Parser.TemplateidContext ctx) {

    }

    @Override
    public void enterTemplatename(CPP14Parser.TemplatenameContext ctx) {

    }

    @Override
    public void exitTemplatename(CPP14Parser.TemplatenameContext ctx) {

    }

    @Override
    public void enterTemplateargumentlist(CPP14Parser.TemplateargumentlistContext ctx) {

    }

    @Override
    public void exitTemplateargumentlist(CPP14Parser.TemplateargumentlistContext ctx) {

    }

    @Override
    public void enterTemplateargument(CPP14Parser.TemplateargumentContext ctx) {

    }

    @Override
    public void exitTemplateargument(CPP14Parser.TemplateargumentContext ctx) {

    }

    @Override
    public void enterTypenamespecifier(CPP14Parser.TypenamespecifierContext ctx) {

    }

    @Override
    public void exitTypenamespecifier(CPP14Parser.TypenamespecifierContext ctx) {

    }

    @Override
    public void enterExplicitinstantiation(CPP14Parser.ExplicitinstantiationContext ctx) {

    }

    @Override
    public void exitExplicitinstantiation(CPP14Parser.ExplicitinstantiationContext ctx) {

    }

    @Override
    public void enterExplicitspecialization(CPP14Parser.ExplicitspecializationContext ctx) {

    }

    @Override
    public void exitExplicitspecialization(CPP14Parser.ExplicitspecializationContext ctx) {

    }

    @Override
    public void enterTryblock(CPP14Parser.TryblockContext ctx) {
        languageParser.add(CPP_TRY_BEGIN,ctx.getStart());
    }

    @Override
    public void exitTryblock(CPP14Parser.TryblockContext ctx) {
        languageParser.add(CPP_TRY_END,ctx.getStop());
    }

    @Override
    public void enterFunctiontryblock(CPP14Parser.FunctiontryblockContext ctx) {
        languageParser.add(CPP_TRY_BEGIN,ctx.getStart());
    }

    @Override
    public void exitFunctiontryblock(CPP14Parser.FunctiontryblockContext ctx) {
        languageParser.add(CPP_TRY_END,ctx.getStop());
    }

    @Override
    public void enterHandlerseq(CPP14Parser.HandlerseqContext ctx) {

    }

    @Override
    public void exitHandlerseq(CPP14Parser.HandlerseqContext ctx) {

    }

    @Override
    public void enterHandler(CPP14Parser.HandlerContext ctx) {
        languageParser.add(CPP_CATCH_BEGIN,ctx.getStart());
    }

    @Override
    public void exitHandler(CPP14Parser.HandlerContext ctx) {
        languageParser.add(CPP_CATCH_END,ctx.getStop());
    }

    @Override
    public void enterExceptiondeclaration(CPP14Parser.ExceptiondeclarationContext ctx) {

    }

    @Override
    public void exitExceptiondeclaration(CPP14Parser.ExceptiondeclarationContext ctx) {

    }

    @Override
    public void enterThrowexpression(CPP14Parser.ThrowexpressionContext ctx) {
        languageParser.add(CPP_THROW,ctx.getStart());
    }

    @Override
    public void exitThrowexpression(CPP14Parser.ThrowexpressionContext ctx) {

    }

    @Override
    public void enterExceptionspecification(CPP14Parser.ExceptionspecificationContext ctx) {

    }

    @Override
    public void exitExceptionspecification(CPP14Parser.ExceptionspecificationContext ctx) {

    }

    @Override
    public void enterDynamicexceptionspecification(CPP14Parser.DynamicexceptionspecificationContext ctx) {

    }

    @Override
    public void exitDynamicexceptionspecification(CPP14Parser.DynamicexceptionspecificationContext ctx) {

    }

    @Override
    public void enterTypeidlist(CPP14Parser.TypeidlistContext ctx) {

    }

    @Override
    public void exitTypeidlist(CPP14Parser.TypeidlistContext ctx) {

    }

    @Override
    public void enterNoexceptspecification(CPP14Parser.NoexceptspecificationContext ctx) {

    }

    @Override
    public void exitNoexceptspecification(CPP14Parser.NoexceptspecificationContext ctx) {

    }

    @Override
    public void enterRightShift(CPP14Parser.RightShiftContext ctx) {

    }

    @Override
    public void exitRightShift(CPP14Parser.RightShiftContext ctx) {

    }

    @Override
    public void enterRightShiftAssign(CPP14Parser.RightShiftAssignContext ctx) {

    }

    @Override
    public void exitRightShiftAssign(CPP14Parser.RightShiftAssignContext ctx) {

    }

    @Override
    public void enterTheoperator(CPP14Parser.TheoperatorContext ctx) {

    }

    @Override
    public void exitTheoperator(CPP14Parser.TheoperatorContext ctx) {

    }

    @Override
    public void enterLiteral(CPP14Parser.LiteralContext ctx) {

    }

    @Override
    public void exitLiteral(CPP14Parser.LiteralContext ctx) {

    }

    @Override
    public void enterBooleanliteral(CPP14Parser.BooleanliteralContext ctx) {

    }

    @Override
    public void exitBooleanliteral(CPP14Parser.BooleanliteralContext ctx) {

    }

    @Override
    public void enterPointerliteral(CPP14Parser.PointerliteralContext ctx) {

    }

    @Override
    public void exitPointerliteral(CPP14Parser.PointerliteralContext ctx) {

    }

    @Override
    public void enterUserdefinedliteral(CPP14Parser.UserdefinedliteralContext ctx) {

    }

    @Override
    public void exitUserdefinedliteral(CPP14Parser.UserdefinedliteralContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
