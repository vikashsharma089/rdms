package com.rdms.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "purwa")
public class PurwaModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	@Column(name = "purwa")
    private String purwaName;

	@OneToOne()
	@Fetch(FetchMode.SELECT)
	@JoinColumn(name = "village", referencedColumnName = "ID")
	private Village village;
	
	public PurwaModel() {
		super();
	}
	

	public PurwaModel(Integer iD) {
		super();
		ID = iD;
	}


	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getPurwaName() {
		return purwaName;
	}

	public void setPurwaName(String purwaName) {
		this.purwaName = purwaName;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
}
