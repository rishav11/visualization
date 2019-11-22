package ast;

import java.util.ArrayList;
import java.util.List;

public class DIGITCONSTRAINT extends DIGITDEF {
    private List<Integer> digits = new ArrayList<>();

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("from");
        digits.add(Integer.parseInt(tokenizer.getNext()));
        tokenizer.getAndCheckNext("to");
        digits.add(Integer.parseInt(tokenizer.getNext()));
        while (tokenizer.checkToken("and")) {
            tokenizer.getAndCheckNext("and");
            digits.add(Integer.parseInt(tokenizer.getNext()));
            tokenizer.getAndCheckNext("to");
            digits.add(Integer.parseInt(tokenizer.getNext()));
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

        for (int i=0; i<digits.size(); i++) {
            output += (digits.get(i));
            if (i%2 == 0) {
                output += ("-");
            }
        }

        return output;
    }
}
