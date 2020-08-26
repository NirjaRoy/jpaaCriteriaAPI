package com.employee.data;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EmployeeModel.class)
public abstract class EmployeeModel_ {

	public static volatile SingularAttribute<EmployeeModel, String> name;
	public static volatile SingularAttribute<EmployeeModel, Long> id;
	public static volatile SingularAttribute<EmployeeModel, String> dept;
	public static volatile SingularAttribute<EmployeeModel, Long> salary;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String DEPT = "dept";
	public static final String SALARY = "salary";

}

