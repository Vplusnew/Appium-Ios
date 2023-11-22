package appiumtest;

import java.io.*;
import com.jcraft.jsch.*;
import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;
import static net.sf.expectit.matcher.Matchers.anyOf;
import static net.sf.expectit.matcher.Matchers.contains;


public class SSHManager2 extends session
{
	public static void main(String[] args) throws JSchException, IOException, InterruptedException 
		 {

			String sshuser = "mncnow";
			String sshhost = "10.10.20.20";
			int sshport = 22;
			String sshpassword = "Welcome.21!--";
			String dbUser = "qa_vplus";
			String dbName = "sms_gateway";
			String dbHost = "10.10.128.146";
			String dbPort = "5432";
			String dbPassword = "qacredential";
			String SQLQuery = "SELECT otp FROM smsotp ORDER BY created_at desc LIMIT 1;"; // Replace with your SQL query

			JSch jsch = new JSch();
		
			Session session = jsch.getSession(sshuser, sshhost, sshport);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(sshpassword);
			session.connect();

			System.out.println("Connected");

			Channel channel = session.openChannel("shell");
			channel.connect();

			Expect expect = new ExpectBuilder().withOutput(channel.getOutputStream())
					.withInputs(channel.getInputStream(), channel.getExtInputStream()).withEchoInput(System.out)
					.withEchoOutput(System.err).withInputFilters(removeColors(), removeNonPrintable()).build();
			System.out.println("Channel Connected to machine ");


	        expect.sendLine("sudo ssh ubuntu@10.10.128.146 -i keypem/p-sms-otp-db.pem" );
	        expect.expect(contains("password"));
	        expect.sendLine("Welcome.21!--");
	        expect.expect(contains("ubuntu"));
	        expect.sendLine("psql -U qa_vplus -d sms_gateway -h 10.10.128.146 -p 5432 -t -c \"" + SQLQuery + "\"");
	        expect.expect(contains("Password"));
	        expect.sendLine("qacredential");
	        
	        Thread.sleep(889);
		 }
}
		
	