package com.jowisz.jowisz;

public class Sprzet implements Comparable<Sprzet>
{
	String idSpr;
	String nazwa;
	Double cena;
	String opis;
	int liczbaSzt;
	int producent;
	int typ;
	
	public Sprzet(String idSpr, String nazwa, Double cena, String opis, int liczbaSzt, int producent, int typ)
	{
		this.idSpr = idSpr;
		this.nazwa = nazwa;
		this.cena = cena;
		this.opis = opis;
		this.liczbaSzt = liczbaSzt;
		this.producent = producent;
		this.typ = typ;
	}

	public String getIdSpr()
	{
		return idSpr;
	}

	public void setIdSpr(String idSpr)
	{
		this.idSpr = idSpr;
	}

	public String getNazwa()
	{
		return nazwa;
	}

	public void setNazwa(String nazwa)
	{
		this.nazwa = nazwa;
	}

	public Double getCena()
	{
		return cena;
	}

	public void setCena(Double cena)
	{
		this.cena = cena;
	}

	public String getOpis()
	{
		return opis;
	}

	public void setOpis(String opis)
	{
		this.opis = opis;
	}

	public int getLiczbaSzt()
	{
		return liczbaSzt;
	}

	public void setLiczbaSzt(int liczbaSzt)
	{
		this.liczbaSzt = liczbaSzt;
	}

	public int getProducent()
	{
		return producent;
	}

	public void setProducent(int producent)
	{
		this.producent = producent;
	}

	public int getTyp()
	{
		return typ;
	}

	public void setTyp(int typ)
	{
		this.typ = typ;
	}

	@Override
	public String toString()
	{
		return "Sprzet [idSpr=" + idSpr + ", nazwa=" + nazwa + ", cena=" + cena + ", opis=" + opis + ", liczbaSzt="
				+ liczbaSzt + ", producent=" + producent + ", typ=" + typ + "]";
	}

	@Override
	public int compareTo(Sprzet sprzet)
	{
		return this.nazwa.compareTo(sprzet.nazwa);
	}
}
