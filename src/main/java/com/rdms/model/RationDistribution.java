package com.rdms.model;

import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name = "distribution")
public class RationDistribution {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	
	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "stock_id", referencedColumnName = "ID")
    private StockModel stock;

	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "ration_card", referencedColumnName = "ID")
    private RationCardModel rationCard;
	
	@Column(name = "distribution_date")
    private Instant distributedOn;
	
	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "village_id", referencedColumnName = "ID")
    private Village village;

	@Column(name = "total_amount")
	private Double totalAmount;


	 @OneToMany(mappedBy="distribution")
	 @OrderBy("stock_item")
	 private Set<DistributionDetails> details;
	 
	 
	
	public RationDistribution() {
		super();
	}
	

	public RationDistribution(Integer iD) {
		super();
		ID = iD;
	}


	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	
	public StockModel getStock() {
		return stock;
	}

	public void setStock(StockModel stock) {
		this.stock = stock;
	}

	public RationCardModel getRationCard() {
		return rationCard;
	}

	public void setRationCard(RationCardModel rationCard) {
		this.rationCard = rationCard;
	}

	public Instant getDistributedOn() {
		return distributedOn;
	}

	public void setDistributedOn(Instant distributedOn) {
		this.distributedOn = distributedOn;
	}

	public Set<DistributionDetails> getDetails() {
		return details;
	}

	public void setDetails(Set<DistributionDetails> details) {
		this.details = details;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
}

