package com.excilys.ebi.bank.model.entity.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import com.excilys.ebi.bank.model.IConstants;

@Entity(name = "REF_ACCOUNT_TYPE")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = IConstants.Cache.ENTITY_CACHE)
@Immutable
public class AccountTypeRef extends Ref {

	private static final long serialVersionUID = 3416012584227710916L;

	private AccountType id;
	private AccountCategoryRef category;

	@Id
	@Column(name = "ID", length = 20)
	@Enumerated(EnumType.STRING)
	public AccountType getId() {
		return id;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "CATEGORY", nullable = false, updatable = false)
	public AccountCategoryRef getCategory() {
		return category;
	}

	public void setId(AccountType id) {
		this.id = id;
	}

	public void setCategory(AccountCategoryRef category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AccountTypeRef other = (AccountTypeRef) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
