package se.chas.projecttemplate.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.jasypt.properties.PropertyValueEncryptionUtils;


public class EncryptDecrypt {
	Logger logger = Logger.getLogger(EncryptDecrypt.class.getName());
	
	private static final String PWD = "SDHLKSHUWEHDKSLWEFC23SDFCEW213KLJKSALJDLKA00IUAY98273492JLKASJDLKASJDKLAJSD";
	private static final String ALGORITHM = "PBEWithMD5AndDES";
	
	//Constructor
	public EncryptDecrypt() {
		
	}
	
	/**
	 * @param propertyToEncrypt
	 * @return
	 * returns the string as an encrypted value
	 */
	public String encryptString(String valueToEncrypt) throws Exception {
		
		StandardPBEStringEncryptor  encryptor = new StandardPBEStringEncryptor ();
		encryptor.setAlgorithm(ALGORITHM);
		encryptor.setPassword(PWD);
		
		String encryptedProperty = "";
		
		//Encrypt the specified value
		encryptedProperty = PropertyValueEncryptionUtils.encrypt(valueToEncrypt, encryptor);
		
		logger.debug(this.getClass().getName() + " Exiting encryptString(String propertyToEncrypt)");
		
		return encryptedProperty;
	}
	
	public String decryptString(String valueToDecrypt) {
		
		StandardPBEStringEncryptor  encryptor = new StandardPBEStringEncryptor ();
		encryptor.setAlgorithm(ALGORITHM);
		encryptor.setPassword(PWD);
		
		String decryptedProperty = "";
		
		try {
			decryptedProperty = PropertyValueEncryptionUtils.decrypt(valueToDecrypt, encryptor);
		} catch (Exception e) {
//			logger.debug(this.getClass().getName() + " EXCEPTION + " + e);
		}
		
		
		return decryptedProperty;
	}
	
	/**
	 * @param fileName
	 * @return a Properties Object that contains encrypted value(s) of type EncryptableProperties
	 */
	public Properties readPropertyFileWithEncryptedValuesViaResource(String fileName) throws IOException, Exception {
		
		//Create the Encryptor
		StandardPBEStringEncryptor  encryptor = new StandardPBEStringEncryptor ();
		encryptor.setAlgorithm(ALGORITHM);
		encryptor.setPassword(PWD);
		
		Properties properties = new EncryptableProperties(encryptor);
			
		//Try to load create and load the input stream
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("AppConfigPropertyFiles" + File.separator + fileName);
		properties.load(inStream);
		
		inStream.close();
		
		
		return properties;
		
	}
	
	/**
	 * @param pathToFile (without trailing slash)
	 * @param fileName
	 * @return a Properties Object that contains encrypted value(s) of type EncryptableProperties
	 */
	public Properties readPropertyFileWithEncryptedValuesViaSpecificPath(String pathToFile, String fileName) 
		throws IOException, Exception { 
		
		//Create the Encryptor
		StandardPBEStringEncryptor  encryptor = new StandardPBEStringEncryptor ();
		encryptor.setAlgorithm(ALGORITHM);
		encryptor.setPassword(PWD);
		
		Properties properties = new EncryptableProperties(encryptor);
		
		InputStream inStream = null;
		
		//Try to load create and load the input stream
		if (pathToFile.equals("")) {
			inStream = new FileInputStream(fileName);
		} else {
			inStream = new FileInputStream(pathToFile + File.separator + fileName);
		}
		properties.load(inStream);
		
		inStream.close();
		
		return properties;
		
	}
}
