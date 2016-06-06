package autoKeytool;

public class Keytool {
	
	public static void generateKeyPair(String name, String password, String keystore){
		String command = " -genkeypair "+
	                     " -alias "+name+" "+
	                     " -keyalg RSA "+
	                     " -sigalg SHA256withRSA "+
	                     " -validity 720 "+
	                     " -dname CN="+name+" "+
	                     " -storetype JKS "+
	                     " -keypass "+password+" "+
	                     " -keystore "+keystore+" "+
	                     " -storepass "+password;
		execute(command);
	}
	
	public static void export(String name, String file, String password, String keystore){
		String command = " -exportcert "+
	                     " -alias "+name+" "+
	                     " -file "+file+" "+
	                     " -keypass "+password+" "+
	                     " -keystore "+keystore+" "+
	                     " -storepass "+password;
		execute(command);
	}
	
	public static void importCert(String name, String file, String password, String keystore){
		String command = " -importcert "+
	                     " -alias "+name+" "+
	                     " -noprompt "+
	                     " -file "+file+" "+
	                     " -keypass "+password+" "+
	                     " -keystore "+keystore+" "+
	                     " -storepass "+password;
		execute(command);
	}

	///////////////////////////////////////////////////////////////////////////////////////
	
	public static void execute(String command){
		try{
			sun.security.tools.keytool.Main.main(parse(command));
		} catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	private static String[] parse(String command){
		String[] options = command.trim().split("\\s+");
		return options;
	}
	
}
