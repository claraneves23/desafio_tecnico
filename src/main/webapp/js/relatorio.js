function submitForm(action) {
         var form = document.getElementById('relatorioForm');
         form.action = action + '.action';
         form.submit();
}

