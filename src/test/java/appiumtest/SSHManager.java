
package appiumtest;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SSHManager {
    private String jumpHost;
    private String jumpUser;
    private String jumpPassword;
    private Session session;

    public SSHManager(String jumpHost, String jumpUser, String jumpPassword) {
        this.jumpHost = jumpHost;
        this.jumpUser = jumpUser;
        this.jumpPassword = jumpPassword;
    }

    public int openSSHTunnel(String destinationHost, int destinationPort) throws JSchException {
        JSch jsch = new JSch();

        // Create a session
        session = jsch.getSession(jumpUser, jumpHost, 22);
        session.setPassword(jumpPassword);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        // Open the SSH tunnel
        int localPort = session.setPortForwardingL(0, destinationHost, destinationPort);

        System.out.println("SSH anda terhubung dengan port: " + localPort);
        return localPort;
    }

    public void closeSSHTunnel() {
        if (session != null && session.isConnected()) {
            session.disconnect();
            System.out.println("SSH tunnel closed.");
        }
    }
}
