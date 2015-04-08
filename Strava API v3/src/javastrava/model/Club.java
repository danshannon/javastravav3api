package javastrava.model;

import java.util.List;

/**
 * @author Dan Shannon
 *
 */
public class Club {
	private String id;
	private List<Athlete> members;
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}
	/**
	 * @return the members
	 */
	public List<Athlete> getMembers() {
		return this.members;
	}
	/**
	 * @param members the members to set
	 */
	public void setMembers(final List<Athlete> members) {
		this.members = members;
	}
}
