package ast;
import logger.Logger;

import java.util.ArrayList;

public class RULE extends STATEMENT {

    private ANCHOR anchor;
    private QUANTIFIER quantifier;
    private ArrayList<KEYWORD> keywords = new ArrayList<>();

    @Override
    public void parse() {
        // check if it has anchor, if yes
        if (!tokenizer.checkToken("[0-9]+")) {
            anchor = new ANCHOR();
Logger.log(this, "anchor");
            anchor.parse();
        }
        quantifier = new QUANTIFIER();
Logger.log(this, "quantifier");
        quantifier.parse();
        tokenizer.getAndCheckNext(":");
        while (!tokenizer.checkToken(",")) {
            if (tokenizer.checkToken("\\}"))
                break;
            if (!tokenizer.checkToken("or")) {
                KEYWORD keyword = new KEYWORD();
                keyword.parse();
                keywords.add(keyword);
Logger.log(this, "keywords");
            }
            if (tokenizer.checkToken("or")) {
                tokenizer.getNext();
            }
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

        String anchorRegEx = "";
        if (anchor != null) {
            anchorRegEx = anchor.evaluate();
        }
        if (anchorRegEx.equals("^")) {
            output += "^";
        }

        output += "(";
        for (int i = 0; i < keywords.size(); i++) {
            output += keywords.get(i).evaluate();
            if (i + 1 < keywords.size()) {
                output += "|";
            }
        }
        output += ")";
        output += quantifier.evaluate();

        if (anchorRegEx.equals("$")) {
            output += "$";
        }

        return output;
    }

}
