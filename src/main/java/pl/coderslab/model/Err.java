package pl.coderslab.model;


import java.util.ArrayList;
import java.util.List;

public class Err {


    private List<String> errors = new ArrayList<>();

    public Err() {
    }

    public void addErr(String err){ this.errors.add(err); }

    public boolean isEmpty(){ return this.errors.isEmpty(); }

    public List<String> getErrors(){ return errors;}

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
