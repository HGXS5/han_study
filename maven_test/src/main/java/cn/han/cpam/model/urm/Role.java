package cn.han.cpam.model.urm;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "urm_role")
public class Role implements Serializable{

	private static final long serialVersionUID = -1904341205097578614L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String remark;
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
}
