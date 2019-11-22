package ast;

import libs.Node;

import java.util.ArrayList;
import java.util.List;

public class PROGRAM extends Node {

    private List<STATEMENT> statements = new ArrayList<>();

    @Override
    public void parse() {
        while (!tokenizer.checkToken("NO_MORE_TOKENS")) {
            STATEMENT s = null;
            if (tokenizer.checkToken("expression")) {
                s = new EXPRESSION();
            } else if (tokenizer.checkToken("test")) {
                s = new TEST();
            }
            s.parse();
            statements.add(s);
        }
    }

    public void nameCheck(){
        for (STATEMENT s : statements){
            s.nameCheck();
        }
    }

    @Override
    public void typeCheck() {
        for (STATEMENT s : statements){
            s.typeCheck();
        }
    }

    @Override
    public String evaluate() {
        for (STATEMENT s : statements){
            s.evaluate();
        }
        return "";
    }
}
