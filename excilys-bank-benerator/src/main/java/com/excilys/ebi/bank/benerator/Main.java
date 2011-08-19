package com.excilys.ebi.bank.benerator;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String[] argsArray = { "descriptor.xml" };
		org.databene.benerator.main.Benerator.main(argsArray);
	}

}