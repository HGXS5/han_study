package cn.han.abstractandimplement;

import cn.han.abstractandimplement.abstarct.Person;

public class test {
    private static Person person;

    public test() {
        person = new Woman();
    }

    public static void main(String[] args) {
        test t = new test();
        t.play();
    }

    public static void play(){
        person.play();
    }
}
