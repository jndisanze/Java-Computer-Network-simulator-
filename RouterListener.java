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
import reso.ip.Datagram;
import reso.ip.IPInterfaceAdapter;
import reso.ip.IPInterfaceListener;
/**
 * classe en cours de construiction
 * but perturbation de message
 * */
public class RouterListener implements IPInterfaceListener {
	@Override
	public void receive(IPInterfaceAdapter src, Datagram datagram)
			throws Exception {
		System.out.println( (Packet)datagram.getPayload());
	}

}
