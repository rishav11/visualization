package ast;

import java.util.ArrayList;
import java.util.List;


public class KEYWORD extends STATEMENT {
    private KEYWORD k;

    @Override
    public void parse(){
        if (tokenizer.checkToken("character")) {
            k = new CHARDEF();
        } else if (tokenizer.checkToken("digit")) {
            k = new DIGITDEF();
        } else if (tokenizer.checkToken("(\"([^\"])*\")")) {
            k = new STRINGDEF();
        } else if (tokenizer.checkToken("any")) {
            k = new STRINGDEF();
        } else if (tokenizer.checkToken("non-digit")) {
            k = new STRINGDEF();
        } else if (tokenizer.checkToken("whitespace")) {
            k = new STRINGDEF();
        } else if (tokenizer.checkToken("non-whitespace")) {
            k = new STRINGDEF();
        } else if (tokenizer.checkToken("newline")) {
            k = new STRINGDEF();
        } else if (tokenizer.checkToken("tab")) {
            k = new STRINGDEF();
        }
        k.parse();
    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }

    @Override
    public String evaluate() {
        return k.evaluate();
    }
}
