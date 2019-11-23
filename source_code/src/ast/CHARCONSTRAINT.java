package ast;

import logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class CHARCONSTRAINT extends CHARDEF {
    private List<String> chars = new ArrayList<>();
    private String loggerString;

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("from");
        String firstCharConstraint = tokenizer.getNext();
        chars.add(firstCharConstraint);
        loggerString = "from" + firstCharConstraint;

        tokenizer.getAndCheckNext("to");
        String secondCharConstraint = tokenizer.getNext();
        chars.add(secondCharConstraint);
        loggerString = loggerString.concat("to" + secondCharConstraint);

        while (tokenizer.checkToken("and")) {
            tokenizer.getAndCheckNext("and");
            String xCharConstraint = tokenizer.getNext();
            chars.add(xCharConstraint);
            loggerString = loggerString.concat("and" + xCharConstraint);

            tokenizer.getAndCheckNext("to");
            String yCharConstraint = tokenizer.getNext();
            chars.add(yCharConstraint);
            loggerString = loggerString.concat("to" + yCharConstraint);
        }

        Logger.log(this, loggerString);
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

        for (int i=0; i<chars.size(); i++) {
            output += chars.get(i);
            if (i%2 == 0) {
                output += "-";
            }
        }

        return output;
    }
}
