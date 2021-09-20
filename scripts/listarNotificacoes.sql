delimiter $$
create procedure listarNotificacoes()
begin
	select numeroNotificacao from notificacao;
end
$$
delimiter ;

drop procedure listarNotificacoes;

show procedure status where Db = 'susbase';

call listarNotificacoes();