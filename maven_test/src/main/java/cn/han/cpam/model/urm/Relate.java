package cn.han.cpam.model.urm;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "urm_relate", indexes = {
		@Index(name="idx_objectId", columnList = "objectId"),
		@Index(name="idx_relatedId", columnList = "relatedId"),
		@Index(name="idx_typeId", columnList = "typeId") })
public class Relate implements Serializable{
	
	private static final long serialVersionUID = 1251963512206816213L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String objectId;
	
	private String relatedId;

	private String typeId;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}