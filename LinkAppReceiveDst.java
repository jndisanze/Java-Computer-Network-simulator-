package reso.examples.transfer_delay;
import reso.common.AbstractApplication;
import reso.ip.IPHost;
public class LinkAppReceiveDst extends AbstractApplication  {

	private IPHost host1;

	public LinkAppReceiveDst(IPHost host) {
		super(host,"Destination");
		this.host1 = host;
	}

	@Override
	public void start() throws Exception {
		host1.getIPLayer().addListener(LinkProtocolNumber.IP_PROTO_LINK,new LinkAppListenerDst(host1));
		host1.getIPLayer().disableForwarding();
	}

	@Override
	public void stop() {}
}
