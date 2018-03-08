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
package reso.examples.transfer_delay;
import java.io.UnsupportedEncodingException;
import reso.common.Message;
/**
 * classe modelisant un message de la couche application
 * */
public class MyMessage implements Message{
	private byte[] message;
	private int length;
	private int ttl = 1;
	public static int numSequence = 0;
	public MyMessage(byte[] message){
		this.message = message;
		MyMessage.numSequence = MyMessage.numSequence + 1;
		this.setLength(message.length);
	}
	public void setLength(int length) {
		this.length = length;
		
	}
	public byte[] getMessage(){
		return message;
	}
	public String toString(){
		String st = "";
		try {
			st= new String(message,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return st;
	}
	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param ttl the ttl to set
	 */
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}
	/**
	 * @return the ttl
	 */
	public int getTtl() {
		return this.ttl;
	}
}