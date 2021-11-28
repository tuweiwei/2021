use demo;
create table IF NOT EXISTS t_tuwei (
                         `id` INT UNSIGNED AUTO_INCREMENT,
                         `title` VARCHAR(100) NOT NULL,
                         `author` VARCHAR(40) NOT NULL,
                         `date` DATE,
                         PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table IF NOT EXISTS t_tuweituwei (
                         `id` INT UNSIGNED AUTO_INCREMENT,
                         `title` VARCHAR(100) NOT NULL,
                         `author` VARCHAR(40) NOT NULL,
                         `date` DATE,
                         PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;