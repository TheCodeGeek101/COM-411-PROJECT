package com.example.smartassaultapplication.Domain;

public class Soldier {
    private String fullname;
    private String email;

    public Soldier()
    {

    }

    public Soldier(String fullname , String email)
    {
        this.email = email;
        this.fullname = fullname;
    }

    public Soldier(Builder builder) {
        this.fullname=fullname;
        this.email=email;
    }
    public static Builder builder(){

        return new Builder();
    }

    public static class Builder{
        private String fullname;
        private String email;

        public Builder fullname(String fullname){
            this.fullname=fullname;
            return this;
        }
        public Builder email(String email){
            this.email= email;
            return this;
        }
        public Soldier build(){
            return  new Soldier(this);
        }
    }



}
