package com.han.test9;

public class ProxyTest1 {
    public static void main(String[] args) {
        Gunman li = new Gunman(new LazyStudent("李小明"));
        li.answerTheQuestions();
    }
}
