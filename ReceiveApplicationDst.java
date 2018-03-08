/*******************************************************************************
 * Copyright (c) 2017 Ndisanze Jean P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Ndisanze Jean P - initial API and implementation
 ******************************************************************************/
package reso.examples.proto_rdt;
import reso.common.AbstractApplication;
import reso.ip.IPAddress;
import reso.ip.IPHost;
import reso.ip.IPLayer;
public class ReceiveApplicationDst extends AbstractApplication implements ReceiveHote {
	private  IPLayer ip;
	private HoteRDTState current_State;
	public ReceiveApplicationDst(IPHost host,IPAddress IP_ADDR) {
		super(host,"hote2");
		ip= host.getIPLayer();
    	this.current_State = WaitPack0.getInstance(host,IP_ADDR,(ReceiveHote)this);
	}
	public void set_state(HoteRDTState state){
		ip.removeListener(HoteRDTState.PROTO, current_State);
		this.current_State = state;
		ip.addListener(HoteRDTState.PROTO, current_State);
	}
	public void start() throws Exception{
			ip.addListener(HoteRDTState.PROTO, current_State);
	}
	public void stop() {}
	@Override
	public void sendNextPacket() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendCurrentPacket() {
		// TODO Auto-generated method stub
		
	}
}