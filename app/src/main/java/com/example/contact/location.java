package com.example.contact;

public class location {

        private String attraction;
        private int budget;
        private String name;




        public location() {
        }


        public location(String attraction, int budget, String name) {
            this.attraction = attraction;
            this.budget = budget;
            this.name = name;
        }

        public String getAttraction() {
            return attraction;
        }

        public void setAttraction(String attraction) {
            this.attraction = attraction;
        }

        public int getBudget() {
            return budget;
        }

        public void setBudget(int budget) {
            this.budget = budget;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


}
