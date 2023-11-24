
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class TryGetfromdb{
    public static void main(String[] args) {
    	 String privateKeyPath = "./src/test/java/appiumtest/p-sms-otp-db.pem";
      	String sshuser = "mncnow";
          String sshhost = "10.10.20.20";
          String sshpassword = "Welcome.21!--"; 
          int sshport = 22;
          String dbUser = "qa_vplus";
          String dbName = "sms_gateway";
          String dbHost = "10.10.128.146";
          String dbPort = "5432";
          String dbPassword="qacredential";
          String SQLQuery = "SELECT * FROM smsotp ORDER BY created_at desc LIMIT 11;"; // Replace with your SQL query
        

        try {
            JSch jsch = new JSch();

            // Load the private key for authentication
            jsch.addIdentity(privateKeyPath);

            // Establish an SSH session
            Session session = jsch.getSession(sshuser, sshhost, sshport);
    		Properties config = new Properties();
    		config.put("StrictHostKeyChecking", "no");
    		session.setConfig(config);
    		session.setPassword(sshpassword);
    		session.connect();
    		
    		System.out.println("Connected");
            // Execute the SQL query using psql on the remote server
            String command = "psql -U \"" + dbUser + "\" -d \"" + dbName + "\" -h \"" + dbHost + "\" -p \"" + dbPort + "\" -t -c \"" + SQLQuery + "\" | grep -oP '\\b\\d+\\b'\nEOF";

   ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            // Get the output of the command
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
