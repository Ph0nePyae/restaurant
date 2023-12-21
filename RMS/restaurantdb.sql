PGDMP     )    !                {         
   restaurant    10.23    10.23 -    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            )           1262    16476 
   restaurant    DATABASE     �   CREATE DATABASE restaurant WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE restaurant;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            *           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3                        3079    12924    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            +           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1255    16845 #   bigint_exclude_diff(bigint, bigint)    FUNCTION     �   CREATE FUNCTION public.bigint_exclude_diff(bigint, bigint) RETURNS boolean
    LANGUAGE sql IMMUTABLE STRICT
    AS $_$
    SELECT $1 <> $2;
$_$;
 :   DROP FUNCTION public.bigint_exclude_diff(bigint, bigint);
       public       postgres    false    3            y           2753    16836 
   bigint_ops    OPERATOR FAMILY     5   CREATE OPERATOR FAMILY public.bigint_ops USING gist;
 3   DROP OPERATOR FAMILY public.bigint_ops USING gist;
       public       postgres    false    3                       2616    16837 
   bigint_ops    OPERATOR CLASS     �  CREATE OPERATOR CLASS public.bigint_ops
    DEFAULT FOR TYPE bigint USING gist FAMILY public.bigint_ops AS
    OPERATOR 1 <(bigint,bigint) ,
    OPERATOR 2 <=(bigint,bigint) ,
    OPERATOR 3 =(bigint,bigint) ,
    OPERATOR 4 >=(bigint,bigint) ,
    OPERATOR 5 >(bigint,bigint) ,
    FUNCTION 1 (bigint, bigint) btint4cmp(integer,integer) ,
    FUNCTION 2 (bigint, bigint) btint4sortsupport(internal);
 2   DROP OPERATOR CLASS public.bigint_ops USING gist;
       public       postgres    false    3    1657            �            1259    16506    drinks    TABLE     �   CREATE TABLE public.drinks (
    drinks_id bigint NOT NULL,
    drinks character varying NOT NULL,
    d_price bigint NOT NULL
);
    DROP TABLE public.drinks;
       public         postgres    false    3            �            1259    16504    drinks_drinks_id_seq    SEQUENCE     �   ALTER TABLE public.drinks ALTER COLUMN drinks_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.drinks_drinks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    3    201            �            1259    16485    items    TABLE     �   CREATE TABLE public.items (
    items_id bigint NOT NULL,
    food character varying(255) NOT NULL,
    price bigint NOT NULL,
    drinks_id bigint
);
    DROP TABLE public.items;
       public         postgres    false    3            �            1259    16490    items_items_id_seq    SEQUENCE     �   ALTER TABLE public.items ALTER COLUMN items_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.items_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    197    3            �            1259    16494    orders    TABLE     �   CREATE TABLE public.orders (
    order_id bigint NOT NULL,
    table_no bigint NOT NULL,
    items_id bigint,
    drinks_id bigint,
    qty bigint,
    d_qty bigint,
    checkout boolean
);
    DROP TABLE public.orders;
       public         postgres    false    3            �            1259    16795    orders_order_id_seq    SEQUENCE     �   ALTER TABLE public.orders ALTER COLUMN order_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.orders_order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    3    199            �            1259    16477 
   table_list    TABLE     l   CREATE TABLE public.table_list (
    table_no bigint NOT NULL,
    table_name character varying NOT NULL
);
    DROP TABLE public.table_list;
       public         postgres    false    3            �            1259    16516    table_no    TABLE     �   CREATE TABLE public.table_no (
    table_id bigint NOT NULL,
    items character varying(255) NOT NULL,
    price bigint NOT NULL,
    qty bigint NOT NULL
);
    DROP TABLE public.table_no;
       public         postgres    false    3            �            1259    16514    table_table_id_seq    SEQUENCE     �   ALTER TABLE public.table_no ALTER COLUMN table_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.table_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public       postgres    false    3    203                       0    16506    drinks 
   TABLE DATA               <   COPY public.drinks (drinks_id, drinks, d_price) FROM stdin;
    public       postgres    false    201   �1                 0    16485    items 
   TABLE DATA               A   COPY public.items (items_id, food, price, drinks_id) FROM stdin;
    public       postgres    false    197   �1                 0    16494    orders 
   TABLE DATA               _   COPY public.orders (order_id, table_no, items_id, drinks_id, qty, d_qty, checkout) FROM stdin;
    public       postgres    false    199   y2                 0    16477 
   table_list 
   TABLE DATA               :   COPY public.table_list (table_no, table_name) FROM stdin;
    public       postgres    false    196   �2       "          0    16516    table_no 
   TABLE DATA               ?   COPY public.table_no (table_id, items, price, qty) FROM stdin;
    public       postgres    false    203   �2       ,           0    0    drinks_drinks_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.drinks_drinks_id_seq', 4, true);
            public       postgres    false    200            -           0    0    items_items_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.items_items_id_seq', 9, true);
            public       postgres    false    198            .           0    0    orders_order_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.orders_order_id_seq', 189, true);
            public       postgres    false    204            /           0    0    table_table_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.table_table_id_seq', 19, true);
            public       postgres    false    202            �
           2606    16513    drinks drinks_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.drinks
    ADD CONSTRAINT drinks_pkey PRIMARY KEY (drinks_id);
 <   ALTER TABLE ONLY public.drinks DROP CONSTRAINT drinks_pkey;
       public         postgres    false    201            �
           2606    16489    items items_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (items_id);
 :   ALTER TABLE ONLY public.items DROP CONSTRAINT items_pkey;
       public         postgres    false    197            �
           2606    16498    orders table_1_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT table_1_pkey PRIMARY KEY (order_id);
 =   ALTER TABLE ONLY public.orders DROP CONSTRAINT table_1_pkey;
       public         postgres    false    199            �
           2606    16520    table_no table_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.table_no
    ADD CONSTRAINT table_pkey PRIMARY KEY (table_id);
 =   ALTER TABLE ONLY public.table_no DROP CONSTRAINT table_pkey;
       public         postgres    false    203            �
           2606    16484    table_list tables_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.table_list
    ADD CONSTRAINT tables_pkey PRIMARY KEY (table_no);
 @   ALTER TABLE ONLY public.table_list DROP CONSTRAINT tables_pkey;
       public         postgres    false    196            �
           2606    16852    orders uc_drink 
   CONSTRAINT     Y   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT uc_drink UNIQUE (table_no, drinks_id);
 9   ALTER TABLE ONLY public.orders DROP CONSTRAINT uc_drink;
       public         postgres    false    199    199            �
           2606    16850    orders uc_food 
   CONSTRAINT     W   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT uc_food UNIQUE (table_no, items_id);
 8   ALTER TABLE ONLY public.orders DROP CONSTRAINT uc_food;
       public         postgres    false    199    199            �
           2606    16522    table_no uk_items 
   CONSTRAINT     M   ALTER TABLE ONLY public.table_no
    ADD CONSTRAINT uk_items UNIQUE (items);
 ;   ALTER TABLE ONLY public.table_no DROP CONSTRAINT uk_items;
       public         postgres    false    203            �
           2606    16537    table_no uk_items_p 
   CONSTRAINT     V   ALTER TABLE ONLY public.table_no
    ADD CONSTRAINT uk_items_p UNIQUE (items, price);
 =   ALTER TABLE ONLY public.table_no DROP CONSTRAINT uk_items_p;
       public         postgres    false    203    203            �
           1259    16789    fki_drinks_fk    INDEX     D   CREATE INDEX fki_drinks_fk ON public.items USING btree (drinks_id);
 !   DROP INDEX public.fki_drinks_fk;
       public         postgres    false    197            �
           1259    16831    idx_unique_orders_table_drinks    INDEX     �   CREATE UNIQUE INDEX idx_unique_orders_table_drinks ON public.orders USING btree (table_no, drinks_id) WHERE (drinks_id IS NOT NULL);
 2   DROP INDEX public.idx_unique_orders_table_drinks;
       public         postgres    false    199    199    199            �
           1259    16846    idx_unique_orders_table_items    INDEX     �   CREATE UNIQUE INDEX idx_unique_orders_table_items ON public.orders USING btree (table_no, items_id) WHERE (items_id IS NOT NULL);
 1   DROP INDEX public.idx_unique_orders_table_items;
       public         postgres    false    199    199    199            �
           2606    16802    orders drink_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT drink_fk FOREIGN KEY (drinks_id) REFERENCES public.drinks(drinks_id) ON UPDATE CASCADE ON DELETE CASCADE;
 9   ALTER TABLE ONLY public.orders DROP CONSTRAINT drink_fk;
       public       postgres    false    2711    201    199            �
           2606    16790    items drinks_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.items
    ADD CONSTRAINT drinks_fk FOREIGN KEY (drinks_id) REFERENCES public.drinks(drinks_id) ON UPDATE CASCADE ON DELETE CASCADE;
 9   ALTER TABLE ONLY public.items DROP CONSTRAINT drinks_fk;
       public       postgres    false    201    2711    197            �
           2606    16499 	   orders fk    FK CONSTRAINT     o   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk FOREIGN KEY (items_id) REFERENCES public.items(items_id);
 3   ALTER TABLE ONLY public.orders DROP CONSTRAINT fk;
       public       postgres    false    199    2701    197            �
           2606    16529    orders table_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT table_fk FOREIGN KEY (table_no) REFERENCES public.table_list(table_no) ON UPDATE CASCADE ON DELETE CASCADE;
 9   ALTER TABLE ONLY public.orders DROP CONSTRAINT table_fk;
       public       postgres    false    196    199    2698                =   x�3�t*MJ�IUIM�4250�2��H�D�M8�K�3�Kb�8́|CN���D03F��� >�         �   x�5�?�0��ݧ���寫��#K��@k���O/��~��㣗E�`d߇���'q$�j�4����G�Kת"�S����C���R<�if]w�
�ex�Y9vيY�q��)Sw�tZ�;��/�ٖ��~�q��:��&Չ�~L3             x������ � �         ?   x�3�,IL�I�7���2������,c.(�(o
e��fP�YL�g��P�W� ��}      "      x������ � �     