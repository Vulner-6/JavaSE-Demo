package com.crackpwd;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CrackPwd implements Runnable{
    String password="";
    public CrackPwd(String pwd){
        this.password=pwd;
    }

    public boolean brute(String pwd){
        String[] key={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X"
                ,"Y","Z"};
        boolean returnValue=false;
        List<String> result=new ArrayList<String>();
        //三重循环，暴力生成三位数大写字母密码，进行碰撞
        OUTER:for(int i=0;i<key.length;i++){
            for(int j=0;j<key.length;j++){
                for(int k=0;k<key.length;k++){
                    String temp=key[i]+key[j]+key[k];
                    result.add(temp);
                    if(temp.equals(pwd)){
                        System.out.format("密码破解成功，密码是：%s %n",temp);
                        returnValue=true;
                        break OUTER;
                    }
                }
            }
        }
        //找到密码后，将碰撞过的密码写入文件
        File attempPwd=new File("E:\\Programming\\Projects\\JavaSE-Demo\\attempPwd.txt");
        try{
            //创建文件字符流
            FileWriter fw=new FileWriter(attempPwd);
            //缓存流必须建立在一个存在的流的基础上
            PrintWriter pw=new PrintWriter(fw);
            pw.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnValue;
    }

    public void run(){
        brute(this.password);
    }
}
