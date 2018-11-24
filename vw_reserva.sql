create view vw_reserva
as
select * from tb_pessoa natural join tb_reserva natural join tb_livro;
