package com.jowisz.jowisz;

import java.sql.Date;

public class Zamowienie implements Comparable<Zamowienie>
{

	String idZam;
	String nazw;
	String imie;
	String adres;
	String tel;
	String dataZam;
	String czyOplacono;
	int idK;
	int idPlat;
	
	public Zamowienie(String idZam, String nazwisko, String imie, String adres,String tel, String data,String czyOplacono,int idKlienta, int idPlat)
	{
		this.idZam=idZam;
		this.nazw=nazwisko;
		this.imie=imie;
		this.adres=adres;
		this.tel=tel;
		this.dataZam=data;
		this.czyOplacono=czyOplacono;
		this.idK=idKlienta;
		this.idPlat=idPlat;
	}
	
	
	public String getIdZam() {
		return idZam;
	}


	public void setIdZam(String idZam) {
		this.idZam = idZam;
	}


	public String getNazw() {
		return nazw;
	}


	public void setNazwisko(String nazwisko) {
		nazw = nazwisko;
	}


	public String getImie() {
		return imie;
	}


	public void setImie(String imie) {
		this.imie = imie;
	}


	public String getAdres() {
		return adres;
	}


	public void setAdres(String adres) {
		this.adres = adres;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getDataZam() {
		return dataZam;
	}


	public void setData(String data) {
		dataZam = data;
	}


	public String getCzyOplacono() {
		return czyOplacono;
	}


	public void setCzyOplacono(String czyOplacono) {
		this.czyOplacono = czyOplacono;
	}


	public int getIdK() {
		return idK;
	}


	public void setIdK(int idKlienta) {
		idK = idKlienta;
	}


	public int getIdPlat() {
		return idPlat;
	}


	public void setIdPlat(int idPlat) {
		this.idPlat = idPlat;
	}

	

	@Override
	public int compareTo(Zamowienie o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
