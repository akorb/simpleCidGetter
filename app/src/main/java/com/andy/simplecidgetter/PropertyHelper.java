package com.andy.simplecidgetter;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

final class PropertyHelper {
    private PropertyHelper() {
        // Hide constructor
    }

    public static String getAll() {
        try {
            Scanner reader = new Scanner(Runtime.getRuntime().exec("getprop")
                    .getInputStream());

            StringBuilder sb = new StringBuilder();
            String separator = System.getProperty("line.separator");

            while (reader.hasNext()) {
                String s1 = reader.nextLine();

                sb.append(s1);
                sb.append(separator);
            }

            reader.close();

            return sb.toString();
        } catch (IOException ex) {
            return "";
        }
    }

    public static String getPropertyValue(String propertyName) {
        try {
            Scanner reader = new Scanner(Runtime.getRuntime()
                    .exec("getprop " + propertyName).getInputStream());

            String result;

            if (reader.hasNext()) {
                result = reader.nextLine();
            } else {
                result = "";
            }

            reader.close();
            return result;
        } catch (IOException ex) {
            return "";
        }
    }

    public static String getCid() {
        // The cid is stored usually stored in ro.cid
        String primaryCid = getPropertyValue("ro.cid");
        if (!primaryCid.isEmpty())
            return primaryCid.toUpperCase(Locale.getDefault());
        // ro.cid does not exist for each device.
        // I guess it's for GPE devices, but this isn't for sure yet.
        String fallbackCid = getPropertyValue("ro.boot.cid");
        if (!fallbackCid.isEmpty())
            return fallbackCid.toUpperCase(Locale.getDefault());
        // When cid does not exist, return empty string.
        return "";
    }
}
