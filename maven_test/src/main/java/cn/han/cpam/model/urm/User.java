package cn.han.cpam.model.urm;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "urm_user", indexes = {
		@Index(name = "idx_loginName", columnList = "loginName"),
		@Index(name = "idx_orgId", columnList = "orgId") })
public class User implements Serializable {
	
	private static final long serialVersionUID = -6711497018149144036L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	private String loginName;
	@JsonIgnore
	private String password;

	private String orgId;
	private Integer manager;
	@Transient
	private List<Resource> resources;
	@Transient
	private String orgName;
	@Transient
	private Organization org;
	@Transient
	private List<Organization> relateOrgs;
	@Transient
	private String checked;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Organization getOrg() {
		return org;
	}

	public Integer isManager() {
		return manager;
	}

	public void setManager(Integer manager) {
		this.manager = manager;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public List<Organization> getRelateOrgs() {
		return relateOrgs;
	}

	public void setRelateOrgs(List<Organization> relateOrgs) {
		this.relateOrgs = relateOrgs;
	}

	public boolean getChecked() {
		return Boolean.parseBoolean(checked);
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}