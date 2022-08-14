package com.rdms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "rules")
public class Rules {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "stock_item", referencedColumnName = "ID" )
    private StockItem stockItem;
	
	@Column(name = "per_unit_card", nullable = false)
    private String  perUnitOrCard;
	
	@Column(name = "per_kg", nullable = false)
    private Integer kgPerUnitOrCard;

	@Column(name = "cardType", nullable = false)
    private String rationCardType;
	
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

	public StockItem getStockItem() {
		return stockItem;
	}

	public void setStockItem(StockItem stockItem) {
		this.stockItem = stockItem;
	}


	public Integer getKgPerUnitOrCard() {
		return kgPerUnitOrCard;
	}

	public void setKgPerUnitOrCard(Integer kgPerUnitOrCard) {
		this.kgPerUnitOrCard = kgPerUnitOrCard;
	}

	public String getRationCardType() {
		return rationCardType;
	}

	public void setRationCardType(String rationCardType) {
		this.rationCardType = rationCardType;
	}

	public String getPerUnitOrCard() {
		return perUnitOrCard;
	}

	public void setPerUnitOrCard(String perUnitOrCard) {
		this.perUnitOrCard = perUnitOrCard;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	
	
	
	
}
