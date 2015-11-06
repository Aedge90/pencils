package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

public class PencilPhotoFactory extends PhotoFactory {

	
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     */
    public static synchronized PhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic PhotoFactory").toString());
            setInstance(new PencilPhotoFactory());
        }

        return instance;
    }

    protected static synchronized void setInstance(PhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize PhotoFactory twice");
        }
        instance = photoFactory;
    }
	
	/**
	 * @methodtype factory
	 */
	@Override
	public PencilPhoto createPhoto() {
		return new PencilPhoto();
	}

	/**
	 * Creates a new photo with the specified id
	 */
	@Override
	public PencilPhoto createPhoto(PhotoId id) {
		return new PencilPhoto(id);
	}

}
