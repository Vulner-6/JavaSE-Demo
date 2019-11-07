package com.crackpwd;

import java.util.ArrayList;
import java.util.List;

public class CrackPwd implements Runnable{
    char A=65;   //65-91 A-Z
    char a=97;   //97-123 a-z
    List tempList=new ArrayList();



    /**
     * 生成大小写字母列表的二维数组
     * @return
     */
    public List keyList(){
        List smallList=new ArrayList();
        List bigList=new ArrayList();
        List result=new ArrayList();
        for(int i=65;i<92;i++){
            smallList.add(i);
            bigList.add(i+32);
        }
        result.add(smallList);
        result.add(bigList);
        return result;
    }

    public void run(){

    }
}
