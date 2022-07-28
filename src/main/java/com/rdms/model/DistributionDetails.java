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
@Table(name = "distribution_detail")
public class DistributionDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="distribution", nullable=false)
    private RationDistribution distribution;
	
	@Column(name = "quantity")
    private Double quantity;

	@OneToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "stock_item", referencedColumnName = "ID")
    private StockItem stockItem;

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public RationDistribution getDistribution() {
		return distribution;
	}

	public void setDistribution(RationDistribution distribution) {
		this.distribution = distribution;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity == null ? 0: quantity;
	}

	public StockItem getStockItem() {
		return stockItem;
	}

	public void setStockItem(StockItem stockItem) {
		this.stockItem = stockItem;
	}
	
	
	
}

