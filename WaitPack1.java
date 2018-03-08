package reso.examples.proto_rdt;
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
import reso.ip.Datagram;
import reso.ip.IPAddress;
import reso.ip.IPHost;
import reso.ip.IPInterfaceAdapter;
/**
 * classe modelisant l'etat FSM wait pack 1 du
 * protocole RDT 3.0 
 * */
public class WaitPack1 extends HoteRDTState {
	private IPHost host;
	private IPAddress dst;
	private ReceiveHote receive;
	private WaitPack1(IPHost host, IPAddress dst,ReceiveHote receive) {
		this.host = host;
		this.dst = dst;
		this.receive = receive;
	}
	private static WaitPack1 instance;
	public final static WaitPack1 getInstance(IPHost host, IPAddress dst,
			ReceiveHote receive){
        if (WaitPack1.instance == null) {
           synchronized(WaitPack1.class) {
             if (WaitPack1.instance == null) {
            	 WaitPack1.instance = new WaitPack1(host,dst,receive);
             }
           }
        }
        return WaitPack1.instance;
	}
	@Override
	public void receive(IPInterfaceAdapter src, Datagram datagram)
			throws Exception {
		Packet pkt = (Packet)datagram.getPayload();
		if(Joker.isAs()){	
			//simulation de la corruption du packet	
			pkt.setChecksum(Cryptage.crypt("messageFail"));
		}
		if(pkt.getPackNumber() == 1){
		if(Cryptage.decrypt(pkt.getChecksum()).equals(pkt.toString())){
			// si rd.nextInt(10)%2 == 0 alors on simule une perte de paquet
			 if(Joker.isFigure()){	
				 System.out.println("pack reçu Etat 1  not corrupt " + pkt +" " +pkt.numSequence);
				 ACKnow ack = new ACKnow(1,true,Cryptage.crypt("1,true")); 
				 if(Joker.isXFigure()){
						// simulation de la perte de ACK 
					 	System.out.println(" <perte de packet> ACK : " + ack +" " +ack.numSequence +"\n");
						this.receive.set_state((HoteRDTState)WaitPack1.getInstance(host,dst,receive));
				        }
				else{
				  		host.getIPLayer().send(
					  			IPAddress.ANY,dst,HoteRDTState.PROTO,ack);
					  	this.receive.set_state((HoteRDTState)WaitPack1.getInstance(host,dst,receive));
					}
			     }
		else 
			System.out.println(" <perte de packet> Retransmission  de : " + pkt +" " +pkt.numSequence +"\n");
		 }	
		  else if(!Cryptage.decrypt(pkt.getChecksum()).equals(pkt.toString())){
			  			System.out.println("pack reçu Etat 1 corrupt " + pkt +" " +pkt.numSequence );
			  			ACKnow ack1 = new ACKnow(1,false,Cryptage.crypt("1,false"));
			  			if(Joker.isXFigure())
			  				host.getIPLayer().send(
			  						IPAddress.ANY,dst,HoteRDTState.PROTO,ack1);
			  	else
			  			System.out.println(" <perte de packet> NACK : " + ack1 +" " +ack1.numSequence +"\n");
				 }
			}
		else{
			System.out.println("duplication message");
		}	
	}
	public int lancedes(){
		return (int)(1+6*Math.random());
	}	
}
