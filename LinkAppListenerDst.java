package reso.examples.transfer_delay;
import reso.ip.Datagram;
import reso.ip.IPHost;
import reso.ip.IPInterfaceAdapter;
import reso.ip.IPInterfaceListener;
/ **
  * this class is message listener arriving one on host
  * and displaying different information of the message
  * * /
public class LinkAppListenerDst implements IPInterfaceListener{
	public LinkAppListenerDst(IPHost host) {
		this.host = host;
	}
	private IPHost host;
	@Override
	public void receive(IPInterfaceAdapter src, Datagram datagram)
			throws Exception {
		MyMessage msg= (MyMessage) datagram.getPayload();
		double time = host.getNetwork().getScheduler().getCurrentTime();
		System.out.println("(" + time + "ms)" +
		" host= " + host.name + ", dgram.src=" + datagram.src + ", dgram.dst=" +
		datagram.dst + ", iif= " + src + " \n"+ msg+" "+msg.getLength()); 
		
	}

}
