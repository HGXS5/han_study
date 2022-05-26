/**   
 * @project    : adi-oss
 * @copyright  : (c) 2016 Wasu,ltd.
 * @package    : com.wasu.adi.oss.client
 * @created on : 2016年6月13日 下午4:49:23
 * @author     : wangych
 */
package cn.han.cpam.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @fileName : Resource.java
 * @description: TODO
 */

public class Resource implements Serializable {
	private static final long serialVersionUID = -3481951652735160673L;
	private String id;
	private String code;
	private String name;
	private String action;
	private String parentId;
	private int auth;
	private int rank;
	private List<Resource> subResources = new ArrayList<Resource>();
	
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

	public int getAuth() {
		return auth;
	}

	public boolean isAuthed() {
		return auth == 1;
	}
	
	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public List<Resource> getSubResources() {
		return subResources;
	}

	public void setSubResources(List<Resource> subResources) {
		this.subResources = subResources;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
