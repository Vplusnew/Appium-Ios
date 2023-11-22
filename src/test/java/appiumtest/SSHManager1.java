package appiumtest;

import java.io.*;
import java.util.Properties;

import com.jcraft.jsch.*;

public class SSHManager1
{
	public static void main(String[] args) {
        
//		
//		String phoneNumber = "$1";
//		// Remove the '+62' prefix
//		phoneNumber = phoneNumber.substring(phoneNumber.indexOf("+62") + 3);
		String name = System.getProperty("user.dir");
		String privateKeyPath = name+"/src/test/java/appiumtest/p-sms-otp-db.pem";
     	String user = "mncnow";
         String host = "10.10.20.20";
         String password = "Welcome.21!--"; 
         String dbUser = "qa_vplus";
         String dbName = "sms_gateway";
         String dbHost = "10.10.128.146";
         String dbPort = "5432";
         String dbPassword="qacredential";
         String SQLQuery = "SELECT * FROM smsotp ORDER BY created_at desc LIMIT 1;"; // Replace with your SQL query
        
       
        try {
        	JSch jsch = new JSch();

            // Load the private key for authentication
            jsch.addIdentity(privateKeyPath);

            // Establish an SSH session
            Session session = jsch.getSession(user, host, 22);
            Properties configi = new Properties ();
            configi.put("StrictHostKeyChecking", "no");
            session.setConfig(configi);
            session.setPassword(password);
            
            
            session.connect();

            // If using password-based authentication
            session.setPassword(password);

            // Avoid asking for key confirmation
          
            
//            String A = "Fatah" +user+ "Alim";
//            System.out.println(A);
            // Execute command
            String command = "/opt/homebrew/Cellar/sshpass/1.06/bin/sshpass -p \"" + password + "\" ssh -i \" " + privateKeyPath + "\" \"" + user + "@" + host + "\" <<-EOF\n"
                    + "  psql -U \"" + dbUser + "\" -d \"" + dbName + "\" -h \"" + dbHost + "\" -p \"" + dbPort + "\" -t -c \"" + SQLQuery + "\" | grep -oP '\\b\\d+\\b'\n"
                    + "EOF";
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            // Get the command's output
            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    System.out.println("Exit status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }

            // Disconnect from the remote server
            channel.disconnect();
            session.disconnect();

        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        
    }
}