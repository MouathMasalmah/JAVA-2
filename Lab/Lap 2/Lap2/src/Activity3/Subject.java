package Activity3;

public class Subject {
	private String title;
	private String type;
	private int mark;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title != null)
			this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type.equals("Mandatory") || type.equals("ElectivI") || type.equals("ElectivII"))
			this.type = type;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		if (mark >= 0 || mark <= 100)
			this.mark = mark;
	}

	public Subject(String title, String type, int mark) {
		this.title = title;
		if (type.equals("Mandatory") || type.equals("ElectivI") || type.equals("ElectivII"))
			this.type = type;
		if (mark >= 0)
			this.mark = mark;
	}

}
