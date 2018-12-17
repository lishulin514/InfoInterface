package com.telezone.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;


public class SocketClient implements Runnable{

    private final static Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private Socket socket = null;
    private byte[] sendMsg = {};
    private String ip;
    private Integer port;


    public SocketClient(String ip, Integer port, byte[] sendMsg){

        this.sendMsg = sendMsg;
        this.ip = ip;
        this.port = port;
    }


    @Override
    public void run() {
        try{
            socket = createSocket();

            initSocket(socket);
            //连接本地，端口20000，超时时间3000ms
//            socket.connect(new InetSocketAddress("192.168.195.138", 34567),3000);
            socket.connect(new InetSocketAddress(ip, port),3000);
            send();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                socket.close();
                socket = null;
            }catch (Exception e){}
        }
    }


    private void send() throws IOException{
        // 得到Socket输出流，并转换为打印流
        OutputStream outputStream = socket.getOutputStream();

        byte[] buffer = new byte[256];

        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        byteBuffer.put(sendMsg);

        logger.info(String.format(" ip: %s ；port：%d ；call-bytes: %s ",ip, port, new String(sendMsg)));

        // 发送到服务器
        outputStream.write(buffer, 0, byteBuffer.position()+1);
        //资源释放
        outputStream.close();
        logger.info("send success");
    }

    private static Socket createSocket() throws IOException {

/*            // 无代理模式， 等效于空构造函数
        Socket socket = new Socket(Proxy.NO_PROXY);

        // 新建一份具有HTTP代理的套接字， 传输数据将通过www.baidu.com:8080端口转发 实际并无此功能
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Inet4Address.getByName("www.baidu.com"), 8800));
        socket = new Socket(proxy);

        // 新建一个套接字， 并且直接链接到本地20000的服务器上
        socket = new Socket("localhost", PORT);

        // 新建一个套接字， 并且直接链接到本地20000的服务器上 同上
        socket = new Socket(Inet4Address.getLocalHost(), PORT);

        // 新建一个套接字， 并且直接链接到本地20000的服务器上， 并且绑定到本地20001端口上
        socket = new Socket("localhost", PORT, Inet4Address.getLocalHost(), LOCAL_PORT);
        socket = new Socket(Inet4Address.getLocalHost(), PORT, Inet4Address.getLocalHost(), LOCAL_PORT);*/

        Socket socket = new Socket();
        //超时时间
//        socket.setSoTimeout(3000);
        return socket;
    }

    private static void initSocket(Socket socket) throws SocketException {

        // 设置读取超时时间为2秒
        socket.setSoTimeout(2000);

        // 是否服用未完全关闭的Socket地址， 对于指定bind操作后的套接字有效
        socket.setReuseAddress(true);

        // 是否开启Nagle算法
        socket.setTcpNoDelay(false);

        // 是否需要在长时无数据响应时发送确认数据（类似心跳包）， 时间大约为2小时
        socket.setKeepAlive(true);

        // 对于close关闭操作行为进行怎样的处理； 默认为false,0
        // false/0:默认情况， 关闭时立即返回，底层系统接管输出流，将缓冲区内的数据发送完成
        // true/0: 关闭时立即返回，缓冲区数据抛弃，直接发送RST结束命令到对方， 并无经过2MSL等待
        // true/200:关闭时最长阻塞200毫秒，随后按第二情况处理
        socket.setSoLinger(false, 20);

        // 是否让紧急数据内敛, 默认false; 紧急数据通过 socket.sendUrgentData(1);发送
        socket.setOOBInline(false);
        // 发送紧急数据 只发送int数据的低8位 容易和业务数据混淆
//        socket.sendUrgentData(1);

        // 设置接受发送缓冲器大小
//        socket.setReceiveBufferSize(64 * 1024 * 1024);
        socket.setSendBufferSize(64 * 1024 * 1024);


        // *** 设置性能参数：短连接， 延迟， 带宽的相对重要性  (是权重比例) 根据三者间连接关系设置比重 比较重要（怎么搭配选型）
        socket.setPerformancePreferences(1,2,2);
    }
}
