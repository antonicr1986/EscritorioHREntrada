PGDMP     %                
    {         	   HREntrada    14.0    14.0                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            	           1262    28540 	   HREntrada    DATABASE     g   CREATE DATABASE "HREntrada" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE "HREntrada";
                admin    false            �            1259    29129 	   empleados    TABLE     �   CREATE TABLE public.empleados (
    dni text NOT NULL,
    nom text NOT NULL,
    apellido text NOT NULL,
    nomempresa text NOT NULL,
    departament text NOT NULL,
    codicard integer NOT NULL,
    mail text NOT NULL,
    telephon integer NOT NULL
);
    DROP TABLE public.empleados;
       public         heap    postgres    false            �            1259    29122    empresa    TABLE     n   CREATE TABLE public.empresa (
    nom text NOT NULL,
    address text NOT NULL,
    telephon text NOT NULL
);
    DROP TABLE public.empresa;
       public         heap    postgres    false            �            1259    29176    jornada    TABLE     �   CREATE TABLE public.jornada (
    dni text NOT NULL,
    nom text NOT NULL,
    apellido text NOT NULL,
    codicard integer NOT NULL,
    horaentrada text NOT NULL,
    horasalida text,
    total text,
    fecha text NOT NULL
);
    DROP TABLE public.jornada;
       public         heap    postgres    false            �            1259    29143    users    TABLE     �   CREATE TABLE public.users (
    login text NOT NULL,
    pass text NOT NULL,
    numtipe integer NOT NULL,
    dni text NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false                      0    29129 	   empleados 
   TABLE DATA           j   COPY public.empleados (dni, nom, apellido, nomempresa, departament, codicard, mail, telephon) FROM stdin;
    public          postgres    false    210   d                  0    29122    empresa 
   TABLE DATA           9   COPY public.empresa (nom, address, telephon) FROM stdin;
    public          postgres    false    209   �                 0    29176    jornada 
   TABLE DATA           f   COPY public.jornada (dni, nom, apellido, codicard, horaentrada, horasalida, total, fecha) FROM stdin;
    public          postgres    false    212                    0    29143    users 
   TABLE DATA           :   COPY public.users (login, pass, numtipe, dni) FROM stdin;
    public          postgres    false    211   ,       j           2606    29137     empleados empleados_codicard_key 
   CONSTRAINT     _   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_codicard_key UNIQUE (codicard);
 J   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleados_codicard_key;
       public            postgres    false    210            l           2606    29135    empleados empleados_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_pkey PRIMARY KEY (dni);
 B   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleados_pkey;
       public            postgres    false    210            h           2606    29128    empresa empresa_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pkey PRIMARY KEY (nom);
 >   ALTER TABLE ONLY public.empresa DROP CONSTRAINT empresa_pkey;
       public            postgres    false    209            n           2606    29151    users users_dni_key 
   CONSTRAINT     M   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_dni_key UNIQUE (dni);
 =   ALTER TABLE ONLY public.users DROP CONSTRAINT users_dni_key;
       public            postgres    false    211            p           2606    29149    users users_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (login);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    211            q           2606    29138 #   empleados empleados_nomempresa_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.empleados
    ADD CONSTRAINT empleados_nomempresa_fkey FOREIGN KEY (nomempresa) REFERENCES public.empresa(nom) ON UPDATE CASCADE ON DELETE CASCADE;
 M   ALTER TABLE ONLY public.empleados DROP CONSTRAINT empleados_nomempresa_fkey;
       public          postgres    false    210    209    3176            t           2606    29186    jornada jornada_codicard_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.jornada
    ADD CONSTRAINT jornada_codicard_fkey FOREIGN KEY (codicard) REFERENCES public.empleados(codicard) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.jornada DROP CONSTRAINT jornada_codicard_fkey;
       public          postgres    false    3178    210    212            s           2606    29181    jornada jornada_dni_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.jornada
    ADD CONSTRAINT jornada_dni_fkey FOREIGN KEY (dni) REFERENCES public.empleados(dni) ON UPDATE CASCADE ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.jornada DROP CONSTRAINT jornada_dni_fkey;
       public          postgres    false    210    3180    212            r           2606    29152    users users_dni_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_dni_fkey FOREIGN KEY (dni) REFERENCES public.empleados(dni) ON UPDATE CASCADE ON DELETE CASCADE;
 >   ALTER TABLE ONLY public.users DROP CONSTRAINT users_dni_fkey;
       public          postgres    false    210    211    3180               c   x�3426153�p�tL����,.)JL�/��r�1�X�yi�E��%�ɉ��(�����s3s���s9-�!F[rB,�t�-N-*&h�XCc���� i;�          (   x��r�+)JLI�tJ,JN���K�4664!�=... ��	p            x������ � �         .   x�KL����L���F�&�f��\�ũE�`�&j������ s^�     