package The_War_On_Gaza;

import java.util.ArrayList;

public interface Sortable {
	public ArrayList<Family> sortByMartyrs(ArrayList<Family> families) throws CloneNotSupportedException;

	/*
	 * The sorting should be based on the number of Martyrs. Keep the original
	 * family list as it is.
	 */
	public ArrayList<Family> sortByOrphans (ArrayList<Family> families) throws CloneNotSupportedException;
	/*
	 * The sorting should be based on the number of Orphans. Keep the original
	 * family list as it is.
	 */
}
