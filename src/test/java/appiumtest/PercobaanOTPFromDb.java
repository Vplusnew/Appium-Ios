package appiumtest;

import static net.sf.expectit.filter.Filters.removeColors;
import static net.sf.expectit.filter.Filters.removeNonPrintable;
import static net.sf.expectit.matcher.Matchers.contains;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sf.expectit.Expect;
import net.sf.expectit.ExpectBuilder;

public class PercobaanOTPFromDb {

	@Test
	public String OTP(String otp1) throws JSchException, IOException, InterruptedException {
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

		expect.sendLine("sudo ssh ubuntu@10.10.128.146 -i keypem/p-sms-otp-db.pem");
		expect.expect(contains("password"));
		expect.sendLine("Welcome.21!--");
		expect.expect(contains("ubuntu"));
		expect.sendLine("psql -U qa_vplus -d sms_gateway -h 10.10.128.146 -p 5432 -t -c \"" + SQLQuery + "\"");
		expect.expect(contains("Password"));
		expect.sendLine("qacredential");
		String sqlQueryResult = expect.expect(contains("ubuntu")).getBefore();

		// Do something with the captured SQL query result

		// Concatenate with the prefix
		String sqlQueryResult1 = "for user qa_vplus: \n" + sqlQueryResult;

		// Use regular expression to extract numeric value
		String otpRegex = "\\b\\d+\\b";
		Pattern pattern = Pattern.compile(otpRegex);
		java.util.regex.Matcher matcher = pattern.matcher(sqlQueryResult1);

		// Find the first match (assuming there is only one OTP)
		if (matcher.find()) {
			String otp = matcher.group();
			System.out.println(otp);
			Thread.sleep(889); // This sleep might not be necessary, depending on your use case.
			return otp; // Return the extracted OTP value
		} else {
			System.out.println("No OTP found in the SQL query result.");
			return null; // or throw an exception or handle it accordingly
		}
	}
}