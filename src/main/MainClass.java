package main;

import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) {
		
		Torneo t = new Torneo(20);
		System.out.println(t.equipos);
		
		t.tournamentCreator(1, false);
		
		
		for(int i=0; i<19; i++) {
		
			for(int j=0; j<10; j++) {
			System.out.println(t.fechas.get(i)[j]);
			}
			System.out.println();
		}
		
		/*
		 * Crear un formato lindo para las fechas con el maximo length
		 * de nombre o tabeados, para que quede tipo
		 * 
		 * RIVER         2-0  BOCA
		 * INDPENDIENTE  1-1  RACING
		 * BANFIELD      3-1  LANUS
		 * 
		 * 
		 * Crear el sistema de randomizar fechas de las n que se generan, y luego
		 * de esta randomizada aplicar el invertimiento de localias.
		 * Crear los partidos y estadisticas guardables, una vez hechos los enfrentaminetos,
		 * crear sistemas de tablas por fechas e imprimirlo todo en el txt.
		 */
		
	}
	
	public static void createStats(int enditerations) {
		
		String s = "";
		char c;
		int iterations = 0;
		boolean end = true;
		int counter00 = 0;
		int counter10 = 0;
		int counter11 = 0;
		int counter20 = 0;
		int counter21 = 0;
		int counter22 = 0;
		int counter30 = 0;
		int counter31 = 0;
		int counter32 = 0;
		int counter33 = 0;
		int counter40 = 0;
		int counter41 = 0;
		int counter42 = 0;
		int counter43 = 0;
		int counter44 = 0;
		int other = 0;
		
		int localCounter = 0;
		int visitCounter = 0;
		int drawCounter = 0;
		
		
		while(iterations < enditerations) {
			
			s = Score.getOnlyScore();
			
			switch(s) {
			case "0-0" -> counter00++;
			case "1-0", "0-1" -> counter10++;
			case "1-1" -> counter11++;
			case "2-0", "0-2" -> counter20++;
			case "2-1", "1-2" -> counter21++;
			case "2-2" -> counter22++;
			case "3-0", "0-3" -> counter30++;
			case "3-1", "1-3" -> counter31++;
			case "3-2", "2-3" -> counter32++;
			case "3-3" -> counter33++;
			case "4-0", "0-4" -> counter40++;
			case "4-1", "1-4" -> counter41++;
			case "4-2", "2-4" -> counter42++;
			case "4-3", "3-4" -> counter43++;
			case "4-4" -> counter44++;
			default -> other++;
			
			}
			
			if(s.charAt(0)>s.charAt(2)) {
				localCounter++;
			} else if(s.charAt(0)<s.charAt(2)) {
				visitCounter++;
			} else drawCounter++;
			
			iterations++;
		}
		
		System.out.println(counter00+"-"+counter10+"-"+counter11+"-"+
				counter20+"-"+counter21+"-"+counter22+"-"+
				counter30+"-"+counter31+"-"+counter32+"-"+
				counter33+"-"+counter40+"-"+counter41+"-"+
				counter42+"-"+counter43+"-"+counter44+"-"+other);
		
		System.out.println("Local:" + localCounter +", Visit: "+visitCounter+", Draws: "+drawCounter);
		
	}
		

}
