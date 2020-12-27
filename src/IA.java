
public class IA extends Player {
	
	
	public IA(){
		this.prenom="";
		this.nom="";
		this.numero=0;
		this.gameWin=0;
		this.ia=true;
	}
	public IA(String surname,String name,int n) {
		this.prenom=surname;
		this.nom=name;
		this.numero=n;
		this.gameWin=0;
		this.ia=true;
	}
	
	public boolean summa(int[]dice,int x){
		int cpt=0;
		for(int i=0;i<dice.length;i++){
			if(dice[i]==(x+1))
				cpt+=1;
		}
		if(cpt>=3&&this.score[x]==null)
			return true;
		else
			return false;
	}
	
	public boolean aequalisDice(int[]dice,int x){
		switch(x){
		case 1:
			if(dice[0]==dice[2]&&this.score[6]==null )
				return true;
			else if(dice[1]==dice[3]&&this.score[6]==null)
				return true;
			else if(dice[2]==dice[4]&&this.score[6]==null)
				return true;
			else
				return false;
		case 2:
			if(dice[0]==dice[3]&&this.score[8]==null)
				return true;
			else if(dice[1]==dice[4]&&this.score[8]==null)
				return true;
			else 
				return false;
		case 3:
			if(dice[0]==dice[4]&&this.score[11]==null)
				return true;
			else
				return false;
		default:
			return false;
		}
	}
	
	public boolean Full(int[]dice){
		int x=0;
		if((dice[2]==dice[0])&&(dice[3]==dice[4])&&(dice[2]!=dice[3]))
				return true;
		else if((dice[2]==dice[4])&&(dice[0]==dice[1])&&(dice[2]!=dice[1]))
			return true;
		else
			return false;
	}
	
	public boolean haec(int[]dice,int c){
		int cpt=0;
		boolean test=false;
		if(c==1) {                       //Gestion de la Petite Suite//
			int cpt2=0;
			for(int i=1;i<dice.length;i++) {
				if((dice[i-1])==(dice[i]-1))
					cpt+=1;
				else
					cpt2+=1;
			}
			if(cpt>=3&&cpt2<=1)
				test= true;
			else
				test= false;
		}else if(c==2) {                //Gestion de la Grande Suite//
			for (int i=0;i<dice.length-1;i++) {
				if((dice[i]+1)==dice[i+1])
					cpt+=1;
			}
			if(cpt==4)
				test= true;
			else
				test= false;
		}
		return test;
	}
	
	public int indiceMaxDice(int[]cptTab,int max){
		int indice=0;
		for(int i=0;i<cptTab.length;i++) {
			if(cptTab[i]==max) {
				indice=i;
			}
		}
		return indice;
	}
	
	public int maxDice(int[]cptTab){
		int max=0;
		for(int i=0;i<cptTab.length;i++){
			if(cptTab[i]>max){
				max=cptTab[i];
			}
		}
		return max;
	}
	
	public int[]numerisDice(int[]dice){
		int[]cptTab=new int[6];
		int cpt;
		for(int i=0;i<cptTab.length;i++){
			cpt=0;
			for(int j=0;j<dice.length;j++){
				if(dice[j]==(i+1))
					cpt+=1;
			}
			cptTab[i]=cpt;
		}
		return cptTab;
	}
	
}
