package com.javatpoint;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class StopDockerContainers {

	public static void main(String[] args) {
		
		
		try{
		    
			  File  file=new File("//home//ci-cd//Desktop//demo//PetClinic.txt");
			     
			  file.renameTo(new File("//home//ci-cd//Desktop//git2//"+file.getName()));
			
			 
	     } catch (Exception e) {
	         e.printStackTrace();
	     }

	}

}
