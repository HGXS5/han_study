package cn.han.objectfg;

import lombok.Data;
import lombok.ToString;

/**
 * @Author han_s
 * @Date 2022/7/11 17:41
 * @ProName maven_test
 */
@ToString
@Data
public class YoukuSpAssetFile {
    private String name;
    private String play;

    public YoukuSpAssetFile(String name, String play) {
        this.name = name;
        this.play = play;
    }
}
