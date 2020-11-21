package com.kaifei.io.nio;

import com.kaifei.util.logger.LoggerUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    /**
     * 使用管道来获取客户端连接和输入
     * <p>
     * 优点： 只使用一个线程， 通过循环来处理客户端连接和输入
     * <p>
     * 缺点： 为了获取用户输入，每次都需要变量客户端输入的数据
     * 如10000个客户端，只有5个人输入
     */
    @Test
    public void nioServerV1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ArrayList<SocketChannel> socketChannels = new ArrayList<>();

        try {
            // 创建服务， 绑定端口
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));

            // 设置非阻塞channel
            serverSocketChannel.configureBlocking(false);

            while (true) {
                SocketChannel clientSocketChannel = serverSocketChannel.accept();


                if (clientSocketChannel == null) {
                    // 需要处理已有的连接
                    for (SocketChannel socketChannel : socketChannels) {
                        // 获取到客户端的连接
                        int data = socketChannel.read(byteBuffer);
                        if (data > 0) {
                            LoggerUtil.info(new String(byteBuffer.array(), 0, data));
                        }
                        byteBuffer.clear();
                    }

                } else {
                    LoggerUtil.info("监听到客户端连接： " + clientSocketChannel);
                    clientSocketChannel.configureBlocking(false);
                    socketChannels.add(clientSocketChannel);
                    // 获取到客户端的连接
                    int data = clientSocketChannel.read(byteBuffer);
                    if (data > 0) {
                        LoggerUtil.info(new String(byteBuffer.array(), 0, data));
                    }
                    byteBuffer.clear();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用selector来实现
     */
    @Test
    public void nioServerV2() {


        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));

            serverSocketChannel.configureBlocking(false);

            // 获取selector
            Selector selector = Selector.open();

            //绑定selector，注册监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            LoggerUtil.info("绑定selector，注册监听连接事件");

            while(true){
                // 获取就绪的连接
                selector.select(); // 至少有一个就绪channel，才能进来. 能够监视多个客户端
                LoggerUtil.info("获取就绪的channel连接");
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    handler(selectionKey);
                }

            }

        } catch (IOException e) {
            LoggerUtil.error(e.getMessage());
        }
    }

    private void handler(SelectionKey selectionKey) throws IOException {
        // 拿到连接事件
        if(selectionKey.isAcceptable()){
            LoggerUtil.info("从selector里获取到新的连接");
            SocketChannel clientSocket = ((ServerSocketChannel) selectionKey.channel()).accept();// SocketChannel clientSocket = serverSocketChannel.accept();

            // 设置为非阻塞
            clientSocket.configureBlocking(false);

            // 注册事件
            clientSocket.register(selectionKey.selector(), SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()){
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            LoggerUtil.info("从selector里监听到读事件");
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            int data = socketChannel.read(byteBuffer);
            if (data > 0) {
                byteBuffer.flip();
                String content = new String(byteBuffer.array(), 0, data);
                LoggerUtil.info("读到数据： " + content);
                byteBuffer.clear();
            }
        }
    }
}
