import java.net.URI;
import java.net.URISyntaxException;
import microsoft.exchange.webservices.data.*;

/**
 * <pre>
 * <B>Copyright:</B>   Izik Golan
 * <B>Owner:</B>       <a href="mailto:golan2@hotmail.com">Izik Golan</a>
 * <B>Creation:</B>    15/08/14 11:09
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * Download WSDL:
 *  https://sync.austin.hp.com/ews/Services.wsdl
 *  https://sync.austin.hp.com/ews/Exchange.asmx
 *  https://casarray1.austin.hp.com/EWS/Exchange.asmx
 *
 * </pre>
 */
public class Connect2Exchange {


  public static void main(String[] args) {

  }

  public static void testMethod() {
    ExchangeService service = new ExchangeService(
      ExchangeVersion.Exchange2007_SP1);
    ExchangeCredentials credentials = new WebCredentials("username",
                                                         "password");

    service.setCredentials(credentials);

    try {
      service.setUrl(new URI("https://domain/EWS/Exchange.asmx"));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    EmailMessage msg;
    try {
      msg = new EmailMessage(service);
      msg.setSubject("hello world");
      msg.setBody(MessageBody
                    .getMessageBodyFromText("Sent using the EWS API"));
      msg.getToRecipients().add("test@test.com");
      msg.send();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
