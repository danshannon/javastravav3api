package stravajava.util;

import java.io.InputStream;

import stravajava.util.exception.JsonSerialisationException;

/**
 * <p>Abstracted JSON utilities for serialisation and de-serialisation, so we're not reliant on any one JSON library for the basic code</p>
 * 
 * @author Dan Shannon
 */
public interface JsonUtil {

	public abstract <T> T deserialise(InputStream is, Class<T> class1) throws JsonSerialisationException;

	public abstract <T> T deserialise(String is, Class<T> class1) throws JsonSerialisationException;

	public abstract <T> String serialise(T object) throws JsonSerialisationException;

}