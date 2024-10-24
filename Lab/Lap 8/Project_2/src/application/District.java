package application;

public class District implements Comparable<District> {
	private BSTree<Location> locationTree = new BSTree<>();
	private String districtName;

	public District(String districtName) {
		this.districtName = districtName;
	}

	public BSTree<Location> getLocationTree() {
		return locationTree;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Override
	public String toString() {
		return districtName + "\n";
	}

	public int theNumberOfMartyer() {
		int count = 0;
		locationTree.traverseLevelByLevel();
		QStack<Location> locationStack = locationTree.getNextStack();
		Location selectedLoca;
		if (locationTree.isEmpty()) {
			return 0;
		} else if (!locationStack.isEmpty()) {
				while (!locationStack.isEmpty()) {	
					selectedLoca = locationStack.pop();
					count += selectedLoca.numberOfMartyrInLocation();
				
				}
		}
		return count;
	}

	@Override
	public int compareTo(District o) {

		return this.districtName.toLowerCase().compareTo(o.getDistrictName().toLowerCase());
	}

}
