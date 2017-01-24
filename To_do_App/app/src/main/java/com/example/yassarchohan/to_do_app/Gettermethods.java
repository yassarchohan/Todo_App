package com.example.yassarchohan.to_do_app;

/**
 * Created by Yassar chohan on 1/12/2017.
 */
public class Gettermethods {

    public String name;
    public String secretcode;
    public Boolean checkstate;
    public String nodeKey;
    public Gettermethods values;

    public Gettermethods() {
    }

    public Gettermethods(String name, String secretcode, Boolean checkstate, String key) {
        this.name = name;
        this.secretcode = secretcode;
        this.checkstate = checkstate;
       this.nodeKey = key;
    }

    public String getName() {
        return name;
    }

    public String getSecretcode() {
        return secretcode;
    }

    public Boolean getCheckstate() {
        return checkstate;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setSecretcode(String secretcode) {
        this.secretcode = secretcode;
    }

    public void setCheckstate(Boolean checkstate) {
        this.checkstate = checkstate;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setvalues(Gettermethods values) {
        name = values.name;
        secretcode = values.secretcode;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }
}

