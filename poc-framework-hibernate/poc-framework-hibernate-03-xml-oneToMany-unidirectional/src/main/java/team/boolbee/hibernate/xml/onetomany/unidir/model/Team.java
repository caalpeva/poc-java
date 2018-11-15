package team.boolbee.hibernate.xml.onetomany.unidir.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private Long id;
	private String name;
	private List<Rider> riders = new ArrayList<Rider>();

	/**
	 * default constructor
	 */
	public Team () {
	}

	/**
	 * @param id
	 * @param name
	 * @param players
	 */
	public Team(Long id, String name, List<Rider> players) {
		this.id = id;
		this.name = name;
		this.riders = players;
	}
	
	/**
	 * Adds player to team
	 * @param player
	 */
	public void addRider (Rider rider) {
		riders.add(rider);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the players
	 */
	public List<Rider> getRiders() {
		return riders;
	}

	/**
	 * @param players the players to set
	 */
	public void setRiders(List<Rider> riders) {
		this.riders = riders;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", players=" + riders
				+ "]";
	}
}
