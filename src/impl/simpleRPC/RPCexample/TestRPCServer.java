package impl.simpleRPC.RPCexample;

/**
 * Created by dyj on 7/25/18.
 */
public class TestRPCServer {

    public static void main(String[] args) {

        RPCServer server = new RPCServerImpl();
        server.registerService(new HelloWorld());
        server.startServer(51234);

    }
}
