package nmea4j.gps;

import java.util.Date;

/**
 * GPRMC message
 * 
 * @author Jamie Archibald
 * 
 */
public class GPRMC {

	private double latitude;
	private double longitude;
	private Date dateTime;
	private String navigationReceiverWarning;
	private double speedOverGround;
	private double course;
	private double magneticVariation;
	private int checksum;

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNavigationReceiverWarning() {
		return navigationReceiverWarning;
	}

	public void setNavigationReceiverWarning(String navigationReceiverWarning) {
		this.navigationReceiverWarning = navigationReceiverWarning;
	}

	public double getSpeedOverGround() {
		return speedOverGround;
	}

	public void setSpeedOverGround(double speedOverGround) {
		this.speedOverGround = speedOverGround;
	}

	public double getCourse() {
		return course;
	}

	public void setCourse(double course) {
		this.course = course;
	}

	public double getMagneticVariation() {
		return magneticVariation;
	}

	public void setMagneticVariation(double magneticVariation) {
		this.magneticVariation = magneticVariation;
	}

	public int getChecksum() {
		return checksum;
	}

	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}

}
