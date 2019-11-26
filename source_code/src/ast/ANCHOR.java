package ast;
import logger.Logger;

public class ANCHOR extends STATEMENT {

    private String anchor;

    @Override
    public void parse() {
        anchor = tokenizer.getNext();
Logger.log(this, "anchor");
    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }

    @Override
    public String evaluate() {
        if (anchor.equals("startswith")) {
            return "^";
        } else if (anchor.equals("endswith")) {
            return "$";
        }
        return "";
    }
}
