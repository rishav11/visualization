package ast;

import logger.Logger;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.ArrayList;

public class QUANTIFIER extends STATEMENT {

    private int digit;
    private int toDigit;
    private int isExactly;
    // this variable is to keep track of the whole strong so it passes
    // isChangedVar in logger
    private String loggerString;

    @Override
    public void parse() {
        String d = tokenizer.getNext();
        loggerString = d;

        digit = Integer.parseInt(d);

        String exact = tokenizer.getNext();
        loggerString = loggerString.concat(exact);

        if (exact.equals("of")) {
            isExactly = 0;
            Logger.log(this, loggerString);
        } else if (exact.equals("ormoreof")) {
            isExactly = 1;
            Logger.log(this, loggerString);
        } else if (exact.equals("to")) {
            isExactly = 2;
            String toDigitString = tokenizer.getNext();
            loggerString = loggerString.concat(toDigitString);
            toDigit = Integer.parseInt(toDigitString);
            tokenizer.getAndCheckNext("of");
            loggerString = loggerString.concat("of");

            Logger.log(this, loggerString);
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
