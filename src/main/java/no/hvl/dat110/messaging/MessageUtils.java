package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;

		// TODO
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer

		// Set the segment length to 128 bytes
		segment = new byte[SEGMENTSIZE];
		// Get the message data
		data = message.getData();
		// Set the first byte of the segment to the length of the data --> the header
		byte header = (byte) data.length;
		segment[0] = header;

		// Copy data into segment starting at index 1
		System.arraycopy(data, 0, segment, 1, data.length);

		return segment;
		// TODO - END
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		// decapsulate segment and put received payload data into a message

		// Get the revieced message/packet
		// Take out ONLY the payload data from the segment
		// Header provided in the Message class

		// Get the header
		byte header = segment[0];
		// Get the payoad
		byte[] payload = Arrays.copyOfRange(segment, 1, 1+ header);
		// Create a new message with the data
		message = new Message(payload);
		
		// TODO - END
		
		return message;
		
	}
	
}
