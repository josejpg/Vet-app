--
-- PostgreSQL database dump
--

-- Dumped from database version 11.1
-- Dumped by pg_dump version 11.1

-- Started on 2019-01-24 20:04:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 595 (class 1247 OID 16448)
-- Name: sexoMascota; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."sexoMascota" AS ENUM (
    'M',
    'H'
);


ALTER TYPE public."sexoMascota" OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16439)
-- Name: mascota; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.mascota (
    nombre character varying(255) NOT NULL,
    edad integer NOT NULL,
    raza character varying(255) NOT NULL,
    color character varying(255) NOT NULL,
    observaciones text,
    sexo "char" NOT NULL,
    id bigint NOT NULL
);


ALTER TABLE public.mascota OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16459)
-- Name: gato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.gato (
    unyas boolean NOT NULL
)
INHERITS (public.mascota);


ALTER TABLE public.gato OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 24576)
-- Name: mascota_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.mascota_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mascota_id_seq OWNER TO postgres;

--
-- TOC entry 2834 (class 0 OID 0)
-- Dependencies: 199
-- Name: mascota_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.mascota_id_seq OWNED BY public.mascota.id;


--
-- TOC entry 197 (class 1259 OID 16453)
-- Name: perro; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perro (
    pelaje character varying(255) NOT NULL,
    peligrosa boolean NOT NULL
)
INHERITS (public.mascota);


ALTER TABLE public.perro OWNER TO postgres;

--
-- TOC entry 2701 (class 2604 OID 24580)
-- Name: gato id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.gato ALTER COLUMN id SET DEFAULT nextval('public.mascota_id_seq'::regclass);


--
-- TOC entry 2699 (class 2604 OID 24578)
-- Name: mascota id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mascota ALTER COLUMN id SET DEFAULT nextval('public.mascota_id_seq'::regclass);


--
-- TOC entry 2700 (class 2604 OID 24579)
-- Name: perro id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perro ALTER COLUMN id SET DEFAULT nextval('public.mascota_id_seq'::regclass);


--
-- TOC entry 2827 (class 0 OID 16459)
-- Dependencies: 198
-- Data for Name: gato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.gato (nombre, edad, raza, color, observaciones, unyas, sexo, id) FROM stdin;
gato	22	sdfsdf	sdfdsf	sdfdsfds	f	H	4
gato 2	44	asdfsf	sdfsdf	asdsad	t	M	5
gato hembra con uñas	11	asdsad	asdsad	asdsad	t	H	6
\.


--
-- TOC entry 2825 (class 0 OID 16439)
-- Dependencies: 196
-- Data for Name: mascota; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.mascota (nombre, edad, raza, color, observaciones, sexo, id) FROM stdin;
\.


--
-- TOC entry 2826 (class 0 OID 16453)
-- Dependencies: 197
-- Data for Name: perro; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.perro (nombre, edad, raza, color, observaciones, pelaje, peligrosa, sexo, id) FROM stdin;
adsad	12	sadsad	asdsad	sdfsdfsdf	sdfsdf	f	M	1
perro 2	1	sdfdsf	sdfdsf	sdfsdfdsf	dsfdsf	f	H	2
perro macho peligroso	11	asdsad	asdsad	asdsad	adasdsa	t	M	3
\.


--
-- TOC entry 2835 (class 0 OID 0)
-- Dependencies: 199
-- Name: mascota_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.mascota_id_seq', 6, true);


--
-- TOC entry 2703 (class 2606 OID 24600)
-- Name: mascota mascota_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.mascota
    ADD CONSTRAINT mascota_pkey PRIMARY KEY (id);


-- Completed on 2019-01-24 20:04:07

--
-- PostgreSQL database dump complete
--

