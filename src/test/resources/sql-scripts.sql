--Authors Samples
insert into authors (author_name,country)
values("AuthorX","England");
insert into authors (author_name,country)
values("AuthorY","Italy");
insert into authors (author_name,country)
values("AuthorZ","France");


--Genres Samples
insert into genres (genre_name)
values ("Adventure");
insert into genres (genre_name)
values ("Detective");
insert into genres (genre_name)
values ("Science");


--Books Samples
insert into books (title,description,price,isbn,quantity_in_stock,published_day,author_fk_id)
values("Title1","Description1",50.0,"0000000000000",5,"1850-04-05",1);
insert into books (title,description,price,isbn,quantity_in_stock,published_day,author_fk_id)
values("Title2","Description2",100.0,"0000000000000",10,"1850-04-05",1);
insert into books (title,description,price,isbn,quantity_in_stock,published_day,author_fk_id)
values("Title3","Description3",150.0,"0000000000000",8,"1850-04-05",2);


--Books_genre Table
insert into book_genre (book_fk_id,genre_fk_id)
values(1,1);
insert into book_genre (book_fk_id,genre_fk_id)
values(2,1);
insert into book_genre (book_fk_id,genre_fk_id)
values(3,2);