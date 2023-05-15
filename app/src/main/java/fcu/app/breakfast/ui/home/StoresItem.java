package fcu.app.breakfast.ui.home;

public class StoresItem {
    private int imageId;
    private String storeName;
    private String storeAddress;

    public StoresItem(int imageId ,String storeName,String storeAddress)
    {
        this.imageId=imageId;
        this.storeName=storeName;
        this.storeAddress=storeAddress;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getstoreName() {
        return storeName;
    }

    public void setstoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getstoreAddress() {
        return storeAddress;
    }

    public void setstoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }
}
