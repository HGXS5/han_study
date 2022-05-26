package cn.han.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author zhaomy
 */
public class Constant {

 // 是否打印日志
 public static final boolean IS_PRINT_LOG = true;
 // 自动下载
 public static final Integer IS_AUTO_DOWNLOAD = 0;
 // 自动更新
 public static final Integer IS_AUTO_UPDATE = 0;
 
 // 文件类型 图片
 public static final String FILE_TYPE_IMAGE = "image";
 // 文件类型 视频
 public static final String FILE_TYPE_VIDEO = "video";
 
 // 文件类型 视频
 public static final String FILE_TYPE_AUDIO = "audio";
 
 // 文件下载命令
 public static final String FILE_DOWNLOAD_COMMAND = "download";
 
 // 文件下载的目标地址
 public static final String FTE_DEST_URL = "/data/";
 
 // 海报图片
 public static final String PLAY_IMAGE_FOLDER_CONSTANT_WORD =  "海报图片"; // 海报图片
 public static final String PLAY_IMAGE_FOLDER_CONSTANT_WORD_EN =  "poster";
 // 海报图片
 public static final String IMAGE_FOLDER_CONSTANT_WORD =  "图片";
 public static final String IMAGE_FOLDER_CONSTANT_WORD_EN =  "image";
 //文件类型 图片
 public static final String ASSET_TYPE_MOVIE = "电影";
 //文件类型 图片
 public static final String ASSET_TYPE_SERIAL= "电视剧";
 
 public static final String ASSET_TYPE_VARIETY = "综艺";
 
 //文件类型 图片
 public static final String ASSET_TYPE_MOVIE_EN = "movie";
 //文件类型 图片
 public static final String ASSET_TYPE_SERIA_EN= "serial";
 
 public static final String FTP = "FTP";
 
 public static final String DESTINATION_FTP_ADDRESS = "ftp://mgtvftp:mgtv123@125.210.117.33:21";
 
 public static final String IMAGE_URL_PRE_URL = "/data";
 
 public static final int NOT_DEL_FLG = 0;
 
 public static final int DEL_FLG = 1;
 
 // 乐视tv约定内容管理参数cspid
 public static final String LETV_CSPID = "letv";
 
 /**
  * 欧洲
  */
 public static final String EUROPE = "欧洲";
 /**
  * 美国
  */
 public static final String USA = "美国";
 /**
  * 中国
  */
 public static final String CHINA = "中国";
 /**
  * 大陆
  */
 public static final String MAINLAND = "大陆";
 /**
  * 中国香港
  */
 public static final String HONGKONG_CHINA = "中国香港";
 /**
  * 中国台湾
  */
 public static final String TAIWAN_CHINA = "中国台湾";
 /**
  * 中国澳门
  */
 public static final String MACAO_CHINA = "中国澳门";
 /**
  * 日本
  */
 public static final String JAPAN = "日本";
 /**
  * 韩国
  */
 public static final String REPUBLIC_KOREA = "韩国";
 /**
  * 泰国
  */
 public static final String THAILAND = "泰国";
 /**
  * 菲律宾
  */
 public static final String PHILIPPINES = "菲律宾";
 /**
  * 新加坡
  */
 public static final String SINGAPORE = "新加坡";
 /**
  * 越南
  */
 public static final String VIETNAM = "越南";
 /**
  * 老挝
  */
 public static final String LAOS = "老挝";
 /**
  * 柬埔寨
  */
 public static final String CAMBODIA = "柬埔寨";
 /**
  * 缅甸
  */
 public static final String MYANMAR = "缅甸";
 /**
  * 马来西亚
  */
 public static final String MALAYSIA = "马来西亚";
 /**
  * 印度尼西亚
  */
 public static final String INDONESIA = "印度尼西亚";
 /**
  * 文莱
  */
 public static final String BRUNEI = "文莱";
 /**
  * 东帝汶
  */
 public static final String TIMOR_LESTE = "东帝汶";
 // 乐视tv约定内容管理参数lspid
 public static final String LETV_LSPID = "wasu";
 
 public static final Map<String, String> SERIES_TYPE =  new HashMap<String, String>(){{
	 put("4", "电影");
	 put("5","电视剧");
	 put("6", "动漫");
	 put("66", "音乐");
	 put("78", "综艺");
	 put("86", "娱乐");
	 put("111", "人文历史纪实");
	 put("221", "体育");
	 put("10000", "卫视");
	 put("10059", "幼儿");
	 put("10232", "教育");
 	}
 };
 
 
 public static final Map<String, String> COUNTRYOFORIGIN_TYPE =  new HashMap<String, String>(){{
	 put("4", "电影");
	 put("5","电视剧");
	 put("6", "动漫");
	 put("66", "音乐");
	 put("78", "综艺");
	 put("86", "娱乐");
	 put("111", "人文历史纪实");
	 put("221", "体育");
	 put("10000", "卫视");
	 put("10059", "幼儿");
	 put("10232", "教育");
 	}
 };
 //蜻蜓FM的CLIENTID和CLIENTSECRET
 public static final String CLIENTID = "MGNhZDdkYTAtM2M4NC0xMWU4LTkyM2YtMDAxNjNlMDAyMGFk";
 public static final String CLIENTSECRET = "MDI4ZjU5NDUtZGU0Zi0zN2UwLTlkMjYtNzE0YTkzMTM0YWY4";
//优酷文件所在位置
 public static final String YOUKU_DEST_URL = "/data/data/youku/";
//七牛抓取文件的来源服务器 测试用
//public static final String QINIU_FETCH_URL= "http://125.210.163.67:8989";
//七牛抓取文件的来源服务器 正式
public static final String QINIU_FETCH_URL= "http://125.210.117.33:8989";

// 虎牙文件所在位置
public static final String HUYA_DEST_URL = "/data/电竞/虎牙专区/";
// 虎牙cp providerId
public static final String HUYA_CSPID = "huya";
// 虎牙文件类型 大图片
public static final String HUYA_BIG_IMAGE = "bigimg";

// 梨视频视频文件所在fds位置
public static final String PEAR_DEST_URL = "/data/梨视频/";
// 梨视频cp providerId
public static final String PEAR_PROVIDERID = "pear";
//梨视频imp文件所在本地位置
public static final String PEAR_IMP_URL = "/data/pear_imp_xml/";

//风行本地图片位置
public static final String FENGXING_DEST_URL = "/data/funshion/";
//风行sp providerId
public static final String FENGXING_PROVIDERID = "funshion";
//二更视频cp providerId
public static final String ERGENG_PROVIDERID = "ergeng";
public static final String ERGENG_DEST_URL = "/data/二更/";
//元数据输入cp providerId
public static final String COMMON_PROVIDERID = "common";
public static final String COMMON_DEST_URL = "/data/";

//通用imp文件所在本地位置
public static final String COMMON_IMP_URL  = "/Users/zhongbo/home/";

// 新华社视频文件所在fds位置
public static final String XINHUAAGENCY_DEST_URL = "/data/新华社/";
// 新华社cp providerId
public static final String XINHUA_PROVIDERID = "xinhuaAgency";
// 融合门户
public static final String SOPPLUS_PROVIDERID = "sopplus";

    /**
     * 融合门户 资料集
     */
    public static final Map<String, String> DataSet_TYPE =  new HashMap<String, String>(){{
        put("电影", "56c488421c1c46ad9c9948a288b2b10b");
        put("电视剧","bee4279c4bfb48b9aee54f5a0accfcff");
        put("新闻", "aca682adafff45e8a0baa2dab803433c");
        put("音乐", "a44f00af16d64ae1b787d2da627c964d");
        put("语音", "dda7f4d875824c65ad4da91c744ccaa8");
        put("剧照", "531c0f3c7fd74f4f9cb4041ecfabef08");
        put("栏目", "f8f36b9cf2a84b1ea7f1b41e9461fc99");
    }
    };
}
