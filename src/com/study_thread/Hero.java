package com.study_thread;

public class Hero
{
    public String name;
    public float hp;
    public int damage;

    public Hero(String name, float hp, int damage)
    {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    /**
     * 当前英雄攻击传入的英雄1下
     *
     * @param h
     */
    public void attackHero(Hero h)
    {
        //表示攻击需要时间，间隔1秒
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        h.hp -= this.damage;
        System.out.format("%s 正在攻击 %s，%s的血量变成了%.0f %n", this.name, h.name, h.name, h.hp);    //不影响线程安全
    }

    public void eatHp()
    {
        this.hp += 30;
        System.out.format("%s 吃了一口血，现在血量是%.0f %n", this.name, this.hp);
        //吃血有冷却时间
        try
        {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void adugen()
    {
        for (int i = 1; i <= 3; i++)
        {
            System.out.format("波动拳，第%d下", i);
        }
    }

    /**
     * 判断当前英雄是否死亡
     *
     * @return
     */
    public boolean isDead()
    {
        return 0 >= hp ? true : false;
    }
}
