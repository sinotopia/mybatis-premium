CREATE TABLE IF NOT EXISTS  h2address (
	addr_id BIGINT(20) NOT NULL ,
	addr_name VARCHAR(30) NULL DEFAULT NULL ,
	test_id BIGINT(20) NOT NULL ,
	PRIMARY KEY (addr_id)
)
