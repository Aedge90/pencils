package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class PencilPhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Pencil pencil;
	
	
	public PencilPhoto() {
		super();
	}
	
	public PencilPhoto(PhotoId id) {
		super(id);
	}
	
	/**
	 * @methodtype set
	 */
	void setPencil(Pencil pencil){
		this.pencil = pencil;
	}
	
	/**
	 * @methodtype get
	 */
	Pencil getPencil(){
		return this.pencil;
	}

}
