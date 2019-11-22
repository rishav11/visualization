package ast;

public class STRINGDEF extends KEYWORD {
    private String str;

    @Override
    public void parse(){
        str = tokenizer.getNext();
    }

    @Override
    public void nameCheck() {

    }

    @Override
    public void typeCheck() {

    }

    @Override
    public String evaluate() {
        if (str.equals("any")) {
            return ".";
        } else if (str.equals("non-digit")) {
            return "\\D";
        } else if (str.equals("whitespace")) {
            return "\\s";
        } else if (str.equals("non-whitespace")) {
            return "\\S";
        } else if (str.equals("newline")) {
            return "\\n";
        } else if (str.equals("tab")) {
            return "\\t";
        }
        String sanitizedString = str.replaceAll("\"", "");
        return sanitizedString.replaceAll("[\\<\\(\\[\\{\\\\\\^\\-\\=\\$\\!\\|\\]\\}\\)\\?\\*\\+\\.\\>]", "\\\\$0");
    }
}
