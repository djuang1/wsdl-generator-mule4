package com.dejim;

import java.io.*;

public class WSDLGeneratorWrapper {
	
	public WSDLGeneratorWrapper() {
		
	}
	
	public static String generateWSDL(String appHome, String inputFileLocation, String outputFileLocation) {
		StringBuffer response = new StringBuffer();
		String line;
		Process process;

		String toolString = "java -jar " + appHome + "/openapi-generator-cli-6.2.1.jar generate -g wsdl-schema -i " + inputFileLocation + " -o " + outputFileLocation + " --skip-validate-spec";
		//System.out.print(toolString + "\n");

		try {

			process = Runtime.getRuntime().exec(String.format(toolString));
			process.waitFor();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			while ((line = error.readLine()) != null) {
				response.append(line);
			}
		} catch (IOException e1) {
		} catch (InterruptedException e2) {
		}
		return response.toString();
	}
}
