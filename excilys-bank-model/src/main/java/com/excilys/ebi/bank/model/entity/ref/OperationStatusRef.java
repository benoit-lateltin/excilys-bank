package com.excilys.ebi.bank.model.entity.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import com.excilys.ebi.bank.model.IConstants;

@Entity(name = "REF_OPERATION_STATUS")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = IConstants.Cache.ENTITY_CACHE)
@Immutable
public class OperationStatusRef extends Ref {

	private static final long serialVersionUID = 3416012584227710916L;

	private OperationStatus id;

	@Id
	@Column(name = "ID", length = 20)
	@Enumerated(EnumType.STRING)
	public OperationStatus getId() {
		return id;
	}

	public void setId(OperationStatus id) {
		this.id = id;
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
		OperationStatusRef other = (OperationStatusRef) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
