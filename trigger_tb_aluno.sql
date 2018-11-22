delimiter $$;

drop trigger tr_inc_aluno;

create trigger tr_inc_aluno after insert on tb_aluno
for each row
begin

	if exists (select `cod-aluno` from tb_aluno where `data-de-conclusao-prevista` <  cast(now() as date)) then

		signal sqlstate '45000' set message_text = 'Insercao nao permitida!';

	end if;


end $$;
delimiter ;
											      


