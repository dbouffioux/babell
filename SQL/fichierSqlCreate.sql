PGDMP         :                w           babell    11.2    11.2 #    #           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            $           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            %           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            &           1262    17774    babell    DATABASE     �   CREATE DATABASE babell WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_Belgium.1252' LC_CTYPE = 'French_Belgium.1252';
    DROP DATABASE babell;
             postgres    false            �            1259    17852    person    TABLE     �   CREATE TABLE public.person (
    id_person bigint NOT NULL,
    firstname character varying(150) NOT NULL,
    lastname character varying(150) NOT NULL,
    email character varying(150) NOT NULL,
    password character varying(150) NOT NULL
);
    DROP TABLE public.person;
       public         postgres    false            �            1259    17850    person_id_person_seq    SEQUENCE     }   CREATE SEQUENCE public.person_id_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.person_id_person_seq;
       public       postgres    false    201            '           0    0    person_id_person_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.person_id_person_seq OWNED BY public.person.id_person;
            public       postgres    false    200            �            1259    17786    project    TABLE     �   CREATE TABLE public.project (
    id_project bigint NOT NULL,
    name character varying(150) NOT NULL,
    project_start date NOT NULL,
    project_end date NOT NULL
);
    DROP TABLE public.project;
       public         postgres    false            �            1259    17784    project_id_project_seq    SEQUENCE        CREATE SEQUENCE public.project_id_project_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.project_id_project_seq;
       public       postgres    false    197            (           0    0    project_id_project_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.project_id_project_seq OWNED BY public.project.id_project;
            public       postgres    false    196            �            1259    17794    todo    TABLE       CREATE TABLE public.todo (
    id_todo bigint NOT NULL,
    name character varying(150) NOT NULL,
    description character varying(250) NOT NULL,
    estimation numeric,
    in_progress boolean NOT NULL,
    done boolean NOT NULL,
    id_project numeric NOT NULL
);
    DROP TABLE public.todo;
       public         postgres    false            �            1259    17792    todo_id_todo_seq    SEQUENCE     y   CREATE SEQUENCE public.todo_id_todo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.todo_id_todo_seq;
       public       postgres    false    199            )           0    0    todo_id_todo_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.todo_id_todo_seq OWNED BY public.todo.id_todo;
            public       postgres    false    198            �            1259    17874    todo_person    TABLE     �   CREATE TABLE public.todo_person (
    id_todo_person bigint NOT NULL,
    id_todo bigint NOT NULL,
    id_person bigint NOT NULL
);
    DROP TABLE public.todo_person;
       public         postgres    false            �            1259    17872    todo_person_id_todo_person_seq    SEQUENCE     �   CREATE SEQUENCE public.todo_person_id_todo_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.todo_person_id_todo_person_seq;
       public       postgres    false    203            *           0    0    todo_person_id_todo_person_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.todo_person_id_todo_person_seq OWNED BY public.todo_person.id_todo_person;
            public       postgres    false    202            �
           2604    17855    person id_person    DEFAULT     t   ALTER TABLE ONLY public.person ALTER COLUMN id_person SET DEFAULT nextval('public.person_id_person_seq'::regclass);
 ?   ALTER TABLE public.person ALTER COLUMN id_person DROP DEFAULT;
       public       postgres    false    201    200    201            �
           2604    17789    project id_project    DEFAULT     x   ALTER TABLE ONLY public.project ALTER COLUMN id_project SET DEFAULT nextval('public.project_id_project_seq'::regclass);
 A   ALTER TABLE public.project ALTER COLUMN id_project DROP DEFAULT;
       public       postgres    false    197    196    197            �
           2604    17797    todo id_todo    DEFAULT     l   ALTER TABLE ONLY public.todo ALTER COLUMN id_todo SET DEFAULT nextval('public.todo_id_todo_seq'::regclass);
 ;   ALTER TABLE public.todo ALTER COLUMN id_todo DROP DEFAULT;
       public       postgres    false    199    198    199            �
           2604    17877    todo_person id_todo_person    DEFAULT     �   ALTER TABLE ONLY public.todo_person ALTER COLUMN id_todo_person SET DEFAULT nextval('public.todo_person_id_todo_person_seq'::regclass);
 I   ALTER TABLE public.todo_person ALTER COLUMN id_todo_person DROP DEFAULT;
       public       postgres    false    203    202    203                      0    17852    person 
   TABLE DATA               Q   COPY public.person (id_person, firstname, lastname, email, password) FROM stdin;
    public       postgres    false    201   �&                 0    17786    project 
   TABLE DATA               O   COPY public.project (id_project, name, project_start, project_end) FROM stdin;
    public       postgres    false    197   �&                 0    17794    todo 
   TABLE DATA               e   COPY public.todo (id_todo, name, description, estimation, in_progress, done, id_project) FROM stdin;
    public       postgres    false    199   �&                  0    17874    todo_person 
   TABLE DATA               I   COPY public.todo_person (id_todo_person, id_todo, id_person) FROM stdin;
    public       postgres    false    203   �&       +           0    0    person_id_person_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.person_id_person_seq', 1, false);
            public       postgres    false    200            ,           0    0    project_id_project_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.project_id_project_seq', 1, false);
            public       postgres    false    196            -           0    0    todo_id_todo_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.todo_id_todo_seq', 1, false);
            public       postgres    false    198            .           0    0    todo_person_id_todo_person_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.todo_person_id_todo_person_seq', 1, false);
            public       postgres    false    202            �
           2606    17860    person person_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public         postgres    false    201            �
           2606    17791    project project_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id_project);
 >   ALTER TABLE ONLY public.project DROP CONSTRAINT project_pkey;
       public         postgres    false    197            �
           2606    17879    todo_person todo_person_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT todo_person_pkey PRIMARY KEY (id_todo_person);
 F   ALTER TABLE ONLY public.todo_person DROP CONSTRAINT todo_person_pkey;
       public         postgres    false    203            �
           2606    17802    todo todo_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.todo
    ADD CONSTRAINT todo_pkey PRIMARY KEY (id_todo);
 8   ALTER TABLE ONLY public.todo DROP CONSTRAINT todo_pkey;
       public         postgres    false    199            �
           2606    17880    todo_person FK_id_person    FK CONSTRAINT     �   ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT "FK_id_person" FOREIGN KEY (id_person) REFERENCES public.person(id_person);
 D   ALTER TABLE ONLY public.todo_person DROP CONSTRAINT "FK_id_person";
       public       postgres    false    201    2714    203            �
           2606    17803    todo FK_id_project    FK CONSTRAINT     }   ALTER TABLE ONLY public.todo
    ADD CONSTRAINT "FK_id_project" FOREIGN KEY (id_todo) REFERENCES public.project(id_project);
 >   ALTER TABLE ONLY public.todo DROP CONSTRAINT "FK_id_project";
       public       postgres    false    2710    199    197            �
           2606    17885    todo_person FK_id_todo    FK CONSTRAINT     {   ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT "FK_id_todo" FOREIGN KEY (id_todo) REFERENCES public.todo(id_todo);
 B   ALTER TABLE ONLY public.todo_person DROP CONSTRAINT "FK_id_todo";
       public       postgres    false    2712    203    199                  x������ � �            x������ � �            x������ � �             x������ � �     