package com.excilys.ebi.bank.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import com.excilys.ebi.bank.model.entity.ref.OperationSign;
import com.excilys.ebi.bank.model.entity.ref.OperationStatusRef;
import com.excilys.ebi.bank.model.entity.ref.OperationTypeRef;

@Entity
@Table(name = "OPERATION")
public class Operation implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2903197989437525974L;

	private Integer id;
	private BigDecimal amount;
	private String name;
	private DateTime date;
	private Account account;
	private Card card;
	private OperationStatusRef status;
	private OperationTypeRef type;

	public static class Builder {

		private Integer id;
		private BigDecimal amount;
		private String name;
		private DateTime date;
		private Account account;
		private Card card;
		private OperationStatusRef status;
		private OperationTypeRef type;

		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

		public Builder withAmount(BigDecimal amount) {
			this.amount = amount;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withDate(DateTime date) {
			this.date = date;
			return this;
		}

		public Builder withAccount(Account account) {
			this.account = account;
			return this;
		}

		public Builder withCard(Card card) {
			this.card = card;
			return this;
		}

		public Builder withStatus(OperationStatusRef status) {
			this.status = status;
			return this;
		}

		public Builder withType(OperationTypeRef type) {
			this.type = type;
			return this;
		}

		public Operation build() {
			Operation operation = new Operation();
			operation.id = id;
			operation.amount = amount;
			operation.name = name;
			operation.date = date;
			operation.account = account;
			operation.card = card;
			operation.status = status;
			operation.type = type;

			return operation;
		}
	}

	@Transient
	public OperationSign getSign() {
		return OperationSign.getSign(amount);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 20)
	public Integer getId() {
		return id;
	}

	@Column(name = "AMOUNT", nullable = false)
	public BigDecimal getAmount() {
		return amount;
	}

	@Column(name = "NAME", nullable = false, length = 20)
	public String getName() {
		return name;
	}

	@Column(name = "DATE", nullable = false, updatable = false)
	public DateTime getDate() {
		return date;
	}

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT", nullable = false, updatable = false)
	public Account getAccount() {
		return account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD", nullable = true, updatable = false)
	public Card getCard() {
		return card;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "STATUS", nullable = false)
	public OperationStatusRef getStatus() {
		return status;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "TYPE", nullable = false)
	public OperationTypeRef getType() {
		return type;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setStatus(OperationStatusRef status) {
		this.status = status;
	}

	public void setType(OperationTypeRef type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Operation other = (Operation) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
}
