package org.pytorch.demo.objectdetection;

import java.util.ArrayList;

public class Garbage {

	private String description;
	private boolean severe;
	private boolean organic;
	private double latitude;
	private double longitude;
	private int upvotes;
	private String name;
	private String url;
	private String mobNumber;
	private ArrayList<String> upvoters;
	public Garbage(){
		//empty
	}

	public Garbage(String description, boolean severe, boolean organic, double latitude, double longitude, int upvotes, String name, String url, String mobNumber, ArrayList<String> upvoters)
	{
		if(description.trim().equals("")){
			description = "No Name";
		}
		this.description = description;
		this.severe = severe;
		this.organic = organic;
		this.latitude = latitude;
		this.longitude = longitude;
		this.upvotes = upvotes;
		this.name = name;
		this.url = url;
		this.mobNumber = mobNumber;
		this.upvoters = upvoters;
	}

	public String getDescription()
	{
		return description;
	}

	public ArrayList<String> getUpvoters() {
		return upvoters;
	}

	public void setUpvoters(ArrayList<String> upvoters) {
		this.upvoters = upvoters;
	}

	public boolean getSevere(){
		return severe;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getLongitude() {
		return longitude;
	}

	public boolean getOrganic() {
		return organic;
	}

	public int getUpvotes() {
		return upvotes;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String name){
		this.description = name;
	}

	public void setSevere(boolean severe){
		this.severe = severe;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setOrganic(boolean organic) {
		this.organic = organic;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public void setName(String name) {
		this.name = name;
	}
}
