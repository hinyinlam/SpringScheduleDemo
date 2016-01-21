package com.pivotal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

@SpringBootApplication
public class SpringTaskSchedulerDemoApplication implements CommandLineRunner{

	@Autowired
	public TaskScheduler taskScheduler;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(SpringTaskSchedulerDemoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		String payloadName = "ThisIsAPlainJavaMainProgram.jar";
		String payloadTarget = System.getProperty("user.home");
		System.out.println("User home is:" + payloadTarget);

		Resource payloadResource = context.getResource("classpath:"+payloadName);
		FileInputStream payloadStream = new FileInputStream(payloadResource.getFile());
		File outfile = new File(System.getProperty("user.home") + System.getProperty("file.separator") + payloadName);
		FileOutputStream payloadOutStream = new FileOutputStream(outfile);
		byte[] fileByte = new byte[1];
		while(payloadStream.read(fileByte)!=-1){
			payloadOutStream.write(fileByte);
		}
		payloadOutStream.close();
		payloadStream.close();

		ArrayList<String> command = new ArrayList<>();
		command.add(System.getProperty("java.home") + "/bin/java");
		command.add("-jar");
		command.add(System.getProperty("user.home") + System.getProperty("file.separator") + payloadName );
		command.add("10");
		taskScheduler.schedule(new ExternalCommandTask(command), new CronTrigger("0 * * * * *"));
	}

	@RequestMapping("/")
	public String getDefaultHome(){
		return "This is the default home";
	}
}
