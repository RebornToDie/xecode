package com.core;

public class TestMain {
    public static void main(String[] args) {
        Popper p = new Popper(3);
        System.out.print(p);
    }
}

class Popper{
    {
        int x = 10;
    }
    private int i;
    Popper (int i1 ){
        i = i1;
    }

}
