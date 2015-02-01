# dropwizard-example
Example of web application consuming a RESTful service. We use the Dropwizard framework, both for the web application and the web service.

$ mysql -u root -p

> CREATE USER 'phonebookuser'@'localhost' IDENTIFIED BY'phonebookpassword';
> GRANT ALL ON phonebook.* TO 'phonebookuser'@'localhost';

> USE `phonebook`;
> 
> CREATE TABLE IF NOT EXISTS `contact` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `firstName` varchar(255) NOT NULL,
    `lastName` varchar(255) NOT NULL,
    `phone` varchar(30) NOT NULL,
    PRIMARY KEY (`id`)
    ) 
    ENGINE=InnoDB 
    DEFAULT CHARSET=utf8 
    AUTO_INCREMENT=1 ;

    > INSERT INTO `contact` VALUES (NUL L, 'John', 'Doe', '+123456789'), (NULL, 'Jane', 'Doe', '+987654321');


    CREATE TABLE IF NOT EXISTS `users` (
      `username` varchar(20) NOT NULL,
      `password` varchar(255) NOT NULL,
      PRIMARY KEY (`username`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

    INSERT INTO `users` VALUES ('wsuser', 'wspassword');
