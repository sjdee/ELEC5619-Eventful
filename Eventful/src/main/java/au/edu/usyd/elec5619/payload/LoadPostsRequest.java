package au.edu.usyd.elec5619.payload;

import java.util.Date;

public class LoadPostsRequest {

	int eventId;
    int oldestPostId;

	public int getOldestPostId() {
		return oldestPostId;
	}

	public void setOldestPostId(int oldestPostId) {
		this.oldestPostId = oldestPostId;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
    
}