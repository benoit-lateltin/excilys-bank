package com.excilys.ebi.bank.service.impl.security;

import java.io.Serializable;

import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.util.Assert;

public class SimpleAccessControlEntryImpl implements AccessControlEntry {

	private static final long serialVersionUID = -6381437038368669206L;

	private final Acl acl;
	private Permission permission;
	private final Sid sid;
	private final boolean granting;

	public SimpleAccessControlEntryImpl(Acl acl, Sid sid, Permission permission, boolean granting) {
		Assert.notNull(acl, "Acl required");
		Assert.notNull(sid, "Sid required");
		Assert.notNull(permission, "Permission required");
		this.acl = acl; // can be null
		this.sid = sid;
		this.permission = permission;
		this.granting = granting;
	}

	public boolean equals(Object arg0) {
		if (!(arg0 instanceof SimpleAccessControlEntryImpl)) {
			return false;
		}

		SimpleAccessControlEntryImpl rhs = (SimpleAccessControlEntryImpl) arg0;

		if (this.acl == null) {
			if (rhs.getAcl() != null) {
				return false;
			}
			// Both this.acl and rhs.acl are null and thus equal
		} else {
			// this.acl is non-null
			if (rhs.getAcl() == null) {
				return false;
			}

			// Both this.acl and rhs.acl are non-null, so do a comparison
			if (this.acl.getObjectIdentity() == null) {
				if (rhs.acl.getObjectIdentity() != null) {
					return false;
				}
				// Both this.acl and rhs.acl are null and thus equal
			} else {
				// Both this.acl.objectIdentity and rhs.acl.objectIdentity are
				// non-null
				if (!this.acl.getObjectIdentity().equals(rhs.getAcl().getObjectIdentity())) {
					return false;
				}
			}
		}

		return true;
	}

	public Acl getAcl() {
		return acl;
	}

	@Override
	public Serializable getId() {
		return null;
	}

	public Permission getPermission() {
		return permission;
	}

	public Sid getSid() {
		return sid;
	}

	public boolean isGranting() {
		return granting;
	}

	void setPermission(Permission permission) {
		Assert.notNull(permission, "Permission required");
		this.permission = permission;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("SimpleAccessControlEntryImpl[");
		sb.append("granting: ").append(this.granting).append("; ");
		sb.append("sid: ").append(this.sid).append("; ");
		sb.append("permission: ").append(this.permission);
		sb.append("]");

		return sb.toString();
	}
}
