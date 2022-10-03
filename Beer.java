package lab4;
import java.io.*;
import java.util.*;

public class Beer {
	private String name;
	private String style;
	private double strength;
	public static InputStreamReader isr = new InputStreamReader(System.in);
	public static BufferedReader br = new BufferedReader(isr);
	public static ArrayList<Beer> BeerList = new ArrayList<Beer>();
	

	Beer(String n, String sty, double str){
		name = n;
		style = sty;
		strength = str;
	}
	
	public String getName() {return name;}
	public String getStyle() {return style;}
	public double getStrength() {return strength;}
	
	public String toString() {return name + " " + style + " " + strength;}
	
	public void exit(String[] cmd) { System.exit(0); }
	public void add(String[] cmd) { 
		Beer newBeer = new Beer(cmd[1], cmd[2], Double.parseDouble(cmd[3]));
		BeerList.add(newBeer);
	}
	public void list(String[] cmd) {
		if(cmd.length >= 2) {
			if (cmd[1].equals("name")) {
				NameComparator nc = new NameComparator();
				BeerList.sort(nc);
			}
			else if (cmd[1].equals("style")) {
				StyleComparator styc = new StyleComparator();
				BeerList.sort(styc);
			}
			else if (cmd[1].equals("strength")) {
				StrengthComparator strc = new StrengthComparator();
				BeerList.sort(strc);
			}
				
		}
		
		System.out.println(BeerList.toString());
	}
	public void save(String[] cmd) throws IOException{
		FileWriter fw = new FileWriter(cmd[1]);
		PrintWriter pw = new PrintWriter(fw);
		
		Beer[] ListaArray = new Beer[10];
		BeerList.toArray(ListaArray);
		for(int i = 0; i < ListaArray.length; i++) {
			if (ListaArray[i] == null)
				break;
			pw.println(ListaArray[i]);
		}
		
		
		fw.close();

	}
	public void load(String[] cmd) throws IOException {
		FileReader filereader = new FileReader(cmd[1]);
		BufferedReader buffr = new BufferedReader(filereader);
		
		BeerList.clear();
		String line = buffr.readLine();
		while(line != null) {
			String[] beerline = line.split(" ");
			BeerList.add(new Beer(beerline[0], beerline[1], Double.parseDouble(beerline[2])));
			line = buffr.readLine();
		}
		
		filereader.close();
	}
	
	public void search(String[] cmd) {
		for(Beer b : BeerList) {
			if(b.getName().equals(cmd[1]))
				System.out.println(b.toString());
		}
	}
	
	public void find(String[] cmd) {
		for(Beer b : BeerList) {
			if(b.getName().contains(cmd[1]))
				System.out.println(b.toString());
		}
	}
	
	//nem volt teljesen egyértelmű, minden illeszkedő nevű sört töröl
	public void delete(String[] cmd) {
		Iterator<Beer> it = BeerList.iterator();
		while(it.hasNext()) {
			if(it.next().getName().equals(cmd[1]))
				it.remove();
		}
	}
	
	

	
	public static void main(String[] args) throws IOException {
		Beer commander = new Beer("Commander", "Commander", 100.0);
		String Sor = br.readLine();
		while (Sor != null) {
			String[] cmd = Sor.split(" ");
			if (cmd[0].equals("exit"))
				commander.exit(cmd);
			else if (cmd[0].equals("add"))
				commander.add(cmd);
			else if (cmd[0].equals("list"))
				commander.list(cmd);
			else if (cmd[0].equals("save"))
				commander.save(cmd);
			else if (cmd[0].equals("load"))
				commander.load(cmd);
			else if (cmd[0].equals("search"))
				commander.search(cmd);
			else if (cmd[0].equals("find"))
				commander.find(cmd);
			else if (cmd[0].equals("delete"))
				commander.delete(cmd);
			else
				System.out.println("Nem ismert parancs :(");
			
			Sor = br.readLine();
		}

	}
}
