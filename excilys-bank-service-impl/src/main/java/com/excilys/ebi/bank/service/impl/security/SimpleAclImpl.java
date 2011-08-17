package com.excilys.ebi.bank.service.impl.security;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.security.acls.model.AccessControlEntry;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.acls.model.UnloadedSidException;

public class SimpleAclImpl implements Acl {

	private static final long serialVersionUID = -328134516312149798L;

	private final ObjectIdentity objectIdentity;
	private final List<AccessControlEntry> entries = newArrayList();

	public SimpleAclImpl(ObjectIdentity objectIdentity) {
		this.objectIdentity = objectIdentity;
	}

	@Override
	public List<AccessControlEntry> getEntries() {
		return entries;
	}

	@Override
	public ObjectIdentity getObjectIdentity() {
		return objectIdentity;
	}

	@Override
	public Sid getOwner() {
		return null;
	}

	@Override
	public Acl getParentAcl() {
		return null;
	}

	@Override
	public boolean isEntriesInheriting() {
		return false;
	}

	@Override
	public boolean isGranted(List<Permission> permission, List<Sid> sids, boolean administrativeMode) throws NotFoundException, UnloadedSidException {

		AccessControlEntry firstRejection = null;

		for (Permission p : permission) {
			for (Sid sid : sids) {
				// Attempt to find exact match for this permission mask and SID
				boolean scanNextSid = true;

				for (AccessControlEntry ace : entries) {

					if ((ace.getPermission().getMask() == p.getMask()) && ace.getSid().equals(sid)) {
						// Found a matching ACE, so its authorization decision
						// will prevail
						if (ace.isGranting()) {
							return true;
						}

						// Failure for this permission, so stop search
						// We will see if they have a different permission
						// (this permission is 100% rejected for this SID)
						if (firstRejection == null) {
							// Store first rejection for auditing reasons
							firstRejection = ace;
						}

						scanNextSid = false; // helps break the loop

						break; // exit aces loop
					}
				}

				if (!scanNextSid) {
					break; // exit SID for loop (now try next permission)
				}
			}
		}

		if (firstRejection != null) {
			// We found an ACE to reject the request at this point, as no
			// other ACEs were found that granted a different permission
			return false;
		}

		// No matches have been found
		throw new NotFoundException("Unable to locate a matching ACE for passed permissions and SIDs");
	}

	@Override
	public boolean isSidLoaded(List<Sid> sids) {
		return true;
	}
}
