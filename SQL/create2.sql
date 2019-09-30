PGDMP     "    '                w           babell    11.2    11.2 #    #           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            $           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            %           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            &           1262    18361    babell    DATABASE     �   CREATE DATABASE babell WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_Belgium.1252' LC_CTYPE = 'French_Belgium.1252';
    DROP DATABASE babell;
             postgres    false            �            1259    18362    person    TABLE     �   CREATE TABLE public.person (
    id_person bigint NOT NULL,
    firstname character varying(150) NOT NULL,
    lastname character varying(150) NOT NULL,
    email character varying(150) NOT NULL,
    password character varying(150) NOT NULL
);
    DROP TABLE public.person;
       public         postgres    false            �            1259    18368    person_id_person_seq    SEQUENCE     }   CREATE SEQUENCE public.person_id_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.person_id_person_seq;
       public       postgres    false    196            '           0    0    person_id_person_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.person_id_person_seq OWNED BY public.person.id_person;
            public       postgres    false    197            �            1259    18370    project    TABLE     �   CREATE TABLE public.project (
    id_project bigint NOT NULL,
    name character varying(150) NOT NULL,
    project_start date NOT NULL,
    project_end date NOT NULL
);
    DROP TABLE public.project;
       public         postgres    false            �            1259    18373    project_id_project_seq    SEQUENCE        CREATE SEQUENCE public.project_id_project_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.project_id_project_seq;
       public       postgres    false    198            (           0    0    project_id_project_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.project_id_project_seq OWNED BY public.project.id_project;
            public       postgres    false    199            �            1259    18375    todo    TABLE       CREATE TABLE public.todo (
    id_todo bigint NOT NULL,
    name character varying(150) NOT NULL,
    description character varying(250) NOT NULL,
    estimation numeric,
    in_progress boolean NOT NULL,
    done boolean NOT NULL,
    id_project numeric NOT NULL
);
    DROP TABLE public.todo;
       public         postgres    false            �            1259    18381    todo_id_todo_seq    SEQUENCE     y   CREATE SEQUENCE public.todo_id_todo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.todo_id_todo_seq;
       public       postgres    false    200            )           0    0    todo_id_todo_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.todo_id_todo_seq OWNED BY public.todo.id_todo;
            public       postgres    false    201            �            1259    18383    todo_person    TABLE     �   CREATE TABLE public.todo_person (
    id_todo_person bigint NOT NULL,
    id_todo bigint NOT NULL,
    id_person bigint NOT NULL
);
    DROP TABLE public.todo_person;
       public         postgres    false            �            1259    18386    todo_person_id_todo_person_seq    SEQUENCE     �   CREATE SEQUENCE public.todo_person_id_todo_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.todo_person_id_todo_person_seq;
       public       postgres    false    202            *           0    0    todo_person_id_todo_person_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.todo_person_id_todo_person_seq OWNED BY public.todo_person.id_todo_person;
            public       postgres    false    203            �
           2604    18388    person id_person    DEFAULT     t   ALTER TABLE ONLY public.person ALTER COLUMN id_person SET DEFAULT nextval('public.person_id_person_seq'::regclass);
 ?   ALTER TABLE public.person ALTER COLUMN id_person DROP DEFAULT;
       public       postgres    false    197    196            �
           2604    18389    project id_project    DEFAULT     x   ALTER TABLE ONLY public.project ALTER COLUMN id_project SET DEFAULT nextval('public.project_id_project_seq'::regclass);
 A   ALTER TABLE public.project ALTER COLUMN id_project DROP DEFAULT;
       public       postgres    false    199    198            �
           2604    18390    todo id_todo    DEFAULT     l   ALTER TABLE ONLY public.todo ALTER COLUMN id_todo SET DEFAULT nextval('public.todo_id_todo_seq'::regclass);
 ;   ALTER TABLE public.todo ALTER COLUMN id_todo DROP DEFAULT;
       public       postgres    false    201    200            �
           2604    18391    todo_person id_todo_person    DEFAULT     �   ALTER TABLE ONLY public.todo_person ALTER COLUMN id_todo_person SET DEFAULT nextval('public.todo_person_id_todo_person_seq'::regclass);
 I   ALTER TABLE public.todo_person ALTER COLUMN id_todo_person DROP DEFAULT;
       public       postgres    false    203    202                      0    18362    person 
   TABLE DATA                     public       postgres    false    196   %                 0    18370    project 
   TABLE DATA                     public       postgres    false    198   8%                 0    18375    todo 
   TABLE DATA                     public       postgres    false    200   &                 0    18383    todo_person 
   TABLE DATA                     public       postgres    false    202   �&       +           0    0    person_id_person_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.person_id_person_seq', 1, false);
            public       postgres    false    197            ,           0    0    project_id_project_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.project_id_project_seq', 9, true);
            public       postgres    false    199            -           0    0    todo_id_todo_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.todo_id_todo_seq', 4, true);
            public       postgres    false    201            .           0    0    todo_person_id_todo_person_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.todo_person_id_todo_person_seq', 1, false);
            public       postgres    false    203            �
           2606    18393    person person_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public         postgres    false    196            �
           2606    18395    project project_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id_project);
 >   ALTER TABLE ONLY public.project DROP CONSTRAINT project_pkey;
       public         postgres    false    198            �
           2606    18397    todo_person todo_person_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT todo_person_pkey PRIMARY KEY (id_todo_person);
 F   ALTER TABLE ONLY public.todo_person DROP CONSTRAINT todo_person_pkey;
       public         postgres    false    202            �
           2606    18399    todo todo_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.todo
    ADD CONSTRAINT todo_pkey PRIMARY KEY (id_todo);
 8   ALTER TABLE ONLY public.todo DROP CONSTRAINT todo_pkey;
       public         postgres    false    200            �
           2606    18400    todo_person FK_id_person    FK CONSTRAINT     �   ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT "FK_id_person" FOREIGN KEY (id_person) REFERENCES public.person(id_person);
 D   ALTER TABLE ONLY public.todo_person DROP CONSTRAINT "FK_id_person";
       public       postgres    false    2710    202    196            �
           2606    18405    todo FK_id_project    FK CONSTRAINT     }   ALTER TABLE ONLY public.todo
    ADD CONSTRAINT "FK_id_project" FOREIGN KEY (id_todo) REFERENCES public.project(id_project);
 >   ALTER TABLE ONLY public.todo DROP CONSTRAINT "FK_id_project";
       public       postgres    false    198    2712    200            �
           2606    18410    todo_person FK_id_todo    FK CONSTRAINT     {   ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT "FK_id_todo" FOREIGN KEY (id_todo) REFERENCES public.todo(id_todo);
 B   ALTER TABLE ONLY public.todo_person DROP CONSTRAINT "FK_id_todo";
       public       postgres    false    200    202    2714               
   x���             �   x���;�0��_�H�i���	��DЕ�	�R��{c���n�L_�=Q���EqzE�P��ܴ�|�R!����vP����Ƙ�*��yS��\�a�,� �\.F�����7%J0a�}4������ji-e���Gނq�C)�ek�Z�z����Z��z3���A��w�\�[=�i�z�����[��ֆ��	۠         �   x���v
Q���W((M��L�+�O�W��L�1t�sSuRR���2J2��tR�K2s!�̼��������b���<�Z�V�PVjr��B��O�k������z	P�:�F6(��㣣���S�
��4���� l4�         
   x���         