package com.mhawi.slider;

import org.primefaces.PrimeFaces;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@RequestScoped
public class ResetInputView {

	private String text1;

	private String text2;

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public void save() {

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null,"Data Saved"));
	}

	public void reset() {
		PrimeFaces.current().resetInputs("form:panel");
	}

	public void resetFail() {
		this.text1 = null;
		this.text2 = null;

		FacesMessage msg = new FacesMessage("Model reset, but it won't work properly.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}