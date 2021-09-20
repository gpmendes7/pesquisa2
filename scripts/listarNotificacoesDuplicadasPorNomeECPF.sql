delimiter $$
create procedure listarNotificacoesDuplicadasPorNomeCompletoECpf(
    in numeroNotificacao text,
	in nomeCompleto text,
    in cpf text
)
begin
	select n.numeroNotificacao
	from notificacao n
	where n.nomeCompleto = nomeCompleto 
	and n.cpf = cpf
    and n.numeroNotificacao != numeroNotificacao;
end
$$
delimiter ;

drop procedure listarNotificacoesDuplicadasPorNomeCompletoECpf;

show procedure status where Db = 'susbase';

call listarNotificacoesDuplicadasPorNomeCompletoECpf('332043021095', 'ANA CAROLINA BATISTA DO NASCIMENTO', '188.517.687-22');