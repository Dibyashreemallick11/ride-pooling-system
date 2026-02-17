package com.airport.pooling.util;

public class DistanceUtil {

    private static final double KM_CONVERSION = 111;

    public static double calculateDistance(
            double lat1, double lon1,
            double lat2, double lon2) {

        double latDiff = lat2 - lat1;
        double lonDiff = lon2 - lon1;

        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff) * KM_CONVERSION;
    }
}
