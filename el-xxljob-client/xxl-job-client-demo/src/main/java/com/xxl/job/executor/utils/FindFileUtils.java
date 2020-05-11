package com.xxl.job.executor.utils;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * @author Roman.Zhang
 * @date 2020/5/8
 * @description:
 */
public class FindFileUtils {
    public static String getJson(String url) throws Exception {
        //判断路径是否存在
        boolean exists = Files.exists(Paths.get(url.toString()));
        if(!exists){
            throw new Exception("路径不存在，请创建执行路径");
        }
        //根据路径下，获取路径下的文件
        List<String> strings = Arrays.asList(new File(url).list());
        if(strings!=null && !strings.isEmpty()){
            //TODO 文件后缀暂不校验
            url =url+strings.get(0);
        }else{
            throw new Exception("文件不存在，请创建文件");
        }
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

    /*public static String getJsonJar(String path) throws IOException {
        InputStream io = (InputStream) ClassLoader.getSystemResources(path);


    }*/
}
