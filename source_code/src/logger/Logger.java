package logger;

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

    private static boolean isChangedVar(Object reflectionVar, Object realVar) {
        ArrayList<?> list;
        if (reflectionVar instanceof ArrayList<?>) {
            list = (ArrayList<?>) reflectionVar;
            return list.contains(realVar);
        }
        return (reflectionVar.equals(realVar));
    }

    public static void log(Object obj, Object var){
        for(Field field : obj.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try {
                if (isChangedVar(field.get(obj), var)) {
                    write("Class name: " + obj + "\n");
                    write("Variable name: " + field.getName() + "\n");
                    write("Variable value: " + var + "\n");
                    write("+++++++++++++++++++++++++++++++\n");
                };
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void logTwo(Object obj, String varName){
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
