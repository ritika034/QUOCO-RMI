
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of the broker service that uses the Service Registry.
 * 
 * @author Rem
 *
 */
public class LocalBrokerService implements BrokerService {
	@Override
	public List<Quotation> getQuotations(ClientInfo info) throws RemoteException {
		List<Quotation> quotations = new LinkedList<>();
		Registry registry = LocateRegistry.getRegistry();
		ClientInfo clientInfo=new ClientInfo();
		clientInfo.gender= ClientInfo.FEMALE;
		for(String name : registry.list()){
			if(name.startsWith("qs-")){
				try {
					QuotationService quotationService = (QuotationService)
							registry.lookup(name);
					quotations.add(quotationService.generateQuotation(clientInfo));
				}
				catch(NotBoundException e){
					System.out.println("xxx");
				}

			}
		};

//		for (String name : ServiceRegistry.list()) {
//			if (name.startsWith("qs-")) {
//				QuotationService service = (QuotationService) ServiceRegistry.lookup(name);
//				quotations.add(service.generateQuotation(info));
//			}
//		}

		return quotations;
	}
}
