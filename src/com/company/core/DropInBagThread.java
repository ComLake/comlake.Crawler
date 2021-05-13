package com.company.core;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DropInBagThread extends Thread{
    private String link;
    private String path;

    public DropInBagThread(String link,String path) {
        this.link = link;
        this.path = path;
    }

    @Override
    public void run() {
        drop(path);
    }
    public void drop(String path){
        try {
            File file = new File(path);
            if (!file.exists()){
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            URL url = new URL(link);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            double fileSize = (double) urlConnection.getContentLengthLong();
            BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            FileOutputStream fileOS = new FileOutputStream(file);
            BufferedOutputStream outputStream = new BufferedOutputStream(fileOS,1024);
            byte[]buffer = new byte[1024];
            double downloaded = 0.00;
            double percentageDownloaded;
            int read;
            while ((read = inputStream.read(buffer,0,1024))!=-1){
                outputStream.write(buffer,0,read);
                downloaded += read;
                percentageDownloaded = (downloaded*100)/fileSize;
                String percent = String.format("%.2f",percentageDownloaded);
                System.out.println("Downloaded " + percent + "% of the file..");
            }
            outputStream.close();
            inputStream.close();
            System.out.println("Downloaded!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
