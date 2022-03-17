package cn.han.map;

import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("hg", "qwe");
        System.out.println(hm.get("hg"));
//        ter();


    }

    private static void test() {
    }

    private static void ter(){
        int h = 4;
        int i = h >>> 1;
        int y = i >>> 1;
        int x = h ^ i;
        int z = 1 << 4;
        int c = 1 << 30;
        int b = (int) (z * 0.75);
        int l = 12 << 1;
        System.out.println(i);
        System.out.println(y);
        System.out.println(x);
        System.out.println(z);
        System.out.println(b);
        System.out.println(c);
        System.out.println(l);
    }

    private static void test(int f){
        if (f<3){
            for (int binCount = 0; ; ++binCount) {
                System.out.println("binCount:"+binCount);
                if (binCount>8){
                    System.out.println(binCount);
                }
                break;
            }
        }else{
            System.out.println(f);
        }

    }
}
