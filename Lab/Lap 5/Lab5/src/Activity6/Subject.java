package Activity6;

public class Subject implements Comparable<Subject> {
	private String title;
	private String type;
	private int mark=100;
	private final String M = "Mandatory", EI = "ElectiveI", EII = "ElectiveII";
	double maxMark;

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
		if (type.equals(M) || type.equals(EI) || type.equals(EII))
			this.type = type;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		if (mark >= 0 || mark <= 200)
			this.mark = mark;
	}

	public double getMaxMark() {
		return maxMark;
	}

	public void setMaxMark(double maxMark) {
		this.maxMark = maxMark;
	}

	public Subject(String title, String type, int mark) {
		setTitle(title);
		setMark(mark);
		setType(type);
		
	}
	public Subject(String title, String type, int mark, double maxMark) {
		setTitle(title);
		setMark(mark);
		setType(type);
		setMaxMark(maxMark);
	}

	@Override
	public int compareTo(Subject o) {
		if (this.title.equals(o.title)) {
			if (this.mark - o.mark > 0)
				return 1;
			else if (this.mark - o.mark == 0)
				return 0;
			else {
				return -1;
			}
		}
		return -1;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Subject) {
			return (this.title==((Subject) obj).title && this.mark == ((Subject) obj).mark);
		}

		return false;
	}
}
