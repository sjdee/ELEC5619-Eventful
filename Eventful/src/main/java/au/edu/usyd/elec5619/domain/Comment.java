package au.edu.usyd.elec5619.domain;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Comment")
public class Comment implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	public int id;

	@Column(name = "contents")
	public String contents;

	@JoinColumn(name = "commenter_email")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public User commenter;

	@JoinColumn(name = "post_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Post post;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public int getId() {
		return id;
	}

}
