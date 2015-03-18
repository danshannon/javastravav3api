package javastrava.json;

import java.io.InputStream;

import javastrava.json.exception.JsonSerialisationException;

/**
 * <p>
 * Abstracted JSON utilities for serialisation and de-serialisation, so we're not reliant on any one JSON library for the basic code
 * </p>
 * 
 * @author Dan Shannon
 */
public interface JsonUtil {

	/**
	 * Deserialise an input stream representation of a JSON document to the named class
	 * 
	 * @param is The input stream to deserialise
	 * @param class1 The class to which is should be deserialised
	 * @param <T> Class to deserialise to
	 * @return An instance of the class as deserialised from the document
	 * @throws JsonSerialisationException If something goes horribly wrong in deserialisation
	 */
	public abstract <T> T deserialise(final InputStream is, final Class<T> class1) throws JsonSerialisationException;

	/**
	 * Deserialise a string representation of a JSON document to the named class
	 * @param input The input string to deserialise
	 * @param class1 The class to which the input should be deserialised
	 * @param <T> Class to deserialise to
	 * @return An instance of the class as deserialised from the document
	 * @throws JsonSerialisationException If something goes horribly wrong in deserialisation
	 */
	public abstract <T> T deserialise(final String input, final Class<T> class1) throws JsonSerialisationException;

	/**
	 * Serialise an object to a JSON string
	 * @param object The object to be serialised
	 * @param <T> Class to serialise
	 * @return The JSON string representation of the object
	 * @throws JsonSerialisationException If something goes horribly wrong in serialisation
	 */
	public abstract <T> String serialise(final T object) throws JsonSerialisationException;

}