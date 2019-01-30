create database parserdb;

create table category(
category_id INT NOT NULL auto_increment,
category_name varchar(255),
category_url varchar(255),
primary key (category_id)
);

create table article(
    article_id INT AUTO_INCREMENT ,
    url VARCHAR(255) NOT NULL,
    title varchar(255) NOT NULL,
    article_text longtext NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(category_id),
    PRIMARY KEY (article_id)

);