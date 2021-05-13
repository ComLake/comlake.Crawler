package com.company;

import com.company.core.ScrapperThread;
import com.company.crawler.WebsiteScrapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> nameThatWebsites = new ArrayList<>();
        System.out.println("     __________\n" +
                " ___/  ______  \\_____\n" +
                "(o____/      \\___))__)");

        System.out.println("------------------------");
        System.out.println("Give me a name");
        String keyword = new Scanner(System.in).nextLine();
        System.out.println("How many website you wanna scrap ? ");
        int n = new Scanner(System.in).nextInt();
        System.out.println("Name of those..");
        for (int i = 0; i < n; i++) {
            String target = new Scanner(System.in).nextLine();
            nameThatWebsites.add(target);
        }
        System.out.println("\n-----------------------------------------");
        for (String websites:nameThatWebsites) {
            ScrapperThread scrapperThread = new ScrapperThread(websites,keyword);
            scrapperThread.run();
        }
    }
}
