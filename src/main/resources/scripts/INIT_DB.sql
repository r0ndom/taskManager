CREATE TABLE IF NOT EXISTS USERS
(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    LDAP VARCHAR(32) NOT NULL,
    FIRST_NAME VARCHAR(32) NOT NULL,
    LAST_NAME VARCHAR(32) NOT NULL,
    PASSWORD VARCHAR(256) NOT NULL,
    ROLE VARCHAR(32) NOT NULL
);

INSERT INTO USERS(LDAP, FIRST_NAME, LAST_NAME, PASSWORD, ROLE) VALUES ('devLDAP', 'Dev', 'Developer', '$2a$10$3N2scUic8DXRigfjZRkHQ.GYkzLFsNXGHKJz2rNlxs6HpRxyyw2Ny', 'ROLE_DEVELOPER');
INSERT INTO USERS (LDAP, FIRST_NAME, LAST_NAME, PASSWORD, ROLE) VALUES ('manLDAP', 'Man', 'Manager', '$2a$10$3N2scUic8DXRigfjZRkHQ.GYkzLFsNXGHKJz2rNlxs6HpRxyyw2Ny', 'ROLE_MANAGER');
