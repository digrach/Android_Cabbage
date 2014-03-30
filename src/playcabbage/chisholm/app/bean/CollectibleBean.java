// playcabbage android app
// Copyright Chisholm Institite - Diploma of Digital and Interactive Games ICA50211 2013
// Trainer: Rachael Colley - rachael.colley@chisholm.edu.au

package playcabbage.chisholm.app.bean;

public class CollectibleBean {

	private int checkpoint_id;
	private String urlstring;
	private int livecollectible_id;
	private int location_id;
	private int x;
	private int y;
	private int z;
	private long lat;
	private long lon;
	private String locationdescription;
	private String collectiblename;
	private int collectiblecategory_id;
	private int value;
	private String collectibledescription;
	private String imagefilename;
	private int item_id;
	private int skill_id;
	private int faculty_id;
	private int checkin_id;
	private String timestamp;
	private boolean available;
	

	// Must provide an empty constructor for Json serialisation.
	public CollectibleBean() {

	}


	public int getCheckpoint_id() {
		return checkpoint_id;
	}


	public void setCheckpoint_id(int checkpoint_id) {
		this.checkpoint_id = checkpoint_id;
	}


	public String getUrlstring() {
		return urlstring;
	}


	public void setUrlstring(String urlstring) {
		this.urlstring = urlstring;
	}


	public int getLivecollectible_id() {
		return livecollectible_id;
	}


	public void setLivecollectible_id(int livecollectible_id) {
		this.livecollectible_id = livecollectible_id;
	}


	public int getLocation_id() {
		return location_id;
	}


	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getZ() {
		return z;
	}


	public void setZ(int z) {
		this.z = z;
	}


	public long getLat() {
		return lat;
	}


	public void setLat(long lat) {
		this.lat = lat;
	}


	public long getLon() {
		return lon;
	}


	public void setLon(long lon) {
		this.lon = lon;
	}


	public String getLocationdescription() {
		return locationdescription;
	}


	public void setLocationdescription(String locationdescription) {
		this.locationdescription = locationdescription;
	}


	public String getCollectiblename() {
		return collectiblename;
	}


	public void setCollectiblename(String collectiblename) {
		this.collectiblename = collectiblename;
	}


	public int getCollectiblecategory_id() {
		return collectiblecategory_id;
	}


	public void setCollectiblecategory_id(int collectiblecategory_id) {
		this.collectiblecategory_id = collectiblecategory_id;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public String getCollectibledescription() {
		return collectibledescription;
	}


	public void setCollectibledescription(String collectibledescription) {
		this.collectibledescription = collectibledescription;
	}


	public String getImagefilename() {
		return imagefilename;
	}


	public void setImagefilename(String imagefilename) {
		this.imagefilename = imagefilename;
	}


	public int getItem_id() {
		return item_id;
	}


	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}


	public int getSkill_id() {
		return skill_id;
	}


	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}


	public int getFaculty_id() {
		return faculty_id;
	}


	public void setFaculty_id(int faculty_id) {
		this.faculty_id = faculty_id;
	}


	public int getCheckin_id() {
		return checkin_id;
	}


	public void setCheckin_id(int checkin_id) {
		this.checkin_id = checkin_id;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public boolean isAvailable() {
		return available;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}



	
}