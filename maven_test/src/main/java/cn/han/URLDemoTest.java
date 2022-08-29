package cn.han;

import cn.han.util.Constant;

import java.io.File;
import java.util.List;

/**
 * @Author han_s
 * @Date 2022/7/13 16:37
 * @ProName maven_test
 */
public class URLDemoTest {
    public static void main(String[] args) {
        testOne();
    }
    public static void testOne(){
        String vodCode = "fd";
        String sort = "发达";
        String dir = "D:\\data\\" + vodCode + "\\" + sort + "\\";
        File file = new File(dir);
        if (!file.exists()) {
            boolean mkdir = file.mkdirs();// 仅能创建当前目录下的子目录,若两级目录均不存在.则创建失败
            if (mkdir){
                System.out.println("成功");
            }
        } else if (!file.isDirectory()) {
            file.mkdir();
        }
        System.out.println(dir);
    }
    public static void testUrl(){
        //        String fileName = "/c1/2022/06/24/99AE9DEADF42E6D67F225D8924831459.ts";
//        String substring = fileName.substring(fileName.lastIndexOf("."));
//        System.out.println(substring);
//        String[] split = fileName.split("/");
//        String filePath = Constant.FTE_DEST_URL+split[1]+ File.separator+split[2]+"/剧集/"+split[3]+File.separator;
//
//        String p = "asd_";
//        int i = p.indexOf("_");
//        System.out.println(i);
//        System.out.println(p.substring(0,i));
        String ids = "";
        String[] split = ids.split(",");
        System.out.println(ids.length());
        System.out.println(split.length);
        System.out.println(split[split.length-1]);
        for (String s : split) {
            System.out.println(s);
        }
    }
    private void testFileId(){
//        StringBuffer buffer = new StringBuffer();
//        buffer.append(fileIds);
//        if (assetIds.length()>0) {
//            String[] ids = assetIds.split(",");
//            for (String id : ids) {
//                List<String> fileIdList = youKuSPFileManagerService.getByAssetIdList(id);
//                if (fileIdList.size() > 0) {
//                    for (String s : fileIdList) {
//                        if (fileIds.length()>0){
//                            buffer.append(",");
//                            buffer.append(s);
//                        }else {
//                            if (s.equals(fileIdList.get(fileIdList.size()-1))){
//                                buffer.append(s);
//                            }else {
//                                buffer.append(s);
//                                buffer.append(",");
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }
    public static void testFt(){
        String ugc_gxyk_download_url = "https://wasu-hls-test-input.obs.cn-east-2.myhuaweicloud.com/";
        String assetFileUrl = "https://wasu-hls-test-input.obs.cn-east-2.myhuaweicloud.com/gxyk/89961649224607979";
        String cpinfoUrl = "ftp://zjyx:gncr)4-5Mm8h@175.6.15.94:21";

        String s = assetFileUrl.replaceAll(ugc_gxyk_download_url, cpinfoUrl);
        System.out.println(s);
    }
}
