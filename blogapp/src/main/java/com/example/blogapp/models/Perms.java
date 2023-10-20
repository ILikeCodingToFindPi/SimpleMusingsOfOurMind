package com.example.blogapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Perms {

    @Id
    @Column(length = 16)
    String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Perms perms1 = (Perms) o;

        return name.equals(perms1.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
