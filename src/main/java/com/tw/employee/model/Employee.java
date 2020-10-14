package com.tw.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String emailId;
}
