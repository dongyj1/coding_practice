package impl.simpleRPC.HelloWorld;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by dyj on 7/25/18.
 *
 * the service definition interface
 */
public interface HelloIF extends Remote {
    public String sayHello(String s) throws RemoteException;
}
