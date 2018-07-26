package impl.simpleRPC.RPCexample;

/**
 * Created by dyj on 7/25/18.
 *
 * Define the services in RPC server
 */
public interface RPCServer {

    void registerService(Object service);

    void startServer(final int port);

    void stopServer();
}
