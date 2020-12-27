import java.util.ArrayList;

public class Player {
	public String prenom;
	public String nom;
	public int numero;
	public Integer [] score =new Integer [14] ;
	public int gameWin;
	boolean ia=false;
	

	public Player() {
		this.prenom="";
		this.nom="";
		this.numero=0;
		this.gameWin=0;
	}
	
	public Player (String s1,String s2,int n){
		this.prenom=s1;
		this.nom=s2;
		this.numero=n;
		this.gameWin=0;
	}
	
	public void totalScore() {
		int total=0;
		for (int i=0;i<score.length-1;i++){
			if(this.score[i]==null)
				total+=0;
			else
				total+=this.score[i];
		}
		this.score[13]=total;
	}
	
	public void getBonus() {
		int total=0;
		for(int i=0;i<6;i++)
			total+=this.score[i];
		if(total>=63) {
			System.out.println("Joueur "+this.printPlayer()+" valide le bonus.");
			System.out.println("Ancien total ="+this.score[13]+"->Nouveau total ="+(this.score[13]+35));
			this.score[13]+=35;
		}else {
			System.out.println("Joueur "+this.printPlayer()+" ne valide pas le bonus.");
			System.out.println("Total ="+this.score[13]);
		}
	}
	
	public String printPlayer(){
		String s=prenom+" "+nom;
		return s;
	}
	
	public void printContracts() {
		String s="Tableau des Scores de "+this.printPlayer()+"\n\n";
		for(int i=0;i<6;i+=2) {
			if(this.score[i]==null)
				s+="Somme "+(i+1)+" = NF 	";
			else if(this.score[i]>9) 
				s+="Somme "+(i+1)+" = "+this.score[i]+" 	";
			else if(this.score[i]<=9)
				s+="Somme "+(i+1)+" = 0"+this.score[i]+" 	";
			if(this.score[i+1]==null)
				s+="Somme "+(i+2)+" = NF\n";
			else if(this.score[i+1]>9) 
				s+="Somme "+(i+2)+" = "+this.score[i+1]+"\n";
			else if(this.score[i+1]<=9)
				s+="Somme "+(i+2)+" = 0"+this.score[i+1]+"\n";
		}
		System.out.print(s);
		if(this.score[6]==null)
			System.out.print("Brelan  = NF 	");
		else if(this.score[6]>9)
			System.out.print("Brelan  = "+this.score[6]+" 	");
		else if(this.score[6]<9)
			System.out.print("Brelan  = 0"+this.score[6]+" 	");
		if(this.score[7]==null)
			System.out.println("Full    = NF");
		else
			System.out.println("Full    = "+this.score[7]);
		if(this.score[8]==null)
			System.out.print("Carre   = NF 	");
		else if(this.score[8]>9)
			System.out.print("Carre   = "+this.score[8]+" 	");
		else if(this.score[8]<9)
			System.out.print("Carre   = 0"+this.score[8]+" 	");
		if(this.score[9]==null)
			System.out.println("P-Suite = NF");
		else
			System.out.println("P-Suite = "+this.score[9]);
		if(this.score[10]==null)
			System.out.print("G-Suite = NF 	");
		else if(this.score[10]!=0)
			System.out.print("G-Suite = "+this.score[10]+" 	");
		else if(this.score[10]==0)
			System.out.print("G-Suite = 0"+this.score[10]+" 	");
		if(this.score[11]==null)
			System.out.println("Yams    = NF");
		else
			System.out.println("Yams    = "+this.score[11]);
		if(this.score[12]==null)
			System.out.println("Chance  = NF");
		else
			System.out.println("Chance  = "+this.score[12]);
		if(this.score[13]==null)
			System.out.println("Total des scores = "+0+"\n");
		else
			System.out.println("Total des scores = "+this.score[13]+"\n");
	}
	
}
