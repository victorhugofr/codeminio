package com.codeminio.model;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Morador extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String apartamento;

	
	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((apartamento == null) ? 0 : apartamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Morador other = (Morador) obj;
		if (apartamento == null) {
			if (other.apartamento != null)
				return false;
		} else if (!apartamento.equals(other.apartamento))
			return false;
		return true;
	}

}
