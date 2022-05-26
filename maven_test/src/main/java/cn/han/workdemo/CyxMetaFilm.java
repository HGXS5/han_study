package cn.han.workdemo;

import java.util.Date;

/**
 * @Author han_s
 * @Date 2022/4/22 10:06
 * @ProName cp-parent
 */
public class CyxMetaFilm {
    /**
     * 代理公司
     */
    private String agent;
    /**
     * 发行地区
     */
    private String region;
    private String language;
    /**
     * 备注
     */
    private String comments;
    /**
     * 制式
     * 为1，表示黑白；为2，表示彩色
     */
    private Integer format;
    /**
     * 发行年份，格式为2001
     */
    private Date year;
    private String director;
    /*男演员*/
    private String actor;
    /*女演员*/
    private String actress;
    /*男配角*/
    private String coactress;
    /*女配角*/
    private String coactor;
    /*监制*/
    private String superviser;
    /*幕后人员*/
    private String background;
    /*描述*/
    private String description;
    /*IPTV介绍*/
    private String iptvdesc;
    /*英文介绍*/
    private String englishdesc;
    /*看点*/
    private String viewpoint;
    /*关键字*/
    private String keyword;
    /**
     * 推荐星级
     * 可取值有：5，4，3，2，1
     */
    private String starlevel;
    /**
     * 限制级别
     *
     */
    private String rating;
    /**
     * 片长信息，以秒为单位
     */
    private String length;
    /*所获奖项*/
    private String awards;
    /*媒体格式*/
    private String mediaformat;
    /*英文名称*/
    private String englishname;
    /**
     * 资产名称
     */
    private String name;
    /**
     * 分类
     */
    private String category;
    /*内容服务商的代码*/
    private String cpcode;
    /**
     * 版权
     *  为0，表示无版权；为1，表示正版；为2，表示为正式签订版权
     */
    private Integer licensetype;
    /**
     * 版权生效日期
     */
    private Date licensevaliddate;
    /**
     * 版权失效日期
     */
    private Date licenseexpiredate;
    /**
     * 外部系统产生的序列号
     *      用来定位是外部系统定义的资产
     */
    private String externalno;
    /*外部URL*/
    private String externalurl;
    /*保留字段1*/
    private String reserve1;
    /*保留字段2*/
    private String reserve2;
    /*出品公司*/
    private String company;
    /*配音*/
    private String duber;

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getFormat() {
        return format;
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }

    public String getCoactress() {
        return coactress;
    }

    public void setCoactress(String coactress) {
        this.coactress = coactress;
    }

    public String getCoactor() {
        return coactor;
    }

    public void setCoactor(String coactor) {
        this.coactor = coactor;
    }

    public String getSuperviser() {
        return superviser;
    }

    public void setSuperviser(String superviser) {
        this.superviser = superviser;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIptvdesc() {
        return iptvdesc;
    }

    public void setIptvdesc(String iptvdesc) {
        this.iptvdesc = iptvdesc;
    }

    public String getEnglishdesc() {
        return englishdesc;
    }

    public void setEnglishdesc(String englishdesc) {
        this.englishdesc = englishdesc;
    }

    public String getViewpoint() {
        return viewpoint;
    }

    public void setViewpoint(String viewpoint) {
        this.viewpoint = viewpoint;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStarlevel() {
        return starlevel;
    }

    public void setStarlevel(String starlevel) {
        this.starlevel = starlevel;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMediaformat() {
        return mediaformat;
    }

    public void setMediaformat(String mediaformat) {
        this.mediaformat = mediaformat;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCpcode() {
        return cpcode;
    }

    public void setCpcode(String cpcode) {
        this.cpcode = cpcode;
    }

    public Integer getLicensetype() {
        return licensetype;
    }

    public void setLicensetype(Integer licensetype) {
        this.licensetype = licensetype;
    }

    public Date getLicensevaliddate() {
        return licensevaliddate;
    }

    public void setLicensevaliddate(Date licensevaliddate) {
        this.licensevaliddate = licensevaliddate;
    }

    public Date getLicenseexpiredate() {
        return licenseexpiredate;
    }

    public void setLicenseexpiredate(Date licenseexpiredate) {
        this.licenseexpiredate = licenseexpiredate;
    }

    public String getExternalno() {
        return externalno;
    }

    public void setExternalno(String externalno) {
        this.externalno = externalno;
    }

    public String getExternalurl() {
        return externalurl;
    }

    public void setExternalurl(String externalurl) {
        this.externalurl = externalurl;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDuber() {
        return duber;
    }

    public void setDuber(String duber) {
        this.duber = duber;
    }

    @Override
    public String toString() {
        return "CyxMetaFilm{" +
                "agent='" + agent + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", comments='" + comments + '\'' +
                ", format=" + format +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", actress='" + actress + '\'' +
                ", coactress='" + coactress + '\'' +
                ", coactor='" + coactor + '\'' +
                ", superviser='" + superviser + '\'' +
                ", background='" + background + '\'' +
                ", description='" + description + '\'' +
                ", iptvdesc='" + iptvdesc + '\'' +
                ", englishdesc='" + englishdesc + '\'' +
                ", viewpoint='" + viewpoint + '\'' +
                ", keyword='" + keyword + '\'' +
                ", starlevel=" + starlevel +
                ", rating='" + rating + '\'' +
                ", length=" + length +
                ", awards='" + awards + '\'' +
                ", mediaformat='" + mediaformat + '\'' +
                ", englishname='" + englishname + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", cpcode='" + cpcode + '\'' +
                ", licensetype=" + licensetype +
                ", licensevaliddate=" + licensevaliddate +
                ", licenseexpiredate=" + licenseexpiredate +
                ", externalno='" + externalno + '\'' +
                ", externalurl='" + externalurl + '\'' +
                ", reserve1='" + reserve1 + '\'' +
                ", reserve2='" + reserve2 + '\'' +
                ", company='" + company + '\'' +
                ", duber='" + duber + '\'' +
                '}';
    }
}
