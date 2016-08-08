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
firm_name varchar(50)
);

DROP TABLE IF EXISTS sentiments;
CREATE TABLE sentiments (
firm_id bigint,
avg_sentiment integer,
update_ts timestamp
);