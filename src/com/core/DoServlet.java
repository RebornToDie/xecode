package com.core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通用响应Servlet
 * @author reborntodie
 */
public class DoServlet extends HttpServlet {
    private static Logger log = LogManager.getLogger(DoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String[] param = uri.substring(uri.lastIndexOf("/")+1).split("\\.");
        //通过实例名实现方法的调用
        String serviceName = param[0];
        if(serviceName != null){


        }


        super.doGet(req, res);
    }

    private Object getBean (String className){
        Class cls = null;
        try {
            cls = Class.forName(className);//对应Spring ->bean -->class
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            //throw new Exception("类错误！");
        }

        Constructor[] cons = null;//得到所有构造器
        try {
            cons = cls.getConstructors();
        } catch (Exception e) {
            e.printStackTrace();
            //throw new Exception("构造器错误！");
        }
        if (cons == null || cons.length < 1) {
            //throw new Exception("没有默认构造方法！");
        }
        //如果上面没错，就有构造方法

        Constructor defCon = cons[0];//得到默认构造器,第0个是默认构造器，无参构造方法
        Object obj = null;//实例化，得到一个对象 //Spring - bean -id
        try {
            obj = defCon.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;

    }
}
