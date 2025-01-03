package com.ttu.airquality;

public class Air {
    private String SiteName;
    private String County;
    private String Longitude;
    private String Latitude;
    private String AQI;
    private String PM2_5;
    private String Status;
    private String PublishTime;
    private String Tips;


    public String getSiteName() {
        return SiteName;
    }

    public void setSiteName(String siteName) {
        this.SiteName = siteName;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String County) {
        this.County = County;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    public String getAQI() {
        return AQI;
    }

    public void setAQI(String AQI) {
        this.AQI = AQI;
    }

    public String getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(String PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        this.PublishTime = publishTime;
    }

    public String getTips() {
        return Tips;
    }

    public void setTips(String Tips) {
        this.Tips = Tips;
    }
}

