package se.chas.projecttemplate.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5Encryptor {
	
	public String MD5Encrypt(String unDigestedString) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		byte[] bytesOfUnDigestedString = unDigestedString.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytesOfDigestedString = md.digest(bytesOfUnDigestedString);
		String digestedString = new String(Hex.encodeHex(bytesOfDigestedString));
		return digestedString;
	}

}
