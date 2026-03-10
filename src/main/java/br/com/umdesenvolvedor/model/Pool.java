package br.com.umdesenvolvedor.model;

import static java.util.Objects.isNull;

import lombok.Getter;

@Getter
public class Pool extends Source {

    private boolean children;

    private Double size;

    private Double depth;

    public Pool(String description, boolean children, Double size, Double depth) {
        super.setDescription(description);
        super.setActive(true);
        
        this.children = children;
        this.size = size;
        this.depth = depth;
    }

    public boolean isValid() {
        if(isNull(super.getDescription()) || super.getDescription().isBlank() || isNull(size) || size == 0.0 || isNull(depth) || depth == 0.0) 
            return false;

        return true;
    }

}
