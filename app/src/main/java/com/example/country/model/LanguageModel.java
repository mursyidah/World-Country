package com.example.country.model;

import com.google.gson.annotations.SerializedName;

public class LanguageModel {


        @SerializedName("iso639_1")
        String iso1;
        @SerializedName("iso639_2")
        String iso2;
        @SerializedName("name")
        String name;
        @SerializedName("nativeName")
        String nativeName;

        public LanguageModel() {
        }

        public String getIso1() {
                return iso1;
        }

        public void setIso1(String iso1) {
                this.iso1 = iso1;
        }

        public String getIso2() {
                return iso2;
        }

        public void setIso2(String iso2) {
                this.iso2 = iso2;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getNativeName() {
                return nativeName;
        }

        public void setNativeName(String nativeName) {
                this.nativeName = nativeName;
        }
}