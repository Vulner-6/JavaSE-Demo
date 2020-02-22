package com.study_thread;

/**
 * 启动一个线程是start()，run()并不能启动一个新的线程。
 * 因此，使用MyRunable的时候，需要  new Thread(MyRunable myRunable);
 */
public class MyRunable implements Runnable
{
    Hero hero1, hero2;

    public MyRunable(Hero hero1, Hero hero2)
    {
        this.hero1 = hero1;
        this.hero2 = hero2;
    }

    public void battle()
    {
        while (this.hero2.hp > 20)
        {
            this.hero1.attackHero(this.hero2);
            this.hero2.eatHp();
        }
    }

    public void run()
    {
        this.battle();
    }
}
