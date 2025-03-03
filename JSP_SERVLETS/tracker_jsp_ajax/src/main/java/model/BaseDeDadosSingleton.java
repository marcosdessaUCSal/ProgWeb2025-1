package model;

import java.util.ArrayList;

public class BaseDeDadosSingleton {
	private static BaseDeDadosSingleton instance = null;
	
	private ArrayList<Rastreio> rastreios;
	
	private BaseDeDadosSingleton() {
		this.rastreios = new ArrayList<Rastreio>();
		
	}
	
	public static BaseDeDadosSingleton getInstance() {
		if (instance == null) {
			instance = new BaseDeDadosSingleton();
		}
		return instance;
	}
	
	public Rastreio procurarPorCodigo(String cod) {
		Rastreio rastr = null;
		for (Rastreio r : rastreios) {
			if (cod.equals(r.getCodigo())) {
				rastr = r;
			}
		}
		return rastr;
	}
	
	public void inserirRastreio(Rastreio rastreio) {
		this.rastreios.add(rastreio);
	}

}
