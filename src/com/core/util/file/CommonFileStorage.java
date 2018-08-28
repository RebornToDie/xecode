package com.core.util.file;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URLDecoder;

/**
 *文件存储
 * @author reborntodie
 */
public class CommonFileStorage implements IFileStorage {
    private static Logger log = LogManager.getLogger(CommonFileStorage.class);

    @Override
    public long saveFile(String aboslutePath, InputStream input) throws IOException {
        long size = -1;
        File target = new File(aboslutePath);
        if(!target.getParentFile().exists()){
            target.getParentFile().mkdirs();
        }
        //move file to up_load_file
        OutputStream newOut = null;
        try{
            newOut = new BufferedOutputStream(new FileOutputStream(target));
            size = IOUtils.copyLarge(input , newOut);
        }catch (Exception e){
            log.error("move file error : " + e);
            throw new IOException(e);
        }finally{
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(newOut);
        }
        return size;
    }

    @Override
    public boolean createNewFile(String aboslutePath) throws IOException {
        File target = new File(aboslutePath);
        if(!target.getParentFile().exists()){
            target.getParentFile().mkdirs();
        }
        //文件存在返回false
        if(target.exists()){
            return false;
        }
        return target.createNewFile();
    }


    @Override
    public boolean deleteFile(String absolutePath) throws IOException{
        File target = new File(absolutePath);
        if(!target.exists()){
            log.warn("the path can not be found : " + absolutePath);
            return false;
        }
        if(!target.isFile()){
            log.warn("the path is not a file : " + absolutePath);
            return false;
        }
        return target.delete();
    }

    @Override
    public boolean exists(String absolutePath) throws IOException{
        File target =  new File(absolutePath);
        return target.exists();
    }

    @Override
    public InputStream getInputStream(String absolutePath) throws IOException{
        try {
            absolutePath = URLDecoder.decode(absolutePath , "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.warn("url encode error : " + e);
        }
        //get from local file system
        File target = new File(absolutePath);
        return new BufferedInputStream(new FileInputStream(target));
    }

    @Override
    public OutputStream getOutputStream(String absolutePath) throws IOException{
        try {
            absolutePath = URLDecoder.decode(absolutePath,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.warn("url encode error : " + e);
        }
        //get from local file system
        File target = new File(absolutePath);
        return new BufferedOutputStream(new FileOutputStream(target));
    }

    @Override
    public String[] list(String path) throws IOException{
        File target = new File(path);
        if(!target.exists()){
            log.warn("the path can not be found : " + path);
        }
        if(!target.isFile()){
            log.warn("the path is not a file : " + path);
        }
        return target.list();
    }

    @Override
    public boolean deleteDirectory(String path) {
        File target = new File(path);
        if(!target.exists()){
            log.warn("the path can not be found : " + path);
            return false;
        }
        if(!target.isDirectory()){
            log.warn("the file is not adirectory : " + path);
            return false;
        }
        return target.delete();
    }

    @Override
    public long lastModified(String path) throws IOException{
        File target = new File(path);
        if(target.exists()){
            return target.lastModified();
        }
        return -1;
    }

    @Override
    public long getSize(String path) throws IOException{
        File target = new File(path);
        return target.length();
    }

    @Override
    public void touch(String path) throws IOException{
        File target = new File(path);
        boolean success = target.setLastModified(System.currentTimeMillis());
        if(!success){
            throw new IOException("unable to set lastModifiedTime !");
        }
    }

    public static void main(String[] args) throws IOException{
        CommonFileStorage file = new CommonFileStorage();
        String path = "F://信息号.txt";
        boolean b = file.exists(path);
        String[] list =  file.list("F://");
        System.out.println(list);
        //InputStream is = file.getInputStream(path);
        //OutputStream os = file.getOutputStream(path);
        //boolean b = file.createNewFile(path);
        //boolean c = file.deleteFile(path);
    }
}
