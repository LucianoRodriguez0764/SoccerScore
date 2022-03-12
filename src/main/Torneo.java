package main;

import java.util.ArrayList;

public class Torneo {

	public int cantEquipos;
	public ArrayList<String> equipos = new ArrayList<String>();
	public ArrayList<String[]> fechas = new ArrayList<String[]>();
	
	
	public Torneo() {};
	
	public Torneo(int cantEquipos) {
		 
		this.cantEquipos = cantEquipos;
		
		ArrayList<String> scriptTeams = Scripter.readTeams("Equipos.txt");
		
		for(int i=0; i<cantEquipos; i++) {
			this.equipos.add(scriptTeams.get(i));
		}

	}
	
	
	public void fechasReader(String[] fecha) {
		
		int team1;
		int team2;
		
		for(int i=0; i< fecha.length; i++) {
			
			if(fecha[i].charAt(1) == '-') {
				
				team1 = Integer.parseInt(fecha[i].substring(0,1));
				
				if(fecha[i].length() == 3) {
					team2 = Integer.parseInt(fecha[i].substring(2,3));
				} else {
					team2 = Integer.parseInt(fecha[i].substring(2,4));
				}
			} else {
				
				team1 = Integer.parseInt(fecha[i].substring(0,2));

				if(fecha[i].length() == 5) {
					team2 = Integer.parseInt(fecha[i].substring(3,5));
				} else {
					team2 = Integer.parseInt(fecha[i].substring(3,4));
				}
				
			}
			

			System.out.println(equipos.get(team1)+" vs "+equipos.get(team2));
		}
		
	}
	
	
	public String[] fechasCreator(int fechaN, int cantEquipos) {
		
		ArrayList<String> fecha = new ArrayList<String>();
		String match = "";
		
		for (int i=0; i<cantEquipos; i++) {
			
			if(i < (fechaN+2)) { // la linea divisora entre los matchs que suman fecha+1 y los que suman cantEquipos+fecha es entre fecha+1 y fecha+2 
				
				match += String.valueOf(i);
				match+="-";
				
				for(int j=i; j<cantEquipos; j++) {
					if(j+i == fechaN+1) {
						
						if(j==i) {
							match += String.valueOf(cantEquipos-1);
						} else {
							match += String.valueOf(j);
						}
						
						fecha.add(match);
						
						break;
					}
				}
						
				
			}
			
			if(i >= fechaN+2) {
				
				match += String.valueOf(i);
				match+="-";
				
				for(int j=i; j<cantEquipos; j++) {
					if(j+i == cantEquipos+fechaN) {
						
						if(j==i) {
							match += String.valueOf(cantEquipos-1);
						} else {
							match += String.valueOf(j);
						}
						
						fecha.add(match);
						
						break;
					}
				}
				
			}
			
			
			match="";
			
		}
		
		String[] fechaString = new String[fecha.size()];
		
		for(int i=0; i<fecha.size(); i++) {
			fechaString[i] = fecha.get(i);
		}
		
		return fechaString;
		
	}
	
	public ArrayList<String[]> tournamentCreator(int cantRuedas, boolean random) {
		
//		
		
		ArrayList<String[]> tournament = new ArrayList<String[]>();
		
		cantRuedas = 1;
		int cantFechas = this.cantEquipos%2==0 ? this.cantEquipos-1 : this.cantEquipos;
		
		for(int i=0; i<cantFechas; i++) {
			
			tournament.add(fechasCreator(i, this.cantEquipos));
			
		
		}
		
		
		if(random) {

			tournament = randomizeTournament(tournament);
			
		}
		
		tournament = invertirLocaliaDeTodasLasFechas(tournament);
		
		fechas = tournament;
				
		return tournament;
	
	}

	private ArrayList<String[]> randomizeTournament(ArrayList<String[]> tournament) {
		
		int randomFecha, randomMatch;
		String auxMatch;
		
		ArrayList<String[]> randomizedTournament = new ArrayList<String[]>();
		
//		int i=0;
		
		while(tournament.size()>0) {
			
			randomFecha = (int)(Math.random()*tournament.size());
			
			for(int k=0; k<tournament.get(randomFecha).length; k++) {
				
				randomMatch = (int)(Math.random()*tournament.get(randomFecha).length);
				
				auxMatch = tournament.get(randomFecha)[k];
				tournament.get(randomFecha)[k] = tournament.get(randomFecha)[randomMatch];
				tournament.get(randomFecha)[randomMatch] = auxMatch;
				
			}
			
			
			
			randomizedTournament.add(tournament.get(randomFecha));
			
			
//			System.out.println("Position of fecha "+randomFecha+" now is "+i+" (length: "+tournament.size()+")");
			
			tournament.remove(randomFecha);

//			i++;
			
		}
		
		
		
		return randomizedTournament;
		
	}
	
	
	public String invertirLocalia(String match) {
		
		int length = match.length();
		String invertMatch = "";
		
		if(length==3) {
			
			invertMatch = match.charAt(2)+"-"+match.charAt(0);
			
		} else if(length == 4) {
			if(match.charAt(2) == '-') {
				invertMatch = match.charAt(3)+"-"+match.substring(0,2);
			} else {
				invertMatch = match.substring(2,4)+"-"+match.charAt(0);
			}
		} else if(length == 5) {
			invertMatch = match.substring(3,5)+"-"+match.substring(0,2);
		}
		
		
		return invertMatch;
	}
	
	public ArrayList<String[]> invertirLocaliaDeTodasLasFechas(ArrayList<String[]> tournament) {
		
		int k=1;
		
		for(int i=tournament.size()-1; i>(tournament.size()-1)/2; i--) { // invertir las ultimas fechas
			
			for(int j=0; j<k; j++) {
				tournament.get(i)[j] = invertirLocalia(tournament.get(i)[j]);
			}
			k++;
		}
		
		return tournament;
		
	}
	
}
