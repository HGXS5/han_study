package cn.han.pojo;

public class Demo {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("小红");
        p.setAge(24);
        p.setApples(new Apples("iphone8",7000));
        Book book = new Book("养生", 200);
        p.setBook(book);

//        try {
//            Person clone = (Person) p.clone();
//            Book book2 = clone.getBook();
//            book2.setName("运动");
//            book2.setPrice(100);
//            System.out.println(clone.getBook().getName());
//            System.out.println(p.getBook().getName());
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
    }
}
