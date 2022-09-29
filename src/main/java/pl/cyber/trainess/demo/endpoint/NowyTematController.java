package pl.cyber.trainess.demo.endpoint;

public class NowyTematController {
    /*

Typy danych:
Java              PostgreSQL

String            varchar(15) max długość 255  // text
Integer           integer
Boolean           boolean
Double            double precision 2
byte[]            bytea
BigDecimal        decimal(p, s)  --- najlepiej używać do struktur aplikacji księgowych




create database [nazwa]; - powoduje utworzenie bazy danych.
drop database [nazwa]; - powoduje usunięcie bazy danych.


create table [nazwa tabeli] (id [typ], nazwa [typ], itd.); - dzięki temu tworzymy tabele.
insert into [tabela] (kolumny) values (wartości); tworzenie rekordów.
update [tabela] set nazwa = ''  - do modyfikacji wszystkich rekordów
update [tabela] set nazwa = '' where id = '';  - do modyfikacji konkretnego rekordu
delete from [tabela] where id = ''; - usuwanie rekordu.
select [] FROM [tabela] where id = ''; wyszukujemy konkretny rekord
select [] FROM [tabela] where nazwa = ''; wyszukujemy rekordy po ich nazwie

select * FROM - pobieramy wszystkie kolumny z tabeli
select id, nazwa FROM
select id, nazwa FROM - pobieramy  kolumny id oraz nazwa z tabeli


alter table [tabela] ... - polecenia modyfikujące tabele.
 */

}
