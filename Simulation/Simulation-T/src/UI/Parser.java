package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

import org.newdawn.slick.SlickException;


public class Parser {
	
	private ArrayList<ArrayList<Action>> actions = new ArrayList<ArrayList<Action>>();
	private ArrayList<Action> current = new ArrayList<Action>();
	private ArrayList<Action> next = new ArrayList<Action>();
	
	public Parser(){
	}
	
	public void addAction(String pathIn, String pathOut){
		current = new ArrayList<Action>();
		next = new ArrayList<Action>();
		replaceRegex(pathIn, pathOut);
		parseFile(pathOut);
		nextAction();
		nextAction();
	}

	
	public ArrayList<ArrayList<Action>> getActions() {
		return actions;
	}


	private void replaceRegex(String pathIn, String pathOut){
		String chaine="";

		Pattern p1 = Pattern.compile("State: [^\n]*");
		Pattern p2 = Pattern.compile("^\n");
		Pattern p3 = Pattern.compile("Transition: ");
		Pattern p4 = Pattern.compile(" $");
		Pattern p5 = Pattern.compile("\\} ");
		//Pattern p6 = Pattern.compile("\{[^\{\}]*\}");
		
		Matcher m;
		try{
			InputStream ips=new FileInputStream(pathIn); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne, tmp;
			FileWriter fw = new FileWriter (pathOut);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			int i = 1;
			while ((ligne=br.readLine())!=null){
				if (i%4 == 3){
					m = p3.matcher(ligne);
					tmp = m.replaceFirst("");
					m = p4.matcher(tmp);
					tmp = m.replaceFirst("");
					m = p5.matcher(tmp);
					tmp = m.replaceAll("\\}\n");
					fichierSortie.println(tmp);
					
				}
				i++;
			}
			fichierSortie.flush();
			br.close(); 
			fw.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	private void parseFile(String path){
		try{
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			ArrayList<String> query = new ArrayList<String>();
			String[] tmp1;
			String[] tmp2;
			String[] tmp3;
			while ((ligne=br.readLine())!=null){
				tmp1 = ligne.split(" -> ");
				tmp2 = tmp1[1].split(" \\{");
				tmp3 = tmp2[1].split(";");
				query.add(tmp1[0].split("\\.")[0]);
				query.add(tmp1[0].split("\\.")[1]);
				query.add(tmp2[0].split("\\.")[1]);
				query.add(tmp3[0]);
				query.add(tmp3[1].substring(1));
				query.add(tmp3[2].substring(1));
				addAction(query);
				
				query.clear();
			}

			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	private void nextAction(){
		if (current.size() > 0){
		actions.add(current);
		System.out.println(current);
		current = next;
		next = new ArrayList<Action>();
		}
	}
	
	
	public void addAction(ArrayList<String> query) throws SlickException, InterruptedException{
		//0: PROCESS
		//1: FROM
		//2: TO
		//3 : GUARD
		//4: SYNCHRONISATION
		//5: UPDATE

		//System.out.println(query);
		
		switch (query.get(0)){
		case  "CarGeneratorEast":
			simulateCarGenerator(query, Orientation.EAST);
			break;
		case  "CarGeneratorSouth":
			simulateCarGenerator(query, Orientation.SOUTH);
			break;
		case  "CarGeneratorWest":
			simulateCarGenerator(query, Orientation.WEST);
			break;
		case "PedestrianGeneratorEast":
			simulatePedestrianGenerator(query);
			break;
		case "LightController1":
			if (query.get(2).equals("Initial"))
				nextAction();
			break;
		}
		
	}
	
	private void simulateCarGenerator(ArrayList<String> query, Orientation o) throws SlickException, InterruptedException{
		String tmp;
		switch (query.get(1)){
		case "AcceptCar":
			if (query.get(2).equals("AcceptCar")){
				tmp = query.get(5).split(",")[0];
				tmp = tmp.split(" ")[2];
				switch(tmp){
				case "L":
					current.add(new Action("addCar", o, Direction.LEFT));
					break;
				case "U":
					current.add(new Action("addCar", o, Direction.UP));
					break;
				case "R":
					current.add(new Action("addCar", o, Direction.RIGHT));
					break;
				}
			}
			if (query.get(2).equals("LightGreen")){
				current.add(new Action("setTL", o, LightColor.GREEN));
				current.add(new Action("removeCar", o));
			}
			break;
		case "TakeDecision":
			if (query.get(2).equals("AcceptCar")){
				next.add(new Action("setTL", o, LightColor.RED));
			}
			break;
		}
	}
	
	private void simulatePedestrianGenerator(ArrayList<String> query) throws SlickException, InterruptedException{
		switch (query.get(2)){
		case "PushButton":
			current.add(new Action("addPedestrian"));
		break;
		case "Cross":
			current.add(new Action("setPedestrian", LightColor.GREEN));
			current.add(new Action("removePedestrian"));
		break;
		case "Empty":
			next.add(new Action("setPedestrian", LightColor.RED));
			break;
		}
	}
}
