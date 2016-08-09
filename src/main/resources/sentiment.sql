DROP SCHEMA IF EXISTS metalgear CASCADE;
CREATE SCHEMA metalgear;
ALTER SCHEMA metalgear OWNER TO metal_gear;

DROP SEQUENCE IF EXISTS metalgear.text_id_num;
CREATE SEQUENCE metalgear.text_id_num start 100000;

DROP TABLE IF EXISTS metalgear.texts;
CREATE TABLE metalgear.texts (
text_id bigint,
firm_id bigint,
user_id varchar(50),
text varchar(2000),
sentiment decimal
);

DROP TABLE IF EXISTS metalgear.firms;
CREATE TABLE metalgear.firms (
firm_id bigint,
firm_name varchar(100)
);

DROP TABLE IF EXISTS metalgear.sentiments;
CREATE TABLE metalgear.sentiments (
firm_id bigint,
avg_sentiment integer,
update_ts timestamp
);