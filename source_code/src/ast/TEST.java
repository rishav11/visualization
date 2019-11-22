package ast;

import libs.NameCheckException;
import libs.Node;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TEST extends STATEMENT {

    private String name ;
    private ArrayList<String> tests =  new ArrayList<>();

    @Override
    public void parse(){
        tokenizer.getAndCheckNext("test") ;
        name = tokenizer.getNext();
        tokenizer.getAndCheckNext("with");
        tokenizer.getAndCheckNext("\\{");
        while(!tokenizer.checkToken("\\}")){
            String test = tokenizer.getNext();
            tests.add(test) ;
            if (!tokenizer.checkToken("\\}")) {
                tokenizer.getAndCheckNext(",");
            }
        }
        tokenizer.getAndCheckNext("\\}");
    }

    @Override
    public void nameCheck() {
        if(!Node.names.contains(name)){
            throw new NameCheckException(name) ;
        }
    }

    @Override
    public void typeCheck() {

    }

    @Override
    public String evaluate() {
        writer.println("**********TESTS FOR " + name + "**********");
        for(String test : tests) {
            Boolean isMatch = Pattern.matches(Node.outputs.get(name), test.replaceAll("\"", ""));
            if (isMatch) {
                writer.print("FOUND MATCH: ");
            } else {
                writer.print("NO MATCH FOUND: ");
            }
            writer.println(test) ;
        }
        return "" ;
    }
}
