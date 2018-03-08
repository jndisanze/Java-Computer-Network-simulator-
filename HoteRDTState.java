package reso.examples.proto_rdt;
import reso.ip.Datagram;
import reso.ip.IPInterfaceAdapter;
import reso.ip.IPInterfaceListener;
/**
* class modeling different FSM states of the
 * RDT 3.0 protocol
 * */
public abstract class HoteRDTState implements IPInterfaceListener {
	public static final int PROTO =  Datagram.allocateProtocolNumber("PROTO");
	private boolean hasMessage;
	private int numberState;
	public void changeState(ReceiveHote app){
		app.set_state(this);
	}
	@Override
	public void receive(IPInterfaceAdapter src, Datagram datagram)
			throws Exception {}
	public boolean hasMessage(){
		return hasMessage;
	}
	public void hasMassage(boolean has){
		this.hasMessage = has;
	}
	public int getNumberState() {
		return numberState;
	}
	public void setNumberState(int numberState) {
		this.numberState = numberState;
	}
	

}
