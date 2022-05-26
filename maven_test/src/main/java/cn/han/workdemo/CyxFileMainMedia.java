package cn.han.workdemo;

/**
 * @Author han_s
 * @Date 2022/4/22 10:12
 * @ProName cp-parent
 */
public class CyxFileMainMedia {
    /**
     * 媒体文件的类型
     */
    private String Type;
    /**
     * 文件对应的单集
     */
    private Integer index;
    /**
     * 路径
     * locationtype为1：文件的路径
     * 当locationtype为 0 或者 2：locationtype所指的是一个外部目录 ftp://user: password @192.168.0.1
     */
    private String Location;
    private Integer locationtype;
    /**
     * 视频文件 DRM类型 （0，1，2，3，）
     * 0：没有DRM 1：MS DRM  2:Real DRM 3: MPEG  DRM
     */
    private String DRMType;
    /**
     * DRM对应的Key值， 是DRM模块传回的Key值，现在是ID；
     */
    private String drmkey;
    /**
     * 视频文件码流, 已Kbyte为单位（0；100；400；450；640；780；1200）
     */
    private String BiteRate;
    /**
     * 文件的唯一标识（导出时显示用，采集时候可以为任意值）
     */
    private String mediano;
    /**
     * 文件的字幕信息
     * 格式（XX1；XX2；XX3；XX4）
     * XX1代表字幕1的信息
     */
    private String caption;
    /**
     * 文件的字幕信息
     * 格式（XX1；XX2；XX3；XX4）
     * XX1代表声道1的信息：数字型
     */
    private String audio;
    /**
     * 媒体文件的分辨率
     */
    private String imageAspect;
    /**
     * 节目源介质
     * 1 ：BETA SP；
     *   2 ：DVD9；
     *   3 ：DVD5；
     *   4 ：SVCD；
     *   5 ：VCD；
     *   6 ：HDVD；
     *   7 ：录制；
     *   9 ：其他；
     */
    private Integer medium;
    /**
     * 文件的画幅宽高比
     *   1 ：4:3；
     *   2 ：16:9；
     *   3 ：非规范遮幅
     */
    private Integer ratio;
    /*媒体文件MD5值*/
    private String fileMD5;
    /*文件的描述信息*/
    private String description;
    /**
     * 媒体文件的时长 单位（秒）
     */
    private String playlength;
    /**
     * 视频文件编码格式
     * 0: 未知 10: WMV 20: MPEG2 30: SRT 40: MPEG4 50: H264 70: MP3 80: MP4 90: RM
     */
    private String format;
    /**
     * 文件的初始大小
     * 单位（Byte）
     * 文件搬迁后会回写文件实际大小。如果此列不存在，默认初始大小为0
     */
    private String orgfilesize;
    /**
     * 文档不存在属性
     * imp文件中存在的属性
     */
    private String director;


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public String getDRMType() {
        return DRMType;
    }

    public void setDRMType(String DRMType) {
        this.DRMType = DRMType;
    }

    public String getDrmkey() {
        return drmkey;
    }

    public void setDrmkey(String drmkey) {
        this.drmkey = drmkey;
    }

    public String getBiteRate() {
        return BiteRate;
    }

    public void setBiteRate(String biteRate) {
        BiteRate = biteRate;
    }

    public String getMediano() {
        return mediano;
    }

    public void setMediano(String mediano) {
        this.mediano = mediano;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImageAspect() {
        return imageAspect;
    }

    public void setImageAspect(String imageAspect) {
        this.imageAspect = imageAspect;
    }

    public Integer getMedium() {
        return medium;
    }

    public void setMedium(Integer medium) {
        this.medium = medium;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaylength() {
        return playlength;
    }

    public void setPlaylength(String playlength) {
        this.playlength = playlength;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOrgfilesize() {
        return orgfilesize;
    }

    public void setOrgfilesize(String orgfilesize) {
        this.orgfilesize = orgfilesize;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "CyxFileMainMedia{" +
                "Type='" + Type + '\'' +
                ", index=" + index +
                ", Location='" + Location + '\'' +
                ", locationtype=" + locationtype +
                ", DRMType='" + DRMType + '\'' +
                ", drmkey=" + drmkey +
                ", BiteRate=" + BiteRate +
                ", mediano='" + mediano + '\'' +
                ", caption='" + caption + '\'' +
                ", audio='" + audio + '\'' +
                ", imageAspect='" + imageAspect + '\'' +
                ", medium=" + medium +
                ", ratio=" + ratio +
                ", fileMD5='" + fileMD5 + '\'' +
                ", description='" + description + '\'' +
                ", playlength=" + playlength +
                ", format=" + format +
                ", orgfilesize=" + orgfilesize +
                ", director='" + director + '\'' +
                '}';
    }
}
