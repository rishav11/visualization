package ast;

public class ANCHOR extends STATEMENT {

    private String anchor;

    @Override
    public void parse() {
        anchor = tokenizer.getNext();
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
