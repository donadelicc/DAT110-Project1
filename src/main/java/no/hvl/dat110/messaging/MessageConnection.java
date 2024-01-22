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
		
		// TODO - START
		// read a segment from the input stream and decapsulate data into a Message
		
		try {
			// Read the first byte to get the message length
			int length = inStream.read();
			if (length > 0) {
				// Initialize the data array with the length
				data = new byte[length];
				// Read the message data
				inStream.readFully(data, 0, length);
				// Decapsulate the data into a message
				message = MessageUtils.decapsulate(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO - END
		
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