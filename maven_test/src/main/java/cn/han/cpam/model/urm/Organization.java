package cn.han.cpam.model.urm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "urm_organization")
public class Organization implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1240361523240556679L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String code;
	private String remark;
	private boolean manager;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean getChecked() {
		return Boolean.parseBoolean(checked);
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}
}