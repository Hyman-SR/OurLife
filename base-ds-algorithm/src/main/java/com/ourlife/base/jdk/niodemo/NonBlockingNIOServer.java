package com.ourlife.base.jdk.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author zhangchao
 * @createdOn 2020/5/21
 */
public class NonBlockingNIOServer {

    public static void main(String[] args) throws IOException {
        //获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //切换非阻塞模式
        ssChannel.configureBlocking(false);
        //绑定连接
        ssChannel.bind(new InetSocketAddress(9090));
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器，并指定"监听接收事件"
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //轮训获取选择器上已经"准备就绪"的事件
        while (selector.select() > 0) {
            //获取当前选择器中所有注册的"选择键(已就绪的监听事件)"
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                //如果事件准备就绪
                if (next.isAcceptable()) {
                    //获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();
                    //切换非阻塞模式
                    sChannel.configureBlocking(false);
                    //将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    //获取当前选择器上"读就绪"状态的通道
                    SocketChannel sChannel = (SocketChannel) next.channel();
                    //读取数据
                    ByteBuffer buff = ByteBuffer.allocate(2014);
                    int len = 0;
                    while ((len = sChannel.read(buff)) > 0) {
                        buff.flip();
                        System.out.println(new String(buff.array(), 0, len));
                        buff.clear();
                    }
                }
                //取消选择键
                iterator.remove();
            }
        }

    }
}
