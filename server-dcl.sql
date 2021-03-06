DROP USER ERS CASCADE;

CREATE USER ERS
IDENTIFIED BY p4ssw0rd
DEFAULT TABLESPACE users
TEMPORARY TABLESPACE temp
QUOTA 10M ON users;

GRANT connect, resource, 
CREATE SESSION, CREATE TABLE, CREATE VIEW to ERS;