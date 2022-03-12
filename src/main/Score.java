package main;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public abstract class Score {

	private static URL url;
	private static Scanner sc;
	private static StringBuffer sb;
	private static boolean wasFound = false; 
	// http://otj.awardspace.co.uk/match.php
	
	public static StringBuffer getWholePage() {
		
		try {
			
			url = new URL("http://otj.awardspace.co.uk/match.php");
			sc = new Scanner(url.openStream());
			sb = new StringBuffer();
			String nextString;
			
			while(sc.hasNext()) {
				
				nextString = sc.next();
				sb.append(nextString);
//				sb.append("\n");
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb;
		
	}
	
	/*
	 * 
	 *  Este metodo recorre la pagina hasta encontrar el string en donde
	 *  se encuentran los resultados (caracterizado por ser el primer #0000ff)
	 *  y luego, dependiendo de si el resultado es de uno o dos digitos almacena
	 *  el substring con offsets ya que en caso de ser de dos digitos correria
	 *  el indice en donde se encuentra el segundo resultado.
	 * 
	 *  El score es devuelto en formato "2-1" 
	 * 
	 */
	public static String getOnlyScore() {
		
		String score = "";
		
		try {
			
			url = new URL("http://otj.awardspace.co.uk/match.php");
			sc = new Scanner(url.openStream());
			sb = new StringBuffer();
			String nextString;
			
			
			while(!wasFound && sc.hasNext()) {
				
				nextString = sc.next();
				sb.append(nextString);
				sb.append("\n");
				
				if(nextString.length() >= 9 && nextString.substring(7,9).equals("#0")) {
					
					int offsetRed = 0;
					int offsetBlue = 0;
					
					if(nextString.charAt(71) != '<') {
						offsetRed++;	
					}
					
					System.out.print("Red Goals: "+nextString.substring(70,71+offsetRed)+", ");
					score += nextString.substring(70,71+offsetRed);
					
					score += "-";

										
					if(nextString.charAt(163+offsetRed) != '<') {
						offsetBlue++;
					}
					
					System.out.println("Blue Goals: "+nextString.substring(162+offsetRed,163+offsetRed));
					score += nextString.substring(162+offsetRed,163+offsetRed+offsetBlue);

					wasFound=true;
					
				}
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			wasFound=false;
		}
		
		return score;
		
	}
	
}
