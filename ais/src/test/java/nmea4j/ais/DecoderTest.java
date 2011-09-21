package nmea4j.ais;

import static org.junit.Assert.*;

import nmea4j.ais.Decoder;
import nmea4j.ais.MessageType1;

import org.junit.Test;


public class DecoderTest {

	@Test(expected=IllegalArgumentException.class)
	public void invalid() throws Exception {
		String msg = "IVDM,1,1,,A,14eG;o@034o8sd<L9i:a;WF>062D,0*7D"; 
		Decoder d = new Decoder();
		d.decode(msg);
	}
	
	@Test
	public void msg() throws Exception {
		String msg = "!AIVDM,1,1,,A,14eG;o@034o8sd<L9i:a;WF>062D,0*7D"; 
		Decoder d = new Decoder();
		MessageType1 result = d.decode(msg);
		assertNotNull(result);
		assertEquals("316005417", result);
	}
}
