package reso.examples.transfer_delay;
import reso.ip.IPAddress;
import reso.ip.IPHost;
import reso.ip.IPLayer;
import reso.scheduler.AbstractEvent;
/**
* class modeling an event in the simulator
  * and assigns a time t and an action (the sending) of a
  * message to the addressee at time t
   * Ndisanze Jean p 
 * */
public class MyEvent extends AbstractEvent {

	private IPAddress dst;
	private IPLayer ip;
	private MyMessage pack;
	private int protocole;
	private boolean send = true;
	private double time1;
	
	public MyEvent(double time,IPHost host, IPAddress dst, MyMessage pack) {
		super(time);
		this.dst= dst;
    	ip= host.getIPLayer();
    	this.pack=pack;
    	this.time1 = time;
    	protocole = LinkProtocolNumber.IP_PROTO_LINK;
	}
	public MyEvent(double time,IPHost host, IPAddress dst,int protocole,MyMessage pack) {
		super(time);
		this.dst= dst;
    	ip= host.getIPLayer();
    	this.pack=pack;
    	this.protocole = protocole;
	}
	@Override
	public void run() throws Exception {
		if(send)
		ip.send(IPAddress.ANY, dst,this.protocole,pack);		
	}
	public String  toString(){
		return "message : "+pack.toString()+" time : " + time1;
	}
}
