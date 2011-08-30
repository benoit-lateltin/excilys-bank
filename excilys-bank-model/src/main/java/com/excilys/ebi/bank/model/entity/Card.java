package com.excilys.ebi.bank.model.entity;

import static com.google.common.collect.Lists.newArrayList;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.excilys.ebi.bank.model.entity.ref.CardTypeRef;

@Entity
@Table(name = "CARD")
public class Card {

	private Integer id;
	private String number;
	private CardTypeRef type;
	private BigDecimal pending;
	private DateTime pendingDate;
	private Account account;

	private List<Operation> operations = newArrayList();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 20)
	public Integer getId() {
		return id;
	}

	@Column(name = "NUMBER", nullable = false, unique = true, length = 20)
	public String getNumber() {
		return number;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "TYPE", nullable = false, updatable = false)
	public CardTypeRef getType() {
		return type;
	}

	@Column(name = "PENDING")
	public BigDecimal getPending() {
		return pending;
	}

	@Column(name = "PENDING_DATE")
	public DateTime getPendingDate() {
		return pendingDate;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT", nullable = false, updatable = false)
	public Account getAccount() {
		return account;
	}

	@OneToMany(mappedBy = "card", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<Operation> getOperations() {
		return operations;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setType(CardTypeRef type) {
		this.type = type;
	}

	public void setPending(BigDecimal pending) {
		this.pending = pending;
	}

	public void setPendingDate(DateTime pendingDate) {
		this.pendingDate = pendingDate;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

}
