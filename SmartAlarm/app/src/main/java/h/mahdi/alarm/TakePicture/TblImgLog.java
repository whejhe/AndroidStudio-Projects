package h.mahdi.alarm.TakePicture;

public class TblImgLog implements Comparable<TblImgLog> {
    String dateTime;
    int id;
    byte[] imgBytes;

    public TblImgLog(int id, String dateTime, byte[] imgBytes) {
        this.dateTime = "";
        this.imgBytes = null;
        this.id = id;
        this.dateTime = dateTime;
        this.imgBytes = imgBytes;
    }

    public TblImgLog(String dateTime, byte[] imgBytes) {
        this.dateTime = "";
        this.imgBytes = null;
        this.dateTime = dateTime;
        this.imgBytes = imgBytes;
    }

    public TblImgLog() {
        this.dateTime = "";
        this.imgBytes = null;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public byte[] getImgBytes() {
        return this.imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public int compareTo(TblImgLog other) {
        int retVal = 0;
        if (this.id > other.id) {
            retVal = -1;
        }
        if (this.id < other.id) {
            return 1;
        }
        return retVal;
    }
}