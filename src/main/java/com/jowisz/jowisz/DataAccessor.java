package com.jowisz.jowisz;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataAccessor
{
	private Connection connection ;

    //public DataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException
    public DataAccessor(String driverClassName, String dbURL) throws SQLException, ClassNotFoundException 
    {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, "root", "");
    }

    public void shutdown() throws SQLException 
    {
        if (connection != null) 
        {
            connection.close();
        }
    }
    
    public ObservableList<Typ> getTypList() throws SQLException 
    {
    	System.out.println("test");
        try 
        (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM TYPSPRZETU ");
        )
        {
            ObservableList<Typ> typList = FXCollections.observableArrayList();
            while (rs.next()) 
            {
            	String idTyp = rs.getString("idTyp");
                String nazwaTyp = rs.getString("nazwaTyp");
                Typ typ = new Typ(idTyp, nazwaTyp);
                
                System.out.println(typ.toString());
                
                typList.add(typ);
            }
            return typList ;
        } 
    }
    
    
    public List<String> getTypNames() throws SQLException 
    {
    	System.out.println("test");
        try 
        (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM TYPSPRZETU ");
        )
        {
            List<String> typList = new ArrayList<>();
            while (rs.next()) 
            {
                String nazwaTyp = rs.getString("nazwaTyp");
                
                typList.add(nazwaTyp);
            }
            return typList ;
        } 
    }
    
    public List<Producent> getProducentList() throws SQLException 
    {
    	System.out.println("Pobrano liste producentow.");
        try 
        (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM producent");
        )
        {
            List<Producent> producentList = new ArrayList<>();
            while (rs.next()) 
            {
            	
            	String idProd = rs.getString("idProd");
                String nazwaProd = rs.getString("nazwaProd");
                Producent producent = new Producent(idProd, nazwaProd);
                
                System.out.println(producent.toString());
                
                producentList.add(producent);
            }
            return producentList ;
        } 
    }
    
    public ObservableList<Producent> getProducentObsList() throws SQLException 
    {
    	System.out.println("Pobrano liste producentow.");
        try 
        (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM producent");
        )
        {
            ObservableList<Producent> producentList = FXCollections.observableArrayList();
            while (rs.next()) 
            {
            	
            	String idProd = rs.getString("idProd");
                String nazwaProd = rs.getString("nazwaProd");
                Producent producent = new Producent(idProd, nazwaProd);
                
                System.out.println(producent.toString());
                
                producentList.add(producent);
            }
            return producentList ;
        } 
    }
    
    public List<Producent> getProducentListWithType(int typId) throws SQLException 
    {
    	System.out.println("Pobrano liste producentow.");
    	System.out.println("TYPID: "+typId);
        try 
        (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery(String.format("SELECT Producent.* "
            		+ "FROM producent RIGHT JOIN sprzet ON producent.IdProd = sprzet.IdProd "
            		+ "WHERE sprzet.IdTyp=%d GROUP BY producent.IdProd",typId));
        )
        {
            List<Producent> producentList = new ArrayList<>();
            while (rs.next()) 
            {
            	String idProd = rs.getString("idProd");
                String nazwaProd = rs.getString("nazwaProd");
                Producent producent = new Producent(idProd, nazwaProd);
                
                System.out.println(producent.toString());
                
                producentList.add(producent);
            }
            return producentList ;
        } 
    }
    

    
    public ObservableList<Sprzet> getSprzetList(Producent produc, int typId) throws SQLException 
    {
    	System.out.println("Pobrano liste sprzetow dla producenta " + produc.getNazwaProd());
    	String query= String.format("SELECT * FROM Sprzet WHERE IdProd = %s AND IdTyp = %d",produc.getIdProd(),typId);
        try 
        (
            Statement stmnt = connection.createStatement();        		
            ResultSet rs = stmnt.executeQuery(query);
        )
        {
            ObservableList<Sprzet> sprzetList =FXCollections.observableArrayList();
            while (rs.next()) 
            {
            	String idSpr = rs.getString("idSpr");
                String nazwa = rs.getString("nazwa");
                Double cena = rs.getDouble("cena");
                String opis = rs.getString("opis");
                int liczbaSzt = rs.getInt("liczbaSzt");
                int producent = rs.getInt("idProd");
                int typ = rs.getInt("idTyp");
                Sprzet sprzet = new Sprzet(idSpr, nazwa, cena, opis, liczbaSzt, producent, typ);
                
                System.out.println(sprzet.toString());
                
                sprzetList.add(sprzet);
            }
            return sprzetList ;
        } 
    }
    
    public void addProducent(Producent producent) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String addSQL = "INSERT INTO PRODUCENT (IdProd, NazwaProd) "
    				+ "VALUES ("
    				+ "NULL" + ","
    				+ "'" + producent.getNazwaProd() + "'"
    				+ ");";
            int rs = stmnt.executeUpdate(addSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }
    }
    
    public void updateProducent(Producent producent) throws SQLException 
    {
    	
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String updateSQL = "UPDATE PRODUCENT "
    				+ "SET "
    				+ "IdProd = '"
    				+ producent.getIdProd() + "',"
    				+ "NazwaProd = '" + producent.getNazwaProd() + "'"
    				+ "WHERE IdProd = '" + producent.getIdProd() + "'";
            int rs = stmnt.executeUpdate(updateSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę edytowac danych " + e.getMessage());
        }
    }
    
    public void deleteProducent(String idProd) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String deleteSQL = "DELETE FROM PRODUCENT "
    				+ "WHERE IdProd = " + idProd;
            int rs = stmnt.executeUpdate(deleteSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę usunac danych " + e.getMessage());
        }
    }
    
    // -----------------------------------------------
    // -----------------------------------------------
    // -----------------------------------------------
    
    public void addSprzet(Sprzet sprzet) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String addSQL = "INSERT INTO SPRZET (IdSpr, Nazwa, Opis, Cena, LiczbaSzt, IdProd, IdTyp) "
    				+ "VALUES ("
    				+ "0" + ","
    				+ "'" + sprzet.getNazwa() + "',"
    				+ "'" + sprzet.getOpis() + "',"
    				+ "'" + sprzet.getCena() + "',"
    				+ "'" + sprzet.getLiczbaSzt() + "',"
    				+ "'" + sprzet.getProducent() + "',"
    				+ "'" + sprzet.getTyp() + "'"
    				+ ");";
            int rs = stmnt.executeUpdate(addSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }
    }
    
    public void updateSprzet(Sprzet sprzet) throws SQLException 
    {
    	
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String updateSQL = "UPDATE SPRZET "
    				+ "SET "
    				+ "IdSpr = '" + sprzet.getIdSpr() + "',"
    				+ "Nazwa = '" + sprzet.getNazwa() + "',"
    				+ "Opis = '" + sprzet.getOpis() + "',"
    	    		+ "Cena = '" + sprzet.getCena() + "',"
    	    		+ "LiczbaSzt = '" + sprzet.getLiczbaSzt() + "',"
    	    	    + "IdProd = '" + sprzet.getProducent() + "',"
    	    	    + "IdTyp = '" + sprzet.getTyp() + "'"
    				+ "WHERE IdSpr = '" + sprzet.getIdSpr() + "'";
            int rs = stmnt.executeUpdate(updateSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę edytowac danych " + e.getMessage());
        }
    }
    
    public void deleteSprzet(String idSpr) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String deleteSQL = "DELETE FROM SPRZET "
    				+ "WHERE IdSpr = " + idSpr;
            int rs = stmnt.executeUpdate(deleteSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę usunac danych " + e.getMessage());
        }
    }
    
    // -----------------------------------------------
    // -----------------------------------------------
    // -----------------------------------------------
    
    public void addZamowienie(Zamowienie zamowienie) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String addSQL = "INSERT INTO ZAMOWIENIE (IdZam, Nazw, Imie, Adres, Tel, DataZam, CzyOplacono, IdK, IdPlat) "
    				+ "VALUES ("
    				+ "'77'" + ","
    				+ "'" + zamowienie.getNazw() + "',"
    				+ "'" + zamowienie.getImie() + "',"
    				+ "'" + zamowienie.getAdres() + "',"
    				+ "'" + zamowienie.getTel() + "',"
    				+ "'" + zamowienie.getDataZam() + "',"
    				+ "'" + zamowienie.getCzyOplacono() + "',"
    				+ "'" + zamowienie.getIdK() + "',"
    				+ "'" + zamowienie.getIdPlat() + "'"
    				+ ");";
            int rs = stmnt.executeUpdate(addSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }
    }
    
    public ObservableList<Zamowienie> getZamowienieList() throws SQLException
    {
    	System.out.println("Pobrano liste zamówień.");
        try 
        (
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM zamowienie");
        )
        {
            ObservableList<Zamowienie> zamowienieList = FXCollections.observableArrayList();
            while (rs.next()) 
            {
                
                String idZam = rs.getString("idZam");
            	String nazw = rs.getString("nazw");
            	String imie = rs.getString("imie");
            	String adres = rs.getString("adres");
            	String tel= rs.getString("tel");
            	String dataZam = rs.getString("dataZam");
            	String czyOplacono = rs.getString("czyOplacono");
            	int idK = rs.getInt("idK");
            	int idPlat = rs.getInt("idPlat");
                
            
            	
            	Zamowienie zamowienie = new Zamowienie(idZam, nazw,imie,adres,tel,dataZam,czyOplacono,idK,idPlat);
                zamowienieList.add(zamowienie);
            }
            return zamowienieList ;
        } 
    }
    
    public void updateZamowienie(Zamowienie zamowienie) throws SQLException 
    {
    	
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String updateSQL = "UPDATE ZAMOWIENIE "
    				+ "SET "
    				+ "IdZam = '" + zamowienie.getIdZam() + "',"
    				+ "Nazw = '" + zamowienie.getNazw() + "',"
    				+ "Imie = '" + zamowienie.getImie() + "',"
    	    		+ "Adres = '" + zamowienie.getAdres() + "',"
    	    		+ "Tel = '" + zamowienie.getTel() + "',"
    	    	    + "DataZam = '" + zamowienie.getDataZam() + "',"
    	    	    + "CzyOplacono = '" + zamowienie.getCzyOplacono() + "',"
    	    	    + "IdK = '" + zamowienie.getIdK() + "',"
    	    	    + "IdPlat = '" + zamowienie.getIdPlat() + "'"
    				+ "WHERE IdZam = '" + zamowienie.getIdZam() + "'";
            int rs = stmnt.executeUpdate(updateSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę edytowac danych " + e.getMessage());
        }
    }
    
    public void deleteZamowienie(String idZam) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String deleteSQL = "DELETE FROM ZAMOWIENIE "
    				+ "WHERE IdZam = " + idZam;
            int rs = stmnt.executeUpdate(deleteSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę usunac danych " + e.getMessage());
        }
    }
    
    // -----------------------------------------------
    // -----------------------------------------------
    // -----------------------------------------------
    
    
    public void addZamowienieSprzetu(ZamowienieSprzetu zamowienieSprzetu) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String addSQL = "INSERT INTO ZAMOWIENIESPRZETU (IdZamS, IdZam, IdSpr, Liczba, Cena) "
    				+ "VALUES ("
    				+ "'55'" + ","
    				+ "'" + zamowienieSprzetu.getIdZam() + "',"
    				+ "'" + zamowienieSprzetu.getIdSpr() + "',"
    				+ "'" + zamowienieSprzetu.getLiczba() + "',"
    				+ "'" + zamowienieSprzetu.getCena() + "'"
    				+ ");";
            int rs = stmnt.executeUpdate(addSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę dodać danych " + e.getMessage());
        }
    }
    
    public void updateZamowienieSprzetu(ZamowienieSprzetu zamowienieSprzetu) throws SQLException 
    {
    	
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String updateSQL = "UPDATE ZAMOWIENIESPRZETU "
    				+ "SET "
    				+ "IdZamS = '" + zamowienieSprzetu.getIdZamS() + "',"
    				+ "IdZam = '" + zamowienieSprzetu.getIdZam() + "',"
    				+ "IdSpr = '" + zamowienieSprzetu.getIdSpr() + "',"
    	    		+ "Liczba = '" + zamowienieSprzetu.getLiczba() + "',"
    	    	    + "Cena = '" + zamowienieSprzetu.getCena() + "'"
    				+ "WHERE IdZamS = '" + zamowienieSprzetu.getIdZamS() + "'";
            int rs = stmnt.executeUpdate(updateSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę edytowac danych " + e.getMessage());
        }
    }
    
    public void deleteZamowienieSprzetu(String idZamSpr) throws SQLException 
    {
    	try 
        {
            Statement stmnt = connection.createStatement();
    		String deleteSQL = "DELETE FROM ZAMOWIENIESPRZETU "
    				+ "WHERE IdZamS = " + idZamSpr;
            int rs = stmnt.executeUpdate(deleteSQL);
        }
        catch (Exception e) 
        {
            System.out.println("Nie mogę usunac danych " + e.getMessage());
        }
    }
    
    // other methods, eg. addPerson(...) etc
}