package reso.examples.proto_rdt;
import reso.examples.transfer_delay.MyMessage;
/**
* class modeling an ACK or NACK segment of the
  * transport layer with a verifying checksum
  * data corruption
 * */
public class ACKnow extends MyMessage{
	public ACKnow(int numberACK, boolean isACK,byte[] checksum) {
		super((numberACK+","+isACK).getBytes());
		this.numberACK = numberACK;
		this.isACK = isACK;
		this.checksum = checksum;
	}
	private int numberACK;
	private boolean isACK;
	private byte[] checksum;
	public int getNumberACK() {
		return numberACK;
	}
	public void setNumberACK(int numberACK) {
		this.numberACK = numberACK;
	}
	public boolean isACK() {
		return isACK;
	}
	public void setACK(boolean isACK) {
		this.isACK = isACK;
	}
	public byte[] getChecksum() {
		return checksum;
	}
	public void setChecksum(byte[] checksum) {
		this.checksum = checksum;
	}
}	
