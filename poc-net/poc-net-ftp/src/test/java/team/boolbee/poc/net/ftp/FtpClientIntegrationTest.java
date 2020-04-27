package team.boolbee.poc.net.ftp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockftpserver.fake.FakeFtpServer;
import org.mockftpserver.fake.UserAccount;
import org.mockftpserver.fake.filesystem.DirectoryEntry;
import org.mockftpserver.fake.filesystem.FileEntry;
import org.mockftpserver.fake.filesystem.FileSystem;
import org.mockftpserver.fake.filesystem.UnixFakeFileSystem;

public class FtpClientIntegrationTest {
	
    private FakeFtpServer fakeFtpServer;    
    private FtpClientHelper ftpClientHelper;
    
    @Before
    public void setup() throws IOException {
    	fakeFtpServer = new FakeFtpServer();
    	fakeFtpServer.addUserAccount(new UserAccount("user", "password", "/data"));
    	
    	FileSystem fileSystem = new UnixFakeFileSystem();
    	fileSystem.add(new DirectoryEntry("/data"));
    	fileSystem.add(new FileEntry("/data/foobar.txt", "abcdef 1234567890"));
    	fakeFtpServer.setFileSystem(fileSystem);
    	fakeFtpServer.setServerControlPort(0);
    	
    	fakeFtpServer.start();
    	
    	ftpClientHelper = new FtpClientHelper("localhost", fakeFtpServer.getServerControlPort(), "user", "password");
		ftpClientHelper.open();    	
    }
    
    @After
    public void teardown() throws IOException {
    	ftpClientHelper.close();
    	fakeFtpServer.stop();
    }
    
    @Test
    public void givenRemoteFile_whenListingRemoteFiles_thenItIsContainedInList() throws IOException {
    	Collection<String> files = ftpClientHelper.listFiles("");
		assertThat(files).contains("foobar.txt");
    }
    
    @Test
    public void givenRemoteFile_whenDownloading_thenItIsOnTheLocalFilesystem2() throws IOException {
    	ftpClientHelper.downloadFile("foobar.txt", "copy_of_foobar.txt");
    	File file = new File("copy_of_foobar.txt");
    	assertThat(file.exists()).isTrue();    	
    	file.deleteOnExit(); // clean up
    }
    
    @Test
    public void givenLocalFile_whenUploadingIt_thenItExistsOnRemoteLocation() 
      throws URISyntaxException, IOException {
    	File file = new File(getClass().getClassLoader().getResource("baz.txt").toURI());
    	ftpClientHelper.putFileToPath(file, "/buz.txt");
    	assertThat(fakeFtpServer.getFileSystem().exists("/buz.txt")).isTrue();
    }
}
