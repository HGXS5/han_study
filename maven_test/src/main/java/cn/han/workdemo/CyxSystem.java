package cn.han.workdemo;

import java.util.Date;

/**
 * @Author han_s
 * @Date 2022/4/22 10:01
 * @ProName cp-parent
 */
public class CyxSystem {
    /**
     * 资产ID
     * 拆条对应的资产ID,用于XML显示,采集不采用此数值
     */
    private Long assetid;
    private String name;
    /**
     * 资产代码
     */
    private String code;
    /**
     * 资产类型
     * 10代表电影；11代表连续剧；13代表视频新闻；20代表文章
     */
    private String assetType;
    /**
     * 片库标识
     */
    private Long contentbankid;
    /**
     * 栏目ID
     */
    private Long folderid;
    /**
     * 服务提供商的代码
     */
    private String spCode;
    /**
     * 内容提供商的代码
     */
    private String cpcode;
    /**
     * 栏目代码
     */
    private String folderCode;
    /**
     * 栏目名称
     */
    private String folderName;
    private Date createtime;
    /**
     * 优先级
     */
    private Integer priority;
    /**
     * 资产等级
     * 可取值为1，2，3，4
     */
    private Integer assetClass;
    private Double price;
    /**
     * 资产类型
     * 为0，表示影视类节目；为1，表示直播类节目；为2，表示信息类节目
     */
    private Integer actType;
    private String searchCode;
    /**
     * 点播扣费方式
     * 为1，表示按时计费，为2，表示按次计费
     */
    private Integer demandChargeType;
    /**
     * 第一级资产类型
     */
    private String firstGradeFolderType;
    /**
     * 第二级资产类型
     */
    private String secondGradeFolderType;

    public Long getAssetid() {
        return assetid;
    }

    public void setAssetid(Long assetid) {
        this.assetid = assetid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Long getContentbankid() {
        return contentbankid;
    }

    public void setContentbankid(Long contentbankid) {
        this.contentbankid = contentbankid;
    }

    public Long getFolderid() {
        return folderid;
    }

    public void setFolderid(Long folderid) {
        this.folderid = folderid;
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public String getCpcode() {
        return cpcode;
    }

    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }

    public String getFolderCode() {
        return folderCode;
    }

    public void setFolderCode(String folderCode) {
        this.folderCode = folderCode;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(Integer assetClass) {
        this.assetClass = assetClass;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getActType() {
        return actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    public Integer getDemandChargeType() {
        return demandChargeType;
    }

    public void setDemandChargeType(Integer demandChargeType) {
        this.demandChargeType = demandChargeType;
    }

    public String getFirstGradeFolderType() {
        return firstGradeFolderType;
    }

    public void setFirstGradeFolderType(String firstGradeFolderType) {
        this.firstGradeFolderType = firstGradeFolderType;
    }

    public String getSecondGradeFolderType() {
        return secondGradeFolderType;
    }

    public void setSecondGradeFolderType(String secondGradeFolderType) {
        this.secondGradeFolderType = secondGradeFolderType;
    }

    @Override
    public String toString() {
        return "CyxSystem{" +
                "assetid=" + assetid +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", assetType='" + assetType + '\'' +
                ", contentbankid=" + contentbankid +
                ", folderid=" + folderid +
                ", spCode='" + spCode + '\'' +
                ", cpcode='" + cpcode + '\'' +
                ", folderCode='" + folderCode + '\'' +
                ", folderName='" + folderName + '\'' +
                ", createtime=" + createtime +
                ", priority=" + priority +
                ", assetClass=" + assetClass +
                ", price=" + price +
                ", actType=" + actType +
                ", searchCode='" + searchCode + '\'' +
                ", demandChargeType=" + demandChargeType +
                ", firstGradeFolderType='" + firstGradeFolderType + '\'' +
                ", secondGradeFolderType='" + secondGradeFolderType + '\'' +
                '}';
    }
}
