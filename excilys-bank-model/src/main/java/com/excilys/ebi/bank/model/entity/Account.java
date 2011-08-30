package com.excilys.ebi.bank.model.entity;

import static com.google.common.collect.Lists.newArrayList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import com.excilys.ebi.bank.model.entity.ref.AccountTypeRef;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 6057067260381947770L;

	private Integer id;

	private String number;

	private BigDecimal balance;

	private DateTime balanceDate;

	private AccountTypeRef type;

	private List<User> users = newArrayList();

	private List<Operation> operations = newArrayList();

	private List<Card> cards = newArrayList();

	@Transient
	public BigDecimal getTotalPending() {

		BigDecimal totalPending = BigDecimal.valueOf(0.0);

		for (Card card : cards) {
			if (card.getPending() != null) {
				totalPending = totalPending.add(card.getPending());
			}
		}
		return totalPending;
	}

	@Transient
	public BigDecimal getEstimatedBalance() {
		return balance.add(getTotalPending());
	}

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

	@Column(name = "BALANCE", nullable = false)
	public BigDecimal getBalance() {
		return balance;
	}

	@Column(name = "BALANCE_DATE")
	public DateTime getBalanceDate() {
		return balanceDate;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "TYPE", nullable = false, updatable = false)
	public AccountTypeRef getType() {
		return type;
	}

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "accounts")
	public List<User> getUsers() {
		return users;
	}

	@OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<Operation> getOperations() {
		return operations;
	}

	@OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<Card> getCards() {
		return cards;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setBalanceDate(DateTime balanceDate) {
		this.balanceDate = balanceDate;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setType(AccountTypeRef type) {
		this.type = type;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
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
		Account other = (Account) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

}
