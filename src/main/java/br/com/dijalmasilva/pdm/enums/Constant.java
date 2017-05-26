package br.com.dijalmasilva.pdm.enums;

/**
 * Created by <a href="http://dijalmasilva.github.io" target="_blank">dijalma</a> on 21/05/17.
 */
public enum Constant {

    HOST("192.168.0.108"), HOST_WITH_PROTOCOL("http://192.168.0.108:8080");
    //    HOST("10.3.27.148"), HOST_WITH_PROTOCOL("http://10.3.27.148:8080");
    private String value;

    Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
