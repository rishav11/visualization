package ast;

import libs.Node;
import java.util.ArrayList;

public class EXPRESSION extends STATEMENT {

    private String name;
    private ArrayList<RULE> rules = new ArrayList<>();

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("expression");
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("\\{");
        while (!tokenizer.checkToken("\\}")) {
            RULE rule = new RULE();
            rule.parse();
            rules.add(rule);
            if (!tokenizer.checkToken("\\}")) {
                tokenizer.getAndCheckNext(",");
            }
        }
        tokenizer.getAndCheckNext("\\}");
    }

    @Override
    public void nameCheck() {
        Node.names.add(name) ;
    }

    @Override
    public void typeCheck() {

    }

    @Override
    public String evaluate() {
        String output = "";

        writer.print(name + ": ");
        for (RULE s : rules){
            output += s.evaluate();
        }
        writer.print(output);
        writer.print("\n");

        Node.outputs.put(name, output);

        return "";
    }
}
