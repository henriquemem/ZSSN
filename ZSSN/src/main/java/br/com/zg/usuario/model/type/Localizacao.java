package br.com.zg.usuario.model.type;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Localizacao implements Serializable{

	private static final long serialVersionUID = 5939667760488260252L;
	
	private float latitude; 
	private float longitude;
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
}
