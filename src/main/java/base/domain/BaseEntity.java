package base.domain;


import java.io.Serializable;

@SuppressWarnings("unused")
public class BaseEntity<ID extends Serializable> {
    private ID id;

    public BaseEntity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}

