package ast;
import logger.Logger;

public class CHARDEF extends KEYWORD {
    private CHARCONSTRAINT c;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("character");
        if (tokenizer.checkToken("from")) {
            c = new CHARCONSTRAINT();
Logger.logTwo(this, "c");
            c.parse();
        }
    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }

    @Override
    public String evaluate() {
        String output = "";
        output += "[";

        if (c == null) {
            output += "a-zA-Z";
        } else {
            output += c.evaluate();
        }

        output += "]";
        return output;
    }
}