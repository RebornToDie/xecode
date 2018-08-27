package com.core.util.file;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件存储接口
 */
public interface IFileStorage {
    long saveFile(String aboslutePath , InputStream input);
    boolean createNewFile(String aboslutePath);
    boolean deleteFile(String absolutePath);
    boolean exists(String absolutePath);
    InputStream getInputStream(String absolutePath);
    OutputStream getOutputStream(String absolutePath);
    String[] list(String path);
    boolean deleteDirectory(String path);
    String lastModified(String path);
    long getSize(String path);
    void touch(String path);
}
