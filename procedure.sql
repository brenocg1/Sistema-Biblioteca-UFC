delimiter $$;

drop procedure pr_cadastra_emprestimo;

create procedure pr_cadastra_emprestimo(var_cod_pessoa int ,var_isbn varchar(13))
begin

	declare tipo_acesso char(1);
    declare dt_devolucao date;
	
    if exists (select `cod-usuario` from tb_usuario where `tipo-acesso` = 'alun' and `cod-pessoa` = var_cod_pessoa) then
        set tipo_acesso := 'a';        		  
    elseif exists (select `cod-usuario` from tb_usuario where `tipo-acesso` = 'func' and `cod-pessoa` = var_cod_pessoa) then
		set tipo_acesso := 'f';
	else
		set tipo_acesso := 'p';
    end if;
    
    if (tipo_acesso = 'a') then
		set dt_devolucao = date_add(now(), interval 15 day);
    end if;
    
	if (@tipo_acesso = 'f') then
		set @dt_devolucao = date_add(now(), interval 21 day);
    end if;
    
	if (@tipo_acesso = 'p') then
		set @dt_devolucao = date_add(now(), interval 30 day);
    end if;
    
	insert into tb_emprestimo (`data-devolucao`, `data-emprestimo` , isbn, `cod-pessoa`) values (@dt_devolucao, now(), var_isbn, var_cod_pessoa);

end $$;
delimiter ;
