package com.excilys.ebi.bank.service.impl.security;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;

import com.excilys.ebi.bank.model.IConstants;
import com.excilys.ebi.bank.model.entity.ref.Role;
import com.excilys.ebi.bank.service.BankService;
import com.excilys.ebi.utils.spring.log.slf4j.InjectLogger;
import com.googlecode.ehcache.annotations.Cacheable;

public class BankAclService implements AclService {

	@InjectLogger
	private Logger logger;

	@Autowired
	private BankService bankService;

	@Override
	public List<ObjectIdentity> findChildren(ObjectIdentity parentIdentity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Acl readAclById(ObjectIdentity object) throws NotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	@Cacheable(cacheName = IConstants.Cache.ACL_CACHE)
	public Acl readAclById(ObjectIdentity object, List<Sid> sids) throws NotFoundException {

		SimpleAclImpl acl = new SimpleAclImpl(object);

		logger.info("type={} id={}", object.getType(), object.getIdentifier());

		for (Sid sid : sids) {
			if (sid instanceof GrantedAuthoritySid && GrantedAuthoritySid.class.cast(sid).getGrantedAuthority().equals(Role.ROLE_ADMIN.name())) {
				acl.getEntries().add(new SimpleAccessControlEntryImpl(acl, sid, BasePermission.READ, true));
				acl.getEntries().add(new SimpleAccessControlEntryImpl(acl, sid, BasePermission.WRITE, true));
				acl.getEntries().add(new SimpleAccessControlEntryImpl(acl, sid, BasePermission.ADMINISTRATION, true));

			} else if (sid instanceof PrincipalSid) {
				Integer accountId = Integer.class.cast(object.getIdentifier());
				String login = ((PrincipalSid) sid).getPrincipal();
				if (bankService.isClientOfAccountByAccountIdAndUserLogin(accountId, login)) {
					acl.getEntries().add(new SimpleAccessControlEntryImpl(acl, sid, BasePermission.READ, true));
					acl.getEntries().add(new SimpleAccessControlEntryImpl(acl, sid, BasePermission.WRITE, true));
				}
			}
		}

		return acl;
	}

	@Override
	public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> objects) throws NotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> objects, List<Sid> sids) throws NotFoundException {
		throw new UnsupportedOperationException();
	}

	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}

}
