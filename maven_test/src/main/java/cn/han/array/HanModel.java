package cn.han.array;

/**
 * @Author han_s
 * @Date 2022/10/19 12:30
 * @ProName maven_test
 */
public class HanModel implements Comparable<HanModel>{
    private int id;
    private String name;
    private int age;

    public HanModel() {
    }

    public HanModel(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "HanModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(HanModel o) {
        return this.id-o.id;
    }
}
