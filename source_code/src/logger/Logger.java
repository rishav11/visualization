package logger;
import logger.Logger;

import javafx.beans.value.ObservableValue;

import javax.swing.event.ChangeListener;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;

public class Logger {

    protected static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("variables_log.txt", "UTF-8");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private static void write(String text) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("variables_log.txt", true));
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(Object obj, String varName){
        try {
            Field field = obj.getClass().getDeclaredField(varName);
            field.setAccessible(true);
            write("Class name: " + obj + "\n");
            write("Variable name: " + field.getName() + "\n");
            write("Variable value: " + field.get(obj) + "\n");
            write("+++++++++++++++++++++++++++++++\n");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
