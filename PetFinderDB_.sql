PGDMP      6                 |         	   PetFinder    16.0    16.0 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17585 	   PetFinder    DATABASE     �   CREATE DATABASE "PetFinder" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "PetFinder";
                postgres    false            u           1247    17617    new_age    TYPE     \   CREATE TYPE public.new_age AS ENUM (
    'Baby',
    'Young',
    'Adult',
    'Elderly'
);
    DROP TYPE public.new_age;
       public          postgres    false            i           1247    17587 
   new_gender    TYPE     Q   CREATE TYPE public.new_gender AS ENUM (
    'Male',
    'Female',
    'Other'
);
    DROP TYPE public.new_gender;
       public          postgres    false            o           1247    17601    new_size    TYPE     c   CREATE TYPE public.new_size AS ENUM (
    'Small',
    'Medium',
    'Large',
    'Extra Large'
);
    DROP TYPE public.new_size;
       public          postgres    false            {           1247    17719    rolename    TYPE     P   CREATE TYPE public.rolename AS ENUM (
    'User',
    'Shleter',
    'Admin'
);
    DROP TYPE public.rolename;
       public          postgres    false            �            1255    18150 %   deleteanimalbyname(character varying) 	   PROCEDURE     t  CREATE PROCEDURE public.deleteanimalbyname(IN p_animalname character varying, OUT p_success boolean)
    LANGUAGE plpgsql
    AS $$
DECLARE
    v_AnimalID INT;
BEGIN
    p_Success := false;

    SELECT AnimalID INTO v_AnimalID
    FROM Animal
    WHERE AnimalName = p_AnimalName;


    IF v_AnimalID IS NOT NULL THEN
        DELETE FROM FoundAnimals WHERE AnimalID = v_AnimalID;
        DELETE FROM Favorites WHERE AnimalID = v_AnimalID;
        DELETE FROM RequestAnimal WHERE AnimalID = v_AnimalID;
        DELETE FROM Animal WHERE AnimalID = v_AnimalID;


        p_Success := TRUE;
    END IF;
END;
$$;
 d   DROP PROCEDURE public.deleteanimalbyname(IN p_animalname character varying, OUT p_success boolean);
       public          postgres    false            �            1259    17626    Age    TABLE     X   CREATE TABLE public."Age" (
    ageid integer NOT NULL,
    lifestage public.new_age
);
    DROP TABLE public."Age";
       public         heap    postgres    false    885            �            1259    17625    Age_ageid_seq    SEQUENCE     �   CREATE SEQUENCE public."Age_ageid_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public."Age_ageid_seq";
       public          postgres    false    220            �           0    0    Age_ageid_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."Age_ageid_seq" OWNED BY public."Age".ageid;
          public          postgres    false    219            �            1259    17610    Size    TABLE     X   CREATE TABLE public."Size" (
    sizeid integer NOT NULL,
    "Size" public.new_size
);
    DROP TABLE public."Size";
       public         heap    postgres    false    879            �            1259    17609    Size_sizeid_seq    SEQUENCE     �   CREATE SEQUENCE public."Size_sizeid_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."Size_sizeid_seq";
       public          postgres    false    218            �           0    0    Size_sizeid_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."Size_sizeid_seq" OWNED BY public."Size".sizeid;
          public          postgres    false    217            �            1259    17756    User    TABLE     �   CREATE TABLE public."User" (
    userid integer NOT NULL,
    username character varying(50) NOT NULL,
    "Password" character varying(50) NOT NULL,
    personid integer,
    roleid integer
);
    DROP TABLE public."User";
       public         heap    postgres    false            �            1259    17755    User_userid_seq    SEQUENCE     �   CREATE SEQUENCE public."User_userid_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public."User_userid_seq";
       public          postgres    false    226            �           0    0    User_userid_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public."User_userid_seq" OWNED BY public."User".userid;
          public          postgres    false    225            �            1259    17900    animal    TABLE     @  CREATE TABLE public.animal (
    animalid integer NOT NULL,
    animalname character varying(50) NOT NULL,
    animaltypeid integer,
    genderid integer,
    sizeid integer,
    ageid integer,
    breedid integer,
    colorid integer,
    description text,
    shelterid integer,
    photourl character varying(255)
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    17899    animal_animalid_seq    SEQUENCE     �   CREATE SEQUENCE public.animal_animalid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.animal_animalid_seq;
       public          postgres    false    238            �           0    0    animal_animalid_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.animal_animalid_seq OWNED BY public.animal.animalid;
          public          postgres    false    237            �            1259    17870 
   animaltype    TABLE     u   CREATE TABLE public.animaltype (
    animaltypeid integer NOT NULL,
    animaltype character varying(50) NOT NULL
);
    DROP TABLE public.animaltype;
       public         heap    postgres    false            �            1259    17861    breed    TABLE     �   CREATE TABLE public.breed (
    breedid integer NOT NULL,
    animaltypeid integer,
    breedname character varying(50) NOT NULL,
    description text
);
    DROP TABLE public.breed;
       public         heap    postgres    false            �            1259    17891    color    TABLE     f   CREATE TABLE public.color (
    colorid integer NOT NULL,
    color character varying(50) NOT NULL
);
    DROP TABLE public.color;
       public         heap    postgres    false            �            1259    17987    foundanimals    TABLE     *  CREATE TABLE public.foundanimals (
    foundanimals integer NOT NULL,
    userid integer,
    animalid integer,
    founddate date DEFAULT CURRENT_DATE,
    city character varying(70) NOT NULL,
    county character varying(70) NOT NULL,
    street character varying(70) NOT NULL,
    nr integer
);
     DROP TABLE public.foundanimals;
       public         heap    postgres    false            �            1259    17594    gender    TABLE     \   CREATE TABLE public.gender (
    genderid integer NOT NULL,
    gender public.new_gender
);
    DROP TABLE public.gender;
       public         heap    postgres    false    873            �            1259    18108    animaldetails    VIEW     4  CREATE VIEW public.animaldetails AS
 SELECT a.animalid,
    a.animalname,
    at.animaltype,
    g.gender,
    s."Size",
    ag.lifestage AS "Age",
    b.breedname AS breed,
    c.color,
    a.description,
    a.photourl,
    fa.founddate,
    fa.city,
    fa.county,
    fa.street,
    fa.nr,
    u.username
   FROM ((((((((public.animal a
     JOIN public.animaltype at ON ((a.animaltypeid = at.animaltypeid)))
     JOIN public.gender g ON ((a.genderid = g.genderid)))
     JOIN public."Size" s ON ((a.sizeid = s.sizeid)))
     JOIN public."Age" ag ON ((a.ageid = ag.ageid)))
     JOIN public.breed b ON ((a.breedid = b.breedid)))
     JOIN public.color c ON ((a.colorid = c.colorid)))
     LEFT JOIN public.foundanimals fa ON ((a.animalid = fa.animalid)))
     LEFT JOIN public."User" u ON ((fa.userid = u.userid)));
     DROP VIEW public.animaldetails;
       public          postgres    false    216    218    218    220    220    226    238    238    238    240    240    240    240    240    238    238    226    232    232    234    234    236    236    238    238    238    240    238    240    238    216    879    873    885            �            1259    17869    animaltype_animaltypeid_seq    SEQUENCE     �   CREATE SEQUENCE public.animaltype_animaltypeid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.animaltype_animaltypeid_seq;
       public          postgres    false    234            �           0    0    animaltype_animaltypeid_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.animaltype_animaltypeid_seq OWNED BY public.animaltype.animaltypeid;
          public          postgres    false    233            �            1259    17860    breed_breedid_seq    SEQUENCE     �   CREATE SEQUENCE public.breed_breedid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.breed_breedid_seq;
       public          postgres    false    232            �           0    0    breed_breedid_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.breed_breedid_seq OWNED BY public.breed.breedid;
          public          postgres    false    231            �            1259    17890    color_colorid_seq    SEQUENCE     �   CREATE SEQUENCE public.color_colorid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.color_colorid_seq;
       public          postgres    false    236            �           0    0    color_colorid_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.color_colorid_seq OWNED BY public.color.colorid;
          public          postgres    false    235            �            1259    18120 	   favorites    TABLE     �   CREATE TABLE public.favorites (
    favoriteid integer NOT NULL,
    userid integer,
    animalid integer,
    shelterid integer
);
    DROP TABLE public.favorites;
       public         heap    postgres    false            �            1259    18119    favorites_favoriteid_seq    SEQUENCE     �   CREATE SEQUENCE public.favorites_favoriteid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.favorites_favoriteid_seq;
       public          postgres    false    245            �           0    0    favorites_favoriteid_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.favorites_favoriteid_seq OWNED BY public.favorites.favoriteid;
          public          postgres    false    244            �            1259    17986    foundanimals_foundanimals_seq    SEQUENCE     �   CREATE SEQUENCE public.foundanimals_foundanimals_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.foundanimals_foundanimals_seq;
       public          postgres    false    240            �           0    0    foundanimals_foundanimals_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.foundanimals_foundanimals_seq OWNED BY public.foundanimals.foundanimals;
          public          postgres    false    239            �            1259    17593    gender_genderid_seq    SEQUENCE     �   CREATE SEQUENCE public.gender_genderid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.gender_genderid_seq;
       public          postgres    false    216            �           0    0    gender_genderid_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.gender_genderid_seq OWNED BY public.gender.genderid;
          public          postgres    false    215            �            1259    17808    manager    TABLE     �   CREATE TABLE public.manager (
    managerid integer NOT NULL,
    userid integer,
    shelterid integer,
    managerialexperience integer NOT NULL
);
    DROP TABLE public.manager;
       public         heap    postgres    false            �            1259    17807    manager_managerid_seq    SEQUENCE     �   CREATE SEQUENCE public.manager_managerid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.manager_managerid_seq;
       public          postgres    false    230            �           0    0    manager_managerid_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.manager_managerid_seq OWNED BY public.manager.managerid;
          public          postgres    false    229            �            1259    17733    person    TABLE     �  CREATE TABLE public.person (
    personid integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    dateofbirth date,
    phone character varying(30) NOT NULL,
    email character varying(100) NOT NULL,
    city character varying(70),
    county character varying(70),
    "Password" character varying(50) NOT NULL,
    roleid integer,
    genderid integer,
    username character varying(50)
);
    DROP TABLE public.person;
       public         heap    postgres    false            �            1259    17732    person_personid_seq    SEQUENCE     �   CREATE SEQUENCE public.person_personid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.person_personid_seq;
       public          postgres    false    224            �           0    0    person_personid_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.person_personid_seq OWNED BY public.person.personid;
          public          postgres    false    223            �            1259    18010    requestanimal    TABLE     �   CREATE TABLE public.requestanimal (
    requestid integer NOT NULL,
    userid integer,
    animalid integer,
    shelterid integer,
    requestdate date DEFAULT CURRENT_DATE
);
 !   DROP TABLE public.requestanimal;
       public         heap    postgres    false            �            1259    18009    requestanimal_requestid_seq    SEQUENCE     �   CREATE SEQUENCE public.requestanimal_requestid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.requestanimal_requestid_seq;
       public          postgres    false    242            �           0    0    requestanimal_requestid_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.requestanimal_requestid_seq OWNED BY public.requestanimal.requestid;
          public          postgres    false    241            �            1259    17726    roles    TABLE     \   CREATE TABLE public.roles (
    roleid integer NOT NULL,
    roles character varying(50)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    17725    roles_roleid_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_roleid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.roles_roleid_seq;
       public          postgres    false    222            �           0    0    roles_roleid_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.roles_roleid_seq OWNED BY public.roles.roleid;
          public          postgres    false    221            �            1259    17791    shelter    TABLE     �  CREATE TABLE public.shelter (
    shelterid integer NOT NULL,
    sheltername character varying(70) NOT NULL,
    capacity integer,
    contactnumber character varying(30) NOT NULL,
    email character varying(100) NOT NULL,
    city character varying(70),
    county character varying(70),
    street character varying(70),
    "Number" integer,
    CONSTRAINT shelter_capacity_check CHECK ((capacity > 15))
);
    DROP TABLE public.shelter;
       public         heap    postgres    false            �            1259    17790    shelter_shelterid_seq    SEQUENCE     �   CREATE SEQUENCE public.shelter_shelterid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.shelter_shelterid_seq;
       public          postgres    false    228            �           0    0    shelter_shelterid_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.shelter_shelterid_seq OWNED BY public.shelter.shelterid;
          public          postgres    false    227            �            1259    26270    userfavoritedetails    VIEW     �  CREATE VIEW public.userfavoritedetails AS
 SELECT f.favoriteid,
    b.breedname AS breed,
    a.description,
    ant.animaltype AS animalcategory
   FROM ((((public.favorites f
     JOIN public.animal a ON ((f.animalid = a.animalid)))
     JOIN public.breed b ON ((a.breedid = b.breedid)))
     JOIN public.animaltype ant ON ((a.animaltypeid = ant.animaltypeid)))
     JOIN public."User" u ON ((f.userid = u.userid)));
 &   DROP VIEW public.userfavoritedetails;
       public          postgres    false    245    238    238    238    238    226    232    232    234    234    245    245            �            1259    18145    userrolebyanimalidview    VIEW     �   CREATE VIEW public.userrolebyanimalidview AS
 SELECT u.roleid,
    fa.animalid
   FROM (public."User" u
     JOIN public.foundanimals fa ON ((u.userid = fa.userid)));
 )   DROP VIEW public.userrolebyanimalidview;
       public          postgres    false    240    240    226    226            �           2604    17629 	   Age ageid    DEFAULT     j   ALTER TABLE ONLY public."Age" ALTER COLUMN ageid SET DEFAULT nextval('public."Age_ageid_seq"'::regclass);
 :   ALTER TABLE public."Age" ALTER COLUMN ageid DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    17613    Size sizeid    DEFAULT     n   ALTER TABLE ONLY public."Size" ALTER COLUMN sizeid SET DEFAULT nextval('public."Size_sizeid_seq"'::regclass);
 <   ALTER TABLE public."Size" ALTER COLUMN sizeid DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    17759    User userid    DEFAULT     n   ALTER TABLE ONLY public."User" ALTER COLUMN userid SET DEFAULT nextval('public."User_userid_seq"'::regclass);
 <   ALTER TABLE public."User" ALTER COLUMN userid DROP DEFAULT;
       public          postgres    false    225    226    226            �           2604    17903    animal animalid    DEFAULT     r   ALTER TABLE ONLY public.animal ALTER COLUMN animalid SET DEFAULT nextval('public.animal_animalid_seq'::regclass);
 >   ALTER TABLE public.animal ALTER COLUMN animalid DROP DEFAULT;
       public          postgres    false    238    237    238            �           2604    17873    animaltype animaltypeid    DEFAULT     �   ALTER TABLE ONLY public.animaltype ALTER COLUMN animaltypeid SET DEFAULT nextval('public.animaltype_animaltypeid_seq'::regclass);
 F   ALTER TABLE public.animaltype ALTER COLUMN animaltypeid DROP DEFAULT;
       public          postgres    false    233    234    234            �           2604    17864    breed breedid    DEFAULT     n   ALTER TABLE ONLY public.breed ALTER COLUMN breedid SET DEFAULT nextval('public.breed_breedid_seq'::regclass);
 <   ALTER TABLE public.breed ALTER COLUMN breedid DROP DEFAULT;
       public          postgres    false    231    232    232            �           2604    17894    color colorid    DEFAULT     n   ALTER TABLE ONLY public.color ALTER COLUMN colorid SET DEFAULT nextval('public.color_colorid_seq'::regclass);
 <   ALTER TABLE public.color ALTER COLUMN colorid DROP DEFAULT;
       public          postgres    false    236    235    236            �           2604    18123    favorites favoriteid    DEFAULT     |   ALTER TABLE ONLY public.favorites ALTER COLUMN favoriteid SET DEFAULT nextval('public.favorites_favoriteid_seq'::regclass);
 C   ALTER TABLE public.favorites ALTER COLUMN favoriteid DROP DEFAULT;
       public          postgres    false    245    244    245            �           2604    17990    foundanimals foundanimals    DEFAULT     �   ALTER TABLE ONLY public.foundanimals ALTER COLUMN foundanimals SET DEFAULT nextval('public.foundanimals_foundanimals_seq'::regclass);
 H   ALTER TABLE public.foundanimals ALTER COLUMN foundanimals DROP DEFAULT;
       public          postgres    false    239    240    240            �           2604    17597    gender genderid    DEFAULT     r   ALTER TABLE ONLY public.gender ALTER COLUMN genderid SET DEFAULT nextval('public.gender_genderid_seq'::regclass);
 >   ALTER TABLE public.gender ALTER COLUMN genderid DROP DEFAULT;
       public          postgres    false    215    216    216            �           2604    17811    manager managerid    DEFAULT     v   ALTER TABLE ONLY public.manager ALTER COLUMN managerid SET DEFAULT nextval('public.manager_managerid_seq'::regclass);
 @   ALTER TABLE public.manager ALTER COLUMN managerid DROP DEFAULT;
       public          postgres    false    230    229    230            �           2604    17736    person personid    DEFAULT     r   ALTER TABLE ONLY public.person ALTER COLUMN personid SET DEFAULT nextval('public.person_personid_seq'::regclass);
 >   ALTER TABLE public.person ALTER COLUMN personid DROP DEFAULT;
       public          postgres    false    223    224    224            �           2604    18013    requestanimal requestid    DEFAULT     �   ALTER TABLE ONLY public.requestanimal ALTER COLUMN requestid SET DEFAULT nextval('public.requestanimal_requestid_seq'::regclass);
 F   ALTER TABLE public.requestanimal ALTER COLUMN requestid DROP DEFAULT;
       public          postgres    false    242    241    242            �           2604    17729    roles roleid    DEFAULT     l   ALTER TABLE ONLY public.roles ALTER COLUMN roleid SET DEFAULT nextval('public.roles_roleid_seq'::regclass);
 ;   ALTER TABLE public.roles ALTER COLUMN roleid DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    17794    shelter shelterid    DEFAULT     v   ALTER TABLE ONLY public.shelter ALTER COLUMN shelterid SET DEFAULT nextval('public.shelter_shelterid_seq'::regclass);
 @   ALTER TABLE public.shelter ALTER COLUMN shelterid DROP DEFAULT;
       public          postgres    false    227    228    228            �          0    17626    Age 
   TABLE DATA           1   COPY public."Age" (ageid, lifestage) FROM stdin;
    public          postgres    false    220   Ƶ       �          0    17610    Size 
   TABLE DATA           0   COPY public."Size" (sizeid, "Size") FROM stdin;
    public          postgres    false    218   �       �          0    17756    User 
   TABLE DATA           P   COPY public."User" (userid, username, "Password", personid, roleid) FROM stdin;
    public          postgres    false    226   C�       �          0    17900    animal 
   TABLE DATA           �   COPY public.animal (animalid, animalname, animaltypeid, genderid, sizeid, ageid, breedid, colorid, description, shelterid, photourl) FROM stdin;
    public          postgres    false    238   K�       �          0    17870 
   animaltype 
   TABLE DATA           >   COPY public.animaltype (animaltypeid, animaltype) FROM stdin;
    public          postgres    false    234   ��       �          0    17861    breed 
   TABLE DATA           N   COPY public.breed (breedid, animaltypeid, breedname, description) FROM stdin;
    public          postgres    false    232   �       �          0    17891    color 
   TABLE DATA           /   COPY public.color (colorid, color) FROM stdin;
    public          postgres    false    236   ��       �          0    18120 	   favorites 
   TABLE DATA           L   COPY public.favorites (favoriteid, userid, animalid, shelterid) FROM stdin;
    public          postgres    false    245   ��       �          0    17987    foundanimals 
   TABLE DATA           k   COPY public.foundanimals (foundanimals, userid, animalid, founddate, city, county, street, nr) FROM stdin;
    public          postgres    false    240   ��       �          0    17594    gender 
   TABLE DATA           2   COPY public.gender (genderid, gender) FROM stdin;
    public          postgres    false    216   ��       �          0    17808    manager 
   TABLE DATA           U   COPY public.manager (managerid, userid, shelterid, managerialexperience) FROM stdin;
    public          postgres    false    230   ��       �          0    17733    person 
   TABLE DATA           �   COPY public.person (personid, first_name, last_name, dateofbirth, phone, email, city, county, "Password", roleid, genderid, username) FROM stdin;
    public          postgres    false    224   �       �          0    18010    requestanimal 
   TABLE DATA           \   COPY public.requestanimal (requestid, userid, animalid, shelterid, requestdate) FROM stdin;
    public          postgres    false    242   ��       �          0    17726    roles 
   TABLE DATA           .   COPY public.roles (roleid, roles) FROM stdin;
    public          postgres    false    222   ��       �          0    17791    shelter 
   TABLE DATA           y   COPY public.shelter (shelterid, sheltername, capacity, contactnumber, email, city, county, street, "Number") FROM stdin;
    public          postgres    false    228   �       �           0    0    Age_ageid_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public."Age_ageid_seq"', 4, true);
          public          postgres    false    219            �           0    0    Size_sizeid_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public."Size_sizeid_seq"', 4, true);
          public          postgres    false    217            �           0    0    User_userid_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."User_userid_seq"', 29, true);
          public          postgres    false    225            �           0    0    animal_animalid_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.animal_animalid_seq', 33, true);
          public          postgres    false    237            �           0    0    animaltype_animaltypeid_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.animaltype_animaltypeid_seq', 7, true);
          public          postgres    false    233            �           0    0    breed_breedid_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.breed_breedid_seq', 50, true);
          public          postgres    false    231            �           0    0    color_colorid_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.color_colorid_seq', 15, true);
          public          postgres    false    235            �           0    0    favorites_favoriteid_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.favorites_favoriteid_seq', 16, true);
          public          postgres    false    244            �           0    0    foundanimals_foundanimals_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.foundanimals_foundanimals_seq', 29, true);
          public          postgres    false    239            �           0    0    gender_genderid_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.gender_genderid_seq', 3, true);
          public          postgres    false    215            �           0    0    manager_managerid_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.manager_managerid_seq', 3, true);
          public          postgres    false    229            �           0    0    person_personid_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.person_personid_seq', 16, true);
          public          postgres    false    223            �           0    0    requestanimal_requestid_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.requestanimal_requestid_seq', 1, false);
          public          postgres    false    241            �           0    0    roles_roleid_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.roles_roleid_seq', 3, true);
          public          postgres    false    221            �           0    0    shelter_shelterid_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.shelter_shelterid_seq', 3, true);
          public          postgres    false    227            �           2606    17631    Age Age_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public."Age"
    ADD CONSTRAINT "Age_pkey" PRIMARY KEY (ageid);
 :   ALTER TABLE ONLY public."Age" DROP CONSTRAINT "Age_pkey";
       public            postgres    false    220            �           2606    17615    Size Size_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."Size"
    ADD CONSTRAINT "Size_pkey" PRIMARY KEY (sizeid);
 <   ALTER TABLE ONLY public."Size" DROP CONSTRAINT "Size_pkey";
       public            postgres    false    218            �           2606    17761    User User_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY (userid);
 <   ALTER TABLE ONLY public."User" DROP CONSTRAINT "User_pkey";
       public            postgres    false    226            �           2606    17763    User User_username_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_username_key" UNIQUE (username);
 D   ALTER TABLE ONLY public."User" DROP CONSTRAINT "User_username_key";
       public            postgres    false    226            �           2606    17907    animal animal_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (animalid);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    238            �           2606    17875    animaltype animaltype_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.animaltype
    ADD CONSTRAINT animaltype_pkey PRIMARY KEY (animaltypeid);
 D   ALTER TABLE ONLY public.animaltype DROP CONSTRAINT animaltype_pkey;
       public            postgres    false    234            �           2606    17868    breed breed_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.breed
    ADD CONSTRAINT breed_pkey PRIMARY KEY (breedid);
 :   ALTER TABLE ONLY public.breed DROP CONSTRAINT breed_pkey;
       public            postgres    false    232            �           2606    17896    color color_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.color
    ADD CONSTRAINT color_pkey PRIMARY KEY (colorid);
 :   ALTER TABLE ONLY public.color DROP CONSTRAINT color_pkey;
       public            postgres    false    236            �           2606    18125    favorites favorites_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_pkey PRIMARY KEY (favoriteid);
 B   ALTER TABLE ONLY public.favorites DROP CONSTRAINT favorites_pkey;
       public            postgres    false    245            �           2606    17993    foundanimals foundanimals_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.foundanimals
    ADD CONSTRAINT foundanimals_pkey PRIMARY KEY (foundanimals);
 H   ALTER TABLE ONLY public.foundanimals DROP CONSTRAINT foundanimals_pkey;
       public            postgres    false    240            �           2606    17599    gender gender_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.gender
    ADD CONSTRAINT gender_pkey PRIMARY KEY (genderid);
 <   ALTER TABLE ONLY public.gender DROP CONSTRAINT gender_pkey;
       public            postgres    false    216            �           2606    17813    manager manager_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.manager
    ADD CONSTRAINT manager_pkey PRIMARY KEY (managerid);
 >   ALTER TABLE ONLY public.manager DROP CONSTRAINT manager_pkey;
       public            postgres    false    230            �           2606    17819    manager manager_shelterid_key 
   CONSTRAINT     ]   ALTER TABLE ONLY public.manager
    ADD CONSTRAINT manager_shelterid_key UNIQUE (shelterid);
 G   ALTER TABLE ONLY public.manager DROP CONSTRAINT manager_shelterid_key;
       public            postgres    false    230            �           2606    17817    manager manager_userid_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.manager
    ADD CONSTRAINT manager_userid_key UNIQUE (userid);
 D   ALTER TABLE ONLY public.manager DROP CONSTRAINT manager_userid_key;
       public            postgres    false    230            �           2606    17744    person person_email_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_email_key UNIQUE (email);
 A   ALTER TABLE ONLY public.person DROP CONSTRAINT person_email_key;
       public            postgres    false    224            �           2606    17742    person person_phone_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_phone_key UNIQUE (phone);
 A   ALTER TABLE ONLY public.person DROP CONSTRAINT person_phone_key;
       public            postgres    false    224            �           2606    17738    person person_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (personid);
 <   ALTER TABLE ONLY public.person DROP CONSTRAINT person_pkey;
       public            postgres    false    224            �           2606    18076    person person_username_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_username_key UNIQUE (username);
 D   ALTER TABLE ONLY public.person DROP CONSTRAINT person_username_key;
       public            postgres    false    224            �           2606    18016     requestanimal requestanimal_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public.requestanimal
    ADD CONSTRAINT requestanimal_pkey PRIMARY KEY (requestid);
 J   ALTER TABLE ONLY public.requestanimal DROP CONSTRAINT requestanimal_pkey;
       public            postgres    false    242            �           2606    17731    roles roles_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (roleid);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    222            �           2606    17799 !   shelter shelter_contactnumber_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.shelter
    ADD CONSTRAINT shelter_contactnumber_key UNIQUE (contactnumber);
 K   ALTER TABLE ONLY public.shelter DROP CONSTRAINT shelter_contactnumber_key;
       public            postgres    false    228            �           2606    17801    shelter shelter_email_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.shelter
    ADD CONSTRAINT shelter_email_key UNIQUE (email);
 C   ALTER TABLE ONLY public.shelter DROP CONSTRAINT shelter_email_key;
       public            postgres    false    228            �           2606    17797    shelter shelter_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.shelter
    ADD CONSTRAINT shelter_pkey PRIMARY KEY (shelterid);
 >   ALTER TABLE ONLY public.shelter DROP CONSTRAINT shelter_pkey;
       public            postgres    false    228            �           2606    18064    User user_un 
   CONSTRAINT     M   ALTER TABLE ONLY public."User"
    ADD CONSTRAINT user_un UNIQUE (personid);
 8   ALTER TABLE ONLY public."User" DROP CONSTRAINT user_un;
       public            postgres    false    226            �           2606    17923    animal animal_ageid_fkey    FK CONSTRAINT     x   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_ageid_fkey FOREIGN KEY (ageid) REFERENCES public."Age"(ageid);
 B   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_ageid_fkey;
       public          postgres    false    220    4806    238            �           2606    17933    animal animal_animaltypeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_animaltypeid_fkey FOREIGN KEY (animaltypeid) REFERENCES public.animaltype(animaltypeid);
 I   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_animaltypeid_fkey;
       public          postgres    false    4838    234    238            �           2606    17908    animal animal_breedid_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_breedid_fkey FOREIGN KEY (breedid) REFERENCES public.breed(breedid);
 D   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_breedid_fkey;
       public          postgres    false    238    232    4836            �           2606    17913    animal animal_colorid_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_colorid_fkey FOREIGN KEY (colorid) REFERENCES public.color(colorid);
 D   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_colorid_fkey;
       public          postgres    false    238    236    4840            �           2606    17928    animal animal_genderid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_genderid_fkey FOREIGN KEY (genderid) REFERENCES public.gender(genderid);
 E   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_genderid_fkey;
       public          postgres    false    4802    216    238            �           2606    18033    animal animal_shelterid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_shelterid_fkey FOREIGN KEY (shelterid) REFERENCES public.shelter(shelterid);
 F   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_shelterid_fkey;
       public          postgres    false    4828    238    228            �           2606    17918    animal animal_sizeid_fkey    FK CONSTRAINT     |   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_sizeid_fkey FOREIGN KEY (sizeid) REFERENCES public."Size"(sizeid);
 C   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_sizeid_fkey;
       public          postgres    false    218    4804    238            �           2606    17938    breed breed_animaltypeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.breed
    ADD CONSTRAINT breed_animaltypeid_fkey FOREIGN KEY (animaltypeid) REFERENCES public.animaltype(animaltypeid);
 G   ALTER TABLE ONLY public.breed DROP CONSTRAINT breed_animaltypeid_fkey;
       public          postgres    false    4838    234    232                       2606    18131 !   favorites favorites_animalid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_animalid_fkey FOREIGN KEY (animalid) REFERENCES public.animal(animalid);
 K   ALTER TABLE ONLY public.favorites DROP CONSTRAINT favorites_animalid_fkey;
       public          postgres    false    238    4842    245                       2606    18136 "   favorites favorites_shelterid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_shelterid_fkey FOREIGN KEY (shelterid) REFERENCES public.shelter(shelterid);
 L   ALTER TABLE ONLY public.favorites DROP CONSTRAINT favorites_shelterid_fkey;
       public          postgres    false    4828    228    245                       2606    18126    favorites favorites_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.favorites
    ADD CONSTRAINT favorites_userid_fkey FOREIGN KEY (userid) REFERENCES public."User"(userid);
 I   ALTER TABLE ONLY public.favorites DROP CONSTRAINT favorites_userid_fkey;
       public          postgres    false    245    226    4818            �           2606    17999 '   foundanimals foundanimals_animalid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.foundanimals
    ADD CONSTRAINT foundanimals_animalid_fkey FOREIGN KEY (animalid) REFERENCES public.animal(animalid);
 Q   ALTER TABLE ONLY public.foundanimals DROP CONSTRAINT foundanimals_animalid_fkey;
       public          postgres    false    4842    238    240                        2606    17994 %   foundanimals foundanimals_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.foundanimals
    ADD CONSTRAINT foundanimals_userid_fkey FOREIGN KEY (userid) REFERENCES public."User"(userid);
 O   ALTER TABLE ONLY public.foundanimals DROP CONSTRAINT foundanimals_userid_fkey;
       public          postgres    false    240    4818    226            �           2606    17830    manager manager_shelterid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.manager
    ADD CONSTRAINT manager_shelterid_fkey FOREIGN KEY (shelterid) REFERENCES public.shelter(shelterid);
 H   ALTER TABLE ONLY public.manager DROP CONSTRAINT manager_shelterid_fkey;
       public          postgres    false    228    230    4828            �           2606    17825    manager manager_userid_fkey    FK CONSTRAINT     ~   ALTER TABLE ONLY public.manager
    ADD CONSTRAINT manager_userid_fkey FOREIGN KEY (userid) REFERENCES public."User"(userid);
 E   ALTER TABLE ONLY public.manager DROP CONSTRAINT manager_userid_fkey;
       public          postgres    false    230    226    4818            �           2606    18051    person person_fk    FK CONSTRAINT     r   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_fk FOREIGN KEY (roleid) REFERENCES public.roles(roleid);
 :   ALTER TABLE ONLY public.person DROP CONSTRAINT person_fk;
       public          postgres    false    222    4808    224            �           2606    18056    person person_genderid_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_genderid_fk FOREIGN KEY (genderid) REFERENCES public.gender(genderid);
 C   ALTER TABLE ONLY public.person DROP CONSTRAINT person_genderid_fk;
       public          postgres    false    4802    216    224                       2606    18022 )   requestanimal requestanimal_animalid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.requestanimal
    ADD CONSTRAINT requestanimal_animalid_fkey FOREIGN KEY (animalid) REFERENCES public.animal(animalid);
 S   ALTER TABLE ONLY public.requestanimal DROP CONSTRAINT requestanimal_animalid_fkey;
       public          postgres    false    242    238    4842                       2606    18027 *   requestanimal requestanimal_shelterid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.requestanimal
    ADD CONSTRAINT requestanimal_shelterid_fkey FOREIGN KEY (shelterid) REFERENCES public.shelter(shelterid);
 T   ALTER TABLE ONLY public.requestanimal DROP CONSTRAINT requestanimal_shelterid_fkey;
       public          postgres    false    228    4828    242                       2606    18017 '   requestanimal requestanimal_userid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.requestanimal
    ADD CONSTRAINT requestanimal_userid_fkey FOREIGN KEY (userid) REFERENCES public."User"(userid);
 Q   ALTER TABLE ONLY public.requestanimal DROP CONSTRAINT requestanimal_userid_fkey;
       public          postgres    false    4818    226    242            �           2606    18065    User user_fk    FK CONSTRAINT     u   ALTER TABLE ONLY public."User"
    ADD CONSTRAINT user_fk FOREIGN KEY (personid) REFERENCES public.person(personid);
 8   ALTER TABLE ONLY public."User" DROP CONSTRAINT user_fk;
       public          postgres    false    224    4814    226            �           2606    18070    User user_roleid_fk    FK CONSTRAINT     w   ALTER TABLE ONLY public."User"
    ADD CONSTRAINT user_roleid_fk FOREIGN KEY (roleid) REFERENCES public.roles(roleid);
 ?   ALTER TABLE ONLY public."User" DROP CONSTRAINT user_roleid_fk;
       public          postgres    false    222    226    4808            �   .   x�3�tJL��2��/�K�2�tL)�)�2�t�II-ʩ����� �
      �   /   x�3��M���2��MM�,��2��I,JO�2�t�()JT��b���� :      �   �   x�5��n� Eח���_K7�dS)J����bT#�0D��w���н�3#\���l|egV���R�wrYӸL�UA�x�Dc��YmA�ǩ�������?im��W
��1�L�W�C%��8Q�k��L�%�]t�B���;g��K��wr�OE�b(���/���qd�� %�Tr�VM�&��d�cr�������4v8]oY�T->����?�����=�����A���ڙ��*������n      �   .  x���Ao�@��ï���b��ɋ��I�6^�:��e��K���wah�^�!��޼}K��צ&�¤��@�3�>�t���0�Rh'�A��WE���$}�d-2g�T��#���)/�xS�.�kwƘl,�Uz�]�c���6t8�������Z7�V���R;���ms2������	�эX�%ZL�J�Xg,�Xdl���6�a���n�A۝BLa��&'�(7�股WJ�[�[��6}�n�}�.�0ꈚ#�L�5��W!�>�݅����-���~����'Q}S�L      �   N   x�3�t�O�2�tN,�2�t�,J�2��M��QH�KQH+-*�T(H-�2�JLJ�,�2���/*N�2�t�,������� ��9      �   �  x��Z˒�6]K_��*��TVw����8�g�c�&5U��H����L���	J�N���E�I�>�=����~��
]���+U�*~�±���L�+��e��*�ϲ6�"K���e-�y�������XO�rø.�V�����V��%��j�̱���	�NR������X�O
���;���!Z�զW��n�~!�:.5=S�F��4-��h������-��Jؘ+ܾ	[sV�V��1��9<a�S��
n]��׸���I!�V֘�h`���V����ƲϢ�R��]�R�N*��B�j`cO$�M7&��x'��N�\�Q��� O���=�(A�Rh_��vq���`�!�fәxo�GQtЛ��E*(a���s������;�O�(s&JX�k������8�}����0X�
Aa{W�����D��}�E�����P�$��l��H�3T�
��䎵F�B0���%/�x�>Ȫ�
-li!J��\��'�@���o|���+�K�zH��	?ɖ��kw����!�'cJ%V_n�`����C�a�1�	�u!F��7a颥�1>�),|��@e��7�뼝^`��15?�KTϯ�3nBKx����CIrQ�1�JN�It��T>`H$�bm�N���v���d����/�ފ��%�l��"�2c�1�țr����]r����,��/;B�`�Wd̒u�i!}H�Wd�+8�9�CL���>��ʴ��x��r׾�t'"���; v����/jW��\�)y���Fߑ�@#����2يm��?Lʑh�ʎ���i~���cBR���2m�v��3
@� ����j����\�B��
F�3�7B+	Ɣ��`��ٱ����2H�`�*,^�����GD����Ck�R$���!�����b���ybO����5���_}5�|'d�p�C  7Ѭ��(�MN��D�<Т�� ���~� �>CY�@ �"m���x�<��d�CBd���1JQ�c_k1���thrXNP��}2a�&f}����M���H7G����b� -�H����tAӫ:Ȑܪ��]n]�[�%*�j�P���
��@k ��j�gS�&x�'��/N�k	��ao�3�I�U%	��Qv(ĦY���P�7���0��˰(�1 �#�Ѹu�R
b��}��ؑ�>^��m`�����ɫx	J��T[qD��*z?�@�y����\�W��H���RD�$'���o����#��cԵ@u�G��A�28H8R!�&�PZ� �(3E¨MJ�E�b7��%qn�ՙ����Q=��6�ל|�TZ�_B�ϼ*�R�s9_�L!�Y0[��#H��@.�JlӲK_� =0P�f���s	���p�u�r�fP�M��E_Qʢ��o�cJ�<�E���]��y((��myV�j��:���l�v{#$|�Z�U;��.�����7,7CD���g�?�UFӳ^`b&P�5�[ 4%*�{����'����dSeJMo���ϣ)Ȩ���灏�k��:�%�ÿB��j?֒D��ȓ,9��{��$M�f�5�.���S9�1�@��2%shbKS>��C��`�g��,ؾgy�����;G	~5;	�c�E�O K��nO�c���R�b����T���N
u!�{����L��s�)�u��׋��2��\7�^�Y�l�6���T��ZTT����}�<a���;e^J���'�*�jI&���(�Vp�dO&=�D� �<�q^/F}�;Q+j����(��D�ݯ�ю������?ҵ?��g��R��#���^<K�ʾ7E�M!DA;E��������X�/�w�ԉ�`[s���P����$�5e�1�?@��Mq�5������;�:�:��O$yU-h�D�f�| >D�7�{w�9�uAx�2�� ����u�d�l�dgB�q����d���ʩ�Z|�I�g�D/<�Į�d��D]�/:��>v�q�1��X F�3�ym��\K�Z��K��[,�h��0#�A�k�0��BÀ�9%̜L�rĩ���Nt�P�W���W�=��&	��i�����$|EI�k��1��WA�4Uʔ��џ2u� �<���SID٭�j`6�(e�<�{<t�H	�2,��,}�F�3���'ƌA�P���$���I���R�3	/hg_Z�B�?)��y�J���u�?w��q@�m}�7���S�k�ڮ#Ҵ�l��Z���g�兏l��G�3�wD>x,_�B�=4�������L���{�5� �A��SJ���h���\	��	K2����,����Y����Qb� B���2��<�hCz��JS�Q��C�H�ʇ�:�!�������"�d��*d�eظ�_�X���e��@����m�p�9� �`��L��7�Ԙ�nW"sn���.l�F�.�)0a���ͬ�>t�D������������|9��:� 7mo+�iFl�烥�X0�SF��r���-jN�Ct|g'K��zh?��$ʃ���QȚ�ֵ���~*�NP��s��Ϟ[:�
��}{�����|L�p� bܐ�k?��D}��֔�0uWͅ'.E�2]�}����I�l�}�b�r�Vtg����#.�s0��y~ n�V.A��<�<�ƅ�f��ô^�6t����tP.��|�\�I�1���U��|�n63��`:��{O Ih<�� �(���A�>^\gLcUϦ&��$���l^��HAg�S���'N��
�����X�|*�a�;�&�$u��I�����lB�W�k߶��_���&n�#�:�,N-Z�ؙ��x�I� Q�^�Fm8�'�5P%�tN �F8�4Wa�����`�G�y�z��`��?~4m~�)�" �A.���h|<��e5$�D^w���s�]�Lw�'8t�(��y��+z7;�[..�`�ߤ�X��x1��S�&c�9X�Y�t�YA�i4X���'�C>�I��Dr"C5��U�;���Qt��6�-��Ď�0�{%��_iD=���=
*��F���� ����@��ƙ7n����P��sE��l�����)�Bav8��p�J��G�����kܭVo�?s�IVK��y2���'��ٜu���	ecLWO�ex|����\��n��h��ə��R���$l�sK,���yZ�_~ �S�^�\��xn�[X�ۻ�|5���N |H�I|��g�W'���r]�`z��g��R�>@�Ґ�
pH�~�7�J�a
'X�d�-E#��S� 8��F���q�Ǭ��82���Ү���8U��m��H��g�I��:
Up�����c��������3���f�d��a�!������y��;/\����rȌ@k�����`�ܘ��pfB���eh��[0�/�	��|iÅ�����n~e?�JuR��y�����tdO����������`;�����y�,MO�-ԋ�� �� M�?l�^�x��#���v�b�ը���h�d�@>�����8�Jo�,����f�ٛDH���<[�&)SbF��.?�^?��!��R(J3:���?����(�>p�kP�7������O[AH����85�DP���S�tz=dL�E��e�ؒ�_�:��{'�����Tt�`+����#
N~b�2⎵�'n�Q��N~6���al��>��S�n_p|8Ĥ?�j���z��Nn�?      �   �   x�5�MKA��ɯ�S�Pl�j�]�z)[�K�;4f4�v�of�S�wxy扰��t�M���'�*�gP	N8�ʒ�¸�;���1���;Xg�Y��F���쒜���Ʉڤ��{7e2�8��/\{�෇�o�mq��K�)��������"3
��(��15;�>�1}a\����_���b���u�﷈xßM0      �   I   x�E���0��QL�`�&R����"�7Z�A:�>�$5������P����0�VeOX?#�}]"����      �   �   x���1�0���_"�K�бu���� W���,�z�:�!��;-�B��j]��z	W��ÑG@B�`drwbz/v�O��n�0�@m���'��2oVo���IW���MU�m*o~��a�~����������ɍ�?��^����QJ�Hf�      �   "   x�3��M�I�2�tK�1�9�K2R��b���� r�C      �   '   x�3�42�4�4�2�42�B.cN##NcN�=... I>7      �   �  x��T�n�0</�E)��t��	
�(✊^�2a3�EA���w�p,$}�Aɣ��<�~0�"|3�e�zlg(8/3�3Q_)��j$qo��u��'��֝���}13<��% A@�͍���vn��ap�M�W��I�BpQ
.�7���"4�Ev"gU�К\��]Lk�P�q�;_ՍR�X����	vw<���I� �ȯ�L���v4w�.�Z�/�U���u1��B�y�#�5!�V�b���la���L4M��^աQ�,�JRIf��	���g<�#���.F��_w'm'=�<�7�f0'�g��UFE?��V���<.oo�MZz{=`7aP\�w�4�]z�j����3���>�<��eU�J��#�G,��I_�N����utE������q�����ɠPղ)|��ﯨ���	�yJ&8ebr�q�_?��Hd�!)$�T� �1�F���F��m��A'��L� ���آ5�6�OM	��S�1�4G7��<�5��g�)�жK�h�A\���Z����E�6v~ɾR�i@�>��@�D���t�L����Ȧ��lx75�,pJbd%u�t��D���%]� UU��I0�1���D�ǈH�H�@7[F١�������á($*�pkZ��i�8�!��6$kA�3{
]\o�D����c��m�Z      �      x������ � �      �   $   x�3�-N-�2��H�)��9Sr3�b���� ���      �   �   x�U���0D�ۯ�$-E)7�$�W/+4���M[L��-�^f7;�7��أ5>L:i�t8F)�"�,9�Йx�B�[ү��8�鱻F�ÿ���"�{ie� d�dP������A�T�|_�� �l�ū�޹��Л�6���/�Eݥ�����/�眕��O���ZdeD�[i���n)!��OV�     