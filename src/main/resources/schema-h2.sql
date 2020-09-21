CREATE TABLE PRODUCT
        (
        id NUMBER(10) NOT NULL,
        type VARCHAR2(50) NOT NULL,
        properties VARCHAR2(50) NOT NULL,
        price DOUBLE,
        store_address VARCHAR2(50) NOT NULL,
        PRIMARY KEY(id)
        );
        