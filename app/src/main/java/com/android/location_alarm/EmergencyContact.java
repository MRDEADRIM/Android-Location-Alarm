package com.android.location_alarm;

public class EmergencyContact {
    private int id;
    private String name;
    private String phonenumber;
    public EmergencyContact(int id, String name, String phonenumber){
        this.id=id;
        this.name=name;
        this.phonenumber=phonenumber;
    }
    public EmergencyContact(){
    }
    @Override
    public String toString(){
        //return "EmergencyContact{"+ "id="+id+ "name"+name+ "phonenumber"+phonenumber+ '}';
        return " " + name + " " + phonenumber + " " ;
        //return " " + id + " " + name + " " + phonenumber + " " ;

    }
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) { this.phonenumber = phonenumber; }

}
