package ast;

import logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DIGITCONSTRAINT extends DIGITDEF {
    private List<Integer> digits = new ArrayList<>();
    private String loggerString;

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("from");
        String firstDigitConstraint = tokenizer.getNext();
        loggerString = "from" + firstDigitConstraint;
        digits.add(Integer.parseInt(firstDigitConstraint));

        tokenizer.getAndCheckNext("to");
        String secondDigitConstraint = tokenizer.getNext();
        digits.add(Integer.parseInt(secondDigitConstraint));
        loggerString = loggerString.concat("to" + secondDigitConstraint);

        while (tokenizer.checkToken("and")) {
            tokenizer.getAndCheckNext("and");
            String xDigitConstraint = tokenizer.getNext();
            digits.add(Integer.parseInt(xDigitConstraint));
            loggerString = loggerString.concat("and" + xDigitConstraint);

            tokenizer.getAndCheckNext("to");
            String yDigitConstraint = tokenizer.getNext();
            digits.add(Integer.parseInt(yDigitConstraint));
            loggerString = loggerString.concat("to" + yDigitConstraint);
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

        for (int i=0; i<digits.size(); i++) {
            output += (digits.get(i));
            if (i%2 == 0) {
                output += ("-");
            }
        }

        return output;
    }
}
