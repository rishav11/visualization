package ast;
import logger.Logger;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;

public class QUANTIFIER extends STATEMENT {

    private int digit;
    private int toDigit;
    private int isExactly;

    @Override
    public void parse() {
        String d = tokenizer.getNext();
        digit = Integer.parseInt(d);
Logger.logTwo(this, "digit");
        String exact = tokenizer.getNext();
        if (exact.equals("of")) {
            isExactly = 0;
Logger.logTwo(this, "isExactly");
        } else if (exact.equals("ormoreof")) {
            isExactly = 1;
Logger.logTwo(this, "isExactly");
        } else if (exact.equals("to")) {
            isExactly = 2 ;
Logger.logTwo(this, "isExactly");
            toDigit = Integer.parseInt(tokenizer.getNext());
Logger.logTwo(this, "toDigit");
            tokenizer.getAndCheckNext("of");
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
        if (isExactly == 0 && digit != 1) {
            return ("{" + digit + "}");
        } else if (isExactly == 1) {
            if (digit == 0) {
                return ("*");
            } else if (digit == 1) {
                return ("+");
            } else {
                return ("{" + digit + ",}");
            }
        } else if (isExactly == 2) {
            if (digit == 0 && toDigit == 1) {
                return ("?");
            } else {
                return ("{" + digit + "," + toDigit + "}");
            }
        }

        return "";
    }
}
