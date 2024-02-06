package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		byte[] data; // data and segment are interchangeable here
		
		// TODO - START
		// encapsulate the data contained in the Message and write to the output stream
		
		data = MessageUtils.encapsulate(message);

		try {
			outStream.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		// TODO - END

	}

	public Message receive() {

		Message message = null;
		byte[] data;
		
		try {
			// Read the first byte to get the message length
			int length = inStream.read();
	
			// Check for end-of-stream condition
			if (length == -1) {
				System.out.println("End of stream reached, no more data to read.");
				// Optionally, throw an exception or handle this case as per your application logic
			} else if (length > 0) {
				// Ensure the length is within the expected range
				data = new byte[length];
				// Read the message data
				inStream.readFully(data, 0, length);
				// Decapsulate the data into a message
				message = MessageUtils.decapsulate(data);
			} else {
				// Handle case where length is not within the valid range
				System.out.println("Received length is not valid: " + length);
				// Optionally, throw an exception or handle this case as per your application logic
			}
		} catch (IOException e) {
			e.printStackTrace();
			// Optionally, handle this exception more gracefully, e.g., by logging or rethrowing as a custom exception
		}
		
		return message;
		
	}
	

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}