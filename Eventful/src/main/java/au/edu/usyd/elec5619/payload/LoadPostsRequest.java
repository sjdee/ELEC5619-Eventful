package au.edu.usyd.elec5619.payload;

import java.util.Date;

public class LoadPostsRequest {

    Date oldestPostDate;

	public Date getOldestPostDate() {
		return oldestPostDate;
	}

	public void setOldestPostDate(Date oldestPostDate) {
		this.oldestPostDate = oldestPostDate;
	}
    
}