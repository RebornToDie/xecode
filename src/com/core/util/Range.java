package com.core.util;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创建一个int类型的数组
 * @author reborntodie
 */
public class Range {
    /**
     * 从0产生值直到范围上限
     * @param a
     * @return
     */
    public static int[] range(int a){
        int[] array = new int[a];
        for(int i = 0 ; i < a ; i++)
            array[i] = i;
        return array;
    }

    /**
     * 重载rnage增加范围
     * @param a
     * @param b
     * @return
     */
    public static int[] range(int a , int b){
        int[] array = new int[b-a];
        for(int i = 0 , j =  a ; j < b ; i++ , j++)
            array[i] = j;
        return array;
    }

    /**
     * 重载增加范围和递增值
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static int[] range(int a , int b , int c){
        int[] array = new int[(b-a)/c];
        for(int i = 0 , j = a ; j  < b ; i++ , j+=c)
            array[i] = j;
        return array;
    }

    public static void main(String[] args) {
        for(int i : range(10))
            System.out.print(i + " ");
        System.out.print("\n");
        for(int i : range(5 ,10))
            System.out.print(i + " ");
        System.out.print("\n");
        for(int i : range(5 ,20 ,3))
            System.out.print(i + " ");
    }

}
