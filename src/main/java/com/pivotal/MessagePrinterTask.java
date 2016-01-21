package com.pivotal;

/**
 * Created by hinlam on 19/1/16.
 */

public class MessagePrinterTask implements Runnable{

        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println(message);
        }
}
