package com.andy.simplecidgetter;

import java.io.IOException;
import java.util.Scanner;

final class PropertyHelper {
    private PropertyHelper() {
        // Hide constructor
    }

    static String getAll() {
        try (Scanner reader = new Scanner(Runtime.getRuntime().exec("getprop")
                .getInputStream())) {
            StringBuilder sb = new StringBuilder();
            String separator = System.lineSeparator();

            while (reader.hasNext()) {
                String s1 = reader.nextLine();

                sb.append(s1);
                sb.append(separator);
            }

            return sb.toString();
        } catch (IOException ex) {
            return "";
        }
    }

    static String getPropertyValue(String propertyName) {
        String result = "";
        try (Scanner reader = new Scanner(Runtime.getRuntime()
                .exec("getprop " + propertyName).getInputStream())) {

            if (reader.hasNext()) {
                result = reader.nextLine();
            }
            return result;
        } catch (IOException ignored) {
        }
        return result;
    }
}
