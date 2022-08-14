package com.rdms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stock_details")
public class StockDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "stock_item", referencedColumnName = "ID")
    private StockItem stockItem;

	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="stock_id", nullable=false)
    private StockModel stock;
	
	@Column(name = "quantity")
    private Double quantity;

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

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity ==null ? 0:quantity;
	}

	public StockModel getStock() {
		return stock;
	}

	public void setStock(StockModel stock) {
		this.stock = stock;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
}
