package nmea4j.ais;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.math.BigInteger;
import java.util.BitSet;

import org.apache.log4j.Logger;

/**
 * Decodes AIS message
 * 
 * @author Jamie Archibald
 * 
 */
public class Decoder {

	private static final Logger log = Logger.getLogger(Decoder.class);
	
	public MessageType1 decode(String msg) throws Exception {
		MessageType1 result = null;
		String[] parts = msg.split(",");

		String nmeaMessageType = parts[0];
		int numSentences = Integer.parseInt(parts[1]);
		int sentenceNumber = Integer.parseInt(parts[2]);
		int sequentialMessageId = parts[3].length() == 0 ? -1 : Integer
				.parseInt(parts[3]);
		char channel = parts[4].toCharArray()[0];
		String aisData = parts[5];
		String endData = parts[6].substring(0, 2);
		String checksum = parts[6].substring(2, 4);

		if (!nmeaMessageType.equals("!AIVDM")) {
			throw new IllegalArgumentException(
					"Message does not start with !AIVDM");
		}

		// In the NMEA encoding for AIS - each ASCII character corresponds to 6
		// binary bits (unlike normal ASCII which uses 8 bits) so you need to
		// step through each character and subtract 48 from the ASCII - then if
		// it's still a decimal number > 40 subtract another 8 - then convert to
		// binary: this guarantees a 6 bit number. Looking at our data (just the
		// first few characters)
		char[] data = aisData.toCharArray();
		for (int i = 0; i < data.length; i++) {
			int c = (int) data[i] - 48;
			if (c > 40) {
				c -= 8;
			}
			// each character is now 6 bits
			data[i] = (char) c;
		}

		int msgType = data[0];

		if (msgType == 1) {
			result = new MessageType1();

			// Data terminal ready (0 = available, 1 = not available)
			int dataTerminalEquipment = data[1] &= 0x80;

			// Indicates data available to transmit (0 = not available 1 =
			// available)
			int dataIndicator = data[1] &= 0x40;

			byte[] mmsiBytes = new byte[6];
			for (int i=1; i<6; i++) {
				// get the bits
				
				
				
				
			}
			
			int mmsi = new BigInteger(mmsiBytes).intValue();
			mmsi >>= 4; // shift 4 bits
			result.setMMSI(mmsi);
			log.debug(mmsi);
			
		} else if (msgType == 2) {

		} else if (msgType == 3) {

		} else {
			throw new IllegalArgumentException("Unsupported message type: " + 3);
		}

		// MMSI Number - starting from bit 8 for 30 bits

		return result;
	}
}
