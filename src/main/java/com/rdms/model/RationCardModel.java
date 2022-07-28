package com.rdms.model;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;



@Entity
@Table(name = "ration_card")
public class RationCardModel {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "ID", nullable = false)
	    private Integer ID;
	 
		@Column(name = "card_number", nullable = false)
	    private String cardNumber;
		
		@Column(name = "card_holder", nullable = false)
	    private String cardHolder;
		
		@Column(name = "father_husband")
	    private String fatherOrHusband;
		
		@Column(name = "mother_name")
	    private String motherName;
		
		@Column(name = "unit")
	    private Integer unit;
		
		@Column(name = "issue_date")
	    private Instant issuedon;
		
		@Column(name = "card_type")
	    private String cartType;
		
		@OneToOne(cascade = CascadeType.MERGE)
	    @Fetch(FetchMode.SELECT)
	    @JoinColumn(name = "purwa_name", referencedColumnName = "ID")
	    private PurwaModel purwa;
		
		@OneToOne(cascade = CascadeType.MERGE)
	    @Fetch(FetchMode.SELECT)
	    @JoinColumn(name = "village", referencedColumnName = "ID")
	    private Village village;

		public RationCardModel() {
		}
		public RationCardModel(Integer iD) {
			ID = iD;
		}

		public Integer getID() {
			return ID;
		}

		public void setID(Integer iD) {
			ID = iD;
		}

		public String getCardNumber() {
			return cardNumber;
		}

		public void setCardNumber(String cardNumber) {
			this.cardNumber = cardNumber;
		}

		public String getCardHolder() {
			return cardHolder;
		}

		public void setCardHolder(String cardHolder) {
			this.cardHolder = cardHolder;
		}

		public String getFatherOrHusband() {
			return fatherOrHusband;
		}

		public void setFatherOrHusband(String fatherOrHusband) {
			this.fatherOrHusband = fatherOrHusband;
		}

		public String getMotherName() {
			return motherName;
		}

		public void setMotherName(String motherName) {
			this.motherName = motherName;
		}

		public Integer getUnit() {
			return unit;
		}

		public void setUnit(Integer unit) {
			this.unit = unit;
		}

		public Instant getIssuedon() {
			return issuedon;
		}

		public void setIssuedon(Instant issuedon) {
			this.issuedon = issuedon;
		}

		public String getCartType() {
			return cartType;
		}

		public void setCartType(String cartType) {
			this.cartType = cartType;
		}
		public PurwaModel getPurwa() {
			return purwa;
		}
		public void setPurwa(PurwaModel purwa) {
			this.purwa = purwa;
		}
		public Village getVillage() {
			return village;
		}
		public void setVillage(Village village) {
			this.village = village;
		}

		
		
		
		

}
