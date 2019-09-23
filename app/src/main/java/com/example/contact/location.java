package com.example.contact;

public class location {

        private String attraction;
        private String budget;
        private String name;
        private String username;




        public location() {
        }


        public location(String attraction, String budget, String name,String username) {
            this.attraction = attraction;
            this.budget = budget;
            this.name = name;
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getAttraction() {
            return attraction;
        }

        public void setAttraction(String attraction) {
            this.attraction = attraction;
        }

        public String getBudget() {
            return budget;
        }

        public void setBudget(String budget) {
            this.budget = budget;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


}
