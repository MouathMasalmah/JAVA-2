package application;

public class Location implements Comparable<Location> {
	private BSTree<MartyrDate> dateTree = new BSTree<>();
	private String locationName;

	public Location() {

	}

	public Location(String locationName) {
		this.locationName = locationName;
	}

	public BSTree<MartyrDate> getDateTree() {
		return dateTree;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public String toString() {
		return locationName;
	}

	public MartyrDate maximumDate() {
		QStack<MartyrDate> temp = dateTree.getNextStack();
		if (!temp.isEmpty()) {

			TNode<MartyrDate> curr = new TNode<>(temp.pop());
			TNode<MartyrDate> maxDate = curr;
			while (!temp.isEmpty()) {
				curr = new TNode<>(temp.pop());
				if (maxDate.getData().getMartyrList().length() < curr.getData().getMartyrList().length()) {
					maxDate = curr;
				}
			}
			return maxDate.getData();
		}
		return null;
	}
	public int numberOfMartyrInLocation() {
	    int count = 0;
	    dateTree.traverseInOrder();  // Assuming this fills the stack with MartyrDate objects in the correct order
	    QStack<MartyrDate> dateStack = dateTree.getNextStack();

	    while (!dateStack.isEmpty()) {
	        MartyrDate selectedMartyrDate = dateStack.pop();
	        count += selectedMartyrDate.getMartyrList().length();  // Assuming this gets the length of the list correctly
	    }

	    return count;
	}
	@Override
	public int compareTo(Location o) {
		return this.getLocationName().toLowerCase().compareTo(o.getLocationName().toLowerCase());
	}

}
