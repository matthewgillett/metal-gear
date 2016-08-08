DROP SEQUENCE IF EXISTS text_id_num;
CREATE SEQUENCE text_id_num start 100000;

DROP TABLE IF EXISTS texts;
CREATE TABLE texts (
text_id bigint,
firm_id bigint,
user_id varchar(50),
text varchar(2000),
sentiment decimal
);

DROP TABLE IF EXISTS firms;
CREATE TABLE firms (
firm_id bigint,
firm_name varchar(100)
);

DROP TABLE IF EXISTS sentiments;
CREATE TABLE sentiments (
firm_id bigint,
avg_sentiment integer,
update_ts timestamp
);