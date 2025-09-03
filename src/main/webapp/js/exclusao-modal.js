function prepararExclusao(id, tipo) {
    console.log("Preparando exclusão do " + tipo + " ID: " + id);
    
    var link = document.getElementById('linkExclusao');
    if (link) {
       
        var url;
        switch(tipo) {
            case 'funcionario':
                url = 'excluirFuncionarios.action?funcionarioVo.rowid=' + id;
                break;
            case 'agenda':
                url = 'excluirAgendas.action?agendaVo.idAgenda=' + id;
                break;
            case 'compromisso':
                url = 'excluirCompromissos.action?compromissoVo.idCompromisso=' + id;
                break;
            default:
                console.error('Tipo não reconhecido:', tipo);
                return;
        }
        
        link.href = url;
        console.log("Link atualizado: " + link.href);
    }
   
    var modal = new bootstrap.Modal(document.getElementById('confirmarExclusao'));
    modal.show();
}