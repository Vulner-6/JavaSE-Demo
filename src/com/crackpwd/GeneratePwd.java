package com.crackpwd;

import java.util.ArrayList;
import java.util.List;

public class GeneratePwd
{
    public String generatePwd()
    {
        //生成大写字母char列表
        List smallList = new ArrayList();
        for (int i = 65; i < 92; i++)
        {
            smallList.add(i);
        }
        String result = "";
        Character temp;
        for (int i = 0; i < 3; i++)
        {
            //随机读取三个char字符，作为密码
            int randomNum = (int) ((Math.random() * 100) / (100 / 25));
            temp = (char) (int) smallList.get(randomNum);
            result += temp.toString();
        }
        return result;
    }
}
