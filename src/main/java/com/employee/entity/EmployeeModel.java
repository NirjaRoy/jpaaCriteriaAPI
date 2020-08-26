package com.employee.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String team;
    private String position;


 
    public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


	public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public static EmployeeModel create(String name, String team, String position) {
    	EmployeeModel employee = new EmployeeModel();
        employee.setName(name);
        employee.setTeam(team);
        employee.setPosition(position);
        return employee;
    }
    
    @Override
	public String toString() {
		return "EmployeeModel [ name=" + name + ", team=" + team + ", position=" + position + "]";
	}


}