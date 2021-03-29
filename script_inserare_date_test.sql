INSERT INTO MARIME VALUES ( NULL,62 );
INSERT INTO MARIME(marime_cm) VALUES (68 );
INSERT INTO MARIME(marime_cm) VALUES (74);
INSERT INTO MARIME(marime_cm) VALUES (80 );
INSERT INTO MARIME(marime_cm) VALUES (86 );
INSERT INTO MARIME(marime_cm) VALUES (92 );
INSERT INTO MARIME(marime_cm) VALUES (98 );
INSERT INTO MARIME(marime_cm) VALUES (104 );
INSERT INTO MARIME(marime_cm) VALUES (110 );
INSERT INTO MARIME(marime_cm) VALUES (116 );
INSERT INTO MARIME(marime_cm) VALUES (122 );

INSERT INTO PRODUS VALUES(NULL,'rochita','BG',1);
INSERT INTO PRODUS VALUES(NULL,'rochita','BG',2);
INSERT INTO PRODUS VALUES(NULL,'rochita','BG',3);
INSERT INTO PRODUS VALUES(NULL,'rochita','BG',4);
INSERT INTO PRODUS VALUES(NULL,'rochita','BG',5);
INSERT INTO PRODUS VALUES(NULL,'rochita','BG',6);

INSERT INTO PRODUS VALUES(NULL,'rochita','G',7);
INSERT INTO PRODUS VALUES(NULL,'rochita','G',8);
INSERT INTO PRODUS VALUES(NULL,'rochita','G',9);
INSERT INTO PRODUS VALUES(NULL,'rochita','G',10);
INSERT INTO PRODUS VALUES(NULL,'rochita','G',11);

INSERT INTO PRODUS VALUES(NULL,'fusta','BG',3);
INSERT INTO PRODUS VALUES(NULL,'fusta','BG',4);
INSERT INTO PRODUS VALUES(NULL,'fusta','BG',5);

INSERT INTO PRODUS VALUES(NULL,'fusta','G',9);
INSERT INTO PRODUS VALUES(NULL,'fusta','G',10);
INSERT INTO PRODUS VALUES(NULL,'fusta','G',11);

INSERT INTO PRODUS VALUES(NULL,'camasa','BG',4);
INSERT INTO PRODUS VALUES(NULL,'camasa','G',7);
INSERT INTO PRODUS VALUES(NULL,'camasa','BB',6);
INSERT INTO PRODUS VALUES(NULL,'camasa','B',8);
INSERT INTO PRODUS VALUES(NULL,'pantalon','BB',3);
INSERT INTO PRODUS VALUES(NULL,'pantalon','B',10);
INSERT INTO PRODUS VALUES(NULL,'sacou','BG',2);
INSERT INTO PRODUS VALUES(NULL,'sacou','G',9);
INSERT INTO PRODUS VALUES(NULL,'sacou','BB',1);
INSERT INTO PRODUS VALUES(NULL,'sacou','B',9);
INSERT INTO PRODUS VALUES(NULL,'palton','BG',5);
INSERT INTO PRODUS VALUES(NULL,'palton','G',11);
INSERT INTO PRODUS VALUES(NULL,'palton','BB',3);
INSERT INTO PRODUS VALUES(NULL,'palton','B',10);
INSERT INTO PRODUS VALUES(NULL,'pijama','BG',5);
INSERT INTO PRODUS VALUES(NULL,'pijama','G',8);
INSERT INTO PRODUS VALUES(NULL,'pijama','BB',2);
INSERT INTO PRODUS VALUES(NULL,'pijama','B',7);



INSERT INTO MATERIAL VALUES(NULL,'bumbac','alb');	
INSERT INTO MATERIAL VALUES(NULL,'bumbac','albastru');
INSERT INTO MATERIAL VALUES(NULL,'bumbac','roz');
INSERT INTO MATERIAL VALUES(NULL,'bumbac_s','alb');
INSERT INTO MATERIAL VALUES(NULL,'bumbac_s','mustar');
INSERT INTO MATERIAL VALUES(NULL,'bumbac_s','rosu');
INSERT INTO MATERIAL VALUES(NULL,'bumbac_m','alb');
INSERT INTO MATERIAL VALUES(NULL,'bumbac_m','galben');
INSERT INTO MATERIAL VALUES(NULL,'in','alb');
INSERT INTO MATERIAL VALUES(NULL,'stofa','crem');



INSERT INTO DEPARTAMENT VALUES (NULL,'manageriat','procurare materiale');
INSERT INTO DEPARTAMENT VALUES (NULL,'croitorie','confectionare haine');
INSERT INTO DEPARTAMENT VALUES (NULL,'design','creare sabloane');

INSERT INTO ANGAJAT VALUES (NULL,'Maftei','Alina',1900.50,SYSDATE-56,2);
INSERT INTO ANGAJAT VALUES (NULL,'Sandu','Maria',1800.50,SYSDATE-54,2);
INSERT INTO ANGAJAT VALUES (NULL,'Rosu','Ioana',1900.50,SYSDATE-56,2);
INSERT INTO ANGAJAT VALUES (NULL,'Vengher','Cristina',1800.50,SYSDATE-54,2);
INSERT INTO ANGAJAT VALUES (NULL,'Asoltanei','Sofica',1855.50,SYSDATE-56,2);
INSERT INTO ANGAJAT VALUES (NULL,'Mocanu','Delia',1900.50,SYSDATE-56,2);
INSERT INTO ANGAJAT VALUES (NULL,'Iancu','Ioana',2700.00,SYSDATE-50,1);
INSERT INTO ANGAJAT VALUES (NULL,'Iancu','Teodora',2700.00,SYSDATE-50,1);
INSERT INTO ANGAJAT VALUES (NULL,'Tudurache','Andrei',2700.00,SYSDATE-25,1);
INSERT INTO ANGAJAT VALUES (NULL,'Davidoaia','Cristina',3100.00,SYSDATE-50,3);

INSERT INTO CONFIDENTIAL VALUES (NULL,10,'2800507371245','0758574852','Iasi, Bd. Tudor Vladimirescu, nr 16');
INSERT INTO CONFIDENTIAL VALUES (NULL,9,'1650912487825','0762174498','Vaslui, Str. Alecu Donici, nr 3');
INSERT INTO CONFIDENTIAL VALUES (NULL,8,'2880207319245','0752858357','Iasi, Str. Nicolina, nr 20');
INSERT INTO CONFIDENTIAL VALUES (NULL,7,'2800507671245','0758392674','Iasi, Str. Nicolina, nr 22');
INSERT INTO CONFIDENTIAL VALUES (NULL,6,'2911028981771','0765574932','Vaslui, Bd. Traian, nr 7');
INSERT INTO CONFIDENTIAL VALUES (NULL,5,'2700507371245','0767892037','Vaslui, Bd. Traian, nr 10');
INSERT INTO CONFIDENTIAL VALUES (NULL,4,'2790507371245','0760182936',NULL);
INSERT INTO CONFIDENTIAL VALUES (NULL,3,'2950507371245','0727189024','Vaslui, Bd. Traian, nr 8');
INSERT INTO CONFIDENTIAL VALUES (NULL,2,'2934507371245','0769944789','Vaslui, Bd. Traian, nr 7');
INSERT INTO CONFIDENTIAL VALUES (NULL,1,'2600807371245','0755566789',NULL);

--SA MA UIT DACA ESTE NEVOIE DE ASTA
INSERT INTO CROITOR VALUES(NULL,1);
INSERT INTO CROITOR VALUES(NULL,2);
INSERT INTO CROITOR VALUES(NULL,3);
INSERT INTO CROITOR VALUES(NULL,4);
INSERT INTO CROITOR VALUES(NULL,5);
INSERT INTO CROITOR VALUES(NULL,6);

INSERT INTO MANAGER VALUES(NULL,7,200000);
INSERT INTO MANAGER VALUES(NULL,8,240000);
INSERT INTO MANAGER VALUES(NULL,9,250000);

insert into material_manager values(1,1, TO_DATE('01.03.2020', 'DD.MM.YYYY'),855,25,'Italia');
insert into material_manager values(2,1, TO_DATE('01.03.2020', 'DD.MM.YYYY'),900,26,'Italia');
insert into material_manager values(3,1, TO_DATE('01.03.2020', 'DD.MM.YYYY'),750,25,'Italia');
insert into material_manager values(9,1, TO_DATE('12.03.2020', 'DD.MM.YYYY'),750,25,'Franta');

insert into material_manager values(4,2, TO_DATE('14.03.2020', 'DD.MM.YYYY'),855,25,'Belgia');
insert into material_manager values(5,2, TO_DATE('15.03.2020', 'DD.MM.YYYY'),900,26,'Franta');
insert into material_manager values(6,2, TO_DATE('15.03.2020', 'DD.MM.YYYY'),750,25,'Franta');

insert into material_manager values(8,3, TO_DATE('05.03.2020', 'DD.MM.YYYY'),855,25,'Italia');
insert into material_manager values(7,3, TO_DATE('05.03.2020', 'DD.MM.YYYY'),900,26,'Italia');
insert into material_manager values(10,3, TO_DATE('07.03.2020', 'DD.MM.YYYY'),900,26,'Turcia');


insert into produs_croitor values(7,2,TO_DATE('01.04.2020', 'DD.MM.YYYY'),10);
insert into produs_croitor values(6,3,TO_DATE('01.04.2020', 'DD.MM.YYYY'),13);
insert into produs_croitor values(5,2,TO_DATE('01.04.2020', 'DD.MM.YYYY'),11);
insert into produs_croitor values(9,3,TO_DATE('01.04.2020', 'DD.MM.YYYY'),10);

insert into produs_croitor values(12,1,TO_DATE('02.04.2020', 'DD.MM.YYYY'),15);
insert into produs_croitor values(13,4,TO_DATE('02.04.2020', 'DD.MM.YYYY'),11);
insert into produs_croitor values(15,1,TO_DATE('02.04.2020', 'DD.MM.YYYY'),10);
insert into produs_croitor values(16,4,TO_DATE('02.04.2020', 'DD.MM.YYYY'),12);

insert into produs_croitor values(21,5,TO_DATE('01.04.2020', 'DD.MM.YYYY'),14);
insert into produs_croitor values(20,6,TO_DATE('01.04.2020', 'DD.MM.YYYY'),10);

insert into produs_croitor values(23,5,TO_DATE('03.04.2020', 'DD.MM.YYYY'),15);
insert into produs_croitor values(25,2,TO_DATE('03.04.2020', 'DD.MM.YYYY'),11);
insert into produs_croitor values(31,4,TO_DATE('02.04.2020', 'DD.MM.YYYY'),9);
insert into produs_croitor values(33,6,TO_DATE('01.04.2020', 'DD.MM.YYYY'),10);

insert into produs_material values(1,1,2);
insert into produs_material values(2,1,1);
insert into produs_material values(3,2,3);
insert into produs_material values(4,1,2);
insert into produs_material values(5,3,3);
insert into produs_material values(6,2,1);

insert into produs_material values(28,10,1);
insert into produs_material values(29,10,3);
insert into produs_material values(30,10,2);
insert into produs_material values(31,10,3);


