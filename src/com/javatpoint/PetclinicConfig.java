package com.javatpoint;
import java.util.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
public class PetclinicConfig {
    private static Map<String,String> confg=new HashMap<String,String>();
	public static void main(String[] args) {
		
		String fileName="//home//ci-cd//Desktop/Umut//PetConfiguration//configs//default.config";	
		try{
			FileReader file=new FileReader(fileName);
        	BufferedReader br=new BufferedReader(file);   
       	    String line;
        	 while((line=br.readLine())!=null){
        	   line=line.replace("\"","");
        	   line=line.replaceAll("\\s+$","");
        	    if("Admin".equals(line)){
        	      	confg.put("AdminServer","admin-server");
       	            
        	    }
        	    else if("INFO".equals(line)){
					confg.put("Logging","INFO");
        	    	
        	    }
        	    else if("DEBUG".equals(line)){
        	        confg.put("Logging","DEBUG");
        	    }
        	    else if("TRACE".equals(line)){
        	    	confg.put("Logging","TRACE");       	            	    	
       	       }
        	    else if("none".equals(line)){
        	    	confg.put("Dll-auto","none");
        	    
        	    }
        	   else if("create-drop".equals(line)){        	    	
         	      confg.put("Dll-auto","create-drop");
      	    	
        	    }
       	       else if("Mysql".equals(line)){
        	    	confg.put("Dbms","mysql");
        	    
        	    }
        	    else if("HSQLDB".equals(line)){
        	   
        	    	 confg.put("Dbms","hsqldb");
        	    }
        	    else if("Spring".equals(line)){
        	    	
        	    	confg.put("Banner","Spring");
       	        }
        	    else if("PetClinic".equals(line)){
        	    	confg.put("Banner","PetClinic");
        	    }
        	 }
       	     br.close();
        	 file.close();
        	 makeConfigurations();
        	 
        	
        	 
         }
         catch(IOException e){
       	   e.printStackTrace();        	 
         }
		
		
	
	}
	
	//the below code fragment is used to configure some parts of xml files in the project
	// or add the banner file into console
	//and run docker containers.
	public static void makeConfigurations(){
		  configureServers();
		  configureBanner();
          configureDbms();
		  pushConfig();
		  pushApplication();
		  dockerLoadImages();
		  configureDockerFile();
		  runDockerComposeFile();
		
	}
	
	public static void pushConfig(){
		//Spring-petclinic-config repo is pushed by using cmd commands.
		
		 try{
			 //Spring-Petclinic repository is pushed.
			 String[] cmd = new String[3];
			 cmd[0]="/bin/sh";
			 cmd[1]="-c";
			 cmd[2]="cd /home/ci-cd/git/spring-petclinic-microservices-config; ls -l";
			 cmd[2]+="  && git init  && git add . &&  git commit -m 'first commit'  ";
			 cmd[2]+=" &&  git remote set-url origin https://umutozturk95:7859UC.n7465@github.com/umutozturk95/spring-petclinic-microservices-config.git ";
			 cmd[2]+=" && git remote -v &&  git push origin master --force ";
			  
			 Process p = Runtime.getRuntime().exec(cmd);
              
	         BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	         String line = reader.readLine();
	         while (line != null) {
	             System.out.println(line);
	             line = reader.readLine();
	         }
	         BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	         BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	         String Error;
	         while ((Error = stdError.readLine()) != null) {
	             System.out.println(Error);
	         }
	         while ((Error = stdInput.readLine()) != null) {
	             System.out.println(Error);
	         }
			 
			
			 
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		
		
		
		
	}
	public static void pushApplication(){
		//Also JGit library is used for this purpose.
		 try{
			 
			 //Spring-Petclinic repository is pushed.
			 String[] cmd = new String[3];
			 cmd[0]="/bin/sh";
			 cmd[1]="-c";
			 cmd[2]="cd /home/ci-cd/git/Spring-Petclinic; ls -l";
			 cmd[2]+="  && git init  && git add . &&  git commit -m 'first commit'  ";
			 cmd[2]+=" &&  git remote set-url origin https://umutozturk95:7859UC.n7465@github.com/umutozturk95/Spring-Petclinic.git ";
			 cmd[2]+=" && git remote -v &&  git push origin master --force ";
			  
			 Process p = Runtime.getRuntime().exec(cmd);
              
	         BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	         String line = reader.readLine();
	         while (line != null) {
	             System.out.println(line);
	             line = reader.readLine();
	         }
	         BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	         BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	         String Error;
	         while ((Error = stdError.readLine()) != null) {
	             System.out.println(Error);
	         }
	         while ((Error = stdInput.readLine()) != null) {
	             System.out.println(Error);
	         }
			 
			
			 
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
			
		
		
	}
	//Docker files are executed by using docker-compose up command. 
	public static void dockerLoadImages(){
      
		try{
			
		   String[]cmd=new String[3];
		   cmd[0]="/bin/sh";
		   cmd[1]="-c";
		   cmd[2]="cd /home/ci-cd/git/Spring-Petclinic; ls -l ";
		   cmd[2]+=" &&  mvn clean install -PbuildDocker";
		   
		   Process p=Runtime.getRuntime().exec(cmd);
		   
		   BufferedReader reader =new BufferedReader(new InputStreamReader(p.getInputStream()));
		   String line=reader.readLine();
		   
		   while(line!=null){
			   System.out.println(line);
			   line=reader.readLine();
		   }
		   
			BufferedReader stdInput=new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError=new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String Error;
			
			while((Error=stdError.readLine())!=null){
				System.out.println(Error);
			}
			
			while((Error=stdInput.readLine())!=null){
				System.out.println(Error);
			}
			
			
		}catch(Exception e){
			
		 e.printStackTrace();
		
		}
	}
	public static void runDockerComposeFile(){
		
		
		try{
			  String[]cmd=new String[3];
			   cmd[0]="/bin/sh";
			   cmd[1]="-c";
			   cmd[2]="cd /home/ci-cd/git/Spring-Petclinic; ls -l ";
			   cmd[2]+=" && docker-compose up ";
			   
			   Process p=Runtime.getRuntime().exec(cmd);
			   
			   BufferedReader reader =new BufferedReader(new InputStreamReader(p.getInputStream()));
			   String line=reader.readLine();
			   
			   while(line!=null){
				   System.out.println(line);
				   line=reader.readLine();
			   }
			   
				BufferedReader stdInput=new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader stdError=new BufferedReader(new InputStreamReader(p.getErrorStream()));
				String Error;
				
				while((Error=stdError.readLine())!=null){
					System.out.println(Error);
				}
				
				while((Error=stdInput.readLine())!=null){
					System.out.println(Error);
				}
				
			
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void configureBanner(){
		
		String banner=(String)confg.get("Banner");
		
		if(banner.equals("PetClinic")){
			 
			try{
			    File  file=new File("//home//ci-cd//Desktop//demo//banner.txt");		         
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-admin-server//src//main//resources//"+file.getName()));
			    File file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-admin-server//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			 
			    
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-api-gateway//src//main//resources//"+file.getName()));
			    file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-api-gateway//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			    
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-config-server//src//main//resources//"+file.getName()));
			    file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-config-server//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			    
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-customers-service//src//main//resources//"+file.getName()));
			    file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-customers-service//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			    
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-discovery-server//src//main//resources//"+file.getName()));
			    file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-discovery-server//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			    
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-tracing-server//src//main//resources//"+file.getName()));
			    file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-tracing-server//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			    
			    
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-vets-service//src//main//resources//"+file.getName()));
			    file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-vets-service//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			    
//			    file.renameTo(new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-visits-service//src//main//resources//"+file.getName()));
			    file2=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-visits-service//src//main//resources//"+file.getName());
			    Files.copy(file.toPath(),file2.toPath(),StandardCopyOption.REPLACE_EXISTING);
			    
			    
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		else if(banner.equals("Spring")){
		   try{
			 File file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-admin-server//src//main//resources//banner.txt");
			// file.renameTo(new File("//home//ci-cd//Desktop//demo//"+file.getName()));
			 
		     file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-api-gateway//src//main//resources//banner.txt");
		     file.delete();
		     file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-config-server//src//main//resources//banner.txt");
		     file.delete();
		     file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-customers-service//src//main//resources//banner.txt");
		     file.delete();
		     file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-discovery-server//src//main//resources//banner.txt");
		     file.delete();
		     file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-tracing-server//src//main//resources//banner.txt");
		     file.delete();
		     file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-vets-service//src//main//resources//banner.txt");
		     file.delete();
		     file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-visits-service//src//main//resources//banner.txt");
		     file.delete();
		     
		     
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
		}
		
		
	}
	
	public static void configureDbms(){
		//-------------Mysql or Hsqldb for applican.yaml file in config repo.
		
	   String cloneApplicationFileName="//home//ci-cd//git//spring-petclinic-microservices-config//application.yml";
	   List<String> newLines=new ArrayList<String>();
	   
	   try{
		  String dbms=(String)confg.get("Dbms");
		  int controlMysql=0;
		  if(dbms.equals("mysql")){
			  
			  for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database:")){
					   String[] splitted=line.split("\\s+");
					   if(splitted[1].equals("mysql")){
						   controlMysql=1;
						   break;
					   }
					   else{
						   
						   newLines.add(line.replace(splitted[1],dbms));   
					       newLines.add("spring.datasource.url: jdbc:mysql://localhost:3306/?user=root&password=123&useSSL=true");
						   newLines.add("spring.jpa.database-platform: org.hibernate.dialect.MySQLDialect");
						   newLines.add("spring.jpa.database: MYSQL ");
					   }
					    
				  }
				  else{ 
					  newLines.add(line);
				  }			  
			  }
			  
			 
			 if(controlMysql==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 }
			  newLines.clear();
			  controlMysql=0;
			  
			  //Aplication.properties in customer service is updated
			  cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-customers-service//src//main//resources//application.properties";
			  
			   for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database=")){
					   String[] splitted=line.split("\\=");
					   if(splitted[1].equals("mysql")){
						   controlMysql=1;
						   break;
					   }
					   else{
						   
						   newLines.add(line.replace(splitted[1],dbms));   
					       newLines.add("spring.datasource.url= jdbc:mysql://localhost:3306/?user=root&password=123&useSSL=true");
						   newLines.add("spring.jpa.database-platform= org.hibernate.dialect.MySQLDialect");
						   newLines.add("spring.jpa.database= MYSQL");
					   }
					    
				  }
				  else{ 
					  newLines.add(line);
				  }			  
			  }
			  
			   if(controlMysql==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 }
			  newLines.clear();
			  controlMysql=0;
			  
			  
			   //Aplication.properties in visits service is updated
			  cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-visits-service//src//main//resources//application.properties";
			  
			   for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database=")){
					   String[] splitted=line.split("\\=");
					   if(splitted[1].equals("mysql")){
						   controlMysql=1;
						   break;
					   }
					   else{
						   
						   newLines.add(line.replace(splitted[1],dbms));   
					       newLines.add("spring.datasource.url= jdbc:mysql://localhost:3306/?user=root&password=123&useSSL=true");
						   newLines.add("spring.jpa.database-platform= org.hibernate.dialect.MySQLDialect");
						   newLines.add("spring.jpa.database= MYSQL");
					   }
					    
				  }
				  else{ 
					  newLines.add(line);
				  }			  
			  }
			  
			   if(controlMysql==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 }
			  newLines.clear();
			  controlMysql=0;
			  
			  
			   //Aplication.properties in vets service is updated
			  cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-vets-service//src//main//resources//application.properties";
			  
			   for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database=")){
					   String[] splitted=line.split("\\=");
					   if(splitted[1].equals("mysql")){
						   controlMysql=1;
						   break;
					   }
					   else{
						   
						   newLines.add(line.replace(splitted[1],dbms));   
					       newLines.add("spring.datasource.url= jdbc:mysql://localhost:3306/?user=root&password=123&useSSL=true");
						   newLines.add("spring.jpa.database-platform= org.hibernate.dialect.MySQLDialect");
						   newLines.add("spring.jpa.database= MYSQL");
					   }
					    
				  }
				  else{ 
					  newLines.add(line);
				  }			  
			  }
			  
			   if(controlMysql==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 }
			  newLines.clear();
			  controlMysql=0;
			  
			  
			  
              			 
		  }
		  else if(dbms.equals("hsqldb")){
		     
			   int controlHsqldb=0;
			   for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database:")){
					   String[] splitted=line.split("\\s+");
					   if(splitted[1].equals("hsqldb")){
						   controlHsqldb=1;
						  
					   }
					  
					     newLines.add(line.replace(splitted[1],"hsqldb"));
				  }
				  else{ 
				      
					  
				    if((line.contains("spring.datasource.url:")==false)&&(line.contains("spring.jpa.database-platform:")==false)&&(line.contains("spring.jpa.database:")==false)&&(line.contains("spring.datasource.driver-class-name:")==false)){
//					       System.out.println("-->"+line);
						  newLines.add(line);
					}
				  
					 
				  }			  
			  }
			  
			 
			 //if(controlHsqldb==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 //}
			  newLines.clear();
			  controlHsqldb=0; 
			 //aplication.properties in customers service is updated.
			 cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-customers-service//src//main//resources//application.properties";
			 
			   for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database=")){
					   String[] splitted=line.split("\\=");
					   if(splitted[1].equals("hsqldb")){
						   controlHsqldb=1;
						   break;
					   }
					    newLines.add(line.replace(splitted[1],"hsqldb"));
					    
				  }
				  else{ 
				  
				    if((line.contains("spring.datasource.url=")==false)&&(line.contains("spring.jpa.database-platform=")==false)&&(line.contains("spring.jpa.database=")==false)&&(line.contains("spring.datasource.driver-class-name=")==false)){
					  	
						  newLines.add(line);
					}
					 
				  }			  
			  }
			  
			 
			 if(controlHsqldb==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 }
			  newLines.clear();
			  controlHsqldb=0;
			  //application.properties in vets  service is updated
			  cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-vets-service//src//main//resources//application.properties";
			  
			   for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database=")){
					   String[] splitted=line.split("\\=");
					   if(splitted[1].equals("hsqldb")){
						   controlHsqldb=1;
						   break;
					   }
					   newLines.add(line.replace(splitted[1],"hsqldb"));
					    
				  }
				  else{ 
				  
					  if((line.contains("spring.datasource.url=")==false)&&(line.contains("spring.jpa.database-platform=")==false)&&(line.contains("spring.jpa.database=")==false)&&(line.contains("spring.datasource.driver-class-name=")==false)){
					  	
						  newLines.add(line);
					}
					 
				  }			  
			  }
			  
			 
			 if(controlHsqldb==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 }
			  newLines.clear();
			  controlHsqldb=0;
			  //aplication.properties in visits service is updated.
			  cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-visits-service//src//main//resources//application.properties";
			  			  
			   for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
				  if(line.contains("petclinic.database=")){
					   String[] splitted=line.split("\\=");
					   if(splitted[1].equals("hsqldb")){
						   controlHsqldb=1;
						   break;
					   }
					   newLines.add(line.replace(splitted[1],"hsqldb"));
					    
				  }
				  else{ 
				  
					  if((line.contains("spring.datasource.url=")==false)&&(line.contains("spring.jpa.database-platform=")==false)&&(line.contains("spring.jpa.database=")==false)&&(line.contains("spring.datasource.driver-class-name=")==false)){					  	
						  newLines.add(line);
					}
					 
				  }			  
			  }
			  
			 
			 if(controlHsqldb==0){
				   Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);				
			 }
			  newLines.clear();
			 
			 
			 
		  }
		  
		 cloneApplicationFileName="//home//ci-cd//git//spring-petclinic-microservices-config//application.yml";
		 String dll_auto=(String)confg.get("Dll-auto");  
		 String logging=(String)confg.get("Logging");
         for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
        	 if(line.contains("spring.jpa.hibernate.ddl-auto:")){
        		
        		 String[] splitted=line.split("\\s+");
        		 newLines.add(line.replace(splitted[1],dll_auto));
        		 
        	 }
			 else if(line.contains("logging.level.org.springframework:")){
				 String[]splitted=line.split("\\s+");
				 newLines.add(line.replace(splitted[1],logging));
				 
			 }
        	 else{
        		 
        		 newLines.add(line);
        	 }
        	 
         }
         
         Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);
         newLines.clear();
         
		 //application.properties in customer service is updated
		 
		 cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-customers-service//src//main//resources//application.properties";
		 
		 for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
        	 if(line.contains("logging.level.org.springframework=")){
        		 String[] splitted=line.split("\\=");
        		 newLines.add(line.replace(splitted[1],logging));
        	 }
		
        	 else{
        		 
        		 newLines.add(line);
        	 }
        	 
         }
         
         Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);
         newLines.clear();
		 
		 
		   
		 //application.properties in visits service is updated
		 
		 cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-visits-service//src//main//resources//application.properties";
		 
		 for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
        	 if(line.contains("logging.level.org.springframework=")){
        		 String[] splitted=line.split("\\=");
        		 newLines.add(line.replace(splitted[1],logging));
        	 }
		
        	 else{
        		 
        		 newLines.add(line);
        	 }
        	 
         }
         
         Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);
         newLines.clear();
		 
		 
		 
		 
		 	   
		 //application.properties in vets service is updated
		 
		 cloneApplicationFileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-vets-service//src//main//resources//application.properties";
		 
		 for(String line:Files.readAllLines(Paths.get(cloneApplicationFileName),StandardCharsets.UTF_8)){
        	 if(line.contains("logging.level.org.springframework=")){
        		 String[] splitted=line.split("\\=");
        		 newLines.add(line.replace(splitted[1],logging));
        	 }
		
        	 else{
        		 
        		 newLines.add(line);
        	 }
        	 
         }
         
         Files.write(Paths.get(cloneApplicationFileName),newLines,StandardCharsets.UTF_8);
         newLines.clear();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
 //the above code fragment changes the port of servers.
	public static void configureDockerFile(){
		String fileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-admin-server//target//docker//Dockerfile";
	    List<String> newLines=new ArrayList<String>();		
		
	    try{
		   for(String line : Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8)){
		    	if(line.contains("EXPOSE")){
		    		  
		    		newLines.add(line.replace("9090","9091"));  
		    	}
		    	else{
		    		
		    		newLines.add(line);
		    	}
		    	  
		   }
		   
            Files.write(Paths.get(fileName),newLines,StandardCharsets.UTF_8);	
            newLines.clear();
            
            //---------------------
            fileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-api-gateway//target//docker//Dockerfile";
            newLines=new ArrayList<String>();
            
            for(String line:Files.readAllLines(Paths.get(fileName),StandardCharsets.UTF_8)){
            	if(line.contains("EXPOSE")){
            		 newLines.add(line.replace("8081","8089"));
            	}
            	else{
            		newLines.add(line);
            		
            	}
            	
            }
            
            Files.write(Paths.get(fileName),newLines,StandardCharsets.UTF_8);
            newLines.clear();
            
            //----------------------
            
           fileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-tracing-server//target//docker//Dockerfile";
           newLines=new ArrayList<String>();
           
           for(String line:Files.readAllLines(Paths.get(fileName),StandardCharsets.UTF_8)){
        	   if(line.contains("EXPOSE")){
        		   
        		   newLines.add(line.replace("8081","9411"));
        	   }
        	   else{
        		   
        		   newLines.add(line);
        		   
        	   }
        	   
           }
           Files.write(Paths.get(fileName),newLines,StandardCharsets.UTF_8);
           newLines.clear();
           //-------------------------------------
           
           fileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-vets-service//target//docker//Dockerfile";
           newLines=new ArrayList<String>();
           
           for(String line:Files.readAllLines(Paths.get(fileName),StandardCharsets.UTF_8)){
        	   if(line.contains("EXPOSE")){
        		    
        		   newLines.add(line.replace("8081","8083"));
        	   }
        	   else{
        		   newLines.add(line);
        	   }
        	   
        	   
           }
           
           Files.write(Paths.get(fileName),newLines,StandardCharsets.UTF_8);
           newLines.clear();
           //------------------
           fileName="//home//ci-cd//git//Spring-Petclinic//spring-petclinic-visits-service//target//docker//Dockerfile";
           newLines=new ArrayList<String>();
           
           for(String line:Files.readAllLines(Paths.get(fileName),StandardCharsets.UTF_8)){
        	   if(line.contains("EXPOSE")){
        		   newLines.add(line.replace("8081","8082"));
        	   }
        	   else{
        		   
        		   newLines.add(line);
        	   }
           }
           Files.write(Paths.get(fileName),newLines,StandardCharsets.UTF_8);
           newLines.clear();
           
           //------------------
           
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
		
	}
	public static void configureServers(){
		String adminServerControl=(String)confg.get("AdminServer");
		if(adminServerControl==null){
			
			try{
				String readFileName="//home//ci-cd//git//Spring-Petclinic//docker-compose.yml";
			    List<String> newLines=new ArrayList<String>();
				int control=0;
              for(String line:Files.readAllLines(Paths.get(readFileName),StandardCharsets.UTF_8)){
	 		   
		     
			   if(line.contains("admin-server:")){
				   control=1;
				   //newLines.add(line);
				   
			   }
			   
			   else{
				   if(control==0){
					    newLines.add(line);
				   }
				   
			   }
			   if(line.isEmpty()){
				   control=0;
			   }
		   }
		   
		     Files.write(Paths.get(readFileName),newLines,StandardCharsets.UTF_8);
		     newLines.clear();				
		     
		     
		     File  file=new File("//home//ci-cd//git//spring-petclinic-microservices-config//admin-server.yml");		     
			 file.renameTo(new File("//home//ci-cd//Desktop//demo//"+file.getName()));
		     
			
			 
//			 file=new File("//home//ci-cd//git//Spring-Petclinic//spring-petclinic-admin-server//target//docker//Dockerfile");
//		     file.delete();
			}
			catch(Exception e){
				e.printStackTrace();
				
			}
			
		}
		
		else{
			
			try{
				String readFileName="//home//ci-cd//git//Spring-Petclinic//docker-compose.yml";			
				FileReader fr=new FileReader(readFileName);
				BufferedReader br=new BufferedReader(fr);
				
				String line=null;
				int serverControl=0;
				while((line=br.readLine())!=null){
					
	                 if(line.trim().equals("admin-server:")){
						serverControl=1;
						break;
					 }
				}
				br.close();
				fr.close();
				if(serverControl==0){
					
					readFileName="//home//ci-cd//Desktop//demo//admin-server.txt";
					fr=new FileReader(readFileName);
					br=new BufferedReader(fr);
					line=null;
					
					String writeFileName="//home//ci-cd//git//Spring-Petclinic//docker-compose.yml";
                    FileWriter fw=new FileWriter(writeFileName,true);                   
				    BufferedWriter bw=new BufferedWriter(new FileWriter(writeFileName,true));
					PrintWriter out=new PrintWriter(bw);
					
					
					while((line=br.readLine())!=null){
					  	out.println(line);
						
					}
					
					  out.close();
					  bw.close();
					  fw.close();
					  
					  
					  br.close();
					  fr.close();
					 				   		
				}
				
				 File  file=new File("//home//ci-cd//Desktop//demo//admin-server.yml");		     
				 file.renameTo(new File("//home//ci-cd//git//spring-petclinic-microservices-config//"+file.getName()));
								
			}
			
			catch(Exception e){
				e.printStackTrace();
				
			}
							
		}
				
	}
	
public static void copyFiles(String inputFile,String outputFile){
	try{
		FileInputStream instream=null;
		FileOutputStream outstream=null;
		
		File infile=new File(inputFile);
		File outfile=new File(outputFile);
		
		instream=new FileInputStream(infile);
		outstream=new FileOutputStream(outputFile);
		
		
		byte[]buffer=new byte[1024];
		
		int length;
		
		while((length=instream.read(buffer))>0){
			
			outstream.write(buffer,0,length);
		}
		
		instream.close();
		outstream.close();
		
		
	}
	catch(Exception e){
		
		e.printStackTrace();
	}
}

}