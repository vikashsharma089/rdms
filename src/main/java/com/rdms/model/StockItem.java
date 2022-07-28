package com.rdms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table( name = "stock_item", uniqueConstraints={ @UniqueConstraint(columnNames = {"item_name_small", "village"})})
public class StockItem {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	@Column(name = "item_name")
    private String itemName;
	
	@Column(name = "item_name_small",unique = true)
	 private String itemNameLowerCase;

	 @PrePersist @PreUpdate private void prepare(){
	     this.itemNameLowerCase = itemName == null ? null : itemName.toLowerCase();
	    }

	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "village", referencedColumnName = "ID")
    private Village village;
	

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
}
