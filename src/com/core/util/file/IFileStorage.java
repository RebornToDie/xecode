package com.core.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件存储接口
 * @author reborntodie
 */
public interface IFileStorage {
    /**
     *保存文件，保存后会close InputStream。若文件不存在，会创建新文件;若文件存在则先删除，再创建。
     * @param aboslutePath
     * @param input
     * @return
     * @throws IOException
     */
    long saveFile(String aboslutePath , InputStream input) throws IOException;

    /**
     * 创建空白新文件，创建成功返回true，否则返回false
     * @param aboslutePath
     * @return
     */
    boolean createNewFile(String aboslutePath) throws IOException;

    /**
     * 删除文件
     * @param absolutePath
     * @return
     * @throws IOException
     */
    boolean deleteFile(String absolutePath) throws IOException;

    /**
     * 判断文件是否存在
     * @param absolutePath
     * @return
     */
    boolean exists(String absolutePath) throws IOException;

    /**
     * 获取文件输入流
     * @param absolutePath
     * @return
     * @throws IOException
     */
    InputStream getInputStream(String absolutePath) throws IOException;

    /**
     * 获取文件输出流
     * @param absolutePath
     * @return
     */
    OutputStream getOutputStream(String absolutePath) throws IOException;

    /**
     * 列出目录下所有文件
     * @param path
     * @return
     */
    String[] list(String path) throws IOException;

    /**
     * 删除文件夹
     * @param path
     * @return
     * @throws IOException
     */
    boolean deleteDirectory(String path) throws IOException;

    /**
     *获取文件修改时间
     * @param path
     * @return
     */
    long lastModified(String path) throws IOException;

    /**
     * 获取文件大小
     * @param path
     * @return
     * @throws IOException
     */
    long getSize(String path) throws IOException;

    /**
     * 设置文件最后修改时间为当前时间
     * @param path
     * @throws IOException
     */
    void touch(String path) throws IOException;
}
