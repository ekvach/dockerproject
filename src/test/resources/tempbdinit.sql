create TABLE IF NOT EXISTS APP_USERS(
				ID BIGINT,
                LOGIN VARCHAR(255) UNIQUE,
                PASSWORD VARCHAR(255) ,
                EMAIL VARCHAR(255) UNIQUE,
                FIRSTNAME VARCHAR(255) ,
                LASTNAME VARCHAR(255) ,
                BIRTHDAY DATE,
                USER_ROLEID BIGINT ,
                PRIMARY KEY ( ID )
				);
					 
					 
create TABLE IF NOT EXISTS USER_ROLE(
    ID BIGINT  PRIMARY KEY,
    NAME varchar not null
);

--ALTER TABLE USERS ADD FOREIGN KEY (USER_ROLEID)
--REFERENCES USER_ROLE(ID);
