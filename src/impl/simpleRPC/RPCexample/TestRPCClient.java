package impl.simpleRPC.RPCexample;

/**
 * Created by dyj on 7/25/18.
 */
public class TestRPCClient {
    public static void main(String[] args) {
        IHelloWorld helloWorld =
                RPCClient.findService("127.0.0.1" , 51234 , IHelloWorld.class) ;
        String  result = helloWorld.sayHello("tantexian, My blog address is: http://my.oschina.net/tantexian/");
        System.out.println(result);
    }
}
