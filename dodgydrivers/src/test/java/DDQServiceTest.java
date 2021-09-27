import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DDQServiceTest {
    private static Registry registry;
    @BeforeClass
    public static void setup() {
        QuotationService ddqService = new DDQService();
        try {
            registry = LocateRegistry.createRegistry(1099);
            QuotationService quotationService = (QuotationService)
                    UnicastRemoteObject.exportObject(ddqService,0);
            registry.bind(Constants.DODGY_DRIVERS_SERVICE, quotationService);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
    @Test
    public void connectionTest() throws Exception {
        QuotationService service = (QuotationService)
                registry.lookup(Constants.DODGY_DRIVERS_SERVICE);
        assertNotNull(service);
    }

    @Test
    public void testGenerateQuotation(){
        DDQService ddqService = new DDQService() ;
        ClientInfo clientInfo = mock(ClientInfo.class);
        clientInfo.gender = ClientInfo.MALE;
        Quotation q = ddqService.generateQuotation(clientInfo);
        Assert.assertNotNull(q);
    }
}