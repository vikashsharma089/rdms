package com.rdms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "village",uniqueConstraints = { @UniqueConstraint(columnNames = { "village", "block" }) })
public class Village {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
 
	@Column(name = "village", nullable = false)
    private String villageName;

	@Column(name = "block", nullable = false)
    private String blockName;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Village() {
		super();
	}
	public Village(Integer iD) {
		super();
		ID = iD;
	}

	
	
	
}
