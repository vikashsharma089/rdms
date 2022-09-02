package com.rdms.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name = "distribution")
public class RationDistribution implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id", referencedColumnName = "ID")
    private StockModel stock;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ration_card", referencedColumnName = "ID")
    private RationCardModel rationCard;

	@Column(name = "distribution_date")
    private Instant distributedOn;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "village_id", referencedColumnName = "ID")
    private Village village;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "signature")
	@Lob
	private byte[] signature;


	 @OneToMany(mappedBy="distribution",fetch = FetchType.EAGER)
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
		if(totalAmount == null)
			totalAmount = 0.0;
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
}

