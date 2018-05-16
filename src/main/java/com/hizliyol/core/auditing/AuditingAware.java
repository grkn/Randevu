package com.hizliyol.core.auditing;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import com.hizliyol.core.session.SessionBean;

public class AuditingAware implements AuditorAware<String>{

	@Autowired
	SessionBean sessionBean;
	
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(sessionBean.getUserName());
	}

}
