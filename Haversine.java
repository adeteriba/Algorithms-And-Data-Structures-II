/*
I wrote a program which reads in two locations in GPS and finds the precise distance in km between them. 
Use it to find the distance between where you are sitting right now and the iconic Ryugyong Hotel in Pyongyang.

The haversine formula is an equation important in navigation, 
giving great-circledistances between two points on a sphere from their longitudes and latitudes

*/

import java.util.*;

public class Haversine {
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			double z = sc.nextDouble();
			double a = sc.nextDouble();
			System.out.println(distance(x,y,z,a));
			sc.close();
	}
	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // <-- d
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

}
