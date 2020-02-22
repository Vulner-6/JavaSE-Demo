package com.crackpwd;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String args[])
    {
        GeneratePwd generatePwd = new GeneratePwd();
        String pwd = generatePwd.generatePwd();
        //开启破解线程
        CrackPwd crackPwd = new CrackPwd(pwd);
        Thread threadCrack = new Thread(crackPwd);
        threadCrack.start();

        //开启守护线程
        MyDaemon myDaemon = new MyDaemon();
        Thread thread = new Thread(myDaemon);
        thread.setDaemon(true);
        thread.start();
    }

}
