package reso.examples.transfer_delay;
import java.util.ArrayList;
import java.util.Date;
import reso.examples.fifo_router.TraitementFichier;
import reso.ip.Datagram;
import reso.ip.IPAddress;
import reso.ip.IPHost;
import reso.ip.IPInterfaceAdapter;
import reso.ip.IPInterfaceListener;
import reso.scheduler.AbstractScheduler;
/ **
  * this class models a queue of a router
  * allows to receive the messages and calculation according to the flow and the current queue
  * the waiting time of a message in the queue
  * Ndisanze Jean p 
  * * /
public class LinkAppListenerRouter implements IPInterfaceListener {
	private final IPHost host;
	private IPAddress IP_ADDR;
	private ArrayList<Double> timesIn = new ArrayList<Double>();
	private ArrayList<Double> timesOut = new ArrayList<Double>();
	private ArrayList<Integer> size = new ArrayList<Integer>();
	private ArrayList<MyMessage> packets = new ArrayList<MyMessage>();
	private AbstractScheduler scheduler;
	private double debit;
	private String filename;
	public static int MAX_LENGTH = 150;
	public LinkAppListenerRouter(IPHost host,IPAddress IP_ADDR,double debit,AbstractScheduler scheduler,int fileLength) {
		 this.IP_ADDR = IP_ADDR;
		 this.host= host;
		 this.scheduler = scheduler;
		 this.debit = debit;
		 this.filename = new Date().toString()+".txt";
		 LinkAppListenerRouter.MAX_LENGTH = fileLength;
		}
	public LinkAppListenerRouter(IPHost host,IPAddress IP_ADDR,double debit,AbstractScheduler scheduler) {
		 this.IP_ADDR = IP_ADDR;
		 this.host= host;
		 this.scheduler = scheduler;
		 this.debit = debit;
		 this.filename = new Date().toString()+".txt";
		}
	@Override
	public void receive (IPInterfaceAdapter src, Datagram datagram) throws Exception {
		if(this.packets.size()< LinkAppListenerRouter.MAX_LENGTH){
		MyMessage msg= (MyMessage) datagram.getPayload();
    	double schTime = host.getNetwork().getScheduler().getCurrentTime();
    		timesIn.add(schTime);
    		this.packets.add(msg);
    		timesOut.add(schTime+(msg.getLength()/(debit*1000.)));
    		this.size.add(0);
    		int count = 0;
    		int i;
    		for(i = 0 ;i<timesIn.size();i++){
    			if(this.timesOut.get(i) >= schTime){
    				count ++;
    			}
    		}
    		this.size.set(i-1,count);
    		//file d'attente 
    		scheduler.schedule(new MyEvent(timesOut.get(i-1),host,this.IP_ADDR,msg));
    		// enregistrement des temps d'attente et la taille de la file dans un fichier 
    		if(msg.toString().equals("EOF")){
    		ArrayList<String> timeFile = new ArrayList<String>();	
    		for(int j = 0 ; j < timesIn.size(); j++){
    			timeFile.add(timesIn.get(j)+"\t"+size.get(j));
    			}
    			TraitementFichier.writePoint(this.filename,timeFile);
    		}
    	}
		else
		System.out.println("packet : " +  (MyMessage) datagram.getPayload() + " is discarded");	
	}
}