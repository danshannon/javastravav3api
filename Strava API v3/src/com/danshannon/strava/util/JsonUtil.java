package com.danshannon.strava.util;

import java.io.InputStream;

import com.danshannon.strava.service.exception.ServiceException;

/**
 * <p>Abstracted JSON utilities for serialisation and de-serialisation, so we're not reliant on any one JSON library for the basic code</p>
 * 
 * @author Dan Shannon
 */
public interface JsonUtil {

	public abstract <T> T deserialise(InputStream is, Class<T> class1) throws ServiceException;

	public abstract <T> T deserialise(String is, Class<T> class1) throws ServiceException;

	public abstract <T> String serialise(T object) throws ServiceException;

}