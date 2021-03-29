-- Generated by Oracle SQL Developer Data Modeler 20.2.0.167.1538
--   at:        2020-12-18 23:19:58 EET
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE angajat (
    id_angajat      NUMBER(3) NOT NULL,
    nume            VARCHAR2(30) NOT NULL,
    prenume         VARCHAR2(30) NOT NULL,
    salariu         NUMBER(10, 2) NOT NULL,
    data_angajare   DATE,
    id_departament  NUMBER(2) NOT NULL
);

ALTER TABLE angajat ADD CONSTRAINT angajat_pk PRIMARY KEY ( id_angajat );

CREATE TABLE confidential (
    id_confidential  NUMBER(3) NOT NULL,
    id_angajat       NUMBER(3) NOT NULL,
    cnp              VARCHAR2(13) NOT NULL,
    telefon          VARCHAR2(15) NOT NULL,
    adresa           VARCHAR2(100)
);

ALTER TABLE confidential
    ADD CONSTRAINT confidential_telefon_ck CHECK ( length(to_char(telefon)) = 10
                                                   AND substr(to_char(telefon), 1, 1) = '0'
                                                   OR substr(to_char(telefon), 2, 1) = '2'
                                                   OR substr(to_char(telefon), 2, 1) = '3'
                                                   OR substr(to_char(telefon), 2, 1) = '7' );

CREATE UNIQUE INDEX confidential__idx ON
    confidential (
        id_angajat
    ASC );

ALTER TABLE confidential ADD CONSTRAINT confidential_pk PRIMARY KEY ( id_confidential );

ALTER TABLE confidential ADD CONSTRAINT confidential_cnp_uk UNIQUE ( cnp );

CREATE TABLE croitor (
    id_croitor  NUMBER(3) NOT NULL,
    id_angajat  NUMBER(3) NOT NULL
);

CREATE UNIQUE INDEX croitor__idx ON
    croitor (
        id_angajat
    ASC );

ALTER TABLE croitor ADD CONSTRAINT croitor_pk PRIMARY KEY ( id_croitor );

CREATE TABLE departament (
    id_departament    NUMBER(2) NOT NULL,
    nume_departament  VARCHAR2(20) NOT NULL,
    rol               VARCHAR2(30) NOT NULL
);

ALTER TABLE departament ADD CONSTRAINT departament_pk PRIMARY KEY ( id_departament );

CREATE TABLE manager (
    id_manager    NUMBER(2) NOT NULL,
    id_angajat    NUMBER(3) NOT NULL,
    buget_alocat  NUMBER(6) NOT NULL
);

ALTER TABLE manager
    ADD CONSTRAINT manager_buget_alocat_ck CHECK ( buget_alocat BETWEEN 10000 AND 500000 );

CREATE UNIQUE INDEX manager__idx ON
    manager (
        id_angajat
    ASC );

ALTER TABLE manager ADD CONSTRAINT manager_pk PRIMARY KEY ( id_manager );

CREATE TABLE marime (
    id_marime  NUMBER(2) NOT NULL,
    marime_cm  NUMBER(3) NOT NULL
);

ALTER TABLE marime
    ADD CHECK ( marime_cm IN ( 62, 68, 74, 80, 86,
                               92, 98, 104, 110, 116,
                               122 ) );

ALTER TABLE marime ADD CONSTRAINT marime_pk PRIMARY KEY ( id_marime );

CREATE TABLE material (
    id_material    NUMBER(2) NOT NULL,
    nume_material  VARCHAR2(20) NOT NULL,
    culoare        VARCHAR2(12) NOT NULL
);

ALTER TABLE material
    ADD CONSTRAINT material_nume_ck CHECK ( nume_material IN ( 'bumbac', 'bumbac_m', 'bumbac_s', 'in', 'stofa' ) );

ALTER TABLE material ADD CONSTRAINT material_pk PRIMARY KEY ( id_material );

CREATE TABLE material_manager (
    id_material          NUMBER(2) NOT NULL,
    id_manager           NUMBER(2) NOT NULL,
    data_achizitionarii  DATE NOT NULL,
    cant_material        NUMBER(5) NOT NULL,
    pret                 NUMBER(5) NOT NULL,
    origine              VARCHAR2(12)
);

ALTER TABLE material_manager ADD CONSTRAINT material_manager_pk PRIMARY KEY ( id_material,
                                                                              id_manager );

CREATE TABLE produs (
    id_produs    NUMBER(3) NOT NULL,
    nume_produs  VARCHAR2(20) NOT NULL,
    categorie    VARCHAR2(2) NOT NULL,
    id_marime    NUMBER(2) NOT NULL
);

ALTER TABLE produs
    ADD CONSTRAINT produs_nume_ck CHECK ( nume_produs IN ( 'camasa', 'fusta', 'hanorac', 'palton', 'pantalon',
                                                           'pijama', 'rochita', 'sacou', 'salopeta', 'tricou' ) );

ALTER TABLE produs
    ADD CONSTRAINT produs_categorie_ck CHECK ( categorie IN ( 'B', 'BB', 'BG', 'G' ) );

ALTER TABLE produs ADD CONSTRAINT art_vestimentar_pk PRIMARY KEY ( id_produs );

CREATE TABLE produs_croitor (
    id_produs        NUMBER(3) NOT NULL,
    id_croitor       NUMBER(3) NOT NULL,
    data_realizarii  DATE NOT NULL,
    nr_produse       NUMBER(2) NOT NULL
);

ALTER TABLE produs_croitor ADD CONSTRAINT produs_croitor_pk PRIMARY KEY ( id_produs,
                                                                          id_croitor );

CREATE TABLE produs_material (
    id_produs           NUMBER(3) NOT NULL,
    id_material         NUMBER(2) NOT NULL,
    cantitate_material  NUMBER(2) NOT NULL
);

ALTER TABLE produs_material ADD CONSTRAINT produs_material_pk PRIMARY KEY ( id_produs,
                                                                            id_material );

ALTER TABLE confidential
    ADD CONSTRAINT angajat_confidential_fk FOREIGN KEY ( id_angajat )
        REFERENCES angajat ( id_angajat );

ALTER TABLE croitor
    ADD CONSTRAINT angajat_croitor_fk FOREIGN KEY ( id_angajat )
        REFERENCES angajat ( id_angajat );

ALTER TABLE manager
    ADD CONSTRAINT angajat_manager_fk FOREIGN KEY ( id_angajat )
        REFERENCES angajat ( id_angajat );

ALTER TABLE angajat
    ADD CONSTRAINT departament_angajat_fk FOREIGN KEY ( id_departament )
        REFERENCES departament ( id_departament );

ALTER TABLE produs
    ADD CONSTRAINT marime_produs_fk FOREIGN KEY ( id_marime )
        REFERENCES marime ( id_marime );

ALTER TABLE material_manager
    ADD CONSTRAINT material_manager_manager_fk FOREIGN KEY ( id_manager )
        REFERENCES manager ( id_manager );

ALTER TABLE material_manager
    ADD CONSTRAINT material_manager_material_fk FOREIGN KEY ( id_material )
        REFERENCES material ( id_material );

ALTER TABLE produs_croitor
    ADD CONSTRAINT produs_croitor_croitor_fk FOREIGN KEY ( id_croitor )
        REFERENCES croitor ( id_croitor );

ALTER TABLE produs_croitor
    ADD CONSTRAINT produs_croitor_produs_fk FOREIGN KEY ( id_produs )
        REFERENCES produs ( id_produs );

ALTER TABLE produs_material
    ADD CONSTRAINT produs_material_material_fk FOREIGN KEY ( id_material )
        REFERENCES material ( id_material );

ALTER TABLE produs_material
    ADD CONSTRAINT produs_material_produs_fk FOREIGN KEY ( id_produs )
        REFERENCES produs ( id_produs );

CREATE SEQUENCE angajat_id_angajat_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER angajat_id_angajat_trg BEFORE
    INSERT ON angajat
    FOR EACH ROW
    WHEN ( new.id_angajat IS NULL )
BEGIN
    :new.id_angajat := angajat_id_angajat_seq.nextval;
END;
/

CREATE SEQUENCE confidential_id_confidential START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER confidential_id_confidential BEFORE
    INSERT ON confidential
    FOR EACH ROW
    WHEN ( new.id_confidential IS NULL )
BEGIN
    :new.id_confidential := confidential_id_confidential.nextval;
END;
/

CREATE SEQUENCE croitor_id_croitor_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER croitor_id_croitor_trg BEFORE
    INSERT ON croitor
    FOR EACH ROW
    WHEN ( new.id_croitor IS NULL )
BEGIN
    :new.id_croitor := croitor_id_croitor_seq.nextval;
END;
/

CREATE SEQUENCE departament_id_departament_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER departament_id_departament_trg BEFORE
    INSERT ON departament
    FOR EACH ROW
    WHEN ( new.id_departament IS NULL )
BEGIN
    :new.id_departament := departament_id_departament_seq.nextval;
END;
/

CREATE SEQUENCE manager_id_manager_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER manager_id_manager_trg BEFORE
    INSERT ON manager
    FOR EACH ROW
    WHEN ( new.id_manager IS NULL )
BEGIN
    :new.id_manager := manager_id_manager_seq.nextval;
END;
/

CREATE SEQUENCE marime_id_marime_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER marime_id_marime_trg BEFORE
    INSERT ON marime
    FOR EACH ROW
    WHEN ( new.id_marime IS NULL )
BEGIN
    :new.id_marime := marime_id_marime_seq.nextval;
END;
/

CREATE SEQUENCE material_id_material_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER material_id_material_trg BEFORE
    INSERT ON material
    FOR EACH ROW
    WHEN ( new.id_material IS NULL )
BEGIN
    :new.id_material := material_id_material_seq.nextval;
END;
/

CREATE SEQUENCE produs_id_produs_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER produs_id_produs_trg BEFORE
    INSERT ON produs
    FOR EACH ROW
    WHEN ( new.id_produs IS NULL )
BEGIN
    :new.id_produs := produs_id_produs_seq.nextval;
END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            11
-- CREATE INDEX                             3
-- ALTER TABLE                             29
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           8
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          8
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0