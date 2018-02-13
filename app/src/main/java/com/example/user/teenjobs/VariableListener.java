package com.example.user.teenjobs;

public class VariableListener<T> {
    private T var;
    private ChangeListener listener;


    public T getVar(){
        return var;
    }



    public void setVar(T var) {
        this.var = var;
        if (listener != null) listener.onChange();
    }


    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}

