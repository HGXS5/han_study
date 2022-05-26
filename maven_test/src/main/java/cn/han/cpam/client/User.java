/**   
 * @project    : adi-oss
 * @copyright  : (c) 2016 Wasu,ltd.
 * @package    : com.wasu.adi.oss.client
 * @created on : 2016年6月13日 下午4:52:06
 * @author     : wangych
 */
package cn.han.cpam.client;

import cn.han.cpam.model.urm.Organization;

import java.io.Serializable;
import java.util.List;

/**
 * @fileName : User.java
 * @description: TODO
 */

public class User implements Serializable{
	
	private static final long serialVersionUID = 8723726680576717478L;
	private String name;
	private String loginName;
	private boolean manager;
	private Organization org;
	private List<Organization> relateOrgs;
	private List<Resource> resources;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Organization getOrg() {
		return org;
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

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

}
