package team.boolbee.poc.net.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClientHelper {

	private String server;
    private int port;
    private String username;
    private String password;
    private FTPClient ftp;
	
    FtpClientHelper(String server, int port, String username, String password) {
    	this.server = server;
    	this.port = port;
    	this.username = username;
    	this.password = password;
    }
    
	void open() throws IOException {
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintStream(System.out)));		
		ftp.connect(server, port);
		int replyCode = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			ftp.disconnect();
			throw new IOException("Exception in connecting to FTP Server");
		}
		
		ftp.login(username, password);
	}
	
	Collection<String> listFiles(String path) throws IOException {
		FTPFile[] files= ftp.listFiles(path);
		return Arrays.stream(files)
				.map(FTPFile::getName)
				.collect(Collectors.toList());		
	}
	
	void downloadFile(String source, String destination) throws IOException {
		ftp.retrieveFile(source, new FileOutputStream(destination));
	}
	
	void putFileToPath(File file, String path) throws IOException {
		ftp.storeFile(path, new FileInputStream(file));
	}
	
	void close() throws IOException {
		ftp.disconnect();
	}
	
}
