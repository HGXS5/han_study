package cn.han.list;

import cn.han.model.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author han_s
 * @Date 2022/12/1 9:20
 * @ProName maven_test
 */

public class ListDemo1 {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("小红", 24));
        personList.add(new Person("小黄", 44));
        personList.add(new Person("小澜", 14));
        System.out.println(personList.toString());
        List<Integer> peekList = new ArrayList<>();
        /*
        * 1.peek没有返回值，独立流之外的逻辑操作
        * 2.map有返回值，可以改变流最终返回集合
        * */
//        List<Integer> collect = personList.stream().map(Person::getAge).collect(Collectors.toList());
//        List<Boolean> collect1 = personList.stream().map(person -> person.getAge() > 20).collect(Collectors.toList());
        Stream<Person> peek = personList.stream().peek(person -> peekList.add(person.getAge()));
        Stream<String> stringStream = peek.map(Person::getName);
        List<String> collect = stringStream.collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(peekList);


    }
}
