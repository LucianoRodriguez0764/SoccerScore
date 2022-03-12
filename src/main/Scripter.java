package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Scripter {
	
	
	public static void createScript() {
	
	try {
	      File file = new File("tournamentGenerator.txt");
	      if (file.createNewFile()) {
	        System.out.println("File created: " + file.getName());
	      } else {
	        System.out.println("File already exists.");
	      }
	    } catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	
	}
	
	public static void writeScript(String s) {
	
			    try {
			      FileWriter wr = new FileWriter("tournamentGenerator.txt",true);
			      wr.write(s);
			      wr.close();
			      System.out.println("Successfully wrote to the file.");
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		
	}
	
	public static void readScript(String s) {
		
		try {
		      File file = new File(s);
		      Scanner sc = new Scanner(file);
		      while (sc.hasNextLine()) {
		        String data = sc.nextLine();
		        System.out.println(data);
		      }
		      sc.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		}
	
	public static ArrayList<String> readTeams(String s) {
		
		String equipo = "";
		ArrayList<String> equipos = new ArrayList<>();
		
		try {
		      File file = new File(s);
		      Scanner sc = new Scanner(file);
		      while (sc.hasNextLine() && !equipo.equals("*")) {
		        equipo = sc.nextLine();
	        	
	        	if(!equipo.equals("*")) {
	        	equipos.add(equipo);
//	        	System.out.println(equipo);
	        	}
	        	
		      }
		      sc.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

		return equipos;
		
		}
	
	

}
