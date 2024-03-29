package internationalization.translate.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface GreetingService extends RemoteService {
  String greetServer(String name) throws IllegalArgumentException;
  
  boolean authenticate(String name, String passwd);
}
