package fr.tfranchesquin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class main {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/aventure_pokemon?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String DATABASE_LOGIN = "root";
	private static final String DATABASE_SECRET = "activ";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InsertDresseur();
		InsertPokemon();
	//	SelectPokemon();
		
	}

	private static final void SelectPokemon() {
		try {
			Connection connexion = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_SECRET);
		
			String requette = "SELECT * FROM pokemon";
			Statement executeur = connexion.createStatement();
			
			ResultSet resultat = executeur.executeQuery(requette);
			
			while (resultat.next()) {
				System.out.println(resultat.getString("nom_pokemon"));
			}
		
			resultat.close();
			executeur.close();
			connexion.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final void InsertDresseur() {

		Scanner input = new Scanner (System.in);
		
		System.out.println("Choisissez un Pseudo pour votre Dresseur");
		String nom_dresseur = input.nextLine();
		
		try {
			Connection connexion = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_SECRET);
		String requette ="INSERT INTO dresseur (nom_dresseur) VALUES (?)";
		PreparedStatement preparateur = connexion.prepareStatement(requette);
		preparateur.setString(1, nom_dresseur);
		
		preparateur.execute();
		preparateur.close();
		connexion.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private static final void InsertPokemon() {
		Scanner input = new Scanner (System.in);
		
		System.out.println("Donnez le nom du pokemon ");
		String nom_pokemon = input.nextLine();
		
		System.out.println("Donnez le code correspondant au type du pokemon : (1,\"Feu\"),\r\n" + 
				"(2,\"Normal\"),\r\n" + 
				"(3,\"Eau\"),\r\n" + 
				"(4,\"Plante\"),\r\n" + 
				"(5,\"Electrik\"),\r\n" + 
				"(6,\"Glace\"),\r\n" + 
				"(7,\"Combat\"),\r\n" + 
				"(8,\"Poison\"),\r\n" + 
				"(9,\"Sol\"),\r\n" + 
				"(10,\"Vol\"),\r\n" + 
				"(11,\"Psy\"),\r\n" + 
				"(12,\"Insecte\"),\r\n" + 
				"(13,\"Roche\"),\r\n" + 
				"(14,\"Spectre\"),\r\n" + 
				"(15,\"Dragon\"),\r\n" + 
				"(16,\"Tenebres\"),\r\n" + 
				"(17,\"Acier\");");
		int ID_type = input.nextInt();
		
		System.out.println("Donnez le N° national du pokemon ");
		int NO_national = input.nextInt();
		
		try {
			Connection connexion = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_SECRET);
		String requette ="INSERT INTO pokemon (nom_pokemon, ID_type, NO_national) VALUES (?,?,?)";
		PreparedStatement preparateur = connexion.prepareStatement(requette);
		preparateur.setString(1, nom_pokemon);
		preparateur.setInt(2, ID_type);
		preparateur.setInt(3, NO_national);
		
		preparateur.execute();
		preparateur.close();
		connexion.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
