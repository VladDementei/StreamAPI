package by.courses.java.streamapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class UserBase {

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
