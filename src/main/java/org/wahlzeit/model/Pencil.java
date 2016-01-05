/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * A photo represents a user-provided (uploaded) photo.
 */
@Entity
public class Pencil extends DataObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3890781687748616070L;
	
	private String thickness;
	
	private String color;
	
	private PencilType type = null;

	@Id
	Long idLong;
	@Parent
	Key parent = ObjectManager.applicationRootKey;
	
	Pencil(PencilType type){
		this.type = type;
	}

	/**
	 * @methodtype get
	 */
	public String getThickness() {
		return thickness;
	}

	/**
	 * @methodtype set
	 */
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}

	/**
	 * @methodtype get
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @methodtype set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
}
