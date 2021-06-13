package com.lk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jboss.netty.handler.codec.serialization.ObjectEncoderOutputStream;

import com.alibaba.dubbo.rpc.cluster.support.AvailableCluster;
import com.alibaba.dubbo.rpc.cluster.support.AvailableClusterInvoker;

/**
 * 服务端
 * @author mac1094
 *
 */

public class Server {
	public static final String ZOOKEEPER_NODE_KAIKEBA="/solomon";

	private static String address;

	private static int port;
	
	private Map<String,Class> serverMap = new HashMap<String,Class>();
	private ServerSocket serversocket = null;
	// 线程池
	private ExecutorService executorservice = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	// 本机服务的名字
	private static String serverName;
	public void start(String address,int port,String serverName) throws IOException {
		serversocket = new ServerSocket();
		serversocket.setReuseAddress(true);
	    serversocket.setReceiveBufferSize(1024*10);
	    serversocket.setSoTimeout(2000*20);
	    serversocket.bind(new InetSocketAddress(address,port));
	        Socket accpet = null;
	        System.out.println("服务己开启");
	        while((accpet = serversocket.accept())!= null){
	        	Socket finalAccept = accpet;
	        	//开启多线程
	        	executorservice.execute(() ->{
	        		
						try {
							// 输入流
						    ObjectInputStream ois = new ObjectInputStream(finalAccept.getInputStream());
						    // 输出流
							ObjectOutputStream oos = new ObjectOutputStream(finalAccept.getOutputStream());
							// 类
						     String className=	ois.readUTF();
						     // 方法
						     String MethodName = ois.readUTF();
						    // 方法参数类型数组
						     Class[] params = (Class[]) ois.readObject();
						     // 方法参数值
						     Object[] values = (Object[]) ois.readObject();
						     // 实现类对象
						     Class im = serverMap.get(className);
						    
						     Object ob = im.newInstance();
						     // 实现方法类的对象
						     Method method = im.getMethod(MethodName,params);
						     // 获得结果
						     Object me  =  method.invoke(ob, values);
						     // 输出结果
						oos.writeObject(me);
						// 关闭流
						oos.close();
						ois.close();
						     
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						} catch (InstantiationException e) {
							
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							
							e.printStackTrace();
						} catch (SecurityException e) {
							
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
	        	});
	        	
	        }
	}
	// 关闭
      public void close() throws IOException {
    	  serversocket.close();
	}
      // 注册服务方法
     public void register(String className,Class im) {
	serverMap.put(className, im);
	
}
public static void main(String[] args) throws IOException {
	Server s = new Server();
	      // 开始服务
	      s.start("localhost",8989,"kaikebal");
	      // 注册服务
	      s.register(IOrder.class.getName(),IOrder.class);
	
}
}
