package com.xxl.job.executor.utils;


import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Roman.Zhang
 * @date 2020/5/8
 * @description:
 */

public class FindFileUtils {
    public static String getJson(String url){
        StringBuilder stringBuilder = new StringBuilder();
        try(
                FileChannel inChannel = FileChannel.open(Paths.get(url), StandardOpenOption.READ);
                ){
            ByteBuffer allocate = ByteBuffer.allocate(1024);


            while(inChannel.read(allocate)!=-1){
                allocate.flip();
                stringBuilder.append(new String(allocate.array(),0,allocate.limit()));
                //清空缓冲区
                allocate.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
