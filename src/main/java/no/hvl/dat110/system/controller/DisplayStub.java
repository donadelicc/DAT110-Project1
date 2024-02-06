package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		// TODO - START
		
		// marshall parameter to write call (a string)
		byte[] request = RPCUtils.marshallString(message);
		
		// acknowledge the call
		byte[] response = rpcclient.call((byte)Common.WRITE_RPCID, request);

		// unmarshall the return value from the call (an integer)
		RPCUtils.unmarshallVoid(response);
		
		// TODO - END
		
	}
}
