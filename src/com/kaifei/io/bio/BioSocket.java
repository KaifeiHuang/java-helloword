package com.kaifei.io.bio;

import com.kaifei.util.logger.LoggerUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioSocket {

    /**
     * serverSocket::accept() and inputStream::read() are blocking
     * <p>
     * 执行该测试用例， 在windows，上使用telnet连接模拟测试
     */
    @Test
    public void bioSocketV1() {
        try {
            LoggerUtil.info("启动服务， 服务端socket");
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));

            while (true) {
                // 监听到客户端，获取到一个客户端socket

                Socket clientSocket = serverSocket.accept(); // 阻塞
                LoggerUtil.info("监听到客户段连接， 创建客户端socket： " + clientSocket);

                InputStream inputStream = clientSocket.getInputStream();

                LoggerUtil.info("监听到客户段连接， 等待读入数据");
                byte[] bytes = new byte[1024];
                int data = inputStream.read(bytes); // 阻塞
                if (data != -1) {
                    String inputStr = new String(bytes, 0, data);
                    LoggerUtil.info("client input is ： " + inputStr);
                }
                LoggerUtil.info("close client when read end.");
            }
        } catch (IOException e) {
            LoggerUtil.error("socket error");
        }
    }

    /**
     * 循环获取客户端输入
     */
    @Test
    public void bioSocketV2() {
        try {
            LoggerUtil.info("启动服务， 服务端socket");
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));

            while (true) {
                // 监听到客户端，获取到一个客户端socket

                Socket clientSocket = serverSocket.accept(); // 阻塞
                LoggerUtil.info("监听到客户段连接， 创建客户端socket： " + clientSocket);

                InputStream inputStream = clientSocket.getInputStream();

                LoggerUtil.info("监听到客户段连接， 等待读入数据");
                byte[] bytes = new byte[1024];

                while(true){
                    int data = inputStream.read(bytes); // 阻塞
                    if (data != -1) {
                        String inputStr = new String(bytes, 0, data);
                        LoggerUtil.info("client input is ： " + inputStr);
                    }
                    LoggerUtil.info("close client when read end.");
                }
            }
        } catch (IOException e) {
            LoggerUtil.error("socket error");
        }
    }

    /**
     * 使用现线程获取多个客户端连接，并循环获取客户端输入
     *
     * 缺点： 并发量大的情况下，就需要创建大量的线程，会是OOM error，
     * 因此： BIO适合并发量低的， 不适合并发量大的
     *
     * 性能缺陷根源：accept() 和 read() 是阻塞导致
     */
    @Test
    public void bioSocketV3() {
        try {
            LoggerUtil.info("启动服务， 服务端socket");
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));

            while (true) {
                // 监听到客户端，获取到一个客户端socket
                Socket clientSocket = serverSocket.accept(); // 阻塞
                LoggerUtil.info("监听到客户段连接， 创建客户端socket： " + clientSocket);

                new Thread(()->{
                    try {
                        InputStream inputStream = clientSocket.getInputStream();

                        LoggerUtil.info("监听到客户段连接， 等待读入数据");
                        byte[] bytes = new byte[1024];

                        while(true){
                            int data = 0; // 阻塞
                            data = inputStream.read(bytes);
                            if (data != -1) {
                                String inputStr = new String(bytes, 0, data);
                                LoggerUtil.info("client input is ： " + inputStr);
                            }
                            LoggerUtil.info("close client when read end.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }).start();

            }
        } catch (IOException e) {
            LoggerUtil.error("socket error");
        }
    }

    /**
     * v4 和 v3 是一样的， 只不过v4使用线程池而已
     */
    @Test
    public void bioSocketV4() {
        try {
            LoggerUtil.info("启动服务， 服务端socket");
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(8080));

            ExecutorService executorService = Executors.newCachedThreadPool();
            while (true) {
                // 监听到客户端，获取到一个客户端socket
                Socket clientSocket = serverSocket.accept(); // 阻塞
                LoggerUtil.info("监听到客户段连接， 创建客户端socket： " + clientSocket);

                executorService.submit(()->{
                    try {
                        InputStream inputStream = clientSocket.getInputStream();

                        LoggerUtil.info("监听到客户段连接， 等待读入数据");
                        byte[] bytes = new byte[1024];

                        while(true){
                            int data = 0; // 阻塞
                            data = inputStream.read(bytes);
                            if (data != -1) {
                                String inputStr = new String(bytes, 0, data);
                                LoggerUtil.info("client input is ： " + inputStr);
                            }
                            LoggerUtil.info("close client when read end.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            LoggerUtil.error("socket error");
        }
    }
}
