package nmea4j.gps;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * GPRMC sentence decoder
 * @author Jamie Archibald
 *
 */
public class GPRMCDecoder {

	private static SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy HHmmss");
	
	static {
		sdf.setCalendar(Calendar.getInstance(TimeZone.getTimeZone("UTC")));
	}
	
	public static GPRMC decode(String sentence) throws Exception {
		GPRMC gprmc = new GPRMC();
		if (!sentence.startsWith("$GPRMC")) {
			throw new Exception("Expected sentence starting with $GPRMC but got: " + sentence);
		}
		String[] parts = sentence.split(",");
		if (parts.length != 12) {
			throw new Exception("Expected 12 words but found " + parts.length);
		}

		double lat = Double.valueOf(parts[3]);
		String latDir = parts[4];
		if (latDir.compareTo("S") == 0) {
			lat *= -1;
		} else if (latDir.compareTo("N") != 0) {
			throw new Exception("Invalid latitude direction: " + latDir);
		}
		
		gprmc.setLatitude(lat);
		
		double lon = Double.valueOf(parts[5]);
		String lonDir = parts[6];
		if (lonDir.compareTo("W") == 0) {
			lon *= -1;
		} else if (latDir.compareTo("E") != 0) {
			throw new Exception("Invalid longitude direction: " + latDir);
		}
		gprmc.setLongitude(lon);
		
		String nrw = parts[2];
		if (nrw.compareTo("A") != 0 && nrw.compareTo("V") != 0) {
			throw new Exception("Invalid navigational receiver warning: " + nrw);
		}
		gprmc.setNavigationReceiverWarning(nrw);
		
		int timeOfFix = Integer.parseInt(parts[1]);
		int dateOfFix = Integer.parseInt(parts[9]);
		
		Date d = sdf.parse(dateOfFix + " " + timeOfFix);
		gprmc.setDateTime(d);
		
		gprmc.setSpeedOverGround(Double.valueOf(parts[7]));
		gprmc.setCourse(Double.valueOf(parts[8]));
		gprmc.setMagneticVariation(Double.valueOf(parts[10]));
		gprmc.setChecksum(Integer.valueOf(parts[11].substring(2, 4)));
		
		return gprmc;
	}
}
