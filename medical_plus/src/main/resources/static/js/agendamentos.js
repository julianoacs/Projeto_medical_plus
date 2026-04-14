const exameSelect = document.getElementById('exameSelect');
    const medicoSelect = document.getElementById('medicoSelect');

    // Filtrar médicos por exame
    exameSelect.addEventListener('change', () => {
        const exameEscolhido = exameSelect.value;
        const options = medicoSelect.querySelectorAll('option');

        options.forEach(opt => {
            if(opt.value === "") return; // placeholder
            const examesDoMedico = opt.dataset.exames ? opt.dataset.exames.split(',') : [];
            opt.style.display = examesDoMedico.includes(exameEscolhido) ? 'block' : 'none';
        });

        medicoSelect.value = ""; // limpa seleção anterior
    });

    // Validar se médico está disponível
    function validarForm() {
        const medicoSelecionado = medicoSelect.value;
        if(!medicoSelecionado) {
            alert("Selecione um médico disponível para o exame escolhido!");
            return false;
        }
        return true;
    }