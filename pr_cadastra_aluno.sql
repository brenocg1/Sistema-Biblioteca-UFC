delimiter $$;

CREATE PROCEDURE `pr_cadastra_aluno`(var_nm_pessoa varchar(45), var_endereco varchar(100),var_cd_curso int, var_matricula int,var_dt_ingresso date)
begin

	declare var_cd_pessoa int;

	insert into tb_pessoa (nome,endereco) values (var_nm_pessoa,var_endereco);
    
    set var_cd_pessoa = last_insert_id();
    
    insert into tb_aluno (matricula,`data-de-ingresso`,`data-de-conclusao-prevista`,cod_curso,cod_pessoa)
    values (var_matricula, var_dt_ingresso, date_add(var_dt_ingresso,interval 5 year), var_cd_curso, var_cd_pessoa);

end $$;
delimiter ;
