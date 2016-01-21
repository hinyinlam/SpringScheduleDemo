package com.pivotal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hinlam on 20/1/16.
 */
public class ExternalCommandTask implements Runnable{

    List<String> command=new ArrayList<String>(); //be aware of passing unsafe command, in this demo I don't check safety.

    @Override
    public void run() {
        try {
            System.out.println("Running external command: " + this.command.toString());
            ProcessBuilder pb = new ProcessBuilder(this.command);
            pb.inheritIO(); // make sure the input/output/error pipe are same as this one.

            Map<String, String> process_env = pb.environment(); //unused, but you can setup environment such as $PATH

            Process externalProcess = pb.start();

            /*InputStream inputStream = externalProcess.getInputStream(); //no use now, please elaborate use case.
            OutputStream outputStream = externalProcess.getOutputStream();
            InputStream errorStrem = externalProcess.getErrorStream();
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ExternalCommandTask(List<String> command){
        this.command = command;
    }
}
