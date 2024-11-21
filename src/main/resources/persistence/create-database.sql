-- Database: ud_library

-- DROP DATABASE IF EXISTS ud_library;

CREATE DATABASE ud_library
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.role

-- DROP TABLE IF EXISTS public.role;

CREATE TABLE IF NOT EXISTS public.role
(
    id smallint NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.role
    OWNER to postgres;

-- Table: public.app_user

-- DROP TABLE IF EXISTS public.app_user;

-- Create the sequence first
CREATE SEQUENCE IF NOT EXISTS app_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Create the table with the id column using the sequence
CREATE TABLE IF NOT EXISTS public.app_user
(
    id smallint NOT NULL DEFAULT nextval('app_user_id_seq'),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    phone character varying(255) COLLATE pg_catalog."default",
    account_status boolean DEFAULT true,
    role_id smallint NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT app_user_pkey PRIMARY KEY (id),
    CONSTRAINT fk_role FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.app_user
    OWNER to postgres;

-- Table: public.author

-- DROP TABLE IF EXISTS public.author;

-- Create the sequence first
CREATE SEQUENCE IF NOT EXISTS author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.author
(
    id smallint NOT NULL DEFAULT nextval('author_id_seq'),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    country_of_origin character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT author_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.author
    OWNER to postgres;

-- Table: public.category

-- DROP TABLE IF EXISTS public.category;

-- Create the sequence first
CREATE SEQUENCE IF NOT EXISTS category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.category
(
    id smallint NOT NULL DEFAULT nextval('category_id_seq'),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT category_pkey PRIMARY KEY (id)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.category
    OWNER to postgres;

-- Table: public.book

-- DROP TABLE IF EXISTS public.book;

-- Create the sequence first
CREATE SEQUENCE IF NOT EXISTS book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.book
(
    id smallint NOT NULL DEFAULT nextval('book_id_seq'),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    publication_year smallint,
    availability boolean DEFAULT true,
    description character varying(255) COLLATE pg_catalog."default",
    category_id smallint,
    author_id smallint,
    CONSTRAINT book_pkey PRIMARY KEY (id),
    CONSTRAINT fk_author FOREIGN KEY (author_id)
        REFERENCES public.author (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_category FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;

-- Table: public.loan

-- DROP TABLE IF EXISTS public.loan;

-- Create the sequence first
CREATE SEQUENCE IF NOT EXISTS loan_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.loan
(
    id smallint NOT NULL DEFAULT nextval('loan_id_seq'),
    user_id smallint NOT NULL,
    start_date date NOT NULL,
    end_date date,
    CONSTRAINT loan_pkey PRIMARY KEY (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES public.app_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.loan
    OWNER to postgres;

-- Table: public.loan_book

-- DROP TABLE IF EXISTS public.loan_book;

CREATE TABLE IF NOT EXISTS public.loan_book
(
    loan_id smallint NOT NULL,
    book_id smallint NOT NULL,
    CONSTRAINT loan_book_pkey PRIMARY KEY (loan_id, book_id),
    CONSTRAINT fk_book FOREIGN KEY (book_id)
        REFERENCES public.book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_loan FOREIGN KEY (loan_id)
        REFERENCES public.loan (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.loan_book
    OWNER to postgres;
