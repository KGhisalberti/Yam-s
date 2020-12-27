import java.util.*;

public class main {

	static Scanner sc=new Scanner(System.in);
	static int[] Dice = new int[5];
	static String stop;
	static IA temp3=new IA ("Cortana","Halsey",1);
	static Player temp=new Player("Noctis","Lucis",2);
	static Player temp2=new Player("Luna","Nox",3);
	
	/*Programme principal*/
	public static void main(String[] args) { 
		int x=0;
		while(x==0) {
			do{
				System.out.println("Bienvenue dans le jeu du Yams !\n\nQue souhaitez-vous faire ?\n");
				System.out.println("1)Jouer en Player versus Player.\n2)Jouer en Player vs IA.\n3)R�gles du jeu\n4)Quitter le programme.");
				x=saisieInt(4);
			}while(x==0);
			switch (x){
			case 1:               /*Lancement d'une partie PVP*/
				x=0; 
				do {              /*Gestion du nombre de joueurs*/
					System.out.println("Veuillez entrer le nombre de joueur.");
					x=saisieInt(10);
					if(x<0) {
						System.out.println("Choisir une valeur valide.\n");
						x=0;
					}
				}while(x==0);
					Player [] tabJ= new Player [x];
					for(int i=0;i<tabJ.length;i++){
						Player player=createJoueur(i+1);
						tabJ[i]=player;
					}
					boolean regame=true;
					int nbGame=1;			//Gestion d'une partie et de la possibilite de relancer une partie//
					while(regame==true) {
						System.out.println("\nLancement de la Game "+nbGame+"\n");
						for(int i=0;i<13;i++) {
							for(int j=0;j<tabJ.length;j++) {
								playerTurn(tabJ[j],i+1);
							}
						}
						for (int i=0;i<tabJ.length;i++)
							tabJ[i].getBonus();
						stop=sc.next();
						Player winner=determineWinner(tabJ);
						System.out.println("Bravo "+winner.printPlayer()+" vous avez remporte la partie !\n");
						for(int i=0;i<tabJ.length;i++) {
							if(tabJ[i]==winner)
								tabJ[i].gameWin+=1;
						}
						x=0;
						do {
							System.out.println("Souhaitez-vous rejouer ?");
							System.out.println("1)Oui 2)Non");
							x=saisieInt(2);
						}while(x==0);
						if(x==1) {                /*Cas ou les utilisateurs rejouent*/
							Integer[]newScore=new Integer[14];
							for (int i=0;i<tabJ.length;i++)
								tabJ[i].score=newScore;
							nbGame++;
						}else if(x==2) {          /*Cas ou les utilisateurs ne rejouent pas*/
							regame=false;
							int max=0;
							for(int i=0;i<tabJ.length;i++) {
								System.out.println("Nombre de parties gagnees par "+tabJ[i].printPlayer()+": "+tabJ[i].score);
								if(max<tabJ[i].gameWin)
									max=tabJ[i].gameWin;
							}
							for(int i=0;i<tabJ.length;i++) {  /*Determination du ou des vainqueurs du match par le nombre de parties gagnees*/
								if(max==tabJ[i].gameWin)
									System.out.println("Victoire de "+tabJ[i].printPlayer()+" avec "+tabJ[i].gameWin+" parties gagnees");
							}
							x=0;
						}
					}
				break;
			case 2:
				/*x=0; 
				do {              
					System.out.println("Veuillez entrer le nombre de joueur et d'IA.");
					x=saisieInt(10);
					if(x<0) {
						System.out.println("Choisir une valeur valide.\n");
						x=0;
					}
				}while(x==0);
				Player [] tabJIA= new Player [x];
				do {
					System.out.println("Entrer le nombre de joueur humain");
					if(x<0) {
						System.out.println("Choisir une valeur valide.\n");
						x=0;
					}
				}while(x==0);
				for(int i=0;i<x;i++){
					Player player=createJoueur(i+1);
					tabJIA[i]=player;
				}
				for(int i=x;i<tabJIA.length;i++) {
					IA cortana=createIA(i+1);
					tabJIA[i]=cortana;
				}
				boolean regame2=true;
				int nbGame2=1;
				while(regame2==true) {
					System.out.println("\nLancement de la Game "+nbGame2+"\n");
					for(int i=0;i<13;i++) {
						for(int j=0;j<tabJIA.length;j++) {
							if(tabJIA[j].ia==false)
								playerTurn(tabJIA[j],i+1);
							else if(tabJIA[j].ia==true){
								
							}
								
						}
					}
				}*/

			case 3:   /*Impression des regles du Yams*/
				System.out.println("Regles du Yams\n");
				System.out.println("Le yams est un jeu de dice(au nombre de 5) qui se joue e plusieurs essentiellement.");
				System.out.println("Chaque joueur peut lancer jusqu'a 3 fois les dices durant son tour et a la fin remplir un contrat.");
				System.out.println("Les contrats sont au nombre de 13 et chaque joueur ont les memes.\n");
				System.out.println("Contrat de 1 a 6) La Somme, vous devez obtenir le plus de dice du nombre correspondant.");
				System.out.println("Score= sommes des dices avec le nombre correspondant.\n");
				System.out.println("Contrat 7) Le Brelan, vous devez obtenir au moins 3 dices avec le meme nombre.");
				System.out.println("Score= somme de tout les dices.\n");
				System.out.println("Contrat 8) Le Full, vous devez obtenir 3 dices identiques et 2 autres dice identiques");
				System.out.println("Score= 25 points\n");
				System.out.println("Contrat 9) Le Carre, vous devez obtenir au moins 4 dices identiques.");
				System.out.println("Score= sommes de tout les dices\n");
				System.out.println("Contrat 10) La P-Suite, vous devez obtenir au moins 4 dices qui se suivent numeriquement");
				System.out.println("Score= 30 points\n");
				System.out.println("Contrat 11) La G-Suite, vous devez obtenir 5 dices qui se suivent numeriquement");
				System.out.println("Score= 40 points\n");
				System.out.println("Contrat 12) Le Yams, vous devez obtenir 5 dices identiques");
				System.out.println("Score= 50 points\n");
				System.out.println("Contrat 13) La Chance, sert generalement de back-up.");
				System.out.println("Score= somme de tout les dices\n");
				System.out.println("Si le joueur au bout de son tour ne parvient pas a faire un contrat, il devra en sacrifier et aura 0 points sur ce contrat.");
				System.out.println("Si l'addition de toutes les sommes fait au moins un total de 63 points, le joueur recevra alors 35 points bonus");
				System.out.println("Si a la fin de la partie, une egalite se presente entre 2 joueurs, un jet supplementaire sera fait afin de determiner un vainqueur.");
				System.out.println("A la fin de la partie, les joueurs peuvent relancer une partie ou arreter le match.");
				System.out.println("A la fin du match, ceux qui ont gagne le plus de parties gagnent le match.");
				System.out.println("FIN");
				stop=sc.next();
				x=0;
				break;
			case 4:
				System.out.println("Bye !");
				System.exit(0);
				break;
			default:
				System.out.println("Area 51");
			}
		}
	}
	
	public static Player createJoueur(int cpt){  /* Fonction de creation d'un joueur.*/
		System.out.println("Entrer prenom du Player "+ cpt +":\n" );  /*On demande a l'utilisateur d'entrer son prenom et nom*/
		String surname=sc.next();
		System.out.println("Entrer nom du Player "+ cpt +":\n");
		String name=sc.next();
		Player j=new Player(surname,name,cpt);
		return j;
	}		
	
	/*Fonction de jet de dice*/
	public static void throwDice(int y){ 
		String s="";
		int x=0;
		if(y==1){                        /*Cas du jet 1 dans le tour du joueur*/
			for(int i=0;i<Dice.length;i++){
				Dice[i]=(int)(((7-1)*Math.random())+1);
			}
		}else{                          /*Cas du jet 2 et 3 dans le tour du joueur*/   
			do{
				System.out.println("\nQuels dices voulez-vous relancer ?");
				printDice();
				System.out.println("Tapez 1 pour garder le dice et 2 pour le relancer.");
				s=sc.next();                           /*Gestion des erreurs de l'utilisateur*/
				x=Integer.parseInt(s);
				if(s.length()!=5){
					System.out.println("Choisir une valeur valide.\n");
					x=0;
				}
			}while(x==0);
			int c;
			for(int i=0;i<s.length();i++){
				c=Integer.parseInt(""+s.charAt(i));
				if(c==2)
					Dice[i]=(int)(((7-1)*Math.random())+1);
			}
		}
		
	}
	
	/*Fonction de gestion du Brelan,Carre et Yams*/
	public static int sameDice(Player j,int c) {    
		boolean test=false;
		int x=0;
		switch(c){
		case 1:                          /*Gestion du Brelan*/
			if(Dice[0]==Dice[2]) {
				test=true;
				j.score[6]=Dice[0]*3+Dice[3]+Dice[4];
			}else if(Dice[1]==Dice[3]) {
				test=true;
				j.score[6]=j.score[6]=Dice[1]*3+Dice[4]+Dice[0];
			}else if(Dice[2]==Dice[4]) {
				test=true;
				j.score[6]=Dice[2]*3+Dice[0]+Dice[1];
			}
			break;
		case 2:                          /*Gestion du Carre*/
			if(Dice[0]==Dice[3]) {
				test=true;
				j.score[8]=Dice[1]*4+Dice[4];
			}else if(Dice[1]==Dice[4]) {
				test=true;
				j.score[8]=Dice[1]*4+Dice[0];
			}
			break;
		case 3:                       /*Gestion du Yams*/
			if(Dice[0]==Dice[4]) {
				test=true;
				j.score[11]=50;
			}
			break;
		default :
			System.out.println("Area 51");
		}
		if(test==false){              /*Gestion du cas ou l'utilisateur veut valider le contrat sans marquer de point*/
			do {
				System.out.println("Vous n'avez pas de quoi faire ce contrat !");
				System.out.println("Voulez-vous vraiment faire ce contrat ? (Ajoutera 0 au score)\n");
				System.out.println("1)Oui 2)Non");
				x=saisieInt(2);
			}while(x==0);
			if(x==1) {
				switch(c) {
				case 1:
					j.score[6]=0;
					break;
				case 2:
					j.score[8]=0;
					break;
				case 3:
					j.score[11]=0;
					break;
				default:
					System.out.println("Area 51");
				}
			}else if(x==2)
				x=0;
		}else
			x=6;
		return x;
	}
	
	
	/*Fonction de gestion du contrat Full*/
	public static int full(Player j) { 
		int x=0;
		if((Dice[2]==Dice[0])&&(Dice[3]==Dice[4])&&(Dice[2]!=Dice[3]))
				x=5;
		else if((Dice[2]==Dice[4])&&(Dice[0]==Dice[1])&&(Dice[2]!=Dice[1]))
			x=5;
		if(x==0) {
			do {                      /*Gestion du cas ou l'utilisateur veut valider le contrat sans marquer de point*/
				System.out.println("Vous n'avez pas de quoi faire ce contrat !");
				System.out.println("Voulez-vous vraiment faire ce contrat ? (Ajoutera 0 au score)\n");
				System.out.println("1)Oui 2)Non");
				x=saisieInt(2);
			}while(x==0);
				if(x==1)
					j.score[7]=0;
				else if(x==2)
					x=0;
		}else
			j.score[7]=25;
		return x;
	}
	
	/*Fonction de gestion des contrats de suite*/
	public static int suite(Player j,int c) {   
		boolean test=false;
		int cpt=0;
		int x=0;
		if(c==1) {                       //Gestion de la Petite Suite//
			int cpt2=0;
			for(int i=1;i<Dice.length;i++) {
				if((Dice[i-1])==(Dice[i]-1))
					cpt+=1;
				else
					cpt2+=1;
			}
			if(cpt>=3&&cpt2<=1){
				test=true;
				j.score[9]=30;
			}
		}else if(c==2) {                //Gestion de la Grande Suite//
			for (int i=0;i<Dice.length-1;i++) {
				if((Dice[i]+1)==Dice[i+1])
					cpt+=1;
			}
			if(cpt==4) {
				test=true;
				j.score[10]=40;
			}
		}
		if(test==false){
			do {                      //Gestion du cas ou l'utilisateur veut valider le contrat sans marquer de point//
				System.out.println("Vous n'avez pas de quoi faire ce contrat !");
				System.out.println("Voulez-vous vraiment faire ce contrat ? (Ajoutera 0 au score)\n");
				System.out.println("1)Oui 2)Non");
				x=saisieInt(2);
			}while(x==0);
			if(x==1) {
				if(c==1)
					j.score[9]=0;
				else if(c==2)
					j.score[10]=0;
			}else if (x==2)
				x=0;
		}else
			x=6;
		return x;
	}
	
	/*Fonction principale de gestion des contrats*/
	public static void checkContrat(Player j){ 
		int x=0;
		while(x==0) {
			do{
				System.out.println("\nQuelle contrat voulez-vous choisir ?\n");     /*Gestion de l'affichage en fonction des contrats non faits*/
				printDice();
				for(int i=0;i<6;i+=2) {
					if(j.score[i]==null)
						System.out.print((i+1)+")Somme "+(i+1)+"	");
					else if(j.score[i]!=null)
						System.out.print((i+1)+")Non Dispo	");
					if(j.score[i+1]==null)
						System.out.println((i+2)+")Somme "+(i+2));
					else if(j.score[i+1]!=null)
						System.out.println((i+2)+")Non Dispo ");
				}
				if(j.score[6]==null)
					System.out.print("7)Brelan	");
				else if(j.score[6]!=null)
					System.out.print("7)Non Dispo	");
				if(j.score[7]==null)
					System.out.println("8)Full");
				else if(j.score[7]!=null)
					System.out.println("8)Non Dispo");
				if(j.score[8]==null)
					System.out.print("9)Carre 	");
				else if(j.score[8]!=null)
					System.out.print("9)Non Dispo 	");
				if(j.score[9]==null)
					System.out.println("10)P-Suite");
				else if(j.score[9]!=null)
					System.out.println("10)Non Dispo");
				if(j.score[10]==null)
					System.out.print("11)G-Suite	");
				else if(j.score[10]!=null)
					System.out.print("11)Non Dispo	");
				if(j.score[11]==null)
					System.out.println("12)Yams");
				else if(j.score[11]!=null)
					System.out.println("12)Non Dispo");
				if(j.score[12]==null)
					System.out.println("13)Chance");
				else if(j.score[12]!=null)
					System.out.println("13)Non Dispo");
				x=saisieInt(13);
				if(j.score[x-1]!=null) {
					System.out.println("Choisir une valeur valide.");
					x=0;
				}
			}while(x==0);
			int cpt=0;                     
			if(x<7) {                    /*Gestion des differents contrats de Somme*/
				for (int i=0;i<Dice.length;i++) {
					if(Dice[i]==x) 
						cpt+=x;
				}
				if(cpt==0){
					x=0;
					do {                   /*Gestion du cas ou l'utilisateur veut valider le contrat sans marquer de point*/
						System.out.println("Vous allez marquer 0 point sur ce contrat !");
						System.out.println("Voulez-vous vraiment faire ce contrat ? (Ajoutera 0 au score)\n");
						System.out.println("1)Oui 2)Non");
						x=saisieInt(2);
					}while(x==0);
					if(x==1) {
						j.score[x-1]=cpt;
					}else if(x==2)
						x=0;
				}else
					j.score[x-1]=cpt;
				j.totalScore();
			}else if(x>=7) {
				switch(x) {
				case 7:                       /*Appel de la fonction de gestion du Brelan*/
					x=sameDice(j,1);
					j.totalScore();
					break;
				case 8:                       /*Appel de la fonction de gestion du Full*/ 
					x=full(j);
					j.totalScore();
					break;
				case 9:                       /*Appel de la fonction de gestion du Carre*/
					x=sameDice(j,2);
					j.totalScore();
					break;
				case 10:                      /*Appel de la fonction de gestion de la Petite Suite*/
					x=suite(j,1);
					j.totalScore();
					break;
				case 11:                      /*Appel de la fonction de gestion de la Grande Suite*/
					x=suite(j,2);
					j.totalScore();
					break;
				case 12:                      /*Appel de la fonction de gestion du Yams*/
					x=sameDice(j,3);
					j.totalScore();
					break;
				case 13:                      /*Gestion de la chance*/
					for (int i=0;i<Dice.length;i++) {
						cpt+=Dice[i];
					}
					x=0;
					do {                              /*Gestion de l'utilisateur*/
						System.out.println("Voulez-vous vraiment utiliser votre chance ?");
						System.out.println("Score obtenue avec cette somme = "+cpt+"\n");
						System.out.println("1)Oui 2)Non");
						x=saisieInt(2);
					}while(x==0);
					if(x==1)
						j.score[12]=cpt;
					else if(x==2)
						x=0;
					j.totalScore();
					break;
				default:
					System.out.println("Area 51");
				}
			}
		}
	}
	
	/*Fonction de deroulement d'un tour complet d'un joueur*/
	public static void playerTurn(Player j,int turn){  
		int x=0;
		int cpt=0;
		while(cpt<3) {
			if(cpt==0) {
				while(x==0||x==2) { /*Affichage avant le jet 1, se renouvelle apres demande d'affichage des contrats*/
					do {
						System.out.println("Tour "+turn+" du Joueur "+j.numero+": "+j.printPlayer());
						System.out.println("Que souhaitez-vous faire ?\n");
						System.out.println("1)Faire le jet 1 2)Consultez vos contrats");
						x=saisieInt(2);
					}while(x==0);
					if(x==1) {     /*Realisation du jet 1*/
						cpt+=1;
						throwDice(1);
					}else if(x==2){    /*Affichage des contrats*/
						j.printContracts();
						stop=sc.next();
					}
				}
			}else if(cpt>0) {     /*Affichage avant le jet 2 et 3, se renouvelle apres demande d'affichage des contrats*/
				x=0;
				while(x==0||x==3) {
					do {
						System.out.println("Que souhaitez-vous faire ?\n");
						printDice();
						System.out.println("1)Garder et faire le jet "+(cpt+1)+" 2)Valider un contrat 3)Consultez vos contrats");
						x=saisieInt(3);
					}while(x==0);
					switch(x){
					case 1:         /*Realisation du jet 2 ou 3*/
						cpt+=1;
						throwDice(2);
						break;
					case 2:      /*Sortie de la boucle anticipee si l'utilisateur souhaite valider un contrat*/
						cpt=3;
						break;
					case 3:
						j.printContracts(); /*Affichage des contrats*/
						stop=sc.next();
						break;
					default:
						System.out.println("Area 51");
					}
				}
			}
		}
		Arrays.sort(Dice);       /*Tri des dices dans l'ordre croissant*/
		checkContrat(j);        /*Appel de la fonction de gestion des contrats*/
		j.printContracts();    /*Affichage du score avant tour du joueur suivant*/
	}
	
	/*Fonction de comparaison des scores totaux des differents Joueurs*/
	public static boolean[] scoreCompare(Player[] tabJ){  
		boolean[]x=new boolean[tabJ.length];
		int temp=0;
		for(int i=0;i<tabJ.length;i++){
			if(tabJ[i].score[13]>temp)
				temp=tabJ[i].score[13];
		}
		for(int i=0;i<tabJ.length;i++) {
			if(tabJ[i].score[13]==temp)
				x[i]=true;
			else
				x[i]=false;
		}
		return x;
	}
	
	/*Fonction pour declarer le vainqueur et gestion des cas d'egalite*/
	public static Player determineWinner(Player[]tabJ){ 
		boolean[]test=new boolean[tabJ.length];
		int cpt=0;
		int indice=0;
		int x=0;
		while(cpt!=1) {
			test=scoreCompare(tabJ);
			cpt=0;
			for (int i=0;i<test.length;i++) {
				if(test[i]==true) {
					cpt+=1;
					indice=i;
				}
			}
			if(cpt>1) {          /*jet supplementaire si deux joueurs ont le score plus eleve afin de determiner un vainqueur*/
				System.out.println("Jet supplementaire pour ceux ayant un score egaux\n");
				for(int i=0;i<test.length;i++) {
					if(test[i]==true) {
						do {
							System.out.println("Tour de "+tabJ[i].printPlayer());
							System.out.println("1)Faire le jet");
							x=saisieInt(1);
						}while(x==0);			
						throwDice(1);
						for(int j=0;j<Dice.length;j++){
							tabJ[i].score[13]+=Dice[j];
						}
						System.out.println("Nouveau score de "+tabJ[i].printPlayer()+"= "+tabJ[i].score[13]);
					}
				}
			}
		}
		return tabJ[indice];       /*on recupere le joueur vainqueur*/
	}
	
	
	/*Fonction de saisie utilisateur et gestion des erreurs*/
	public static int saisieInt(int max) { 
		int x=0;
		try {
			x=sc.nextInt();
		}catch(InputMismatchException e){
			String purge=sc.next();
			System.out.println("Veuillez entrer une valeur valide.");
		}
		if(x>max||x<0) {
			System.out.println("Choisir une valeur valide");
			x=0;
		}
		return x;
	}
	
	/*Fonction d'affichage des dices.*/
	public static void printDice(){  
		String s="Vos dices : ";
		for (int i=0;i<Dice.length;i++)
			s+=Dice[i]+" ";
		System.out.println(s);
	}
	
	
	
	public static IA createIA(int cpt){   /* Fonction de creation d'une IA.*/
			System.out.println("Entrer prenom du Player "+ cpt +":\n" );  /*On demande a l'utilisateur d'entrer son prenom et nom*/
			String surname=sc.next();
			System.out.println("Entrer nom du Player "+ cpt +":\n");
			String name=sc.next();
			IA j=new IA(surname,name,cpt);
			return j;
	}
	/*
	public static void IATurn(IA skynet,int turn) {
		System.out.println("Tour "+turn+" du Joueur "+skynet.numero+ " : IA "+skynet.printPlayer());
		throwDice(1);
		int cptJet=0;
		int[]cptTab=new int[6];
		int summa=7;
		int indice=0;
		int max=0;
		while(cptJet<3) {
			Arrays.sort(Dice);
			printDice();
			cptTab=skynet.numerisDice(Dice);
			max=skynet.maxDice(cptTab);
			if(turn<summa){
				if(skynet.aequalisDice(Dice,3)) {
					System.out.println("L'IA valide le Yams pour 50points");
					skynet.score[12]=50;
					cptJet=4;
				}else {
					indice=skynet.indiceMaxDice(cptTab,max);
					if(skynet.summa(Dice,indice)) {
						for(int i=0;i<Dice.length;i++) {
							if(Dice[i]!=(indice+1))
								Dice[i]=(int)(((7-1)*Math.random())+1);
						}
						cptJet+=1;
					}else{
						if(max==1){
							if(skynet.score[10]==null){
								if(skynet.haec(Dice,2)){
									System.out.println("L'IA valide la G-Suite pour 40 points");
									skynet.score[11]=40;
									cptJet=4;
								}else if(!skynet.haec(Dice,2)){
									Dice[5]=(int)(((7-1)*Math.random())+1);
									cptJet+=1;
								}
							}else if(skynet.score[10]!=null){
								if(skynet.score[indice]==null){
									for(int i=0;i<Dice.length;i++) {
										if(Dice[i]!=(indice+1))
											Dice[i]=(int)(((7-1)*Math.random())+1);
									}
									cptJet+=1;
								}else{
									for(int i=1;i<indice;i++){
										if(skynet.score[indice-i]==null){
											for(int j=0;j<Dice.length;j++) {
												if(Dice[j]!=(indice+1-i))
													Dice[j]=(int)(((7-1)*Math.random())+1);
											}
											cptJet+=1;
											break;
										}
									}
								}
							}
						}else if(max==2){
							if(skynet.score[indice]==null){
								for(int i=0;i<Dice.length;i++) {
									if(Dice[i]!=(indice+1))
										Dice[i]=(int)(((7-1)*Math.random())+1);
								}
								cptJet+=1;
							}else{
								for(int i=1;i<indice;i++){
									if(skynet.score[indice-i]==null){
										for(int j=0;j<Dice.length;j++) {
											if(Dice[j]!=(indice+1-i))
												Dice[j]=(int)(((7-1)*Math.random())+1);
										}
										cptJet+=1;
										break;
									}
								}
							}
						}
					}
				}
			}else if(turn==7){
				if (max>1){
					indice=skynet.indiceMaxDice(cptTab, max);
					for(int i=0;i<Dice.length;i++) {
						if(Dice[i]!=(indice+1))
							Dice[i]=(int)(((7-1)*Math.random())+1);
					}
					cptJet+=1;
				}else if(max==1){
					for(int i=0;i<Dice.length;i++) {
						Dice[i]=(int)(((7-1)*Math.random())+1);
					}
					cptJet+=1;
				}
			}else{
				for(int i=0;i<Dice.length;i++) {
					if(Dice[i]!=(indice+1))
						Dice[i]=(int)(((7-1)*Math.random())+1);
				}
				cptJet+=1;
			}
		}
		indice=skynet.indiceMaxDice(cptTab,max);
		if(skynet.score[indice]==null){
			for(int i=0;i<Dice.length;i++){
				if(Dice[i]==(indice+1))
					skynet.score[indice]+=(indice+1);
			}
			System.out.println("L'IA "+skynet.printPlayer()+" fait la somme des "+(indice+1)+" et marque "+skynet.score[indice]);
		}else{
			if(skynet.aequalisDice(Dice,1)){
				
			}
		}
	} */
	
}