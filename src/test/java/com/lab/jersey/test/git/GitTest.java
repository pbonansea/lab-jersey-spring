package com.lab.jersey.test.git;

import java.io.File;
import java.util.Collection;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.SubmoduleInitCommand;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

public class GitTest {

	private String user = "";
	private String pwd = "";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception { }

	@AfterClass
	public static void tearDownAfterClass() throws Exception { }

	@Before
	public void setUp() throws Exception { }

	@After
	public void tearDown() throws Exception { }

	@Ignore
	public void testGitCloneRepository() throws Exception {

		String remoteUrl = "https://github.com/pbonansea/lab-jersey-spring.git";
		// "https://github.com/mitadmin/panasonic-afr-portal.git";
		// "https://github.com/mitadmin/panasonic-ba-portal-en_gb.git";

		CredentialsProvider cred = new UsernamePasswordCredentialsProvider(
				user, pwd);

		Git.cloneRepository()
				.setCredentialsProvider(cred)
				.setURI(remoteUrl)
				.setDirectory(
						new File(
								"/Users/paolobonansea/Documents/repositories/lab-jersey-spring"))
				.call();
	}

	@Ignore
	public void testGitCloneRepositoryWithCommandObject() throws Exception {

		String remoteUrl = "https://github.com/mitadmin/panasonic-ba-portal-en_gb.git";

		CredentialsProvider cred = new UsernamePasswordCredentialsProvider(
				user, pwd);

		CloneCommand clone = Git
				.cloneRepository()
				.setCredentialsProvider(cred)
				.setURI(remoteUrl)
				.setDirectory(
						new File(
								"/Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb"));
		clone.call();

	}

	@Ignore
	public void testGitSubmoduleInit() throws Exception {

		FileRepository fileRepository = new FileRepository(
				"/Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb");

		Git git = new Git(fileRepository);

		SubmoduleInitCommand command = git.submoduleInit();
		// SubmoduleInitCommand command = new
		// SubmoduleInitCommand(fileRepository);

		Collection<String> modules = null;
		modules = command.call();
		for (String module : (Collection<String>) modules) {
			System.out.print("module: " + module);
		}

	}

	@Ignore
	public void testCheckOutBranch() throws Exception {

		Git git = Git.open(new File(
				"/Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb"));

		CheckoutCommand checkout = git.checkout();

		checkout.setName("master");
		// if the branch doesn't exists in local, should set this property true
		// checkout.setCreateBranch(true);
		checkout.call();

	}

	@Ignore
	public void testGitCloneWithProcessBuilder() throws Exception {

		ProcessBuilder procBuilder = new ProcessBuilder(
				"bash",
				"-c",
				"cd /Users/paolobonansea/Documents/repositories/; /usr/bin/git clone https://github.com/mitadmin/panasonic-ba-portal-en_gb.git");
		procBuilder.redirectErrorStream(true);
		Process process = procBuilder.start();
		process.waitFor();
		process.destroy();

	}

	@Ignore
	public void testGitCheckoutWithProcessBuilder() throws Exception {

		ProcessBuilder procBuilder = new ProcessBuilder(
				"bash",
				"-c",
				"cd /Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb; /usr/bin/git checkout develop");
		procBuilder.redirectErrorStream(true);

		Process process = procBuilder.start();

		process.waitFor();

		process.destroy();

	}

}
