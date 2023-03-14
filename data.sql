
create database gestion_entreprise;

\c gestion_entreprise;

create table societe(
    nom varchar(50),
    siege varchar(50),
    tel varchar(20),
    nomDirigeant varchar(100),
    date_creation date,
    id_fiscale varchar(20),
    num_stat varchar(20),
    debut_exercice date,
    devise varchar(10)
);

create table employes(
    email varchar(20),
    password varchar(20)
);
