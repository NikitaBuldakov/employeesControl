--
-- PostgreSQL database dump
--

-- Dumped from database version 13.1
-- Dumped by pg_dump version 13.1

-- Started on 2021-07-30 10:49:11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 201 (class 1259 OID 17036)
-- Name: employee; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee (
                                 id bigint NOT NULL,
                                 birthday timestamp without time zone,
                                 dateofemployment timestamp without time zone,
                                 email character varying(255),
                                 englishmastery character varying(255),
                                 fio character varying(255),
                                 levelofdeveloper character varying(255),
                                 username character varying(255),
                                 phone character varying(255),
                                 secondname character varying(255),
                                 skype character varying(255),
                                 surname character varying(255),
                                 workexperience character varying(255),
                                 feedback_id bigint,
                                 project_id bigint
);


ALTER TABLE public.employee OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 17034)
-- Name: employee_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.employee_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.employee_id_seq OWNER TO postgres;

--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 200
-- Name: employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;


--
-- TOC entry 203 (class 1259 OID 17047)
-- Name: feedback; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.feedback (
                                 id bigint NOT NULL,
                                 dateofcreating timestamp without time zone,
                                 description character varying(255)
);


ALTER TABLE public.feedback OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 17045)
-- Name: feedback_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.feedback_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.feedback_id_seq OWNER TO postgres;

--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 202
-- Name: feedback_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.feedback_id_seq OWNED BY public.feedback.id;


--
-- TOC entry 205 (class 1259 OID 17055)
-- Name: project; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.project (
                                id bigint NOT NULL,
                                customer character varying(255),
                                duration character varying(255),
                                methodology character varying(255),
                                name character varying(255),
                                projectmanager character varying(255),
                                team_id bigint
);


ALTER TABLE public.project OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 17053)
-- Name: project_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.project_id_seq OWNER TO postgres;

--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 204
-- Name: project_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.project_id_seq OWNED BY public.project.id;


--
-- TOC entry 207 (class 1259 OID 17066)
-- Name: team; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.team (
                             id bigint NOT NULL,
                             team_id bigint
);


ALTER TABLE public.team OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 17064)
-- Name: team_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.team_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.team_id_seq OWNER TO postgres;

--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 206
-- Name: team_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.team_id_seq OWNED BY public.team.id;


--
-- TOC entry 2870 (class 2604 OID 17039)
-- Name: employee id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);


--
-- TOC entry 2871 (class 2604 OID 17050)
-- Name: feedback id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback ALTER COLUMN id SET DEFAULT nextval('public.feedback_id_seq'::regclass);


--
-- TOC entry 2872 (class 2604 OID 17058)
-- Name: project id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project ALTER COLUMN id SET DEFAULT nextval('public.project_id_seq'::regclass);


--
-- TOC entry 2873 (class 2604 OID 17069)
-- Name: team id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team ALTER COLUMN id SET DEFAULT nextval('public.team_id_seq'::regclass);


--
-- TOC entry 3019 (class 0 OID 17036)
-- Dependencies: 201
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.employee (id, birthday, dateofemployment, email, englishmastery, fio, levelofdeveloper, username, phone, secondname, skype, surname, workexperience, feedback_id, project_id) FROM stdin;
2	2000-04-24 00:00:00	2021-08-12 00:00:00	nkb5@mail.ru	B2	Buldakov N. K.	J1	Nikita	89533553049	Konstantinovich	some skypes adress	Buldakov	None	2	1
3	2000-04-24 00:00:00	2021-08-12 00:00:00	nkb5@mail.ru	B2	Buldakov N. K.	J1	Nikita	89533553049	Konstantinovich	some skypes adress	Buldakov	None	2	1
\.


--
-- TOC entry 3021 (class 0 OID 17047)
-- Dependencies: 203
-- Data for Name: feedback; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.feedback (id, dateofcreating, description) FROM stdin;
1	2021-07-30 09:02:04	All cool
2	2021-07-22 09:02:04	Nice
3	2021-07-16 09:02:04	Bad
\.


--
-- TOC entry 3023 (class 0 OID 17055)
-- Dependencies: 205
-- Data for Name: project; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.project (id, customer, duration, methodology, name, projectmanager, team_id) FROM stdin;
1	Yandex	2 years	methodology 1	browser	Jacke	1
2	Google	year	methodology 2	browser, but different	Samantha	2
3	Amazon	3 months	methodology 3	market	Mac	3
\.


--
-- TOC entry 3025 (class 0 OID 17066)
-- Dependencies: 207
-- Data for Name: team; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.team (id, team_id) FROM stdin;
1	1
2	2
3	3
\.


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 200
-- Name: employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.employee_id_seq', 3, true);


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 202
-- Name: feedback_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.feedback_id_seq', 3, true);


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 204
-- Name: project_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.project_id_seq', 3, true);


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 206
-- Name: team_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.team_id_seq', 3, true);


--
-- TOC entry 2875 (class 2606 OID 17044)
-- Name: employee employee_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);


--
-- TOC entry 2877 (class 2606 OID 17052)
-- Name: feedback feedback_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.feedback
    ADD CONSTRAINT feedback_pkey PRIMARY KEY (id);


--
-- TOC entry 2879 (class 2606 OID 17063)
-- Name: project project_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- TOC entry 2881 (class 2606 OID 17071)
-- Name: team team_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT team_pkey PRIMARY KEY (id);


--
-- TOC entry 2883 (class 2606 OID 17073)
-- Name: team uk_8h2hosavjvtfgb1yxcdpgwulm; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.team
    ADD CONSTRAINT uk_8h2hosavjvtfgb1yxcdpgwulm UNIQUE (team_id);


--
-- TOC entry 2887 (class 2606 OID 17089)
-- Name: project fk99hcloicqmg95ty11qht49n8x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.project
    ADD CONSTRAINT fk99hcloicqmg95ty11qht49n8x FOREIGN KEY (team_id) REFERENCES public.team(id);


--
-- TOC entry 2884 (class 2606 OID 17074)
-- Name: employee fko3lcaa550krur49un8uckv6fq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fko3lcaa550krur49un8uckv6fq FOREIGN KEY (feedback_id) REFERENCES public.feedback(id);


--
-- TOC entry 2885 (class 2606 OID 17079)
-- Name: employee fkt7hn4e5o1y2vv3gawqldhhsj8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkt7hn4e5o1y2vv3gawqldhhsj8 FOREIGN KEY (project_id) REFERENCES public.project(id);


--
-- TOC entry 2886 (class 2606 OID 17084)
-- Name: employee fkti2loathd3ugu89fahop5gtxq; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.employee
    ADD CONSTRAINT fkti2loathd3ugu89fahop5gtxq FOREIGN KEY (id) REFERENCES public.team(id);


-- Completed on 2021-07-30 10:49:12

--
-- PostgreSQL database dump complete
--

