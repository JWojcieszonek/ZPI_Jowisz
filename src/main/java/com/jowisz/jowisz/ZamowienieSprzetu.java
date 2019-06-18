package com.jowisz.jowisz;

public class ZamowienieSprzetu
{
	int idZamS;
	int idZam;
	int idSpr;
	int Liczba;
	double cena;
	
	public ZamowienieSprzetu(int idZamS, int idZam, int idSpr, int liczba, double cena)
	{
		this.idZamS = idZamS;
		this.idZam = idZam;
		this.idSpr = idSpr;
		Liczba = liczba;
		this.cena = cena;
	}

	public int getIdZamS()
	{
		return idZamS;
	}

	public void setIdZamS(int idZamS)
	{
		this.idZamS = idZamS;
	}

	public int getIdZam()
	{
		return idZam;
	}

	public void setIdZam(int idZam)
	{
		this.idZam = idZam;
	}

	public int getIdSpr()
	{
		return idSpr;
	}

	public void setIdSpr(int idSpr)
	{
		this.idSpr = idSpr;
	}

	public int getLiczba()
	{
		return Liczba;
	}

	public void setLiczba(int liczba)
	{
		Liczba = liczba;
	}

	public double getCena()
	{
		return cena;
	}

	public void setCena(double cena)
	{
		this.cena = cena;
	}
	
	
}
