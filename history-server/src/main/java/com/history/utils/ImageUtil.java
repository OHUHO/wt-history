package com.history.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

/**
 * Base64 装 图片
 */
public class ImageUtil {

    public static void  generateImage(String file,String path) {

        try {
            Base64.Decoder decoder = Base64.getDecoder();

            // 去掉base64前缀 data:image/jpeg;base64,
            file = file.substring(file.indexOf(",", 1) + 1, file.length());
            byte[] b = decoder.decode(file);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 保存图片
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
