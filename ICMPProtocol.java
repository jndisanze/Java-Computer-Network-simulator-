/*******************************************************************************
 * Copyright (c) 2017 Bruno Quoitin.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Bruno Quoitin - initial API and implementation
 *      Ndisanze jean p
 ******************************************************************************/
package reso.ip;

import reso.common.AbstractApplication;
import reso.common.Message;

public class ICMPProtocol
extends AbstractApplication
implements IPInterfaceListener {
	
	public static final String PROTO_NAME= "ICMP";
	public static final int PROTO_NUM= Datagram.allocateProtocolNumber(PROTO_NAME);
	
	public class ICMPMessage
	implements Message {
		
		public static final int TYPE_ECHO_REQUEST= 0;
		public static final int TYPE_ECHO_REPLY  = 1;
		
		public final int type;
		
		public ICMPMessage(int type) {
			this.type= type;
		}
		
	}

	private final IPLayer ip;
	
	public ICMPProtocol(IPHost host) {
		super(host, PROTO_NAME);
		ip= host.getIPLayer();
	}
	
	@Override
	public void start() throws Exception {
		ip.addListener(PROTO_NUM, this);
	}
	
	@Override
	public void stop() {
		ip.removeListener(PROTO_NUM, this);
	}
	
	@Override
	public void receive(IPInterfaceAdapter src, Datagram datagram)
			throws Exception {
		ICMPMessage msg= (ICMPMessage) datagram.getPayload();
		switch (msg.type) {
		case ICMPMessage.TYPE_ECHO_REQUEST:
			ip.send(IPAddress.ANY, datagram.src, PROTO_NUM, new ICMPMessage(ICMPMessage.TYPE_ECHO_REPLY));
			break;
		}
	}
	
	public void sendRequest(IPAddress dst)
		throws Exception {
		ip.send(IPAddress.ANY, dst, PROTO_NUM, new ICMPMessage(ICMPMessage.TYPE_ECHO_REQUEST));
	}

}
