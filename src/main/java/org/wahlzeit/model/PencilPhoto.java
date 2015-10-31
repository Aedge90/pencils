package org.wahlzeit.model;

public class PencilPhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String typeOfPencil;
	
	private String pencilThickness;
	
	private String pencilColor;
	
	/**
	 * @methodtype set
	 */
	void setTypeOfPencil (String type){
		this.typeOfPencil = type;
	}
	
	/**
	 * @methodtype set
	 */
	void setPencilThickness(String thickness){
		this.pencilThickness = thickness;
	}
	
	/**
	 * @methodtype set
	 */
	void setPencilColor(String color){
		this.pencilColor = color;
	}
	
	/**
	 * @methodtype get
	 */
	String getTypeOfPencil (){
		return this.typeOfPencil;
	}
	
	/**
	 * @methodtype get
	 */
	String getPencilThickness(){
		return this.pencilThickness;
	}
	
	/**
	 * @methodtype get
	 */
	String getPencilColor(){
		return this.pencilColor;
	}

}
