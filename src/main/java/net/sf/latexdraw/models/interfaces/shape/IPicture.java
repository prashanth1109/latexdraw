/*
 * This file is part of LaTeXDraw.
 * Copyright (c) 2005-2018 Arnaud BLOUIN
 * LaTeXDraw is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * LaTeXDraw is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 */
package net.sf.latexdraw.models.interfaces.shape;

import javafx.scene.image.Image;

/**
 * The API for pictures.
 * @author Arnaud Blouin
 */
public interface IPicture extends IPositionShape {
	/**
	 * @return the pathSource.
	 */
	String getPathSource();

	/**
	 * Sets the new picture.
	 * @param pathSource the pathSource to set.
	 */
	void setPathSource(final String pathSource);

	/**
	 * @return the pathTarget.
	 */
	String getPathTarget();

	/**
	 * @return the image.
	 */
	Image getImage();

	@Override
	IPicture duplicate();
}