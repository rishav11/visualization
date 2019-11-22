package libs;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {


    private static String program;
    private static List<String> literals;
    private String[] tokens;
    private int currentToken;
    private static Tokenizer theTokenizer;

    private Tokenizer(String filename, List<String> literalsList){
        literals = literalsList;
        try {
            program = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Didn't find file");
            System.exit(0);
        }
        tokenize();
    }

    private void tokenize (){
        String tokenizedProgram = program;
        tokenizedProgram = tokenizedProgram.replaceAll("\\n(?=(?:\"[^\"]*\"|[^\"])*$)","");
//        tokenizedProgram = tokenizedProgram.replaceAll("([0-9]+)","~$1~");
        System.out.println(program);

        for (String s : literals){
            String el = s.replaceAll("[\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)\\?\\*\\+\\.\\>]", "\\\\$0");
            tokenizedProgram = tokenizedProgram.replaceAll("(" + el + ")(?=(?:\"[^\"]*\"|[^\"])*$)","~"+s+"~");
            System.out.println(tokenizedProgram);
        }

        System.out.println(tokenizedProgram);

        tokenizedProgram = tokenizedProgram.replaceAll("\\s(?=(?:\"[^\"]*\"|[^\"])*$)","");
        System.out.println(tokenizedProgram);

        tokenizedProgram = tokenizedProgram.replaceAll("(\"([^\"])*\")","~$0~");
        tokenizedProgram = tokenizedProgram.replaceAll("~non-~whitespace~","~non-whitespace~");
        tokenizedProgram = tokenizedProgram.replaceAll("~non-~digit~","~non-digit~");
        tokenizedProgram = tokenizedProgram.replaceAll("~starts~with~","~startswith~");
        tokenizedProgram = tokenizedProgram.replaceAll("~ends~with~","~endswith~");
        tokenizedProgram = tokenizedProgram.replaceAll("~or~m~or~e~of~","~ormoreof~");

        System.out.println(tokenizedProgram);
        String [] temparray=tokenizedProgram.split("[~]+");
        tokens = new String[temparray.length-1];

        System.arraycopy(temparray,1,tokens,0,temparray.length-1);
        System.out.println(Arrays.asList(tokens));
    }

    private String checkNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }

    public String getNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
            currentToken++;
        }
        else
            token="NULLTOKEN";
        return token;
    }


    public boolean checkToken(String regexp){
        String s = checkNext();
        System.out.println("comparing: |"+s+"|  to  |"+regexp+"|");
        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp){
        String s = getNext();
        if (!s.matches(regexp)) {
            System.out.println("FAILED!!!!  on "+ regexp);
            System.exit(0);
        }
        System.out.println("matched: "+s+"  to  "+regexp);
        return s;
    }

    public boolean moreTokens(){
        return currentToken<tokens.length;
    }

    public static void makeTokenizer(String filename, List<String> literals){
        if (theTokenizer==null){
            theTokenizer = new Tokenizer(filename,literals);
        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

}
