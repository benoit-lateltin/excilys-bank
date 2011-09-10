package com.excilys.ebi.bank.data.service;

import com.excilys.ebi.bank.data.model.Script;

public interface ScriptExecutorService {

	void execute(Script<?> script) throws Exception;
}
