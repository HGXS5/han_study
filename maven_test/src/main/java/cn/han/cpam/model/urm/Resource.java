package cn.han.cpam.model.urm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "urm_resource", indexes = { @Index(name = "idx_appId", columnList = "appId") })
public class Resource implements Serializable {

	private static final long serialVersionUID = -8305133715250993201L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name;
	private String action;
	private int rank;

	private String appId;
	private Integer parentId;
	@Transient
	private String parentName;
	@Transient
	private Long auth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getAuth() {
		return auth;
	}

	public void setAuth(Long auth) {
		this.auth = auth;
	}

}