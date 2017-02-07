package com.genius.thirdeye_lll.banglamedicinereminder.Module;

/**
 * Created by ThirdEye-lll on 2/4/2017.
 */

public class Medicine {
    private int id;
    private String medicineName;
    private String medicineDescription;
    private String startDate;
    private String endDate;
    private String atTime;

    //TODO-----not using this Variable now--------
    private String ringtone;
    private int vibration;


    public Medicine(String medicineName, String medicineDescription, String startDate, String endDate, String atTime) {
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.atTime = atTime;
    }


    public Medicine(int id, String medicineName, String medicineDescription, String startDate, String endDate, String atTime, String ringtone, int vibration) {
        this.id = id;
        this.medicineName = medicineName;
        this.medicineDescription = medicineDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.atTime = atTime;
        this.ringtone = ringtone;
        this.vibration = vibration;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }

    public String getRingtone() {
        return ringtone;
    }

    public void setRingtone(String ringtone) {
        this.ringtone = ringtone;
    }

    public int getVibration() {
        return vibration;
    }

    public void setVibration(int vibration) {
        this.vibration = vibration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
