function prepararExclusao(id, tipo) {
    
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
    }
   
    var modal = new bootstrap.Modal(document.getElementById('confirmarExclusao'));
    modal.show();
}

function limparFormulario() {
    
    const inputs = document.querySelectorAll('input:not([readonly]), textarea:not([readonly])');
    inputs.forEach(input => {
 
        switch(input.type) {
            case 'text':
            case 'textarea':
                input.value = '';
                break;
            case 'date':
            case 'time':
                input.value = '';
                break;
            case 'checkbox':
            case 'radio':
                input.checked = false;
                break;
            default:
                input.value = '';
        }
    });
    
	
    const selects = document.querySelectorAll('select');
    selects.forEach(select => {
        select.selectedIndex = 0;
    });
    
    const alertasErro = document.querySelectorAll('.alert.alert-danger');
    alertasErro.forEach(alerta => {
        alerta.remove();
    });
    
    const primeiroCampo = document.querySelector('input:not([readonly]):not([type="hidden"]), select, textarea');
    if (primeiroCampo) {
        setTimeout(() => {
            primeiroCampo.focus();
        }, 100);
    }
    
}

