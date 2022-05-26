package cn.han.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 元数据输入
 * 
 * @author renj
 *
 */
@Entity
@Table(indexes = { @Index(name = "idIndex", columnList = "providerId,assetId,videoId,fileId") })
public class CommonAssetFile implements Serializable {

	private static final long serialVersionUID = 5438336865228343393L;
	@Id
	@GeneratedValue
	@IgnoreParam
	private Long sid;
	@Transient
	private Integer type;// 1代表剧集，2.代表文件 2
	@Transient
	private Integer operation;// 1.增加或修改 2.删除

	@Column(nullable = false)
	private String providerId;
	@Column(nullable = false)
	private String batchNo;
	@Column(nullable = false)
	private String assetId;
	@IgnoreParam
	private String assetType; // 电影，电视剧，综艺，游戏，纪录片..
	@IgnoreParam
	@Transient
	private String assetName;
	@Column(nullable = false)
	private String videoId;
	private String title;
	@IgnoreParam
	private String description;
	@IgnoreParam
	private String awards;
	@IgnoreParam
	private String videoType; // 正片，预告片，花絮
	private Float duration;
	@IgnoreParam
	private String sequence;
	@IgnoreParam
	private String images;
	@Column(nullable = false)
	private String fileId;
	private Long fileSize;

	private String fileFormatDesc;
	@IgnoreParam
	private Long fileBitrate;
	@IgnoreParam
	private String fileHash;
	private String fileSourceUrl;
	private String updateTime;
	
	@IgnoreParam
	private Date createTime;
	@IgnoreParam
	private Date lastUpdateTime;
	@IgnoreParam
	private Integer delFlag;
	@IgnoreParam
	private Integer downloadState;
	@IgnoreParam
	private String gid;

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public Integer getDownloadState() {
		return downloadState;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setDownloadState(Integer downloadState) {
		this.downloadState = downloadState;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

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

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileFormatDesc() {
		return fileFormatDesc;
	}

	public void setFileFormatDesc(String fileFormatDesc) {
		this.fileFormatDesc = fileFormatDesc;
	}

	public Long getFileBitrate() {
		return fileBitrate;
	}

	public void setFileBitrate(Long fileBitrate) {
		this.fileBitrate = fileBitrate;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	public String getFileSourceUrl() {
		return fileSourceUrl;
	}

	public void setFileSourceUrl(String fileSourceUrl) {
		this.fileSourceUrl = fileSourceUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}
