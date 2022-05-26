package cn.han.workdemo;

/**
 * @Author han_s
 * @Date 2022/4/22 10:11
 * @ProName cp-parent
 */
public class CyxFileImage {
    /**
     * 图片文件对应的单集号
     *  0代表图片文件对应在全集上，单集号不能为负数，其他的正整数单集号，该单集必须存在
     */
    private Integer index;
    /**
     * 图片文件的类型
     * Main：正片图片；Poster：大海报；SmallPoster：小海报； BigPhoto：大图片；SmallPhoto：小图片；Photo：剧照；Others：其他图片文件
     */
    private String Type;
    /**
     * 文件的唯一标识
     *  导出时显示用，采集时候可以为任意值
     */
    private String Mediano;
    /**
     * 路径
     * locationtype为1：文件的路径
     * 当locationtype为 0 或者 2：locationtype所指的是一个外部目录 ftp://user: password @192.168.0.1
     */
    private String Location;
    private Integer locationtype;
    /*文件的描述信息*/
    private String Description;
    /**
     * 图片文件的序号
     */
    private Long Fileindex;
    /**
     * 文件的初始大小
     * 单位（Byte）
     * 文件搬迁后会回写文件实际大小。如果此列不存在，默认初始大小为0
     */
    private String Orgfilesize;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMediano() {
        return Mediano;
    }

    public void setMediano(String mediano) {
        Mediano = mediano;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getLocationtype() {
        return locationtype;
    }

    public void setLocationtype(Integer locationtype) {
        this.locationtype = locationtype;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getFileindex() {
        return Fileindex;
    }

    public void setFileindex(Long fileindex) {
        Fileindex = fileindex;
    }

    public String getOrgfilesize() {
        return Orgfilesize;
    }

    public void setOrgfilesize(String orgfilesize) {
        Orgfilesize = orgfilesize;
    }

    @Override
    public String toString() {
        return "CyxFileImage{" +
                "index=" + index +
                ", Type='" + Type + '\'' +
                ", Mediano='" + Mediano + '\'' +
                ", Location='" + Location + '\'' +
                ", locationtype=" + locationtype +
                ", Description=" + Description +
                ", Fileindex=" + Fileindex +
                ", Orgfilesize=" + Orgfilesize +
                '}';
    }
}
