package impl.simpleRPC.RPCexample;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created by dyj on 7/25/18.
 *
 * Client for RPC
 */
public class RPCClient {

    /**
     *
     * @param host server host
     * @param port server port
     * @param serviceInterface service interface
     * @param <T> service type
     * @return service
     */
    @SuppressWarnings("unchecked")
    public static <T> T findService(final String host , final int port ,final Class<T> serviceInterface){
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(),
                new Class[]{serviceInterface},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = null;
                        InputStream is = null;
                        OutputStream os = null;
                        ObjectInput oi = null;
                        ObjectOutput oo = null;
                        try {
                            socket = new Socket(host, port);
                            os = socket.getOutputStream();
                            oo = new ObjectOutputStream(os);
                            oo.writeUTF(serviceInterface.getName());
                            oo.writeUTF(method.getName());
                            oo.writeObject(method.getParameterTypes());
                            oo.writeObject(args);

                            is = socket.getInputStream();
                            oi = new ObjectInputStream(is);
                            return oi.readObject();
                        } catch (Exception e) {
                            System.out.println("Error invoking service...");
                            return null;
                        } finally {
                            if (is != null) {
                                is.close();
                            }
                            if (os != null) {
                                is.close();
                            }
                            if (oi != null) {
                                is.close();
                            }
                            if (oo != null) {
                                is.close();
                            }
                            if (socket != null) {
                                is.close();
                            }
                        }
                    }

                });
        }
    }

