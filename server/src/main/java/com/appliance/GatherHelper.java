package com.appliance;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GatherHelper {
    public static String executeCommand(String command) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ProcessUtils.executeCommandLine(command, out, out);
        String result = out.toString();
        return result.trim();
    }


    public static double parseDouble(String text) {
        if (text == null || text.length() == 0) {
            return 0;
        }
        int index = text.indexOf(' ');
        if (index != -1) {
            text = text.substring(0, index);
        }
        return Double.parseDouble(text);
    }

    public static double parsePercent(String text) {
        if (text == null) {
            return 0;
        }
        if (text.endsWith("%")) {
            text = text.substring(0, text.length() - 1);
        }
        text = text.trim();
        return Double.parseDouble(text);
    }

}

