delimiter $$
create procedure listarCamposNotificacoesComCursor()
begin
    declare v_fim_loop int default 0;
    
    declare v_numero_notificacao, v_nome_completo, v_cpf text;
    declare v_data_nascimento datetime(6);
	
    declare c_notificacoes cursor for (
		select numeroNotificacao, nomeCompleto, cpf, dataNascimento from notificacao
    );
    
    declare continue handler for not found set v_fim_loop = 1;

    open c_notificacoes;

    repeat 
		fetch c_notificacoes into v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento;
		
		select v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento;
    until v_fim_loop > 0 end repeat; 
    
    close c_notificacoes;
end
$$
delimiter ;

drop procedure listarCamposNotificacoesComCursor;
call listarCamposNotificacoesComCursor();