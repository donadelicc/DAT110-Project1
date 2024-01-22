package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;
import no.hvl.dat110.system.controller.Common;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// TODO - START
		
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format

		// Get the approppriate method id (Common.java)
			// rpcid= Header

		// Get the parameters (payload)
			// Payload = parameter
		
		try {
			rpcmsg = new byte[1 + payload.length];
			rpcmsg[0] = rpcid;
			System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		// TODO - END
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;
		
		// TODO - START
		
		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		
		try {
			payload = Arrays.copyOfRange(rpcmsg, 1, rpcmsg.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO - END
		
		return payload;
		
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = null;
		
		// TODO - START 

		// Create bytes representation of the message parameter
		try {
			encoded = str.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO - END
		
		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null; 
		
		// TODO - START 

		// Get the recieved essage/packet
		// Take out ONLY the payload data from the segment
		// Header provided the length of the payload data
		
		
		try {			
			decoded = new String(data, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO - END
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		
		try {
			encoded = new byte[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		// TODO - END
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		// TODO
		
		try {
			if (data.length != 0)
				throw new Exception("Wrong data length");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = null;
		
		// TODO - START 
		
		
		try {
			encoded = ByteBuffer.allocate(4).putInt(x).array();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO - END
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		
		// TODO - START 
		
		try {
			decoded = ByteBuffer.wrap(data).getInt();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO - END
		
		return decoded;
		
	}
}
