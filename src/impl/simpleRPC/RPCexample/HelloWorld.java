package impl.simpleRPC.RPCexample;

/**
 * Created by dyj on 7/25/18.
 */
public class HelloWorld implements IHelloWorld {
    @Override
    public String sayHello(String name) {
        return "hi, " + name;
    }
}
