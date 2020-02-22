package com.study_thread;

/**
 * 继承Thread的形式，编写英雄1杀死英雄2的类
 */
public class MyThreads extends Thread
{
    Hero hero1, hero2;

    /**
     * 传入两个已经初始化的Hero，hero1攻击hero2
     *
     * @param hero1
     * @param hero2
     */
    public MyThreads(Hero hero1, Hero hero2)
    {
        this.hero1 = hero1;
        this.hero2 = hero2;
    }

    public void killHero()
    {
        while (hero2.hp > 0)
        {
            this.hero1.attackHero(this.hero2);
        }
        System.out.format("%s已经被%s打死了%n", hero2.name, hero1.name);
    }

    public void run()
    {
        this.killHero();
    }

}
