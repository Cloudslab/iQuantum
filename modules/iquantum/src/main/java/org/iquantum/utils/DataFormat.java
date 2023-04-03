package org.iquantum.utils;

public class DataFormat {

    public static double roundDouble(Double number, int decimalPlaces) {
        double factor = Math.pow(10, decimalPlaces);
        return Math.round(number * factor) / factor;
    }
}
