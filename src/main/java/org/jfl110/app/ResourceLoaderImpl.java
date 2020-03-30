package org.jfl110.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.inject.Inject;

import org.jfl110.util.ExceptionUtils;

/**
 * Implementation of ResourceLoader that uses ClassLoader
 * 
 * @author jim
 */
class ResourceLoaderImpl implements ResourceLoader {

	private final Logger logger;

	@Inject
	ResourceLoaderImpl(Logger logger) {
		this.logger = logger;
	}


	@Override
	public String loadAsString(String resourceName) {
		return loadAsStringIfExists(resourceName).orElseThrow(() -> new IllegalArgumentException("No resource named [" + resourceName + "] found"));
	}
	
	
	@Override
	public InputStream loadAsInputStream(String resourceName) {
		URL resource = getResourceURL(resourceName);
		logger.log("Loading resource named [" + resourceName + "] at [" + resource + "]");
		if(resource == null) {
			throw new IllegalArgumentException("No resource named [" + resourceName + "] found");
		}
		return ExceptionUtils.doRethrowing(() -> resource.openStream());
	}


	@Override
	public Optional<String> loadAsStringIfExists(String resourceName) {
		URL resource = getResourceURL(resourceName);
		logger.log("Loading resource named [" + resourceName + "] at [" + resource + "]");
		try {
			return resource == null ? Optional.empty() : Optional.of(new String(Files.readAllBytes(Paths.get(resource.toURI()))));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}
	
	
	private URL getResourceURL(String resourceName){
		URL resource = getResource(resourceName, getClass());
		if (resource == null) {
			resource = getResource("resource" + ((resourceName.length() == 0 || resourceName.charAt(0) != '/') ? "/" : "") + resourceName,
					getClass());
		}
		return resource;
	}


	/**
	 * /* Copyright 2002-2003,2009 The Apache Software Foundation.
	 * 
	 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
	 * use this file except in compliance with the License. You may obtain a copy of
	 * the License at
	 * 
	 * http://www.apache.org/licenses/LICENSE-2.0
	 * 
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
	 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
	 * License for the specific language governing permissions and limitations under
	 * the License.
	 *
	 * package com.opensymphony.xwork2.util; Load a given resource.
	 *
	 * This method will try to load the resource using the following methods (in
	 * order):
	 * <ul>
	 * <li>From Thread.currentThread().getContextClassLoader()
	 * <li>From ClassLoaderUtil.class.getClassLoader()
	 * <li>callingClass.getClassLoader()
	 * </ul>
	 *
	 * @param resourceName
	 *            The name IllegalStateException("Unable to call ")of the resource
	 *            to load
	 * @param callingClass
	 *            The Class object of the calling object
	 */
	private URL getResource(String resourceName, Class<?> callingClass) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(resourceName);

		if (url == null) {
			url = ResourceLoaderImpl.class.getClassLoader().getResource(resourceName);
		}

		if (url == null) {
			ClassLoader cl = callingClass.getClassLoader();

			if (cl != null) {
				url = cl.getResource(resourceName);
			}
		}

		if ((url == null) && (resourceName != null) && ((resourceName.length() == 0) || (resourceName.charAt(0) != '/'))) {
			return getResource('/' + resourceName, callingClass);
		}

		return url;
	}
}