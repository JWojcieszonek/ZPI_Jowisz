package com.jowisz.jowisz;

public class Producent implements Comparable<Producent>
{
	String idProd;
	String nazwaProd;
	
	public Producent(String idProd, String nazwaProd)
	{
		this.idProd = idProd;
		this.nazwaProd = nazwaProd;
	}

	public String getIdProd()
	{
		return idProd;
	}

	public void setIdProd(String idProd)
	{
		this.idProd = idProd;
	}

	public String getNazwaProd()
	{
		return nazwaProd;
	}

	public void setNazwaProd(String nazwaProd)
	{
		this.nazwaProd = nazwaProd;
	}

	@Override
	public String toString()
	{
		return   nazwaProd ;
	}

	@Override
	public int compareTo(Producent producent)
	{
		return this.nazwaProd.compareTo(producent.nazwaProd);
	}

}
