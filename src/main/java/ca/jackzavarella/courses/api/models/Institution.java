package ca.jackzavarella.courses.api.models;

import ca.jackzavarella.courses.api.dto.InstitutionMetaData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
@Document(collection = "institutions")
public class Institution {
    private String id, name;
    private List<InstitutionMetaData> data = new ArrayList<>();

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InstitutionMetaData> getData() {
        return data;
    }

    public void setData(List<InstitutionMetaData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Institution{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
