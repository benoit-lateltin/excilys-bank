package com.excilys.ebi.bank.data.generator;

import java.io.IOException;

import org.databene.benerator.main.Benerator;
import org.databene.commons.LogCategories;
import org.databene.commons.log.LoggingInfoPrinter;

public class Generator {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		Benerator.runFile("benerator/descriptor.xml", new LoggingInfoPrinter(LogCategories.CONFIG));
	}
}