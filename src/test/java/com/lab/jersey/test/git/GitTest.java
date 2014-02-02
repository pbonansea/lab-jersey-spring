package com.lab.jersey.test.git;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.SubmoduleInitCommand;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class GitTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	public void testGitCloneRepository() {

		String REMOTE_URL = "https://github.com/pbonansea/lab-jersey-spring.git";
		// "https://github.com/mitadmin/panasonic-afr-portal.git";
		// "https://github.com/mitadmin/panasonic-ba-portal-en_gb.git";
		
        CredentialsProvider cred = new 
        		UsernamePasswordCredentialsProvider("pbonansea@velti.com", "livorno09");
        
        try {
			Git.cloneRepository()
					.setCredentialsProvider(cred)
			        .setURI(REMOTE_URL)
			        .setDirectory(new 
							File("/Users/paolobonansea/Documents/repositories/lab-jersey-spring"))
			        .call();
		} catch (InvalidRemoteException ex) {
			ex.printStackTrace();
		} catch (TransportException ex) {
			ex.printStackTrace();
		} catch (GitAPIException ex) {
			ex.printStackTrace();
		}
	}
	
	@Ignore
	public void testGitCloneRepositoryCommand() {

		String REMOTE_URL = "https://github.com/mitadmin/panasonic-ba-portal-en_gb.git";
		
		File gitWorkDir = new File("/Users/paolobonansea/Documents/repositories/");
		Git git = null;
		InitCommand initCommand = Git.init();
	    initCommand.setDirectory(gitWorkDir);
	    try {
			git = initCommand.call();
			
			try {
				git = Git.open(gitWorkDir);
				
				CredentialsProvider cred = new UsernamePasswordCredentialsProvider(
						"pbonansea@velti.com", "livorno09");
				
				CloneCommand clone = git.cloneRepository()
						.setCredentialsProvider(cred)
				        .setURI(REMOTE_URL)
				        .setDirectory(new 
								File("/Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb"));
				clone.call();
				
			} catch (IOException ex) {
				ex.printStackTrace();
			}			
		} catch (GitAPIException ex) {
			ex.printStackTrace();
		}	
	}
	
	@Test
	public void testGitSubmodule() {
		
		
		Runtime run = java.lang.Runtime.getRuntime();
		
		FileRepository fileRepository = null;
		try {
			fileRepository = new FileRepository("/Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Git git = new Git(fileRepository);
		
		SubmoduleInitCommand command = git.submoduleInit();
		
//		SubmoduleInitCommand command = new SubmoduleInitCommand(fileRepository);
		Collection<String> modules = null;
		try {
			modules = command.call();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		
		System.out.println(modules);
		
//		try {
//			ProcessBuilder pb1 = new ProcessBuilder(
//					"bash", "-c",
//					"cd /Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb; /usr/bin/git submodule init; /usr/bin/git submodule update"); 
////					+ " /usr/bin/git init");
//			pb1.redirectErrorStream(true);
//			Process p1 = pb1.start();
//						
//			try {
//				p1.waitFor();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			p1.destroy();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
	}
	
	@Ignore
	public void testCheckOutBranch() {
	
		File gitWorkDir = new File("/Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb");
		
		Git git = null;
		InitCommand initCommand = Git.init();
	    initCommand.setDirectory(gitWorkDir);
	    try {
			git = initCommand.call();
			
			git = Git.open(gitWorkDir);
				
			CheckoutCommand checkout = git.checkout();
				
			checkout.setName("develop");
			checkout.setCreateBranch(true);
			checkout.call();
				
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (GitAPIException ex) {
			ex.printStackTrace();
		}		
	}
	
	@Ignore
	public void testGitCloneWithProcessBuilder() {

		try {
			ProcessBuilder pb1 = new ProcessBuilder(
					"bash", "-c",
					"cd /Users/paolobonansea/Documents/repositories/; /usr/bin/git clone https://github.com/mitadmin/panasonic-ba-portal-en_gb.git"); 
			pb1.redirectErrorStream(true);
			Process p1 = pb1.start();
			try {
				p1.waitFor();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			p1.destroy();

		} catch (IOException ex) {
			ex.printStackTrace();
		}	
	}
	
	@Ignore
	public void testGitCheckoutWithProcessBuilder() {
		
		ProcessBuilder pb2 = new ProcessBuilder(
				"bash", "-c",
				"cd /Users/paolobonansea/Documents/repositories/panasonic-ba-portal-en_gb; /usr/bin/git checkout develop"); 
		pb2.redirectErrorStream(true);
		
		Process p2 = null;
		try {
			p2 = pb2.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			p2.waitFor();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		p2.destroy();
	}
	
}
