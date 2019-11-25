package ast;
import logger.Logger;

public class DIGITDEF extends KEYWORD {
    private DIGITCONSTRAINT d;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("digit");
        if (tokenizer.checkToken("from")) {
            d = new DIGITCONSTRAINT();
Logger.logTwo(this, "d");
            d.parse();
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

        if (d == null) {
            output += "0-9";
        } else {
            output += d.evaluate();
        }

        output += "]";
        return output;
    }
}