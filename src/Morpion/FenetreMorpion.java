/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Morpion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;
/**
 *
 * @author Patricia
 */
public class FenetreMorpion extends JFrame implements ActionListener {//Defini une class public qui herite de JFrame{
    private PlateauMorpion jeu;
    private JButton[][]boutonsCasses;//JButton est un tableau en 2D (3*3) qui represente les casses
    private JLabel messageStatut;//JLabel pour afficher les messages 
    private JButton boutonReset;//JButton un bouton pour reinitialiser le jeu
    private int scoreX=0;// compteur des sccores POUR X
    private int scoreO=0;// compteur des sccores POUR O
    private JLabel scoreLabel;// afficher le score
    
    public FenetreMorpion(){
        jeu=new PlateauMorpion();//pour creer le nouveau jeu
        boutonsCasses=new JButton[3][3];// pour initialiser le tableau de bouton avec 3 lignes et 3 colones
        messageStatut=new JLabel(" C'est au tour de X ");
        boutonReset=new JButton(" Nouvelle partie ");
        boutonReset.addActionListener(this);
        add(boutonReset, BorderLayout.SOUTH);// pour ajouter le bouton en bas
        
        setTitle(" Jeu de Morpion (tic-tac-toe)");
        setSize(400,450);// definir taille de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fermer l'appli
        setLayout(new BorderLayout());// utilisation d'un BorderLayout pour la fenetre principal
        
        JPanel panneauGrille=new JPanel();
        panneauGrille.setLayout(new GridLayout(3,3));// gille de 3 lignes et 3 colones
        Font font=new Font("Arial", Font.BOLD,60);//police des boutons(gros et gras)
        
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                boutonsCasses[i][j]=new JButton("");
                boutonsCasses[i][j].setFont(font);
                boutonsCasses[i][j].addActionListener(this);
                panneauGrille.add(boutonsCasses[i][j]);
            }
        }
        JPanel panneauBas=new JPanel(new BorderLayout());// un panneau avec BorderLayout pour le bas
        messageStatut.setHorizontalAlignment(SwingConstants.CENTER);//centre le text du message
        messageStatut.setFont(new Font("Arial", Font.PLAIN, 18));//police plus ptt pour le message 
        panneauBas.add(messageStatut,BorderLayout.CENTER);//le message au centre du panneau bas
        panneauBas.add(boutonReset,BorderLayout.EAST);// afficher un bouton a droite du panneau bas
        add(panneauGrille,BorderLayout.CENTER);
        add(panneauBas, BorderLayout.SOUTH);
        scoreLabel=new JLabel("Score X:0 | O:0");
        add(scoreLabel, BorderLayout.NORTH);
        setVisible(true);// pour rendre visibe
    }
    @Override
    public void actionPerformed(ActionEvent e){
        Object source=e.getSource();
        if(source==boutonReset){
            reinitialiserJeu();
            return;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(e.getSource()==boutonsCasses[i][j]){
                    if(jeu.joueurCoup(i, j)){
                        char joueur=jeu.getJoueurActuel();
                        boutonsCasses[i][j].setText(String.valueOf(jeu.getJoueurActuel()));
                         if(joueur=='X'){
                             boutonsCasses[i][j].setForeground(Color.ORANGE);//pour changer la couleur des X en orange
                         }else{
                             boutonsCasses[i][j].setForeground(Color.GREEN);// pour changer la couleur des O en vert
                         }
                       if(jeu.verifierVictoire()){
                           messageStatut.setText(jeu.getJoueurActuel()+" a gagné ");
                           desactiverBoutons();
                           if(joueur=='X')scoreX++;
                           else scoreO++;
                           scoreLabel.setText("Score X:"+scoreX+" | O:"+scoreO);
                           
                       } else if(jeu.verifierMatchNul()){
                           messageStatut.setText("Match nul");
                           desactiverBoutons();
                       } else{
                           jeu.changerJoueur();
                           messageStatut.setText("C'est au tour de"+jeu.getJoueurActuel());
                       }
                    }
                }
            }
        }    
    }
   private void reinitialiserJeu(){
       jeu.reinitialiserPlateau();
       for(int i=0;i<3;i++)
           for(int j=0;j<3;j++){
               boutonsCasses[i][j].setText("");
               boutonsCasses[i][j].setEnabled(true);
           }
       messageStatut.setText("C'est au tour de "+jeu.getJoueurActuel());
   }
   private void desactiverBoutons(){
       for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
               boutonsCasses[i][j].setEnabled(false);
           }      
       }       
   }
   public static void main(String[]args){
       SwingUtilities.invokeLater(()->new FenetreMorpion());
   }

    
}
