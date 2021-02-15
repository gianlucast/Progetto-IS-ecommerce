SELECT * FROM techzone.order;
UPDATE techzone.order SET stato="In preparazione" WHERE numeroOrdine=2;
DELETE from techzone.order WHERE numeroOrdine=3;
ALTER TABLE techzone.order AUTO_INCREMENT = 2;
update product SET quantita=15 WHERE codiceProdotto=1;