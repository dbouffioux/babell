--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-09-26 21:15:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 18260)
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id_person bigint NOT NULL,
    firstname character varying(150) NOT NULL,
    lastname character varying(150) NOT NULL,
    email character varying(150) NOT NULL,
    password character varying(150) NOT NULL
);


ALTER TABLE public.person OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 18266)
-- Name: person_id_person_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.person_id_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_person_seq OWNER TO postgres;

--
-- TOC entry 2854 (class 0 OID 0)
-- Dependencies: 197
-- Name: person_id_person_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.person_id_person_seq OWNED BY public.person.id_person;


--
-- TOC entry 198 (class 1259 OID 18268)
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
    id_project bigint NOT NULL,
    name character varying(150) NOT NULL,
    projectStart date NOT NULL,
    projectEnd date NOT NULL
);


ALTER TABLE public.project OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 18271)
-- Name: project_id_project_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_id_project_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_id_project_seq OWNER TO postgres;

--
-- TOC entry 2855 (class 0 OID 0)
-- Dependencies: 199
-- Name: project_id_project_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_id_project_seq OWNED BY public.project.id_project;


--
-- TOC entry 200 (class 1259 OID 18273)
-- Name: todo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.todo (
    id_todo bigint NOT NULL,
    name character varying(150) NOT NULL,
    description character varying(250) NOT NULL,
    estimation numeric,
    in_progress boolean NOT NULL,
    done boolean NOT NULL,
    id_project numeric NOT NULL
);


ALTER TABLE public.todo OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 18279)
-- Name: todo_id_todo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.todo_id_todo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.todo_id_todo_seq OWNER TO postgres;

--
-- TOC entry 2856 (class 0 OID 0)
-- Dependencies: 201
-- Name: todo_id_todo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.todo_id_todo_seq OWNED BY public.todo.id_todo;


--
-- TOC entry 202 (class 1259 OID 18281)
-- Name: todo_person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.todo_person (
    id_todo_person bigint NOT NULL,
    id_todo bigint NOT NULL,
    id_person bigint NOT NULL
);


ALTER TABLE public.todo_person OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 18284)
-- Name: todo_person_id_todo_person_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.todo_person_id_todo_person_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.todo_person_id_todo_person_seq OWNER TO postgres;

--
-- TOC entry 2857 (class 0 OID 0)
-- Dependencies: 203
-- Name: todo_person_id_todo_person_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.todo_person_id_todo_person_seq OWNED BY public.todo_person.id_todo_person;


--
-- TOC entry 2705 (class 2604 OID 18286)
-- Name: person id_person; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person ALTER COLUMN id_person SET DEFAULT nextval('public.person_id_person_seq'::regclass);


--
-- TOC entry 2706 (class 2604 OID 18287)
-- Name: project id_project; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN id_project SET DEFAULT nextval('public.project_id_project_seq'::regclass);


--
-- TOC entry 2707 (class 2604 OID 18288)
-- Name: todo id_todo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.todo ALTER COLUMN id_todo SET DEFAULT nextval('public.todo_id_todo_seq'::regclass);


--
-- TOC entry 2708 (class 2604 OID 18289)
-- Name: todo_person id_todo_person; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.todo_person ALTER COLUMN id_todo_person SET DEFAULT nextval('public.todo_person_id_todo_person_seq'::regclass);


--
-- TOC entry 2841 (class 0 OID 18260)
-- Dependencies: 196
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (id_person, firstname, lastname, email, password) FROM stdin;
\.


--
-- TOC entry 2843 (class 0 OID 18268)
-- Dependencies: 198
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.project (id_project, name, projectStart, projectEnd) FROM stdin;
\.


--
-- TOC entry 2845 (class 0 OID 18273)
-- Dependencies: 200
-- Data for Name: todo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.todo (id_todo, name, description, estimation, in_progress, done, id_project) FROM stdin;
\.


--
-- TOC entry 2847 (class 0 OID 18281)
-- Dependencies: 202
-- Data for Name: todo_person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.todo_person (id_todo_person, id_todo, id_person) FROM stdin;
\.


--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 197
-- Name: person_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_person_seq', 1, false);


--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 199
-- Name: project_id_project_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.project_id_project_seq', 1, false);


--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 201
-- Name: todo_id_todo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.todo_id_todo_seq', 1, false);


--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 203
-- Name: todo_person_id_todo_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.todo_person_id_todo_person_seq', 1, false);


--
-- TOC entry 2710 (class 2606 OID 18291)
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id_person);


--
-- TOC entry 2712 (class 2606 OID 18293)
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id_project);


--
-- TOC entry 2716 (class 2606 OID 18295)
-- Name: todo_person todo_person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT todo_person_pkey PRIMARY KEY (id_todo_person);


--
-- TOC entry 2714 (class 2606 OID 18297)
-- Name: todo todo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.todo
    ADD CONSTRAINT todo_pkey PRIMARY KEY (id_todo);


--
-- TOC entry 2718 (class 2606 OID 18298)
-- Name: todo_person FK_id_person; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT "FK_id_person" FOREIGN KEY (id_person) REFERENCES public.person(id_person);


--
-- TOC entry 2717 (class 2606 OID 18303)
-- Name: todo FK_id_project; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.todo
    ADD CONSTRAINT "FK_id_project" FOREIGN KEY (id_todo) REFERENCES public.project(id_project);


--
-- TOC entry 2719 (class 2606 OID 18308)
-- Name: todo_person FK_id_todo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.todo_person
    ADD CONSTRAINT "FK_id_todo" FOREIGN KEY (id_todo) REFERENCES public.todo(id_todo);


-- Completed on 2019-09-26 21:15:37

--
-- PostgreSQL database dump complete
--

