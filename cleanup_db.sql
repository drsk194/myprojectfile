-- Clean up existing foreign key constraints and data
SET FOREIGN_KEY_CHECKS = 0;

-- Drop existing foreign key constraints if they exist
ALTER TABLE order_items DROP FOREIGN KEY IF EXISTS FKlf6f9q956mt144wiv6p1yko16;
ALTER TABLE user_role_mapping DROP FOREIGN KEY IF EXISTS FK8kud3egqkm4mugsbw5g1bik5n;
ALTER TABLE orders DROP FOREIGN KEY IF EXISTS FK624gtjin3po807j3vix093tlf;
ALTER TABLE payment DROP FOREIGN KEY IF EXISTS FKlouu98csyullos9k25tbpk4va;
ALTER TABLE profile DROP FOREIGN KEY IF EXISTS FK88rruc7qawl75mscnnwrah9sc;

-- Clear existing data to avoid constraint violations
TRUNCATE TABLE user_role_mapping;
TRUNCATE TABLE order_items;
TRUNCATE TABLE payment;
TRUNCATE TABLE orders;
TRUNCATE TABLE profile;
TRUNCATE TABLE product;
TRUNCATE TABLE customer;
TRUNCATE TABLE role;

-- Ensure product table has the correct structure
ALTER TABLE product ADD COLUMN IF NOT EXISTS product_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE profile ADD COLUMN IF NOT EXISTS profile_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE role ADD COLUMN IF NOT EXISTS role_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY;

SET FOREIGN_KEY_CHECKS = 1;