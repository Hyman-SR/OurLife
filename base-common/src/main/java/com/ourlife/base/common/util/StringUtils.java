package com.ourlife.base.common.util;

import com.ourlife.base.common.exception.BaseException;
import com.ourlife.base.common.vo.ResultCode;
import com.sun.tools.javac.util.Convert;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * 字符串的工具类
 *
 * @author zhangchao
 * @createdOn 2020/5/8
 */
public class StringUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtils.class);

    private static final String SOURCE_KEYWORD = "http";

    /**
     * 图片转换成字符
     *
     * @param imagePath
     * @param charKey
     * @return
     */
    public static String image2Char(String imagePath, String charKey) {
        charKey = charKey + "=+;:,.";
        StringBuffer sb = new StringBuffer();
        //读取图片
        BufferedImage image = getBufferedImage(imagePath);
        //先竖向遍历，再横向遍历，即一行一行的找，后面也会一行一行的打印
        for (int y = 0; y < image.getHeight(); y += 2) {
            for (int x = 0; x < image.getWidth(); x++) {
                final int pixel = image.getRGB(x, y);
                final int r = (pixel & 0xff0000) >> 16, g = (pixel & 0xff00) >> 8, b = pixel & 0xff;
                final float gray = 0.299f * r + 0.578f * g + 0.114f * b;
                final int index = Math.round(gray * (charKey.length() + 1) / 255);
                String s = index >= charKey.length() ? " " : String.valueOf(charKey.charAt(index));
                sb.append(s);
            }
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    /**
     * 获取图片流
     *
     * @param imagePath
     * @return
     */
    private static BufferedImage getBufferedImage(String imagePath) {
        //读取图片
        BufferedImage image;
        try {
            if (imagePath.contains(SOURCE_KEYWORD)) {
                //读取网络资源
                image = getRemoteBufferedImage(imagePath);
            } else {
                //读取本地资源
                image = ImageIO.read(new File(imagePath));
            }
        } catch (MalformedURLException e) {
            throw new BaseException(ResultCode.VALIDATE_FAILED, "this imagePath is not valid");
        } catch (IOException e) {
            throw new BaseException(ResultCode.VALIDATE_FAILED, "read image failed");
        }
        return image;
    }

    /**
     * 获取远程图片流
     *
     * @param imagePath
     * @return
     */
    private static BufferedImage getRemoteBufferedImage(String imagePath) throws MalformedURLException, IOException {
        URL url;
        InputStream is = null;
        BufferedImage image;
        try {
            url = new URL(imagePath);
            is = url.openStream();
            image = ImageIO.read(is);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                LOGGER.error("close stream occur error");
                return null;
            }
        }
        return image;
    }

}
