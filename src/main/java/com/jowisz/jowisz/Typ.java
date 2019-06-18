package com.jowisz.jowisz;

public class Typ
{
	String idTyp;
	String nazwaTyp;
	
	public Typ(String idTyp, String nazwaTyp)
	{
		this.idTyp = idTyp;
		this.nazwaTyp = nazwaTyp;
	}

	public String getIdTyp()
	{
		return idTyp;
	}

	public void setIdTyp(String idTyp)
	{
		this.idTyp = idTyp;
	}

	public String getNazwaTyp()
	{
		return nazwaTyp;
	}

	public void setNazwaTyp(String nazwaTyp)
	{
		this.nazwaTyp = nazwaTyp;
	}


	@Override
	public String toString()
	{
		return nazwaTyp ;
	}
	
}
