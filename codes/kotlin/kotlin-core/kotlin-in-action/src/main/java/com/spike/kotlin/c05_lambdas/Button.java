package com.spike.kotlin.c05_lambdas;

public class Button {
    private String id;

    public Button(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setOnClickListener(OnClickListener l) {
    }
}
