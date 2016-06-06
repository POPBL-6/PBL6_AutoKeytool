package autoKeytool;

import java.io.FileInputStream;
import java.util.Scanner;

public class AutoKeytool {
	
	public static final String DEFAULT_FILE = "AutoKeytool.ini";
	public static final String DEFAULT_ISSUER_KEYSTORE = "broker.keystore";

	public static void main(String[] args) throws Exception {
		AutoKeytool a = new AutoKeytool();
		a.start();
	}
	
	public void start() throws Exception {
		Scanner in = new Scanner(new FileInputStream(DEFAULT_FILE));
		Certificate certBroker = new Certificate(in.nextLine().split("[;]"));
		certBroker.generate();
//		while(in.hasNext()) {
//			Certificate certClient = new Certificate(in.nextLine().split("[;]"));
//			certClient.generate();
//			certBroker.importTo(certClient);
//			certClient.importTo(certBroker);
//		}
		in.close();
	}
	
}
