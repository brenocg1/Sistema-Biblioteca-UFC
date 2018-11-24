create view vw_livro
as
 select l.titulo, 
	a.nome as Autor,
    `descricao-categoria` as Categoria,
    l.editora as Editora,
    l.`ano-de-lancamento` as Ano_Publicado
	from tb_livro l 
	natural left join tb_categoria c
    natural left join tb_livro_autor la 
    natural left join tb_autor a
    order by l.titulo;
