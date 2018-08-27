package com.core.util.file;

import java.io.*;

public class CommonFileStorage implements IFileStorage {
    @Override
    public long saveFile(String aboslutePath, InputStream input) {
        long size = -1;
        File target = new File(aboslutePath);
        if(!target.getParentFile().exists()){
            target.getParentFile().mkdirs();
        }
        //move file to up_load_file
        OutputStream newOut = null;
        try{
            newOut = new BufferedOutputStream(new FileOutputStream(target));
            size = I
        }catch (Exception e){

        }finally{

        }


    }

    @Override
    public boolean createNewFile(String aboslutePath) {
        return false;
    }

    @Override
    public boolean deleteFile(String absolutePath) {
        return false;
    }

    @Override
    public boolean exists(String absolutePath) {
        return false;
    }

    @Override
    public InputStream getInputStream(String absolutePath) {
        return null;
    }

    @Override
    public OutputStream getOutputStream(String absolutePath) {
        return null;
    }

    @Override
    public String[] list(String path) {
        return new String[0];
    }

    @Override
    public boolean deleteDirectory(String path) {
        return false;
    }

    @Override
    public String lastModified(String path) {
        return null;
    }

    @Override
    public long getSize(String path) {
        return 0;
    }

    @Override
    public void touch(String path) {

    }
}
