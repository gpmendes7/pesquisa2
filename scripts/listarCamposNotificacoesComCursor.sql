delimiter $$
create procedure listarCamposNotificacoesComCursor()
begin
    declare v_numero_notificacao, v_nome_completo, v_cpf text;
    declare v_data_nascimento datetime(6);
	
    declare c_notificacoes cursor for (
		select numeroNotificacao, nomeCompleto, cpf, dataNascimento from notificacao
    );

    open c_notificacoes;
    
    fetch c_notificacoes into v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento;
    
    select v_numero_notificacao, v_nome_completo, v_cpf, v_data_nascimento;
    
    close c_notificacoes;
end
$$
delimiter ;

drop procedure listarCamposNotificacoesComCursor;
call listarCamposNotificacoesComCursor();