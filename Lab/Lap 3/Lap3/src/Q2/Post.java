package Q2;

import java.util.ArrayList;

public class Post {
	String PostId;
	User author = new User();
	String content;
	int Like;
	ArrayList<User> likedBy = new ArrayList<>();

	public Post() {

	}

	public Post(String postId, User author, String content) {
		super();
		PostId = postId;
		this.author = author;
		this.content = content;
	}

	public int getLike() {
		return Like;
	}

	public void addLike(User user) {
		if (!(likedBy.equals(user))) {
			Like++;
			likedBy.add(user);
		} else {
			System.out.println("The user is already liked");
		}
	}

	@Override
	public String toString() {
		return "Post [PostId=" + PostId + ", author=" + author + ", content=" + content + ", Like=" + getLike()
				+ ", likedBy=" + likedBy + "]";
	}

}
