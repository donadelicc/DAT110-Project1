package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		try {
			connection = msgclient.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 public byte[] call(byte rpcid, byte[] param) {
		byte[] returnval = null;

		try {
			byte[] rpcmsg = RPCUtils.encapsulate(rpcid, param);
			connection.send(new Message(rpcmsg));
			System.out.println("RPCClient: Message sent. Waiting for reply...");
	
			Message msg = connection.receive();
	
			if(msg == null) {
				System.out.println("RPCClient: Received null message.");
			} else {
				System.out.println("RPCClient: Message received. Decapsulating...");
				returnval = RPCUtils.decapsulate(msg.getData());
			}
		} catch (Exception e) {
			e.printStackTrace();    
		}
		return returnval;
	}
	

}
