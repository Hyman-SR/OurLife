package com.ourlife.base.jdk.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author zhangchao
 * @createdOn 2020/5/21
 */
public class NonBlockingNIOClient {


    /**
     * Buffer详解：https://www.iteye.com/blog/xiachaofeng-1416634
     *
     * capacity:表示这个buffer最多能放多少数据
     * limit:在buffer上进行的读写操作不能越过这个下标；当写数据时，limit一般和capacity相等，当读数据时，limit代表buffer中有效数据的长度
     * position:读写操作的当前下标，当使用buffer的相对位置进行读写时，读写会从这个下标开始。并在操作完成后，buffer会更新下标的值
     *
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9090));
        Socket socket = sChannel.socket();
        //切换非阻塞模式
        sChannel.configureBlocking(false);
        //分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(2014);
        Scanner sc = new Scanner(System.in);
        String str = null;
        while (!"exit".equalsIgnoreCase(str)) {
            System.out.println("请输入...");
            str = sc.nextLine();
            //发送数据给服务端
            buf.put(str.getBytes());
            //把limit设置为当前position，把position设置为0，一般在读数据前调用，目的只读取写入的有效数据，不读未写的无效空间
            buf.flip();
            //将数据写入通道
            sChannel.write(buf);
            //清空缓冲区
            buf.clear();
        }
        //关闭通道
        sChannel.close();

    }
}
