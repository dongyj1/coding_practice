package impl.simpleRPC.RPCexample;

import sun.rmi.rmic.RemoteClass;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by dyj on 7/25/18.
 */
public class RPCServerImpl implements RPCServer {

    private static final ExecutorService taskPool = Executors.newFixedThreadPool(50);
    /**
     * Lib for service interfaces. Key: name of interface, value: interface implementation
     */
    private static final ConcurrentHashMap<String, Object> serviceTargets = new ConcurrentHashMap<String, Object>();

    private static AtomicBoolean run = new AtomicBoolean(false);

    @Override
    public void registerService(Object service) {
        // get services implemented interface names
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if (interfaces == null) {
            throw new IllegalArgumentException("Service Object must implements interfaces");
        }
        Class<?> interfaces_ = interfaces[0];
        String interfaceName = interfaces_.getName();
        System.out.println(interfaceName + " " + service.getClass().toString());
        serviceTargets.put(interfaceName, service);
    }

    @Override
    public void startServer(int port) {
        Runnable lifeThread = new Runnable() {
            @Override
            public void run() {
                ServerSocket lifeSocket = null;
                Socket client = null;
                ServiceTask serviceTask = null;
                try {
                    lifeSocket = new ServerSocket(port);
                    run.set(true);
                    while (run.get()) {
                        client = lifeSocket.accept();
                        serviceTask = new ServiceTask(client);
                        serviceTask.accept();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        taskPool.execute(lifeThread);
        System.out.println("Service started...");
    }

    @Override
    public void stopServer() {
        taskPool.shutdown();
        run.set(false);
    }

    public static final class ServiceTask implements Runnable {

        private Socket client;

        public ServiceTask(Socket client) {
            this.client = client;
        }

        public void accept() {
            taskPool.execute(this);
        }

        @Override
        public void run() {
            InputStream is = null;
            ObjectInput oi = null;
            OutputStream os = null;
            ObjectOutput oo = null;
            try {
                is = client.getInputStream();
                os = client.getOutputStream();
                oi = new ObjectInputStream(is);
                String serviceName = oi.readUTF();
                String methodName = oi.readUTF();
                Class<?>[] paramTypes = (Class[]) oi.readObject();
                Object[] arguments = (Object[]) oi.readObject();
                System.out.println("serviceName:" + serviceName + " methodName:" + methodName + " arguments:" + arguments);

                Object targetService = serviceTargets.get(serviceName);
                if (targetService == null) {
                    throw new ClassNotFoundException(serviceName + " Not Found.");
                }

                Method targetMethod = targetService.getClass().getMethod(methodName, paramTypes);
                Object result = targetMethod.invoke(targetMethod, arguments);

                oo = new ObjectOutputStream(os);
                oo.writeObject(result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (oo != null) oo.close();
                    if (os != null) os.close();
                    if (is != null) is.close();
                    if (oi != null) oi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
