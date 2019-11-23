package ast;

import logger.Logger;

import java.util.ArrayList;
import java.util.List;


public class KEYWORD extends STATEMENT {
    private KEYWORD k;
    private String loggerString;

    @Override
    public void parse(){

        if (tokenizer.checkToken("character")) {
            k = new CHARDEF();
            //loggerString = tokenizer.checkNext();
        } else if (tokenizer.checkToken("digit")) {
            k = new DIGITDEF();
            //loggerString = tokenizer.checkNext();
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
        loggerString = tokenizer.checkNext();
        Logger.log(this, loggerString);
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
