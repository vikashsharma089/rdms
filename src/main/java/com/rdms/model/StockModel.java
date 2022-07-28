package com.rdms.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stock")
public class StockModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer ID;
	
	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "month_id", referencedColumnName = "ID")
    private MonthModel monthId;
	
	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "year_id", referencedColumnName = "ID")
    private YearModel yearId;
	
	
	@OneToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "village_id", referencedColumnName = "ID")
    private Village village;
	
	@Column(name = "details", nullable = false)
    private String details;
	
	@Column(name = "distributed")
    private Boolean distributed;
	
	
	 @OneToMany(mappedBy="stock")
	 @OrderBy("stock_item")
	 private Set<StockDetails> items;

	public StockModel() {
	}
	
	public StockModel(Integer iD) {
		ID = iD;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Boolean getDistributed() {
		return distributed;
	}

	public void setDistributed(Boolean distributed) {
		this.distributed = distributed;
	}

	public MonthModel getMonthId() {
		return monthId;
	}

	public void setMonthId(MonthModel monthId) {
		this.monthId = monthId;
	}

	public YearModel getYearId() {
		return yearId;
	}

	public void setYearId(YearModel yearId) {
		this.yearId = yearId;
	}

	public Set<StockDetails> getItems() {
		return items;
	}

	public void setItems(Set<StockDetails> items) {
		this.items = items;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}
	
	
	
}
