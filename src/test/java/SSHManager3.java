
 


import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SSHManager3{
    public static void main(String[] args) {
    	 String privateKeyPath = "./src/test/java/appiumtest/p-sms-otp-db.pem";
      	String user = "mncnow";
          String host = "10.10.20.20";
          String password = "Welcome.21!--"; 
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
            Session session = jsch.getSession(user, host, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            // Execute the SQL query using psql on the remote server
            String command = "\"  psql -U \\\"\" + dbUsername + \"\\\" -d \\\"\" + dbName + \"\\\" -h \\\"\" + dbHost + \"\\\" -p \\\"\" + dbPort + \"\\\" -t -c \\\"\" + SQLQuery + \"\\\" | grep -oP '\\\\b\\\\d+\\\\b'\\n\"\n"
            		+ "                    + \"EOF\";";
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            // Get the output of the command
            InputStream in = channel.getInputStream();
            channel.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Disconnect from the remote server
            channel.disconnect();
            session.disconnect();

        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
    }
}
