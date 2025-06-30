/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Morpion;

/**
 *
 * @author Patricia
 */
public class PlateauMorpion {
    private char[][] plateau;//Grille 3*3pour stocker les X et O
    private char joueurActuel;//Joueur dont c le tour (X ou O)
    
    public PlateauMorpion(){
        plateau=new char[3][3];//Creation de la grille
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                plateau[i][j]=' ';
        joueurActuel='X';//X commence
    }
    public boolean joueurCoup(int ligne,int col){
        if(plateau[ligne][col]==' '){
            plateau[ligne][col]=joueurActuel;
            return true;
        }
        return false;
    }
    public boolean verifierVictoire(){// POur verifier la victoire
        for(int i=0;i<3;i++){
            //LIgnes
            if(plateau[i][0]==joueurActuel && plateau[i][1]==joueurActuel && plateau[i][2]==joueurActuel)
                return true;
            //Colones
            if(plateau[0][i]==joueurActuel && plateau[1][i]==joueurActuel && plateau[2][i]==joueurActuel)
               
                return true;
             }
        //Diagonals
                
         if (plateau[0][0]==joueurActuel && plateau[1][1]==joueurActuel && plateau[2][2]==joueurActuel){
            return true;
        }
        if (plateau[0][2]==joueurActuel && plateau[1][1]==joueurActuel && plateau[2][0]==joueurActuel){
            return true;
        }
        return false;
    }     
    
    public boolean verifierMatchNul(){//Verifier le macth nul
        for(int i=0;i<3;i++)//Repeter une action plusieurs foi selon le nombre donnee
            for(int j=0;j<3;j++)
                if(plateau[i][j]==' ')
                    return false;
        return true;
    }
    public void changerJoueur(){//Changer de joueurs
        joueurActuel=(joueurActuel=='X')?'O': 'X';
    }
    public char getJoueurActuel(){//Prendre le joueur
        return joueurActuel;
    }
    public char getSymboleCase(int ligne,int col){//Prendre le symbol X ou O
        return plateau[ligne][col];
    }
    public void reinitialiserPlateau(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                plateau[i][j]=' ';
        joueurActuel='X';
    }

    
}
