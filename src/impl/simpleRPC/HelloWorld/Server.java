package impl.simpleRPC.HelloWorld;

import java.io.IOException;

/**
 * Created by dyj on 7/25/18.
 *
 * Center
 */
public interface Server {
    public void stop();

    public void start() throws IOException;

    public void register(Class serviceInterface, Class impl);

    public boolean isRunning();

    public int getPort();
}
