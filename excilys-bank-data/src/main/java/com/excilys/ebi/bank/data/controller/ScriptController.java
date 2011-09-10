package com.excilys.ebi.bank.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.ebi.bank.data.model.Script;
import com.excilys.ebi.bank.data.service.ScriptExecutorService;

@Controller
@RequestMapping("/scripts.html")
public class ScriptController {

	@Autowired
	private ScriptExecutorService scriptExecutorService;

	@Autowired
	private List<Script<?>> scripts;

	@ModelAttribute("scripts")
	public List<Script<?>> getScripts() {
		return scripts;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void display(ModelMap model) {

	}

	@RequestMapping(method = RequestMethod.POST)
	public void execute(@RequestParam("selectedScript") int selectedScript, ModelMap model) throws Exception {

		Script<?> script = scripts.get(selectedScript);
		scriptExecutorService.execute(script);
		model.put("selectedScript", selectedScript);
	}
}
