package impl.simpleRPC.HelloWorld;

import java.rmi.RemoteException;

/**
 * Created by dyj on 7/25/18.
 *
 * the service definition implementation class, it implements the HelloIF interface
 */
public class HelloImpl implements HelloIF {
    @Override
    public String sayHello(String s) throws RemoteException {
        return "hi, " + s;
    }
}
