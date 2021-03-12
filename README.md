# Endterm

In this project, I developed a program for posting posts.
___

The project consists of classes: User, Role, Post, Category.
___

This program allows the admin to create posts, delete them, create users, give them roles, and delete them.
Users can display all the posts, save it to their profile and delete them.To create posts, we need to give a name, a description, select a category from the suggested ones, and the creation date will be indicated by the current date automatically.
___

Admin login: admin@gmail.com                                                                                                                                                       
Admin password: 12345
___
DATABASE

Table Role:
CREATE TABLE public.role
(
    id serial,
    name text,
    CONSTRAINT role_pkey PRIMARY KEY (id)
)

Table Category:
CREATE TABLE public.category
(
    id serial,
    name text,
    CONSTRAINT category_pkey PRIMARY KEY (id)
)

Table Post:
CREATE TABLE public.post
(
    id serial,
    title text NOT NULL,
    description text,
    added_date date,
    category_id integer,
    CONSTRAINT post_pkey PRIMARY KEY (id),
    CONSTRAINT title_unique UNIQUE (title),
    CONSTRAINT post_category_fkey FOREIGN KEY (category_id)
        REFERENCES public.category (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE SET NULL
)

Table User:
CREATE TABLE public."user"
(
    id serial,
    name text,
    surname text,
    username text NOT NULL,
    password text NOT NULL,
    role_id integer,
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT username_unique UNIQUE (username),
    CONSTRAINT user_role_fkey FOREIGN KEY (role_id)
        REFERENCES public.role (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE SET NULL
)

Table User_Post:
CREATE TABLE public.user_post
(
    user_id integer,
    post_id integer,
    CONSTRAINT user_post_unique UNIQUE (user_id, post_id),
    CONSTRAINT post_id_fkey FOREIGN KEY (post_id)
        REFERENCES public.post (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public."user" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

