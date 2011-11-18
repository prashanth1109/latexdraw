package net.sf.latexdraw.mapping;

import net.sf.latexdraw.glib.models.interfaces.IShape;
import net.sf.latexdraw.glib.views.Java2D.interfaces.IViewShape;
import net.sf.latexdraw.glib.views.Java2D.interfaces.View2DTK;

import org.malai.mapping.ISingleton;
import org.malai.mapping.MappingRegistry;
import org.malai.mapping.Singleton2SingletonMapping;

/**
 * Defines a mapping that link the temporary shape of the drawing to the
 * temporary view of the canvas.<br>
 *<br>
 * This file is part of LaTeXDraw<br>
 * Copyright (c) 2005-2011 Arnaud BLOUIN<br>
 *<br>
 *  LaTeXDraw is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.<br>
 *<br>
 *  LaTeXDraw is distributed without any warranty; without even the
 *  implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *  PURPOSE. See the GNU General Public License for more details.<br>
 *<br>
 * 05/15/10<br>
 * @author Arnaud BLOUIN
 * @since 3.0
 * @version 3.0
 */
public class TempShape2TempViewMapping extends Singleton2SingletonMapping<IShape, IViewShape<?>> {
	/**
	 * {@link Singleton2SingletonMapping#Singleton2SingletonMapping(ISingleton, ISingleton)}
	 */
	public TempShape2TempViewMapping(final ISingleton<IShape> source, final ISingleton<IViewShape<?>> target) {
		super(source, target);
	}



	@Override
	public void onObjectModified(final Object object) {
		// Nothing to do.
	}



	@Override
	public void onObjectReplaced(final ISingleton<?> object, final Object replacedObject) {
		targetObject.setValue(View2DTK.getFactory().generateView(sourceObject.getValue()));

		if(replacedObject!=null)
			MappingRegistry.REGISTRY.removeMappingsUsingSource(replacedObject);

		if(sourceObject.getValue()!=null)
			MappingRegistry.REGISTRY.addMapping(new Shape2ViewMapping(sourceObject.getValue(), targetObject.getValue()));
	}
}
