package ct414;

import java.util.Date;

public class Session {

    private Date expiry;
    private int userID;

    public Session(int userID) {
        this.expiry = new Date(new Date().getTime() + (3 * 60 * 1000));
        // logs them in for an hour
        this.userID = userID;
    }

    public Date getExpiry() {
        return expiry;
    }

    public int getUserID() {
        return userID;
    }
}
