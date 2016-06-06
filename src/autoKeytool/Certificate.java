package autoKeytool;

import java.io.File;

public class Certificate {

	private String CN, password, keystore, file;
	
	public Certificate(String CN, String password, String keystore) {
		this.CN = CN;
		this.file = CN+".jks";
		this.password = password;
		this.keystore = keystore;
	}
	
	public Certificate(String[] params) {
		this.CN = params[0];
		this.file = CN+".jks";
		this.password = params[1];
		this.keystore = params[2];
	}
	
	public void generate() {
		Keytool.generateKeyPair(CN, password, keystore);
	}
	
	public void export() {
		Keytool.export(CN, file, password, keystore);
	}
	
	public void delete() {
		try {
			File f = new File(file);
			f.delete();
		} catch(Exception e) {}
	}
	
	public void importTo(Certificate cert) {
		export();
		Keytool.importCert(CN, file, cert.password, cert.keystore);
		delete();
	}

	public String getCN() {
		return CN;
	}

	public void setCN(String cN) {
		CN = cN;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeystore() {
		return keystore;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
