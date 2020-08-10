
CREATE TABLE T_ADDRESS (
  addressId  INT IDENTITY PRIMARY KEY,
  line1 VARCHAR(50) NOT NULL,
  line2   VARCHAR(50),
  city   VARCHAR(50) NOT NULL,
  stateCd  CHAR(2)  NOT NULL,
  zipCode  CHAR(10) NOT NULL
);

CREATE TABLE T_EMPLOYEES (  
employeeId INT IDENTITY PRIMARY KEY,
firstName varchar(255) NOT NULL,  
lastName varchar(255) NOT NULL,  
gender CHAR(1) NOT NULL,  
dob DATE,
email varchar(255),   
addressId INT,
foreign key (addressId) references T_ADDRESS(addressId)
); 

