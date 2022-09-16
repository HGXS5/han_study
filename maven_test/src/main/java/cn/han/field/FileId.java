package cn.han.field;

/**
 * @Author han_s
 * @Date 2022/9/6 10:34
 * @ProName maven_test
 */
public class FileId {
    private String[] ids;

    public String[] test(){
        String[] sid = new String[3];
        ids = sid;
        sid[0] = "4";
        sid[1] = "6";
        return sid;
    }

    public String[] getString(){
        return ids;
    }
}
