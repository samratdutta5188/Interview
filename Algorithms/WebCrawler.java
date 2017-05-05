package com.algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;

import com.ds.Queue;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
	
	private static Queue<String> queue = new Queue<String>();
	private static Set<String> discovered = new HashSet<String>();

	public static void main(String[] args) throws Exception {
		crawl("http://www.princeton.edu");
	}

	private static void crawl(String root) throws Exception {
		queue.enQueue(root);
		discovered.add(root);
		while(!queue.isEmpty()){
			String v = queue.deQueue();
			System.out.println(v);
			URL url = new URL(v);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
	        while ((inputLine = br.readLine()) != null){
	        	String regex = "http://(\\w+\\.)*(\\w+)";
	        	Pattern pattern = Pattern.compile(regex);
	        	Matcher matcher = pattern.matcher(inputLine);
	            while(matcher.find()){
	            	String w = matcher.group();
	            	if(!discovered.contains(w)){
	            		discovered.add(w);
	            		queue.enQueue(w);
	            	}
	            }
	        }
	        br.close();
		}
	}

}
