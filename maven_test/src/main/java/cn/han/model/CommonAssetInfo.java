package cn.han.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CommonAssetInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3025308063182543334L;
	@Id
	@GeneratedValue
	@IgnoreParam
	private Long sid;
	@Transient
	@IgnoreParam
	private Integer type;//1代表剧集，2.代表文件 1
	@Transient
	private Integer operation;//1.增加或修改 2.删除
	private String providerId;	
	private String batchNo;
	
	private String assetId;
	
	private String assetName;
	
	private String assetType;

	private String category;
	
	private Integer items;
	@IgnoreParam
	private String keyword;
	@IgnoreParam
	private String simplesPell;
	
	private String description;
	@IgnoreParam
	private String awards;
	@IgnoreParam
	private String tags;
	@IgnoreParam
	private String directors;
	@IgnoreParam
	private String actors;
	@IgnoreParam
	private String language;
	@IgnoreParam
	private String caption;
	@IgnoreParam
	private String country;
	@IgnoreParam
	private String year;
	@IgnoreParam
	private String relaseTime;
	@IgnoreParam
	private String level;
	@IgnoreParam
	private String periods;

	private String images;	
	@IgnoreParam
	private Boolean completed;
	@IgnoreParam
	private String provider;
	@IgnoreParam
	private String license;

	private String updateTime;//局方传值
	@IgnoreParam
	private Integer delFlag;//软删除标识
	@IgnoreParam
	private Date createTime;
	@IgnoreParam
	private Date lastUpdateTime;//系统操作时间
	@IgnoreParam
	private Integer picState;

	@Transient
	@IgnoreParam
	private String autoDownloadId;
	@Transient
	@IgnoreParam
	private String autoDownloadStatus;
	@Transient
	@IgnoreParam
	private String startTime;
	@Transient
	@IgnoreParam
	private String endTime;
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getItems() {
		return items;
	}
	public void setItems(Integer items) {
		this.items = items;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSimplesPell() {
		return simplesPell;
	}
	public void setSimplesPell(String simplesPell) {
		this.simplesPell = simplesPell;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAwards() {
		return awards;
	}
	public void setAwards(String awards) {
		this.awards = awards;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRelaseTime() {
		return relaseTime;
	}
	public void setRelaseTime(String relaseTime) {
		this.relaseTime = relaseTime;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getAutoDownloadId() {
		return autoDownloadId;
	}
	public void setAutoDownloadId(String autoDownloadId) {
		this.autoDownloadId = autoDownloadId;
	}
	public String getAutoDownloadStatus() {
		return autoDownloadStatus;
	}
	public void setAutoDownloadStatus(String autoDownloadStatus) {
		this.autoDownloadStatus = autoDownloadStatus;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPicState() {
		return picState;
	}
	public void setPicState(Integer picState) {
		this.picState = picState;
	}

	
	
}
