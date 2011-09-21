package nmea4j.gps;

import static org.junit.Assert.*;

import org.junit.Test;

public class GPRMCDecoderTest {

	@Test
	public void decode() throws Exception {
		String sentence = "$GPRMC,225446,A,4916.45,N,12311.12,W,000.5,054.7,191194,020.3,E*68";
		GPRMC gprmc = GPRMCDecoder.decode(sentence);
		assertNotNull(gprmc);
		assertEquals("A", gprmc.getNavigationReceiverWarning());
		assertEquals(000.5, gprmc.getSpeedOverGround(), 0.0);
		assertEquals(054.7, gprmc.getCourse(), 0.0);
		assertEquals(020.3, gprmc.getMagneticVariation(), 0.0);
		assertEquals(68, gprmc.getChecksum());
		assertEquals(4916.45, gprmc.getLatitude(), 0.0);
		assertEquals(12311.12, gprmc.getLongitude(), 0.0);
	}
}
